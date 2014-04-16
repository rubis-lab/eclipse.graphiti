/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 378342 - Cannot store more than a diagram per file
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ReflectionUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * Checks whether any file editor input
 * matches one of the opened editors. Scenario is a user's double-clicking
 * on a diagram file in the explorer. If done multiple times on the same
 * file, no new editor must be opened.
 * @since 0.9
 */
public final class DiagramEditorMatchingStrategy implements IEditorMatchingStrategy {

	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		try {
			if (input instanceof DiagramEditorInput) {
				// normal case: check for input equality
				final IEditorInput editorInput = editorRef.getEditorInput();
				if (input.equals(editorInput)) {
					return true;
				}
			} else if (input instanceof URIEditorInput) {
				final URIEditorInput uriInput = (URIEditorInput) input;
				URI uri = uriInput.getURI();
				uri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(uri);

				// Check whether the given file contains a diagram as its
				// root element. If yes, compare it with the given editor's
				// diagram.
				final IEditorInput editorInput = editorRef.getEditorInput();
				if (editorInput instanceof DiagramEditorInput) {
					final DiagramEditorInput diagInput = (DiagramEditorInput) editorInput;

					// We do not compare diagram object but diagram files
					// only.
					// Reason is that if editorRef points to a not yet
					// realized editor, its input's diagram is null (not yet
					// created), thus we can only get its diagram file.
					final String uriString = diagInput.getUriString();
					final URI diagramUri = URI.createURI(uriString);
					if (diagramUri != null) {
						if (uri.equals(diagramUri)) {
							return true;
						}
					}
				}
			} else {
				IFile file = ReflectionUtil.getFile(input);
				if (file != null) {
					// check whether the given input comes with a file which is
					// already opened in the diagram editor.
					final IEditorInput editorInputOfOpenEditor = editorRef.getEditorInput();
					IFile fileOpenedInDiagramEditor = ReflectionUtil.getFile(editorInputOfOpenEditor);
					if (file.equals(fileOpenedInDiagramEditor)) {
						return true;
					}
				}
			}
		} catch (final PartInitException e) {
			T.racer().error(e.getMessage(), e);
		}
		return false;
	}
}