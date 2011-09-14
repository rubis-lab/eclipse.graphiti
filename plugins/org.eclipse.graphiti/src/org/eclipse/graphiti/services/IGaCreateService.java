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
 *    mwenz - Bug 355347 - Remove setters of Graphiti's Font Interface
 *    jpasch - Bug 352542 - Add "plain"-create methods for working with styles
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.services;

import java.util.Collection;
import java.util.List;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The interface IGaCreateService provides services for the creation of all
 * available graphics algorithm's. E.g. Polygon, Rectangle, Text, ...
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IGaCreateService {

	/**
	 * Creates a multitext graphics algorithm with the default font (Arial, size
	 * 8). Use this method only if you want to use the default text, otherwise
	 * use {@link #createMultiText(GraphicsAlgorithmContainer)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(DEFAULT_FONT);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param diagram
	 *            the diagram to manage the font
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	public MultiText createDefaultMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a multitext graphics algorithm with the default font (Arial, size
	 * 8) and the given text. Use this method only if you want to use the
	 * default text, otherwise use
	 * {@link #createMultiText(GraphicsAlgorithmContainer, String)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(DEFAULT_FONT);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param diagram
	 *            the diagram to manage the font
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 */
	public MultiText createDefaultMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value);
	
	/**
	 * Creates a text graphics algorithm with the default font (Arial, size 8).
	 * Use this method only if you want to use the default text, otherwise use
	 * {@link #createText(GraphicsAlgorithmContainer)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(DEFAULT_FONT);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param diagram
	 *            the diagram to manage the font
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 * 
	 */
	public Text createDefaultText(Diagram diagram, GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a text graphics algorithm with the default font (Arial, size 8)
	 * and the given text. Use this method only if you want to use the default
	 * text, otherwise use
	 * {@link #createText(GraphicsAlgorithmContainer, String)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(DEFAULT_FONT);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param diagram
	 *            the diagram to manage the font
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 */
	public Text createDefaultText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value);

	/**
	 * Creates a multitext graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain multitext graphics algorithm. Default values have been reset,
	 * so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public MultiText createPlainMultiText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a multitext graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * text.setValue(value);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 */
	
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer, String value);
	
	/**
	 * Creates a plain multitext graphics algorithm with the given text. Default
	 * values have been reset, so you can use your styles.
	 *
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public MultiText createPlainMultiText(GraphicsAlgorithmContainer gaContainer, String value);

	/**
	 * Creates a multitext graphics algorithm with the given text and font. The
	 * font will be displayed in straight (no italics or bold) and will be
	 * managed within the given diagram; in case the font already exists it will
	 * be reused, otherwise the corresponding font instance will be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * text.setFont(<given font<);<br>
	 * 
	 * @param diagram
	 *            the diagram that shall be used for managing the font for the
	 *            new text field
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @param fontName
	 *            the name of the font to use for the new text field
	 * @param fontSize
	 *            the size of the font to use for the new text field
	 * @return the new multiline text
	 * @since 0.9
	 */
	public MultiText createMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize);

	/**
	 * Creates a multitext graphics algorithm with the given text and font. The
	 * font will be managed within the given diagram; in case the font already
	 * exists it will be reused, otherwise the corresponding font instance will
	 * be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * text.setFont(<given font<);<br>
	 * 
	 * @param diagram
	 *            the diagram that shall be used for managing the font for the
	 *            new text field
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @param fontName
	 *            the name of the font to use for the new text field
	 * @param fontSize
	 *            the size of the font to use for the new text field
	 * @param isFontItalic
	 *            flag if the font to use for the new text field should be
	 *            italic or not
	 * @param isFontBold
	 *            flag if the font to use for the new text field should be bold
	 *            or not
	 * @return the new multiline text
	 * @since 0.9
	 */
	public MultiText createMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize,
			boolean isFontItalic, boolean isFontBold);

	/**
	 * Creates a text graphics algorithm with empty text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain text graphics algorithm with empty text. Default values have
	 * been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Text createPlainText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a text graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer, String value);
	
	/**
	 * Creates a plain text graphics algorithm with the given text. Default values
	 * have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Text createPlainText(GraphicsAlgorithmContainer gaContainer, String value);


	/**
	 * Creates a text graphics algorithm with the given text and font. The font
	 * will be displayed in straight (no italics or bold) and will be managed
	 * within the given diagram; in case the font already exists it will be
	 * reused, otherwise the corresponding font instance will be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * text.setFont(<given font<);<br>
	 * 
	 * @param diagram
	 *            the diagram that shall be used for managing the font for the
	 *            new text field
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @param fontName
	 *            the name of the font to use for the new text field
	 * @param fontSize
	 *            the size of the font to use for the new text field
	 * @return the new text
	 * @since 0.9
	 */
	public Text createText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize);

	/**
	 * Creates a text graphics algorithm with the given text and font. The font
	 * will be managed within the given diagram; in case the font already exists
	 * it will be reused, otherwise the corresponding font instance will be
	 * created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * text.setFont(<given font<);<br>
	 * 
	 * @param diagram
	 *            the diagram that shall be used for managing the font for the
	 *            new text field
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @param fontName
	 *            the name of the font to use for the new text field
	 * @param fontSize
	 *            the size of the font to use for the new text field
	 * @param isFontItalic
	 *            flag if the font to use for the new text field should be
	 *            italic or not
	 * @param isFontBold
	 *            flag if the font to use for the new text field should be bold
	 *            or not
	 * @return the new text
	 * @since 0.9
	 */
	public Text createText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize,
			boolean isFontItalic, boolean isFontBold);

	/**
	 * Creates an ellipse graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new ellipse
	 */
	public Ellipse createEllipse(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain ellipse graphics algorithm. Default values have been reset, so
	 * you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new ellipse
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Ellipse createPlainEllipse(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a image graphics algorithm with the given image id.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * image.setProportional(false);<br>
	 * image.setStretchH(false);<br>
	 * image.setStretchV(false);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param imageId
	 *            the image id
	 * @return the new image
	 */
	public Image createImage(GraphicsAlgorithmContainer gaContainer, String imageId);
	
	/**
	 * Creates a plain image graphics algorithm with the given image id. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param imageId
	 *            the image id
	 * @return the new image
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Image createPlainImage(GraphicsAlgorithmContainer gaContainer, String imageId);

	/**
	 * Create an invisible rectangle.
	 * <p>
	 * The following values are set by default when it is shown:
	 * <p>
	 * graphicsAlgorithm.setBackground(IColorConstant.LIGHT_GRAY);<br>
	 * graphicsAlgorithm.setForeground(IColorConstant.YELLOW);<br>
	 * graphicsAlgorithm.setLineWidth(2);<br>
	 * graphicsAlgorithm.setTransparency(0.75);<br>
	 * <p>
	 * The following values are set by default when it is not shown:
	 * <p>
	 * graphicsAlgorithm.setFilled(false);<br>
	 * graphicsAlgorithm.setLineVisible(false);<br>
	 * 
	 * @param pe
	 *            the pictogram element to create the rectangle
	 * @return the rectangle
	 */
	public Rectangle createInvisibleRectangle(PictogramElement pe);

	/**
	 * Creates the platform graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setLineStyle(LineStyle.SOLID);<br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0d);<br>
	 * platformGraphicsAlgorithm.setId(id);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param id
	 *            the id
	 * @return the platform graphics algorithm
	 */
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id);

	/**
	 * Creates the plain platform graphics algorithm. Default values have been reset,
	 * so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param id
	 *            the id
	 * @return the platform graphics algorithm
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public PlatformGraphicsAlgorithm createPlainPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id);

	/**
	 * Creates a point datatype for the given x/y coordinates.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the new point
	 */
	public Point createPoint(int x, int y);

	/**
	 * Creates a point datatype for the given x/y coordinates. The additional
	 * before/after parameters defined at which distance before/after the point
	 * a rounded curve will start/end.
	 * 
	 * Note, that before/after parameters have only an effect, if the graphics
	 * algorithm support them, e.g. polygon and polyline.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param before
	 *            the distance before, if supported
	 * @param after
	 *            the distance after, if supported
	 * @return the new point
	 */
	public Point createPoint(int x, int y, int before, int after);

	/**
	 * Creates a list of point datatypes for the given x/y coordinates.
	 * 
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ...,xN, yN]
	 * 
	 * @return the point list
	 */
	public List<Point> createPointList(int[] xy);

	/**
	 * Creates a list of point datatypes for the given x/y coordinates.
	 * 
	 * Note, that before/after parameters have only an effect, if the graphics
	 * algorithm support them, e.g. polygon and polyline.
	 * 
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * 
	 * @return the point list
	 */
	public List<Point> createPointList(int[] xy, int beforeAfter[]);

	/**
	 * Creates a polygon graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain polygon graphics algorithm.  Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polygon
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);
	
	/**
	 * Creates a plain polygon graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polygon
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy);
	
	/**
	 * Creates a plain polygon graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polygon
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);
	
	/**
	 * Creates a plain polygon graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polygon
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);

	/**
	 * Creates a polyline graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain polyline graphics algorithm. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polyline
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);
	
	/**
	 * Creates a plain polyline graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polyline
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);

	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy);

	/**
	 * Creates a plain polyline graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polyline
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy);
	
	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);
	
	/**
	 * Creates a plain polyline graphics algorithm with the given points. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polyline
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);

	/**
	 * Creates a rectangle graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new rectangle
	 */
	public Rectangle createRectangle(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain rectangle graphics algorithm. Default
	 * values have been reset, so you can use your styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new rectangle
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Rectangle createPlainRectangle(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a rounded rectangle graphics algorithm with the given corner
	 * dimensions.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param cornerWidth
	 *            the corner width
	 * @param cornerHeight
	 *            the corner height
	 * @return the new rounded rectangle
	 */
	public RoundedRectangle createRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight);

	/**
	 * Creates a plain rounded rectangle graphics algorithm with the given
	 * corner dimensions. Default values have been reset, so you can use your
	 * styles.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param cornerWidth
	 *            the corner width
	 * @param cornerHeight
	 *            the corner height
	 * @return the new rounded rectangle
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public RoundedRectangle createPlainRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight);

	
	/**
	 * Shifts the color darker or lighter.
	 * 
	 * @param color
	 *            the color to be changed
	 * @param shift
	 *            negative shifts means darken the color
	 * @param diagram
	 *            the diagram
	 * @return a new color datatype with the shiftet values
	 */
	public Color createShiftedColor(Color color, int shift, Diagram diagram);

	/**
	 * Shifts the color constant darker or lighter.
	 * 
	 * @param colorConstant
	 *            the color constant to be changed
	 * @param shift
	 *            negative shifts means darken the color
	 * @return the color constant with the shifted values
	 */
	public IColorConstant createShiftedColor(IColorConstant colorConstant, int shift);

	/**
	 * Creates a style with the given id. The style is aggregated under the
	 * given container style.
	 * 
	 * @param styleContainer
	 *            container style
	 * @param id
	 *            style id
	 * @return the newly created style
	 */
	public Style createStyle(StyleContainer styleContainer, String id);

}
