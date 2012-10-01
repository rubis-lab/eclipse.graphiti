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
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle;
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
	 * Creates a {@link MultiText} graphics algorithm with the default font
	 * (Arial, size 8). Use this method only if you want to use the default
	 * text, otherwise use {@link #createMultiText(GraphicsAlgorithmContainer)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value:""<br>
	 * name: Arial<br>
	 * size: 8<br>
	 * italic: false<br>
	 * bold: false<br>
	 * 
	 * @param diagram
	 *            the diagram to manage the font
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	public MultiText createDefaultMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a {@link MultiText} graphics algorithm with the default font
	 * (Arial, size 8) and the given text. Use this method only if you want to
	 * use the default text, otherwise use
	 * {@link #createMultiText(GraphicsAlgorithmContainer, String)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: Arial<br>
	 * size: 8<br>
	 * italic: false<br>
	 * bold: false<br>
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
	 * Creates a {@link Text} graphics algorithm with the default font (Arial,
	 * size 8). Use this method only if you want to use the default text,
	 * otherwise use {@link #createText(GraphicsAlgorithmContainer)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value:""<br>
	 * name: Arial<br>
	 * size: 8<br>
	 * italic: false<br>
	 * bold: false<br>
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
	 * Creates a {@link Text} graphics algorithm with the default font (Arial,
	 * size 8) and the given text. Use this method only if you want to use the
	 * default text, otherwise use
	 * {@link #createText(GraphicsAlgorithmContainer, String)}
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: Arial<br>
	 * size: 8<br>
	 * italic: false<br>
	 * bold: false<br>
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
	 * Creates a {@link MultiText} graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: ""<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link MultiText} graphics algorithm. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link MultiText} graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 */
	
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer, String value);
	
	/**
	 * Creates a plain {@link MultiText} graphics algorithm with the given text.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link MultiText} graphics algorithm with the given text and
	 * font. The font will be displayed in straight (no italics or bold) and
	 * will be managed within the given diagram; in case the font already exists
	 * it will be reused, otherwise the corresponding font instance will be
	 * created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: fontNamel<br>
	 * size: fontSize<br>
	 * italic: false<br>
	 * bold: false<br>
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
	 * Creates a {@link MultiText} graphics algorithm with the given text and
	 * font. The font will be managed within the given diagram; in case the font
	 * already exists it will be reused, otherwise the corresponding font
	 * instance will be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: fontNamel<br>
	 * size: fontSize<br>
	 * italic: isFontItalic<br>
	 * bold: isFontBold<br>
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
	 * Creates a {@link Text} graphics algorithm with empty text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: ""<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link Text} graphics algorithm with empty text. Default
	 * values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Text} graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer, String value);
	
	/**
	 * Creates a plain {@link Text} graphics algorithm with the given text.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Text} graphics algorithm with the given text and font.
	 * The font will be displayed in straight (no italics or bold) and will be
	 * managed within the given diagram; in case the font already exists it will
	 * be reused, otherwise the corresponding font instance will be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: fontNamel<br>
	 * size: fontSize<br>
	 * italic: false<br>
	 * bold: false<br>
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
	 * Creates a {@link Text} graphics algorithm with the given text and font.
	 * The font will be managed within the given diagram; in case the font
	 * already exists it will be reused, otherwise the corresponding font
	 * instance will be created.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * horizontalAlignment: ALIGNMENT_LEFT<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br>
	 * angle: 0<br>
	 * value: value<br>
	 * name: fontNamel<br>
	 * size: fontSize<br>
	 * italic: isFontItalic<br>
	 * bold: isFontBold<br>
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
	 * Creates an {@link Ellipse} graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new ellipse
	 */
	public Ellipse createEllipse(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link Ellipse} graphics algorithm. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link Image} graphics algorithm with the given image id.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: true<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * id: TEST<br>
	 * stretchH: false<br>
	 * stretchV: false<br>
	 * proportional: false<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param imageId
	 *            the image id
	 * @return the new image
	 */
	public Image createImage(GraphicsAlgorithmContainer gaContainer, String imageId);
	
	/**
	 * Creates a plain {@link Image} graphics algorithm with the given image id.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Create an invisible {@link Rectangle}.
	 * <p>
	 * The following values are set by default when it is shown:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * lineVisible: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0
	 * 
	 * @param pe
	 *            the pictogram element to create the rectangle
	 * @return the rectangle
	 */
	public Rectangle createInvisibleRectangle(PictogramElement pe);

	/**
	 * Creates the {@link PlatformGraphicsAlgorithm}.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * id: id<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param id
	 *            the id
	 * @return the platform graphics algorithm
	 */
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id);

	/**
	 * Creates the plain {@link PlatformGraphicsAlgorithm}. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link Point} datatype for the given x/y coordinates.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the new point
	 */
	public Point createPoint(int x, int y);

	/**
	 * Creates a {@link Point} datatype for the given x/y coordinates. The
	 * additional before/after parameters defined at which distance before/after
	 * the point a rounded curve will start/end.
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
	 * Creates a list of {@link Point} datatypes for the given x/y coordinates.
	 * 
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ...,xN, yN]
	 * 
	 * @return the point list
	 */
	public List<Point> createPointList(int[] xy);

	/**
	 * Creates a list of {@link Point} datatypes for the given x/y coordinates.
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
	 * Creates a {@link Polygon} graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: true<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link Polygon} graphics algorithm. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link Polygon} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: true<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);
	
	/**
	 * Creates a plain {@link Polygon} graphics algorithm with the given points.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Polygon} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: true<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy);
	
	/**
	 * Creates a plain {@link Polygon} graphics algorithm with the given points.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Polygon} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: true<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
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
	 * Creates a plain {@link Polygon} graphics algorithm with the given points.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Polyline} graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link Polyline} graphics algorithm. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link Polyline} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);
	
	/**
	 * Creates a plain {@link Polyline} graphics algorithm with the given
	 * points. Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Polyline} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy);

	/**
	 * Creates a plain polyline graphics algorithm with the given points.
	 * Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Polyline} graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * filled: false<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
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
	 * Creates a plain {@link Polyline} graphics algorithm with the given
	 * points. Default values have been reset, so you can use your styles, see
	 * {@link AbstractStyle}.
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
	 * Creates a {@link Rectangle} graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new rectangle
	 */
	public Rectangle createRectangle(GraphicsAlgorithmContainer gaContainer);
	
	/**
	 * Creates a plain {@link Rectangle} graphics algorithm. Default values have
	 * been reset, so you can use your styles, see {@link AbstractStyle}.
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
	 * Creates a {@link RoundedRectangle} graphics algorithm with the given
	 * corner dimensions.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineWidth: 1<br>
	 * lineStyle: SOLID<br>
	 * transparency: 0.0<br>
	 * width: 0<br>
	 * height: 0<br>
	 * x: 0<br>
	 * y: 0<br>
	 * cornerHeight: cornerHeight<br>
	 * cornerWidth: cornerWidth<br>
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
	 * Creates a plain {@link RoundedRectangle} graphics algorithm with the
	 * given corner dimensions. Default values have been reset, so you can use
	 * your styles, see {@link AbstractStyle}.
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
	 * Shifts the {@link Color} darker or lighter.
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
	 * Shifts the {@link Color} constant darker or lighter.
	 * 
	 * @param colorConstant
	 *            the color constant to be changed
	 * @param shift
	 *            negative shifts means darken the color
	 * @return the color constant with the shifted values
	 */
	public IColorConstant createShiftedColor(IColorConstant colorConstant, int shift);

	/**
	 * Creates a {@link Style} with the given id. The style is aggregated under
	 * the given container style.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * lineStyle: SOLID<br>
	 * id: id<br>
	 * horizontalAlignment: ALIGNMENT_CENTER<br>
	 * verticalAlignment: ALIGNMENT_CENTER<br<
	 * 
	 * @param styleContainer
	 *            container style
	 * @param id
	 *            style id
	 * @return the newly created style
	 */
	public Style createStyle(StyleContainer styleContainer, String id);
	
	/**
	 * Creates a {@link Style} with the given id. The style is aggregated under
	 * the given container style. Default values have been reset, so you can use
	 * your styles, see {@link AbstractStyle}.
	 * 
	 * @param styleContainer
	 *            container style
	 * @param id
	 *            style id
	 * @return the newly created style
	 * @see Style
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.9
	 */
	public Style createPlainStyle(StyleContainer styleContainer, String id);

	/**
	 * Creates a {@link TextStyleRegion}. The style is aggregated under the
	 * given abstract text.
	 * 
	 * @param abstractText
	 *            container text
	 * @return the newly created text style region
	 * @see TextStyleRegion
	 * @see #createTextStyleRegion(AbstractText, int, int)
	 * @since 0.10
	 */
	public TextStyleRegion createTextStyleRegion(AbstractText abstractText);

	/**
	 * Creates a {@link TextStyleRegion} with the given bounds. The style is
	 * aggregated under the given abstract text.
	 * 
	 * @param abstractText
	 *            container text
	 * @param start
	 *            region start
	 * @param end
	 *            retion end
	 * @return the newly created text style region
	 * 
	 * @see TextStyleRegion
	 * @since 0.10
	 */
	public TextStyleRegion createTextStyleRegion(AbstractText abstractText, int start, int end);

	/**
	 * Creates a {@link TextStyle}. The style is aggregated under the given text
	 * style region.
	 * 
	 * @param region
	 *            container region
	 * @return the newly created text style
	 * 
	 * @see TextStyle
	 * @see #createTextStyle(TextStyleRegion, boolean, boolean, UnderlineStyle)
	 * @since 0.10
	 */
	public TextStyle createTextStyle(TextStyleRegion region);

	/**
	 * Creates a {@link TextStyle} with the given values. The style is
	 * aggregated under the given text style region.
	 * 
	 * @param region
	 *            container region
	 * @param underline
	 *            the is underlined
	 * @param strikeout
	 *            the is stroke out
	 * @param underlineStyle
	 *            the underline style
	 * @return the newly created text style
	 * 
	 * @see TextStyle
	 * @see #createStyle(StyleContainer, String)
	 * @since 0.10
	 */
	public TextStyle createTextStyle(TextStyleRegion region, boolean underline, boolean strikeout,
			UnderlineStyle underlineStyle);
}
