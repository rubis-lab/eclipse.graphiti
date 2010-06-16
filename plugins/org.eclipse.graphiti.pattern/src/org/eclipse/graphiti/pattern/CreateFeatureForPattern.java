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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

/**
 * The Class CreateFeatureForPattern.
 */
public class CreateFeatureForPattern extends AbstractCreateFeature {
	private IFeatureForPattern delegate;

	/**
	 * Instantiates a new creates the feature for pattern.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public CreateFeatureForPattern(IFeatureProvider featureProvider, IPattern pattern) {
		super(featureProvider, pattern.getCreateName(), pattern.getCreateDescription());
		delegate = new FeatureForPatternDelegate(pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreate#canCreate(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	public boolean canCreate(ICreateContext context) {
		IPattern pattern = delegate.getPattern();
		boolean ret = /* pattern.isContextApplicable(context) && */pattern.canCreate(context);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreate#create(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	public Object[] create(ICreateContext context) {
		return delegate.getPattern().create(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractCreateFeature#getCreateImageId()
	 */
	@Override
	public String getCreateImageId() {
		return delegate.getPattern().getCreateImageId();
	}

	@Override
	public String getCreateLargeImageId() {
		return delegate.getPattern().getCreateLargeImageId();
	}

	/**
	 * Gets the pattern.
	 * 
	 * @return the pattern
	 */
	public IPattern getPattern() {
		return delegate.getPattern();
	}

}
