/**
 * <copyright>
 * 
 * Copyright (c) 2011, 2011 SAP AG.
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
package org.eclipse.graphiti.examples.mm.chess.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.ChessFactory;
import org.eclipse.graphiti.examples.mm.chess.ChessPackage;
import org.eclipse.graphiti.examples.mm.chess.Square;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Board</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl#getSquares <em>Squares</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoardImpl extends EObjectImpl implements Board {
	/**
	 * The cached value of the '{@link #getSquares() <em>Squares</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSquares()
	 * @generated
	 * @ordered
	 */
	protected EList<Square> squares;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	protected BoardImpl() {
		super();
		
		// Add squares
		for (int i = 0; i < 64; i++) {
			Square square = ChessFactory.eINSTANCE.createSquare();
			square.setIndex(i);
			getSquares().add(square);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChessPackage.Literals.BOARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Square> getSquares() {
		if (squares == null) {
			squares = new EObjectContainmentWithInverseEList<Square>(Square.class, this, ChessPackage.BOARD__SQUARES, ChessPackage.SQUARE__BOARD);
		}
		return squares;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSquares()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return ((InternalEList<?>)getSquares()).basicRemove(otherEnd, msgs);
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
			case ChessPackage.BOARD__SQUARES:
				return getSquares();
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
			case ChessPackage.BOARD__SQUARES:
				getSquares().clear();
				getSquares().addAll((Collection<? extends Square>)newValue);
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
			case ChessPackage.BOARD__SQUARES:
				getSquares().clear();
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
			case ChessPackage.BOARD__SQUARES:
				return squares != null && !squares.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BoardImpl
