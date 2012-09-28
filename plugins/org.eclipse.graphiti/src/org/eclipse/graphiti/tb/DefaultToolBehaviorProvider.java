/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 341224: Allow to hide the selection and marquee tools in the palette
 *    mwenz - Bug 363796 - Make setting of selection width of connections public
 *    mgorning - Bug 363186 - Allow modification of selection and hover state also for anchors
 *    mgorning - Bug 386913 - Support also Single-Click-Features
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 28.06.2005
 */
package org.eclipse.graphiti.tb;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.ISingleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.internal.datatypes.impl.LocationImpl;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * The Class DefaultToolBehaviorProvider.
 */
public class DefaultToolBehaviorProvider implements IToolBehaviorProvider {

	/**
	 * The default selection width (the tolerance area a user can click to still
	 * select) a lathy object (e.g. {@link Polyline} or {@link Polygon} for
	 * {@link Shape}s and {@link Connection}s) in the diagram. Is returned in
	 * the default implementation of {@link #getLineSelectionWidth(Polyline)}.
	 * 
	 * @since 0.9
	 */
	public static final int DEFAULT_LINE_SELECTION_WIDTH = 5;

	protected int THRESHOLD_FOR_LONG_RUNNING_OPERATION = 20;

	private static final IDecorator[] NO_RENDERING_DECORATORS = new IDecorator[0];

	private static double[] ZOOM_LEVELS = { 0.01, 0.1, 0.2, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 10.0 };

	private static final String DIAGRAM_PROPERTY_CONTRIBUTOR_SUFFIX = ".PropertyContributor"; //$NON-NLS-1$

	/**
	 * Identifier for a generic context button, used in
	 * {@link DefaultToolBehaviorProvider#setGenericContextButtons(IContextButtonPadData, PictogramElement, int)}
	 * .
	 */
	protected static int CONTEXT_BUTTON_UPDATE = 1 << 1;

	/**
	 * Identifier for a generic context button, used in
	 * {@link DefaultToolBehaviorProvider#setGenericContextButtons(IContextButtonPadData, PictogramElement, int)}
	 * .
	 */
	protected static int CONTEXT_BUTTON_REMOVE = 1 << 2;

	/**
	 * Identifier for a generic context button, used in
	 * {@link DefaultToolBehaviorProvider#setGenericContextButtons(IContextButtonPadData, PictogramElement, int)}
	 * .
	 */
	protected static int CONTEXT_BUTTON_DELETE = 1 << 3;

	/**
	 * The Constant NO_CONTEXT_MENU_ENTRIES.
	 */
	protected static final IContextMenuEntry[] NO_CONTEXT_MENU_ENTRIES = new IContextMenuEntry[0];

	private IDiagramTypeProvider diagramTypeProvider;

