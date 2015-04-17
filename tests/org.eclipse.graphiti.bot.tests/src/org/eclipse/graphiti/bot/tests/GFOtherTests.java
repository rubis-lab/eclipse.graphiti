/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mwenz - Bug 348662 - Setting tooptip to null in tool behavior provider doesn't clear up
 *                         tooltip if the associated figure has a previous tooltip
 *    mwenz - Bug 356828 - Escaped diagram name is used as editor title
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature and
 *                         called features via editor command stack to check it
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    mwenz - Bug 376008 - Iterating through navigation history causes exceptions
 *    mwenz - Bug 378342 - Cannot store more than a diagram per file
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz - Bug 391046 - Deadlock while saving prior to refactoring operation
 *    mwenz - Bug 433650 - Editor in in dirty state after a Save
 *    mwenz - Bug 441676 - Read-only attribute is not respected in Graphiti
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.bot.pageobjects.PoWorkbenchPage;
import org.eclipse.graphiti.bot.tests.util.ITestConstants;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.DefaultFeatureProviderWrapper;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.internal.DefaultFeatureAndContext;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.internal.util.DynamicLook;
import org.eclipse.graphiti.internal.util.LookManager;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.testtool.sketch.features.create.SketchCreateGaContainerFeature;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.DiagramEditorInputFactory;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.features.AbstractDrillDownFeature;
import org.eclipse.graphiti.ui.internal.IResourceRegistry;
import org.eclipse.graphiti.ui.internal.IResourceRegistryHolder;
import org.eclipse.graphiti.ui.internal.ResourceRegistry;
import org.eclipse.graphiti.ui.internal.command.CreateModelObjectCommand;
import org.eclipse.graphiti.ui.internal.feature.DebugFeature;
import org.eclipse.graphiti.ui.internal.figures.GFFigureUtil;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.draw2d.LineSeg;
import org.eclipse.graphiti.ui.internal.util.draw2d.LineSeg.KeyPoint;
import org.eclipse.graphiti.ui.internal.util.draw2d.LineSeg.Sign;
import org.eclipse.graphiti.ui.internal.util.ui.PopupMenu;
import org.eclipse.graphiti.ui.internal.util.ui.PopupMenu.CascadingMenu;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.PredefinedColoredAreas;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.junit.After;
import org.junit.Test;

public class GFOtherTests extends AbstractGFTests {

	public GFOtherTests() {
		super();
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		page.closeAllEditors();
		super.tearDown();
	}

