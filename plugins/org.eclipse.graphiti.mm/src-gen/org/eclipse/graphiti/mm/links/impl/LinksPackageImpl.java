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
 * $Id: LinksPackageImpl.java,v 1.1 2010/06/16 13:25:09 mwenz Exp $
 */
package org.eclipse.graphiti.mm.links.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.datatypes.DatatypesPackage;

import org.eclipse.graphiti.mm.datatypes.impl.DatatypesPackageImpl;

import org.eclipse.graphiti.mm.links.DiagramLink;
import org.eclipse.graphiti.mm.links.LinksFactory;
import org.eclipse.graphiti.mm.links.LinksPackage;
import org.eclipse.graphiti.mm.links.PictogramLink;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

import org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LinksPackageImpl extends EPackageImpl implements LinksPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pictogramLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramLinkEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.graphiti.mm.links.LinksPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LinksPackageImpl() {
		super(eNS_URI, LinksFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link LinksPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LinksPackage init() {
		if (isInited) return (LinksPackage)EPackage.Registry.INSTANCE.getEPackage(LinksPackage.eNS_URI);

		// Obtain or create and register package
		LinksPackageImpl theLinksPackage = (LinksPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LinksPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LinksPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DatatypesPackageImpl theDatatypesPackage = (DatatypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) : DatatypesPackage.eINSTANCE);
		PictogramsPackageImpl thePictogramsPackage = (PictogramsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) instanceof PictogramsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) : PictogramsPackage.eINSTANCE);

		// Create package meta-data objects
		theLinksPackage.createPackageContents();
		theDatatypesPackage.createPackageContents();
		thePictogramsPackage.createPackageContents();

		// Initialize created meta-data
		theLinksPackage.initializePackageContents();
		theDatatypesPackage.initializePackageContents();
		thePictogramsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLinksPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LinksPackage.eNS_URI, theLinksPackage);
		return theLinksPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPictogramLink() {
		return pictogramLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPictogramLink_PictogramElement() {
		return (EReference)pictogramLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPictogramLink_DiagramLink() {
		return (EReference)pictogramLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPictogramLink_BusinessObjects() {
		return (EReference)pictogramLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramLink() {
		return diagramLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Diagram() {
		return (EReference)diagramLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_PictogramLinks() {
		return (EReference)diagramLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinksFactory getLinksFactory() {
		return (LinksFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		pictogramLinkEClass = createEClass(PICTOGRAM_LINK);
		createEReference(pictogramLinkEClass, PICTOGRAM_LINK__PICTOGRAM_ELEMENT);
		createEReference(pictogramLinkEClass, PICTOGRAM_LINK__DIAGRAM_LINK);
		createEReference(pictogramLinkEClass, PICTOGRAM_LINK__BUSINESS_OBJECTS);

		diagramLinkEClass = createEClass(DIAGRAM_LINK);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__DIAGRAM);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__PICTOGRAM_LINKS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PictogramsPackage thePictogramsPackage = (PictogramsPackage)EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		pictogramLinkEClass.getESuperTypes().add(thePictogramsPackage.getPropertyContainer());

		// Initialize classes and features; add operations and parameters
		initEClass(pictogramLinkEClass, PictogramLink.class, "PictogramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPictogramLink_PictogramElement(), thePictogramsPackage.getPictogramElement(), thePictogramsPackage.getPictogramElement_Link(), "pictogramElement", null, 0, 1, PictogramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPictogramLink_DiagramLink(), this.getDiagramLink(), this.getDiagramLink_PictogramLinks(), "diagramLink", null, 1, 1, PictogramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPictogramLink_BusinessObjects(), ecorePackage.getEObject(), null, "businessObjects", null, 0, -1, PictogramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramLinkEClass, DiagramLink.class, "DiagramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagramLink_Diagram(), thePictogramsPackage.getDiagram(), null, "diagram", null, 0, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDiagramLink_PictogramLinks(), this.getPictogramLink(), this.getPictogramLink_DiagramLink(), "pictogramLinks", null, 0, -1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //LinksPackageImpl
