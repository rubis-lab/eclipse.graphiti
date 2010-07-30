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

	/**
	 * Instantiates a new selection info impl.
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
	 * Instantiates a new selection info impl.
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
	 * Instantiates a new selection info impl.
	 */
	public SelectionInfoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getColor()
	 */
	public IColorConstant getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getHandleForegroundColor()
	 */
	public IColorConstant getHandleForegroundColor() {
		return handleForegroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setHandleForegroundColor(org.eclipse
	 * .graphiti.util.IColorConstant)
	 */
	public void setHandleForegroundColor(IColorConstant handleForegroundColor) {
		this.handleForegroundColor = handleForegroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getHandleBackgroundColor()
	 */
	public IColorConstant getHandleBackgroundColor() {
		return handleBackgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setHandleBackgroundColor(org.eclipse
	 * .graphiti.util.IColorConstant)
	 */
	public void setHandleBackgroundColor(IColorConstant handleBackgroundColor) {
		this.handleBackgroundColor = handleBackgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setLineStyle(org.eclipse.graphiti.
	 * mm.pictograms.LineStyleEnum)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setColor(org.eclipse.graphiti.util
	 * .IColorConstant)
	 */
	public void setColor(IColorConstant color) {
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getHoverColor()
	 */
	@Override
	public IColorConstant getHoverColor() {
		return hoverColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setLineStyle(org.eclipse.graphiti
	 * .util.IColorConstant)
	 */
	@Override
	public void setLineStyle(IColorConstant color) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setHoverColor(org.eclipse.graphiti
	 * .util.IColorConstant)
	 */
	@Override
	public void setHoverColor(IColorConstant hoverColor) {
		this.hoverColor = hoverColor;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ISelectionInfo#getHoverColorParentSelected()
	 */
	@Override
	public IColorConstant getHoverColorParentSelected() {
		return hoverColorParentSelected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.ISelectionInfo#setHoverColorParentSelected(org
	 * .eclipse.graphiti.util.IColorConstant)
	 */
	@Override
	public void setHoverColorParentSelected(IColorConstant hoverColor) {
		this.hoverColorParentSelected = hoverColor;

	}
}
