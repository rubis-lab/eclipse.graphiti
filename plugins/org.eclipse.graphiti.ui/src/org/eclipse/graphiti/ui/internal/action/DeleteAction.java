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
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DeleteAction extends AbstractPreDefinedAction implements IContextAndFeatureProvider {

	private static final String TOOL_TIP = Messages.DeleteAction_0_xmsg;

	private static final String TEXT = Messages.DeleteAction_1_xfld;

	public static final String ACTION_ID = ActionFactory.DELETE.getId();

	public DeleteAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
	}

	@Override
	public boolean isAvailable() {
		PictogramElement pe[] = getSelectedPictogramElements();
		for (int i = 0; i < pe.length; i++) {
			IDeleteContext context = new DeleteContext(pe[i]);
			IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(context);
			if (deleteFeature == null) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		PictogramElement pe[] = getSelectedPictogramElements();
		for (int i = 0; i < pe.length; i++) {
			IDeleteContext context = new DeleteContext(pe[i]);
			IFeatureProvider featureProvider = getFeatureProvider();
			if (featureProvider == null) {
				return false;
			}
			IDeleteFeature deleteFeature = featureProvider.getDeleteFeature(context);
			if (deleteFeature != null && !deleteFeature.canDelete(context)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void run() {
		boolean userAgree = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				Messages.AbstractFeature_0_xhed,
				Messages.DefaultDeleteFeature_0_xmsg);
		if (userAgree)
			genericRun(this);
	}

	@Override
	public IContext createContext(PictogramElement pe) {
		return new DeleteContext(pe);
	}

	@Override
	public IFeature provideFeatureForContext(IContext context) {
		return getFeatureProvider().getDeleteFeature((IDeleteContext) context);
	}
}