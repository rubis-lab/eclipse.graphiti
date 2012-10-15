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

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Interface ISelectionInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ISelectionInfo {

	/**
	 * Gets the color.
	 * 
	 * @return the color of the selection border
	 */
	IColorConstant getColor();

	/**
	 * Gets the hover color.
	 * 
	 * @return the on hover color
	 */
	IColorConstant getHoverColor();

	/**
	 * Gets the hover color for a shape whose parent is selected.
	 * 
	 * @return the hover color
	 */
	IColorConstant getHoverColorParentSelected();

	/**
	 * Gets the line style.
	 * 
	 * @return the line style of the selection
	 */
	LineStyle getLineStyle();

	/**
	 * Set the color of the selection.
	 * 
	 * @param color
	 *            the color
	 */
	void setColor(IColorConstant color);

	/**
	 * Sets the hover color.
	 * 
	 * @param hoverColor
	 *            the color
	 */
	void setHoverColor(IColorConstant hoverColor);

	/**
	 * Sets the hover color for shapes whose parent is selected.
	 * 
	 * @param hoverColor
	 *            the color
	 */
	void setHoverColorParentSelected(IColorConstant hoverColor);

	/**
	 * Set the line style of the selection.
	 * 
	 * @param lineStyle
	 *            the line style
	 */
	void setLineStyle(LineStyle lineStyle);

}
