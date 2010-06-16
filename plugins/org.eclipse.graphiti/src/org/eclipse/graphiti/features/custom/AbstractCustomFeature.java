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
/*
 * Created on 12.12.2005
 */
package org.eclipse.graphiti.features.custom;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class AbstractCustomFeature.
 */
public abstract class AbstractCustomFeature extends AbstractFeature implements ICustomFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractCustomFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#canExecute(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	public boolean canExecute(ICustomContext context) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#isAvailable(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti.features.context.IContext)
	 */
	public final boolean canExecute(IContext context) {
		final String SIGNATURE = "canExecute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractCustomFeature.class, SIGNATURE, new Object[] { context });
		}
		boolean ret = false;
		if (context instanceof ICustomContext) {
			ret = canExecute((ICustomContext) context);
		}
		if (info) {
			T.racer().exiting(AbstractCustomFeature.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features.context.IContext)
	 */
	public void execute(IContext context) {
		final String SIGNATURE = "execute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractCustomFeature.class, SIGNATURE, new Object[] { context });
		}
		if (context instanceof ICustomContext) {
			ICustomContext customContext = (ICustomContext) context;
			execute(customContext);
		}
		if (info) {
			T.racer().exiting(AbstractCustomFeature.class, SIGNATURE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#getImageId()
	 */
	public String getImageId() {
		return null;
	}

}