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
 *    Patch 185019 from Bug 332360 contributed by Volker Wegert
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 16.11.2005
 */
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IResizeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;

/**
 * The Interface IResizeFeature.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IResizeFeature extends IFeature {

	/**
	 * Provides configuration object, which describes the resize behavior
	 * 
	 * @param context
	 *            the resizing context
	 * @return configuration object
	 * @deprecated Replaced by {@link #getResizeConfiguration(IResizeContext)}
	 */
	IResizeConfiguration getResizeConfiguration(IResizeShapeContext context);

	/**
	 * Provides configuration object, which describes the resize behavior
	 * 
	 * @param context
	 *            the resizing context
	 * @return configuration object
	 * @since 0.11 replaces IResizeConfiguration
	 *        getResizeConfiguration(IResizeShapeContext context)
	 */
	IResizeConfiguration getResizeConfiguration(IResizeContext context);
}
