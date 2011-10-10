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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.ChessPackage;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.examples.mm.chess.Types;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Piece</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl#getSquare <em>Square</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl#getBoard <em>Board</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PieceImpl extends EObjectImpl implements Piece {
	/**
	 * The default value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected static final Colors OWNER_EDEFAULT = Colors.NONE;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected Colors owner = OWNER_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final Types TYPE_EDEFAULT = Types.NONE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Types type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSquare() <em>Square</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSquare()
	 * @generated
	 * @ordered
	 */
	protected Square square;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PieceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChessPackage.Literals.PIECE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Colors getOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(Colors newOwner) {
		Colors oldOwner = owner;
		owner = newOwner == null ? OWNER_EDEFAULT : newOwner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.PIECE__OWNER, oldOwner, owner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Types getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Types newType) {
		Types oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.PIECE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Square getSquare() {
		if (square != null && square.eIsProxy()) {
			InternalEObject oldSquare = (InternalEObject)square;
			square = (Square)eResolveProxy(oldSquare);
			if (square != oldSquare) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ChessPackage.PIECE__SQUARE, oldSquare, square));
			}
		}
		return square;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Square basicGetSquare() {
		return square;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSquare(Square newSquare, NotificationChain msgs) {
		Square oldSquare = square;
		square = newSquare;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ChessPackage.PIECE__SQUARE, oldSquare, newSquare);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSquare(Square newSquare) {
		if (newSquare != square) {
			NotificationChain msgs = null;
			if (square != null)
				msgs = ((InternalEObject)square).eInverseRemove(this, ChessPackage.SQUARE__PIECE, Square.class, msgs);
			if (newSquare != null)
				msgs = ((InternalEObject)newSquare).eInverseAdd(this, ChessPackage.SQUARE__PIECE, Square.class, msgs);
			msgs = basicSetSquare(newSquare, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.PIECE__SQUARE, newSquare, newSquare));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Board getBoard() {
		if (eContainerFeatureID() != ChessPackage.PIECE__BOARD) return null;
		return (Board)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBoard(Board newBoard, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBoard, ChessPackage.PIECE__BOARD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoard(Board newBoard) {
		if (newBoard != eInternalContainer() || (eContainerFeatureID() != ChessPackage.PIECE__BOARD && newBoard != null)) {
			if (EcoreUtil.isAncestor(this, newBoard))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBoard != null)
				msgs = ((InternalEObject)newBoard).eInverseAdd(this, ChessPackage.BOARD__PIECES, Board.class, msgs);
			msgs = basicSetBoard(newBoard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.PIECE__BOARD, newBoard, newBoard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.PIECE__SQUARE:
				if (square != null)
					msgs = ((InternalEObject)square).eInverseRemove(this, ChessPackage.SQUARE__PIECE, Square.class, msgs);
				return basicSetSquare((Square)otherEnd, msgs);
			case ChessPackage.PIECE__BOARD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBoard((Board)otherEnd, msgs);
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
			case ChessPackage.PIECE__SQUARE:
				return basicSetSquare(null, msgs);
			case ChessPackage.PIECE__BOARD:
				return basicSetBoard(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ChessPackage.PIECE__BOARD:
				return eInternalContainer().eInverseRemove(this, ChessPackage.BOARD__PIECES, Board.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChessPackage.PIECE__OWNER:
				return getOwner();
			case ChessPackage.PIECE__TYPE:
				return getType();
			case ChessPackage.PIECE__SQUARE:
				if (resolve) return getSquare();
				return basicGetSquare();
			case ChessPackage.PIECE__BOARD:
				return getBoard();
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
			case ChessPackage.PIECE__OWNER:
				setOwner((Colors)newValue);
				return;
			case ChessPackage.PIECE__TYPE:
				setType((Types)newValue);
				return;
			case ChessPackage.PIECE__SQUARE:
				setSquare((Square)newValue);
				return;
			case ChessPackage.PIECE__BOARD:
				setBoard((Board)newValue);
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
			case ChessPackage.PIECE__OWNER:
				setOwner(OWNER_EDEFAULT);
				return;
			case ChessPackage.PIECE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ChessPackage.PIECE__SQUARE:
				setSquare((Square)null);
				return;
			case ChessPackage.PIECE__BOARD:
				setBoard((Board)null);
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
			case ChessPackage.PIECE__OWNER:
				return owner != OWNER_EDEFAULT;
			case ChessPackage.PIECE__TYPE:
				return type != TYPE_EDEFAULT;
			case ChessPackage.PIECE__SQUARE:
				return square != null;
			case ChessPackage.PIECE__BOARD:
				return getBoard() != null;
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
		result.append(" (owner: ");
		result.append(owner);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //PieceImpl
