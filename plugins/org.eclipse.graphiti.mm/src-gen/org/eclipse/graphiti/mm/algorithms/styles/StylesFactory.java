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
 */
package org.eclipse.graphiti.mm.algorithms.styles;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage
 * @generated
 */
public interface StylesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StylesFactory eINSTANCE = org.eclipse.graphiti.mm.algorithms.styles.impl.StylesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Rendering Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rendering Style</em>'.
	 * @generated
	 */
	RenderingStyle createRenderingStyle();

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
	 * Returns a new object of class '<em>Font</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Font</em>'.
	 * @generated
	 */
	Font createFont();

	/**
	 * Returns a new object of class '<em>Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Point</em>'.
	 * @generated
	 */
	Point createPoint();

	/**
	 * Returns a new object of class '<em>Color</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Color</em>'.
	 * @generated
	 */
	Color createColor();

	/**
	 * Returns a new object of class '<em>Precision Point</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Precision Point</em>'.
	 * @generated
	 * @since 0.9
	 */
	PrecisionPoint createPrecisionPoint();

	/**
	 * Returns a new object of class '<em>Text Style</em>'.
	 * <!-- begin-user-doc -->
	 * @since 0.10
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Style</em>'.
	 * @generated
	 */
	TextStyle createTextStyle();

	/**
	 * Returns a new object of class '<em>Text Style Region</em>'.
	 * <!-- begin-user-doc -->
	 * @since 0.10
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Style Region</em>'.
	 * @generated
	 */
	TextStyleRegion createTextStyleRegion();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StylesPackage getStylesPackage();

} //StylesFactory
