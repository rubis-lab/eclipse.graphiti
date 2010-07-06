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
package org.eclipse.graphiti.sample.sketch.features.style;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Style;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class MakeStyleFeature.
 */
public class MakeStyleFeature extends AbstractCustomFeature {

	/**
	 * Instantiates a new make style feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public MakeStyleFeature(IFeatureProvider fp) {
		super(fp);
	}

	public void execute(ICustomContext context) {
		String styleId = Double.toString(Math.random());
		Style style = Graphiti.getGaCreateService().createStyle(getDiagram(), styleId);
		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();

			if (i == 0) {
				// copy attributes from GA to style
				style.setBackground(ga.getBackground());
				style.setForeground(ga.getForeground());
				style.setLineStyle(ga.getLineStyle());
				Graphiti.getGaService().deleteRenderingStyle(style); // delete the old style
				// first to avoid garbage
				style.setRenderingStyle(ga.getRenderingStyle());
				style.setLineWidth(ga.getLineWidth());
				style.setDescription(styleId);
			}

			ga.setStyle(style);

			// unset/reset attributes in GA
			ga.setBackground(null);

			ga.setForeground(null);

			ga.setLineStyle(null);

			ga.setRenderingStyle(null);

			ga.setLineWidth(null);
		}

	}

	@Override
	public String getName() {
		return "Make Style";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				return (ga.getStyle() == null);
			}
		}
		return false;
	}

}
