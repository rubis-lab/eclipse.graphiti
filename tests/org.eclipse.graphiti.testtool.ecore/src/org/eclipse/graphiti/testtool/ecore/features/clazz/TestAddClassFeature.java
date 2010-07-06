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
package org.eclipse.graphiti.testtool.ecore.features.clazz;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Orientation;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class TestAddClassFeature.
 */
public class TestAddClassFeature extends AbstractAddShapeFeature {

	/**
	 * The Constant CLASS_TEXT_FOREGROUND.
	 */
	static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(51, 51, 153);

	/**
	 * The Constant CLASS_FOREGROUND.
	 */
	static final IColorConstant CLASS_FOREGROUND = IColorConstant.BLUE;

	/**
	 * The Constant CLASS_BACKGROUND.
	 */
	static final IColorConstant CLASS_BACKGROUND = IColorConstant.LIGHT_BLUE;

	/**
	 * Instantiates a new test add class feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestAddClassFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof EClass) {

			ContainerShape targetContainer = context.getTargetContainer();

			if (targetContainer instanceof Diagram) {
				return true;
			}

			if (getBusinessObjectForPictogramElement(targetContainer) instanceof EPackage) {
				return true;
			}
		}
		return false;
	}

	public PictogramElement add(IAddContext context) {
		EClass addedClass = (EClass) context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService pecService = Graphiti.getPeCreateService();
		IGaService gaService=Graphiti.getGaService();
		ContainerShape containerShape = pecService.createContainerShape(targetContainer, true);

		// check whether valid size is available, e.g. if called from the create
		// feature
		int width = context.getWidth() <= 0 ? 150 : context.getWidth();
		int height = context.getHeight() <= 0 ? 150 : context.getHeight();

		{
			// create and set graphics algorithm
			RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
			roundedRectangle.setForeground(manageColor(CLASS_FOREGROUND));
			roundedRectangle.setBackground(manageColor(CLASS_BACKGROUND));
			roundedRectangle.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);

			// create link and wire it
			link(containerShape, addedClass);
		}

		// SHAPE WITH LINE
		{
			// create shape for line
			Shape shape = pecService.createShape(containerShape, false);

			// create and set graphics algorithm
			Polyline polyline = gaService.createPolyline(shape, new int[] { 0, 20, width, 20 });
			polyline.setForeground(manageColor(CLASS_FOREGROUND));
			polyline.setLineWidth(2);
		}

		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = pecService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(shape, addedClass.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 0, 0, width, 20);

			// create link and wire it
			link(shape, addedClass);
		}

		layoutPictogramElement(containerShape);

		pecService.createChopboxAnchor(containerShape);

		return containerShape;
	}
}
