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
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Style;

/**
 * The Class SetStyleFeature.
 */
public class SetStyleFeature extends AbstractCustomFeature {
	private Style style;

	/**
	 * Instantiates a new sets the style feature.
	 * 
	 * @param style
	 *            the style
	 * @param fp
	 *            the fp
	 */
	public SetStyleFeature(Style style, IFeatureProvider fp) {
		super(fp);
		setStyle(style);
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			ga.setStyle(getStyle());
		}

	}

	@Override
	public String getName() {
		final Style style = getStyle();
		return style.getId() + " (" + style.getDescription() + ")";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				return true;
			}
		}
		return false;
	}

	private Style getStyle() {
		return style;
	}

	private void setStyle(Style style) {
		this.style = style;
	}
}
