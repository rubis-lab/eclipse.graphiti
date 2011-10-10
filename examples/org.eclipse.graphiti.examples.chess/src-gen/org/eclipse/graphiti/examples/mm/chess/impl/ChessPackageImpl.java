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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.ChessFactory;
import org.eclipse.graphiti.examples.mm.chess.ChessPackage;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.examples.mm.chess.Types;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ChessPackageImpl extends EPackageImpl implements ChessPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass squareEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pieceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ranksEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum filesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum colorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typesEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ChessPackageImpl() {
		super(eNS_URI, ChessFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ChessPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ChessPackage init() {
		if (isInited) return (ChessPackage)EPackage.Registry.INSTANCE.getEPackage(ChessPackage.eNS_URI);

		// Obtain or create and register package
		ChessPackageImpl theChessPackage = (ChessPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ChessPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ChessPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theChessPackage.createPackageContents();

		// Initialize created meta-data
		theChessPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theChessPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ChessPackage.eNS_URI, theChessPackage);
		return theChessPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBoard() {
		return boardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBoard_Squares() {
		return (EReference)boardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBoard_Pieces() {
		return (EReference)boardEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSquare() {
		return squareEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSquare_Board() {
		return (EReference)squareEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSquare_Index() {
		return (EAttribute)squareEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSquare_File() {
		return (EAttribute)squareEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSquare_Rank() {
		return (EAttribute)squareEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSquare_Color() {
		return (EAttribute)squareEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSquare_Piece() {
		return (EReference)squareEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPiece() {
		return pieceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPiece_Owner() {
		return (EAttribute)pieceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPiece_Type() {
		return (EAttribute)pieceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPiece_Square() {
		return (EReference)pieceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPiece_Board() {
		return (EReference)pieceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRanks() {
		return ranksEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFiles() {
		return filesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getColors() {
		return colorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypes() {
		return typesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChessFactory getChessFactory() {
		return (ChessFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		boardEClass = createEClass(BOARD);
		createEReference(boardEClass, BOARD__SQUARES);
		createEReference(boardEClass, BOARD__PIECES);

		squareEClass = createEClass(SQUARE);
		createEReference(squareEClass, SQUARE__BOARD);
		createEAttribute(squareEClass, SQUARE__INDEX);
		createEAttribute(squareEClass, SQUARE__FILE);
		createEAttribute(squareEClass, SQUARE__RANK);
		createEAttribute(squareEClass, SQUARE__COLOR);
		createEReference(squareEClass, SQUARE__PIECE);

		pieceEClass = createEClass(PIECE);
		createEAttribute(pieceEClass, PIECE__OWNER);
		createEAttribute(pieceEClass, PIECE__TYPE);
		createEReference(pieceEClass, PIECE__SQUARE);
		createEReference(pieceEClass, PIECE__BOARD);

		// Create enums
		ranksEEnum = createEEnum(RANKS);
		filesEEnum = createEEnum(FILES);
		colorsEEnum = createEEnum(COLORS);
		typesEEnum = createEEnum(TYPES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(boardEClass, Board.class, "Board", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBoard_Squares(), this.getSquare(), this.getSquare_Board(), "squares", null, 64, 64, Board.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBoard_Pieces(), this.getPiece(), this.getPiece_Board(), "pieces", null, 0, 32, Board.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(boardEClass, this.getSquare(), "getSquare", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRanks(), "rank", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFiles(), "file", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(squareEClass, Square.class, "Square", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSquare_Board(), this.getBoard(), this.getBoard_Squares(), "board", null, 1, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSquare_Index(), ecorePackage.getEInt(), "index", "-1", 1, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSquare_File(), this.getFiles(), "file", null, 1, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSquare_Rank(), this.getRanks(), "rank", "", 1, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSquare_Color(), this.getColors(), "color", null, 1, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSquare_Piece(), this.getPiece(), this.getPiece_Square(), "piece", null, 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(squareEClass, ecorePackage.getEInt(), "getOffsetX", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(squareEClass, ecorePackage.getEInt(), "getOffsetY", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(pieceEClass, Piece.class, "Piece", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPiece_Owner(), this.getColors(), "owner", null, 0, 1, Piece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPiece_Type(), this.getTypes(), "type", null, 0, 1, Piece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPiece_Square(), this.getSquare(), this.getSquare_Piece(), "square", null, 1, 1, Piece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPiece_Board(), this.getBoard(), this.getBoard_Pieces(), "board", null, 1, 1, Piece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(ranksEEnum, Ranks.class, "Ranks");
		addEEnumLiteral(ranksEEnum, Ranks.NONE);
		addEEnumLiteral(ranksEEnum, Ranks.ONE);
		addEEnumLiteral(ranksEEnum, Ranks.TWO);
		addEEnumLiteral(ranksEEnum, Ranks.THREE);
		addEEnumLiteral(ranksEEnum, Ranks.FOUR);
		addEEnumLiteral(ranksEEnum, Ranks.FIVE);
		addEEnumLiteral(ranksEEnum, Ranks.SIX);
		addEEnumLiteral(ranksEEnum, Ranks.SEVEN);
		addEEnumLiteral(ranksEEnum, Ranks.EIGHT);

		initEEnum(filesEEnum, Files.class, "Files");
		addEEnumLiteral(filesEEnum, Files.NONE);
		addEEnumLiteral(filesEEnum, Files.A);
		addEEnumLiteral(filesEEnum, Files.B);
		addEEnumLiteral(filesEEnum, Files.C);
		addEEnumLiteral(filesEEnum, Files.D);
		addEEnumLiteral(filesEEnum, Files.E);
		addEEnumLiteral(filesEEnum, Files.F);
		addEEnumLiteral(filesEEnum, Files.G);
		addEEnumLiteral(filesEEnum, Files.H);

		initEEnum(colorsEEnum, Colors.class, "Colors");
		addEEnumLiteral(colorsEEnum, Colors.NONE);
		addEEnumLiteral(colorsEEnum, Colors.LIGHT);
		addEEnumLiteral(colorsEEnum, Colors.DARK);

		initEEnum(typesEEnum, Types.class, "Types");
		addEEnumLiteral(typesEEnum, Types.NONE);
		addEEnumLiteral(typesEEnum, Types.KING);
		addEEnumLiteral(typesEEnum, Types.QUEEN);
		addEEnumLiteral(typesEEnum, Types.ROOK);
		addEEnumLiteral(typesEEnum, Types.KNIGHT);
		addEEnumLiteral(typesEEnum, Types.BISHOP);
		addEEnumLiteral(typesEEnum, Types.PAWN);

		// Create resource
		createResource(eNS_URI);
	}

} //ChessPackageImpl
