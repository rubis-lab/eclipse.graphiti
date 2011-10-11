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

import org.eclipse.graphiti.examples.chess.ChessImageProvider;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Types;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaLayoutService;

public class AddChessPieceFeature extends AbstractAddShapeFeature implements IAddFeature {

	private static final int SQUARE_SIZE = 50;

	public AddChessPieceFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof Piece) {
			return context.getTargetContainer().getChildren().size() < 64;
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		// Get the Graphiti services
		ICreateService createService = Graphiti.getCreateService();
		IGaLayoutService layoutService = Graphiti.getGaLayoutService();

		// Get the piece to add
		Piece piece = (Piece) context.getNewObject();

		// Get the image
		String imageId = null;
		if (Types.BISHOP.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_BISHOP;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_BISHOP;
			}
		} else if (Types.KING.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_KING;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_KING;
			}
		} else if (Types.KNIGHT.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_KNIGHT;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_KNIGHT;
			}
		} else if (Types.PAWN.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_PAWN;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_PAWN;
			}
		} else if (Types.QUEEN.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_QUEEN;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_QUEEN;
			}
		} else if (Types.ROOK.equals(piece.getType())) {
			if (Colors.DARK.equals(piece.getOwner())) {
				imageId = ChessImageProvider.IMG_BLACK_ROOK;
			} else {
				imageId = ChessImageProvider.IMG_WHITE_ROOK;
			}
		}

		// Create the visualization of the board as a square
		Shape pieceShape = createService.createShape(context.getTargetContainer(), true);
		Image image = createService.createImage(pieceShape, imageId);
		layoutService.setLocationAndSize(image, 0, 0, SQUARE_SIZE, SQUARE_SIZE);

		// Link the visualization with the board
		link(pieceShape, piece);

		return pieceShape;
	}
}
