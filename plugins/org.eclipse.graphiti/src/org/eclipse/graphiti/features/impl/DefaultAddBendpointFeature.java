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

import java.util.List;

import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.datatypes.Point;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Prefer using this default implementation over implementing interface
 * {@link org.eclipse.graphiti.features.IAddBendpointFeature} for yourself.
 */
public class DefaultAddBendpointFeature extends AbstractFeature implements IAddBendpointFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultAddBendpointFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IAddBendpointFeature#canAddBendpoint(org
	 * .eclipse.graphiti.features.context.IAddBendpointContext)
	 */
	public boolean canAddBendpoint(IAddBendpointContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IAddBendpointFeature#addBendpoint(org.eclipse
	 * .graphiti.features.context.IAddBendpointContext)
	 */
	public void addBendpoint(IAddBendpointContext context) {
		FreeFormConnection freeFormConnection = context.getConnection();
		List<Point> bendpoints = freeFormConnection.getBendpoints();
		Point newPoint = Graphiti.getGaService().createPoint(context.getX(), context.getY());
		int index = context.getBendpointIndex();
		bendpoints.add(index, newPoint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti
	 * .features .context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IAddBendpointContext) {
			ret = canAddBendpoint((IAddBendpointContext) context);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features
	 * .context.IContext)
	 */
	public void execute(IContext context) {
		addBendpoint((IAddBendpointContext) context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultAddBendpointFeature_0_xfld;
}
