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
package org.eclipse.graphiti.testtool.sketch;

import org.eclipse.graphiti.features.FeatureCheckerAdapter;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.testtool.sketch.features.SwitchModeFeature;

public class ViewerModeChecker extends FeatureCheckerAdapter {

	public ViewerModeChecker() {
		super(false);
	}

	@Override
	public boolean allow(IFeature feature, IContext context) {
		if (feature instanceof SwitchModeFeature) {
			if (context instanceof CustomContext) {
				CustomContext cc = (CustomContext) context;
				PictogramElement[] pes = cc.getPictogramElements();
				return pes[0] instanceof Diagram;
			}
		}
		return false;
	}

	@Override
	public boolean allowCustomFeatures(ICustomContext context) {
		return true;
	}
}
