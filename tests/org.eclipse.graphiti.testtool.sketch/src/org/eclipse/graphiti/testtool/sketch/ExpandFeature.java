package org.eclipse.graphiti.testtool.sketch;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class ExpandFeature extends AbstractCustomFeature {

	public static final String HINT = "expand"; //$NON-NLS-1$

	public ExpandFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Expand"; //$NON-NLS-1$
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return context.getPictogramElements().length > 0;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		for (PictogramElement pe : pes) {
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			if (ga == null)
				continue;
			int width = ga.getWidth();
			int height = ga.getHeight();
			ga.setWidth((int) (width * 1.1));
			ga.setHeight((int) (height * 1.1));
		}
	}

}
