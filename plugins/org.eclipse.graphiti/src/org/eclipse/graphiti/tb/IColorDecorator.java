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
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.9
 */
public interface IColorDecorator extends IDecorator {

	/**
	 * Returns the color that will be used for painting the foreground of the
	 * shape to decorate. By default (when returning <code>null</code>) the
	 * original foreground color of the shape is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	public IColorConstant getForegroundColor();

	/**
	 * Returns the color that will be used for painting the background of the
	 * shape to decorate. By default (when returning <code>null</code>) the
	 * original background color of the shape is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	public IColorConstant getBackgroundColor();
}
