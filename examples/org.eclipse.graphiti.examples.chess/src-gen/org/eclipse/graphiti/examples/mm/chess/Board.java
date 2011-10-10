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
package org.eclipse.graphiti.examples.mm.chess;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Board</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Board#getSquares <em>Squares</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Board#getPieces <em>Pieces</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getBoard()
 * @model
 * @generated
 */
public interface Board extends EObject {
	/**
	 * Returns the value of the '<em><b>Squares</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.examples.mm.chess.Square}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Square#getBoard <em>Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Squares</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Squares</em>' containment reference list.
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getBoard_Squares()
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getBoard
	 * @model opposite="board" containment="true" lower="64" upper="64"
	 * @generated
	 */
	EList<Square> getSquares();

	/**
	 * Returns the value of the '<em><b>Pieces</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.examples.mm.chess.Piece}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getBoard <em>Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pieces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pieces</em>' containment reference list.
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getBoard_Pieces()
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getBoard
	 * @model opposite="board" containment="true" upper="32"
	 * @generated
	 */
	EList<Piece> getPieces();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" rankRequired="true" fileRequired="true"
	 * @generated
	 */
	Square getSquare(Ranks rank, Files file);

} // Board
