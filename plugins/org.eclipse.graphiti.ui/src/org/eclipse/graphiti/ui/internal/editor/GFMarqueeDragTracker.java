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
 *    fvelasco - Bug 403664 - Enable DoubleClickFeature on the diagram background
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;

/**
 * Drag tracker to promote GFMarqueeSelectionTool.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFMarqueeDragTracker extends GFMarqueeSelectionTool implements DragTracker {

	private EditPart editPart;

	public GFMarqueeDragTracker(EditPart editPart) {
		this.editPart = editPart;
	}

	/**
	 * Called when the mouse button is released. Overridden to do nothing, since
	 * a drag tracker does not need to unload when finished.
	 */
	@Override
	protected void handleFinished() {
	}

	@Override
	protected boolean handleDoubleClick(int button) {
		SelectionRequest request = new SelectionRequest();
		request.setLocation(getLocation());
		request.setType(RequestConstants.REQ_OPEN);
		editPart.performRequest(request);
		
		return true;
	}
}
