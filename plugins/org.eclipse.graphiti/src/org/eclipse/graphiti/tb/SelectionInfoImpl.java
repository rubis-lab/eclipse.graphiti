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

	@Override
	public IColorConstant getColor() {
		return this.color;
	}

	@Override
	public IColorConstant getHandleForegroundColor() {
		return this.handleForegroundColor;
	}

	@Override
	public void setHandleForegroundColor(IColorConstant handleForegroundColor) {
		this.handleForegroundColor = handleForegroundColor;
	}

	@Override
	public IColorConstant getHandleBackgroundColor() {
		return this.handleBackgroundColor;
	}

	@Override
	public void setHandleBackgroundColor(IColorConstant handleBackgroundColor) {
		this.handleBackgroundColor = handleBackgroundColor;
	}

	@Override
	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	@Override
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	@Override
	public void setColor(IColorConstant color) {
		this.color = color;
	}

	@Override
	public IColorConstant getHoverColor() {
		return this.hoverColor;
	}

	@Override
	public void setLineStyle(IColorConstant color) {

	}

	@Override
	public void setHoverColor(IColorConstant hoverColor) {
		this.hoverColor = hoverColor;

	}

	@Override
	public IColorConstant getHoverColorParentSelected() {
		return this.hoverColorParentSelected;
	}

	@Override
	public void setHoverColorParentSelected(IColorConstant hoverColor) {
		this.hoverColorParentSelected = hoverColor;

	}

	@Override
	public IColorConstant getPrimarySelectionBackGroundColor() {
		return this.primarySelectionBackgroundColor;
	}

	@Override
	public IColorConstant getSecondarySelectionBackGroundColor() {
		return this.secondarySelectionBackgroundColor;

	}

	@Override
	public void setPrimarySelectionBackgroundColor(IColorConstant color) {
		this.primarySelectionBackgroundColor = color;
	}

	@Override
	public void setSecondarySelectionBackgroundColor(IColorConstant color) {
		this.secondarySelectionBackgroundColor = color;
	}
}
