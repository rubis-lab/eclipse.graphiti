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
import org.eclipse.graphiti.examples.chess.MoveUtil;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
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
		super(fp, "Create Move", "Creates a new move for a piece");
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Piece piece = getPiece(context.getSourceAnchor());
		return piece != null;
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return false;
		}

		Square sourceSquare = getSquare(context.getSourceAnchor());
		if (sourceSquare == null) {
			return false;
		}

		Square targetSquare = getSquare(context.getTargetAnchor());
		if (targetSquare == null) {
			return false;
		}

		return MoveUtil.isMoveAllowed(piece, sourceSquare, targetSquare);
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		// get Squares to be connected
		Square sourceSquare = getPiece(context.getSourceAnchor()).getSquare();
		Square targetSquare = getSquare(context.getTargetAnchor());

		if (sourceSquare != null && targetSquare != null) {
			// Add connection for business object
			AddConnectionContext addContext = new AddConnectionContext(
					getSquareConnectionAnchor(context.getSourceAnchor()),
					getSquareConnectionAnchor(context.getTargetAnchor()));
			addContext.putProperty(MoveUtil.PROPERTY_MOVE, Boolean.TRUE);
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}

		// Take back the highlighting
		showFeedback(context, false);

		return newConnection;
	}

	@Override
	public void attachedToSource(ICreateConnectionContext context) {
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return;
		}

		Square sourceSquare = getSquare(context.getSourceAnchor());
		if (sourceSquare == null) {
			return;
		}

		// Highlight all allowed squares
		showFeedback(context, true);
	}

	@Override
	public void canceledAttaching(ICreateConnectionContext context) {
		// Take back the highlighting
		showFeedback(context, false);
	}

	/**
	 * @param allowedSquares
	 */
	private void showFeedback(ICreateConnectionContext context, final boolean show) {
		Piece piece = getPiece(context.getSourceAnchor());
		if (piece == null) {
			return;
		}

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

		// Mark or unmark the allowed squares
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
								if (show) {
									// Highlight the square in orange
									squareGA.setForeground(manageColor(IColorConstant.ORANGE));
									squareGA.setLineWidth(2);
								} else {
									// Take back the highlighting
									Color background = squareGA.getBackground();
									squareGA.setForeground(manageColor(background.getRed(), background.getGreen(),
											background.getBlue()));
									squareGA.setLineWidth(1);
								}

							}
						}
					}
				}
			}
		});
	}

	private Piece getPiece(Anchor anchor) {
		if (anchor != null) {
			Object obj = getBusinessObjectForPictogramElement(anchor.getParent());
			if (obj instanceof Piece) {
				return (Piece) obj;
			}
		}
		return null;
	}

	private Square getSquare(Anchor anchor) {
		if (anchor != null) {
			Object obj = getBusinessObjectForPictogramElement(anchor.getParent());
			if (obj instanceof Square) {
				return (Square) obj;
			} else if (obj instanceof Piece) {
				return ((Piece) obj).getSquare();
			}
		}
		return null;
	}

	private Anchor getSquareConnectionAnchor(Anchor anchor) {
		if (anchor != null) {
			AnchorContainer parent = anchor.getParent();
			if (parent instanceof ContainerShape) {
				Object obj = getBusinessObjectForPictogramElement(parent);
				if (obj instanceof Square) {
					EList<Anchor> anchors = parent.getAnchors();
					return findConnectionAnchor(anchors);
				} else if (obj instanceof Piece) {
					EList<Anchor> anchors = ((ContainerShape) parent).getContainer().getAnchors();
					return findConnectionAnchor(anchors);
				}
			} else {
				throw new IllegalStateException("Parent shape is not a container shape");
			}
		}
		return null;
	}

	private Anchor findConnectionAnchor(EList<Anchor> anchors) {
		for (Anchor connectionAnchor : anchors) {
			if (connectionAnchor instanceof BoxRelativeAnchor) {
				return connectionAnchor;
			}
		}
		throw new IllegalStateException("No BoxRelativeAnchor found");
	}
}
