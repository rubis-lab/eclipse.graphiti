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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

/**
 * The model based editor input for {@link DiagramEditor} diagram editors.
 * Basically a {@linkTransactionalEditingDomain} with an already existing
 * {@linkResourceSet} is hosted to resolve an {@link EObject} from an
 * {@link URI} or {@link URI} String. Some helper methods are added.
 * 
 * @see {@link IEditorInput}
 * @see {@link IPersistableElement}
 * @see {@link DiagramEditorInputDisposingTED}
 * @see {@link DiagramEditorFactory}
 * @see {@link DiagramEditor}
 */
public class DiagramEditorInput implements IEditorInput, IPersistableElement {

	/**
	 * The stored {@link URI} string
	 */
	private final String uriName;

	/**
	 * The key for the stored {@link URI} string
	 */
	public static final String KEY_URI = "uri"; //$NON-NLS-1$

	/**
	 * The key for the stored object name
	 */
	public static final String KEY_OBJECT_NAME = "objectName"; //$NON-NLS-1$

	/**
	 * The cached input name
	 * 
	 * @see #getLiveName()
	 */
	private String mame;

	/**
	 * The cached input tooltip
	 * 
	 * @see #getLiveToolTipText()
	 */
	private String tooltip;

	/**
	 * The editor's {@link TransactionalEditingDomain} set by
	 * {@link #setEditorEditingDomain(TransactionalEditingDomain)}. Its
	 * ResourcSet is used to resolve {@link #uriName} into live objects.
	 * 
	 * @see #getEditingDomain()
	 */
	protected TransactionalEditingDomain editingDomain;
	/**
	 * The ID of the diagram type provider.
	 */
	private String providerId;

	/**
	 * The key for the ID of the diagram type provider.
	 */
	public static String KEY_PROVIDER_ID = "PROVIDERID"; //$NON-NLS-1$

	/**
	 * Creates an input out of a {@link URI} string and a transactional editing
	 * domain. For resolving the {@link URI} to an {@link EObject} its
	 * {@link ResourceSet} is used. The ResourceSet of the editing domain must
	 * have been already set from outside. A diagram type provider ID is hold in
	 * this class.
	 * 
	 * @param diagramUriString
	 *            A {@link URI} string as returned by {@link URI#toString()}
	 *            that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}
	 * @param providerID2
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditor}
	 * @throws IllegalArgumentException
	 *             if <code>uriString</code> parameter is null
	 * @see URI
	 */
	public DiagramEditorInput(String diagramUriString, TransactionalEditingDomain domain, String providerID2) {
		if (diagramUriString == null) {
			throw new IllegalStateException("diagramUriString must not be null"); //$NON-NLS-1$
		}
		this.uriName = diagramUriString;
		setEditorEditingDomain(domain);
		setProviderId(providerID2);
	}

	/**
	 * Creates an input out of a {@link URI} string and a transactional editing
	 * domain. For resolving the {@link URI} to an {@link EObject} its
	 * {@link ResourceSet} is used. The ResourceSet of the editing domain must
	 * have been already set from outside. A diagram type provider ID is hold in
	 * this class.Creates an input out of a {@link URI} string and a
	 * transactional editing domain. For resolving the {@link URI} to an
	 * {@link EObject} its {@link ResourceSet} is used. The ResourceSet of the
	 * editing domain must have been already set from outside. A diagram type
	 * provider ID is hold in this class.
	 * 
	 * @param diagramUri
	 *            a {@link URI} that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}
	 * @param providerID2
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditor}
	 * @throws IllegalArgumentException
	 *             , if <code>uri</code> parameter is null
	 * @see #DiagramEditorInputBase(String, TransactionalEditingDomain)
	 * @see URI
	 */
	public DiagramEditorInput(URI diagramUri, TransactionalEditingDomain domain, String providerID2) {
		if (diagramUri == null) {
			throw new IllegalStateException("diagramUri must not be null"); //$NON-NLS-1$
		}
		this.uriName = diagramUri.toString();
		setEditorEditingDomain(domain);
		setProviderId(providerID2);
	}

