/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2014, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IContext;

/**
 * @since 0.12
 */
public interface ICustomUndoRedoFeature {

	boolean canUndo(IContext context);

	void preUndo(IContext context);

	void undo(IContext context);

	void postUndo(IContext context);

	boolean canRedo(IContext context);

	void preRedo(IContext context);

	void redo(IContext context);

	void postRedo(IContext context);
}
