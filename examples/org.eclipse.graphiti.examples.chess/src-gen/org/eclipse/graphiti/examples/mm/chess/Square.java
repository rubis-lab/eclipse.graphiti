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
 * A representation of the model object '<em><b>Square</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Square#getBoard <em>Board</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Square#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.graphiti.examples.mm.chess.Square#getPiece <em>Piece</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getSquare()
 * @model
 * @generated
 */
public interface Square extends EObject {

	/**
	 * Returns the value of the '<em><b>Board</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Board#getSquares <em>Squares</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Board</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Board</em>' container reference.
	 * @see #setBoard(Board)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getSquare_Board()
	 * @see org.eclipse.graphiti.examples.mm.chess.Board#getSquares
	 * @model opposite="squares" required="true" transient="false"
	 * @generated
	 */
	Board getBoard();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Square#getBoard <em>Board</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Board</em>' container reference.
	 * @see #getBoard()
	 * @generated
	 */
	void setBoard(Board value);

	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getSquare_Index()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Square#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

	/**
	 * Returns the value of the '<em><b>Piece</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getSquare <em>Square</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Piece</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Piece</em>' reference.
	 * @see #setPiece(Piece)
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getSquare_Piece()
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getSquare
	 * @model opposite="square"
	 * @generated
	 */
	Piece getPiece();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.examples.mm.chess.Square#getPiece <em>Piece</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Piece</em>' reference.
	 * @see #getPiece()
	 * @generated
	 */
	void setPiece(Piece value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	Ranks getRank();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	Files getFile();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Colors getColor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getOffsetX();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getOffsetY();
} // Square
