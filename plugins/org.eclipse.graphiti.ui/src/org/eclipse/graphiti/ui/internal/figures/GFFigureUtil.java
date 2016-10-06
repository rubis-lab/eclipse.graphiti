/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 416039 - TextStyle rendering does not fall back to abstract text font
 *    palldredge - Bug 504173 - Selection Issues with Large Connections
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.ui.internal.IResourceRegistry;
import org.eclipse.graphiti.ui.internal.IResourceRegistryHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;
import org.eclipse.graphiti.util.PredefinedColoredAreas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;

/**
 * A utility class containing static helper-methods for Graphiti figures.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFFigureUtil {

	/**
	 * Singleton used for temporary calculations.
	 */
	private static final float SINGLETON_BOUNDS[] = new float[4];

	/**
	 * Fills a given rectangle with a gradient color-flow on the given Graphics.
	 * For this the rectangle is separated into several inner rectangles. Those
	 * inner rectangles are calculated from the given rectangle by
	 * percentage-values. Each inner rectangle has a start-color and end-color,
	 * which define the gradient of the color-flow.
	 * <p>
	 * Example: The parameters [Color.red, Color.yellow, Color.green] and [0,
	 * 20, 80] would result in a filled rectangle, where the top 20% are flow
	 * from red to yellow and the next 80% flow from yellow to green.
	 * 
	 * @param registryHolder
	 *            To get the
	 *            {@link IResourceRegistry#getSwtColor(int, int, int)}.
	 * 
	 * @param rectangle
	 *            The rectangle, which to fill with the gradient color-flow.
	 * @param graphics
	 *            The Graphics, on which to fill the rectangle.
	 * @param coloredArea
	 *            The area of the rectangle and the colors used for filling.
	 * @param zoom
	 *            The current zoom-level
	 * @param vertical
	 *            If true, fills the area vertically, otherwise horizontally
	 */
	public static void paintColorFlow(IResourceRegistryHolder registryHolder, Rectangle rectangle, Graphics graphics,
			GradientColoredArea coloredArea, double zoom, boolean vertical) {
		// calculate rectangle to fill
		Rectangle fillRectangle;
		if (vertical) {
			int start = PredefinedColoredAreas.getLocation(coloredArea.getStart(), rectangle.height, zoom);
			int end = PredefinedColoredAreas.getLocation(coloredArea.getEnd(), rectangle.height, zoom);
			fillRectangle = new Rectangle(rectangle.x, rectangle.y + start, rectangle.width, end - start);
		} else {
			int start = PredefinedColoredAreas.getLocation(coloredArea.getStart(), rectangle.width, zoom);
			int end = PredefinedColoredAreas.getLocation(coloredArea.getEnd(), rectangle.width, zoom);
			fillRectangle = new Rectangle(rectangle.x + start, rectangle.y, end - start, rectangle.height);
		}
		// fill rectangle
		org.eclipse.graphiti.mm.algorithms.styles.Color foregroundColor = coloredArea.getStart().getColor();
		Color foregroundColorSWT = DataTypeTransformation.toSwtColor(registryHolder.getResourceRegistry(),
				foregroundColor);
		org.eclipse.graphiti.mm.algorithms.styles.Color backgroundColor = coloredArea.getEnd().getColor();
		Color backgroundColorSWT = DataTypeTransformation.toSwtColor(registryHolder.getResourceRegistry(),
				backgroundColor);

		graphics.setForegroundColor(foregroundColorSWT);
		graphics.setBackgroundColor(backgroundColorSWT);
		graphics.fillGradient(fillRectangle.x, fillRectangle.y, fillRectangle.width, fillRectangle.height, vertical);
	}

	/**
	 * Returns if the given point is contained in the ellipse defined by the
	 * bounding rectangle. Possible results are
	 * <ul>
	 * <li>Boolean.TRUE = the point is located directly in the ellipse.</li>
	 * <li>Boolean.FALSE = the point is located outside the bounding box of the
	 * ellipse.</li>
	 * <li>null = the point is located inside the bounding box of the ellipse</li>
	 * </ul>
	 * defined by the bounding rectangle.
	 * 
	 * @param r
	 *            The bounding-rectangle of the ellipse.
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * @return If the given point is contained in the ellipse defined by the
	 *         bounding rectangle.
	 */
	public static Boolean containsPointInEllipse(Rectangle r, int x, int y) {
		Ellipse ellipse = new Ellipse();
		ellipse.setBounds(r);
		return ellipse.containsPoint(x, y);
	}

	/**
	 * Returns if the given point is contained in this line. Possible results
	 * are
	 * <ul>
	 * <li>Boolean.TRUE = the point is located directly on the line.</li>
	 * <li>Boolean.FALSE = the point is located outside the bounding box of the
	 * line.</li>
	 * <li>null = the point is located inside the bounding box of the line</li>
	 * </ul>
	 * The lineWidth is considered in the calculation.
	 * 
	 * @param x1
	 *            The x-coordinate of the start-point of the line.
	 * @param y1
	 *            The y-coordinate of the start-point of the line.
	 * @param x2
	 *            The x-coordinate of the end-point of the line.
	 * @param y2
	 *            The y-coordinate of the end-point of the line.
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * @param lineWidth
	 *            The width of the line.
	 * @return If the given point is contained in this line.
	 */
	public static Boolean containsPointInLine(int x1, int y1, int x2, int y2, int x, int y, int lineWidth) {
		int halfLineWidth = lineWidth / 2;

		Rectangle lineBounds = Rectangle.SINGLETON;
		lineBounds.setSize(0, 0);
		lineBounds.setLocation(x1, y1);
		lineBounds.union(x2, y2);
		lineBounds.expand(halfLineWidth, halfLineWidth);

		// fast check
		if (!lineBounds.contains(x, y))
			return Boolean.FALSE;

		long v1x, v1y, v2x, v2y;
		long numerator, denominator;
		long result = 0;

		// calculates the length squared of the cross product of two vectors, v1
		// & v2.
		if (x1 != x2 && y1 != y2) {
			v1x = x2 - x1;
			v1y = y2 - y1;
			v2x = x - x1;
			v2y = y - y1;

			numerator = v2x * v1y - v1x * v2y;
			denominator = v1x * v1x + v1y * v1y;
			result = numerator * numerator / denominator;
		}

		// if it is the same point, and it passes the bounding box test,
		// the result is always true.
		if (result <= halfLineWidth * halfLineWidth) {
			return Boolean.TRUE;
		} else {
			return null;
		}
	}

	/**
	 * Returns if the given point is contained in this polyline. Possible
	 * results are
	 * <ul>
	 * <li>Boolean.TRUE = the point is located directly on the polyline.</li>
	 * <li>Boolean.FALSE = the point is located outside the bounding box of the
	 * polyline.</li>
	 * <li>null = the point is located inside the bounding box of the polyline</li>
	 * </ul>
	 * The lineWidth is considered in the calculation.
	 * 
	 * @param points
	 *            The points of the polyline.
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * @param lineWidth
	 *            The width of the line.
	 * @return If the given point is contained in this polyline.
	 */
	public static Boolean containsPointInPolyline(PointList points, int x, int y, int lineWidth) {
		int halfLineWidth = lineWidth / 2;
		Rectangle lineBounds = points.getBounds().getCopy();
		lineBounds.expand(halfLineWidth, halfLineWidth);

		// fast check
		if (!lineBounds.contains(x, y)) {
			return Boolean.FALSE;
		}

		// check if point on lines (regarding tolerance)
		int ints[] = points.toIntArray();
		for (int index = 0; index < ints.length - 3; index += 2) {
			Boolean containsPointInLine = GFFigureUtil.containsPointInLine(ints[index], ints[index + 1], ints[index + 2], ints[index + 3],
					x, y, lineWidth);
			if (Boolean.TRUE.equals(containsPointInLine)) {
				return Boolean.TRUE;
			}
		}

		return null;
	}

	/**
	 * Returns if the given point is contained in this polygon. Possible results
	 * are
	 * <ul>
	 * <li>Boolean.TRUE = the point is located directly in the polygon.</li>
	 * <li>Boolean.FALSE = the point is located outside the bounding box of the
	 * polygon.</li>
	 * <li>null = the point is located inside the bounding box of the polygon</li>
	 * </ul>
	 * 
	 * @param points
	 *            The points of the polygon.
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * @return If the given point is contained in this polygon.
	 */
	public static Boolean containsPointInPolygon(PointList points, int x, int y) {
		// fast check
		if (!points.getBounds().contains(x, y)) {
			return Boolean.FALSE;
		}

		// check if point is inside polygon
		boolean isOdd = false;
		int[] pointsxy = points.toIntArray();
		int n = pointsxy.length;
		if (n > 3) { // if there are at least 2 Points (4 ints)
			int x1, y1;
			int x0 = pointsxy[n - 2];
			int y0 = pointsxy[n - 1];

			for (int i = 0; i < n; x0 = x1, y0 = y1) {
				x1 = pointsxy[i++];
				y1 = pointsxy[i++];

				if (y0 <= y && y < y1 && crossProduct(x1, y1, x0, y0, x, y) > 0)
					isOdd = !isOdd;
				if (y1 <= y && y < y0 && crossProduct(x0, y0, x1, y1, x, y) > 0)
					isOdd = !isOdd;
			}
			if (isOdd) {
				return Boolean.TRUE;
			}
		}

		return null;
	}

	/**
	 * Private helper method which calculates the cross-product of the given
	 * numbers.
	 */
	private static int crossProduct(int ax, int ay, int bx, int by, int cx, int cy) {
		return (ax - cx) * (by - cy) - (ay - cy) * (bx - cx);
	}

	/**
	 * Returns a draw2d point-list of the given polygon model-element.
	 * 
	 * @param polyline
	 *            The polygon model-element for which to return the draw2d
	 *            point-list.
	 * @return A draw2d point-list of the given polygon model-element.
	 */
	public static PointList getPointList(org.eclipse.graphiti.mm.algorithms.Polyline polyline) {
		int deltaX = polyline.getX();
		int deltaY = polyline.getY();

		PointList pointList = new PointList();
		for (org.eclipse.graphiti.mm.algorithms.styles.Point dtp : polyline.getPoints()) {
			pointList.addPoint(dtp.getX() + deltaX, dtp.getY() + deltaY);
		}
		return pointList;
	}

	/**
	 * Returns the rectangular bounds of a given Path.
	 * 
	 * @param path
	 *            The Path for which to return the bounds.
	 * @return The rectangular bounds of a given Path.
	 */
	public static Rectangle getPathBounds(Path path) {
		Rectangle ret = Rectangle.SINGLETON;
		path.getBounds(SINGLETON_BOUNDS);
		ret.setLocation((int) Math.floor(SINGLETON_BOUNDS[0]), (int) Math.floor(SINGLETON_BOUNDS[1]));
		ret.setSize((int) Math.ceil(SINGLETON_BOUNDS[2]), (int) Math.ceil(SINGLETON_BOUNDS[3]));
		return ret;
	}

	/**
	 * Returns a new instance of the input rectangle zoomed by the zoom-level
	 * and shrinked by the half line-width.
	 * 
	 * @param rectangle
	 *            The rectangle to zoom and shrink.
	 * @param zoom
	 *            The zoom-level to use.
	 * @param lw
	 *            The line-width to use.
	 * @return A new instance of the input rectangle zoomed by the zoom-level
	 *         and shrinked by the half line-width. Returns null, if input
	 *         rectangle is null.
	 */
	public static Rectangle getAdjustedRectangle(Rectangle rectangle, double zoom, int lw) {
		if (rectangle == null) {
			return null;
		}

		Rectangle ret = new Rectangle(rectangle);

		if (zoom != 1.0) {
			ret.x = (int) (Math.floor(rectangle.x * zoom));
			ret.y = (int) (Math.floor(rectangle.y * zoom));
			ret.width = (int) (Math.floor(((rectangle.x + rectangle.width) * zoom))) - ret.x;
			ret.height = (int) (Math.floor(((rectangle.y + rectangle.height) * zoom))) - ret.y;
		}

		int adjustmentTopLeft = lw / 2;
		int adjustmentBottomRight = lw;
		ret.x += adjustmentTopLeft;
		ret.y += adjustmentTopLeft;
		ret.width -= adjustmentBottomRight;
		ret.height -= adjustmentBottomRight;

		return ret;
	}

	/**
	 * Returns a new instance of the input point-list, which is adjusted
	 * regarding the given zoom-factor and line-width. Concretely this means the
	 * following:
	 * <ul>
	 * <li>All points are zoomed by the given zoom-factor.</li>
	 * <li>All points are translated towards the center by half the line-width.
	 * This way the point-list stays insides its bounds even for a big
	 * line-width.</li>
	 * </ul>
	 * 
	 * @param points
	 *            The point-list which to adjust.
	 * @param zoom
	 *            The zoom-factor by which to zoom all points.
	 * @param lw
	 *            The line-width, which to consider when translating towards the
	 *            center.
	 */
	public static PointList getAdjustedPointList(PointList points, double zoom, double lw) {
		Rectangle zoomedBounds = points.getBounds().getCopy().scale(zoom);
		double middlex = zoomedBounds.x + (zoomedBounds.width / 2);
		double middley = zoomedBounds.y + (zoomedBounds.height / 2);

		PointList ret = new PointList();
		for (int i = 0; i < points.size(); i++) {
			Point point = points.getPoint(i);
			point.scale(zoom);

			// translate all points towards the middle depending on the
			// line-width, so that the polyline remains inside the bounds
			// Note, that the delta has to be rounded up/down depending on the
			// relative location from point to middle.
			double dx;
			double dy;
			if (point.x < middlex) {
				dx = Math.ceil(((middlex - point.x) / zoomedBounds.width) * lw);
			} else {
				dx = Math.floor(((middlex - point.x) / zoomedBounds.width) * lw);
			}
			if (point.y < middley) {
				dy = Math.ceil(((middley - point.y) / zoomedBounds.height) * lw);
			} else {
				dy = Math.floor(((middley - point.y) / zoomedBounds.height) * lw);
			}

			point.translate((int) dx, (int) dy);

			ret.addPoint(point);
		}
		return ret;
	}

	/**
	 * Returns a new PointList, which results from translating the given
	 * PointList.
	 * 
	 * @param points
	 *            The PointList, which is used as source for translation. It is
	 *            not changed.
	 * @param dx
	 *            The x-value to translate.
	 * @param dy
	 *            The y-value to translate.
	 * @return A new PointList, which results from translating the source
	 *         PointList.
	 */
	public static PointList getTranslatedPointList(PointList points, int dx, int dy) {
		PointList ret = new PointList();
		for (int i = 0; i < points.size(); i++) {
			Point pt = points.getPoint(i);
			pt.x += dx;
			pt.y += dy;
			ret.addPoint(pt);
		}
		return ret;
	}

	/**
	 * Returns a path which draws a bezier-curve through the given
	 * bezier-point-list.
	 * 
	 * @param origPoints
	 *            The bezier-points through which to draw the bezier-curve.
	 * @param isClosed
	 *            If true, then the path will be closed, so that the
	 *            bezier-curve ends at the start-point. Note, that the result is
	 *            different to a path with the same start/end point but where
	 *            isClosed is false, because in that case there is no rounded
	 *            corner at the start/end point.
	 * @return A path which draws a bezier-curve through the given point-list.
	 */
	public static Path getBezierPath(List<BezierPoint> origPoints, boolean isClosed) {
		Path path = new Path(null);
		// make a copy, so that we can change it
		List<BezierPoint> points = new ArrayList<BezierPoint>(origPoints.size() + 2);
		points.addAll(origPoints);

		// Draw simple lines, as bezier-curve doesn't make sense
		if (points.size() < 3 || !hasBezierDistance(origPoints)) {
			if (points.size() != 0) {
				path.moveTo(points.get(0).getX(), points.get(0).getY());
				for (int i = 1; i < points.size(); i++) {
					path.lineTo(points.get(i).getX(), points.get(i).getY());
				}
			}
		} else { // Draw bezier curve

			// Idea for the closed bezier curve:
			// The first two points are added to the end of the point-list.
			// Afterwards the bezier-curve through the points is drawn as usual,
			// except that the first line and the last line are not drawn.

			// Adjust point-list if closing is needed: add the first two points
			// again at the end
			if (isClosed) {
				if (!points.get(points.size() - 1).equals(points.get(0))) { // first
																			// !=
																			// last
																			// =>
																			// only
																			// then
																			// double
																			// first
																			// point
					points.add(points.get(0));
				}
				points.add(points.get(1));
			}

			// Initialize the points for calculation
			Point c = points.get(0).createDraw2dPoint(); // the current
															// control-point
			Point q = points.get(1).createDraw2dPoint(); // the point following
															// the current
			// control-point
			Point r = new Point();
			Point s = new Point();

			// If not closed, draw the first line from the first point to r,
			// otherwise move to r
			determineBezierPoints(c, q, r, s, points.get(0).getBezierDistanceAfter(), points.get(1).getBezierDistanceBefore());
			if (!isClosed) {
				path.moveTo(points.get(0).getX(), points.get(0).getY());
				path.lineTo(r.x, r.y);
			} else {
				path.moveTo(r.x, r.y);
			}

			for (int index = 2; index < points.size(); index++) {
				// Move c and q one position forward
				c.setLocation(q);
				points.get(index).copyToDraw2dPoint(q);

				// Update r and s
				determineBezierPoints(c, q, r, s, points.get(index - 1).getBezierDistanceAfter(), points.get(index)
						.getBezierDistanceBefore());

				// draw the curve
				path.quadTo(c.x, c.y, s.x, s.y);
				path.lineTo(r.x, r.y);
			}

			// If not closed, draw the final line from r to the last point,
			// otherwise do nothing
			if (!isClosed) {
				// Draw the final line from r to the last point.
				path.lineTo(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
			}
		}

		// The algorithm already takes care, that the line ends again at the
		// start-point.
		// But a final path.close() takes care of drawing problems.
		if (isClosed) {
			path.close();
		}

		return path;
	}

	/**
	 * Returns true, if at least one of the points in the list has a
	 * bezier-distance != 0.
	 * 
	 * @param points
	 *            The points, which bezier-distances to check.
	 * 
	 * @return true, if at least one of the points in the list has a
	 *         bezier-distance != 0.
	 */
	public static boolean hasBezierDistance(List<BezierPoint> points) {
		for (BezierPoint point : points) {
			if (point.getBezierDistanceBefore() != 0 || point.getBezierDistanceAfter() != 0)
				return true;
		}
		return false;
	}

	/**
	 * Internal helper method used by {@link #getBezierPath(List, boolean)} to
	 * calculate the bezier-points 'r' and 's'. Note, that the parameters 'r'
	 * and 's' are changed in this method, so they are basically the result of
	 * this method.
	 * 
	 * @param c
	 *            The current control-point.
	 * @param q
	 *            The point following the current control-point.
	 * @param r
	 *            The bezier-point, which is calculated in this method. This is
	 *            a "return-value".
	 * @param s
	 *            The bezier-point, which is calculated in this method. This is
	 *            a "return-value".
	 * @param distanceAfterCurrent
	 *            The bezier distance after the current point, used to calculate
	 *            the rounding of the bezier-curve.
	 * @param distanceBeforeNext
	 *            The bezier distance before the next point, used to calculate
	 *            the rounding of the bezier-curve.
	 */
	private static void determineBezierPoints(Point c, Point q, Point r, Point s, int distanceAfterCurrent, int distanceBeforeNext) {
		// Determine v and m
		// Ray v = new Ray();
		int vx = q.x - c.x;
		int vy = q.y - c.y;
		Vector v = new Vector(vx, vy);
		double absV = v.getLength();
		// Ray m = new Ray();
		int mx = Math.round(c.x + vx / 2);
		int my = Math.round(c.y + vy / 2);

		// Determine tolerance
		// Idea:
		// The vector v is the line after the current control-point c.
		// If the sum of the bezier-distances is greater than the half
		// line-length of v,
		// then a simplified calculation for the bezier-points r and s must be
		// done.
		int tolerance = distanceAfterCurrent + distanceBeforeNext;

		// Determine the "results" r and s
		if (absV < tolerance) {
			// use the the midpoint m for r and s
			r.x = mx;
			r.y = my;
			s.x = mx;
			s.y = my;
		} else {
			double x = (absV - distanceBeforeNext) / absV;
			r.x = Math.round(c.x + (float) x * vx);
			r.y = Math.round(c.y + (float) x * vy);
			double y = distanceAfterCurrent / absV;
			s.x = Math.round(c.x + (float) y * vx);
			s.y = Math.round(c.y + (float) y * vy);
		}
	}

	protected static void drawRichText(Graphics g, String draw, int x, int y,
			IConfigurationProviderInternal configurationProvider, AbstractText text) {
		drawRichText(g, draw, x, y, -1, false, 0, configurationProvider, text);
	}

	protected static void drawRichText(Graphics g, String draw, int x, int y, int bidiLevel, boolean mirrored,
			int currentOffset, IConfigurationProviderInternal configurationProvider, AbstractText text) {
		if (bidiLevel == -1) {
			TextLayout tl = new TextLayout(Display.getDefault());
			if (mirrored) {
				tl.setOrientation(SWT.RIGHT_TO_LEFT);
			}

			List<Font> fontsToDispose = new ArrayList<Font>();
			org.eclipse.graphiti.mm.algorithms.styles.Font textFont = text.getFont();
			if (textFont != null) {
				// Use the font of the provided AbstractText as fallback (see
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=416039)
				tl.setFont(DataTypeTransformation.toSwtFont(textFont));
				fontsToDispose.add(tl.getFont());
			} else {
				// Use default font in case AbstractText has no font
				tl.setFont(g.getFont());
			}
			tl.setText(draw);

			for (TextStyleRegion style : text.getStyleRegions()) {
				int start = style.getStart() - currentOffset;
				int end = style.getEnd() - currentOffset;
				if (start >= draw.length())
					continue;
				if (end < 0)
					continue;

				org.eclipse.swt.graphics.TextStyle textStyle = new org.eclipse.swt.graphics.TextStyle();
				TextStyle gTextStyle = style.getStyle();

				textStyle.underline = gTextStyle.isUnderline();
				textStyle.strikeout = gTextStyle.isStrikeout();
				textStyle.underlineStyle = gTextStyle.getUnderlineStyle().getValue();

				org.eclipse.graphiti.mm.algorithms.styles.Font font = gTextStyle.getFont();
				if (font != null) {
					textStyle.font = DataTypeTransformation.toSwtFont(font);
					fontsToDispose.add(textStyle.font);
				}

				org.eclipse.graphiti.mm.algorithms.styles.Color foreground = gTextStyle.getForeground();
				if (foreground != null) {
					textStyle.foreground = DataTypeTransformation.toSwtColor(
							configurationProvider.getResourceRegistry(), foreground);
				}

				org.eclipse.graphiti.mm.algorithms.styles.Color background = gTextStyle.getBackground();
				if (background != null) {
					textStyle.background = DataTypeTransformation.toSwtColor(
							configurationProvider.getResourceRegistry(), background);
				}

				org.eclipse.graphiti.mm.algorithms.styles.Color underlineColor = gTextStyle.getUnderlineColor();
				if (underlineColor != null) {
					textStyle.underlineColor = DataTypeTransformation.toSwtColor(
							configurationProvider.getResourceRegistry(), underlineColor);
				}

				org.eclipse.graphiti.mm.algorithms.styles.Color strikeoutColor = gTextStyle.getStrikeoutColor();
				if (strikeoutColor != null) {
					textStyle.strikeoutColor = DataTypeTransformation.toSwtColor(
							configurationProvider.getResourceRegistry(), strikeoutColor);
				}

				tl.setStyle(textStyle, start, end);
			}

			g.drawTextLayout(tl, x, y);
			tl.dispose();
			for (Font font : fontsToDispose) {
				font.dispose();
			}

		} else {
			TextLayout tl = new TextLayout(Display.getDefault());
			if (mirrored)
				tl.setOrientation(SWT.RIGHT_TO_LEFT);
			tl.setFont(g.getFont());
			tl.setText(draw);
			g.drawTextLayout(tl, x, y);
			tl.dispose();
		}
	}

}
