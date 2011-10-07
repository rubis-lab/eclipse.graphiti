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
	 * The number of structural features of the '<em>Board</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOARD_FEATURE_COUNT = 1;

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
	 * The number of structural features of the '<em>Square</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQUARE_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Ranks <em>Ranks</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Ranks
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getRanks()
	 * @generated
	 */
	int RANKS = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Files <em>Files</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Files
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getFiles()
	 * @generated
	 */
	int FILES = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.examples.mm.chess.Colors <em>Colors</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.examples.mm.chess.Colors
	 * @see org.eclipse.graphiti.examples.mm.chess.impl.ChessPackageImpl#getColors()
	 * @generated
	 */
	int COLORS = 4;


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

	}

} //ChessPackage
