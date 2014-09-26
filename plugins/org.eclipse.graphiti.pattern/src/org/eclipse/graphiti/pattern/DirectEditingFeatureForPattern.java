/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Volker Wegert - Bug 332363 - Direct Editing: enable automatic resizing for combo boxes
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomAbortableUndoRedoFeature;
import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.func.IDirectEditing;
import org.eclipse.graphiti.func.IProposalSupport;

/**
 * This feature wraps the direct editing functionality of a pattern for calls of
 * the Graphiti framework. Clients should not need to use this class directly.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class DirectEditingFeatureForPattern extends AbstractDirectEditingFeature implements ICustomUndoableFeature,
		ICustomAbortableUndoRedoFeature {
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

	/**
	 * @since 0.12
	 */
	@Override
	public boolean isAbort() {
		if (delegate instanceof ICustomAbortableUndoRedoPattern) {
			return ((ICustomAbortableUndoRedoPattern) delegate).isAbort();
		}
		return false;
	}

	@Override
	public boolean canUndo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) delegate).canUndo(this, context);
		}
		return super.canUndo(context);
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preUndo(IContext context) {
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postUndo(IContext context) {
		if (delegate instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) delegate).postUndo(this, context);
		}
	}

	/**
	 * @since 0.8
	 * @deprecated use {@link #postUndo(IContext)} instead
	 */
	public void undo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) delegate).undo(this, context);
		}
	}

	/**
	 * @since 0.8
	 */
	public boolean canRedo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) delegate).canRedo(this, context);
		}
		return true;
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preRedo(IContext context) {
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postRedo(IContext context) {
		if (delegate instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) delegate).postRedo(this, context);
		}
	}

	/**
	 * @since 0.8
	 * @deprecated use {@link #postRedo(IContext)} instead
	 */
	public void redo(IContext context) {
		if (delegate instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) delegate).redo(this, context);
		}
	}
}
