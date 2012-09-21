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
 *    mwenz - Bug 324859 - initial API, implementation and documentation
 *    mwenz - Bug 389380 - Undo/Redo handling wrong Command executed by undo action
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.IContextHolder;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.IFeatureHolder;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.DefaultFeatureAndContext;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.command.GFPreparableCommand2;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.tb.IContextEntry;
import org.eclipse.graphiti.ui.internal.command.AddModelObjectCommand;
import org.eclipse.graphiti.ui.internal.command.ContextEntryCommand;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.command.GFCommand;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.editor.EmfOnGefCommand;
import org.eclipse.graphiti.ui.internal.editor.GefOnEmfCommand;
import org.eclipse.graphiti.ui.internal.services.ICommandService;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CommandService implements ICommandService {

	public org.eclipse.emf.common.command.Command transformFromGefToEmfCommand(org.eclipse.gef.commands.Command gefCommand) {
		if (gefCommand instanceof GefOnEmfCommand) {
			GefOnEmfCommand gefOnEmfCommand = (GefOnEmfCommand) gefCommand;
			return gefOnEmfCommand.getEmfCommand();
		}
		return new EmfOnGefCommand(gefCommand);
	}

	public org.eclipse.gef.commands.Command transformFromEmfToGefCommand(org.eclipse.emf.common.command.Command emfCommand) {
		if (emfCommand instanceof EmfOnGefCommand) {
			EmfOnGefCommand emfOnGefCommand = (EmfOnGefCommand) emfCommand;
			return emfOnGefCommand.getGefCommand();
		} else if (emfCommand instanceof GFPreparableCommand2) {
			Command command = ((GFPreparableCommand2) emfCommand).getCommand();
			return transformFromEmfToGefCommand(command);
		}
		return new GefOnEmfCommand(emfCommand);
	}

	public DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, org.eclipse.gef.commands.Command gefCommand) {
		if (gefCommand instanceof CompoundCommand) {
			CompoundCommand compoundCommand = (CompoundCommand) gefCommand;
			@SuppressWarnings("unchecked")
			final List<org.eclipse.gef.commands.Command> commands = compoundCommand.getCommands();
			for (org.eclipse.gef.commands.Command childCommand : commands) {
				completeExecutionInfo(executionInfo, childCommand);
			}
		}
		if (gefCommand instanceof CreateConnectionCommand) {
			CreateConnectionCommand createConnectionCommand = (CreateConnectionCommand) gefCommand;
			final IFeatureAndContext[] features = createConnectionCommand.getFeaturesAndContexts();
			for (IFeatureAndContext feature : features) {
				executionInfo.addFeatureAndContext(feature);
			}
		}
		if (gefCommand instanceof GefCommandWrapper) {
			GefCommandWrapper gefCommandWrapper = (GefCommandWrapper) gefCommand;
			final ICommand graphitiCommand = gefCommandWrapper.getCommand();
			completeExecutionInfo(executionInfo, graphitiCommand);
		}
		if (gefCommand instanceof GFCommand) {
			final GFCommand gfCommand = (GFCommand) gefCommand;
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(gfCommand.getFeature(), gfCommand.getContext()));
		}
		if (gefCommand instanceof ContextEntryCommand) {
			ContextEntryCommand cec = (ContextEntryCommand) gefCommand;
			IContextEntry contextEntry = cec.getContextEntry();
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(contextEntry.getFeature(), contextEntry.getContext()));
		}
		if (gefCommand instanceof AddModelObjectCommand) {
			AddModelObjectCommand addModelObjectCommand = (AddModelObjectCommand) gefCommand;
			final IFeatureAndContext[] features = addModelObjectCommand.getFeaturesAndContexts();
			for (IFeatureAndContext feature : features) {
				executionInfo.addFeatureAndContext(feature);
			}
		}

		return executionInfo;
	}

	public DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, ICommand gfCommand) {
		if (gfCommand instanceof CommandContainer) {
			CommandContainer cc = (CommandContainer) gfCommand;
			final ICommand[] childCommands = cc.getCommands();
			for (int i = 0; i < childCommands.length; i++) {
				ICommand childCommand = childCommands[i];
				completeExecutionInfo(executionInfo, childCommand);
			}
		}

		else {
			IContext context = null;
			IFeature feature = null;
			if (gfCommand instanceof IFeatureHolder) {
				IFeatureHolder featureHolder = (IFeatureHolder) gfCommand;
				feature = featureHolder.getFeature();
			}
			if (gfCommand instanceof IContextHolder) {
				IContextHolder contextHolder = (IContextHolder) gfCommand;
				context = contextHolder.getContext();
			}
			executionInfo.addFeatureAndContext(new DefaultFeatureAndContext(feature, context));
		}

		return executionInfo;
	}

	public IExecutionInfo removeFeaturesWithoutChanges(IExecutionInfo executionInfo) {
		DefaultExecutionInfo result = new DefaultExecutionInfo();
		for (IFeatureAndContext featureAndContext : executionInfo.getExecutionList()) {
			if (featureAndContext.getFeature().hasDoneChanges()) {
				result.addFeatureAndContext(featureAndContext);
			}
		}
		return result;
	}
}
