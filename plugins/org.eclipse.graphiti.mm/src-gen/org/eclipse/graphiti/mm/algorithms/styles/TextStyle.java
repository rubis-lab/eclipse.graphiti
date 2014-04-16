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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#isUnderline <em>Underline</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getUnderlineStyle <em>Underline Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#isStrikeout <em>Strikeout</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getUnderlineColor <em>Underline Color</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getStrikeoutColor <em>Strikeout Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle()
 * @model
 * @generated
 * @since 0.10
 */
public interface TextStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Underline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Underline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Underline</em>' attribute.
	 * @see #setUnderline(boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_Underline()
	 * @model
	 * @generated
	 */
	boolean isUnderline();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#isUnderline <em>Underline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Underline</em>' attribute.
	 * @see #isUnderline()
	 * @generated
	 */
	void setUnderline(boolean value);

	/**
	 * Returns the value of the '<em><b>Underline Style</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Underline Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Underline Style</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle
	 * @see #setUnderlineStyle(UnderlineStyle)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_UnderlineStyle()
	 * @model
	 * @generated
	 */
	UnderlineStyle getUnderlineStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getUnderlineStyle <em>Underline Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Underline Style</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle
	 * @see #getUnderlineStyle()
	 * @generated
	 */
	void setUnderlineStyle(UnderlineStyle value);

	/**
	 * Returns the value of the '<em><b>Strikeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strikeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strikeout</em>' attribute.
	 * @see #setStrikeout(boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_Strikeout()
	 * @model
	 * @generated
	 */
	boolean isStrikeout();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#isStrikeout <em>Strikeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strikeout</em>' attribute.
	 * @see #isStrikeout()
	 * @generated
	 */
	void setStrikeout(boolean value);

	/**
	 * Returns the value of the '<em><b>Font</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font</em>' reference.
	 * @see #setFont(Font)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_Font()
	 * @model
	 * @generated
	 */
	Font getFont();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getFont <em>Font</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' reference.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(Font value);

	/**
	 * Returns the value of the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreground</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreground</em>' reference.
	 * @see #setForeground(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_Foreground()
	 * @model
	 * @generated
	 */
	Color getForeground();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getForeground <em>Foreground</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreground</em>' reference.
	 * @see #getForeground()
	 * @generated
	 */
	void setForeground(Color value);

	/**
	 * Returns the value of the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background</em>' reference.
	 * @see #setBackground(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_Background()
	 * @model
	 * @generated
	 */
	Color getBackground();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getBackground <em>Background</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' reference.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(Color value);

	/**
	 * Returns the value of the '<em><b>Underline Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Underline Color</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Underline Color</em>' reference.
	 * @see #setUnderlineColor(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_UnderlineColor()
	 * @model
	 * @generated
	 */
	Color getUnderlineColor();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getUnderlineColor <em>Underline Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Underline Color</em>' reference.
	 * @see #getUnderlineColor()
	 * @generated
	 */
	void setUnderlineColor(Color value);

	/**
	 * Returns the value of the '<em><b>Strikeout Color</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strikeout Color</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strikeout Color</em>' reference.
	 * @see #setStrikeoutColor(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getTextStyle_StrikeoutColor()
	 * @model
	 * @generated
	 */
	Color getStrikeoutColor();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.TextStyle#getStrikeoutColor <em>Strikeout Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strikeout Color</em>' reference.
	 * @see #getStrikeoutColor()
	 * @generated
	 */
	void setStrikeoutColor(Color value);

} // TextStyle
