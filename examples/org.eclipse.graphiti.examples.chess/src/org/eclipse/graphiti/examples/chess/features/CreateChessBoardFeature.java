package org.eclipse.graphiti.examples.chess.features;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class CreateChessBoardFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateChessBoardFeature(IFeatureProvider fp) {
		super(fp, "Create Board", "Create a new chess board");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		if (context.getTargetContainer() instanceof Diagram) {
			// Add new board only in case of an empty diagram
			return context.getTargetContainer().getChildren().size() == 0;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		addGraphicalRepresentation(context, null);
		return null;
	}
}
