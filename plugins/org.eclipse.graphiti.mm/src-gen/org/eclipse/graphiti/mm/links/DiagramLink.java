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
 * $Id: DiagramLink.java,v 1.1 2010/06/16 13:24:54 mwenz Exp $
 */
package org.eclipse.graphiti.mm.links;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.links.DiagramLink#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.links.DiagramLink#getPictogramLinks <em>Pictogram Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.links.LinksPackage#getDiagramLink()
 * @model
 * @generated
 */
public interface DiagramLink extends EObject {
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
	 * @see org.eclipse.graphiti.mm.links.LinksPackage#getDiagramLink_Diagram()
	 * @model ordered="false"
	 * @generated
	 */
	Diagram getDiagram();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.links.DiagramLink#getDiagram <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(Diagram value);

	/**
	 * Returns the value of the '<em><b>Pictogram Links</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.links.PictogramLink}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.links.PictogramLink#getDiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pictogram Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pictogram Links</em>' reference list.
	 * @see org.eclipse.graphiti.mm.links.LinksPackage#getDiagramLink_PictogramLinks()
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getDiagramLink
	 * @model opposite="diagramLink" ordered="false"
	 * @generated
	 */
	EList<PictogramLink> getPictogramLinks();

} // DiagramLink
