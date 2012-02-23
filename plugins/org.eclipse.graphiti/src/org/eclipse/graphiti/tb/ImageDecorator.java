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
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 358255 - Add Border/Background decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

/**
 * Image decorators can be used to add an image to the visualization of a shape
 * without modifying the dirty state of the displaying editor, see
 * {@link IDecorator}.
 * 
 * @noextend This interface is not intended to be extended by clients.
 */
public class ImageDecorator extends AbstractDecorator implements IImageDecorator {

	private static final int DEFAULT_LOCATION = 4;

	private String imageId;

	private int y = DEFAULT_LOCATION;

	private int x = DEFAULT_LOCATION;

	/**
	 * Creates a new image decorator that decorates a shape with the image
	 * identified by the given image id.
	 * 
	 * @param imageId
	 *            a {@link String} holding the image id
	 */
	public ImageDecorator(String imageId) {
		super();
		this.imageId = imageId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IImageDecorator#getImageId()
	 */
	public String getImageId() {
		return this.imageId;
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
}
