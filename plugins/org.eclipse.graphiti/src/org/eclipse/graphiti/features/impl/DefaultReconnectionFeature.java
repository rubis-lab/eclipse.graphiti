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
 * Created on 13.12.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Class DefaultReconnectionFeature.
 */
public class DefaultReconnectionFeature extends AbstractFeature implements IReconnectionFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultReconnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IReconnectionFeature#canReconnect(org.eclipse
	 * .graphiti.features.context.IReconnectionContext)
	 */
	public boolean canReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Anchor newAnchor = getNewAnchor(context);
		boolean ret = (connection != null) && (newAnchor != null) && (connection.getStart() != null) && (connection.getEnd() != null)
				&& !(newAnchor.getParent() instanceof Diagram);
		return ret;
	}

	/**
	 * Gets the new anchor.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the new anchor
	 */
	protected Anchor getNewAnchor(IReconnectionContext context) {
		return context.getNewAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IReconnectionFeature#reconnnect(org.eclipse
	 * .graphiti.features.context.IReconnectionContext)
	 */
	public final void reconnect(IReconnectionContext context) {
		if (!getUserDecision()) {
			return;
		}
		Connection connection = context.getConnection();
		Anchor newAnchor = context.getNewAnchor();
		Anchor oldAnchor = context.getOldAnchor();

		preReconnect(context);

		if (connection.getStart().equals(oldAnchor)) {
			connection.setStart(newAnchor);
		} else {
			connection.setEnd(newAnchor);
		}

		postReconnect(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IReconnectionFeature#preReconnnect(org.
	 * eclipse.graphiti.features.context.IReconnectionContext)
	 */
	public void preReconnect(IReconnectionContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IReconnectionFeature#postReconnnect(org
	 * .eclipse.graphiti.features.context.IReconnectionContext)
	 */
	public void postReconnect(IReconnectionContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti
	 * .features.context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IReconnectionContext) {
			ret = canReconnect((IReconnectionContext) context);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features
	 * .context.IContext)
	 */
	public void execute(IContext context) {
		if (context instanceof IReconnectionContext) {
			reconnect((IReconnectionContext) context);
		}
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

	private static final String NAME = Messages.DefaultReconnectionFeature_0_xfld;
}