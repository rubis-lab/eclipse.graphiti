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
package org.eclipse.graphiti.testtool.ecore.features.pack;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class RenamePackageFeature.
 */
public class RenamePackageFeature extends AbstractCustomFeature {

	private static final String NAME = "Rename Package...";

	private static final String DESCRIPTION = "Change Package Name";

	/**
	 * Instantiates a new rename package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public RenamePackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		// allow rename if exactly one pictogram element (which represents a
		// package) is selected
		boolean ret = false;
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pes[0]);
			if (bo instanceof EPackage) {
				ret = true;
			}
		}
		return ret;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pes[0]);
			if (bo instanceof EPackage) {
				EPackage p = (EPackage) bo;
				String currentPackageName = p.getName();
				// ask user for a new package name
				String newPackageName = SampleUtil.askString(NAME, DESCRIPTION, currentPackageName);
				if (newPackageName != null) {
					p.setName(newPackageName);
				}
			}
		}
	}
}
