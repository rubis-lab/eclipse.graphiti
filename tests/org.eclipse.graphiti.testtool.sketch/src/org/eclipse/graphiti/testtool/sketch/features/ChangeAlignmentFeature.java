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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class ChangeAlignmentFeature.
 */
public class ChangeAlignmentFeature extends AbstractCustomFeature {

	private String name;

	private Orientation hTextOrientation;

	private Orientation vTextOrientation;

	/**
	 * Instantiates a new change alignment feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param hTextOrientation
	 *            the h text orientation
	 * @param vTextOrientation
	 *            the v text orientation
	 */
	public ChangeAlignmentFeature(IFeatureProvider fp, String name, Orientation hTextOrientation, Orientation vTextOrientation) {
		super(fp);
		this.name = name;
		this.hTextOrientation = hTextOrientation;
		this.vTextOrientation = vTextOrientation;
	}

	public void execute(ICustomContext context) {
		MultiText text = (MultiText) context.getInnerGraphicsAlgorithm();
		if (hTextOrientation != null) {
			text.setHorizontalAlignment(hTextOrientation);
		}
		if (vTextOrientation != null) {
			text.setVerticalAlignment(vTextOrientation);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		MultiText text = (MultiText) context.getInnerGraphicsAlgorithm();
		if (hTextOrientation != null && !hTextOrientation.equals(Graphiti.getGaService().getHorizontalAlignment(text, true))) {
			return true;
		}
		if (vTextOrientation != null && !vTextOrientation.equals(Graphiti.getGaService().getVerticalAlignment(text, true))) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAvailable(IContext context) {
		if (context instanceof ICustomContext) {
			ICustomContext customContext = (ICustomContext) context;
			GraphicsAlgorithm innerGraphicsAlgorithm = customContext.getInnerGraphicsAlgorithm();
			if (innerGraphicsAlgorithm instanceof MultiText) {
				return true;
			}
		}
		return false;
	}
}
