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
 * The Class DefaultPrintFeature.
 */
public class DefaultPrintFeature extends AbstractFeature implements IPrintFeature {

	/**
	 * Instantiates a new default print feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultPrintFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IPrintFeature#canPrint(org.eclipse.graphiti.features.context.IPrintContext)
	 */
	public boolean canPrint(IPrintContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IPrintFeature#postPrint(org.eclipse.graphiti.features.context.IPrintContext)
	 */
	public void postPrint(IPrintContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IPrintFeature#prePrint(org.eclipse.graphiti.features.context.IPrintContext)
	 */
	public void prePrint(IPrintContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti.features.context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IPrintContext) {
			ret = canPrint((IPrintContext) context);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features.context.IContext)
	 */
	public void execute(IContext context) {
		// todo: not possible yet
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultPrintFeature_0_xfld;
}
