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
package org.eclipse.graphiti.bot.tests.swtbot;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.ui.internal.editor.GFFigureCanvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

public class GFSWTBotGefEditor extends SWTBotGefEditor {

	/**
	 * @param reference
	 * @param bot
	 * @throws WidgetNotFoundException
	 */
	public GFSWTBotGefEditor(IEditorReference reference, SWTWorkbenchBot bot) throws WidgetNotFoundException {
		super(reference, bot);
	}

	protected void init() throws WidgetNotFoundException {
		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				final IEditorPart editor = partReference.getEditor(true);
				graphicalViewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
				final Control control = graphicalViewer.getControl();
				if (control instanceof GFFigureCanvas) {
					GFFigureCanvas c = (GFFigureCanvas) control;
					LightweightSystem l = c.getLightweightSystem();
					canvas = new SWTBotGefFigureCanvas(c, l);
				} else if (control instanceof FigureCanvas) {
					canvas = new SWTBotGefFigureCanvas((FigureCanvas) control);
				}
				editDomain = graphicalViewer.getEditDomain();
			}
		});

		if (graphicalViewer == null) {
			throw new WidgetNotFoundException("Editor does not adapt to a GraphicalViewer");
		}
	}

	public SWTBotGefFigureCanvas getGFCanvas() {
		return getCanvas();
	}

}
