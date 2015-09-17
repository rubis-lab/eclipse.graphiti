/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 423018 - Direct Graphiti diagram exporter
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.common.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.examples.common.Messages;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Exports a diagram file as an image in dark mode (no need to have an open
 * Graphiti diagram editor
 * 
 * @since 0.13
 */
public final class SaveDiagramAsImageHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {

		// Get the current selection
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}

		// Operation must be started on diagram node -> cancel if not
		Object first = ((IStructuredSelection) selection).getFirstElement();
		if (!(first instanceof IFile)) {
			return null;
		}

		IFile file = (IFile) first;
		URI diagramFileUri = GraphitiUiInternal.getEmfService().getFileURI(file);

		// the file's first base node has to be a diagram
		URI diagramUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);

		// Get the default resource set to hold the new resource
		ResourceSet resourceSet = new ResourceSetImpl();
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resourceSet);
		if (editingDomain == null) {
			// Not yet existing, create one
			editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
		}

		Resource resource = editingDomain.getResourceSet().getResource(diagramFileUri, false);
		if (resource == null) {
			resource = editingDomain.getResourceSet().getResource(diagramFileUri, true);
		}
		EObject eObject = editingDomain.getResourceSet().getEObject(diagramUri, false);
		if (!(eObject instanceof Diagram)) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(),
					Messages.SaveDiagramAsImageHandler_NoDiagramFoundError,
					Messages.SaveDiagramAsImageHandler_NoDiagramFoundDialogText, Status.CANCEL_STATUS);
			return null;
		}

		Diagram diagram = (Diagram) eObject;
		byte[] bytes = GraphitiUi.getImageService().convertDiagramToBytes(diagram, SWT.IMAGE_JPEG);

		// Ask for the file to save
		FileDialog dialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.jpg", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
		dialog.setFilterIndex(0);
		dialog.setText(Messages.SaveDiagramAsImageHandler_DialogTitle);
		dialog.setFileName(file.getName());
		String fileName = dialog.open();
		if (fileName == null) {
			// Cancelled
			return null;
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			return showFileSelectionErrorDialog(e);
		} catch (IOException e) {
			return showFileSelectionErrorDialog(e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				return showFileSelectionErrorDialog(e);
			}
		}

		// Dispose the editing domain to eliminate memory leak
		editingDomain.dispose();

		return null;
	}

	private Object showFileSelectionErrorDialog(IOException e) {
		IStatus status = new Status(IStatus.ERROR, "org.eclipse.graphiti.examples.common", e.getMessage(), e); //$NON-NLS-1$
		ErrorDialog.openError(Display.getCurrent().getActiveShell(),
				Messages.SaveDiagramAsImageHandler_SaveFileError, e.getMessage(), status);
		return null;
	}
}