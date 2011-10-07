/**
 * <copyright>
 * 
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.chess.features;

import java.util.Iterator;

import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaLayoutService;
import org.eclipse.graphiti.util.IColorConstant;

public class AddChessBoardFeature extends AbstractAddShapeFeature implements IAddFeature {

	private static final int SQUARE_SIZE = 50;
	private static final int BOARD_SIZE = SQUARE_SIZE * 8;

	public AddChessBoardFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof Board) {
			if (context.getTargetContainer() instanceof Diagram) {
				// Add new board only in case of an empty diagram
				return context.getTargetContainer().getChildren().size() == 0;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		// Get the Graphiti services
		ICreateService createService = Graphiti.getCreateService();
		IGaLayoutService layoutService = Graphiti.getGaLayoutService();

		// Get the board to add
		Board board = (Board) context.getNewObject();

		// Create the visualization of the board as a square
		ContainerShape outerContainerShape = createService.createContainerShape(getDiagram(), true);
		Rectangle outerRectangle = createService.createRectangle(outerContainerShape);
		layoutService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), BOARD_SIZE, BOARD_SIZE);

		// Link the visualization with the board
		link(outerContainerShape, board);

		// Add the 64 single squares to the board
		for (Iterator<Square> it = board.getSquares().iterator(); it.hasNext();) {
			Square square = it.next();

			// Visualize as a square at the correct location
			ContainerShape squareShape = createService.createContainerShape(outerContainerShape, true);
			Rectangle squareRectangle = createService.createRectangle(squareShape);
			layoutService.setLocationAndSize(squareRectangle, square.getOffsetX() * SQUARE_SIZE, square.getOffsetY()
					* SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

			// Set the fill color
			IColorConstant color;
			if (square.getColor() == Colors.LIGHT) {
				color = IColorConstant.WHITE;
			} else {
				color = IColorConstant.BLACK;
			}
			squareRectangle.setBackground(manageColor(color));

			// And do the linking
			link(squareShape, square);
		}

		return outerContainerShape;
	}
}
