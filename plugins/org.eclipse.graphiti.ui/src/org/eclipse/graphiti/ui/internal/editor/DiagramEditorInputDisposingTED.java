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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;

public class DiagramEditorInputDisposingTED extends DiagramEditorInput {

	public DiagramEditorInputDisposingTED(String diagramUriString, TransactionalEditingDomain domain, String providerID2) {
		super(diagramUriString, domain, providerID2);
	}

	public DiagramEditorInputDisposingTED(URI diagramUri, TransactionalEditingDomain domain, String providerID2) {
		super(diagramUri, domain, providerID2);
	}

	@Override
	public void dispose() {
		if (this.editingDomain != null) {
			this.editingDomain.dispose();
			this.editingDomain = null;
		}
		super.dispose();
	}
}
