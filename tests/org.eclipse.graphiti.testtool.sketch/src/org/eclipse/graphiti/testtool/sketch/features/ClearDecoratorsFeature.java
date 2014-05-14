/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 358255 - initial API, implementation and documentation
 *    mwenz - Bug 434458 - Connections don't support Color decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.testtool.sketch.SketchToolBehavior;

/**
 * The Class ClearDecoratorsFeature.
 */
public class ClearDecoratorsFeature extends AbstractCustomFeature {

	public ClearDecoratorsFeature(IFeatureProvider fp, ICustomContext context) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Clear All";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0) {
			PictogramElement pe = pes[0];

			SketchToolBehavior toolBehaviorProvider = (SketchToolBehavior) getFeatureProvider()
					.getDiagramTypeProvider().getCurrentToolBehaviorProvider();
			if (pe instanceof CompositeConnection) {
				EList<CurvedConnection> children = ((CompositeConnection) pe).getChildren();
				for (CurvedConnection curvedConnection : children) {
					toolBehaviorProvider.clearDecorators(curvedConnection);
				}
			} else {
				toolBehaviorProvider.clearDecorators(pe);
			}
			getDiagramBehavior().refreshRenderingDecorators(pe);
		}
	}

	@Override
	public boolean isAvailable(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			if (pes == null || pes.length > 1) {
				return false;
			}
			PictogramElement pe = pes[0];
			if (pe instanceof Shape && !(pe instanceof Diagram) || pe instanceof Connection) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasDoneChanges() {
		return false;
	}
}
