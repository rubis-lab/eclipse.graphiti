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
 *    mgorning - Bug 391523 - Revise getSelectionInfo...() in IToolBehaviorProvider
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Interface IConnectionSelectionInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients, use
 *              {@link ConnectionSelectionInfoImpl} instead
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.10
 */
public interface IConnectionSelectionInfo extends ISelectionInfo {

	/**
	 * Gets the primary selection foreground color for the bendpoint.
	 * 
	 * @return the foreground color
	 */
	IColorConstant getPrimarySelectionBendpointForegroundColor();

	/**
	 * Gets the primary selection background color for the bendpoint.
	 * 
	 * @return the background color
	 */
	IColorConstant getPrimarySelectionBendpointBackgroundColor();

	/**
	 * Gets the secondary selection foreground color for the bendpoint.
	 * 
	 * @return the foreground color
	 */
	IColorConstant getSecondarySelectionBendpointForegroundColor();

	/**
	 * Gets the secondary selection background color for the bendpoint.
	 * 
	 * @return the background color
	 */
	IColorConstant getSecondarySelectionBendpointBackgroundColor();

	/**
	 * Sets the primary selection foreground color for the bendpoint.
	 * 
	 * @param color
	 *            the color
	 */
	void setPrimarySelectionBendpointForegroundColor(IColorConstant color);

	/**
	 * Sets the primary selection background color for the bendpoint.
	 * 
	 * @param color
	 *            the color
	 */
	void setPrimarySelectionBendpointBackgroundColor(IColorConstant color);

	/**
	 * Sets the secondary selection foreground color for the bendpoint.
	 * 
	 * @param color
	 *            the color
	 */
	void setSecondarySelectionBendpointForegroundColor(IColorConstant color);

	/**
	 * Sets the secondary selection background color for the bendpoint.
	 * 
	 * @param color
	 *            the color
	 */
	void setSecondarySelectionBendpointBackgroundColor(IColorConstant color);

}
