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

import java.util.List;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.pictograms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;

/**
 * The Class SketchLayoutFeature.
 */
public class SketchLayoutFeature extends AbstractLayoutFeature {

	/**
	 * The TOP.
	 */
	public static int TOP = 10;

	/**
	 * The BOTTOM.
	 */
	public static int BOTTOM = 10;

	/**
	 * The LEFT.
	 */
	public static int LEFT = 10;

	/**
	 * The RIGHT.
	 */
	public static int RIGHT = 10;

	/**
	 * The DIST.
	 */
	public static int DIST = 10;

	/**
	 * Instantiates a new sketch layout feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
		boolean ret = (SketchUtil.getLabelGa(context.getPictogramElement()) != null);
		return ret;
	}

	public boolean layout(ILayoutContext context) {
		IGaService gaService=Graphiti.getGaService();
		final PictogramElement pe = context.getPictogramElement();
		GraphicsAlgorithm containerGa = pe.getGraphicsAlgorithm();
		final List<GraphicsAlgorithm> gaChildren = containerGa.getGraphicsAlgorithmChildren();
		if (SketchUtil.getGhostGa(pe) != null) {
			final IDimension containerGaSize = gaService.calculateSize(containerGa);
			final int containerWidth = containerGaSize.getWidth();
			final int containerHeight = containerGaSize.getHeight();
			final GraphicsAlgorithm firstGa = gaChildren.get(0);
			final GraphicsAlgorithm textGa = gaChildren.get(1);
			final int textHeight = 20; // textGa.getHeight();
			gaService.setLocationAndSize(firstGa, LEFT, TOP, containerWidth - LEFT - RIGHT, containerHeight - TOP - BOTTOM
					- DIST - textHeight, true);
			gaService.setSize(textGa, containerWidth - LEFT - RIGHT, textHeight);
			gaService.setLocation(textGa, LEFT, containerHeight - BOTTOM - textHeight, true);
		}

		AbstractText text = SketchUtil.getLabelGa(pe);
		if (text != null) {
			gaService.setSize(text, containerGa.getWidth(), containerGa.getHeight());
		}

		return true;
	}

}
