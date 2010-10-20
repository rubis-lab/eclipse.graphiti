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
package org.eclipse.graphiti.internal.util;

import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.ILook;

/**
 * The Class DefaultLook.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultLook implements ILook {

	private IColorConstant fieldErrorBackgroundColor;

	private IColorConstant fieldErrorForegroundColor;

	private IColorConstant gridBackgroundColor;

	private IColorConstant majorGridLineColor;

	private IColorConstant minorGridLineColor;

	public IColorConstant getFieldErrorBackgroundColor() {
		if (fieldErrorBackgroundColor == null) {
			fieldErrorBackgroundColor = new ColorConstant("FBE9EB"); //$NON-NLS-1$
		}
		return fieldErrorBackgroundColor;
	}

	@Override
	public IColorConstant getFieldErrorForegroundColor() {
		if (fieldErrorForegroundColor == null) {
			fieldErrorForegroundColor = new ColorConstant("B00017"); //$NON-NLS-1$
		}
		return fieldErrorForegroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getGridBackgroundColor()
	 */
	public IColorConstant getGridBackgroundColor() {
		if (gridBackgroundColor == null) {
			gridBackgroundColor = new ColorConstant("FFFFFF"); //$NON-NLS-1$
		}
		return gridBackgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getGridLineThickness()
	 */
	public int getGridLineThickness() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMajorGridLineColor()
	 */
	public IColorConstant getMajorGridLineColor() {
		if (majorGridLineColor == null) {
			majorGridLineColor = new ColorConstant("CEE0F2"); //$NON-NLS-1$
		}
		return majorGridLineColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMajorGridLineDistance()
	 */
	public int getMajorGridLineDistance() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMinorGridLineColor()
	 */
	public IColorConstant getMinorGridLineColor() {
		if (minorGridLineColor == null) {
			minorGridLineColor = new ColorConstant("E3EEF9"); //$NON-NLS-1$
		}
		return minorGridLineColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMinorGridLineDistance()
	 */
	public int getMinorGridLineDistance() {
		return 10;
	}
}
