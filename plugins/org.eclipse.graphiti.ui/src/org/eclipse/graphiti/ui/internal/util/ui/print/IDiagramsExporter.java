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
package org.eclipse.graphiti.ui.internal.util.ui.print;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Image;

/**
 * Exports the content of a graphical viewer provided as image and as figure.
 * The usual use-case is exporting to a file. But exports could in principal go
 * anywhere.
 * 
 * @since 0.8.0
 * 
 */
public interface IDiagramsExporter {

	/**
	 * Exports the graphics using the passed image or figure. Throws a Exception
	 * if something goes wrong.
	 * 
	 * @param im
	 *            the image to be exported, the image is already scaled
	 * @param figure
	 *            the figure to be exported
	 * @param fileName
	 *            null or an filename
	 * @param scaleFactor
	 *            the scale factor, can be used to scale the figure
	 */
	public void export(Image im, IFigure figure, String fileName, Double scaleFactor) throws Exception;

}
