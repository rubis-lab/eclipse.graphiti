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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.examples.mm.chess.ChessFactory
 * @model kind="package"
 * @generated
 */
public interface ChessPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "chess";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/graphiti/examples/chess";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "chess";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ChessPackage eINSTANCE = org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl <em>Board</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getBoard()
	 * @generated
	 */
	int BOARD = 0;

	/**
	 * The feature id for the '<em><b>Squares</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOARD__SQUARES = 0;

	/**
	 * The feature id for the '<em><b>Pieces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOARD__PIECES = 1;

	/**
	 * The number of structural features of the '<em>Board</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOARD_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl <em>Square</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getSquare()
	 * @generated
	 */
	int SQUARE = 1;

	/**
	 * The feature id for the '<em><b>Board</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__BOARD = 0;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__INDEX = 1;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__FILE = 2;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__RANK = 3;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__COLOR = 4;

	/**
	 * The feature id for the '<em><b>Piece</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE__PIECE = 5;

	/**
	 * The number of structural features of the '<em>Square</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE_FEATURE_COUNT = 6;


	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl <em>Piece</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getPiece()
	 * @generated
	 */
	int PIECE = 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIECE__OWNER = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIECE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Square</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIECE__SQUARE = 2;

	/**
	 * The feature id for the '<em><b>Board</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIECE__BOARD = 3;

	/**
	 * The number of structural features of the '<em>Piece</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIECE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Ranks <em>Ranks</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Ranks
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getRanks()
	 * @generated
	 */
	int RANKS = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Files <em>Files</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Files
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getFiles()
	 * @generated
	 */
	int FILES = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Colors <em>Colors</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Colors
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getColors()
	 * @generated
	 */
	int COLORS = 5;


	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Types <em>Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Types
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getTypes()
	 * @generated
	 */
	int TYPES = 6;


	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.examples.mm.chess.Board <em>Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Board</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Board
	 * @generated
	 */
	EClass getBoard();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.examples.mm.chess.Board#getSquares <em>Squares</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Squares</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Board#getSquares()
	 * @see #getBoard()
	 * @generated
	 */
	EReference getBoard_Squares();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.examples.mm.chess.Board#getPieces <em>Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pieces</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Board#getPieces()
	 * @see #getBoard()
	 * @generated
	 */
	EReference getBoard_Pieces();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.examples.mm.chess.Square <em>Square</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Square</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square
	 * @generated
	 */
	EClass getSquare();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.examples.mm.chess.Square#getBoard <em>Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Board</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getBoard()
	 * @see #getSquare()
	 * @generated
	 */
	EReference getSquare_Board();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Square#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getIndex()
	 * @see #getSquare()
	 * @generated
	 */
	EAttribute getSquare_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Square#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getFile()
	 * @see #getSquare()
	 * @generated
	 */
	EAttribute getSquare_File();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Square#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getRank()
	 * @see #getSquare()
	 * @generated
	 */
	EAttribute getSquare_Rank();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Square#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getColor()
	 * @see #getSquare()
	 * @generated
	 */
	EAttribute getSquare_Color();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.examples.mm.chess.Square#getPiece <em>Piece</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Piece</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Square#getPiece()
	 * @see #getSquare()
	 * @generated
	 */
	EReference getSquare_Piece();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.examples.mm.chess.Piece <em>Piece</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Piece</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece
	 * @generated
	 */
	EClass getPiece();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getOwner()
	 * @see #getPiece()
	 * @generated
	 */
	EAttribute getPiece_Owner();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getType()
	 * @see #getPiece()
	 * @generated
	 */
	EAttribute getPiece_Type();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getSquare <em>Square</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Square</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getSquare()
	 * @see #getPiece()
	 * @generated
	 */
	EReference getPiece_Square();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.examples.mm.chess.Piece#getBoard <em>Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Board</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Piece#getBoard()
	 * @see #getPiece()
	 * @generated
	 */
	EReference getPiece_Board();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.examples.mm.chess.Ranks <em>Ranks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Ranks</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Ranks
	 * @generated
	 */
	EEnum getRanks();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.examples.mm.chess.Files <em>Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Files</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Files
	 * @generated
	 */
	EEnum getFiles();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.examples.mm.chess.Colors <em>Colors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Colors</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Colors
	 * @generated
	 */
	EEnum getColors();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.examples.mm.chess.Types <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Types</em>'.
	 * @see org.eclipse.graphiti.examples.mm.chess.Types
	 * @generated
	 */
	EEnum getTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ChessFactory getChessFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl <em>Board</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.BoardImpl
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getBoard()
		 * @generated
		 */
		EClass BOARD = eINSTANCE.getBoard();

		/**
		 * The meta object literal for the '<em><b>Squares</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOARD__SQUARES = eINSTANCE.getBoard_Squares();

		/**
		 * The meta object literal for the '<em><b>Pieces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOARD__PIECES = eINSTANCE.getBoard_Pieces();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl <em>Square</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.SquareImpl
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getSquare()
		 * @generated
		 */
		EClass SQUARE = eINSTANCE.getSquare();

		/**
		 * The meta object literal for the '<em><b>Board</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SQUARE__BOARD = eINSTANCE.getSquare_Board();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQUARE__INDEX = eINSTANCE.getSquare_Index();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQUARE__FILE = eINSTANCE.getSquare_File();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQUARE__RANK = eINSTANCE.getSquare_Rank();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQUARE__COLOR = eINSTANCE.getSquare_Color();

		/**
		 * The meta object literal for the '<em><b>Piece</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SQUARE__PIECE = eINSTANCE.getSquare_Piece();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl <em>Piece</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.PieceImpl
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getPiece()
		 * @generated
		 */
		EClass PIECE = eINSTANCE.getPiece();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIECE__OWNER = eINSTANCE.getPiece_Owner();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIECE__TYPE = eINSTANCE.getPiece_Type();

		/**
		 * The meta object literal for the '<em><b>Square</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIECE__SQUARE = eINSTANCE.getPiece_Square();

		/**
		 * The meta object literal for the '<em><b>Board</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIECE__BOARD = eINSTANCE.getPiece_Board();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.Ranks <em>Ranks</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.Ranks
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getRanks()
		 * @generated
		 */
		EEnum RANKS = eINSTANCE.getRanks();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.Files <em>Files</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.Files
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getFiles()
		 * @generated
		 */
		EEnum FILES = eINSTANCE.getFiles();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.Colors <em>Colors</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.Colors
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getColors()
		 * @generated
		 */
		EEnum COLORS = eINSTANCE.getColors();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.examples.mm.chess.Types <em>Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.examples.mm.chess.Types
		 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getTypes()
		 * @generated
		 */
		EEnum TYPES = eINSTANCE.getTypes();

	}

} //ChessPackage
