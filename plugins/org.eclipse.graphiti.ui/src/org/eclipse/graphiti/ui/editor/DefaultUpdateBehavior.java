/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 329523 - Add notification of DiagramTypeProvider after saving a diagram
 *    mwenz - Bug 347152 - Do not log diagnostics errors as errors in the Eclipse error log
 *    mwenz - Bug 359928 - DiagramEditorBehavior does not initialize adapterActive field
 *    Bug 336488 - DiagramEditor API - Rename from DiagramEditorBehavior to DefaultUpdateBehavior
 *    mwenz - Bug 389426 - Add factory method for creating EMF workspace synchronizer delegate
 *    pjpaulin - Bug 352120 - Eliminated assumption that diagram is in an IEditorPart
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz - Bug 427444 - Issues with drill-down diagram editor when diagram file is deleted
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;


import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.editor.DomainModelWorkspaceSynchronizerDelegate;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

/**
 * The default implementation for the {@link IDiagramContainerUI} behavior extension
 * that controls update behavior of the editor and defines the EMF adapters that
 * watch over model object modifications. Clients may subclass to change the
 * behavior; use {@link IDiagramContainerUI#createUpdateBehavior()} to return the
 * instance that shall be used.<br>
 * Note that there is always a 1:1 relation with a {@link IDiagramContainerUI}.
 * 
 * @since 0.9
 */
public class DefaultUpdateBehavior extends PlatformObject implements IEditingDomainProvider, IOperationHistoryListener {

	/**
	 * @since 0.10
	 */
	protected final DiagramBehavior diagramBehavior;

	/**
	 * The editing domain that is used throughout the {@link DiagramBehavior} is
	 * kept here and only here.
	 */
	private TransactionalEditingDomain editingDomain;

	/**
	 * Closes editor if model object is deleted.
	 */
	private ElementDeleteListener elementDeleteListener = null;

	/**
	 * This resource set update adapter is added to the {@link ResourceSet} of
	 * the {@link TransactionalEditingDomain}. When notified, the resource set
	 * adapter adds the passed adapter (the
	 * {@link DefaultUpdateBehavior#updateAdapter} instance held in
	 * {@link DefaultUpdateBehavior}) to the adapters of a newly added Resource
	 * in the ResourceSet or removes the adapter from a removed resource.
	 * 
	 * @see DefaultUpdateBehavior#initializeEditingDomain(TransactionalEditingDomain)
	 */
	private ResourceSetUpdateAdapter resourceSetUpdateAdapter;

	/**
	 * Is toggled by {@link DefaultUpdateBehavior#updateAdapter}.
	 */
	private boolean resourceDeleted = false;

	/**
	 * Is toggled by {@link DefaultUpdateBehavior#updateAdapter}.
	 */
	private boolean resourceChanged = false;

	// Note: there are some concerns out there that the Synchronizer does not
	// work for inter-EditingDomain-eventing, @see
	// http://www.eclipse.org/forums/index.php?t=msg&th=140242&start=0&
	private WorkspaceSynchronizer workspaceSynchronizer;

	/**
	 * Flag that indicates if the {@link #updateAdapter} shall be active or not.
	 * It may be deactivated when mass operations (e.g. saving the diagram
	 * editor with all its resources) take place. Use the methods
	 * {@link #isAdapterActive()} and {@link #setAdapterActive(boolean)} to
	 * access this field.
	 */
	private boolean adapterActive = true;

	/**
	 * Stores the update adapter that cares about refreshing the diagram editor
	 * in case of resource changes. May be disabled by overriding
	 * {@link #isAdapterActive()} and returning false. The instance will be
	 * created in the
	 * {@link #initializeEditingDomain(TransactionalEditingDomain)} method and
	 * can be exchanged with an own implementation by overriding
	 * {@link #createUpdateAdapter()}.
	 * 
	 * @see #init()
	 * @see #initializeEditingDomain(TransactionalEditingDomain)
	 */
	private Adapter updateAdapter = null;

	/**
	 * Creates a new {@link DefaultUpdateBehavior} instance associated with the
	 * given {@link DiagramBehavior}.
	 * 
	 * @param diagramEditor
	 *            the part this model editor works on
	 * @since 0.10
	 */
	public DefaultUpdateBehavior(DiagramBehavior diagramBehavior) {
		super();
		this.diagramBehavior = diagramBehavior;
	}

	/**
	 * Returns the flag that indicates if the underlying resource of the
	 * {@link Diagram} has been deleted. Note that this flag will only be
	 * updated in case the {@link #updateAdapter} is enabled, see
	 * {@link #adapterActive}, {@link #isAdapterActive()} and
	 * {@link #setAdapterActive(boolean)}. If this flag is set the editor will
	 * close on receiving the next event.
	 * 
	 * @return <code>true</code> in case the resource has been deleted,
	 *         <code>false</code> otherwise
	 */
	protected boolean isResourceDeleted() {
		return resourceDeleted;
	}

