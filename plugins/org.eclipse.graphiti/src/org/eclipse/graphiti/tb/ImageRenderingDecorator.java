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

import org.eclipse.graphiti.datatypes.ILocation;

/**
 * The Class ImageRenderingDecorator.
 */
public class ImageRenderingDecorator extends AbstractRenderingDecorator implements ILocation {
	private static final int DEFAULT_LOCATION = 4;

	private String imageId;

	private int y = DEFAULT_LOCATION;

	private int x = DEFAULT_LOCATION;

	/**
	 * The Constructor.
	 * 
	 * @param imageId
	 *            the image id
	 */
	public ImageRenderingDecorator(String imageId) {
		super();
		setImageId(imageId);
	}

	/**
	 * Gets the image id.
	 * 
	 * @return the image id
	 */
	public String getImageId() {
		return imageId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#getX()
	 */
	public int getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.ILocation#getY()
	 */
	public int getY() {
		return y;
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

	private void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
