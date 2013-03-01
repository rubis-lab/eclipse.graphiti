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
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.requests.ContextButtonDragRequest;

/**
 * The Class GFDragConnectionTool.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFDragConnectionTool extends ConnectionDragCreationTool {

	/**
	 * changed order: feedback gets deleted after command is executed (popup!).
	 * 
	 * @return true, if handle create connection
	 */
	@Override
	protected boolean handleCreateConnection() {

		Command endCommand = getCommand();
		setCurrentCommand(endCommand);
		executeCurrentCommand();
		eraseSourceFeedback();

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.ConnectionDragCreationTool#handleButtonUp(int)
	 */
	@Override
	protected boolean handleButtonUp(int button) {
		boolean b = super.handleButtonUp(button);
		setViewer(diagramEditor.getGraphicalViewer());
		diagramEditor.getEditDomain().setActiveTool(diagramEditor.getEditDomain().getDefaultTool());
		return b;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.tools.AbstractConnectionCreationTool#createTargetRequest
	 * ()
	 */
	@Override
	protected Request createTargetRequest() {
		ContextButtonDragRequest request = new ContextButtonDragRequest();
		request.setType(getCommandName());
		request.setContextButtonEntry(contextButtonEntry);
		return request;
	}

	private IDiagramContainerUI diagramEditor;

	private ContextButtonEntry contextButtonEntry;

	/**
	 * Start connection.
	 * 
	 * @param targetEditPart
	 *            the target edit part
	 * @param diagramEditor
	 *            the diagram editor
	 * @param contextButtonEntry
	 *            the context button entry
	 */
	public void startConnection(EditPart targetEditPart, IDiagramContainerUI diagramEditor, ContextButtonEntry contextButtonEntry) {

		this.diagramEditor = diagramEditor;
		this.contextButtonEntry = contextButtonEntry;
		activate();
		setConnectionSource(targetEditPart);
		lockTargetEditPart(targetEditPart);

		((CreateConnectionRequest) getTargetRequest()).setSourceEditPart(getTargetEditPart());
		Command command = getCommand();
		if (command != null) {
			setCurrentCommand(command);
			setState(STATE_CONNECTION_STARTED);
		}

		handleDrag();
		setViewer(diagramEditor.getGraphicalViewer());
		unlockTargetEditPart();
	}

	/**
	 * Continue connection.
	 * 
	 * @param targetEditPart
	 *            the target edit part
	 * @param diagramEditor
	 *            the diagram editor
	 * @param contextButtonEntry
	 *            the context button entry
	 * @param targetTargetEditPart
	 *            the target target edit part
	 */
	public void continueConnection(EditPart targetEditPart, IDiagramContainerUI diagramEditor, ContextButtonEntry contextButtonEntry,
			EditPart targetTargetEditPart) {

		this.diagramEditor = diagramEditor;
		this.contextButtonEntry = contextButtonEntry;
		activate();
		setConnectionSource(targetEditPart);
		lockTargetEditPart(targetEditPart);

		CreateConnectionRequest createConnectionRequest = ((CreateConnectionRequest) getTargetRequest());
		createConnectionRequest.setSourceEditPart(targetEditPart);
		createConnectionRequest.setTargetEditPart(targetTargetEditPart);
		Command command = getCommand();
		if (command != null) {
			setCurrentCommand(command);
			setState(STATE_CONNECTION_STARTED);
		}

		handleDrag();
		setViewer(diagramEditor.getGraphicalViewer());
		unlockTargetEditPart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.tools.AbstractConnectionCreationTool#updateTargetRequest
	 * ()
	 */
	@Override
	protected void updateTargetRequest() {
		// setViewer(diagramEditor.getGraphicalViewer());
		// unlockTargetEditPart();
		updateTargetUnderMouse();

		CreateConnectionRequest request = (CreateConnectionRequest) getTargetRequest();
		request.setType(getCommandName());
		//

		Point absoluteMousePosition = diagramEditor.getDiagramSupport().getMouseLocation();
		request.setLocation(absoluteMousePosition);

	}

	@Override
	protected void setState(int state) {
		if (state == STATE_CONNECTION_STARTED) {
			Command cmd = getCurrentCommand();
			if (cmd instanceof CreateConnectionCommand) {
				((CreateConnectionCommand) cmd).connectionStarted();
			}
		}
		super.setState(state);
	}

}
