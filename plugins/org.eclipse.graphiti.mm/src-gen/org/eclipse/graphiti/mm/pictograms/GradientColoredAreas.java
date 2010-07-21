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
 * $Id: GradientColoredAreas.java,v 1.1 2010/07/21 12:34:45 jpasch Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gradient Colored Areas</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.GradientColoredAreas#getGradientColor <em>Gradient Color</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.GradientColoredAreas#getStyleAdaption <em>Style Adaption</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredAreas()
 * @model
 * @generated
 */
public interface GradientColoredAreas extends EObject {
	/**
	 * Returns the value of the '<em><b>Gradient Color</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.GradientColoredArea}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gradient Color</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gradient Color</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredAreas_GradientColor()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<GradientColoredArea> getGradientColor();

	/**
	 * Returns the value of the '<em><b>Style Adaption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Adaption</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style Adaption</em>' attribute.
	 * @see #setStyleAdaption(Integer)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredAreas_StyleAdaption()
	 * @model
	 * @generated
	 */
	Integer getStyleAdaption();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.GradientColoredAreas#getStyleAdaption <em>Style Adaption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Adaption</em>' attribute.
	 * @see #getStyleAdaption()
	 * @generated
	 */
	void setStyleAdaption(Integer value);

} // GradientColoredAreas
