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
package org.eclipse.graphiti.ui.services;

import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.internal.services.impl.ImageService;

/**
 * This class is the main access point to all public Graphiti UI services. These
 * services can be used to work with eclipse platform stuff. E.g. deal with
 * images or platform extensions.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GraphitiUi {

	final private static IImageService imageService = new ImageService();

	/**
	 * Provides the extension manager.
	 * 
	 * @return the extension manager
	 * 
	 * @see org.eclipse.graphiti.ui.services.IExtensionManager
	 */
	public static IExtensionManager getExtensionManager() {
		return ExtensionManager.getSingleton();
	}

	/**
	 * Provides the image service.
	 * 
	 * @return the image service
	 * 
	 * @see org.eclipse.graphiti.ui.services.IImageService
	 */
	public static IImageService getImageService() {
		return imageService;
	}
}
