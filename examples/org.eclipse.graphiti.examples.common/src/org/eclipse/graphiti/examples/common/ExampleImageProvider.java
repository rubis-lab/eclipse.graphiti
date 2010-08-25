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
/**
 * 
 */
package org.eclipse.graphiti.examples.common;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

/**
 * The Class ExampleImageProvider.
 */
public class ExampleImageProvider extends AbstractImageProvider {

	private static final String ROOT_FOLDER_FOR_IMG = "icons/"; //$NON-NLS-1$

	@Override
	protected void addAvailableImages() {
		// attributes
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_A_PUBLIC, ROOT_FOLDER_FOR_IMG + "modifier/attribute_public.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_A_PROTECTED, ROOT_FOLDER_FOR_IMG + "modifier/attribute_protected.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_A_PRIVATE, ROOT_FOLDER_FOR_IMG + "modifier/attribute_private.gif"); //$NON-NLS-1$

		// references
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_R_PROTECTED, ROOT_FOLDER_FOR_IMG + "modifier/reference_public.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_R_PUBLIC, ROOT_FOLDER_FOR_IMG + "modifier/reference_protected.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_R_PRIVATE, ROOT_FOLDER_FOR_IMG + "modifier/reference_private.gif"); //$NON-NLS-1$

		// operations
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_O_PRIVATE, ROOT_FOLDER_FOR_IMG + "modifier/operation_public.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_O_PROTECTED, ROOT_FOLDER_FOR_IMG + "modifier/operation_protected.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MODIFIER_O_PUBLIC, ROOT_FOLDER_FOR_IMG + "modifier/operation_private.gif"); //$NON-NLS-1$

		// mof
		addImageFilePath(IExampleImageConstants.IMG_CLASS, ROOT_FOLDER_FOR_IMG + "mof/class.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_NEW_CLASS, ROOT_FOLDER_FOR_IMG + "mof/newclass.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_PACKAGE, ROOT_FOLDER_FOR_IMG + "mof/package.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_MOF, ROOT_FOLDER_FOR_IMG + "mof/mof.gif"); //$NON-NLS-1$

		// tree
		addImageFilePath(IExampleImageConstants.IMG_TREE_DOWN, ROOT_FOLDER_FOR_IMG + "tree/tree_down.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_TREE_LEFT, ROOT_FOLDER_FOR_IMG + "tree/tree_left.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_TREE_RIGHT, ROOT_FOLDER_FOR_IMG + "tree/tree_right.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_TREE_UP, ROOT_FOLDER_FOR_IMG + "tree/tree_up.gif"); //$NON-NLS-1$

		// outline
		addImageFilePath(IExampleImageConstants.IMG_OUTLINE_TREE, ROOT_FOLDER_FOR_IMG + "outline/tree.gif"); //$NON-NLS-1$
		addImageFilePath(IExampleImageConstants.IMG_OUTLINE_THUMBNAIL, ROOT_FOLDER_FOR_IMG + "outline/thumbnail.gif"); //$NON-NLS-1$
	}
}
