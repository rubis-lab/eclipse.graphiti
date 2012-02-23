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
 *    mwenz - Bug 358255 - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.util.IColorConstant;

/**
 * Border decorators can be used to add a border (a rectangle around the shape)
 * to the visualization of a shape without modifying the dirty state of the
 * displaying editor, see {@link IDecorator}.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @since 0.9
 */
public class BorderDecorator extends AbstractDecorator implements IBorderDecorator {

	private IColorConstant borderColor = null;
	private Integer lineWidth = null;
	private Integer lineStyle = null;

	/**
	 * Creates a new border decorator that decorates a shape with a border.
	 */
	public BorderDecorator() {
		super();
	}

	/**
	 * Creates a new border decorator that decorates a shape with a border with
	 * the given settings. See the setter methods for details on these values.
	 * 
	 * @param borderColor
	 *            an {@link IColorConstant} defining the color for the border
	 *            line
	 * @param lineWidth
	 *            an {@link Integer} defining the width of the border line
	 * @param lineStyle
	 *            an {@link Integer} defining the style of the border line
	 */
	public BorderDecorator(IColorConstant borderColor, Integer lineWidth, Integer lineStyle) {
		super();
		this.borderColor = borderColor;
		this.lineWidth = lineWidth;
		this.lineStyle = lineStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IBorderDecorator#getBorderColor()
	 */
	public IColorConstant getBorderColor() {
		return borderColor;
	}

	/**
	 * Sets the color to be used for the border line. By default (when
	 * <code>null</code> is set) {@link IColorConstant#BLACK} is used.
	 * 
	 * @param borderColor
	 */
	public void setBorderColor(IColorConstant borderColor) {
		this.borderColor = borderColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IBorderDecorator#getBorderWidth()
	 */
	public Integer getBorderWidth() {
		return lineWidth;
	}

	/**
	 * Sets the width that will be used for the border line. By default (when
	 * <code>null</code> or a value smaller than 1 is set) 1 is used.
	 * 
	 * @param lineWidth
	 *            an Integer defining the width of the border line
	 */
	public void setBorderWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IBorderDecorator#getBorderStyle()
	 */
	public Integer getBorderStyle() {
		return lineStyle;
	}

	/**
	 * Sets the style that will be used for the border line. Possible values
	 * are:
	 * <p>
	 * <ul>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_SOLID}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASH}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASHDOT}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASHDOTDOT}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DOT}</li>
	 * </ul>
	 * By default (when <code>null</code> or an invalid value is set)
	 * {@link org.eclipse.draw2d.Graphics#LINE_SOLID} is used.
	 * 
	 * @param lineStyle
	 *            an Integer defining the style of the border line
	 */
	public void setBorderStyle(Integer lineStyle) {
		this.lineStyle = lineStyle;
	}
}
