/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 352440 - Fixed deprecation warnings - contributed by Felix Velasco
 *    mwenz - Bug 477421 - SWTException in ScaledGraphics.getCachedFontData
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.fixed;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;

/**
 * The Class FixedScalableFreeformLayeredPane.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FixedScalableFreeformLayeredPane extends ScalableFreeformLayeredPane {
	/**
	 * Paint client area.
	 * 
	 * Change from super method: Do not call the super method in case of scale
	 * == 1.0; instead always use FixedScaledGraphics for consistency. Also
	 * check disposed state of FixedScaledGraphics.
	 * 
	 * @param graphics
	 *            the graphics
	 * 
	 * @see org.eclipse.draw2d.Figure#paintClientArea(Graphics)
	 */
	@Override
	protected void paintClientArea(Graphics graphics) {
		if (getChildren().isEmpty()) {
			return;
		}
		FixedScaledGraphics g = new FixedScaledGraphics(graphics);
		boolean optimizeClip = getBorder() == null || getBorder().isOpaque();
		if (!optimizeClip)
			g.clipRect(getBounds().getShrinked(getInsets()));
		g.scale(getScale());
		g.pushState();
		if (!g.isDisposed()) {
			paintChildren(g);
			g.dispose();
		}
		graphics.restoreState();
	}
}
