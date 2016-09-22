/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2016 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 348662 - Setting tooptip to null in tool behavior provider doesn't clear up
 *                         tooltip if the associated figure has a previous tooltip
 *    mgorning - Bug 363229 - Nested GA inside Anchors are not correctly refreshed
 *    Felix Velasco (mwenz) - Bug 349416 - Support drag&drop operations on FixPointAnchors
 *                                         the same way as for BoxRelativeAnchors
 *    mgorning - Bug 347144 - Angle of MultiText objects can't be modified
 *    Felix Velasco (mgorning) - Bug 328279 - Support rendering decorator positioning for connection text decorator
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 341898 - Support for AdvancedPropertySheet
 *    mwenz - Bug 358255 - Add Border/Background decorators
 *    mgorning - Bug 368124 - ConnectionDecorator with Text causes problems 
 *    fvelasco - Bug 396247 - ImageDescriptor changes
 *    Andreas Graf/mwenz - Bug 396793 - Text decorators
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz - Bug 412858 - canUpdate is not consulted before calling updateNeeded during startup
 *    mwenz - Bug 434458 - Connections don't support Color decorators
 *    mwenz - Bug 459386 - Refresh Connection when getDiagramBehavior().refreshRenderingDecorators(PEInstance) is called
 *    mwenz - Bug 464857 - Images created by GFImageFigure are not destroyed
 *    mwenz - Bug 481619 - Graphiti is not using custom PlatformGraphicsAlgorithm's layout manager; enforces XYLayout
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.parts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.internal.datatypes.impl.LocationImpl;
import org.eclipse.graphiti.internal.pref.GFPreferences;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.graphiti.platform.ga.IVisualState;
import org.eclipse.graphiti.platform.ga.RendererContext;
import org.eclipse.graphiti.platform.ga.VisualState;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IBorderDecorator;
import org.eclipse.graphiti.tb.IColorDecorator;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.IImageDecorator;
import org.eclipse.graphiti.tb.ITextDecorator;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DefaultRefreshBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.IResourceRegistry;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.figures.DecoratorImageFigure;
import org.eclipse.graphiti.ui.internal.figures.GFAbstractShape;
import org.eclipse.graphiti.ui.internal.figures.GFEllipse;
import org.eclipse.graphiti.ui.internal.figures.GFEllipseDecoration;
import org.eclipse.graphiti.ui.internal.figures.GFImageFigure;
import org.eclipse.graphiti.ui.internal.figures.GFMultilineText;
import org.eclipse.graphiti.ui.internal.figures.GFPolygon;
import org.eclipse.graphiti.ui.internal.figures.GFPolygonDecoration;
import org.eclipse.graphiti.ui.internal.figures.GFPolyline;
import org.eclipse.graphiti.ui.internal.figures.GFPolylineConnection;
import org.eclipse.graphiti.ui.internal.figures.GFPolylineDecoration;
import org.eclipse.graphiti.ui.internal.figures.GFRectangleFigure;
import org.eclipse.graphiti.ui.internal.figures.GFRoundedRectangle;
import org.eclipse.graphiti.ui.internal.figures.GFText;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.IWorkbenchAdapter2;

