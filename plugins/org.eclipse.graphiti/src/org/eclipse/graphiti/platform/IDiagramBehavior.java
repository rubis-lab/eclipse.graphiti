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


/**
 * @since 0.10
 */
public interface IDiagramBehavior {

	public IDiagramContainer getDiagramContainer();

	public void refresh();

	public void refreshPalette();

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
