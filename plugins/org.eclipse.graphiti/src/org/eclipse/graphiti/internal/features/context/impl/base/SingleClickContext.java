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
 *    mgorning - Bug 386913 - Support also Single-Click-Features
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal.features.context.impl.base;

import org.eclipse.graphiti.features.context.ISingleClickContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class SingleClickContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class SingleClickContext extends AbstractClickContext implements ISingleClickContext {

	/**
	 * Instantiates a new single click context.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param innerPictogramElement
	 *            the inner pictogram element
	 * @param innerGraphicsAlgorithm
	 *            the inner graphics algorithm
	 */
	public SingleClickContext(PictogramElement pictogramElement, PictogramElement innerPictogramElement,
			GraphicsAlgorithm innerGraphicsAlgorithm) {
		super(pictogramElement, innerPictogramElement, innerGraphicsAlgorithm);
	}

}