/**
 * A class, which contains helper-methods, which are necessary to implement the
 * interface IAnchorContainerEditPart. It is not possible to make this an
 * EditPart itself, because of different inheritance-hierarchies used in the
 * sub-classes.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PictogramElementDelegate implements IPictogramElementDelegate {

	private boolean forceRefresh = false;

	private boolean valid = true;

	private IConfigurationProviderInternal configurationProvider;

	private final Hashtable<GraphicsAlgorithm, IFigure> elementFigureHash = new Hashtable<GraphicsAlgorithm, IFigure>();

	private final HashSet<Font> fontList = new HashSet<Font>();
	private final HashSet<Font> decoratorFontList = new HashSet<Font>();

	private PictogramElement pictogramElement;

	private final HashMap<IFigure, List<IFigure>> decoratorMap = new HashMap<IFigure, List<IFigure>>();

	// edit part which holds the instance of this delegate
	private EditPart containerEditPart;

	/**
	 * The {@link IVisualState} of this pictogram element delegate.
	 */
	private IVisualState visualState;

	/**
	 * Creates a new PictogramElementDelegate.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param pictogramElement
	 *            the pictogram element
	 * @param containerEditPart
	 *            the container edit part
	 */
	public PictogramElementDelegate(IConfigurationProviderInternal configurationProvider,
			PictogramElement pictogramElement, EditPart containerEditPart) {
		setConfigurationProvider(configurationProvider);
		setPictogramElement(pictogramElement);
		setContainerEditPart(containerEditPart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate
	 * #activate ()
	 */
	public void activate() {
		// register listener for changes in the bo model -> will be done
		// globally in the DiagramEditorInternal
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate#
	 * createFigure()
	 */
	public IFigure createFigure() {
		PictogramElement pe = getPictogramElement();
		IFigure ret = createFigureForPictogramElement(pe);
		if (getDiagramBehavior().getRefreshBehavior().isMultipleRefreshSupressionActive()) {
			return ret;
		} else {
			refreshFigureForPictogramElement(pe);
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate#
	 * deactivate()
	 */
	public void deactivate() {

		for (IFigure figure : elementFigureHash.values()) {
			removeDecorators(figure);
			if (figure instanceof ImageFigure) {
				// Trigger disposal of currently displayed image (bug 464857)
				((ImageFigure) figure).setImage(null);
			}
		}

		disposeFonts();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		Object ret = null;
		if (key == IGFAdapter.class || key == IWorkbenchAdapter.class || key == IWorkbenchAdapter2.class) {
			ret = new GFAdapter();
		}
		return ret;
	}

	/**
	 * Gets the configuration provider.
	 * 
	 * @return Returns the configurationProvider.
	 */
	public IConfigurationProviderInternal getConfigurationProvider() {
		return configurationProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate#
	 * getFigureForGraphicsAlgorithm(org.eclipse.graphiti.mm.pictograms.
	 * GraphicsAlgorithm)
	 */
	public IFigure getFigureForGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm) {
		IFigure ret = null;
		if (graphicsAlgorithm == null) {
			return ret;
		}
		ret = elementFigureHash.get(graphicsAlgorithm);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate#
	 * getPictogramElement()
	 */
	public PictogramElement getPictogramElement() {
		return pictogramElement;
	}

	/**
	 * refresh edit parts for child pictogram elements.
	 * 
	 * @param ep
	 *            the ep
	 */
	public void refreshEditPartsForModelChildrenAndSourceConnections(EditPart ep) {
		if (ep instanceof IPictogramElementEditPart) {

			IPictogramElementEditPart peep = (IPictogramElementEditPart) ep;

			List<PictogramElement> peList = new ArrayList<PictogramElement>();
			peList.addAll(peep.getModelChildren());
			peList.addAll(peep.getModelSourceConnections());
			// peList.addAll(peep.getModelTargetConnections());

			if (ep.getParent() != null) {
				EditPartViewer viewer = ep.getViewer();
				if (viewer != null) {
					Map<?, ?> editPartRegistry = viewer.getEditPartRegistry();
					if (editPartRegistry != null) {
						for (PictogramElement childPe : peList) {
							Object object = editPartRegistry.get(childPe);
							if (object instanceof EditPart) {
								EditPart editPart = (EditPart) object;
								try {
									editPart.refresh();
								} catch (NullPointerException e) {
									String message = "PictogramElementDelegate.refreshEditPartsForModelChildrenAndSourceConnections():\n    editPart.refresh() threw NullPointerException\n    editPart: " //$NON-NLS-1$
											+ editPart;
									T.racer().error(message, e);
								}// try
							}// if
						}// for
					}// if
				}// if
			}// if
		}// if
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate#
	 * refreshFigureForEditPart(org.eclipse.gef.EditPart)
	 */
	public void refreshFigureForEditPart() {

		// DR: Avoid multiple refresh of the same edit part
		DefaultRefreshBehavior refreshBehavior = getDiagramBehavior().getRefreshBehavior();
		if (!isForceRefresh() && refreshBehavior.isMultipleRefreshSupressionActive()) {
			if (!refreshBehavior.shouldRefresh(getContainerEditPart())) {
				return;
			}
		}

		PictogramElement pe = getPictogramElement();

		if (!GraphitiInternal.getEmfService().isObjectAlive(pe)) {
			return;
		}

		if (pe instanceof org.eclipse.graphiti.mm.pictograms.Shape || pe instanceof AdvancedAnchor) {
			if (isRefreshPossible(pe)) {
				refreshFigureForPictogramElement(pe);
			} else {
				EditPart parentEp = getContainerEditPart().getParent();
				if (parentEp instanceof IShapeEditPart) {
					elementFigureHash.clear();
					IShapeEditPart parent = (IShapeEditPart) parentEp;
					parent.deleteChildAndRefresh(getContainerEditPart());
					setValid(false); // invalidate current delegate
				}
			}
		} else if (pe instanceof Connection) {
			Connection connection = (Connection) pe;
			if (!isConnectionRefreshPossible(connection)) {
				// if refresh is not possible -> reset figure-tree
				IFigure figure = getFigureForGraphicsAlgorithm(connection.getGraphicsAlgorithm());
				elementFigureHash.clear();

				// clean passive decorators
				if (figure instanceof GFPolylineConnection) {
					GFPolylineConnection c = (GFPolylineConnection) figure;
					c.removeAllDecorations();
				}

				addGraphicsAlgorithmForFigure(figure, connection.getGraphicsAlgorithm());
				// create passive decorators
				createFiguresForPassiveDecorators(connection);
			}
			refreshFigureForPictogramElement(pe);
		} else if (pe instanceof Anchor) {
			refreshFigureForPictogramElement(pe);
		}
	}

	/**
	 * Gets the edit part which holds the instance of this delegate.
	 * 
	 * @return the container edit part
	 */
	protected EditPart getContainerEditPart() {
		return containerEditPart;
	}

	/**
	 * Sets the edit part which holds the instance of this delegate.
	 * 
	 * @param containerEditPart
	 *            the new container edit part
	 */
	protected void setContainerEditPart(EditPart containerEditPart) {
		this.containerEditPart = containerEditPart;
	}

	/**
	 * Check if update needed.
	 * 
	 * @param pe
	 *            the pe
	 * @return the i reason
	 */
	IReason checkIfUpdateNeeded(PictogramElement pe) {
		IReason ret = Reason.createFalseReason();
		IFeatureProvider featureProvider = getConfigurationProvider().getFeatureProvider();
		IUpdateContext updateCtx = new UpdateContext(pe);
		IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
		if (updateFeature != null && updateFeature.canUpdate(updateCtx)) {
			ret = updateFeature.updateNeeded(updateCtx);
		}

		if (getPreferences().isRecursiveCheckForUpdateActive()) {
			// check children as well
			if (!ret.toBoolean()) {
				Collection<PictogramElement> peChildren = Graphiti.getPeService().getPictogramElementChildren(pe);
				for (PictogramElement peChild : peChildren) {
					ret = checkIfUpdateNeeded(peChild);
					if (ret.toBoolean()) {
						break;
					}
				}
			}
		}
		if (T.racer().info()) {
			T.racer().info("returns " + ret.toString()); //$NON-NLS-1$
		}
		return ret;
	}

	/**
	 * Refresh figure for graphics algorithm.
	 * 
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 * @param pe
	 *            The pictogram-element to which the graphics-algorithm (or the
	 *            parent-ga) belongs
	 * @param updateNeeded
	 *            the update needed
	 */
	void refreshFigureForGraphicsAlgorithm(final GraphicsAlgorithm graphicsAlgorithm, final PictogramElement pe,
			IReason updateNeeded) {
		if (graphicsAlgorithm == null || pe == null) {
			return;
		}
		final IFigure figure = getFigureForGraphicsAlgorithm(graphicsAlgorithm);
		if (figure == null) {
			return;
		}

		DefaultRefreshBehavior refreshBehavior = getDiagramBehavior().getRefreshBehavior();
		if (!isForceRefresh() && refreshBehavior.isMultipleRefreshSupressionActive()) {
			if (!refreshBehavior.shouldRefresh(graphicsAlgorithm)) {
				return;
			}
		}

		// figure.getChildren().removeAll(figure.getChildren()); //?

		// refresh common figure data
		figure.setOpaque(true);
		figure.setVisible(pe.isVisible());
		figure.setBorder(null);

		// check whether the edit part is a connection edit part and the edit
		// part is selected
		// if yes, refresh of colors and shape style is not necessary
		boolean selectedConnection = false;

		if (getContainerEditPart() instanceof ConnectionEditPart) {
			int selectedState = getContainerEditPart().getSelected();
			if (selectedState == EditPart.SELECTED_PRIMARY || selectedState == EditPart.SELECTED) {
				selectedConnection = true;
			}
		}

		// refresh figure colors
		if (selectedConnection) {
			Color bg = DataTypeTransformation.toSwtColor(getConfigurationProvider().getResourceRegistry(), Graphiti
					.getGaService().getBackgroundColor(graphicsAlgorithm, true));
			figure.setBackgroundColor(bg);
		} else {
			refreshFigureColors(figure, graphicsAlgorithm);
		}

		// refresh specific figure-data
		if (graphicsAlgorithm instanceof Ellipse
				&& (figure instanceof org.eclipse.draw2d.Ellipse || figure instanceof GFEllipse || figure instanceof GFEllipseDecoration)) {
			Shape f = (Shape) figure;
			refreshShapeData(f, graphicsAlgorithm);

		} else if (graphicsAlgorithm instanceof Polygon
				&& (figure instanceof org.eclipse.draw2d.Polygon || figure instanceof GFPolygon || figure instanceof GFPolygonDecoration)) {

			Polygon polygon = (Polygon) graphicsAlgorithm;
			ILocation polygonLocation = new LocationImpl(polygon.getX(), polygon.getY());
			PointList pointList = toAbsoluteDraw2dPoints(polygon.getPoints(), polygonLocation);

			if (figure instanceof GFPolygonDecoration) {
				GFPolygonDecoration p = (GFPolygonDecoration) figure;
				p.setSpecificBezierDistances(getBezierDistances(polygon.getPoints()));
				p.setDecoratorTemplate(pointList);
			} else if (figure instanceof GFPolygon) {
				GFPolygon p = (GFPolygon) figure;
				p.setSpecificBezierDistances(getBezierDistances(polygon.getPoints()));
				p.setPoints(pointList);
			}

			if (!selectedConnection) {
				refreshShapeData((org.eclipse.draw2d.Shape) figure, graphicsAlgorithm);
			}

		} else if (graphicsAlgorithm instanceof Polyline
				&& (figure instanceof org.eclipse.draw2d.Polyline || figure instanceof GFPolyline || figure instanceof GFPolylineDecoration)) {

			// if figure is a PolylineConnection then just a refreshShapeData is
			// necessary

			Polyline polyline = (Polyline) graphicsAlgorithm;
			ILocation polylineLocation = new LocationImpl(polyline.getX(), polyline.getY());
			PointList pointList = toAbsoluteDraw2dPoints(polyline.getPoints(), polylineLocation);

			if (figure instanceof GFPolylineConnection) {
				if (!isDefaultBendPointRenderingActive() && (pe instanceof FreeFormConnection)) {
					FreeFormConnection ffc = (FreeFormConnection) pe;
					GFPolylineConnection p = (GFPolylineConnection) figure;
					int[] bendpointBezierDistances = getBezierDistances(ffc.getBendpoints());
					// add default bendpoints for start and end
					int[] allPointsBezierDistances = new int[bendpointBezierDistances.length + 4];
					allPointsBezierDistances[0] = 0;
					allPointsBezierDistances[1] = 0;
					for (int i = 0; i < bendpointBezierDistances.length; i++) {
						allPointsBezierDistances[i + 2] = bendpointBezierDistances[i];
					}
					allPointsBezierDistances[allPointsBezierDistances.length - 2] = 0;
					allPointsBezierDistances[allPointsBezierDistances.length - 1] = 0;
					p.setSpecificBezierDistances(allPointsBezierDistances);
				}
			} else if (figure instanceof GFPolylineDecoration) {
				GFPolylineDecoration p = (GFPolylineDecoration) figure;
				p.setSpecificBezierDistances(getBezierDistances(polyline.getPoints()));
				p.setDecoratorTemplate(pointList);
			} else if (figure instanceof GFPolyline) {
				GFPolyline p = (GFPolyline) figure;
				p.setSpecificBezierDistances(getBezierDistances(polyline.getPoints()));
				p.setPoints(pointList);
			}

			if (!selectedConnection) {
				refreshShapeData((org.eclipse.draw2d.Shape) figure, graphicsAlgorithm);
			}

		} else if (graphicsAlgorithm instanceof Rectangle && figure instanceof GFRectangleFigure) {
			GFRectangleFigure f = (GFRectangleFigure) figure;
			refreshShapeData(f, graphicsAlgorithm);
		} else if (graphicsAlgorithm instanceof RoundedRectangle) {
			if (figure instanceof GFRoundedRectangle) {
				GFRoundedRectangle f = (GFRoundedRectangle) figure;
				refreshShapeData(f, graphicsAlgorithm);
				RoundedRectangle rr = (RoundedRectangle) graphicsAlgorithm;
				Dimension dimension = new Dimension(rr.getCornerWidth(), rr.getCornerHeight());

				f.setCornerDimensions(dimension);
			}
		} else if (graphicsAlgorithm instanceof MultiText && figure instanceof GFMultilineText) {
			MultiText text = (MultiText) graphicsAlgorithm;
			GFMultilineText label = (GFMultilineText) figure;
			label.setBorder(new MarginBorder(2));
			label.setText(text.getValue());
			refreshFlowTextAlignment(label, text);
			refreshFont(text, label);
			label.setOpaque(Graphiti.getGaService().isFilled(text, true));
			label.setRequestFocusEnabled(false);
			label.invalidateTree();
		} else if (graphicsAlgorithm instanceof Text && figure instanceof GFText) {
			Text text = (Text) graphicsAlgorithm;
			GFText label = (GFText) figure;
			label.setText(text.getValue());
			refreshTextOrientation(label, text);
			refreshFont(text, label);
			label.setOpaque(Graphiti.getGaService().isFilled(text, true));
			label.setRequestFocusEnabled(false);
		} else if (graphicsAlgorithm instanceof Image && figure instanceof ImageFigure) {
			ImageFigure imageFigure = (ImageFigure) figure;
			Image pictogramImage = (Image) graphicsAlgorithm;
			org.eclipse.swt.graphics.Image image = GraphitiUi.getImageService().getImageForId(
					configurationProvider.getDiagramTypeProvider().getProviderId(), pictogramImage.getId());
			imageFigure.setImage(image);
			imageFigure.setAlignment(PositionConstants.CENTER);
			imageFigure.setOpaque(false);
		}
		// set location and size of figure
		setFigureConstraint(figure, graphicsAlgorithm, pe);

		// refresh child GAs
		Collection<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		for (Iterator<GraphicsAlgorithm> iter = graphicsAlgorithmChildren.iterator(); iter.hasNext();) {
			GraphicsAlgorithm childGA = iter.next();
			refreshFigureForGraphicsAlgorithm(childGA, pe, Reason.createFalseReason());
		}

		IDiagramTypeProvider diagramTypeProvider = getConfigurationProvider().getDiagramTypeProvider();
		IToolBehaviorProvider toolBehaviorProvider = diagramTypeProvider.getCurrentToolBehaviorProvider();

		// decorators
		addDecorators(graphicsAlgorithm, pe, figure, toolBehaviorProvider);

		GraphicsAlgorithm selectionGraphicsAlgorithm = toolBehaviorProvider.getSelectionBorder(pe);
		IFigure selectionFigure = getFigureForGraphicsAlgorithm(selectionGraphicsAlgorithm);
		if (selectionFigure == null) {
			// Retreat to graphiti behavior.
			selectionFigure = figure;
		}

		// Create a tooltip label
		IFigure tooltipFigure = null;

		// First check the need for an update needed tooltip
		Label indicateUpdateNeedeTooltipLabel = null;
		if (selectionFigure != null) {
			// Indicate needed updates on selectionFigure (using figure would
			// cause problems with invisible rectangles)
			indicateUpdateNeedeTooltipLabel = indicateNeededUpdates(selectionFigure, updateNeeded);
		}

		// Use the update needed tooltip in case it exists...
		if (indicateUpdateNeedeTooltipLabel != null) {
			// Use update needed tooltip in any case (tool provided tooltip
			// would be probably invalid)
			tooltipFigure = indicateUpdateNeedeTooltipLabel;
		} else {
			// ... if not get the tool provided tooltip (for performance reasons
			// only called in case no update needed tooltip exists)
			Object toolTip = toolBehaviorProvider.getToolTip(graphicsAlgorithm);
			// null or empty string means no tooltip wanted
			if (toolTip != null) {
				if (toolTip instanceof String) {
					String toolTipString = (String) toolTip;
					if (toolTipString.length() > 0) {
						tooltipFigure = new Label(toolTipString);
					}
				} else if (toolTip instanceof Text) {
					Text text = (Text) toolTip;
					if (text.getValue() != null && (text.getValue().length() > 0)) {
						tooltipFigure = new GFText(this, text);
						((GFText) tooltipFigure).setText(text.getValue());
					}
				} else if (toolTip instanceof MultiText) {
					MultiText multiText = (MultiText) toolTip;
					if (multiText.getValue() != null && (multiText.getValue().length() > 0)) {
						tooltipFigure = new GFMultilineText(this, multiText);
					}
				}
			}
		}

		// Set the tooltip in any case, especially also when it's null to clean
		// up a previously set tooltip (see Bugzilla 348662)
		figure.setToolTip(tooltipFigure);
	}

	private void refreshFont(AbstractText text, Figure label) {
		if (text == null || label == null) {
			return;
		}

		// if valid font-information exists in the pictogram-model, then
		// create/change swt-font of label
		org.eclipse.graphiti.mm.algorithms.styles.Font font = Graphiti.getGaService().getFont(text, true);
		if (font != null && font.getName() != null) {

			Font currentSwtFont = label.getFont();
			if (currentSwtFont == null || currentSwtFont.isDisposed()) {
				Font newSwtFont = DataTypeTransformation.toSwtFont(font);
				fontList.add(newSwtFont);
				label.setFont(newSwtFont);
			} else {
				Font newSwtFont = DataTypeTransformation.syncToSwtFont(font, currentSwtFont);
				if (newSwtFont != currentSwtFont) {
					fontList.add(newSwtFont);
					label.setFont(newSwtFont);
					boolean wasInList = fontList.remove(currentSwtFont);
					if (wasInList) {
						currentSwtFont.dispose();
					}
				}
			}
		}
	}

	private void addGraphicsAlgorithmForFigure(IFigure figure, GraphicsAlgorithm graphicsAlgorithm) {
		if (figure != null && graphicsAlgorithm != null) {
			elementFigureHash.put(graphicsAlgorithm, figure);
		}
	}

	private ILocation calculatePolylineLocation(Polyline polyline) {
		Collection<org.eclipse.graphiti.mm.algorithms.styles.Point> points = polyline.getPoints();

		int minX = points.isEmpty() ? 0 : ((org.eclipse.graphiti.mm.algorithms.styles.Point) points.toArray()[0])
				.getX();
		int minY = points.isEmpty() ? 0 : ((org.eclipse.graphiti.mm.algorithms.styles.Point) points.toArray()[0])
				.getY();

		for (Iterator<org.eclipse.graphiti.mm.algorithms.styles.Point> iter = points.iterator(); iter.hasNext();) {
			org.eclipse.graphiti.mm.algorithms.styles.Point point = iter.next();
			int x = point.getX();
			int y = point.getY();
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
		}

		int locX = polyline.getX();
		int locY = polyline.getY();

		return new LocationImpl(minX + locX, minY + locY);
	}

	/**
	 * @param graphicsAlgorithm
	 * @return TRUE, if a figure exists for the ga and for all child-ga's
	 */
	private boolean checkGA(GraphicsAlgorithm graphicsAlgorithm) {

		if (graphicsAlgorithm != null && GraphitiInternal.getEmfService().isObjectAlive(graphicsAlgorithm)) {
			IFigure ret = getFigureForGraphicsAlgorithm(graphicsAlgorithm);
			if (ret == null) {
				return false;
			}
			Collection<GraphicsAlgorithm> children = graphicsAlgorithm.getGraphicsAlgorithmChildren();
			for (Iterator<GraphicsAlgorithm> iter = children.iterator(); iter.hasNext();) {
				GraphicsAlgorithm childGraphicsAlgorithm = iter.next();
				if (!checkGA(childGraphicsAlgorithm)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * returns TRUE, if a figure exists for each ga
	 */
	private boolean checkGAs(PictogramElement pe) {

		if (!GraphitiInternal.getEmfService().isObjectAlive(pe))
			return false;

		GraphicsAlgorithm graphicsAlgorithm = pe.getGraphicsAlgorithm();

		if (!checkGA(graphicsAlgorithm)) {
			return false;
		}

		if (pe instanceof ContainerShape) {
			ContainerShape containerShape = (ContainerShape) pe;
			List<org.eclipse.graphiti.mm.pictograms.Shape> children = containerShape.getChildren();
			for (org.eclipse.graphiti.mm.pictograms.Shape childShape : children) {
				if (!childShape.isActive()) {
					if (!checkGAs(childShape)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private IFigure createFigureForGraphicsAlgorithm(PictogramElement pe, GraphicsAlgorithm graphicsAlgorithm) {
		return createFigureForGraphicsAlgorithm(pe, graphicsAlgorithm, false);
	}

	/**
	 * @param graphicsAlgorithm
	 * @return
	 */
	private IFigure createFigureForGraphicsAlgorithm(PictogramElement pe, GraphicsAlgorithm graphicsAlgorithm,
			boolean specialSelectionHandlingForOuterGaFigures) {
		IFigure ret = null;
		if (graphicsAlgorithm != null) {
			if (pe instanceof Connection) {
				// special for connections
				ret = new GFPolylineConnection(this, graphicsAlgorithm);
			} else if (graphicsAlgorithm instanceof Ellipse) {
				if (pe instanceof ConnectionDecorator && !pe.isActive()) {
					ret = new GFEllipseDecoration(this, graphicsAlgorithm);
				} else {
					ret = new GFEllipse(this, graphicsAlgorithm);
				}
			} else if (graphicsAlgorithm instanceof Polygon) {
				// if graphics-algorithm belongs to an inactive decorator-shape
				// use special polygon
				if (pe instanceof ConnectionDecorator && !pe.isActive()) {
					ret = new GFPolygonDecoration(this, graphicsAlgorithm);
				} else {
					ret = new GFPolygon(this, graphicsAlgorithm);
				}
			} else if (graphicsAlgorithm instanceof Polyline) {
				// if graphics-algorithm belongs to an inactive decorator-shape
				// use special polygon
				if (pe instanceof ConnectionDecorator && !pe.isActive()) {
					ret = new GFPolylineDecoration(this, graphicsAlgorithm);
				} else {
					ret = new GFPolyline(this, graphicsAlgorithm);
				}
			} else if (graphicsAlgorithm instanceof Rectangle) {
				ret = new GFRectangleFigure(this, graphicsAlgorithm);
			} else if (graphicsAlgorithm instanceof RoundedRectangle) {
				ret = new GFRoundedRectangle(this, graphicsAlgorithm);
			} else if (graphicsAlgorithm instanceof MultiText) {
				ret = new GFMultilineText(this, (MultiText) graphicsAlgorithm);
			} else if (graphicsAlgorithm instanceof Text) {
				ret = new GFText(this, (Text) graphicsAlgorithm);
			} else if (graphicsAlgorithm instanceof PlatformGraphicsAlgorithm) {
				PlatformGraphicsAlgorithm pga = (PlatformGraphicsAlgorithm) graphicsAlgorithm;
				IGraphicsAlgorithmRendererFactory factory = getGraphicsAlgorithmRendererFactory();
				if (factory != null) {
					IRendererContext rendererContext = new RendererContext(pga, getConfigurationProvider()
							.getDiagramTypeProvider());
					IGraphicsAlgorithmRenderer pr = factory.createGraphicsAlgorithmRenderer(rendererContext);
					if (pr instanceof IFigure) {
						ret = (IFigure) pr;
					}
				}
			} else if (graphicsAlgorithm instanceof Image) {
				ret = new GFImageFigure(graphicsAlgorithm);
			}

			if (ret != null) {
				if (graphicsAlgorithm.getGraphicsAlgorithmChildren().size() > 0) {
					// Bug 481619 - in case we have a platform graphics
					// algorithm and a layout manager is set then do not
					// override with XYLayout.
					if (!(graphicsAlgorithm instanceof PlatformGraphicsAlgorithm) || ret.getLayoutManager() == null) {
						ret.setLayoutManager(new XYLayout());
					}
				}

				addGraphicsAlgorithmForFigure(ret, graphicsAlgorithm);

				List<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
				for (GraphicsAlgorithm childGa : graphicsAlgorithmChildren) {
					ret.add(createFigureForGraphicsAlgorithm(null, childGa));
				}
			}
		}
		if (specialSelectionHandlingForOuterGaFigures) {
			if (ret instanceof GFAbstractShape) {
				GFAbstractShape gfAbstractShape = (GFAbstractShape) ret;
				IToolBehaviorProvider currentToolBehaviorProvider = getConfigurationProvider().getDiagramTypeProvider()
						.getCurrentToolBehaviorProvider();
				gfAbstractShape.setSelectionBorder(currentToolBehaviorProvider
						.getSelectionBorder(getPictogramElement()));
				gfAbstractShape.setClickArea(currentToolBehaviorProvider.getClickArea(getPictogramElement()));
			}
		}

		return ret;
	}

	private IFigure createFigureForPictogramElement(final PictogramElement pe) {
		GraphicsAlgorithm graphicsAlgorithm = pe.getGraphicsAlgorithm();

		IFigure ret = createFigureForGraphicsAlgorithm(pe, graphicsAlgorithm, pe.isActive());

		if (ret == null) {
			return ret;
		}

		// _directEditPerformer = new
		// DirectEditPerformer(getConfigurationProvider(), this, _labels,
		// attributes);

		if (pe instanceof ContainerShape) {
			// Bug 481619 - in case we have a platform graphics algorithm and a
			// layout manager is set then do not override with XYLayout.
			if (!(graphicsAlgorithm instanceof PlatformGraphicsAlgorithm) || ret.getLayoutManager() == null) {
				ret.setLayoutManager(new XYLayout());
			}
			ContainerShape containerShape = (ContainerShape) pe;
			List<org.eclipse.graphiti.mm.pictograms.Shape> containersChildren = containerShape.getChildren();
			for (org.eclipse.graphiti.mm.pictograms.Shape shape : containersChildren) {
				if (!shape.isActive()) {
					IFigure f = createFigureForPictogramElement(shape);
					ret.add(f);
				}
			}
		} else if (pe instanceof Connection) {
			createFiguresForPassiveDecorators((Connection) pe);
		}

		return ret;
	}

	private void createFiguresForPassiveDecorators(Connection connection) {

		IFigure figure = getFigureForGraphicsAlgorithm(connection.getGraphicsAlgorithm());
		if (figure instanceof GFPolylineConnection) {
			GFPolylineConnection polylineConnection = (GFPolylineConnection) figure;
			Collection<ConnectionDecorator> c = connection.getConnectionDecorators();
			for (ConnectionDecorator connectionDecorator : c) {
				if (!connectionDecorator.isActive()) {
					GraphicsAlgorithm graphicsAlgorithm = connectionDecorator.getGraphicsAlgorithm();
					IFigure newFigure = createFigureForGraphicsAlgorithm(connectionDecorator, graphicsAlgorithm);
					RotatableDecoration rotatableDecoration = null;
					if (newFigure instanceof RotatableDecoration) {
						rotatableDecoration = (RotatableDecoration) newFigure;
					}
					if (connectionDecorator.isLocationRelative()) {
						double relativeLocation = connectionDecorator.getLocation();
						// TODO: change metamodel to get rid of special handling
						// for backward compability
						if (relativeLocation != 1) {
							polylineConnection.addDecoration(rotatableDecoration, true, relativeLocation, 0, 0);
						} else {
							polylineConnection.addDecoration(rotatableDecoration, false, 0.0, 0, 0);
						}
					}
				}
			}
		}
	}

	/**
	 * returns null if the decorator does not produce a figure of its own
	 */
	private IFigure decorateFigure(IFigure figure, IDecorator decorator) {
		if (decorator == null) {
			return null;
		}

		String messageText = decorator.getMessage();

		// Variable to store an additional figure for the decorator (needed in
		// case of an image decorator. Cannot be created in the if for image
		// decorators, because a shape can have more than one decorator (also of
		// different kinds)
		IFigure decoratorFigure = null;
		org.eclipse.draw2d.geometry.Rectangle boundsForDecoratorFigure = new org.eclipse.draw2d.geometry.Rectangle(0,
				0, 16, 16);

		// Decorate with an image
		if (decorator instanceof IImageDecorator) {
			IImageDecorator imageDecorator = (IImageDecorator) decorator;
			org.eclipse.swt.graphics.Image imageForId = GraphitiUi.getImageService().getImageForId(
					configurationProvider.getDiagramTypeProvider().getProviderId(),
					imageDecorator.getImageId());
			ImageFigure imageFigure = new DecoratorImageFigure(imageForId);
			decoratorFigure = imageFigure;
			org.eclipse.swt.graphics.Rectangle imageBounds = imageFigure.getImage().getBounds();
			boundsForDecoratorFigure.setSize(imageBounds.width, imageBounds.height);

			if (decorator instanceof ILocation) {
				ILocation location = (ILocation) decorator;
				boundsForDecoratorFigure.setLocation(location.getX(), location.getY());
			}

			IFigure parent = figure.getParent();
			if (parent != null) {
				org.eclipse.draw2d.geometry.Rectangle ownerBounds = (org.eclipse.draw2d.geometry.Rectangle) parent
						.getLayoutManager().getConstraint(figure);
				boundsForDecoratorFigure.translate(ownerBounds.getLocation());
			}

			decoratorFigure.setVisible(true);
			if (messageText != null && messageText.length() > 0) {
				decoratorFigure.setToolTip(new Label(messageText));
			}
			if (parent.getLayoutManager() == null) {
				parent.setLayoutManager(new XYLayout());
			}
			parent.add(decoratorFigure, boundsForDecoratorFigure, parent.getChildren().indexOf(figure) + 1);
		}

		// Decorate with text
		if (decorator instanceof ITextDecorator) {
			ITextDecorator textDecorator = (ITextDecorator) decorator;
			String text = textDecorator.getText();
			decoratorFigure = new Label(text);

			Font font = new org.eclipse.swt.graphics.Font(null, textDecorator.getFontName(),
					textDecorator.getFontSize(), SWT.NORMAL);
			decoratorFontList.add(font);
			Dimension dimension = TextUtilities.INSTANCE.getStringExtents(text, font);
			boundsForDecoratorFigure.setSize(dimension.width, dimension.height);

			decoratorFigure.setFont(font);

			IResourceRegistry resourceRegistry = getConfigurationProvider().getResourceRegistry();
			IColorConstant backgroundColor = textDecorator.getBackgroundColor();
			if (backgroundColor != null) {
				decoratorFigure.setOpaque(true);
				decoratorFigure.setBackgroundColor(resourceRegistry.getSwtColor(backgroundColor.getRed(),
						backgroundColor.getGreen(), backgroundColor.getBlue()));
			}
			IColorConstant foregroundColor = textDecorator.getForegroundColor();
			if (foregroundColor != null) {
				decoratorFigure.setForegroundColor(resourceRegistry.getSwtColor(foregroundColor.getRed(),
						foregroundColor.getGreen(), foregroundColor.getBlue()));
			}

			if (decorator instanceof ILocation) {
				ILocation location = (ILocation) decorator;
				boundsForDecoratorFigure.setLocation(location.getX(), location.getY());
			}

			IFigure parent = figure.getParent();
			if (parent != null) {
				org.eclipse.draw2d.geometry.Rectangle ownerBounds = (org.eclipse.draw2d.geometry.Rectangle) parent
						.getLayoutManager().getConstraint(figure);
				boundsForDecoratorFigure.translate(ownerBounds.getLocation());
			}

			decoratorFigure.setVisible(true);
			if (messageText != null && messageText.length() > 0) {
				decoratorFigure.setToolTip(new Label(messageText));
			}
			if (parent.getLayoutManager() == null) {
				parent.setLayoutManager(new XYLayout());
			}
			parent.add(decoratorFigure, boundsForDecoratorFigure, parent.getChildren().indexOf(figure) + 1);
		}

		// Decorate the border of the shape
		if (decorator instanceof IBorderDecorator) {
			IBorderDecorator renderingDecorator = ((IBorderDecorator) decorator);
			IResourceRegistry resourceRegistry = getConfigurationProvider().getResourceRegistry();

			// Get the width for the border (or set to default)
			Integer borderWidth = renderingDecorator.getBorderWidth();
			if (borderWidth == null || borderWidth < 1) {
				borderWidth = 1;
			}

			// Get the style for the border (or set to default)
			Integer borderStyle = renderingDecorator.getBorderStyle();
			if (borderStyle == null
					|| (Graphics.LINE_DASH != borderStyle && Graphics.LINE_DASHDOT != borderStyle
							&& Graphics.LINE_DASHDOTDOT != borderStyle && Graphics.LINE_DOT != borderStyle && Graphics.LINE_SOLID != borderStyle)) {
				borderStyle = Graphics.LINE_SOLID;
			}

			// Get the color for the border (or set to default)
			Color borderColor = null;
			IColorConstant colorConstant = renderingDecorator.getBorderColor();
			if (colorConstant == null) {
				colorConstant = IColorConstant.BLACK;
			}
			borderColor = resourceRegistry.getSwtColor(colorConstant.getRed(), colorConstant.getGreen(),
					colorConstant.getBlue());

			// Apply border decorations
			figure.setBorder(new LineBorder(borderColor, borderWidth, borderStyle));
		}

		// Decorate using the colors of the shape
		if (decorator instanceof IColorDecorator) {
			IColorDecorator renderingDecorator = ((IColorDecorator) decorator);
			IResourceRegistry resourceRegistry = getConfigurationProvider().getResourceRegistry();

			// Get the background color (or set to default)
			Color backgroundColor = null;
			IColorConstant colorConstant = renderingDecorator.getBackgroundColor();
			if (colorConstant != null) {
				backgroundColor = resourceRegistry.getSwtColor(colorConstant.getRed(), colorConstant.getGreen(),
						colorConstant.getBlue());
			}

			// Get the foreground color (or set to default)
			Color foregroundColor = null;
			colorConstant = renderingDecorator.getForegroundColor();
			if (colorConstant != null) {
				foregroundColor = resourceRegistry.getSwtColor(colorConstant.getRed(), colorConstant.getGreen(),
						colorConstant.getBlue());
			}

			// Apply color decorations
			if (foregroundColor != null) {
				figure.setForegroundColor(foregroundColor);
			}
			if (backgroundColor != null) {
				figure.setBackgroundColor(backgroundColor);
			}
			if (figure instanceof Shape) {
				((Shape) figure).setFill(true);
			}
		}

		// Return additionally created figure
		return decoratorFigure;
	}

	/*
	 * must be called from the edit-part if this edit-part is de-activated
	 */
	private void disposeFonts() {
		for (Iterator<Font> iter = fontList.iterator(); iter.hasNext();) {
			Font font = iter.next();
			font.dispose();
		}
		disposeDecoratorFonts();
	}

	private void disposeDecoratorFonts() {
		for (Iterator<Font> iter = decoratorFontList.iterator(); iter.hasNext();) {
			Font font = iter.next();
			font.dispose();
		}
	}

	/**
	 * @param figure
	 * @param updateNeeded
	 */
	private Label indicateNeededUpdates(IFigure figure, IReason updateNeeded) {
		Label ret = null;
		if (figure != null && updateNeeded != null && updateNeeded.toBoolean()) {
			// The figure needs an update, we indicate that with a red border
			// and a tooltip showing the reason for the update
			figure.setForegroundColor(ColorConstants.red);
			if (figure instanceof Shape) {
				Shape draw2dShape = (Shape) figure;
				draw2dShape.setLineWidth(2);
				draw2dShape.setLineStyle(Graphics.LINE_DOT);

				String updateNeededText = updateNeeded.getText();
				if (updateNeededText != null && updateNeededText.length() > 0) {
					Label toolTipFigure = new Label();
					toolTipFigure.setText(updateNeededText);
					org.eclipse.swt.graphics.Image image = PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJS_WARN_TSK);
					toolTipFigure.setIcon(image);
					ret = toolTipFigure;
				}
			}
		}
		return ret;
	}

	/**
	 * returns TRUE if structure of connection-model (connection,passive
	 * decorators and ga's) are in sync with the current figure-tree
	 */
	private boolean isConnectionRefreshPossible(Connection connection) {
		if (!GraphitiInternal.getEmfService().isObjectAlive(connection)) {
			return false;
		}

		// compare pictogram-model with figure-tree -> structural changes?

		List<GraphicsAlgorithm> gaList = new ArrayList<GraphicsAlgorithm>();
		Collection<ConnectionDecorator> c = connection.getConnectionDecorators();
		for (ConnectionDecorator connectionDecorator : c) {
			if (!connectionDecorator.isActive()) {
				GraphicsAlgorithm ga = connectionDecorator.getGraphicsAlgorithm();
				if (ga != null) {
					gaList.add(ga);
				}
			}
		}

		if (connection.getGraphicsAlgorithm() != null) {
			gaList.add(connection.getGraphicsAlgorithm());
		}

		// do all ga's have a figure?
		for (GraphicsAlgorithm graphicsAlgorithm : gaList) {
			IFigure figure = getFigureForGraphicsAlgorithm(graphicsAlgorithm);
			if (figure == null) {
				return false;
			}
		}

		// are there any registered GA, which are not in use?
		for (GraphicsAlgorithm graphicsAlgorithm : elementFigureHash.keySet()) {
			if (!gaList.contains(graphicsAlgorithm)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * returns TRUE if structure of pictogram-model (shapes & ga's) are in sync
	 * with the current figure-tree
	 */
	private boolean isRefreshPossible(PictogramElement pe) {
		// compare pictogram-model with figure-tree -> structural changes?
		if (pe instanceof org.eclipse.graphiti.mm.pictograms.Shape || pe instanceof AdvancedAnchor) {
			boolean ret = checkGAs(pe);
			if (ret == false) {
				return ret;
			}
			// invalid ga's in hashtable?
			for (GraphicsAlgorithm graphicsAlgorithm : elementFigureHash.keySet()) {
				if (!GraphitiInternal.getEmfService().isObjectAlive(graphicsAlgorithm)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param figure
	 * @param graphicsAlgorithm
	 */
	private void refreshFigureColors(IFigure figure, GraphicsAlgorithm graphicsAlgorithm) {
		Color fg = DataTypeTransformation.toSwtColor(getConfigurationProvider().getResourceRegistry(), Graphiti
				.getGaService().getForegroundColor(graphicsAlgorithm, true));
		Color bg = DataTypeTransformation.toSwtColor(getConfigurationProvider().getResourceRegistry(), Graphiti
				.getGaService().getBackgroundColor(graphicsAlgorithm, true));
		figure.setBackgroundColor(bg);
		figure.setForegroundColor(fg);
	}

	// private void refreshFigureColors(IFigure figure, GraphicsAlgorithm
	// graphicsAlgorithm, int redShift) {
	// if (redShift == 0) {
	// refreshFigureColors(figure, graphicsAlgorithm);
	// } else {
	// Color fg =
	// toSwtColor(Graphiti.getGaService()Internal.getForegroundColor(graphicsAlgorithm,
	// true), redShift);
	// Color bg =
	// toSwtColor(Graphiti.getGaService()Internal.getBackgroundColor(graphicsAlgorithm,
	// true), redShift);
	// figure.setBackgroundColor(bg);
	// figure.setForegroundColor(fg);
	// }
	// }

	/**
	 * @param figure
	 */
	private void refreshFigureForPictogramElement(PictogramElement pe) {

		if (pe != null) {
			DefaultRefreshBehavior refreshBehavior = getDiagramBehavior().getRefreshBehavior();
			if (!isForceRefresh() && refreshBehavior.isMultipleRefreshSupressionActive()) {
				if (!refreshBehavior.shouldRefresh(pe)) {
					return;
				}
			}

			if (pe instanceof ContainerShape) {
				ContainerShape containerShape = (ContainerShape) pe;
				List<org.eclipse.graphiti.mm.pictograms.Shape> children = containerShape.getChildren();
				for (org.eclipse.graphiti.mm.pictograms.Shape shape : children) {
					if (!shape.isActive()) {
						refreshFigureForPictogramElement(shape);
					}
				}
			} else if (pe instanceof Connection) {
				Connection connection = (Connection) pe;
				Collection<ConnectionDecorator> c = connection.getConnectionDecorators();
				for (ConnectionDecorator decorator : c) {
					if (!decorator.isActive()) {
						refreshFigureForPictogramElement(decorator);
					}
				}
			}

			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			if (ga != null) {
				IReason updateNeeded = checkIfUpdateNeeded(pe);
				refreshFigureForGraphicsAlgorithm(ga, pe, updateNeeded);
			}
		}
	}

	/**
	 * @param shape
	 * @param graphicsAlgorithm
	 */
	private void refreshShapeData(org.eclipse.draw2d.Shape shape, GraphicsAlgorithm graphicsAlgorithm) {

		if (shape == null || graphicsAlgorithm == null) {
			return;
		}

		// line width
		int lineWidth = Graphiti.getGaService().getLineWidth(graphicsAlgorithm, true);
		shape.setLineWidth(lineWidth);

		// line style
		LineStyle lineStyle = Graphiti.getGaService().getLineStyle(graphicsAlgorithm, true);
		int draw2dLineStyle = Graphics.LINE_SOLID;
		if (lineStyle == LineStyle.DASH) {
			draw2dLineStyle = Graphics.LINE_DASH;
		} else if (lineStyle == LineStyle.DASHDOT) {
			draw2dLineStyle = Graphics.LINE_DASHDOT;
		} else if (lineStyle == LineStyle.DASHDOTDOT) {
			draw2dLineStyle = Graphics.LINE_DASHDOTDOT;
		} else if (lineStyle == LineStyle.DOT) {
			draw2dLineStyle = Graphics.LINE_DOT;
		} else if (lineStyle == LineStyle.SOLID) {
			draw2dLineStyle = Graphics.LINE_SOLID;
		}
		shape.setLineStyle(draw2dLineStyle);

		// fill?
		final boolean filled = Graphiti.getGaService().isFilled(graphicsAlgorithm, true);
		shape.setFill(filled);

		// outline?
		final boolean lineVisible = Graphiti.getGaService().isLineVisible(graphicsAlgorithm, true);
		shape.setOutline(lineVisible);
	}

	private void refreshTextOrientation(GFText label, Text text) {

		int draw2dOrientation = PositionConstants.LEFT;

		Orientation orientation = Graphiti.getGaService().getHorizontalAlignment(text, true);
		if (orientation != null) {
			if (orientation == Orientation.ALIGNMENT_BOTTOM) {
				draw2dOrientation = PositionConstants.BOTTOM;
			} else if (orientation == Orientation.ALIGNMENT_CENTER) {
				draw2dOrientation = PositionConstants.CENTER;
			} else if (orientation == Orientation.ALIGNMENT_LEFT) {
				draw2dOrientation = PositionConstants.LEFT;
			} else if (orientation == Orientation.ALIGNMENT_RIGHT) {
				draw2dOrientation = PositionConstants.RIGHT;
			} else if (orientation == Orientation.ALIGNMENT_TOP) {
				draw2dOrientation = PositionConstants.TOP;
			}
		}
		label.setLabelAlignment(draw2dOrientation);
	}

	private void refreshFlowTextAlignment(GFMultilineText label, MultiText text) {

		int draw2dOrientation = PositionConstants.LEFT;
		Orientation orientation = Graphiti.getGaService().getHorizontalAlignment(text, true);
		if (orientation != null) {
			if (orientation == Orientation.ALIGNMENT_RIGHT) {
				draw2dOrientation = PositionConstants.RIGHT;
			} else if (orientation == Orientation.ALIGNMENT_CENTER) {
				draw2dOrientation = PositionConstants.CENTER;
			}
		}
		label.setHorizontalAligment(draw2dOrientation);

		draw2dOrientation = PositionConstants.TOP;
		orientation = Graphiti.getGaService().getVerticalAlignment(text, true);
		if (orientation != null) {
			if (orientation == Orientation.ALIGNMENT_BOTTOM) {
				draw2dOrientation = PositionConstants.BOTTOM;
			} else if (orientation == Orientation.ALIGNMENT_CENTER) {
				draw2dOrientation = PositionConstants.MIDDLE;
			}
		}
		label.setVerticalAligment(draw2dOrientation);
	}

	// private void
	// registerBusinessChangeListenersForPictogramElement(PictogramElement pe) {
	// if (businessChangeListeners != null) {
	// return; // throw new IllegalStateException("Listeners already
	// // registered");
	// }
	// EventRegistry eventRegistry = getEventRegistry();
	// if (eventRegistry != null) {
	// IFeatureProvider featureProvider =
	// getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
	// if (featureProvider != null) {
	// Object[] businessObjects =
	// featureProvider.getAllBusinessObjectsForPictogramElement(pe);
	//
	// int boCount = businessObjects.length;
	// businessChangeListeners = new BusinessChangeListener[boCount];
	// for (int i = 0; i < boCount; i++) {
	// Object bo = businessObjects[i];
	// if (bo instanceof EObject) {
	// EObject rbo = (EObject) bo;
	// EventFilter filter = new CompositionHierarchyFilter(rbo);
	// businessChangeListeners[i] = new BusinessChangeListener();
	// eventRegistry.registerUpdateListener(businessChangeListeners[i], filter);
	// // register a partition change listener as well
	// if (bo instanceof Partitionable) {
	// Partitionable partitionlable = (Partitionable) bo;
	// ModelPartition partition = partitionlable.get___Partition();
	// eventRegistry.registerUpdateListener(businessChangeListeners[i], new
	// AndFilter(new EventTypeFilter(
	// PartitionChangeEvent.class), new PartitionFilter(partition)));
	// }
	// }
	// }
	// }
	// }
	// }

	/**
	 * @param configurationProvider
	 *            The configurationProvider to set.
	 */
	private void setConfigurationProvider(IConfigurationProviderInternal configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	private void setFigureConstraint(IFigure figure, GraphicsAlgorithm graphicsAlgorithm, PictogramElement pe) {

		// PolylineConnection's and RotatableDecoration's will be handled by the
		// gef-framework
		if ((figure instanceof PolylineConnection) || figure instanceof GFPolylineConnection
				|| (figure instanceof RotatableDecoration && !(figure instanceof GFText)) || figure.getParent() == null) {
			return;
		}

		IDimension gaSize = Graphiti.getGaService().calculateSize(graphicsAlgorithm);

		if (gaSize == null) {
			return;
		}

		Dimension dimension = new Dimension(gaSize.getWidth(), gaSize.getHeight());

		// Special handling if a text is used in a passive connection decorator.
		// In this case simply set the size. The location can not be set,
		// because it will be overwritten later through the class
		// FlexibleRotatableLocator
		if (figure instanceof GFText && (pe instanceof ConnectionDecorator) && !pe.isActive()
				&& (figure.getParent() instanceof GFPolylineConnection)) {
			GFText text = (GFText) figure;
			text.setSize(dimension);
			return;
		}

		ILocation gaLocation = null;

		if (!(graphicsAlgorithm instanceof Polyline)) {
			gaLocation = new LocationImpl(graphicsAlgorithm.getX(), graphicsAlgorithm.getY());
		} else {
			gaLocation = calculatePolylineLocation((Polyline) graphicsAlgorithm);
		}

		if (gaLocation == null) {
			gaLocation = new LocationImpl(0, 0);
		}

		Point point = null;

		if (pe instanceof ConnectionDecorator && pe.isActive()) {

			// get relative point on connection-figure
			ConnectionDecorator connectionDecorator = (ConnectionDecorator) pe;
			Connection connection = connectionDecorator.getConnection();
			Point pointAt = null;
			double decoratorLocation = connectionDecorator.getLocation();
			if (connectionDecorator.isLocationRelative()) {
				pointAt = GraphitiUiInternal.getGefService().getConnectionPointAt(connection, decoratorLocation);
			} else { // location is absolute
				pointAt = GraphitiUiInternal.getGefService()
						.getAbsolutePointOnConnection(connection, decoratorLocation);
			}

			if (pointAt == null) {
				return;
			}
			point = new Point(pointAt.x + gaLocation.getX(), pointAt.y + gaLocation.getY());

			// all decorator-shapes get the the dimension of the text which they
			// contain
			// TODO fix this hack; later the features are responsibles for
			// providing the correct size
			if (figure instanceof Label && connectionDecorator.getGraphicsAlgorithm() instanceof Text) {
				Label label = (Label) figure;
				if (label.getText() != null && label.getText().length() > 0) {
					// if text was not set, getTextBounds() does not work
					// correct
					dimension = label.getTextBounds().getSize();
					// WORKAROUND:
					// We had the problem, that the text-width was sometimes too
					// small
					// (depending on the zoom-level, text or installation?!)
					// As an easy fix, we add one pixel per character
					dimension.width += label.getText().length();
				}
			}

		} else if (pe instanceof BoxRelativeAnchor && pe.equals(graphicsAlgorithm.getPictogramElement())) {
			BoxRelativeAnchor bra = (BoxRelativeAnchor) pe;
			IRectangle gaBoundsForAnchor = Graphiti.getLayoutService().getGaBoundsForAnchor(bra);
			double newX = gaBoundsForAnchor.getX() + (gaBoundsForAnchor.getWidth() * bra.getRelativeWidth())
					+ gaLocation.getX();
			double newY = gaBoundsForAnchor.getY() + (gaBoundsForAnchor.getHeight() * bra.getRelativeHeight())
					+ gaLocation.getY();
			point = new PrecisionPoint(newX, newY);

		} else if (pe instanceof FixPointAnchor && pe.equals(graphicsAlgorithm.getPictogramElement())) {
			FixPointAnchor fpa = (FixPointAnchor) pe;
			IRectangle gaBoundsForAnchor = Graphiti.getLayoutService().getGaBoundsForAnchor(fpa);
			org.eclipse.graphiti.mm.algorithms.styles.Point fpaLocation = fpa.getLocation();
			if (fpaLocation == null) {
				return;
			}
			point = new Point(gaBoundsForAnchor.getX() + fpaLocation.getX() + gaLocation.getX(),
					gaBoundsForAnchor.getY() + fpaLocation.getY() + gaLocation.getY());

		} else {
			point = new Point(gaLocation.getX(), gaLocation.getY());
		}

		if (point != null) {
			IFigure parent = figure.getParent();
			if (parent != null) {
				parent.setConstraint(figure, new org.eclipse.draw2d.geometry.Rectangle(point, dimension));
			}
		}
	}

	/**
	 * @param pictogramElement
	 *            The pictogramElement to set.
	 */
	private void setPictogramElement(PictogramElement pictogramElement) {
		this.pictogramElement = pictogramElement;
	}

	private PointList toAbsoluteDraw2dPoints(Collection<org.eclipse.graphiti.mm.algorithms.styles.Point> points,
			ILocation location) {
		int deltaX = 0;
		int deltaY = 0;
		if (location != null) {
			deltaX = location.getX();
			deltaY = location.getY();
		}
		PointList pointList = new PointList();
		for (org.eclipse.graphiti.mm.algorithms.styles.Point dtp : points) {
			pointList.addPoint(dtp.getX() + deltaX, dtp.getY() + deltaY);
		}
		return pointList;
	}

	/**
	 * Returns the bezier-distances (distance-before and distance-after), which
	 * are calculated from the radius of the given points.
	 * 
	 * @param points
	 *            The points, for which to return the bezier-distances.
	 * 
	 * @return The bezier-distances (distance-before and distance-after), which
	 *         are calculated from the radius of the given points.
	 */
	private int[] getBezierDistances(Collection<org.eclipse.graphiti.mm.algorithms.styles.Point> points) {
		if (getPreferences().getPolylineRounding() == 0 || getPreferences().getPolylineRounding() == 2) {
			int i = 0;
			int bezierDistances[] = new int[points.size() * 2];
			for (org.eclipse.graphiti.mm.algorithms.styles.Point dtp : points) {
				if (getPreferences().getPolylineRounding() == 0) { // rounding
																	// on
					bezierDistances[i++] = dtp.getBefore(); // bezierDistancesBefore
					bezierDistances[i++] = dtp.getAfter(); // bezierDistancesAfter
				} else if (getPreferences().getPolylineRounding() == 2) { // rounding
																			// always
					bezierDistances[i++] = 15; // bezierDistancesBefore
					bezierDistances[i++] = 15; // bezierDistancesAfter
				}
			}
			return bezierDistances;
		}
		return null; // rounding off
	}

	private IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return getConfigurationProvider().getDiagramTypeProvider().getGraphicsAlgorithmRendererFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		IConfigurationProvider cp = getConfigurationProvider();
		IFeatureProvider ret = null;
		if (cp != null) {
			ret = cp.getFeatureProvider();
		}
		if (T.racer().info()) {
			T.racer().info("PictogramElementDelegate", "getFeatureProvider", "returns " + ret.toString()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return ret;
	}

	public void setForceRefresh(boolean forceRefresh) {
		this.forceRefresh = forceRefresh;
	}

	protected boolean isForceRefresh() {
		return forceRefresh;
	}

	private DiagramBehavior getDiagramBehavior() {
		return getConfigurationProvider().getDiagramBehavior();
	}

	protected void addDecorators(final GraphicsAlgorithm graphicsAlgorithm, final PictogramElement pe,
			final IFigure figure, IToolBehaviorProvider toolBehaviorProvider) {
		if (pe.isActive() && !(pe instanceof Anchor) && graphicsAlgorithm.equals(pe.getGraphicsAlgorithm())) {

			removeDecorators(figure);
			refreshFigureColors(figure, graphicsAlgorithm);
			figure.setBorder(null);

			IDecorator[] decorators = toolBehaviorProvider.getDecorators(pe);

			if (decorators.length > 0) {
				List<IFigure> decList = new ArrayList<IFigure>();
				decoratorMap.put(figure, decList);
				for (int i = 0; i < decorators.length; i++) {
					if (pe instanceof Connection && !(decorators[i] instanceof IColorDecorator)) {
						// For connections only color decorators are supported
						continue;
					}
					IDecorator decorator = decorators[i];
					IFigure decorateFigure = decorateFigure(figure, decorator);
					if (decorateFigure != null) {
						decList.add(decorateFigure);
					}
				}
			}
		}
	}

	private void removeDecorators(final IFigure figure) {
		List<IFigure> decFigureList = decoratorMap.get(figure);
		if (decFigureList != null) {
			for (IFigure decFigure : decFigureList) {
				IFigure parent = decFigure.getParent();
				if (parent != null) {
					parent.remove(decFigure);
				}
			}
			decFigureList.clear();
			decoratorMap.remove(figure);
		}
		disposeDecoratorFonts();
	}

	public boolean isValid() {
		return valid;
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Returns the visual state of this shape.
	 * 
	 * @return The visual state of this shape.
	 */
	public IVisualState getVisualState() {
		if (visualState == null) {
			visualState = new VisualState();
		}
		return visualState;
	}

	private boolean isDefaultBendPointRenderingActive() {
		boolean defaultBendPointRenderingActive = true;
		IToolBehaviorProvider ctbp = getConfigurationProvider().getDiagramTypeProvider()
				.getCurrentToolBehaviorProvider();
		if (ctbp instanceof DefaultToolBehaviorProvider) {
			defaultBendPointRenderingActive = ((DefaultToolBehaviorProvider) ctbp).isDefaultBendPointRenderingActive();
		}
		return defaultBendPointRenderingActive;
	}

	public List<IFigure> getMainFiguresFromChildEditparts() {
		List<IFigure> ret = new ArrayList<IFigure>();
		List<EditPart> children = GraphitiUiInternal.getGefService().getEditPartChildren(getContainerEditPart());
		for (EditPart ep : children) {
			if (ep instanceof ShapeEditPart || ep instanceof AdvancedAnchorEditPart) {
				GraphicalEditPart gep = (GraphicalEditPart) ep;
				ret.add(gep.getFigure());
			}
		}
		return ret;
	}

	protected GFPreferences getPreferences() {
		return GFPreferences.getInstance();
	}

	public void refreshDecorators() {
		PictogramElement pe = getPictogramElement();
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
		IToolBehaviorProvider tbp = getConfigurationProvider().getDiagramTypeProvider()
				.getCurrentToolBehaviorProvider();
		addDecorators(ga, pe, getFigureForGraphicsAlgorithm(ga), tbp);
	}
}
