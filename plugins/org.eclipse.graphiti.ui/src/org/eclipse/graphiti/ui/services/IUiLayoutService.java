/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 415884 - Cannot query size of a multi-line text
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.services;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.services.ILayoutService;

/**
 * Extends the layout service interface to be able to offer layout methods which
 * depend on UI.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be sub-classed by clients.
 */
public interface IUiLayoutService extends ILayoutService {

	/**
	 * Calculates the width and height of the given text in the given font
	 * ignoring any new line characters in the string.
	 * 
	 * @param text
	 *            the string to calculate the rendering size for
	 * @param font
	 *            the font which should be considered for the string
	 * @return
	 * 
	 * @see #calculateSize(org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm,
	 *      boolean)
	 */
	public IDimension calculateTextSize(String text, Font font);

	/**
	 * Calculates the width and height of the given text in the given font.
	 * 
	 * @param text
	 *            the string to calculate the rendering size for
	 * @param font
	 *            the font which should be considered for the string
	 * @param handleMultiline
	 *            Defines if line breaks in the string should be used in the
	 *            calculation of the size or not. In case <code>true</code>, a
	 *            new line character in the string will increase the size of the
	 *            returned dimensions by one line, in case <code>false</code> a
	 *            new line character will be ignored.
	 * @return
	 * 
	 * @since 0.11
	 */
	IDimension calculateTextSize(String text, Font font, boolean handleMultiline);

	/**
	 * Calculates the width and height of the given text in the font of the
	 * gievn text. In case the given text is a {@link MultiText} new line
	 * characters in the string will increase the height of the returned size,
	 * otherwise (text is a {@link Text}) any new line characters will be
	 * ignored.
	 * 
	 * @param text
	 *            the {@link AbstractText} to calculate the rendering size for
	 * @return
	 * 
	 * @since 0.11
	 */
	IDimension calculateTextSize(AbstractText text);
}
