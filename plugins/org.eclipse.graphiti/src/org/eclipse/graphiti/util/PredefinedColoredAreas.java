/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.datatypes.DatatypesFactory;
import org.eclipse.graphiti.mm.pictograms.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.pictograms.GradientColoredArea;
import org.eclipse.graphiti.mm.pictograms.GradientColoredAreas;
import org.eclipse.graphiti.mm.pictograms.GradientColoredLocation;
import org.eclipse.graphiti.mm.pictograms.LocationType;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;

/**
 * Predefined colored areas to be used in {@link GFFigureUtil#paintColorFlow} .
 * 
 */
public class PredefinedColoredAreas implements IPredefinedRenderingStyle {

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #BLUE_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteGlossDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "F8FBFE", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F8FBFE", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "EDF5FC", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "EDF5FC", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "DEEDFA", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "DEEDFA", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "D4E7F8", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FAFBFC", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "E2E5E9", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "E2E5E9", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #BLUE_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteGlossPrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "EEF6FD", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "EEF6FD", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "D0E6F9", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "D0E6F9", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "ACD2F4", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "ACD2F4", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "81B9EA", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "AAD0F2", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "9ABFE0", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "9ABFE0", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #BLUE_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteGlossSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "F5F9FE", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F5F9FE", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "E2EFFC", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "E2EFFC", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "CBE3F9", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "CBE3F9", 3, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "BBDAF7", 3, LocationType.LOCATION_TYPE_ABSOLUTE_START, "C5E0F7", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "B2CDE5", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "B2CDE5", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for elements where an action is allowed
	 * with the ID {@link #BLUE_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteGlossActionAllowedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_ACTION_ALLOWED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "99CC00", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "339966", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for elements where an action is forbidden
	 * with the ID {@link #BLUE_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteGlossActionForbiddenAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_ACTION_FORBIDDEN);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "FFCC00", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FF6600", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getBlueWhiteGlossAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(BLUE_WHITE_GLOSS_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getBlueWhiteGlossDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getBlueWhiteGlossPrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getBlueWhiteGlossSecondarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_ACTION_ALLOWED,
				getBlueWhiteGlossActionAllowedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_ACTION_FORBIDDEN,
				getBlueWhiteGlossActionForbiddenAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #BLUE_WHITE_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "D4E7F8", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FAFBFC", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #BLUE_WHITE_ID}.
	 */
	private static GradientColoredAreas getBlueWhitePrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "81B9EA", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "AAD0F2", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #BLUE_WHITE_ID}.
	 */
	private static GradientColoredAreas getBlueWhiteSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "BBDAF7", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "C5E0F7", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getBlueWhiteAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(BLUE_WHITE_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getBlueWhiteDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getBlueWhitePrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getBlueWhiteSecondarySelectedAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #LIGHT_YELLOW_ID}.
	 */
	private static GradientColoredAreas getLightYellowDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "FEFEF4", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FEFEF4", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #LIGHT_YELLOW_ID}.
	 */
	private static GradientColoredAreas getLightYellowPrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "E5E5C2", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "E5E5C2", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #LIGHT_YELLOW_ID}.
	 */
	private static GradientColoredAreas getLightYellowSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "FAFAD9", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FAFAD9", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getLightYellowAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(LIGHT_YELLOW_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getLightYellowDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getLightYellowPrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getLightYellowSecondarySelectedAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #LIGHT_GRAY_ID}.
	 */
	private static GradientColoredAreas getLightGrayDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "F5F5ED", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F5F5ED", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #LIGHT_GRAY_ID}.
	 */
	private static GradientColoredAreas getLightGrayPrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "D6D6D0", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "D6D6D0", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #LIGHT_GRAY_ID}.
	 */
	private static GradientColoredAreas getLightGraySecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "E5E5Df", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "E5E5Df", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getLightGrayAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(LIGHT_GRAY_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getLightGrayDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getLightGrayPrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getLightGraySecondarySelectedAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #COPPER_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getCopperWhiteGlossDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "F9F5F2", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F9F5F2", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "EDE0D8", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "EDE0D8", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "E4D0C4", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F5F0E8", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "E6DDD0", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "E6DDD0", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getCopperWhiteGlossAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(COPPER_WHITE_GLOSS_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getCopperWhiteGlossDefaultAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #SILVER_WHITE_GLOSS_ID}.
	 */
	private static GradientColoredAreas getSilverWhiteGlossDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = PictogramsFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		addGradientColoredArea(gcas, "EEEEED", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "EEEEED", 1, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "E1E1E0", 1, LocationType.LOCATION_TYPE_ABSOLUTE_START, "E1E1E0", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "D2D1CF", 2, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F2F0EF", 2, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "DFDEDD", 2, LocationType.LOCATION_TYPE_ABSOLUTE_END, "DFDEDD", 0, //$NON-NLS-1$ //$NON-NLS-2$
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * @return
	 */
	public static AdaptedGradientColoredAreas getSilverWhiteGlossAdaptions() {
		final AdaptedGradientColoredAreas agca = PictogramsFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(SILVER_WHITE_GLOSS_ID);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getSilverWhiteGlossDefaultAreas());
		return agca;
	}

	/**
	 * @param id
	 * @return
	 */
	public static AdaptedGradientColoredAreas getAdaptedGradientColoredAreas(String id) {
		if (BLUE_WHITE_GLOSS_ID.equals(id)) {
			return getBlueWhiteGlossAdaptions();
		} else if (BLUE_WHITE_ID.equals(id)) {
			return getBlueWhiteAdaptions();
		} else if (LIGHT_YELLOW_ID.equals(id)) {
			return getLightYellowAdaptions();
		} else if (LIGHT_GRAY_ID.equals(id)) {
			return getLightGrayAdaptions();
		} else if (COPPER_WHITE_GLOSS_ID.equals(id)) {
			return getCopperWhiteGlossAdaptions();
		} else if (SILVER_WHITE_GLOSS_ID.equals(id)) {
			return getSilverWhiteGlossAdaptions();
		} else {
			return null;
		}

	}

	protected static void addGradientColoredArea(EList<GradientColoredArea> gcas, String colorStart, int locationValueStart,
			LocationType locationTypeStart, String colorEnd, int locationValueEnd, LocationType locationTypeEnd) {
		final GradientColoredArea gca = PictogramsFactory.eINSTANCE.createGradientColoredArea();
		gcas.add(gca);
		gca.setStart(PictogramsFactory.eINSTANCE.createGradientColoredLocation());
		gca.getStart().setColor(DatatypesFactory.eINSTANCE.createColor());
		gca.getStart().getColor().setBlue(ColorUtil.getBlueFromHex(colorStart));
		gca.getStart().getColor().setGreen(ColorUtil.getGreenFromHex(colorStart));
		gca.getStart().getColor().setRed(ColorUtil.getRedFromHex(colorStart));
		gca.getStart().setLocationType(locationTypeStart);
		gca.getStart().setLocationValue(locationValueStart);
		gca.setEnd(PictogramsFactory.eINSTANCE.createGradientColoredLocation());
		gca.getEnd().setColor(DatatypesFactory.eINSTANCE.createColor());
		gca.getEnd().getColor().setBlue(ColorUtil.getBlueFromHex(colorEnd));
		gca.getEnd().getColor().setGreen(ColorUtil.getGreenFromHex(colorEnd));
		gca.getEnd().getColor().setRed(ColorUtil.getRedFromHex(colorEnd));
		gca.getEnd().setLocationType(locationTypeEnd);
		gca.getEnd().setLocationValue(locationValueEnd);
	}

	/**
	 * Returns the location for the given length and zoom-factor. The location
	 * is calculated from the location-value and location-type in relation to
	 * the given length and zoom-factor.
	 * <p>
	 * The idea is, that the location-type specifies how to interpret the
	 * location-value in relation to the length. For example if the
	 * location-type is "relative" and the location-value is "40", then this
	 * means, that the location is at 40% of the length. So for a length of 200
	 * the location would be 80.
	 * 
	 * @param length
	 *            The length, for which to calculate the location.
	 * @param zoom
	 *            The zoom-factor, which to use on the location-value.
	 * @return The location for the given length and zoom-factor.
	 */
	public static int getLocation(GradientColoredLocation gradientColoredLocation, int length, double zoom) {
		if (gradientColoredLocation.getLocationType() == LocationType.LOCATION_TYPE_ABSOLUTE_START) {
			return (int) Math.round(gradientColoredLocation.getLocationValue() * zoom);
		}
		if (gradientColoredLocation.getLocationType() == LocationType.LOCATION_TYPE_ABSOLUTE_END) {
			return length - (int) Math.round(gradientColoredLocation.getLocationValue() * zoom);
		}
		if (gradientColoredLocation.getLocationType() == LocationType.LOCATION_TYPE_RELATIVE) {
			final double ret = (double) gradientColoredLocation.getLocationValue() / 100 * length;
			return (int) Math.round(ret);
		}
		throw new IllegalStateException("Unknown location type '" + gradientColoredLocation.getLocationType() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
