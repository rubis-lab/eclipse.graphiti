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
package org.eclipse.graphiti.tests.cases;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.MoveBendpointContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.datatypes.impl.DimensionImpl;
import org.eclipse.graphiti.internal.datatypes.impl.LocationImpl;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.util.ILocationInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PeServiceTest extends GFAbstractTestCase {
	final static String CORRECT_DIAGRAM_NAME = "sketch diagram";
	private static IFeatureProvider fpMock;

	final static String WRONG_DIAGRAM_NAME = "sktch digram";

	@AfterClass
	public static void afterClass() {
		fpMock = null;
	}

	@BeforeClass
	public static void prepareClass() {
		fpMock = createNiceMock(IFeatureProvider.class);
		IDiagramTypeProvider dtpMock = createMock(IDiagramTypeProvider.class);
		IDiagramEditor editorMock = createNiceMock(IDiagramEditor.class); // nice
																			// mock
																			// returns
																			// defaults
		expect(fpMock.getDiagramTypeProvider()).andReturn(dtpMock).anyTimes();
		expect(fpMock.getRemoveFeature(isA(RemoveContext.class))).andReturn(new DefaultRemoveFeature(fpMock));
		expect(dtpMock.getDiagramEditor()).andReturn(editorMock).anyTimes();
		replay(fpMock, dtpMock, editorMock);
	}

	private Diagram d;

	@Test
	public void checkActiveContainerPe() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);
		ContainerShape cs2 = createService.createContainerShape(cs1, false);
		assertNotNull(cs2);
		ContainerShape cs3 = createService.createContainerShape(cs2, false);
		assertNotNull(cs3);
		Ellipse el3 = createService.createEllipse(cs3);
		assertNotNull(el3);

		PictogramElement activeContainerPe1 = Graphiti.getPeService().getActiveContainerPe(cs3);
		assertEquals(cs1, activeContainerPe1);
		PictogramElement activeContainerPe2 = Graphiti.getPeService().getActiveContainerPe(el3);
		assertEquals(cs1, activeContainerPe2);
	}

	@Test
	public void checkAllContainedPes() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);
		ContainerShape cs2 = createService.createContainerShape(cs1, false);
		assertNotNull(cs2);
		ContainerShape cs3 = createService.createContainerShape(cs2, false);
		assertNotNull(cs3);

		Collection<PictogramElement> allContainedPes = Graphiti.getPeService().getAllContainedPictogramElements(d);
		List<EObject> expectedList = Arrays.asList(new EObject[] { cs1, cs2, cs3 });
		assertTrue(allContainedPes.containsAll(expectedList));
	}

	@Test
	public void checkAllContainedShapes() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);
		ContainerShape cs2 = createService.createContainerShape(cs1, false);
		assertNotNull(cs2);
		ContainerShape cs3 = createService.createContainerShape(cs2, false);
		assertNotNull(cs3);

		Collection<Shape> allContainedShapes = Graphiti.getPeService().getAllContainedShapes(d);
		List<EObject> expectedList = Arrays.asList(new EObject[] { cs1, cs2, cs3 });
		assertTrue(allContainedShapes.containsAll(expectedList));
	}

	@Test
	public void checkAnchor() {
		ILayoutService layoutService = Graphiti.getLayoutService();
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		layoutService.setLocationAndSize(r1, 100, 100, 100, 200);

		ContainerShape cs2 = createService.createContainerShape(d, false);
		assertNotNull(cs2);

		IPeService peService = Graphiti.getPeService();
		FreeFormConnection freeFormConnection = peService.createFreeFormConnection(d);
		assertNotNull(freeFormConnection);

		Anchor chopboxAnchor1 = peService.createChopboxAnchor(cs1);
		assertNotNull(chopboxAnchor1);
		assertEquals(chopboxAnchor1, peService.getChopboxAnchor(cs1));

		Anchor chopboxAnchor2 = peService.createChopboxAnchor(cs2);
		assertNotNull(chopboxAnchor2);
		assertEquals(chopboxAnchor2, peService.getChopboxAnchor(cs2));

		freeFormConnection.setStart(chopboxAnchor1);
		freeFormConnection.setEnd(chopboxAnchor2);
		assertEquals(freeFormConnection, peService.getOutgoingConnections(cs1).get(0));
		assertEquals(freeFormConnection, peService.getIncomingConnections(cs2).get(0));

		assertEquals(cs1, peService.getPictogramElementParent(chopboxAnchor1));
		assertEquals(d, peService.getPictogramElementParent(freeFormConnection));

		ILocation locationRelativeToDiagram = layoutService.getLocationRelativeToDiagram(chopboxAnchor1);
		assertEquals(150, locationRelativeToDiagram.getX());
		assertEquals(200, locationRelativeToDiagram.getY());

		IRectangle gaBoundsForAnchor = layoutService.getGaBoundsForAnchor(chopboxAnchor1);
		assertEquals(100, gaBoundsForAnchor.getWidth(), 0d);
		assertEquals(200, gaBoundsForAnchor.getHeight(), 0d);
	}

	@Test
	public void checkChopBoxAnchor() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		ContainerShape cs2 = createService.createContainerShape(d, false);
		assertNotNull(cs2);

		IPeService peService = Graphiti.getPeService();
		FreeFormConnection freeFormConnection = peService.createFreeFormConnection(d);
		assertNotNull(freeFormConnection);

		Anchor chopboxAnchor1 = peService.createChopboxAnchor(cs1);
		assertNotNull(chopboxAnchor1);
		assertEquals(chopboxAnchor1, peService.getChopboxAnchor(cs1));

		Anchor chopboxAnchor2 = peService.createChopboxAnchor(cs2);
		assertNotNull(chopboxAnchor2);
		assertEquals(chopboxAnchor2, peService.getChopboxAnchor(cs2));

		freeFormConnection.setStart(chopboxAnchor1);
		freeFormConnection.setEnd(chopboxAnchor2);
		assertEquals(freeFormConnection, peService.getOutgoingConnections(cs1).get(0));
		assertEquals(freeFormConnection, peService.getIncomingConnections(cs2).get(0));

		assertEquals(cs1, peService.getPictogramElementParent(chopboxAnchor1));
		assertEquals(d, peService.getPictogramElementParent(freeFormConnection));
	}

	@Test
	public void checkConnectionStuff() {
		ICreateService createService = Graphiti.getCreateService();
		Shape s1 = createService.createShape(d, true);
		assertNotNull(s1);
		Rectangle r1 = createService.createRectangle(s1);
		assertNotNull(r1);
		Shape s2 = createService.createShape(d, true);
		assertNotNull(s2);
		Rectangle r2 = createService.createRectangle(s2);
		assertNotNull(r2);

		ILayoutService layoutService = Graphiti.getLayoutService();
		layoutService.setLocationAndSize(r1, 0, 0, 100, 100);
		layoutService.setLocationAndSize(r2, 300, 300, 100, 100);

		FixPointAnchor anchor1 = createService.createFixPointAnchor(s1);
		anchor1.setLocation(createService.createPoint(50, 50));
		FixPointAnchor anchor2 = createService.createFixPointAnchor(s2);
		anchor2.setLocation(createService.createPoint(50, 50));

		FreeFormConnection connection1 = createService.createFreeFormConnection(d);
		assertNotNull(connection1);
		connection1.setStart(anchor1);
		connection1.setEnd(anchor2);

		ILocation connectionMidpoint = layoutService.getConnectionMidpoint(connection1, 0.5);
		assertEquals(new LocationImpl(200, 200), connectionMidpoint);

		org.eclipse.graphiti.mm.algorithms.styles.Point bendpoint = createService.createPoint(150, 150);
		connection1.getBendpoints().add(bendpoint);
		IExecutionInfo executionInfo1 = new DefaultExecutionInfo();
		MoveBendpointContext moveBendpointContext1 = new MoveBendpointContext(bendpoint);
		moveBendpointContext1.setLocation(200, 200);
		IPeService peService = Graphiti.getPeService();
		peService.moveBendpoints(executionInfo1);
		assertEquals(new LocationImpl(200, 200), layoutService.getConnectionMidpoint(connection1, 0.5));

		Diagram diagramForAnchor = peService.getDiagramForAnchor(anchor1);
		assertEquals(d, diagramForAnchor);

		Collection<Connection> allConnections1 = peService.getAllConnections(anchor1);
		assertTrue((allConnections1.size() == 1) && (allConnections1.contains(connection1)));

		Collection<Connection> allConnections2 = peService.getAllConnections(s1);
		assertTrue(allConnections2.size() == 1 && allConnections2.contains(connection1));

		Collection<Connection> outgoingConnections = peService.getOutgoingConnections(s1);
		assertTrue(outgoingConnections.size() == 1 && outgoingConnections.contains(connection1));

		Collection<Connection> incomingConnections = peService.getIncomingConnections(s2);
		assertTrue(incomingConnections.size() == 1 && incomingConnections.contains(connection1));
	}

	@Test
	public void checkManhattanConnection() {
		ICreateService createService = Graphiti.getCreateService();
		Shape s1 = createService.createShape(d, true);
		assertNotNull(s1);
		Rectangle r1 = createService.createRectangle(s1);
		assertNotNull(r1);
		Shape s2 = createService.createShape(d, true);
		assertNotNull(s2);
		Rectangle r2 = createService.createRectangle(s2);
		assertNotNull(r2);

		ILayoutService layoutService = Graphiti.getLayoutService();
		layoutService.setLocationAndSize(r1, 0, 0, 100, 100);
		layoutService.setLocationAndSize(r2, 300, 300, 100, 100);

		FixPointAnchor anchor1 = createService.createFixPointAnchor(s1);
		anchor1.setLocation(createService.createPoint(50, 50));
		FixPointAnchor anchor2 = createService.createFixPointAnchor(s2);
		anchor2.setLocation(createService.createPoint(50, 50));

		ManhattanConnection connection1 = createService.createManhattanConnection(d);
		assertNotNull(connection1);
		connection1.setStart(anchor1);
		connection1.setEnd(anchor2);

		IPeService peService = Graphiti.getPeService();

		Diagram diagramForAnchor = peService.getDiagramForAnchor(anchor1);
		assertEquals(d, diagramForAnchor);

		Collection<Connection> allConnections1 = peService.getAllConnections(anchor1);
		assertTrue((allConnections1.size() == 1) && (allConnections1.contains(connection1)));

		Collection<Connection> allConnections2 = peService.getAllConnections(s1);
		assertTrue(allConnections2.size() == 1 && allConnections2.contains(connection1));

		Collection<Connection> outgoingConnections = peService.getOutgoingConnections(s1);
		assertTrue(outgoingConnections.size() == 1 && outgoingConnections.contains(connection1));

		Collection<Connection> incomingConnections = peService.getIncomingConnections(s2);
		assertTrue(incomingConnections.size() == 1 && incomingConnections.contains(connection1));
	}

	@Test
	public void checkLinkedPes() {
		ICreateService createService = Graphiti.getCreateService();
		Shape s1 = createService.createShape(d, true);
		assertNotNull(s1);
		Rectangle r1 = createService.createRectangle(s1);
		assertNotNull(r1);
		Shape s2 = createService.createShape(d, true);
		assertNotNull(s2);
		Rectangle r2 = createService.createRectangle(s2);
		assertNotNull(r2);

		Object[] linkedPictogramElements = Graphiti.getPeService().getLinkedPictogramElements(new EObject[] { s1, s2 }, d);
		assertArrayEquals(new Object[0], linkedPictogramElements);
	}

	@Test
	public void checkLocationInfo() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);

		ILayoutService layoutService = Graphiti.getLayoutService();
		layoutService.setLocationAndSize(r1, -10, -10, 100, 100, true);
		IDimension dimR1 = Graphiti.getGaService().calculateSize(r1);
		assertEquals(new DimensionImpl(100, 100), dimR1);

		layoutService.setLocation(r1, 100, 100, true);
		ILocationInfo locationInfo = layoutService.getLocationInfo(d, 150, 150);
		assertEquals(cs1, locationInfo.getShape());
		assertEquals(r1, locationInfo.getGraphicsAlgorithm());

		layoutService.setLocation(r1, 100, 100);
		ILocation locationRelativeToDiagram = layoutService.getLocationRelativeToDiagram(cs1);
		assertEquals(new LocationImpl(100, 100), locationRelativeToDiagram);

		layoutService.setSize(r1, 200, 200);
		IDimension dim2 = layoutService.calculateSize(r1, false);
		assertEquals(new DimensionImpl(200, 200), dim2);

		layoutService.setWidth(r1, 100);
		layoutService.setHeight(r1, 100);
		IDimension dim3 = layoutService.calculateSize(r1, false);
		assertEquals(new DimensionImpl(100, 100), dim3);
	}

	@Test
	public void checkProperties() {
		ICreateService createService = Graphiti.getCreateService();
		Shape s1 = createService.createShape(d, true);
		assertNotNull(s1);

		// local constants
		final String KEY = "key";
		final String VALUE = "value";
		IPeService peService = Graphiti.getPeService();
		peService.setPropertyValue(s1, KEY, VALUE);

		String propertyValue = peService.getPropertyValue(s1, KEY);
		assertEquals(VALUE, propertyValue);

		Property property = peService.getProperty(s1, KEY);
		assertNotNull(property);
		if (property != null) {
			assertEquals(VALUE, property.getValue());
		}

		peService.removeProperty(s1, KEY);
		assertNull(peService.getProperty(s1, KEY));
	}

	@Test
	public void checkSendToBackAndFront() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);
		ContainerShape cs2 = createService.createContainerShape(d, false);
		assertNotNull(cs2);

		IPeService peService = Graphiti.getPeService();
		Collection<Shape> allContainedShapes1 = peService.getAllContainedShapes(d);
		List<EObject> expectedList1 = Arrays.asList(new EObject[] { cs1, cs2 });
		assertTrue(allContainedShapes1.equals(expectedList1));

		peService.sendToFront(cs1);

		Collection<Shape> allContainedShapes2 = peService.getAllContainedShapes(d);
		List<EObject> expectedList2 = Arrays.asList(new EObject[] { cs2, cs1 });
		assertTrue(allContainedShapes2.equals(expectedList2));

		peService.sendToBack(cs1);

		Collection<Shape> allContainedShapes3 = peService.getAllContainedShapes(d);
		List<EObject> expectedList3 = Arrays.asList(new EObject[] { cs1, cs2 });
		assertTrue(allContainedShapes3.equals(expectedList3));
	}

	@Test
	public void createContainerShape() {
		IPeService peService = Graphiti.getPeService();
		ContainerShape cs1 = peService.createContainerShape(d, true);
		assertNotNull(cs1);

		Rectangle r1 = Graphiti.getCreateService().createRectangle(cs1);
		assertNotNull(r1);
		ILayoutService layoutService = Graphiti.getLayoutService();
		layoutService.setLocationAndSize(r1, 100, 200, 300, 400);
		IDimension r1Size = layoutService.calculateSize(r1);
		assertEquals(300, r1Size.getWidth());
		assertEquals(400, r1Size.getHeight());
		ILocation cs1Location = peService.getLocationRelativeToDiagram(cs1);
		assertEquals(100, cs1Location.getX());
		assertEquals(200, cs1Location.getY());

		Collection<PictogramElement> pictogramElementChildren = peService.getPictogramElementChildren(d);
		assertEquals(cs1, pictogramElementChildren.iterator().next());
		assertEquals(d, peService.getPictogramElementParent(cs1));
	}

	@Test
	public void createShape() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Shape s1 = createService.createShape(cs1, true);
		assertNotNull(s1);

		IPeService peService = Graphiti.getPeService();
		Collection<PictogramElement> pictogramElementChildren = peService.getPictogramElementChildren(cs1);
		assertEquals(s1, pictogramElementChildren.iterator().next());
		assertEquals(cs1, peService.getPictogramElementParent(s1));
	}

	@Test
	public void diagramForPes() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);

		Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(cs1);
		assertEquals(d, diagram);
	}

	@Test
	public void elementsNotInDiagram() {
		ICreateService createService = Graphiti.getCreateService();
		ContainerShape cs1 = createService.createContainerShape(d, true);
		assertNotNull(cs1);
		Rectangle r1 = createService.createRectangle(cs1);
		assertNotNull(r1);

		EObject[] elements = new EObject[] { d, cs1, r1 };
		EObject[] elementsNotInDiagram = Graphiti.getPeService().getElementsNotInDiagram(elements, d);

		List<EObject> resultList = Arrays.asList(elementsNotInDiagram);
		List<EObject> expectedList = Arrays.asList(elements);
		assertTrue(resultList.containsAll(expectedList));
	}

	@Before
	public void initializeTest() {
		d = Graphiti.getCreateService().createDiagram("sketch", WRONG_DIAGRAM_NAME, false);
		assertNotNull(d);
		ResourceImpl resource = new ResourceImpl();
		resource.getContents().add(d);
	}

	@Test
	public void setDiagramName() {
		assertFalse(d.isSnapToGrid());
		assertEquals("wrong diagram name", WRONG_DIAGRAM_NAME, d.getName());
		d.setName(CORRECT_DIAGRAM_NAME);
		assertEquals("wrong diagram name", CORRECT_DIAGRAM_NAME, d.getName());
	}

	@After
	public void uninitializeTest() {

	}
}
