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
package org.eclipse.graphiti.testtool.sketch.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.testtool.sketch.SketchFeatureProvider;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class SketchCreateGaFeature.
 */
public abstract class SketchCreateGaFeature extends AbstractCreateFeature {

	private static final String NEW_ELEMENT = "new element";
	private Class<? extends GraphicsAlgorithm> gaType;

	/**
	 * Instantiates a new sketch create ga feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 * @param gaType
	 *            the ga type
	 */
	public SketchCreateGaFeature(IFeatureProvider fp, String name, String description, Class<? extends GraphicsAlgorithm> gaType) {
		super(fp, name, description);
		setGaType(gaType);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		boolean ret = false;
		ContainerShape targetContainer = context.getTargetContainer();
		if (targetContainer != null) {
			ret = true;
		}
		return ret;
	}

	@Override
	public Object[] create(ICreateContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		AnchorContainer newAc = createAnchorContainer(targetContainer);
		IPeCreateService pecService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		final ChopboxAnchor newAnchor = pecService.createChopboxAnchor(newAc);

		int width = context.getWidth() < 50 ? 50 : context.getWidth();
		int height = context.getHeight() < 50 ? 50 : context.getHeight();

		GraphicsAlgorithm newGa = createGa(newAc, width, height);

		gaService.setLocationAndSize(newGa, context.getX(), context.getY(), width, height);

		AbstractText label;
		if (isMultiLineText()) {
			label = gaService.createDefaultMultiText(newGa, NEW_ELEMENT);
		} else {
			label = gaService.createText(newGa, NEW_ELEMENT);
		}
		Font font = gaService.manageFont(getDiagram(), "Comic Sans MS", 14);
		label.setFont(font);
		label.setForeground(manageColor(IColorConstant.BLUE));
		label.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		label.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(label, 0, 0, width, height);
		layoutPictogramElement(newAc);

		final Connection targetConnection = context.getTargetConnection();
		if (targetConnection != null) {
			gaService.setLocation(newGa, context.getX() - (width / 2), context.getY() - (height / 2));

			Anchor oldEndAnchor = targetConnection.getEnd();
			targetConnection.setEnd(newAnchor);

			Connection connection = pecService.createFreeFormConnection(getDiagram());
			Polyline p = gaService.createPolyline(connection);

			GraphicsAlgorithm targetConnectionGraphicsAlgorithm = targetConnection.getGraphicsAlgorithm();
			p.setLineWidth(targetConnectionGraphicsAlgorithm.getLineWidth());
			p.setForeground(targetConnectionGraphicsAlgorithm.getForeground());
			p.setLineStyle(targetConnectionGraphicsAlgorithm.getLineStyle());

			connection.setStart(newAnchor);
			connection.setEnd(oldEndAnchor);
		}

		SketchFeatureProvider fp = (SketchFeatureProvider) getFeatureProvider();
		if (fp.isTestMode() && (newGa instanceof Rectangle)) {
			BoxRelativeAnchor bra = pecService.createBoxRelativeAnchor(newAc);
			Rectangle r = gaService.createRectangle(bra);
			gaService.setLocationAndSize(r, -5, 0, 10, 10);
			r.setTransparency(1.0);
			bra.setRelativeWidth(0.5);

			FixPointAnchor fpa = pecService.createFixPointAnchor(newAc);
			r = gaService.createRectangle(fpa);
			gaService.setLocationAndSize(r, 0, 0, 10, 10);
			r.setTransparency(1.0);
			fpa.setLocation(gaService.createPoint(10, 10));
		}

		return new Object[] { newAc };
	}

	/**
	 * Creates the anchor container.
	 * 
	 * @param targetContainer
	 *            the target container
	 * 
	 * @return the anchor container
	 */
	abstract protected AnchorContainer createAnchorContainer(ContainerShape targetContainer);

	/**
	 * Creates the ga.
	 * 
	 * @param gac
	 *            the gac
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * 
	 * @return the graphics algorithm
	 */
	protected GraphicsAlgorithm createGa(GraphicsAlgorithmContainer gac, int width, int height) {
		GraphicsAlgorithm ret = null;
		IGaService gaService = Graphiti.getGaService();
		if (RoundedRectangle.class.equals(gaType)) {
			ret = gaService.createRoundedRectangle(gac, 10, 10);
		} else if (Rectangle.class.equals(gaType)) {
			ret = gaService.createRectangle(gac);
		} else if (Ellipse.class.equals(gaType)) {
			ret = gaService.createEllipse(gac);
		} else if (Polyline.class.equals(gaType)) {
			// top-left, top-right, bottom-right, bottom-left
			int[] xy = new int[] { 0, 0, width, 0, width, height, 0, height };
			int[] ba = new int[] { 0, 0, 20, 40, 40, 20, 0, 0 };
			Polyline p = gaService.createPolyline(gac, xy, ba);
			ret = p;
		} else if (Polygon.class.equals(gaType)) {
			if (getCreateName().startsWith("Tria")) { // triangle
				// top-middle, bottom-right, bottom-left
				int xy[] = new int[] { width / 2, 0, width, height, 0, height };
				int ba[] = new int[] { 0, 0, 40, 40, 40, 40 };
				Polygon p = gaService.createPolygon(gac, xy, ba);
				ret = p;
			} else { // arrow
				// top-left, top-middle, middle-right, bottom-middle, bottom-right
				int xy[] = new int[] { 0, 0, (2 * width / 3), 0, width, height / 2, (2 * width / 3), height, 0, height };
				int ba[] = new int[] { 0, 0, 30, 15, 0, 0, 15, 30, 60, 60 };
				Polygon p = gaService.createPolygon(gac, xy, ba);
				ret = p;
			}
		}

		if (ret != null) {
			Diagram diagram = getDiagram();
			ret.setForeground(gaService.manageColor(diagram, IColorConstant.BLACK));
			ret.setBackground(gaService.manageColor(diagram, IColorConstant.WHITE));
			ret.setTransparency(0.3);
			ret.setLineWidth(2);
		}

		return ret;
	}

	private void setGaType(Class<? extends GraphicsAlgorithm> gaType) {
		this.gaType = gaType;
	}

	protected Class<?> getGaType() {
		return gaType;
	}

	protected boolean isMultiLineText() {
		return true;
	}
}
