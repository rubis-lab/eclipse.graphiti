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
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Board</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl#getSquares <em>Squares</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl#getPieces <em>Pieces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoardImpl extends EObjectImpl implements Board {
	/**
	 * The cached value of the '{@link #getSquares() <em>Squares</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSquares()
	 * @generated
	 * @ordered
	 */
	protected EList<Square> squares;

	/**
	 * The cached value of the '{@link #getPieces() <em>Pieces</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected EList<Piece> pieces;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	protected BoardImpl() {
		super();

		// Add squares
		for (int i = 0; i < 64; i++) {
			Square square = ChessFactory.eINSTANCE.createSquare();

			// Index
			square.setIndex(i);

			// Compute rank
			int rank = (63 - square.getIndex()) / 8 + 1;
			square.setRank(Ranks.get(rank));

			// Compute file
			int file = (square.getIndex()) % 8 + 1;
			square.setFile(Files.get(file));

			// Compute color
			Colors color;
			if (file % 2 == 0) {
				if (rank % 2 == 0) {
					color = Colors.LIGHT;
				} else {
					color = Colors.DARK;
				}
			} else {
				if (rank % 2 == 0) {
					color = Colors.DARK;
				} else {
					color = Colors.LIGHT;
				}
			}
			square.setColor(color);

			getSquares().add(square);
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChessPackage.Literals.BOARD;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Square> getSquares() {
		if (squares == null) {
			squares = new EObjectContainmentWithInverseEList<Square>(Square.class, this, ChessPackage.BOARD__SQUARES, ChessPackage.SQUARE__BOARD);
		}
		return squares;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Piece> getPieces() {
		if (pieces == null) {
			pieces = new EObjectContainmentWithInverseEList<Piece>(Piece.class, this, ChessPackage.BOARD__PIECES, ChessPackage.PIECE__BOARD);
		}
		return pieces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public Square getSquare(Ranks rank, Files file) {
		for (Square square : getSquares()) {
			if (rank.equals(square.getRank()) && file.equals(square.getFile())) {
				return square;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSquares()).basicAdd(otherEnd, msgs);
			case ChessPackage.BOARD__PIECES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPieces()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return ((InternalEList<?>)getSquares()).basicRemove(otherEnd, msgs);
			case ChessPackage.BOARD__PIECES:
				return ((InternalEList<?>)getPieces()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return getSquares();
			case ChessPackage.BOARD__PIECES:
				return getPieces();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
			case ChessPackage.BOARD__PIECES:
				getPieces().clear();
				getPieces().addAll((Collection<? extends Piece>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				getSquares().clear();
				return;
			case ChessPackage.BOARD__PIECES:
				getPieces().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ChessPackage.BOARD__SQUARES:
				return squares != null && !squares.isEmpty();
			case ChessPackage.BOARD__PIECES:
				return pieces != null && !pieces.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // BoardImpl
