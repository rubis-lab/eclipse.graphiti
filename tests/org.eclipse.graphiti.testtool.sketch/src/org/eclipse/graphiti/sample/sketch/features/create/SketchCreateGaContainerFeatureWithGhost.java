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
package org.eclipse.graphiti.sample.sketch.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.sample.sketch.SketchUtil;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class SketchCreateGaContainerFeature.
 */
public class SketchCreateGaContainerFeatureWithGhost extends SketchCreateGaFeature {

	private static final int FRAME = 10;

	/**
	 * Instantiates a new sketch create ga container feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 * @param gaType
	 *            the ga type
	 */
	public SketchCreateGaContainerFeatureWithGhost(IFeatureProvider fp, String name, String description, Class<GraphicsAlgorithm> gaType) {
		super(fp, name, description, gaType);
	}

	@Override
	protected AnchorContainer createAnchorContainer(ContainerShape targetContainer) {
		IPeService peService = Graphiti.getPeService();
		ContainerShape cs = peService.createContainerShape(targetContainer, true);

		String value = null;
		if (RoundedRectangle.class.equals(getGaType())) {
			value = SketchUtil.GA_ROUNDED_RECTANGLE;
		} else if (Rectangle.class.equals(getGaType())) {
			value = SketchUtil.GA_RECTANGLE;
		} else if (Ellipse.class.equals(getGaType())) {
			value = SketchUtil.GA_ELLIPSE;
		}

		if (value != null) {
			peService.setPropertyValue(cs, SketchUtil.GA_TAG, value);
		}

		peService.setPropertyValue(cs, SketchUtil.GHOST_TAG, "");
		peService.setPropertyValue(cs, SketchUtil.CONTAINER_TAG, "");

		return cs;
	}

	@Override
	protected GraphicsAlgorithm createGa(GraphicsAlgorithmContainer gac, int width, int height) {
		IGaService gaService=Graphiti.getGaService();
		final Rectangle ghostGa = gaService.createInvisibleRectangle((PictogramElement) gac);
		gaService.setSize(ghostGa, width, height);
		final int newWidth = width - 2 * FRAME;
		final int newHeight = height - 2 * FRAME;
		final GraphicsAlgorithm innerGa = super.createGa(ghostGa, newWidth, newHeight);
		gaService.setLocationAndSize(innerGa, FRAME, FRAME, newWidth, newHeight);
		innerGa.setBackground(gaService.manageColor(getDiagram(), IColorConstant.BLUE));

		// inner inner GA
		final Ellipse innerInnerGa = gaService.createEllipse(innerGa);
		innerInnerGa.setBackground(gaService.manageColor(getDiagram(), IColorConstant.ORANGE));
		innerInnerGa.setForeground(gaService.manageColor(getDiagram(), IColorConstant.GREEN));
		innerInnerGa.setLineWidth(3);

		return ghostGa;
	}
}
