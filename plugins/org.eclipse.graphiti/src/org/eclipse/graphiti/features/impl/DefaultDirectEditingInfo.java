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
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DefaultDirectEditingInfo.
 */
public class DefaultDirectEditingInfo implements IDirectEditingInfo {

	private PictogramElement mainPictogramElement;

	private PictogramElement pictogramElement;

	private GraphicsAlgorithm graphicsAlgorithm;

	private boolean isActive = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#getGraphicsAlgorithm()
	 */
	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return graphicsAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#getMainPictogramElement()
	 */
	public PictogramElement getMainPictogramElement() {
		return mainPictogramElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#getPictogramElement()
	 */
	public PictogramElement getPictogramElement() {
		return pictogramElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#isActive()
	 */
	public boolean isActive() {
		return isActive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#reset()
	 */
	public void reset() {
		setMainPictogramElement(null);
		setPictogramElement(null);
		setGraphicsAlgorithm(null);
		setActive(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#setActive(boolean)
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#setGraphicsAlgorithm(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm)
	 */
	public void setGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#setMainPictogramElement(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public void setMainPictogramElement(PictogramElement pictogramElement) {
		this.mainPictogramElement = pictogramElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IDirectEditingInfo#setPictogramElement(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public void setPictogramElement(PictogramElement pictogramElement) {
		this.pictogramElement = pictogramElement;
	}
}
