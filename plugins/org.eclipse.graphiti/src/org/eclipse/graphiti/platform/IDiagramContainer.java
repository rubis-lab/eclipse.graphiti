/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
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
package org.eclipse.graphiti.platform;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IDiagramContainer. This is the base interface for all
 * containers that can display diagrams. Such a container may be an editor or a
 * view, possibly also a plain composite used within e.g. a popup or inside an
 * editor or view.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           DiagramEditor or DiagramComposite instead.
 * 
 * @since 0.10
 */
public interface IDiagramContainer extends IDiagramEditor {

	/*
	 * Selection handling
	 */

	/**
	 * Selects all the given pictogram elements in the container.
	 * 
	 * @param pictogramElements
	 *            The pictogram elements to select
	 */
	public void selectPictogramElements(PictogramElement[] pictogramElements);

	/**
	 * Gets all pictogram elements that are currently selected.
	 * 
	 * @return all selected pictogram elements
	 */
	public PictogramElement[] getSelectedPictogramElements();

	/**
	 * Sets the pictogram element which should be selected after the container
	 * refresh.
	 * 
	 * @param pictogramElement
	 *            The pictogram element to select
	 */
	public void setPictogramElementForSelection(PictogramElement pictogramElement);

	/**
	 * Sets the pictogram elements which should be selected after the container
	 * refresh.
	 * 
	 * @param pictogramElements
	 *            The pictogram elements to select
	 */
	public void setPictogramElementsForSelection(PictogramElement[] pictogramElements);

	/*
	 * EMF
	 */

	/**
	 * Gets the transactional editing domain.
	 * 
	 * @return The transactional editing domain which is used in the container
	 */
	public TransactionalEditingDomain getEditingDomain();

	/**
	 * Gets the resource set.
	 * 
	 * @return The resource set which is used in the container
	 */
	public ResourceSet getResourceSet();

	/*
	 * Refreshing and UI
	 */

	/**
	 * Gets the title for the container that is displaying the diagram.
	 * 
	 * @return The title as a {@link String}
	 */
	public String getTitle();

	/**
	 * Returns the tooltip for the container. For an editor that would e.g. be
	 * the string that is displayed when hovering over the editor title tab.
	 * 
	 * @return The tooltip as a {@link String}
	 */
	public String getTitleToolTip();

	/**
	 * Refreshes the container.
	 */
	public void refresh();

	/**
	 * Refreshes the title text of this container. It depends on the container
	 * type what and if anything is refreshed, for an editor e.g. the part tab
	 * will be refreshed.
	 */
	public void refreshTitle();

	/**
	 * Refreshes the title tool tip text of this part. It depends on the
	 * container type what and if anything is refreshed, for an editor e.g. the
	 * part tab tooltip will be refreshed.
	 */
	public void refreshTitleToolTip();

	/**
	 * Refreshes all rendering decorators for the given pictogram element. That
	 * means: 1. delete current decorators 2. ask the tool behaviour provider
	 * for decorator data 3. create new decorators with this data and render
	 * this new decorators
	 * 
	 * @param pe
	 *            The pictogram element to refresh the decorators for
	 */
	public void refreshRenderingDecorators(PictogramElement pe);

	/**
	 * Refreshes the containers's palette.
	 */
	public void refreshPalette();

	/**
	 * Refreshes the content of the container (what's shown inside the diagram
	 * itself).
	 */
	public void refreshContent();

	/*
	 * Lifecycle
	 */

	/**
	 * Checks if the container is dirty.
	 * 
	 * @return <code>true</code>, if container is dirty, <code>false</code>
	 *         otherwise
	 */
	public boolean isDirty();

	/**
	 * Triggers that the diagram model is persisted.
	 * 
	 */
	public void doSave(IProgressMonitor monitor);

	/**
	 * Updates the UI of the container to correctly reflect the dirty state.
	 * What (and if anything) happens depends on the container type. The default
	 * implementation in the editor e.g. does this by firing a
	 * {@link IEditorPart#PROP_DIRTY} property change.
	 */
	void updateDirtyState();

	/**
	 * Checks if this container is alive.
	 * 
	 * @return <code>true</code>, if the container contains a model connector
	 *         and a valid Diagram, <code>false</code> otherwise.
	 */
	public boolean isAlive();

	/**
	 * Notify the container that it should shut down or clear it's state.
	 */
	public void close();

	/*
	 * Other
	 */

	/**
	 * Gets the diagram type provider.
	 * 
	 * @return The diagram type provider
	 */
	public IDiagramTypeProvider getDiagramTypeProvider();

	/**
	 * Executes the given feature in the given context using the command stack
	 * and editing domain of the container. In case of an IAddFeature being
	 * passed this method will also trigger the selection of the newly added
	 * shape.
	 * 
	 * @param feature
	 *            The feature to execute
	 * @param context
	 *            The context object to use with the feature
	 * @return an object representing the result of the feature call (depends on
	 *         the concrete implementation)
	 * 
	 */
	public Object executeFeature(IFeature feature, IContext context);
}
