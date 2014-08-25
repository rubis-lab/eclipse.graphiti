/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    fvelasco - Bug 323349 External feature invocation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FeatureExecutionHandler extends AbstractHandler {

	public static final String COMMAND_ID = "org.eclipse.graphiti.ui.featureExecutionCommand"; //$NON-NLS-1$
	// org.eclipse.graphiti.ui.featureExecutionCommand
	// org.eclipse.graphiti.ui.featureExecutionCommand
	private static final String HINT_PARAMETER_ID = "org.eclipse.graphiti.ui.featureHint";

	private IConfigurationProvider configurationProvider;

	public FeatureExecutionHandler(IConfigurationProvider configurationProvider) {
		super();
		this.configurationProvider = configurationProvider;
	}

	protected PictogramElement[] getSelectedPictogramElements(ExecutionEvent event) {
		ISelection selectedObjects = HandlerUtil.getCurrentSelection(event);
		List<Object> list = new ArrayList<Object>();
		if (selectedObjects instanceof IStructuredSelection) {
			for (Iterator<?> iter = ((IStructuredSelection) selectedObjects).iterator(); iter.hasNext();) {
				Object o = iter.next();
				if (o instanceof EditPart) {
					EditPart editPart = (EditPart) o;
					if (editPart.getModel() instanceof PictogramElement) {
						list.add(editPart.getModel());
					}
				}
			}
		}

		return list.toArray(new PictogramElement[0]);
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IToolBehaviorProvider featureProvider = getToolBehaviorProvider();
		// CommandContainer commandContainer = new
		// CommandContainer(featureProvider);

		PictogramElement pes[] = getSelectedPictogramElements(event);
		CustomContext context = new CustomContext(pes);
		IFeature feature = featureProvider.getCommandFeature(context, event.getParameter(HINT_PARAMETER_ID));
		if (feature != null && feature.canExecute(context)) {
			// commandContainer.add(new
			// GenericFeatureCommandWithContext(feature, context));
			executeOnCommandStack(new GenericFeatureCommandWithContext(feature, context));
		}

		// executeOnCommandStack(cmd);

		return null;
	}

	private IToolBehaviorProvider getToolBehaviorProvider() {
		return getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
	}

	protected IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	protected void executeOnCommandStack(ICommand command) {
		CommandStack commandStack = getConfigurationProvider().getDiagramContainer().getEditDomain().getCommandStack();
		commandStack.execute(new GefCommandWrapper(command, getConfigurationProvider().getDiagramBehavior()
				.getEditingDomain()));
	}
}