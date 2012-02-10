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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
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
		compositeConnection.getChildren().add(
				Graphiti.getPeCreateService().createCurvedConnection(new double[] { 0.1d, -50d, 0.9d, -50d },
						getDiagram()));
		compositeConnection.getChildren().add(
				Graphiti.getPeCreateService().createCurvedConnection(new double[] { 0.1d, 50d, 0.9d, 50d },
						getDiagram()));
		return compositeConnection;
	}
}
