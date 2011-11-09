/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
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
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class SelectionInfoImpl.
 */
public class SelectionInfoImpl implements ISelectionInfo {

	private IColorConstant color;

	private IColorConstant handleForegroundColor;

	private IColorConstant handleBackgroundColor;

	private IColorConstant hoverColor;

	private LineStyle lineStyle;

	private IColorConstant hoverColorParentSelected;

	private IColorConstant primarySelectionBackgroundColor;

	private IColorConstant secondarySelectionBackgroundColor;

	/**
	 * Creates a new {@link SelectionInfoImpl}.
	 * 
	 * @param color
	 *            the color
	 * @param handleForegroundColor
	 *            the handle foreground color
	 * @param handleBackgroundColor
	 *            the handle background color
	 * @param lineStyle
	 *            the line style
	 */
	public SelectionInfoImpl(IColorConstant color, IColorConstant handleForegroundColor, IColorConstant handleBackgroundColor,
			LineStyle lineStyle) {

		setColor(color);
		setHandleForegroundColor(handleForegroundColor);
		setHandleBackgroundColor(handleBackgroundColor);
		setLineStyle(lineStyle);
	}

	/**
	 * Creates a new {@link SelectionInfoImpl}.
	 * 
	 * @param color
	 *            the color
	 * @param handleForegroundColor
	 *            the handle foreground color
	 * @param handleBackgroundColor
	 *            the handle background color
	 * @param lineStyle
	 *            the line style
	 */
	public SelectionInfoImpl(IColorConstant color, IColorConstant handleForegroundColor, IColorConstant handleBackgroundColor,
			IColorConstant hoverColor, LineStyle lineStyle) {

		setColor(color);
		setHandleForegroundColor(handleForegroundColor);
		setHandleBackgroundColor(handleBackgroundColor);
		setHoverColor(hoverColor);
		setLineStyle(lineStyle);
	}

	/**
	 * Creates a new {@link SelectionInfoImpl}.
	 */
	public SelectionInfoImpl() {
	}

	public IColorConstant getColor() {
		return this.color;
	}

	public IColorConstant getHandleForegroundColor() {
		return this.handleForegroundColor;
	}

	public void setHandleForegroundColor(IColorConstant handleForegroundColor) {
		this.handleForegroundColor = handleForegroundColor;
	}

	public IColorConstant getHandleBackgroundColor() {
		return this.handleBackgroundColor;
	}

	public void setHandleBackgroundColor(IColorConstant handleBackgroundColor) {
		this.handleBackgroundColor = handleBackgroundColor;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public void setColor(IColorConstant color) {
		this.color = color;
	}

	public IColorConstant getHoverColor() {
		return this.hoverColor;
	}

	public void setLineStyle(IColorConstant color) {

	}

	public void setHoverColor(IColorConstant hoverColor) {
		this.hoverColor = hoverColor;

	}

	public IColorConstant getHoverColorParentSelected() {
		return this.hoverColorParentSelected;
	}

	public void setHoverColorParentSelected(IColorConstant hoverColor) {
		this.hoverColorParentSelected = hoverColor;

	}

	public IColorConstant getPrimarySelectionBackGroundColor() {
		return this.primarySelectionBackgroundColor;
	}

	public IColorConstant getSecondarySelectionBackGroundColor() {
		return this.secondarySelectionBackgroundColor;

	}

	public void setPrimarySelectionBackgroundColor(IColorConstant color) {
		this.primarySelectionBackgroundColor = color;
	}

	public void setSecondarySelectionBackgroundColor(IColorConstant color) {
		this.secondarySelectionBackgroundColor = color;
	}
}
