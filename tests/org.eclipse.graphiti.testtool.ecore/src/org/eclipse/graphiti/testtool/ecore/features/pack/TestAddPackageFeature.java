/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 355347 - Remove setters of Graphiti's Font Interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.ecore.features.pack;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class TestAddPackageFeature.
 */
public class TestAddPackageFeature extends AbstractAddShapeFeature {

	private static final IColorConstant PACKAGE_TEXT_FOREGROUND = new ColorConstant(51, 51, 153);

	private static final IColorConstant PACKAGE_FOREGROUND = IColorConstant.BLACK;

	private static final IColorConstant PACKAGE_BACKGROUND = new ColorConstant(255, 204, 153);

	/**
	 * Instantiates a new test add package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestAddPackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		// check if user wants to add a package
		if (context.getNewObject() instanceof EPackage) {

			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	public PictogramElement add(IAddContext context) {
		EPackage addedPackage = (EPackage) context.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService pecService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		ContainerShape containerShape = pecService.createContainerShape(targetDiagram, true);

		// check whether valid size is available, e.g. if called from the create
		// feature
		int width = context.getWidth() <= 0 ? 100 : context.getWidth();
		int height = context.getHeight() <= 0 ? 50 : context.getHeight();

		{
			// create and set graphics algorithm
			RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
			roundedRectangle.setForeground(manageColor(PACKAGE_FOREGROUND));
			roundedRectangle.setBackground(manageColor(PACKAGE_BACKGROUND));
			roundedRectangle.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);

			// create link and wire it
			link(containerShape, addedPackage);
		}

		// SHAPE WITH LINE
		{
			// create shape for line
			Shape shape = pecService.createShape(containerShape, false);

			// create and set graphics algorithm
			Polyline polyline = gaService.createPolyline(shape, new int[] { 0, 20, width, 20 });
			polyline.setForeground(manageColor(PACKAGE_FOREGROUND));
			polyline.setLineWidth(2);
		}

		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = pecService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createText(shape, addedPackage.getName());
			text.setForeground(manageColor(PACKAGE_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
			gaService.setLocationAndSize(text, 0, 0, width, 20);

			// create link and wire it
			link(shape, addedPackage);
		}

		layoutPictogramElement(containerShape);

		return containerShape;
	}
}
