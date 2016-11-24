/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2016 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 373298 - Possible Resource leaks in Graphiti
 *    apupier - Bug 508133 - Use FontRegistry for better performance
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.draw2d;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class Tooltip extends FlowPage {

	private static final int TOOLTIP_MAX_WIDTH = 250;
	private static final Border TOOLTIP_BORDER = new MarginBorder(0, 2, 1, 2);

	private TextFlow header;
	private TextFlow description;
	private String headerText;
	private String descriptionText;

	public Tooltip() {
		setForegroundColor(GFColorConstants.TOOLTIP_FG);
		setBackgroundColor(GFColorConstants.TOOLTIP_BG);
		setOpaque(true);
		setBorder(TOOLTIP_BORDER);

		header = new TextFlow();
		header.setForegroundColor(ColorConstants.darkGray);
		header.setFont(getBoldFont());
		add(header);

		description = new TextFlow();
		description.setForegroundColor(ColorConstants.darkGray);
		add(description);
	}

	public void setHeader(String text) {
		headerText = text;
		adjustTexts();
	}

	public void setDescription(String text) {
		descriptionText = text;
		adjustTexts();
	}

	private void adjustTexts() {
		description.setText(descriptionText);
		if (headerText != null && descriptionText != null) {
			// needs extra line-break
			header.setText(headerText + "\n"); //$NON-NLS-1$
		} else {
			header.setText(headerText);
		}
	}

	@Override
	public Dimension getPreferredSize(int w, int h) {
		Dimension d = super.getPreferredSize(-1, -1);
		if (d.width > TOOLTIP_MAX_WIDTH)
			d = super.getPreferredSize(TOOLTIP_MAX_WIDTH, -1);
		return d;
	}

	private Font getBoldFont() {
		return JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
	}
}
