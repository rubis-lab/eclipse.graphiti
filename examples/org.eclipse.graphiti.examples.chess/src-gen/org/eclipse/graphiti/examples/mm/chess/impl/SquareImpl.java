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
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Square</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getBoard <em>Board</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getRank <em>Rank</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl#getPiece <em>Piece</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SquareImpl extends EObjectImpl implements Square {
	/**
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int INDEX_EDEFAULT = -1;
	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected int index = INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final Files FILE_EDEFAULT = Files.NONE;
	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected Files file = FILE_EDEFAULT;
	/**
	 * The default value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected static final Ranks RANK_EDEFAULT = Ranks.NONE;
	/**
	 * The cached value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected Ranks rank = RANK_EDEFAULT;
	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final Colors COLOR_EDEFAULT = Colors.NONE;
	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected Colors color = COLOR_EDEFAULT;
	/**
	 * The cached value of the '{@link #getPiece() <em>Piece</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPiece()
	 * @generated
	 * @ordered
	 */
	protected Piece piece;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SquareImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChessPackage.Literals.SQUARE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Board getBoard() {
		if (eContainerFeatureID() != ChessPackage.SQUARE__BOARD) return null;
		return (Board)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBoard(Board newBoard, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBoard, ChessPackage.SQUARE__BOARD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoard(Board newBoard) {
		if (newBoard != eInternalContainer() || (eContainerFeatureID() != ChessPackage.SQUARE__BOARD && newBoard != null)) {
			if (EcoreUtil.isAncestor(this, newBoard))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBoard != null)
				msgs = ((InternalEObject)newBoard).eInverseAdd(this, ChessPackage.BOARD__SQUARES, Board.class, msgs);
			msgs = basicSetBoard(newBoard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__BOARD, newBoard, newBoard));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(int newIndex) {
		int oldIndex = index;
		index = newIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__INDEX, oldIndex, index));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Piece getPiece() {
		if (piece != null && piece.eIsProxy()) {
			InternalEObject oldPiece = (InternalEObject)piece;
			piece = (Piece)eResolveProxy(oldPiece);
			if (piece != oldPiece) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ChessPackage.SQUARE__PIECE, oldPiece, piece));
			}
		}
		return piece;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Piece basicGetPiece() {
		return piece;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPiece(Piece newPiece, NotificationChain msgs) {
		Piece oldPiece = piece;
		piece = newPiece;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__PIECE, oldPiece, newPiece);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPiece(Piece newPiece) {
		if (newPiece != piece) {
			NotificationChain msgs = null;
			if (piece != null)
				msgs = ((InternalEObject)piece).eInverseRemove(this, ChessPackage.PIECE__SQUARE, Piece.class, msgs);
			if (newPiece != null)
				msgs = ((InternalEObject)newPiece).eInverseAdd(this, ChessPackage.PIECE__SQUARE, Piece.class, msgs);
			msgs = basicSetPiece(newPiece, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__PIECE, newPiece, newPiece));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Ranks getRank() {
		return rank;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRank(Ranks newRank) {
		Ranks oldRank = rank;
		rank = newRank == null ? RANK_EDEFAULT : newRank;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__RANK, oldRank, rank));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Files getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(Files newFile) {
		Files oldFile = file;
		file = newFile == null ? FILE_EDEFAULT : newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Colors getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(Colors newColor) {
		Colors oldColor = color;
		color = newColor == null ? COLOR_EDEFAULT : newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChessPackage.SQUARE__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public int getOffsetX() {
		return getFile().getValue() - 1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public int getOffsetY() {
		return 8 - getRank().getValue();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChessPackage.SQUARE__BOARD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBoard((Board)otherEnd, msgs);
			case ChessPackage.SQUARE__PIECE:
				if (piece != null)
					msgs = ((InternalEObject)piece).eInverseRemove(this, ChessPackage.PIECE__SQUARE, Piece.class, msgs);
				return basicSetPiece((Piece)otherEnd, msgs);
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
			case ChessPackage.SQUARE__BOARD:
				return basicSetBoard(null, msgs);
			case ChessPackage.SQUARE__PIECE:
				return basicSetPiece(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ChessPackage.SQUARE__BOARD:
				return eInternalContainer().eInverseRemove(this, ChessPackage.BOARD__SQUARES, Board.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChessPackage.SQUARE__BOARD:
				return getBoard();
			case ChessPackage.SQUARE__INDEX:
				return getIndex();
			case ChessPackage.SQUARE__FILE:
				return getFile();
			case ChessPackage.SQUARE__RANK:
				return getRank();
			case ChessPackage.SQUARE__COLOR:
				return getColor();
			case ChessPackage.SQUARE__PIECE:
				if (resolve) return getPiece();
				return basicGetPiece();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChessPackage.SQUARE__BOARD:
				setBoard((Board)newValue);
				return;
			case ChessPackage.SQUARE__INDEX:
				setIndex((Integer)newValue);
				return;
			case ChessPackage.SQUARE__FILE:
				setFile((Files)newValue);
				return;
			case ChessPackage.SQUARE__RANK:
				setRank((Ranks)newValue);
				return;
			case ChessPackage.SQUARE__COLOR:
				setColor((Colors)newValue);
				return;
			case ChessPackage.SQUARE__PIECE:
				setPiece((Piece)newValue);
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
			case ChessPackage.SQUARE__BOARD:
				setBoard((Board)null);
				return;
			case ChessPackage.SQUARE__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case ChessPackage.SQUARE__FILE:
				setFile(FILE_EDEFAULT);
				return;
			case ChessPackage.SQUARE__RANK:
				setRank(RANK_EDEFAULT);
				return;
			case ChessPackage.SQUARE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case ChessPackage.SQUARE__PIECE:
				setPiece((Piece)null);
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
			case ChessPackage.SQUARE__BOARD:
				return getBoard() != null;
			case ChessPackage.SQUARE__INDEX:
				return index != INDEX_EDEFAULT;
			case ChessPackage.SQUARE__FILE:
				return file != FILE_EDEFAULT;
			case ChessPackage.SQUARE__RANK:
				return rank != RANK_EDEFAULT;
			case ChessPackage.SQUARE__COLOR:
				return color != COLOR_EDEFAULT;
			case ChessPackage.SQUARE__PIECE:
				return piece != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (index: ");
		result.append(index);
		result.append(", file: ");
		result.append(file);
		result.append(", rank: ");
		result.append(rank);
		result.append(", color: ");
		result.append(color);
		result.append(')');
		return result.toString();
	}

} // SquareImpl
