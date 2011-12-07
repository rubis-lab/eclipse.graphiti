/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mgorning - Bug 347144 - Angle of MultiText objects can't be modified
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.graphiti.mm.algorithms.MultiText;

public class GFTextFlow extends TextFlow {

	private MultiText multiText;

	public GFTextFlow(MultiText multiText) {
		this.multiText = multiText;
	}

	@Override
	public void paint(Graphics graphics) {

		if (multiText.getAngle() != 0) {
			if (getLocalBackgroundColor() != null)
				graphics.setBackgroundColor(getLocalBackgroundColor());
			if (getLocalForegroundColor() != null)
				graphics.setForegroundColor(getLocalForegroundColor());
			if (getLocalFont() != null)
				graphics.setFont(getLocalFont());

			graphics.pushState();
			try {
				Rectangle flowPageFigureBounds = getParent().getBounds();
				Rectangle multiTextBounds = getParent().getParent().getBounds();

				Point clipLocation = new Point(multiTextBounds.x - flowPageFigureBounds.x, multiTextBounds.y
						- flowPageFigureBounds.y);
				Rectangle clip = new Rectangle(clipLocation, multiTextBounds.getSize());
				graphics.setClip(clip);

				paintFigure(graphics);
				graphics.restoreState();
				paintClientArea(graphics);
				paintBorder(graphics);
			} finally {
				graphics.popState();
			}
		} else {
			super.paint(graphics);
		}
	}

	@Override
	protected void paintText(Graphics g, String draw, int x, int y, int bidiLevel) {
		if (bidiLevel == -1 && multiText.getAngle() != 0) {
			g.pushState();

			int xOff = getParent().getBounds().width() / 2;
			int yOff = getBounds().height() / 2;
			g.translate(xOff, yOff);
			g.rotate(multiText.getAngle());

			g.drawText(draw, x - xOff, y - yOff);

			g.popState();
		} else {
			super.paintText(g, draw, x, y, bidiLevel);
		}
	}
}
