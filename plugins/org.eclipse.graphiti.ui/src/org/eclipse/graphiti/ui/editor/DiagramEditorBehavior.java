/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
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
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.editor.DomainModelWorkspaceSynchronizerDelegate;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 * @since 0.9
 */
public class DiagramEditorBehavior extends PlatformObject implements IEditingDomainProvider, IOperationHistoryListener {


	private DiagramEditor diagramEditor;

	private TransactionalEditingDomain editingDomain;

	/**
	 * Closes editor if model object is deleted.
	 */
	private ElementDeleteListener elementDeleteListener = null;



	/**
	 * The update adapter is added to every {@link Resource} adapters in the
	 * {@link ResourceSet} of the {@link TransactionalEditingDomain}. When
	 * notified, it adds an {@link DiagramEditorBehavior#updateAdapter} to the
	 * adapters of the ResourceSet.
	 * 
	 * @see DiagramEditorBehavior#initializeEditingDomain(TransactionalEditingDomain)
	 */
	private ResourceSetUpdateAdapter resourceSetUpdateAdapter;

	/**
	 * Is toggled by {@link DiagramEditorBehavior#updateAdapter}.
	 */
	protected boolean resourceDeleted = false;

	/**
	 * Is toggled by {@link DiagramEditorBehavior#updateAdapter}.
	 */
	private boolean resourceChanged = false;

	// TODO, FIXME:
	// check if the Synchronizer really works for inter-EditingDomain-eventing
	// there are some concerns out there regarding WorkspaceSynchronizers, @see
	// http://www.eclipse.org/forums/index.php?t=msg&th=140242&start=0&
	// Also EMF group experienced deadlocks caused by the synchronizer.
	private WorkspaceSynchronizer workspaceSynchronizer;

	private boolean adapterActive = true;


	/**
	 * Creates a model editor responsible for the given {@link IEditorPart}.
	 * 
	 * @param diagramEditor
	 *            the part this model editor works on
	 */
	public DiagramEditorBehavior(DiagramEditor diagramEditor) {
		super();
		this.diagramEditor = diagramEditor;
	}

	private boolean isResourceDeleted() {
		return resourceDeleted;
	}


	/**
	 * Should not be called by external clients.
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 * @param resourceDeleted
	 */
	public void setResourceDeleted(boolean resourceDeleted) {
		this.resourceDeleted = resourceDeleted;
	}


	private boolean isResourceChanged() {
		return resourceChanged;
	}


	/**
	 * Should not be called by external clients.
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 * @param resourceChanged
	 */
	public void setResourceChanged(boolean resourceChanged) {
		this.resourceChanged = resourceChanged;
	}

	/**
	 * This sets up the editing domain for this model editor.
	 * 
	 * @param domain
	 *            The {@link TransactionalEditingDomain} that is used within
	 *            this model editor
	 */
	private void initializeEditingDomain(TransactionalEditingDomain domain) {
		editingDomain = domain;
		final ResourceSet resourceSet = domain.getResourceSet();

		resourceSetUpdateAdapter = new ResourceSetUpdateAdapter(updateAdapter);
		resourceSet.eAdapters().add(resourceSetUpdateAdapter);

		// Install synchronizer for editor-external changes to the files
		// underlying the resources of the ED
		workspaceSynchronizer = new WorkspaceSynchronizer(getEditingDomain(),
				new DomainModelWorkspaceSynchronizerDelegate(diagramEditor));

		// Problem analysis
		diagramEditor.editingDomainInitialized();
	}

	private Shell getShell() {
		return diagramEditor.getSite().getShell();
	}

	final Adapter updateAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (!isAdapterActive())
				return;
			if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
				if (msg.getNewBooleanValue() == Boolean.FALSE) {
					final Resource resource = (Resource) msg.getNotifier();
					final URI uri = resource.getURI();
					if (editingDomain.getResourceSet().getURIConverter().exists(uri, null)) {
						// file content has changes
						setResourceChanged(true);
						final IEditorPart activeEditor = diagramEditor.getSite().getPage().getActiveEditor();
						if (activeEditor == diagramEditor) {
							getShell().getDisplay().asyncExec(new Runnable() {
								public void run() {
									handleActivate();
								}
							});
						}
					} else {
						// file has been deleted
						if (!diagramEditor.isDirty()) {
							final IEditorInput editorInput = diagramEditor.getEditorInput();
							if (editorInput instanceof IDiagramEditorInput) {
								final IDiagramEditorInput input = (IDiagramEditorInput) editorInput;
								URI inputUri = input.getUri();
								URI diagUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(uri);
								if (diagUri.equals(inputUri)) {
									startCloseEditorJob();
								}
							}
						} else {
							setResourceDeleted(true);
							final IEditorPart activeEditor = diagramEditor.getSite().getPage().getActiveEditor();
							if (activeEditor == diagramEditor) {
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

		private void startCloseEditorJob() {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					closeEd();
				}
			});
		}
	};

