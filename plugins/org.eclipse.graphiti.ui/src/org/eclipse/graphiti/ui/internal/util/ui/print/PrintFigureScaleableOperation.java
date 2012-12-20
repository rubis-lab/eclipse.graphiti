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
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.ui.print;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PrintFigureOperation;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.ui.print.IDefaultPrintPreferences;
import org.eclipse.graphiti.ui.print.IPrintConfiguration;
import org.eclipse.swt.graphics.Image;

/**
 * A PrintFigureOperation, which allows to print with fully configurable size
 * and position. The width, height, and margins are expected to be accessible
 * through the field {@link _preferences}.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PrintFigureScaleableOperation extends PrintFigureOperation {

	/**
	 * The preferences which should contain appropriate values for the keys <li>
	 * DefaultPrintPreferences.PRINTER_IMAGE_WIDTH</li> <li>
	 * DefaultPrintPreferences.PRINTER_IMAGE_HEIGHT</li> <li>
	 * DefaultPrintPreferences.PRINTER_LEFT_MARGIN</li> <li>
	 * DefaultPrintPreferences.PRINTER_TOP_MARGIN</li>
	 * 
	 * @see DefaultPrintPreferences
	 */
	private IDefaultPrintPreferences _preferences;
	private Image _image;

	/**
	 * Creates a new PrintFigureScaleableOperation.
	 * 
	 * @param p
	 *            The printer, where to print
	 * @param figure
	 *            actually only used to call super methods
	 * @param image
	 *            The image to print, this is currently used for printing since
	 *            Figures do not allow for flexible xy-scaling.
	 */
	public PrintFigureScaleableOperation(IPrintConfiguration printConfiguration) {
		super(printConfiguration.getPrinter(), printConfiguration.getFigure());
		_preferences = printConfiguration.getPreferences();
		_image = printConfiguration.getScaledImage();
	}

	/**
	 * Overrides superclass. This means, that the print-modes of the super-class
	 * are ignored.
	 * 
	 * @see org.eclipse.draw2d.PrintFigureOperation#setupPrinterGraphicsFor(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.IFigure)
	 */
	@Override
	protected void setupPrinterGraphicsFor(Graphics graphics, IFigure figure) {

		graphics.setForegroundColor(figure.getForegroundColor());
		graphics.setBackgroundColor(figure.getBackgroundColor());
		graphics.setFont(figure.getFont());
	}

	/**
	 * Prints the pages based on the preference values in {@link _preferences}.
	 * 
	 * @see org.eclipse.draw2d.PrintOperation#printPages()
	 */
	@Override
	protected void printPages() {
		Graphics graphics = getFreshPrinterGraphics();
		IFigure figure = getPrintSource();
		setupPrinterGraphicsFor(graphics, figure);
		Rectangle clipRect = new Rectangle();

		int imageWidthScaled = (int) _preferences.getDoublePreference(DefaultPrintPreferences.PRINTER_IMAGE_WIDTH);
		int imageHeightScaled = (int) _preferences.getDoublePreference(DefaultPrintPreferences.PRINTER_IMAGE_HEIGHT);
		int translateX = (int) _preferences.getDoublePreference(DefaultPrintPreferences.PRINTER_LEFT_MARGIN);
		int translateY = (int) _preferences.getDoublePreference(DefaultPrintPreferences.PRINTER_TOP_MARGIN);

		int x = 0, y = 0;

		while (y < translateY + imageHeightScaled) {
			while (x < translateX + imageWidthScaled) {
				graphics.pushState();
				getPrinter().startPage();
				graphics.translate(-x, -y);
				graphics.getClip(clipRect);
				clipRect.setLocation(x, y);
				graphics.clipRect(clipRect);
				graphics.drawImage(_image, 0, 0, _image.getBounds().width, _image.getBounds().height, translateX, translateY,
						imageWidthScaled, imageHeightScaled);
				getPrinter().endPage();
				graphics.popState();
				x += clipRect.width;
			}
			x = 0;
			y += clipRect.height;
		}
	}
}
