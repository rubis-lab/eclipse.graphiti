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
 * $Id: LinksPackage.java,v 1.2 2010/07/08 09:27:59 mgorning Exp $
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
	 * The feature id for the '<em><b>Business Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK__BUSINESS_OBJECTS = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Pictogram Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_LINK_FEATURE_COUNT = PictogramsPackage.PROPERTY_CONTAINER_FEATURE_COUNT + 2;

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
		 * The meta object literal for the '<em><b>Business Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_LINK__BUSINESS_OBJECTS = eINSTANCE.getPictogramLink_BusinessObjects();

	}

} //LinksPackage
