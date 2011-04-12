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
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;

/**
 * The Class LayoutFeatureForPattern.
 */
public class LayoutFeatureForPattern extends AbstractLayoutFeature implements ICustomUndoableFeature {
	private IFeatureForPattern delegate;

	/**
	 * Creates a new {@link LayoutFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public LayoutFeatureForPattern(IFeatureProvider featureProvider, IPattern pattern) {
		super(featureProvider);
		delegate = new FeatureForPatternDelegate(pattern);
	}

	public boolean canLayout(ILayoutContext context) {
		IPattern pattern = delegate.getPattern();
		return pattern.canLayout(context);
	}

	public boolean layout(ILayoutContext context) {
		return delegate.getPattern().layout(context);
	}

	@Override
	public boolean canUndo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canUndo(this, context);
		}
		return super.canUndo(context);
	}

	@Override
	public void undo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).undo(this, context);
		}
	}

	@Override
	public boolean canRedo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canRedo(this, context);
		}
		return true;
	}

	@Override
	public void redo(IContext context) {
		IPattern pattern = delegate.getPattern();
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).redo(this, context);
		}
	}
}
