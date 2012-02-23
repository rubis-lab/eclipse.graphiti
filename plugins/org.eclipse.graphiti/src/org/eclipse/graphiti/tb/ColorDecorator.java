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
 * Color decorators can be used to modify the visualization of a shape without
 * modifying the dirty state of the displaying editor, see {@link IDecorator}.
 * Note that modifying background and foreground colors as it is possible with
 * this decorator will have no effect if the shape is invisible, the complete
 * shape is hidden underneath other (possibly contained) shapes or the shape
 * uses gradients.
 * 
 * @since 0.9
 */
public class ColorDecorator extends AbstractDecorator implements IColorDecorator {

	private IColorConstant foregroundColor = null;
	private IColorConstant backgroundColor = null;

	/**
	 * Creates a new color decorator that decorates a shape with foreground and
	 * background colors.
	 */
	public ColorDecorator() {
		super();
	}

	/**
	 * Creates a new color decorator that decorates a shape with the given
	 * foreground and background colors. See the setter methods for details on
	 * these values.
	 * 
	 * @param foregroundColor
	 *            an {@link IColorConstant} defining the color for the
	 *            foreground of the shape
	 * @param backgroundColor
	 *            an {@link IColorConstant} defining the color for the
	 *            background of the shape
	 */
	public ColorDecorator(IColorConstant foregroundColor, IColorConstant backgroundColor) {
		super();
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IColorDecorator#getForegroundColor()
	 */
	public IColorConstant getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * Sets the color to be used for the foreground of the shape. By default
	 * (when <code>null</code> is set)the original foreground color of the shape
	 * is kept.
	 * 
	 * @param foregroundColor
	 *            an {@link IColorConstant} defining the color for the
	 *            foreground of the shape
	 */
	public void setForegroundColor(IColorConstant foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IColorDecorator#getBackgroundColor()
	 */
	public IColorConstant getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Sets the color to be used for the background of the shape. By default
	 * (when <code>null</code> is set) the original background color of the
	 * shape is kept.
	 * 
	 * @param backgroundColor
	 *            an {@link IColorConstant} defining the color for the
	 *            background of the shape
	 */
	public void setBackgroundColor(IColorConstant backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
