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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.editor.DomainModelWorkspaceSynchronizerDelegate;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 * @since 0.9
 */
public class DiagramEditorBehavior extends PlatformObject implements IEditingDomainProvider, IOperationHistoryListener {

	/**
	 * The part this model editor works on.
	 * 
	 * @see {@link DiagramEditorBehavior#DiagramEditorBehavior(IEditorPart)}
	 */
	private DiagramEditor diagramEditor = null;

	/**
	 * Keeps track of the editing domain that is used to track all changes to
	 * the model.
	 */
	private TransactionalEditingDomain editingDomain;

	/**
	 * Closes editor if model object is deleted.
	 */
	private ElementDeleteListener elementDeleteListener = null;

	/**
	 * Is responsible for creating workspace resource markers presented in
	 * Eclipse's Problems View.
	 */
	private final MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
	 * Map to store the diagnostic associated with a resource.
	 */
	private final Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

	/**
	 * Controls whether the problem indication should be updated.
	 */
	private boolean updateProblemIndication = true;


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
	 * Creates a model editor responsible for the given {@link IEditorPart}.
	 * 
	 * @param diagramEditor
	 *            the part this model editor works on
	 */
	public DiagramEditorBehavior(DiagramEditor diagramEditor) {
		super();
		this.diagramEditor = diagramEditor;
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

		// Problem analysis
		resourceSet.eAdapters().add(problemIndicationAdapter);

		resourceSetUpdateAdapter = new ResourceSetUpdateAdapter();
		resourceSet.eAdapters().add(resourceSetUpdateAdapter);

		// Install synchronizer for editor-external changes to the files
		// underlying the resources of the ED
		workspaceSynchronizer = new WorkspaceSynchronizer(getEditingDomain(),
				new DomainModelWorkspaceSynchronizerDelegate(this));
	}

	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded.
	 */
	private final EContentAdapter problemIndicationAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				switch (notification.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
				case Resource.RESOURCE__ERRORS:
				case Resource.RESOURCE__WARNINGS: {
					final Resource resource = (Resource) notification.getNotifier();
					final Diagnostic diagnostic = analyzeResourceProblems(resource, null);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						resourceToDiagnosticMap.put(resource, diagnostic);
					} else {
						resourceToDiagnosticMap.remove(resource);
					}

					if (updateProblemIndication) {
						getShell().getDisplay().asyncExec(new Runnable() {
							public void run() {
								updateProblemIndication();
							}
						});
					}
					break;
				}
				}
			} else {
				super.notifyChanged(notification);
			}
		}

		@Override
		protected void setTarget(Resource target) {
			basicSetTarget(target);
		}

		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
		}
	};

	private Shell getShell() {
		return diagramEditor.getSite().getShell();
	}

	private final Adapter updateAdapter = new AdapterImpl() {
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

	private void setAdapterActive(boolean b) {
		adapterActive = b;
	}

	/**
	 * Handles what to do with changed resources on activation.
	 */
	private void handleChangedResources() {
		if (!diagramEditor.isDirty() || handleDirtyConflict()) {
			getOperationHistory().dispose(getUndoContext(), true, true, true);

			setProblemIndicationUpdateActive(false);
			// Disable adapter temporarily.
			setAdapterActive(false);
			try {
				// We unload our resources such that refreshEditorContent does a
				// complete diagram refresh.
				EList<Resource> resources = getEditingDomain().getResourceSet().getResources();
				for (Resource resource : resources) {
					resource.unload();
				}
				refreshEditorContent();
			} finally {
				setAdapterActive(true);
			}
			setProblemIndicationUpdateActive(true);
			updateProblemIndication();
		}
	}

	/**
	 * Updates the problems indication with the information described in the
	 * specified diagnostic.
	 */
	void updateProblemIndication() {
		if (this.updateProblemIndication && editingDomain != null) {
			final BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, GraphitiUIPlugin.PLUGIN_ID, 0, null,
					new Object[] { editingDomain.getResourceSet() });
			for (final Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
				if (childDiagnostic.getSeverity() != Diagnostic.OK) {
					diagnostic.add(childDiagnostic);
				}
			}
			if (markerHelper.hasMarkers(editingDomain.getResourceSet())) {
				markerHelper.deleteMarkers(editingDomain.getResourceSet());
			}
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				try {
					markerHelper.createMarkers(diagnostic);
					T.racer().info(diagnostic.toString());
				} catch (final CoreException exception) {
					T.racer().error(exception.getMessage(), exception);
				}
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

	/**
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any).
	 */
	public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
		if ((!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) && editingDomain != null) {
			final IFile file = GraphitiUiInternal.getEmfService().getFile(resource.getURI());
			final String fileName = file != null ? file.getFullPath().toString() : "unknown name"; //$NON-NLS-1$
			final BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
					Diagnostic.ERROR,
					GraphitiUIPlugin.PLUGIN_ID,
					0,
					"Problems encountered in file " + fileName, new Object[] { exception == null ? (Object) resource : exception }); //$NON-NLS-1$
			basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
			return basicDiagnostic;
		} else if (exception != null) {
			return new BasicDiagnostic(Diagnostic.ERROR, GraphitiUIPlugin.PLUGIN_ID, 0, "Problems encountered in file", //$NON-NLS-1$ 
					new Object[] { exception });
		} else {
			return Diagnostic.OK_INSTANCE;
		}
	}

	public void setProblemIndicationUpdateActive(boolean onOff) {
		this.updateProblemIndication = onOff;
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
	 * Initializes this editor with the given editor site and input. If a
	 * dirtyStateUpdater is provided, it will be registered such that it toggles
	 * the editor's dirty state.
	 * 
	 * @param site
	 *            the editor site
	 * @param input
	 *            the editor's input, preferably a {@link DiagramEditorInput}
	 * @param dirtyStateUpdater
	 *            an optional operation for toggling the dirty state, which is
	 *            called at the appropriate time. Its implementation should
	 *            contain a call
	 *            <code>firePropertyChange(IEditorPart.PROP_DIRTY)</code>.
	 * @throws PartInitException
	 *             if the initialization fails
	 */
	/**
	 * @param site
	 * @param editorInput
	 */
	public void init(IEditorInput editorInput) {
		// Retrieve the object from the editor input
		final EObject object = (EObject) diagramEditor.getAdapter(Diagram.class);

		for (final Resource r : getEditingDomain().getResourceSet().getResources()) {
			r.eAdapters().add(updateAdapter);
		}

		// Register for object deletion
		if (object != null) {
			elementDeleteListener = new ElementDeleteListener();
			object.eAdapters().add(this.elementDeleteListener);
		}

		getOperationHistory().addOperationHistoryListener(this);
	}

	public void initializeEditingDomain() {
		TransactionalEditingDomain ed = GraphitiUiInternal.getEmfService().createResourceSetAndEditingDomain();
		initializeEditingDomain(ed);
	}

	public void dispose() {
		setProblemIndicationUpdateActive(false);

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
	 * Adding update adapters to the respective resources.
	 */
	private final class ResourceSetUpdateAdapter extends AdapterImpl {
		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					((Resource) msg.getNewValue()).eAdapters().add(updateAdapter);
					break;
				case Notification.ADD_MANY:
					for (final Resource res : (Collection<Resource>) msg.getNewValue()) {
						res.eAdapters().add(updateAdapter);
					}
					break;
				case Notification.REMOVE:
					((Resource) msg.getOldValue()).eAdapters().remove(updateAdapter);
					break;
				case Notification.REMOVE_MANY:
					for (final Resource res : (Collection<Resource>) msg.getOldValue()) {
						res.eAdapters().remove(updateAdapter);
					}
					break;

				default:
					break;
				}
			}
		}
	}

	/**
	 * Closes editor if model element was deleted. For instance, if only the
	 * diagram object is deleted from the resource, this listeners handles
	 * closing the editor.
	 */
	private final class ElementDeleteListener extends AdapterImpl {

		@Override
		public boolean isAdapterForType(Object type) {
			return type instanceof EObject;
		}

		@Override
		public void notifyChanged(Notification msg) {
			final IEditorPart part = diagramEditor;
			if (T.racer().debug()) {
				final String editorName = part.getTitle();
				T.racer().debug("Delete listener called of editor " //$NON-NLS-1$
						+ editorName + " with events " + msg.toString()); //$NON-NLS-1$
			}

			final IEditorInput in = part.getEditorInput();
			if (in != null) {
				final IEditorSite site = part.getEditorSite();
				if (site == null) {
					return;
				}
				final Shell shell = site.getShell();
				// Do the real work, e.g. object retrieval from input and
				// closing, asynchronous to not block this listener longer than necessary,
				// which may provoke deadlocks.
				shell.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (diagramEditor == null) {
							return; // disposed
						}
						if (shell.isDisposed()) {
							return; // disposed
						}
						Diagram diagram = null;
						try {
							diagram = (Diagram) diagramEditor.getAdapter(Diagram.class);
						} catch (final Exception e) {
							// Ignore, exception indicates that the diagram has
							// been deleted
						}
						if (diagram == null || EcoreUtil.getRootContainer(diagram) == null) {
							// diagram is gone so try to close
							final IWorkbenchPage page = site.getPage();
							if (T.racer().debug()) {
								final String editorName = part.getTitle();
								T.racer().debug("Closing editor " + editorName); //$NON-NLS-1$
							}
							page.closeEditor(part, false);
						}
					}
				});
			}
		}
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

	public void refreshEditorContent() {
		if (diagramEditor instanceof DiagramEditor) {
			((DiagramEditor) diagramEditor).refreshContent();
		}
	}

	public IDiagramEditorInput getEditorInput() {
		IEditorInput editorInput = diagramEditor.getEditorInput();
		if (editorInput instanceof IDiagramEditorInput) {
			return ((IDiagramEditorInput) editorInput);
		}
		return null;

	}
}
