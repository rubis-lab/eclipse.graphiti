package org.eclipse.graphiti.testtool.sketch;

import java.util.List;

import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class SketchUtil.
 */
public class SketchUtil {

	/**
	 * The Constant POOL_TAG.
	 */
	public static final String POOL_TAG = "POOL"; //$NON-NLS-1$

	/**
	 * The Constant LANE_TAG.
	 */
	public static final String LANE_TAG = "LANE"; //$NON-NLS-1$

	public static final String GHOST_TAG = "GHOST_TAG"; //$NON-NLS-1$
	public static final String CONTAINER_TAG = "CONTAINER_TAG"; //$NON-NLS-1$
	public static final String GA_TAG = "GA_TAG"; //$NON-NLS-1$

	// values for key GA_TAG
	public static final String GA_RECTANGLE = "GA_RECTANGLE"; //$NON-NLS-1$
	public static final String GA_ROUNDED_RECTANGLE = "GA_ROUNDED_RECTANGLE"; //$NON-NLS-1$
	public static final String GA_ELLIPSE = "GA_ELLIPSE"; //$NON-NLS-1$

	/**
	 * Gets the current label value.
	 * 
	 * @param containerPe
	 *            the container pe
	 * 
	 * @return the current label value
	 */
	public static String getCurrentLabelValue(PictogramElement containerPe) {

		String ret = ""; //$NON-NLS-1$

		AbstractText text = getLabelGa(containerPe);
		if (text != null) {
			ret = text.getValue();
		}

		return ret;
	}

	/**
	 * Sets the current label value.
	 * 
	 * @param containerPe
	 *            the container pe
	 * @param newValue
	 *            the new value
	 */
	public static void setCurrentLabelValue(PictogramElement containerPe, String newValue) {

		AbstractText text = getLabelGa(containerPe);
		if (text != null) {
			text.setValue(newValue);
		}
	}

	/**
	 * Gets the label ga.
	 * 
	 * @param containerPe
	 *            the container pe
	 * 
	 * @return the label ga
	 */
	public static AbstractText getLabelGa(PictogramElement containerPe) {

		AbstractText ret = null;
		List<GraphicsAlgorithm> children = containerPe.getGraphicsAlgorithm().getGraphicsAlgorithmChildren();
		if (children.size() > 0) {
			GraphicsAlgorithm ga = children.get(0);
			if (ga instanceof AbstractText) {
				ret = (AbstractText) ga;
			}
		}
		return ret;
	}

	/**
	 * Checks if is pool pe.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return true, if is pool pe
	 */
	public static boolean isPoolPe(PictogramElement pe) {
		return Graphiti.getPeService().getProperty(pe, POOL_TAG) != null;
	}

	/**
	 * Checks if is lane pe.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return true, if is lane pe
	 */
	public static boolean isLanePe(PictogramElement pe) {
		return Graphiti.getPeService().getProperty(pe, LANE_TAG) != null;
	}

	/**
	 * Checks if is lonely lane pe.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return true, if is lonely lane pe
	 */
	public static boolean isLonelyLanePe(PictogramElement pe) {
		boolean isLane = isLanePe(pe);
		if (isLane && pe instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pe;
			ContainerShape container = cs.getContainer();
			if (isPoolPe(container)) {
				List<Shape> children = container.getChildren();
				int laneCounter = 0;
				for (Object child : children) {
					if (child instanceof PictogramElement) {
						PictogramElement childPe = (PictogramElement) child;
						if (isLanePe(childPe)) {
							laneCounter++;
						}
					}
				}
				return laneCounter == 1;
			}
		}
		return false;
	}

	/**
	 * Gets the ghost ga.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * 
	 * @return the ghost ga
	 */
	public static GraphicsAlgorithm getGhostGa(PictogramElement pictogramElement) {
		final GraphicsAlgorithm potGhostGa = pictogramElement.getGraphicsAlgorithm();
		final List<GraphicsAlgorithm> gaChildren = potGhostGa.getGraphicsAlgorithmChildren();
		if (gaChildren.size() == 2) {
			if (gaChildren.get(1) instanceof AbstractText) {
				return potGhostGa;
			}
		}
		return null;
	}

	public static boolean isGhost(PictogramElement pe) {
		return Graphiti.getPeService().getProperty(pe, GHOST_TAG) != null;
	}

	public static boolean isContainer(PictogramElement pe) {
		return Graphiti.getPeService().getProperty(pe, CONTAINER_TAG) != null;
	}

	public static boolean isRectangle(PictogramElement pe) {
		return GA_RECTANGLE.equals(Graphiti.getPeService().getPropertyValue(pe, GA_TAG));
	}

	public static boolean isRoundedRectangle(PictogramElement pe) {
		return GA_ROUNDED_RECTANGLE.equals(Graphiti.getPeService().getPropertyValue(pe, GA_TAG));
	}
}
