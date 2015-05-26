/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Eliminated assumption that diagram is in an IEditorPart
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    jsivadier - Bug 467502 - Improve DiagramComposite implementation without IWorkbenchPart
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
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Closes editor if model element was deleted. For instance, if only the diagram
 * object is deleted from the resource, this listeners handles closing the
 * editor. Probably this is a very rare case.
 * 
 * @since 0.9
 */
public final class ElementDeleteListener extends AdapterImpl {

	private DiagramBehavior diagramBehavior;

	/**
	 * @since 0.10
	 */
	public ElementDeleteListener(DiagramBehavior diagramBehavior) {
		this.diagramBehavior = diagramBehavior;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type instanceof EObject;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (T.racer().debug()) {
			final String editorName = diagramBehavior.getDiagramContainer().getTitle();
			T.racer().debug("Delete listener called of editor " //$NON-NLS-1$
					+ editorName + " with events " + msg.toString()); //$NON-NLS-1$
		}

		final IDiagramEditorInput in = diagramBehavior.getDiagramContainer().getDiagramEditorInput();
		if (in != null) {
			final IWorkbenchPartSite site = diagramBehavior.getDiagramContainer().getSite();
			if (site == null) {
				return;
			}
			final Shell shell = site.getShell();
			// Do the real work, e.g. object retrieval from input and
			// closing, asynchronous to not block this listener longer than necessary,
			// which may provoke deadlocks.
			shell.getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (diagramBehavior == null) {
						return; // disposed
					}
					if (shell.isDisposed()) {
						return; // disposed
					}
					Diagram diagram = null;
					try {
						diagram = (Diagram) diagramBehavior.getAdapter(Diagram.class);
					} catch (final Exception e) {
						// Ignore, exception indicates that the diagram has
						// been deleted
					}
					if (diagram == null || EcoreUtil.getRootContainer(diagram) == null) {
						// diagram is gone so try to close
						if (T.racer().debug()) {
							final String editorName = diagramBehavior.getDiagramContainer().getTitle();
							T.racer().debug("Closing editor " + editorName); //$NON-NLS-1$
						}
						diagramBehavior.getDiagramContainer().close();
					}
				}
			});
		}
	}
}