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

/**
 * The Interface IContainerNode.
 */
public interface IContainerNode {

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	Object getParent();

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	Object[] getChildren();

	/**
	 * Checks for children.
	 * 
	 * @return true, if successful
	 */
	boolean hasChildren();

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	String getText();

	Image getImage();
}