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
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;

/**
 * The Class DefaultMoveConnectionDecoratorFeature.
 */
public class DefaultMoveConnectionDecoratorFeature extends AbstractFeature implements IMoveConnectionDecoratorFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultMoveConnectionDecoratorFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature#canLayoutConnectionDecorator(org.eclipse.graphiti.dt.IContext)
	 */
	public boolean canMoveConnectionDecorator(IMoveConnectionDecoratorContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature#layoutConnectionDecorator(org.eclipse.graphiti.dt.IContext)
	 */
	public void moveConnectionDecorator(IMoveConnectionDecoratorContext context) {
		if (!getUserDecision()) {
			return;
		}
		int posX = context.getX();
		int posY = context.getY();

		ConnectionDecorator connectionDecorator = context.getConnectionDecorator();
		GraphicsAlgorithm ga = connectionDecorator.getGraphicsAlgorithm();
		ga.setX(posX);
		ga.setY(posY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti.features.context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IMoveConnectionDecoratorContext) {
			ret = canMoveConnectionDecorator((IMoveConnectionDecoratorContext) context);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features.context.IContext)
	 */
	public void execute(IContext context) {
		if (context instanceof IMoveConnectionDecoratorContext) {
			IMoveConnectionDecoratorContext moveConnectionDecoratorContext = (IMoveConnectionDecoratorContext) context;
			if (moveConnectionDecoratorContext.isExecuteAllowed()) {
				moveConnectionDecorator(moveConnectionDecoratorContext);
			}
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

	private static final String NAME = Messages.DefaultMoveConnectionDecoratorFeature_0_xfld;
}