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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.filesystem;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class FilesystemImageProvider extends AbstractImageProvider {

	protected static final String PREFIX = "org.eclipse.graphiti.examples.filesystem."; //$NON-NLS-1$

	public static final String IMG_CREATE_FILE = PREFIX + "createFile"; //$NON-NLS-1$
	public static final String IMG_DELETE_FILE = PREFIX + "deleteFile"; //$NON-NLS-1$

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_CREATE_FILE, "icons/create_file.png"); //$NON-NLS-1$
		addImageFilePath(IMG_DELETE_FILE, "icons/delete_file.png"); //$NON-NLS-1$
	}
}
