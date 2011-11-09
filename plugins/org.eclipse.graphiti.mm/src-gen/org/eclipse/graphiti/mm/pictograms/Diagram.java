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
 */
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getGridUnit <em>Grid Unit</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getDiagramTypeId <em>Diagram Type Id</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#isSnapToGrid <em>Snap To Grid</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getColors <em>Colors</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getFonts <em>Fonts</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getPictogramLinks <em>Pictogram Links</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getVerticalGridUnit <em>Vertical Grid Unit</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Diagram#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram()
 * @model features="showGuides" 
 *        showGuidesUnique="false" showGuidesDataType="org.eclipse.emf.ecore.EBoolean" showGuidesRequired="true" showGuidesOrdered="false" showGuidesSuppressedGetVisibility="true" showGuidesSuppressedSetVisibility="true"
 * @generated
 */
public interface Diagram extends ContainerShape, StyleContainer {
	/**
	 * Returns the value of the '<em><b>Grid Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Unit</em>' attribute.
	 * @see #setGridUnit(int)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_GridUnit()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getGridUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getGridUnit <em>Grid Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Unit</em>' attribute.
	 * @see #getGridUnit()
	 * @generated
	 */
	void setGridUnit(int value);

	/**
	 * Returns the value of the '<em><b>Diagram Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram Type Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram Type Id</em>' attribute.
	 * @see #setDiagramTypeId(String)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_DiagramTypeId()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getDiagramTypeId();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getDiagramTypeId <em>Diagram Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram Type Id</em>' attribute.
	 * @see #getDiagramTypeId()
	 * @generated
	 */
	void setDiagramTypeId(String value);

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.Connection}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Connection#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_Connections()
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getParent
	 * @model opposite="parent" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<Connection> getConnections();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Snap To Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snap To Grid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snap To Grid</em>' attribute.
	 * @see #setSnapToGrid(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_SnapToGrid()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isSnapToGrid();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Diagram#isSnapToGrid <em>Snap To Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snap To Grid</em>' attribute.
	 * @see #isSnapToGrid()
	 * @generated
	 */
	void setSnapToGrid(boolean value);

	/**
	 * Returns the value of the '<em><b>Colors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Colors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Colors</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_Colors()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<Color> getColors();

	/**
	 * Returns the value of the '<em><b>Fonts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.Font}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fonts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fonts</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_Fonts()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<Font> getFonts();

	/**
	 * Returns the value of the '<em><b>Pictogram Links</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.PictogramLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pictogram Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pictogram Links</em>' reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_PictogramLinks()
	 * @model ordered="false"
	 * @generated
	 */
	EList<PictogramLink> getPictogramLinks();

	/**
	 * Returns the value of the '<em><b>Vertical Grid Unit</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertical Grid Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertical Grid Unit</em>' attribute.
	 * @see #setVerticalGridUnit(int)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_VerticalGridUnit()
	 * @model default="-1"
	 * @generated
	 */
	int getVerticalGridUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getVerticalGridUnit <em>Vertical Grid Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertical Grid Unit</em>' attribute.
	 * @see #getVerticalGridUnit()
	 * @generated
	 */
	void setVerticalGridUnit(int value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 0.9
	 *        <p>
	 *        If the meaning of the '<em>Version</em>' attribute isn't clear,
	 *        there really should be more of a description here...
	 *        </p>
	 *        <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getDiagram_Version()
	 * @model default="" suppressedSetVisibility="true"
	 * @generated
	 */
	String getVersion();

} // Diagram
