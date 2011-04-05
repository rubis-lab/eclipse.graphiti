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
 *    Volker Wegert - Bug 332363 - Direct Editing: enable automatic resizing for combo boxes
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.func.IDirectEditing;
import org.eclipse.graphiti.func.IProposal;

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

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		return delegate.canDirectEdit(context);
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		return delegate.checkValueValid(value, context);
	}

	@Override
	public String completeValue(String value, int caretPos, String choosenValue, IDirectEditingContext context) {
		return delegate.completeValue(value, caretPos, choosenValue, context);
	}

	@Override
	public String[] getPossibleValues(IDirectEditingContext context) {
		return delegate.getPossibleValues(context);
	}

	@Override
	public String[] getValueProposals(String value, int caretPos, IDirectEditingContext context) {
		return delegate.getValueProposals(value, caretPos, context);
	}

	@Override
	public boolean isAutoCompletionEnabled() {
		return delegate.isAutoCompletionEnabled();
	}

	@Override
	public boolean isCompletionAvailable() {
		return delegate.isCompletionAvailable();
	}

	@Override
	public boolean stretchFieldToFitText() {
		return delegate.stretchFieldToFitText();
	}

	public int getEditingType() {
		return delegate.getEditingType();
	}

	public String getInitialValue(IDirectEditingContext context) {
		return delegate.getInitialValue(context);
	}

	public void setValue(String value, IDirectEditingContext context) {
		delegate.setValue(value, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#getPossibleValuesAsProposal(org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public IProposal[] getPossibleValuesAsProposal(IDirectEditingContext context) {
		return delegate.getPossibleValuesAsProposal(context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#getValueProposalsAsProposal(java.lang.String, int, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public IProposal[] getValueProposalsAsProposal(String value, int caretPosition, IDirectEditingContext context) {
		return delegate.getValueProposalsAsProposal(value, caretPosition, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#completeValueFromProposal(java.lang.String, int, org.eclipse.graphiti.func.IProposal, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String completeValueFromProposal(String value, int caretPosition, IProposal choosenValue, IDirectEditingContext context) {
		return delegate.completeValueFromProposal(value, caretPosition, choosenValue, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#setValueAsProposal(org.eclipse.graphiti.func.IProposal, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public void setValueAsProposal(IProposal value, IDirectEditingContext context) {
		delegate.setValueAsProposal(value, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#checkValueValidAsProposal(org.eclipse.graphiti.func.IProposal, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String checkValueValidAsProposal(IProposal value, IDirectEditingContext context) {
		return delegate.checkValueValidAsProposal(value, context);
	}
	
	
}
