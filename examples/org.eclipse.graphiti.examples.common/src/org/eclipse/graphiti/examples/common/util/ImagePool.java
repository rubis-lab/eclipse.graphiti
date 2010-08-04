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
package org.eclipse.graphiti.examples.common.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * This class is the single access-point for all images of this plugin.
 */
public class ImagePool {
	/**
	 * The Constant ROOT_FOLDER_FOR_IMG.
	 */
	public static final String ROOT_FOLDER_FOR_IMG = "icons/"; //$NON-NLS-1$

	// dialogs
	/**
	 * The Constant IMG_LAYOUT_CIRCULAR.
	 */
	public static final String IMG_LAYOUT_CIRCULAR = "dialogs/layout_circular.gif"; //$NON-NLS-1$

	/**
	 * The Constant IMG_LAYOUT_ORGANIC.
	 */
	public static final String IMG_LAYOUT_ORGANIC = "dialogs/layout_organic.gif"; //$NON-NLS-1$

	// general
	/**
	 * The Constant IMG_ARROW_UP.
	 */
	public static final String IMG_ARROW_UP = "general/arrow_up.gif"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ARROW_DOWN.
	 */
	public static final String IMG_ARROW_DOWN = "general/arrow_down.gif"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ARROW_LEFT.
	 */
	public static final String IMG_ARROW_LEFT = "general/arrow_left.gif"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ARROW_RIGHT.
	 */
	public static final String IMG_ARROW_RIGHT = "general/arrow_right.gif"; //$NON-NLS-1$

	// outline
	/**
	 * The Constant IMG_OUTLINE_TREE.
	 */
	public static final String IMG_OUTLINE_TREE = "outline/tree.gif"; //$NON-NLS-1$

	/**
	 * The Constant IMG_OUTLINE_THUMBNAIL.
	 */
	public static final String IMG_OUTLINE_THUMBNAIL = "outline/thumbnail.gif"; //$NON-NLS-1$

	/**
	 * Returns the ImageDescriptor, which can be found at the given relative
	 * location. The complete filename is getInstallURL() + ROOT_FOLDER_FOR_IMG
	 * + relativeImageLocation.
	 * 
	 * @param relativeImageLocation
	 *            The location of the image, relative to the
	 *            ROOT_FOLDER_FOR_IMG.
	 * 
	 * @return The ImageDescriptor, which can be found at the given relative
	 *         location.
	 */
	public static ImageDescriptor getImageDescriptor(String relativeImageLocation) {
		try {
			URL url = new URL(ExamplesCommonPlugin.getInstallURL(), ROOT_FOLDER_FOR_IMG + relativeImageLocation);
			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e) {
			// $JL-EXC$
			return ImageDescriptor.getMissingImageDescriptor();
		} catch (NullPointerException npe) {
			// $JL-EXC$
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	/**
	 * Returns the Image, which is registered in the ImageRegistry for the given
	 * name.
	 * 
	 * @param imageName
	 *            The name of the Image, which to find in the ImageRegistry.
	 * 
	 * @return The Image, which is registered in the ImageRegistry for the given
	 *         name.
	 */
	public static Image getImage(String imageName) {
		ImageRegistry imageRegistry = ExamplesCommonPlugin.getDefault().getImageRegistry();
		Image result = imageRegistry.get(imageName);
		if (result != null)
			return result;

		// try to create Image from ImageDescriptor (initialize the
		// ImageRegistry on the fly)
		ImageDescriptor imageDescriptor = getImageDescriptor(imageName);
		if (imageDescriptor != null) {
			imageRegistry.put(imageName, imageDescriptor);
			return imageRegistry.get(imageName); // now there is an image
			// registered
		}

		return null;
	}
}
