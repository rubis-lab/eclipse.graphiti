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
package org.eclipse.graphiti.pattern.id;

import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * @since 0.10
 * @experimental This API is in an experimental state and should be used by
 *               clients, as it not final and can be removed or changed without
 *               prior notice!
 */
public class IdLayoutContext extends LayoutContext {

	private GraphicsAlgorithm graphicsAlgorithm;
	private PictogramElement rootPictogramElement;

	public IdLayoutContext(PictogramElement pictogramElement, GraphicsAlgorithm graphicsAlgorithm,
			PictogramElement rootPictogramElement) {
		super(pictogramElement);
		this.graphicsAlgorithm = graphicsAlgorithm;
		this.rootPictogramElement = rootPictogramElement;
	}

	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return graphicsAlgorithm;
	}

	public void setGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	public PictogramElement getRootPictogramElement() {
		return rootPictogramElement;
	}

	public void setRootPictogramElement(PictogramElement rootPictogramElement) {
		this.rootPictogramElement = rootPictogramElement;
	}
}
