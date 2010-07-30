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
package org.eclipse.graphiti.testtool.sketch.features.bd;

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.testtool.sketch.features.create.AbstractSketchCreateConnectionFeature;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class CreateChannelFeature.
 */
public class CreateChannelFeature extends AbstractSketchCreateConnectionFeature implements IBlockDiagramConfiguration {

	/**
	 * Instantiates a new creates the channel feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public CreateChannelFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	public Connection create(ICreateConnectionContext context) {
		Anchor startAnchor = context.getSourceAnchor();
		Anchor endAnchor = context.getTargetAnchor();

		if (startAnchor == null || endAnchor == null) {
			return null;
		}

		ILocation startLocation = Graphiti.getLayoutService().getLocationRelativeToDiagram(startAnchor);
		ILocation endLocation = Graphiti.getLayoutService().getLocationRelativeToDiagram(endAnchor);

		int xPosForChannelShape = (startLocation.getX() + endLocation.getX() - CHANNEL_SIZE) / 2;
		int yPosForChannelShape = (startLocation.getY() + endLocation.getY() - CHANNEL_SIZE) / 2;

		// create channel itself
		Anchor channelAnchor = createChannelShape(xPosForChannelShape, yPosForChannelShape);

		// create start and en connection
		Connection startConnection = createConnection(startAnchor, channelAnchor);
		createConnection(channelAnchor, endAnchor);

		return startConnection;
	}

	private Anchor createChannelShape(int x, int y) {
		ContainerShape channelShape = Graphiti.getPeCreateService().createContainerShape(getDiagram(), true);
		Graphiti.getPeService().setPropertyValue(channelShape, BLOCK_DIAGRAM_ELEMENT_TYPE, BD_CHANNEL);
		Ellipse channelEllipse = Graphiti.getGaCreateService().createEllipse(channelShape);
		Graphiti.getGaService().setLocationAndSize(channelEllipse, x, y, CHANNEL_SIZE, CHANNEL_SIZE, true);
		channelEllipse.setLineWidth(2);
		channelEllipse.setLineStyle(LineStyle.SOLID);
		channelEllipse.setFilled(false);
		channelEllipse.setForeground(manageColor(IColorConstant.LIGHT_BLUE));
		Anchor channelAnchor = Graphiti.getPeCreateService().createChopboxAnchor(channelShape);
		return channelAnchor;
	}

	private Connection createConnection(Anchor startAnchor, Anchor endAnchor) {
		FreeFormConnection ret = Graphiti.getPeCreateService().createFreeFormConnection(getDiagram());
		ret.setStart(startAnchor);
		ret.setEnd(endAnchor);

		Polyline p = Graphiti.getGaCreateService().createPolyline(ret);
		p.setLineWidth(2);
		p.setForeground(manageColor(IColorConstant.LIGHT_BLUE));
		p.setLineStyle(LineStyle.SOLID);

		return ret;
	}
}
