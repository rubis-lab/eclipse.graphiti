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
package org.eclipse.graphiti.ui.internal.services.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.platform.IImageProvider;
import org.eclipse.graphiti.ui.platform.PlatformImageProvider;
import org.eclipse.graphiti.ui.services.IImageService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ImageService implements IImageService {

	public ImageDescriptor getImageDescriptorForId(String providerId, String imageId) {

		if (imageId == null || providerId == null)
			return null;

		String registryKey = makeKey(providerId, imageId);
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(registryKey);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		imageDescriptor = createImageDescriptorForId(providerId, imageId);
		return imageDescriptor;
	}

	public Image getImageForId(String providerId, String imageId) {

		if (imageId == null || providerId == null)
			return null;

		String registryKey = makeKey(providerId, imageId);
		// if image already available take it
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		Image result = imageRegistry.get(registryKey);
		if (result != null && !result.isDisposed()) {
			return result;
		}

		createImageDescriptorForId(providerId, imageId);
		Image image = imageRegistry.get(registryKey); // now there is an image
													// registered
		if (image == null) {
			throw new IllegalStateException("No image could be retrieved for imageId '" + imageId + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return image;
	}

	public void removeImageFromRegistry(String key) {
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		imageRegistry.remove(key);
	}

	public ImageDescriptor getPlatformImageDescriptorForId(String imageId) {
		if (imageId == null)
			return null;

		String registryKey = makePlatformKey(imageId);
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(registryKey);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		imageDescriptor = createPlatformImageDescriptorForId(imageId);
		return imageDescriptor;
	}

	public Image getPlatformImageForId(String imageId) {
		if (imageId == null)
			return null;

		String registryKey = makePlatformKey(imageId);
		// if image already available take it
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		Image result = imageRegistry.get(registryKey);
		if (result != null && !result.isDisposed()) {
			return result;
		}

		createPlatformImageDescriptorForId(imageId);
		Image image = imageRegistry.get(registryKey); // now there is an image
														// registered
		if (image == null) {
			throw new IllegalStateException("No image could be retrieved for imageId '" + imageId + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return image;
	}

	public byte[] convertImageToBytes(Image image, int format) {
		ByteArrayOutputStream result = new ByteArrayOutputStream();

		try {
			ImageData imDat = null;
			// Save as GIF is only working if not more than 256 colors are used
			// in the image
			if (format == SWT.IMAGE_GIF) {
				imDat = create8BitIndexedPaletteImage(image);
			}

			if (imDat == null) {
				imDat = image.getImageData();
			}

			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { imDat };
			try {
				imageLoader.save(result, format);
			} catch (SWTException e) {
				String error = "Depth: " + Integer.toString(image.getImageData().depth) + "\n" + "X: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+ Integer.toString(image.getImageData().x)
						+ "\n" + "Y: " + Integer.toString(image.getImageData().y); //$NON-NLS-1$ //$NON-NLS-2$
				throw new IllegalStateException(error, e);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			image.dispose();
		}

		return result.toByteArray();
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
	private ImageDescriptor fixImageDescriptor(ImageDescriptor descriptor) {
		// Typically the incoming ImageDescriptor is an URLImageDescriptor. The following lines create an ImageDataImageDescriptor
		// from it, which basically describes the same image. But that one works. So there seems to be an error in the
		// URLImageDescriptor.
		ImageData data = descriptor.getImageData();
		return ImageDescriptor.createFromImageData(data);

	}

	private ImageDescriptor createImageDescriptorForId(String providerId, String imageId) {

		if (imageId == null || providerId == null)
			return null;

		String registryKey = makeKey(providerId, imageId);
		// if image descriptor already exists return it
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(registryKey);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		// try get the image-location from the image-providers
		Collection<IImageProvider> imageProviders = ExtensionManager.getSingleton()
				.getImageProvidersForDiagramTypeProviderId(providerId);
		for (IImageProvider imageProvider : imageProviders) {
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
		imageRegistry.put(registryKey, imageDescriptor);

		return imageDescriptor;
	}

	private ImageDescriptor createPlatformImageDescriptorForId(String imageId) {

		if (imageId == null)
			return null;

		String registryKey = makePlatformKey(imageId);
		// if image descriptor already exists return it
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(registryKey);
		if (imageDescriptor != null) {
			return imageDescriptor;
		}

		// try get the image-location from the image-providers
		IImageProvider platformImageProvider = ExtensionManager.getSingleton().getPlatformImageProvider();
		String imageFilePath = platformImageProvider.getImageFilePath(imageId);
		if (imageFilePath != null) {
			String pluginId = platformImageProvider.getPluginId();
			if (pluginId != null) {
				// try to create Image from ImageDescriptor (initialize the
				// ImageRegistry on the fly)
				imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, imageFilePath);
			}
		}

		if (imageDescriptor == null) {
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
		}

		imageDescriptor = fixImageDescriptor(imageDescriptor);
		imageRegistry.put(registryKey, imageDescriptor);

		return imageDescriptor;
	}

	private String makeKey(String dtp, String imageId) {
		return dtp + "||" + imageId;
	}

	private String makePlatformKey(String imageId) {
		return makeKey(PlatformImageProvider.ID, imageId);
	}

	private ImageData create8BitIndexedPaletteImage(Image image) {
		int upperboundWidth = image.getBounds().width;
		int upperboundHeight = image.getBounds().height;
		ImageData imageData = image.getImageData();

		// determine number of used colors
		ArrayList<Integer> colors = new ArrayList<Integer>();
		for (int x = 0; x < upperboundWidth; x++) {
			for (int y = 0; y < upperboundHeight; y++) {
				int color = imageData.getPixel(x, y);
				Integer colorInteger = new Integer(color);
				if (!colors.contains(colorInteger))
					colors.add(colorInteger);
			}
		}

		// at the moment this is only working if not more than 256 colors are
		// used in the image
		if (colors.size() > 256) {
			throw new IllegalStateException(
					"Image contains more than 256 colors. \n Automated color reduction is currently not supported."); //$NON-NLS-1$
		}

		// create an indexed palette
		RGB[] rgbs = new RGB[256];
		for (int i = 0; i < 256; i++)
			rgbs[i] = new RGB(255, 255, 255);
		for (int i = 0; i < colors.size(); i++) {
			int pixelValue = ((colors.get(i))).intValue();
			int red = (pixelValue & imageData.palette.redMask) >>> Math.abs(imageData.palette.redShift);
			int green = (pixelValue & imageData.palette.greenMask) >>> Math.abs(imageData.palette.greenShift);
			int blue = (pixelValue & imageData.palette.blueMask) >>> Math.abs(imageData.palette.blueShift);
			rgbs[i] = new RGB(red, green, blue);
		}

		// create new imageData
		PaletteData palette = new PaletteData(rgbs);
		ImageData newImageData = new ImageData(imageData.width, imageData.height, 8, palette);

		// adjust imageData with regard to the palette
		for (int x = 0; x < upperboundWidth; x++) {
			for (int y = 0; y < upperboundHeight; y++) {
				int color = imageData.getPixel(x, y);
				newImageData.setPixel(x, y, colors.indexOf(new Integer(color)));
			}
		}

		return newImageData;
	}
}
