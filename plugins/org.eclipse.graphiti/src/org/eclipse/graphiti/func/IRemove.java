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

import org.eclipse.graphiti.features.context.IRemoveContext;

/**
 * Instances of this interface provide the behavior to remove objects. Removing
 * means removing the graphical representation of a business object from the
 * diagram while keeping the business object itself as it is.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.8.0
 */
public interface IRemove {

	/**
	 * Checks if given object could be removed.
	 * 
	 * @param context
	 *            contains object to remove
	 * 
	 * @return true if remove is possible
	 */
	boolean canRemove(IRemoveContext context);

	/**
	 * called prior to remove call.
	 * 
	 * @param context
	 *            the context
	 */
	void preRemove(IRemoveContext context);

	/**
	 * Remove the given object.
	 * 
	 * @param context
	 *            contains object to remove
	 */
	void remove(IRemoveContext context);

	/**
	 * called after remove call.
	 * 
	 * @param context
	 *            the context
	 */
	void postRemove(IRemoveContext context);

}
