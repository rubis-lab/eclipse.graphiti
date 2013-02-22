/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Ali Akar, mwenz - Bug 348420 - Opening a user contributed editor
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramEditorUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.ui.editor.IDiagramEditorUI;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IWorkbenchService {

	/**
	 * Method returns the current {@link StatusLineManager}. If no
	 * StatusLineManager is available, a new StatusLineManager will be created
	 * and returned. In most cases this will not have any effect and will not be
	 * able to display status info, but it makes null pointer checks when
	 * calling the method obsolete
	 * 
	 * @return the status line manager of the active part or a new one if there
	 *         is no such part
	 */
	public IStatusLineManager getActiveStatusLineManager();

	/**
	 * @return the active workbench window, or the first one if there is no
	 *         active window
	 */
	public IWorkbenchWindow getActiveOrFirstWorkbenchWindow();

	/**
	 * Opens the given diagram in the diagram editor.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @return the editor instance
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram);

	/**
	 * Opens the given diagram in the diagram editor.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @param providerId
	 *            the unique provider id of a diagram type provider which will
	 *            be used by the editor.
	 * @return the editor instance
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram, String providerId);

	/**
	 * Opens the given diagram in the diagram editor with the given id.
	 * 
	 * @param diagram
	 *            which should be opened
	 * @param domain
	 * @param providerId
	 *            the unique provider id of a diagram type provider which will
	 *            be used by the editor.
	 * @param editorId
	 *            the unique Eclipse editor id of the diagram editor to open.
	 *            This id must belong to a subclass of {@link IDiagramEditorUI} .
	 * @return the editor instance
	 * @since 0.8.0
	 */
	public IDiagramEditor openDiagramEditor(Diagram diagram, String providerId, String editorId);

	/**
	 * Returns the shell of the active workbench window.
	 * 
	 * @return the shell
	 */
	public Shell getShell();

}