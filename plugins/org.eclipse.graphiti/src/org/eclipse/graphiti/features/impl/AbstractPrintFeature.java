/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2013 SAP AG.
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
import org.eclipse.graphiti.features.IPrintFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IPrintContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * Abstract base implementation of a save as image feature.
 * 
 * @since 0.10
 */
public abstract class AbstractPrintFeature extends AbstractFeature implements IPrintFeature {

	private static final String NAME = Messages.DefaultPrintFeature_0_xfld;

	/**
	 * Constructor that is to be called by any subclass.
	 * 
	 * @param fp
	 *            The feature provider that created the feature
	 */
	public AbstractPrintFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Returns the name of the print feature, by default "Print"
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * Checks if this feature can be executed by delegating to the method
	 * {@link #canPrint(IPrintContext)}.
	 * 
	 * @param context
	 *            Context information for printing.
	 * @return <code>true</code> in case this print feature can be executed,
	 *         <code>false</code> otherwise.
	 */
	final public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IPrintContext) {
			ret = canPrint((IPrintContext) context);
		}
		return ret;
	}

	/**
	 * Checks if this feature can execute. The default implementation simply
	 * returns <code>true</code>. Note that the Graphiti framework already
	 * checks the availability of printer in the print action that triggers this
	 * feature.
	 * 
	 * @param context
	 *            Context information for printing.
	 * @return <code>true</code> in case this print feature can be executed,
	 *         <code>false</code> otherwise.
	 */
	public boolean canPrint(IPrintContext context) {
		return true;
	}

	/**
	 * Hook method for executing stuff that needs to be done before actually
	 * printing a diagram. The default implementation does nothing.
	 * 
	 * @param context
	 *            Context information for printing.
	 */
	public void prePrint(IPrintContext context) {
	}

	/**
	 * Executes this print feature by sequentially calling
	 * {@link #prePrint(IPrintContext)}, {@link #print(IPrintContext)} and
	 * {@link #postPrint(IPrintContext)}. This method will fail in case the
	 * passed context is no {@link IPrintContext}.
	 * 
	 * @param context
	 *            Context information for printing.
	 */
	final public void execute(IContext context) {
		IPrintContext printContext = (IPrintContext) context;
		prePrint(printContext);
		print(printContext);
		postPrint(printContext);
	}

	/**
	 * Hook method for executing stuff that needs to be done after actually
	 * printing a diagram. The default implementation does nothing.
	 * 
	 * @param context
	 *            Context information for printing.
	 */
	public void postPrint(IPrintContext context) {
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
