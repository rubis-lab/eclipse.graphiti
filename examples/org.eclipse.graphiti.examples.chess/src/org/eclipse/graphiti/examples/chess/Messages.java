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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.chess;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.graphiti.examples.chess.messages"; //$NON-NLS-1$
	public static String CreateAllInitialChessPiecesFeature_name;
	public static String CreateAllInitialChessPiecesFeature_description;
	public static String CreateChessBoardFeature_name;
	public static String CreateChessBoardFeature_description;
	public static String CreateChessMoveFeature_name;
	public static String CreateChessMoveFeature_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
