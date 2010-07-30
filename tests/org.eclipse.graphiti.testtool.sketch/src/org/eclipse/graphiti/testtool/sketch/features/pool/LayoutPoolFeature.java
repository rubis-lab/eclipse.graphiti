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

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;

/**
 * The Class LayoutPoolFeature.
 */
public class LayoutPoolFeature extends AbstractLayoutFeature {
	private static final int LANE_INDENT = 5;

	private static final int LANE_DISTANCE = 3;

	/**
	 * Instantiates a new layout pool feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public LayoutPoolFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
		PictogramElement pe = context.getPictogramElement();
		return SketchUtil.isPoolPe(pe);
	}

	public boolean layout(ILayoutContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof ContainerShape) {
			ContainerShape poolCs = (ContainerShape) pe;
			List lanePes = poolCs.getChildren();
			int laneCount = lanePes.size();
			GraphicsAlgorithm poolGa = poolCs.getGraphicsAlgorithm();
			int poolHeight = poolGa.getHeight();
			int laneHeight = poolHeight - (2 * LANE_INDENT);
			int poolWidth = poolGa.getWidth();
			int laneWidth = (poolWidth - (2 * LANE_INDENT) - ((laneCount - 1) * LANE_DISTANCE)) / laneCount;

			int x = LANE_INDENT;
			for (Object object : lanePes) {
				if (object instanceof ContainerShape) {
					ContainerShape laneCs = (ContainerShape) object;
					GraphicsAlgorithm laneGa = laneCs.getGraphicsAlgorithm();
					Graphiti.getGaService().setLocationAndSize(laneGa, x, LANE_INDENT, laneWidth, laneHeight);
				}
				x += laneWidth + LANE_DISTANCE;
			}
		}
		return false;
	}

}
