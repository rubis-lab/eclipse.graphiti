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

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.examples.mm.chess.ChessFactory;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Files;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Ranks;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.examples.mm.chess.Types;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class CreateAllInitialChessPiecesFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateAllInitialChessPiecesFeature(IFeatureProvider fp) {
		super(fp, "Create Pieces", "Create the initial set of pieces");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Board board = getBoard(context);
		if (board != null) {
			// Allow the creation of the initial set of pieces in case a
			// board exists...
			if (board.getPieces().size() == 0) {
				// ... and the board has contains no pieces
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Collection<Piece> addedPieces = new ArrayList<Piece>(32);

		// Get the chess board
		Board board = getBoard(context);

		for (Colors color : Colors.VALUES) {
			for (Types type : Types.VALUES) {
				Collection<Square> relevantSquares = new ArrayList<Square>();

				// Get the rank
				Ranks rank;
				if (Types.ROOK.equals(type) || Types.KNIGHT.equals(type) || Types.BISHOP.equals(type)
						|| Types.QUEEN.equals(type) || Types.KING.equals(type)) {
					if (Colors.DARK.equals(color)) {
						rank = Ranks.EIGHT;
					} else {
						rank = Ranks.ONE;
					}
				} else {
					// Pawn
					if (Colors.DARK.equals(color)) {
						rank = Ranks.SEVEN;
					} else {
						rank = Ranks.TWO;
					}
				}

				// Get the file(s)
				if (Types.ROOK.equals(type)) {
					relevantSquares.add(board.getSquare(rank, Files.A));
					relevantSquares.add(board.getSquare(rank, Files.H));
				} else if (Types.KNIGHT.equals(type)) {
					relevantSquares.add(board.getSquare(rank, Files.B));
					relevantSquares.add(board.getSquare(rank, Files.G));
				} else if (Types.BISHOP.equals(type)) {
					relevantSquares.add(board.getSquare(rank, Files.C));
					relevantSquares.add(board.getSquare(rank, Files.F));
				} else if (Types.QUEEN.equals(type)) {
					relevantSquares.add(board.getSquare(rank, Files.D));
				} else if (Types.KING.equals(type)) {
					relevantSquares.add(board.getSquare(rank, Files.E));
				} else if (Types.PAWN.equals(type)) {
					for (Files file : Files.VALUES) {
						relevantSquares.add(board.getSquare(rank, file));
					}
				}

				// Add the piece to all relevant squares
				for (Square square : relevantSquares) {
					Piece piece = ChessFactory.eINSTANCE.createPiece();
					piece.setBoard(board);
					piece.setOwner(color);
					piece.setType(type);
					piece.setSquare(square);

					// Delegate to the add feature
					addGraphicalRepresentation(context, piece);
					addedPieces.add(piece);
				}
			}
		}

		return addedPieces.toArray(new Piece[addedPieces.size()]);
	}

	private Board getBoard(ICreateContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		if (targetContainer instanceof Diagram) {
			EList<Shape> children = targetContainer.getChildren();
			for (Shape shape : children) {
				Object bo = getBusinessObjectForPictogramElement(shape);
				if (bo instanceof Board) {
					return (Board) bo;
				}
			}
		}
		return null;
	}
}