	/**
	 * Creates an editor input {@link DiagramEditorInput} or a
	 * {@link DiagramEditorInputDisposingTED} with a self created {@link}
	 * TransactionalEditingDomain editing domain, which must be disposed later
	 * on.
	 * 
	 * @param diagram
	 *            A {@link Diagram}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}
	 * @param providerId
	 *            A {@link String} which holds the diagram type id.
	 * @param disposeEditingDomain
	 *            A {@link Boolean}, when true a
	 *            {@link DiagramEditorInputDisposingTED} is created, otherwise a
	 *            {@link DiagramEditorInput}
	 * @return A {@link DiagramEditorInput} editor input
	 */
	public static DiagramEditorInput createEditorInput(Diagram diagram, TransactionalEditingDomain domain, String providerId,
			boolean disposeEditingDomain) {
		final Resource resource = diagram.eResource();
		if (resource == null) {
			throw new IllegalArgumentException();
		}
		final String fragment = resource.getURIFragment(diagram);
		final URI fragmentUri = resource.getURI().appendFragment(fragment);
		DiagramEditorInput diagramEditorInput;
		if (disposeEditingDomain) {
			diagramEditorInput = new DiagramEditorInputDisposingTED(fragmentUri, domain, providerId);
		} else {
			diagramEditorInput = new DiagramEditorInput(fragmentUri, domain, providerId);
		}
		return diagramEditorInput;
	}

	/**
	 * Gets the diagram.
	 * 
	 * @return Returns the diagram.
	 */
	public Diagram getDiagram() {
		return (Diagram) getAdapter(Diagram.class);
	}

	/**
	 * Gets the diagram type provider id.
	 * 
	 * @return Returns the providerId.
	 */
	public String getProviderId() {
		return this.providerId;
	}

	/**
	 * Sets the diagram type provider id.
	 * 
	 * @param providerId
	 *            The providerId to set.
	 */
	protected void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getFactoryId() {
		return DiagramEditorFactory.class.getName();
	}

	public void dispose() {
		// do nothing

	}

	/**
	 * @return <code>null</code>
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/**
	 * @return The cached name or the input's {@link URI} string
	 * @see #getLiveName()
	 */
	public String getName() {
		if (this.mame != null) {
			return this.mame;
		}
		return this.uriName;
	}

	protected boolean hasName() {
		return this.mame != null;
	}

	protected void setName(String name) {
		this.mame = name;
	}

	/**
	 * @return The cached tooltip or the input's {@link URI} string
	 * @see #getLiveToolTipText()
	 */
	public String getToolTipText() {
		if (this.tooltip != null) {
			return this.tooltip;
		}
		return getName();
	}

	/**
	 * Returns a name for the input from the {@link Diagram}, when possible.
	 * Otherwise it tries to get a human readable name from the hold EObject of
	 * the editor input.
	 * 
	 * @return A name from Diagram or from the EObject of the editor input or
	 *         <code>null</code> to indicate that a it can currently not be
	 *         obtained.
	 * @see IEditorInput#getName()
	 * @see #getLiveName()
	 */
	protected String getLiveName() {
		final Diagram diagram = getDiagram();
		if (diagram != null) {
			return diagram.getName();
		}
		final EObject object = getEObject();
		if (object != null) {
			final String name = GraphitiUiInternal.getEmfService().getObjectName(object);
			return name;
		}
		return null;
	}

	/**
	 * Returns a tooltip for the input from the {@link Diagram} object, when
	 * possible. Otherwise it tries to return the object's live name and type.
	 * 
	 * @return a tooltip from the Diagram or the hold object or
	 *         <code>null</code> to indicate that a it can currently not be
	 *         obtained.
	 * @see IEditorInput#getToolTipText()
	 */
	protected String getLiveToolTipText() {
		final Diagram diagram = getDiagram();
		if (diagram != null && diagram.getDiagramTypeId() != null) {
			final String id = diagram.getDiagramTypeId();
			if (id != null) {
				String tip = getLiveName();
				tip += " (" + id + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				return tip;
			}
		}
		final EObject object = getEObject();
		if (object != null) {
			final String name = getLiveName();
			final String typeName = object.eClass().getName();
			final String tip = name + " (" + typeName + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			return tip;
		}
		return null;
	}

