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
 *    mgorning - Bug 329517 - state call backs during creation of a connection
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Connection;

/**
 * The Interface ICreateConnection.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ICreateConnection extends ICreateInfo {

	/**
	 * Can create.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canCreate(ICreateConnectionContext context);

	/**
	 * Creates the.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the connection
	 */
	Connection create(ICreateConnectionContext context);

	/**
	 * Can start connection.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canStartConnection(ICreateConnectionContext context);

	/**
	 * Will called after a connection creation tool from the palette was
	 * selected.
	 * 
	 * @since 0.9
	 */
	void startConnecting();

	/**
	 * Will called after a connection creation tool from the palette was
	 * deselected.
	 * 
	 * @since 0.9
	 */
	void endConnecting();

	/**
	 * Will called after a connection was successfully attached to an anchor of
	 * a source object.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @since 0.9
	 */
	void attachedToSource(ICreateConnectionContext context);

	/**
	 * Will called if the connection creation process was canceled after the
	 * successful attachment of the connection to an anchor of a source object.
	 * E.g. user pressed ESC, user clicked on an invalid target, focus was lost,
	 * ...
	 * 
	 * @param context
	 *            the context
	 * 
	 * @since 0.9
	 */
	void canceledAttaching(ICreateConnectionContext context);
}
