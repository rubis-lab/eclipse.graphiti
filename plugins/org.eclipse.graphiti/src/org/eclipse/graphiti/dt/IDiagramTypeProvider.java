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
 *    mwenz - Bug 329523 - Add notification of DiagramTypeProvider after saving a diagram
 *    mwenz - Bug 352109 - Enable auto-update option for saved editor
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.dt;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.notification.INotificationService;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.platform.IExtension;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Interface IDiagramTypeProvider is the central interface from the Graphiti
 * framework to the diagram type agent. Do not implement this class directly.
 * Extend appropriate classes instead. This is the first revision of the diagram
 * type interface.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * 
 *           Extend {@link AbstractDiagramTypeProvider} instead
 */
public interface IDiagramTypeProvider extends IExtension, IFeatureProviderHolder {

	/**
	 * Implement this method to initialize the diagram type provider.
	 * 
	 * @param diagram
	 *            the currently opened diagram
	 * @param diagramEditor
	 *            TODO
	 */
	void init(Diagram diagram, IDiagramEditor diagramEditor);

	/**
	 * Returns the diagram.
	 * 
	 * @return the currently opened diagram
	 */
	Diagram getDiagram();

	/**
	 * Returns the notification service.
	 * 
	 * @return the notification service
	 */
	INotificationService getNotificationService();

	/**
	 * Get the list of available tool behaviour providers.
	 * 
	 * @return the list of tool behaviour providers
	 * @see IToolBehaviorProvider
	 */
	IToolBehaviorProvider[] getAvailableToolBehaviorProviders();

	/**
	 * Gets the current tool behaviour provider.
	 * 
	 * @return the currently active tool behaviour provider
	 * @see IToolBehaviorProvider
	 */
	IToolBehaviorProvider getCurrentToolBehaviorProvider();

	/**
	 * Returns the diagram title.
	 * 
	 * @return the diagram-title, e.g. this will be used for the title-bar of
	 *         the editor
	 */
	String getDiagramTitle();

	/**
	 * Returns the editor's update behaviour at startup.
	 * 
	 * @return true if diagram should be updated (if needed) immediately after
	 *         open in editor - editor will be dirty then; false if diagram
	 *         should not be updated - editor not dirty but eventually red at
	 *         out of date areas
	 */
	boolean isAutoUpdateAtStartup();

	/**
	 * Returns the editor's update behaviour. This flag controls if a diagram
	 * editor will update its contents (call the {@link AbstractUpdateFeature
	 * update feature} of the {@link PictogramElement}s changes are indicated
	 * for.<br>
	 * Note that the update will only be triggered in case the editor is already
	 * dirty, see {@link #isAutoUpdateAtRuntimeWhenEditorIsSaved()}.
	 * 
	 * @return true if diagram should be updated automatically (only if editor
	 *         is already dirty)
	 */
	boolean isAutoUpdateAtRuntime();

	/**
	 * Returns the editor's update behaviour when the editor is saved. This
	 * method is only called when {@link #isAutoUpdateAtRuntime()} returns
	 * <code>true</code> and the editor is not dirty. In case this method
	 * returns <code>true</code> the editor will do an update; this will usually
	 * cause the editor to get dirty.
	 * 
	 * @return true if diagram should be updated automatically (only if editor
	 *         is already dirty)
	 * @since 0.9
	 */
	boolean isAutoUpdateAtRuntimeWhenEditorIsSaved();

	/**
	 * Returns the editor's update behaviour on reset.
	 * 
	 * @return true if diagram should be updated automatically if editor is
	 *         already dirty and the user chooses to discard his changes (reset
	 *         of the diagram) when a change from outside of the editor happens.
	 */
	boolean isAutoUpdateAtReset();

	/**
	 * Returns the current diagram editor.
	 * 
	 * @return current diagram editor
	 */
	IDiagramEditor getDiagramEditor();

	/**
	 * Dispose.
	 */
	void dispose();

	/**
	 * Gets the related business objects.
	 * 
	 * @param bos
	 *            the business objects
	 * @return the related business objects
	 */
	Object[] getRelatedBusinessObjects(Object[] bos);

	/**
	 * Gets the graphics algorithm renderer factory.
	 * 
	 * @return the graphics algorithm renderer factory
	 */
	IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory();

	/**
	 * This method will be called after this diagram type provider has been
	 * completely initialised. The state of the using diagram editor can not be
	 * predicted.
	 */
	void postInit();


	/**
	 * @since 0.9
	 */
	int getCurrentToolBehaviorIndex();


	void setCurrentToolBehaviorIndex(int index);

	/**
	 * This method will be called if the underlying resource which contains the
	 * diagram has been reloaded.
	 * 
	 * @param diagram
	 */
	void resourceReloaded(Diagram diagram);

	/**
	 * This method will be called by the DiagramEditor when a diagram has been
	 * saved.
	 * 
	 * @param diagram
	 *            The diagram for which the editor has been saved
	 * @param savedResources
	 *            The resources that have been saved
	 */
	void resourcesSaved(Diagram diagram, Resource[] savedResources);

	/**
	 * Gets the context id.
	 * 
	 * @return the context id
	 * @since 0.10
	 */
	String getContextId();

	/**
	 * Sets the context id.
	 * 
	 * @param contextId
	 *            the new context id
	 * @since 0.10
	 */
	void setContextId(String contextId);

}