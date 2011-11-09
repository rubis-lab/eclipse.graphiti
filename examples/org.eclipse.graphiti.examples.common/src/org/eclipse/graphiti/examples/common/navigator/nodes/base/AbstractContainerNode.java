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

	protected AbstractContainerNode() {
		super();
	}

	public String getText() {
		String ret = getContainerName();
		return ret;
	}

	/**
	 * Gets the container name.
	 * 
	 * @return the container name
	 */
	abstract protected String getContainerName();

	public boolean hasChildren() {
			return true;
	}

	public Image getImage() {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}
}
