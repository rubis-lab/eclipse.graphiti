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
 * $Id: PictogramsAdapterFactory.java,v 1.1 2010/06/16 13:25:10 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.pictograms.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage
 * @generated
 */
public class PictogramsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PictogramsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PictogramsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PictogramsSwitch<Adapter> modelSwitch =
		new PictogramsSwitch<Adapter>() {
			@Override
			public Adapter caseShape(Shape object) {
				return createShapeAdapter();
			}
			@Override
			public Adapter caseContainerShape(ContainerShape object) {
				return createContainerShapeAdapter();
			}
			@Override
			public Adapter caseDiagram(Diagram object) {
				return createDiagramAdapter();
			}
			@Override
			public Adapter caseGraphicsAlgorithm(GraphicsAlgorithm object) {
				return createGraphicsAlgorithmAdapter();
			}
			@Override
			public Adapter casePolyline(Polyline object) {
				return createPolylineAdapter();
			}
			@Override
			public Adapter caseEllipse(Ellipse object) {
				return createEllipseAdapter();
			}
			@Override
			public Adapter casePictogramElement(PictogramElement object) {
				return createPictogramElementAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseAnchor(Anchor object) {
				return createAnchorAdapter();
			}
			@Override
			public Adapter caseHorizontalConnection(HorizontalConnection object) {
				return createHorizontalConnectionAdapter();
			}
			@Override
			public Adapter caseVerticalConnection(VerticalConnection object) {
				return createVerticalConnectionAdapter();
			}
			@Override
			public Adapter caseAnchorContainer(AnchorContainer object) {
				return createAnchorContainerAdapter();
			}
			@Override
			public Adapter caseFixPointAnchor(FixPointAnchor object) {
				return createFixPointAnchorAdapter();
			}
			@Override
			public Adapter caseBoxRelativeAnchor(BoxRelativeAnchor object) {
				return createBoxRelativeAnchorAdapter();
			}
			@Override
			public Adapter caseChopboxAnchor(ChopboxAnchor object) {
				return createChopboxAnchorAdapter();
			}
			@Override
			public Adapter caseLinkToDiagram(LinkToDiagram object) {
				return createLinkToDiagramAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public Adapter caseText(Text object) {
				return createTextAdapter();
			}
			@Override
			public Adapter caseConnectionDecorator(ConnectionDecorator object) {
				return createConnectionDecoratorAdapter();
			}
			@Override
			public Adapter caseFreeFormConnection(FreeFormConnection object) {
				return createFreeFormConnectionAdapter();
			}
			@Override
			public Adapter caseDirectConnection(DirectConnection object) {
				return createDirectConnectionAdapter();
			}
			@Override
			public Adapter caseManhattanConnection(ManhattanConnection object) {
				return createManhattanConnectionAdapter();
			}
			@Override
			public Adapter casePolygon(Polygon object) {
				return createPolygonAdapter();
			}
			@Override
			public Adapter caseRectangle(Rectangle object) {
				return createRectangleAdapter();
			}
			@Override
			public Adapter caseRoundedRectangle(RoundedRectangle object) {
				return createRoundedRectangleAdapter();
			}
			@Override
			public Adapter caseFont(Font object) {
				return createFontAdapter();
			}
			@Override
			public Adapter caseRenderingStyle(RenderingStyle object) {
				return createRenderingStyleAdapter();
			}
			@Override
			public Adapter caseImage(Image object) {
				return createImageAdapter();
			}
			@Override
			public Adapter caseFanConnection(FanConnection object) {
				return createFanConnectionAdapter();
			}
			@Override
			public Adapter casePlatformGraphicsAlgorithm(PlatformGraphicsAlgorithm object) {
				return createPlatformGraphicsAlgorithmAdapter();
			}
			@Override
			public Adapter caseGraphicsAlgorithmContainer(GraphicsAlgorithmContainer object) {
				return createGraphicsAlgorithmContainerAdapter();
			}
			@Override
			public Adapter caseAbstractText(AbstractText object) {
				return createAbstractTextAdapter();
			}
			@Override
			public Adapter caseMultiText(MultiText object) {
				return createMultiTextAdapter();
			}
			@Override
			public Adapter casePropertyContainer(PropertyContainer object) {
				return createPropertyContainerAdapter();
			}
			@Override
			public Adapter caseStyleContainer(StyleContainer object) {
				return createStyleContainerAdapter();
			}
			@Override
			public Adapter caseStyle(Style object) {
				return createStyleAdapter();
			}
			@Override
			public Adapter caseAbstractStyle(AbstractStyle object) {
				return createAbstractStyleAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Shape
	 * @generated
	 */
	public Adapter createShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ContainerShape <em>Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ContainerShape
	 * @generated
	 */
	public Adapter createContainerShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram
	 * @generated
	 */
	public Adapter createDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm <em>Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm
	 * @generated
	 */
	public Adapter createGraphicsAlgorithmAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Polyline
	 * @generated
	 */
	public Adapter createPolylineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Ellipse
	 * @generated
	 */
	public Adapter createEllipseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement
	 * @generated
	 */
	public Adapter createPictogramElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Anchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor
	 * @generated
	 */
	public Adapter createAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.HorizontalConnection <em>Horizontal Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.HorizontalConnection
	 * @generated
	 */
	public Adapter createHorizontalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.VerticalConnection <em>Vertical Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.VerticalConnection
	 * @generated
	 */
	public Adapter createVerticalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer <em>Anchor Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.AnchorContainer
	 * @generated
	 */
	public Adapter createAnchorContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor <em>Fix Point Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.FixPointAnchor
	 * @generated
	 */
	public Adapter createFixPointAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor <em>Box Relative Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor
	 * @generated
	 */
	public Adapter createBoxRelativeAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ChopboxAnchor <em>Chopbox Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ChopboxAnchor
	 * @generated
	 */
	public Adapter createChopboxAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.LinkToDiagram <em>Link To Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.LinkToDiagram
	 * @generated
	 */
	public Adapter createLinkToDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Text
	 * @generated
	 */
	public Adapter createTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator <em>Connection Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator
	 * @generated
	 */
	public Adapter createConnectionDecoratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection <em>Free Form Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.FreeFormConnection
	 * @generated
	 */
	public Adapter createFreeFormConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.DirectConnection <em>Direct Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.DirectConnection
	 * @generated
	 */
	public Adapter createDirectConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ManhattanConnection <em>Manhattan Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ManhattanConnection
	 * @generated
	 */
	public Adapter createManhattanConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Polygon <em>Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Polygon
	 * @generated
	 */
	public Adapter createPolygonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Rectangle
	 * @generated
	 */
	public Adapter createRectangleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.RoundedRectangle <em>Rounded Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.RoundedRectangle
	 * @generated
	 */
	public Adapter createRoundedRectangleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Font <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Font
	 * @generated
	 */
	public Adapter createFontAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.RenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.RenderingStyle
	 * @generated
	 */
	public Adapter createRenderingStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Image
	 * @generated
	 */
	public Adapter createImageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.FanConnection <em>Fan Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.FanConnection
	 * @generated
	 */
	public Adapter createFanConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm <em>Platform Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm
	 * @generated
	 */
	public Adapter createPlatformGraphicsAlgorithmAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer <em>Graphics Algorithm Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer
	 * @generated
	 */
	public Adapter createGraphicsAlgorithmContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.AbstractText <em>Abstract Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractText
	 * @generated
	 */
	public Adapter createAbstractTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.MultiText <em>Multi Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.MultiText
	 * @generated
	 */
	public Adapter createMultiTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.PropertyContainer <em>Property Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.PropertyContainer
	 * @generated
	 */
	public Adapter createPropertyContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.StyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.StyleContainer
	 * @generated
	 */
	public Adapter createStyleContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Style
	 * @generated
	 */
	public Adapter createStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.AbstractStyle <em>Abstract Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.AbstractStyle
	 * @generated
	 */
	public Adapter createAbstractStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PictogramsAdapterFactory
