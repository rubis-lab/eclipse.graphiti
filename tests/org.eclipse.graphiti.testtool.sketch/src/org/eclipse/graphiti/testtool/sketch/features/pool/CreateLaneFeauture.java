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
package org.eclipse.graphiti.testtool.sketch.features.pool;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class CreateLaneFeauture.
 */
public class CreateLaneFeauture extends AbstractCustomFeature {

	private static final String NAME = "Add Lane";

	/**
	 * Instantiates a new creates the lane feauture.
	 * 
	 * @param fp
	 *            the fp
	 */
	public CreateLaneFeauture(IFeatureProvider fp) {
		super(fp);
	}

	public void execute(ICustomContext context) {
		ContainerShape poolPe = (ContainerShape) context.getInnerPictogramElement();
		createLaneInPool(poolPe, getDiagram());

		layoutPictogramElement(poolPe);
	}

	/**
	 * Creates the lane in pool.
	 * 
	 * @param poolPe
	 *            the pool pe
	 * @param diagram
	 *            the diagram
	 */
	static void createLaneInPool(ContainerShape poolPe, Diagram diagram) {
		final IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();
		ContainerShape lanePe = peService.createContainerShape(poolPe, true);
		peService.setPropertyValue(lanePe, SketchUtil.LANE_TAG, "");
		peService.createChopboxAnchor(lanePe);
		GraphicsAlgorithm laneGa = gaService.createRectangle(lanePe);
		laneGa.setBackground(gaService.manageColor(diagram, IColorConstant.VC_LIGHT_ORANGE));
		laneGa.setForeground(gaService.manageColor(diagram, IColorConstant.VC_DARK_ORANGE));
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return SketchUtil.isPoolPe(context.getInnerPictogramElement());
	}

}
