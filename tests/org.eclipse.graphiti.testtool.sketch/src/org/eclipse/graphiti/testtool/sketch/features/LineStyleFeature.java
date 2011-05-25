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
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class LineWidthFeature.
 */
public class LineStyleFeature extends AbstractCustomFeature {

	/**
	 * The line style.
	 */
	private LineStyle lineStyle;

	/**
	 * Instantiates a new line width feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param lineWidth
	 *            the line style
	 */
	public LineStyleFeature(IFeatureProvider fp, LineStyle lineStyle) {
		super(fp);
		this.lineStyle = lineStyle;
	}

	public void execute(ICustomContext context) {

		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			if (pe != null && pe.getGraphicsAlgorithm() != null) {
				GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
				if (ga.getLineStyle() != null) {
					Style style = ga.getStyle();
					if (style != null) {
						style.setLineStyle(lineStyle);
					} else {
						ga.setLineStyle(lineStyle);
					}
				} else {
					ga.setLineStyle(lineStyle);
				}
			}
		}
	}

	@Override
	public String getName() {
		return getLineStyleName(lineStyle);
	}

	private static String getLineStyleName(LineStyle lineStyle) {
		return lineStyle.toString().toLowerCase();
	}

	@Override
	public boolean isAvailable(IContext context) {
		boolean ret = false;
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				if (pe instanceof Shape && pe.getGraphicsAlgorithm() != null && !(pe instanceof Diagram)) {
					ret = true;
				} else if (pe instanceof Connection && pe.getGraphicsAlgorithm() != null && !(pe instanceof Diagram)) {
					ret = true;
				} else{
					return false;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement pe = context.getPictogramElements()[0];
		if (pe != null && pe.getGraphicsAlgorithm() != null) {
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			LineStyle currentLineStyle = Graphiti.getGaService().getLineStyle(ga, true);
			return currentLineStyle != lineStyle;
		}
		return true;
	}
}
