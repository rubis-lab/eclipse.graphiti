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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.examples.mm.chess.ChessPackage#getTypes()
 * @model
 * @generated
 */
public enum Types implements Enumerator {
	/**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "none", "none"), /**
	 * The '<em><b>King</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KING_VALUE
	 * @generated
	 * @ordered
	 */
	KING(1, "king", "king"),

	/**
	 * The '<em><b>Queen</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #QUEEN_VALUE
	 * @generated
	 * @ordered
	 */
	QUEEN(2, "queen", "queen"),

	/**
	 * The '<em><b>Rook</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROOK_VALUE
	 * @generated
	 * @ordered
	 */
	ROOK(3, "rook", "rook"),

	/**
	 * The '<em><b>Knight</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KNIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	KNIGHT(4, "knight", "knight"),

	/**
	 * The '<em><b>Bishop</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BISHOP_VALUE
	 * @generated
	 * @ordered
	 */
	BISHOP(5, "bishop", "bishop"),

	/**
	 * The '<em><b>Pawn</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PAWN_VALUE
	 * @generated
	 * @ordered
	 */
	PAWN(6, "pawn", "pawn");

	/**
	 * The '<em><b>None</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model name="none"
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>King</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>King</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KING
	 * @model name="king"
	 * @generated
	 * @ordered
	 */
	public static final int KING_VALUE = 1;

	/**
	 * The '<em><b>Queen</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Queen</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #QUEEN
	 * @model name="queen"
	 * @generated
	 * @ordered
	 */
	public static final int QUEEN_VALUE = 2;

	/**
	 * The '<em><b>Rook</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rook</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROOK
	 * @model name="rook"
	 * @generated
	 * @ordered
	 */
	public static final int ROOK_VALUE = 3;

	/**
	 * The '<em><b>Knight</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Knight</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KNIGHT
	 * @model name="knight"
	 * @generated
	 * @ordered
	 */
	public static final int KNIGHT_VALUE = 4;

	/**
	 * The '<em><b>Bishop</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bishop</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BISHOP
	 * @model name="bishop"
	 * @generated
	 * @ordered
	 */
	public static final int BISHOP_VALUE = 5;

	/**
	 * The '<em><b>Pawn</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Pawn</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PAWN
	 * @model name="pawn"
	 * @generated
	 * @ordered
	 */
	public static final int PAWN_VALUE = 6;

	/**
	 * An array of all the '<em><b>Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Types[] VALUES_ARRAY =
		new Types[] {
			NONE,
			KING,
			QUEEN,
			ROOK,
			KNIGHT,
			BISHOP,
			PAWN,
		};

	/**
	 * A public read-only list of all the '<em><b>Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Types> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Types get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Types result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Types getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Types result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Types get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case KING_VALUE: return KING;
			case QUEEN_VALUE: return QUEEN;
			case ROOK_VALUE: return ROOK;
			case KNIGHT_VALUE: return KNIGHT;
			case BISHOP_VALUE: return BISHOP;
			case PAWN_VALUE: return PAWN;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Types(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //Types
