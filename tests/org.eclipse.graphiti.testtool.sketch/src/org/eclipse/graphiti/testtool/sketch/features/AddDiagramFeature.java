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

import java.util.Collection;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class AddDiagramFeature.
 */
public class AddDiagramFeature extends AbstractAddShapeFeature {

	/**
	 * Instantiates a new adds the diagram feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AddDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	public PictogramElement add(IAddContext context) {
		((Collection) context.getTargetContainer().getChildren()).add(context.getNewObject());
		return null;
	}

	public boolean canAdd(IAddContext context) {
		if (context.getTargetContainer() != null && context.getNewObject() instanceof Diagram) {
			return true;
		}
		return false;
	}

}
