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

	private IColorConstant secondarySelectionBackgroundColor;

	public IColorConstant getPrimarySelectionBackGroundColor() {
		return this.primarySelectionBackgroundColor;
	}

	public IColorConstant getSecondarySelectionBackGroundColor() {
		return this.secondarySelectionBackgroundColor;
	}

	public void setPrimarySelectionBackgroundColor(IColorConstant color) {
		this.primarySelectionBackgroundColor = color;
	}

	public void setSecondarySelectionBackgroundColor(IColorConstant color) {
		this.secondarySelectionBackgroundColor = color;
	}
}