	/**
	 * Sets the flag that indicates if the underlying resource of the
	 * {@link Diagram} has been deleted. Note that this flag should only be
	 * updated by the {@link #updateAdapter}, see {@link #adapterActive},
	 * {@link #isAdapterActive()} and {@link #setAdapterActive(boolean)}.
	 * <p>
	 * Should not be called by external clients.
	 * 
	 * @param resourceDeleted
	 *            the value to set the flag to, <code>true</code> indicates that
	 *            the resource has been deleted.
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void setResourceDeleted(boolean resourceDeleted) {
		this.resourceDeleted = resourceDeleted;
	}

	/**
	 * Returns the flag that indicates if the underlying resource of the
	 * {@link Diagram} has been changed. Note that this flag will only be
	 * updated in case the {@link #updateAdapter} is enabled, see
	 * {@link #adapterActive}, {@link #isAdapterActive()} and
	 * {@link #setAdapterActive(boolean)}.
	 * 
	 * @return <code>true</code> in case the resource has been changed,
	 *         <code>false</code> otherwise
	 */
	protected boolean isResourceChanged() {
		return resourceChanged;
	}

	/**
	 * Sets the flag that indicates if the underlying resource of the
	 * {@link Diagram} has been changed. Note that this flag should only be
	 * updated by the {@link #updateAdapter}, see {@link #adapterActive},
	 * {@link #isAdapterActive()} and {@link #setAdapterActive(boolean)}.
	 * <p>
	 * Should not be called by external clients.
	 * 
	 * @param resourceChanged
	 *            the value to set the flag to, <code>true</code> indicates that
	 *            the resource has been changed.
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void setResourceChanged(boolean resourceChanged) {
		this.resourceChanged = resourceChanged;
	}

	/**
	 * Handles activation of the editor. In case of the underlying diagram
	 * resource being deleted ({@link #resourceDeleted} is <code>true</code>)
	 * the editor will be closed after a call to {@link #handleDirtyConflict()}
	 * that returns <code>true</code>. Also it will call
	 * {@link #handleChangedResources()} in case the underlying diagram resource
	 * has changed ({@link #resourceChanged} is <code>true</code>).
	 */
	public void handleActivate() {
		if (isResourceDeleted()) {
			if (handleDirtyConflict()) {
				closeContainer();
			} else {
				setResourceDeleted(false);
				setResourceChanged(false);
			}
		} else if (isResourceChanged()) {
			handleChangedResources();
			setResourceChanged(false);
		}
	}

	/**
	 * Returns the flag that indicates if the {@link #updateAdapter} shall be
	 * active of not ({@link #adapterActive}). In case this method returns
	 * <code>false</code>, the {@link #updateAdapter} will do nothing on being
	 * called.
	 * 
	 * @return <code>true</code> in case the adapter shall run,
	 *         <code>false</code> otherwise.
	 */
	protected boolean isAdapterActive() {
		return adapterActive;
	}

	/**
	 * Sets the flag that indicates if the {@link #updateAdapter} shall be
	 * active of not ({@link #adapterActive}).
	 * 
	 * @param active
	 *            the new value for the flag
	 */
	public void setAdapterActive(boolean active) {
		adapterActive = active;
	}

	/**
	 * Handles what to do with changed resources on editor activation.
	 */
	protected void handleChangedResources() {
		if (!diagramBehavior.getDiagramContainer().isDirty() || handleDirtyConflict()) {
			IUndoContext undoContext = ((IWorkspaceCommandStack) getEditingDomain().getCommandStack())
					.getDefaultUndoContext();
			IOperationHistory operationHistory = getOperationHistory();
			if (operationHistory != null) {
				operationHistory.dispose(undoContext, true, true, true);
			}

			// Disable adapters temporarily.
			diagramBehavior.disableAdapters();

			try {
				// We unload our resources such that refreshEditorContent does a
				// complete diagram refresh.
				EList<Resource> resources = getEditingDomain().getResourceSet().getResources();
				for (Resource resource : resources) {
					resource.unload();
				}
				diagramBehavior.refreshContent();
			} finally {
				// Re-enable adapters again
				diagramBehavior.enableAdapters();
			}
		}
	}

