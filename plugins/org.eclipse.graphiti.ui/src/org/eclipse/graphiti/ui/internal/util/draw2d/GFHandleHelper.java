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
 *    mwenz - Bug 346487 - No selection feedback for non-resizable diagram nodes 
 *    mgorning - Bug 391523 - Revise getSelectionInfo...() in IToolBehaviorProvider
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.draw2d;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.tb.IShapeSelectionInfo;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;

/**
 * Collection of static helper methods for handles.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFHandleHelper {

	/**
	 * Returns the current zoom-level to use for the handles. This method can be
	 * overwritten to enable/disable zooming for handles.
	 * 
	 * @param configurationProvider
	 *            The configuration provider from which the zoom-level can be
	 *            retrieved.
	 * 
	 * @return The current zoom-level of the given configuration provider.
	 */
	public static double getZoomLevel(IConfigurationProvider configurationProvider) {
		// double zoom =
		// configurationProvider.getDiagramEditor().getZoomLevel();
		double zoom = 1.0;
		return zoom;
	}

	/**
	 * Returns a list containing all handles for a given shape.
	 * 
	 * @param owner
	 *            The shape, for which to create the handles.
	 * @param cp
	 *            The configuration provider, which can be used to access the
	 *            environment.
	 * @param supportedResizeDirections
	 *            The directions, in which the shape can be resized (see
	 *            {@link PositionConstants}).
	 * @param movable
	 *            true, if the shape can be moved.
	 * 
	 * @return A list containing all handles for a given shape.
	 */
	public static List<AbstractHandle> createShapeHandles(GraphicalEditPart owner, IConfigurationProviderInternal cp,
			int supportedResizeDirections, boolean movable, boolean resizeAllowed) {
		List<AbstractHandle> list = new ArrayList<AbstractHandle>();

		IShapeSelectionInfo si = null;
		IToolBehaviorProvider tbp = cp.getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		Object model = owner.getModel();
		if (model instanceof Shape) {
			si = tbp.getSelectionInfoForShape((Shape) model);
		} else if (model instanceof Anchor) {
			si = tbp.getSelectionInfoForAnchor((Anchor) model);
		}

		list.add(new GFSurroundingHandle(owner, cp, movable, si));

		if (resizeAllowed) {
			if ((PositionConstants.NORTH_EAST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.NORTH_EAST, supportedResizeDirections,
						movable, si));
			if ((PositionConstants.SOUTH_EAST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.SOUTH_EAST, supportedResizeDirections,
						movable, si));
			if ((PositionConstants.SOUTH_WEST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.SOUTH_WEST, supportedResizeDirections,
						movable, si));
			if ((PositionConstants.NORTH_WEST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.NORTH_WEST, supportedResizeDirections,
						movable, si));
			if ((PositionConstants.NORTH & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.NORTH, supportedResizeDirections, movable, si));
			if ((PositionConstants.EAST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.EAST, supportedResizeDirections, movable, si));
			if ((PositionConstants.SOUTH & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.SOUTH, supportedResizeDirections, movable, si));
			if ((PositionConstants.WEST & supportedResizeDirections) != 0)
				list.add(new GFCornerHandle(owner, cp, PositionConstants.WEST, supportedResizeDirections, movable, si));
		}

		return list;
	}
}
