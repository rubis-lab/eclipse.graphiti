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
package org.eclipse.graphiti.sample.ecore.features.pack;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.datatypes.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

/**
 * The Class TestLayoutPackageFeature.
 */
public class TestLayoutPackageFeature extends AbstractLayoutFeature {
	private static final int MIN_HEIGHT = 40;

	private static final int MIN_WIDTH = 25;

	/**
	 * Instantiates a new test layout package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestLayoutPackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
		if (!(context.getPictogramElement() instanceof ContainerShape)) {
			return false;
		}

		// return true, if linked business object is a EPackage
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (bo instanceof EPackage);
	}

	public boolean layout(ILayoutContext context) {
		boolean ret = false;
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();

		// height
		{
			int containerHeight = containerGa.getHeight();
			if (containerHeight < MIN_HEIGHT) {
				containerGa.setHeight(MIN_HEIGHT);
				ret = true;
			}
		}

		// width
		{
			int containerWidth = containerGa.getWidth();

			if (containerWidth < MIN_WIDTH) {
				containerGa.setWidth(MIN_WIDTH);
				ret = true;
			}

			Collection<Shape> children = containerShape.getChildren();
			for (Shape shape : children) {
				if (!(getBusinessObjectForPictogramElement(shape) instanceof EClass)) {
					GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
					IGaService gaService = Graphiti.getGaService();
					IDimension size = gaService.calculateSize(graphicsAlgorithm);
					if (containerWidth != size.getWidth()) {
						if (graphicsAlgorithm instanceof Polyline) {
							Polyline polyline = (Polyline) graphicsAlgorithm;
							Point secondPoint = polyline.getPoints().get(1);
							polyline.getPoints().set(1, gaService.createPoint(containerWidth, secondPoint.getY()));
							ret = true;
						} else {
							gaService.setWidth(graphicsAlgorithm, containerWidth);
							ret = true;
						}

					}
				}
			}
		}
		return ret;
	}
}
