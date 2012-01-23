/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class SetImageAttributesFeature.
 */
public class SetImageAttributesFeature extends AbstractCustomFeature {

	public static final int ATTRIBUTE_STRETCH_HORIZONTALLY = 0;
	public static final int ATTRIBUTE_STRETCH_VERTICALLY = 1;
	public static final int ATTRIBUTE_PROPORTIONAL = 2;

	private Image image;
	private int attribute;

	public SetImageAttributesFeature(IFeatureProvider fp, ICustomContext context, int attribute) {
		super(fp);
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0 && pes[0] instanceof Shape && pes[0].getGraphicsAlgorithm() instanceof Image) {
			this.image = (org.eclipse.graphiti.mm.algorithms.Image) pes[0].getGraphicsAlgorithm();
		}
		this.attribute = attribute;
	}

	@Override
	public String getName() {
		EAttribute attrInst = getAttrInst();
		Object value;
		if (image != null) {
			value = image.eGet(attrInst);
		} else {
			value = "<n/a>";
		}
		switch (attribute) {
		case ATTRIBUTE_STRETCH_HORIZONTALLY:
			return "Stretch Horizontally (current value: " + value.toString() + ")";
		case ATTRIBUTE_STRETCH_VERTICALLY:
			return "Stretch Vertically (current value: " + value.toString() + ")";
		case ATTRIBUTE_PROPORTIONAL:
			return "Proportional (current value: " + value.toString() + ")";
		default:
			return "<Error>";
		}
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0 && pes[0] instanceof Shape) {
			org.eclipse.graphiti.mm.algorithms.Image image = (org.eclipse.graphiti.mm.algorithms.Image) pes[0]
					.getGraphicsAlgorithm();

			EAttribute attrInst = getAttrInst();

			Boolean value = (Boolean) image.eGet(attrInst);
			image.eSet(attrInst, !value.booleanValue());
		}
	}

	private EAttribute getAttrInst() {
		EAttribute attrInst;
		switch (attribute) {
		case ATTRIBUTE_STRETCH_HORIZONTALLY:
			attrInst = AlgorithmsPackage.eINSTANCE.getImage_StretchH();
			break;
		case ATTRIBUTE_STRETCH_VERTICALLY:
			attrInst = AlgorithmsPackage.eINSTANCE.getImage_StretchV();
			break;
		case ATTRIBUTE_PROPORTIONAL:
			attrInst = AlgorithmsPackage.eINSTANCE.getImage_Proportional();
			break;
		default:
			attrInst = null;
			break;
		}
		return attrInst;
	}

	@Override
	public boolean isAvailable(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			if (pes == null || pes.length > 1) {
				return false;
			}
			PictogramElement pe = pes[0];
			if (pe instanceof Shape && pe.getGraphicsAlgorithm() instanceof org.eclipse.graphiti.mm.algorithms.Image
					&& !(pe instanceof Diagram)) {
				return true;
			}
		}
		return false;
	}
}
