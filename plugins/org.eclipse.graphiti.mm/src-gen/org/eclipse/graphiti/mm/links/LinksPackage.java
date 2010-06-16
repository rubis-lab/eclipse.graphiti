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
 * $Id: LinksPackage.java,v 1.1 2010/06/16 13:24:54 mwenz Exp $
 */
package org.eclipse.graphiti.mm.links;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

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
 * @see org.eclipse.graphiti.mm.links.LinksFactory
 * @model kind="package"
 * @generated
 */
public interface LinksPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "links";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/graphiti/links";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "li";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LinksPackage eINSTANCE = org.eclipse.graphiti.mm.links.impl.LinksPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl <em>Pictogram Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl
	 * @see org.eclipse.graphiti.mm.links.impl.LinksPackageImpl#getPictogramLink()
	 * @generated
	 */
	int PICTOGRAM_LINK = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK__PROPERTIES = PictogramsPackage.PROPERTY_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK__PICTOGRAM_ELEMENT = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Diagram Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK__DIAGRAM_LINK = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Business Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK__BUSINESS_OBJECTS = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Pictogram Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK_FEATURE_COUNT = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl <em>Diagram Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl
	 * @see org.eclipse.graphiti.mm.links.impl.LinksPackageImpl#getDiagramLink()
	 * @generated
	 */
	int DIAGRAM_LINK = 1;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Pictogram Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__PICTOGRAM_LINKS = 1;

	/**
	 * The number of structural features of the '<em>Diagram Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.links.PictogramLink <em>Pictogram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pictogram Link</em>'.
	 * @see org.eclipse.graphiti.mm.links.PictogramLink
	 * @generated
	 */
	EClass getPictogramLink();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.links.PictogramLink#getPictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pictogram Element</em>'.
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getPictogramElement()
	 * @see #getPictogramLink()
	 * @generated
	 */
	EReference getPictogramLink_PictogramElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.links.PictogramLink#getDiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram Link</em>'.
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getDiagramLink()
	 * @see #getPictogramLink()
	 * @generated
	 */
	EReference getPictogramLink_DiagramLink();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.graphiti.mm.links.PictogramLink#getBusinessObjects <em>Business Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Business Objects</em>'.
	 * @see org.eclipse.graphiti.mm.links.PictogramLink#getBusinessObjects()
	 * @see #getPictogramLink()
	 * @generated
	 */
	EReference getPictogramLink_BusinessObjects();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.links.DiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Link</em>'.
	 * @see org.eclipse.graphiti.mm.links.DiagramLink
	 * @generated
	 */
	EClass getDiagramLink();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.links.DiagramLink#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram</em>'.
	 * @see org.eclipse.graphiti.mm.links.DiagramLink#getDiagram()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Diagram();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.graphiti.mm.links.DiagramLink#getPictogramLinks <em>Pictogram Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Pictogram Links</em>'.
	 * @see org.eclipse.graphiti.mm.links.DiagramLink#getPictogramLinks()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_PictogramLinks();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LinksFactory getLinksFactory();

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
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl <em>Pictogram Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl
		 * @see org.eclipse.graphiti.mm.links.impl.LinksPackageImpl#getPictogramLink()
		 * @generated
		 */
		EClass PICTOGRAM_LINK = eINSTANCE.getPictogramLink();

		/**
		 * The meta object literal for the '<em><b>Pictogram Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_LINK__PICTOGRAM_ELEMENT = eINSTANCE.getPictogramLink_PictogramElement();

		/**
		 * The meta object literal for the '<em><b>Diagram Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_LINK__DIAGRAM_LINK = eINSTANCE.getPictogramLink_DiagramLink();

		/**
		 * The meta object literal for the '<em><b>Business Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_LINK__BUSINESS_OBJECTS = eINSTANCE.getPictogramLink_BusinessObjects();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl <em>Diagram Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl
		 * @see org.eclipse.graphiti.mm.links.impl.LinksPackageImpl#getDiagramLink()
		 * @generated
		 */
		EClass DIAGRAM_LINK = eINSTANCE.getDiagramLink();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK__DIAGRAM = eINSTANCE.getDiagramLink_Diagram();

		/**
		 * The meta object literal for the '<em><b>Pictogram Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK__PICTOGRAM_LINKS = eINSTANCE.getDiagramLink_PictogramLinks();

	}

} //LinksPackage
