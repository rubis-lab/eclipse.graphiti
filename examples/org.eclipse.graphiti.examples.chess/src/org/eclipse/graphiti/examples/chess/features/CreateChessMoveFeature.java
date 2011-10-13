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

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.examples.chess.MoveUtil;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

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

		return newConnection;
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
