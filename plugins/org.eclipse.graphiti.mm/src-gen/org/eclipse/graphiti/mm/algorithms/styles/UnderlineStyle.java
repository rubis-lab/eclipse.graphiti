/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2011 SAP AG.
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
package org.eclipse.graphiti.mm.algorithms.styles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Underline Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getUnderlineStyle()
 * @model
 * @generated
 * @since 0.10
 */
public enum UnderlineStyle implements Enumerator {
	/**
	 * The '<em><b>UNDERLINE SINGLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_SINGLE_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERLINE_SINGLE(0, "UNDERLINE_SINGLE", "UNDERLINE_SINGLE"),

	/**
	 * The '<em><b>UNDERLINE DOUBLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_DOUBLE_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERLINE_DOUBLE(1, "UNDERLINE_DOUBLE", "UNDERLINE_DOUBLE"),

	/**
	 * The '<em><b>UNDERLINE ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERLINE_ERROR(2, "UNDERLINE_ERROR", "UNDERLINE_ERROR"),

	/**
	 * The '<em><b>UNDERLINE SQUIGGLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_SQUIGGLE_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERLINE_SQUIGGLE(3, "UNDERLINE_SQUIGGLE", "UNDERLINE_SQUIGGLE");

	/**
	 * The '<em><b>UNDERLINE SINGLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERLINE SINGLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_SINGLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNDERLINE_SINGLE_VALUE = 0;

	/**
	 * The '<em><b>UNDERLINE DOUBLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERLINE DOUBLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_DOUBLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNDERLINE_DOUBLE_VALUE = 1;

	/**
	 * The '<em><b>UNDERLINE ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERLINE ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNDERLINE_ERROR_VALUE = 2;

	/**
	 * The '<em><b>UNDERLINE SQUIGGLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERLINE SQUIGGLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERLINE_SQUIGGLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNDERLINE_SQUIGGLE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Underline Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UnderlineStyle[] VALUES_ARRAY =
		new UnderlineStyle[] {
			UNDERLINE_SINGLE,
			UNDERLINE_DOUBLE,
			UNDERLINE_ERROR,
			UNDERLINE_SQUIGGLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Underline Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<UnderlineStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Underline Style</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnderlineStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnderlineStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Underline Style</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnderlineStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnderlineStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Underline Style</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnderlineStyle get(int value) {
		switch (value) {
			case UNDERLINE_SINGLE_VALUE: return UNDERLINE_SINGLE;
			case UNDERLINE_DOUBLE_VALUE: return UNDERLINE_DOUBLE;
			case UNDERLINE_ERROR_VALUE: return UNDERLINE_ERROR;
			case UNDERLINE_SQUIGGLE_VALUE: return UNDERLINE_SQUIGGLE;
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
	private UnderlineStyle(int value, String name, String literal) {
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
	
} //UnderlineStyle
