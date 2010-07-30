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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class CornerDimensionFeature.
 */
public class CornerDimensionFeature extends AbstractCustomFeature {

	/**
	 * The dimension.
	 */
	Dimension dimension;

	/**
	 * Instantiates a new corner dimension feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param dimension
	 *            the dimension
	 */
	public CornerDimensionFeature(IFeatureProvider fp, Dimension dimension) {
		super(fp);
		this.dimension = dimension;
	}

	@Override
	public String getName() {
		return "(w=" + dimension.width + ", h=" + dimension.height + ")";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			if (pe != null && pe.getGraphicsAlgorithm() != null) {
				RoundedRectangle rect = (RoundedRectangle) pe.getGraphicsAlgorithm();
				rect.setCornerWidth(dimension.width);
				rect.setCornerHeight(dimension.height);
			}
		}
	}

	@Override
	public boolean isAvailable(IContext context) {
		boolean ret = false;
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				if (pe instanceof Shape && pe.getGraphicsAlgorithm() instanceof RoundedRectangle && !(pe instanceof Diagram)) {
					ret = true;
				} else {
					return false;
				}
			}
		}
		return ret;
	}
}
