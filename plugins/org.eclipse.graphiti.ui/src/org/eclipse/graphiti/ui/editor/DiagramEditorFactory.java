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
package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PartInitException;

/**
 * A factory for creating DiagramEditorInternal objects.
 * 
 * @see {@link DiagramEditorInputBase}
 * @see {@link DiagramEditorInputDisposingTED}
 * @see {@link DiagramEditorFactory}
 * @see {@link DiagramEditorInput}
 */
public class DiagramEditorFactory implements IElementFactory {

	/**
	 * Creates a {@link DiagramEditorInputDisposingTED} with a self created
	 * {@link TransactionalEditingDomain} or returns otherInput, if it is a
	 * {@link DiagramEditorInput}.
	 * 
	 * @param otherInput
	 *            an {@link IEditorInput} editor input
	 * @return a {@link DiagramEditorInput} editor input or a
	 *         {@link DiagramEditorInputDisposingTED}
	 */
	public DiagramEditorInput createEditorInput(IEditorInput otherInput) {
		if (otherInput instanceof DiagramEditorInput) {
			return (DiagramEditorInput) otherInput;
		}
		if (otherInput instanceof IFileEditorInput) {
			final IFileEditorInput fileInput = (IFileEditorInput) otherInput;
			final IFile file = fileInput.getFile();
			final TransactionalEditingDomain domain = GraphitiUiInternal.getEmfService().createResourceSetAndEditingDomain();
			URI diagramFileUri = GraphitiUiInternal.getEmfService().getFileURI(file, domain.getResourceSet());
			if (diagramFileUri != null) {
				// the file has to contain one base node which has to be a diagram
				diagramFileUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);
				return new DiagramEditorInput(diagramFileUri, domain, null, true);
			}
		}

		return null;
	}

	/**
	 * <b>For internal use only</b>. Checks whether any file editor input
	 * matches one of the opened editors. Scenario is a user's double-clicking
	 * on a diagram file in the explorer. If done multiple times on the same
	 * file, no new editor must be opened.
	 */
	public static final class DiagramEditorMatchingStrategy implements IEditorMatchingStrategy {
		public boolean matches(IEditorReference editorRef, IEditorInput input) {
			try {
				if (input instanceof IFileEditorInput) {
					final IFileEditorInput fileInput = (IFileEditorInput) input;
					final IFile file = fileInput.getFile();

					// Check whether the given file contains a diagram as its
					// root element. If yes, compare it with the given editor's
					// diagram.
					final IEditorInput editorInput = editorRef.getEditorInput();
					if (editorInput instanceof DiagramEditorInput) {
						final DiagramEditorInput diagInput = (DiagramEditorInput) editorInput;

						// We do not compare diagram object but diagram files only.
						// Reason is that if editorRef points to a not yet
						// realized editor, its input's diagram is null (not yet
						// created), thus we can only get its diagram file.
						final String uriString = diagInput.getUriString();
						final URI uri = URI.createURI(uriString);
						if (uri != null) {
							final ResourceSet rSet = new ResourceSetImpl();
							final Diagram diagram = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, rSet);
							if (diagram != null) {
								final URI diagramUri = EcoreUtil.getURI(diagram);
								if (uri.equals(diagramUri)) {
									return true;
								}
							}
						}
					}
				} else if (input instanceof DiagramEditorInput) {
					// normal case: check for input equality
					final IEditorInput editorInput = editorRef.getEditorInput();
					if (input.equals(editorInput)) {
						return true;
					}
				}
			} catch (final PartInitException e) {
				T.racer().error(e.getMessage(), e);
			}
			return false;
		}
	}

	@Override
	public IAdaptable createElement(IMemento memento) {
		// get diagram URI
		final String diagramUriString = memento.getString(DiagramEditorInput.KEY_URI);
		if (diagramUriString == null) {
			return null;
		}

		// get diagram type provider id
		final String providerID = memento.getString(DiagramEditorInput.KEY_PROVIDER_ID);
		//TODO		if (providerID == null)
		//			return null;

		final TransactionalEditingDomain domain = GraphitiUiInternal.getEmfService().createResourceSetAndEditingDomain();
		return new DiagramEditorInput(diagramUriString, domain, providerID, true);
	}

}