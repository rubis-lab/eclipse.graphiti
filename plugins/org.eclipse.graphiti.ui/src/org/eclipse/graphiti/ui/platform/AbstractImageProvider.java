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
package org.eclipse.graphiti.ui.platform;

import java.util.Hashtable;

import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.ui.internal.T;

/**
 * Instead of implementing IImageProvider directly the clients should extend
 * this AbstractImageProvider. They can simply override the method
 * addAvailableImages().
 */
public abstract class AbstractImageProvider extends AbstractExtension implements IImageProvider {

	private String pluginId;

	private Hashtable<String, String> htKeyImage = new Hashtable<String, String>();

	public AbstractImageProvider() {
		super();
		addAvailableImages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.platform.IImageProvider#getPluginId()
	 */
	final public String getPluginId() {
		return pluginId;
	}

	/**
	 * @param pluginId
	 *            The pluginId to set.
	 */
	final public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.internal.platform.IImageProvider#getImageFilePath
	 * (java.lang.String)
	 */
	final public String getImageFilePath(String imageId) {
		Object htObject = htKeyImage.get(imageId);
		if (htObject instanceof String) {
			return (String) htObject;
		}
		return null;
	}

	final protected void addImageFilePath(String imageId, String imageFilePath) {
		if (htKeyImage.get(imageId) != null)
			T.racer().error("Image with ID '" + imageId + "' is already registered"); //$NON-NLS-1$ //$NON-NLS-2$

		htKeyImage.put(imageId, imageFilePath);
	}

	/**
	 * add all available images with addImageFilePath(String imageId, String
	 * imageFilePath);
	 */
	abstract protected void addAvailableImages();
}