	private void closeEd() {
		if (diagramEditor == null)
			return;
		IWorkbenchPartSite site = diagramEditor.getSite();
		// Since we run async we have to check if our ui is still there.
		if (site == null)
			return;
		IWorkbenchPage page = site.getPage();
		if (page == null)
			return;
		page.closeEditor(diagramEditor, false);
	}

	/**
	 * Handles activation of the editor.
	 */
	private void handleActivate() {
		if (isResourceDeleted()) {
			if (handleDirtyConflict()) {
				closeEd();
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
	 * @return
	 */
	protected boolean isAdapterActive() {
		return adapterActive;
	}

	public void setAdapterActive(boolean b) {
		adapterActive = b;
	}

	/**
	 * Handles what to do with changed resources on activation.
	 */
	private void handleChangedResources() {
		if (!diagramEditor.isDirty() || handleDirtyConflict()) {
			getOperationHistory().dispose(getUndoContext(), true, true, true);

			// Disable adapters temporarily.
			diagramEditor.disableAdapters();

			try {
				// We unload our resources such that refreshEditorContent does a
				// complete diagram refresh.
				EList<Resource> resources = getEditingDomain().getResourceSet().getResources();
				for (Resource resource : resources) {
					resource.unload();
				}
				diagramEditor.refreshContent();
			} finally {
				// Re-enable adapters again
				diagramEditor.enableAdapters();
			}
		}
	}

	/**
	 * Shows a dialog that asks if conflicting changes should be discarded.
	 */
	private boolean handleDirtyConflict() {
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

	private IOperationHistory getOperationHistory() {
		IOperationHistory history = null;
		final TransactionalEditingDomain domain = getEditingDomain();
		if (domain != null) {
			final IWorkspaceCommandStack commandStack = (IWorkspaceCommandStack) getEditingDomain().getCommandStack();
			if (commandStack != null) {
				history = commandStack.getOperationHistory();
			}
		}
		return history;
	}

	/**
	 * Initializes listeners and adapters.
	 */
	public void init() {
		for (final Resource r : getEditingDomain().getResourceSet().getResources()) {
			r.eAdapters().add(updateAdapter);
		}

		// Retrieve the object from the editor input
		final EObject object = (EObject) diagramEditor.getAdapter(Diagram.class);
		// Register for object deletion
		if (object != null) {
			elementDeleteListener = new ElementDeleteListener(diagramEditor);
			object.eAdapters().add(elementDeleteListener);
		}

		getOperationHistory().addOperationHistoryListener(this);
	}

	public void initializeEditingDomain() {
		TransactionalEditingDomain ed = GraphitiUiInternal.getEmfService().createResourceSetAndEditingDomain();
		initializeEditingDomain(ed);
	}

	public void dispose() {

		// Remove all the registered listeners
		editingDomain.getResourceSet().eAdapters().remove(resourceSetUpdateAdapter);
		getOperationHistory().removeOperationHistoryListener(this);

		for (Resource r : editingDomain.getResourceSet().getResources()) {
			r.eAdapters().remove(updateAdapter);
		}

		EObject object = (EObject) diagramEditor.getAdapter(Diagram.class);
		if (object != null) {
			object.eAdapters().remove(elementDeleteListener);
		}

		workspaceSynchronizer.dispose();

		// Remove reference
		disposeEditingDomain();
		editingDomain = null;
		diagramEditor = null;
	}

	protected void disposeEditingDomain() {
		editingDomain.dispose();
	}

	/**
	 * Called by editor parts when focus is set by Eclipse. It is necessary to
	 * update the action bars (undo menu) accordingly if editor receives focus
	 */
	public void setFocus() {
		this.handleActivate();
	}

	public void historyNotification(OperationHistoryEvent event) {
		switch (event.getEventType()) {
		case OperationHistoryEvent.REDONE:
		case OperationHistoryEvent.UNDONE:
			diagramEditor.updateDirtyState();
			break;
		}
	}

	public IUndoContext getUndoContext() {
		return ((IWorkspaceCommandStack) getEditingDomain().getCommandStack()).getDefaultUndoContext();
	}

}
