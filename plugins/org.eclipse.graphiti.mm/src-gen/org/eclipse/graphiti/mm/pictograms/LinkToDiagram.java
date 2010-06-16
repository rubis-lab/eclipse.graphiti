/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 *
 * $Id: LinkToDiagram.java,v 1.1 2010/06/16 13:24:53 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.graphiti.mm.datatypes.ViewPort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link To Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#isAsIcon <em>As Icon</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getViewport <em>Viewport</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getDiagram <em>Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getLinkToDiagram()
 * @model
 * @generated
 */
public interface LinkToDiagram extends Shape {
	/**
	 * Returns the value of the '<em><b>As Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Icon</em>' attribute.
	 * @see #setAsIcon(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getLinkToDiagram_AsIcon()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isAsIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#isAsIcon <em>As Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Icon</em>' attribute.
	 * @see #isAsIcon()
	 * @generated
	 */
	void setAsIcon(boolean value);

	/**
	 * Returns the value of the '<em><b>Viewport</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Viewport</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Viewport</em>' containment reference.
	 * @see #setViewport(ViewPort)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getLinkToDiagram_Viewport()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	ViewPort getViewport();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getViewport <em>Viewport</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewport</em>' containment reference.
	 * @see #getViewport()
	 * @generated
	 */
	void setViewport(ViewPort value);

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' reference.
	 * @see #setDiagram(Diagram)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getLinkToDiagram_Diagram()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Diagram getDiagram();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getDiagram <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(Diagram value);

} // LinkToDiagram
