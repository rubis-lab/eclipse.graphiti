/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 421813 - Relative position to diagram of active Shape nested in inactive ContainerShape is calculated incorrectly
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tests.internal.services.impl;

import static org.junit.Assert.assertEquals;

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.internal.services.impl.PeServiceImpl;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.junit.Test;

@SuppressWarnings("restriction")
public class PeServiceImplTest {

	private PeServiceImpl peService = (PeServiceImpl) Graphiti.getPeService();

	private ICreateService createService = Graphiti.getCreateService();
	private IGaService gaService = Graphiti.getGaService();

	@Test
	public void getAbsoluteLocation_singleGa() throws Exception {
		ContainerShape containerShape = createService.createContainerShape(null, true);
		Rectangle rectangle = createService.createRectangle(containerShape);
		gaService.setLocationAndSize(rectangle, 10, 20, 100, 100);

		ILocation location = peService.getLocationRelativeToDiagram(containerShape);
		assertEquals(10, location.getX());
		assertEquals(20, location.getY());
	}

	@Test
	public void getAbsoluteLocation_twoGas() throws Exception {
		ContainerShape containerShape = createService.createContainerShape(null, true);
		Rectangle rectangle = createService.createRectangle(containerShape);
		gaService.setLocationAndSize(rectangle, 10, 20, 100, 100);
		ContainerShape containerShape2 = createService.createContainerShape(containerShape, true);
		Rectangle rectangle2 = createService.createRectangle(containerShape2);
		gaService.setLocationAndSize(rectangle2, 5, 15, 50, 50);

		ILocation location = peService.getLocationRelativeToDiagram(containerShape2);
		assertEquals(15, location.getX());
		assertEquals(35, location.getY());
	}

	@Test
	public void getAbsoluteLocation_twoGasWithInactive() throws Exception {
		ContainerShape containerShape = createService.createContainerShape(null, false);
		Rectangle rectangle = createService.createRectangle(containerShape);
		gaService.setLocationAndSize(rectangle, 10, 20, 100, 100);
		ContainerShape containerShape2 = createService.createContainerShape(containerShape, true);
		Rectangle rectangle2 = createService.createRectangle(containerShape2);
		gaService.setLocationAndSize(rectangle2, 5, 15, 50, 50);

		ILocation location = peService.getLocationRelativeToDiagram(containerShape2);
		assertEquals(5, location.getX());
		assertEquals(15, location.getY());
	}
}
