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
package org.eclipse.graphiti.sample.ecore.features.pack;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;

/**
 * The Class TestMovePackageFeature.
 */
public class TestMovePackageFeature extends DefaultMoveShapeFeature {

	/**
	 * Instantiates a new test move package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestMovePackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {

		boolean ret = super.canMoveShape(context);

		// check further details only if move allowed by default feature
		if (ret) {

			// don't allow move if package name has the length of one
			Object bo = getBusinessObjectForPictogramElement(context.getShape());
			if (bo instanceof EPackage) {
				EPackage p = (EPackage) bo;
				if (p.getName() != null && p.getName().length() == 1) {
					ret = false;
				}
			}
		}
		return ret;
	}
}
