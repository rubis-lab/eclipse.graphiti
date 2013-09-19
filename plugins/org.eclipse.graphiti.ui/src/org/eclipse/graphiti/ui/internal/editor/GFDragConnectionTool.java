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
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    fvelasco - Bug 417577 - state call backs review
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.requests.ContextButtonDragRequest;

/**
 * The Class GFDragConnectionTool.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFDragConnectionTool extends ConnectionDragCreationTool {

	public GFDragConnectionTool(DiagramBehavior diagramBehavior, ContextButtonEntry contextButtonEntry) {
		this.diagramBehavior = diagramBehavior;
		this.contextButtonEntry = contextButtonEntry;
	}

	/**
	 * changed order: feedback gets deleted after command is executed (popup!).
	 * 
	 * @return true, if handle create connection
	 */
	@Override
	protected boolean handleCreateConnection() {

		Command endCommand = getCommand();
		setCurrentCommand(endCommand);
		if (endCommand == null || !endCommand.canExecute()) {
			for (IFeatureAndContext ifac : getCreateConnectionFeaturesAndContext()) {
				ICreateConnectionFeature ccf = (ICreateConnectionFeature) ifac.getFeature();
				ccf.canceledAttaching((ICreateConnectionContext) ifac.getContext());
			}
		}
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
		setViewer(diagramBehavior.getDiagramContainer().getGraphicalViewer());
		diagramBehavior.getEditDomain().setActiveTool(diagramBehavior.getEditDomain().getDefaultTool());
		return b;

	}

	@Override
	protected void handleFinished() {
		for (IFeatureAndContext ifac : getCreateConnectionFeaturesAndContext()) {
			ICreateConnectionFeature ccf = (ICreateConnectionFeature) ifac.getFeature();
			ccf.endConnecting();
		}

		super.handleFinished();
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

	private DiagramBehavior diagramBehavior;

	private ContextButtonEntry contextButtonEntry;

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
	public void continueConnection(EditPart targetEditPart,
			EditPart targetTargetEditPart) {

		setConnectionSource(targetEditPart);
		lockTargetEditPart(targetEditPart);

		CreateConnectionRequest createConnectionRequest = ((CreateConnectionRequest) getTargetRequest());
		createConnectionRequest.setSourceEditPart(targetEditPart);
		createConnectionRequest.setTargetEditPart(targetTargetEditPart);
		Command command = getCommand();
		if (command != null) {
			setCurrentCommand(command);
			if (stateTransition(STATE_INITIAL, STATE_CONNECTION_STARTED)) {
				for (IFeatureAndContext ifac : getCreateConnectionFeaturesAndContext()) {
					ICreateConnectionFeature ccf = (ICreateConnectionFeature) ifac.getFeature();
					ccf.startConnecting();
					ccf.attachedToSource((ICreateConnectionContext) ifac.getContext());
				}
			}
		}

		setViewer(diagramBehavior.getDiagramContainer().getGraphicalViewer());
		handleDrag();
		unlockTargetEditPart();
	}

	private Iterable<IFeatureAndContext> getCreateConnectionFeaturesAndContext() {
		if (getTargetRequest() instanceof CreateConnectionRequest) {
			List<IFeatureAndContext> ret = new ArrayList<IFeatureAndContext>();
			CreateConnectionRequest r = (CreateConnectionRequest) getTargetRequest();
			if (r.getStartCommand() instanceof CreateConnectionCommand) {
				CreateConnectionCommand cmd = (CreateConnectionCommand) r.getStartCommand();
				for (IFeatureAndContext ifac : cmd.getFeaturesAndContexts()) {
					if (ifac.getFeature() instanceof ICreateConnectionFeature) {
						ret.add(ifac);
					}
				}
			}
			return ret;
		}
		return Collections.emptyList();
	}

	protected boolean handleMove() {
		if (isInState(STATE_CONNECTION_STARTED | STATE_INITIAL | STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
			updateTargetRequest();
			updateTargetUnderMouse();
			showSourceFeedback();
			showTargetFeedback();
			setCurrentCommand(getCommand());
		}
		return true;
	}

	@Override
	protected Point getLocation() {
		Point absoluteMousePosition = diagramBehavior.getMouseLocation();
		return absoluteMousePosition;
	}
}
