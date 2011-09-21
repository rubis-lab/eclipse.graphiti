package org.eclipse.graphiti.examples.chess.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;

public class ChessDiagramTypeProvider extends AbstractDiagramTypeProvider implements IDiagramTypeProvider {

	public ChessDiagramTypeProvider() {
		super();
		setFeatureProvider(new ChessFeatureProvider(this));
	}
}
