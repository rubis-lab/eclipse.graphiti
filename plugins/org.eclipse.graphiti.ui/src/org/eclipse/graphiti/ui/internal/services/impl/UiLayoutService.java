/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 415884 - Cannot query size of a multi-line text 
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.services.IUiLayoutService;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 *
 */
public class UiLayoutService implements IUiLayoutService {

	ILayoutService ls = Graphiti.getLayoutService();

	public ILocation getConnectionMidpoint(Connection c, double d) {
		return ls.getConnectionMidpoint(c, d);
	}

	public IRectangle getGaBoundsForAnchor(Anchor anchor) {
		return ls.getGaBoundsForAnchor(anchor);
	}

	public ILocationInfo getLocationInfo(Shape shape, int x, int y) {
		return ls.getLocationInfo(shape, x, y);
	}

	public ILocation getLocationRelativeToDiagram(Anchor anchor) {
		return ls.getLocationRelativeToDiagram(anchor);
	}

	public ILocation getLocationRelativeToDiagram(Shape shape) {
		return ls.getLocationRelativeToDiagram(shape);
	}

	public IDimension calculateSize(GraphicsAlgorithm ga) {
		return ls.calculateSize(ga);
	}

	public IDimension calculateSize(GraphicsAlgorithm ga, boolean considerLineWidth) {
		return ls.calculateSize(ga, considerLineWidth);
	}

	public void setHeight(GraphicsAlgorithm ga, int height) {
		ls.setHeight(ga, height);

	}

	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height) {
		ls.setLocationAndSize(ga, x, y, width, height);

	}

	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height, boolean avoidNegativeCoordinates) {
		ls.setLocationAndSize(ga, x, y, width, height, avoidNegativeCoordinates);

	}

	public void setLocation(GraphicsAlgorithm ga, int x, int y) {
		ls.setLocation(ga, x, y);

	}

	public void setLocation(GraphicsAlgorithm ga, int x, int y, boolean avoidNegativeCoordinates) {
		ls.setLocation(ga, x, y, avoidNegativeCoordinates);

	}

	public void setSize(GraphicsAlgorithm ga, int width, int height) {
		ls.setSize(ga, width, height);

	}

	public void setWidth(GraphicsAlgorithm ga, int width) {
		ls.setWidth(ga, width);

	}

	public IDimension calculateTextSize(String text, Font font, boolean handleMultiline) {
		return GraphitiUiInternal.getGefService().calculateTextSize(text, font, handleMultiline);
	}

	public IDimension calculateTextSize(String text, Font font) {
		return calculateTextSize(text, font, false);
	}

	public IDimension calculateTextSize(AbstractText text) {
		return calculateTextSize(text.getValue(), text.getFont(), (text instanceof MultiText));
	}
}
