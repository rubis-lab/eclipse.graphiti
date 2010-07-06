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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class AddAnythingFeature.
 */
public class AddAnythingFeature extends AbstractAddFeature {

	/**
	 * Instantiates a new adds the anything feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AddAnythingFeature(IFeatureProvider fp) {
		super(fp);
	}

	public PictogramElement add(IAddContext context) {
		Object newObject = context.getNewObject();
		Shape shape = Graphiti.getPeCreateService().createShape(context.getTargetContainer(), true);

		IGaService gaService = Graphiti.getGaService();
		Rectangle r = gaService.createRectangle(shape);
		gaService.setLocationAndSize(r, context.getX(), context.getY(), 400, 100);
		r.setBackground(gaService.manageColor(getDiagram(), IColorConstant.WHITE));

		String text = newObject.getClass().getName() + " - " + newObject.toString();
		Text textGa = gaService.createDefaultText(r, text);
		gaService.setLocationAndSize(textGa, 0, 0, 400, 100);
		textGa.setAngle(-1);

		return shape;
	}

	public boolean canAdd(IAddContext context) {
		return context.getTargetContainer() != null;
	}
}
