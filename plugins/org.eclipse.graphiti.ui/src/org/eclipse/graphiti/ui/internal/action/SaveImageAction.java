/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    jpasch - Bug 323025 ActionBarContributor cleanup
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.features.context.impl.SaveImageContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.action.Action;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class SaveImageAction extends Action {

	private IDiagramBehavior diagramBehavior;
	private IConfigurationProvider configurationProvider;
	
	public static final String TOOL_TIP = Messages.SaveImageAction_1_xmsg;
	
	public static final String TEXT = Messages.SaveImageAction_0_xmsg;
	
	public static final String ACTION_ID = "export_diagram_action"; //$NON-NLS-1$
	
	public static final String ACTION_DEFINITION_ID = "org.eclipse.graphiti.ui.internal.action.SaveImageAction"; //$NON-NLS-1$

	public SaveImageAction(IDiagramBehavior diagramBehavior, IConfigurationProvider configurationProvider) {
		super();
		this.diagramBehavior = diagramBehavior;
		this.configurationProvider = configurationProvider;

		setText(TEXT);
		setToolTipText(TOOL_TIP);
		setId(ACTION_ID);
		setActionDefinitionId(ACTION_DEFINITION_ID);
	}

	@Override
	public boolean isEnabled() {
		IFeatureProvider featureProvider = getFeatureProvider();
		if (featureProvider == null) {
			return false;
		}
		ISaveImageFeature feature = featureProvider.getSaveImageFeature();
		ISaveImageContext context = createSaveImageContext();
		if (feature == null || !feature.canSave(context)) {
			return false;
		}

		if (((IAdaptable) diagramBehavior.getDiagramContainer()).getAdapter(GraphicalViewer.class) == null) {
			return false;
		}

		return true;
	}

	@Override
	public void run() {
		ISaveImageContext context = createSaveImageContext();
		IFeatureProvider featureProvider = getFeatureProvider();
		ISaveImageFeature feature = featureProvider.getSaveImageFeature();
		if (feature != null) {
			FeatureCommandWithContext command = new GenericFeatureCommandWithContext(feature, context);
			executeOnCommandStack(command);
		}
	}

	private ISaveImageContext createSaveImageContext() {
		SaveImageContext context = new SaveImageContext();
		return context;
	}

	private IFeatureProvider getFeatureProvider() {
		return configurationProvider.getDiagramTypeProvider().getFeatureProvider();
	}

	private void executeOnCommandStack(ICommand command) {
		CommandStack commandStack = configurationProvider.getDiagramBehavior().getEditDomain().getCommandStack();
		GefCommandWrapper wrapperCommand = new GefCommandWrapper(command, configurationProvider.getDiagramBehavior()
				.getEditingDomain());
		commandStack.execute(wrapperCommand);
	}
}
