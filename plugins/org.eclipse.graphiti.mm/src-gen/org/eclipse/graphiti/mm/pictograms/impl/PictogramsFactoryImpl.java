/**
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
 * $Id: PictogramsFactoryImpl.java,v 1.3 2010/07/21 12:34:45 jpasch Exp $
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.graphiti.mm.pictograms.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PictogramsFactoryImpl extends EFactoryImpl implements PictogramsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PictogramsFactory init() {
		try {
			PictogramsFactory thePictogramsFactory = (PictogramsFactory)EPackage.Registry.INSTANCE.getEFactory("http://eclipse.org/graphiti/pictograms"); 
			if (thePictogramsFactory != null) {
				return thePictogramsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PictogramsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PictogramsPackage.SHAPE: return createShape();
			case PictogramsPackage.CONTAINER_SHAPE: return createContainerShape();
			case PictogramsPackage.DIAGRAM: return createDiagram();
			case PictogramsPackage.POLYLINE: return createPolyline();
			case PictogramsPackage.ELLIPSE: return createEllipse();
			case PictogramsPackage.CONNECTION: return createConnection();
			case PictogramsPackage.FIX_POINT_ANCHOR: return createFixPointAnchor();
			case PictogramsPackage.BOX_RELATIVE_ANCHOR: return createBoxRelativeAnchor();
			case PictogramsPackage.CHOPBOX_ANCHOR: return createChopboxAnchor();
			case PictogramsPackage.PROPERTY: return createProperty();
			case PictogramsPackage.TEXT: return createText();
			case PictogramsPackage.CONNECTION_DECORATOR: return createConnectionDecorator();
			case PictogramsPackage.FREE_FORM_CONNECTION: return createFreeFormConnection();
			case PictogramsPackage.MANHATTAN_CONNECTION: return createManhattanConnection();
			case PictogramsPackage.POLYGON: return createPolygon();
			case PictogramsPackage.RECTANGLE: return createRectangle();
			case PictogramsPackage.ROUNDED_RECTANGLE: return createRoundedRectangle();
			case PictogramsPackage.FONT: return createFont();
			case PictogramsPackage.RENDERING_STYLE: return createRenderingStyle();
			case PictogramsPackage.IMAGE: return createImage();
			case PictogramsPackage.PLATFORM_GRAPHICS_ALGORITHM: return createPlatformGraphicsAlgorithm();
			case PictogramsPackage.MULTI_TEXT: return createMultiText();
			case PictogramsPackage.STYLE: return createStyle();
			case PictogramsPackage.GRADIENT_COLORED_LOCATION: return createGradientColoredLocation();
			case PictogramsPackage.GRADIENT_COLORED_AREA: return createGradientColoredArea();
			case PictogramsPackage.GRADIENT_COLORED_AREAS: return createGradientColoredAreas();
			case PictogramsPackage.ADAPTED_GRADIENT_COLORED_AREAS: return createAdaptedGradientColoredAreas();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PictogramsPackage.LINE_STYLE:
				return createLineStyleFromString(eDataType, initialValue);
			case PictogramsPackage.ORIENTATION:
				return createOrientationFromString(eDataType, initialValue);
			case PictogramsPackage.LOCATION_TYPE:
				return createLocationTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PictogramsPackage.LINE_STYLE:
				return convertLineStyleToString(eDataType, instanceValue);
			case PictogramsPackage.ORIENTATION:
				return convertOrientationToString(eDataType, instanceValue);
			case PictogramsPackage.LOCATION_TYPE:
				return convertLocationTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape createShape() {
		ShapeImpl shape = new ShapeImpl();
		return shape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerShape createContainerShape() {
		ContainerShapeImpl containerShape = new ContainerShapeImpl();
		return containerShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Polyline createPolyline() {
		PolylineImpl polyline = new PolylineImpl();
		return polyline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ellipse createEllipse() {
		EllipseImpl ellipse = new EllipseImpl();
		return ellipse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixPointAnchor createFixPointAnchor() {
		FixPointAnchorImpl fixPointAnchor = new FixPointAnchorImpl();
		return fixPointAnchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoxRelativeAnchor createBoxRelativeAnchor() {
		BoxRelativeAnchorImpl boxRelativeAnchor = new BoxRelativeAnchorImpl();
		return boxRelativeAnchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChopboxAnchor createChopboxAnchor() {
		ChopboxAnchorImpl chopboxAnchor = new ChopboxAnchorImpl();
		return chopboxAnchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionDecorator createConnectionDecorator() {
		ConnectionDecoratorImpl connectionDecorator = new ConnectionDecoratorImpl();
		return connectionDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FreeFormConnection createFreeFormConnection() {
		FreeFormConnectionImpl freeFormConnection = new FreeFormConnectionImpl();
		return freeFormConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManhattanConnection createManhattanConnection() {
		ManhattanConnectionImpl manhattanConnection = new ManhattanConnectionImpl();
		return manhattanConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Polygon createPolygon() {
		PolygonImpl polygon = new PolygonImpl();
		return polygon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rectangle createRectangle() {
		RectangleImpl rectangle = new RectangleImpl();
		return rectangle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoundedRectangle createRoundedRectangle() {
		RoundedRectangleImpl roundedRectangle = new RoundedRectangleImpl();
		return roundedRectangle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Font createFont() {
		FontImpl font = new FontImpl();
		return font;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderingStyle createRenderingStyle() {
		RenderingStyleImpl renderingStyle = new RenderingStyleImpl();
		return renderingStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image createImage() {
		ImageImpl image = new ImageImpl();
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm() {
		PlatformGraphicsAlgorithmImpl platformGraphicsAlgorithm = new PlatformGraphicsAlgorithmImpl();
		return platformGraphicsAlgorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiText createMultiText() {
		MultiTextImpl multiText = new MultiTextImpl();
		return multiText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Style createStyle() {
		StyleImpl style = new StyleImpl();
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradientColoredLocation createGradientColoredLocation() {
		GradientColoredLocationImpl gradientColoredLocation = new GradientColoredLocationImpl();
		return gradientColoredLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradientColoredArea createGradientColoredArea() {
		GradientColoredAreaImpl gradientColoredArea = new GradientColoredAreaImpl();
		return gradientColoredArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradientColoredAreas createGradientColoredAreas() {
		GradientColoredAreasImpl gradientColoredAreas = new GradientColoredAreasImpl();
		return gradientColoredAreas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptedGradientColoredAreas createAdaptedGradientColoredAreas() {
		AdaptedGradientColoredAreasImpl adaptedGradientColoredAreas = new AdaptedGradientColoredAreasImpl();
		return adaptedGradientColoredAreas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyle createLineStyleFromString(EDataType eDataType, String initialValue) {
		LineStyle result = LineStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLineStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation createOrientationFromString(EDataType eDataType, String initialValue) {
		Orientation result = Orientation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocationType createLocationTypeFromString(EDataType eDataType, String initialValue) {
		LocationType result = LocationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsPackage getPictogramsPackage() {
		return (PictogramsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PictogramsPackage getPackage() {
		return PictogramsPackage.eINSTANCE;
	}

} //PictogramsFactoryImpl
