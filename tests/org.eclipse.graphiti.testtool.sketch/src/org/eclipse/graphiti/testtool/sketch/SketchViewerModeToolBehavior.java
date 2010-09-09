package org.eclipse.graphiti.testtool.sketch;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureChecker;
import org.eclipse.graphiti.features.IFeatureCheckerHolder;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;

public class SketchViewerModeToolBehavior extends SketchToolBehavior implements IFeatureCheckerHolder {

	private static final double[] ZOOM_LEVELS = new double[] { 0.01, 0.025, 0.05, 0.1, 0.25, 0.5, 1, 1.5, 2, 3, 4, 5 };
	private IFeatureChecker featureChecker;

	public SketchViewerModeToolBehavior(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	protected boolean showCreateConnectionFeatures() {
		return false;
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		return new IPaletteCompartmentEntry[0];
	}

	@Override
	public double[] getZoomLevels() {
		return ZOOM_LEVELS;
	}

	@Override
	public String getName() {
		return "Viewer Mode";
	}

	public IFeatureChecker getFeatureChecker() {
		if (featureChecker == null) {
			featureChecker = new SketchViewerModeChecker();

		}
		return featureChecker;
	}
}
