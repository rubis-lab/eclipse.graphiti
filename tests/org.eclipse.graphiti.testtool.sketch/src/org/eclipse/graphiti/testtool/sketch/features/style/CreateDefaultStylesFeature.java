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
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class CreateDefaultStylesFeature.
 */
public class CreateDefaultStylesFeature extends AbstractCustomFeature {

	private static final String BLUE = "Blue";

	/**
	 * Instantiates a new creates the default styles feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public CreateDefaultStylesFeature(IFeatureProvider fp) {
		super(fp);
	}

	public void execute(ICustomContext context) {
		Diagram diagram = getDiagram();

		IGaService gaService = Graphiti.getGaService();
		Style style_blue = gaService.createStyle(diagram, BLUE);
		style_blue.setDescription(BLUE);
		style_blue.setBackground(gaService.manageColor(diagram, IColorConstant.LIGHT_BLUE));
		style_blue.setForeground(gaService.manageColor(diagram, IColorConstant.DARK_BLUE));
		style_blue.setLineWidth(2);
		style_blue.setLineVisible(true);
		style_blue.setFilled(true);

		Style style_blue_1 = gaService.createStyle(style_blue, "Blue 1");
		style_blue_1.setDescription("Blue 1");
		style_blue_1.setBackground(null);
		style_blue_1.setForeground(null);
		style_blue_1.setLineWidth(4);
		style_blue_1.setLineVisible(true);
		style_blue_1.setFilled(true);

		Style style_blue_2 = gaService.createStyle(style_blue, "Blue red 2");
		style_blue_2.setDescription("Blue red 2");
		style_blue_2.setBackground(null);
		style_blue_2.setForeground(gaService.manageColor(diagram, IColorConstant.RED));
		style_blue_2.setLineWidth(8);
		style_blue_2.setLineVisible(true);
		style_blue_2.setFilled(true);
	}

	@Override
	public String getName() {
		return "Create Default Styles";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes.length == 1) {
			if (pes[0] instanceof Diagram) {
				return Graphiti.getGaService().findStyle(getDiagram(), BLUE) == null;
			}
		}
		return false;
	}

}
