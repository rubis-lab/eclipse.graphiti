/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 Eclipse Modeling Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Laurent Le Moux, mwenz - Bug 423018 - Direct Graphiti diagram exporter
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class ExportDiagramAsImageDummyImageProvider extends AbstractExtension implements IImageProvider {

	private String pluginId;

	public ExportDiagramAsImageDummyImageProvider() {
		super();
	}

	final public String getPluginId() {
		return this.pluginId;
	}

	final public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	final public String getImageFilePath(String imageId) {
		return "icons/NoImage.png"; //$NON-NLS-1$
	}
}
