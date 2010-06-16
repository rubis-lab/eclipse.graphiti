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
package org.eclipse.graphiti.examples.common.pattern.compartment;

import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class DefaultCompartmentPatternConfiguration.
 */
public class DefaultCompartmentPatternConfiguration implements ICompartmentPatternConfiguration {

	private int lineWidth = 1;

	private int minimumHeight = 40;

	private int minimumWidth = 25;

	private int outerIndentTop = 2;

	private int outerIndentBottom = 2;

	private int outerIndentLeft = 5;

	private int outerIndentRight = 5;

	private IColorConstant foregroundColor = IColorConstant.BLACK;

	private IColorConstant backgroundColor = IColorConstant.WHITE;

	private IColorConstant textColor = IColorConstant.BLACK;

	private double transparency = 0;

	private int cornerHeight = 5;

	private int cornerWidth = 5;

	private boolean isHeaderImageVisible = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#getBackgroundColor()
	 */
	public IColorConstant getBackgroundColor() {
		return backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#getCornerHeight()
	 */
	public int getCornerHeight() {
		return cornerHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#getCornerWidth()
	 */
	public int getCornerWidth() {
		return cornerWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#getForegroundColor()
	 */
	public IColorConstant getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * Gets the line width.
	 * 
	 * @return the lineWidth
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IMinimumSizeConfiguration#getMinimumHeight()
	 */
	public int getMinimumHeight() {
		return minimumHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IMinimumSizeConfiguration#getMinimumWidth()
	 */
	public int getMinimumWidth() {
		return minimumWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#getOuterIndentBottom()
	 */
	public int getOuterIndentBottom() {
		return outerIndentBottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#getOuterIndentLeft()
	 */
	public int getOuterIndentLeft() {
		return outerIndentLeft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#getOuterIndentRight()
	 */
	public int getOuterIndentRight() {
		return outerIndentRight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#getOuterIndentTop()
	 */
	public int getOuterIndentTop() {
		return outerIndentTop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#getTextColor()
	 */
	public IColorConstant getTextColor() {
		return textColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#getTransparency()
	 */
	public double getTransparency() {
		return transparency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#setBackgroundColor(com.sap.tc.emf.gfw.util.IColorConstant)
	 */
	public void setBackgroundColor(IColorConstant color) {
		backgroundColor = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#setForegroundColor(com.sap.tc.emf.gfw.util.IColorConstant)
	 */
	public void setForegroundColor(IColorConstant color) {
		foregroundColor = color;
	}

	/**
	 * Sets the line width.
	 * 
	 * @param lineWidth
	 *            the lineWidth to set
	 */
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IMinimumSizeConfiguration#setMinimumHeight(int)
	 */
	public void setMinimumHeight(int minimumHeight) {
		this.minimumHeight = minimumHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IMinimumSizeConfiguration#setMinimumWidth(int)
	 */
	public void setMinimumWidth(int minimumWidth) {
		this.minimumWidth = minimumWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#setOuterIndentBottom(int)
	 */
	public void setOuterIndentBottom(int outerIndentBottom) {
		this.outerIndentBottom = outerIndentBottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#setOuterIndentLeft(int)
	 */
	public void setOuterIndentLeft(int outerIndentLeft) {
		this.outerIndentLeft = outerIndentLeft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#setOuterIndentRight(int)
	 */
	public void setOuterIndentRight(int outerIndentRight) {
		this.outerIndentRight = outerIndentRight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IIndentConfiguration#setOuterIndentTop(int)
	 */
	public void setOuterIndentTop(int outerIndentTop) {
		this.outerIndentTop = outerIndentTop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#setTextColor(com.sap.tc.emf.gfw.util.IColorConstant)
	 */
	public void setTextColor(IColorConstant color) {
		textColor = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.tc.emf.gfw.pattern.config.IColorConfiguration#setTransparency(int)
	 */
	public void setTransparency(int t) {
		transparency = t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#setCornerHeight(int)
	 */
	public void setCornerHeight(int cornerHeight) {
		this.cornerHeight = cornerHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#setCornerWidth(int)
	 */
	public void setCornerWidth(int cornerWidth) {
		this.cornerWidth = cornerWidth;
	}

	/**
	 * Sets the transparency.
	 * 
	 * @param transparency
	 *            the new transparency
	 */
	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#isHeaderImageVisible()
	 */
	public boolean isHeaderImageVisible() {
		return isHeaderImageVisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.examples.common.pattern.compartment.ICompartmentPatternConfiguration#setHeaderImageVisible(boolean)
	 */
	public void setHeaderImageVisible(boolean isHeaderImageVisible) {
		this.isHeaderImageVisible = isHeaderImageVisible;
	}
}
