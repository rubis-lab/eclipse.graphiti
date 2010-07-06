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
package org.eclipse.graphiti.sample.sketch.features;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

/**
 * The Class LayoutContainerShapeGhostAndInnerShapeFeature.
 */
public class LayoutContainerShapeGhostAndInnerShapeFeature extends AbstractLayoutFeature {
	private static final int INSET = 30;

	/**
	 * Instantiates a new layout container shape ghost and inner shape feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public LayoutContainerShapeGhostAndInnerShapeFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
		boolean ret = context.getPictogramElement() instanceof ContainerShape;
		return ret;
	}

	public boolean layout(ILayoutContext context) {
		ContainerShape cs = (ContainerShape) context.getPictogramElement();
		IGaService gaService=Graphiti.getGaService();
		GraphicsAlgorithm ghost = cs.getGraphicsAlgorithm();
		IDimension ghostSize = gaService.calculateSize(ghost);
		int ghostWidth = ghostSize.getWidth();
		int ghostHeight = ghostSize.getHeight();
		gaService.setLocationAndSize(ghost.getGraphicsAlgorithmChildren().get(0), INSET, INSET, ghostWidth - INSET - INSET,
				ghostHeight - INSET - INSET);

		Shape innerShape = cs.getChildren().get(0);
		int width = INSET;
		int height = width;
		int x = (ghostWidth - width) / 2;
		int y = INSET - (height / 2);
		gaService.setLocationAndSize(innerShape.getGraphicsAlgorithm(), x, y, width, height);

		return true;
	}
}
