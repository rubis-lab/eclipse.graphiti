/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz Bug 352119 - initial API, implementation and documentation contributed by Benjamin Schmeling
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.mm.algorithms.styles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Precision Point</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint#getX <em>
 * X</em>}</li>
 * <li>{@link org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint#getY <em>
 * Y</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getPrecisionPoint()
 * @model
 * @generated
 * @since 0.9
 */
public interface PrecisionPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getPrecisionPoint_X()
	 * @model required="true"
	 * @generated
	 */
	double getX();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(double value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(double)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getPrecisionPoint_Y()
	 * @model required="true"
	 * @generated
	 */
	double getY();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(double value);

} // PrecisionPoint
