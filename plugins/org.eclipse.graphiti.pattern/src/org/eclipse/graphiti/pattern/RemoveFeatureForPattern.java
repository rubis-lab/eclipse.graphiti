/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2014 Volker Wegert and others.
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
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *    mwenz - Bug 390331 - preDelete and postDelete not called for Patterns 
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *    mwenz - Bug 453553 - Provide an abort possibility for delete and remove features in case 'pre' methods fail
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomAbortableUndoRedoFeature;
import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.func.IRemove;

/**
 * This feature wraps the remove functionality of a pattern for calls of the
 * Graphiti framework. Clients should not need to use this class directly.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 * 
 * @since 0.8.0
 */
public class RemoveFeatureForPattern extends DefaultRemoveFeature implements ICustomUndoableFeature,
		ICustomAbortableUndoRedoFeature {

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

	/**
	 * @since 0.12
	 */
	@Override
	public boolean isRemoveAbort() {
		IPattern pattern = delegate.getPattern();
		return pattern.isRemoveAbort();
	}

	@Override
	public void remove(IRemoveContext context) {
		delegate.getPattern().remove(context);
	}

	@Override
	public void postRemove(IRemoveContext context) {
		delegate.getPattern().postRemove(context);
	}

	/**
	 * @since 0.12
	 */
	@Override
	public boolean isAbort() {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomAbortableUndoRedoPattern) {
			return ((ICustomAbortableUndoRedoPattern) pattern).isAbort();
		}
		return false;
	}

	@Override
	public boolean canUndo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canUndo(this, context);
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
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).postUndo(this, context);
		}
	}

	/**
	 * @deprecated use {@link #postUndo(IContext)} instead
	 */
	public void undo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).undo(this, context);
		}
	}

	public boolean canRedo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canRedo(this, context);
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
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).postRedo(this, context);
		}
	}

	/**
	 * @deprecated use {@link #postRedo(IContext)} instead
	 */
	public void redo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).redo(this, context);
		}
	}

	@Override
	public boolean hasDoneChanges() {
		IPattern pattern = delegate.getPattern();
		return pattern.hasDoneChanges(IRemove.class);
	}
}
