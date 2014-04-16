/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
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
package org.eclipse.graphiti.examples.chess.features;

import org.eclipse.graphiti.examples.chess.MoveUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

public class AddChessMoveFeature extends AbstractAddFeature {

	public AddChessMoveFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		// Add the connection in case it is a move connection (indicated by a
		// property)
		if (context instanceof IAddConnectionContext && context.getProperty(MoveUtil.PROPERTY_MOVE) == Boolean.TRUE) {
			return true;
		}
		return false;
	}

	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Connection visualised as a line
		Connection connection = peCreateService.createFreeFormConnection(getDiagram());
		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());
		Polyline polyline = gaService.createPolyline(connection);
		polyline.setLineWidth(2);
		polyline.setForeground(manageColor(IColorConstant.ORANGE));

		// Add decorator for source of connection (circle)
		ConnectionDecorator cd = peCreateService.createConnectionDecorator(connection, false, 0, true);
		Ellipse circle = Graphiti.getGaCreateService().createEllipse(cd);
		circle.setLineWidth(2);
		gaService.setLocationAndSize(circle, 0, 0, 6, 6);
		circle.setForeground(manageColor(IColorConstant.ORANGE));

		// Add decorator for target of connection (cross)
		cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
		Polyline crossPolyline = Graphiti.getGaCreateService().createPolyline(cd,
				new int[] { -5, -5, 5, 5, 0, 0, -5, 5, 5, -5 });
		crossPolyline.setLineWidth(2);
		crossPolyline.setForeground(manageColor(IColorConstant.ORANGE));

		// Add an anchor for move continuation: one to grab the request to
		// connect to a connection...
		peCreateService.createChopboxAnchor(connection);
		// ... and one to actually connect to located at the end decorator
		peCreateService.createChopboxAnchor(cd);

		return connection;
	}
}
