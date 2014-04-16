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
 * This class represents a special {@link UpdateContext} suitable for usage
 * within an {@link IdPattern}. Besides the usual {@link UpdateContext} options
 * this context object also stores the associated {@link GraphicsAlgorithm} for
 * the {@link PictogramElement} of the context, the root
 * {@link PictogramElement} of the pattern and the linked domain object. The
 * second may be of use for easier finding a certain child within a pattern
 * representation.
 * 
 * @since 0.10
 * @experimental This API is in an experimental state and should be used by
 *               clients only with care, as it not final and can be removed or
 *               changed without prior notice!
 */
public class IdUpdateContext extends UpdateContext {

	private GraphicsAlgorithm graphicsAlgorithm;
	private PictogramElement rootPictogramElement;
	private Object domainObject;

	/**
	 * Creates a new {@link IdUpdateContext} instance holding the given
	 * pictogram model instances and domain object instance.
	 * 
	 * @param pictogramElement
	 *            The {@link PictogramElement} that shall be updated, see
	 *            {@link UpdateContext}.
	 * @param graphicsAlgorithm
	 *            The associated {@link GraphicsAlgorithm} for the
	 *            {@link PictogramElement}
	 * @param rootPictogramElement
	 *            The root pictogram element, this is the one that holds the ID
	 *            property for the pattern
	 * @param domainObject
	 *            The domain object that is linked with the pattern root
	 *            pictogram element.
	 */
	public IdUpdateContext(PictogramElement pictogramElement, GraphicsAlgorithm graphicsAlgorithm,
			PictogramElement rootPictogramElement, Object domainObject) {
		super(pictogramElement);
		this.graphicsAlgorithm = graphicsAlgorithm;
		this.rootPictogramElement = rootPictogramElement;
		this.setDomainObject(domainObject);
	}

	/**
	 * Returns the stored {@link PictogramElement}.
	 * 
	 * @return The instance of the {@link PictogramElement} to layout.
	 */
	@Override
	public PictogramElement getPictogramElement() {
		/*
		 * Overriding this method is necessary because otherwise clients calling
		 * this method will get access warnings (PictogramElementContext
		 * offering this method is not part of the API).
		 */
		return super.getPictogramElement();
	}

	/**
	 * Returns the stored {@link GraphicsAlgorithm}.
	 * 
	 * @return The instance of the {@link GraphicsAlgorithm} for the
	 *         {@link PictogramElement} to update.
	 */
	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return graphicsAlgorithm;
	}

	/**
	 * Sets a new {@link GraphicsAlgorithm} for the {@link PictogramElement} to
	 * update.
	 * 
	 * @param graphicsAlgorithm
	 *            The new instance to set.
	 */
	public void setGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	/**
	 * Returns the stored root {@link PictogramElement}.
	 * 
	 * @return The instance of the root {@link PictogramElement}.
	 */
	public PictogramElement getRootPictogramElement() {
		return rootPictogramElement;
	}

	/**
	 * Sets a new root {@link PictogramElement}.
	 * 
	 * @param rootPictogramElement
	 *            The new instance to set.
	 */
	public void setRootPictogramElement(PictogramElement rootPictogramElement) {
		this.rootPictogramElement = rootPictogramElement;
	}

	/**
	 * Returns the stored domain object instance.
	 * 
	 * @return The stored instance of the domain object.
	 */
	public Object getDomainObject() {
		return domainObject;
	}

	/**
	 * Sets a new domain object instance.
	 * 
	 * @param domainObject
	 *            The new instance to set.
	 */
	public void setDomainObject(Object domainObject) {
		this.domainObject = domainObject;
	}
}
