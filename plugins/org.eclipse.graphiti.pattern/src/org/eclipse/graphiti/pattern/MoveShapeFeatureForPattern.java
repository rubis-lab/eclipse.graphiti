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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.AbstractMoveShapeFeature;

/**
 * The Class MoveShapeFeatureForPattern.
 */
public class MoveShapeFeatureForPattern extends AbstractMoveShapeFeature {
	private IFeatureForPattern delegate;

	/**
	 * Instantiates a new move shape feature for pattern.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public MoveShapeFeatureForPattern(IFeatureProvider featureProvider, IPattern pattern) {
		super(featureProvider);
		delegate = new FeatureForPatternDelegate(pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature#canMoveShape(org.eclipse.graphiti.features.context.IMoveShapeContext)
	 */
	public boolean canMoveShape(IMoveShapeContext context) {
		IPattern pattern = delegate.getPattern();
		return pattern.canMoveShape(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature#moveShape(org.eclipse.graphiti.features.context.IMoveShapeContext)
	 */
	public void moveShape(IMoveShapeContext context) {
		delegate.getPattern().moveShape(context);
	}
}
