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
package org.eclipse.graphiti.testtool.sketch.features.style;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class UnsetStyleFeature.
 */
public class UnsetStyleFeature extends AbstractCustomFeature {
	private static final String NAME = "Unset Style";

	/**
	 * Instantiates a new unset style feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public UnsetStyleFeature(IFeatureProvider fp) {
		super(fp);
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			ga.setStyle(null);
		}

	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				return true;
			}
		}
		return false;
	}
}