	/**
	 * Returns the concrete model element represented by the given URI, if the
	 * requested adapter is either the same class or super class. In addition
	 * the method examines all composition parents and checks whether or not
	 * they are of the requested class or a sub class.
	 * <ul>
	 * <li>get model element for uri</li>
	 * <li>if adapter == modelElement.class return modelElement</li>
	 * <li>if adapter == modelElement.superclass return modelElement</li>
	 * <li>if adapter == modelElement.compositionParent.class return
	 * modelElement</li>
	 * <li>if adapter == modelElement.anyCompositionParent.class return
	 * modelElement</li>
	 * <li>if adapter == ResourceSet return resourceSet</li>
	 * <li>if adapter == TransactionalEditingDomain return editingDomain</li>
	 * <li>if adapter == IFile returns the file (aka. Resource) in which the
	 * root element of this editor is contained</li>
	 * </ul>
	 * 
	 * @param adapter
	 *            The adapter class to look up
	 * @return A object castable to the given class, or <code>null</code> if
	 *         this object does not have an adapter for the given class
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {

		if (Diagram.class.isAssignableFrom(adapter)) {
			final EObject obj = getEObject();
			if (obj instanceof Diagram) {
				return obj;
			}
		} else if (EObject.class.isAssignableFrom(adapter)) {
			EObject eObject = null;
			try {
				eObject = getEObject(adapter);
			} catch (final Exception e) {
				// Not able to adapt to eObject, object has been deleted, file was deleted...
				// adapt must deliver null
				//TODO IStatus status = new Status(IStatus.WARNING, 0, e.getMessage(), e, EmfFwkUIPlugin.PLUGIN_ID);
				T.racer().log(IStatus.INFO, e.getMessage());
				return null;
			}
			return eObject;
		} else if (TransactionalEditingDomain.class.isAssignableFrom(adapter)) {
			return getEditingDomain();
		} else if (ResourceSet.class.isAssignableFrom(adapter)) {
			return getEditingDomain().getResourceSet();
		} else if (IFile.class == adapter) {
			final EObject refObject = getEObject();
			if (refObject != null) {
				return GraphitiUiInternal.getEmfService().getFile(refObject);
			}
		}
		return null;
	}

	/**
	 * Saves {@link URI} string and object name
	 */
	public void saveState(IMemento memento) {
		//TODO
		// Do not store anything for deleted objects
		boolean exists = exists();
		if (!exists) {
			return;
		}
		// Store object name, URI and diagram type provider ID
		memento.putString(KEY_URI, this.uriName);
		memento.putString(KEY_OBJECT_NAME, getName());
		memento.putString(KEY_PROVIDER_ID, this.providerId);
	}

	/**
	 * Called from constructor to populate the editor's
	 * {@link TransactionalEditingDomain} to its input. The resourceSet of the
	 * TransactionalEditingDomain is needed on requests to resolve the stored
	 * {@link #uriName}.
	 * 
	 * @param editingDomain
	 *            the current transactional editing domain
	 * @throws IllegalArgumentException
	 *             if <code>editingDomain</code> parameter is null
	 * @see #getEditingDomain()
	 */
	private void setEditorEditingDomain(TransactionalEditingDomain editingDomain) {
		if (editingDomain == null) {
			throw new IllegalArgumentException("EditingDomain must not be null"); //$NON-NLS-1$
		}

		this.editingDomain = editingDomain;
		final EObject obj = getEObject(EObject.class);
		if (obj != null) {
			if (this.mame == null) {
				this.mame = getLiveName();
			} else {
				final String newName = getLiveName();
				if (newName != null) {
					this.mame = newName;
				}
			}
			this.tooltip = getLiveToolTipText();
		}
	}

