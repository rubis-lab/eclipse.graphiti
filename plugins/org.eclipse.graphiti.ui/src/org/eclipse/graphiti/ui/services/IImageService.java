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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * This interface provides services ...
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IImageService {
	/**
	 * Gets the image descriptor for id.
	 * 
	 * @param imageId
	 *            the image id
	 * 
	 * @return the image descriptor for id
	 */
	ImageDescriptor getImageDescriptorForId(String imageId);

	/**
	 * Gets the image for id.
	 * 
	 * @param imageId
	 *            the image id
	 * 
	 * @return the image for id
	 */
	Image getImageForId(String imageId);
}
