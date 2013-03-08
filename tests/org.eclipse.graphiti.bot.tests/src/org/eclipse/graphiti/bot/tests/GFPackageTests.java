/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 363539 - Enabled feature delegation via IDiagramEditor.execute method
 *    mgorning - Bug 371671 - addGraphicalRepresentation returns null in dark mode
 *    mwenz - Felix Velasco - Bug 374918 - Let default paste use LocalSelectionTransfer
 *    fvelasco - Bug 396247 - ImageDescriptor changes
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.bot.tests.features.DefaultCopyFeature;
import org.eclipse.graphiti.bot.tests.util.ITestConstants;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ConfigurableFeatureProviderWrapper;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.AreaAnchorContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.context.impl.CopyContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.PasteContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveAnchorFeature;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.mapping.data.ImageDataMapping;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.testtool.ecore.TestDiagramTypeProvider;
import org.eclipse.graphiti.testtool.sketch.SketchDiagramTypeProvider;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.editor.GFMarqueeSelectionTool;
import org.eclipse.graphiti.ui.internal.util.clipboard.ModelClipboard;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.LocationInfo;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.junit.Test;

// Check whether this test makes sense at all...
@SuppressWarnings("restriction")
public class GFPackageTests extends AbstractGFTests {

	public GFPackageTests() {
		super();
	}

	@Test
	public void testDarkFeatureProcessing() throws Exception {
		String[] providerIds = GraphitiUi.getExtensionManager().getDiagramTypeProviderIds(
				ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		assertTrue("no dtp for sketch diagram type available", providerIds.length > 0);
		if (providerIds.length > 0) {
			final Diagram d = createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH, "diagram");
			IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(d, providerIds[0]);
			assertNotNull("dtp couldn't be instantiated", dtp);

			// Bug 363539: assure that feature execution in dummy editor works
			final boolean[] canExecuteCalled = { false };
			final boolean[] executeCalled = { false };
			dtp.getDiagramBehavior().executeFeature(new AbstractFeature(dtp.getFeatureProvider()) {
				public boolean canExecute(IContext context) {
					canExecuteCalled[0] = true;
					return true;
				}

				public void execute(IContext context) {
					executeCalled[0] = true;
				}
			}, new DefaultContext());
			assertTrue(canExecuteCalled[0]);
			assertTrue(executeCalled[0]);

			// Bug 371671 - addGraphicalRepresentation returns null in dark mode
			AbstractAddFeature addFeature = new AbstractAddFeature(dtp.getFeatureProvider()) {

				public boolean canAdd(IAddContext context) {
					return true;
				}

				public PictogramElement add(IAddContext context) {
					Shape shape = Graphiti.getPeCreateService().createShape(d, true);
					return shape;
				}
			};

			Object result = dtp.getDiagramBehavior().executeFeature(addFeature, new AddContext());
			assertTrue(result instanceof Shape);
		}
	}

	@Test
	public void testGraphitiUi() throws Exception {
		String id = DiagramEditor.DIAGRAM_EDITOR_ID;
		assertNotNull(id);
		assertTrue(!("".equals(id)));
		assertNotNull(GraphitiUIPlugin.getDefault());
	}

	@Test
	public void testGraphitiUiInternalEditor() throws Exception {
		new GFMarqueeSelectionTool();
	}

	@Test
	public void testGraphitiFeatures() throws Exception {

		SketchDiagramTypeProvider mySketchDiagramTypeProvider = new SketchDiagramTypeProvider();
		IFeatureProvider provider = mySketchDiagramTypeProvider.getFeatureProvider();
		assertTrue(provider instanceof ConfigurableFeatureProviderWrapper);
		ConfigurableFeatureProviderWrapper myConfigurableFeatureProviderWrapper = (ConfigurableFeatureProviderWrapper) provider;

		// test canAdd
		AreaContext areaContext = new AreaContext();
		areaContext.setLocation(10, 20);
		final Diagram diagram = createDiagram("testPackageOrgEclipseGraphitiFeatures");
		Object value = new Object();
		AddContext addContext = new AddContext(areaContext, value);
		addContext.setTargetContainer(diagram);
		assertTrue(myConfigurableFeatureProviderWrapper.canAdd(addContext).toBoolean());
		addContext.setTargetContainer(null);
		assertFalse(myConfigurableFeatureProviderWrapper.canAdd(addContext).toBoolean());

		// test getAddFeature
		addContext.setTargetContainer(diagram);
		assertNotNull(myConfigurableFeatureProviderWrapper.getAddFeature(addContext));

		// test canLayout
		IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		PictogramElement pe = getPictogramElement(diagramEditor);
		LayoutContext layoutContext = new LayoutContext(pe);
		assertFalse(myConfigurableFeatureProviderWrapper.canLayout(layoutContext).toBoolean());
		page.closeActiveEditor();
	}

