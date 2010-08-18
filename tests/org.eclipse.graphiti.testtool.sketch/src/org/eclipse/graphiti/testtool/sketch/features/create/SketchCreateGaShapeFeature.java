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
package org.eclipse.graphiti.testtool.sketch.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class SketchCreateGaShapeFeature.
 */
public class SketchCreateGaShapeFeature extends SketchCreateGaFeature {

	private boolean multiLineText = true;

	/**
	 * Instantiates a new sketch create ga shape feature.
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
	public SketchCreateGaShapeFeature(IFeatureProvider fp, String name, String description, Class<? extends GraphicsAlgorithm> gaType) {
		super(fp, name, description, gaType);
	}

	public SketchCreateGaShapeFeature(IFeatureProvider fp, String name, String description, Class<? extends GraphicsAlgorithm> gaType,
			boolean multiLineText) {
		this(fp, name, description, gaType);
		this.multiLineText = multiLineText;
	}

	@Override
	protected AnchorContainer createAnchorContainer(ContainerShape targetContainer) {
		final Shape ret = Graphiti.getPeCreateService().createShape(targetContainer, true);
		return ret;
	}

	@Override
	protected boolean isMultiLineText() {
		return multiLineText;
	}
}
