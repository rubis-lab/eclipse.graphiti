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
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.config;

import org.eclipse.graphiti.ui.internal.IResourceRegistryHolder;
import org.eclipse.graphiti.ui.internal.contextbuttons.IContextButtonManager;
import org.eclipse.graphiti.ui.internal.policy.IEditPolicyFactory;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IConfigurationProviderInternal extends IConfigurationProvider, IResourceRegistryHolder {

	/**
	 * Returns the IEditPartFactory of this Model.
	 * 
	 * @return The IEditPartFactory of this Model.
	 */
	public IEditPartFactory getEditPartFactory();

	/**
	 * Returns the IEditPolicyFactory of this Model.
	 * 
	 * @return The IEditPolicyFactory of this Model.
	 */
	public IEditPolicyFactory getEditPolicyFactory();

	public abstract void setContextButtonManager(IContextButtonManager contextButtonManager);

	public abstract IContextButtonManager getContextButtonManager();
}