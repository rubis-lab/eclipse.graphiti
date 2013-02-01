/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * Abstract base implementation of a print feature.
 * 
 * @since 0.10
 */
public abstract class AbstractSaveAsImageFeature extends AbstractFeature implements ISaveImageFeature {

	private static final String NAME = Messages.DefaultSaveImageFeature_0_xfld;

	/**
	 * Constructor that is to be called by any subclass.
	 * 
	 * @param fp
	 *            The feature provider that created the feature
	 */
	public AbstractSaveAsImageFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Returns the name of the save as image feature, by default "Save As Image"
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * Checks if this feature can be executed by delegating to the method
	 * {@link #canSave(ISaveImageContext)}.
	 * 
	 * @param context
	 *            Context information for printing.
	 * @return <code>true</code> in case this save image feature can be
	 *         executed, <code>false</code> otherwise.
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ISaveImageContext) {
			ret = canSave((ISaveImageContext) context);
		}
		return ret;
	}

	/**
	 * Checks if this feature can execute. The default implementation simply
	 * returns <code>true</code>.
	 * 
	 * @param context
	 *            Context information for saving an image.
	 * @return <code>true</code> in case this save image feature can be
	 *         executed, <code>false</code> otherwise.
	 */
	public boolean canSave(ISaveImageContext context) {
		return true;
	}

	/**
	 * Hook method for executing stuff that needs to be done before actually
	 * saving a diagram. The default implementation does nothing.
	 * 
	 * @param context
	 *            Context information for saving.
	 */
	public void preSave(ISaveImageContext context) {
	}

	/**
	 * Executes this save image feature by sequentially calling
	 * {@link #preSave(ISaveImageContext)}, {@link #save(ISaveImageContext)} and
	 * {@link #postSave(ISaveImageContext)}. This method will fail in case the
	 * passed context is no {@link ISaveImageContext}.
	 * 
	 * @param context
	 *            Context information for saving.
	 */
	public final void execute(IContext context) {
		ISaveImageContext saveImageContext = (ISaveImageContext) context;
		preSave(saveImageContext);
		save(saveImageContext);
		postSave(saveImageContext);
	}

	/**
	 * Hook method for executing stuff that needs to be done after actually
	 * saving a diagram. The default implementation does nothing.
	 * 
	 * @param context
	 *            Context information for saving.
	 */
	public void postSave(ISaveImageContext context) {
	}

	/**
	 * Hook method that reports if changes have been done while executing this
	 * feature. In case <code>false</code> is returned the feature will not
	 * appear in the undo stack. The default implementation simply returns
	 * <code>false</code>.
	 * 
	 * @return <code>true</code> in case changes have been made,
	 *         <code>false</code> otherwise.
	 */
	@Override
	public boolean hasDoneChanges() {
		return false;
	}
}
