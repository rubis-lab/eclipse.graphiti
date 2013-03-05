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
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.platform;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramSupport;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This interface is the wrapping container around the providers and Eclipse
 * parts involved with an editor. So by having access to this interface, it is
 * possible to retrieve every information which might be necessary to build on
 * this framework.
 * <p>
 * All providers and factories have access to the IConfigurationProvider, to
 * which they belong (backward-pointer). This is necessary, because the
 * providers/factories are sometimes dependent on each other. However, this
 * prohibits the usage of the same instance of a provider/factory in different
 * IConfigurationProviders.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 * @since 0.10
 */
public interface IConfigurationProvider extends IFeatureProviderHolder {

	/**
	 * Disposes this object and frees all resources. This object will be
	 * unusable afterwards.
	 */
	public void dispose();

	/**
	 * Returns true, if this object is already disposed.
	 * 
	 * @return true, if this object is already disposed.
	 */
	public boolean isDisposed();

	/**
	 * Returns the Diagram-Model to display.
	 * 
	 * @return The Diagram-Model to display.
	 */
	public Diagram getDiagram();

	/**
	 * Sets the workbench-part of this configuration-provider. This method
	 * should be called, when a workbench-part is available for the
	 * configuration-provider. After setting the workbench-part it must not be
	 * changed again.
	 * <p>
	 * However, it must not be assumed, that this method is ever called. So the
	 * workbench-part might always be null. It is just an offer, to support
	 * possible further functionality.
	 * 
	 * @param workbenchPart
	 *            The workbench-part to set.
	 */
	public void setWorkbenchPart(IWorkbenchPart workbenchPart);

	/**
	 * Returns the workbench-part of this configuration-provider. Can return
	 * null.
	 * 
	 * @return The workbench-part of this configuration-provider. Can return
	 *         null.
	 */
	public IWorkbenchPart getWorkbenchPart();

	/**
	 * Returns the diagram type provider.
	 * 
	 * @return diagram type provider
	 */
	public IDiagramTypeProvider getDiagramTypeProvider();

	/**
	 * Gets the diagram editor.
	 * 
	 * @return the editor which is connected with this configuration-provider
	 * @deprecated Use {@link #getDiagramContainer()} instead
	 */
	public IDiagramContainerUI getDiagramEditor();

	/**
	 * Gets the diagram container.
	 * 
	 * @return the container which is connected with this configuration-provider
	 */
	public IDiagramContainerUI getDiagramContainer();

	public DiagramSupport getDiagramSupport();
}
