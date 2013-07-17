/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    fvelasco - Bug 396247 - ImageDescriptor changes
 *    mwenz - Bug 413139 - Visibility of convertImageToBytes in DefaultSaveImageFeature
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.services;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageLoader;

/**
 * This interface provides services for the creation of images.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IImageService {

	/**
	 * Gets an image descriptor for the given image id. This image id must be
	 * supported by an graphiti image provider, and available to the given
	 * DiagramTypeProvider. The image registry of the plugin
	 * <code>org.eclipse.graphiti.ui</code> is used. This ensures that the image
	 * descriptor will only created once.
	 * 
	 * @param imageId
	 *            the image id which is supported by an graphiti image provider
	 * @param diagramTypeProviderId
	 * 
	 * 
	 * @return the image descriptor for the id
	 * 
	 * @see org.eclipse.jface.resource.ImageDescriptor
	 * @since 0.10
	 */
	ImageDescriptor getImageDescriptorForId(String diagramTypeProviderId, String imageId);

	/**
	 * Gets an image for the given image id. This image id must be supported by
	 * an graphiti image provider, and available to the given
	 * DiagramTypeProvider. The image registry of the plugin
	 * <code>org.eclipse.graphiti.ui</code> is used. This ensures that the image
	 * will only created once. The image returned must not be disposed by the
	 * caller.
	 * 
	 * @param imageId
	 *            the image id which is supported by an graphiti image provider
	 * 
	 * @return the image for the id
	 * 
	 * @see org.eclipse.swt.graphics.Image
	 * @since 0.10
	 */
	Image getImageForId(String diagramTypeProviderId, String imageId);

	/**
	 * Removes the corresponding image entry from the image registry and
	 * disposes the corresponding image (if existent). The passed image id must
	 * be supported by an graphiti image provider. The image registry of the
	 * plugin <code>org.eclipse.graphiti.ui</code> is used. Only call this
	 * method if you can guarantee that the image/image descriptor is no longer
	 * in use.
	 * 
	 * @param imageId
	 *            the image id which is supported by an graphiti image provider
	 * 
	 * @see org.eclipse.swt.graphics.Image
	 * @since 0.9
	 */
	void removeImageFromRegistry(String imageId);

	/**
	 * Gets an image descriptor for the given image id. This image id must be
	 * supported by the graphiti platform image provider. The image registry of
	 * the plugin <code>org.eclipse.graphiti.ui</code> is used. This ensures
	 * that the image descriptor will only created once.
	 * 
	 * @param imageId
	 *            the image id which is supported by the graphiti platform image
	 *            provider
	 * 
	 * @return the image descriptor for the id
	 * 
	 * @see org.eclipse.jface.resource.ImageDescriptor
	 * @since 0.10
	 */
	ImageDescriptor getPlatformImageDescriptorForId(String imageId);

	/**
	 * Gets an image for the given image id. This image id must be supported by
	 * the graphiti platform image provider. The image registry of the plugin
	 * <code>org.eclipse.graphiti.ui</code> is used. This ensures that the image
	 * will only created once. The image returned must not be disposed by the
	 * caller.
	 * 
	 * @param imageId
	 *            the image id which is supported by the graphiti platform image
	 *            provider
	 * 
	 * @return the image for the id
	 * 
	 * @see org.eclipse.swt.graphics.Image
	 * @since 0.10
	 */
	Image getPlatformImageForId(String imageId);

	/**
	 * Converts the given {@link Image} into a byte array in the given format
	 * that could be directly saved to a file in that format. Supported formats
	 * are BMP (Windows or OS/2 Bitmap), ICO (Windows Icon), JPEG, GIF, PNG and
	 * TIFF, see {@link ImageLoader} for details.<br>
	 * In case GIF is the target format this method will check that the image
	 * does not contain more than 256 colors (limit for GIF format). In case
	 * there are more colors this method will throw an
	 * {@link IllegalStateException}.
	 * 
	 * @param image
	 *            The image to convert to a byte array
	 * @param format
	 *            The format to use see {@link ImageLoader}
	 * @return A byte array containing the image
	 * @throws In
	 *             case of GIF format and the image having more than 256 colors.
	 * 
	 * @since 0.11
	 */
	byte[] convertImageToBytes(Image image, int format);
}
