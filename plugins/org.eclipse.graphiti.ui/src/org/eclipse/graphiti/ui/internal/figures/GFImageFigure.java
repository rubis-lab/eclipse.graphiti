/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Veit Hoffmann (mwenz) - Bug 342869 - Image doesn't scale the contained SWT Image on resize
 *    mwenz - Bug 464857 - Images created by GFImageFigure are not destroyed
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFImageFigure extends ImageFigureAntialias {
	private GraphicsAlgorithm graphicsAlgorithm;

	private Image fillImage = null;

	public GFImageFigure(GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	@Override
	public void setImage(Image image) {
		if (image != null
				&& graphicsAlgorithm instanceof org.eclipse.graphiti.mm.algorithms.Image) {
			org.eclipse.graphiti.mm.algorithms.Image imageGA = (org.eclipse.graphiti.mm.algorithms.Image) graphicsAlgorithm;
			if (imageGA.getStretchH() || imageGA.getStretchV()) {
				// scaling required
				double scalefactorX = 1.0;
				double scalefactorY = 1.0;
				// set initial scalefactor
				// get image data from default image
				ImageData originalImageData = image.getImageData();
				int imageWidth = originalImageData.width;
				int imageHeight = originalImageData.height;
				if (imageGA.getStretchH()) {
					scalefactorX = (double) graphicsAlgorithm.getWidth()
							/ (double) imageWidth;
				}
				if (imageGA.getStretchV()) {
					scalefactorY = (double) graphicsAlgorithm.getHeight()
							/ (double) imageHeight;
				}
				if (imageGA.getProportional()) {
					// scale down the image to match into frame
					// both dimensions are scaled to the smaller scalefactor in
					// order to make the image matching into the container
					double actualScalefactor = 0;
					if (scalefactorX < scalefactorY) {
						actualScalefactor = scalefactorX;
					} else {
						actualScalefactor = scalefactorY;
					}
					scalefactorX = actualScalefactor;
					scalefactorY = actualScalefactor;
				}
				// create scaled image
				double d = imageWidth * scalefactorX;
				double e = imageHeight * scalefactorY;
				fillImage = new Image(Display.getCurrent(),
						originalImageData.scaledTo((int) d, (int) e));
				disposeOldImage();
			} else {
				fillImage = image;
				// Must not dispose here as this leads to Graphics is disposed
				// exceptions when images are reused, see
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=464857#c7
			}
		} else {
			fillImage = image;
			disposeOldImage();
		}
		super.setImage(fillImage);
	}

	private void disposeOldImage() {
		// keep track of the old fill image on resize and dispose it to avoid
		// memory leaks
		// We don't change the image in
		// GraphitiUIPlugin.getDefault().getImageRegistry() because scaling down
		// makes image quality bad
		Image oldImage = getImage();
		if (oldImage != null && !oldImage.isDisposed()) {
			oldImage.dispose();
		}
	}

	@Override
	public void paintFigure(Graphics graphics) {
		if (graphicsAlgorithm != null && GraphitiInternal.getEmfService().isObjectAlive(graphicsAlgorithm)) {
			double transparency = Graphiti.getGaService().getTransparency(graphicsAlgorithm, true);
			int alpha = (int) ((1.0 - transparency) * 255.0);
			graphics.setAlpha(alpha);
		}
		super.paintFigure(graphics);
	}
}
