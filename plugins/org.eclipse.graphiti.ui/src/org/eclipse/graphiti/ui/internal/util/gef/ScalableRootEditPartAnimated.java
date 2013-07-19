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
 *    fvelasco - Bug 403664 - Enable DoubleClickFeature on the diagram background
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.gef;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DoubleClickContext;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.editor.GFMarqueeDragTracker;
import org.eclipse.graphiti.ui.internal.fixed.FixedScalableFreeformRootEditPart;
import org.eclipse.graphiti.ui.internal.util.draw2d.GridLayer;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * A subclass of the ScalableRootEditPart, which just replaces the default
 * ZoomManager with a ZoomManagerWithAnimation (unfortunately there is no method
 * setZoomManager() on the ScalableRootEditPart)
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ScalableRootEditPartAnimated extends FixedScalableFreeformRootEditPart {

	private ZoomManagerWithAnimation zoomManagerWithAnimation;

	private IConfigurationProviderInternal configurationProviderInternal;

	private static boolean connectionLayerOnTop = true; // true means default

	// behavior

	/**
	 * Creates a new ScalableRootEditPartAnimated.
	 */
	public ScalableRootEditPartAnimated(ScrollingGraphicalViewer viewer,
			IConfigurationProviderInternal configurationProviderInternal) {
		zoomManagerWithAnimation = new ZoomManagerWithAnimation((ScalableFreeformLayeredPane) getScaledLayers(), ((Viewport) getFigure()),
				viewer);
		this.configurationProviderInternal = configurationProviderInternal;
	}

	protected GridLayer createGridLayer() {
		return new GridLayer(configurationProviderInternal);
	}

	@Override
	protected void createLayers(LayeredPane layeredPane) {
		super.createLayers(layeredPane);

		// THIS IS A WORKAROUND: PROBLEM WITH HANDLES MOVING POLYGONS WITH LINE
		// WIDTH = 1
		layeredPane.remove(getLayer(HANDLE_LAYER));
		layeredPane.add(new FreeformLayer() {
			@Override
			public void validate() {

				setValid(false);
				super.validate();
			}
		}, HANDLE_LAYER);
		// END WORKAROUND
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ScalableRootEditPart#getZoomManager()
	 */
	@Override
	public ZoomManager getZoomManager() {
		return zoomManagerWithAnimation;
	}

	@Override
	protected LayeredPane createPrintableLayers() {
		if (connectionLayerOnTop) {
			return super.createPrintableLayers();
		} else {
			FreeformLayeredPane layeredPane = new FreeformLayeredPane();
			layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
			layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
			return layeredPane;
		}
	}

	@Override
	public DragTracker getDragTracker(Request req) {
		return new GFMarqueeDragTracker(this);
	}

	@Override
	public void performRequest(Request request) {

		// get current mouse location from the viewer, adjusting for scroll and
		// zoom
		DiagramBehavior behavior = configurationProviderInternal.getDiagramBehavior();
		Point point = behavior.calculateRealMouseLocation(behavior.getMouseLocation());

		Shape shape = configurationProviderInternal.getDiagram();
		ILocationInfo locationInfo = Graphiti.getLayoutService().getLocationInfo(shape, point.x, point.y);

		if (request.getType().equals(REQ_OPEN)) {

			if (locationInfo != null) {
				DoubleClickContext dcc = new DoubleClickContext(shape, locationInfo.getShape(),
						locationInfo.getGraphicsAlgorithm());

				if (request instanceof LocationRequest) {
					Point location = ((LocationRequest) request).getLocation();
					location = behavior.calculateRealMouseLocation(location);
					dcc.setLocation(location.x, location.y);
				}

				IToolBehaviorProvider currentToolBehaviorProvider = configurationProviderInternal
						.getDiagramTypeProvider()
						.getCurrentToolBehaviorProvider();

				IFeature doubleClickFeature = currentToolBehaviorProvider.getDoubleClickFeature(dcc);

				if (doubleClickFeature != null && doubleClickFeature.canExecute(dcc)) {
					GenericFeatureCommandWithContext commandWithContext = new GenericFeatureCommandWithContext(
							doubleClickFeature, dcc);
					CommandStack commandStack = behavior.getEditDomain().getCommandStack();
					commandStack.execute(new GefCommandWrapper(commandWithContext, behavior.getEditingDomain()));
				}

			}

		}

		super.performRequest(request);
	}
}