	/**
	 * Shows a dialog that asks if conflicting changes should be discarded or
	 * not. See {@link #handleActivate()}.
	 * 
	 * @return <code>true</code> in case the editor shall be closed,
	 *         <code>false</code> otherwise
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				Messages.DiscardChangesDialog_0_xmsg, Messages.DiscardChangesDialog_1_xmsg);
	}

	/**
	 * This returns the editing domain as required by the
	 * {@link IEditingDomainProvider} interface.
	 * 
	 * @return The {@link TransactionalEditingDomain} that is used within this
	 *         editor
	 */
	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * Initializes listeners and adapters.
	 */
	public void init() {
		// Retrieve the object from the editor input
		final EObject object = (EObject) diagramBehavior.getAdapter(Diagram.class);
		// Register for object deletion
		if (object != null) {
			elementDeleteListener = new ElementDeleteListener(diagramBehavior);
			object.eAdapters().add(elementDeleteListener);
		}

		IOperationHistory operationHistory = getOperationHistory();
		if (operationHistory != null) {
			operationHistory.addOperationHistoryListener(this);
		}
	}

	/**
	 * Hook to create an {@link DefaultUpdateBehavior#updateAdapter} that cares
	 * about updating the diagram editor. The default implementation simply
	 * creates a {@link DefaultUpdateAdapter}.
	 * 
	 * @return The newly created adapter
	 * @since 0.11
	 */
	protected Adapter createUpdateAdapter() {
		return new DefaultUpdateAdapter();
	}

	/**
	 * Returns the {@link #updateAdapter} of this instance or <code>null</code>
	 * in case the adapter has not yet been initialized.
	 * 
	 * @return
	 * @since 0.11
	 */
	protected Adapter getUpdateAdapter() {
		return updateAdapter;
	}

	/**
	 * Created the {@link TransactionalEditingDomain} that shall be used within
	 * the diagram editor and initializes it by delegating to
	 * {@link #initializeEditingDomain(TransactionalEditingDomain)}.
	 */
	protected void createEditingDomain() {
		TransactionalEditingDomain editingDomain = GraphitiUiInternal.getEmfService()
				.createResourceSetAndEditingDomain();
		initializeEditingDomain(editingDomain);
	}

	/**
	 * This sets up the editing domain for this model editor.
	 * 
	 * @param domain
	 *            The {@link TransactionalEditingDomain} that is used within
	 *            this model editor
	 */
	protected void initializeEditingDomain(TransactionalEditingDomain domain) {
		editingDomain = domain;
		final ResourceSet resourceSet = domain.getResourceSet();

		updateAdapter = createUpdateAdapter();

		resourceSetUpdateAdapter = new ResourceSetUpdateAdapter(updateAdapter);
		resourceSet.eAdapters().add(resourceSetUpdateAdapter);

		// Install synchronizer for editor-external changes to the files
		// underlying the resources of the ED
		Delegate synchronizerDelegate = createWorkspaceSynchronizerDelegate();
		if (synchronizerDelegate != null) {
			workspaceSynchronizer = new WorkspaceSynchronizer(getEditingDomain(), synchronizerDelegate);
		}

		// Problem analysis
		diagramBehavior.editingDomainInitialized();
	}

	/**
	 * Can be overridden to return a client specific implementation of a
	 * {@link WorkspaceSynchronizer} {@link Delegate} object. Graphiti uses the
	 * returned instance to manage and react on changes done to the resources
	 * tied to the diagram outside of the diagram editor's
	 * TransactionalEditingDomain.
	 * 
	 * @return The delegate to use. The default implementation return's
	 *         Graphiti's own implementation of such a delegate that should be
	 *         sufficient for most client editors. Might return
	 *         <code>null</code>; in this case no {@link WorkspaceSynchronizer}
	 *         will be installed.
	 * 
	 * @since 0.10
	 */
	protected WorkspaceSynchronizer.Delegate createWorkspaceSynchronizerDelegate() {
		return new DomainModelWorkspaceSynchronizerDelegate(diagramBehavior);
	}

	/**
	 * Disposes this {@link DefaultUpdateBehavior} and free all resources it
	 * holds. In case you only want to omit or influence the disposal of the
	 * {@link TransactionalEditingDomain}, you can also override
	 * {@link #disposeEditingDomain()}.
	 */
	public void dispose() {
		editingDomain.getResourceSet().eAdapters().remove(resourceSetUpdateAdapter);
		resourceSetUpdateAdapter = null;

		IOperationHistory operationHistory = getOperationHistory();
		if (operationHistory != null) {
			operationHistory.removeOperationHistoryListener(this);
		}

		for (Resource r : editingDomain.getResourceSet().getResources()) {
			r.eAdapters().remove(updateAdapter);
		}

		EObject object = (EObject) diagramBehavior.getAdapter(Diagram.class);
		if (object != null) {
			object.eAdapters().remove(elementDeleteListener);
		}

		if (workspaceSynchronizer != null) {
			workspaceSynchronizer.dispose();
		}

		// Remove reference
		disposeEditingDomain();
		editingDomain = null;
	}

	/**
	 * Cares about disposing the {@link TransactionalEditingDomain} held in this
	 * instance. Is called during the {@link #dispose()} method.
	 */
	protected void disposeEditingDomain() {
		editingDomain.dispose();
	}

