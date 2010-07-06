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
package org.eclipse.graphiti.sample.sketch.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;

/**
 * The Class AbstractSketchCreateConnectionFeature.
 */
public abstract class AbstractSketchCreateConnectionFeature extends AbstractCreateConnectionFeature {

	/**
	 * Instantiates a new abstract sketch create connection feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public AbstractSketchCreateConnectionFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	public boolean canCreate(ICreateConnectionContext context) {
		boolean ret = false;

		// allow connection creation only if anchors do not belong to the same
		// container
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		if (sourceAnchor != null && targetAnchor != null) {
			AnchorContainer sourceParent = sourceAnchor.getParent();
			if (sourceParent != null && !sourceParent.equals(targetAnchor.getParent())) {
				ret = true;
			}
		}

		return ret;
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		boolean ret = false;

		if (context.getSourceAnchor() != null) {
			ret = true;
		}

		return ret;
	}
}
