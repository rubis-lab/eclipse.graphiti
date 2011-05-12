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
package org.eclipse.graphiti.bot.tests;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.bot.tests.util.ITestConstants;
import org.eclipse.graphiti.examples.common.ExampleProjectNature;
import org.eclipse.graphiti.examples.common.FileService;
import org.eclipse.graphiti.features.ConfigurableFeatureProviderWrapper;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.testtool.sketch.SketchFeatureProvider;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.editor.GFFigureCanvas;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotPerspective;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.junit.Before;
import org.junit.BeforeClass;

abstract class AbstractGFTests extends SWTBotGefTestCase {

	private static final String PROJECT_NAME = "GraphitiTestProject";

	public static final IPath SOURCE_FOLDER = new Path("src"); //$NON-NLS-1$

	private static IPath DIAGRAMS_FOLDER = SOURCE_FOLDER.append("diagrams");

	private class DiagramEditorHolder {
		DiagramEditor diagramEditor;

		public DiagramEditor getDiagramEditor() {
			return diagramEditor;
		}

		public void setDiagramEditor(DiagramEditor diagramEditor2) {
			this.diagramEditor = diagramEditor2;
		}
	}

	protected TransactionalEditingDomain domain;

	private IProject project;

	protected static final String VIEW_REGEXP = "/^[\\*]?test_diagram_.+$/";


	public AbstractGFTests() {
		super();
		setName(getClass().getName());
	}

	@BeforeClass
	public static void beforeClass() {

	}

	protected void addClassToDiagram(IFeatureProvider fp, Diagram diagram, int x, int y, String className) {

		// create add context
		AreaContext areaContext = new AreaContext();
		areaContext.setLocation(x, y);
		EClass newEClass = createEClass(diagram, className);
		AddContext addContext = new AddContext(areaContext, newEClass);
		addContext.setTargetContainer(diagram);

		// get add class feature
		IAddFeature feature = fp.getAddFeature(addContext);
		assertNotNull("add class feature not available", feature);

		if (feature.canAdd(addContext)) {
			feature.execute(addContext);
		}
	}

	protected void addClassesAndReferenceToDiagram(IFeatureProvider fp, Diagram diagram, int sourceX, int sourceY, String sourceClassName,
			int targetX, int targetY, String targetClassName) {
		addClassToDiagram(fp, diagram, sourceX, sourceY, sourceClassName);
		addClassToDiagram(fp, diagram, targetX, targetY, targetClassName);
		addReferenceToDiagram(fp, diagram, sourceClassName, targetClassName);

	}

	protected void addEnumToDiagram(IFeatureProvider fp, Diagram diagram, int x, int y, String enumName) {

		// create add context
		AreaContext areaContext = new AreaContext();
		areaContext.setLocation(x, y);
		EEnum newEEnum = createEEnum(diagram, enumName);
		AddContext addContext = new AddContext(areaContext, newEEnum);
		addContext.setTargetContainer(diagram);

		// get add class feature
		IAddFeature addFeature = fp.getAddFeature(addContext);
		assertNotNull("add class feature not available", addFeature);

		if (addFeature.canAdd(addContext)) {
			addFeature.execute(addContext);
		}
	}

	protected void cleanupEditingDomain() {
		if (domain != null) {
			//			domain.dispose();
			domain = null;
		}
	}

	protected void closeEditor(final DiagramEditor diagramEditor) {

		if (diagramEditor == null) {
			return;
		}

		final IWorkbenchPage workbenchPage = diagramEditor.getEditorSite().getPage();

		if (Display.getCurrent() == null) {

			syncExec(new VoidResult() {
				@Override
				public void run() {
					if (diagramEditor != null) {
						workbenchPage.closeEditor(diagramEditor, false);
					}
				}
			});
		} else {
			workbenchPage.closeEditor(diagramEditor, false);
		}
	}

