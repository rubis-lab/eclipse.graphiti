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
 *    mgorning - Bug 391523 - Revise getSelectionInfo...() in IToolBehaviorProvider
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.util.IColorConstant;

/**
 * @since 0.10
 */
public class ShapeSelectionInfoImpl extends SelectionInfoImpl implements IShapeSelectionInfo {

	private IColorConstant primarySelectionBackgroundColor;
	private IColorConstant primarySelectionHandleForegroundColor;
	private IColorConstant primarySelectionHandleBackgroundColor;
	private IColorConstant secondarySelectionBackgroundColor;
	private IColorConstant secondarySelectionHandleForegroundColor;
	private IColorConstant secondarySelectionHandleBackgroundColor;

	public IColorConstant getPrimarySelectionBackgroundColor() {
		return primarySelectionBackgroundColor;
	}

	public IColorConstant getSecondarySelectionBackgroundColor() {
		return secondarySelectionBackgroundColor;
	}

	public void setPrimarySelectionBackgroundColor(IColorConstant color) {
		primarySelectionBackgroundColor = color;
	}

	public void setSecondarySelectionBackgroundColor(IColorConstant color) {
		secondarySelectionBackgroundColor = color;
	}

	public IColorConstant getPrimarySelectionHandleForegroundColor() {
		return primarySelectionHandleForegroundColor;
	}

	public IColorConstant getPrimarySelectionHandleBackgroundColor() {
		return primarySelectionHandleBackgroundColor;
	}

	public IColorConstant getSecondarySelectionHandleForegroundColor() {
		return secondarySelectionHandleForegroundColor;
	}

	public IColorConstant getSecondarySelectionHandleBackgroundColor() {
		return secondarySelectionHandleBackgroundColor;
	}

	public void setPrimarySelectionHandleForegroundColor(IColorConstant color) {
		primarySelectionHandleForegroundColor = color;
	}

	public void setPrimarySelectionHandleBackgroundColor(IColorConstant color) {
		primarySelectionHandleBackgroundColor = color;
	}

	public void setSecondarySelectionHandleForegroundColor(IColorConstant color) {
		secondarySelectionHandleForegroundColor = color;
	}

	public void setSecondarySelectionHandleBackgroundColor(IColorConstant color) {
		secondarySelectionHandleBackgroundColor = color;
	}
}
