/*******************************************************************************
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
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.chess;

import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.examples.mm.chess.Types;

public final class MoveUtil {

	public static final String PROPERTY_MOVE = "move"; //$NON-NLS-1$

	private MoveUtil() {
		super();
	}

	public static boolean isMoveAllowed(Piece piece, Square sourceSquare, Square targetSquare) {
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
		throw new IllegalStateException("Invalid Piece type: " + piece.getType()); //$NON-NLS-1$
	}

	private static boolean allowMovingToSides(Square sourceSquare, Square targetSquare, int fileGap, int rankGap) {
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

	private static boolean allowDiagonalMoving(Square sourceSquare, Square targetSquare, int fileGap, int rankGap) {
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
}
