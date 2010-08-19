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
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.func.IDirectEditing;

/**
 * The Class DirectEditingFeatureForPattern.
 */
public class DirectEditingFeatureForPattern extends AbstractDirectEditingFeature {
	private IDirectEditing delegate;

	/**
	 * Creates a new {@link DirectEditingFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public DirectEditingFeatureForPattern(IFeatureProvider featureProvider, IDirectEditing pattern) {
		super(featureProvider);
		delegate = pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#canDirectEdit(org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		return delegate.canDirectEdit(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#checkValueValid(java.lang.String,
	 *      org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		return delegate.checkValueValid(value, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#completeValue(java.lang.String, int, java.lang.String,
	 *      org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String completeValue(String value, int caretPos, String choosenValue, IDirectEditingContext context) {
		return delegate.completeValue(value, caretPos, choosenValue, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#getPossibleValues(org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String[] getPossibleValues(IDirectEditingContext context) {
		return delegate.getPossibleValues(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#getValueProposals(java.lang.String, int,
	 *      org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String[] getValueProposals(String value, int caretPos, IDirectEditingContext context) {
		return delegate.getValueProposals(value, caretPos, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#isAutoCompletionEnabled()
	 */
	@Override
	public boolean isAutoCompletionEnabled() {
		return delegate.isAutoCompletionEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#isCompletionAvailable()
	 */
	@Override
	public boolean isCompletionAvailable() {
		return delegate.isCompletionAvailable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#stretchTextfieldToFitText()
	 */
	@Override
	public boolean stretchTextfieldToFitText() {
		return delegate.stretchTextfieldToFitText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#getEditingType()
	 */
	public int getEditingType() {
		return delegate.getEditingType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#getInitialValue(org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String getInitialValue(IDirectEditingContext context) {
		return delegate.getInitialValue(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#setValue(java.lang.String, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public void setValue(String value, IDirectEditingContext context) {
		delegate.setValue(value, context);
	}
}
