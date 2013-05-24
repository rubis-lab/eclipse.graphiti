/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - initial API implementation

 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.diagramviewer;

import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;

public class DiagramViewerBehavior extends DiagramBehavior {

	public DiagramViewerBehavior(IDiagramContainerUI diagramContainer) {
		super(diagramContainer);
	}

	@Override
	protected void setInput(IDiagramEditorInput input) {
		// Force the diagram provider to be the Diagram Viewer (otherwise the
		// diagram would use its default diagram type provider)
		input.setProviderId(Activator.DIAGRAM_VIEWER_DIAGRAM_TYPE_PROVIDER);

		super.setInput(input);
	}
}
