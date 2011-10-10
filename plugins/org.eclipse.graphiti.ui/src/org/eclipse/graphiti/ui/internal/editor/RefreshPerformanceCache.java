package org.eclipse.graphiti.ui.internal.editor;

import java.util.HashSet;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class RefreshPerformanceCache {

	/* keep refreshed EP/GA/PE to avoid multiple refresh of same figure */
	private HashSet<EditPart> refreshedFigure4EP = new HashSet<EditPart>();

	private HashSet<GraphicsAlgorithm> refreshedFigure4GA = new HashSet<GraphicsAlgorithm>();

	private HashSet<PictogramElement> refreshedFigure4PE = new HashSet<PictogramElement>();

	/**
	 * Gets the refreshed figure4 ep.
	 * 
	 * @return the refreshed figure4 ep
	 */
	public HashSet<EditPart> getRefreshedFigure4EP() {
		return refreshedFigure4EP;
	}

	/**
	 * Gets the refreshed figure4 ga.
	 * 
	 * @return the refreshed figure4 ga
	 */
	public HashSet<GraphicsAlgorithm> getRefreshedFigure4GA() {
		return refreshedFigure4GA;
	}

	/**
	 * Gets the refreshed figure4 pe.
	 * 
	 * @return the refreshed figure4 pe
	 */
	public HashSet<PictogramElement> getRefreshedFigure4PE() {
		return refreshedFigure4PE;
	}

	/**
	 * Inits the refresh.
	 */
	public void initRefresh() {
		refreshedFigure4EP.clear();
		refreshedFigure4GA.clear();
		refreshedFigure4PE.clear();
	}

	public boolean shouldRefresh(EditPart ep) {
		return getRefreshedFigure4EP().add(ep);
	}

	public boolean shouldRefresh(PictogramElement pe) {
		return getRefreshedFigure4PE().add(pe);
	}

	public boolean shouldRefresh(GraphicsAlgorithm ga) {
		return getRefreshedFigure4GA().add(ga);
	}

}