	@Test
	public void testGraphitiFeaturesContext() throws Exception {


		String s = null;
		TestDiagramTypeProvider myDiagramTypeProvider = new TestDiagramTypeProvider();
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
		myDiagramTypeProvider.init(diagram, diagramEditor);
		PictogramElement pe = getPictogramElement(diagramEditor);
		EList<Shape> shapes = diagram.getChildren();
		assertNotNull(pe);
		PictogramElement[] pes = new PictogramElement[] { pe };

		final DefaultCopyFeature myDefaultCopyFeature = new DefaultCopyFeature(
				myDiagramTypeProvider.getFeatureProvider());
		final ICopyContext copyContext = new CopyContext(pes);
		assertEquals(true, myDefaultCopyFeature.canExecute(copyContext));

		syncExec(new VoidResult() {
			public void run() {
				myDefaultCopyFeature.execute(copyContext);
			}
		});

		s = null;
		s = myDefaultCopyFeature.getName();
		assertNotNull(s);
		assertFalse("".equals(s));
		final MyPasteFeature myPasteFeature = new MyPasteFeature(myDiagramTypeProvider.getFeatureProvider(), diagram);
		final PasteContext pasteContext = new PasteContext(pes);
		pasteContext.setLocation(100, 200);

		syncExec(new VoidResult() {
			public void run() {
				assertEquals(true, myPasteFeature.canExecute(pasteContext));

				myPasteFeature.execute(pasteContext);
				diagramEditor.doSave(null);
				// duplicated and pasted objects are not equal with original
				// objects
				assertFalse(myPasteFeature.isEqual());
			}
		});

		s = null;
		s = myPasteFeature.getName();
		assertNotNull(s);
		assertFalse("".equals(s));

		// test ModelClipboard to paste a root element (parent = null)
		final Diagram dia = myDiagramTypeProvider.getDiagram();
		final EObject[] objs = new EObject[] { dia };
		syncExec(new VoidResult() {
			public void run() {
				ModelClipboard.getDefault().setContent(objs);
				Collection<EObject> copy = ModelClipboard.getDefault().duplicateAndPaste(null,
						ed.getTransactionalEditingDomain());
				diagramEditor.doSave(null);
				assertTrue(!copy.isEmpty() && !copy.contains(dia));
			}
		});

		DefaultMoveAnchorFeature myDefaultMoveAnchorFeature = new DefaultMoveAnchorFeature(
				myDiagramTypeProvider.getFeatureProvider());

		GraphicsAlgorithm graphicsAlgorithmMock = createNiceMock(GraphicsAlgorithm.class);
		replay(graphicsAlgorithmMock);

		FixPointAnchor fixPointAnchorMock = createNiceMock(FixPointAnchor.class);
		expect(fixPointAnchorMock.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(fixPointAnchorMock);

		AreaAnchorContext myAreaAnchorContext = new AreaAnchorContext(fixPointAnchorMock);
		assertEquals(true, myDefaultMoveAnchorFeature.canMoveAnchor(myAreaAnchorContext));

		myAreaAnchorContext.setLocation(10, 20);
		myDefaultMoveAnchorFeature.canExecute(myAreaAnchorContext);
		myDefaultMoveAnchorFeature.execute(myAreaAnchorContext);

		AnchorContainer myAnchorContainer = createNiceMock(AnchorContainer.class);
		expect(myAnchorContainer.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock);
		replay(myAnchorContainer);

		BoxRelativeAnchor boxRelativeAnchor = createNiceMock(BoxRelativeAnchor.class);
		expect(boxRelativeAnchor.getParent()).andReturn(myAnchorContainer);
		expect(boxRelativeAnchor.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(boxRelativeAnchor);

		myAreaAnchorContext = new AreaAnchorContext(boxRelativeAnchor);
		myAreaAnchorContext.setLocation(10, 20);

		myDefaultMoveAnchorFeature.moveAnchor(myAreaAnchorContext);

		s = null;
		s = myDefaultMoveAnchorFeature.getName();
		assertNotNull(s);
		assertFalse("".equals(s));

		DefaultReconnectionFeature myDefaultReconnectionFeature = new DefaultReconnectionFeature(
				myDiagramTypeProvider.getFeatureProvider());

		Anchor anchorMock = createNiceMock(Anchor.class);
		expect(anchorMock.getParent()).andReturn(shapes.get(0));
		replay(anchorMock);

		org.eclipse.graphiti.mm.pictograms.Connection connectionMock = createNiceMock(org.eclipse.graphiti.mm.pictograms.Connection.class);
		expect(connectionMock.getEnd()).andReturn(boxRelativeAnchor).anyTimes();
		expect(connectionMock.getStart()).andReturn(anchorMock).anyTimes();
		replay(connectionMock);

		IReconnectionContext myReconnectionContext = new ReconnectionContext(connectionMock, anchorMock, anchorMock,
				null);
		myReconnectionContext.setTargetPictogramElement(pe);

		assertTrue(myDefaultReconnectionFeature.canExecute(myReconnectionContext));

		myDefaultReconnectionFeature.reconnect(myReconnectionContext);

		s = null;
		s = myDefaultReconnectionFeature.getName();
		assertNotNull(s);
		assertFalse("".equals(s));

		DefaultFeatureProvider myDefaultFeatureProvider = new DefaultFeatureProvider(myDiagramTypeProvider);
		IUpdateContext context = new UpdateContext(null);
		IUpdateFeature updateFeature = myDefaultFeatureProvider.getUpdateFeature(context);
		final PictogramLink linkForPictogramElement = Graphiti.getLinkService().getLinkForPictogramElement(pe);
		if (linkForPictogramElement != null) {
			final EList<EObject> businessObject = linkForPictogramElement.getBusinessObjects();
			if (businessObject != null && !businessObject.isEmpty()) {
				executeInRecordingCommandInUIThread(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						businessObject.removeAll(businessObject);
					}
				});
			}
		}
		context = new UpdateContext(pe);
		try {
			updateFeature.update(context);
		} catch (Exception e) {
			// ignore
		}

		page.closeActiveEditor();
	}

