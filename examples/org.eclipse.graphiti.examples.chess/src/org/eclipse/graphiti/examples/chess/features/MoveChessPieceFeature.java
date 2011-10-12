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

import java.util.ArrayList;
import java.util.Collection;

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
import org.eclipse.graphiti.mm.pictograms.Shape;
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
		if (!(bo instanceof Piece)) {
			return false;
		}

		if (context.getSourceContainer().equals(context.getTargetContainer())) {
			// Moving pieces is only allowed from one square to another not
			// within the same square
			return false;
		} else {
			Collection<Shape> pieceShapes = null;

			Object targetContainerBo = getBusinessObjectForPictogramElement(context.getTargetContainer());
			if (!(targetContainerBo instanceof Square) && !(targetContainerBo instanceof Piece)) {
				// Only drop onto squares and pieces allowed (in general, more
				// evaluations follow below)
				return false;
			}

			// Find pieces that are located on the target
			if (targetContainerBo instanceof Square) {
				// Case 1: Drop on square --> check children shapes
				pieceShapes = context.getTargetContainer().getChildren();
			} else if (targetContainerBo instanceof Piece) {
				// Case 2: Drop on piece --> already found
				pieceShapes = new ArrayList<Shape>(1);
				pieceShapes.add(context.getTargetContainer());
			}

			if (pieceShapes != null) {
				for (Shape shape : pieceShapes) {
					Piece targetPiece = (Piece) getBusinessObjectForPictogramElement(shape);
					if (targetPiece.getOwner().equals(((Piece) bo).getOwner())) {
						// Moving onto a square occupied by another own piece is
						// not allowed
						return false;
					}
				}
			}

			// Check moving rules
			Piece piece = (Piece) bo;

			Square sourceSquare = (Square) getBusinessObjectForPictogramElement(context.getSourceContainer());
			Object targetBO = getBusinessObjectForPictogramElement(context.getTargetContainer());
			Square targetSquare;
			if (targetBO instanceof Square) {
				targetSquare = (Square) targetBO;
			} else {
				targetSquare = ((Piece) targetBO).getSquare();
			}
			int fileGap = targetSquare.getFile().getValue() - sourceSquare.getFile().getValue();
			int rankGap = targetSquare.getRank().getValue() - sourceSquare.getRank().getValue();

			if (Types.PAWN.equals(piece.getType())) {
				if (fileGap == 0) {
					// Same file
					if (Colors.LIGHT.equals(piece.getOwner())) {
						if (rankGap == 1
								|| (rankGap == 2 && Ranks.TWO.equals(sourceSquare.getRank()) && sourceSquare.getBoard()
										.getSquare(Ranks.THREE, sourceSquare.getFile()).getPiece() == null)) {
							// Allow move 1 to front or 2 to front in case of
							// start position
							return targetSquare.getPiece() == null;
						}
					} else {
						if (rankGap == -1
								|| (rankGap == -2 && Ranks.SEVEN.equals(sourceSquare.getRank()) && sourceSquare
										.getBoard().getSquare(Ranks.SIX, sourceSquare.getFile()).getPiece() == null)) {
							// Allow move 1 to front or 2 to front in case of
							// start position
							return targetSquare.getPiece() == null;
						}
					}
				} else if (fileGap == 1 || fileGap == -1) {
					if (Colors.LIGHT.equals(piece.getOwner())) {
						if (rankGap == 1) {
							// Allow taking diagonally
							return targetSquare.getPiece() != null
									&& targetSquare.getPiece().getOwner() != piece.getOwner();
						}
					} else {
						if (rankGap == -1) {
							// Allow taking diagonally
							return targetSquare.getPiece() != null
									&& targetSquare.getPiece().getOwner() != piece.getOwner();
						}
					}
				}
				return false;
			} else if (Types.KING.equals(piece.getType())) {
				if (rankGap >= -1 && rankGap <= 1 && fileGap >= -1 && fileGap <= 1) {
					// Allow moving one to every side and diagonally and taking
					// other owner's pieces
					return targetSquare.getPiece() == null || targetSquare.getPiece().getOwner() != piece.getOwner();
				}
				return false;
			} else if (Types.ROOK.equals(piece.getType())) {
				return allowMovingToSides(sourceSquare, targetSquare, fileGap, rankGap);
			} else if (Types.BISHOP.equals(piece.getType())) {
				return allowDiagonalMoving(sourceSquare, targetSquare, fileGap, rankGap);
			} else if (Types.QUEEN.equals(piece.getType())) {
				return allowMovingToSides(sourceSquare, targetSquare, fileGap, rankGap)
						|| allowDiagonalMoving(sourceSquare, targetSquare, fileGap, rankGap);
			} else if (Types.KNIGHT.equals(piece.getType())) {
				if ((Math.abs(rankGap) == 2 && Math.abs(fileGap) == 1)
						|| (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 2)) {
					return true;
				}
				return false;
			}

			// Should never get here
			return false;
		}
	}

	private boolean allowMovingToSides(Square sourceSquare, Square targetSquare, int fileGap, int rankGap) {
		Board board = sourceSquare.getBoard();
		if (rankGap == 0) {
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
		if (fileGap == 0) {
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
			int fileDirection = 1;
			if (sourceSquare.getFile().getValue() > targetSquare.getFile().getValue()) {
				fileDirection = -1;
			}
			for (int i = 1; i < toRank - fromRank; i++) {
				if (board.getSquare(Ranks.get(fromRank + i),
						Files.get(sourceSquare.getFile().getValue() + (i * fileDirection))).getPiece() != null) {
					// Move over occupied square
					return false;
				}
			}
			return true;
		}
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
		Piece piece = (Piece) getBusinessObjectForPictogramElement(context.getShape());
		Object targetContainerBO = getBusinessObjectForPictogramElement(context.getTargetContainer());

		// Find the target square
		Square targetSquare = null;
		if (targetContainerBO instanceof Square) {
			// Case 1: Dropped onto square shape
			targetSquare = (Square) targetContainerBO;
		} else if (targetContainerBO instanceof Piece) {
			// Case 2: Dropped onto piece shape
			targetSquare = ((Piece) targetContainerBO).getSquare();
		}

		// Check for taking and delete the taken piece
		if (targetSquare.getPiece() != null) {
			ContainerShape takenPieceShape = (ContainerShape) Graphiti.getLinkService()
					.getPictogramElements(getDiagram(), targetSquare.getPiece()).get(0);
			DeleteContext deleteContext = new DeleteContext(takenPieceShape);
			IMultiDeleteInfo multiDeleteInfo = new MultiDeleteInfo(false, false, 0);
			deleteContext.setMultiDeleteInfo(multiDeleteInfo);
			try {
				((ChessFeatureProvider) getFeatureProvider()).setProgrammaticFeatureCallActive(true);
				IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(deleteContext);
				deleteFeature.execute(deleteContext);
			} finally {
				((ChessFeatureProvider) getFeatureProvider()).setProgrammaticFeatureCallActive(false);
			}
		}

		// Adapt business model to reflect new containment
		piece.setSquare(targetSquare);

		// Set the line color; it needs to be the opposite color of the square
		Polygon polygon = (Polygon) context.getShape().getGraphicsAlgorithm();
		if (Colors.LIGHT.equals(targetSquare.getColor())) {
			polygon.setForeground(manageColor(IColorConstant.BLACK));
		} else {
			polygon.setForeground(manageColor(IColorConstant.WHITE));
		}
		polygon.setLineWidth(2);
	}
}
