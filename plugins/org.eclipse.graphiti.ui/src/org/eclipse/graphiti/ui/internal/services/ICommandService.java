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
 *    mwenz - Bug 324859 - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.graphiti.internal.command.DefaultExecutionInfo;
import org.eclipse.graphiti.internal.command.ICommand;

/**
 * Internal utility class for commands (EMF and GEF)<br>
 * - Transform into each other<br>
 * - Extract features from commands<br>
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface ICommandService {

	DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, ICommand gfCommand);

	DefaultExecutionInfo completeExecutionInfo(DefaultExecutionInfo executionInfo, org.eclipse.gef.commands.Command gefCommand);

	org.eclipse.gef.commands.Command transformFromEmfToGefCommand(org.eclipse.emf.common.command.Command emfCommand);

	org.eclipse.emf.common.command.Command transformFromGefToEmfCommand(org.eclipse.gef.commands.Command gefCommand);

}
