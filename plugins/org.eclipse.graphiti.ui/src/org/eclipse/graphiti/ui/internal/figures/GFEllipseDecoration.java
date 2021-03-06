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
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;
import org.eclipse.swt.graphics.Path;

/**
 * A Graphiti Ellipse Rotatable Decoration. Most functionality is handled in the
 * super-class. This class only has to define the path and the touch-point for
 * the figure-specific painting.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFEllipseDecoration extends GFAbstractRotatableDecoration implements RotatableDecoration {

	/**
	 * The initial touch-point as described in {@link #getInitialTouchPoint()}.
	 */
	private Point initialTouchPoint = null;

	/**
	 * Creates a new GFEllipseDecoration.
	 * 
	 * @param pictogramElementDelegate
	 *            The PictogramElementDelegate which provides the
	 *            GraphicsAlgorithm.
	 * @param graphicsAlgorithm
	 *            The GraphicsAlgorithm which provides the values to paint this
	 *            Shape.
	 */
	public GFEllipseDecoration(IPictogramElementDelegate pictogramElementDelegate, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElementDelegate, graphicsAlgorithm);

		// shift the touchpoint by the x/y coordinates of the graphics algorithm
		initialTouchPoint = new Point(0, getInitialBounds().height / 2);
		initialTouchPoint.translate(-graphicsAlgorithm.getX(), -graphicsAlgorithm.getY());
	}

	// ============================= abstract methods =========================

	/**
	 * Returns the initial touch-point of the decorator-shape. This is the point
	 * of the decorator-shape inside the initial-bounds (see
	 * {@link #getInitialBounds()}), which shall touch the decorated figure at
	 * the decorator-location (see {@link #getDecoratorLocation()}. It is called
	 * "inital" because it is the point before translation/rotation.
	 * <p>
	 * In caes of this Ellipse the point
	 * <code>(0, getInitialBounds().height / 2)</code> is returned.
	 * 
	 * @return The initial touch-point of the decorator-shape.
	 */
	@Override
	protected Point getInitialTouchPoint() {
		return initialTouchPoint;
	}

	/**
	 * Returns true, if the given point is contained inside this ellipse. This
	 * implementation overwrites the super-implementation, which only checks if
	 * the point is in the rectangular bounds.
	 * 
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * @return true, if the given point is contained inside this ellipse.
	 */
	@Override
	public boolean containsPointInInitialFigure(int x, int y) {
		return Boolean.TRUE.equals(GFFigureUtil.containsPointInEllipse(getInitialBounds(), x, y));
	}

	/**
	 * Returns the Path specifying an ellipse for the given outer bounds. It
	 * does not differentiate between a fill Path and an outline Path.
	 * 
	 * @param outerBounds
	 *            The outer bounds which shall contain the Path. They are
	 *            calculated from the bounds of this figure by
	 *            {@link GFFigureUtil#getAdjustedRectangle(Rectangle, double, int)}
	 *            . Note, that those outline-bounds are just a suggestion which
	 *            works fine for many cases.
	 * @param graphics
	 *            The Graphics on which the outline Path shall be painted. It
	 *            can be used to react on Graphics specific values, like the
	 *            zoom-level of the Graphics.
	 * @param isFill
	 *            if true, the Path is used for filling the Shape, otherwise for
	 *            outlining the Shape.
	 * @return The Path specifying an ellipse for the given outer bounds.
	 */
	@Override
	protected Path createPath(Rectangle outerBounds, Graphics graphics, boolean isFill) {
		Path path = new Path(null);
		float x = outerBounds.x;
		float y = outerBounds.y;
		float height = outerBounds.height;
		float width = outerBounds.width;
		path.moveTo(x, y);
		path.addArc(x, y, width, height, 0, 360);
		return path;
	}
}