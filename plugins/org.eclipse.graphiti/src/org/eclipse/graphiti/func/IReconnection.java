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
 *    mgorning - Bug 343983 - Notification for Cancelled Reconnection Events
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.IReconnectionContext;

/**
 * The Interface IReconnection.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IReconnection {

	/**
	 * Can reconnect.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canReconnect(IReconnectionContext context);

	/**
	 * Reconnnect.
	 * 
	 * @param context
	 *            the context
	 */
	void reconnect(IReconnectionContext context);

	/**
	 * Pre reconnnect.
	 * 
	 * @param context
	 *            the context
	 */
	void preReconnect(IReconnectionContext context);

	/**
	 * Post reconnnect.
	 * 
	 * @param context
	 *            the context
	 */
	void postReconnect(IReconnectionContext context);

	/**
	 * Will called if the connection reconnect process was canceled after
	 * dragging the start or end of the connection. E.g. user pressed ESC, user
	 * clicked on an invalid target, focus was lost, ...
	 * 
	 * @param context
	 *            the context
	 * 
	 * @since 0.9
	 */
	void canceledReconnect(IReconnectionContext context);
}
