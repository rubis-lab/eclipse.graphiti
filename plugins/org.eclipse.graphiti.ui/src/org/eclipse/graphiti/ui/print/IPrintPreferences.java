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
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.print;

/**
 * A generic interface for preference containers for printing a diagram, which
 * serves as abstraction for generic edit fields which store their values in a
 * preference object
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IPrintPreferences {

	/**
	 * Resets all preferences to their default values.
	 */
	public void setDefaults();

	/**
	 * Sets the integer preference value of the preference at position atIndex
	 * to the given value.
	 * 
	 * @param atIndex
	 *            The index of the preference to set
	 * @param value
	 *            The new value to set
	 */
	public void setIntPreference(int atIndex, int value);

	/**
	 * Returns the integer value of the preference with the given index.
	 * 
	 * @param atIndex
	 *            The index of the preference to return
	 * @return The value of the preference with the given index as an integer
	 */
	public int getIntPreference(int atIndex);

	/**
	 * Sets the double preference value of the preference at position atIndex to
	 * the given value.
	 * 
	 * @param atIndex
	 *            The index of the preference to set
	 * @param value
	 *            The new value to set
	 */
	public void setDoublePreference(int atIndex, double value);

	/**
	 * Returns the double value of the preference with the given index.
	 * 
	 * @param atIndex
	 *            The index of the preference to return
	 * @return The value of the preference with the given index as a double
	 */
	public double getDoublePreference(int atIndex);
}