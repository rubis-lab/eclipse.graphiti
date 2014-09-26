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
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mgorning - Bug 343983 - Notification for Cancelled Reconnection Events
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *    mlypik - Bug 401792 - Disable starting reconnection
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.ICustomAbortableUndoRedoFeature;
import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.func.IReconnection;
import org.eclipse.graphiti.internal.Messages;

/**
 * This feature wraps the reconnection functionality of a pattern for calls of
 * the Graphiti framework. Clients should not need to use this class directly.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class ReconnectionFeatureForPattern extends AbstractFeature implements IReconnectionFeature,
		ICustomUndoableFeature, ICustomAbortableUndoRedoFeature {

	private static final String NAME = Messages.ReconnectionFeatureForPattern_0_xfld;
	private IReconnection delegate;

	/**
	 * Creates a new {@link ReconnectionFeatureForPattern}.
	 * 
	 * @param fp
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public ReconnectionFeatureForPattern(IFeatureProvider fp, IReconnection pattern) {
		super(fp);
		this.delegate = pattern;
	}

	public boolean canReconnect(IReconnectionContext context) {
		return delegate.canReconnect(context);
	}

	public void postReconnect(IReconnectionContext context) {
		delegate.postReconnect(context);
	}

	public void preReconnect(IReconnectionContext context) {
		delegate.preReconnect(context);
	}

	public void reconnect(IReconnectionContext context) {
		delegate.reconnect(context);
	}

	/**
	 * @since 0.9
	 */
	public void canceledReconnect(IReconnectionContext context) {
		delegate.canceledReconnect(context);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IReconnectionContext) {
			ret = canReconnect((IReconnectionContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IReconnectionContext) {
			reconnect((IReconnectionContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
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

	/**
	 * @since 0.11
	 */
	@Override
	public boolean canStartReconnect(IReconnectionContext context) {
		return true;
	}
}
