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
package org.eclipse.graphiti.pattern;



/**
 * This interface can by used and implemented by customers within any pattern to
 * signal the need for additional work that needs to be done before or after
 * undo and redo. In addition to {@link ICustomUndoRedoPattern} this interface
 * supports cancellation of undo/redo operations.
 * 
 * @see ICustomUndoRedoPattern
 * @since 0.12
 */
public interface ICustomAbortableUndoRedoPattern extends ICustomUndoRedoPattern {

	/**
	 * The Graphiti framework will call this method after
	 * {@link #preUndo(org.eclipse.graphiti.features.context.IContext)}/
	 * {@link #preRedo(org.eclipse.graphiti.features.context.IContext)} have
	 * been called and before the actual undo/redo operation is triggered. In
	 * case this method returns <code>true</code>, the operation will be
	 * cancelled by the Graphiti framework by throwing an
	 * {@link OperationCanceledException} that causes am EMF revert of the
	 * operation.
	 * <p>
	 * Implementing classes might e.g. set a flag in
	 * {@link #preUndo(org.eclipse.graphiti.features.context.IContext)}/
	 * {@link #preRedo(org.eclipse.graphiti.features.context.IContext)} as
	 * cancellation indication and check that that flag here.
	 * 
	 * @return <code>true</code> in case you want to cancel the current
	 *         operation, <code>false</code> otherwise.
	 */
	boolean isAbort();
}
