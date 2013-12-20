/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mgorning - Bug 342262 - enhanced resize behavior for container shapes
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Interface IResizeShapeContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IResizeShapeContext extends IResizeContext, IPictogramElementContext {

	/**
	 * Gets the shape.
	 * 
	 * @return the shape
	 */
	Shape getShape();

	/**
	 * Returns the specified direction. The direction is specified using
	 * {@link IResizeShapeContext#NORTH}, {@link IResizeShapeContext#NORTH_EAST}
	 * , etc.
	 * 
	 * @return the direction
	 * 
	 * @since 0.9
	 */
	int getDirection();
}