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
 *    jpasch - Bug 323025 ActionBarContributor cleanup
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.action.Action;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class SaveImageAction extends Action {

	private ISaveImageFeature saveImageFeature;

	private ISaveImageContext context;

	private IDiagramContainerUI graphicsEditor;
	
	public static final String TOOL_TIP = Messages.SaveImageAction_1_xmsg;
	
	public static final String TEXT = Messages.SaveImageAction_0_xmsg;
	
	public static final String ACTION_ID = "export_diagram_action"; //$NON-NLS-1$
	
	public static final String ACTION_DEFINITION_ID = "org.eclipse.graphiti.ui.internal.action.SaveImageAction"; //$NON-NLS-1$

	public SaveImageAction(ISaveImageFeature saveImageFeature, ISaveImageContext context, IDiagramContainerUI graphicsEditor) {
		super();
		this.saveImageFeature = saveImageFeature;
		this.context = context;
		this.graphicsEditor = graphicsEditor;
		setText(TEXT);
		setToolTipText(TOOL_TIP);
		setId(ACTION_ID);
		setActionDefinitionId(ACTION_DEFINITION_ID);
	}

	@Override
	public boolean isEnabled() {
		return saveImageFeature.canSave(context);
	}

	@Override
	public void run() {
		saveImageFeature.preSave(context);

		// get viewer and start save-image-dialog
		GraphicalViewer viewer = (GraphicalViewer) graphicsEditor.getAdapter(GraphicalViewer.class);
		GraphitiUiInternal.getUiService().startSaveAsImageDialog(viewer);

		saveImageFeature.postSave(context);
	}
}