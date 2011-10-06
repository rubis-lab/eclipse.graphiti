/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 339525 - Enrich paste context with location information
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 17.11.2005
 */
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.internal.features.context.impl.base.PictogramElementsContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class PasteContext.
 */
public class PasteContext extends PictogramElementsContext implements IPasteContext {

	// Use delegation for LocationContext, since we already extend
	// PictogramElementsContext (which is parallel to LocationContext in the
	// inheritance hierarchy)
	LocationContext locationContext;

	/**
	 * Creates a new {@link PasteContext}.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	public PasteContext(PictogramElement[] pictogramElements) {
		this(pictogramElements, -1, -1);
	}
	
	/**
	 * Creates a new {@link PasteContext}.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 * @param pasteLocationX
	 *            the x location to paste to
	 * @param pasteLocationY
	 *            the y location to paste to
	 * @since 0.9
	 */
	public PasteContext(PictogramElement[] pictogramElements, int pasteLocationX, int pasteLocationY) {
		super(pictogramElements);
		locationContext = new LocationContext(pasteLocationX, pasteLocationY);
	}

	/**
	 * Gets the x location to paste to
	 * 
	 * @return an integer representing the x location
	 * 
	 * @since 0.9
	 */
	@Override
	public int getX() {
		return locationContext.getX();
	}

	/**
	 * Sets the x location to paste to
	 * 
	 * @param x
	 *            an integer representing the x location
	 * @since 0.9
	 */
	public void setX(int x) {
		locationContext.setX(x);
	}

	/**
	 * Gets the y location to paste to
	 * 
	 * @return an integer representing the y location
	 * @since 0.9
	 */
	@Override
	public int getY() {
		return locationContext.getY();
	}

	/**
	 * Sets the y location to paste to
	 * 
	 * @param y
	 *            an integer representing the y location
	 * @since 0.9
	 */
	public void setY(int y) {
		locationContext.setY(y);
	}

	/**
	 * Sets the location to paste to
	 * 
	 * @param x
	 *            an integer representing the x location
	 * @param y
	 *            an integer representing the y location
	 * @since 0.9
	 */
	public void setLocation(int x, int y) {
		locationContext.setLocation(x, y);
	}
}
