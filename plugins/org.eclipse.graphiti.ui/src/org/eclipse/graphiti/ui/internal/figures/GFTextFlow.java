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
 *    mgorning - Bug 347144 - Angle of MultiText objects can't be modified
 *    mgorning - Bug 377419 - Hide text in underlying GA while DirectEditing is enabled
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.draw2d.text.TextFragmentBox;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;

public class GFTextFlow extends TextFlow {

	private MultiText multiText;

	private boolean suppressText = false;

	private int currentOffset;

	private IConfigurationProviderInternal configurationProvider;

	public GFTextFlow(MultiText multiText, IConfigurationProviderInternal configurationProvider) {
		this.multiText = multiText;
		this.configurationProvider = configurationProvider;
	}

	@Override
	public void paint(Graphics graphics) {

		int angle = 0;
		angle = Graphiti.getGaService().getAngle(multiText, true);

		if (angle != 0) {
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
	protected String getBidiSubstring(TextFragmentBox box, int index) {
		currentOffset = box.offset;
		return super.getBidiSubstring(box, index);
	}

	@Override
	protected void paintText(Graphics g, String draw, int x, int y, int bidiLevel) {
		if (suppressText) {
			return;
		}
		int angle = 0;
		angle = Graphiti.getGaService().getAngle(multiText, true);

		if (bidiLevel == -1 && angle != 0) {
			g.pushState();

			int xOff = getParent().getBounds().width() / 2;
			int yOff = getBounds().height() / 2;
			g.translate(xOff, yOff);
			g.rotate(angle);

			GFFigureUtil.drawRichText(g, draw, x - xOff, y - yOff, bidiLevel, isMirrored(), currentOffset,
					configurationProvider, multiText);

			g.popState();
		} else {
			GFFigureUtil.drawRichText(g, draw, x, y, bidiLevel, isMirrored(), currentOffset, configurationProvider,
					multiText);
		}
	}

	public void setSuppressText(boolean suppressText) {
		this.suppressText = suppressText;
		repaint();
	}
}
