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

import org.eclipse.graphiti.examples.chess.diagram.ChessFeatureProvider;
import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.examples.mm.chess.Types;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.context.impl.MultiDeleteInfo;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;

/**
 *
 */
public class MoveChessPieceFeature extends DefaultMoveShapeFeature implements IMoveShapeFeature {

	public MoveChessPieceFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getShape());

		// We can only move pieces
		if (!(bo instanceof Piece)) {
			return false;
		}
		Piece piece = (Piece) bo;

		// Moving pieces is only allowed from one square to another not
		// within the same square
		if (context.getSourceContainer().equals(context.getTargetContainer())) {
			return false;
		}

		// Find the source and target squares
		Square sourceSquare = (Square) getBusinessObjectForPictogramElement(context.getSourceContainer());
		Object targetBO = getBusinessObjectForPictogramElement(context.getTargetContainer());
		Square targetSquare;
		if (targetBO instanceof Square) {
			targetSquare = (Square) targetBO;
		} else if (targetBO instanceof Piece) {
			targetSquare = ((Piece) targetBO).getSquare();
		} else {
			// Not allowed to drop onto anything else than squares and pieces
			return false;
		}

		// Moving onto a square occupied by another own piece is not allowed
		if (targetSquare.getPiece() != null && targetSquare.getPiece().getOwner().equals(piece.getOwner())) {
			return false;
		}

		// Determine number of steps taken in file and rank direction
		int fileSteps = targetSquare.getFile().getValue() - sourceSquare.getFile().getValue();
		int rankSteps = targetSquare.getRank().getValue() - sourceSquare.getRank().getValue();

		// Check type specific moving rules
		if (Types.PAWN.equals(piece.getType())) {
			// Same file
			if (fileSteps == 0) {
				// White pawns move upwards to higher ranks
				if (Colors.LIGHT.equals(piece.getOwner())) {
					if (rankSteps == 1
							|| (rankSteps == 2 && Ranks.TWO.equals(sourceSquare.getRank()) && sourceSquare.getBoard()
									.getSquare(Ranks.THREE, sourceSquare.getFile()).getPiece() == null)) {
						// Allow to move 1 to front or 2 to front in case of
						// start position
						return targetSquare.getPiece() == null;
					}
				}
				// Black pawns move downwards to lower ranks
				else {
					if (rankSteps == -1
							|| (rankSteps == -2 && Ranks.SEVEN.equals(sourceSquare.getRank()) && sourceSquare
									.getBoard().getSquare(Ranks.SIX, sourceSquare.getFile()).getPiece() == null)) {
						// Allow to move 1 to front or 2 to front in case of
						// start position
						return targetSquare.getPiece() == null;
					}
				}
			}
			// Neighbour file
			else if (fileSteps == 1 || fileSteps == -1) {
				// White pawns move upwards to higher ranks
				if (Colors.LIGHT.equals(piece.getOwner())) {
					// Allow to move 1 to front
					if (rankSteps == 1) {
						// Allow taking diagonally
						return targetSquare.getPiece() != null
								&& targetSquare.getPiece().getOwner() != piece.getOwner();
					}
				}
				// Black pawns move downwards to lower ranks
				else {
					// Allow to move 1 to front
					if (rankSteps == -1) {
						// Allow taking diagonally
						return targetSquare.getPiece() != null
								&& targetSquare.getPiece().getOwner() != piece.getOwner();
					}
				}
			}
			return false;
		} else if (Types.KING.equals(piece.getType())) {
			// Kings are allowed to move either horizontally, vertically or
			// diagonally but just one step
			if (rankSteps >= -1 && rankSteps <= 1 && fileSteps >= -1 && fileSteps <= 1) {
				// Allow moving one to every side and diagonally and taking
				// other owner's pieces
				return targetSquare.getPiece() == null || targetSquare.getPiece().getOwner() != piece.getOwner();
			}
			return false;
		} else if (Types.ROOK.equals(piece.getType())) {
			// Rooks are allowed to move either horizontally or vertically
			return allowMovingToSides(sourceSquare, targetSquare, fileSteps, rankSteps);
		} else if (Types.BISHOP.equals(piece.getType())) {
			// Bishops are allowed to move diagonally
			return allowDiagonalMoving(sourceSquare, targetSquare, fileSteps, rankSteps);
		} else if (Types.QUEEN.equals(piece.getType())) {
			// Queens are allowed to move either horizontally, vertically or
			// diagonally
			return allowMovingToSides(sourceSquare, targetSquare, fileSteps, rankSteps)
					|| allowDiagonalMoving(sourceSquare, targetSquare, fileSteps, rankSteps);
		} else if (Types.KNIGHT.equals(piece.getType())) {
			// For one direction the gap needs to be 1, for the other it
			// needs to be 2. Jumping over other pieces is allowed for
			// knights
			if ((Math.abs(rankSteps) == 2 && Math.abs(fileSteps) == 1)
					|| (Math.abs(rankSteps) == 1 && Math.abs(fileSteps) == 2)) {
				return true;
			}
			return false;
		}

		// Should never get here
		throw new IllegalStateException("Invalid Piece type: " + piece.getType());
	}

	private boolean allowMovingToSides(Square sourceSquare, Square targetSquare, int fileGap, int rankGap) {
		Board board = sourceSquare.getBoard();

		// Same rank --> horizontal move
		if (rankGap == 0) {
			// Check if any square in between is occupied, if yes the move is
			// not allowed
			int from = Math.min(sourceSquare.getFile().getValue(), targetSquare.getFile().getValue());
			int to = Math.max(sourceSquare.getFile().getValue(), targetSquare.getFile().getValue());
			for (int i = from + 1; i < to; i++) {
				if (board.getSquare(sourceSquare.getRank(), Files.get(i)).getPiece() != null) {
					// Move over occupied square
					return false;
				}
			}
			// No occupied square in between --> allow
			return true;
		}

		// Same file --> vertical move
		if (fileGap == 0) {
			// Check if any square in between is occupied, if yes the move is
			// not allowed
			int from = Math.min(sourceSquare.getRank().getValue(), targetSquare.getRank().getValue());
			int to = Math.max(sourceSquare.getRank().getValue(), targetSquare.getRank().getValue());
			for (int i = from + 1; i < to; i++) {
				if (board.getSquare(Ranks.get(i), sourceSquare.getFile()).getPiece() != null) {
					// Move over occupied square
					return false;
				}
			}
			// No occupied square in between --> allow
			return true;
		}
		return false;
	}

	private boolean allowDiagonalMoving(Square sourceSquare, Square targetSquare, int fileGap, int rankGap) {
		Board board = sourceSquare.getBoard();
		if (Math.abs(rankGap) == Math.abs(fileGap)) {
			// Allow moving diagonally
			int fromRank = Math.min(sourceSquare.getRank().getValue(), targetSquare.getRank().getValue());
			int toRank = Math.max(sourceSquare.getRank().getValue(), targetSquare.getRank().getValue());

			// Determine if the rank and file directions are the same (upward or
			// downwards); in case not we need to reverse file direction
			// counting
			int fileDirection = 1;
			if (sourceSquare.getFile().getValue() > targetSquare.getFile().getValue()) {
				fileDirection = -1;
			}

			// Check if any square in between is occupied, if yes the move is
			// not allowed
			for (int i = 1; i < toRank - fromRank; i++) {
				if (board.getSquare(Ranks.get(fromRank + i),
						Files.get(sourceSquare.getFile().getValue() + (i * fileDirection))).getPiece() != null) {
					// Move over occupied square
					return false;
				}
			}
			// Move is ok
			return true;
		}
		// Move is not diagonal, not ok
		return false;
	}

	protected void preMoveShape(IMoveShapeContext context) {
		// We need an instance of MoveShapeContext (and can be sure to have it)
		// to be able to modify the X and Y coordinates
		MoveShapeContext moveContext = (MoveShapeContext) context;

		// Set the X and Y coordinates to 0; they are set to the location the
		// user dropped the shape but pieces always need a location of 0 because
		// the visible polygon already has the offset from the source of the
		// containing shape
		moveContext.setX(0);
		moveContext.setY(0);

		Object targetContainerBO = getBusinessObjectForPictogramElement(context.getTargetContainer());
		if (targetContainerBO instanceof Piece) {
			// Dropped onto piece --> correct the target shape to the shape of
			// the square
			moveContext.setTargetContainer(context.getTargetContainer().getContainer());
		}
	}

	@Override
	protected void postMoveShape(IMoveShapeContext context) {
		// Find the moved piece target square (target container was already set
		// to the square shape in preMoveShape)
		Piece piece = (Piece) getBusinessObjectForPictogramElement(context.getShape());
		Square targetSquare = (Square) getBusinessObjectForPictogramElement(context.getTargetContainer());

		// Check for taking and delete the taken piece
		if (targetSquare.getPiece() != null) {
			deleteTakenPieceFromSquare(targetSquare);
		}

		// Adapt business model to reflect new containment
		piece.setSquare(targetSquare);

		// Adapt shape
		adaptMovedPieceShape(context, targetSquare);
	}

	private void adaptMovedPieceShape(IMoveShapeContext context, Square targetSquare) {
		// Set the line color; it needs to be the opposite color of the square
		Polygon polygon = (Polygon) context.getShape().getGraphicsAlgorithm();
		if (Colors.LIGHT.equals(targetSquare.getColor())) {
			polygon.setForeground(manageColor(IColorConstant.BLACK));
		} else {
			polygon.setForeground(manageColor(IColorConstant.WHITE));
		}
		polygon.setLineWidth(2);
	}

	private void deleteTakenPieceFromSquare(Square targetSquare) {
		// Find the shape
		ContainerShape takenPieceShape = (ContainerShape) Graphiti.getLinkService()
				.getPictogramElements(getDiagram(), targetSquare.getPiece()).get(0);

		// Create a delete context for that shape and prevent "are you sure?"
		// popup
		DeleteContext deleteContext = new DeleteContext(takenPieceShape);
		IMultiDeleteInfo multiDeleteInfo = new MultiDeleteInfo(false, false, 0);
		deleteContext.setMultiDeleteInfo(multiDeleteInfo);

		// Delegate to the delete feature for pieces; since it is disabled for
		// the UI we need to set programmatic mode for the feature provider,
		// which will enable delivery of the feature
		try {
			((ChessFeatureProvider) getFeatureProvider()).setProgrammaticFeatureCallActive(true);
			IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(deleteContext);
			deleteFeature.execute(deleteContext);
		} finally {
			// Make sure to set back programmatic mode
			((ChessFeatureProvider) getFeatureProvider()).setProgrammaticFeatureCallActive(false);
		}
	}
}
