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
package org.eclipse.graphiti.testtool.ecore.features.clazz;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Class TestCreateClassFeature.
 */
public class TestCreateClassFeature extends AbstractCreateFeature {

	// private static final String TITLE = "Create Class";
	//
	// private static final String USER_QUESTION = "Enter new class name";

	/**
	 * Instantiates a new test create class feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestCreateClassFeature(IFeatureProvider fp) {
		// set name and description of the creation feature
		super(fp, "Class", "Create Class");
	}

	public boolean canCreate(ICreateContext context) {

		ContainerShape targetContainer = context.getTargetContainer();

		if (targetContainer instanceof Diagram) {
			return true;
		}

		if (getBusinessObjectForPictogramElement(targetContainer) instanceof EPackage) {
			return true;
		}

		return false;
	}

	public Object[] create(ICreateContext context) {
		// ask user for Class name
		// String newClassName = getPlatformService().askString(TITLE,
		// USER_QUESTION, "");
		// if (newClassName == null || newClassName.trim().length() == 0) {
		// return EMPTY;
		// }

		String newClassName = "NewClass";

		// create new Class
		EClass newClass = EcoreFactory.eINSTANCE.createEClass();
		newClass.setName(newClassName);
		getDiagram().eResource().getContents().add(newClass);

		// do the add
		addGraphicalRepresentation(context, newClass);

		// return newly created business object(s)
		return new Object[] { newClass };
	}
}
