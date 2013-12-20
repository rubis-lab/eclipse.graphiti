package org.eclipse.graphiti.examples.tutorial.diagram;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.features.impl.DefaultResizeConnectionDecoratorFeature;

public class ResizeConnectionLabelFeature extends DefaultResizeConnectionDecoratorFeature {

	public ResizeConnectionLabelFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeConnectionDecorator(IResizeConnectionDecoratorContext context) {
		System.out.println("ResizeConnectionLabelFeature.canResizeShape() was called");
		return true;
	}

	@Override
	public void resizeConnectionDecorator(IResizeConnectionDecoratorContext context) {
		// Just call super.resizeShape().
		System.out.println("ResizeConnectionLabelFeature.resizeShape() was called");
		super.resizeConnectionDecorator(context);
	}

}

