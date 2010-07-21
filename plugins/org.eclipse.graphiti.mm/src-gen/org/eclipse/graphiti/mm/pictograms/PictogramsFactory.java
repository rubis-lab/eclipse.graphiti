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
 * $Id: PictogramsFactory.java,v 1.3 2010/07/21 12:34:45 jpasch Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage
 * @generated
 */
public interface PictogramsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PictogramsFactory eINSTANCE = org.eclipse.graphiti.mm.pictograms.impl.PictogramsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Shape</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shape</em>'.
	 * @generated
	 */
	Shape createShape();

	/**
	 * Returns a new object of class '<em>Container Shape</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Shape</em>'.
	 * @generated
	 */
	ContainerShape createContainerShape();

	/**
	 * Returns a new object of class '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram</em>'.
	 * @generated
	 */
	Diagram createDiagram();

	/**
	 * Returns a new object of class '<em>Polyline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Polyline</em>'.
	 * @generated
	 */
	Polyline createPolyline();

	/**
	 * Returns a new object of class '<em>Ellipse</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ellipse</em>'.
	 * @generated
	 */
	Ellipse createEllipse();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	Connection createConnection();

	/**
	 * Returns a new object of class '<em>Fix Point Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fix Point Anchor</em>'.
	 * @generated
	 */
	FixPointAnchor createFixPointAnchor();

	/**
	 * Returns a new object of class '<em>Box Relative Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Box Relative Anchor</em>'.
	 * @generated
	 */
	BoxRelativeAnchor createBoxRelativeAnchor();

	/**
	 * Returns a new object of class '<em>Chopbox Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Chopbox Anchor</em>'.
	 * @generated
	 */
	ChopboxAnchor createChopboxAnchor();

	/**
	 * Returns a new object of class '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property</em>'.
	 * @generated
	 */
	Property createProperty();

	/**
	 * Returns a new object of class '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text</em>'.
	 * @generated
	 */
	Text createText();

	/**
	 * Returns a new object of class '<em>Connection Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Decorator</em>'.
	 * @generated
	 */
	ConnectionDecorator createConnectionDecorator();

	/**
	 * Returns a new object of class '<em>Free Form Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Free Form Connection</em>'.
	 * @generated
	 */
	FreeFormConnection createFreeFormConnection();

	/**
	 * Returns a new object of class '<em>Manhattan Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Manhattan Connection</em>'.
	 * @generated
	 */
	ManhattanConnection createManhattanConnection();

	/**
	 * Returns a new object of class '<em>Polygon</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Polygon</em>'.
	 * @generated
	 */
	Polygon createPolygon();

	/**
	 * Returns a new object of class '<em>Rectangle</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rectangle</em>'.
	 * @generated
	 */
	Rectangle createRectangle();

	/**
	 * Returns a new object of class '<em>Rounded Rectangle</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rounded Rectangle</em>'.
	 * @generated
	 */
	RoundedRectangle createRoundedRectangle();

	/**
	 * Returns a new object of class '<em>Font</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Font</em>'.
	 * @generated
	 */
	Font createFont();

	/**
	 * Returns a new object of class '<em>Rendering Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rendering Style</em>'.
	 * @generated
	 */
	RenderingStyle createRenderingStyle();

	/**
	 * Returns a new object of class '<em>Image</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Image</em>'.
	 * @generated
	 */
	Image createImage();

	/**
	 * Returns a new object of class '<em>Platform Graphics Algorithm</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Graphics Algorithm</em>'.
	 * @generated
	 */
	PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm();

	/**
	 * Returns a new object of class '<em>Multi Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Text</em>'.
	 * @generated
	 */
	MultiText createMultiText();

	/**
	 * Returns a new object of class '<em>Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Style</em>'.
	 * @generated
	 */
	Style createStyle();

	/**
	 * Returns a new object of class '<em>Gradient Colored Location</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gradient Colored Location</em>'.
	 * @generated
	 */
	GradientColoredLocation createGradientColoredLocation();

	/**
	 * Returns a new object of class '<em>Gradient Colored Area</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gradient Colored Area</em>'.
	 * @generated
	 */
	GradientColoredArea createGradientColoredArea();

	/**
	 * Returns a new object of class '<em>Gradient Colored Areas</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gradient Colored Areas</em>'.
	 * @generated
	 */
	GradientColoredAreas createGradientColoredAreas();

	/**
	 * Returns a new object of class '<em>Adapted Gradient Colored Areas</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adapted Gradient Colored Areas</em>'.
	 * @generated
	 */
	AdaptedGradientColoredAreas createAdaptedGradientColoredAreas();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PictogramsPackage getPictogramsPackage();

} //PictogramsFactory
