/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 358255 - Add Border/Background decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.chess.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.chess.features.CreateChessMoveFeature;
import org.eclipse.graphiti.examples.mm.chess.Square;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.BorderDecorator;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.util.IColorConstant;

public class ChessToolBehaviorProvider extends DefaultToolBehaviorProvider {

	private List<Square> allowedSquaresForMove = new ArrayList<Square>();

	public ChessToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public boolean isShowGuides() {
		return false;
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		Object object = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		// Check if the business object of the given pictogram element (the
		// square) is one of the allowed squares (see CreateChessMoveFeature)
		// for a move in progress
		if (object instanceof Square) {
			if (allowedSquaresForMove.contains(object)) {
				// Yes --> decorate with an orange border
				BorderDecorator decorator = new BorderDecorator();
				decorator.setBorderColor(IColorConstant.ORANGE);
				decorator.setBorderWidth(2);
				return new IDecorator[] { decorator };
			}
		}
		return super.getDecorators(pe);
	}

	/**
	 * Adds the given {@link List} of {@link Square}s to the list of allowed
	 * squares for a move, see {@link CreateChessMoveFeature}.
	 * 
	 * @param squares
	 *            the squares to add
	 */
	public void addToAllowedSquaresForMove(List<Square> squares) {
		allowedSquaresForMove.addAll(squares);
	}

	/**
	 * Clears the {@link List} of allowed {@link Square}s for a move, see
	 * {@link CreateChessMoveFeature}.
	 */
	public void clearAllowedSquaresForMove() {
		allowedSquaresForMove.clear();
	}
}
