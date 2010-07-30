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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class TransparencyFeature.
 */
public class TransparencyFeature extends AbstractCustomFeature {

	private static final String PERCENT = " %";

	/**
	 * The transparency.
	 */
	double transparency;

	/**
	 * Instantiates a new transparency feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param transparency
	 *            the transparency
	 */
	public TransparencyFeature(IFeatureProvider fp, double transparency) {
		super(fp);
		this.transparency = transparency;
	}

	public void execute(ICustomContext context) {

		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			if (pe != null && pe.getGraphicsAlgorithm() != null) {
				GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
				ga.setTransparency(transparency / 100);
			}
		}
	}

	@Override
	public String getName() {
		// return Double.toString(transparency) + PERCENT;
		return Math.round(transparency) + PERCENT;
	}

	@Override
	public boolean isAvailable(IContext context) {
		boolean ret = false;
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				if (pe instanceof Shape && pe.getGraphicsAlgorithm() != null && !(pe instanceof Diagram)) {
					ret = true;
				} else {
					return false;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}
}
