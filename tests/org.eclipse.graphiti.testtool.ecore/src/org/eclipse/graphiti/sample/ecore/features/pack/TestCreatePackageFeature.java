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
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Class TestCreatePackageFeature.
 */
public class TestCreatePackageFeature extends AbstractCreateFeature {

	private static final String TITLE = "Create Package";

	private static final String USER_QUESTION = "Enter new package name";

	/**
	 * Instantiates a new test create package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestCreatePackageFeature(IFeatureProvider fp) {
		// set name and description of the creation feature
		super(fp, "Package", "Create Package");
	}

	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	public Object[] create(ICreateContext context) {
		// ask user for package name
		String newPackageName = SampleUtil.askString(TITLE, USER_QUESTION, "");
		if (newPackageName == null || newPackageName.trim().length() == 0) {
			return EMPTY;
		}

		// create new package
		EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
		newPackage.setName(newPackageName);

		getDiagram().eResource().getContents().add(newPackage);

		// do the add
		addGraphicalRepresentation(context, newPackage);

		// return newly created business object(s)
		return new Object[] { newPackage };
	}
}
