package org.eclipse.graphiti.bot.pageobjects;

import java.awt.AWTException;

import java.awt.Robot;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.bot.tests.AbstractGFTests;
import org.eclipse.graphiti.bot.tests.GFOtherTests;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotPerspective;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Encapsulates SWTBot for operations on the active workbench page.
 *
 */
public class PoWorkbenchPage extends PageObject{
	
	/**
	 * @return
	 */
	public SWTBotGefEditor getGefEditor() {
		SWTBotEditor activeEditor = bot.activeEditor();
		String title = activeEditor.getTitle();
		SWTBotGefEditor ed = bot.gefEditor(title);
		return ed;
	}
	
	public int openDiagramEditorFromFile(final IFile diagFile) {
		return syncExec(new IntResult() {
			@Override
			public Integer run() {
				try {
					IEditorPart ed = IDE.openEditor(getWorkbenchPage(), diagFile, org.eclipse.graphiti.ui.editor.DiagramEditor.DIAGRAM_EDITOR_ID);
					GFOtherTests.assertTrue("Editor must be a diagram editor: " + ed, ed instanceof DiagramEditor);
				} catch (PartInitException e) {
					GFOtherTests.fail(e.getMessage());
				}
				return getWorkbenchPage().getEditorReferences().length;
			}
		});
	}
	
	public int openDiagramEditorFromObject(final Diagram diagram, final TransactionalEditingDomain domain) {
		Resource resource = diagram.eResource();
		assertNotNull(resource);

		return syncExec(new IntResult() {
			@Override
			public Integer run() {
				IWorkbenchPage page = getWorkbenchPage();
				try {
					IEditorInput input = DiagramEditorInput.createEditorInput(diagram, domain, GraphitiUi
							.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId()), false);
					IEditorPart ed = IDE.openEditor(page, input, DiagramEditor.DIAGRAM_EDITOR_ID);
					assertTrue("Editor must be a diagram editor: " + ed, ed instanceof DiagramEditor);
				} catch (PartInitException e) {
					fail(e.getMessage());
				}
				return page.getEditorReferences().length;
			}
		});
	}
	
	private IWorkbenchPage getWorkbenchPage() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return page;
	}
	
	public void shutdownEditor(final SWTBotGefEditor diagramEditor) {
		// Close popups.
		syncExec(new VoidResult() {
			@Override
			public void run() {
				// Using SWTBot yields an exception since a keyboard layout file for DE is not available.
				//				bot.activeShell().pressShortcut(SWT.NONE, SWT.ESC);
				try {
					Robot r = new Robot();
					r.keyPress(SWT.ESC);
					r.keyRelease(SWT.ESC);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
		diagramEditor.saveAndClose();
	}
	
	public void shutdownEditor(final DiagramEditor diagramEditor) {
		// Close popups.
		syncExec(new VoidResult() {
			@Override
			public void run() {
				// Using SWTBot yields an exception since a keyboard layout file for DE is not available.
				//				bot.activeShell().pressShortcut(SWT.NONE, SWT.ESC);
				try {
					Robot r = new Robot();
					r.keyPress(SWT.ESC);
					r.keyRelease(SWT.ESC);
				} catch (AWTException e) {
					e.printStackTrace();
				}
				diagramEditor.doSave(new NullProgressMonitor());
				bot.editorByTitle(diagramEditor.getTitle()).close();
			}
		});
	}
	
	public void closeAllEditors() {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.closeAllEditors(false);
			}
		});
	}
	
	public void closeActiveEditor(){
		SWTBotEditor activeEditor = bot.activeEditor();
		activeEditor.close();
	}

	public void openGraphitiTestPerspective() {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				SWTBotPerspective p = bot.perspectiveById("org.eclipse.graphiti.examples.common.perspective.GFPerspective");
				p.activate();
				bot.activeShell().widget.setMaximized(true);
			}
		});
	}

	public void closeWelcomeView(AbstractGFTests abstractGFTests) {
		try {
			bot.viewByTitle("Welcome").close();
		} catch (WidgetNotFoundException e) {
			// do nothing
		}
	}

}
