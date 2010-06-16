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
 * $Id: RenderingStyleImpl.java,v 1.1 2010/06/16 13:24:59 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.mm.pictograms.RenderingStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendering Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.RenderingStyleImpl#getPredefinedStyleId <em>Predefined Style Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderingStyleImpl extends EObjectImpl implements RenderingStyle {
	/**
	 * The default value of the '{@link #getPredefinedStyleId() <em>Predefined Style Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedStyleId()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDEFINED_STYLE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredefinedStyleId() <em>Predefined Style Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedStyleId()
	 * @generated
	 * @ordered
	 */
	protected String predefinedStyleId = PREDEFINED_STYLE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderingStyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.RENDERING_STYLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredefinedStyleId() {
		return predefinedStyleId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredefinedStyleId(String newPredefinedStyleId) {
		String oldPredefinedStyleId = predefinedStyleId;
		predefinedStyleId = newPredefinedStyleId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.RENDERING_STYLE__PREDEFINED_STYLE_ID, oldPredefinedStyleId, predefinedStyleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.RENDERING_STYLE__PREDEFINED_STYLE_ID:
				return getPredefinedStyleId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PictogramsPackage.RENDERING_STYLE__PREDEFINED_STYLE_ID:
				setPredefinedStyleId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PictogramsPackage.RENDERING_STYLE__PREDEFINED_STYLE_ID:
				setPredefinedStyleId(PREDEFINED_STYLE_ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PictogramsPackage.RENDERING_STYLE__PREDEFINED_STYLE_ID:
				return PREDEFINED_STYLE_ID_EDEFAULT == null ? predefinedStyleId != null : !PREDEFINED_STYLE_ID_EDEFAULT.equals(predefinedStyleId);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (predefinedStyleId: ");
		result.append(predefinedStyleId);
		result.append(')');
		return result.toString();
	}

} //RenderingStyleImpl
