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
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.graphiti.mm.algorithms.MultiText;

public class GFTextFlow extends TextFlow {

	private MultiText text;

	public GFTextFlow(MultiText text) {
		this.text = text;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
	}

	@Override
	protected void paintText(Graphics g, String draw, int x, int y, int bidiLevel) {
		super.paintText(g, draw, x, y, bidiLevel);
	}
}
