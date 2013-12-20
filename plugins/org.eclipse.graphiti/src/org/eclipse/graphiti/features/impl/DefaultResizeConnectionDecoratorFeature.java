/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation (Bug 424458)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.DefaultResizeConfiguration;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.IResizeConnectionDecoratorFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IResizeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;

/**
 * This class implements the default behavior for connection decorator resizing:
 * it disables it.
 * 
 * @since 0.11
 */
public class DefaultResizeConnectionDecoratorFeature extends AbstractFeature implements
		IResizeConnectionDecoratorFeature {

	/**
	 * Creates a new {@link DefaultResizeConnectionDecoratorFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultResizeConnectionDecoratorFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Generic feature method that checks if the feature can be executed. In
	 * case of context being a {@link IResizeConnectionDecoratorContext} it will
	 * delegate to
	 * {@link #canResizeConnectionDecorator(IResizeConnectionDecoratorContext)}.
	 * 
	 * @param context
	 *            The generic feature context instance
	 */
	@Override
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IResizeConnectionDecoratorContext) {
			ret = canResizeConnectionDecorator((IResizeConnectionDecoratorContext) context);
		}
		return ret;
	}

	/**
	 * Implements the actual check if a connection decorator can be resized or
	 * not. The default implementation will always return <code>false</code>.
	 * 
	 * @param context
	 *            The context instance identifying the connection decorator.
	 * @return <code>true</code> in case the decorator can be resized,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean canResizeConnectionDecorator(IResizeConnectionDecoratorContext context) {
		// By default resize is not allowed for connection decorators
		return false;
	}

	/**
	 * Generic feature method that executes the feature. In case of context
	 * being a {@link IResizeConnectionDecoratorContext} it will check the
	 * context if execution is allowed by calling
	 * {@link IResizeConnectionDecoratorContext#isExecuteAllowed()} and in case
	 * this call returns <code>true</code>, it will delegate to
	 * {@link #resizeConnectionDecorator(IResizeConnectionDecoratorContext)}.
	 * 
	 * @param context
	 *            The generic feature context instance
	 */
	@Override
	public void execute(IContext context) {
		if (context instanceof IResizeConnectionDecoratorContext) {
			IResizeConnectionDecoratorContext resizeConnectionDecoratorContext = (IResizeConnectionDecoratorContext) context;
			if (resizeConnectionDecoratorContext.isExecuteAllowed()) {
				resizeConnectionDecorator(resizeConnectionDecoratorContext);
			}
		}
	}

	/**
	 * Implements the actual resize for the connection decorator. This default
	 * implementation will simply do nothing.
	 * 
	 * @param context
	 *            The context instance identifying the connection decorator.
	 */
	@Override
	public void resizeConnectionDecorator(IResizeConnectionDecoratorContext context) {
	}

	@Override
	public IResizeConfiguration getResizeConfiguration(IResizeContext context) {
		return new DefaultResizeConfiguration();
	}

	/**
	 * @deprecated replaced by {@link #getResizeConfiguration(IResizeContext)}
	 */
	@Override
	public IResizeConfiguration getResizeConfiguration(IResizeShapeContext context) {
		return new DefaultResizeConfiguration();
	}
}
