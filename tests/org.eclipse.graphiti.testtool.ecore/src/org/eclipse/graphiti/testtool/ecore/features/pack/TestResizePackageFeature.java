package org.eclipse.graphiti.testtool.ecore.features.pack;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;

/**
 * The Class TestResizePackageFeature.
 */
public class TestResizePackageFeature extends DefaultResizeShapeFeature {

	/**
	 * Instantiates a new test resize package feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestResizePackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	// @Override
	// public boolean canResizeShape(IResizeShapeContext context) {
	// GraphicsAlgorithm ga =
	// context.getPictogramElement().getGraphicsAlgorithm();
	// return (ga instanceof Polygon);
	// }
	//
	// @Override
	// public void resizeShape(IResizeShapeContext context) {
	// PictogramElement pe = context.getPictogramElement();
	// GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
	// Polygon polygon = (Polygon) ga;
	// Point point0 = (Point) polygon.getPoints().get(0);
	// Point point1 = (Point) polygon.getPoints().get(1);
	// Point point2 = (Point) polygon.getPoints().get(2);
	// Point point3 = (Point) polygon.getPoints().get(3);
	//
	// int x = point0.getX();
	// int y = point0.getY();
	// int width = context.getWidth();
	// int height = context.getHeight();
	//
	// point1.setX(x + width);
	// point2.setX(x + width);
	// point2.setY(y + height);
	// point3.setY(y + height);
	//
	// layoutPictogramElement(pe);
	// }
}