	/**
	 * Creates a new {@link DefaultToolBehaviorProvider}.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public DefaultToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super();
		setDiagramTypeProvider(diagramTypeProvider);
	}

	public void dispose() {
	}

	public Object getAdapter(Class<?> type) {
		return null;
	}

	public PictogramElement getSelection(PictogramElement originalPe, PictogramElement[] oldSelection) {
		return null;
	}

	public ISelectionInfo getSelectionInfoForConnection(Connection connection) {
		ISelectionInfo si = new SelectionInfoImpl(IColorConstant.CONNECTION_SELECTION_FG, IColorConstant.HANDLE_FG,
				IColorConstant.HANDLE_BG, LineStyle.DASH);
		return si;
	}

	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		final String SIGNATURE = "getContextButtonPadData(IPictogramElementContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(DefaultToolBehaviorProvider.class, SIGNATURE, new Object[] { context });
		}

		IContextButtonPadData ret = new DefaultContextButtonPadData();

		PictogramElement pe = context.getPictogramElement();
		GraphicsAlgorithm ga = getSelectionBorder(pe);
		if (ga == null) {
			ga = pe.getGraphicsAlgorithm();
		}

		// set default location
		if (ga != null) {
			ILocation origin = getAbsoluteLocation(ga);
			ret.getPadLocation().setRectangle(origin.getX(), origin.getY(), ga.getWidth(), ga.getHeight());
		}

		// set default generic context buttons
		setGenericContextButtons(ret, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_REMOVE | CONTEXT_BUTTON_UPDATE);

		if (info) {
			T.racer().exiting(DefaultToolBehaviorProvider.class, SIGNATURE, ret);
		}

		return ret;
	}

	/**
	 * Sets the defined generic context buttons to the given
	 * IContextButtonPadData. You should never change the list of generic
	 * context buttons directly but always use this method. This ensures, that
	 * only the 'generic' context buttons are set and that they have the correct
	 * ordering.
	 * 
	 * @param data
	 *            The IContextButtonPadData to which to set the generic context
	 *            buttons.
	 * @param pe
	 *            The pictogram element for which to get the underlying features
	 *            of the context buttons.
	 * @param identifiers
	 *            The context button identifiers (e.g. "
	 *            {@link #CONTEXT_BUTTON_UPDATE} &
	 *            {@link #CONTEXT_BUTTON_REMOVE}")
	 */
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {
		data.getGenericContextButtons().clear();

		// update button
		if ((identifiers & CONTEXT_BUTTON_UPDATE) != 0) {
			IContextButtonEntry updateButton = ContextEntryHelper.createDefaultUpdateContextButton(
					getFeatureProvider(), pe);
			if (updateButton != null) {
				data.getGenericContextButtons().add(updateButton);
			}
		}

		// remove button
		if ((identifiers & CONTEXT_BUTTON_REMOVE) != 0) {
			IContextButtonEntry removeButton = ContextEntryHelper.createDefaultRemoveContextButton(
					getFeatureProvider(), pe);
			if (removeButton != null) {
				data.getGenericContextButtons().add(removeButton);
			}
		}

		// delete button
		if ((identifiers & CONTEXT_BUTTON_DELETE) != 0) {
			IContextButtonEntry deleteButton = ContextEntryHelper.createDefaultDeleteContextButton(
					getFeatureProvider(), pe);
			if (deleteButton != null) {
				data.getGenericContextButtons().add(deleteButton);
			}
		}
	}

	/**
	 * Returns the location of the graphics algorithm associated to the
	 * pictogram element in absolute coordinates. Also see
	 * {@link #getAbsoluteLocation(GraphicsAlgorithm)}.
	 * 
	 * @param pe
	 *            The pictogram element, for which graphics algorithm to return
	 *            the location.
	 * @return The location of the graphics algorithm associated to the
	 *         pictogram element in absolute coordinates.
	 */
	protected ILocation getAbsoluteLocation(PictogramElement pe) {
		return getAbsoluteLocation(pe.getGraphicsAlgorithm());
	}

	/**
	 * Returns the location of the graphics algorithm in absolute coordinates.
	 * 
	 * @param ga
	 *            The graphics algorithm for which to return the location.
	 * @return The location of the graphics algorithm in absolute coordinates.
	 */
	protected ILocation getAbsoluteLocation(GraphicsAlgorithm ga) {
		ILocation ret = new LocationImpl(0, 0);
		while (ga != null) {
			ret.setX(ret.getX() + ga.getX());
			ret.setY(ret.getY() + ga.getY());
			PictogramElement pe = ga.getPictogramElement();
			if (pe != null) {
				PictogramElement parent = Graphiti.getPeService().getPictogramElementParent(pe);
				if (parent != null) {
					ga = parent.getGraphicsAlgorithm();
				} else {
					ga = null;
				}
			} else {
				ga = ga.getParentGraphicsAlgorithm();
			}
		}
		return ret;
	}

