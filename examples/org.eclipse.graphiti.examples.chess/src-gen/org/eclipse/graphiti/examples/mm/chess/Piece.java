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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Piece</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Piece#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Piece#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Piece#getSquare <em>Square</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Piece#getBoard <em>Board</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getPiece()
 * @model
 * @generated
 */
public interface Piece extends EObject {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.examples.mm.chess.Colors}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' attribute.
	 * @see org.eclipse.graphiti.examples.mm.chess.Colors
	 * @see #setOwner(Colors)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getPiece_Owner()
	 * @model
	 * @generated
	 */
	Colors getOwner();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getOwner <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' attribute.
	 * @see org.eclipse.graphiti.examples.mm.chess.Colors
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(Colors value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.examples.mm.chess.Types}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.graphiti.examples.mm.chess.Types
	 * @see #setType(Types)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getPiece_Type()
	 * @model
	 * @generated
	 */
	Types getType();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.graphiti.examples.mm.chess.Types
	 * @see #getType()
	 * @generated
	 */
	void setType(Types value);

	/**
	 * Returns the value of the '<em><b>Square</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Square#getPiece <em>Piece</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Square</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Square</em>' reference.
	 * @see #setSquare(Square)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getPiece_Square()
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getPiece
	 * @model opposite="piece" required="true"
	 * @generated
	 */
	Square getSquare();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getSquare <em>Square</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Square</em>' reference.
	 * @see #getSquare()
	 * @generated
	 */
	void setSquare(Square value);

	/**
	 * Returns the value of the '<em><b>Board</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Board#getPieces <em>Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Board</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Board</em>' container reference.
	 * @see #setBoard(Board)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getPiece_Board()
	 * @see org.eclipse.graphiti.examples.mm.chess.Board#getPieces
	 * @model opposite="pieces" required="true" transient="false"
	 * @generated
	 */
	Board getBoard();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getBoard <em>Board</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Board</em>' container reference.
	 * @see #getBoard()
	 * @generated
	 */
	void setBoard(Board value);

} // Piece
