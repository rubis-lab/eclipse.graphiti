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
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class DefaultSaveImageFeature.
 */
public class DefaultSaveImageFeature extends AbstractFeature implements ISaveImageFeature {

	/**
	 * Instantiates a new default save image feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultSaveImageFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.ISaveImageFeature#canSave(org.eclipse.graphiti.features.context.ISaveImageContext)
	 */
	public boolean canSave(ISaveImageContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.ISaveImageFeature#postSave(org.eclipse.graphiti.features.context.ISaveImageContext)
	 */
	public void postSave(ISaveImageContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.ISaveImageFeature#preSave(org.eclipse.graphiti.features.context.ISaveImageContext)
	 */
	public void preSave(ISaveImageContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti.features.context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ISaveImageContext) {
			ret = canSave((ISaveImageContext) context);
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

	private static final String NAME = Messages.DefaultSaveImageFeature_0_xfld;
}
