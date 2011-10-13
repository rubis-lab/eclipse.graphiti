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

import org.eclipse.graphiti.examples.chess.MoveUtil;
import org.eclipse.graphiti.examples.chess.diagram.ChessFeatureProvider;
import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Square;
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

		return MoveUtil.isMoveAllowed(piece, sourceSquare, targetSquare);
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
