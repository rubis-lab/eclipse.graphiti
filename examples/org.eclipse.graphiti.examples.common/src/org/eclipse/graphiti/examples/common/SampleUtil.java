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
package org.eclipse.graphiti.examples.common;

import org.eclipse.graphiti.mm.datatypes.Color;
import org.eclipse.graphiti.mm.pictograms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Font;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class SampleUtil {

	/**
	 * Opens an simple input dialog with OK and Cancel buttons.
	 * <p>
	 * 
	 * @param dialogTitle
	 *            the dialog title, or <code>null</code> if none
	 * @param dialogMessage
	 *            the dialog message, or <code>null</code> if none
	 * @param initialValue
	 *            the initial input value, or <code>null</code> if none
	 *            (equivalent to the empty string)
	 * @return the string
	 */
	public static String askString(String dialogTitle, String dialogMessage, String initialValue) {
		String ret = null;
		Shell shell = getShell();
		InputDialog inputDialog = new InputDialog(shell, dialogTitle, dialogMessage, initialValue, null);
		int retDialog = inputDialog.open();
		if (retDialog == Window.OK) {
			ret = inputDialog.getValue();
		}
		return ret;
	}

	/**
	 * Opens a dialog to change the color.
	 * 
	 * @param color
	 *            the color to change
	 * @return the changed color
	 */
	public static Color editColor(Color color) {
		if (color != null && color.eContainer() instanceof Diagram) {
			Shell shell = getShell();
			ColorDialog colorDialog = new ColorDialog(shell);
			colorDialog.setText(Messages.PlatformServiceImpl_0_xfld);
			colorDialog.setRGB(new RGB(color.getRed(), color.getGreen(), color.getBlue()));

			RGB retRgb = colorDialog.open();
			if (retRgb == null) {
				return null;
			}

			Diagram diagram = (Diagram) color.eContainer();
			Color newColor = Graphiti.getGaService().manageColor(diagram, retRgb.red, retRgb.green, retRgb.blue);
			return newColor;

		}

		return null;
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
	public static ColoredFont editFont(AbstractText text, ColoredFont coloredFont, Diagram diagram) {
		Font inputFont = coloredFont.getFont();
		Color inputColor = coloredFont.getColor();

		Shell shell = getShell();
		FontDialog fontDialog = new FontDialog(shell);
		FontData fontData = DataTypeTransformation.toFontData(inputFont);

		fontDialog.setFontList(new FontData[] { fontData });
		RGB rgb = new RGB(inputColor.getRed(), inputColor.getGreen(), inputColor.getBlue());
		fontDialog.setRGB(rgb);

		FontData retFontData = fontDialog.open();

		if (retFontData == null) {
			return null;
		}

		Font retFont = DataTypeTransformation.toPictogramsFont(text, retFontData);
		RGB retRgb = fontDialog.getRGB();
		Color retColor = DataTypeTransformation.toPictogramsColor(retRgb, diagram);

		ColoredFont ret = new ColoredFont(retFont, retColor);

		return ret;
	}

	/**
	 * Returns the currently active Shell.
	 * 
	 * @return The currently active Shell.
	 */
	private static Shell getShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}
