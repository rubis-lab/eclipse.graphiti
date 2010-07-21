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
 * $Id: GradientColoredLocation.java,v 1.1 2010/07/21 12:34:45 jpasch Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.datatypes.Color;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gradient Colored Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getLocationType <em>Location Type</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getLocationValue <em>Location Value</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredLocation()
 * @model
 * @generated
 */
public interface GradientColoredLocation extends EObject {
	/**
	 * Returns the value of the '<em><b>Location Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.mm.pictograms.LocationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location Type</em>' attribute.
	 * @see org.eclipse.graphiti.mm.pictograms.LocationType
	 * @see #setLocationType(LocationType)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredLocation_LocationType()
	 * @model required="true"
	 * @generated
	 */
	LocationType getLocationType();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getLocationType <em>Location Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location Type</em>' attribute.
	 * @see org.eclipse.graphiti.mm.pictograms.LocationType
	 * @see #getLocationType()
	 * @generated
	 */
	void setLocationType(LocationType value);

	/**
	 * Returns the value of the '<em><b>Location Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location Value</em>' attribute.
	 * @see #setLocationValue(Integer)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredLocation_LocationValue()
	 * @model required="true"
	 * @generated
	 */
	Integer getLocationValue();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getLocationValue <em>Location Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location Value</em>' attribute.
	 * @see #getLocationValue()
	 * @generated
	 */
	void setLocationValue(Integer value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' containment reference.
	 * @see #setColor(Color)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getGradientColoredLocation_Color()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	Color getColor();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.GradientColoredLocation#getColor <em>Color</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' containment reference.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(Color value);

} // GradientColoredLocation
