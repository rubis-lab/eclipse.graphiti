/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 388335 - Undo/Redo functionality as part of features
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.IDescription;
import org.eclipse.graphiti.IName;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;

/**
 * The Interface IFeature provides the common API for all kinds of features in
 * Graphiti. Features implement a piece of functionality for a Graphiti editor.
 * Usually they implement one aspect in the lifecycle of one domain object, e.g.
 * creating a new domain object of a specific type or adding an existing domain
 * object to the diagram.
 * 
 * There are various more specific sub interfaces that define the specific API
 * for the different kinds of feature like {@link IAddFeature} or
 * {@link ICreateFeature}.
 * 
 * IFeature offers similar functionality as actions and commands and contains
 * methods that enable the Graphiti framework to check if a feature is available
 * and enabled in the current situation and a method to execute it.
 * 
 * Classes that implement this interface may also implement
 * {@link ICustomUndoableFeature} to provide enhanced undo/redo functionality.
 * This might be of interest for non-EMF domain objects but also in general if
 * you need to trigger additional operations when an undo or redo of the feature
 * is done.
 * 
 * @see org.eclipse.graphiti.features.context.IContext
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IFeature extends IName, IDescription, IFeatureProviderHolder {

	/**
	 * Decides if the current feature is available with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if it is available, false if not
	 * 
	 * @see IContext
	 */
	boolean isAvailable(IContext context);

	/**
	 * Decides if the current feature can execute with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if the feature can be executed, false if not
	 * 
	 * @see IContext
	 */
	boolean canExecute(IContext context);

	/**
	 * Executes the current feature with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @see IContext
	 */
	void execute(IContext context);

	/**
	 * Decides if the current feature can be undone - this is the undo of the
	 * execute operation.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if the feature can be undone, false if not
	 * 
	 * @see IContext
	 */
	boolean canUndo(IContext context);

	/**
	 * Is queried by the framework after a feature has been executed to find out
	 * if this feature should appear in the undo stack of e.g. an editor. By
	 * default all features should appear there (see implementation in
	 * {@link AbstractFeature}), but features may decide to override this
	 * behavior. Note that this is a dynamic attribute of the feature that is
	 * queried each time <b>after</b> the feature has been executed.
	 * <p>
	 * <b>IMPORTANT NOTE:</b> The implementor of the feature is responsible for
	 * correctly implementing this method! It might lead to inconsistencies in
	 * the command stack if this method returns <code>false</code> although the
	 * feature did changes.
	 * 
	 * @return <code>true</code> if the feature should appear in the undo stack,
	 *         <code>false</code> otherwise
	 */
	boolean hasDoneChanges();
}