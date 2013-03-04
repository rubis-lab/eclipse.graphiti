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
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    pjpaulin - Bug 352120 - Added non-GEF methods required for a diagram container.
 *    pjpaulin - Bug 352120 - Removed @noimplement and @noextend. Created filters for new methods.
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.platform;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IDiagramEditor.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           DiagramEditor instead.
 * @deprecated Use {@link IDiagramContainer} instead
 */
public interface IDiagramEditor {

	/**
	 * Select all the given pictogram elements in the editor.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	void selectPictogramElements(PictogramElement[] pictogramElements);

	/**
	 * Get all pictogram elements currently selected.
	 * 
	 * @return all selected pictogram elements
	 */
	PictogramElement[] getSelectedPictogramElements();

	/**
	 * Sets the pictogram element which should be selected after the editor
	 * refresh.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 */
	void setPictogramElementForSelection(PictogramElement pictogramElement);

	/**
	 * Sets the pictogram elements which should be selected after the editor
	 * refresh.
	 * 
	 * @param pictogramElements
	 *            the pictogram element
	 */
	void setPictogramElementsForSelection(PictogramElement[] pictogramElements);

	/**
	 * Gets the transactional editing domain.
	 * 
	 * @return transactional editing domain which is linked to the editor
	 */
	TransactionalEditingDomain getEditingDomain();

	/**
	 * Gets the resource set.
	 * 
	 * @return resource set
	 */
	ResourceSet getResourceSet();

	/**
	 * Gets the diagram type provider.
	 * 
	 * @return the diagram type provider
	 */
	IDiagramTypeProvider getDiagramTypeProvider();

	/**
	 * Refresh.
	 */
	void refresh();

	/**
	 * Checks if is dirty.
	 * 
	 * @return true, if editor is dirty
	 */
	boolean isDirty();

	/**
	 * Refreshes the title text of this part.
	 * 
	 * @since 0.9
	 */
	void refreshTitle();

	/**
	 * Refreshes the title tool tip text of this part.
	 */
	void refreshTitleToolTip();

	/**
	 * Refreshes all rendering decorators for the given pictogram element. That
	 * means: 1. delete current decorators 2. ask the toolbehaviour provider for
	 * decorator data 3. create new decorators with this data and render this
	 * new decorators
	 * 
	 * @param pe
	 *            pictogram element
	 */
	void refreshRenderingDecorators(PictogramElement pe);

	/**
	 * Refreshes the editor's palette.
	 */
	void refreshPalette();
	
	/**
	 * Executes the given feature in the given context using the command stack
	 * and editing domain of the diagram editor. In case of an IAddFeature being
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
	 * @since 0.9
	 */
	Object executeFeature(IFeature feature, IContext context);
}
