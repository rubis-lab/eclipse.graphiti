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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;


/**
 * This interface is intended as UI independent base interface for the common
 * behavior object that describes and implements the behavior of diagrams and
 * can be reused within all kinds of diagram containers, e.g. editors, views or
 * plain UI composites.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           DiagramBehavior instead.
 * 
 * @since 0.10
 */
public interface IDiagramBehavior {

	/**
	 * Returns the associated container visualizing the diagram.
	 * 
	 * @return The associated instance of {@link IDiagramContainer}.
	 */
	public IDiagramContainer getDiagramContainer();

	/**
	 * Refreshes the complete visualization.
	 */
	public void refresh();

	/**
	 * Refreshes the containers's palette.
	 */
	public void refreshPalette();

	/**
	 * Refreshes the content of the container (what's shown inside the diagram
	 * itself).
	 */
	public void refreshContent();

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
	 * Executes the given feature in the given context using the command stack
	 * and editing domain of the diagram behavior object. In case of an
	 * IAddFeature being passed this method will also trigger the selection of
	 * the newly added shape.
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

	/**
	 * Gets the transactional editing domain.
	 * 
	 * @return The transactional editing domain which is used in the behavior
	 *         object
	 */
	public TransactionalEditingDomain getEditingDomain();
}
