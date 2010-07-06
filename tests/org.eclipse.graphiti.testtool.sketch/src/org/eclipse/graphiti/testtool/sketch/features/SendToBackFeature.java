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
package org.eclipse.graphiti.testtool.sketch.features;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class BackgroundColorFeature.
 */
public class SendToBackFeature extends AbstractCustomFeature {

	private static final String NAME = "Send to back";

	private static final String DESCRIPTION = "Send to the back";

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SendToBackFeature(IFeatureProvider fp) {
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
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			if (pe instanceof Shape) {
				Shape shape = (Shape) pe;
				ContainerShape containerShape = shape.getContainer();
				if (containerShape != null) {
					List<Shape> childList = containerShape.getChildren();
					if (childList.size() > 1 && childList.indexOf(shape) > 0) {
						return true;
					}
				}
			}
		}
		return ret;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			if (pe instanceof Shape) {
				Shape shape = (Shape) pe;
				ContainerShape containerShape = shape.getContainer();
				List<Shape> childList = containerShape.getChildren();
				if (childList.size() > 1 && childList.indexOf(shape) > 0) {
					Graphiti.getPeService().sendToBack(shape);
				}
			}
		}
	}
}