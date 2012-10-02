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

import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * @since 0.10
 * @experimental This API is in an experimental state and should be used by
 *               clients only with care, as it not final and can be removed or
 *               changed without prior notice!
 */
public class IdUpdateContext extends UpdateContext {

	private GraphicsAlgorithm graphicsAlgorithm;
	private PictogramElement rootPictogramElement;
	private Object domainObject;

	public IdUpdateContext(PictogramElement pictogramElement, GraphicsAlgorithm graphicsAlgorithm,
			PictogramElement rootPictogramElement, Object domainObject) {
		super(pictogramElement);
		this.graphicsAlgorithm = graphicsAlgorithm;
		this.rootPictogramElement = rootPictogramElement;
		this.setDomainObject(domainObject);
	}

	@Override
	public PictogramElement getPictogramElement() {
		/*
		 * Overriding this method is necessary because otherwise clients calling
		 * this method will get access warnings (PictogramElementContext
		 * offering this method is not part of the API).
		 */
		return super.getPictogramElement();
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

	public Object getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(Object domainObject) {
		this.domainObject = domainObject;
	}
}
