/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 470455 - Difficulty in creating associations
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.command;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.junit.Test;

public class CreateConnectionCommandTest {

	/*
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=470455
	 */
	@Test
	public void whenInnerShapeHasNoAnchor_andCanStartConnection_thenReturnTrue() {
		ICreateService createService = Graphiti.getCreateService();

		Diagram diagram = createService.createDiagram("dummy", "name", true);
		ContainerShape containerShape = createService.createContainerShape(diagram, true);
		createService.createChopboxAnchor(containerShape);
		createService.createRectangle(containerShape);
		ContainerShape anotherShape = createService.createContainerShape(containerShape, true);
		createService.createRectangle(anotherShape);
		Shape textShape = createService.createShape(anotherShape, true);
		createService.createText(textShape);

		ICreateConnectionFeature createConnectionFeature = new AbstractCreateConnectionFeature(null, "Test", "Test") {

			@Override
			public Connection create(ICreateConnectionContext context) {
				return null;
			}

			@Override
			public boolean canStartConnection(ICreateConnectionContext context) {
				return true;
			}

			@Override
			public boolean canCreate(ICreateConnectionContext context) {
				return false;
			}
		};
		ArrayList<IFeature> featureList = new ArrayList<IFeature>(1);
		featureList.add(createConnectionFeature);

		CreateConnectionCommand createConnectionCommand = new CreateConnectionCommand(null, textShape, featureList);
		assertTrue(createConnectionCommand.canStartConnection());
	}
}
