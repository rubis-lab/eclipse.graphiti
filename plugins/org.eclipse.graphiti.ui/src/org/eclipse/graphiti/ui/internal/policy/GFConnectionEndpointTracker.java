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
 *    mgorning - Bug 343983 - Notification for Cancelled Reconnection Events
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.policy;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.ConnectionEndpointTracker;
import org.eclipse.graphiti.ui.internal.command.ReconnectCommand;

public class GFConnectionEndpointTracker extends ConnectionEndpointTracker {

	public GFConnectionEndpointTracker(ConnectionEditPart cep) {
		super(cep);
	}

	@Override
	public void deactivate() {
		if (getCurrentCommand() instanceof ReconnectCommand) {
			ReconnectCommand cmd = (ReconnectCommand) getCurrentCommand();
			if (cmd != null) {
				cmd.deactivate();
			}
		}
		super.deactivate();
	}

	@Override
	protected boolean handleButtonUp(int button) {
		// Store current command for later usage in deactivate(). Call to
		// super.handleButtonUp() sets current command after execution always to
		// null.
		Command cmd = getCurrentCommand();
		boolean ret = super.handleButtonUp(button);
		setCurrentCommand(cmd);
		return ret;
	}
}
