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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.dnd;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorInternal;
import org.eclipse.swt.dnd.DND;

/**
 * This subclass of TemplateTransferDropTargetListener
 * {@link org.eclipse.gef.dnd.TemplateTransferDropTargetListener} was introduced
 * to change the default behavior. For details on this see csn-message
 * 0120031469 0001576030 2008
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	private DiagramEditorInternal editor;

	public GFTemplateTransferDropTargetListener(EditPartViewer viewer, DiagramEditorInternal editor) {
		super(viewer);
		// it is important to set this value to true
		setEnablementDeterminedByCommand(true);
		this.editor = editor;
	}

	@Override
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();

		if (getTargetEditPart() != null) {
			Command command = getCommand();
			if (command != null && command.canExecute())
				getViewer().getEditDomain().getCommandStack().execute(command);
			else
				getCurrentEvent().detail = DND.DROP_NONE;
		} else
			getCurrentEvent().detail = DND.DROP_NONE;

		if (!editor.isDirectEditingActive()) {
			selectAddedObject();
		}
	}

	private void selectAddedObject() {
		Object model = getCreateRequest().getNewObject();
		if (model == null)
			return;
		EditPartViewer viewer = getViewer();
		viewer.getControl().forceFocus();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) {
			// Force a layout first.
			getViewer().flush();
			viewer.select((EditPart) editpart);
		}
	}
}