	public IContextMenuEntry[] getContextMenu(ICustomContext context) {
		final String SIGNATURE = "getContextMenu(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(DefaultToolBehaviorProvider.class, SIGNATURE, new Object[] { context });
		}
		IContextMenuEntry[] ret = NO_CONTEXT_MENU_ENTRIES;
		List<IContextMenuEntry> retList = new ArrayList<IContextMenuEntry>();

		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(context);
		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			retList.add(new ContextMenuEntry(customFeature, context));
		}

		ret = retList.toArray(NO_CONTEXT_MENU_ENTRIES);
		if (info) {
			T.racer().exiting(DefaultToolBehaviorProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	public ILocationInfo getLocationInfo(PictogramElement pe, ILocationInfo locationInfo) {
		return locationInfo;
	}

	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		return null;
	}

	/**
	 * @since 0.10
	 */
	public ICustomFeature getSingleClickFeature(ISingleClickContext context) {
		return null;
	}

	/**
	 * Default implementation: creates a connection and an object compartment.
	 * Adds all connection creation features and creation features.
	 * 
	 * @return the palette entries
	 */
	public IPaletteCompartmentEntry[] getPalette() {
		final String SIGNATURE = "getPaletteCompartments()"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(DefaultToolBehaviorProvider.class, SIGNATURE, new Object[0]);
		}
		List<IPaletteCompartmentEntry> compartments = new ArrayList<IPaletteCompartmentEntry>();

		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry(
				Messages.DefaultToolBehaviorProvider_0_xfld, null);
		compartments.add(compartmentEntry);

		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
		if (createConnectionFeatures.length > 0) {

			// ConnectionCreationToolEntry multiTool = null;
			// if (createConnectionFeatures.length > 1) {
			// multiTool = new ConnectionCreationToolEntry("Connection",
			// "dynamic connection", null);
			// compartmentEntry.addToolEntry(multiTool);
			// }

			for (ICreateConnectionFeature createConnectionFeature : createConnectionFeatures) {
				ConnectionCreationToolEntry ccTool = new ConnectionCreationToolEntry(
						createConnectionFeature.getCreateName(), createConnectionFeature.getCreateDescription(),
						createConnectionFeature.getCreateImageId(), createConnectionFeature.getCreateLargeImageId());
				ccTool.addCreateConnectionFeature(createConnectionFeature);
				// if (multiTool != null) {
				// multiTool.addCreateConnectionFeature(createConnectionFeature);
				// }
				compartmentEntry.addToolEntry(ccTool);
			}

		}

		compartmentEntry = new PaletteCompartmentEntry(Messages.DefaultToolBehaviorProvider_1_xfld, null);
		compartments.add(compartmentEntry);

		ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();

		for (ICreateFeature createFeature : createFeatures) {
			ObjectCreationToolEntry objectCreationToolEntry = new ObjectCreationToolEntry(
					createFeature.getCreateName(), createFeature.getCreateDescription(),
					createFeature.getCreateImageId(), createFeature.getCreateLargeImageId(), createFeature);

			compartmentEntry.addToolEntry(objectCreationToolEntry);

		}

		IPaletteCompartmentEntry[] res = compartments.toArray(new IPaletteCompartmentEntry[compartments.size()]);
		if (info) {
			T.racer().exiting(DefaultToolBehaviorProvider.class, SIGNATURE, res);
		}
		return res;
	}

	public IDecorator[] getDecorators(PictogramElement pe) {
		return NO_RENDERING_DECORATORS;
	}

	public ISelectionInfo getSelectionInfoForShape(Shape shape) {
		ISelectionInfo si = new SelectionInfoImpl(IColorConstant.SHAPE_SELECTION_FG, IColorConstant.HANDLE_FG,
				IColorConstant.HANDLE_BG, LineStyle.DASH);
		return si;
	}

	/**
	 * @since 0.9
	 */
	public ISelectionInfo getSelectionInfoForAnchor(Anchor anchor) {
		ISelectionInfo si = new SelectionInfoImpl(IColorConstant.SHAPE_SELECTION_FG, IColorConstant.HANDLE_FG,
				IColorConstant.HANDLE_BG, LineStyle.DASH);
		return si;
	}

	public double[] getZoomLevels() {
		return ZOOM_LEVELS;
	}

	/**
	 * @deprecated Scroll bar based infinite canvas is a workaround for Bug
	 *             195527 and can be harmed by GEF modifications. It will be
	 *             removed then
	 */
	@Deprecated
	public DiagramScrollingBehavior getDiagramScrollingBehavior() {
		return DiagramScrollingBehavior.GEF_DEFAULT;
	}

	private void setDiagramTypeProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
	}

	/**
	 * Gets the diagram type provider.
	 * 
	 * @return the diagram type provider
	 */
	protected IDiagramTypeProvider getDiagramTypeProvider() {
		return this.diagramTypeProvider;
	}

	/**
	 * Gets the feature provider.
	 * 
	 * @return the feature provider
	 */
	protected IFeatureProvider getFeatureProvider() {
		return getDiagramTypeProvider().getFeatureProvider();
	}

	public void preExecute(IExecutionInfo executionInfo) {
	}

	public void postExecute(IExecutionInfo executionInfo) {
	}

	public String getToolTip(GraphicsAlgorithm ga) {
		return null;
	}

	public AbstractText getRichToolTip(GraphicsAlgorithm ga) {
		return Graphiti.getGaCreateService().createPlainText(null, getToolTip(ga));
	}

	public GraphicsAlgorithm getSelectionBorder(PictogramElement pe) {
		return null;
	}

	public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
		GraphicsAlgorithm graphicsAlgorithm = pe.getGraphicsAlgorithm();
		if (graphicsAlgorithm != null) {
			return new GraphicsAlgorithm[] { graphicsAlgorithm };
		}
		return new GraphicsAlgorithm[0];
	}

	public GraphicsAlgorithm getChopboxAnchorArea(PictogramElement pe) {
		return pe.getGraphicsAlgorithm();
	}

	public String getTitleToolTip() {
		return null;
	}

	public boolean isShowGuides() {
		return true;
	}

	public GraphicsAlgorithm getContentArea(ContainerShape cs) {
		return null;
	}

	public String getContributorId() {
		String diagramTypeId = getDiagramTypeProvider().getDiagram().getDiagramTypeId();
		return diagramTypeId + DIAGRAM_PROPERTY_CONTRIBUTOR_SUFFIX;
	}

	public boolean isDefaultBendPointRenderingActive() {
		return true;
	}

	public boolean isMultiSelectionEnabled() {
		return true;
	}

	public boolean isConnectionSelectionEnabled() {
		return true;
	}

	/**
	 * Default Implementation. Customers requested the possibility to plug in
	 * equality decision based on object identity.
	 */
	public boolean equalsBusinessObjects(Object o1, Object o2) {
		if (o1 instanceof EObject && o2 instanceof EObject) {
			return EcoreUtil.equals((EObject) o1, (EObject) o2);
		}
		// Both BOs have to be EMF objects. Otherwise the IndependenceSolver
		// does the job.
		return false;
	}

	/**
	 * {@see IToolBehaviorProvider#isShowFlyoutPalette()}. This default
	 * implementation returns <code>true</code> and tells the framework to show
	 * the palette.
	 * 
	 * @return <code>true</code>
	 * 
	 * @since 0.9
	 */
	public boolean isShowFlyoutPalette() {
		return true;
	}

	/**
	 * {@see IToolBehaviorProvider#isShowSelectionTool()}. This default
	 * implementation returns <code>true</code> and tells the framework to show
	 * the selection tool entry in the palette.
	 * 
	 * @return <code>true</code>
	 * 
	 * @since 0.9
	 */
	public boolean isShowSelectionTool() {
		return true;
	}

	/**
	 * {@see IToolBehaviorProvider#isShowMarqueeTool()}. This default
	 * implementation returns <code>true</code> and tells the framework to show
	 * the marquee tool entry in the palette.
	 * 
	 * @return <code>true</code>
	 * 
	 * @since 0.9
	 */
	public boolean isShowMarqueeTool() {
		return true;
	}

	/**
	 * Is asked to return the selection width (the tolerance area a user can
	 * click to still select) a lathy object (e.g. {@link Polyline} or
	 * {@link Polygon} for {@link Shape}s and {@link Connection}s) in the
	 * diagram. The method is called when a new object is drawn for the first
	 * time onto a diagram (on creation of the object or on opening the
	 * diagram).<br>
	 * The default implementation returns {@link #DEFAULT_LINE_SELECTION_WIDTH}
	 * with the value {@value #DEFAULT_LINE_SELECTION_WIDTH} for all shapes.
	 * 
	 * @param polyline
	 *            the {@link Polyline} object to get the selection width for
	 * @return an int representing the allowed tolerance for clicking in pixels
	 * 
	 * @since 0.9
	 */
	public int getLineSelectionWidth(Polyline polyline) {
		return DEFAULT_LINE_SELECTION_WIDTH;
	}
}