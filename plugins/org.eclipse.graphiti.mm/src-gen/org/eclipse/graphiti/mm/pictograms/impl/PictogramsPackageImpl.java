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
 * $Id: PictogramsPackageImpl.java,v 1.1 2010/06/16 13:24:59 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.datatypes.DatatypesPackage;

import org.eclipse.graphiti.mm.datatypes.impl.DatatypesPackageImpl;

import org.eclipse.graphiti.mm.links.LinksPackage;

import org.eclipse.graphiti.mm.links.impl.LinksPackageImpl;

import org.eclipse.graphiti.mm.pictograms.AbstractStyle;
import org.eclipse.graphiti.mm.pictograms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.DirectConnection;
import org.eclipse.graphiti.mm.pictograms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.FanConnection;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.Font;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.pictograms.HorizontalConnection;
import org.eclipse.graphiti.mm.pictograms.Image;
import org.eclipse.graphiti.mm.pictograms.LineStyle;
import org.eclipse.graphiti.mm.pictograms.LinkToDiagram;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.MultiText;
import org.eclipse.graphiti.mm.pictograms.Orientation;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Property;
import org.eclipse.graphiti.mm.pictograms.PropertyContainer;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RenderingStyle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Style;
import org.eclipse.graphiti.mm.pictograms.StyleContainer;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.mm.pictograms.VerticalConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PictogramsPackageImpl extends EPackageImpl implements PictogramsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shapeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerShapeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicsAlgorithmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polylineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ellipseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pictogramElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anchorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass horizontalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass verticalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anchorContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixPointAnchorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boxRelativeAnchorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chopboxAnchorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkToDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass freeFormConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manhattanConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polygonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rectangleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roundedRectangleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fontEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass renderingStyleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fanConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformGraphicsAlgorithmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicsAlgorithmContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractStyleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum lineStyleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationEEnum = null;

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
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PictogramsPackageImpl() {
		super(eNS_URI, PictogramsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PictogramsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PictogramsPackage init() {
		if (isInited) return (PictogramsPackage)EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);

		// Obtain or create and register package
		PictogramsPackageImpl thePictogramsPackage = (PictogramsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PictogramsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PictogramsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DatatypesPackageImpl theDatatypesPackage = (DatatypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) : DatatypesPackage.eINSTANCE);
		LinksPackageImpl theLinksPackage = (LinksPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LinksPackage.eNS_URI) instanceof LinksPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LinksPackage.eNS_URI) : LinksPackage.eINSTANCE);

		// Create package meta-data objects
		thePictogramsPackage.createPackageContents();
		theDatatypesPackage.createPackageContents();
		theLinksPackage.createPackageContents();

		// Initialize created meta-data
		thePictogramsPackage.initializePackageContents();
		theDatatypesPackage.initializePackageContents();
		theLinksPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePictogramsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PictogramsPackage.eNS_URI, thePictogramsPackage);
		return thePictogramsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShape() {
		return shapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getShape_Container() {
		return (EReference)shapeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainerShape() {
		return containerShapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerShape_Children() {
		return (EReference)containerShapeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagram() {
		return diagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_GridUnit() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_DiagramTypeId() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Connections() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_Name() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_SnapToGrid() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_ShowGuides() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Colors() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicsAlgorithm() {
		return graphicsAlgorithmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_GraphicsAlgorithmChildren() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_ParentGraphicsAlgorithm() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_PictogramElement() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Width() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Height() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_X() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Y() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_Style() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolyline() {
		return polylineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolyline_Points() {
		return (EReference)polylineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEllipse() {
		return ellipseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPictogramElement() {
		return pictogramElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPictogramElement_Visible() {
		return (EAttribute)pictogramElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPictogramElement_GraphicsAlgorithm() {
		return (EReference)pictogramElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPictogramElement_Active() {
		return (EAttribute)pictogramElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPictogramElement_Link() {
		return (EReference)pictogramElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Start() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_End() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Parent() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_ConnectionDecorators() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnchor() {
		return anchorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnchor_Parent() {
		return (EReference)anchorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnchor_OutgoingConnections() {
		return (EReference)anchorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnchor_IncomingConnections() {
		return (EReference)anchorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnchor_ReferencedGraphicsAlgorithm() {
		return (EReference)anchorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHorizontalConnection() {
		return horizontalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVerticalConnection() {
		return verticalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnchorContainer() {
		return anchorContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnchorContainer_Anchors() {
		return (EReference)anchorContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixPointAnchor() {
		return fixPointAnchorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixPointAnchor_Location() {
		return (EReference)fixPointAnchorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBoxRelativeAnchor() {
		return boxRelativeAnchorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBoxRelativeAnchor_RelativeWidth() {
		return (EAttribute)boxRelativeAnchorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBoxRelativeAnchor_RelativeHeight() {
		return (EAttribute)boxRelativeAnchorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChopboxAnchor() {
		return chopboxAnchorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkToDiagram() {
		return linkToDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLinkToDiagram_AsIcon() {
		return (EAttribute)linkToDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkToDiagram_Viewport() {
		return (EReference)linkToDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkToDiagram_Diagram() {
		return (EReference)linkToDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Key() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getText() {
		return textEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionDecorator() {
		return connectionDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDecorator_LocationRelative() {
		return (EAttribute)connectionDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDecorator_Location() {
		return (EAttribute)connectionDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionDecorator_Connection() {
		return (EReference)connectionDecoratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFreeFormConnection() {
		return freeFormConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFreeFormConnection_Bendpoints() {
		return (EReference)freeFormConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDirectConnection() {
		return directConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManhattanConnection() {
		return manhattanConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolygon() {
		return polygonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRectangle() {
		return rectangleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoundedRectangle() {
		return roundedRectangleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoundedRectangle_CornerHeight() {
		return (EAttribute)roundedRectangleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoundedRectangle_CornerWidth() {
		return (EAttribute)roundedRectangleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFont() {
		return fontEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Name() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Size() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Italic() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Bold() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRenderingStyle() {
		return renderingStyleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRenderingStyle_PredefinedStyleId() {
		return (EAttribute)renderingStyleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Id() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_StretchH() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_StretchV() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Proportional() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFanConnection() {
		return fanConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformGraphicsAlgorithm() {
		return platformGraphicsAlgorithmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlatformGraphicsAlgorithm_Id() {
		return (EAttribute)platformGraphicsAlgorithmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicsAlgorithmContainer() {
		return graphicsAlgorithmContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractText() {
		return abstractTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractText_Font() {
		return (EReference)abstractTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_HorizontalAlignment() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_VerticalAlignment() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_Angle() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_Value() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiText() {
		return multiTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyContainer() {
		return propertyContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyContainer_Properties() {
		return (EReference)propertyContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyleContainer() {
		return styleContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyleContainer_Styles() {
		return (EReference)styleContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyle() {
		return styleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Id() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Description() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyle_Font() {
		return (EReference)styleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_HorizontalAlignment() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_VerticalAlignment() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Angle() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_StretchH() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_StretchV() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Proportional() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyle_StyleContainer() {
		return (EReference)styleEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractStyle() {
		return abstractStyleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_Background() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_Foreground() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineWidth() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineStyle() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_Filled() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineVisible() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_RenderingStyle() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_Transparency() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLineStyle() {
		return lineStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientation() {
		return orientationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsFactory getPictogramsFactory() {
		return (PictogramsFactory)getEFactoryInstance();
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
		shapeEClass = createEClass(SHAPE);
		createEReference(shapeEClass, SHAPE__CONTAINER);

		containerShapeEClass = createEClass(CONTAINER_SHAPE);
		createEReference(containerShapeEClass, CONTAINER_SHAPE__CHILDREN);

		diagramEClass = createEClass(DIAGRAM);
		createEAttribute(diagramEClass, DIAGRAM__GRID_UNIT);
		createEAttribute(diagramEClass, DIAGRAM__DIAGRAM_TYPE_ID);
		createEReference(diagramEClass, DIAGRAM__CONNECTIONS);
		createEAttribute(diagramEClass, DIAGRAM__NAME);
		createEAttribute(diagramEClass, DIAGRAM__SNAP_TO_GRID);
		createEAttribute(diagramEClass, DIAGRAM__SHOW_GUIDES);
		createEReference(diagramEClass, DIAGRAM__COLORS);

		graphicsAlgorithmEClass = createEClass(GRAPHICS_ALGORITHM);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__WIDTH);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__HEIGHT);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__X);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__Y);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__STYLE);

		polylineEClass = createEClass(POLYLINE);
		createEReference(polylineEClass, POLYLINE__POINTS);

		ellipseEClass = createEClass(ELLIPSE);

		pictogramElementEClass = createEClass(PICTOGRAM_ELEMENT);
		createEAttribute(pictogramElementEClass, PICTOGRAM_ELEMENT__VISIBLE);
		createEReference(pictogramElementEClass, PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM);
		createEAttribute(pictogramElementEClass, PICTOGRAM_ELEMENT__ACTIVE);
		createEReference(pictogramElementEClass, PICTOGRAM_ELEMENT__LINK);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__START);
		createEReference(connectionEClass, CONNECTION__END);
		createEReference(connectionEClass, CONNECTION__PARENT);
		createEReference(connectionEClass, CONNECTION__CONNECTION_DECORATORS);

		anchorEClass = createEClass(ANCHOR);
		createEReference(anchorEClass, ANCHOR__PARENT);
		createEReference(anchorEClass, ANCHOR__OUTGOING_CONNECTIONS);
		createEReference(anchorEClass, ANCHOR__INCOMING_CONNECTIONS);
		createEReference(anchorEClass, ANCHOR__REFERENCED_GRAPHICS_ALGORITHM);

		horizontalConnectionEClass = createEClass(HORIZONTAL_CONNECTION);

		verticalConnectionEClass = createEClass(VERTICAL_CONNECTION);

		anchorContainerEClass = createEClass(ANCHOR_CONTAINER);
		createEReference(anchorContainerEClass, ANCHOR_CONTAINER__ANCHORS);

		fixPointAnchorEClass = createEClass(FIX_POINT_ANCHOR);
		createEReference(fixPointAnchorEClass, FIX_POINT_ANCHOR__LOCATION);

		boxRelativeAnchorEClass = createEClass(BOX_RELATIVE_ANCHOR);
		createEAttribute(boxRelativeAnchorEClass, BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH);
		createEAttribute(boxRelativeAnchorEClass, BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT);

		chopboxAnchorEClass = createEClass(CHOPBOX_ANCHOR);

		linkToDiagramEClass = createEClass(LINK_TO_DIAGRAM);
		createEAttribute(linkToDiagramEClass, LINK_TO_DIAGRAM__AS_ICON);
		createEReference(linkToDiagramEClass, LINK_TO_DIAGRAM__VIEWPORT);
		createEReference(linkToDiagramEClass, LINK_TO_DIAGRAM__DIAGRAM);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		textEClass = createEClass(TEXT);

		connectionDecoratorEClass = createEClass(CONNECTION_DECORATOR);
		createEAttribute(connectionDecoratorEClass, CONNECTION_DECORATOR__LOCATION_RELATIVE);
		createEAttribute(connectionDecoratorEClass, CONNECTION_DECORATOR__LOCATION);
		createEReference(connectionDecoratorEClass, CONNECTION_DECORATOR__CONNECTION);

		freeFormConnectionEClass = createEClass(FREE_FORM_CONNECTION);
		createEReference(freeFormConnectionEClass, FREE_FORM_CONNECTION__BENDPOINTS);

		directConnectionEClass = createEClass(DIRECT_CONNECTION);

		manhattanConnectionEClass = createEClass(MANHATTAN_CONNECTION);

		polygonEClass = createEClass(POLYGON);

		rectangleEClass = createEClass(RECTANGLE);

		roundedRectangleEClass = createEClass(ROUNDED_RECTANGLE);
		createEAttribute(roundedRectangleEClass, ROUNDED_RECTANGLE__CORNER_HEIGHT);
		createEAttribute(roundedRectangleEClass, ROUNDED_RECTANGLE__CORNER_WIDTH);

		fontEClass = createEClass(FONT);
		createEAttribute(fontEClass, FONT__NAME);
		createEAttribute(fontEClass, FONT__SIZE);
		createEAttribute(fontEClass, FONT__ITALIC);
		createEAttribute(fontEClass, FONT__BOLD);

		renderingStyleEClass = createEClass(RENDERING_STYLE);
		createEAttribute(renderingStyleEClass, RENDERING_STYLE__PREDEFINED_STYLE_ID);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__ID);
		createEAttribute(imageEClass, IMAGE__STRETCH_H);
		createEAttribute(imageEClass, IMAGE__STRETCH_V);
		createEAttribute(imageEClass, IMAGE__PROPORTIONAL);

		fanConnectionEClass = createEClass(FAN_CONNECTION);

		platformGraphicsAlgorithmEClass = createEClass(PLATFORM_GRAPHICS_ALGORITHM);
		createEAttribute(platformGraphicsAlgorithmEClass, PLATFORM_GRAPHICS_ALGORITHM__ID);

		graphicsAlgorithmContainerEClass = createEClass(GRAPHICS_ALGORITHM_CONTAINER);

		abstractTextEClass = createEClass(ABSTRACT_TEXT);
		createEReference(abstractTextEClass, ABSTRACT_TEXT__FONT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__VERTICAL_ALIGNMENT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__ANGLE);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__VALUE);

		multiTextEClass = createEClass(MULTI_TEXT);

		propertyContainerEClass = createEClass(PROPERTY_CONTAINER);
		createEReference(propertyContainerEClass, PROPERTY_CONTAINER__PROPERTIES);

		styleContainerEClass = createEClass(STYLE_CONTAINER);
		createEReference(styleContainerEClass, STYLE_CONTAINER__STYLES);

		styleEClass = createEClass(STYLE);
		createEAttribute(styleEClass, STYLE__ID);
		createEAttribute(styleEClass, STYLE__DESCRIPTION);
		createEReference(styleEClass, STYLE__FONT);
		createEAttribute(styleEClass, STYLE__HORIZONTAL_ALIGNMENT);
		createEAttribute(styleEClass, STYLE__VERTICAL_ALIGNMENT);
		createEAttribute(styleEClass, STYLE__ANGLE);
		createEAttribute(styleEClass, STYLE__STRETCH_H);
		createEAttribute(styleEClass, STYLE__STRETCH_V);
		createEAttribute(styleEClass, STYLE__PROPORTIONAL);
		createEReference(styleEClass, STYLE__STYLE_CONTAINER);

		abstractStyleEClass = createEClass(ABSTRACT_STYLE);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__BACKGROUND);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__FOREGROUND);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_WIDTH);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_STYLE);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__FILLED);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_VISIBLE);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__RENDERING_STYLE);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__TRANSPARENCY);

		// Create enums
		lineStyleEEnum = createEEnum(LINE_STYLE);
		orientationEEnum = createEEnum(ORIENTATION);
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
		DatatypesPackage theDatatypesPackage = (DatatypesPackage)EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI);
		LinksPackage theLinksPackage = (LinksPackage)EPackage.Registry.INSTANCE.getEPackage(LinksPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		shapeEClass.getESuperTypes().add(this.getAnchorContainer());
		containerShapeEClass.getESuperTypes().add(this.getShape());
		diagramEClass.getESuperTypes().add(this.getContainerShape());
		diagramEClass.getESuperTypes().add(this.getStyleContainer());
		graphicsAlgorithmEClass.getESuperTypes().add(this.getGraphicsAlgorithmContainer());
		graphicsAlgorithmEClass.getESuperTypes().add(this.getAbstractStyle());
		polylineEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		ellipseEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		pictogramElementEClass.getESuperTypes().add(this.getGraphicsAlgorithmContainer());
		connectionEClass.getESuperTypes().add(this.getAnchorContainer());
		anchorEClass.getESuperTypes().add(this.getPictogramElement());
		horizontalConnectionEClass.getESuperTypes().add(this.getConnection());
		verticalConnectionEClass.getESuperTypes().add(this.getConnection());
		anchorContainerEClass.getESuperTypes().add(this.getPictogramElement());
		fixPointAnchorEClass.getESuperTypes().add(this.getAnchor());
		boxRelativeAnchorEClass.getESuperTypes().add(this.getAnchor());
		chopboxAnchorEClass.getESuperTypes().add(this.getAnchor());
		linkToDiagramEClass.getESuperTypes().add(this.getShape());
		textEClass.getESuperTypes().add(this.getAbstractText());
		connectionDecoratorEClass.getESuperTypes().add(this.getShape());
		freeFormConnectionEClass.getESuperTypes().add(this.getConnection());
		directConnectionEClass.getESuperTypes().add(this.getConnection());
		manhattanConnectionEClass.getESuperTypes().add(this.getConnection());
		polygonEClass.getESuperTypes().add(this.getPolyline());
		rectangleEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		roundedRectangleEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		imageEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		fanConnectionEClass.getESuperTypes().add(this.getConnection());
		platformGraphicsAlgorithmEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		graphicsAlgorithmContainerEClass.getESuperTypes().add(this.getPropertyContainer());
		abstractTextEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		multiTextEClass.getESuperTypes().add(this.getAbstractText());
		styleEClass.getESuperTypes().add(this.getStyleContainer());
		styleEClass.getESuperTypes().add(this.getAbstractStyle());

		// Initialize classes and features; add operations and parameters
		initEClass(shapeEClass, Shape.class, "Shape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getShape_Container(), this.getContainerShape(), this.getContainerShape_Children(), "container", null, 0, 1, Shape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(containerShapeEClass, ContainerShape.class, "ContainerShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainerShape_Children(), this.getShape(), this.getShape_Container(), "children", null, 0, -1, ContainerShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDiagram_GridUnit(), ecorePackage.getEInt(), "gridUnit", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDiagram_DiagramTypeId(), ecorePackage.getEString(), "diagramTypeId", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDiagram_Connections(), this.getConnection(), this.getConnection_Parent(), "connections", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDiagram_Name(), ecorePackage.getEString(), "name", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDiagram_SnapToGrid(), ecorePackage.getEBoolean(), "snapToGrid", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDiagram_ShowGuides(), ecorePackage.getEBoolean(), "showGuides", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDiagram_Colors(), theDatatypesPackage.getColor(), null, "colors", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(graphicsAlgorithmEClass, GraphicsAlgorithm.class, "GraphicsAlgorithm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGraphicsAlgorithm_GraphicsAlgorithmChildren(), this.getGraphicsAlgorithm(), this.getGraphicsAlgorithm_ParentGraphicsAlgorithm(), "graphicsAlgorithmChildren", null, 0, -1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraphicsAlgorithm_ParentGraphicsAlgorithm(), this.getGraphicsAlgorithm(), this.getGraphicsAlgorithm_GraphicsAlgorithmChildren(), "parentGraphicsAlgorithm", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGraphicsAlgorithm_PictogramElement(), this.getPictogramElement(), this.getPictogramElement_GraphicsAlgorithm(), "pictogramElement", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Width(), ecorePackage.getEInt(), "width", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Height(), ecorePackage.getEInt(), "height", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_X(), ecorePackage.getEInt(), "x", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Y(), ecorePackage.getEInt(), "y", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGraphicsAlgorithm_Style(), this.getStyle(), null, "style", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(polylineEClass, Polyline.class, "Polyline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPolyline_Points(), theDatatypesPackage.getPoint(), null, "points", null, 0, -1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ellipseEClass, Ellipse.class, "Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pictogramElementEClass, PictogramElement.class, "PictogramElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPictogramElement_Visible(), ecorePackage.getEBoolean(), "visible", null, 1, 1, PictogramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPictogramElement_GraphicsAlgorithm(), this.getGraphicsAlgorithm(), this.getGraphicsAlgorithm_PictogramElement(), "graphicsAlgorithm", null, 0, 1, PictogramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getPictogramElement_Active(), ecorePackage.getEBoolean(), "active", null, 1, 1, PictogramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPictogramElement_Link(), theLinksPackage.getPictogramLink(), theLinksPackage.getPictogramLink_PictogramElement(), "link", null, 0, 1, PictogramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_Start(), this.getAnchor(), this.getAnchor_OutgoingConnections(), "start", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnection_End(), this.getAnchor(), this.getAnchor_IncomingConnections(), "end", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnection_Parent(), this.getDiagram(), this.getDiagram_Connections(), "parent", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnection_ConnectionDecorators(), this.getConnectionDecorator(), this.getConnectionDecorator_Connection(), "connectionDecorators", null, 0, -1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(anchorEClass, Anchor.class, "Anchor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnchor_Parent(), this.getAnchorContainer(), this.getAnchorContainer_Anchors(), "parent", null, 1, 1, Anchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnchor_OutgoingConnections(), this.getConnection(), this.getConnection_Start(), "outgoingConnections", null, 0, -1, Anchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnchor_IncomingConnections(), this.getConnection(), this.getConnection_End(), "incomingConnections", null, 0, -1, Anchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnchor_ReferencedGraphicsAlgorithm(), this.getGraphicsAlgorithm(), null, "referencedGraphicsAlgorithm", null, 0, 1, Anchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(horizontalConnectionEClass, HorizontalConnection.class, "HorizontalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(verticalConnectionEClass, VerticalConnection.class, "VerticalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(anchorContainerEClass, AnchorContainer.class, "AnchorContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnchorContainer_Anchors(), this.getAnchor(), this.getAnchor_Parent(), "anchors", null, 0, -1, AnchorContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(fixPointAnchorEClass, FixPointAnchor.class, "FixPointAnchor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFixPointAnchor_Location(), theDatatypesPackage.getPoint(), null, "location", null, 1, 1, FixPointAnchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(boxRelativeAnchorEClass, BoxRelativeAnchor.class, "BoxRelativeAnchor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBoxRelativeAnchor_RelativeWidth(), ecorePackage.getEDouble(), "relativeWidth", null, 1, 1, BoxRelativeAnchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getBoxRelativeAnchor_RelativeHeight(), ecorePackage.getEDouble(), "relativeHeight", null, 1, 1, BoxRelativeAnchor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(chopboxAnchorEClass, ChopboxAnchor.class, "ChopboxAnchor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkToDiagramEClass, LinkToDiagram.class, "LinkToDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinkToDiagram_AsIcon(), ecorePackage.getEBoolean(), "asIcon", null, 1, 1, LinkToDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLinkToDiagram_Viewport(), theDatatypesPackage.getViewPort(), null, "viewport", null, 1, 1, LinkToDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLinkToDiagram_Diagram(), this.getDiagram(), null, "diagram", null, 1, 1, LinkToDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(connectionDecoratorEClass, ConnectionDecorator.class, "ConnectionDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectionDecorator_LocationRelative(), ecorePackage.getEBoolean(), "locationRelative", null, 1, 1, ConnectionDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getConnectionDecorator_Location(), ecorePackage.getEDouble(), "location", null, 1, 1, ConnectionDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnectionDecorator_Connection(), this.getConnection(), this.getConnection_ConnectionDecorators(), "connection", null, 1, 1, ConnectionDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(freeFormConnectionEClass, FreeFormConnection.class, "FreeFormConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFreeFormConnection_Bendpoints(), theDatatypesPackage.getPoint(), null, "bendpoints", null, 0, -1, FreeFormConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(directConnectionEClass, DirectConnection.class, "DirectConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(manhattanConnectionEClass, ManhattanConnection.class, "ManhattanConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(polygonEClass, Polygon.class, "Polygon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rectangleEClass, Rectangle.class, "Rectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roundedRectangleEClass, RoundedRectangle.class, "RoundedRectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoundedRectangle_CornerHeight(), ecorePackage.getEInt(), "cornerHeight", null, 1, 1, RoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRoundedRectangle_CornerWidth(), ecorePackage.getEInt(), "cornerWidth", null, 1, 1, RoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(fontEClass, Font.class, "Font", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFont_Name(), ecorePackage.getEString(), "name", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Size(), ecorePackage.getEInt(), "size", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Italic(), ecorePackage.getEBoolean(), "italic", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Bold(), ecorePackage.getEBoolean(), "bold", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(renderingStyleEClass, RenderingStyle.class, "RenderingStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRenderingStyle_PredefinedStyleId(), ecorePackage.getEString(), "predefinedStyleId", null, 1, 1, RenderingStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImage_Id(), ecorePackage.getEString(), "id", null, 1, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_StretchH(), ecorePackage.getEBooleanObject(), "stretchH", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_StretchV(), ecorePackage.getEBooleanObject(), "stretchV", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_Proportional(), ecorePackage.getEBooleanObject(), "proportional", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(fanConnectionEClass, FanConnection.class, "FanConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(platformGraphicsAlgorithmEClass, PlatformGraphicsAlgorithm.class, "PlatformGraphicsAlgorithm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlatformGraphicsAlgorithm_Id(), ecorePackage.getEString(), "id", null, 1, 1, PlatformGraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(graphicsAlgorithmContainerEClass, GraphicsAlgorithmContainer.class, "GraphicsAlgorithmContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractTextEClass, AbstractText.class, "AbstractText", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractText_Font(), this.getFont(), null, "font", null, 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_HorizontalAlignment(), this.getOrientation(), "horizontalAlignment", "ALIGNMENT_LEFT", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_VerticalAlignment(), this.getOrientation(), "verticalAlignment", "ALIGNMENT_CENTER", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_Angle(), ecorePackage.getEIntegerObject(), "angle", "0", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_Value(), ecorePackage.getEString(), "value", null, 1, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(multiTextEClass, MultiText.class, "MultiText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyContainerEClass, PropertyContainer.class, "PropertyContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyContainer_Properties(), this.getProperty(), null, "properties", null, 0, -1, PropertyContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(styleContainerEClass, StyleContainer.class, "StyleContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStyleContainer_Styles(), this.getStyle(), this.getStyle_StyleContainer(), "styles", null, 0, -1, StyleContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(styleEClass, Style.class, "Style", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStyle_Id(), ecorePackage.getEString(), "id", null, 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Description(), ecorePackage.getEString(), "description", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getStyle_Font(), this.getFont(), null, "font", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_HorizontalAlignment(), this.getOrientation(), "horizontalAlignment", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_VerticalAlignment(), this.getOrientation(), "verticalAlignment", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Angle(), ecorePackage.getEIntegerObject(), "angle", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_StretchH(), ecorePackage.getEBooleanObject(), "stretchH", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_StretchV(), ecorePackage.getEBooleanObject(), "stretchV", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Proportional(), ecorePackage.getEBooleanObject(), "proportional", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getStyle_StyleContainer(), this.getStyleContainer(), this.getStyleContainer_Styles(), "styleContainer", null, 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractStyleEClass, AbstractStyle.class, "AbstractStyle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractStyle_Background(), theDatatypesPackage.getColor(), null, "background", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractStyle_Foreground(), theDatatypesPackage.getColor(), null, "foreground", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineWidth(), ecorePackage.getEIntegerObject(), "lineWidth", "1", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineStyle(), this.getLineStyle(), "lineStyle", "SOLID", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_Filled(), ecorePackage.getEBooleanObject(), "filled", "true", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineVisible(), ecorePackage.getEBooleanObject(), "lineVisible", "true", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractStyle_RenderingStyle(), this.getRenderingStyle(), null, "renderingStyle", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_Transparency(), ecorePackage.getEDoubleObject(), "transparency", "0", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(lineStyleEEnum, LineStyle.class, "LineStyle");
		addEEnumLiteral(lineStyleEEnum, LineStyle.SOLID);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASH);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOTDOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.UNSPECIFIED);

		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_CENTER);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_LEFT);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_TOP);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_RIGHT);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_BOTTOM);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_MIDDLE);
		addEEnumLiteral(orientationEEnum, Orientation.UNSPECIFIED);

		// Create resource
		createResource(eNS_URI);
	}

} //PictogramsPackageImpl
