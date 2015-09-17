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

import java.util.Hashtable;

import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class ExportDiagramAsImageDummyImageProvider extends AbstractExtension implements IImageProvider {

	private static final String PREFIX = "org.eclipse.graphiti.ui.internal."; //$NON-NLS-1$
	private static final String IMG_NO_IMAGE = PREFIX + "noImage"; //$NON-NLS-1$

	private String pluginId;

	private Hashtable<String, String> htKeyImage = new Hashtable<String, String>();

	public ExportDiagramAsImageDummyImageProvider() {
		super();
		addAvailableImages();
	}

	final public String getPluginId() {
		return this.pluginId;
	}

	final public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	final public String getImageFilePath(String imageId) {
		Object htObject = this.htKeyImage.get(IMG_NO_IMAGE);
		if (htObject instanceof String) {
			return (String) htObject;
		}
		return null;
	}

	final protected void addImageFilePath(String imageId, String imageFilePath) {
		if (this.htKeyImage.get(imageId) != null) {
			T.racer().error("Image with ID '" + imageId + "' is already registered"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		this.htKeyImage.put(imageId, imageFilePath);
	}

	protected void addAvailableImages() {
		addImageFilePath(IMG_NO_IMAGE, "icons/NoImage.png"); //$NON-NLS-1$

	}
}
