/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2016 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 329523 - Add notification of DiagramTypeProvider after saving a diagram
 *    mwenz - Bug 320635 - Could not open an existing diagram
 *    tkaiser - Bug 340939 - Only one instance of an Graphiti editor may exist, for each editor input
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 372753 - save shouldn't (necessarily) flush the command stack
 *    mwenz - Bug 371513 - Save failed with NPE when switching editors
 *    mwenz - Bug 393074 - Save Editor Progress Monitor Argument
 *    fvelasco - Bug 412838 - Check for read-only resources before saving
 *    mwenz - Bug 489681 - EmfService.getFile fails with some URIs
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.editor.GFWorkspaceCommandStackImpl;
import org.eclipse.graphiti.ui.internal.services.IEmfService;
import org.eclipse.graphiti.ui.internal.util.ModelElementNameComparator;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class EmfService implements IEmfService {

	private final String LINE_SEP = System.getProperty("line.separator"); //$NON-NLS-1$
	private final String ATTRIBUTE_OBJ_NAME = "name";//$NON-NLS-1$
	private final String ATTRIBUTE_OBJ_ID = "id";//$NON-NLS-1$

	public String getObjectName(final Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Obj must not be null"); //$NON-NLS-1$
		}

		if (obj instanceof ENamedElement) {
			final ENamedElement elem = (ENamedElement) obj;
			final String name = elem.getName();
			if (name != null) {
				return name;
			}
		}
		if (obj instanceof EObject) {
			final EObject refObject = (EObject) obj;
			final Entry<EAttribute, String> attr = getObjectNameAttribute(refObject);
			if (attr != null) {
				final String value = attr.getValue();
				if (value != null) {
					return value;
				}
			}
		}

		return obj.toString();
	}

	public EObject getEObject(Object object) {
		EObject eObject = null;
		if (object != null && object instanceof EObject) {
			eObject = (EObject) object;
			return eObject;
		}
		// unwrap a structured selection
		if (object instanceof IStructuredSelection) {
			if (((IStructuredSelection) object).isEmpty()) {
				return null;
			}
			final Object element = ((IStructuredSelection) object).getFirstElement();
			return getEObject(element);
		}
		return null;
	}

	/**
	 * Returns the EMF attribute that contains a human readable name of a given
	 * object as its value. The method tries to access a modeled attribute
	 * "name" or "id" in this order. If no attribute with this name is modeled,
	 * the first attribute whose value is a String is returned, otherwise
	 * <code>null</code> .
	 * 
	 * @param eObject
	 *            the object to get a name for
	 * @return the attribute (currently "name", id") and the readable name
	 * 
	 * @see #getObjectName(Object)
	 */
	private Entry<EAttribute, String> getObjectNameAttribute(final EObject eObject) {
		final EClass metaObject = eObject.eClass();
		final EList<EAttribute> attrs = metaObject.getEAllAttributes();
		if (attrs != null) {
			for (final EAttribute attr : attrs) {
				final Object value = eObject.eGet(attr);
				// "name"
				if (ATTRIBUTE_OBJ_NAME.equalsIgnoreCase(attr.getName())) {
					if (value != null) {
						final String v = String.valueOf(value);
						return Collections.singletonMap(attr, v).entrySet().iterator().next();
					}
				}
				// "id"
				if (ATTRIBUTE_OBJ_ID.equalsIgnoreCase(attr.getName())) {
					if (value != null) {
						final String v = String.valueOf(value);
						return Collections.singletonMap(attr, v).entrySet().iterator().next();
					}
				}
				// string value
				if (value instanceof String) {
					return Collections.singletonMap(attr, (String) value).entrySet().iterator().next();
				}
			}
		}

		return null;
	}

	public IFile getFile(EObject object) {
		IFile result = null;
		final Resource resource = object.eResource();
		if (resource != null) {
			final ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				result = getFile(resource.getURI());
			}
		}
		return result;
	}
	
	public IFile getFile(URI uri) {
		if (uri == null) {
			return null;
		}

		String filePath = getWorkspaceFilePath(uri.trimFragment());
		if (filePath != null) {
			// Platform resource URIs
			IResource workspaceResource = getWorkspaceRoot().findMember(filePath);
			if (workspaceResource instanceof IFile) {
				return (IFile) workspaceResource;
			}
		} else {
			// File URIs
			IFile file = getFileForUriString(uri.toFileString());
			if (file != null) {
				return file;
			}
			file = getFileForUriString(uri.toString());
			if (file != null) {
				return file;
			}
		}
		return null;
	}

	protected IFile getFileForUriString(String uriString) {
		if (uriString != null) {
			IPath location = Path.fromOSString(uriString);
			if (location != null) {
				IFile file = getWorkspaceRoot().getFileForLocation(location);
				if (file != null) {
					return file;
				}
			}
		}
		return null;
	}

	private IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	private String getWorkspaceFilePath(URI uri) {
		if (uri.isPlatform()) {
			return uri.toPlatformString(true);
		}
		return null;
	}

	public StringBuilder toString(final EObject o, final StringBuilder result) {
		final EClass metaObject = o.eClass();
		// type
		result.append(metaObject.getName());
		result.append(" \""); //$NON-NLS-1$
		// human-readable name
		result.append(getObjectName(o));
		result.append("\": "); //$NON-NLS-1$
		// URI
		result.append(EcoreUtil.getURI(o));
		result.append(LINE_SEP);
		// all attributes with values (sorted by name)
		if (o instanceof EObject) {
			final Map<EAttribute, Object> atts = new TreeMap<EAttribute, Object>(ModelElementNameComparator.INSTANCE_IGNORING_CASE);
			atts.putAll(getAttributesWithValues(o, true));
			final Set<Entry<EAttribute, Object>> attsWithValues = atts.entrySet();
			for (final Entry<EAttribute, Object> attr : attsWithValues) {
				result.append("  "); //$NON-NLS-1$
				result.append(attr.getKey().getName());
				result.append("="); //$NON-NLS-1$
				result.append(String.valueOf(attr.getValue()));
				result.append(LINE_SEP);
			}
		}
		// class name of Jmi interface
		result.append("  class="); //$NON-NLS-1$
		result.append(o.eClass().getInstanceClassName());
		return result;
	}

	private Map<EAttribute, Object> getAttributesWithValues(EObject refObject, boolean b) {
		final EClass metaObject = refObject.eClass();
		final EList<EAttribute> attrs = metaObject.getEAllAttributes();
		final HashMap<EAttribute, Object> result = new HashMap<EAttribute, Object>(attrs.size());
		for (final EAttribute attr : attrs) {
			final Object value = refObject.eGet(attr);
			result.put(attr, value);
		}
		return result;
	}

	public Diagram getDiagramFromFile(IFile file, ResourceSet resourceSet) {
		// Get the URI of the model file.
		URI resourceURI = getFileURI(file);
		// Demand load the resource for this file.
		Resource resource;
		try {
			resource = resourceSet.getResource(resourceURI, true);
			if (resource != null) {
				// does resource contain a diagram as root object?
				URI diagramUri = mapDiagramFileUriToDiagramUri(resourceURI);
				EObject eObject = resource.getEObject(diagramUri.fragment());
				if (eObject instanceof Diagram)
					return (Diagram) eObject;
			}
		} catch (WrappedException e) {
		}
		return null;
	}


	public URI getFileURI(IFile file) {
		String pathName = file.getFullPath().toString();
		URI resourceURI = URI.createPlatformResourceURI(pathName, true);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceURI = resourceSet.getURIConverter().normalize(resourceURI);
		return resourceURI;
	}

	public URI mapDiagramFileUriToDiagramUri(URI diagramFileUri) {
		return diagramFileUri.appendFragment("/0"); //$NON-NLS-1$
	}
	
	public TransactionalEditingDomain createResourceSetAndEditingDomain() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		final IWorkspaceCommandStack workspaceCommandStack = new GFWorkspaceCommandStackImpl(new DefaultOperationHistory());
	
		final TransactionalEditingDomainImpl editingDomain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), workspaceCommandStack, resourceSet);
		WorkspaceEditingDomainFactory.INSTANCE.mapResourceSet(editingDomain);
		return editingDomain;
	}

}
