/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
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
package org.eclipse.graphiti.ui.saveasimage;

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * This interface defines a save as image configuration container, that is used
 * for saving a diagram as an image. The container itself is also capable of
 * providing an image for the figure to save as an image.
 * 
 * @since 0.10
 */
public interface ISaveAsImageConfiguration {

	/**
	 * The return code for {@link #configure()} that indicates a successful
	 * configuration process.
	 */
	public static final int OK = Window.OK;

	/**
	 * Should add the given exporters to an internal list. Clients may use this
	 * list to let users select in an UI from the exporters or programmatically
	 * decide which one to use.
	 * 
	 * @param diagramExporterTypes
	 *            A {@link Map} holding all exporters.
	 */
	public void addExporters(Map<String, Boolean> diagramExporterTypes);

	/**
	 * Does the actual configuration. May bring up a UI.
	 * 
	 * @return
	 */
	public int configure();

	/**
	 * Returns the selected file extension formatted in a standard way, e.g.
	 * converted to lower case.
	 * 
	 * @return A string holding the file extension
	 */
	public String getFormattedFileExtension();
	
	/**
	 * Returns the image in the final scaled version that fits the zooom factor
	 * etc. the user (or the configure coding) defined.
	 * 
	 * @return An {@link Image} containing what shall be saved.
	 */
	public Image getScaledImage();

	/**
	 * Returns the file extension to use.
	 * 
	 * @return A string holding the file extension
	 */
	public String getFileExtension();

	/**
	 * Returns the image format as defined by the SWT.IMAGE_* constants like
	 * {@link SWT#IMAGE_BMP}.
	 * 
	 * @return An integer defining the format
	 */
	public int getImageFormat();

	/**
	 * Returns the figure to be saved as an image.
	 * 
	 * @return The figure to be saved as an image
	 */
	public IFigure getFigure();

	/**
	 * Retunrs the scale factor to be used in the saved image.
	 * 
	 * @return A double value defining the scale factor.
	 */
	public double getImageScaleFactor();
}
