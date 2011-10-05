package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
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
	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		try {
			IFile file = ReflectionUtil.getFile(input);
			if (file != null) {

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
					final URI uri = URI.createURI(uriString);
					if (uri != null) {
						final ResourceSet rSet = new ResourceSetImpl();
						final Diagram diagram = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, rSet);
						if (diagram != null) {
							final URI diagramUri = EcoreUtil.getURI(diagram);
							// Trim fragmentssince in some cases for the
							// first root object just a slash is used, in
							// other cases it is /0
							// (if more than one root object is present).
							// If we would compare the full diagram URI
							// we could get false even if we have the same
							// referent.
							// since we only allow one diagram per file
							// comparing file URI is ok:
							// "one input a diagramfileinput, the other
							// contains a diagram, and the files are the
							// same" implies "same diagram".
							URI diagramUriTrimFragment = diagramUri.trimFragment();
							URI uriTrimFragment = uri.trimFragment();
							if (uriTrimFragment.equals(diagramUriTrimFragment)) {
								return true;
							}
						}
					}
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