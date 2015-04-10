/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 372753 - save shouldn't (necessarily) flush the command stack
 *    mwenz - Bug 376008 - Iterating through navigation history causes exceptions
 *    mwenz - Bug 393074 - Save Editor Progress Monitor Argument
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz/Rob Cernich - Bug 391046 - Deadlock while saving prior to refactoring operation
 *    mwenz - Bug 437933 - NullPointerException in DefaultPersistencyBehavior.isDirty()
 *    mwenz - Bug 441676 - Read-only attribute is not respected in Graphiti
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.internal.IDiagramVersion;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.IThreadListener;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.swt.widgets.Display;

/**
 * The default implementation for the {@link DiagramBehavior} behavior extension
 * that controls the persistence behavior of the Graphiti diagram Editor.
 * Clients may subclass to change the behavior; use
 * {@link DiagramBehavior#createPersistencyBehavior()} to return the instance
 * that shall be used.<br>
 * Note that there is always a 1:1 relation with a {@link DiagramBehavior}.
 * 
 * @since 0.9
 */
public class DefaultPersistencyBehavior {

	/**
	 * The associated {@link DiagramBehavior}
	 * 
	 * @since 0.10
	 */
	protected final DiagramBehavior diagramBehavior;

	/**
	 * Used to store the command that was executed before the editor was saved.
	 * By comparing with the top of the current undo stack this point in the
	 * command stack indicates if the editor is dirty.
	 */
	protected Command savedCommand = null;

	/**
	 * Creates a new instance of {@link DefaultPersistencyBehavior} that is
	 * associated with the given {@link DiagramBehavior}.
	 * 
	 * @param diagramEditor
	 *            the associated {@link DiagramBehavior}
	 * @since 0.10
	 */
	public DefaultPersistencyBehavior(DiagramBehavior diagramBehavior) {
		this.diagramBehavior = diagramBehavior;
	}

	/**
	 * This method is called to load the diagram into the editor. The default
	 * implementation here will use the {@link TransactionalEditingDomain} and
	 * its {@link ResourceSet} to load an EMF {@link Resource} that holds the
	 * {@link Diagram}. It will also enable modification tracking on the diagram
	 * {@link Resource}.
	 * 
	 * @param uri
	 *            the {@link URI} of the diagram to load
	 * @return the instance of the {@link Diagram} as it is resolved within the
	 *         editor, meaning as it is resolved within the editor's
	 *         {@link TransactionalEditingDomain}.
	 */
	public Diagram loadDiagram(URI uri) {
		if (uri != null) {
			final TransactionalEditingDomain editingDomain = diagramBehavior.getEditingDomain();
			if (editingDomain != null) {
				// First try the URI resolution without loading not yet loaded
				// resources because calling with loadOnDemand will _always_
				// create a new Resource instance for newly created and not yet
				// saved Resources, no matter if they already exist within the
				// ResourceSet or not
				EObject modelElement = null;
				// Catch exceptions that happen while loading the resource to
				// avoid spamming the log and showing nasty messages to the user
				// in the editor, see Bug 376008
				try {
					modelElement = editingDomain.getResourceSet().getEObject(uri, false);
					if (modelElement == null) {
						modelElement = editingDomain.getResourceSet().getEObject(uri, true);
						if (modelElement == null) {
							return null;
						}
					}
				} catch (WrappedException e) {
					// Log only if debug tracing is active to avoid user
					// confusion (message is shown in the editor anyhow)
					T.racer().debug("Diagram with URI '" + uri.toString() + "' could not be loaded", e); //$NON-NLS-1$ //$NON-NLS-2$
					return null;
				}
				modelElement.eResource().setTrackingModification(true);
				return (Diagram) modelElement;
			}
		}
		return null;
	}

