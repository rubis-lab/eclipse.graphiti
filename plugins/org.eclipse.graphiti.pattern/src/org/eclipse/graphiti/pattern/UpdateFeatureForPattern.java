/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.func.IUpdate;
import org.eclipse.graphiti.internal.util.T;

/**
 * This feature wraps the update functionality of a pattern for calls of the
 * Graphiti framework. Clients should not need to use this class directly.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class UpdateFeatureForPattern extends AbstractUpdateFeature implements ICustomUndoableFeature {

	private IUpdate pattern;

	/**
	 * Creates a new {@link UpdateFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public UpdateFeatureForPattern(IFeatureProvider featureProvider, IUpdate pattern) {
		super(featureProvider);
		this.pattern = pattern;
	}

	public boolean canUpdate(IUpdateContext context) {
		boolean ret = pattern.canUpdate(context);
		return ret;
	}

	public IReason updateNeeded(IUpdateContext context) {
		if (T.racer().info()) {
			T.racer().info("UpdateFeatureForPattern", "updateNeeded", "context: " + context); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		IReason ret = pattern.updateNeeded(context);
		if (T.racer().info()) {
			T.racer().info("UpdateFeatureForPattern", "updateNeeded", "returns " + ret.toString()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return ret;
	}

	public boolean update(IUpdateContext context) {
		return pattern.update(context);
	}

	@Override
	public boolean canUndo(IContext context) {
		if (pattern instanceof ICustomUndoablePattern) {
			return ((ICustomUndoablePattern) pattern).canUndo(this, context);
		}
		return super.canUndo(context);
	}

	/**
	 * @since 0.8
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
		}
		return true;
	}

	/**
	 * @since 0.8
	 */
	public void redo(IContext context) {
		if (pattern instanceof ICustomUndoablePattern) {
			((ICustomUndoablePattern) pattern).redo(this, context);
		}
	}
}
