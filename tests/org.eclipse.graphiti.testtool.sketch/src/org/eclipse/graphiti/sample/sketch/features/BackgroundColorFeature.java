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
/*
 * Created on 12.12.2005
 */
package org.eclipse.graphiti.sample.sketch.features;

import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.datatypes.Color;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Style;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class BackgroundColorFeature.
 */
public class BackgroundColorFeature extends AbstractColorFeature {

	private static final String NAME = "Background Color...";

	private static final String DESCRIPTION = "Modify Background Color";

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public BackgroundColorFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		boolean ret = false;
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				if (ga.getBackground() != null) {
					ret = true;
				} else {
					Style style = ga.getStyle();
					if (style != null) {
						if (style.getBackground() != null) {
							ret = true;
						}
					}
				}
			}
		}
		return ret;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				Color newColor;
				{
					Color oldBackground = Graphiti.getGaService().getBackgroundColor(ga, true);
					newColor = SampleUtil.editColor(oldBackground);
					if (newColor == null) {
						return;
					}
				}

				for (int i = 0; i < pes.length; i++) {
					PictogramElement pe = pes[i];
					GraphicsAlgorithm currentGa = pe.getGraphicsAlgorithm();
					if (currentGa.getBackground() != null) {
						currentGa.setBackground(newColor);
					} else {
						currentGa.getStyle().setBackground(newColor);
					}
				}
			}
		}
	}
}