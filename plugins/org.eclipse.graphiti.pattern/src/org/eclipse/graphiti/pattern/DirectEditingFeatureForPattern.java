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
 *    Volker Wegert - Bug 332363 - Direct Editing: enable automatic resizing for combo boxes
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
	private IDirectEditing pattern;

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
		this.pattern = pattern;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		return pattern.canDirectEdit(context);
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		return pattern.checkValueValid(value, context);
	}

	@Override
	public String completeValue(String value, int caretPos, String choosenValue, IDirectEditingContext context) {
		return pattern.completeValue(value, caretPos, choosenValue, context);
	}

	@Override
	public String[] getPossibleValues(IDirectEditingContext context) {
		return pattern.getPossibleValues(context);
	}

	@Override
	public String[] getValueProposals(String value, int caretPos, IDirectEditingContext context) {
		return pattern.getValueProposals(value, caretPos, context);
	}

	@Override
	public boolean isAutoCompletionEnabled() {
		return pattern.isAutoCompletionEnabled();
	}

	@Override
	public boolean isCompletionAvailable() {
		return pattern.isCompletionAvailable();
	}

	@Override
	public boolean stretchFieldToFitText() {
		return pattern.stretchFieldToFitText();
	}

	public int getEditingType() {
		return pattern.getEditingType();
	}

	public String getInitialValue(IDirectEditingContext context) {
		return pattern.getInitialValue(context);
	}

	public void setValue(String value, IDirectEditingContext context) {
		pattern.setValue(value, context);
	}

	@Override
	public IProposalSupport getProposalSupport() {
		return pattern.getProposalSupport();
	}

	/**
	 * @since 0.12
	 */
	@Override
	public boolean isAbort() {
		if (pattern instanceof ICustomAbortableUndoRedoPattern) {
			return ((ICustomAbortableUndoRedoPattern) pattern).isAbort();
		}
		return false;
	}

	@Override
	public boolean canUndo(IContext context) {
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
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).preUndo(this, context);
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postUndo(IContext context) {
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).postUndo(this, context);
		}
	}

	/**
	 * @since 0.8
	 * @deprecated use {@link #postUndo(IContext)} instead
	 */
	public void undo(IContext context) {
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).undo(this, context);
		}
	}

	/**
	 * @since 0.8
	 */
	public boolean canRedo(IContext context) {
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
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).preRedo(this, context);
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postRedo(IContext context) {
		if (pattern instanceof ICustomUndoRedoPattern) {
			((ICustomUndoRedoPattern) pattern).postRedo(this, context);
		}
	}

	/**
	 * @since 0.8
	 * @deprecated use {@link #postRedo(IContext)} instead
	 */
	public void redo(IContext context) {
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).redo(this, context);
		}
	}
}
