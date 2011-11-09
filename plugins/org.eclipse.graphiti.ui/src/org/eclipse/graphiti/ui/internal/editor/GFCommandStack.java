/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mwenz - Bug 351053 - Remove the need for WorkspaceCommandStackImpl
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.command.FeatureCommand;
import org.eclipse.graphiti.internal.command.GFPreparableCommand2;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.command.AddModelObjectCommand;
import org.eclipse.graphiti.ui.internal.command.ContextEntryCommand;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.command.GFCommand;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.command.ReconnectCommand;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

/**
 * The Class GFCommandStack.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCommandStack extends CommandStack implements CommandStackListener {

	static final String OPTION_EXECUTION_INFO = "org.eclipse.graphiti.execution.info";

	private TransactionalCommandStack emfCommandStack;

	private IConfigurationProvider configurationProvider;

	private TransactionalEditingDomain editingDomain;

	public GFCommandStack(IConfigurationProvider configurationProvider, TransactionalEditingDomain editingDomain) {
		org.eclipse.emf.common.command.CommandStack commandStack = editingDomain.getCommandStack();
		if (!(commandStack instanceof TransactionalCommandStack)) {
			IllegalArgumentException e = new IllegalArgumentException(
					"The command stack of the passed editing domain must be a TransactionalCommandStack");
			e.fillInStackTrace();
			throw e;
		}
		emfCommandStack = (TransactionalCommandStack) commandStack;
		emfCommandStack.addCommandStackListener(this);
		setConfigurationProvider(configurationProvider);
		this.editingDomain = editingDomain;
	}

	@Override
	public boolean canRedo() {
		return getEmfCommandStack().canRedo();
	}

	@Override
	public boolean canUndo() {
		return getEmfCommandStack().canUndo();
	}

	@Override
	public void dispose() {
		super.dispose();
		emfCommandStack.removeCommandStackListener(this);
		emfCommandStack = null;
		configurationProvider = null;
		editingDomain = null;
	}

	@Override
	public void execute(Command gefCommand) {
		if (gefCommand == null) {
			return;
		}

		org.eclipse.emf.common.command.Command emfCommand = GraphitiUiInternal.getCommandService().transformFromGefToEmfCommand(gefCommand);
		org.eclipse.emf.common.command.Command gfPreparableCommand = new GFPreparableCommand2(getTransactionalEditingDomain(), emfCommand);

		IToolBehaviorProvider tbp = getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();

		DefaultExecutionInfo executionInfo = new DefaultExecutionInfo();
		GraphitiUiInternal.getCommandService().completeExecutionInfo(executionInfo, gefCommand);
		// Put the execution info object into the options map for consumption within the execution
		Map<String, DefaultExecutionInfo> options = new HashMap<String, DefaultExecutionInfo>(2);
		options.put(OPTION_EXECUTION_INFO, executionInfo);

		tbp.preExecute(executionInfo);
		try {
			getEmfCommandStack().execute(gfPreparableCommand, options);
		} catch (RollbackException e) {
			if (e.getStatus().getSeverity() == IStatus.CANCEL) {
				// Just log it as info (operation was cancelled on purpose) 
				T.racer().log(IStatus.INFO, "GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			} else {
				// Just log it as an error
				T.racer().error("GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			}
		} catch (Exception e) {
			// Just log it as an error
			T.racer().error("GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
		}
		tbp.postExecute(executionInfo);

		// Check if the executed feature has really done changes (indicated by
		// IFeature.hasDoneChanges). If not, remove the operation of the command
		// used to execute the feature from the command stack. It will then also
		// no longer appear as an entry in the undo stack. This is especially
		// necessary for direct editing in a pattern environment when e.g. a new
		// object is created and directly offered for direct editing (handled
		// internally as two different commands because of EMF restrictions).
		// This has been reworked for bug 327756 to enable the call to
		// hasDoneChanges also for other types of features (especially
		// CustomFeatures and CreateFeatures).
		List<IFeature> features = new ArrayList<IFeature>(2);
		if (gefCommand instanceof GefCommandWrapper) {
			GefCommandWrapper gefCommandWrapper = (GefCommandWrapper) gefCommand;
			ICommand command = gefCommandWrapper.getCommand();
			extractFeatures(features, command);
		} else {
			extractFeatures(features, gefCommand);
		}

		boolean changesDone = false;
		// Basic assumption is that no changes were done, only if no features
		// were found we cannot judge if any changes happened, in that case we
		// need to assume that changes happened.
		if (features.size() == 0) {
			changesDone = true;
		}

		// Check if any of the involved features has done changes
		for (Iterator<IFeature> iterator = features.iterator(); iterator.hasNext();) {
			IFeature feature = iterator.next();
			if (feature.hasDoneChanges()) {
				// First change is enough
				changesDone = true;
				break;
			}
		}

		// If no changes were done revert the undo stack entry
		if (!changesDone) {
			// Use the default context and retrieve the last operation
			WorkspaceCommandStackImpl workspaceCommandStackImpl = (WorkspaceCommandStackImpl) getEmfCommandStack();
			IUndoContext context = workspaceCommandStackImpl.getDefaultUndoContext();
			IUndoableOperation operation = workspaceCommandStackImpl.getOperationHistory().getUndoOperation(context);

			// Replace the found operation with an empty set
			workspaceCommandStackImpl.getOperationHistory().replaceOperation(operation, new IUndoableOperation[0]);

			// Update the editor actions bars, especially Edit --> Undo
			notifyListeners(gefCommand, CommandStack.POST_MASK);
		}
	}

	/**
	 * Extracts from the given command (java.lang.Object is used because the
	 * potentially passed objects do not have another common super class) the
	 * list of all involved (executed) features. The features are added to the
	 * passed list.
	 * 
	 * @param features
	 *            The list to which the features will be added.
	 * @param command
	 *            Any kind of command object that is executed on this command
	 *            stack.
	 */
	private void extractFeatures(List<IFeature> features, Object command) {
		if (command instanceof FeatureCommand) {
			// Used in direct editing
			features.add(((FeatureCommand) command).getFeature());
		} else if (command instanceof ContextEntryCommand) {
			// Used for wrapping any feature in a context button
			features.add(((ContextEntryCommand) command).getContextEntry().getFeature());
		} else if (command instanceof AddModelObjectCommand) {
			// Used for add features
			IFeatureAndContext[] innerFeatures = ((AddModelObjectCommand) command).getFeaturesAndContexts();
			for (int i = 0; i < innerFeatures.length; i++) {
				features.add(innerFeatures[i].getFeature());
			}
		} else if (command instanceof CreateConnectionCommand) {
			// Used for connection creation
			IFeatureAndContext[] innerFeatures = ((CreateConnectionCommand) command).getFeaturesAndContexts();
			for (int i = 0; i < innerFeatures.length; i++) {
				features.add(innerFeatures[i].getFeature());
			}
		} else if (command instanceof GFCommand) {
			// Used for object creation
			features.add(((GFCommand) command).getFeature());
		} else if (command instanceof ReconnectCommand) {
			// Used for reconnection features
			features.add(((ReconnectCommand) command).getFeature());
		} else if (command instanceof CommandContainer) {
			// Used for custom features
			ICommand[] innerCommands = ((CommandContainer) command).getCommands();
			for (int i = 0; i < innerCommands.length; i++) {
				extractFeatures(features, innerCommands[i]);
			}
		}
	}

	@Override
	public void flush() {
		super.flush();
	}

	@Override
	public Object[] getCommands() {
		return super.getCommands();
	}

	@Override
	public Command getRedoCommand() {
		org.eclipse.emf.common.command.Command emfCommand = getEmfCommandStack().getRedoCommand();
		return emfCommand != null ? GraphitiUiInternal.getCommandService().transformFromEmfToGefCommand(emfCommand) : null;
	}

	@Override
	public Command getUndoCommand() {
		org.eclipse.emf.common.command.Command emfCommand = getEmfCommandStack().getUndoCommand();
		return emfCommand != null ? GraphitiUiInternal.getCommandService().transformFromEmfToGefCommand(emfCommand) : null;
	}

	@Override
	public boolean isDirty() {
		return super.isDirty();
	}

	@Override
	public void redo() {
		getEmfCommandStack().redo();
	}

	@Override
	public void undo() {
		getEmfCommandStack().undo();
	}

	private TransactionalCommandStack getEmfCommandStack() {
		return emfCommandStack;
	}

	private TransactionalEditingDomain getTransactionalEditingDomain() {
		return editingDomain;
	}

	public void commandStackChanged(EventObject event) {
		notifyListeners();
		notifyListeners(null, 0);
	}

	private IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	private void setConfigurationProvider(IConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

}