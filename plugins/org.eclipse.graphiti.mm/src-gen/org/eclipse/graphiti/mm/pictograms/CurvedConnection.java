/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz Bug 352119 - initial API, implementation and documentation contributed by Benjamin Schmeling
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Curved Connection</b></em>'.
 * 
 * @since 0.9
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.CurvedConnection#getControlPoints <em>Control Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getCurvedConnection()
 * @model
 * @generated
 */
public interface CurvedConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Control Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Points</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getCurvedConnection_ControlPoints()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<PrecisionPoint> getControlPoints();

} // CurvedConnection
