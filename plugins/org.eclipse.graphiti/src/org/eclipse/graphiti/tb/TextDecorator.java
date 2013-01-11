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

/**
 * Text decorators can be used to add a text to the visualization of a shape
 * without modifying the dirty state of the displaying editor, see
 * {@link IDecorator}.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.10
 */
public class TextDecorator extends AbstractDecorator implements ITextDecorator {

	private static final int DEFAULT_LOCATION = 4;

	private String text;

	private String fontName = "Arial";

	private int fontSize = 10;

	private int y = DEFAULT_LOCATION;

	private int x = DEFAULT_LOCATION;

	/**
	 * Creates a new text decorator that decorates a shape with the given text.
	 * The default font used for displaying the text is Arial in size 10, the
	 * text will appear by default 4px from the upper left corner of the
	 * decorated shape.
	 * 
	 * @param text
	 *            a {@link String} providing the text
	 */
	public TextDecorator(String text) {
		super();
		this.text = text;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#getX()
	 */
	public int getX() {
		return this.x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#getY()
	 */
	public int getY() {
		return this.y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#setX(int)
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#setY(int)
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#getText()
	 */
	public String getText() {
		return this.text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#getFontName()
	 */
	public String getFontName() {
		return fontName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#setFontName(java.lang.String)
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#getFontSize()
	 */
	public int getFontSize() {
		return fontSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.ITextDecorator#setFontSize(int)
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