	@Test
	public void testGraphitiPasteTwoDiagrams() throws Exception {
		TestDiagramTypeProvider myDiagramTypeProvider = new TestDiagramTypeProvider();
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
		myDiagramTypeProvider.init(diagram, diagramEditor);
		PictogramElement pe = getPictogramElement(diagramEditor);
		assertNotNull(pe);
		PictogramElement[] pes = new PictogramElement[] { pe };
		syncExec(new VoidResult() {
			public void run() {
				diagramEditor.doSave(null);
			}
		});

		final DefaultCopyFeature myDefaultCopyFeature = new DefaultCopyFeature(
				myDiagramTypeProvider.getFeatureProvider());
		final ICopyContext copyContext = new CopyContext(pes);
		assertEquals(true, myDefaultCopyFeature.canExecute(copyContext));

		syncExec(new VoidResult() {
			public void run() {
				myDefaultCopyFeature.execute(copyContext);
			}
		});

		// Test paste in a second diagram
		TestDiagramTypeProvider myDiagramTypeProvider2 = new TestDiagramTypeProvider();
		final IDiagramContainerUI diagramEditor2 = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE, "xmi", "diagram2");
		final Diagram diagram2 = diagramEditor2.getDiagramTypeProvider().getDiagram();
		myDiagramTypeProvider2.init(diagram2, diagramEditor2);
		final MyPasteFeature myPasteFeature = new MyPasteFeature(myDiagramTypeProvider2.getFeatureProvider(), diagram2);
		final PasteContext pasteContext = new PasteContext(pes);
		pasteContext.setLocation(100, 200);

