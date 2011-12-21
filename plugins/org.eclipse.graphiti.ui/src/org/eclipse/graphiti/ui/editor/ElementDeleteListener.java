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
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Closes editor if model element was deleted. For instance, if only the diagram
 * object is deleted from the resource, this listeners handles closing the
 * editor. Probably this is a very rare case.
 * 
 * @since 0.9
 */
public final class ElementDeleteListener extends AdapterImpl {

	private DiagramEditor diagramEditor;

	public ElementDeleteListener(DiagramEditor d) {
		this.diagramEditor = d;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type instanceof EObject;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (T.racer().debug()) {
			final String editorName = diagramEditor.getTitle();
			T.racer().debug("Delete listener called of editor " //$NON-NLS-1$
					+ editorName + " with events " + msg.toString()); //$NON-NLS-1$
		}

		final IEditorInput in = diagramEditor.getEditorInput();
		if (in != null) {
			final IEditorSite site = diagramEditor.getEditorSite();
			if (site == null) {
				return;
			}
			final Shell shell = site.getShell();
			// Do the real work, e.g. object retrieval from input and
			// closing, asynchronous to not block this listener longer than necessary,
			// which may provoke deadlocks.
			shell.getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (diagramEditor == null) {
						return; // disposed
					}
					if (shell.isDisposed()) {
						return; // disposed
					}
					Diagram diagram = null;
					try {
						diagram = (Diagram) diagramEditor.getAdapter(Diagram.class);
					} catch (final Exception e) {
						// Ignore, exception indicates that the diagram has
						// been deleted
					}
					if (diagram == null || EcoreUtil.getRootContainer(diagram) == null) {
						// diagram is gone so try to close
						final IWorkbenchPage page = site.getPage();
						if (T.racer().debug()) {
							final String editorName = diagramEditor.getTitle();
							T.racer().debug("Closing editor " + editorName); //$NON-NLS-1$
						}
						page.closeEditor(diagramEditor, false);
					}
				}
			});
		}
	}
}