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
package org.eclipse.graphiti.internal;

/**
 * Provides constants for the Graphiti diagram versions and a description of the
 * changes. Digram versioning was not in place until Graphiti 0.8.0 and was
 * introduced for Juno M3 (version 0.9.0).
 */
public interface IDiagramVersion {
	
	/**
	 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=360800:<br>
	 * The background property of text GAs was ignored, the background color was
	 * always set to white. Changing that to the default behaviour caused old
	 * diagrams (not haveing the background color set for texts) appear awkward.
	 */
	public static final String VERSION_ENABLE_TEXT_BACKGROUND = "0.9.0"; //$NON-NLS-1$

	public static final String CURRENT = "0.9.1"; //$NON-NLS-1$
}
