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
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
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
