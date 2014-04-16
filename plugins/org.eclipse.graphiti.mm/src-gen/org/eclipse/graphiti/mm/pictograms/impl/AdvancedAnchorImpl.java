/**
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
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Advanced Anchor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.AdvancedAnchorImpl#isUseAnchorLocationAsConnectionEndpoint <em>Use Anchor Location As Connection Endpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AdvancedAnchorImpl extends AnchorImpl implements AdvancedAnchor {
	/**
	 * The default value of the '{@link #isUseAnchorLocationAsConnectionEndpoint() <em>Use Anchor Location As Connection Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseAnchorLocationAsConnectionEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUseAnchorLocationAsConnectionEndpoint() <em>Use Anchor Location As Connection Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseAnchorLocationAsConnectionEndpoint()
	 * @generated
	 * @ordered
	 */
	protected boolean useAnchorLocationAsConnectionEndpoint = USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdvancedAnchorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.ADVANCED_ANCHOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUseAnchorLocationAsConnectionEndpoint() {
		return useAnchorLocationAsConnectionEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseAnchorLocationAsConnectionEndpoint(boolean newUseAnchorLocationAsConnectionEndpoint) {
		boolean oldUseAnchorLocationAsConnectionEndpoint = useAnchorLocationAsConnectionEndpoint;
		useAnchorLocationAsConnectionEndpoint = newUseAnchorLocationAsConnectionEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.ADVANCED_ANCHOR__USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT, oldUseAnchorLocationAsConnectionEndpoint, useAnchorLocationAsConnectionEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.ADVANCED_ANCHOR__USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT:
				return isUseAnchorLocationAsConnectionEndpoint();
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
			case PictogramsPackage.ADVANCED_ANCHOR__USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT:
				setUseAnchorLocationAsConnectionEndpoint((Boolean)newValue);
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
			case PictogramsPackage.ADVANCED_ANCHOR__USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT:
				setUseAnchorLocationAsConnectionEndpoint(USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT_EDEFAULT);
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
			case PictogramsPackage.ADVANCED_ANCHOR__USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT:
				return useAnchorLocationAsConnectionEndpoint != USE_ANCHOR_LOCATION_AS_CONNECTION_ENDPOINT_EDEFAULT;
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
		result.append(" (useAnchorLocationAsConnectionEndpoint: ");
		result.append(useAnchorLocationAsConnectionEndpoint);
		result.append(')');
		return result.toString();
	}

} //AdvancedAnchorImpl
