package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ReflectionUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

/**
 * @since 0.9
 */
public class EditorInputAdapter {

	/**
	 * Creates a new {@link DiagramEditorInput} with a self created
	 * {@link TransactionalEditingDomain} in case the passed
	 * {@link IEditorInput} is either a {@link IFileEditorInput} or a
	 * {@link URIEditorInput}. It returns otherInput, if it is a
	 * {@link DiagramEditorInput}. The created editor input object will care
	 * about the disposal of the editing domain.
	 * 
	 * @param otherInput
	 *            an {@link IEditorInput} editor input
	 * @return a {@link DiagramEditorInput} editor input if the conversion is
	 *         supported and succeeded, otherwise <code>null</code>.
	 * @since 0.9
	 */
	public static DiagramEditorInput adaptToDiagramEditorInput(IEditorInput otherInput) {
		if (otherInput instanceof DiagramEditorInput) {
			DiagramEditorInput input = (DiagramEditorInput) otherInput;
			return input;
		}
		IFile file = ReflectionUtil.getFile(otherInput);
		if (file != null) {
			URI diagramFileUri = GraphitiUiInternal.getEmfService().getFileURI(file);
			return createDiagramEditorInput(diagramFileUri);
		}
		if (otherInput instanceof URIEditorInput) {
			final URIEditorInput uriInput = (URIEditorInput) otherInput;
			URI diagramFileUri = uriInput.getURI();
			return createDiagramEditorInput(diagramFileUri);
		}

		return null;
	}

	private static DiagramEditorInput createDiagramEditorInput(URI diagramFileUri) {
		if (diagramFileUri != null) {
			// the file's first base node has to be a diagram
			URI diagramUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);
			// take the first installed provider for this diagram type
			return new DiagramEditorInput(diagramUri, null);
		}
		return null;
	}

}
