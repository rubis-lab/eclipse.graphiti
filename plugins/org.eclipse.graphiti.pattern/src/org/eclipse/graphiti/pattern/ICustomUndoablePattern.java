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
 *    mwenz - Bug 324859 - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * This interface can by used by customers and implemented within any pattern to
 * signal the need for additional undo or redo work. When a pattern implements
 * this interface, and the framework performs an undo or a redo, the framework
 * will call the contained methods.
 * <p>
 * Implementing this interface is especially helpful if customers want to
 * implement undo/redo functionality for non-EMF changes, e.g. for non-EMF
 * domain models. Note that any EMF-model changes (including the changes done to
 * the graphical representation (Graphiti {@link PictogramElement}s and
 * {@link GraphicsAlgorithm}s will by handled automatically by the Graphiti
 * framework no matter if this interface is implemented by a pattern or not.
 * 
 * @since 0.8.0
 */
public interface ICustomUndoablePattern {

	/**
	 * Decides if the processed pattern functionality can be undone.
	 * 
	 * @param feature
	 *            this is the instance of the {@link IFeature} object that was
	 *            in use when executing the pattern functionality
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 * 
	 * @return true if the feature can be undone, false if not
	 */
	boolean canUndo(IFeature feature, IContext context);

	/**
	 * This method will be called to actually do the work needed for undo.
	 * Customers may revert their non-EMF changes done by the pattern
	 * functionality here.
	 * 
	 * @param feature
	 *            this is the instance of the {@link IFeature} object that was
	 *            in use when executing the pattern functionality
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void undo(IFeature feature, IContext context);

	/**
	 * Decides if the processed pattern functionality can be re-done.
	 * 
	 * @param feature
	 *            this is the instance of the {@link IFeature} object that was
	 *            in use when executing the pattern functionality
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 * 
	 * @return true if the feature can be re-done, false if not
	 */
	boolean canRedo(IFeature feature, IContext context);

	/**
	 * This method will be called to actually do the work needed for redo.
	 * Customers may re-apply their non-EMF changes done by the pattern
	 * functionality here. (Usually it might be sufficient to delegate to the
	 * execution method of the pattern functionality.)
	 * 
	 * @param feature
	 *            this is the instance of the {@link IFeature} object that was
	 *            in use when executing the pattern functionality
	 * @param context
	 *            this is the instance of the {@link IContext} object that was
	 *            in use when executing the feature
	 */
	void redo(IFeature feature, IContext context);
}