	@Test
	public void testOnEcoreDiagram() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);

		syncExec(new VoidResult() {
			public void run() {

				final IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
				final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
				final Diagram currentDiagram = diagramTypeProvider.getDiagram();
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassesAndReferenceToDiagram(fp, currentDiagram, 100, 100, "Connection", 700, 200, "ConnectionDecorator");
					}
				});
				// check new model data in repository
				assertEquals("unexpected number of diagram children", 2, currentDiagram.getChildren().size());
				assertEquals("unexpected number of connections", 1, currentDiagram.getConnections().size());
				org.eclipse.graphiti.mm.pictograms.Connection c = (org.eclipse.graphiti.mm.pictograms.Connection) currentDiagram
						.getConnections().toArray()[0];
				assertEquals("unexpected number of connection decorators", 3, c.getConnectionDecorators().size());
				// waitForRefresh();
				// change background color of GA
				final Shape shape = findShapeForEClass(currentDiagram, "Connection");
				// waitForRefresh();
				diagramEditor.selectPictogramElements(new PictogramElement[] { shape });
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						moveClassShape(fp, currentDiagram, 10, 30, "Connection");
						removeClassShape(fp, currentDiagram, "ConnectionDecorator");
					}
				});

				// Test refreshes
				diagramEditor.getDiagramBehavior().refreshContent();
				diagramEditor.getDiagramBehavior().refreshPalette();
				diagramEditor.getDiagramBehavior().refreshRenderingDecorators(shape);
				diagramEditor.refreshTitleToolTip();
			}

		});

		page.shutdownEditor(diagramEditor);
	}

	@Test
	public void testDontSave() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);

		syncExec(new VoidResult() {
			public void run() {

				IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
				final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
				final Diagram currentDiagram = diagramTypeProvider.getDiagram();
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassToDiagram(fp, currentDiagram, 100, 100, "Shape");
					}
				});
			}
		});

		diagramEditor.doSave(new NullProgressMonitor());

		{
			ResourceSet rs = new ResourceSetImpl();
			URI uri = diagramEditor.getDiagramEditorInput().getUri();
			Resource res2 = rs.getResource(uri, true);
			assertEquals("Save has failed", 2, res2.getContents().size());
		}

		syncExec(new VoidResult() {
			public void run() {

				IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
				final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
				final Diagram currentDiagram = diagramTypeProvider.getDiagram();
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassToDiagram(fp, currentDiagram, 200, 200, "Shape");
					}
				});
			}
		});

		Resource res = diagramEditor.getDiagramTypeProvider().getDiagram().eResource();
		assertEquals("Shape was not created", 3, res.getContents().size());
		IFile file = GraphitiUiInternal.getEmfService().getFile(res.getURI());
		ResourceAttributes resourceAttributes = new ResourceAttributes();
		resourceAttributes.setReadOnly(true);
		file.setResourceAttributes(resourceAttributes);

		diagramEditor.doSave(new NullProgressMonitor());
		
		{
			ResourceSet rs = new ResourceSetImpl();
			URI uri = diagramEditor.getDiagramEditorInput().getUri();
			Resource res2 = rs.getResource(uri, true);
			assertEquals("File was saved after resource being set as read-only", 2, res2.getContents().size());
		}

		page.shutdownEditor(diagramEditor);
	}

	@Test
	public void testTooltip() throws Exception {
		/*
		 * Tests if the tooltips displayed in a diagram are correctly updated.
		 * The test uses the tutorial diagram type because there we had the
		 * situation that setting a previously set tooltip to null did not
		 * remove the tooltip from the figure. This was caused by the remove of
		 * the tooltip happening in the method
		 * PictogramElementDelegate.indicateNeededUpdates for the
		 * selectionFigure which is in case of the tutorial different from the
		 * figure holding the tooltip. See Bugzilla 348662 for details.
		 */
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_TUTORIAL);

		/*
		 * Open a new diagram containing just one class
		 */
		final IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
		final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
		final Diagram currentDiagram = diagramTypeProvider.getDiagram();
		executeInRecordingCommandInUIThread(diagramEditor.getDiagramBehavior(), new Runnable() {
			public void run() {
				/*
				 * Reuse of functionality originally written to add classes for
				 * the ECore test tool. Might need adaption in case of future
				 * changes
				 */
				addClassToDiagram(fp, currentDiagram, 300, 300, "Shape");
			}
		});

		/*
		 * Check the correctness of the initial tooltip
		 */
		IFigure figure = ed.getFigureWithLabel("Shape");
		// Check original tooltip
		if (!"Shape".equals(((Label) figure.getToolTip()).getText())) {
			fail("Tooltip must be 'Shape'");
		}

		/*
		 * Change the name of the eClass (and the display name in the diagram to
		 * avoid the need for calling the update feature)
		 */
		final ContainerShape tooltipShape = (ContainerShape) findShapeForEClass(currentDiagram, "Shape");
		Object bo = diagramTypeProvider.getFeatureProvider().getBusinessObjectForPictogramElement(tooltipShape);
		if (bo instanceof EClass) {
			final EClass eClass = (EClass) bo;
			// Change the tooltip to something else and check it
			executeInRecordingCommandInUIThread(diagramEditor.getDiagramBehavior(), new Runnable() {
				public void run() {
					eClass.setName("Changed");
					Text text = (Text) tooltipShape.getChildren().get(1).getGraphicsAlgorithm();
					text.setValue("Changed");
				}
			});
		}

		/*
		 * Check that the tooltip of the figure has been updated
		 */
		figure = ed.getFigureWithLabel("Changed");
		ContainerShape tooltipShape2 = (ContainerShape) findShapeForEClass(currentDiagram, "Changed");
		bo = diagramTypeProvider.getFeatureProvider().getBusinessObjectForPictogramElement(tooltipShape2);
		if (bo instanceof EClass) {
			if (!"Changed".equals(((Label) figure.getToolTip()).getText())) {
				fail("Tooltip must be 'Changed' but is '" + ((Label) figure.getToolTip()).getText() + "'");
			}
		}

		/*
		 * Change the name of the eClass to the empty string (and the display
		 * name in the diagram to avoid the need for calling the update
		 * feature), this will end up showing no tooltip.
		 */
		ContainerShape tooltipShape3 = (ContainerShape) findShapeForEClass(currentDiagram, "Changed");
		bo = diagramTypeProvider.getFeatureProvider().getBusinessObjectForPictogramElement(tooltipShape3);
		if (bo instanceof EClass) {
			final EClass eClass = (EClass) bo;
			executeInRecordingCommandInUIThread(diagramEditor.getDiagramBehavior(), new Runnable() {
				public void run() {
					// Change the tooltip to null and check it
					eClass.setName(""); // Empty name means no tooltip
					Text text = (Text) tooltipShape.getChildren().get(1).getGraphicsAlgorithm();
					text.setValue("Changed");
				}
			});
		}

		/*
		 * Check that the tooltip of the figure has been removed
		 */
		// Get the figure to check the tooltip via SWTBot
		figure = ed.getFigureWithLabel("");
		if (figure.getToolTip() != null) {
			fail("Tooltip must be null, but was '" + ((Label) figure.getToolTip()).getText() + "'");
		}
		page.shutdownEditor(diagramEditor);
	}

	@Test
	public void testUndoRedo() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
		final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
		final Diagram diagram = diagramTypeProvider.getDiagram();
		syncExec(new VoidResult() {
			public void run() {
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassToDiagram(fp, diagram, 500, 500, "Shape");
						addClassToDiagram(fp, diagram, 100, 100, "ContainerShape");
						removeClassShape(fp, diagram, "ContainerShape");
						moveClassShape(fp, diagram, 0, 0, "Shape");

					}
				});

				// get UnDoStack
				TransactionalEditingDomain editingDomain = diagramEditor.getDiagramBehavior().getEditingDomain();
				org.eclipse.emf.common.command.CommandStack cmdStack = editingDomain.getCommandStack();

				// process "undo" until UnDoStack is empty
				while (cmdStack.canUndo()) {
					cmdStack.undo();
				}
				assertEquals("Undo/Redo failed: Diagram not empty at end of UndoStack", 0, diagram.getChildren().size());

				// process "redo" until ReDoStack is empty
				while (cmdStack.canRedo()) {
					cmdStack.redo();
				}
				assertShapeCoordinates(diagramTypeProvider, "Shape", 0, 0);
			}
		});
		page.shutdownEditor(diagramEditor);
	}

	/**
	 * @see org.eclipse.graphiti.ui.tests.RollbackTest
	 */
	@Test
	public void testRollback() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final IFeatureProvider fp = diagramEditor.getDiagramTypeProvider().getFeatureProvider();
		final Diagram diagram = fp.getDiagramTypeProvider().getDiagram();
		executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
			public void run() {
				addClassToDiagram(fp, diagram, 500, 500, "Shape");
				// enforce roll-back
				throw new OperationCanceledException();

			}
		});

		assertEquals("Rollback of creation of shape failed", 0, diagram.getChildren().size());
		page.shutdownEditor(diagramEditor);
	}

	@Test
	public void testAbstractDrillDownFeature() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		Object rootModelObject = diagramEditor.getGraphicalViewer().getContents().getModel();
		assertTrue(rootModelObject instanceof Diagram);
		Diagram diagram = (Diagram) rootModelObject;
		IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
		IFeatureProvider featureProvider = diagramTypeProvider.getFeatureProvider();
		syncExec(new VoidResult() {
			public void run() {
				IDiagramTypeProvider dtp = diagramEditor.getDiagramTypeProvider();
				IFeatureProvider fp = dtp.getFeatureProvider();

				int x = 50;
				CommandStack commandStack = diagramEditor.getEditDomain().getCommandStack();
				ICreateFeature[] createFeatures = fp.getCreateFeatures();
				for (ICreateFeature createFeature : createFeatures) {
					Rectangle rectangle = new Rectangle(x, 50, 100, 100);
					ICreateContext createContext = createCreateContext(dtp.getDiagram(), rectangle);
					Command createCommand = new CreateModelObjectCommand(getConfigProviderMock(dtp, diagramEditor),
							createFeature, createContext);
					commandStack.execute(createCommand);
					x += 150;
				}
			}
		});

		// test constructor
		AbstractDrillDownFeature drillDownFeature = new MyAbstractDrillDownFeature(featureProvider);

		// test AbstractDrillDownFeature.getName()
		String name = drillDownFeature.getName();
		assertNotNull(name);
		assertTrue(!"".equals(name));

		// test AbstractDrillDownFeature.canExecute()
		List<Shape> shapes = diagram.getChildren();
		Shape shape = shapes.iterator().next();
		assertNotNull("Diagram does not contain any shapes!", shape);

		ICustomContext customContextMock = createNiceMock(ICustomContext.class);
		expect(customContextMock.getPictogramElements()).andReturn(new PictogramElement[] { diagram }).once();
		expect(customContextMock.getPictogramElements()).andReturn(new PictogramElement[] { shape }).anyTimes();
		replay(customContextMock);

		drillDownFeature.canExecute((IContext) customContextMock);
		drillDownFeature.canExecute(customContextMock);
		verify(customContextMock);

		// test AbstractDrillDownFeature.execute()
		drillDownFeature.execute((IContext) customContextMock);
		drillDownFeature.execute(customContextMock);

		ed.closeEditor();
	}

	private class MyAbstractDrillDownFeature extends AbstractDrillDownFeature {
		MyAbstractDrillDownFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		protected Collection<Diagram> getDiagrams() {
			final Collection<Diagram> ret = new HashSet<Diagram>();
			for (final TreeIterator<?> i = getDiagramBehavior().getEditingDomain().getResourceSet().getAllContents(); i
					.hasNext();) {
				final Object child = i.next();
				if (child instanceof Diagram) {
					ret.add((Diagram) child);
				}
			}
			return ret;
		}

	}

	@Test
	public void testThumbnailView() throws Exception {
		SWTBotView thumbnail = bot.viewById("org.eclipse.graphiti.ui.internal.editor.thumbnailview");
		thumbnail.show();
		assertTrue(thumbnail.isActive());
	}

	@Test
	public void testPlatformUtil() throws Exception {
		syncExec(new VoidResult() {
			public void run() {
				final Diagram newDiagram = createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
				assertTrue("create diagram does not work", newDiagram != null);
				GraphitiUiInternal.getWorkbenchService().openDiagramEditor(newDiagram);
			}
		});
		ed.closeEditor();
	}

	@Test
	public void testOnSketchDiagram() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);

		syncExec(new VoidResult() {
			public void run() {
				IDiagramTypeProvider dtp = diagramEditor.getDiagramTypeProvider();
				IFeatureProvider fp = dtp.getFeatureProvider();

				// test debug feature
				Diagram diagram = dtp.getDiagram();
				int debugTypes[] = new int[] { DebugFeature.TYPE_DUMP_ALL, DebugFeature.TYPE_DUMP_EDIT_PART_DATA,
						DebugFeature.TYPE_DUMP_FIGURE_DATA, DebugFeature.TYPE_DUMP_PICTOGRAM_DATA, DebugFeature.TYPE_REFRESH };
				ICustomContext customContext = new CustomContext(new PictogramElement[] { diagram });
				for (int i = 0; i < debugTypes.length; i++) {
					DebugFeature debugFeature = new DebugFeature(fp, debugTypes[i]);
					debugFeature.getName();
					if (debugFeature.canExecute(customContext)) {
						debugFeature.execute(customContext);
					}
				}

				int x = 50;
				CommandStack commandStack = diagramEditor.getEditDomain().getCommandStack();
				ICreateFeature[] createFeatures = fp.getCreateFeatures();
				for (ICreateFeature createFeature : createFeatures) {
					Rectangle rectangle = new Rectangle(x, 50, 100, 100);
					ICreateContext createContext = createCreateContext(dtp.getDiagram(), rectangle);
					Command createCommand = new CreateModelObjectCommand(getConfigProviderMock(dtp, diagramEditor),
							createFeature, createContext);
					commandStack.execute(createCommand);
					x -= 150;
				}

				GraphicalViewer viewer = diagramEditor.getGraphicalViewer();

				GraphitiUiInternal.getGefService().findEditPartAt(viewer, new Point(75, 75), true);

				EditPart rootEditPart = viewer.getRootEditPart();
				if (rootEditPart instanceof ScalableFreeformRootEditPart) {
					ZoomManager zoomManager = ((ScalableFreeformRootEditPart) rootEditPart).getZoomManager();
					zoomManager.setZoom(0.5);
					zoomManager.setZoom(2.0);
				}

				EditPart diagramEditPart = GraphitiUiInternal.getGefService().getEditPartChildren(rootEditPart).get(0);

				@SuppressWarnings("unchecked")
				List<EditPart> children = diagramEditPart.getChildren();

				EditPart tmp = null;
				for (EditPart child : children) {
					Object modelElement = child.getModel();

					// test GEFUtil
					GraphitiUiInternal.getGefService().getLayoutConstraint(child);
					GraphitiUiInternal.getGefService().selectEditPart(viewer, modelElement);
					GraphitiUiInternal.getGefService().selectEditPart(viewer, null);
					assertTrue((new Point(0, 0)).equals(GraphitiUiInternal.getGefService().calculateTranslation(child, child)));
					if (tmp != null) {
						GraphitiUiInternal.getGefService().calculateTranslation(child, tmp);
					}
					tmp = child;
					// end of test GEFUtil
				}

				GraphitiUiInternal.getTraceService().dumpEditPartTree(diagramEditPart);
			}
		});
		page.shutdownEditor(diagramEditor);
	}

	@Test
	public void testCommandContainer() throws Exception {
		CommandContainer container = new CommandContainer(null);

		assertTrue(container.canExecute());
		container.getFeatureProvider(); // assertNotNull(container.getFeatureProvider());
		assertNotNull(container.getCommands());
		assertFalse(container.containsCommands());
		assertNotNull(container.getDescription());
		container.add(new ICommand() {
			public boolean canExecute() {
				return false;
			}

			public boolean canUndo() {
				return false;
			}

			public boolean execute() {
				return false;
			}

			public IFeatureProvider getFeatureProvider() {
				return null;
			}

			public boolean undo() {
				return false;
			}

			public String getDescription() {
				return new String("Test Description");
			}
		});
	}

	@Test
	public void testDraw2d() throws Exception {
		Point origin = new Point(0, 0);
		Point term = new Point(7, 7);
		Point test = new Point(2, 3);

		LineSeg lineSeg = new LineSeg(origin, term);
		lineSeg.containsPoint(test, 0);
		lineSeg.distanceAlong(test);
		lineSeg.distanceToPoint(4, 5);
		lineSeg.equals(lineSeg);
		lineSeg.getEquation();
		lineSeg.getInfimum();

		Rectangle ellipseBounds = new Rectangle(origin, term);
		lineSeg.getLineIntersectionsWithEllipse(ellipseBounds);
		PointList pointList = new PointList();
		pointList.addPoint(origin);
		pointList.addPoint(term);
		lineSeg.getLineIntersectionsWithLineSegs(pointList);
		lineSeg.getLinesIntersections(lineSeg);
		lineSeg.getOrigin();
		lineSeg.getParallelLineSegThroughPoint(test);
		lineSeg.getSupremum();
		lineSeg.getTerminus();
		lineSeg.getTrigValues(new Vector(new PrecisionPoint(origin), new PrecisionPoint(test)));
		lineSeg.intersect(lineSeg, 0);
		lineSeg.isHorizontal();
		lineSeg.isVertical();
		lineSeg.length();
		lineSeg.locatePoint(0.5D, 0, Sign.POSITIVE);
		lineSeg.performScale(2D);
		lineSeg.perpIntersect(3, 7);
		lineSeg.perpSlope();
		lineSeg.pointOn(-1L, KeyPoint.ORIGIN, new Point());
		lineSeg.positionRelativeTo(test);
		lineSeg.projection(5, 5);
		lineSeg.setOrigin(origin);
		lineSeg.setTerminus(term);
		lineSeg.slope();
		LineSeg.getLineEquation(0D, 0D, -3.5D, 7.5D);
		lineSeg.performTranslate(10, 10);
	}

	private void removeClassShape(IFeatureProvider fp, Diagram diagram, String className) {

		Shape shape = findShapeForEClass(diagram, className);
		RemoveContext removeContext = new RemoveContext(shape);
		// remove the shape
		IRemoveFeature removeFeature = fp.getRemoveFeature(removeContext);
		assertNotNull("remove feature not available", removeFeature);
		if (removeFeature.canRemove(removeContext)) {
			try {
				CommandExec.executeFeatureWithContext(removeFeature, removeContext);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
	}

	private void assertShapeCoordinates(IDiagramTypeProvider diagramTypeProvider, String className, int x, int y) {
		Shape shape = findShapeForEClass(diagramTypeProvider.getDiagram(), className);
		assertEquals("Undo/Redo failed: wrong X coordinate for " + className, x, shape.getGraphicsAlgorithm().getX());
		assertEquals("Undo/Redo failed: wrong Y coordinate " + className, y, shape.getGraphicsAlgorithm().getY());
	}

	@Test
	public void testGaUtil() throws Exception {
		final Diagram diagram = createDiagram("test_gautil");
		executeInRecordingCommand(ed.getTransactionalEditingDomain(), new Runnable() {
			public void run() {
				ContainerShape cs1 = getPeService().createContainerShape(diagram, true);
				Shape shape1 = getPeService().createShape(cs1, true);
				IGaService gaService = Graphiti.getGaService();
				MultiText multiText1 = gaService.createMultiText(shape1);
				MultiText multiText2 = gaService.createMultiText(shape1, "GaUtil");
				gaService.createText(shape1);
				gaService.createText(shape1, "GaUtil");
				gaService.resetAll(multiText1);
				gaService.findStyle(diagram, "s");
				gaService.createStyle(diagram, "s");
				gaService.findStyle(diagram, "s");
				gaService.createShiftedColor(IColorConstant.BLUE, 5);
				gaService.deleteFont(multiText2.getFont());
			}
		});
	}

	@Test
	public void testUtils() throws Exception {
		TransactionalEditingDomain editingDomain = GraphitiUiInternal.getEmfService().createResourceSetAndEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		URI diagramFileUri = URI.createPlatformPluginURI(
				"/org.eclipse.graphiti.bot.tests/src/org/eclipse/graphiti/bot/tests/testUtil.diagram", true);
		Resource diagramResource = resourceSet.getResource(diagramFileUri, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0");
		final EClass c1 = (EClass) diagramResource.getEObject("/1");
		final EClass c2 = (EClass) diagramResource.getEObject("/3");
		final EClass c3 = (EClass) diagramResource.getEObject("/4");
		final EClass c4 = (EClass) diagramResource.getEObject("/5");

		executeInRecordingCommand(editingDomain, new Runnable() {
			public void run() {
				ContainerShape cs1 = getPeService().createContainerShape(diagram, true);
				ContainerShape cs2 = getPeService().createContainerShape(diagram, true);
				Shape shape1 = getPeService().createShape(cs1, true);
				Anchor anchor1 = getPeService().createFixPointAnchor(cs1);
				Anchor anchor2 = getPeService().createFixPointAnchor(cs2);
				FreeFormConnection freeFormConnection = getPeService().createFreeFormConnection(diagram);
				ConnectionDecorator connectionDecorator = getPeService().createConnectionDecorator(freeFormConnection, true, 0.5, true);

				EObject[] ro = new EObject[] { c1, c2, c3, c4 };
				Object[] linkedPictogramElements = getPeService().getLinkedPictogramElements(ro, diagram);
				assertEquals("Number of linked elements mismatch: ", 4, linkedPictogramElements.length);
				EObject[] elementsNotInDiagram = getPeService().getElementsNotInDiagram(ro, diagram);
				assertEquals("Number of elements not in diagram mismatch: ", 2, elementsNotInDiagram.length);
				getPeService().getPictogramElementChildren(cs1);
				getPeService().getPictogramElementParent(freeFormConnection);
				getPeService().getPictogramElementParent(anchor1);
				getPeService().getPictogramElementParent(connectionDecorator);
				getPeService().getDiagramForPictogramElement(cs1);
				getPeService().getDiagramForPictogramElement(diagram);
				getPeService().getDiagramForPictogramElement(anchor2);
				getPeService().getDiagramForPictogramElement(freeFormConnection);
				getPeService().getDiagramForAnchor(anchor1);
				getPeService().getDiagramForShape(cs1);
				getPeService().getAllContainedPictogramElements(diagram);
				getPeService().getChopboxAnchor(diagram);
				getPeService().createFixPointAnchor(diagram);
				getPeService().getConnectionMidpoint(freeFormConnection, 0.5);
				getPeService().getActiveContainerPe(shape1);
				getPeService().removeProperty(cs1, "mc");

				LookManager.getLook();
				LookManager.setDynamicLook(true);
				LookManager.setDynamicLook(false);

				GraphitiUiInternal.getTraceService().dumpPictogramModelTree(diagram);
				org.eclipse.graphiti.mm.algorithms.Rectangle rectangle = Graphiti.getGaCreateService().createRectangle(diagram);
				Graphiti.getGaCreateService().createDefaultText(diagram, rectangle);
				GraphitiUiInternal.getTraceService().dumpGATree(rectangle);
				Ellipse ellipse = new Ellipse();
				ellipse.add(new Ellipse());
				ellipse.add(new Polygon());
				GraphitiUiInternal.getTraceService().dumpFigureTree(ellipse);
			}
		});
	}

	@Test
	public void testUtils2() throws Exception {
		final Diagram diagram = createDiagram("test_peutil");
		executeInRecordingCommand(ed.getTransactionalEditingDomain(), new Runnable() {
			public void run() {
				// Build Pictograms model
				IGaService gaService = Graphiti.getGaService();
				ContainerShape containerShape1 = getPeService().createContainerShape(diagram, true);
				{
					RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape1, 5, 5);
					roundedRectangle.setPictogramElement(containerShape1);
					gaService.setLocationAndSize(roundedRectangle, 0, 0, 5, 5);
				}
				ContainerShape containerShape2 = getPeService().createContainerShape(diagram, true);
				{
					RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape2, 5, 5);
					roundedRectangle.setPictogramElement(containerShape2);
					gaService.setLocationAndSize(roundedRectangle, 20, 20, 5, 5);
				}
				Shape innerShape1 = getPeService().createShape(containerShape1, true);
				containerShape1.getChildren().add(innerShape1);
				Anchor anchor1 = getPeService().createChopboxAnchor(containerShape1);
				Anchor anchor2 = getPeService().createChopboxAnchor(containerShape2);
				getPeService().createBoxRelativeAnchor(innerShape1);
				containerShape1.getAnchors().add(anchor1);
				containerShape2.getAnchors().add(anchor2);
				FreeFormConnection freeFormConnection = getPeService().createFreeFormConnection(diagram);
				org.eclipse.graphiti.mm.algorithms.styles.Point p = StylesFactory.eINSTANCE.createPoint();
				p.setX(12);
				p.setY(13);
				freeFormConnection.getBendpoints().add(p);
				freeFormConnection.setStart(anchor1);
				freeFormConnection.setEnd(anchor2);

				// Navigate and modify
				List<Connection> allConnections = getPeService().getAllConnections(containerShape1);
				assertFalse(allConnections.isEmpty());
				ILocation connectionMidpoint = getPeService().getConnectionMidpoint(freeFormConnection, 1);
				assertNotNull(connectionMidpoint);
				IRectangle gaBoundsForAnchor = getPeService().getGaBoundsForAnchor(anchor1);
				assertNotNull(gaBoundsForAnchor);
				List<Connection> incomingConnections = getPeService().getIncomingConnections(containerShape2);
				assertTrue(incomingConnections.size() == 1);
				List<Connection> outgoingConnections = getPeService().getOutgoingConnections(containerShape1);
				assertTrue(outgoingConnections.size() == 1);
				DefaultExecutionInfo executionInfo = new DefaultExecutionInfo();
				MoveShapeContext moveShapeContext = new MoveShapeContext(containerShape1);
				moveShapeContext.setDeltaX(1);
				moveShapeContext.setDeltaY(1);
				DefaultFeatureAndContext fac = new DefaultFeatureAndContext(null, moveShapeContext);
				MoveShapeContext moveShapeContext2 = new MoveShapeContext(containerShape2);
				moveShapeContext.setDeltaX(1);
				moveShapeContext.setDeltaY(1);
				DefaultFeatureAndContext fac2 = new DefaultFeatureAndContext(null, moveShapeContext2);
				executionInfo.addFeatureAndContext(fac);
				executionInfo.addFeatureAndContext(fac2);
				getPeService().moveBendpoints(executionInfo);
				getPeService().sendToBack(innerShape1);
				getPeService().sendToFront(innerShape1);
			}
		});

		// Test DynamicLook
		DynamicLook dynamicLook = new DynamicLook();
		IColorConstant c = dynamicLook.getFieldErrorBackgroundColor();
		assertNotNull(c);
		c = dynamicLook.getGridBackgroundColor();
		assertNotNull(c);
		int t = dynamicLook.getGridLineThickness();
		assertNotNull(t);
		c = dynamicLook.getMajorGridLineColor();
		assertNotNull(c);
		t = dynamicLook.getMajorGridLineDistance();
		assertNotNull(t);
		c = dynamicLook.getMinorGridLineColor();
		assertNotNull(c);
		t = dynamicLook.getMinorGridLineDistance();
		assertNotNull(t);

	}

	@Test
	public void testFigureUtil() {
		class RegistryHolder implements IResourceRegistryHolder {
			IResourceRegistry resourceRegistry = new ResourceRegistry();

			public IResourceRegistry getResourceRegistry() {
				return resourceRegistry;
			}

		}
		// IConfigurationProviderInternal xx = new
		Image image = new Image(Display.getCurrent(), 22, 22);
		GC gc = new GC(image);
		SWTGraphics graphics = new SWTGraphics(gc);
		double zoom = 1;
		Rectangle rectangle = new Rectangle(1, 2, 3, 4);
		AdaptedGradientColoredAreas agca = PredefinedColoredAreas.getLightYellowAdaptions();
		IResourceRegistryHolder rrh = new RegistryHolder();

		EList<GradientColoredAreas> gradientColoredAreas = agca.getAdaptedGradientColoredAreas();
		EList<GradientColoredArea> gradienColoredAreaList = gradientColoredAreas.get(0).getGradientColor();

		for (Iterator<GradientColoredArea> iterator = gradienColoredAreaList.iterator(); iterator.hasNext();) {
			GradientColoredArea gradientColoredArea = iterator.next();
			GFFigureUtil.paintColorFlow(rrh, rectangle, graphics, gradientColoredArea, zoom, true);
		}

		final Diagram diagram = createDiagram("test_peutil");

		executeInRecordingCommand(ed.getTransactionalEditingDomain(), new Runnable() {
			public void run() {
				ContainerShape cs1 = getPeService().createContainerShape(diagram, true);
				int[] intArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
				Polyline polyline = Graphiti.getGaCreateService().createPolyline(cs1, intArray);
				PointList pointList = GFFigureUtil.getPointList(polyline);
				int[] resultIntArray = pointList.toIntArray();
				for (int i = 0; i < intArray.length; i++) {
					assertEquals(intArray[i], resultIntArray[i]);
				}
			}
		});
	}

	@Test
	public void testPopupMenu() {
		List<Object> content = new ArrayList<Object>();
		content.add("One");
		content.add("Two");
		ILabelProvider labelProvider = new LabelProvider();
		List<Object> subContent = new ArrayList<Object>();
		subContent.add("More1");
		subContent.add("More2");
		subContent.add("More3");
		PopupMenu subMenu = new PopupMenu(subContent, labelProvider);
		content.add(new CascadingMenu("More than two", subMenu));
		content.add("Three");
		final PopupMenu popupMenu = new PopupMenu(content, labelProvider);

		syncExec(new VoidResult() {
			public void run() {
				// Shell activeShell = Display.getCurrent().getActiveShell();
				// popupMenu.show(activeShell);
			}
		});

		assertNull(popupMenu.getResult());
	}

	@Test
	public void testPredefinedColoredAreas() {
		assertNotNull(PredefinedColoredAreas.getBlueWhiteAdaptions());
		assertNotNull(PredefinedColoredAreas.getBlueWhiteGlossAdaptions());
		assertNotNull(PredefinedColoredAreas.getCopperWhiteGlossAdaptions());
		assertNotNull(PredefinedColoredAreas.getLightGrayAdaptions());
		assertNotNull(PredefinedColoredAreas.getLightYellowAdaptions());
		assertNotNull(PredefinedColoredAreas.getSilverWhiteGlossAdaptions());

	}

	@Test
	public void testCreateDiagramInputViaFactory() {
		Diagram d = createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH, "diagram");
		URI uri = EcoreUtil.getURI(d);
		String diagramUri = uri.toString();
		{
			IMemento memento = createNiceMock(IMemento.class);
			replay(memento);
			IAdaptable element = new DiagramEditorInputFactory().createElement(memento);
			assertNull(element);
		}
		{
			IMemento memento = createNiceMock(IMemento.class);
			expect(memento.getString(DiagramEditorInput.KEY_URI)).andStubReturn(diagramUri);
			replay(memento);
			IAdaptable element = new DiagramEditorInputFactory().createElement(memento);
			assertNotNull(element);
		}
		{
			IMemento memento = createNiceMock(IMemento.class);
			expect(memento.getString(DiagramEditorInput.KEY_URI)).andStubReturn(diagramUri);
			expect(memento.getString(DiagramEditorInput.KEY_PROVIDER_ID)).andStubReturn("myProviderId");
			// expect(memento.getString(ModelObjectElementFactory.KEY_PROJECTNAME)).andStubReturn(getProject().getName());
			replay(memento);
			IAdaptable element = new DiagramEditorInputFactory().createElement(memento);
			assertNotNull(element);
			assertTrue(element instanceof DiagramEditorInput);
		}
	}

	@Test
	public void testMoveFileWhileDiagramIsOpen() throws Exception {
		IFile diagFile = createInitialDiagramFile();
		IFolder folder = createTargetFolder(diagFile);
		IFile file = moveFileToFolder(diagFile, folder);

		Thread.sleep(500);
		// Check if editor still there.
		assertTrue(ed.isVisible());

		// No new editor should open.
		PoWorkbenchPage page = new PoWorkbenchPage();
		int count = page.openDiagramEditorFromFile(file);
		assertEquals("No new editor should be opened.", count, 1);

		//clean up.
		page.closeAllEditors();
		file.delete(true, new NullProgressMonitor());
	}

	@Test
	public void testMoveFileWhileDiagramIsOpenAndDirty() throws Exception {
		IFile diagFile = createInitialDiagramFile();

		ed.dirtify();
		assertTrue(ed.isDirty());

		IFolder folder = createTargetFolder(diagFile);
		IFile file = moveFileToFolder(diagFile, folder);

		Thread.sleep(500);
		// Check if editor still there.
		assertTrue(ed.isVisible());

		// No new editor should open.
		PoWorkbenchPage page = new PoWorkbenchPage();
		int count = page.openDiagramEditorFromFile(file);
		assertEquals("No new editor should be opened.", count, 1);

		page.closeAllEditors();
		file.delete(true, new NullProgressMonitor());

	}

	@Test
	public void testRenameFileWhileDiagramIsOpen() throws Exception {
		IFile diagFile = createInitialDiagramFile();

		// Rename
		IPath destination = diagFile.getFullPath().removeLastSegments(1).append("renamed.diagram");
		diagFile.move(destination, true, new NullProgressMonitor());
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource newResource = root.findMember(destination);
		assertTrue(newResource.exists());

		Thread.sleep(500);
		// Check that title did not change
		assertEquals("diagram", ed.getTitle());

		page.closeAllEditors();
		diagFile.delete(true, new NullProgressMonitor());
		newResource.delete(true, new NullProgressMonitor());

	}

	@Test
	public void testRenameFileWhileDiagramIsOpenAndDirty() throws Exception {
		IFile diagFile = createInitialDiagramFile();

		ed.dirtify();
		assertTrue(ed.isDirty());

		// Rename
		IPath destination = diagFile.getFullPath().removeLastSegments(1).append("renamed.diagram");
		diagFile.move(destination, true, new NullProgressMonitor());
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource newResource = root.findMember(destination);
		assertTrue(newResource.exists());

		Thread.sleep(500);

		// Check that title did not change and editor still dirty.
		assertTrue(ed.isDirty());
		assertEquals("diagram", ed.getTitle());

		page.closeAllEditors();
		diagFile.delete(true, new NullProgressMonitor());
		newResource.delete(true, new NullProgressMonitor());

	}

	private IFile moveFileToFolder(IFile diagFile, IFolder folder) throws CoreException {
		IPath destination = folder.getFullPath().append(diagFile.getName());
		diagFile.move(destination, true, new NullProgressMonitor());
		diagFile.getProject().refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
		IFile file = diagFile.getProject().getFolder(folder.getName()).getFile(diagFile.getName());
		assertNotNull(file);
		return file;
	}

	private IFolder createTargetFolder(IFile diagFile) throws CoreException {
		final String folderName = "testmove";
		IFolder folder = diagFile.getProject().getFolder(folderName);
		if (!folder.exists())
			folder.create(true, true, new NullProgressMonitor());
		assertTrue(folder.exists());
		return folder;
	}

	private IFile createInitialDiagramFile() throws Exception {
		IFile diagFile = createPersistentDiagram();
		assertTrue(diagFile.exists());
		int editorCount = new PoWorkbenchPage().openDiagramEditorFromFile(diagFile);
		assertEquals("One editor must have opened for " + diagFile, 1, editorCount);
		return diagFile;
	}

	@Test
	public void testOpenDiagramFromFile() throws Exception {
		int editorCount = 0;
		page.closeAllEditors();

		IFile diagFile1 = createPersistentDiagram();
		IFile diagFile2 = createPersistentDiagram();

		// open editor on first diagram file
		PoWorkbenchPage wp = new PoWorkbenchPage();
		editorCount = wp.openDiagramEditorFromFile(diagFile1);
		assertEquals("One editor must have opened for " + diagFile1, 1, editorCount);

		// open same file again
		editorCount = wp.openDiagramEditorFromFile(diagFile1);
		assertEquals("No new editor must have opened for " + diagFile1, 1, editorCount);

		// open editor on second file
		editorCount = wp.openDiagramEditorFromFile(diagFile2);
		assertEquals("One editor must have opened for " + diagFile2, 2, editorCount);

		// open first file again
		editorCount = wp.openDiagramEditorFromFile(diagFile1);
		assertEquals("No new editor must have opened for " + diagFile1, 2, editorCount);
	}

	@Test
	public void testOpenDiagramFromObject() throws Exception {
		int editorCount = 0;
		page.closeAllEditors();

		Diagram[] diagram = new Diagram[1];
		createPersistentDiagram(diagram);
		Diagram diagram1 = diagram[0];
		createPersistentDiagram(diagram);
		Diagram diagram2 = diagram[0];

		// open editor on first diagram
		PoWorkbenchPage wp = new PoWorkbenchPage();
		editorCount = wp.openDiagramEditorFromObject(diagram1);
		assertEquals("One editor must have opened for " + diagram1, 1, editorCount);

		// open same diagram again
		editorCount = wp.openDiagramEditorFromObject(diagram1);
		assertEquals("No new editor must have opened for " + diagram1, 1, editorCount);

		// open editor on second diagram
		editorCount = wp.openDiagramEditorFromObject(diagram2);
		assertEquals("One editor must have opened for " + diagram2, 2, editorCount);

		// open first diagram again
		editorCount = wp.openDiagramEditorFromObject(diagram1);
		assertEquals("No new editor must have opened for " + diagram1, 2, editorCount);
	}

	@Test
	public void testDiagramEditorTitle() throws Exception {
		// Test for Bugzilla 356828
		// Create a diagram with a space in the object name
		Diagram[] diagram = new Diagram[1];
		createPersistentDiagram(diagram, "Diagram Object");

		// Open editor
		PoWorkbenchPage wp = new PoWorkbenchPage();
		wp.openDiagramEditorFromObject(diagram[0]);

		// The title of the editor shall be the name of the diagram
		assertEquals("Diagram Object", wp.getGefEditor().getTitle());
	}

	@Test
	public void testAutoUpdateAtStartup() throws Exception {
		// Test for Bug 356218 - DefaultUpdateDiagramFeature correctly
		// triggered via editor command stack
		IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_WITH_AUTO_UPDATE_AT_STARTUP);
		Thread.sleep(1000);
		assertFalse(diagramEditor.isDirty());
	}

	@Test
	public void testAddReturnsAddedPictogramElement() throws Exception {
		/*
		 * Test for Bug 367204 - AbstractFeatureProvider's addIfPossible method
		 * should return added {@link PictogramElement}
		 */
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_TUTORIAL);

		syncExec(new VoidResult() {
			public void run() {

				final IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
				final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();

				final Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						EClass eClass = createEClass(diagram, "Class1");

						AddContext context = new AddContext();
						context.setNewObject(eClass);
						context.setTargetContainer(diagram);
						context.setLocation(100, 100);
						PictogramElement addedObject = fp.addIfPossible(context);
						assertNotNull(addedObject);
					}
				});
			}
		});
	}

	/*
	 * Test for Bug 376008 - Iterating through navigation history causes
	 * exceptions
	 */
	@Test
	public void testOpenDiagramEditorWithInvalidUrl() throws Exception {
		IFile diagFile = createInitialDiagramFile();
		diagFile.delete(true, new NullProgressMonitor());

		Thread.sleep(500);

		final Object[] data = new Object[1];
		for (int i = 0; i > -1; i++) {
			final SWTBotToolbarDropDownButton toolbarDropDownButton = bot.toolbarDropDownButton(i);
			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					data[0] = toolbarDropDownButton.widget.getData();
				}
			});

			if (data[0] instanceof ActionContributionItem) {
				ActionContributionItem item = (ActionContributionItem) data[0];
				if (item.getId().equals(ActionFactory.BACKWARD_HISTORY.getId())) { // Found!
					toolbarDropDownButton.click();
					break;
				}
			}
		}

		SWTBotEditor editor = bot.editorById(DiagramEditor.DIAGRAM_EDITOR_ID);
		assertNotNull(editor);
		editor.show();
		SWTBotStyledText styledText = editor.bot().styledText();
		assertNotNull(styledText);
		assertTrue(styledText.getText().startsWith("No Diagram found for URI"));

		// clean up.
		page.closeAllEditors();
	}

	/*
	 * Test for Bug 378342 - Cannot store more than a diagram per file
	 */
	@Test
	public void testOpenDiagramEditorWithTwoDiagramsInResource() throws Exception {
		String fileName = createDiagramFileName("xmi");
		URI diagramUri = createDiagramFileUri(fileName);
		// Create a resource set and EditingDomain
		final TransactionalEditingDomain editingDomain = GraphitiUiInternal.getEmfService()
				.createResourceSetAndEditingDomain();
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		// Create a resource for this file.
		final Resource resource = resourceSet.createResource(diagramUri);
		final org.eclipse.emf.common.command.CommandStack commandStack = editingDomain.getCommandStack();

		// Create 2 diagrams
		final Diagram diagram1 = createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH, "diagram", "Diagram1");
		final Diagram diagram2 = getPeService().createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH, "Diagram2", true);

		commandStack.execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				resource.setTrackingModification(true);
				resource.getContents().add(diagram1);
				resource.getContents().add(diagram2);
			}
		});

		// Save and dispose
		resource.save(Collections.<Resource, Map<?, ?>> emptyMap());
		final URI resourceUri = resource.getURI();
		final URI diagramUri1 = EcoreUtil.getURI(diagram1);
		final URI diagramUri2 = EcoreUtil.getURI(diagram2);
		editingDomain.dispose();

		syncExec(new VoidResult() {
			public void run() {
				try {

					// Open editor for diagram 1
					{
						DiagramEditorInput input1 = new DiagramEditorInput(diagramUri1,
								"org.eclipse.graphiti.testtool.sketch.SketchDiagramTypeProvider");
						IDiagramContainerUI editor1;
						editor1 = (IDiagramContainerUI) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.openEditor(input1, DiagramEditor.DIAGRAM_EDITOR_ID);
						assertEquals("Diagram1", editor1.getDiagramTypeProvider().getDiagram().getName());
					}

					// Open editor for diagram 2
					{
						DiagramEditorInput input2 = new DiagramEditorInput(diagramUri2,
								"org.eclipse.graphiti.testtool.sketch.SketchDiagramTypeProvider");
						IDiagramContainerUI editor2 = (IDiagramContainerUI) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().openEditor(input2, DiagramEditor.DIAGRAM_EDITOR_ID);
						assertEquals("Diagram2", editor2.getDiagramTypeProvider().getDiagram().getName());
					}

					page.closeAllEditors();

					IDiagramContainerUI editor3;
					{
						// Open editor for default diagram in file
						DiagramEditorInput input3 = new DiagramEditorInput(resourceUri,
								"org.eclipse.graphiti.testtool.sketch.SketchDiagramTypeProvider");
						editor3 = (IDiagramContainerUI) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.openEditor(input3, DiagramEditor.DIAGRAM_EDITOR_ID);
						assertEquals("Diagram1", editor3.getDiagramTypeProvider().getDiagram().getName());
					}

					// Again open editor for diagram 1 (editor 3 must be reused)
					{
						DiagramEditorInput input4 = new DiagramEditorInput(diagramUri1,
								"org.eclipse.graphiti.testtool.sketch.SketchDiagramTypeProvider");
						IDiagramContainerUI editor4 = (IDiagramContainerUI) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().openEditor(input4, DiagramEditor.DIAGRAM_EDITOR_ID);
						assertEquals("Diagram1", editor4.getDiagramTypeProvider().getDiagram().getName());
						assertEquals(editor3, editor4);
					}
				} catch (PartInitException e) {
					fail(e.getMessage());
				}
			}
		});
	}

	/*
	 * Test for Bug 391046. Editor save would block if called from within a
	 * scheduling rule, e.g. during Eclipse refactoring operation. Solution is
	 * to pass the outer scheduling rule to the model context job doing the
	 * save.
	 */
	@Test
	public void testSaveTransfersSchedulingRule() throws Exception {
		page.closeAllEditors();
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		final IDiagramTypeProvider dtp = diagramEditor.getDiagramTypeProvider();
		final IFeatureProvider fp = ((DefaultFeatureProviderWrapper) dtp.getFeatureProvider())
				.getInnerFeatureProvider();
		final CommandStack commandStack = diagramEditor.getEditDomain().getCommandStack();

		// Create something on the diagram to make it dirty
		syncExec(new VoidResult() {
			public void run() {
				ICreateFeature createFeature = new SketchCreateGaContainerFeature(fp, "Rounded Rectangle Container",
						"draw rounded rectangle", RoundedRectangle.class);
				Rectangle rectangle = new Rectangle(50, 50, 100, 50);
				ICreateContext createContext = createCreateContext(dtp.getDiagram(), rectangle);
				Command createCommand = new CreateModelObjectCommand(getConfigProviderMock(dtp, diagramEditor),
						createFeature, createContext);
				commandStack.execute(createCommand);
				ContainerShape shape1 = (ContainerShape) dtp.getDiagram().getChildren().get(0);
				assertNotNull(shape1);

			}
		});

		// Call the editor save from within a scheduling rule
		syncExec(new VoidResult() {
			@Override
			public void run() {
				Job.getJobManager().beginRule(ResourcesPlugin.getWorkspace().getRoot(), null);
				diagramEditor.doSave(null);
				Job.getJobManager().endRule(ResourcesPlugin.getWorkspace().getRoot());
			}
		});

		page.shutdownEditor(diagramEditor);
	}

	/*
	 * Test for Bug 433650 - Editor in in dirty state after a Save
	 */
	@Test
	public void testIsDirtyIsFalseAfterDoSaveUndoSave() throws Exception {
		final IDiagramContainerUI diagramEditor = openDiagramEditor(ITestConstants.DIAGRAM_TYPE_ID_ECORE);
		final IDiagramTypeProvider diagramTypeProvider = diagramEditor.getDiagramTypeProvider();
		final IFeatureProvider fp = diagramTypeProvider.getFeatureProvider();
		final Diagram diagram = diagramTypeProvider.getDiagram();
		syncExec(new VoidResult() {
			public void run() {
				executeInRecordingCommand(diagramEditor.getDiagramBehavior(), new Runnable() {
					public void run() {
						addClassToDiagram(fp, diagram, 500, 500, "Shape");
						addClassToDiagram(fp, diagram, 100, 100, "ContainerShape");
						removeClassShape(fp, diagram, "ContainerShape");
						moveClassShape(fp, diagram, 0, 0, "Shape");

					}
				});
			}
		});

		diagramEditor.doSave(new NullProgressMonitor());

		syncExec(new VoidResult() {
			public void run() {

				// get UnDoStack
				TransactionalEditingDomain editingDomain = diagramEditor.getDiagramBehavior().getEditingDomain();
				org.eclipse.emf.common.command.CommandStack cmdStack = editingDomain.getCommandStack();

				// Do "undo"
				cmdStack.undo();
				assertEquals("Undo/Redo failed: Diagram not empty at end of UndoStack", 0, diagram.getChildren().size());
			}
		});

		diagramEditor.doSave(new NullProgressMonitor());
		assertFalse(diagramEditor.isDirty());

		page.shutdownEditor(diagramEditor);
	}

	private IFile createPersistentDiagram() throws Exception {
		return createPersistentDiagram(null);
	}

	/*
	 * return file and diagram
	 */
	private IFile createPersistentDiagram(Diagram[] diagram) throws Exception {
		return createPersistentDiagram(diagram, "diagram");
	}

	/*
	 * return file and diagram
	 */
	private IFile createPersistentDiagram(Diagram[] diagram, String diagramName) throws Exception {
		Diagram d = createDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH, "diagram", diagramName);
		assertNotNull(d);
		if (diagram != null) {
			diagram[0] = d;
		}
		Resource eResource = d.eResource();
		URI eUri = eResource.getURI();

		IFile diagFile = null;
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			diagFile = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}

		assertNotNull(diagFile);
		return diagFile;
	}
}
