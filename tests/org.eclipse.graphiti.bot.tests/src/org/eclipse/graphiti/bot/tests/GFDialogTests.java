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
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.asyncExec;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.graphiti.bot.tests.util.ITestConstants;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.action.PrintGraphicalViewerAction;
import org.eclipse.graphiti.ui.internal.action.SaveImageAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Test;

public class GFDialogTests extends AbstractGFTests {

	public GFDialogTests() {
		super();
	}

	@Test
	public void testPrintDialog() throws Exception {
		final DiagramEditor diagramEditor = openDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		final boolean[] enabled = new boolean[1];
		final CountDownLatch signal = new CountDownLatch(1);
		asyncExec(new VoidResult() {
			public void run() {
				IDiagramTypeProvider dtp = diagramEditor.getDiagramTypeProvider();
				IAction printGraphicalViewerAction = new PrintGraphicalViewerAction(diagramEditor,
						getConfigProviderMock(dtp, diagramEditor));
				// check if default printer is configured, otherwise SWT throws
				// a "no more handles" error in Printer.checkNull(..)
				enabled[0] = printGraphicalViewerAction.isEnabled();
				if (enabled[0])
					printGraphicalViewerAction.run();
				signal.countDown();
			}
		});
		signal.await(5, TimeUnit.SECONDS);
		// If the action is not enabled since there is no printer available on
		// the system, we simply return.
		if (!enabled[0]) {
			return;
		}
		bot.waitUntil(Conditions.shellIsActive(Messages.PrintFigureDialog_3_xfld));
		SWTBotShell shell = bot.shell(Messages.PrintFigureDialog_3_xfld);
		Thread.sleep(1000);
		shell.bot().button("Cancel").click();
		Thread.sleep(300);
		bot.waitUntil(Conditions.shellCloses(shell));
		page.closeActiveEditor();

	}

	@Test
	public void testSaveDialog() throws Exception {
		final DiagramEditor diagramEditor = openDiagram(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		final boolean[] enabled = new boolean[1];

		asyncExec(new VoidResult() {
			public void run() {
				IDiagramTypeProvider dtp = diagramEditor.getDiagramTypeProvider();
				IAction saveImageAction = new SaveImageAction(diagramEditor, getConfigProviderMock(dtp, diagramEditor));
				// check if default printer is configured, otherwise SWT throws
				// a "no more handles" error in Printer.checkNull(..)
				enabled[0] = saveImageAction.isEnabled();
				if (enabled[0])
					saveImageAction.run();
			}
		});

		bot.waitUntil(Conditions.shellIsActive(Messages.SaveFigureAsImageDialog_5_xtxt));
		SWTBotShell shell = bot.shell(Messages.SaveFigureAsImageDialog_5_xtxt);
		Thread.sleep(2000);
		shell.bot().button("Cancel").click();
		Thread.sleep(300);
		bot.waitUntil(Conditions.shellCloses(shell));
		page.closeActiveEditor();

	}
}
