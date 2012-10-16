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

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class SelectionInfoImpl.
 */
public abstract class SelectionInfoImpl implements ISelectionInfo {

	private IColorConstant color;

	private IColorConstant hoverColor;

	private LineStyle lineStyle;

	private IColorConstant hoverColorParentSelected;

	/**
	 * Creates a new {@link SelectionInfoImpl}.
	 */
	public SelectionInfoImpl() {
	}

	public IColorConstant getColor() {
		return this.color;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public void setColor(IColorConstant color) {
		this.color = color;
	}

	public IColorConstant getHoverColor() {
		return this.hoverColor;
	}

	public void setHoverColor(IColorConstant hoverColor) {
		this.hoverColor = hoverColor;

	}

	public IColorConstant getHoverColorParentSelected() {
		return this.hoverColorParentSelected;
	}

	public void setHoverColorParentSelected(IColorConstant hoverColor) {
		this.hoverColorParentSelected = hoverColor;

	}

}
