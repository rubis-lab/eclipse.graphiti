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
 *    mwenz - Bug 323155 - Check usage scenarios for DefaultPrintFeature and
 *            DefaultSaveImageFeature
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IPrintContext;
import org.eclipse.graphiti.features.impl.AbstractPrintFeature;

/**
 * The Interface IPrintFeature for print support of diagrams.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link AbstractPrintFeature} or
 *              {@link DefaultPrintFeature} instead.
 */
public interface IPrintFeature extends IFeature {

	/**
	 * Checks if the printing can be executed, e.g the availability of printers
	 * is being checked in the default implementation.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canPrint(IPrintContext context);

	/**
	 * Print the diagram using the given context information. By default the
	 * complete diagram is printed, there's currently no option to influence the
	 * printing via the context.
	 * 
	 * @param context
	 *            Context information for printing
	 * 
	 * @since 0.10
	 */
	void print(IPrintContext context);

	/**
	 * Pre-print hook. Called before the actual print process starts. You may
	 * use this hook to influence the current state of the diagram or the
	 * selection.
	 * 
	 * @param context
	 *            the context
	 */
	void prePrint(IPrintContext context);

	/**
	 * Post-print hook. Called after the actual print process. You may use this
	 * hook to set back the changes done in the prePrint method.
	 * 
	 * @param context
	 *            the context
	 */
	void postPrint(IPrintContext context);
}
