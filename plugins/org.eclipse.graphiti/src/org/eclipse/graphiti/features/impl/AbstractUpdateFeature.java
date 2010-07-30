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

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class AbstractUpdateFeature.
 */
public abstract class AbstractUpdateFeature extends AbstractFeature implements IUpdateFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti
	 * .features.context.IContext)
	 */
	public final boolean canExecute(IContext context) {
		final String SIGNATURE = Messages.AbstractUpdateFeature_0_xfld;
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractUpdateFeature.class, SIGNATURE, new Object[] { context });
		}
		boolean ret = false;
		if (context instanceof IUpdateContext) {
			IUpdateContext updateSemanticsContext = (IUpdateContext) context;
			ret = canUpdate(updateSemanticsContext);
		}
		if (info) {
			T.racer().exiting(AbstractUpdateFeature.class, SIGNATURE, ret);
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
		final String SIGNATURE = Messages.AbstractUpdateFeature_1_xfld;
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractUpdateFeature.class, SIGNATURE, new Object[] { context });
		}
		if (context instanceof IUpdateContext) {
			update((IUpdateContext) context);
		}
		if (info) {
			T.racer().exiting(AbstractUpdateFeature.class, SIGNATURE);
		}
	}

	/**
	 * Can update pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 * @return true, if successful
	 */
	protected boolean canUpdatePictogramElement(PictogramElement pe) {
		return getFeatureProvider().canUpdate(new UpdateContext(pe)).toBoolean();
	}

	/**
	 * Update pictogram element needed.
	 * 
	 * @param pe
	 *            the pe
	 * @return the i reason
	 */
	protected IReason updatePictogramElementNeeded(PictogramElement pe) {
		return getFeatureProvider().updateNeeded(new UpdateContext(pe));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.impl.AbstractFeature#updatePictogramElement
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	protected void updatePictogramElement(PictogramElement pe) {
		getFeatureProvider().updateIfPossible(new UpdateContext(pe));
	}

	/**
	 * Resize shape.
	 * 
	 * @param shape
	 *            the shape
	 */
	protected void resizeShape(Shape shape) {
		final ResizeShapeContext resizeShapeContext = new ResizeShapeContext(shape);
		GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
		IDimension gaSize = Graphiti.getGaService().calculateSize(ga, false);
		resizeShapeContext.setWidth(gaSize.getWidth());
		resizeShapeContext.setHeight(gaSize.getHeight());

		final IResizeShapeFeature resizeShapeFeature = getFeatureProvider().getResizeShapeFeature(resizeShapeContext);
		if (resizeShapeFeature != null) {
			if (resizeShapeFeature.canResizeShape(resizeShapeContext)) {
				resizeShapeFeature.resizeShape(resizeShapeContext);
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

	private static final String NAME = Messages.AbstractUpdateFeature_2_xfld;
}