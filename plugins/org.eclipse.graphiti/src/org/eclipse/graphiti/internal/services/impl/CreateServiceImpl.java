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
 *    Patch 184530 from Bug 331829 contributed by Henrik Rentz-Reichert
 *    mwenz - Bug 331715: Support for rectangular grids in diagrams
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal.services.impl;

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
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
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
	@Override
	public MultiText createDefaultMultiText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createDefaultMultiText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultMultiText
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public MultiText createDefaultMultiText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createDefaultMultiText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultText(org.
	 * eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer,
	 * java.lang.String)
	 */
	@Override
	public Text createDefaultText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createDefaultText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createDefaultText(org.
	 * eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Text createDefaultText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createDefaultText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createEllipse(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Ellipse createEllipse(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createEllipse(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createFont(org.eclipse
	 * .graphiti.mm.pictograms.AbstractText, java.lang.String, int, boolean,
	 * boolean)
	 */
	@Override
	public Font createFont(AbstractText text, String name, int size, boolean isItalic, boolean isBold) {
		return getGaService().createFont(text, name, size, isItalic, isBold);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createFont(org.eclipse
	 * .graphiti.mm.pictograms.AbstractText, java.lang.String, int)
	 */
	@Override
	public Font createFont(AbstractText text, String name, int size) {
		return getGaService().createFont(text, name, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createFont(org.eclipse
	 * .graphiti.mm.pictograms.Style, java.lang.String, int, boolean, boolean)
	 */
	@Override
	public Font createFont(Style style, String name, int size, boolean isItalic, boolean isBold) {
		return getGaService().createFont(style, name, size, isItalic, isBold);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createFont(org.eclipse
	 * .graphiti.mm.pictograms.Style, java.lang.String, int)
	 */
	@Override
	public Font createFont(Style style, String name, int size) {
		return getGaService().createFont(style, name, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createImage(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.lang.String)
	 */
	@Override
	public Image createImage(GraphicsAlgorithmContainer gaContainer, String imageId) {
		return getGaService().createImage(gaContainer, imageId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createInvisibleRectangle
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
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
	@Override
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createMultiText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createMultiText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createMultiText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.services.IGaCreateService#
	 * createPlatformGraphicsAlgorithm
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer,
	 * java.lang.String)
	 */
	@Override
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id) {
		return getGaService().createPlatformGraphicsAlgorithm(gaContainer, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPoint(org.eclipse
	 * .emf.ecore.EObject, int, int, int, int)
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPolygon(gaContainer, points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[], int[])
	 */
	@Override
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPolygon(gaContainer, xy, beforeAfter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[])
	 */
	@Override
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPolygon(gaContainer, xy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolygon(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPolygon(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.util.Collection)
	 */
	@Override
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points) {
		return getGaService().createPolyline(gaContainer, points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[], int[])
	 */
	@Override
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int[] beforeAfter) {
		return getGaService().createPolyline(gaContainer, xy, beforeAfter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, int[])
	 */
	@Override
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy) {
		return getGaService().createPolyline(gaContainer, xy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createPolyline(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createPolyline(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createRectangle(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Rectangle createRectangle(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createRectangle(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createRoundedRectangle
	 * (org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer, int, int)
	 */
	@Override
	public RoundedRectangle createRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight) {
		return getGaService().createRoundedRectangle(gaContainer, cornerWidth, cornerHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createShiftedColor(org
	 * .eclipse.graphiti.mm.datatypes.Color, int,
	 * org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	@Override
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
	@Override
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
	@Override
	public Style createStyle(StyleContainer styleContainer, String id) {
		return getGaService().createStyle(styleContainer, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer, java.lang.String)
	 */
	@Override
	public Text createText(GraphicsAlgorithmContainer gaContainer, String value) {
		return getGaService().createText(gaContainer, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IGaCreateService#createText(org.eclipse
	 * .graphiti.mm.pictograms.GraphicsAlgorithmContainer)
	 */
	@Override
	public Text createText(GraphicsAlgorithmContainer gaContainer) {
		return getGaService().createText(gaContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createBoxRelativeAnchor
	 * (org.eclipse.graphiti.mm.pictograms.AnchorContainer)
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public ManhattanConnection createManhattanConnection(Diagram diagram) {
		return getPeService().createManhattanConnection(diagram);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.services.IPeCreateService#createShape(org.eclipse
	 * .graphiti.mm.pictograms.ContainerShape, boolean)
	 */
	@Override
	public Shape createShape(ContainerShape parentContainerShape, boolean active) {
		return getPeService().createShape(parentContainerShape, active);
	}
}
