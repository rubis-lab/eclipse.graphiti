package org.eclipse.graphiti.ui.editor;

import org.eclipse.emf.common.util.URI;

/**
 * @since 0.9
 */
public interface IDiagramEditorInput {

	String getUriString();

	URI getUri();

	String getProviderId();

	void setProviderId(String providerId);

	void updateUri(URI newURI);

}
