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
package org.eclipse.graphiti.examples.chess.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.chess.features.AddChessBoardFeature;
import org.eclipse.graphiti.examples.chess.features.CreateChessBoardFeature;
import org.eclipse.graphiti.examples.mm.chess.Board;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class ChessFeatureProvider extends DefaultFeatureProvider implements IFeatureProvider {

	public ChessFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateChessBoardFeature(this) };
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context.getNewObject() instanceof Board) {
			return new AddChessBoardFeature(this);
		}
		return super.getAddFeature(context);
	}
}
