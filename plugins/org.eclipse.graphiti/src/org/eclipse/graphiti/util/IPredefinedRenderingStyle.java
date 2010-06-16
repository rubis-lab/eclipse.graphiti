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
package org.eclipse.graphiti.util;

/**
 * The IDs of the predefined rendering styles. See
 * {@link GaUtil#setRenderingStyle(org.eclipse.graphiti.mm.pictograms.AbstractStyle, String)}
 * .
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPredefinedRenderingStyle {

	/**
	 * The ID for a blue-to-white gradient with a gloss-effect.
	 */
	public static final String BLUE_WHITE_GLOSS_ID = "blue-white-gloss"; //$NON-NLS-1$

	/**
	 * The ID for a blue-to-white gradient.
	 */
	public static final String BLUE_WHITE_ID = "blue-white"; //$NON-NLS-1$

	/**
	 * The ID for a light yellow color.
	 */
	public static final String LIGHT_YELLOW_ID = "light-yellow"; //$NON-NLS-1$

	/**
	 * The ID for a light gray color.
	 */
	public static final String LIGHT_GRAY_ID = "light-gray"; //$NON-NLS-1$

	/**
	 * The ID for a copper-to-white gradient with a gloss-effect.
	 */
	public static final String COPPER_WHITE_GLOSS_ID = "copper-white-gloss"; //$NON-NLS-1$

	/**
	 * The ID for a silver-to-white gradient with a gloss-effect.
	 */
	public static final String SILVER_WHITE_GLOSS_ID = "silver-white-gloss"; //$NON-NLS-1$

	/**
	 * All IDs.
	 */
	public static final String[] ALL_IDS = new String[] { BLUE_WHITE_GLOSS_ID, BLUE_WHITE_ID, LIGHT_YELLOW_ID, LIGHT_GRAY_ID,
			COPPER_WHITE_GLOSS_ID, SILVER_WHITE_GLOSS_ID };

}
