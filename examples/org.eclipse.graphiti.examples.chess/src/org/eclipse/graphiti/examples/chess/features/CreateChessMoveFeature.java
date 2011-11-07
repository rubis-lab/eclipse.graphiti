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
package org.eclipse.graphiti.examples.chess.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.examples.chess.Messages;
import org.eclipse.graphiti.examples.chess.MoveUtil;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.IColorConstant;

public class CreateChessMoveFeature extends AbstractCreateConnectionFeature {

	public CreateChessMoveFeature(IFeatureProvider fp) {
		super(fp, Messages.CreateChessMoveFeature_name, Messages.CreateChessMoveFeature_description);
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// We can start a move connection at every anchor that belong to a
		// square holding a piece or that belongs to a move connection
		Piece piece = getPiece(context.getSourceAnchor());
		return piece != null;
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// Get the piece to move (potentially follow connection)
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return false;
		}

		// The start square for the current move
		Square sourceSquare = getSquare(context.getSourceAnchor());
		if (sourceSquare == null) {
			return false;
		}

		// The end square for the current move
		Square targetSquare = getSquare(context.getTargetAnchor());
		if (targetSquare == null) {
			return false;
		}

		// Check if the piece can move from start to end square
		return MoveUtil.isMoveAllowed(piece, sourceSquare, targetSquare);
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		// Take back the highlighting
		getDiagramEditor().getEditingDomain().getCommandStack().undo();

		Connection newConnection = null;
		Anchor sourceAnchor = context.getSourceAnchor();

		// Get Squares to be connected
		Square sourceSquare = getPiece(sourceAnchor).getSquare();
		Square targetSquare = getSquare(context.getTargetAnchor());

		if (sourceSquare != null && targetSquare != null) {
			AddConnectionContext addContext;
			AnchorContainer parent = sourceAnchor.getParent();
			if (parent instanceof ContainerShape) {
				// Add connection for normal container shape (either piece or
				// square)
				addContext = new AddConnectionContext(getSquareConnectionAnchor(sourceAnchor),
						getSquareConnectionAnchor(context.getTargetAnchor()));
			} else if (parent instanceof Connection) {
				// Add connection for move connection, use end anchor
				addContext = new AddConnectionContext(((Connection) parent).getEnd(),
						getSquareConnectionAnchor(context.getTargetAnchor()));
			} else {
				throw new IllegalStateException("Parent in neither a ContainerShape nor a Connection: " + parent); //$NON-NLS-1$
			}

			// Set the property identifying a move connection
			addContext.putProperty(MoveUtil.PROPERTY_MOVE, Boolean.TRUE);

			// Add the connection to the diagram
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}

