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
 * The Interface IShapeSelectionInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients, use
 *              {@link ShapeSelectionInfoImpl} instead
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.10
 */
public interface IShapeSelectionInfo extends ISelectionInfo {
	/**
	 * Gets the primary selection background color. Only used for shapes and if
	 * no Rendering style is set.
	 * 
	 * @return the primary selection background color
	 */
	IColorConstant getPrimarySelectionBackGroundColor();

	/**
	 * Gets the secondary selection background color. Only used for shapes and
	 * if no Rendering style is set.
	 * 
	 * @return the secondary selection background color
	 */
	IColorConstant getSecondarySelectionBackGroundColor();

	/**
	 * Sets color for primary selection background. Only used for shapes and if
	 * no Rendering style is set.
	 * 
	 * @param color
	 *            the color
	 */
	void setPrimarySelectionBackgroundColor(IColorConstant color);

	/**
	 * Sets color for secondary selection background. Only used for shapes and
	 * if no Rendering style is set.
	 * 
	 * @param color
	 *            the color
	 */
	void setSecondarySelectionBackgroundColor(IColorConstant color);

}
