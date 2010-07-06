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
package org.eclipse.graphiti.sample.sketch;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.platform.ga.IRendererContext;

/**
 * A factory for creating SketchGraphicsAlgorithmRenderer objects.
 */
public class SketchGraphicsAlgorithmRendererFactory implements IGraphicsAlgorithmRendererFactory {

	/**
	 * The Constant CANFIGURE.
	 */
	public final static String CANFIGURE = "sketch.canfigure";

	public IGraphicsAlgorithmRenderer createGraphicsAlgorithmRenderer(IRendererContext context) {
		if (CANFIGURE.equals(context.getPlatformGraphicsAlgorithm().getId())) {
			return createCanFigure();
		}
		return null;
	}

	private CanFigure createCanFigure() {
		CanFigure ret = new CanFigure(false);
		ret.setDropShadow(false);
		ret.setTextColor(ColorConstants.blue);
		ret.setBackgroundColor(ColorConstants.yellow);
		ret.setCaption("can figure test");
		return ret;

	}
}
