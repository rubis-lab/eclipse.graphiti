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
package org.eclipse.graphiti.sample.sketch.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.sample.sketch.SketchUtil;

/**
 * The Class SketchLabelDirectEditingFeature.
 */
public class SketchLabelDirectEditingFeature extends AbstractDirectEditingFeature {

	/**
	 * Instantiates a new sketch label direct editing feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchLabelDirectEditingFeature(IFeatureProvider fp) {
		super(fp);
	}

	public int getEditingType() {
		return TYPE_MULTILINETEXT;
	}

	public String getInitialValue(IDirectEditingContext context) {
		return SketchUtil.getCurrentLabelValue(context.getPictogramElement());
	}

	public void setValue(String value, IDirectEditingContext context) {
		SketchUtil.setCurrentLabelValue(context.getPictogramElement(), value);
	}

}
