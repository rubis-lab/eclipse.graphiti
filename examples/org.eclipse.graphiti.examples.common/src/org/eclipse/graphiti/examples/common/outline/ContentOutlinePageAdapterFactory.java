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
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.common.outline;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.graphiti.internal.pref.GFPreferences;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

// The generic outline uses internal functionality of Graphiti. For concrete
// tool implementations this should not be necessary 
@SuppressWarnings("restriction")
public class ContentOutlinePageAdapterFactory implements IAdapterFactory {

	private static final Class<?>[] ADAPTERS = new Class[] { IContentOutlinePage.class };

	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
		if (GFPreferences.getInstance().isGenericOutlineActive()) {
			if (IContentOutlinePage.class.equals(adapterType)) {
				if (adaptableObject instanceof IDiagramContainerUI) {
					IDiagramContainerUI diagramEditor = (IDiagramContainerUI) adaptableObject;
					if (diagramEditor.getDiagramTypeProvider() != null) { // diagram
																			// editor
																			// initialized?
						GraphicsEditorOutlinePage outlinePage = new GraphicsEditorOutlinePage(diagramEditor);
						return outlinePage;
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return ADAPTERS;
	}
}
