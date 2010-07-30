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

import org.eclipse.graphiti.examples.common.ColoredFont;
import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;

/**
 * The Class SketchFontFeature.
 */
public class SketchFontFeature extends AbstractCustomFeature {

	private static final String NAME = "Font...";

	private static final String DESCRIPTION = "Modify Font";

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchFontFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		AbstractText textGa = SketchUtil.getLabelGa(pes[0]);

		Font font = textGa.getFont();
		Color fgColor = textGa.getForeground();
		ColoredFont coloredFont = SampleUtil.editFont(textGa, new ColoredFont(font, fgColor), getDiagram());
		if (coloredFont == null) {
			return; // cancel in dialog
		}
		Font f = coloredFont.getFont();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			if (pe != null) {
				textGa = SketchUtil.getLabelGa(pe);
				if (textGa != null) {
					Font clonedFont = Graphiti.getGaCreateService().createFont(textGa, f.getName(), f.getSize(), f.isItalic(), f.isBold());
					textGa.setFont(clonedFont);
					textGa.setForeground(coloredFont.getColor());
				}
			}
		}
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
	public boolean isAvailable(IContext context) {
		boolean ret = false;
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				if (SketchUtil.getLabelGa(pe) != null) {
					ret = true;
				} else {
					return false;
				}
			}
		}
		return ret;
	}

}
