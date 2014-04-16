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
public class ConnectionSelectionInfoImpl extends SelectionInfoImpl implements IConnectionSelectionInfo {

	private IColorConstant primarySelectionBendpointForegroundColor;
	private IColorConstant primarySelectionBendpointBackgroundColor;
	private IColorConstant secondarySelectionBendpointForegroundColor;
	private IColorConstant secondarySelectionBendpointBackgroundColor;

	public IColorConstant getPrimarySelectionBendpointForegroundColor() {
		return primarySelectionBendpointForegroundColor;
	}

	public IColorConstant getPrimarySelectionBendpointBackgroundColor() {
		return primarySelectionBendpointBackgroundColor;
	}

	public IColorConstant getSecondarySelectionBendpointForegroundColor() {
		return secondarySelectionBendpointForegroundColor;
	}

	public IColorConstant getSecondarySelectionBendpointBackgroundColor() {
		return secondarySelectionBendpointBackgroundColor;
	}

	public void setPrimarySelectionBendpointForegroundColor(IColorConstant color) {
		primarySelectionBendpointForegroundColor = color;
	}

	public void setPrimarySelectionBendpointBackgroundColor(IColorConstant color) {
		primarySelectionBendpointBackgroundColor = color;
	}

	public void setSecondarySelectionBendpointForegroundColor(IColorConstant color) {
		secondarySelectionBendpointForegroundColor = color;
	}

	public void setSecondarySelectionBendpointBackgroundColor(IColorConstant color) {
		secondarySelectionBendpointBackgroundColor = color;
	}

}
