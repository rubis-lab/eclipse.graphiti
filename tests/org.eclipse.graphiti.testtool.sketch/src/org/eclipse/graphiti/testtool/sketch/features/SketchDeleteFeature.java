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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.impl.DefaultDeleteFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class SketchDeleteFeature.
 */
public class SketchDeleteFeature extends DefaultDeleteFeature {

	/**
	 * Instantiates a new sketch delete feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		final PictogramElement pe = context.getPictogramElement();
		final Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo != null) {
			return super.canDelete(context);
		}
		return false;
	}

}
