package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class DoubleClickFeature extends AbstractCustomFeature {

	public static boolean executed = false;

	public DoubleClickFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return context.getInnerPictogramElement() instanceof Diagram;
	}

	@Override
	public boolean hasDoneChanges() {
		return false;
	}

	public void execute(ICustomContext context) {
		executed = true;
	}


}
