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
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.func.IAdd;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class AddFeatureForPattern.
 */
public class AddFeatureForPattern extends AbstractAddFeature implements ICustomUndoableFeature {
	private IAdd pattern;

	/**
	 * Creates a new {@link AddFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public AddFeatureForPattern(IFeatureProvider featureProvider, IAdd pattern) {
		super(featureProvider);
		this.pattern = pattern;
	}

	public boolean canAdd(IAddContext context) {
		return pattern.canAdd(context);
	}

	public PictogramElement add(IAddContext context) {
		return pattern.add(context);
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
