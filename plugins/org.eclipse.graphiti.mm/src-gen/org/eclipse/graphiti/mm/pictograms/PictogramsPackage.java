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
 * $Id: PictogramsPackage.java,v 1.1 2010/06/16 13:24:53 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsFactory
 * @model kind="package"
 * @generated
 */
public interface PictogramsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pictograms";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/graphiti/pictograms";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PictogramsPackage eINSTANCE = org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PropertyContainerImpl <em>Property Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PropertyContainerImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPropertyContainer()
	 * @generated
	 */
	int PROPERTY_CONTAINER = 33;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONTAINER__PROPERTIES = 0;

	/**
	 * The number of structural features of the '<em>Property Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmContainerImpl <em>Graphics Algorithm Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmContainerImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getGraphicsAlgorithmContainer()
	 * @generated
	 */
	int GRAPHICS_ALGORITHM_CONTAINER = 30;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM_CONTAINER__PROPERTIES = PROPERTY_CONTAINER__PROPERTIES;

	/**
	 * The number of structural features of the '<em>Graphics Algorithm Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT = PROPERTY_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl <em>Pictogram Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPictogramElement()
	 * @generated
	 */
	int PICTOGRAM_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT__PROPERTIES = GRAPHICS_ALGORITHM_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT__VISIBLE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT__ACTIVE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT__LINK = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Pictogram Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICTOGRAM_ELEMENT_FEATURE_COUNT = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorContainerImpl <em>Anchor Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.AnchorContainerImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAnchorContainer()
	 * @generated
	 */
	int ANCHOR_CONTAINER = 11;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__PROPERTIES = PICTOGRAM_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__VISIBLE = PICTOGRAM_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__GRAPHICS_ALGORITHM = PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__ACTIVE = PICTOGRAM_ELEMENT__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__LINK = PICTOGRAM_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER__ANCHORS = PICTOGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Anchor Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_CONTAINER_FEATURE_COUNT = PICTOGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ShapeImpl <em>Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ShapeImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getShape()
	 * @generated
	 */
	int SHAPE = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__PROPERTIES = ANCHOR_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__VISIBLE = ANCHOR_CONTAINER__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__GRAPHICS_ALGORITHM = ANCHOR_CONTAINER__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__ACTIVE = ANCHOR_CONTAINER__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__LINK = ANCHOR_CONTAINER__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__ANCHORS = ANCHOR_CONTAINER__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__CONTAINER = ANCHOR_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_FEATURE_COUNT = ANCHOR_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl <em>Container Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getContainerShape()
	 * @generated
	 */
	int CONTAINER_SHAPE = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__PROPERTIES = SHAPE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__VISIBLE = SHAPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__GRAPHICS_ALGORITHM = SHAPE__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__ACTIVE = SHAPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__LINK = SHAPE__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__ANCHORS = SHAPE__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__CONTAINER = SHAPE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE__CHILDREN = SHAPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_SHAPE_FEATURE_COUNT = SHAPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.DiagramImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__PROPERTIES = CONTAINER_SHAPE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__VISIBLE = CONTAINER_SHAPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRAPHICS_ALGORITHM = CONTAINER_SHAPE__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ACTIVE = CONTAINER_SHAPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__LINK = CONTAINER_SHAPE__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ANCHORS = CONTAINER_SHAPE__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CONTAINER = CONTAINER_SHAPE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CHILDREN = CONTAINER_SHAPE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__STYLES = CONTAINER_SHAPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Grid Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_UNIT = CONTAINER_SHAPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Diagram Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__DIAGRAM_TYPE_ID = CONTAINER_SHAPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CONNECTIONS = CONTAINER_SHAPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NAME = CONTAINER_SHAPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Snap To Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SNAP_TO_GRID = CONTAINER_SHAPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Show Guides</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SHOW_GUIDES = CONTAINER_SHAPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Colors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__COLORS = CONTAINER_SHAPE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = CONTAINER_SHAPE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmImpl <em>Graphics Algorithm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getGraphicsAlgorithm()
	 * @generated
	 */
	int GRAPHICS_ALGORITHM = 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PROPERTIES = GRAPHICS_ALGORITHM_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__BACKGROUND = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__FOREGROUND = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_WIDTH = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_STYLE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__FILLED = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_VISIBLE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__RENDERING_STYLE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__TRANSPARENCY = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__WIDTH = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__HEIGHT = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__X = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__Y = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__STYLE = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Graphics Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM_FEATURE_COUNT = GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PolylineImpl <em>Polyline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PolylineImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPolyline()
	 * @generated
	 */
	int POLYLINE = 4;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__POINTS = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Polyline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.EllipseImpl <em>Ellipse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.EllipseImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getEllipse()
	 * @generated
	 */
	int ELLIPSE = 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The number of structural features of the '<em>Ellipse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 7;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__PROPERTIES = ANCHOR_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__VISIBLE = ANCHOR_CONTAINER__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__GRAPHICS_ALGORITHM = ANCHOR_CONTAINER__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ACTIVE = ANCHOR_CONTAINER__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__LINK = ANCHOR_CONTAINER__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ANCHORS = ANCHOR_CONTAINER__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__START = ANCHOR_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__END = ANCHOR_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__PARENT = ANCHOR_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__CONNECTION_DECORATORS = ANCHOR_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = ANCHOR_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl <em>Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAnchor()
	 * @generated
	 */
	int ANCHOR = 8;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__PROPERTIES = PICTOGRAM_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__VISIBLE = PICTOGRAM_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__GRAPHICS_ALGORITHM = PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__ACTIVE = PICTOGRAM_ELEMENT__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__LINK = PICTOGRAM_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__PARENT = PICTOGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__OUTGOING_CONNECTIONS = PICTOGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__INCOMING_CONNECTIONS = PICTOGRAM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Referenced Graphics Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR__REFERENCED_GRAPHICS_ALGORITHM = PICTOGRAM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_FEATURE_COUNT = PICTOGRAM_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.HorizontalConnectionImpl <em>Horizontal Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.HorizontalConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getHorizontalConnection()
	 * @generated
	 */
	int HORIZONTAL_CONNECTION = 9;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The number of structural features of the '<em>Horizontal Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.VerticalConnectionImpl <em>Vertical Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.VerticalConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getVerticalConnection()
	 * @generated
	 */
	int VERTICAL_CONNECTION = 10;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The number of structural features of the '<em>Vertical Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FixPointAnchorImpl <em>Fix Point Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.FixPointAnchorImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFixPointAnchor()
	 * @generated
	 */
	int FIX_POINT_ANCHOR = 12;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__PROPERTIES = ANCHOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__VISIBLE = ANCHOR__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__GRAPHICS_ALGORITHM = ANCHOR__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__ACTIVE = ANCHOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__LINK = ANCHOR__LINK;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__PARENT = ANCHOR__PARENT;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__OUTGOING_CONNECTIONS = ANCHOR__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__INCOMING_CONNECTIONS = ANCHOR__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Referenced Graphics Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__REFERENCED_GRAPHICS_ALGORITHM = ANCHOR__REFERENCED_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR__LOCATION = ANCHOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fix Point Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_POINT_ANCHOR_FEATURE_COUNT = ANCHOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl <em>Box Relative Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getBoxRelativeAnchor()
	 * @generated
	 */
	int BOX_RELATIVE_ANCHOR = 13;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__PROPERTIES = ANCHOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__VISIBLE = ANCHOR__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__GRAPHICS_ALGORITHM = ANCHOR__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__ACTIVE = ANCHOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__LINK = ANCHOR__LINK;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__PARENT = ANCHOR__PARENT;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__OUTGOING_CONNECTIONS = ANCHOR__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__INCOMING_CONNECTIONS = ANCHOR__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Referenced Graphics Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__REFERENCED_GRAPHICS_ALGORITHM = ANCHOR__REFERENCED_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Relative Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH = ANCHOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Relative Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT = ANCHOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Box Relative Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_RELATIVE_ANCHOR_FEATURE_COUNT = ANCHOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ChopboxAnchorImpl <em>Chopbox Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ChopboxAnchorImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getChopboxAnchor()
	 * @generated
	 */
	int CHOPBOX_ANCHOR = 14;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__PROPERTIES = ANCHOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__VISIBLE = ANCHOR__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__GRAPHICS_ALGORITHM = ANCHOR__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__ACTIVE = ANCHOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__LINK = ANCHOR__LINK;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__PARENT = ANCHOR__PARENT;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__OUTGOING_CONNECTIONS = ANCHOR__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__INCOMING_CONNECTIONS = ANCHOR__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Referenced Graphics Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR__REFERENCED_GRAPHICS_ALGORITHM = ANCHOR__REFERENCED_GRAPHICS_ALGORITHM;

	/**
	 * The number of structural features of the '<em>Chopbox Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOPBOX_ANCHOR_FEATURE_COUNT = ANCHOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl <em>Link To Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getLinkToDiagram()
	 * @generated
	 */
	int LINK_TO_DIAGRAM = 15;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__PROPERTIES = SHAPE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__VISIBLE = SHAPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__GRAPHICS_ALGORITHM = SHAPE__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__ACTIVE = SHAPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__LINK = SHAPE__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__ANCHORS = SHAPE__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__CONTAINER = SHAPE__CONTAINER;

	/**
	 * The feature id for the '<em><b>As Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__AS_ICON = SHAPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Viewport</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__VIEWPORT = SHAPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM__DIAGRAM = SHAPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Link To Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_DIAGRAM_FEATURE_COUNT = SHAPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PropertyImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 16;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AbstractTextImpl <em>Abstract Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.AbstractTextImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAbstractText()
	 * @generated
	 */
	int ABSTRACT_TEXT = 31;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FONT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__VERTICAL_ALIGNMENT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__ANGLE = GRAPHICS_ALGORITHM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__VALUE = GRAPHICS_ALGORITHM_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Abstract Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.TextImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 17;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PROPERTIES = ABSTRACT_TEXT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__BACKGROUND = ABSTRACT_TEXT__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FOREGROUND = ABSTRACT_TEXT__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_WIDTH = ABSTRACT_TEXT__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_STYLE = ABSTRACT_TEXT__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FILLED = ABSTRACT_TEXT__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_VISIBLE = ABSTRACT_TEXT__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__RENDERING_STYLE = ABSTRACT_TEXT__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__TRANSPARENCY = ABSTRACT_TEXT__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__GRAPHICS_ALGORITHM_CHILDREN = ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PARENT_GRAPHICS_ALGORITHM = ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PICTOGRAM_ELEMENT = ABSTRACT_TEXT__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__WIDTH = ABSTRACT_TEXT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__HEIGHT = ABSTRACT_TEXT__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__X = ABSTRACT_TEXT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__Y = ABSTRACT_TEXT__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__STYLE = ABSTRACT_TEXT__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FONT = ABSTRACT_TEXT__FONT;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__HORIZONTAL_ALIGNMENT = ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__VERTICAL_ALIGNMENT = ABSTRACT_TEXT__VERTICAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ANGLE = ABSTRACT_TEXT__ANGLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__VALUE = ABSTRACT_TEXT__VALUE;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = ABSTRACT_TEXT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl <em>Connection Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getConnectionDecorator()
	 * @generated
	 */
	int CONNECTION_DECORATOR = 18;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__PROPERTIES = SHAPE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__VISIBLE = SHAPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__GRAPHICS_ALGORITHM = SHAPE__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__ACTIVE = SHAPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__LINK = SHAPE__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__ANCHORS = SHAPE__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__CONTAINER = SHAPE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Location Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__LOCATION_RELATIVE = SHAPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__LOCATION = SHAPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR__CONNECTION = SHAPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Connection Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DECORATOR_FEATURE_COUNT = SHAPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FreeFormConnectionImpl <em>Free Form Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.FreeFormConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFreeFormConnection()
	 * @generated
	 */
	int FREE_FORM_CONNECTION = 19;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION__BENDPOINTS = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Free Form Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREE_FORM_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.DirectConnectionImpl <em>Direct Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.DirectConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getDirectConnection()
	 * @generated
	 */
	int DIRECT_CONNECTION = 20;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The number of structural features of the '<em>Direct Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECT_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ManhattanConnectionImpl <em>Manhattan Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ManhattanConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getManhattanConnection()
	 * @generated
	 */
	int MANHATTAN_CONNECTION = 21;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The number of structural features of the '<em>Manhattan Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANHATTAN_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PolygonImpl <em>Polygon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PolygonImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPolygon()
	 * @generated
	 */
	int POLYGON = 22;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PROPERTIES = POLYLINE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__BACKGROUND = POLYLINE__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__FOREGROUND = POLYLINE__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_WIDTH = POLYLINE__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_STYLE = POLYLINE__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__FILLED = POLYLINE__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_VISIBLE = POLYLINE__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__RENDERING_STYLE = POLYLINE__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__TRANSPARENCY = POLYLINE__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__GRAPHICS_ALGORITHM_CHILDREN = POLYLINE__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PARENT_GRAPHICS_ALGORITHM = POLYLINE__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PICTOGRAM_ELEMENT = POLYLINE__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__WIDTH = POLYLINE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__HEIGHT = POLYLINE__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__X = POLYLINE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__Y = POLYLINE__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__STYLE = POLYLINE__STYLE;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__POINTS = POLYLINE__POINTS;

	/**
	 * The number of structural features of the '<em>Polygon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON_FEATURE_COUNT = POLYLINE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RectangleImpl <em>Rectangle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.RectangleImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRectangle()
	 * @generated
	 */
	int RECTANGLE = 23;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The number of structural features of the '<em>Rectangle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RoundedRectangleImpl <em>Rounded Rectangle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.RoundedRectangleImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRoundedRectangle()
	 * @generated
	 */
	int ROUNDED_RECTANGLE = 24;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Corner Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__CORNER_HEIGHT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Corner Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__CORNER_WIDTH = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rounded Rectangle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FontImpl <em>Font</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.FontImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFont()
	 * @generated
	 */
	int FONT = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__SIZE = 1;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__ITALIC = 2;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__BOLD = 3;

	/**
	 * The number of structural features of the '<em>Font</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RenderingStyleImpl <em>Rendering Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.RenderingStyleImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRenderingStyle()
	 * @generated
	 */
	int RENDERING_STYLE = 26;

	/**
	 * The feature id for the '<em><b>Predefined Style Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING_STYLE__PREDEFINED_STYLE_ID = 0;

	/**
	 * The number of structural features of the '<em>Rendering Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING_STYLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.ImageImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 27;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__ID = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stretch H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STRETCH_H = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Stretch V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STRETCH_V = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Proportional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PROPORTIONAL = GRAPHICS_ALGORITHM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FanConnectionImpl <em>Fan Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.FanConnectionImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFanConnection()
	 * @generated
	 */
	int FAN_CONNECTION = 28;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__PROPERTIES = CONNECTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__VISIBLE = CONNECTION__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__GRAPHICS_ALGORITHM = CONNECTION__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__ACTIVE = CONNECTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__LINK = CONNECTION__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__ANCHORS = CONNECTION__ANCHORS;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__START = CONNECTION__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__END = CONNECTION__END;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__PARENT = CONNECTION__PARENT;

	/**
	 * The feature id for the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION__CONNECTION_DECORATORS = CONNECTION__CONNECTION_DECORATORS;

	/**
	 * The number of structural features of the '<em>Fan Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAN_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PlatformGraphicsAlgorithmImpl <em>Platform Graphics Algorithm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PlatformGraphicsAlgorithmImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPlatformGraphicsAlgorithm()
	 * @generated
	 */
	int PLATFORM_GRAPHICS_ALGORITHM = 29;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__ID = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Platform Graphics Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.MultiTextImpl <em>Multi Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.MultiTextImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getMultiText()
	 * @generated
	 */
	int MULTI_TEXT = 32;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PROPERTIES = ABSTRACT_TEXT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__BACKGROUND = ABSTRACT_TEXT__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FOREGROUND = ABSTRACT_TEXT__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_WIDTH = ABSTRACT_TEXT__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_STYLE = ABSTRACT_TEXT__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FILLED = ABSTRACT_TEXT__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_VISIBLE = ABSTRACT_TEXT__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__RENDERING_STYLE = ABSTRACT_TEXT__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__TRANSPARENCY = ABSTRACT_TEXT__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__GRAPHICS_ALGORITHM_CHILDREN = ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PARENT_GRAPHICS_ALGORITHM = ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PICTOGRAM_ELEMENT = ABSTRACT_TEXT__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__WIDTH = ABSTRACT_TEXT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__HEIGHT = ABSTRACT_TEXT__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__X = ABSTRACT_TEXT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__Y = ABSTRACT_TEXT__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__STYLE = ABSTRACT_TEXT__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FONT = ABSTRACT_TEXT__FONT;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__HORIZONTAL_ALIGNMENT = ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__VERTICAL_ALIGNMENT = ABSTRACT_TEXT__VERTICAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__ANGLE = ABSTRACT_TEXT__ANGLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__VALUE = ABSTRACT_TEXT__VALUE;

	/**
	 * The number of structural features of the '<em>Multi Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT_FEATURE_COUNT = ABSTRACT_TEXT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.StyleContainerImpl <em>Style Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.StyleContainerImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getStyleContainer()
	 * @generated
	 */
	int STYLE_CONTAINER = 34;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_CONTAINER__STYLES = 0;

	/**
	 * The number of structural features of the '<em>Style Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl <em>Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.StyleImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getStyle()
	 * @generated
	 */
	int STYLE = 35;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STYLES = STYLE_CONTAINER__STYLES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__BACKGROUND = STYLE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FOREGROUND = STYLE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_WIDTH = STYLE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_STYLE = STYLE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FILLED = STYLE_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_VISIBLE = STYLE_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__RENDERING_STYLE = STYLE_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__TRANSPARENCY = STYLE_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__ID = STYLE_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__DESCRIPTION = STYLE_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FONT = STYLE_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__HORIZONTAL_ALIGNMENT = STYLE_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__VERTICAL_ALIGNMENT = STYLE_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__ANGLE = STYLE_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Stretch H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STRETCH_H = STYLE_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Stretch V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STRETCH_V = STYLE_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Proportional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__PROPORTIONAL = STYLE_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Style Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STYLE_CONTAINER = STYLE_CONTAINER_FEATURE_COUNT + 17;

	/**
	 * The number of structural features of the '<em>Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_FEATURE_COUNT = STYLE_CONTAINER_FEATURE_COUNT + 18;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AbstractStyleImpl <em>Abstract Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.impl.AbstractStyleImpl
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAbstractStyle()
	 * @generated
	 */
	int ABSTRACT_STYLE = 36;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__BACKGROUND = 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__FOREGROUND = 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_WIDTH = 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_STYLE = 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__FILLED = 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_VISIBLE = 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__RENDERING_STYLE = 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__TRANSPARENCY = 7;

	/**
	 * The number of structural features of the '<em>Abstract Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.LineStyle <em>Line Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.LineStyle
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getLineStyle()
	 * @generated
	 */
	int LINE_STYLE = 37;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.pictograms.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.pictograms.Orientation
	 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 38;


	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Shape
	 * @generated
	 */
	EClass getShape();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.Shape#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Shape#getContainer()
	 * @see #getShape()
	 * @generated
	 */
	EReference getShape_Container();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.ContainerShape <em>Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Shape</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ContainerShape
	 * @generated
	 */
	EClass getContainerShape();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.ContainerShape#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ContainerShape#getChildren()
	 * @see #getContainerShape()
	 * @generated
	 */
	EReference getContainerShape_Children();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getGridUnit <em>Grid Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Unit</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getGridUnit()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_GridUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getDiagramTypeId <em>Diagram Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Diagram Type Id</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getDiagramTypeId()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_DiagramTypeId();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getConnections()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Connections();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getName()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Diagram#isSnapToGrid <em>Snap To Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snap To Grid</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#isSnapToGrid()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_SnapToGrid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Diagram#isShowGuides <em>Show Guides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Guides</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#isShowGuides()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_ShowGuides();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getColors <em>Colors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Colors</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getColors()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Colors();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm <em>Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm
	 * @generated
	 */
	EClass getGraphicsAlgorithm();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getGraphicsAlgorithmChildren <em>Graphics Algorithm Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Graphics Algorithm Children</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getGraphicsAlgorithmChildren()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_GraphicsAlgorithmChildren();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getParentGraphicsAlgorithm()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_ParentGraphicsAlgorithm();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getPictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pictogram Element</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getPictogramElement()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_PictogramElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getWidth()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getHeight()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getX()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getY()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Y();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm#getStyle()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_Style();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polyline</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Polyline
	 * @generated
	 */
	EClass getPolyline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.Polyline#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Polyline#getPoints()
	 * @see #getPolyline()
	 * @generated
	 */
	EReference getPolyline_Points();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ellipse</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Ellipse
	 * @generated
	 */
	EClass getEllipse();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pictogram Element</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement
	 * @generated
	 */
	EClass getPictogramElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isVisible <em>Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement#isVisible()
	 * @see #getPictogramElement()
	 * @generated
	 */
	EAttribute getPictogramElement_Visible();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm <em>Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm()
	 * @see #getPictogramElement()
	 * @generated
	 */
	EReference getPictogramElement_GraphicsAlgorithm();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement#isActive()
	 * @see #getPictogramElement()
	 * @generated
	 */
	EAttribute getPictogramElement_Active();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Link</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement#getLink()
	 * @see #getPictogramElement()
	 * @generated
	 */
	EReference getPictogramElement_Link();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.Connection#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getStart()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Start();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.Connection#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getEnd()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_End();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.Connection#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getParent()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.Connection#getConnectionDecorators <em>Connection Decorators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Decorators</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getConnectionDecorators()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_ConnectionDecorators();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Anchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anchor</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor
	 * @generated
	 */
	EClass getAnchor();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getParent()
	 * @see #getAnchor()
	 * @generated
	 */
	EReference getAnchor_Parent();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Connections</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getOutgoingConnections()
	 * @see #getAnchor()
	 * @generated
	 */
	EReference getAnchor_OutgoingConnections();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Connections</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getIncomingConnections()
	 * @see #getAnchor()
	 * @generated
	 */
	EReference getAnchor_IncomingConnections();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getReferencedGraphicsAlgorithm <em>Referenced Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getReferencedGraphicsAlgorithm()
	 * @see #getAnchor()
	 * @generated
	 */
	EReference getAnchor_ReferencedGraphicsAlgorithm();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.HorizontalConnection <em>Horizontal Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Horizontal Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.HorizontalConnection
	 * @generated
	 */
	EClass getHorizontalConnection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.VerticalConnection <em>Vertical Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertical Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.VerticalConnection
	 * @generated
	 */
	EClass getVerticalConnection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer <em>Anchor Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anchor Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AnchorContainer
	 * @generated
	 */
	EClass getAnchorContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer#getAnchors <em>Anchors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Anchors</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AnchorContainer#getAnchors()
	 * @see #getAnchorContainer()
	 * @generated
	 */
	EReference getAnchorContainer_Anchors();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor <em>Fix Point Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fix Point Anchor</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.FixPointAnchor
	 * @generated
	 */
	EClass getFixPointAnchor();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Location</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.FixPointAnchor#getLocation()
	 * @see #getFixPointAnchor()
	 * @generated
	 */
	EReference getFixPointAnchor_Location();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor <em>Box Relative Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Box Relative Anchor</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor
	 * @generated
	 */
	EClass getBoxRelativeAnchor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeWidth <em>Relative Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative Width</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeWidth()
	 * @see #getBoxRelativeAnchor()
	 * @generated
	 */
	EAttribute getBoxRelativeAnchor_RelativeWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeHeight <em>Relative Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative Height</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeHeight()
	 * @see #getBoxRelativeAnchor()
	 * @generated
	 */
	EAttribute getBoxRelativeAnchor_RelativeHeight();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.ChopboxAnchor <em>Chopbox Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chopbox Anchor</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ChopboxAnchor
	 * @generated
	 */
	EClass getChopboxAnchor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram <em>Link To Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link To Diagram</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.LinkToDiagram
	 * @generated
	 */
	EClass getLinkToDiagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#isAsIcon <em>As Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As Icon</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.LinkToDiagram#isAsIcon()
	 * @see #getLinkToDiagram()
	 * @generated
	 */
	EAttribute getLinkToDiagram_AsIcon();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getViewport <em>Viewport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Viewport</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getViewport()
	 * @see #getLinkToDiagram()
	 * @generated
	 */
	EReference getLinkToDiagram_Viewport();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.LinkToDiagram#getDiagram()
	 * @see #getLinkToDiagram()
	 * @generated
	 */
	EReference getLinkToDiagram_Diagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Property#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator <em>Connection Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Decorator</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator
	 * @generated
	 */
	EClass getConnectionDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#isLocationRelative <em>Location Relative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location Relative</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#isLocationRelative()
	 * @see #getConnectionDecorator()
	 * @generated
	 */
	EAttribute getConnectionDecorator_LocationRelative();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getLocation()
	 * @see #getConnectionDecorator()
	 * @generated
	 */
	EAttribute getConnectionDecorator_Location();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection()
	 * @see #getConnectionDecorator()
	 * @generated
	 */
	EReference getConnectionDecorator_Connection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection <em>Free Form Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Free Form Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.FreeFormConnection
	 * @generated
	 */
	EClass getFreeFormConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bendpoints</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.FreeFormConnection#getBendpoints()
	 * @see #getFreeFormConnection()
	 * @generated
	 */
	EReference getFreeFormConnection_Bendpoints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.DirectConnection <em>Direct Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Direct Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.DirectConnection
	 * @generated
	 */
	EClass getDirectConnection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.ManhattanConnection <em>Manhattan Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manhattan Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.ManhattanConnection
	 * @generated
	 */
	EClass getManhattanConnection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Polygon <em>Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polygon</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Polygon
	 * @generated
	 */
	EClass getPolygon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rectangle</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Rectangle
	 * @generated
	 */
	EClass getRectangle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.RoundedRectangle <em>Rounded Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rounded Rectangle</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.RoundedRectangle
	 * @generated
	 */
	EClass getRoundedRectangle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.RoundedRectangle#getCornerHeight <em>Corner Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Corner Height</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.RoundedRectangle#getCornerHeight()
	 * @see #getRoundedRectangle()
	 * @generated
	 */
	EAttribute getRoundedRectangle_CornerHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.RoundedRectangle#getCornerWidth <em>Corner Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Corner Width</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.RoundedRectangle#getCornerWidth()
	 * @see #getRoundedRectangle()
	 * @generated
	 */
	EAttribute getRoundedRectangle_CornerWidth();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Font <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Font
	 * @generated
	 */
	EClass getFont();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Font#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Font#getName()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Font#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Font#getSize()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Font#isItalic <em>Italic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Italic</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Font#isItalic()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Italic();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Font#isBold <em>Bold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bold</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Font#isBold()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Bold();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.RenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rendering Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.RenderingStyle
	 * @generated
	 */
	EClass getRenderingStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.RenderingStyle#getPredefinedStyleId <em>Predefined Style Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predefined Style Id</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.RenderingStyle#getPredefinedStyleId()
	 * @see #getRenderingStyle()
	 * @generated
	 */
	EAttribute getRenderingStyle_PredefinedStyleId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Image#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Image#getId()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Image#getStretchH <em>Stretch H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch H</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Image#getStretchH()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_StretchH();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Image#getStretchV <em>Stretch V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch V</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Image#getStretchV()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_StretchV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Image#getProportional <em>Proportional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proportional</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Image#getProportional()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Proportional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.FanConnection <em>Fan Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fan Connection</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.FanConnection
	 * @generated
	 */
	EClass getFanConnection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm <em>Platform Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm
	 * @generated
	 */
	EClass getPlatformGraphicsAlgorithm();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm#getId()
	 * @see #getPlatformGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getPlatformGraphicsAlgorithm_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer <em>Graphics Algorithm Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphics Algorithm Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer
	 * @generated
	 */
	EClass getGraphicsAlgorithmContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.AbstractText <em>Abstract Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Text</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText
	 * @generated
	 */
	EClass getAbstractText();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.AbstractText#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText#getFont()
	 * @see #getAbstractText()
	 * @generated
	 */
	EReference getAbstractText_Font();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractText#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText#getHorizontalAlignment()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_HorizontalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractText#getVerticalAlignment <em>Vertical Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText#getVerticalAlignment()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_VerticalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractText#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText#getAngle()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_Angle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractText#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText#getValue()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.MultiText <em>Multi Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Text</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.MultiText
	 * @generated
	 */
	EClass getMultiText();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.PropertyContainer <em>Property Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PropertyContainer
	 * @generated
	 */
	EClass getPropertyContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.PropertyContainer#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.PropertyContainer#getProperties()
	 * @see #getPropertyContainer()
	 * @generated
	 */
	EReference getPropertyContainer_Properties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.StyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.StyleContainer
	 * @generated
	 */
	EClass getStyleContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.pictograms.StyleContainer#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Styles</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.StyleContainer#getStyles()
	 * @see #getStyleContainer()
	 * @generated
	 */
	EReference getStyleContainer_Styles();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style
	 * @generated
	 */
	EClass getStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getId()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getDescription()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.Style#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getFont()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_Font();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getHorizontalAlignment()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_HorizontalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getVerticalAlignment <em>Vertical Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getVerticalAlignment()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_VerticalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getAngle()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Angle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getStretchH <em>Stretch H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch H</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getStretchH()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_StretchH();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getStretchV <em>Stretch V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch V</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getStretchV()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_StretchV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.Style#getProportional <em>Proportional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proportional</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getProportional()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Proportional();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.pictograms.Style#getStyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Style Container</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Style#getStyleContainer()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_StyleContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle <em>Abstract Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle
	 * @generated
	 */
	EClass getAbstractStyle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Background</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getBackground()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_Background();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getForeground <em>Foreground</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Foreground</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getForeground()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_Foreground();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineWidth <em>Line Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Width</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineWidth()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineStyle()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getFilled <em>Filled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filled</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getFilled()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_Filled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineVisible <em>Line Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Visible</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getLineVisible()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineVisible();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getRenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rendering Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getRenderingStyle()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_RenderingStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle#getTransparency <em>Transparency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transparency</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle#getTransparency()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_Transparency();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.mm.pictograms.LineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Line Style</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.LineStyle
	 * @generated
	 */
	EEnum getLineStyle();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.mm.pictograms.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see org.eclipse.graphiti.mm.pictograms.Orientation
	 * @generated
	 */
	EEnum getOrientation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PictogramsFactory getPictogramsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ShapeImpl <em>Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ShapeImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getShape()
		 * @generated
		 */
		EClass SHAPE = eINSTANCE.getShape();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHAPE__CONTAINER = eINSTANCE.getShape_Container();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl <em>Container Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getContainerShape()
		 * @generated
		 */
		EClass CONTAINER_SHAPE = eINSTANCE.getContainerShape();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_SHAPE__CHILDREN = eINSTANCE.getContainerShape_Children();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.DiagramImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Grid Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_UNIT = eINSTANCE.getDiagram_GridUnit();

		/**
		 * The meta object literal for the '<em><b>Diagram Type Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__DIAGRAM_TYPE_ID = eINSTANCE.getDiagram_DiagramTypeId();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__CONNECTIONS = eINSTANCE.getDiagram_Connections();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

		/**
		 * The meta object literal for the '<em><b>Snap To Grid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__SNAP_TO_GRID = eINSTANCE.getDiagram_SnapToGrid();

		/**
		 * The meta object literal for the '<em><b>Show Guides</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__SHOW_GUIDES = eINSTANCE.getDiagram_ShowGuides();

		/**
		 * The meta object literal for the '<em><b>Colors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__COLORS = eINSTANCE.getDiagram_Colors();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmImpl <em>Graphics Algorithm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getGraphicsAlgorithm()
		 * @generated
		 */
		EClass GRAPHICS_ALGORITHM = eINSTANCE.getGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = eINSTANCE.getGraphicsAlgorithm_GraphicsAlgorithmChildren();

		/**
		 * The meta object literal for the '<em><b>Parent Graphics Algorithm</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = eINSTANCE.getGraphicsAlgorithm_ParentGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Pictogram Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = eINSTANCE.getGraphicsAlgorithm_PictogramElement();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__WIDTH = eINSTANCE.getGraphicsAlgorithm_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__HEIGHT = eINSTANCE.getGraphicsAlgorithm_Height();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__X = eINSTANCE.getGraphicsAlgorithm_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__Y = eINSTANCE.getGraphicsAlgorithm_Y();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__STYLE = eINSTANCE.getGraphicsAlgorithm_Style();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PolylineImpl <em>Polyline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PolylineImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPolyline()
		 * @generated
		 */
		EClass POLYLINE = eINSTANCE.getPolyline();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLYLINE__POINTS = eINSTANCE.getPolyline_Points();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.EllipseImpl <em>Ellipse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.EllipseImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getEllipse()
		 * @generated
		 */
		EClass ELLIPSE = eINSTANCE.getEllipse();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl <em>Pictogram Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPictogramElement()
		 * @generated
		 */
		EClass PICTOGRAM_ELEMENT = eINSTANCE.getPictogramElement();

		/**
		 * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PICTOGRAM_ELEMENT__VISIBLE = eINSTANCE.getPictogramElement_Visible();

		/**
		 * The meta object literal for the '<em><b>Graphics Algorithm</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM = eINSTANCE.getPictogramElement_GraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PICTOGRAM_ELEMENT__ACTIVE = eINSTANCE.getPictogramElement_Active();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PICTOGRAM_ELEMENT__LINK = eINSTANCE.getPictogramElement_Link();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__START = eINSTANCE.getConnection_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__END = eINSTANCE.getConnection_End();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__PARENT = eINSTANCE.getConnection_Parent();

		/**
		 * The meta object literal for the '<em><b>Connection Decorators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__CONNECTION_DECORATORS = eINSTANCE.getConnection_ConnectionDecorators();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl <em>Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAnchor()
		 * @generated
		 */
		EClass ANCHOR = eINSTANCE.getAnchor();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANCHOR__PARENT = eINSTANCE.getAnchor_Parent();

		/**
		 * The meta object literal for the '<em><b>Outgoing Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANCHOR__OUTGOING_CONNECTIONS = eINSTANCE.getAnchor_OutgoingConnections();

		/**
		 * The meta object literal for the '<em><b>Incoming Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANCHOR__INCOMING_CONNECTIONS = eINSTANCE.getAnchor_IncomingConnections();

		/**
		 * The meta object literal for the '<em><b>Referenced Graphics Algorithm</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANCHOR__REFERENCED_GRAPHICS_ALGORITHM = eINSTANCE.getAnchor_ReferencedGraphicsAlgorithm();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.HorizontalConnectionImpl <em>Horizontal Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.HorizontalConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getHorizontalConnection()
		 * @generated
		 */
		EClass HORIZONTAL_CONNECTION = eINSTANCE.getHorizontalConnection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.VerticalConnectionImpl <em>Vertical Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.VerticalConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getVerticalConnection()
		 * @generated
		 */
		EClass VERTICAL_CONNECTION = eINSTANCE.getVerticalConnection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorContainerImpl <em>Anchor Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.AnchorContainerImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAnchorContainer()
		 * @generated
		 */
		EClass ANCHOR_CONTAINER = eINSTANCE.getAnchorContainer();

		/**
		 * The meta object literal for the '<em><b>Anchors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANCHOR_CONTAINER__ANCHORS = eINSTANCE.getAnchorContainer_Anchors();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FixPointAnchorImpl <em>Fix Point Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.FixPointAnchorImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFixPointAnchor()
		 * @generated
		 */
		EClass FIX_POINT_ANCHOR = eINSTANCE.getFixPointAnchor();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_POINT_ANCHOR__LOCATION = eINSTANCE.getFixPointAnchor_Location();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl <em>Box Relative Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getBoxRelativeAnchor()
		 * @generated
		 */
		EClass BOX_RELATIVE_ANCHOR = eINSTANCE.getBoxRelativeAnchor();

		/**
		 * The meta object literal for the '<em><b>Relative Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH = eINSTANCE.getBoxRelativeAnchor_RelativeWidth();

		/**
		 * The meta object literal for the '<em><b>Relative Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT = eINSTANCE.getBoxRelativeAnchor_RelativeHeight();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ChopboxAnchorImpl <em>Chopbox Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ChopboxAnchorImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getChopboxAnchor()
		 * @generated
		 */
		EClass CHOPBOX_ANCHOR = eINSTANCE.getChopboxAnchor();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl <em>Link To Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getLinkToDiagram()
		 * @generated
		 */
		EClass LINK_TO_DIAGRAM = eINSTANCE.getLinkToDiagram();

		/**
		 * The meta object literal for the '<em><b>As Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK_TO_DIAGRAM__AS_ICON = eINSTANCE.getLinkToDiagram_AsIcon();

		/**
		 * The meta object literal for the '<em><b>Viewport</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_TO_DIAGRAM__VIEWPORT = eINSTANCE.getLinkToDiagram_Viewport();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_TO_DIAGRAM__DIAGRAM = eINSTANCE.getLinkToDiagram_Diagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PropertyImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.TextImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl <em>Connection Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getConnectionDecorator()
		 * @generated
		 */
		EClass CONNECTION_DECORATOR = eINSTANCE.getConnectionDecorator();

		/**
		 * The meta object literal for the '<em><b>Location Relative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DECORATOR__LOCATION_RELATIVE = eINSTANCE.getConnectionDecorator_LocationRelative();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DECORATOR__LOCATION = eINSTANCE.getConnectionDecorator_Location();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_DECORATOR__CONNECTION = eINSTANCE.getConnectionDecorator_Connection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FreeFormConnectionImpl <em>Free Form Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.FreeFormConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFreeFormConnection()
		 * @generated
		 */
		EClass FREE_FORM_CONNECTION = eINSTANCE.getFreeFormConnection();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FREE_FORM_CONNECTION__BENDPOINTS = eINSTANCE.getFreeFormConnection_Bendpoints();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.DirectConnectionImpl <em>Direct Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.DirectConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getDirectConnection()
		 * @generated
		 */
		EClass DIRECT_CONNECTION = eINSTANCE.getDirectConnection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ManhattanConnectionImpl <em>Manhattan Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ManhattanConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getManhattanConnection()
		 * @generated
		 */
		EClass MANHATTAN_CONNECTION = eINSTANCE.getManhattanConnection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PolygonImpl <em>Polygon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PolygonImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPolygon()
		 * @generated
		 */
		EClass POLYGON = eINSTANCE.getPolygon();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RectangleImpl <em>Rectangle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.RectangleImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRectangle()
		 * @generated
		 */
		EClass RECTANGLE = eINSTANCE.getRectangle();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RoundedRectangleImpl <em>Rounded Rectangle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.RoundedRectangleImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRoundedRectangle()
		 * @generated
		 */
		EClass ROUNDED_RECTANGLE = eINSTANCE.getRoundedRectangle();

		/**
		 * The meta object literal for the '<em><b>Corner Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUNDED_RECTANGLE__CORNER_HEIGHT = eINSTANCE.getRoundedRectangle_CornerHeight();

		/**
		 * The meta object literal for the '<em><b>Corner Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUNDED_RECTANGLE__CORNER_WIDTH = eINSTANCE.getRoundedRectangle_CornerWidth();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FontImpl <em>Font</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.FontImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFont()
		 * @generated
		 */
		EClass FONT = eINSTANCE.getFont();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__NAME = eINSTANCE.getFont_Name();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__SIZE = eINSTANCE.getFont_Size();

		/**
		 * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__ITALIC = eINSTANCE.getFont_Italic();

		/**
		 * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__BOLD = eINSTANCE.getFont_Bold();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.RenderingStyleImpl <em>Rendering Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.RenderingStyleImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getRenderingStyle()
		 * @generated
		 */
		EClass RENDERING_STYLE = eINSTANCE.getRenderingStyle();

		/**
		 * The meta object literal for the '<em><b>Predefined Style Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERING_STYLE__PREDEFINED_STYLE_ID = eINSTANCE.getRenderingStyle_PredefinedStyleId();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.ImageImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__ID = eINSTANCE.getImage_Id();

		/**
		 * The meta object literal for the '<em><b>Stretch H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__STRETCH_H = eINSTANCE.getImage_StretchH();

		/**
		 * The meta object literal for the '<em><b>Stretch V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__STRETCH_V = eINSTANCE.getImage_StretchV();

		/**
		 * The meta object literal for the '<em><b>Proportional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__PROPORTIONAL = eINSTANCE.getImage_Proportional();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.FanConnectionImpl <em>Fan Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.FanConnectionImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getFanConnection()
		 * @generated
		 */
		EClass FAN_CONNECTION = eINSTANCE.getFanConnection();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PlatformGraphicsAlgorithmImpl <em>Platform Graphics Algorithm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PlatformGraphicsAlgorithmImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPlatformGraphicsAlgorithm()
		 * @generated
		 */
		EClass PLATFORM_GRAPHICS_ALGORITHM = eINSTANCE.getPlatformGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLATFORM_GRAPHICS_ALGORITHM__ID = eINSTANCE.getPlatformGraphicsAlgorithm_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmContainerImpl <em>Graphics Algorithm Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.GraphicsAlgorithmContainerImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getGraphicsAlgorithmContainer()
		 * @generated
		 */
		EClass GRAPHICS_ALGORITHM_CONTAINER = eINSTANCE.getGraphicsAlgorithmContainer();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AbstractTextImpl <em>Abstract Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.AbstractTextImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAbstractText()
		 * @generated
		 */
		EClass ABSTRACT_TEXT = eINSTANCE.getAbstractText();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TEXT__FONT = eINSTANCE.getAbstractText_Font();

		/**
		 * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT = eINSTANCE.getAbstractText_HorizontalAlignment();

		/**
		 * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__VERTICAL_ALIGNMENT = eINSTANCE.getAbstractText_VerticalAlignment();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__ANGLE = eINSTANCE.getAbstractText_Angle();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__VALUE = eINSTANCE.getAbstractText_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.MultiTextImpl <em>Multi Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.MultiTextImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getMultiText()
		 * @generated
		 */
		EClass MULTI_TEXT = eINSTANCE.getMultiText();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.PropertyContainerImpl <em>Property Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PropertyContainerImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getPropertyContainer()
		 * @generated
		 */
		EClass PROPERTY_CONTAINER = eINSTANCE.getPropertyContainer();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CONTAINER__PROPERTIES = eINSTANCE.getPropertyContainer_Properties();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.StyleContainerImpl <em>Style Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.StyleContainerImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getStyleContainer()
		 * @generated
		 */
		EClass STYLE_CONTAINER = eINSTANCE.getStyleContainer();

		/**
		 * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE_CONTAINER__STYLES = eINSTANCE.getStyleContainer_Styles();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl <em>Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.StyleImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getStyle()
		 * @generated
		 */
		EClass STYLE = eINSTANCE.getStyle();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__ID = eINSTANCE.getStyle_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__DESCRIPTION = eINSTANCE.getStyle_Description();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__FONT = eINSTANCE.getStyle_Font();

		/**
		 * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__HORIZONTAL_ALIGNMENT = eINSTANCE.getStyle_HorizontalAlignment();

		/**
		 * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__VERTICAL_ALIGNMENT = eINSTANCE.getStyle_VerticalAlignment();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__ANGLE = eINSTANCE.getStyle_Angle();

		/**
		 * The meta object literal for the '<em><b>Stretch H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__STRETCH_H = eINSTANCE.getStyle_StretchH();

		/**
		 * The meta object literal for the '<em><b>Stretch V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__STRETCH_V = eINSTANCE.getStyle_StretchV();

		/**
		 * The meta object literal for the '<em><b>Proportional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__PROPORTIONAL = eINSTANCE.getStyle_Proportional();

		/**
		 * The meta object literal for the '<em><b>Style Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__STYLE_CONTAINER = eINSTANCE.getStyle_StyleContainer();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.impl.AbstractStyleImpl <em>Abstract Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.impl.AbstractStyleImpl
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getAbstractStyle()
		 * @generated
		 */
		EClass ABSTRACT_STYLE = eINSTANCE.getAbstractStyle();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__BACKGROUND = eINSTANCE.getAbstractStyle_Background();

		/**
		 * The meta object literal for the '<em><b>Foreground</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__FOREGROUND = eINSTANCE.getAbstractStyle_Foreground();

		/**
		 * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_WIDTH = eINSTANCE.getAbstractStyle_LineWidth();

		/**
		 * The meta object literal for the '<em><b>Line Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_STYLE = eINSTANCE.getAbstractStyle_LineStyle();

		/**
		 * The meta object literal for the '<em><b>Filled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__FILLED = eINSTANCE.getAbstractStyle_Filled();

		/**
		 * The meta object literal for the '<em><b>Line Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_VISIBLE = eINSTANCE.getAbstractStyle_LineVisible();

		/**
		 * The meta object literal for the '<em><b>Rendering Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__RENDERING_STYLE = eINSTANCE.getAbstractStyle_RenderingStyle();

		/**
		 * The meta object literal for the '<em><b>Transparency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__TRANSPARENCY = eINSTANCE.getAbstractStyle_Transparency();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.LineStyle <em>Line Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.LineStyle
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getLineStyle()
		 * @generated
		 */
		EEnum LINE_STYLE = eINSTANCE.getLineStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.pictograms.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.pictograms.Orientation
		 * @see org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl#getOrientation()
		 * @generated
		 */
		EEnum ORIENTATION = eINSTANCE.getOrientation();

	}

} //PictogramsPackage