	/**
	 * Is called by the operation history of the
	 * {@link TransactionalEditingDomain} in case the history changes. Reacts on
	 * undo and redo events and updates the dirty state of the editor.
	 * 
	 * @param event
	 *            the {@link OperationHistoryEvent} to react upon
	 */
	public void historyNotification(OperationHistoryEvent event) {
		switch (event.getEventType()) {
		case OperationHistoryEvent.REDONE:
		case OperationHistoryEvent.UNDONE:
			diagramBehavior.getDiagramContainer().updateDirtyState();
			break;
		}
	}

	private Shell getShell() {
		return diagramBehavior.getDiagramContainer().getSite().getShell();
	}

	/**
	 * Closes the {@link IDiagramContainerUI} (usually a diagram Editor) behind
	 * this {@link DefaultUpdateBehavior} instance. Called e.g. when the diagram
	 * resource underneath the editor has been deleted.
	 * 
	 * @since 0.11
	 */
	protected void closeContainer() {
		IDiagramContainerUI diagramContainer = diagramBehavior.getDiagramContainer();
		if (diagramContainer == null) {
			return;
		}
		IWorkbenchPartSite site = diagramContainer.getSite();
		// Since we run asynchronously we have to check if our UI is still
		// there.
		if (site == null) {
			return;
		}
		IWorkbenchPage page = site.getPage();
		if (page == null) {
			return;
		}
		diagramContainer.close();
	}

	/**
	 * Returns the {@link IOperationHistory} for the command stack. The default
	 * implementation retrieves the command stack of the current
	 * {@link TransactionalEditingDomain} and returns its operation history.
	 * 
	 * @return The operation history
	 * @since 0.11
	 */
	protected IOperationHistory getOperationHistory() {
		IOperationHistory history = null;
		TransactionalEditingDomain domain = getEditingDomain();
		if (domain != null) {
			IWorkspaceCommandStack commandStack = (IWorkspaceCommandStack) domain.getCommandStack();
			if (commandStack != null) {
				history = commandStack.getOperationHistory();
			}
		}
		return history;
	}

	/**
	 * @since 0.10
	 */
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		initializeEditingDomain(editingDomain);
	}

	/**
	 * The default implementation of the update adapter that cares about
	 * refreshing the diagram editor in case of resource changes. The default
	 * implementation will trigger the update of the diagram editor in case the
	 * EMF resource has been changed externally; in case the resource was
	 * deleted the default implementation will close the editor if there are no
	 * unsaved changes or ask the user what to do in case of unsaved changes.
	 * 
	 * @since 0.11
	 */
	protected class DefaultUpdateAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (!isAdapterActive()) {
				return;
			}
			if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
				if (msg.getNewBooleanValue() == Boolean.FALSE) {
					final Resource resource = (Resource) msg.getNotifier();
					final URI uri = resource.getURI();
					IDiagramContainerUI diagramContainer = diagramBehavior.getDiagramContainer();
					if (editingDomain.getResourceSet().getURIConverter().exists(uri, null)) {
						// file content has changes
						setResourceChanged(true);
						final IWorkbenchPart activePart = diagramContainer.getWorkbenchPart().getSite().getPage()
								.getActivePart();
						if (activePart == diagramContainer) {
							getShell().getDisplay().asyncExec(new Runnable() {
								public void run() {
									handleActivate();
								}
							});
						}
					} else {
						// File has been deleted
						if (!diagramContainer.isDirty()) {
							// Close diagram editor in case the diagram is
							// stored in the deleted resource
							Diagram diagram = diagramContainer.getDiagramTypeProvider().getDiagram();
							if (diagram != null) {
								URI diagramUri = EcoreUtil.getURI(diagram);
								if (diagramUri != null) {
									URI uriOfDiagramFile = diagramUri.trimFragment();
									// Bug 427444: To find out if the diagram
									// resource has been deleted check if the
									// base URI of the diagram object is the
									// same as the URI of the resource
									if (uriOfDiagramFile.equals(uri)) {
										closeEditorAsynchronously();
									}
								}
							}
						} else {
							// Ask user what to da with unsaved changes in the
							// editor
							setResourceDeleted(true);
							final IWorkbenchPart activePart = diagramContainer.getWorkbenchPart().getSite().getPage()
									.getActivePart();
							if (activePart == diagramContainer) {
								getShell().getDisplay().asyncExec(new Runnable() {
									public void run() {
										handleActivate();
									}
								});
							}
						}
					}
				}
			}
			super.notifyChanged(msg);
		}

		/**
		 * Triggers closing of the editor by scheduling an asynchronous call to
		 * {@link DefaultUpdateBehavior#closeContainer()}.
		 */
		protected void closeEditorAsynchronously() {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					closeContainer();
				}
			});
		}
	}
}
