/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.internal.IDiagramVersion;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

/**
 * This class can be sub-classed by clients to adapt the persistency of the
 * Graphiti diagram Editor.
 * 
 * @since 0.9
 */
public class DefaultPersistencyBehavior {

	protected final DiagramEditor diagramEditor;

	public DefaultPersistencyBehavior(DiagramEditor diagramEditor) {
		this.diagramEditor = diagramEditor;
	}

	public Diagram loadDiagram(URI uri) {
		if (uri != null) {
			final TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
			if (editingDomain != null) {
				// First try the URI resolution without loading not yet loaded
				// resources because calling with loadOnDemand will _always_
				// create a new Resource instance for newly created and not yet
				// saved Resources, no matter if they already exist within the
				// ResourceSet or not
				EObject modelElement = editingDomain.getResourceSet().getEObject(uri, false);
				if (modelElement == null) {
					modelElement = editingDomain.getResourceSet().getEObject(uri, true);
					if (modelElement == null) {
						return null;
					}
				}
				modelElement.eResource().setTrackingModification(true);
				return (Diagram) modelElement;
			}
		}
		return null;
	}

	public void saveDiagram(IProgressMonitor monitor) {
		// set version info.
		final Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
		setDiagramVersion(diagram);

		Map<Resource, Map<?, ?>> saveOptions = createSaveOptions();
		final Set<Resource> savedResources = new HashSet<Resource>();
		final IRunnableWithProgress operation = createOperation(savedResources, saveOptions);

		diagramEditor.disableAdapters();
		try {
			// This runs the options, and shows progress.
			new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false,
					operation);

			((BasicCommandStack) diagramEditor.getEditingDomain().getCommandStack()).saveIsDone();
			// Refresh the necessary state.
			diagramEditor.updateDirtyState();
		} catch (final Exception exception) {
			// Something went wrong that shouldn't.
			T.racer().error(exception.getMessage(), exception);
		}
		diagramEditor.enableAdapters();

		Resource[] savedResourcesArray = savedResources.toArray(new Resource[savedResources.size()]);
		diagramEditor.commandStackChanged(null);
		IDiagramTypeProvider provider = diagramEditor.getConfigurationProvider().getDiagramTypeProvider();
		provider.resourcesSaved(diagramEditor.getDiagramTypeProvider().getDiagram(), savedResourcesArray);
	}

	protected Map<Resource, Map<?, ?>> createSaveOptions() {
		// Save only resources that have actually changed.
		final Map<Object, Object> saveOption = new HashMap<Object, Object>();
		saveOption.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		EList<Resource> resources = diagramEditor.getEditingDomain().getResourceSet().getResources();
		final Map<Resource, Map<?, ?>> saveOptions = new HashMap<Resource, Map<?, ?>>();
		for (Resource resource : resources) {
			saveOptions.put(resource, saveOption);
		}
		return saveOptions;
	}

	protected IRunnableWithProgress createOperation(final Set<Resource> savedResources,
			final Map<Resource, Map<?, ?>> saveOptions) {
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		final IRunnableWithProgress operation = new IRunnableWithProgress() {
			// This is the method that gets invoked when the operation runs.
			public void run(IProgressMonitor monitor) {
				// Save the resources to the file system.
				try {
					savedResources.addAll(save(diagramEditor.getEditingDomain(), saveOptions));
				} catch (final WrappedException e) {
					final MultiStatus errorStatus = new MultiStatus(GraphitiUIPlugin.PLUGIN_ID, 0, e.getMessage(),
							e.exception());
					GraphitiUIPlugin.getDefault().getLog().log(errorStatus);
					T.racer().error(e.getMessage(), e.exception());
				}
			}
		};
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
	 * @return a {@link Set} of all EMF {@link Resource}s that where actually
	 *         saved.
	 */
	protected Set<Resource> save(TransactionalEditingDomain editingDomain, Map<Resource, Map<?, ?>> saveOptions) {
		return GraphitiUiInternal.getEmfService().save(editingDomain, saveOptions);
	}

	protected void setDiagramVersion(final Diagram diagram) {
		diagramEditor.getEditingDomain().getCommandStack()
				.execute(new RecordingCommand(diagramEditor.getEditingDomain()) {

					@Override
					protected void doExecute() {
						diagram.eSet(PictogramsPackage.eINSTANCE.getDiagram_Version(), IDiagramVersion.CURRENT);
					}
				});
	}
}
