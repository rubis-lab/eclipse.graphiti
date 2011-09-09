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

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;
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

	private boolean doneChanges = false;

	/**
	 * Creates a new {@link DefaultDeleteFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canDelete(IDeleteContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		IRemoveContext rc = new RemoveContext(pictogramElement);
		IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(rc);
		boolean ret = (removeFeature != null) && removeFeature.canRemove(rc);
		return ret;
	}

	public void delete(IDeleteContext context) {
		// we need this reset, since the an instance of this feature can be
		// used multiple times, e.g. as a part of a pattern
		setDoneChanges(false);

		IMultiDeleteInfo multiDeleteInfo = context.getMultiDeleteInfo();
		if (multiDeleteInfo != null && multiDeleteInfo.isDeleteCanceled()) {
			return;
		}
		PictogramElement pe = context.getPictogramElement();
		Object[] businessObjectsForPictogramElement = getAllBusinessObjectsForPictogramElement(pe);
		if (businessObjectsForPictogramElement != null && businessObjectsForPictogramElement.length > 0) {
			if (multiDeleteInfo == null) {
				if (!getUserDecision(context)) {
					return;
				}
			} else {
				if (multiDeleteInfo.isShowDialog()) {
					boolean okPressed = getUserDecision(context);
					if (okPressed) {
						// don't show further dialogs
						multiDeleteInfo.setShowDialog(false);
					} else {
						multiDeleteInfo.setDeleteCanceled(true);
						return;
					}
				}
			}
		}

		setDoneChanges(true);

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

	public void preDelete(IDeleteContext context) {
	}

	public void postDelete(IDeleteContext context) {
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IDeleteContext) {
			ret = canDelete((IDeleteContext) context);
		}
		return ret;
	}

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

	@Override
	public boolean hasDoneChanges() {
		return doneChanges;
	}

	private void setDoneChanges(boolean doneChanges) {
		this.doneChanges = doneChanges;
	}

	/**
	 * Shows a dialog which asks the user to confirm the deletion of one or more
	 * elements.
	 * 
	 * @param context
	 *            delete context
	 * @return TRUE, delete element(s); FALSE cancel delete
	 */
	protected boolean getUserDecision(IDeleteContext context) {
		String msg;
		IMultiDeleteInfo multiDeleteInfo = context.getMultiDeleteInfo();
		if (multiDeleteInfo != null) {
			msg = MessageFormat.format(Messages.DefaultDeleteFeature_2_xmsg, multiDeleteInfo.getNumber());
		} else {
			String deleteName = getDeleteName(context);
			if (deleteName != null && deleteName.length() > 0) {
				msg = MessageFormat.format(Messages.DefaultDeleteFeature_3_xmsg, deleteName);
			} else {
				msg = Messages.DefaultDeleteFeature_4_xmsg;
			}
		}
		return MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				Messages.DefaultDeleteFeature_5_xfld, msg);
	}

	/**
	 * Returns the delete name which will be used for the delete dialog. E.g.
	 * "file test.java"
	 * 
	 * @param context
	 *            the delete context
	 * @return the delete name
	 * @since 0.8
	 */
	protected String getDeleteName(IDeleteContext context) {
		return null;
	}
}