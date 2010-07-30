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
package org.eclipse.graphiti.ui.internal;

import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.platform.IImageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This class is the single access-point for all images of this plugin.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ImagePool {

	/**
	 * Gets the image descriptor for id.
	 * 
	 * @param imageId
	 *            the image id
	 * 
	 * @return the image descriptor for id
	 */
	public static ImageDescriptor getImageDescriptorForId(String imageId) {

		if (imageId == null)
			return null;

		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(imageId);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		imageDescriptor = createImageDescriptorForId(imageId);
		return imageDescriptor;
	}

	/**
	 * Gets the image for id.
	 * 
	 * @param imageId
	 *            the image id
	 * 
	 * @return the image for id
	 */
	public static Image getImageForId(String imageId) {
		if (imageId != null) {
			// if image already available take it
			ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
			Image result = imageRegistry.get(imageId);
			if (result != null)
				return result;

			createImageDescriptorForId(imageId);
			return imageRegistry.get(imageId); // now there is an image registered
		}

		return null;
	}

	/**
	 * This method fixes a problem for ImageDescriptors. It returns a corrected
	 * ImageDescriptor for problematic ImageDescriptors.
	 * <p>
	 * There is a problem with transparent GIFs. If the RGB value of the
	 * transparent color index is identical to the RGB value of other colors
	 * indices in the palette, then all those color indices are considered as
	 * transparent. So as a result the transparency seems to be on an RGB value
	 * instead of a color index.
	 */
	private static ImageDescriptor fixImageDescriptor(ImageDescriptor descriptor) {
		// Typically the incoming ImageDescriptor is an URLImageDescriptor. The following lines create an ImageDataImageDescriptor
		// from it, which basically describes the same image. But that one works. So there seems to be an error in the
		// URLImageDescriptor.
		ImageData data = descriptor.getImageData();
		return ImageDescriptor.createFromImageData(data);

	}

	/**
	 * Gets the image for pictogram image.
	 * 
	 * @param pictogramImage
	 *            the pictogram image
	 * 
	 * @return the image for pictogram image
	 */
	public static Image getImageForPictogramImage(org.eclipse.graphiti.mm.algorithms.Image pictogramImage) {
		if (pictogramImage != null) {
			String imageId = pictogramImage.getId();
			return getImageForId(imageId);
		}
		return null;
	}

	private static ImageDescriptor createImageDescriptorForId(String imageId) {

		if (imageId == null)
			return null;

		// if image descriptor already exists return it
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(imageId);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		// try get the image-location from the image-providers
		IImageProvider imageProviders[] = ExtensionManager.getSingleton().getImageProviders();
		for (int i = 0; i < imageProviders.length; i++) {
			IImageProvider imageProvider = imageProviders[i];
			String imageFilePath = imageProvider.getImageFilePath(imageId);
			if (imageFilePath != null) {
				String pluginId = imageProvider.getPluginId();
				if (pluginId != null) {
					// try to create Image from ImageDescriptor (initialize the ImageRegistry on the fly)
					imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, imageFilePath);
				}
				break;
			}
		}

		if (imageDescriptor == null) {
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
		}

		imageDescriptor = fixImageDescriptor(imageDescriptor);
		imageRegistry.put(imageId, imageDescriptor);

		return imageDescriptor;
	}
}
