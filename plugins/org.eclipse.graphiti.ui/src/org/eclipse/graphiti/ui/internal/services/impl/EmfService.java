/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getObjectName(java.
	 * lang.Object)
	 */
	public String getObjectName(final Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Obj must not be null"); //$NON-NLS-1$
		}

		// shortcut for MOF
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getEObject(java.lang
	 * .Object)
	 */
	public EObject getEObject(Object object) {
		// if object directly adapts to target type, take this
		//EObject refBaseObject = adaptObject(object, EObject.class);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getObjectNameAttribute
	 * (org.eclipse.emf.ecore.EObject)
	 */
	public Entry<EAttribute, String> getObjectNameAttribute(final EObject eObject) {
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
				// translatable texts
				//				if (value instanceof TranslatableText) {
				//					final String text = valueAsString(value);
				//					return Collections.singletonMap(attr, text).entrySet().iterator().next();
				//				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getFile(org.eclipse
	 * .emf.ecore.EObject)
	 */
	public IFile getFile(EObject object) {
		IFile result = null;
		final Resource resource = object.eResource();
		if (resource != null) {
			final ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				result = getFile(resource.getURI(), resourceSet);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getFile(org.eclipse
	 * .emf.common.util.URI,
	 * org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	public IFile getFile(URI uri, TransactionalEditingDomain editingDomain) {
		return getFile(uri, editingDomain.getResourceSet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getFile(org.eclipse
	 * .emf.common.util.URI, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public IFile getFile(URI uri, ResourceSet resourceSet) {
		if (uri == null) {
			return null;
		}

		//		// Check extension point
		//		if (resourceSet != null) {
		//			IFile file = UriConverter.resolve(resourceSet, uri);
		//			if (file != null && file.exists()) {
		//				return file;
		//			}
		//		}

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		// File URIs
		final String filePath = getWorkspaceFilePath(uri.trimFragment());
		if (filePath == null) {
			final IPath location = Path.fromOSString(uri.toString());
			final IFile file = workspaceRoot.getFileForLocation(location);
			if (file != null) {
				return file;
			}

			return null;
		}

		// Platform resource URIs
		else {
			final IResource workspaceResource = workspaceRoot.findMember(filePath);
			if (workspaceResource == null || workspaceResource.getType() != IResource.FILE) {
				// Alternative ?
				final String fileName = uri.toString();
				if (fileName != null) {
					// TODO check
					//					IPath uriPath = new Path(fileName).makeUNC(true).makeRelative().removeFirstSegments(1);
					//					IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
					//					for (int i = 0; i < projects.length; i++) {
					//						IPath projectPath = projects[i].getLocation();
					//						if (uriPath.matchingFirstSegments(projectPath) == projectPath.segmentCount()) {
					//							IProject project = projects[i];
					//
					//							break;
					//						}
					//					}

					//					IFile files = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(fileName));
					//					if (files.length > 0) {
					//						IProject project = files[0].getProject();
					//					}
				}

				return null;
			}
			return (IFile) workspaceResource;
		}
	}

	private String getWorkspaceFilePath(URI uri) {
		if (uri.isPlatform()) {
			return uri.toPlatformString(true);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getEditingDomain(org
	 * .eclipse.emf.ecore.resource.ResourceSet)
	 */
	public TransactionalEditingDomain getEditingDomain(ResourceSet resourceSet) {
		return TransactionUtil.getEditingDomain(resourceSet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getResourceSet(org.
	 * eclipse.emf.ecore.EObject)
	 */
	public ResourceSet getResourceSet(EObject object) {
		ResourceSet resourceSet = null;
		if (object == null) {
			throw new IllegalArgumentException("Object must not be null"); //$NON-NLS-1$
		}
		final Resource resource = object.eResource();
		if (resource != null) {
			resourceSet = resource.getResourceSet();
		}
		return resourceSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getEditingDomain(org
	 * .eclipse.emf.ecore.EObject)
	 */
	public TransactionalEditingDomain getEditingDomain(EObject object) {
		final ResourceSet resourceSet = getResourceSet(object);
		if (resourceSet != null) {
			return getEditingDomain(resourceSet);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#save(org.eclipse.emf
	 * .transaction.TransactionalEditingDomain)
	 */
	@SuppressWarnings("unchecked")
	public void save(TransactionalEditingDomain editingDomain) throws WrappedException {
		save(editingDomain, Collections.EMPTY_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#save(org.eclipse.emf
	 * .transaction.TransactionalEditingDomain, java.util.Map)
	 */
	public void save(TransactionalEditingDomain editingDomain, Map<Resource, Map<?, ?>> options) {
		saveInWorkspaceRunnable(editingDomain, options);
	}

	private void saveInWorkspaceRunnable(final TransactionalEditingDomain editingDomain, final Map<Resource, Map<?, ?>> options) {

		final Map<URI, Throwable> failedSaves = new HashMap<URI, Throwable>();
		final IWorkspaceRunnable wsRunnable = new IWorkspaceRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException {

				final Runnable runnable = new Runnable() {

					@Override
					public void run() {
						Transaction parentTx;
						if (editingDomain != null
								&& (parentTx = ((TransactionalEditingDomainImpl) editingDomain).getActiveTransaction()) != null) {
							do {
								if (!parentTx.isReadOnly()) {
									throw new IllegalStateException(
											"ModelUtil.save() called from within a command (likely produces a deadlock)");
								}
							} while ((parentTx = ((TransactionalEditingDomainImpl) editingDomain).getActiveTransaction().getParent()) != null);
						}

						final EList<Resource> resources = editingDomain.getResourceSet().getResources();
						// Copy list to an array to prevent
						// ConcurrentModificationExceptions
						// during the saving of the dirty resources
						Resource[] resourcesArray = new Resource[resources.size()];
						resourcesArray = resources.toArray(resourcesArray);
						final Set<Resource> savedResources = new HashSet<Resource>();
						for (int i = 0; i < resourcesArray.length; i++) {
							// In case resource modification tracking is
							// switched on, we can check if a resource
							// has been modified, so that we only need to same
							// really changed resources; otherwise
							// we need to save all resources in the set
							final Resource resource = resourcesArray[i];
							if (!resource.isTrackingModification() || resource.isModified()) {
								try {
									resource.save(options.get(resource));
									savedResources.add(resource);
								} catch (final Throwable t) {
									failedSaves.put(resource.getURI(), t);
								}
							}
						}
					}
				};

				try {
					editingDomain.runExclusive(runnable);
				} catch (final InterruptedException e) {
					throw new RuntimeException(e);
				}
				// For the time being, we clear the command stack after
				// saving.
				// In a later sprint we might try to implement undo/redo
				// across save calls (not that easy
				// to handle the ResourceImpl.isModified flag, which is also
				// set by save() outside any command).
				editingDomain.getCommandStack().flush();
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(wsRunnable, null);
			if (!failedSaves.isEmpty()) {
				throw new WrappedException(createMessage(failedSaves), new RuntimeException());
			}
		} catch (final CoreException e) {
			final Throwable cause = e.getStatus().getException();
			if (cause instanceof RuntimeException) {
				throw (RuntimeException) cause;
			}
			throw new RuntimeException(e);
		}
	}

	private String createMessage(Map<URI, Throwable> failedSaves) {
		final StringBuilder buf = new StringBuilder("The following resources could not be saved:");
		for (final Entry<URI, Throwable> entry : failedSaves.entrySet()) {
			buf.append("\nURI: ").append(entry.getKey().toString()).append(", cause: \n").append(getExceptionAsString(entry.getValue()));
		}
		return buf.toString();
	}

	private String getExceptionAsString(Throwable t) {
		final StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		final String result = stringWriter.toString();
		try {
			stringWriter.close();
		} catch (final IOException e) {
			// $JL-EXC$ ignore
		}
		printWriter.close();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#toString(org.eclipse
	 * .emf.ecore.EObject, java.lang.StringBuilder)
	 */
	public StringBuilder toString(final EObject o, final StringBuilder result) {
		final EClass metaObject = o.eClass();
		// type
		result.append(metaObject.getName());
		result.append(" \""); //$NON-NLS-1$
		// human-readable name
		result.append(getObjectName(o));
		result.append("\": "); //$NON-NLS-1$
		// MRI
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
				result.append(valueAsString(attr.getValue()));
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

	/**
	 * @return the given value as a string
	 */
	private String valueAsString(final Object value) {

		return String.valueOf(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getDiagramFromFile(
	 * org.eclipse.core.resources.IFile,
	 * org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public Diagram getDiagramFromFile(IFile file, ResourceSet resourceSet) {

		// Get the URI of the model file.
		URI resourceURI = getFileURI(file, resourceSet);

		// Demand load the resource for this file.
		Resource resource;
		try {
			resource = resourceSet.getResource(resourceURI, true);
			if (resource != null) {
				// does resource contain a diagram as root object?
				EList<EObject> contents = resource.getContents();
				for (EObject object : contents) {
					if (object instanceof Diagram) {
						return (Diagram) object;
					}
				}
			}
		} catch (WrappedException e) {
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.IEmfService#getFileURI(org.eclipse
	 * .core.resources.IFile, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public URI getFileURI(IFile file, ResourceSet resourceSet) {
		String pathName = file.getFullPath().toString();
		URI resourceURI = URI.createFileURI(pathName);
		resourceURI = resourceSet.getURIConverter().normalize(resourceURI);
		return resourceURI;
	}

	/**
	 * Creates a {@link TransactionalEditingDomain} with a {@link ResourceSet}
	 * resource set and a {@link IWorkspaceCommandStack} command stack.
	 * 
	 * @return a {@link TransactionalEditingDomain} editing domain
	 */
	public TransactionalEditingDomain createResourceSetAndEditingDomain() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		final IWorkspaceCommandStack workspaceCommandStack = new GFWorkspaceCommandStackImpl(new DefaultOperationHistory());
		final TransactionalEditingDomainImpl editingDomain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), workspaceCommandStack, resourceSet);
		return editingDomain;
	}
}
