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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Font</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Font#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Font#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Font#isItalic <em>Italic</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Font#isBold <em>Bold</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getFont()
 * @model
 * @generated
 */
public interface Font extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getFont_Name()
	 * @model unique="false" required="true" ordered="false"
	 *        suppressedSetVisibility="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getFont_Size()
	 * @model unique="false" required="true" ordered="false"
	 *        suppressedSetVisibility="true"
	 * @generated
	 */
	int getSize();

	/**
	 * Returns the value of the '<em><b>Italic</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Italic</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Italic</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getFont_Italic()
	 * @model unique="false" required="true" ordered="false"
	 *        suppressedSetVisibility="true"
	 * @generated
	 */
	boolean isItalic();

	/**
	 * Returns the value of the '<em><b>Bold</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bold</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Bold</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getFont_Bold()
	 * @model unique="false" required="true" ordered="false"
	 *        suppressedSetVisibility="true"
	 * @generated
	 */
	boolean isBold();

} // Font