		return newConnection;
	}

	@Override
	public void attachedToSource(ICreateConnectionContext context) {
		// Called as soon as a connection is started
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return;
		}

		Square sourceSquare = getSquare(context.getSourceAnchor());
		if (sourceSquare == null) {
			return;
		}

		// Highlight all allowed squares.
		// The highlighting here is done by modifying the Graphiti pictogram
		// model, which might be considered is a little tricky since the
		// highlighting should not be part of the visualization infomation
		// persisted in the diagram. A more cleaner approach would be to use
		// decorators for highlighting. In the end this is just a demonstration
		// of the priciple of these hooks.
		showFeedback(context);
	}

	@Override
	public void canceledAttaching(ICreateConnectionContext context) {
		// Take back the highlighting
		getDiagramEditor().getEditingDomain().getCommandStack().undo();
	}

	private void showFeedback(ICreateConnectionContext context) {
		// Find the piece that shall be moved (potentially follow move
		// connections)
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return;
		}

		// Find the square to start th move step from
		Square sourceSquare = getSquare(context.getSourceAnchor());
		if (sourceSquare == null) {
			return;
		}

		// Find all allowed squares to move to
		EList<Square> allSquares = sourceSquare.getBoard().getSquares();
		final List<Square> allowedSquares = new ArrayList<Square>();
		for (Square square : allSquares) {
			if (MoveUtil.isMoveAllowed(piece, sourceSquare, square)) {
				allowedSquares.add(square);
			}
		}

		// Mark or un-mark the allowed squares
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				for (Square square : allowedSquares) {
					List<PictogramElement> squarePE = GraphitiUi.getLinkService().getPictogramElements(getDiagram(),
							square);
					for (PictogramElement pictogramElement : squarePE) {
						if (pictogramElement instanceof ContainerShape) {
							GraphicsAlgorithm squareGA = pictogramElement.getGraphicsAlgorithm();
							if (squareGA instanceof Rectangle) {
								// Highlight the square in orange
								squareGA.setForeground(manageColor(IColorConstant.ORANGE));
								squareGA.setLineWidth(2);
							}
						}
					}
				}
			}
		});
	}

	private Piece getPiece(Anchor anchor) {
		// Try to find a piece for the given anchor
		if (anchor != null) {
			AnchorContainer parent = anchor.getParent();
			Object obj = getBusinessObjectForPictogramElement(parent);
			if (obj instanceof Piece) {
				// The shape of the anchor represents the piece
				return (Piece) obj;
			} else if (obj instanceof Square) {
				// The shape of the anchor represents a square
				Piece pieceOnSquare = ((Square) obj).getPiece();
				if (pieceOnSquare != null) {
					// Return the piece on the square
					return pieceOnSquare;
				}
				// No piece on the square, check for move connection
				EList<Connection> incomingConnections = anchor.getIncomingConnections();
				for (Connection connection : incomingConnections) {
					// Follow the first connection back to a piece
					Piece piece = getPiece(connection.getStart());
					if (piece != null) {
						return piece;
					}
				}
			} else if (parent instanceof Connection) {
				// Anchor of a connection, follow it backwards to its piece
				Anchor startAnchor = ((Connection) parent).getStart();
				return getPiece(startAnchor);
			}
		}
		return null;
	}

	private Square getSquare(Anchor anchor) {
		// Try to find a square for the given anchor
		if (anchor != null) {
			AnchorContainer parent = anchor.getParent();
			Object obj = getBusinessObjectForPictogramElement(parent);
			if (obj instanceof Square) {
				// The shape of the anchor represents a square
				return (Square) obj;
			} else if (obj instanceof Piece) {
				// The shape of the anchor represents a piece
				return ((Piece) obj).getSquare();
			} else if (parent instanceof Connection) {
				// The anchor belongs to a connection; get the square at its end
				Anchor startAnchor = ((Connection) parent).getEnd();
				return getSquare(startAnchor);
			}
		}
		return null;
	}

	private Anchor getSquareConnectionAnchor(Anchor anchor) {
		// Find the anchor to attach a move connection to a square
		if (anchor != null) {
			AnchorContainer parent = anchor.getParent();
			if (parent instanceof ContainerShape) {
				Object obj = getBusinessObjectForPictogramElement(parent);
				if (obj instanceof Square) {
					// Anchor belongs to a shape that represents a square
					EList<Anchor> anchors = parent.getAnchors();
					return findConnectionAnchor(anchors);
				} else if (obj instanceof Piece) {
					// Anchor belongs to a shape that represents a piece, use
					// the anchors of the parent shape
					EList<Anchor> anchors = ((ContainerShape) parent).getContainer().getAnchors();
					return findConnectionAnchor(anchors);
				}
			} else {
				throw new IllegalStateException("Parent shape is not a container shape"); //$NON-NLS-1$
			}
		}
		return null;
	}

	private Anchor findConnectionAnchor(EList<Anchor> anchors) {
		// Return the right anchor from the list of found anchors (the
		// BoxRelativeAnchor is used for connections while the ChopboxAnchor
		// only catches the connection attachment)
		for (Anchor connectionAnchor : anchors) {
			if (connectionAnchor instanceof BoxRelativeAnchor) {
				return connectionAnchor;
			}
		}
		throw new IllegalStateException("No BoxRelativeAnchor found"); //$NON-NLS-1$
	}
}
