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
package org.eclipse.graphiti.ui.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.features.impl.AbstractFeatureProvider;
import org.eclipse.graphiti.features.impl.DefaultAddBendpointFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveAnchorFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveBendpointFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.features.impl.DefaultRemoveBendpointFeature;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.features.impl.DefaultUpdateDiagramFeature;
import org.eclipse.graphiti.features.impl.UpdateNoBoFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Class DefaultFeatureProvider.
 */
public class DefaultFeatureProvider extends AbstractFeatureProvider {

	/**
	 * Instantiates a new default feature provider.
	 * 
	 * @param dtp
	 *            the dtp
	 */
	public DefaultFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getAddBendpointFeature
	 * (org.eclipse.graphiti.features.context.IAddBendpointContext)
	 */
	@Override
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		IAddBendpointFeature ret = new DefaultAddBendpointFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getCopyFeature(org.eclipse
	 * .graphiti.features.context.ICopyContext)
	 */
	public ICopyFeature getCopyFeature(ICopyContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.impl.AbstractFeatureProvider#getCustomFeatures
	 * (org.eclipse.graphiti.features.context.ICustomContext)
	 */
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		ICustomFeature[] ret = super.getCustomFeatures(context);
		List<ICustomFeature> retList = new ArrayList<ICustomFeature>();
		for (int i = 0; i < ret.length; i++) {
			retList.add(ret[i]);
		}
		ret = retList.toArray(ret);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.edit.IFeatureProvider#getDeleteFeatures()
	 */
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		IDeleteFeature ret = null;
		ret = new DefaultDeleteFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getMoveAnchorFeature
	 * (org.eclipse.graphiti.features.context.IMoveAnchorContext)
	 */
	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		IMoveAnchorFeature ret = new DefaultMoveAnchorFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getMoveBendpointFeature
	 * (org.eclipse.graphiti.features.context.IMoveBendpointContext)
	 */
	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		IMoveBendpointFeature ret = new DefaultMoveBendpointFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getMoveConnectionDecoratorFeature(org.eclipse.graphiti.features.context.
	 * IMoveConnectionDecoratorContext)
	 */
	@Override
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		IMoveConnectionDecoratorFeature ret = new DefaultMoveConnectionDecoratorFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getMoveShapeFeature
	 * (org.eclipse.graphiti.features.context.IMoveShapeContext)
	 */
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		IMoveShapeFeature ret = new DefaultMoveShapeFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getPasteFeature(org.eclipse
	 * .graphiti.features.context.IPasteContext)
	 */
	public IPasteFeature getPasteFeature(IPasteContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getRemoveBendpointFeature
	 * (org.eclipse.graphiti.features.context.IRemoveBendpointContext )
	 */
	@Override
	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		IRemoveBendpointFeature ret = new DefaultRemoveBendpointFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.impl.AbstractFeatureProvider#
	 * getResizeShapeFeature
	 * (org.eclipse.graphiti.features.context.IResizeShapeContext)
	 */
	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		IResizeShapeFeature ret = new DefaultResizeShapeFeature(this);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.impl.AbstractFeatureProvider#getRemoveFeature
	 * (org.eclipse.graphiti.features.context.IRemoveContext)
	 */
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return new DefaultRemoveFeature(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.impl.AbstractFeatureProvider#getUpdateFeature
	 * (org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		IUpdateFeature ret = null;
		if (context.getPictogramElement() instanceof Diagram) {
			return new DefaultUpdateDiagramFeature(this);
		}
		Object[] bos = getAllBusinessObjectsForPictogramElement(context.getPictogramElement());
		if (bos.length == 0) {
			ret = new UpdateNoBoFeature(this);
		} else {
			ret = super.getUpdateFeature(context);
		}

		return ret;
	}
}