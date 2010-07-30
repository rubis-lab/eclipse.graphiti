package org.eclipse.graphiti.examples.tutorial;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.pictograms.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.pictograms.GradientColoredArea;
import org.eclipse.graphiti.mm.pictograms.GradientColoredAreas;
import org.eclipse.graphiti.mm.pictograms.LocationType;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;
import org.eclipse.graphiti.util.PredefinedColoredAreas;

/**
 * Gradient definition for ID {@link #LIME_WHITE_ID} with adaptations
 * {@link #STYLE_ADAPTATION_DEFAULT} {@link #STYLE_ADAPTATION_PRIMARY_SELECTED},
 * {@link #STYLE_ADAPTATION_SECONDARY_SELECTED}.
 * 
 * @see StyleUtil
 */
public class TutorialColoredAreas extends PredefinedColoredAreas implements ITutorialRenderingStyle {

	private static GradientColoredAreas getLimeWhiteDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "CCFFCC", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFFCC", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "CCFF99", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFF99", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "CCFF66", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFF66", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66FF00", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFFCC", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "CCFFCC", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "CCFFCC", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		return gradientColoredAreas;
	}

	private static GradientColoredAreas getLimeWhitePrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "66CCCC", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CCCC", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66CC99", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CC99", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66CC66", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CC66", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "00CC00", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "00CC66", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "00CC99", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "00CC99", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	private static GradientColoredAreas getLimeWhiteSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "33CCCC", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CCCC", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC99", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC99", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC66", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC66", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC00", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC99", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "66CC99", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "66CC99", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	public static AdaptedGradientColoredAreas getLimeWhiteAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(LIME_WHITE_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getLimeWhiteDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getLimeWhitePrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getLimeWhiteSecondarySelectedAreas());
		return agca;
	}
}
