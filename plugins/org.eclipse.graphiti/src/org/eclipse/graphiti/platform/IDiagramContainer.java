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
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
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
public interface IDiagramContainer {

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
	 * Returns the associated diagram behavior instance, that describes and
	 * implements the standard diagram behavior.
	 * 
	 * @return The associated instance of {@link IDiagramBehavior}.
	 */
	public IDiagramBehavior getDiagramBehavior();
}
