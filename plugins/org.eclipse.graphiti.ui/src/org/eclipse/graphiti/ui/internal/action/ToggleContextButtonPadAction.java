/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Felix Velasco (mwenz) - Bug 323351 - Enable to suppress/reactivate the speed buttons
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.graphiti.ui.editor.DiagramSupport;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.contextbuttons.IContextButtonManager;
import org.eclipse.jface.action.Action;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ToggleContextButtonPadAction extends Action {

	private DiagramSupport diagramSupport;
	
	public static final String TOOL_TIP = Messages.ToggleContextButtonPadAction_0_xmsg;
	
	public static final String TEXT = Messages.ToggleContextButtonPadAction_1_xfld;
	
	public static final String ACTION_ID = "toggle_context_button_pad"; //$NON-NLS-1$
	
	public ToggleContextButtonPadAction(DiagramSupport diagramSupport) {
		super();
		this.diagramSupport = diagramSupport;
		setText(TEXT);
		setToolTipText(TOOL_TIP);
		setId(ACTION_ID);
	}

	@Override
	public void run() {
		IContextButtonManager contextButtonManager = (IContextButtonManager) diagramSupport
				.getAdapter(IContextButtonManager.class);
		contextButtonManager.setContextButtonShowing(!isChecked());
	}
}