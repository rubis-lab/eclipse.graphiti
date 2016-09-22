/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2016 SRC
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    pjpaulin - Bug 352120 - Initial API, implementation and documentation
 *    mwenz - Bug 394315 - Enable injecting behavior objects in DiagramEditor
 *    mwenz - Bug 401859 - Graphiti DiagramEditor#dispose() does not release the editor related objects
 *    mwenz - Bug 407510 - Color background without Grid Layer turned to gray
 *    fvelasco - Bug 403664 - Enable DoubleClickFeature on the diagram background
 *    mwenz - Bug 433650 - Editor in in dirty state after a Save
 *    mwenz - Bug 439689 - DiagramEdtior.setPictogramElementForSelection adds SelectionBorders to invisible PictogramElements
 *    mwenz - Bug 407894 - Luna: After DiagramsInViews change graphical viewer is configured and initialized only by a workaround
 *    mwenz - Bug 433779 - DiagramBehaviour.setInput() is not extensible
 *    mwenz - Bug 470038 - NullPointerException in DiagramBehavior.unregisterDiagramResourceSetListener
 *    mwenz - Bug 470150 - NullPointerException in DiagramBehavior.getAdapter
 *    mwenz - Bug 477526 - NullPointerException in DiagramBehavior.addGefListeners
 *    mwenz - Bug 480961 - NullPointerException below ScrollingGraphicalViewer.reveal
 *	  edeley - Bug 498164 - Providing a JFace ResourceManager in DiagramBehavior to manage SWT resources
 *    mwenz - Bug 500851 - Probable NullPointerException in DiagramBehavior.disposeAfterGefDispose 
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.gef.ui.actions.MatchWidthAction;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPrintFeature;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.command.AddFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.action.CopyAction;
import org.eclipse.graphiti.ui.internal.action.DeleteAction;
import org.eclipse.graphiti.ui.internal.action.FeatureExecutionHandler;
import org.eclipse.graphiti.ui.internal.action.PasteAction;
import org.eclipse.graphiti.ui.internal.action.PrintGraphicalViewerAction;
import org.eclipse.graphiti.ui.internal.action.RemoveAction;
import org.eclipse.graphiti.ui.internal.action.SaveImageAction;
import org.eclipse.graphiti.ui.internal.action.ToggleContextButtonPadAction;
import org.eclipse.graphiti.ui.internal.action.UpdateAction;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.ConfigurationProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.contextbuttons.ContextButtonManagerForPad;
import org.eclipse.graphiti.ui.internal.contextbuttons.IContextButtonManager;
import org.eclipse.graphiti.ui.internal.dnd.GFTemplateTransferDropTargetListener;
import org.eclipse.graphiti.ui.internal.dnd.ObjectsTransferDropTargetListener;
import org.eclipse.graphiti.ui.internal.editor.DiagramChangeListener;
import org.eclipse.graphiti.ui.internal.editor.DomainModelChangeListener;
import org.eclipse.graphiti.ui.internal.editor.GFCommandStack;
import org.eclipse.graphiti.ui.internal.editor.GFFigureCanvas;
import org.eclipse.graphiti.ui.internal.editor.GFScrollingGraphicalViewer;
import org.eclipse.graphiti.ui.internal.editor.GraphitiScrollingGraphicalViewer;
import org.eclipse.graphiti.ui.internal.util.gef.ScalableRootEditPartAnimated;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Provides the common functionality needed to display and manage diagrams.
 * 
 * Diagrams can be displayed either in a simple SWT {@link Composite}, in a
 * {@link ViewPart} or in an {@link IEditorPart}, so it's not possible to
 * provide common functionality through sub-classing.
 * 
 * @since 0.10
 */
public class DiagramBehavior implements IDiagramBehaviorUI {

	private IDiagramContainerUI diagramContainer;

	private DefaultUpdateBehavior updateBehavior;
	private DefaultPaletteBehavior paletteBehaviour;
	private DefaultPersistencyBehavior persistencyBehavior;
	private DefaultMarkerBehavior markerBehavior;
	private DefaultRefreshBehavior refreshBehavior;

	private PictogramElement pictogramElementsForSelection[];
	private IConfigurationProvider configurationProvider;
	private Point mouseLocation;
	private KeyHandler keyHandler;
	private DiagramScrollingBehavior diagramScrollingBehavior;
	private boolean directEditingActive = false;
	private CommandStackEventListener gefCommandStackListener;

	private DiagramChangeListener diagramChangeListener;
	private DomainModelChangeListener domainModelListener;

	private IDiagramEditorInput diagramEditorInput;

	private String editorInitializationError = null;

	private IWorkbenchPart parentPart;

	private ContextMenuProvider contextMenuProvider = null;

	private ResourceManager resourceManager = null;

	public DiagramBehavior(IDiagramContainerUI diagramContainer) {
		super();
		this.setDiagramContainer(diagramContainer);
	}

	/**
	 * Setter for the associated {@link IDiagramContainerUI container}
	 * displaying the diagram of this behavior. Note that once the container has
	 * been set it must not be changed any more.
	 * 
	 * @param diagramContainer
	 *            the diagramContainer to set
	 * @since 0.12
	 */
	protected void setDiagramContainer(IDiagramContainerUI diagramContainer) {
		if (this.diagramContainer != null) {
			throw new IllegalStateException("diagramContainer must not be changed once it has been set");
		}
		this.diagramContainer = diagramContainer;
	}

	/**
	 * Returns the associated container displaying the diagram of this behavior
	 * object.
	 * 
	 * @return The associated {@link IDiagramContainerUI} instance.
	 */
	public IDiagramContainerUI getDiagramContainer() {
		return diagramContainer;
	}

	/**
	 * Creates the behavior extension that deals with markers. See
	 * {@link DefaultMarkerBehavior} for details and the default implementation.
	 * Override to change the marker behavior.
	 * 
	 * @return a new instance of {@link DefaultMarkerBehavior}
	 */
	protected DefaultMarkerBehavior createMarkerBehavior() {
		return new DefaultMarkerBehavior(this);
	}

	/**
	 * Returns the instance of the marker behavior that is used with this
	 * behavior. To change the behavior override {@link #createMarkerBehavior()}
	 * .
	 * 
	 * @return the used instance of the marker behavior, by default a
	 *         {@link DefaultMarkerBehavior}.
	 */
	protected DefaultMarkerBehavior getMarkerBehavior() {
		return markerBehavior;
	}

	/**
	 * Creates the behavior extension that deals with the update handling. See
	 * {@link DefaultUpdateBehavior} for details and the default implementation.
	 * Override to change the update behavior.
	 * 
	 * @return a new instance of {@link DefaultUpdateBehavior}
	 */
	protected DefaultUpdateBehavior createUpdateBehavior() {
		return new DefaultUpdateBehavior(this);
	}

	/**
	 * Returns the instance of the update behavior that is used with this
	 * behavior. To change the behavior override {@link #createUpdateBehavior()}
	 * .
	 * 
	 * @return the used instance of the marker behavior, by default a
	 *         {@link DefaultUpdateBehavior}.
	 */
	public DefaultUpdateBehavior getUpdateBehavior() {
		return updateBehavior;
	}

	/**
	 * Creates the behavior extension that deals with the palette handling. See
	 * {@link DefaultPaletteBehavior} for details and the default
	 * implementation. Override to change the palette behavior.
	 * 
	 * @return a new instance of {@link DefaultPaletteBehavior}
	 */
	protected DefaultPaletteBehavior createPaletteBehaviour() {
		return new DefaultPaletteBehavior(this);
	}

	/**
	 * Returns the instance of the palette behavior that is used with this
	 * behavior. To change the behavior override
	 * {@link #createPaletteBehaviour()} .
	 * 
	 * @return the used instance of the palette behavior, by default a
	 *         {@link DefaultPaletteBehavior}.
	 */
	protected DefaultPaletteBehavior getPaletteBehavior() {
		return paletteBehaviour;
	}

