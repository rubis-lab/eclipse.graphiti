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

import org.eclipse.graphiti.features.context.IConnectionContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.datatypes.Color;
import org.eclipse.graphiti.mm.links.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.mapping.IStructureMapping;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;
import org.eclipse.graphiti.pattern.mapping.data.ITextDataMapping;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class AbstractConnectionPattern.
 */
public abstract class AbstractConnectionPattern extends AbstractBasePattern implements IConnectionPattern {

	/**
	 * Gets the adds the connection context.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the adds the connection context
	 */
	protected static AddConnectionContext getAddConnectionContext(ICreateConnectionContext context) {
		AddConnectionContext result = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		return result;
	}

	/**
	 * Instantiates a new abstract connection pattern.
	 */
	public AbstractConnectionPattern() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.ICreateConnectionFeature#canCreate(org.
	 * eclipse.graphiti.features.context.ICreateConnectionContext)
	 */
	public boolean canCreate(ICreateConnectionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.ICreateConnectionFeature#canStartConnection
	 * (org.eclipse.graphiti.features.context.ICreateConnectionContext)
	 */
	public boolean canStartConnection(ICreateConnectionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.ICreateConnectionFeature#create(org.eclipse
	 * .graphiti.features.context.ICreateConnectionContext)
	 */
	public Connection create(ICreateConnectionContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds the graphical representation.
	 * 
	 * @param context
	 *            the context
	 * @param newObject
	 *            the new object
	 * 
	 * @return the connection
	 */
	protected Connection addGraphicalRepresentation(IConnectionContext context, Object newObject) {
		AddConnectionContext newContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		newContext.setNewObject(newObject);
		return (Connection) getFeatureProvider().addIfPossible(newContext);
	}

	/**
	 * Gets the text.
	 * 
	 * @param structureMapping
	 *            the structure mapping
	 * @param link
	 *            the link
	 * 
	 * @return the text
	 */
	protected String getText(IStructureMapping structureMapping, PictogramLink link) {
		String ret = null;
		IDataMapping dm = structureMapping.getDataMapping();
		if (dm instanceof ITextDataMapping) {
			ret = ((ITextDataMapping) dm).getText(link);
		}
		return ret;
	}

	/**
	 * Layout pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void layoutPictogramElement(PictogramElement pe) {
		LayoutContext context = new LayoutContext(pe);
		getFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * Manage color.
	 * 
	 * @param colorConstant
	 *            the color constant
	 * 
	 * @return the color
	 */
	protected Color manageColor(IColorConstant colorConstant) {
		return Graphiti.getGaService().manageColor(getFeatureProvider().getDiagramTypeProvider().getDiagram(), colorConstant);
	}

	/**
	 * Manage color.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 * 
	 * @return the color
	 */
	protected Color manageColor(int red, int green, int blue) {
		return Graphiti.getGaService().manageColor(getFeatureProvider().getDiagramTypeProvider().getDiagram(), red, green, blue);
	}

	/**
	 * Update pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void updatePictogramElement(PictogramElement pe) {
		UpdateContext context = new UpdateContext(pe);
		getFeatureProvider().updateIfPossible(context);
		layoutPictogramElement(pe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreateInfo#getCreateDescription()
	 */
	public String getCreateDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreateInfo#getCreateImageId()
	 */
	public String getCreateImageId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCreateLargeImageId() {
		return getCreateImageId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreateInfo#getCreateName()
	 */
	public String getCreateName() {
		// TODO Auto-generated method stub
		return null;
	}
}
