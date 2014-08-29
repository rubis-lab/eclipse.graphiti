/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Benjamin Schmeling - mwenz - Bug 367483 - Support composite connections
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.util;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.pictograms.*;
import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage
 * @generated
 */
public class PictogramsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PictogramsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsSwitch() {
		if (modelPackage == null) {
			modelPackage = PictogramsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case PictogramsPackage.SHAPE: {
				Shape shape = (Shape)theEObject;
				T result = caseShape(shape);
				if (result == null) result = caseAnchorContainer(shape);
				if (result == null) result = casePictogramElement(shape);
				if (result == null) result = caseGraphicsAlgorithmContainer(shape);
				if (result == null) result = casePropertyContainer(shape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONTAINER_SHAPE: {
				ContainerShape containerShape = (ContainerShape)theEObject;
				T result = caseContainerShape(containerShape);
				if (result == null) result = caseShape(containerShape);
				if (result == null) result = caseAnchorContainer(containerShape);
				if (result == null) result = casePictogramElement(containerShape);
				if (result == null) result = caseGraphicsAlgorithmContainer(containerShape);
				if (result == null) result = casePropertyContainer(containerShape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.DIAGRAM: {
				Diagram diagram = (Diagram)theEObject;
				T result = caseDiagram(diagram);
				if (result == null) result = caseContainerShape(diagram);
				if (result == null) result = caseStyleContainer(diagram);
				if (result == null) result = caseShape(diagram);
				if (result == null) result = caseAnchorContainer(diagram);
				if (result == null) result = casePictogramElement(diagram);
				if (result == null) result = caseGraphicsAlgorithmContainer(diagram);
				if (result == null) result = casePropertyContainer(diagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.PICTOGRAM_ELEMENT: {
				PictogramElement pictogramElement = (PictogramElement)theEObject;
				T result = casePictogramElement(pictogramElement);
				if (result == null) result = caseGraphicsAlgorithmContainer(pictogramElement);
				if (result == null) result = casePropertyContainer(pictogramElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseAnchorContainer(connection);
				if (result == null) result = casePictogramElement(connection);
				if (result == null) result = caseGraphicsAlgorithmContainer(connection);
				if (result == null) result = casePropertyContainer(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.ANCHOR: {
				Anchor anchor = (Anchor)theEObject;
				T result = caseAnchor(anchor);
				if (result == null) result = casePictogramElement(anchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(anchor);
				if (result == null) result = casePropertyContainer(anchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.ANCHOR_CONTAINER: {
				AnchorContainer anchorContainer = (AnchorContainer)theEObject;
				T result = caseAnchorContainer(anchorContainer);
				if (result == null) result = casePictogramElement(anchorContainer);
				if (result == null) result = caseGraphicsAlgorithmContainer(anchorContainer);
				if (result == null) result = casePropertyContainer(anchorContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.FIX_POINT_ANCHOR: {
				FixPointAnchor fixPointAnchor = (FixPointAnchor)theEObject;
				T result = caseFixPointAnchor(fixPointAnchor);
				if (result == null) result = caseAdvancedAnchor(fixPointAnchor);
				if (result == null) result = caseAnchor(fixPointAnchor);
				if (result == null) result = casePictogramElement(fixPointAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(fixPointAnchor);
				if (result == null) result = casePropertyContainer(fixPointAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.BOX_RELATIVE_ANCHOR: {
				BoxRelativeAnchor boxRelativeAnchor = (BoxRelativeAnchor)theEObject;
				T result = caseBoxRelativeAnchor(boxRelativeAnchor);
				if (result == null) result = caseAdvancedAnchor(boxRelativeAnchor);
				if (result == null) result = caseAnchor(boxRelativeAnchor);
				if (result == null) result = casePictogramElement(boxRelativeAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(boxRelativeAnchor);
				if (result == null) result = casePropertyContainer(boxRelativeAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CHOPBOX_ANCHOR: {
				ChopboxAnchor chopboxAnchor = (ChopboxAnchor)theEObject;
				T result = caseChopboxAnchor(chopboxAnchor);
				if (result == null) result = caseAnchor(chopboxAnchor);
				if (result == null) result = casePictogramElement(chopboxAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(chopboxAnchor);
				if (result == null) result = casePropertyContainer(chopboxAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONNECTION_DECORATOR: {
				ConnectionDecorator connectionDecorator = (ConnectionDecorator)theEObject;
				T result = caseConnectionDecorator(connectionDecorator);
				if (result == null) result = caseShape(connectionDecorator);
				if (result == null) result = caseAnchorContainer(connectionDecorator);
				if (result == null) result = casePictogramElement(connectionDecorator);
				if (result == null) result = caseGraphicsAlgorithmContainer(connectionDecorator);
				if (result == null) result = casePropertyContainer(connectionDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.FREE_FORM_CONNECTION: {
				FreeFormConnection freeFormConnection = (FreeFormConnection)theEObject;
				T result = caseFreeFormConnection(freeFormConnection);
				if (result == null) result = caseConnection(freeFormConnection);
				if (result == null) result = caseAnchorContainer(freeFormConnection);
				if (result == null) result = casePictogramElement(freeFormConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(freeFormConnection);
				if (result == null) result = casePropertyContainer(freeFormConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.MANHATTAN_CONNECTION: {
				ManhattanConnection manhattanConnection = (ManhattanConnection)theEObject;
				T result = caseManhattanConnection(manhattanConnection);
				if (result == null) result = caseConnection(manhattanConnection);
				if (result == null) result = caseAnchorContainer(manhattanConnection);
				if (result == null) result = casePictogramElement(manhattanConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(manhattanConnection);
				if (result == null) result = casePropertyContainer(manhattanConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.PICTOGRAM_LINK: {
				PictogramLink pictogramLink = (PictogramLink)theEObject;
				T result = casePictogramLink(pictogramLink);
				if (result == null) result = casePropertyContainer(pictogramLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.ADVANCED_ANCHOR: {
				AdvancedAnchor advancedAnchor = (AdvancedAnchor)theEObject;
				T result = caseAdvancedAnchor(advancedAnchor);
				if (result == null) result = caseAnchor(advancedAnchor);
				if (result == null) result = casePictogramElement(advancedAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(advancedAnchor);
				if (result == null) result = casePropertyContainer(advancedAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CURVED_CONNECTION: {
				CurvedConnection curvedConnection = (CurvedConnection)theEObject;
				T result = caseCurvedConnection(curvedConnection);
				if (result == null) result = caseConnection(curvedConnection);
				if (result == null) result = caseAnchorContainer(curvedConnection);
				if (result == null) result = casePictogramElement(curvedConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(curvedConnection);
				if (result == null) result = casePropertyContainer(curvedConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.COMPOSITE_CONNECTION: {
				CompositeConnection compositeConnection = (CompositeConnection)theEObject;
				T result = caseCompositeConnection(compositeConnection);
				if (result == null) result = caseConnection(compositeConnection);
				if (result == null) result = caseAnchorContainer(compositeConnection);
				if (result == null) result = casePictogramElement(compositeConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(compositeConnection);
				if (result == null) result = casePropertyContainer(compositeConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShape(Shape object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerShape(ContainerShape object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagram(Diagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pictogram Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pictogram Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePictogramElement(PictogramElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnchor(Anchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anchor Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anchor Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnchorContainer(AnchorContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix Point Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix Point Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixPointAnchor(FixPointAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Box Relative Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Box Relative Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoxRelativeAnchor(BoxRelativeAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chopbox Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chopbox Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChopboxAnchor(ChopboxAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionDecorator(ConnectionDecorator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Free Form Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Free Form Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFreeFormConnection(FreeFormConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Manhattan Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Manhattan Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManhattanConnection(ManhattanConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pictogram Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pictogram Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePictogramLink(PictogramLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Advanced Anchor</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch.
	 * 
	 * @since 0.9 <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Advanced Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdvancedAnchor(AdvancedAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Curved Connection</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch.
	 * 
	 * @since 0.9 <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Curved Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCurvedConnection(CurvedConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Connection</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * @since 0.9 <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeConnection(CompositeConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyContainer(PropertyContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graphics Algorithm Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graphics Algorithm Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphicsAlgorithmContainer(GraphicsAlgorithmContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Style Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Style Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStyleContainer(StyleContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //PictogramsSwitch
