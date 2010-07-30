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

import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class FontColorFeature.
 */
public class FontColorFeature extends AbstractColorFeature {

	private static final String NAME = "Font Color...";

	private static final String DESCRIPTION = "Modify Font Color";

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public FontColorFeature(IFeatureProvider fp) {
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
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length >= 1) {
			for (PictogramElement pe : pes) {
				if (SketchUtil.getLabelGa(pe) == null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		Color oldColor;
		if (pes.length > 1) {
			oldColor = manageColor(IColorConstant.BLACK);
		} else {
			AbstractText ga = SketchUtil.getLabelGa(pes[0]);
			oldColor = ga.getForeground();
		}

		Color newColor = SampleUtil.editColor(oldColor);
		if (newColor != null) {
			for (PictogramElement pe : pes) {
				AbstractText ga = SketchUtil.getLabelGa(pe);
				ga.setForeground(newColor);
			}
		}
	}
}