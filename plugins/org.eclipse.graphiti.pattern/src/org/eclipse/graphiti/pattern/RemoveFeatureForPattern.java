/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011 Volker Wegert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Volker Wegert - initial API, implementation and documentation:
 *                    Bug 336828: patterns should support delete,
 *                    remove, direct editing and conditional palette
 *                    creation entry
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;

/**
 * This class is used by the {@link DefaultFeatureProviderWithPatterns} to wrap
 * the removal behavior provided by an {@link IPattern} into an
 * {@link IRemoveFeature}.
 * 
 * @since 0.8.0
 */
public class RemoveFeatureForPattern extends DefaultRemoveFeature {

	private IFeatureForPattern delegate;

	/**
	 * Creates a new {@link RemoveFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public RemoveFeatureForPattern(IFeatureProvider featureProvider, IPattern pattern) {
		super(featureProvider);
		delegate = new FeatureForPatternDelegate(pattern);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return delegate.getPattern().canRemove(context);
	}

	@Override
	public void preRemove(IRemoveContext context) {
		delegate.getPattern().preRemove(context);
	}

	// public void remove(IRemoveContext context)
	// is final and cannot be overridden...	

	@Override
	public void postRemove(IRemoveContext context) {
		delegate.getPattern().postRemove(context);
	}

}
