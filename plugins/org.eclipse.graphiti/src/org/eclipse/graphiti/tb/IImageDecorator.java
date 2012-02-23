/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 358255 - Add Border/Background decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.datatypes.ILocation;

/**
 * Image decorators can be used to add an image to the visualization of a shape
 * without modifying the dirty state of the displaying editor, see
 * {@link IDecorator}.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IImageDecorator extends IDecorator, ILocation {
	
	/**
	 * Gets the image id of the image used to decorate a shape.
	 * 
	 * @return a {@link String} holding the image id
	 */
	public String getImageId();
}
