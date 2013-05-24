package org.eclipse.graphiti.examples.diagramviewer.view;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.examples.diagramviewer.Activator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Opens the Diagram Viewer as a view
 */
public final class OpenDiagramViewerHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {

		// Get the current selection
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}

		// Operation must be started on ECLasses node -> cancel if not
		Object first = ((IStructuredSelection) selection).getFirstElement();
		if (!(first instanceof IFile)) {
			return null;
		}

		IFile file = (IFile) first;
		URI uri = URI.createFileURI(file.getFullPath().toString());

		IViewPart diagramViewerPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView(Activator.DIAGRAM_VIEWER_VIEWER_ID);

		if (diagramViewerPart == null) {
			try {
				diagramViewerPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.showView(Activator.DIAGRAM_VIEWER_VIEWER_ID);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			diagramViewerPart.setFocus();
		}

		((DiagramViewerView) diagramViewerPart).showDiagram(uri);

		return null;
	}
}