	/**
	 * Creates the behavior extension that deals with the persistence handling.
	 * See {@link DefaultPersistencyBehavior} for details and the default
	 * implementation. Override to change the persistence behavior.
	 * 
	 * @return a new instance of {@link DefaultPersistencyBehavior}
	 */
	protected DefaultPersistencyBehavior createPersistencyBehavior() {
		return new DefaultPersistencyBehavior(this);
	}

	/**
	 * Returns the instance of the persistency behavior that is used with this
	 * behavior. To change the behavior override
	 * {@link #createPersistencyBehavior()} .
	 * 
	 * @return the used instance of the persistency behavior, by default a
	 *         {@link DefaultPersistencyBehavior}.
	 */
	protected DefaultPersistencyBehavior getPersistencyBehavior() {
		return persistencyBehavior;
	}

	/**
	 * Creates the behavior extension that deals with the refresh handling. See
	 * {@link DefaultRefreshBehavior} for details and the default
	 * implementation. Override to change the refresh behavior.
	 * 
	 * @return a new instance of {@link DefaultRefreshBehavior}
	 */
	protected DefaultRefreshBehavior createRefreshBehavior() {
		return new DefaultRefreshBehavior(this);
	}

	/**
	 * Returns the instance of the refresh behavior that is used with this
	 * behavior. To change the behavior override
	 * {@link #createRefreshBehavior()} .
	 * 
	 * @return the used instance of the refresh behavior, by default a
	 *         {@link DefaultRefreshBehavior}.
	 */
	public DefaultRefreshBehavior getRefreshBehavior() {
		return refreshBehavior;
	}

	// ------------------ Initialization ---------------------------------------

	/**
	 * Hook to initialize the default sub behavior instances used by this editor
	 * behavior. The default implementation simply delegates to the create
	 * methods for the various objects. In case other default behavior
	 * implementation should be used, clients should override these create
	 * methods instead of this method.
	 * 
	 * @see #createMarkerBehavior()
	 * @see #createUpdateBehavior()
	 * @see #createPaletteBehaviour()
	 * @see #createPersistencyBehavior()
	 * @see #createRefreshBehavior()
	 */
	protected void initDefaultBehaviors() {
		// Initialize behavior objects first, they are needed already within the
		// init method. We cannot create these objects in the constructor of
		// diagram editor because that would prevent injecting them, see
		// Bugzilla 394315
		markerBehavior = createMarkerBehavior();
		updateBehavior = createUpdateBehavior();
		paletteBehaviour = createPaletteBehaviour();
		persistencyBehavior = createPersistencyBehavior();
		refreshBehavior = createRefreshBehavior();
	}

	/**
	 * Sets the given {@link IDiagramEditorInput} object as the input for this
	 * behavior instance. The default implementation here cares about loading
	 * the diagram from the EMF {@link Resource} the input points to, sets the
	 * ID of the {@link IDiagramTypeProvider} for the diagram given in the
	 * input, registers listeners (by delegating to
	 * {@link #registerDiagramResourceSetListener()} and
	 * {@link #registerBusinessObjectsListener()}) and does the refreshing of
	 * the UI.
	 * 
	 * @param input
	 *            the {@link DiagramEditorInput} instance to use within this
	 *            behavior.
	 */
	protected void setInput(IDiagramEditorInput input) {
		// Check the input
		if (input == null) {
			throw new IllegalArgumentException("The IEditorInput must not be null"); //$NON-NLS-1$
		}
		setDiagramEditorInput((IDiagramEditorInput) input);
		
		// Retrieve the diagram
		Diagram diagram = getPersistencyBehavior().loadDiagram(getInput().getUri());
		if (diagram == null) {
			// Can happen if editor is started with invalid URI
			setEditorInitializationError("No Diagram found for URI '" + getInput().getUri().toString()); //$NON-NLS-1$
			return;
		}

		IDiagramTypeProvider diagramTypeProvider = initDiagramTypeProvider(diagram);
		initConfigurationProvider(diagramTypeProvider);

		getRefreshBehavior().handleAutoUpdateAtStartup();
		getDiagramContainer().refreshTitle();

		registerBusinessObjectsListener();
		registerDiagramResourceSetListener();
	}

	/**
	 * Creates and initializes a new {@link IDiagramTypeProvider} for the given
	 * {@link Diagram} and diagram type provider ID.
	 * 
	 * @param diagram
	 *            The diagram
	 * @param providerId
	 *            The diagram type provider ID
	 * 
	 * @since 0.12
	 */
	protected IDiagramTypeProvider initDiagramTypeProvider(Diagram diagram) {
		String providerId = getDiagramTypeProviderId(diagram);
		IDiagramTypeProvider diagramTypeProvider = GraphitiUi.getExtensionManager().createDiagramTypeProvider(
				providerId);
		if (diagramTypeProvider == null) {
			setEditorInitializationError("Could not find diagram type provider for " + diagram.getDiagramTypeId()); //$NON-NLS-1$
			return null;
		}
		diagramTypeProvider.init(diagram, this);
		return diagramTypeProvider;
	}

	/**
	 * Gets the diagram type provider ID for the diagram. The default
	 * implementation checks if the input defines one and will use that that,
	 * otherwise it will retrieve the ID of the first diagram type provider that
	 * is registered for the type of the passed diagram.
	 * 
	 * In case no ID is found this method will set the editor initialization
	 * error and throw an {@link AssertionFailedException}.
	 * 
	 * @param diagram
	 *            The diagram to find the ID for.
	 * @since 0.12
	 */
	protected String getDiagramTypeProviderId(Diagram diagram) {
		String providerId = getInput().getProviderId();
		// If provider is null then take the first installed provider for this
		// diagram type
		if (providerId == null) {
			providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
			getInput().setProviderId(providerId);
		}

		if (providerId == null) {
			String message = "DiagramEditorInput does not convey a Provider ID '" + getInput() + ". See the error log for details."; //$NON-NLS-1$ $NON-NLS-1$
			setEditorInitializationError(message);
			Assert.isNotNull(providerId, message);
		}
		return providerId;
	}

	/**
	 * Adds the needed GEF listeners after the edit domain is initialized
	 */
	protected void addGefListeners() {
		getDiagramTypeProvider().postInit();
		gefCommandStackListener = new CommandStackEventListener() {

			public void stackChanged(CommandStackEvent event) {
				// Only fire if triggered from UI thread
				if (Display.getCurrent() != null) {
					IDiagramContainerUI diagramContainer = getDiagramContainer();
					if (diagramContainer != null) {
						diagramContainer.updateDirtyState();

						// Promote the changes to the command stack also to the
						// action bars and registered actions to correctly
						// reflect e.g. undo/redo in the menu (introduced to
						// enable removing NOP commands from the command stack
						diagramContainer.commandStackChanged(event);
					}
				}
			}
		};
		getEditDomain().getCommandStack().addCommandStackEventListener(gefCommandStackListener);
	}