	/**
	 * Returns the model object the editor input is working on.
	 * 
	 * @return The object or <code>null</code> if no such object can be
	 *         obtained, e.g. if the input's editor is not yet initialised.
	 */
	public final EObject getEObject() {
		final EObject object = getEObject(EObject.class);
		return object;
	}

	/**
	 * @return The {@link URI} string this input and its editors operate on
	 */
	public final String getUriString() {
		return this.uriName;
	}

	/**
	 * @return <code>true</code> if the input's state denotes a living EMF
	 *         object <br>
	 *         Note: The editor gets only restored, when <code>true</code> is
	 *         returned.
	 */
	public boolean exists() {
		if (this.uriName == null) {
			return false;
		}
		final URI mri = getUri();
		if (mri == null) {
			return false;
		}
		EObject eObject = null;
		try {
			eObject = getEObject();
		} catch (final Exception e) {
			// The object cannot be resolved --> it does not exist (prevent to appear in navigation history)
			return false;
		}
		return eObject != null;
	}

	/**
	 * @return this input if it is persistable, otherwise null
	 */
	public IPersistableElement getPersistable() {
		IPersistableElement element = null;
		EObject obj = null;
		try {
			obj = getEObject();
		} catch (WrappedException e) {
			// JL-EXC$
			// Ignore (object may not exists, even resource may be gone
		}
		if (obj != null) {
			// The input is only persistable in case an EObject can be retrieved
			// (this is e.g. relevant for removing entries from the editor navigation history for deleted objects)
			element = this;
		}
		return element;
	}

	/**
	 * This method returns an instance which is either of the same
	 * <code>Class</code> object a sub class or sub interface of the class
	 * defined by <code>adapter</code> - <code>null</code> otherwise. The
	 * implementation also checks composite children.
	 * 
	 * @param adapter
	 * 
	 * @return
	 */
	private EObject getEObject(Class<EObject> adapter) {
		final URI uri = getUri();
		if (uri != null) {
			final TransactionalEditingDomain editingDomain = getEditingDomain();
			if (editingDomain != null) {
				// First try the URI resolution without loading not yet loaded resources
				// because calling with loadOnDemand will _always_ create a new Resource
				// instance for newly created and not yet saved Resources, no matter if
				// they already exist within the ResourceSet or not
				EObject modelElement = editingDomain.getResourceSet().getEObject(uri, false);
				if (modelElement == null) {
					modelElement = editingDomain.getResourceSet().getEObject(uri, true);
					if (modelElement == null) {
						return null;
					}
				}

				modelElement.eResource().setTrackingModification(true);

				// Climb up the chain of composition. Also uses a counter to
				// prevent infinite loops
				// in case refImmediateComposite does not return null
				// TODO ModelEditorInput must not adapt to composite
				// children
				// as this is completely unpredictable - e.g. the first
				// element in an unordered collection might be returned
				int i = 0;
				EObject o = modelElement;
				do {
					if (adapter.isAssignableFrom(o.getClass())) {
						return o;
					}

					o = o.eContainer();
				} while (o != null && ++i < 1000);
			}
		}
		return null;
	}

	/**
	 * @return the resolved {@link URI} or <code>null</code> in case of failures
	 */
	private URI getUri() {
		if (this.uriName == null) {
			return null;
		}
		final URI uri = URI.createURI(this.uriName);
		return uri;
	}

	/**
	 * @return The current {@link TransactionalEditingDomain} or
	 *         <code>null</code>
	 */
	private TransactionalEditingDomain getEditingDomain() {
		return this.editingDomain;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DiagramEditorInput other = (DiagramEditorInput) obj;
		if (this.uriName == null) {
			if (other.uriName != null) {
				return false;
			}
		} else if (!this.uriName.equals(other.uriName)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 0; // super.hashCode();
		result = PRIME * result + (this.uriName == null ? 0 : this.uriName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		final String s = super.toString() + " uriName: " + this.uriName + " on TransactionalEditingDomain " //$NON-NLS-1$ 
				+ String.valueOf(getEditingDomain()) + " (null=OK)"; //$NON-NLS-1$
		return s;
	}

}