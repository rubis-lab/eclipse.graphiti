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
package org.eclipse.graphiti.mm.pictograms;


/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Advanced Anchor</b></em>'.
 * 
 * @since 0.9 <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.AdvancedAnchor#isUseAnchorLocationAsConnectionEndpoint <em>Use Anchor Location As Connection Endpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAdvancedAnchor()
 * @model abstract="true"
 * @generated
 */
public interface AdvancedAnchor extends Anchor {
	/**
	 * Returns the value of the '<em><b>Use Anchor Location As Connection Endpoint</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Anchor Location As Connection Endpoint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Anchor Location As Connection Endpoint</em>' attribute.
	 * @see #setUseAnchorLocationAsConnectionEndpoint(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAdvancedAnchor_UseAnchorLocationAsConnectionEndpoint()
	 * @model default="false" unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isUseAnchorLocationAsConnectionEndpoint();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.AdvancedAnchor#isUseAnchorLocationAsConnectionEndpoint <em>Use Anchor Location As Connection Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Anchor Location As Connection Endpoint</em>' attribute.
	 * @see #isUseAnchorLocationAsConnectionEndpoint()
	 * @generated
	 */
	void setUseAnchorLocationAsConnectionEndpoint(boolean value);

} // AdvancedAnchor
