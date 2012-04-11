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
 *    mwenz - Bug 352440 - Fixed deprecation warnings - contributed by Felix Velasco
 *    mwenz - Bug 373298 - Possible Resource leaks in Graphiti
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.draw2d;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Insets;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class TransparentGhostFigure extends ImageFigure {

	private int alpha;

	public TransparentGhostFigure(int alpha) {
		this.alpha = alpha;
		setOpaque(false);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		graphics.setAlpha(alpha); // fill transparent gray rectangle
		graphics.setBackgroundColor(ColorConstants.lightGray);
		graphics.fillRectangle(getClientArea().getShrinked(new Insets(0, 0, 1, 1)));
		graphics.setAlpha(255); // draw non-transparent dotted rectangle border
		graphics.setLineStyle(Graphics.LINE_DOT);
		graphics.drawRectangle(getClientArea().getShrinked(new Insets(0, 0, 1, 1)));
	}

	public void dispose() {
		if (getImage() != null) {
			getImage().dispose();
		}
	}
}
