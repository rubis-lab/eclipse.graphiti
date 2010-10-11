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
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DefaultDeleteFeature.
 */
public class DefaultDeleteFeature extends AbstractFeature implements IDeleteFeature {

	/**
	 * Creates a new {@link DefaultDeleteFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		IRemoveContext rc = new RemoveContext(pictogramElement);
		IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(rc);
		boolean ret = (removeFeature != null) && removeFeature.canRemove(rc);
		return ret;
	}

	@Override
	public void delete(IDeleteContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object[] businessObjectsForPictogramElement = getAllBusinessObjectsForPictogramElement(pe);
		if (businessObjectsForPictogramElement != null && businessObjectsForPictogramElement.length > 0) {
			if (!getUserDecision()) {
				return;
			}
		}

		preDelete(context);

		IRemoveContext rc = new RemoveContext(pe);
		IFeatureProvider featureProvider = getFeatureProvider();
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
		if (removeFeature != null) {
			removeFeature.remove(rc);
		}

		deleteBusinessObjects(businessObjectsForPictogramElement);

		postDelete(context);
	}

	/**
	 * Delete business objects.
	 * 
	 * @param businessObjects
	 *            the business objects
	 */
	protected void deleteBusinessObjects(Object[] businessObjects) {
		if (businessObjects != null) {
			for (Object bo : businessObjects) {
				deleteBusinessObject(bo);
			}
		}
	}

	/**
	 * Delete business object.
	 * 
	 * @param bo
	 *            the bo
	 */
	protected void deleteBusinessObject(Object bo) {
		if (bo instanceof EObject) {
			EcoreUtil.delete((EObject) bo, true);
		}
	}


	@Override
	public void preDelete(IDeleteContext context) {
	}

	@Override
	public void postDelete(IDeleteContext context) {
	}

	@Override
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IDeleteContext) {
			ret = canDelete((IDeleteContext) context);
		}
		return ret;
	}

	@Override
	public void execute(IContext context) {
		if (context instanceof IDeleteContext) {
			delete((IDeleteContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultDeleteFeature_1_xfld;
}