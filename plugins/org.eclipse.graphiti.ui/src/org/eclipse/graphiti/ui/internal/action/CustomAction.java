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
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramEditorUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.ui.editor.IDiagramEditorUI;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.jface.action.Action;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CustomAction extends Action {

	private ICustomFeature customFeature;

	private ICustomContext context;

	private IDiagramEditorUI diagramEditor;

	public CustomAction(ICustomFeature customFeature, ICustomContext context, IDiagramEditorUI diagramEditor) {
		super();
		this.customFeature = customFeature;
		this.context = context;
		this.diagramEditor = diagramEditor;
		setText(customFeature.getName());
		setToolTipText(customFeature.getDescription());
	}

	@Override
	public boolean isEnabled() {
		return customFeature.canExecute(context);
	}

	@Override
	public void run() {
		CommandContainer commandContainer = new CommandContainer(customFeature.getFeatureProvider());
		commandContainer.add(new GenericFeatureCommandWithContext(customFeature, context));
		executeOnCommandStack(commandContainer);
	}

	protected void executeOnCommandStack(ICommand command) {
		CommandStack commandStack = diagramEditor.getEditDomain().getCommandStack();
		commandStack.execute(new GefCommandWrapper(command, diagramEditor.getEditingDomain()));
	}
}