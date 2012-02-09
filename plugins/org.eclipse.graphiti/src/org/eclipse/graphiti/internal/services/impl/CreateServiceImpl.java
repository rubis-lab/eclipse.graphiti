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
 *    Patch 184530 from Bug 331829 contributed by Henrik Rentz-Reichert
 *    mwenz - Bug 331715: Support for rectangular grids in diagrams
 *    mwenz - Bug 355347 - Remove setters of Graphiti's Font Interface
 *    jpasch - Bug 352542 - Add "plain"-create methods for working with styles
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal.services.impl;

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
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
/**
 * @author hrentz
 * 
 */
public final class CreateServiceImpl extends AbstractServiceHolder implements ICreateService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultMultiText
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer,
	 * java.lang.String)
	 */
	public MultiText createDefaultMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createDefaultMultiText(diagram, gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultMultiText
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public MultiText createDefaultMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createDefaultMultiText(diagram, gaContainer);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultText(org.
	 * eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer,
	 * java.lang.String)
	 */
	public Text createDefaultText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createDefaultText(diagram, gaContainer, value);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultText(org.
	 * eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Text createDefaultText(Diagram diagram, GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createDefaultText(diagram, gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createEllipse(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Ellipse createEllipse(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createEllipse(gaContainer);
	}
	
	public Ellipse createPlainEllipse(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainEllipse(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createImage(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.lang.String)
	 */
	public Image createImage(GraphicsAlgorithmContainer gaContainer, String imageId) {
		return getGaService().createImage(gaContainer, imageId);
	}
	
	public Image createPlainImage(GraphicsAlgorithmContainer gaContainer, String imageId) {
		return getGaService().createPlainImage(gaContainer, imageId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createInvisibleRectangle
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public Rectangle createInvisibleRectangle(PictogramElement pe) {
		return getGaService().createInvisibleRectangle(pe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createMultiText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.lang.String)
	 */
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createMultiText(gaContainer, value);
	}
	
	public MultiText createPlainMultiText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createPlainMultiText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createMultiText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createMultiText(gaContainer);
	}
	
	public MultiText createPlainMultiText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainMultiText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createMultiText(org.eclipse
	 * .graphiti.mm.pictograms.Diagram,
	 * org.eclipse.graphiti.mm.GraphicsAlgorithmContainer, java.lang.String,
	 * java.lang.String, int)
	 */
	public MultiText createMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize) {
		return getGaService().createMultiText(diagram, gaContainer, value, fontName, fontSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createMultiText(org.eclipse
	 * .graphiti.mm.pictograms.Diagram,
	 * org.eclipse.graphiti.mm.GraphicsAlgorithmContainer, java.lang.String,
	 * java.lang.String, int, boolean, boolean)
	 */
	public MultiText createMultiText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize,
			boolean isFontItalic, boolean isFontBold) {
		return getGaService().createMultiText(diagram, gaContainer, value, fontName, fontSize, isFontItalic, isFontBold);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.services.IGaCreateService#
	 * createPlatformGraphicsAlgorithm
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer,
	 * java.lang.String)
	 */
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id) {
		return getGaService().createPlatformGraphicsAlgorithm(gaContainer, id);
	}
	
	public PlatformGraphicsAlgorithm createPlainPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id) {
		return getGaService().createPlainPlatformGraphicsAlgorithm(gaContainer, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPoint(org.eclipse
	 * .emf.ecore.EObject, int, int, int, int)
	 */
	public Point createPoint(int x, int y, int before, int after) {
		return getGaService().createPoint(x, y, before, after);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPoint(org.eclipse
	 * .emf.ecore.EObject, int, int)
	 */
	public Point createPoint(int x, int y) {
		return getGaService().createPoint(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPointList(org.eclipse
	 * .emf.ecore.EObject, int[], int[])
	 */
	public List<Point> createPointList(int[] xy, int[] beforeAfter) {
		return getGaService().createPointList(xy, beforeAfter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPointList(org.eclipse
	 * .emf.ecore.EObject, int[])
	 */
	public List<Point> createPointList(int[] xy) {
		return getGaService().createPointList(xy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.util.Collection)
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPolygon(gaContainer, points);
	}
	
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPlainPolygon(gaContainer, points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[], int[])
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPolygon(gaContainer, xy, beforeAfter);
	}
	
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPlainPolygon(gaContainer, xy, beforeAfter);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[])
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPolygon(gaContainer, xy);
	}
	
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPolygon(gaContainer, xy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPolygon(gaContainer);
	}
	
	public Polygon createPlainPolygon(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainPolygon(gaContainer);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.util.Collection)
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPolyline(gaContainer, points);
	}
	
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPlainPolyline(gaContainer, points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[], int[])
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPolyline(gaContainer, xy, beforeAfter);
	}
	
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPlainPolyline(gaContainer, xy, beforeAfter);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[])
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPolyline(gaContainer, xy);
	}
	
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPlainPolyline(gaContainer, xy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPolyline(gaContainer);
	}
	
	public Polyline createPlainPolyline(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainPolyline(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createRectangle(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Rectangle createRectangle(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createRectangle(gaContainer);
	}
	
	public Rectangle createPlainRectangle(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainRectangle(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createRoundedRectangle
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer, int, int)
	 */
	public RoundedRectangle createRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight) {
		return getGaService().createRoundedRectangle(gaContainer, cornerWidth, cornerHeight);
	}
	
	public RoundedRectangle createPlainRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight) {
		return getGaService().createPlainRoundedRectangle(gaContainer, cornerWidth, cornerHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createShiftedColor(org
	 * .eclipse.graphiti.mm.datatypes.Color, int,
	 * org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	public Color createShiftedColor(Color color, int shift, Diagram diagram) {
		return getGaService().createShiftedColor(color, shift, diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createShiftedColor(org
	 * .eclipse.graphiti.util.IColorConstant, int)
	 */
	public IColorConstant createShiftedColor(IColorConstant colorConstant, int shift) {
		return getGaService().createShiftedColor(colorConstant, shift);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createStyle(org.eclipse
	 * .graphiti.mm.pictograms.StyleContainer, java.lang.String)
	 */
	public Style createStyle(StyleContainer styleContainer, String id) {
		return getGaService().createStyle(styleContainer, id);
	}
	
	public Style createPlainStyle(StyleContainer styleContainer, String id) {
		return getGaService().createStyle(styleContainer, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.lang.String)
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createText(gaContainer, value);
	}
	
	public Text createPlainText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createPlainText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createText(gaContainer);
	}
	
	public Text createPlainText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPlainText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.Diagram,
	 * org.eclipse.graphiti.mm.GraphicsAlgorithmContainer, java.lang.String,
	 * java.lang.String, int)
	 */
	public Text createText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize) {
		return getGaService().createText(diagram, gaContainer, value, fontName, fontSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.Diagram,
	 * org.eclipse.graphiti.mm.GraphicsAlgorithmContainer, java.lang.String,
	 * java.lang.String, int, boolean, boolean)
	 */
	public Text createText(Diagram diagram, GraphicsAlgorithmContainer gaContainer, String value, String fontName, int fontSize,
			boolean isFontItalic, boolean isFontBold) {
		return getGaService().createText(diagram, gaContainer, value, fontName, fontSize, isFontItalic, isFontBold);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createBoxRelativeAnchor
	 * (org.eclipse.graphiti.mm.pictograms.AnchorContainer)
	 */
	public BoxRelativeAnchor createBoxRelativeAnchor(AnchorContainer anchorContainer) {
		return getPeService().createBoxRelativeAnchor(anchorContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createChopboxAnchor(org
	 * .eclipse.graphiti.mm.pictograms.AnchorContainer)
	 */
	public ChopboxAnchor createChopboxAnchor(AnchorContainer anchorContainer) {
		return getPeService().createChopboxAnchor(anchorContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createConnectionDecorator
	 * (org.eclipse.graphiti.mm.pictograms.Connection, boolean, double, boolean)
	 */
	public ConnectionDecorator createConnectionDecorator(Connection connection, boolean active, double location, boolean isRelative) {
		return getPeService().createConnectionDecorator(connection, active, location, isRelative);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createContainerShape(org
	 * .eclipse.graphiti.mm.pictograms.ContainerShape, boolean)
	 */
	public ContainerShape createContainerShape(ContainerShape parentContainerShape, boolean active) {
		return getPeService().createContainerShape(parentContainerShape, active);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createDiagram(java.lang
	 * .String, java.lang.String, boolean)
	 */
	public Diagram createDiagram(String diagramTypeId, String diagramName, boolean snap) {
		return getPeService().createDiagram(diagramTypeId, diagramName, snap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createDiagram(java.lang
	 * .String, java.lang.String, int, boolean)
	 */
	public Diagram createDiagram(String diagramTypeId, String diagramName, int gridUnit, boolean snap) {
		return getPeService().createDiagram(diagramTypeId, diagramName, gridUnit, snap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createDiagram(java.lang
	 * .String, java.lang.String, int, boolean)
	 */
	public Diagram createDiagram(String diagramTypeId, String diagramName, int horizontalGridUnit, int verticalGridUint, boolean snap) {
		return getPeService().createDiagram(diagramTypeId, diagramName, horizontalGridUnit, verticalGridUint, snap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createFixPointAnchor(org
	 * .eclipse.graphiti.mm.pictograms.AnchorContainer)
	 */
	public FixPointAnchor createFixPointAnchor(AnchorContainer anchorContainer) {
		return getPeService().createFixPointAnchor(anchorContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createFreeFormConnection
	 * (org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	public FreeFormConnection createFreeFormConnection(Diagram diagram) {
		return getPeService().createFreeFormConnection(diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createManhattanConnection
	 * (org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	public ManhattanConnection createManhattanConnection(Diagram diagram) {
		return getPeService().createManhattanConnection(diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createCurvedConnection
	 * (org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	public CurvedConnection createCurvedConnection(double[] controllPoints, Diagram diagram) {
		return getPeService().createCurvedConnection(controllPoints, diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createCompositeConnection
	 * (org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	public CompositeConnection createCompositeConnection(Diagram diagram) {
		return getPeService().createCompositeConnection(diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createShape(org.eclipse
	 * .graphiti.mm.pictograms.ContainerShape, boolean)
	 */
	public Shape createShape(ContainerShape parentContainerShape, boolean active) {
		return getPeService().createShape(parentContainerShape, active);
	}
}
