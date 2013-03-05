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
 *    pjpaulin - Bug 352120 - Eliminated assumption that diagram is in an IEditorPart
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.graphiti.ui.editor.DiagramSupport;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;

/**
 * Manages changes done to the resources tied to the diagram outside of the
 * editor's TransactionalEditingDomain.
 */
public class DomainModelWorkspaceSynchronizerDelegate implements WorkspaceSynchronizer.Delegate {

	private IDiagramContainerUI diagramEditor;
	private DiagramSupport diagramSupport;

	/**
	 * The DiagramEditorBehavior reacts on a setResourceChanged(true) if he gets
	 * activated.
	 */
	public DomainModelWorkspaceSynchronizerDelegate(IDiagramContainerUI diagramEditor) {
		this.diagramEditor = diagramEditor;
		this.diagramSupport = diagramEditor.getDiagramSupport();
	}

	public void dispose() { 
		diagramEditor = null;

	}

	public boolean handleResourceChanged(Resource resource) {
		IFile file = WorkspaceSynchronizer.getUnderlyingFile(resource);
		// Since we cannot get timestamp information, we have to be pessimistic
		if (file == null){
			diagramSupport.getUpdateBehavior().setResourceChanged(true);
			return true;
		}
		// if file does not exist the getLocalTimeStamp method will return
		// NULL_TIMESTAMP and we will also get a refresh
		if (file.getLocalTimeStamp() != resource.getTimeStamp()) {
			diagramSupport.getUpdateBehavior().setResourceChanged(true);
			return true;
		}
		return true;
	}

	public boolean handleResourceDeleted(Resource resource) {
		// This will lead to an unload of the resource, which is quite what we want.
		// As a follow up of the unload the editor is closed.
		return false;
	}

	public boolean handleResourceMoved(Resource resource, URI newURI) {
		// Handle rename or move of resource.
		// ((ResourceSetImpl)
		// (ResourceSetimpl(deb.getEditingDomain().getResourceSet())).getURIResourceMap().remove(resource.getURI());
		// Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)deb.getEditingDomain().getResourceSet()).getURIResourceMap();
		resource.setURI(newURI);
		IDiagramEditorInput editorInput = diagramEditor.getDiagramEditorInput();
		if (editorInput != null) {
			((IDiagramEditorInput) editorInput).updateUri(newURI);
		}
		diagramSupport.refreshContent();
		return true;
	}

}
