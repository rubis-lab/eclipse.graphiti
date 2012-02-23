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
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.9
 */
public interface IBorderDecorator extends IDecorator {

	/**
	 * Returns the color that will be used for the border. By default (when
	 * returning <code>null</code>) {@link IColorConstant#BLACK} is used.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	public IColorConstant getBorderColor();

	/**
	 * Returns the width that will be used for the border line. By default (when
	 * returning <code>null</code> or a value smaller than 1) 1 is used.
	 * 
	 * @return an {@link Integer} defining the width of the border line
	 */
	public Integer getBorderWidth();

	/**
	 * Returns the style that will be used for the border line. Possible values
	 * are:
	 * <p>
	 * <ul>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_SOLID}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASH}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASHDOT}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DASHDOTDOT}</li>
	 * <li>{@link org.eclipse.draw2d.Graphics#LINE_DOT}</li>
	 * </ul>
	 * By default (when returning <code>null</code> or an invalid value)
	 * {@link org.eclipse.draw2d.Graphics#LINE_SOLID} is used.
	 * 
	 * @return an {@link Integer} defining the width of the border style
	 */
	public Integer getBorderStyle();
}
