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
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class TestDirectEditingClassFeature.
 */
public class TestDirectEditingClassFeature extends AbstractDirectEditingFeature {

	private static final String SPACE = " ";

	/**
	 * Instantiates a new test direct editing class feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestDirectEditingClassFeature(IFeatureProvider fp) {
		super(fp);
	}

	public int getEditingType() {
		return TYPE_TEXT;
	}

	public String getInitialValue(IDirectEditingContext context) {
		String ret = null;
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof EClass) {
			EClass ge = (EClass) bo;
			ret = ge.getName();
		}
		return ret;
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value.length() < 1) {
			return "Please enter any text as class name.";
		}
		if (value.contains(SPACE)) {
			return "Spaces are not allowed in class names.";
		}
		if (value.contains("\n")) {
			return "Line breakes are not allowed in class names.";
		}
		if (value.contains(":")) {
			return "Colons are not allowed in class names.";
		}

		return null;
	}

	public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof EClass) {
			EClass ge = (EClass) bo;
			ge.setName(value);
			if (pe instanceof Shape) {
				updatePictogramElement(((Shape) pe).getContainer());
			}
		}
	}

	@Override
	public boolean canExecute(IContext context) {
		boolean ret = super.canExecute(context);
		if (context instanceof IDirectEditingContext) {
			IDirectEditingContext deContext = (IDirectEditingContext) context;
			PictogramElement pe = deContext.getPictogramElement();
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (bo instanceof EClass) {
				ret = true;
			}
		}
		return ret;
	}
}
