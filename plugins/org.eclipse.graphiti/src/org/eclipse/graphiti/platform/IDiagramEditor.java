/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    pjpaulin - Bug 352120 - Added non-GEF methods required for a diagram container.
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.platform;

import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IDiagramEditor.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           DiagramEditor instead.
 */
public interface IDiagramEditor {

	/**
	 * Select all the given pictogram elements in the editor.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	void selectPictogramElements(PictogramElement[] pictogramElements);

	/**
	 * Get all pictogram elements currently selected.
	 * 
	 * @return all selected pictogram elements
	 */
	PictogramElement[] getSelectedPictogramElements();

	/**
	 * Sets the pictogram element which should be selected after the editor
	 * refresh.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 */
	void setPictogramElementForSelection(PictogramElement pictogramElement);

	/**
	 * Sets the pictogram elements which should be selected after the editor
	 * refresh.
	 * 
	 * @param pictogramElements
	 *            the pictogram element
	 */
	void setPictogramElementsForSelection(PictogramElement[] pictogramElements);

	/**
	 * Gets the transactional editing domain.
	 * 
	 * @return transactional editing domain which is linked to the editor
	 */
	TransactionalEditingDomain getEditingDomain();

	/**
	 * Gets the resource set.
	 * 
	 * @return resource set
	 */
	ResourceSet getResourceSet();

	/**
	 * Gets the diagram type provider.
	 * 
	 * @return the diagram type provider
	 */
	IDiagramTypeProvider getDiagramTypeProvider();

	/**
	 * Refresh.
	 */
	void refresh();

	/**
	 * Checks if is dirty.
	 * 
	 * @return true, if editor is dirty
	 */
	boolean isDirty();

	/**
	 * Refreshes the title text of this part.
	 * 
	 * @since 0.9
	 */
	void refreshTitle();

	/**
	 * Refreshes the title tool tip text of this part.
	 */
	void refreshTitleToolTip();

	/**
	 * Refreshes all rendering decorators for the given pictogram element. That
	 * means: 1. delete current decorators 2. ask the toolbehaviour provider for
	 * decorator data 3. create new decorators with this data and render this
	 * new decorators
	 * 
	 * @param pe
	 *            pictogram element
	 */
	void refreshRenderingDecorators(PictogramElement pe);

	/**
	 * Refreshes the editor's palette.
	 */
	void refreshPalette();
	
	/**
	 * Executes the given feature in the given context using the command stack
	 * and editing domain of the diagram editor. In case of an IAddFeature being
	 * passed this method will also trigger the selection of the newly added
	 * shape.
	 * 
	 * @param feature
	 *            The feature to execute
	 * @param context
	 *            The context object to use with the feature
	 * @return an object representing the result of the feature call (depends on
	 *         the concrete implementation)
	 * 
	 * @since 0.9
	 */
	Object executeFeature(IFeature feature, IContext context);

	/* Methods added to support DiagramEditor type */

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
	 * @since 0.10
	 */
	void disableAdapters();

	/**
	 * Should be called by the various behavior instances after mass EMF
	 * resource operations have been triggered (e.g. saving all resources). Can
	 * be used to re-enable eventing after it was disabled for performance
	 * reasons. See {@link #disableAdapters()} as well.<br>
	 * Must be called after {@link #disableAdapters()} has been called and the
	 * operation has finshed (best in a finally clause to also enable the
	 * exception case), otherwise strange errors may occur within the editor.
	 * 
	 * @since 0.10
	 */
	void enableAdapters();

	/**
	 * When the command stack changes, the actions interested in the command
	 * stack are updated.
	 * 
	 * @param event
	 *            the change event
	 * @since 0.10
	 */
	void commandStackChanged(EventObject event);

	/**
	 * Checks if this editor is alive.
	 * 
	 * @return <code>true</code>, if editor contains a model connector and a
	 *         valid Diagram, <code>false</code> otherwise.
	 * @since 0.10
	 */
	boolean isAlive();

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
	 * can be stored for a selection operation that is triggered lateron during
	 * a general refresh via the method
	 * {@link #selectBufferedPictogramElements()}. This mechanism is used e.g.
	 * in the Graphiti framework in direct editing to restore the previous
	 * selection, but can also be used by clients.
	 * 
	 * @since 0.10
	 */
	void selectBufferedPictogramElements();

	/**
	 * Refreshes the content of the editor (what's shown inside the diagram
	 * itself).
	 * 
	 * @since 0.10
	 */
	void refreshContent();

	/**
	 * Hook that is called by the holder of the
	 * {@link TransactionalEditingDomain} ({@link DefaultUpdateBehavior} or a
	 * subclass of it) after the editing domain has been initialized. Can be
	 * used to e.g. register additional listeners on the domain.<br>
	 * The default implementation notifies the marker behavior extension to
	 * register its listeners.
	 * 
	 * @since 0.10
	 */
	void editingDomainInitialized();

	/**
	 * @since 0.10
	 */
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type);

	/**
	 * Return whether the editing domain is local to the diagram or whether the
	 * diagram is operating as part of a larger editing domain.
	 * 
	 * @return <code>true</code>, if the diagram has created it's own editing
	 *         domain, <code>false</code> otherwise.
	 * @since 0.10
	 */
	boolean isLocalEditingDomain();

	/**
	 * Request that the diagram model be persisted.
	 * 
	 * @since 0.10
	 */
	void doSave(IProgressMonitor monitor);
}
