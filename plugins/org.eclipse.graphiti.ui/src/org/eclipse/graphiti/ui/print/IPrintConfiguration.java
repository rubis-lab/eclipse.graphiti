/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.print;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.Printer;

/**
 * This interface defines a print configuration container, that is used for
 * printing a diagram. The container itself is also capable of providing an
 * image for the figure to print.
 * 
 * @since 0.10
 */
public interface IPrintConfiguration {

	public static final int OK = Window.OK;

	/**
	 * Configures this container. May e.g. mean that a popup is presented to the
	 * user, but also simply some default values are set.
	 * 
	 * @return The return code of this operation. Anything else than 0 will be
	 *         interpreted as a cancellation request.
	 */
	public int configure();

	/**
	 * Returns the figure to be printed.
	 * 
	 * @return The figure to be printed
	 */
	public IFigure getFigure();

	/**
	 * Calculates an image with the applied scale factors.
	 * 
	 * @return A scaled image
	 */
	public Image getScaledImage();

	/**
	 * The defined preferences for printing
	 * 
	 * @return A filled {@link IPrintPreferences} instance holding the
	 *         current configuration
	 */
	public IPrintPreferences getPreferences();

	/**
	 * Cleans up all stored data.
	 */
	public void cleanUp();

	/**
	 * Returns the {@link Printer} to be used.
	 * 
	 * @return The printer.
	 */
	public Printer getPrinter();

}
