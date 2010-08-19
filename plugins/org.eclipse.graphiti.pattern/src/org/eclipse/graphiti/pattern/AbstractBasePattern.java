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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;

/**
 * The Class AbstractBasePattern.
 */
public abstract class AbstractBasePattern implements IFeatureProviderHolder {

	private IFeatureProvider featureProvider;

	/**
	 * Adds the.
	 * 
	 * @param context
	 *            the add context
	 * 
	 * @return the pictogram element
	 */
	public PictogramElement add(IAddContext context) {
		return null;
	}

	/**
	 * Can add.
	 * 
	 * @param context
	 *            the add context
	 * 
	 * @return true, if successful
	 */
	public boolean canAdd(IAddContext context) {
		return false;
	}

	/**
	 * Gets the feature provider.
	 * 
	 * @return Returns the featureProvider.
	 */
	public IFeatureProvider getFeatureProvider() {
		return featureProvider;
	}

	/**
	 * Sets the feature provider.
	 * 
	 * @param featureProvider
	 *            the new featureProvider
	 */
	public void setFeatureProvider(IFeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	/**
	 * Gets the business object for pictogram element.
	 * 
	 * @param pe
	 *            the pictogram element
	 * 
	 * @return the business object for pictogram element
	 */
	protected Object getBusinessObjectForPictogramElement(PictogramElement pe) {
		return getFeatureProvider().getBusinessObjectForPictogramElement(pe);
	}

	/**
	 * Gets the diagram editor.
	 * 
	 * @return the diagram editor
	 */
	protected IDiagramEditor getDiagramEditor() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagramEditor();
	}

	/**
	 * Gets the mapping provider.
	 * 
	 * @return the mapping provider
	 */
	protected IMappingProvider getMappingProvider() {
		return getFeatureProvider();
	}

	/**
	 * Link.
	 * 
	 * @param pe
	 *            the pictogram element
	 * @param businessObject
	 *            the business object
	 */
	protected void link(PictogramElement pe, Object businessObject) {
		link(pe, new Object[] { businessObject });
	}

	/**
	 * Link.
	 * 
	 * @param pe
	 *            the pictogram element
	 * @param businessObjects
	 *            the business objects
	 */
	protected void link(PictogramElement pe, Object businessObjects[]) {
		getMappingProvider().link(pe, businessObjects);
	}
}
