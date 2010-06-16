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
 * $Id: RenderingStyle.java,v 1.1 2010/06/16 13:24:53 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rendering Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.RenderingStyle#getPredefinedStyleId <em>Predefined Style Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getRenderingStyle()
 * @model
 * @generated
 */
public interface RenderingStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Predefined Style Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Style Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Style Id</em>' attribute.
	 * @see #setPredefinedStyleId(String)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getRenderingStyle_PredefinedStyleId()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getPredefinedStyleId();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.RenderingStyle#getPredefinedStyleId <em>Predefined Style Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predefined Style Id</em>' attribute.
	 * @see #getPredefinedStyleId()
	 * @generated
	 */
	void setPredefinedStyleId(String value);

} // RenderingStyle
