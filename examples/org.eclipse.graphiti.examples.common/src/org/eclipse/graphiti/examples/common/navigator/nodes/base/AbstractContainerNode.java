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
/*
 * Created on 09.06.2005
 */
package org.eclipse.graphiti.examples.common.navigator.nodes.base;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * The Class AbstractContainerNode.
 */
public abstract class AbstractContainerNode implements IContainerNode {
	/**
	 * Performance Flag Set value to false for best performance.
	 */
	private static final boolean APPEND_FOLDER_SIZE = false;

	/**
	 * Performance Flag Set value to true for best performance.
	 */
	private static final boolean OPTIMISTIC_CHILDREN_CHECK = true;

	protected AbstractContainerNode() {
	}

	public String getText() {
		String ret = getContainerName();
		if (APPEND_FOLDER_SIZE) {
			int childCount = 0;
			Object[] children = getChildren();
			if (children != null) {
				childCount = children.length;
			}
			ret = ret + " (" + childCount + ")";
		}
		return ret;
	}

	/**
	 * Gets the container name.
	 * 
	 * @return the container name
	 */
	abstract protected String getContainerName();

	public boolean hasChildren() {
		if (OPTIMISTIC_CHILDREN_CHECK) {
			return true;
		}

		long start = System.currentTimeMillis();
		boolean bool = getChildren() != null && getChildren().length > 0;
		long stop = System.currentTimeMillis();
		long time = stop - start;
		return bool;
	}

	@Override
	public Image getImage() {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}
}
