/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011 Volker Wegert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Volker Wegert - initial API, implementation and documentation:
 *                    Bug 336828: patterns should support delete,
 *                    remove, direct editing and conditional palette
 *                    creation entry
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.IDeleteContext;

/**
 * Instances of this interface provide the behavior to delete objects. Deleting
 * means removing both the business object from its model as well as its
 * graphical representation from the diagram.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.8.0
 */
public interface IDelete {

	/**
	 * Can delete hook. Needs to be implemented in order to decide if a feature
	 * can (and wants to) handle a delete request.
	 * 
	 * @param context
	 *            the context describing the request
	 * 
	 * @return <code>true</code>, if the feature can perform the delete
	 *         operation
	 */
	boolean canDelete(IDeleteContext context);

	/**
	 * Pre delete hook that can be implemented by users to perform any
	 * operations that need to be done before the standard delete functionality
	 * starts. Be sure to call
	 * {@link DefaultDeleteFeature#setDoneChanges(boolean)} in case you modify
	 * any EMF objects to enable that the command stack gets updated.
	 * 
	 * @param context
	 *            the context
	 */
	void preDelete(IDeleteContext context);

	/**
	 * Hook to implement the actual delete functionality.
	 * 
	 * @param context
	 *            the context
	 */
	void delete(IDeleteContext context);

	/**
	 * Post delete hook that can be implemented by users to perform any
	 * operations that need to be done after the standard delete functionality
	 * ends.
	 * 
	 * @param context
	 *            the context
	 */
	void postDelete(IDeleteContext context);

}