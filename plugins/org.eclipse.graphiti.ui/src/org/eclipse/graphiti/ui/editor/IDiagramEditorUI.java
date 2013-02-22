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
 *    mwenz - Bug 331715: Support for rectangular grids in diagrams
 *    mwenz - Bug 332964: Enable setting selection for non-EMF domain models and
 *                        when embedded into a multi-page editor
 *    mwenz - Bug 336075 - DiagramEditor accepts URIEditorInput
 *    mwenz - Bug 329523 - Add notification of DiagramTypeProvider after saving a diagram
 *    jpasch - Bug 323025 ActionBarContributor cleanup
 *    mwenz - Bug 345347 - There should be a way to not allow other plugins to contribute to the diagram context menu
 *    mwenz - Bug 346932 - Navigation history broken
 *    mwenz - Bug 356828 - Escaped diagram name is used as editor title
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    Felix Velasco (mwenz) - Bug 323351 - Enable to suppress/reactivate the speed buttons
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    mwenz - Bug 324556 - Prevent invisible shapes to be selected to avoid IllegalArgumentException
 *    mwenz - Bug 372753 - save shouldn't (necessarily) flush the command stack
 *    mwenz - Bug 376008 - Iterating through navigation history causes exceptions
 *    Felix Velasco - mwenz - Bug 379788 - Memory leak in DefaultMarkerBehavior
 *    mwenz - Bug 387971 - Features cant't be invoked from contextMenu
 *    fvelasco - Bug 323349 - Enable external invocation of features
 *    mwenz - Bug 393113 - Auto-focus does not work for connections
 *    pjpaulin - Bug 352120 - Changed from a class to an interface - API BREAKAGE HERE
 *    pjpaulin - Bug 352120 - Renamed to IDiagramEditorUI so that DiagramEditor class can remain
 *
 * </copyright>
 *
 *******************************************************************************/

package org.eclipse.graphiti.ui.editor;

import java.util.EventObject;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * This is the main interface for the Graphiti diagram editor. It can be
 * implemented by any class that would like to display a Graphiti diagram.
 * 
 * A DiagramEditor takes in a {@link DiagramEditorInput} that points to the
 * diagram to display. This input is not technically an IEditorInput, as
 * diagrams may be displayed in non-editor parts.
 * 
 * @since 0.10
 */
public interface IDiagramEditorUI extends IDiagramEditor {

	/**
	 * The ID of the context as it is registed with the org.eclipse.ui.contexts
	 * extension point.
	 * 
	 * @since 0.10
	 */
	public static final String DIAGRAM_CONTEXT_ID = "org.eclipse.graphiti.ui.diagramEditor"; //$NON-NLS-1$

	/**
	 * Returns the GEF edit domain as needed for some of the feature
	 * functionality in Graphiti; simply a public rewrite of the GEF editor
	 * super method.
	 * 
	 * @return the {@link DefaultEditDomain} used in this editor
	 * @see GraphicalEditor#getEditDomain()
	 * 
	 * @since 0.9
	 */
	DefaultEditDomain getEditDomain();

	void setGEFEditDomain(DefaultEditDomain editDomain);

	/**
	 * Returns the GEF {@link GraphicalViewer} as it is needed in some Graphiti
	 * feature implementations. This is simply a public rewrite of the according
	 * super method.
	 * 
	 * @return the {@link GraphicalViewer} used within this editor instance
	 * @see GraphicalEditor#getGraphicalViewer()
	 */
	GraphicalViewer getGraphicalViewer();

	/**
	 * Updates the UI to correctly reflect the dirty state of the editor. The
	 * default implementation does this by firing a
	 * {@link IEditorPart#PROP_DIRTY} property change.
	 * 
	 * @since 0.9
	 */
	void updateDirtyState();

	IConfigurationProvider getConfigurationProvider();

	/**
	 * Returns the contents {@link EditPart} of this Editor. This is the topmost
	 * EditPart in the {@link GraphicalViewer}.
	 * 
	 * @return The contents {@link EditPart} of this Editor.
	 * @since 0.9
	 */
	EditPart getContentEditPart();

	/**
	 * Method to retrieve the GEF {@link EditPart} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the GEF
	 *            representation for
	 * @return the GEF {@link GraphicalEditPart} that represents the given
	 *         {@link PictogramElement}.
	 * @since 0.9
	 */
	GraphicalEditPart getEditPartForPictogramElement(PictogramElement pe);

