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
package org.eclipse.graphiti.datatypes;

/**
 * The Interface IInsets. Returns the Insets values.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IInsets {

	/**
	 * Gets the left inset.
	 * 
	 * @return the left inset
	 */
	int getLeft();

	/**
	 * Gets the right inset.
	 * 
	 * @return the right inset
	 */
	int getRight();

	/**
	 * Gets the top inset.
	 * 
	 * @return the top inset
	 */
	int getTop();

	/**
	 * Gets the bottom inset.
	 * 
	 * @return the bottom inset
	 */
	int getBottom();
}
