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
 *    cbrand - Bug 377475 - Fix AbstractCustomFeature.execute and canExecute
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.graphiti.examples.common.ColoredFont;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * The Class SketchFontFeature.
 */
public class SketchFontFeature extends AbstractCustomFeature {

	private static final String NAME = "Font...";

	private static final String DESCRIPTION = "Modify Font";

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchFontFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();

		AbstractText textGa = SketchUtil.getLabelGa(pes[0]);

		Font font = textGa.getFont();
		Color fgColor = textGa.getForeground();
		ColoredFont coloredFont = editFont(textGa, new ColoredFont(font, fgColor), getDiagram());
		if (coloredFont == null) {
			return; // cancel in dialog
		}
		Font f = coloredFont.getFont();

		for (int i = 0; i < pes.length; i++) {
			PictogramElement pe = pes[i];
			if (pe != null) {
				textGa = SketchUtil.getLabelGa(pe);
				if (textGa != null) {
					Font clonedFont = Graphiti.getGaService().manageFont(getDiagram(), f.getName(), f.getSize(),
							f.isItalic(), f.isBold());
					textGa.setFont(clonedFont);
					textGa.setForeground(coloredFont.getColor());
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isAvailable(IContext context) {
		boolean ret = false;
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				if (SketchUtil.getLabelGa(pe) != null) {
					ret = true;
				} else {
					return false;
				}
			}
		}
		return ret;
	}

	/**
	 * Opens a dialog to change the font styles and colors.
	 * 
	 * @param coloredFont
	 *            the current colored font
	 * @param diagram
	 *            the diagram
	 * @param text
	 *            the text
	 * @return the changed colored font
	 */
	private ColoredFont editFont(AbstractText text, ColoredFont coloredFont, Diagram diagram) {
		Font inputFont = coloredFont.getFont();
		Color inputColor = coloredFont.getColor();

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		FontDialog fontDialog = new FontDialog(shell);
		FontData fontData = toFontData(inputFont);

		fontDialog.setFontList(new FontData[] { fontData });
		RGB rgb = new RGB(inputColor.getRed(), inputColor.getGreen(), inputColor.getBlue());
		fontDialog.setRGB(rgb);

		FontData retFontData = fontDialog.open();

		if (retFontData == null) {
			return null;
		}

		Font retFont = toPictogramsFont(text, retFontData);
		RGB retRgb = fontDialog.getRGB();
		Color retColor = Graphiti.getGaService().manageColor(diagram, retRgb.red, retRgb.green, retRgb.blue);

		ColoredFont ret = new ColoredFont(retFont, retColor);

		return ret;
	}

	private Font toPictogramsFont(AbstractText text, FontData fontData) {
		if (fontData == null) {
			return null;
		}
		Font ret;
		String name = fontData.getName();
		int height = fontData.getHeight();
		boolean italic = (fontData.getStyle() & SWT.ITALIC) != 0;
		boolean bold = (fontData.getStyle() & SWT.BOLD) != 0;
		ret = Graphiti.getGaService().manageFont(getDiagram(), name, height, italic, bold);
		return ret;
	}

	private FontData toFontData(Font pictogramFont) {
		FontData fontData;
		if (pictogramFont != null) {
			int style = SWT.NORMAL;
			if (pictogramFont.isItalic()) {
				style |= SWT.ITALIC;
			}
			if (pictogramFont.isBold()) {
				style |= SWT.BOLD;
			}
			int size = pictogramFont.getSize();
			String name = pictogramFont.getName();
			fontData = new FontData(name, size, style);
		} else {
			fontData = new FontData();
		}
		return fontData;
	}
}
