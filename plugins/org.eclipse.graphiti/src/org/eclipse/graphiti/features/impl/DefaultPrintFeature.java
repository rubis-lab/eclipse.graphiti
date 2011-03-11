/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 323155 - Check usage scenarios for DefaultPrintFeature and
 *            DefaultSaveImageFeature
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
 * The Class DefaultPrintFeature. It is planned to use this for print support.
 * Not yet supported perfectly.
 */
public class DefaultPrintFeature extends AbstractFeature implements IPrintFeature {

	private static final String NAME = Messages.DefaultPrintFeature_0_xfld;

	/**
	 * Creates a new {@link DefaultPrintFeature}.
	 * 
	 * @param fp
	 *            the feauture provider
	 */
	public DefaultPrintFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canPrint(IPrintContext context) {
		return true;
	}

	@Override
	public void postPrint(IPrintContext context) {
	}

	@Override
	public void prePrint(IPrintContext context) {
	}

	@Override
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IPrintContext) {
			ret = canPrint((IPrintContext) context);
		}
		return ret;
	}

	@Override
	final public void execute(IContext context) {
		// not relevant (actual work is done in PrintGraphicalViewerAction)
	}

	@Override
	public String getName() {
		return NAME;
	}
}
