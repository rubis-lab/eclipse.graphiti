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
package org.eclipse.graphiti.mm.pictograms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Curved Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.CurvedConnectionImpl#getControlPoints <em>Control Points</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CurvedConnectionImpl extends ConnectionImpl implements CurvedConnection {
	/**
	 * The cached value of the '{@link #getControlPoints() <em>Control Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<PrecisionPoint> controlPoints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CurvedConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.CURVED_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrecisionPoint> getControlPoints() {
		if (controlPoints == null) {
			controlPoints = new EObjectContainmentEList.Resolving<PrecisionPoint>(PrecisionPoint.class, this, PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS);
		}
		return controlPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS:
				return ((InternalEList<?>)getControlPoints()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS:
				return getControlPoints();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS:
				getControlPoints().clear();
				getControlPoints().addAll((Collection<? extends PrecisionPoint>)newValue);
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
			case PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS:
				getControlPoints().clear();
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
			case PictogramsPackage.CURVED_CONNECTION__CONTROL_POINTS:
				return controlPoints != null && !controlPoints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CurvedConnectionImpl