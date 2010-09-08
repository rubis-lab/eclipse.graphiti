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
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * The Interface IToolBehaviorProvider.
 * 
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultToolBehaviorProvider} instead
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IToolBehaviorProvider {

	/**
	 * Returns the context button pad data for the given pictogram element
	 * context. The context button pad data defines, which context buttons to
	 * show for a given pictogram element. Can return null, if no there is no
	 * context button pad for the given pictogram element.
	 * 
	 * @param context
	 *            The pictogram element context, for which to return the context
	 *            button pad data.
	 * @return The context button pad data for the given pictogram element
	 *         context.
	 */
	IContextButtonPadData getContextButtonPad(IPictogramElementContext context);

	/**
	 * Gets the context menu.
	 * 
	 * @param context
	 *            the context
	 * @return the context menu
	 */
	IContextMenuEntry[] getContextMenu(ICustomContext context);

	/**
	 * Gets the palette compartments.
	 * 
	 * @return the palette compartments
	 */
	IPaletteCompartmentEntry[] getPalette();

	/**
	 * Checks if is palette applicable.
	 * 
	 * @param feature
	 *            the feature
	 * @return true, if checks if is palette applicable
	 */
	boolean isPaletteApplicable(IFeature feature);

	/**
	 * Decides about the feature to execute if a double click is triggered. We
	 * decided to return a custom feature, because custom features appear in the
	 * context menu an the double click feature should also appear in the
	 * context menu (usual UI guideline).
	 * 
	 * @param context
	 *            contains information where the double click gesture has
	 *            happened
	 * @return the feature to execute
	 */
	ICustomFeature getDoubleClickBehavior(IDoubleClickContext context);

	/**
	 * Gets the decorators.
	 * 
	 * @param pe
	 *            the pictogram element
	 * @return the decorators
	 */
	IDecorator[] getDecorators(PictogramElement pe);

	/**
	 * Gets the adapter.
	 * 
	 * @param type
	 *            the type
	 * @return the adapter
	 */
	Object getAdapter(Class<?> type);

	/**
	 * Override this method if you want to change the default selection on mouse
	 * click.
	 * 
	 * @param originalPe
	 *            the original pictogram element
	 * @param oldSelection
	 *            the old selection
	 * @return the active PE to be selected instead; return null if there should
	 *         not be a special selection behavior; if there should not be a
	 *         selection return the diagram
	 */
	PictogramElement getCustomSelection(PictogramElement originalPe, PictogramElement[] oldSelection);

	/**
	 * Override this method if you want to change the default scrolling behavior
	 * of the diagram. The default is DiagramScrollingBehavior.GEF_DEFAULT: the
	 * empty diagram comes up without scroll bars. The scroll bars start to
	 * appear when objects are moved to the outside of the currently visible
	 * area. For permanently visible scroll bars, return the value
	 * DiagramScrollingBehavior.SCROLLBARS_PERMANENTLY_VISIBLE.
	 * 
	 * @return The DiagramScrollingBehavior
	 * @deprecated Scroll bar based infinite canvas is a workaround for GEF
	 *             limitations.
	 * 
	 * @see DefaultToolBehaviorProvider#getDiagramScrollingBehavior()
	 */
	@Deprecated
	DiagramScrollingBehavior getDiagramScrollingBehavior();

	/**
	 * Gets the zoom levels.
	 * 
	 * @return the zoom levels
	 */
	double[] getZoomLevels();

	/**
	 * Dispose.
	 */
	void dispose();

	/**
	 * Return the selection info for the given shape.
	 * 
	 * @param shape
	 *            the shape
	 * @return the shape selection info
	 */
	ISelectionInfo getSelectionInfoForShape(Shape shape);

	/**
	 * Return the selection info for the given connection.
	 * 
	 * @param connection
	 *            the connection
	 * @return the connection selection info
	 */
	ISelectionInfo getSelectionInfoForConnection(Connection connection);

	/**
	 * Returns the location info which will be used for direct editing if the
	 * framework cannot decide this. E.g. a shape is selected and the user
	 * presses F2 but the mouse is outside the shape.
	 * 
	 * @param pe
	 *            the active and selected pictogram element
	 * @param currentLocationInfo
	 *            the current location info determined by the framework
	 * @return the location info for the given selected pictogram element
	 */
	ILocationInfo getLocationInfo(PictogramElement pe, ILocationInfo currentLocationInfo);

	/**
	 * Called before execution on stack.
	 * 
	 * @param executionInfo
	 *            info about content to be executed
	 */
	void preExecute(IExecutionInfo executionInfo);

	/**
	 * Called after execution on stack.
	 * 
	 * @param executionInfo
	 *            info about content to be executed
	 */
	void postExecute(IExecutionInfo executionInfo);

	/**
	 * Returns the tooltip to be attached to the graphics algorithm.
	 * 
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 * @return the tooltip
	 */
	String getToolTip(GraphicsAlgorithm graphicsAlgorithm);

	/**
	 * The returned graphics algorithm defines the selection border and the
	 * rectangle where the context buttons appear at.
	 * 
	 * @param pe
	 *            the pictogram element
	 * @return the graphics algorithm which defines the selection border
	 */
	GraphicsAlgorithm getSelectionBorder(PictogramElement pe);

	/**
	 * The returned graphics algorithm defines the technical container for
	 * active children. Currently implementers have to secure to deliver a
	 * (Rounded)Rectangle. Does not make sense for other types of graphics
	 * algorithm.
	 * 
	 * @param cs
	 *            the container shape
	 * @return the graphics algorithm acting as technical container
	 */
	GraphicsAlgorithm getContentArea(ContainerShape cs);

	/**
	 * The returned graphics algorithm's define the area where the user can
	 * click to select the shape.
	 * 
	 * @param pe
	 *            the pictogram element
	 * @return the click area
	 */
	GraphicsAlgorithm[] getClickArea(PictogramElement pe);

	/**
	 * Provides the GA that defines the outline for the given pictogram
	 * element's chopbox Anchor.
	 * 
	 * @param pe
	 *            the given pictogram element
	 * @return the GA that defines the outline for connections from or to the
	 *         shape's chopbox anchor
	 */
	GraphicsAlgorithm getChopboxAnchorArea(PictogramElement pe);

	/**
	 * Returns a tooltip for the workbench titlebar for the given diagram
	 * 
	 * @return a tooltip or <code>null</code> to indicate that the default from
	 *         the graphics framework will be used
	 */
	String getTitleToolTip();

	/**
	 * Indicates whether guides will be shown or not.
	 * 
	 * @return TRUE, if guides should be shown
	 */
	boolean isShowGuides();

	/**
	 * Indicates if the selection of multiple elements is enabled.
	 * 
	 * @return FALSE, if single selection is enforced
	 */
	boolean isMultiSelectionEnabled();

	/**
	 * Indicates if the selection of connections is enabled.
	 * 
	 * @return TRUE, if selection of connections is enabled
	 */
	boolean isConnectionSelectionEnabled();

	/**
	 * Returns the contributor ID for the tabbed property sheet page.
	 * 
	 * @return the contributor ID for the tabbed property sheet page.
	 */
	public String getContributorId();

}