	/**
	 * This method is called to save a diagram. The default implementation here
	 * saves all changes done to any of the EMF resources loaded within the
	 * {@link DiagramBehavior} so that the complete state of all modified
	 * objects will be persisted in the file system.<br>
	 * The default implementation also sets the current version information
	 * (currently 0.12.0) to the diagram before saving it and wraps the save
	 * operation inside a {@link IRunnableWithProgress} that cares about sending
	 * only one {@link Resource} change event holding all modified files.
	 * Besides also all adapters are temporarily switched off (see
	 * {@link DiagramBehavior#disableAdapters()}).<br>
	 * To only modify the actual saving clients should rather override
	 * {@link #save(TransactionalEditingDomain, Map)}.
	 * 
	 * @param monitor
	 *            the Eclipse {@link IProgressMonitor} to use to report progress
	 */
	public void saveDiagram(IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		// set version info.
		final Diagram diagram = diagramBehavior.getDiagramTypeProvider().getDiagram();
		setDiagramVersion(diagram);

		Map<Resource, Map<?, ?>> saveOptions = createSaveOptions();
		final Set<Resource> savedResources = new HashSet<Resource>();
		final IRunnableWithProgress operation = createOperation(savedResources, saveOptions);

		diagramBehavior.disableAdapters();

		try {
			// This runs the options in a background thread reporting progress
			// to the progress monitor passed into this method (see Bug 393074)
			ModalContext.run(operation, true, monitor, Display.getDefault());

			BasicCommandStack commandStack = (BasicCommandStack) diagramBehavior.getEditingDomain().getCommandStack();
			commandStack.saveIsDone();

			// Store the last executed command on the undo stack as save point
			// and refresh the dirty state of the editor
			savedCommand = commandStack.getUndoCommand();
			diagramBehavior.getDiagramContainer().updateDirtyState();
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof SaveException) {
				showSaveError(((SaveException) e.getCause()).getStatus());
			} else {
				T.racer().error("Save failed", e);
			}
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			T.racer().error(exception.getMessage(), exception);
		} finally {
			diagramBehavior.enableAdapters();
		}