	protected void closeAllEditors() {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.closeAllEditors(false);
			}
		});
	}

	protected Diagram createDiagram(String diagramTypeId) {
		return createDiagram(diagramTypeId, "xmi");
	}

	/**
	 * Create Diagram and diagram file.
	 * 
	 * @param diagramTypeId
	 * @param fileExtension
	 * @return
	 */
	protected Diagram createDiagram(String diagramTypeId, String fileExtension) {

		String diagramName = createDiagramFileName(fileExtension);
		URI diagramUri = createDiagramFileUri(diagramName);
		final Diagram diagram = getPeService().createDiagram(diagramTypeId, diagramName, true);

		TransactionalEditingDomain editingDomain = FileService.createEmfFileForDiagram(diagramUri, diagram);
		setEditingDomain(editingDomain);
		return diagram;
	}

	private void setEditingDomain(TransactionalEditingDomain newDomain) {
		if (this.domain != null) {
			// TODO: dispose in corect order (recorder was already null in editingdomain -> nullpointer during dispose()
			//			this.domain.dispose();
		}
		this.domain = newDomain;

	}

	protected EEnum createEEnum(Diagram diagram, String enumName) {
		EEnum newEnum = EcoreFactory.eINSTANCE.createEEnum();
		newEnum.setName(enumName);

		diagram.eResource().getContents().add(newEnum);

		return newEnum;
	}

	// private IWorkbenchWindow getActiveOrFirstWorkbenchWindow() {
	// IWorkbenchWindow result =
	// PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	// if (result == null) {
	// IWorkbenchWindow[] windows =
	// PlatformUI.getWorkbench().getWorkbenchWindows();
	// if (windows != null && windows.length > 0) {
	// result = windows[0];
	// }
	// }
	// return result;
	// }

	protected EClass createEClass(Diagram diagram, String className) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(className);

		diagram.eResource().getContents().add(eClass);

		return eClass;
	}

	protected Shape findShapeForEClass(Diagram diagram, String className) {
		Object[] allShapes = getPeService().getAllContainedShapes(diagram).toArray();
		Shape shape = null;
		for (int i = 0; i < allShapes.length; i++) {
			shape = (Shape) allShapes[i];
			if (shape instanceof ContainerShape) {
				Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(shape);
				if (bo instanceof EClass) {
					String boName = ((EClass) bo).getName();
					if (boName.equals(className)) {
						return shape;
					}
				}
			}
		}
		return null;
	}

	protected TransactionalEditingDomain getTransactionalEditingDomain() {
		assertNotNull(this.domain);
		return this.domain;
	}

	protected void moveClassShape(IFeatureProvider fp, Diagram diagram, int x, int y, String className) {

		Shape shape = findShapeForEClass(diagram, className);

		MoveShapeContext moveShapeContext = new MoveShapeContext(shape);

		moveShapeContext.setSourceContainer(shape.getContainer());
		moveShapeContext.setTargetContainer(shape.getContainer());
		moveShapeContext.setLocation(x, y);
		moveShapeContext.setDeltaX(x - shape.getGraphicsAlgorithm().getX());
		moveShapeContext.setDeltaY(y - shape.getGraphicsAlgorithm().getY());

		IMoveShapeFeature moveShapeFeature = fp.getMoveShapeFeature(moveShapeContext);
		assertNotNull("move shape feature not available", moveShapeFeature);

		if (moveShapeFeature.canMoveShape(moveShapeContext)) {
			moveShapeFeature.execute(moveShapeContext);
		}
	}

	protected DiagramEditor openDiagram(final String type) {
		final DiagramEditorHolder deh = new DiagramEditorHolder();
		syncExec(new VoidResult() {
			@Override
			public void run() {

				/**/
				final Diagram newDiagram = createDiagram(type);
				assertTrue("create diagram does not work", newDiagram != null);

				//				assertEditingDomainSave(getTransactionalEditingDomain());

				// use TestUtil to open editor since this waits for late
				// initialization
				DiagramEditor diagramEditor = (DiagramEditor) GraphitiUiInternal.getWorkbenchService().openDiagramEditor(newDiagram,
						getTransactionalEditingDomain(), false);
				deh.setDiagramEditor(diagramEditor);
			}
		});
		DiagramEditor diagramEditor = deh.getDiagramEditor();
		if (ITestConstants.DIAGRAM_TYPE_ID_SKETCH.equals(type)) {
			IFeatureProvider featureProvider = diagramEditor.getDiagramTypeProvider().getFeatureProvider();
			if (featureProvider instanceof ConfigurableFeatureProviderWrapper) {
				ConfigurableFeatureProviderWrapper fpw = (ConfigurableFeatureProviderWrapper) featureProvider;
				IFeatureProvider innerFeatureProvider = fpw.getInnerFeatureProvider();
				if (innerFeatureProvider instanceof SketchFeatureProvider) {
					((SketchFeatureProvider) innerFeatureProvider).setTestMode(true);
				}
			}
		}
		return diagramEditor;
	}

	@Override
	@Before
	protected void setUp() throws Exception {
		//		T.racer().setInfoAlwaysTrue(true); // tracing enabled for testing

		super.setUp();

		// Force activation of current shell to avoid "no widget found"
		// exceptions
		// see http://www.eclipse.org/forums/index.php?t=msg&goto=484090&
		UIThreadRunnable.syncExec(new VoidResult() {
			@Override
			public void run() {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
			}
		});

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(PROJECT_NAME);
		project.create(null);
		project.open(null);
		IProjectDescription desc = project.getDescription();
		desc.setNatureIds(new String[] { ExampleProjectNature.NATURE_ID });
		project.setDescription(desc, null);

		this.project = project;
		try {
			bot.viewByTitle("Welcome").close();
		} catch (WidgetNotFoundException e) {
			// do nothing
		}

		openGraphitiTestPerspective();
	}

	@Override
	protected void tearDown() throws Exception {
		cleanupEditingDomain();
		if (this.project != null) {
			this.project.close(null);
			this.project.delete(true, null);
		}
		super.tearDown();
	}


	private String createDiagramFileName(String extension) {

		List<String> currentDiagramNames = new ArrayList<String>();

		IFolder folder = getProject().getFolder(DIAGRAMS_FOLDER);
		try {
			if (folder.exists()) {
				IResource[] members = folder.members();
				for (IResource resource : members) {
					if (resource instanceof IFile) {
						String fileName = ((IFile) resource).getName();
						if (fileName.startsWith(ITestConstants.TEST_DIAGRAM_PREFIX)) {
							int indexOf = fileName.indexOf('.');
							if (indexOf > 0) {
								fileName = fileName.substring(0, indexOf);
							}
							currentDiagramNames.add(fileName);
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		int i = 0;
		String diagramName;

		do {
			diagramName = ITestConstants.TEST_DIAGRAM_PREFIX + i++;
		} while (currentDiagramNames.contains(diagramName));

		diagramName = diagramName + "." + extension;

		return diagramName;
	}

	private URI createDiagramFileUri(String diagramName) {
		URI uri = null;
		IFolder folder = getProject().getFolder(DIAGRAMS_FOLDER);
		IFile file = folder.getFile(diagramName);
		uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		return uri;
	}

	private IProject getProject() {
		return this.project;
	}

	private void openGraphitiTestPerspective() {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				SWTBotPerspective p = bot.perspectiveById("org.eclipse.graphiti.examples.common.perspective.GFPerspective");
				p.activate();
				bot.activeShell().widget.setMaximized(true);
			}
		});
	}

	private void addReferenceToDiagram(IFeatureProvider fp, Diagram diagram, String sourceClass, String targetClass) {
		ICreateConnectionFeature[] ccfs = fp.getCreateConnectionFeatures();
		if (ccfs != null) {
			Shape sourceShape = findShapeForEClass(diagram, sourceClass);
			Anchor sourceAnchor = getPeService().getChopboxAnchor(sourceShape);
			Shape targetShape = findShapeForEClass(diagram, targetClass);
			Anchor targetAnchor = getPeService().getChopboxAnchor(targetShape);
			CreateConnectionContext ccc = new CreateConnectionContext();
			ccc.setSourceAnchor(sourceAnchor);
			ccc.setTargetAnchor(targetAnchor);
			// question instantiate create feature directly? 
			for (ICreateConnectionFeature ccf : ccfs) {
				if (ccf.canCreate(ccc)) {
					ccf.execute(ccc);
					break;
				}
			}
		}
	}

	protected IPeService getPeService() {
		return Graphiti.getPeService();
	}

	/**
	 * @param diagramEditor
	 */
	protected void shutdownEditor(final DiagramEditor diagramEditor) {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				// Using SWTBot yields an exception since a keyboard layout file for DE is not available.
				//				bot.activeShell().pressShortcut(SWT.NONE, SWT.ESC);
				try {
					Robot r = new Robot();
					r.keyPress(SWT.ESC);
					r.keyRelease(SWT.ESC);
				} catch (AWTException e) {
					e.printStackTrace();
				}
				diagramEditor.doSave(null);
				closeEditor(diagramEditor);
			}
		});
	}

	/**
	 * @return
	 */
	protected SWTBotGefEditor getGefEditor() {
		SWTBotEditor activeEditor = bot.activeEditor();
		String title = activeEditor.getTitle();
		SWTBotGefEditor ed = bot.gefEditor(title);
		return ed;
	}

	/**
	 * @param ed
	 * @return
	 */
	protected GFFigureCanvas getGFCanvas(final SWTBotGefEditor ed) {
		// get instance of GFFigureCanvas
		GFFigureCanvas gfFigureCanvas = getGFCanvas();
		return gfFigureCanvas;
	}


	/**
	 * @param ed
	 * @return
	 */
	protected Point getOrigin(final SWTBotGefEditor ed) {
		Canvas c = getGFCanvas();
		Point p = c.toDisplay(0, 0);
		return p;
	}

	protected GFFigureCanvas getGFCanvas() {
		IEditorReference reference = getGefEditor().getReference();
		final IEditorPart editor = reference.getEditor(true);
		GraphicalViewer graphicalViewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
		final Control control = graphicalViewer.getControl();
		if (control instanceof GFFigureCanvas) {
			GFFigureCanvas c = (GFFigureCanvas) control;
			return c;
		}
		return null;
	}

	protected ICreateContext createCreateContext(ContainerShape target, Rectangle rect) {
		CreateContext ret = new CreateContext();

		ret.setTargetContainer(target);

		ret.setX(rect.x);
		ret.setY(rect.y);
		ret.setWidth(rect.width);
		ret.setHeight(rect.height);

		return ret;
	}
}