	/**
	 * Gets the current mouse location as a {@link Point}.
	 * 
	 * @return the mouse location
	 * @since 0.9
	 */
	Point getMouseLocation();

	/**
	 * @return the {@link IWorkbenchPart} that is displaying the diagram.
	 */
	IWorkbenchPart getWorkbenchPart();

	/**
	 * @return the site for the {@link IWorkbenchPart} that is displaying the
	 *         diagram.
	 * @since 0.10
	 */
	IWorkbenchPartSite getSite();

	/**
	 * @return the title for the {@link IWorkbenchPart} that is displaying the
	 *         diagram.
	 * @since 0.10
	 */
	String getTitle();

	/**
	 * Adds a listener for changes to properties of this workbench part. Has no
	 * effect if an identical listener is already registered.
	 * <p>
	 * The property ids are defined in {@link IWorkbenchPartConstants}.
	 * </p>
	 * 
	 * @param listener
	 *            a property listener
	 * @since 0.10
	 */
	public void addPropertyListener(IPropertyListener listener);

	/**
	 * Removes the given property listener from this workbench part. Has no
	 * effect if an identical listener is not registered.
	 * 
	 * @param listener
	 *            a property listener
	 * @since 0.10
	 */
	public void removePropertyListener(IPropertyListener listener);

	/**
	 * Returns the instance of the update behavior that is used with this
	 * editor. To change the behavior override {@link #createUpdateBehavior()}.
	 * 
	 * @return the used instance of the update behavior, by default a
	 *         {@link DefaultUpdateBehavior}.
	 * @since 0.9
	 */
	DefaultUpdateBehavior getUpdateBehavior();

	/**
	 * @return the input containing the model for the diagram
	 */
	IDiagramEditorInput getDiagramEditorInput();

	/**
	 * Notify the container that it should shut down or clear it's state.
	 */
	void shutdown();

	/**
	 * Calculates the mouse location depending on scrollbars and zoom factor.
	 * 
	 * @param nativeLocation
	 *            the native location given as {@link Point}
	 * @return the {@link Point} of the real mouse location
	 * @since 0.9
	 */
	Point calculateRealMouseLocation(Point nativeLocation);

	/**
	 * Returns the instance of the refresh behavior that is used with this
	 * editor. To change the behavior override {@link #createRefreshBehavior()}.
	 * 
	 * @return the used instance of the refresh behavior, by default a
	 *         {@link DefaultRefreshBehavior}.
	 * @since 0.9
	 */
	DefaultRefreshBehavior getRefreshBehavior();

	/**
	 * Returns if direct editing is currently active for this editor.
	 * 
	 * @return <code>true</code> in case direct editing is currently active
	 *         within this editor, <code>false</code> otherwise.
	 * 
	 * @since 0.9
	 */
	boolean isDirectEditingActive();

	/**
	 * Sets that direct editing is now active in the editor or not. Note that
	 * this flag set to <code>true</code> does not actually start direct editing
	 * it is simply an indication that prevents certain operations from running
	 * (e.g. refresh)
	 * 
	 * @param directEditingActive
	 *            <code>true</code> to set the flag to direct editing currently
	 *            active, <code>false</code> otherwise.
	 * 
	 * @since 0.9
	 */
	void setDirectEditingActive(boolean directEditingActive);

	/**
	 * Returns the zoom level currently used in the editor.
	 * 
	 * @return the zoom level
	 * @since 0.9
	 */
	double getZoomLevel();

	/**
	 * Method to retrieve the Draw2D {@link IFigure} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the Draw2D
	 *            representation for
	 * @return the Draw2D {@link IFigure} that represents the given
	 *         {@link PictogramElement}.
	 * 
	 * @since 0.9
	 */
	IFigure getFigureForPictogramElement(PictogramElement pe);

	/**
	 * Public accessor that should redirect to protected method.
	 */
	void initializeGEFGraphicalViewer();

	ActionRegistry getActionRegistry();

	@SuppressWarnings("rawtypes")
	List getSelectionActions();

	SelectionSynchronizer getSelectionSynchronizer();

	void commandStackChanged(EventObject event);

	void setGraphicalViewer(GraphicalViewer viewer);

	void hookGraphicalViewer();
}
