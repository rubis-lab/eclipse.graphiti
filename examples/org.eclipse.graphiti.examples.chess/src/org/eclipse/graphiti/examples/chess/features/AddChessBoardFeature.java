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
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaLayoutService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.IColorConstant;

public class AddChessBoardFeature extends AbstractAddShapeFeature implements IAddFeature {

	private static final int SQUARE_SIZE = 50;
	private static final int BOARD_SIZE = SQUARE_SIZE * 8;

	private static final int FRAME_WIDTH = 20;
	private static final int FRAME_HEIGHT = 20;

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

		// Create the visualisation of the board as a square
		ContainerShape outerContainerShape = createService.createContainerShape(getDiagram(), true);

		// Create invisible outer rectangle that holds the rank and file names
		Rectangle outerRectangle = createService.createRectangle(outerContainerShape);
		outerRectangle.setBackground(manageColor(IColorConstant.LIGHT_GRAY));
		outerRectangle.setTransparency(0.8d);
		layoutService.setLocationAndSize(outerRectangle, context.getX() - FRAME_WIDTH, context.getY() - FRAME_HEIGHT,
				BOARD_SIZE + FRAME_WIDTH * 2, BOARD_SIZE + FRAME_HEIGHT * 2);
		for (Files file : Files.values()) {
			if (Files.NONE.equals(file)) {
				continue;
			}

			// Display file names on top
			Text text = createService.createText(outerRectangle);
			setLayoutForBorderTexts(layoutService, text);
			layoutService.setLocationAndSize(text, FRAME_WIDTH + (file.getValue() - 1) * SQUARE_SIZE, 0, SQUARE_SIZE,
					FRAME_HEIGHT);
			text.setValue(file.getLiteral());

			// Display file names at bottom
			text = createService.createText(outerRectangle);
			setLayoutForBorderTexts(layoutService, text);
			layoutService.setLocationAndSize(text, FRAME_WIDTH + (file.getValue() - 1) * SQUARE_SIZE, FRAME_HEIGHT
					+ BOARD_SIZE, SQUARE_SIZE, FRAME_HEIGHT);
			text.setValue(file.getLiteral());
		}
		for (Ranks rank : Ranks.values()) {
			if (Ranks.NONE.equals(rank)) {
				continue;
			}

			// Display rank names at left side
			Text text = createService.createText(outerRectangle);
			setLayoutForBorderTexts(layoutService, text);
			layoutService.setLocationAndSize(text, 0, FRAME_WIDTH + (8 - rank.getValue()) * SQUARE_SIZE, FRAME_WIDTH,
					SQUARE_SIZE);
			text.setValue(Integer.toString(rank.getValue()));

			// Display rank names at right side
			text = createService.createText(outerRectangle);
			setLayoutForBorderTexts(layoutService, text);
			layoutService.setLocationAndSize(text, FRAME_WIDTH + BOARD_SIZE, FRAME_WIDTH + (8 - rank.getValue())
					* SQUARE_SIZE, FRAME_WIDTH, SQUARE_SIZE);
			text.setValue(Integer.toString(rank.getValue()));
		}

		Rectangle boardRectangle = createService.createRectangle(outerRectangle);
		boardRectangle.setParentGraphicsAlgorithm(outerRectangle);
		layoutService.setLocationAndSize(boardRectangle, 20, 20, BOARD_SIZE, BOARD_SIZE);

		// Link the visualisation with the board
		link(outerContainerShape, board);

		// Add the 64 single squares to the board
		for (Iterator<Square> it = board.getSquares().iterator(); it.hasNext();) {
			Square square = it.next();

			// Visualise as a square at the correct location
			ContainerShape squareShape = createService.createContainerShape(outerContainerShape, true);
			Rectangle squareRectangle = createService.createRectangle(squareShape);
			layoutService.setLocationAndSize(squareRectangle, FRAME_WIDTH + square.getOffsetX() * SQUARE_SIZE,
					FRAME_HEIGHT + square.getOffsetY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

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

			// Create an anchor for attaching moves
			createService.createChopboxAnchor(squareShape);
			BoxRelativeAnchor relativeAnchor = createService.createBoxRelativeAnchor(squareShape);
			relativeAnchor.setRelativeHeight(0.5d);
			relativeAnchor.setRelativeWidth(0.5d);
			relativeAnchor.setReferencedGraphicsAlgorithm(squareRectangle);
			relativeAnchor.setUseAnchorLocationAsConnectionEndpoint(true);
			Ellipse anchorEllipse = createService.createEllipse(relativeAnchor);
			layoutService.setLocationAndSize(anchorEllipse, 25, 25, 0, 0);
		}

		return outerContainerShape;
	}

	/**
	 * @param layoutService
	 * @param file
	 * @param text
	 */
	private void setLayoutForBorderTexts(IGaLayoutService layoutService, Text text) {
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setForeground(manageColor(IColorConstant.BLACK));
		text.setFont(GraphitiUi.getGaService().manageFont(getDiagram(), IGaService.DEFAULT_FONT, 12, false, true));
	}
}
