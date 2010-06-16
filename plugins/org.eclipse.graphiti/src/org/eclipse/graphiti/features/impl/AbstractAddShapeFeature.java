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
/*
 * Created on 12.07.2005
 *


 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;

/**
 * Add feature especially for pictogram elements.
 */
public abstract class AbstractAddShapeFeature extends AbstractAddPictogramElementFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractAddShapeFeature(IFeatureProvider fp) {
		super(fp);
	}

}