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
 *    mwenz - Bug 336075 - DiagramEditor accepts URIEditorInput
 *    mwenz - Bug 346932 - Navigation history broken
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * The Class DiagramEditorFactory.
 * 
 * A factory for creating DiagramEditorInput objects.
 * 
 * @see {@link DiagramEditorInputFactory}
 * @see {@link DiagramEditorInput}
 * @since 0.9 results from renaming DiagramEditorFactory
 */
public class DiagramEditorInputFactory implements IElementFactory {

	public IAdaptable createElement(IMemento memento) {
		// get diagram URI
		final String diagramUriString = memento.getString(DiagramEditorInput.KEY_URI);
		if (diagramUriString == null) {
			return null;
		}
		// get diagram type provider id
		final String providerID = memento.getString(DiagramEditorInput.KEY_PROVIDER_ID);
		URI diagramUri = URI.createURI(diagramUriString);
		return new DiagramEditorInput(diagramUri, providerID);
	}
}