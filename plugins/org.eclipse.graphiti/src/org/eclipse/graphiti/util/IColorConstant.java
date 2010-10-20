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
 * The Interface IColorConstant.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IColorConstant {

	/**
	 * The "white" color.
	 */
	IColorConstant WHITE = new ColorConstant(255, 255, 255);

	/**
	 * The "light light gray" color.
	 */
	IColorConstant LIGHT_LIGHT_GRAY = new ColorConstant(250, 250, 250);

	/**
	 * The "light gray" color.
	 */
	IColorConstant LIGHT_GRAY = new ColorConstant(192, 192, 192);

	/**
	 * The "gray" color.
	 */
	IColorConstant GRAY = new ColorConstant(128, 128, 128);

	/**
	 * The "dark gray" color.
	 */
	IColorConstant DARK_GRAY = new ColorConstant(64, 64, 64);

	/**
	 * The "black" color.
	 */
	IColorConstant BLACK = new ColorConstant(0, 0, 0);

	/**
	 * The "red" color.
	 */
	IColorConstant RED = new ColorConstant(255, 0, 0);

	/**
	 * The "light orange" color.
	 */
	IColorConstant LIGHT_ORANGE = new ColorConstant(252, 207, 153);

	/**
	 * The "orange" color.
	 */
	IColorConstant ORANGE = new ColorConstant(255, 153, 51);

	/**
	 * The "dark orange" color.
	 */
	IColorConstant DARK_ORANGE = new ColorConstant(187, 102, 0);

	/**
	 * The "yellow" color.
	 */
	IColorConstant YELLOW = new ColorConstant(255, 255, 0);

	/**
	 * The "green" color.
	 */
	IColorConstant GREEN = new ColorConstant(0, 255, 0);

	/**
	 * The "light green" color.
	 */
	IColorConstant LIGHT_GREEN = new ColorConstant(96, 255, 96);

	/**
	 * The "dark green" color.
	 */
	IColorConstant DARK_GREEN = new ColorConstant(0, 127, 0);

	/**
	 * The "cyan" color.
	 */
	IColorConstant CYAN = new ColorConstant(0, 255, 255);

	/**
	 * The "light blue" color.
	 */
	IColorConstant LIGHT_BLUE = new ColorConstant(127, 127, 255);

	/**
	 * The "blue" color.
	 */
	IColorConstant BLUE = new ColorConstant(0, 0, 255);

	/**
	 * The "dark blue" color.
	 */
	IColorConstant DARK_BLUE = new ColorConstant(0, 0, 127);

	//
	// selection and selection handles
	//
	/**
	 * The handle foreground color.
	 */
	IColorConstant HANDLE_FG = DARK_ORANGE;

	/**
	 * The handle background color.
	 */
	IColorConstant HANDLE_BG = ORANGE;

	/**
	 * The foreground selection color of a shape.
	 */
	IColorConstant SHAPE_SELECTION_FG = LIGHT_ORANGE;

	/**
	 * The foreground selection color of a connection.
	 */
	IColorConstant CONNECTION_SELECTION_FG = ORANGE;

	/**
	 * Returns the amount of red in the color, from 0 to 255.
	 * 
	 * @return the red component of the color
	 */
	int getRed();

	/**
	 * Returns the amount of green in the color, from 0 to 255.
	 * 
	 * @return the green component of the color
	 */
	int getGreen();

	/**
	 * Returns the amount of blue in the color, from 0 to 255.
	 * 
	 * @return the blue component of the color
	 */
	int getBlue();
}
