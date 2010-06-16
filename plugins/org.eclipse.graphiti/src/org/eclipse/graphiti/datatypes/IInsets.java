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
 * The Interface IInsets. Returns the Insets parameters
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IInsets {

	/**
	 * Gets the left.
	 * 
	 * @return the left
	 */
	int getLeft();

	/**
	 * Gets the right.
	 * 
	 * @return the right
	 */
	int getRight();

	/**
	 * Gets the top.
	 * 
	 * @return the top
	 */
	int getTop();

	/**
	 * Gets the bottom.
	 * 
	 * @return the bottom
	 */
	int getBottom();
}