	/**
	 * Creates the GraphicalViewer on the specified {@link Composite} and
	 * initializes it.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	protected void createGraphicalViewer(Composite parent) {
		GraphicalViewer viewer;
		if (getDiagramScrollingBehavior() == DiagramScrollingBehavior.SCROLLBARS_ALWAYS_VISIBLE) {
			viewer = new GFScrollingGraphicalViewer(this);
			((GFScrollingGraphicalViewer) viewer).createGFControl(parent);
		} else {
			viewer = new GraphitiScrollingGraphicalViewer(this);
			viewer.createControl(parent);
		}
		IDiagramContainerUI diagramContainer = getDiagramContainer();
		diagramContainer.setGraphicalViewer(viewer);
		diagramContainer.configureGraphicalViewer();
		diagramContainer.hookGraphicalViewer();
		diagramContainer.initializeGraphicalViewer();
	}

	/**
	 * Called to configure the behavior viewer, before it receives its content.
	 * The default-implementation is for example doing the following: configure
	 * the ZoomManager, registering Actions... Here everything is done, which is
	 * independent of the {@link IConfigurationProvider}.
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	protected void configureGraphicalViewer() {

		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getDiagramContainer().getGraphicalViewer();

		ScalableRootEditPartAnimated rootEditPart = new ScalableRootEditPartAnimated(viewer, getConfigurationProvider()) {

			protected GridLayer createGridLayer() {
				return new org.eclipse.graphiti.ui.internal.util.draw2d.GridLayer(
						(IConfigurationProviderInternal) getConfigurationProvider());
			}

		};

		// configure ZoomManager
		viewer.setRootEditPart(rootEditPart); // support

		// animation of the zoom
		ZoomManager zoomManager = rootEditPart.getZoomManager();
		List<String> zoomLevels = new ArrayList<String>(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		zoomManager.setZoomLevelContributions(zoomLevels);
		IToolBehaviorProvider toolBehaviorProvider = getConfigurationProvider().getDiagramTypeProvider()
				.getCurrentToolBehaviorProvider();
		zoomManager.setZoomLevels(toolBehaviorProvider.getZoomLevels());

		this.initActionRegistry(zoomManager);

		// set the keyhandler.
		viewer.setKeyHandler((new GraphicalViewerKeyHandler(viewer)).setParent(getCommonKeyHandler()));

		// settings for grid and guides
		Diagram diagram = getConfigurationProvider().getDiagram();

		boolean snapToGrid = diagram.isSnapToGrid();
		int horizontalGridUnit = diagram.getGridUnit();
		int verticalGridUnit = diagram.getVerticalGridUnit();
		if (verticalGridUnit == -1) {
			// No vertical grid unit set (or old diagram before 0.8): use
			// vertical grid unit
			verticalGridUnit = horizontalGridUnit;
		}
		boolean gridVisisble = (horizontalGridUnit > 0) && (verticalGridUnit > 0);

		viewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, new Boolean(gridVisisble));
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, new Boolean(snapToGrid));
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(horizontalGridUnit, verticalGridUnit));
		viewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, toolBehaviorProvider.isShowGuides());

		// context button manager
		IConfigurationProviderInternal configurationProvider = (IConfigurationProviderInternal) this
				.getConfigurationProvider();
		configurationProvider.setContextButtonManager(new ContextButtonManagerForPad(this, configurationProvider
				.getResourceRegistry()));

		/* sw: make scroll bars always visible */
		if (getDiagramScrollingBehavior() == DiagramScrollingBehavior.SCROLLBARS_ALWAYS_VISIBLE) {
			GFFigureCanvas figureCanvas = getGFFigureCanvas();
			if (figureCanvas != null) {
				figureCanvas.setScrollBarVisibility(FigureCanvas.ALWAYS);
			}
		}
	}

	/**
	 * Called to initialize the behavior viewer with its content. Here
	 * everything is done, which is dependent of the
	 * {@link IConfigurationProvider}.
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	protected void initializeGraphicalViewer() {

		// register Actions
		IFeatureProvider featureProvider = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
		if (featureProvider != null) {
			IPrintFeature pf = featureProvider.getPrintFeature();
			if (pf != null && parentPart != null) {
				registerAction(new PrintGraphicalViewerAction(this, getConfigurationProvider()));
			}
		}

		// setting ContextMenuProvider
		contextMenuProvider = createContextMenuProvider();
		GraphicalViewer graphicalViewer = getDiagramContainer().getGraphicalViewer();
		if (contextMenuProvider != null) {
			graphicalViewer.setContextMenu(contextMenuProvider);
			// the registration allows an extension of the context-menu by other
			// plugins
			if (shouldRegisterContextMenu() && parentPart != null) {
				parentPart.getSite().registerContextMenu(contextMenuProvider, graphicalViewer);
			}
		}

		// set contents
		graphicalViewer.setEditPartFactory(((IConfigurationProviderInternal) getConfigurationProvider())
				.getEditPartFactory());
		graphicalViewer.setContents(getConfigurationProvider().getDiagram());

		getPaletteBehavior().initializeViewer();

		graphicalViewer.getControl().addMouseMoveListener(new MouseMoveListener() {

			public void mouseMove(MouseEvent e) {
				setMouseLocation(e.x, e.y);
			}
		});

		List<TransferDropTargetListener> objectDropTargetListeners = createBusinessObjectDropTargetListeners();
		for (TransferDropTargetListener dropTargetListener : objectDropTargetListeners) {
			graphicalViewer.addDropTargetListener(dropTargetListener);
		}

		TransferDropTargetListener paletteDropTargetListener = createPaletteDropTargetListener();
		if (paletteDropTargetListener != null) {
			graphicalViewer.addDropTargetListener(paletteDropTargetListener);
		}
	}

	/**
	 * Creates the drop target listener that is used for adding new objects from
	 * the palette via drag and drop. Clients may change the default behavior by
	 * providing their own drop target listener or disable drag and drop from
	 * the palette by returning null.
	 * 
	 * @return An instance of the {@link TransferDropTargetListener} that
	 *         handles dropping new objects from the palette or
	 *         <code>null</code> to disable dropping from the palette.
	 * @since 0.10
	 */
	protected TransferDropTargetListener createPaletteDropTargetListener() {
		return new GFTemplateTransferDropTargetListener(getDiagramContainer().getGraphicalViewer(), this);
	}

	/**
	 * Creates a list of drop target listeners that enable dropping domain
	 * objects into the diagram, e.g. from the project explorer. By adding
	 * additional listeners other sources may be enabled, simply returning an
	 * empty list will disable drag and drop into the editor.
	 * 
	 * @return a {@link List} containing all the
	 *         {@link TransferDropTargetListener} that shall be registered in
	 *         the editor.
	 * @since 0.10
	 */
	protected List<TransferDropTargetListener> createBusinessObjectDropTargetListeners() {
		ArrayList<TransferDropTargetListener> result = new ArrayList<TransferDropTargetListener>(1);
		result.add(new ObjectsTransferDropTargetListener(getDiagramContainer().getGraphicalViewer()));
		return result;
	}

	/**
	 * Returns the error text of the error that occurred while initializing this
	 * behavior and its components. In case this method reports the error text
	 * and error UI may be shown instead of the normal diagram viewer.
	 * 
	 * @return The error text in case an error has occurred, <code>null</code>
	 *         otherwise
	 */
	protected String getEditorInitializationError() {
		return editorInitializationError;
	}

	/**
	 * Sets the error text for an error that occured during the initialization
	 * of this {@link DiagramEditor}. Setting a <code>non-null</code> value
	 * indicates an error has occurred, setting <code>null</code> indicates
	 * everything went fine.
	 * 
	 * @param editorInitializationError
	 *            The error message
	 * @since 0.12
	 */
	protected void setEditorInitializationError(String editorInitializationError) {
		this.editorInitializationError = editorInitializationError;
	}

	/**
	 * Creates the default error page in case an error occurred while
	 * initializing this behavior.
	 * 
	 * @param parent
	 *            The parent {@link Composite} to add the UI to
	 */
	protected void createErrorPartControl(Composite parent) {
		Display display = parent.getDisplay();

		// Define colors as desired, in high contrast mode use system defaults
		Color backgroundColor;
		final Color foregroundColor;
		final Color separatorColor;
		if (display.getHighContrast()) {
			backgroundColor = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
			foregroundColor = display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
			separatorColor = foregroundColor;
		} else {
			backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);
			foregroundColor = display.getSystemColor(SWT.COLOR_DARK_BLUE);
			separatorColor = new Color(display, 152, 170, 203);
		}

		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if (separatorColor != foregroundColor) {
					// Dispose the color only if it was created as additional
					// color
					separatorColor.dispose();
				}
			}
		});

		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(backgroundColor);
		composite.setLayout(new GridLayout());

		Composite separator = new Composite(composite, SWT.NO_FOCUS);
		separator.setBackground(separatorColor);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 2;
		data.verticalIndent = 50;
		separator.setLayoutData(data);

		StyledText widget = new StyledText(composite, SWT.READ_ONLY | SWT.MULTI);
		widget.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widget.setText(getEditorInitializationError());
		widget.setBackground(backgroundColor);
		widget.setForeground(foregroundColor);
		widget.setCaret(null);

		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	// ------------------- Dirty state -----------------------------------------

	/**
	 * Returns the dirty state of this behavior object
	 * 
	 * @return <code>true</code> in case the stored saved command is different
	 *         from the next undo command.
	 */
	protected boolean isDirty() {
		return getPersistencyBehavior().isDirty();
	}

	// ---------------------- Palette --------------------------------------- //

	/**
	 * Delegates to the method (or the method in a subclass of)
	 * {@link DefaultPaletteBehavior#createPaletteViewerProvider()
	 * #createPaletteViewerProvider()} to create the
	 * {@link PaletteViewerProvider} used inside the GEF editor.
	 * 
	 * @return the {@link PaletteViewerProvider} to use
	 */
	protected final PaletteViewerProvider createPaletteViewerProvider() {
		if (getEditorInitializationError() != null) {
			// Editor input is erroneous, show error page instead of diagram and
			// do not initialize the palette to avoid exceptions
			return null;
		}
		return paletteBehaviour.createPaletteViewerProvider();
	}

	/**
	 * Delegates to the method (or the method in a subclass of)
	 * {@link DefaultPaletteBehavior#getPalettePreferences()}. To change the
	 * palette override the behavior there.
	 * 
	 * @return the {@link PaletteViewerProvider} preferences to use.
	 */
	protected final FlyoutPreferences getPalettePreferences() {
		return getPaletteBehavior().getPalettePreferences();
	}

	/**
	 * Returns the {@link PaletteRoot} to use in the GEF editor by delegating to
	 * {@link DefaultPaletteBehavior#getPaletteRoot()}.
	 * 
	 * @return the {@link PaletteRoot} to use
	 */
	protected final PaletteRoot getPaletteRoot() {
		return getPaletteBehavior().getPaletteRoot();
	}

	// ---------------------- Refresh --------------------------------------- //

	/**
	 * Triggers a complete refresh of the behavior visualization (content,
	 * title, tooltip, palette and decorators) by delegating to
	 * {@link DefaultRefreshBehavior#refresh()}.
	 */
	public void refresh() {
		getRefreshBehavior().refresh();

	}

	/**
	 * Refreshes the rendering decorators (image decorators and the like) by
	 * delegating to
	 * {@link DefaultRefreshBehavior#refreshRenderingDecorators(PictogramElement)}
	 * for the given {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} for which the decorators shall be
	 *            refreshed.
	 */
	public void refreshRenderingDecorators(PictogramElement pe) {
		getRefreshBehavior().refreshRenderingDecorators(pe);
	}

	/**
	 * Refreshes the palette to correctly reflect all available creation tools
	 * for the available create features and the currently enabled selection
	 * tools.
	 */
	public void refreshPalette() {
		getPaletteBehavior().refreshPalette();
	}

	/**
	 * Refreshes the content of the editor (what's shown inside the diagram
	 * itself).
	 */
	public void refreshContent() {
		Diagram currentDiagram = getDiagramTypeProvider().getDiagram();
		if (GraphitiInternal.getEmfService().isObjectAlive(currentDiagram)) {
			refresh();
		} else {
			IDiagramEditorInput diagramEditorInput = getDiagramContainer().getDiagramEditorInput();
			// resolve diagram in reloaded resource
			Diagram diagram = getPersistencyBehavior().loadDiagram(diagramEditorInput.getUri());
			IDiagramTypeProvider diagramTypeProvider = getConfigurationProvider().getDiagramTypeProvider();
			diagramTypeProvider.resourceReloaded(diagram);
			// clean performance hashtables which have references
			getRefreshBehavior().initRefresh();
			// to old proxies
			setPictogramElementsForSelection(null);
			// create new edit parts
			getDiagramContainer().getGraphicalViewer().setContents(diagram);
			getRefreshBehavior().handleAutoUpdateAtReset();
		}
	}

	// ---------------------- Selection ------------------------------------- //

	/**
	 * Selects the given {@link PictogramElement}s in the diagram.
	 * 
	 * @param pictogramElements
	 *            an array of {@link PictogramElement}s to select.
	 */
	protected void selectPictogramElements(PictogramElement[] pictogramElements) {
		List<EditPart> editParts = new ArrayList<EditPart>();
		Map<?, ?> editPartRegistry = getDiagramContainer().getGraphicalViewer().getEditPartRegistry();
		if (editPartRegistry != null) {
			for (int i = 0; i < pictogramElements.length; i++) {
				PictogramElement pe = pictogramElements[i];
				/*
				 * Bug 439689 - Check for visible state of PictogramElement as
				 * it might have changed and the edit part state might not have
				 * been updated yet.
				 */
				if (pe != null && pe.isVisible()) {
					Object obj = editPartRegistry.get(pe);
					/*
					 * Add all EditParts to a list to be selected. Bug 324556:
					 * Only add EditParts that allow selection to the list, e.g.
					 * invisible objects will cause an IllegalArgumentException
					 * in AbstractEditPart.setSelected (GEF) when setSelected is
					 * called.
					 */
					if (obj instanceof EditPart && ((EditPart) obj).isSelectable()) {
						editParts.add((EditPart) obj);
					}
				}
			}
			if (parentPart != null) {
				parentPart.getSite().getSelectionProvider().setSelection(new StructuredSelection(editParts));
			}
			if (editParts.size() > 0) {
				final EditPart editpart = editParts.get(0);
				// if the editPart is newly created it is possible that his
				// figure has not a valid bounds. Hence we have to wait for
				// the UI update (for the validation of the figure tree).
				// Otherwise the reveal method can't work correctly.
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						GraphicalViewer graphicalViewer = getDiagramContainer().getGraphicalViewer();
						// Bug 480961 - Prevent NullPointerException in
						// async call (might be no longer available when
						// the async call is executed).
						if (graphicalViewer.getControl() != null) {
							graphicalViewer.reveal(editpart);
						}
					}
				});
			}
		}
	}

	/**
	 * Returns the {@link PictogramElement}s that are currently selected in the
	 * diagram.
	 * 
	 * @return an array of {@link PictogramElement}s.
	 */
	public PictogramElement[] getSelectedPictogramElements() {
		PictogramElement pe[] = new PictogramElement[0];
		ISelectionProvider selectionProvider = null;

		if (parentPart == null) {
			selectionProvider = getDiagramContainer().getGraphicalViewer();
		} else {
			selectionProvider = parentPart.getSite().getSelectionProvider();
		}

		if (selectionProvider != null) {
			ISelection s = selectionProvider.getSelection();
			if (s instanceof IStructuredSelection) {
				IStructuredSelection sel = (IStructuredSelection) s;
				List<PictogramElement> list = new ArrayList<PictogramElement>();
				for (Iterator<?> iter = sel.iterator(); iter.hasNext();) {
					Object o = iter.next();
					if (o instanceof EditPart) {
						EditPart editPart = (EditPart) o;
						if (editPart.getModel() instanceof PictogramElement) {
							list.add((PictogramElement) editPart.getModel());
						}
					}
				}
				pe = list.toArray(new PictogramElement[0]);
			}
		}
		return pe;
	}

	/**
	 * Sets one {@link PictogramElement} for later selection.
	 * <p>
	 * The methods {@link #getPictogramElementsForSelection()},
	 * {@link #setPictogramElementForSelection(PictogramElement)},
	 * {@link #setPictogramElementsForSelection(PictogramElement[])} and
	 * {@link #selectBufferedPictogramElements()} offer the possibility to use a
	 * deferred selection mechanism: via the setters, {@link PictogramElement}s
	 * can be stored for a selection operation that is triggered lateron during
	 * a general refresh via the method
	 * {@link #selectBufferedPictogramElements()}. This mechanism is used e.g.
	 * in the Graphiti framework in direct editing to restore the previous
	 * selection, but can also be used by clients.
	 * 
	 * @param pictogramElement
	 *            the {@link PictogramElement} that shall be stored for later
	 *            selection
	 */
	public void setPictogramElementForSelection(PictogramElement pictogramElement) {
		if (pictogramElement == null) {
			pictogramElementsForSelection = null;
		} else {
			pictogramElementsForSelection = new PictogramElement[] { pictogramElement };
		}
	}

	/**
	 * Sets {@link PictogramElement}s for later selection.
	 * <p>
	 * The methods {@link #getPictogramElementsForSelection()},
	 * {@link #setPictogramElementForSelection(PictogramElement)},
	 * {@link #setPictogramElementsForSelection(PictogramElement[])} and
	 * {@link #selectBufferedPictogramElements()} offer the possibility to use a
	 * deferred selection mechanism: via the setters, {@link PictogramElement}s
	 * can be stored for a selection operation that is triggered later on during
	 * a general refresh via the method
	 * {@link #selectBufferedPictogramElements()}. This mechanism is used e.g.
	 * in the Graphiti framework in direct editing to restore the previous
	 * selection, but can also be used by clients.
	 * 
	 * @param pictogramElements
	 *            the {@link PictogramElement}s that shall be stored for later
	 *            selection
	 */
	protected void setPictogramElementsForSelection(PictogramElement[] pictogramElements) {
		pictogramElementsForSelection = pictogramElements;
	}

	/**
	 * Triggers the selection for the {@link PictogramElement}s that are stored
	 * for later selection. Can be called e.g during a general refresh of the
	 * editor or after another operation needing another selection is finished
	 * (an example in the framework is direct editing).
	 * <p>
	 * The methods {@link #getPictogramElementsForSelection()},
	 * {@link #setPictogramElementForSelection(PictogramElement)},
	 * {@link #setPictogramElementsForSelection(PictogramElement[])} and
	 * {@link #selectBufferedPictogramElements()} offer the possibility to use a
	 * deferred selection mechanism: via the setters, {@link PictogramElement}s
	 * can be stored for a selection operation that is triggered later on during
	 * a general refresh via the method
	 * {@link #selectBufferedPictogramElements()}. This mechanism is used e.g.
	 * in the Graphiti framework in direct editing to restore the previous
	 * selection, but can also be used by clients.
	 */
	public void selectBufferedPictogramElements() {
		if (getPictogramElementsForSelection() != null) {
			selectPictogramElements(getPictogramElementsForSelection());
			setPictogramElementsForSelection(null);
		}
	}

	/**
	 * Returns the {@link PictogramElement}s that are set for later selection.
	 * <p>
	 * The methods {@link #getPictogramElementsForSelection()},
	 * {@link #setPictogramElementForSelection(PictogramElement)},
	 * {@link #setPictogramElementsForSelection(PictogramElement[])} and
	 * {@link #selectBufferedPictogramElements()} offer the possibility to use a
	 * deferred selection mechanism: via the setters, {@link PictogramElement}s
	 * can be stored for a selection operation that is triggered lateron during
	 * a general refresh via the method
	 * {@link #selectBufferedPictogramElements()}. This mechanism is used e.g.
	 * in the Graphiti framework in direct editing to restore the previous
	 * selection, but can also be used by clients.
	 * 
	 * @return the {@link PictogramElement}s stored for later selection
	 */
	protected PictogramElement[] getPictogramElementsForSelection() {
		return pictogramElementsForSelection;
	}

	// ---------------------- Other ----------------------------------------- //

	/**
	 * Returns the EMF {@link TransactionalEditingDomain} used within this
	 * behavior object by delegating to the update behavior extension, by
	 * default {@link DefaultUpdateBehavior#getEditingDomain()}.
	 * 
	 * @return the {@link TransactionalEditingDomain} instance used in the
	 *         behavior
	 */
	public TransactionalEditingDomain getEditingDomain() {
		return getUpdateBehavior().getEditingDomain();
	}

	/**
	 * The EMF {@link ResourceSet} used within this {@link DiagramBehavior}. The
	 * resource set is always associated in a 1:1 relation to the
	 * {@link TransactionalEditingDomain}.
	 * 
	 * @return the resource set used within this behavior object
	 */
	public ResourceSet getResourceSet() {
		ResourceSet ret = null;
		TransactionalEditingDomain editingDomain = getEditingDomain();
		if (editingDomain != null) {
			ret = editingDomain.getResourceSet();
		}
		return ret;
	}

	/**
	 * Returns the {@link IDiagramTypeProvider} instance associated with this
	 * {@link DiagramBehavior}. There is always a 1:1 relation between the
	 * behavior and the provider.
	 * 
	 * @return the associated {@link IDiagramTypeProvider} instance.
	 */
	public IDiagramTypeProvider getDiagramTypeProvider() {
		IConfigurationProvider cfgProvider = getConfigurationProvider();
		if (cfgProvider != null)
			return cfgProvider.getDiagramTypeProvider();
		return null;
	}

	/**
	 * Executes the given {@link IFeature} with the given {@link IContext} in
	 * the scope of this {@link DiagramBehavior}, meaning within its
	 * {@link TransactionalEditingDomain} and on its
	 * {@link org.eclipse.emf.common.command.CommandStack}.
	 * 
	 * @param feature
	 *            the feature to execute
	 * @param context
	 *            the context to use. In case the passed feature is a
	 *            {@link IAddFeature} this context needs to be an instance of
	 *            {@link IAddContext}, otherwise an
	 *            {@link AssertionFailedException} will be thrown.
	 * @return in case of an {@link IAddFeature} being passed as feature the
	 *         newly added {@link PictogramElement} will be returned (in case
	 *         the add method returning it), in all other cases
	 *         <code>null</code>
	 * 
	 * @since 0.9
	 */
	public Object executeFeature(IFeature feature, IContext context) {
		Object returnValue = null;

		DefaultEditDomain domain = getDiagramContainer().getEditDomain();

		// Make sure the editor is valid
		Assert.isNotNull(domain);
		CommandStack commandStack = domain.getCommandStack();

		// Create the correct feature command
		FeatureCommandWithContext featureCommand = null;
		if (feature instanceof IAddFeature) {
			// Context must fit to the feature
			Assert.isTrue(context instanceof IAddContext);
			featureCommand = new AddFeatureCommandWithContext(feature, context);
		} else {
			featureCommand = new GenericFeatureCommandWithContext(feature, context);
		}

		// Execute the feature using the command
		GefCommandWrapper commandWrapper = new GefCommandWrapper(featureCommand, getEditingDomain());
		commandStack.execute(commandWrapper);

		if (featureCommand instanceof AddFeatureCommandWithContext) {
			// In case of an add feature, select the newly added shape
			PictogramElement addedPictogramElement = ((AddFeatureCommandWithContext) featureCommand)
					.getAddedPictogramElements();
			if (addedPictogramElement != null) {
				setPictogramElementForSelection(addedPictogramElement);
			}

			// Store the added pictogram element as return value
			returnValue = addedPictogramElement;
		}

		return returnValue;
	}

	/**
	 * Should be called (e.g. by the various behavior instances) before mass EMF
	 * resource operations are triggered (e.g. saving all resources). Can be
	 * used to disable eventing for performance reasons. See
	 * {@link #enableAdapters()} as well.<br>
	 * Important note: make sure that you re-enable eventing using
	 * {@link #enableAdapters()} after the operation has finished (best in a
	 * finally clause to do that also in case of exceptions), otherwise strange
	 * errors may happen.
	 * 
	 * @since 0.12
	 */
	public void disableAdapters() {
		markerBehavior.disableProblemIndicationUpdate();
		updateBehavior.setAdapterActive(false);
	}

	/**
	 * Should be called by the various behavior instances after mass EMF
	 * resource operations have been triggered (e.g. saving all resources). Can
	 * be used to re-enable eventing after it was disabled for performance
	 * reasons. See {@link #disableAdapters()} as well.<br>
	 * Must be called after {@link #disableAdapters()} has been called and the
	 * operation has finshed (best in a finally clause to also enable the
	 * exception case), otherwise strange errors may occur within the editor.
	 * 
	 * @since 0.12
	 */
	public void enableAdapters() {
		markerBehavior.enableProblemIndicationUpdate();
		updateBehavior.setAdapterActive(true);
	}

	/**
	 * Checks if this behavior is alive.
	 * 
	 * @return <code>true</code>, if editor contains a model connector and a
	 *         valid Diagram, <code>false</code> otherwise.
	 */
	public boolean isAlive() {
		IConfigurationProvider cp = getConfigurationProvider();
		if (cp != null) {
			TransactionalEditingDomain editingDomain = getEditingDomain();
			Diagram diagram = cp.getDiagram();
			if (editingDomain != null && GraphitiInternal.getEmfService().isObjectAlive(diagram)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Hook that is called by the holder of the
	 * {@link TransactionalEditingDomain} ({@link DefaultUpdateBehavior} or a
	 * subclass of it) after the editing domain has been initialized. Can be
	 * used to e.g. register additional listeners on the domain.<br>
	 * The default implementation notifies the marker behavior extension to
	 * register its listeners.
	 */
	protected void editingDomainInitialized() {
		markerBehavior.initialize();
	}

	/**
	 * Implements the Eclipse {@link IAdaptable} interface. This implementation
	 * first delegates to the {@link IToolBehaviorProvider#getAdapter(Class)}
	 * method and checks if something is returned. In case the return value is
	 * <code>null</code> it returns adapters for ZoomManager,
	 * IPropertySheetPage, Diagram, KeyHandler, SelectionSynchronizer and
	 * IContextButtonManager. It also delegates to the super implementation in
	 * {@link GraphicalEditorWithFlyoutPalette#getAdapter(Class)}.
	 * 
	 * @param type
	 *            the type to which shall be adapted
	 * @return the adapter instance
	 */
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		IConfigurationProvider cfgProvider = getConfigurationProvider();

		if (cfgProvider != null) {
			IDiagramTypeProvider diagramTypeProvider = cfgProvider.getDiagramTypeProvider();
			if (diagramTypeProvider != null) {
				IToolBehaviorProvider toolBehaviorProvider = diagramTypeProvider.getCurrentToolBehaviorProvider();
				if (toolBehaviorProvider != null) {
					Object ret = toolBehaviorProvider.getAdapter(type);
					if (ret != null) {
						return ret;
					}
				}
			}
		}

		IDiagramContainerUI diagramContainer = getDiagramContainer();
		GraphicalViewer viewer = diagramContainer.getGraphicalViewer();
		if (type == ZoomManager.class && viewer != null) {
			return viewer.getProperty(ZoomManager.class.toString());
		}

		if (type == IPropertySheetPage.class) {
			if (cfgProvider != null && diagramContainer instanceof ITabbedPropertySheetPageContributor) {
				ITabbedPropertySheetPageContributor contributor = (ITabbedPropertySheetPageContributor) diagramContainer;
				if (contributor.getContributorId() != null) {
					return new TabbedPropertySheetPage(contributor);
				}
			}
			return null; // not yet initialized
		}

		if (type == Diagram.class) {
			IDiagramTypeProvider diagramTypeProvider = getDiagramTypeProvider();
			if (diagramTypeProvider != null) {
				return diagramTypeProvider.getDiagram();
			} else {
				return null;
			}
		}
		if (type == KeyHandler.class) {
			return getCommonKeyHandler();
		}
		if (type == IContextButtonManager.class) {
			return ((IConfigurationProviderInternal) getConfigurationProvider()).getContextButtonManager();
		}

		return null;
	}

	/**
	 * Returns the {@link ConfigurationProvider} for this behavior. It is mainly
	 * a wrapper around various objects that are connected to the diagram
	 * behavior.
	 * 
	 * @return an {@link IConfigurationProvider} instance.
	 * @since 0.12
	 */
	public IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	/**
	 * Returns the contents {@link EditPart} of this behavior. This is the
	 * topmost EditPart in the {@link GraphicalViewer}.
	 * 
	 * @return The contents {@link EditPart} of this behavior.
	 */
	public EditPart getContentEditPart() {
		if (getDiagramContainer().getGraphicalViewer() != null) {
			return getDiagramContainer().getGraphicalViewer().getContents();
		}
		return null;
	}

	/**
	 * Method to retrieve the GEF {@link EditPart} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the GEF
	 *            representation for
	 * @return the GEF {@link GraphicalEditPart} that represents the given
	 *         {@link PictogramElement}.
	 */
	public GraphicalEditPart getEditPartForPictogramElement(PictogramElement pe) {
		Map<?, ?> editPartRegistry = getDiagramContainer().getGraphicalViewer().getEditPartRegistry();
		if (editPartRegistry != null) {
			Object obj = editPartRegistry.get(pe);
			if (obj instanceof GraphicalEditPart) {
				GraphicalEditPart ep = (GraphicalEditPart) obj;
				return ep;
			}
		}
		return null;
	}

	/**
	 * Gets the current mouse location as a {@link Point}.
	 * 
	 * @return the mouse location
	 */
	public Point getMouseLocation() {
		if (mouseLocation == null) {
			mouseLocation = new Point();
		}
		return mouseLocation;
	}

	/**
	 * Calculates the mouse location depending on scrollbars and zoom factor.
	 * 
	 * @param nativeLocation
	 *            the native location given as {@link Point}
	 * @return the {@link Point} of the real mouse location
	 */
	public Point calculateRealMouseLocation(Point nativeLocation) {
		Point ret = new Point(nativeLocation);
		Point viewLocation;
		// view location depends on the current scroll bar position
		if (getDiagramScrollingBehavior() == DiagramScrollingBehavior.SCROLLBARS_ALWAYS_VISIBLE) {
			viewLocation = getGFFigureCanvas().getViewport().getViewLocation();
		} else {
			viewLocation = getFigureCanvas().getViewport().getViewLocation();
		}

		ret.x += viewLocation.x;
		ret.y += viewLocation.y;

		ZoomManager zoomManager = (ZoomManager) getDiagramContainer().getGraphicalViewer()
				.getProperty(ZoomManager.class.toString());
		ret = ret.getScaled(1 / zoomManager.getZoom());

		return ret;
	}

	/**
	 * Returns if direct editing is currently active for this behavior.
	 * 
	 * @return <code>true</code> in case direct editing is currently active
	 *         within this editor, <code>false</code> otherwise.
	 */
	public boolean isDirectEditingActive() {
		return directEditingActive;
	}

	/**
	 * Sets that direct editing is now active in the behavior or not. Note that
	 * this flag set to <code>true</code> does not actually start direct editing
	 * it is simply an indication that prevents certain operations from running
	 * (e.g. refresh)
	 * 
	 * @param directEditingActive
	 *            <code>true</code> to set the flag to direct editing currently
	 *            active, <code>false</code> otherwise.
	 */
	public void setDirectEditingActive(boolean directEditingActive) {
		this.directEditingActive = directEditingActive;
		((IConfigurationProviderInternal) getConfigurationProvider()).getContextButtonManager()
				.hideContextButtonsInstantly();
	}

	/**
	 * Returns the zoom level currently used in the diagram.
	 * 
	 * @return the zoom level
	 */
	public double getZoomLevel() {
		ZoomManager zoomManager = (ZoomManager) getAdapter(ZoomManager.class);
		if (zoomManager == null)
			return 1;

		/*
		 * avoid long running calculations for large diagrams and zoom factors
		 * below 5%
		 */
		return Math.max(0.05D, zoomManager.getZoom());
	}

	/**
	 * Method to retrieve the Draw2D {@link IFigure} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the Draw2D
	 *            representation for
	 * @return the Draw2D {@link IFigure} that represents the given
	 *         {@link PictogramElement}.
	 */
	public IFigure getFigureForPictogramElement(PictogramElement pe) {
		GraphicalEditPart ep = getEditPartForPictogramElement(pe);
		if (ep != null) {
			return ep.getFigure();
		}
		return null;
	}

	/**
	 * Initializes the given {@link IConfigurationProvider} for this
	 * {@link DiagramBehavior} instance and sets it.
	 * 
	 * @param configurationProvider
	 *            The configuration provider
	 * 
	 * @since 0.12
	 */
	protected void initConfigurationProvider(IDiagramTypeProvider diagramTypeProvider) {
		configurationProvider = createConfigurationProvider(diagramTypeProvider);

		// initialize configuration-provider depending on this editor
		configurationProvider.setWorkbenchPart(parentPart);

		if (getDiagramContainer().getGraphicalViewer() != null) {
			initializeGraphicalViewer();
		}

		if (getDiagramContainer() instanceof IEditorPart) {
			DefaultEditDomain editDomain = new DefaultEditDomain((IEditorPart) getDiagramContainer());
			getDiagramContainer().setEditDomain(editDomain);
		}

		CommandStack commandStack = new GFCommandStack(configurationProvider, getEditingDomain());
		getEditDomain().setCommandStack(commandStack);
	}

	/**
	 * Creates a new {@link ConfigurationProvider} for this
	 * {@link DiagramBehavior} and the given {@link IDiagramTypeProvider}. The
	 * default implementation will create a default
	 * {@link ConfigurationProvider} which should suite for all Graphiti
	 * clients.
	 * 
	 * @since 0.12
	 */
	protected IConfigurationProvider createConfigurationProvider(IDiagramTypeProvider diagramTypeProvider) {
		return new ConfigurationProvider(this, diagramTypeProvider);
	}

	private void setMouseLocation(int x, int y) {
		getMouseLocation().setLocation(x, y);
	}

	/**
	 * Returns a new {@link ContextMenuProvider}. Clients can return null, if no
	 * context-menu shall be displayed.
	 * 
	 * @return A new instance of {@link ContextMenuProvider}.
	 */
	protected ContextMenuProvider createContextMenuProvider() {
		return new DiagramEditorContextMenuProvider(getDiagramContainer().getGraphicalViewer(),
				getDiagramContainer().getActionRegistry(),
				getConfigurationProvider());
	}

	/**
	 * Allows subclasses to prevent that the diagram context menu should be
	 * registered for extensions at Eclipse. By default others can provide
	 * extensions to the menu (default return value of this method is
	 * <code>true</code>). By returning <code>false</code> any extension of the
	 * context menu can be prevented.
	 * <p>
	 * For details see Bugzilla 345347
	 * (https://bugs.eclipse.org/bugs/show_bug.cgi?id=345347).
	 * 
	 * @return <code>true</code> in case extensions shall be allowed (default),
	 *         <code>false</code> otherwise.
	 * @since 0.9
	 */
	protected boolean shouldRegisterContextMenu() {
		return true;
	}

	/**
	 * Registers the given action with the Eclipse {@link ActionRegistry}.
	 * 
	 * @param action
	 *            the action to register
	 * @since 0.9
	 */
	protected void registerAction(IAction action) {
		if (action == null || parentPart == null) {
			return;
		}
		IDiagramContainerUI diagramContainer = getDiagramContainer();
		diagramContainer.getActionRegistry().registerAction(action);

		if (action.getActionDefinitionId() != null) {
			IHandlerService hs = (IHandlerService) parentPart.getSite()
					.getService(IHandlerService.class);
			hs.activateHandler(action.getActionDefinitionId(), new ActionHandler(action));
		}

		@SuppressWarnings("unchecked")
		List<String> selectionActions = diagramContainer.getSelectionActions();
		selectionActions.add(action.getId());
	}

	/**
	 * Initializes the action registry with the predefined actions (update,
	 * remove, delete, copy, paste, zooming, direct editing, alignment and
	 * toggling actions for the diagram grip and hiding of the context button
	 * pad.
	 * 
	 * @param zoomManager
	 *            the GEF zoom manager to use
	 */
	protected void initActionRegistry(ZoomManager zoomManager) {
		if (parentPart == null)
		{
			return;
		}
		IDiagramContainerUI diagramContainer = getDiagramContainer();
		final ActionRegistry actionRegistry = diagramContainer.getActionRegistry();
		@SuppressWarnings("unchecked")
		final List<String> selectionActions = diagramContainer.getSelectionActions();

		// register predefined actions (e.g. update, remove, delete, ...)
		IAction action = new UpdateAction(parentPart, getConfigurationProvider());
		actionRegistry.registerAction(action);
		selectionActions.add(action.getId());

		action = new RemoveAction(parentPart, getConfigurationProvider());
		actionRegistry.registerAction(action);
		selectionActions.add(action.getId());

		action = new DeleteAction(parentPart, getConfigurationProvider());
		actionRegistry.registerAction(action);
		selectionActions.add(action.getId());

		action = new CopyAction(parentPart, getConfigurationProvider());
		actionRegistry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PasteAction(parentPart, getConfigurationProvider());
		actionRegistry.registerAction(action);
		selectionActions.add(action.getId());

		IFeatureProvider fp = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
		if (fp != null) {
			ISaveImageFeature sf = fp.getSaveImageFeature();

			if (sf != null) {
				action = new SaveImageAction(this, getConfigurationProvider());
				actionRegistry.registerAction(action);
				selectionActions.add(action.getId());
			}
		}

		registerAction(new ZoomInAction(zoomManager));
		registerAction(new ZoomOutAction(zoomManager));
		registerAction(new DirectEditAction(parentPart));

		registerAction(new AlignmentAction(parentPart, PositionConstants.LEFT));
		registerAction(new AlignmentAction(parentPart, PositionConstants.RIGHT));
		registerAction(new AlignmentAction(parentPart, PositionConstants.TOP));
		registerAction(new AlignmentAction(parentPart, PositionConstants.BOTTOM));
		registerAction(new AlignmentAction(parentPart, PositionConstants.CENTER));
		registerAction(new AlignmentAction(parentPart, PositionConstants.MIDDLE));
		registerAction(new MatchWidthAction(parentPart));
		registerAction(new MatchHeightAction(parentPart));
		IAction showGrid = new ToggleGridAction(diagramContainer.getGraphicalViewer());
		diagramContainer.getActionRegistry().registerAction(showGrid);

		// Bug 323351: Add button to toggle a flag if the context pad buttons
		// shall be shown or not
		IAction toggleContextButtonPad = new ToggleContextButtonPadAction(this);
		toggleContextButtonPad.setChecked(false);
		actionRegistry.registerAction(toggleContextButtonPad);
		// End bug 323351

		IHandlerService hs = (IHandlerService) parentPart.getSite()
				.getService(IHandlerService.class);
		hs.activateHandler(FeatureExecutionHandler.COMMAND_ID, new FeatureExecutionHandler(getConfigurationProvider()));
	}

	/**
	 * Returns the KeyHandler with common bindings to be used for both the
	 * Outline and the Graphical Viewer.
	 * 
	 * @return The KeyHandler with common bindings for both the Outline and the
	 *         Graphical Viewer.
	 * @since 0.9
	 */
	protected KeyHandler getCommonKeyHandler() {
		if (keyHandler == null) {
			keyHandler = new KeyHandler();
			IDiagramContainerUI diagramContainer = getDiagramContainer();
			keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
					diagramContainer.getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, SWT.SHIFT), diagramContainer.getActionRegistry()
					.getAction(RemoveAction.ACTION_ID));
			keyHandler.put(KeyStroke.getPressed(SWT.F2, 0),
					diagramContainer.getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
			keyHandler.put(KeyStroke.getPressed('c', SWT.CTRL),
					diagramContainer.getActionRegistry().getAction(ActionFactory.COPY.getId()));
			keyHandler.put(KeyStroke.getPressed('v', SWT.CTRL),
					diagramContainer.getActionRegistry().getAction(ActionFactory.PASTE.getId()));
			// _keyHandler.put(KeyStroke.getPressed((char) 1, 'a', SWT.CTRL),
			// getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));
		}
		return keyHandler;
	}

	/**
	 * Gets the diagram scrolling behavior.
	 * 
	 * @return the diagram scrolling behavior
	 * @deprecated Scroll bar based infinite canvas is a workaround for GEF
	 *             limitations.
	 * 
	 * @see DefaultToolBehaviorProvider#getDiagramScrollingBehavior()
	 */
	@Deprecated
	private DiagramScrollingBehavior getDiagramScrollingBehavior() {
		if (diagramScrollingBehavior == null) {
			IToolBehaviorProvider tbp = getConfigurationProvider().getDiagramTypeProvider()
					.getCurrentToolBehaviorProvider();
			diagramScrollingBehavior = tbp.getDiagramScrollingBehavior();
		}
		return diagramScrollingBehavior;
	}

	private FigureCanvas getFigureCanvas() {
		GraphicalViewer viewer = getDiagramContainer().getGraphicalViewer();
		if (viewer != null) {
			Control control = viewer.getControl();
			if (control instanceof FigureCanvas) {
				return (FigureCanvas) control;
			}
		}
		return null;
	}

	private GFFigureCanvas getGFFigureCanvas() {
		GraphicalViewer viewer = getDiagramContainer().getGraphicalViewer();
		if (viewer != null) {
			Control control = viewer.getControl();
			if (control instanceof GFFigureCanvas) {
				return (GFFigureCanvas) control;
			}
		}
		return null;
	}

	/**
	 * Hook to unregister the listeners for diagram changes.
	 * 
	 * @see #registerDiagramResourceSetListener()
	 */
	protected void unregisterDiagramResourceSetListener() {
		if (diagramChangeListener != null) {
			diagramChangeListener.stopListening();
			TransactionalEditingDomain editingDomain = getEditingDomain();
			if (editingDomain != null) {
				editingDomain.removeResourceSetListener(diagramChangeListener);
			}
		}
	}

	/**
	 * Hook that is called to unregister the listeners for changes of the
	 * business objects (domain objects).
	 * 
	 * @see DiagramBehavior#registerBusinessObjectsListener()
	 */
	protected void unregisterBusinessObjectsListener() {
		if (domainModelListener != null) {
			TransactionalEditingDomain eDomain = getEditingDomain();
			eDomain.removeResourceSetListener(domainModelListener);
		}
	}

	/**
	 * Hook to register listeners for diagram changes. The listener will be
	 * notified with all events and has to filter for the ones regarding the
	 * diagram.<br>
	 * Note that additional listeners registered here should also be
	 * unregistered in {@link #unregisterDiagramResourceSetListener()}.
	 */
	protected void registerDiagramResourceSetListener() {
		diagramChangeListener = new DiagramChangeListener(this);
		TransactionalEditingDomain eDomain = getEditingDomain();
		eDomain.addResourceSetListener(diagramChangeListener);
	}

	/**
	 * Hook that is called to register listeners for changes of the business
	 * objects (domain objects) in the resource set of the editor. The default
	 * implementation registers the {@link DomainModelChangeListener}.<br>
	 * Note that additional listeners registered here should also be
	 * unregistered in {@link #unregisterBusinessObjectsListener()}.
	 */
	protected void registerBusinessObjectsListener() {
		domainModelListener = new DomainModelChangeListener(this);
		TransactionalEditingDomain eDomain = getEditingDomain();
		eDomain.addResourceSetListener(domainModelListener);
	}

	/**
	 * Returns the {@link DiagramEditorInput} instance used in this behavior.
	 * 
	 * @return An {@link IDiagramEditorInput} instance.
	 */
	protected IDiagramEditorInput getInput() {
		return this.diagramEditorInput;
	}

	/**
	 * Setter for the field storing the editor input.
	 * 
	 * @param diagramEditorInput
	 *            The new input
	 * @since 0.12
	 */
	protected void setDiagramEditorInput(IDiagramEditorInput diagramEditorInput) {
		this.diagramEditorInput = diagramEditorInput;
	}

	/**
	 * The part of the dispose that should happen before the GEF dispose.
	 * Disposes this {@link DiagramBehavior} instance and frees all used
	 * resources and clears all references. Also delegates to all the behavior
	 * extensions to also free their resources (e.g. and most important is the
	 * {@link TransactionalEditingDomain} held by the
	 * {@link DefaultPersistencyBehavior}. Always delegate to
	 * <code>super.dispose()</code> in case you override this method!
	 */
	protected void disposeBeforeGefDispose() {
		unregisterDiagramResourceSetListener();
		unregisterBusinessObjectsListener();

		if (getConfigurationProvider() != null) {
			getConfigurationProvider().dispose();
		}

		if (paletteBehaviour != null) {
			paletteBehaviour.dispose();
		}

		markerBehavior.dispose();

		// unregister selection listener, registered during createPartControl()
		IDiagramContainerUI diagramContainer = getDiagramContainer();
		if (diagramContainer instanceof ISelectionListener) {
			if (diagramContainer.getSite() != null && diagramContainer.getSite().getPage() != null) {
				diagramContainer.getSite().getPage().removeSelectionListener((ISelectionListener) diagramContainer);
			}
		}

		if (getEditDomain() != null && getEditDomain().getCommandStack() != null) {
			getEditDomain().getCommandStack().removeCommandStackEventListener(gefCommandStackListener);
			getEditDomain().getCommandStack().dispose();
		}

		DefaultUpdateBehavior behavior = getUpdateBehavior();
		behavior.dispose();

		if (contextMenuProvider != null) {
			contextMenuProvider.dispose();
			contextMenuProvider = null;
		}
	}

	/**
	 * The part of the dispose that should happen after the GEF dispose. Empties
	 * the command stack of the edit domain. Always delegate to
	 * <code>super.dispose()</code> in case you override this method!
	 */
	protected void disposeAfterGefDispose() {
		if (getEditDomain() != null) {
			getEditDomain().setCommandStack(null);
		}
		if (resourceManager != null) {
			resourceManager.dispose();
		}
	}

	/**
	 * We provide migration from 0.8.0 to 0.9.0. You can override if you want to
	 * migrate manually. WARNING: If your diagram is under version control, this
	 * method can cause a check out dialog to be opened etc.
	 * 
	 * @since 0.9
	 */
	protected void migrateDiagramModelIfNecessary() {
		final Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (Graphiti.getMigrationService().shouldMigrate080To090(diagram)) {
			getEditingDomain().getCommandStack().execute(new RecordingCommand(getEditingDomain()) {
				@Override
				protected void doExecute() {
					Graphiti.getMigrationService().migrate080To090(diagram);
				}
			});
		}
	}

	/**
	 * Delegation method to retrieve the GEF edit domain also here. Simply
	 * delegates to the container.
	 * 
	 * @return The GEF edit domain used used in the container
	 */
	public DefaultEditDomain getEditDomain() {
		return getDiagramContainer().getEditDomain();
	}

	/**
	 * Sets the parent {@link IWorkbenchPart} for this behavior. Can be used to
	 * embed this behavior in various UIs.
	 * 
	 * @param parentPart
	 */
	protected void setParentPart(IWorkbenchPart parentPart) {
		this.parentPart = parentPart;
	}

	/**
	 * Returns the parent {@link IWorkbenchPart} this behavior is embedded into.
	 * May be <code>null</code> in case the behavior is embedded in a non part
	 * UI, like a popup.
	 * 
	 * @return The parent {@link IWorkbenchPart} or <code>null</code> in case it
	 *         does not exist
	 */
	protected IWorkbenchPart getParentPart() {
		return parentPart;
	}
	
	/**
	 * Creates a new instance of a {@link ResourceManager} implementation.
	 * <p>
	 * By default this creates a {@link LocalResourceManager} instance.
	 * Subclasses may override this method to enforce the usage of another 
	 * implementation.
	 * </p>
	 * @return A new instance of a {@link ResourceManager}.
	 *
	 * @since 0.14
	 */
	protected ResourceManager createResourceManager() {
		return new LocalResourceManager(JFaceResources.getResources());
	}

	/**
	 *
	 * @return the JFace {@link ResourceManager} to be used to create images, 
	 *         fonts etc for an open diagram.
	 *
	 * @since 0.14
	 */
	public ResourceManager getResourceManager() {
		synchronized(this) {
			// don't initialize in the constructor as that seems to break mock
			// DiagramBehavior instances in e.g. CustomUndoableFeatureTest.
			if(resourceManager==null) {
				resourceManager = createResourceManager();
			}
		}
		return resourceManager;
	}
}
