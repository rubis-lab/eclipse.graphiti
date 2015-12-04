/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *    mwenz - Bug 481994 - Some XxxFeatureForPattern classes call ICustomUndoablePattern#redo instead of ICustomUndoRedoPattern#postRedo
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomAbortableUndoRedoFeature;
import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.AbstractMoveShapeFeature;

/**
 * This feature wraps the move shape functionality of a pattern for calls of the
 * Graphiti framework. Clients should not need to use this class directly.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class MoveShapeFeatureForPattern extends AbstractMoveShapeFeature implements ICustomUndoableFeature,
		ICustomAbortableUndoRedoFeature {
	private IFeatureForPattern delegate;

	/**
	 * Creates a new {@link MoveShapeFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public MoveShapeFeatureForPattern(IFeatureProvider featureProvider, IPattern pattern) {
		super(featureProvider);
		delegate = new FeatureForPatternDelegate(pattern);
	}

	public boolean canMoveShape(IMoveShapeContext context) {
		IPattern pattern = delegate.getPattern();
		return pattern.canMoveShape(context);
	}

	public void moveShape(IMoveShapeContext context) {
		delegate.getPattern().moveShape(context);
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
		} else if (pattern instanceof ICustomUndoRedoPattern) {
			return ((ICustomUndoRedoPattern) pattern).canUndo(this, context);
		}
		return super.canUndo(context);
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preUndo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).preUndo(this, context);
		}
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
	 * @since 0.8
	 * @deprecated use {@link #postUndo(IContext)} instead
	 */
	public void undo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).undo(this, context);
		}
	}

	/**
	 * @since 0.8
	 */
	public boolean canRedo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canRedo(this, context);
		} else if (pattern instanceof ICustomUndoRedoPattern) {
			return ((ICustomUndoRedoPattern) pattern).canRedo(this, context);
		}
		return true;
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preRedo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).preRedo(this, context);
		}
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
	 * @since 0.8
	 * @deprecated use {@link #postRedo(IContext)} instead
	 */
	public void redo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).redo(this, context);
		}
	}
}
