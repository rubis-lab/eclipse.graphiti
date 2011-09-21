package org.eclipse.graphiti.examples.chess.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.chess.features.AddChessBoardFeature;
import org.eclipse.graphiti.examples.chess.features.CreateChessBoardFeature;
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
		return new ICreateFeature[] {new CreateChessBoardFeature(this)};
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		return new AddChessBoardFeature(this);
	}
}
