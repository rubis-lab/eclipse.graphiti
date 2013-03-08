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
 *    pjpaulin - Bug 352120 - Renamed to IDiagramContainerUI so that DiagramEditor class can remain
 *
 * </copyright>
 *
 *******************************************************************************/

package org.eclipse.graphiti.ui.editor;

import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * This is the main UI interface for the Graphiti diagram containers. It can be
 * implemented by any class that would like to display a Graphiti diagram.
 * 
 * A DiagramContainer takes in a {@link DiagramEditorInput} that points to the
 * diagram to display. This input is not technically an IEditorInput, as
 * diagrams may be displayed in non-editor parts.
 * 
 * @since 0.10
 */
public interface IDiagramContainerUI extends IDiagramContainer, IAdaptable {

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
	 */
	public DefaultEditDomain getEditDomain();

	/**
	 * Sets the GEF edit domain to the container. Needed for initializing the
	 * container from the {@link DiagramBehavior} instance.
	 * 
	 * @param editDomain
	 *            The {@link DefaultEditDomain} to set
	 * @see GraphicalEditor#setEditDomain()
	 */
	public void setEditDomain(DefaultEditDomain editDomain);

	/**
	 * Returns the GEF {@link GraphicalViewer} as it is needed in some Graphiti
	 * feature implementations. This is simply a public rewrite of the according
	 * super method.
	 * 
	 * @return the {@link GraphicalViewer} used within this editor instance
	 * @see GraphicalEditor#getGraphicalViewer()
	 */
	public GraphicalViewer getGraphicalViewer();

	/**
	 * Returns the instance of the Eclipse {@link IWorkbenchPart} that displays
	 * this container. E.g. for an editor this will be the editor itself.
	 * 
	 * @return The {@link IWorkbenchPart} that is displaying the diagram.
	 */
	public IWorkbenchPart getWorkbenchPart();

	/**
	 * Returns the {@link IWorkbenchPartSite} of the Eclipse
	 * {@link IWorkbenchPart} that displays this container. E.g. for an editor
	 * this will be the editor site.
	 * 
	 * @return The site for the {@link IWorkbenchPart} that is displaying the
	 *         diagram.
	 */
	public IWorkbenchPartSite getSite();

	/**
	 * Returns the {@link IDiagramEditorInput} instance used for this container.
	 * Basically it is used as an Eclipse {@link IEditorInput} object only in
	 * case the container is an editor; for other types of containers the input
	 * is simply used as a holder for a URI pointing to a diagram.
	 * 
	 * @return The input containing the URI for the diagram
	 */
	public IDiagramEditorInput getDiagramEditorInput();

	/**
	 * Returns the GEF action registry for the container.
	 * 
	 * @return The {@link ActionRegistry}
	 */
	public ActionRegistry getActionRegistry();

	/**
	 * Returns the actions used for selection of the parent GEF editor, for an
	 * editor based upon the GEF editor this simply returns the standard GEF
	 * selection actions by delegating to the super editor class.
	 * 
	 * @return A {@link List} containing the selection actions
	 * @see GraphicalEditor#getSelectionActions()
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectionActions();

	/**
	 * Notification that the command stack changed. This might e.g. trigger an
	 * update of the dirty state of the container.
	 * 
	 * @param event
	 *            An event instance describing what happened
	 * @see GraphicalEditor#commandStackChanged(EventObject event)
	 */
	public void commandStackChanged(EventObject event);

	/**
	 * Sets the {@link GraphicalViewer} to be used inside the container. The
	 * viewer is created by the {@link DiagramBehavior} instance and needs to be
	 * set in the GEF container.
	 * 
	 * @param viewer
	 *            The viewer to use.
	 * @see GraphicalEditor#setGraphicalViewer(GraphicalViewer viewer)
	 */
	public void setGraphicalViewer(GraphicalViewer viewer);

	/**
	 * Hooks the {@link GraphicalViewer} to be used inside the container.
	 * 
	 * @param viewer
	 *            The viewer to use.
	 * @see GraphicalEditor#hookGraphicalViewer(GraphicalViewer viewer)
	 */
	public void hookGraphicalViewer();

	/**
	 * Returns the {@link DiagramBehavior} instance associated with this
	 * container.
	 * 
	 * @return The associated {@link DiagramBehavior} instance
	 */
	public DiagramBehavior getDiagramBehavior();
}
