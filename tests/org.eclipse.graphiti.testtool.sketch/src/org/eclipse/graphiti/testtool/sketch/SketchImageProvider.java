/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 342869 - Image doesn't scale the contained SWT Image on resize
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class SketchImageProvider extends AbstractImageProvider {

	// The prefix for all identifiers of this image provider
	protected static final String PREFIX = "org.eclipse.graphiti.testtool.sketch."; //$NON-NLS-1$

	// The image identifier for an EReference.
	public static final String IMG_GRAPHITI = PREFIX + "graphiti"; //$NON-NLS-1$

	@Override
	protected void addAvailableImages() {
		// register the path for each image identifier
		addImageFilePath(IMG_GRAPHITI, "icons/Graphiti.jpg"); //$NON-NLS-1$
	}
}
