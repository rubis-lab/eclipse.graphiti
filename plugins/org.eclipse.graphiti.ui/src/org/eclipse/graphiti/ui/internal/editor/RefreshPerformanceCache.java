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
	 * Inits the refresh.
	 */
	public void initRefresh() {
		refreshedFigure4EP.clear();
		refreshedFigure4GA.clear();
		refreshedFigure4PE.clear();
	}

	/**
	 * Answers if a refresh should be triggered for an object of type
	 * <ul>
	 * <li> {@link GraphicsAlgorithm}
	 * <li> {@link PictogramElement}
	 * <li> {@link EditPart}
	 * </ul>
	 * If the supplied argument is of another type an
	 * {@link IllegalArgumentException} is thrown.
	 * 
	 * @param obj
	 * @return true if the supplied object should be refreshed, false otherwise.
	 */
	public boolean shouldRefresh(Object obj) {
		if (obj instanceof GraphicsAlgorithm) {
			return refreshedFigure4GA.add((GraphicsAlgorithm) obj);
		} else if (obj instanceof EditPart) {
			return refreshedFigure4EP.add((EditPart) obj);
		} else if (obj instanceof PictogramElement) {
			return refreshedFigure4PE.add((PictogramElement) obj);
		} else {
			throw new IllegalArgumentException("Can not refresh type: " + obj.getClass()); //$NON-NLS-1$
		}

	}

}
