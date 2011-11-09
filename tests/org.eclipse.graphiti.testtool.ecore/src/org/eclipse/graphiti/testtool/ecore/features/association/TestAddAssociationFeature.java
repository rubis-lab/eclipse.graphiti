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
package org.eclipse.graphiti.testtool.ecore.features.association;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class TestAddAssociationFeature.
 */
public class TestAddAssociationFeature extends AbstractAddFeature {

	/**
	 * Instantiates a new test add association feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestAddAssociationFeature(IFeatureProvider fp) {
		super(fp);
	}

	public PictogramElement add(IAddContext context) {

		IAddConnectionContext addConnectionContext = (IAddConnectionContext) context;

		// add graphical representation for this connection
		IPeCreateService pecService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		Connection newConnection = pecService.createFreeFormConnection(getDiagram());
		Polyline p = gaService.createPolyline(newConnection);
		p.setLineWidth(2);
		p.setForeground(manageColor(IColorConstant.BLACK));
		newConnection.setStart(addConnectionContext.getSourceAnchor());
		newConnection.setEnd(addConnectionContext.getTargetAnchor());

		// add dynamic text decorator for the association name
		ConnectionDecorator labelDecorator = pecService.createConnectionDecorator(newConnection, true, 0.5, true);
		Text text = gaService.createDefaultText(getDiagram(), labelDecorator);
		text.setForeground(manageColor(IColorConstant.BLACK));
		gaService.setLocationAndSize(text, 30, 20, 50, 12);

		// add static graphical decorators (composition and navigable)
		ConnectionDecorator cd;
		cd = pecService.createConnectionDecorator(newConnection, false, 0.0, true);
		createArrow(cd);
		cd = pecService.createConnectionDecorator(newConnection, false, 1.0, true);
		createRhombus(cd, true);

		// set association name in the text decorator
		EReference association = (EReference) context.getNewObject();
		text.setValue(association.getName());

		return newConnection;
	}

	public boolean canAdd(IAddContext context) {
		// return true if given business object is of instance association
		if (context instanceof IAddConnectionContext && context.getNewObject() instanceof EReference) {
			return true;
		}
		return false;
	}

	// create an arrow
	private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {

		Color fg = manageColor(IColorConstant.BLACK);

		Polyline p = Graphiti.getGaCreateService().createPolyline(gaContainer, new int[] { -9, 6, 0, 0, -9, -6 });
		p.setForeground(fg);
		p.setLineWidth(2);

		return p;
	}

	// rhombus
	private Polygon createRhombus(GraphicsAlgorithmContainer gaContainer, boolean isFilled) {

		Color fg = manageColor(IColorConstant.BLACK);
		Color fillColor;
		if (isFilled) {
			fillColor = manageColor(IColorConstant.BLACK);
		} else {
			fillColor = manageColor(IColorConstant.WHITE);
		}

		Polygon p = Graphiti.getGaCreateService().createPolygon(gaContainer, new int[] { 0, 0, -6, 6, -12, 0, -6, -6 });
		p.setForeground(fg);
		p.setBackground(fillColor);

		p.setLineWidth(2);

		return p;
	}
}
