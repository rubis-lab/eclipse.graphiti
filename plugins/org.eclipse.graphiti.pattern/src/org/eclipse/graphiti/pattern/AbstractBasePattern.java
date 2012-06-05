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
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * This is the abstract base class for patterns. Clients implementing own
 * patterns should not subclass this class, but use {@link AbstractPattern} or
 * {@link AbstractConnectionPattern} instead.
 */
public abstract class AbstractBasePattern implements IFeatureProviderHolder {

	private IFeatureProvider featureProvider;

	/**
	 * Clients must override this method to provide the functionality to add an
	 * existing domain object to a diagram. Corresponds to the
	 * {@link IAddFeature#add(IAddContext)} method. The default implementation
	 * simply does nothing and returns <code>null</code>.
	 * 
	 * @param context
	 *            The add context holding information about the added domain
	 *            object.
	 * 
	 * @return The root shape of the created pictogram tree.
	 */
	public PictogramElement add(IAddContext context) {
		return null;
	}

	/**
	 * Clients must override this method to indicate the framework that this
	 * pattern can add a domain object to the diagram. Corresponds to the
	 * {@link IAddFeature#canAdd(IAddContext)} method. The default
	 * implementation simply returns <code>false</code>.
	 * 
	 * @param context
	 *            The add context holding information about the added domain
	 *            object.
	 * 
	 * @return <code>true</code>, if the domain object can be added,
	 *         <code>false</code> otherwise.
	 */
	public boolean canAdd(IAddContext context) {
		return false;
	}

	/**
	 * Returns the feature provider for this pattern.
	 * 
	 * @return Returns the featureProvider.
	 */
	public IFeatureProvider getFeatureProvider() {
		return featureProvider;
	}

	/**
	 * Sets the feature provider for this pattern. Note that once a feature
	 * provider has been set, it should not be changed again.
	 * 
	 * @param featureProvider
	 *            The new featureProvider
	 */
	public void setFeatureProvider(IFeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	/**
	 * Helper method that resolves the domain object for the given pictogram
	 * element (shape).
	 * 
	 * @param pe
	 *            The pictogram element for which a domain object shall be
	 *            resolved.
	 * 
	 * @return The domain object for the given pictogram element or
	 *         <code>null</code> in case none could be found.
	 */
	protected Object getBusinessObjectForPictogramElement(PictogramElement pe) {
		return getFeatureProvider().getBusinessObjectForPictogramElement(pe);
	}

	/**
	 * Returns the diagram editor instance this pattern lives in.
	 * 
	 * @return The diagram editor
	 */
	protected IDiagramEditor getDiagramEditor() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagramEditor();
	}

	/**
	 * Returns the {@link IMappingProvider} that can be used to map pictogram
	 * elements onto domain objects and vice versa.
	 * 
	 * @return The mapping provider
	 */
	protected IMappingProvider getMappingProvider() {
		return getFeatureProvider();
	}

	/**
	 * Helper method to link a {@link PictogramElement} to a domain object.
	 * 
	 * @param pe
	 *            The pictogram element
	 * @param businessObject
	 *            The domain object
	 */
	protected void link(PictogramElement pe, Object businessObject) {
		link(pe, new Object[] { businessObject });
	}

	/**
	 * Helper method to link a {@link PictogramElement} to a number of domain
	 * objects.
	 * 
	 * @param pe
	 *            The pictogram element
	 * @param businessObjects
	 *            The business objects as an array
	 */
	protected void link(PictogramElement pe, Object businessObjects[]) {
		getMappingProvider().link(pe, businessObjects);
	}

	/**
	 * Returns the {@link Diagram} this pattern lives for.
	 * 
	 * @return The diagram
	 */
	protected Diagram getDiagram() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagram();
	}

	/**
	 * A convenience method for the color handling which simply calls
	 * {@link IGaService#manageColor(Diagram, IColorConstant)} to manage a
	 * {@link Color} used within the {@link Diagram}.
	 * 
	 * @param colorConstant
	 *            The color constant to manage.
	 * 
	 * @return The managed color.
	 */
	protected Color manageColor(IColorConstant colorConstant) {
		return Graphiti.getGaService().manageColor(getDiagram(), colorConstant);
	}

	/**
	 * A convenience method for the color handling which simply calls
	 * {@link IGaService#manageColor(Diagram, int, int, int)} to manage a
	 * {@link Color} used within the {@link Diagram}.
	 * 
	 * @param red
	 *            The red portion of the color to manage.
	 * @param green
	 *            The green portion of the color to manage.
	 * @param blue
	 *            The blue portion of the color to manage.
	 * 
	 * @return The managed color.
	 */
	protected Color manageColor(int red, int green, int blue) {
		return Graphiti.getGaService().manageColor(getDiagram(), red, green, blue);
	}

	/**
	 * A convenience method for the {@link Font} handling which simply calls
	 * {@link IGaService#manageFont(Diagram, String, int)} to manage a
	 * {@link Font} used within the {@link Diagram}.
	 * 
	 * @param name
	 *            The name of the font.
	 * @param size
	 *            The size of the font.
	 * @return The managed font instance.
	 * @since 0.9
	 */
	protected Font manageFont(String name, int size) {
		return Graphiti.getGaService().manageFont(getDiagram(), name, size);
	}

	/**
	 * A convenience method for the {@link Font} handling which simply calls
	 * {@link IGaService#manageFont(Diagram, String, int, boolean, boolean)} to
	 * manage a {@link Font} used within the {@link Diagram}.
	 * 
	 * @param name
	 *            The name of the font.
	 * @param size
	 *            The size of the font.
	 * @param isItalic
	 *            The italic flag of the font.
	 * @param isBold
	 *            The bold flag of the font.
	 * @return The managed font instance.
	 * @since 0.9
	 */
	protected Font manageFont(String name, int size, boolean isItalic, boolean isBold) {
		return Graphiti.getGaService().manageFont(getDiagram(), name, size, isItalic, isBold);
	}
}
