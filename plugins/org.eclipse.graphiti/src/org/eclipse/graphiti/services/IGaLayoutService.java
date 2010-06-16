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
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;

/**
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IGaLayoutService {

	/**
	 * Calculates the size of the given graphics algorithm. If the graphics
	 * algorithm is a polyline or polygon then the size will be calculated.
	 * Otherwise the size of the graphics algorithm is simply returned.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @return the i dimension
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga);

	/**
	 * Calculates the size of the given graphics algorithm. If the graphics
	 * algorithm is a polyline or polygon then the size will be calculated.
	 * Otherwise the size of the graphics algorithm is simply returned.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param considerLineWidth
	 *            the consider line width
	 * @return the i dimension
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga, boolean considerLineWidth);

	/**
	 * Changes the height of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param height
	 *            the height
	 */
	public void setHeight(GraphicsAlgorithm ga, int height);

	/**
	 * Changes location and size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height);

	/**
	 * Changes location and size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param avoidNegativeCoordinates
	 *            the avoid negative coordinates
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height, boolean avoidNegativeCoordinates);

	/**
	 * Changes location of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y);

	/**
	 * Changes location of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param avoidNegativeCoordinates
	 *            the avoid negative coordinates
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y, boolean avoidNegativeCoordinates);

	/**
	 * Changes the size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setSize(GraphicsAlgorithm ga, int width, int height);

	/**
	 * Changes the width of the given graphics algorithm.
	 * 
	 * @param ga
	 *            GraphicsAlgorithm
	 * @param width
	 *            the width
	 */
	public void setWidth(GraphicsAlgorithm ga, int width);

}
