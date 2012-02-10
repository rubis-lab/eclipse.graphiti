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
package org.eclipse.graphiti.testtool.sketch.features.create;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class SketchCreateCompositeConnectionFeature.
 */
public class SketchCreateCompositeConnectionFeature extends AbstractSketchCreateSimpleConnectionFeature {

	/**
	 * Instantiates a new sketch create composite connection feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public SketchCreateCompositeConnectionFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	protected Connection createConnection() {
		CompositeConnection compositeConnection = Graphiti.getPeCreateService().createCompositeConnection(getDiagram());
		CurvedConnection curvedConnection1 = Graphiti.getPeCreateService().createCurvedConnection(
				new double[] { 0.1d, -50d, 0.9d, -50d }, getDiagram());
		compositeConnection.getChildren().add(curvedConnection1);
		CurvedConnection curvedConnection2 = Graphiti.getPeCreateService().createCurvedConnection(
				new double[] { 0.1d, 50d, 0.9d, 50d }, getDiagram());
		compositeConnection.getChildren().add(curvedConnection2);
		return compositeConnection;
	}

	protected Connection createConnection(Anchor startAnchor, Anchor endAnchor) {
		Connection connection = createConnection();
		createVisualization(connection);
		connection.getGraphicsAlgorithm().setLineVisible(false);
		setAnchors(startAnchor, endAnchor, connection);

		EList<Connection> children = ((CompositeConnection) connection).getChildren();
		for (Iterator<Connection> childConnections = children.iterator(); childConnections.hasNext();) {
			Connection childConnection = childConnections.next();
			createVisualization(childConnection);
		}

		return connection;
	}
}
