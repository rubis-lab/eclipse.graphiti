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
package org.eclipse.graphiti.ui.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * The Class DefaultDeleteFeature.
 */
public class DefaultDeleteFeature extends AbstractFeature implements IDeleteFeature {

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public DefaultDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDeleteFeature#canDelete(org.eclipse.graphiti
	 * .dt.IContext)
	 */
	public boolean canDelete(IDeleteContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		IRemoveContext rc = new RemoveContext(pictogramElement);
		IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(rc);
		boolean ret = (removeFeature != null) && removeFeature.canRemove(rc);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDeleteFeature#delete(org.eclipse.graphiti.
	 * features.context.IDeleteContext)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#getUserDecision()
	 */
	@Override
	protected boolean getUserDecision() {
		return MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.AbstractFeature_0_xhed,
				Messages.DefaultDeleteFeature_0_xmsg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDeleteFeature#delete(org.eclipse.graphiti.
	 * dt.IContext)
	 */
	public void preDelete(IDeleteContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDeleteFeature#postDelete(org.eclipse.graphiti
	 * .dt.IContext)
	 */
	public void postDelete(IDeleteContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#canExecute(org.eclipse.graphiti
	 * .features .context.IContext)
	 */
	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IDeleteContext) {
			ret = canDelete((IDeleteContext) context);
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
		if (context instanceof IDeleteContext) {
			delete((IDeleteContext) context);
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

	private static final String NAME = Messages.DefaultDeleteFeature_1_xfld;
}