		syncExec(new VoidResult() {
			public void run() {
				assertEquals(true, myPasteFeature.canExecute(pasteContext));

				myPasteFeature.execute(pasteContext);
				// duplicated and pasted objects are not equal with original
				// objects
				assertFalse(myPasteFeature.isEqual());
			}
		});

		syncExec(new VoidResult() {
			public void run() {
				diagramEditor2.doSave(null);
			}
		});
		page.closeAllEditors();
	}

	private PictogramElement getPictogramElement(IDiagramContainerUI diagramEditor) {

		Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
		addClassesAndReferencesToDiagram(diagramEditor);
		EList<Shape> shapes = diagram.getChildren();
		PictogramLink link;
		link = shapes.get(0).getLink();
		PictogramElement pe;
		pe = link.getPictogramElement();
		return pe;
	}

	private void addClassesAndReferencesToDiagram(final IDiagramContainerUI diagramEditor) {
		syncExec(new VoidResult() {
			public void run() {
				IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
				final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
				final Diagram currentDiagram = diagramTypeProvider.getDiagram();
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassesAndReferenceToDiagram(fp, currentDiagram, -100, -100, "Connection", -700, -200,
								"ConnectionDecorator");
					}
				});
			}
		});
	}

	// Should be moved to a unit test plugin for the graphiti pattern plugin
	@Test
	public void testMappingData() throws Exception {
		class MyImageDataMapping extends ImageDataMapping {
			public MyImageDataMapping(IMappingProvider mappingProvider) {
				super(mappingProvider);
			}
		}

		IMappingProvider mappingProviderMock = createNiceMock(IMappingProvider.class);
		replay(mappingProviderMock);

		MyImageDataMapping myImageDataMapping = new MyImageDataMapping(mappingProviderMock);

		PictogramLink pictogramLinkMock = createNiceMock(PictogramLink.class);
		replay(pictogramLinkMock);

		myImageDataMapping.getImageId(pictogramLinkMock);

		myImageDataMapping.getUpdateWarning(pictogramLinkMock);
	}

	@Test
	public void testGraphitiUtil() throws Exception {
		Shape shapeMock = createNiceMock(Shape.class);
		replay(shapeMock);

		GraphicsAlgorithm graphicsAlgorithmMock = createNiceMock(GraphicsAlgorithm.class);
		replay(graphicsAlgorithmMock);

		LocationInfo myLocationInfo = new LocationInfo(shapeMock, graphicsAlgorithmMock);

		assertTrue(shapeMock.equals(myLocationInfo.getShape()));

		assertTrue(graphicsAlgorithmMock.equals(myLocationInfo.getGraphicsAlgorithm()));

		graphicsAlgorithmMock = createNiceMock(GraphicsAlgorithm.class);
		expect(graphicsAlgorithmMock.getX()).andReturn(10).anyTimes();
		expect(graphicsAlgorithmMock.getY()).andReturn(10).anyTimes();
		replay(graphicsAlgorithmMock);

		PictogramElement pictogramElementMock = createNiceMock(PictogramElement.class);
		expect(pictogramElementMock.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(pictogramElementMock);

		shapeMock = createNiceMock(Shape.class);
		expect(shapeMock.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(shapeMock);
		EList<Shape> shapeList = new BasicEList<Shape>();
		shapeList.add(shapeMock);

		EList<org.eclipse.graphiti.mm.algorithms.styles.Point> points = new BasicEList<org.eclipse.graphiti.mm.algorithms.styles.Point>();

		ConnectionDecorator connectionDecoratorMock1 = createNiceMock(ConnectionDecorator.class);
		expect(connectionDecoratorMock1.isLocationRelative()).andReturn(new Boolean(true)).anyTimes();
		expect(connectionDecoratorMock1.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(connectionDecoratorMock1);

		ConnectionDecorator connectionDecoratorMock2 = createNiceMock(ConnectionDecorator.class);
		expect(connectionDecoratorMock2.isLocationRelative()).andReturn(new Boolean(false)).anyTimes();
		expect(connectionDecoratorMock2.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(connectionDecoratorMock2);

		EList<ConnectionDecorator> decorators = new BasicEList<ConnectionDecorator>();
		decorators.add(connectionDecoratorMock1);
		decorators.add(connectionDecoratorMock2);

		FreeFormConnection connectionMock = createNiceMock(FreeFormConnection.class);
		expect(connectionMock.getBendpoints()).andReturn(points).anyTimes();
		expect(connectionMock.getConnectionDecorators()).andReturn(decorators).anyTimes();
		expect(connectionMock.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(connectionMock);

		EList<org.eclipse.graphiti.mm.pictograms.Connection> connections = new BasicEList<org.eclipse.graphiti.mm.pictograms.Connection>();
		connections.add(connectionMock);

		Diagram diagramMock = createNiceMock(Diagram.class);
		expect(diagramMock.getChildren()).andReturn(shapeList).anyTimes();
		expect(diagramMock.getConnections()).andReturn(connections).anyTimes();
		replay(diagramMock);

		decorators = new BasicEList<ConnectionDecorator>();
		decorators.add(connectionDecoratorMock2);

		connectionMock = createNiceMock(FreeFormConnection.class);
		expect(connectionMock.getBendpoints()).andReturn(points).anyTimes();
		expect(connectionMock.getConnectionDecorators()).andReturn(decorators).anyTimes();
		expect(connectionMock.getGraphicsAlgorithm()).andReturn(graphicsAlgorithmMock).anyTimes();
		replay(connectionMock);

		connections = new BasicEList<org.eclipse.graphiti.mm.pictograms.Connection>();
		connections.add(connectionMock);

		diagramMock = createNiceMock(Diagram.class);
		expect(diagramMock.getChildren()).andReturn(shapeList).anyTimes();
		expect(diagramMock.getConnections()).andReturn(connections).anyTimes();
		replay(diagramMock);

		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();

		IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
		final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
		final Diagram currentDiagram = diagramTypeProvider.getDiagram();
		executeInRecordingCommandInUIThread(diagramEditor.getDiagramBehavior(), new Runnable() {
			public void run() {
				addClassesAndReferenceToDiagram(fp, currentDiagram, -100, -100, "Connection", -700, -200,
						"ConnectionDecorator");
				moveClassShape(fp, currentDiagram, 300, 300, "Connection");
				getPeService().setPropertyValue(diagram, "Test", "test");
			}
		});
		Property property = getPeService().getProperty(diagram, "Test");
		assertTrue("test".equals(property.getValue()));

		pictogramElementMock = createNiceMock(PictogramElement.class);
		replay(pictogramElementMock);

		page.closeActiveEditor();
	}

	final class MyPasteFeature extends AbstractPasteFeature {

		private PictogramElement[] copy;
		private PictogramElement[] fromClipboard = new PictogramElement[1];
		private Diagram diagram;

		public MyPasteFeature(IFeatureProvider fp, Diagram diagram) {
			super(fp);
			this.diagram = diagram;
		}

		public boolean canPaste(IPasteContext context) {
			this.copy = context.getPictogramElements();
			if (this.copy.length == 1) {
				if (this.copy[0] instanceof ContainerShape) {
					return getFromClipboard().length > 0;
				}
			}
			return false;
		}

		public void paste(IPasteContext context) {
			Object[] obs = getCopiesFromClipBoard(this.diagram);
			assertTrue((obs != null && obs.length > 0 && obs[0] instanceof PictogramElement));
			assertEquals(100, context.getX());
			assertEquals(200, context.getY());
			for (int i = 0; i < obs.length; i++) {
				if (obs[i] instanceof PictogramElement) {
					this.fromClipboard[i] = (PictogramElement) obs[i];
				}
			}
		}

		public boolean isEqual() {
			boolean res = false;
			if (this.copy != null && this.fromClipboard != null) {
				res = getFeatureProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider()
						.equalsBusinessObjects(this.copy[0], this.fromClipboard[0]);
			}
			return res;
		}
	}
}
