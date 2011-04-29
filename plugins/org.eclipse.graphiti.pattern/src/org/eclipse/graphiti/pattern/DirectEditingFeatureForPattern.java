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
 *    Volker Wegert - Bug 332363 - Direct Editing: enable automatic resizing for combo boxes
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.func.IDirectEditing;
import org.eclipse.graphiti.func.IProposalSupport;

/**
 * The Class DirectEditingFeatureForPattern.
 */
public class DirectEditingFeatureForPattern extends AbstractDirectEditingFeature implements ICustomUndoableFeature {
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

	@Override
	public IProposalSupport getProposalSupport() {
		return delegate.getProposalSupport();
	}

	@Override
	public boolean canUndo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) delegate).canUndo(this, context);
		}
		return super.canUndo(context);
	}

	/**
	 * @since 0.8
	 */
	@Override
	public void undo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) delegate).undo(this, context);
		}
	}

	/**
	 * @since 0.8
	 */
	@Override
	public boolean canRedo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) delegate).canRedo(this, context);
		}
		return true;
	}

	/**
	 * @since 0.8
	 */
	@Override
	public void redo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) delegate).redo(this, context);
		}
	}
}
