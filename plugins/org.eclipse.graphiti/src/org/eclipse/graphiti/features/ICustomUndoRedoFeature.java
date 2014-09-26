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
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * This interface can by used and implemented by customers within any feature to
 * signal the need for additional work that needs to be done before or after
 * undo and redo. When a feature implements this interface, and the framework
 * performs an undo or a redo, the framework will call the contained methods.
 * <p>
 * Implementing this interface is especially helpful if customers want to
 * implement undo/redo functionality for non-EMF changes, e.g. for non-EMF
 * domain models. Note that any EMF-model change (including the changes done to
 * the graphical representation (Graphiti {@link PictogramElement}s and
 * {@link GraphicsAlgorithm}s will by handled automatically by the Graphiti
 * framework no matter if this interface is implemented by a feature or not. The
 * feature may use the context object (e.g. the contained properties set) passed
 * to the contained methods while executing the feature in order to collect any
 * information needed for undo.
 * <p>
 * In case you want to cancel undo/redo operations in {@link #preUndo(IContext)}/{@link #preRedo(IContext)}, you need to implement
 * {@link ICustomAbortableUndoRedoFeature} which offers an
 * {@link ICustomAbortableUndoRedoFeature#isAbort()} method that causes the
 * cancellation of undo/redo operation in case <code>true</code> is returned.
 * 
 * @see ICustomAbortableUndoRedoFeature
 * @since 0.12
 */
public interface ICustomUndoRedoFeature {

	/**
	 * Decides if the changes done by a processed feature can be undone. This
	 * method is called once by the Graphiti framework just before any undo work
	 * is started, e.g. before {@link #preUndo(IContext)}.
	 * <p>
	 * Note that as soon as any feature reports <code>false</code> here, also
	 * all previous entries in the command stack are no longer reachable for
	 * undo.
	 * <p>
	 * Note: this method with exactly the same signature is also already part of
	 * the {@link IFeature} contract. It is repeated here for transparency
	 * purposes only.
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature.
	 * 
	 * @return true if the feature can be undone, false if not
	 */
	boolean canUndo(IContext context);

	/**
	 * This method will be called by the Graphiti framework before the EMF undo
	 * is triggered. Customers may revert their non-EMF changes done by the
	 * feature here or in {@link #postUndo(IContext)}.
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void preUndo(IContext context);

	/**
	 * This method will be called by the Graphiti framework after the EMF undo
	 * is finished. Customers may revert their non-EMF changes done by the
	 * feature here or in {@link #preUndo(IContext)}.
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void postUndo(IContext context);

	/**
	 * Decides if the processed feature can be re-done. This method is called
	 * once by the Graphiti framework just before any redo work is started, e.g.
	 * before {@link #preRedo(IContext)}.
	 * <p>
	 * Note that as soon as any feature reports <code>false</code> here, also
	 * all consecutive entries in the command stack are no longer reachable for
	 * redo.
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 * 
	 * @return true if the feature can be re-done, false if not
	 */
	boolean canRedo(IContext context);

	/**
	 * This method will be called by the Graphiti framework before the EMF undo
	 * has triggered. Customers may re-apply their non-EMF changes done by the
	 * feature here or in {@link #postRedo(IContext)}. (Usually it might be
	 * sufficient to delegate to the execution method of the feature.)
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void preRedo(IContext context);

	/**
	 * This method will be called by the Graphiti framework after the EMF undo
	 * has finished. Customers may re-apply their non-EMF changes done by the
	 * feature here or in {@link #preRedo(IContext)}. (Usually it might be
	 * sufficient to delegate to the execution method of the feature.)
	 * 
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void postRedo(IContext context);
}
