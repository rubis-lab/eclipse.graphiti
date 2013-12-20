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
 *    SAP AG - initial API, implementation and documentation (Bug 424458)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

/**
 * The Interface IResizeConnectionDecoratorContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.11
 */
public interface IResizeConnectionDecoratorContext extends IAreaContext, IResizeContext {

	/**
	 * Gets the connection decorator.
	 * 
	 * @return the connection decorator
	 */
	ConnectionDecorator getConnectionDecorator();

	/**
	 * Checks if execute is allowed.
	 * 
	 * @return true, if execute is allowed
	 */
	boolean isExecuteAllowed();
}