/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Andreas Graf/mwenz - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * Text decorators can be used to add a text to the visualization of a shape
 * without modifying the dirty state of the displaying editor, see
 * {@link IDecorator}.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.10
 */
public interface ITextDecorator extends IDecorator, ILocation {

	/**
	 * Returns the text of the decorator.
	 * 
	 * @return A {@link String} containing the text
	 */
	String getText();

	/**
	 * Sets the text or the decorator.
	 * 
	 * @param text
	 *            A {@link String} containing the text
	 */
	void setText(String text);

	/**
	 * Gets the name of the font used in the decorator.
	 * 
	 * @return A {@link String} containing the name of the font.
	 */
	String getFontName();

	/**
	 * Sets the name of the font used in the decorator.
	 * 
	 * @param fontName
	 *            A {@link String} containing the name of the font.
	 */
	void setFontName(String fontName);

	/**
	 * Gets the size of the font used in the decorator.
	 * 
	 * @return An integer defining the size of the font.
	 */
	int getFontSize();

	/**
	 * Sets the size of the font used in the decorator.
	 * 
	 * @param fontSize
	 *            An integer defining the size of the font.
	 */
	void setFontSize(int fontSize);

	/**
	 * Sets the color that will be used for painting the background of the text.
	 * By default (when returning <code>null</code>) the original background
	 * color of the text is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	void setBackgroundColor(IColorConstant backgroundColor);

	/**
	 * Returns the color that will be used for painting the background of the
	 * shape to decorate. By default (when returning <code>null</code>) the
	 * original background color of the shape is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	public IColorConstant getBackgroundColor();

	/**
	 * Sets the color that will be used for painting the foreground of the text.
	 * By default (when returning <code>null</code>) the original foreground
	 * color of the text is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	void setForegroundColor(IColorConstant foregroundColor);

	/**
	 * Returns the color that will be used for painting the foreground of the
	 * shape to decorate. By default (when returning <code>null</code>) the
	 * original foreground color of the shape is kept.
	 * 
	 * @return a {@link IColorConstant} defining the color
	 */
	public IColorConstant getForegroundColor();

}
