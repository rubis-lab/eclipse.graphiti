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
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

/**
 * The Interface IFeatureForPattern marks a feature as being executable inside a
 * pattern. It is used for all the features that wrap pattern functionality and
 * should not be used by clients directly.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           {@link FeatureForPatternDelegate} instead
 */
public interface IFeatureForPattern {

	/**
	 * Gets the pattern.
	 * 
	 * @return the pattern
	 */
	IPattern getPattern();
}