		Resource[] savedResourcesArray = savedResources.toArray(new Resource[savedResources.size()]);
		diagramBehavior.getDiagramContainer().commandStackChanged(null);
		IDiagramTypeProvider provider = diagramBehavior.getConfigurationProvider().getDiagramTypeProvider();
		provider.resourcesSaved(provider.getDiagram(), savedResourcesArray);
	}

	/**
	 * Returns if the editor needs to be saved or not. Is queried by the
	 * {@link DiagramBehavior#isDirty()} method. The default implementation
	 * checks if the top of the current undo stack is equal to the stored top
	 * command of the undo stack at the time of the last saving of the editor.
	 * 
	 * @return <code>true</code> in case the editor needs to be saved,
	 *         <code>false</code> otherwise.
	 */
	public boolean isDirty() {
		TransactionalEditingDomain editingDomain = diagramBehavior.getEditingDomain();
		if (editingDomain == null) {
			// Right in the middle of closing the editor, it cannot be dirty
			// without an editing domain
			return false;
		}
		BasicCommandStack commandStack = (BasicCommandStack) editingDomain.getCommandStack();
		if (commandStack == null) {
			// Right in the middle of closing the editor, it cannot be dirty
			// without a command stack
			return false;
		}
		return savedCommand != commandStack.getUndoCommand();
	}

	/**
	 * Returns the EMF save options to be used when saving the EMF
	 * {@link Resource}s.
	 * 
	 * @return a {@link Map} object holding the used EMF save options.
	 */
	protected Map<Resource, Map<?, ?>> createSaveOptions() {
		// Save only resources that have actually changed.
		final Map<Object, Object> saveOption = new HashMap<Object, Object>();
		saveOption.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		EList<Resource> resources = diagramBehavior.getEditingDomain().getResourceSet().getResources();
		final Map<Resource, Map<?, ?>> saveOptions = new HashMap<Resource, Map<?, ?>>();
		for (Resource resource : resources) {
			saveOptions.put(resource, saveOption);
		}
		return saveOptions;
	}

	/**
	 * Creates the runnable to be used to wrap the actual saving of the EMF
	 * {@link Resource}s.<br>
	 * To only modify the actual saving clients should rather override
	 * {@link #save(TransactionalEditingDomain, Map)}.
	 * 
	 * @param savedResources
	 *            this parameter will after the operation has been performed
	 *            contain all EMF {@link Resource}s that have really been saved.
	 * 
	 * @param saveOptions
	 *            the EMF save options to use.
	 * @return an {@link IRunnableWithProgress} instance wrapping the actual
	 *         save process.
	 */
	protected IRunnableWithProgress createOperation(final Set<Resource> savedResources,
			final Map<Resource, Map<?, ?>> saveOptions) {
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		final IRunnableWithProgress operation = new SaveOperation(saveOptions, savedResources);
		return operation;
	}

	/**
	 * Saves all resources in the given {@link TransactionalEditingDomain}. Can
	 * be overridden to enable additional (call the super method to save the EMF
	 * resources) or other persistencies.
	 * 
	 * @param editingDomain
	 *            the {@link TransactionalEditingDomain} for which all resources
	 *            will be saved
	 * @param saveOptions
	 *            the EMF save options used for the saving.
	 * @param monitor
	 *            The progress monitor to use for reporting progress
	 * @return a {@link Set} of all EMF {@link Resource}s that were actually
	 *         saved.
	 * @since 0.10 The parameter monitor has been added compared to the 0.9
	 *        version of this method
	 */
	protected Set<Resource> save(final TransactionalEditingDomain editingDomain,
			final Map<Resource, Map<?, ?>> saveOptions,
			IProgressMonitor monitor) {

		final Map<URI, Throwable> failedSaves = new HashMap<URI, Throwable>();
		final Set<Resource> savedResources = new HashSet<Resource>();
		final IWorkspaceRunnable wsRunnable = new IWorkspaceRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException {

				final Runnable runnable = new Runnable() {
					public void run() {
						Transaction parentTx;
						if (editingDomain != null
								&& (parentTx = ((InternalTransactionalEditingDomain) editingDomain)
										.getActiveTransaction()) != null) {
							do {
								if (!parentTx.isReadOnly()) {
									throw new IllegalStateException(
											"saveInWorkspaceRunnable() called from within a command (likely to produce deadlock)"); //$NON-NLS-1$
								}
							} while ((parentTx = ((InternalTransactionalEditingDomain) editingDomain)
									.getActiveTransaction().getParent()) != null);
						}

						final EList<Resource> resources = editingDomain.getResourceSet().getResources();
						// Copy list to an array to prevent
						// ConcurrentModificationExceptions during the saving of
						// the dirty resources
						Resource[] resourcesArray = new Resource[resources.size()];
						resourcesArray = resources.toArray(resourcesArray);
						for (int i = 0; i < resourcesArray.length; i++) {
							// In case resource modification tracking is
							// switched on, we can check if a resource has been
							// modified, so that we only need to save really
							// changed resources; otherwise we need to save all
							// resources in the set
							final Resource resource = resourcesArray[i];

							if (shouldSave(resource)) {
								try {
									resource.save(saveOptions.get(resource));
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
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(wsRunnable, null);
			if (!failedSaves.isEmpty()) {
				SaveException exception = createException(failedSaves);
				IStatus[] statuses = exception.getStatus().getChildren();
				for (IStatus status : statuses) {
					T.racer().error("Resources could not be saved", status.getException());
				}
				throw exception;
			}
		} catch (final CoreException e) {
			final Throwable cause = e.getStatus().getException();
			if (cause instanceof RuntimeException) {
				throw (RuntimeException) cause;
			}
			throw new RuntimeException(e);
		}

		return savedResources;
	}

	private SaveException createException(Map<URI, Throwable> failedSaves) {
		MultiStatus multiStatus = new MultiStatus(GraphitiUIPlugin.PLUGIN_ID, 0,
				Messages.DefaultPersistencyBehavior_2, null);
		for (Entry<URI, Throwable> entry : failedSaves.entrySet()) {
			IStatus status = new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, entry.getKey().toString(),
					entry.getValue());
			multiStatus.add(status);
		}
		return new SaveException(multiStatus);
	}

	/**
	 * Called in {@link #saveDiagram(IProgressMonitor)} to update the Graphiti
	 * diagram version before saving a diagram. Currently the diagram version is
	 * set to 0.12.0
	 * 
	 * @param diagram
	 *            the {@link Diagram} to update the version attribute for
	 */
	protected void setDiagramVersion(final Diagram diagram) {
		// Only trigger a command if the version really changes to avoid an
		// empty entry in the command stack / undo stack
		if (!IDiagramVersion.CURRENT.equals(diagram.getVersion())) {
			CommandStack commandStack = diagramBehavior.getEditingDomain().getCommandStack();
			commandStack.execute(new RecordingCommand(diagramBehavior.getEditingDomain()) {
				@Override
				protected void doExecute() {
					diagram.eSet(PictogramsPackage.eINSTANCE.getDiagram_Version(), IDiagramVersion.CURRENT);
				}
			});
		}
	}

	/**
	 * Checks whether a resource should be save during the diagram save process.
	 * By default, just passes the check to the editing domain.
	 * 
	 * @param resource
	 *            the {@link Resource} to check
	 * @return true if the resource must be saved, i.e., it's not read-only
	 * 
	 * @since 0.11
	 */
	protected boolean shouldSave(Resource resource) {
		/*
		 * Bug 371513 - Added check for isLoaded(): a resource that has not yet
		 * been loaded (possibly after a reload triggered by a change in another
		 * editor) has no content yet; saving such a resource will simply erase
		 * _all_ content from the resource on the disk (including the diagram).
		 * --> a not yet loaded resource must not be saved
		 */
		/*
		 * Bug 441676 - Removed check for isReadOnly on resource level. When
		 * read-only resources are saved an exception should be thrown to
		 * indicate to the user that something went wrong.
		 */
		return (!resource.isTrackingModification() || resource.isModified()) && resource.isLoaded();
	}

	/**
	 * @since 0.12
	 */
	protected void showSaveError(final IStatus status) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				new ErrorDialog(Display.getDefault().getActiveShell(), Messages.DefaultPersistencyBehavior_3,
						Messages.DefaultPersistencyBehavior_4, status, IStatus.ERROR).open();
			}
		});
	}

	/**
	 * The workspace operation used to do the actual save.
	 * 
	 * @since 0.11
	 */
	protected final class SaveOperation implements IRunnableWithProgress, IThreadListener {
		private final Map<Resource, Map<?, ?>> saveOptions;
		private final Set<Resource> savedResources;

		private SaveOperation(Map<Resource, Map<?, ?>> saveOptions, Set<Resource> savedResources) {
			this.saveOptions = saveOptions;
			this.savedResources = savedResources;
		}

		// This is the method that gets invoked when the operation runs.
		public void run(IProgressMonitor monitor) {
			// Save the resources to the file system.
			try {
				savedResources.addAll(save(diagramBehavior.getEditingDomain(), saveOptions, monitor));
			} catch (final WrappedException e) {
				Status errorStatus = new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, 0, e.getMessage(),
						e.exception());
				showSaveError(errorStatus);
				T.racer().error(e.getMessage(), e.exception());
			}
		}

		/*
		 * Transfer the rule from the calling thread to the callee. This should
		 * be invoked before executing the callee and after the callee has
		 * executed, thus transferring the rule back to the calling thread. See
		 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=391046
		 */
		@Override
		public void threadChange(Thread thread) {
			ISchedulingRule rule = Job.getJobManager().currentRule();
			if (rule != null) {
				Job.getJobManager().transferRule(rule, thread);
			}
		}
	}

	/**
	 * @since 0.12
	 */
	protected final class SaveException extends RuntimeException {

		private static final long serialVersionUID = 5342549194820431664L;

		private MultiStatus status;

		public SaveException(MultiStatus status) {
			super(Messages.DefaultPersistencyBehavior_4);
			this.status = status;
		}

		/**
		 * @return the status
		 */
		public MultiStatus getStatus() {
			return status;
		}
	}
}
