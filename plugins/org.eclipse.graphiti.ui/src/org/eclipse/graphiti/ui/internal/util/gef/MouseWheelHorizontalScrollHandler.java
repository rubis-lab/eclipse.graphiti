/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    fvelasco - Bug 403664 - Enable DoubleClickFeature on the diagram background
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.gef;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.ui.internal.editor.GFFigureCanvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ScrollBar;

public class MouseWheelHorizontalScrollHandler implements MouseWheelHandler {

	/**
	 * The Singleton
	 */
	public static final MouseWheelHandler SINGLETON = new MouseWheelHorizontalScrollHandler();

	private MouseWheelHorizontalScrollHandler() {
	}

	/**
	 * Horizontally scroll the given viewer.
	 * 
	 * @see MouseWheelHandler#handleMouseWheel(Event, EditPartViewer)
	 */
	public void handleMouseWheel(Event event, EditPartViewer viewer) {
		if (viewer instanceof ScrollingGraphicalViewer) {
			Control control = viewer.getControl();
			if (control instanceof FigureCanvas) {
				FigureCanvas canvas = (FigureCanvas) control;
				ScrollBar hBar = canvas.getHorizontalBar();

				int value = hBar.getSelection() + (hBar.getIncrement() * event.count);
				canvas.scrollToX(value);
				event.doit = false;
			} else if (control instanceof GFFigureCanvas) {
				GFFigureCanvas canvas = (GFFigureCanvas) control;
				ScrollBar hBar = canvas.getHorizontalBar();

				int value = hBar.getSelection() + (hBar.getIncrement() * event.count);
				int y = canvas.getViewport().getViewLocation().y;
				canvas.scrollTo(value, y);
				event.doit = false;
			}
		}
	}

}