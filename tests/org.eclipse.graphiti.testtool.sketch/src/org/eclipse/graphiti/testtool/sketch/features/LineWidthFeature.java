/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Benjamin Schmeling - mwenz - Bug 367483 - Support composite connections
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class LineWidthFeature.
 */
public class LineWidthFeature extends AbstractCustomFeature {

	private static final String UNIT = " px";

	/**
	 * The line width.
	 */
	int lineWidth;

	/**
	 * Instantiates a new line width feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param lineWidth
	 *            the line width
	 */
	public LineWidthFeature(IFeatureProvider fp, int lineWidth) {
		super(fp);
		this.lineWidth = lineWidth;
	}

	public void execute(ICustomContext context) {

		PictogramElement[] pes = context.getPictogramElements();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			setLineWidth(pe);
			if (pe instanceof CompositeConnection) {
				EList<CurvedConnection> children = ((CompositeConnection) pe).getChildren();
				for (Connection connection : children) {
					setLineWidth(connection);
				}
			}
		}
	}

	private void setLineWidth(PictogramElement pe) {
		if (pe != null && pe.getGraphicsAlgorithm() != null) {
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			if (ga.getLineWidth() != null) {
				Style style = ga.getStyle();
				if (style != null) {
					style.setLineWidth(lineWidth);
				} else {
					ga.setLineWidth(lineWidth);
				}
			} else {
				ga.setLineWidth(lineWidth);
			}
		}
	}

	@Override
	public String getName() {
		return Integer.toString(lineWidth) + UNIT;
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
				} else {
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
			int currentLineWidth = Graphiti.getGaService().getLineWidth(ga, true);
			return currentLineWidth != lineWidth;
		}
		return true;
	}
}
