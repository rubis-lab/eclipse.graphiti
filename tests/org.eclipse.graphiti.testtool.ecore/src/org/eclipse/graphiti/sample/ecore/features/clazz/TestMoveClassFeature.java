/*******************************************************************************
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
 *******************************************************************************/
package org.eclipse.graphiti.sample.ecore.features.clazz;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;

/**
 * The Class TestMoveClassFeature.
 */
public class TestMoveClassFeature extends DefaultMoveShapeFeature {

	/**
	 * Instantiates a new test move class feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestMoveClassFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {

		if (getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof EClass) {
			return false;
		}
		return true;
	}

}
