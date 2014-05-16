/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 355347 - Remove setters of Graphiti's Font Interface
 *    mwenz - Bug 423573 - Angles should never be integer
 *    mwenz - Bug 423913 - Style#unsetFilled sets filled to true instead of null
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tests.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.impl.GaServiceImpl;
import org.eclipse.graphiti.tests.GFAbstractCreateTestCase;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.PredefinedColoredAreas;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GaServiceTest extends GFAbstractCreateTestCase {

	// Fixture
	private Diagram d;
	private Shape s1;
	private static final String FONTNAME = "Baskerville Old Face";

	public static IGaService gas = Graphiti.getGaService();

	@BeforeClass
	public static void prepareClass() {
	}

	@Before
	public void initializeTest() {
		d = Graphiti.getPeCreateService().createDiagram("tutorial", "test", true);
		assertNotNull(d);
		s1 = Graphiti.getPeCreateService().createShape(d, true);
		s1.setGraphicsAlgorithm(Graphiti.getGaCreateService().createRectangle(d));
		assertNotNull(s1);
		ResourceImpl resource = new ResourceImpl();
		resource.getContents().add(d);
	}

	@Test
	public void createDefaultMultiText() {
		MultiText multiText = gas.createDefaultMultiText(d, s1);
		assertNotNull(multiText);
		assertEquals(DEFAULT_FONT, multiText.getFont().getName());
		checkTextDefaults(multiText, "");
	}

	@Test
	public void createDefaultMultiText2() {
		MultiText multiText = gas.createDefaultMultiText(d, s1, VALUE);
		assertNotNull(multiText);
		assertEquals(DEFAULT_FONT, multiText.getFont().getName());
		checkTextDefaults(multiText, VALUE);
	}

	@Test
	public void createMultiText() {
		MultiText multiText = gas.createMultiText(s1);
		assertNotNull(multiText);
		assertNull(multiText.getFont());
		checkTextDefaults(multiText, "");
	}
	
	@Test
	public void createPlainMultiText(){
		MultiText multiText = gas.createPlainMultiText(s1);
		assertNotNull(multiText);
		assertNull(multiText.getFont());
		checkPlainTextDefaults(multiText, "");
	}

	@Test
	public void createMultiText2() {
		MultiText multiText = gas.createMultiText(s1, VALUE);
		assertNotNull(multiText);
		assertNull(multiText.getFont());
		checkTextDefaults(multiText, VALUE);
	}
	
	@Test
	public void createPlainMultiText2() {
		MultiText multiText = gas.createPlainMultiText(s1, VALUE);
		assertNotNull(multiText);
		assertNull(multiText.getFont());
		checkPlainTextDefaults(multiText, VALUE);
	}


	@Test
	public void createDefaultText() {
		Text text = gas.createDefaultText(d, s1);
		assertNotNull(text);
		assertEquals(DEFAULT_FONT, text.getFont().getName());
		checkTextDefaults(text, "");
	}

	@Test
	public void createDefaultText2() {
		Text text = gas.createDefaultText(d, s1, VALUE);
		assertNotNull(text);
		assertEquals(DEFAULT_FONT, text.getFont().getName());
		checkTextDefaults(text, VALUE);
	}

	@Test
	public void createEllipse() {
		Ellipse ellipse = gas.createEllipse(s1);
		assertNotNull(ellipse);
		checkGraphicsAlgorithmDefaults(ellipse);
	}
	
	@Test
	public void createPlainEllipse() {
		Ellipse ellipse = gas.createPlainEllipse(s1);
		assertNotNull(ellipse);
		checkPlainGraphicsAlgorithmDefaults(ellipse);
	}

	@Test
	public void manageFont() {
		Font font = gas.manageFont(d, FONTNAME, 10);
		assertNotNull(font);
		assertEquals(10, font.getSize());
		assertEquals(FONTNAME, font.getName());
	}

	@Test
	public void manageFont2() {
		Font font = gas.manageFont(d, FONTNAME, 10, true, true);
		assertNotNull(font);
		assertEquals(10, font.getSize());
		assertEquals(FONTNAME, font.getName());
		assertTrue(font.isItalic());
		assertTrue(font.isBold());
		font = gas.manageFont(d, FONTNAME, 10, true, false);
		assertTrue(font.isItalic());
		assertFalse(font.isBold());
		font = gas.manageFont(d, FONTNAME, 10, false, true);
		assertFalse(font.isItalic());
		assertTrue(font.isBold());
		font = gas.manageFont(d, FONTNAME, 10, false, false);
		assertFalse(font.isItalic());
		assertFalse(font.isBold());
	}

	@Test
	public void manageFont3() {
		Text text = gas.createDefaultText(d, s1);
		Font font = gas.manageFont(d, FONTNAME, 10);
		assertNotNull(font);
		assertEquals(10, font.getSize());
		assertEquals(FONTNAME, font.getName());
		text.setFont(font);
		assertNotNull(text.getFont());
		// Check if aggregation on diagram level works-> no additional font
		// object should be created
		Font font2 = gas.manageFont(d, FONTNAME, 10);
		assertEquals(font, font2);
	}

	@Test
	public void manageDefaultFont() {
		Font font = gas.manageDefaultFont(d);
		assertNotNull(font);
		assertEquals(IGaService.DEFAULT_FONT, font.getName());
		assertEquals(IGaService.DEFAULT_FONT_SIZE, font.getSize());
		assertFalse(font.isItalic());
		assertFalse(font.isBold());
	}

	@Test
	public void manageDefaultFont2() {
		Font font = gas.manageDefaultFont(d, true, true);
		assertNotNull(font);
		assertEquals(IGaService.DEFAULT_FONT_SIZE, font.getSize());
		assertEquals(IGaService.DEFAULT_FONT, font.getName());
		assertTrue(font.isItalic());
		assertTrue(font.isBold());
		font = gas.manageDefaultFont(d, true, false);
		assertTrue(font.isItalic());
		assertFalse(font.isBold());
		font = gas.manageDefaultFont(d, false, true);
		assertFalse(font.isItalic());
		assertTrue(font.isBold());
		font = gas.manageDefaultFont(d, false, false);
		assertFalse(font.isItalic());
		assertFalse(font.isBold());
	}

	@Test
	public void manageDefaultFont3() {
		Text text = gas.createDefaultText(d, s1);
		Font font = gas.manageDefaultFont(d);
		assertNotNull(font);
		assertEquals(IGaService.DEFAULT_FONT_SIZE, font.getSize());
		assertEquals(IGaService.DEFAULT_FONT, font.getName());
		assertEquals(font, text.getFont());
		// Check if aggregation on diagram level works-> no additional font
		// object should be created
		Font font2 = gas.manageDefaultFont(d);
		assertEquals(font, font2);
	}

	@Test
	public void createStyle() {
		Style style = gas.createStyle(d, VALUE);
		assertEquals(VALUE, style.getId());
		assertEquals(1, d.getStyles().size());
		checkInheritedStyleDefaultsFromMetamodel(style);
	}
	
	@Test
	public void createPlainStyle() {
		Style style = gas.createPlainStyle(d, VALUE);
		assertEquals(VALUE, style.getId());
		assertEquals(1, d.getStyles().size());
		checkInheritedPlainStyleDefaultsFromMetamodel(style);
	}

	@Test
	public void createImage() {
		Image im = gas.createImage(d, VALUE);
		checkGraphicsAlgorithmDefaults(im);
		checkImageDefaults(im);
	}
	
	@Test
	public void createPlainImage() {
		Image im = gas.createPlainImage(d, VALUE);
		checkPlainGraphicsAlgorithmDefaults(im);
		checkPlainImageDefaults(im);
	}

	@Test
	public void createInvisibleRectangle() {
		Rectangle invisibleRect = gas.createInvisibleRectangle(s1);
		assertFalse(invisibleRect.getLineVisible());
	}

	@Test
	public void createPlatformGa() {
		PlatformGraphicsAlgorithm pGa = gas.createPlatformGraphicsAlgorithm(s1, VALUE);
		checkGraphicsAlgorithmDefaults(pGa);
		assertEquals(s1, pGa.eContainer());
		assertEquals(VALUE, pGa.getId());
	}
	
	@Test
	public void createPlainPlatformGa() {
		PlatformGraphicsAlgorithm pGa = gas.createPlainPlatformGraphicsAlgorithm(s1, VALUE);
		checkPlainGraphicsAlgorithmDefaults(pGa);
		assertEquals(s1, pGa.eContainer());
		assertEquals(VALUE, pGa.getId());
	}

	@Test
	public void createPoint() {
		Point point = gas.createPoint(0, 1);
		assertEquals(0, point.getX());
		assertEquals(1, point.getY());
		point = gas.createPoint(1, 0, 2, 3);
		assertEquals(1, point.getX());
		assertEquals(0, point.getY());
		assertEquals(2, point.getBefore());
		assertEquals(3, point.getAfter());
	}

	@Test
	public void createPointList() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Point p1 = points.get(0);
		assertEquals(1, p1.getX());
		assertEquals(2, p1.getY());
		Point p2 = points.get(1);
		assertEquals(3, p2.getX());
		assertEquals(4, p2.getY());
	}

	@Test
	public void createPointList2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 }, new int[] { 5, 6, 7, 8 });
		Point p1 = points.get(0);
		assertEquals(1, p1.getX());
		assertEquals(2, p1.getY());
		assertEquals(5, p1.getBefore());
		assertEquals(6, p1.getAfter());
		Point p2 = points.get(1);
		assertEquals(3, p2.getX());
		assertEquals(4, p2.getY());
		assertEquals(7, p2.getBefore());
		assertEquals(8, p2.getAfter());
	}

	@Test
	public void createPolygon() {
		Polygon polygon = gas.createPolygon(s1);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polygon);
	}
	
	@Test
	public void createPlainPolygon() {
		Polygon polygon = gas.createPlainPolygon(s1);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polygon);
	}

	@Test
	public void createPolygon2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polygon polygon = gas.createPolygon(s1, points);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polygon);
		EList<Point> points2 = polygon.getPoints();
		assertEquals(points, points2);
	}
	
	@Test
	public void createPlainPolygon2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polygon polygon = gas.createPlainPolygon(s1, points);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polygon);
		EList<Point> points2 = polygon.getPoints();
		assertEquals(points, points2);
	}

	@Test
	public void createPolygon3() {
		int[] points = new int[] { 1, 2, 3, 4 };
		Polygon polygon = gas.createPolygon(s1, points);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polygon);
		EList<Point> points2 = polygon.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		int[] beforeAfter = new int[] { 5, 6, 7, 8 };
		polygon = gas.createPolygon(s1, points, beforeAfter);
		checkGraphicsAlgorithmDefaults(polygon);
		points2 = polygon.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		assertEquals(5, points2.get(0).getBefore());
		assertEquals(6, points2.get(0).getAfter());
		assertEquals(7, points2.get(1).getBefore());
		assertEquals(8, points2.get(1).getAfter());
	}
	
	@Test
	public void createPlainPolygon3() {
		int[] points = new int[] { 1, 2, 3, 4 };
		Polygon polygon = gas.createPlainPolygon(s1, points);
		assertEquals(polygon, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polygon);
		EList<Point> points2 = polygon.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		int[] beforeAfter = new int[] { 5, 6, 7, 8 };
		polygon = gas.createPlainPolygon(s1, points, beforeAfter);
		checkPlainGraphicsAlgorithmDefaults(polygon);
		points2 = polygon.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		assertEquals(5, points2.get(0).getBefore());
		assertEquals(6, points2.get(0).getAfter());
		assertEquals(7, points2.get(1).getBefore());
		assertEquals(8, points2.get(1).getAfter());
	}

	@Test
	public void createPolyline() {
		Polyline polyline = gas.createPolyline(s1);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polyline);
	}
	
	@Test
	public void createPlainPolyline() {
		Polyline polyline = gas.createPlainPolyline(s1);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polyline);
	}

	@Test
	public void createPolyline2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polyline polyline = gas.createPolyline(s1, points);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polyline);
		EList<Point> points2 = polyline.getPoints();
		assertEquals(points, points2);
	}
	
	@Test
	public void createPlainPolyline2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polyline polyline = gas.createPlainPolyline(s1, points);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polyline);
		EList<Point> points2 = polyline.getPoints();
		assertEquals(points, points2);
	}


	@Test
	public void createPolyline3() {
		int[] points = new int[] { 1, 2, 3, 4 };
		Polyline polyline = gas.createPolyline(s1, points);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(polyline);
		EList<Point> points2 = polyline.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		int[] beforeAfter = new int[] { 5, 6, 7, 8 };
		polyline = gas.createPolyline(s1, points, beforeAfter);
		checkGraphicsAlgorithmDefaults(polyline);
		points2 = polyline.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		assertEquals(5, points2.get(0).getBefore());
		assertEquals(6, points2.get(0).getAfter());
		assertEquals(7, points2.get(1).getBefore());
		assertEquals(8, points2.get(1).getAfter());
	}
	
	@Test
	public void createPlainPolyline3() {
		int[] points = new int[] { 1, 2, 3, 4 };
		Polyline polyline = gas.createPlainPolyline(s1, points);
		assertEquals(polyline, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(polyline);
		EList<Point> points2 = polyline.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		int[] beforeAfter = new int[] { 5, 6, 7, 8 };
		polyline = gas.createPlainPolyline(s1, points, beforeAfter);
		checkPlainGraphicsAlgorithmDefaults(polyline);
		points2 = polyline.getPoints();
		assertEquals(1, points2.get(0).getX());
		assertEquals(2, points2.get(0).getY());
		assertEquals(3, points2.get(1).getX());
		assertEquals(4, points2.get(1).getY());
		assertEquals(5, points2.get(0).getBefore());
		assertEquals(6, points2.get(0).getAfter());
		assertEquals(7, points2.get(1).getBefore());
		assertEquals(8, points2.get(1).getAfter());
	}

	@Test
	public void createRectangle() {
		Rectangle rect = gas.createRectangle(s1);
		assertEquals(rect, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(rect);
	}
	
	@Test
	public void createPlainRectangle() {
		Rectangle rect = gas.createPlainRectangle(s1);
		assertEquals(rect, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(rect);
	}

	@Test
	public void createRoundedRectangle() {
		RoundedRectangle rect = gas.createRoundedRectangle(s1, 5, 6);
		assertEquals(rect, s1.getGraphicsAlgorithm());
		checkGraphicsAlgorithmDefaults(rect);
		assertEquals(5, rect.getCornerWidth());
		assertEquals(6, rect.getCornerHeight());
	}
	
	@Test
	public void createPlainRoundedRectangle() {
		RoundedRectangle rect = gas.createPlainRoundedRectangle(s1, 5, 6);
		assertEquals(rect, s1.getGraphicsAlgorithm());
		checkPlainGraphicsAlgorithmDefaults(rect);
		assertEquals(5, rect.getCornerWidth());
		assertEquals(6, rect.getCornerHeight());
	}

	@Test
	public void shiftedColor() {
		Color black = gas.manageColor(d, IColorConstant.BLACK);
		IColorConstant colorConstant = gas.createShiftedColor(IColorConstant.BLACK, 20);
		Color shifted = gas.manageColor(d, colorConstant);
		assertEquals(20, colorConstant.getRed());
		assertEquals(20, colorConstant.getGreen());
		assertEquals(20, colorConstant.getBlue());
		Color shouldBeBlack = gas.createShiftedColor(shifted, -20, d);
		assertEquals(black, shouldBeBlack);
	}

	@Test
	public void calculateSizeOfGraphicsAlgorithm() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polyline polyline = gas.createPolyline(s1, points);
		IDimension size = gas.calculateSize(polyline);
		assertEquals(3, size.getWidth());
		assertEquals(3, size.getHeight());
		Polygon polygon = gas.createPolygon(s1, points);
		size = gas.calculateSize(polygon);
		assertEquals(3, size.getWidth());
		assertEquals(3, size.getHeight());
	}

	@Test
	public void calculateSizeOfGraphicsAlgorithm2() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		List<Point> points2 = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polyline polyline = gas.createPolyline(s1, points);
		IDimension size = gas.calculateSize(polyline, false);
		assertEquals(3, size.getWidth());
		assertEquals(3, size.getHeight());
		Polygon polygon = gas.createPolygon(s1, points2);
		size = gas.calculateSize(polygon, false);
		assertEquals(3, size.getWidth());
		assertEquals(3, size.getHeight());

		polyline.setLineWidth(2);
		size = gas.calculateSize(polyline, true);
		assertEquals(4, size.getWidth());
		assertEquals(4, size.getHeight());
		polygon.setLineWidth(3);
		size = gas.calculateSize(polygon, true);
		assertEquals(5, size.getWidth());
		assertEquals(5, size.getHeight());
	}

	@Test
	public void deleteFont() {
		Text text = gas.createDefaultText(d, s1);
		Font font = gas.manageFont(d, FONTNAME, 10);
		text.setFont(font);
		assertEquals(font, text.getFont());
		gas.deleteFont(font);
		assertNull(text.getFont());
	}

	@Test
	public void setAndUnsetRenderingStyle() {
		Style style = gas.createStyle(d, VALUE);
		assertEquals(VALUE, style.getId());
		assertEquals(1, d.getStyles().size());
		AdaptedGradientColoredAreas gradient = PredefinedColoredAreas.getBlueWhiteGlossAdaptions();
		gas.setRenderingStyle(style, gradient);
		assertEquals(gradient, style.getRenderingStyle().getAdaptedGradientColoredAreas());
		gas.deleteRenderingStyle(style);
		assertNull(style.getRenderingStyle());
	}

	@Test
	public void findStyle() {
		Style style = gas.createStyle(d, VALUE);
		assertEquals(VALUE, style.getId());
		assertEquals(1, d.getStyles().size());
		Style styleFound = gas.findStyle(d, VALUE);
		assertEquals(style, styleFound);
	}

	@Test
	public void getter() {
		Text text = gas.createDefaultText(d, s1);
		assertNotNull(text);
		assertNotNull(gas.getAngle(text, false));
		assertNotNull(gas.getRotation(text, false));
		assertNotNull(gas.getLineWidth(text, false));
		assertNotNull(gas.getLineStyle(text, false));
		assertNotNull(gas.getFont(text, false));
		assertNotNull(gas.getHorizontalAlignment(text, false));
		assertNotNull(gas.getVerticalAlignment(text, false));
		assertNull(gas.getRenderingStyle(text, false));
		assertNotNull(gas.getTransparency(text, false));
		assertNull(gas.getForegroundColor(text, false));
		assertNull(gas.getBackgroundColor(text, false));
		assertFalse(gas.isFilled(text, false));
		assertTrue(gas.isLineVisible(text, false));

	}

	@Test
	public void getterWithCheckStyles() {
		Text text = gas.createDefaultText(d, s1);
		Style style = gas.createStyle(d, VALUE);
		assertEquals(VALUE, style.getId());
		assertEquals(1, d.getStyles().size());
		AdaptedGradientColoredAreas gradient = PredefinedColoredAreas.getBlueWhiteGlossAdaptions();
		gas.setRenderingStyle(style, gradient);
		style.setAngle(2);
		style.setLineWidth(3);
		style.setTransparency(4.0);
		style.setLineStyle(LineStyle.DASH);
		style.setBackground(gas.manageColor(d, IColorConstant.BLACK));
		style.setForeground(gas.manageColor(d, IColorConstant.BLUE));
		Font font = gas.manageFont(d, FONTNAME, 10);

		style.setFont(font);
		style.setHorizontalAlignment(Orientation.ALIGNMENT_BOTTOM);
		style.setVerticalAlignment(Orientation.ALIGNMENT_TOP);
		style.setProportional(true);
		style.setFilled(false);
		style.setLineVisible(false);
		style.setAngle(2);
		style.setLineWidth(3);
		text.setStyle(style);

		// Reset information on text, such that the style is queried.
		text.setAngle(null);
		text.setLineWidth(null);
		text.setLineStyle(LineStyle.UNSPECIFIED);
		text.unsetLineVisible();
		text.setFont(null);
		text.setHorizontalAlignment(Orientation.UNSPECIFIED);
		text.setVerticalAlignment(Orientation.UNSPECIFIED);
		text.setTransparency(null);
		text.unsetFilled();

		assertNotNull(text);
		assertEquals(2, gas.getAngle(text, true));
		assertEquals(2, gas.getRotation(text, true), 0);
		assertEquals(3, gas.getLineWidth(text, true));
		assertEquals(LineStyle.DASH, gas.getLineStyle(text, true));
		assertEquals(font, gas.getFont(text, true));
		assertEquals(Orientation.ALIGNMENT_BOTTOM, gas.getHorizontalAlignment(text, true));
		assertEquals(Orientation.ALIGNMENT_TOP, gas.getVerticalAlignment(text, true));
		assertEquals(gradient, gas.getRenderingStyle(text, true).getAdaptedGradientColoredAreas());
		assertEquals(4.0, gas.getTransparency(text, true), 0);
		assertEquals(gas.manageColor(d, IColorConstant.BLUE), gas.getForegroundColor(text, true));
		assertEquals(gas.manageColor(d, IColorConstant.BLACK), gas.getBackgroundColor(text, true));
		assertFalse(gas.isFilled(text, true));
		assertFalse(gas.isLineVisible(text, true));

	}

	@Test
	public void movePolylinePoint() {
		List<Point> points = gas.createPointList(new int[] { 1, 2, 3, 4 });
		Polyline polyline = gas.createPolyline(s1, points);
		gas.movePolylinePoint(polyline, 1, 1, 2);
		assertEquals(4, polyline.getPoints().get(1).getX());
		assertEquals(6, polyline.getPoints().get(1).getY());
	}

	@Test
	public void setLocationAndSize() {
		Rectangle rect = gas.createRectangle(s1);
		gas.setLocationAndSize(rect, -1, 1, 20, 30);
		assertEquals(-1, rect.getX());
		assertEquals(1, rect.getY());
		assertEquals(20, rect.getWidth());
		assertEquals(30, rect.getHeight());
		gas.setLocation(rect, -3, -2, true);
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		rect.setX(-1);
		gas.setHeight(rect, 10);
		assertEquals(10, rect.getHeight());
		gas.setWidth(rect, 20);
		assertEquals(20, rect.getWidth());
		gas.setLocation(rect, 2, -2);
		assertEquals(2, rect.getX());
		assertEquals(-2, rect.getY());
		gas.setLocation(rect, 2, -2, true);
		assertEquals(2, rect.getX());
		assertEquals(0, rect.getY());
		gas.setSize(rect, 50, 60);
		assertEquals(50, rect.getWidth());
		assertEquals(60, rect.getHeight());
		gas.setLocationAndSize(rect, -1, 1, 20, 30, true);
		assertEquals(0, rect.getX());
		assertEquals(1, rect.getY());
		assertEquals(20, rect.getWidth());
		assertEquals(30, rect.getHeight());
	}

	@Test
	public void resetAll() {
		AbstractStyle style = gas.createStyle(d, VALUE);
		assertEquals(1, d.getStyles().size());
		gas.setRenderingStyle(style, PredefinedColoredAreas.getBlueWhiteGlossAdaptions());
		style.setLineWidth(3);
		style.setTransparency(4.0);
		style.setLineStyle(LineStyle.DASH);
		style.setBackground(gas.manageColor(d, IColorConstant.BLACK));
		style.setForeground(gas.manageColor(d, IColorConstant.BLUE));

		gas.resetAll(style);

		assertNull(style.getBackground());
		assertNull(style.getForeground());
		assertEquals(LineStyle.UNSPECIFIED, style.getLineStyle());
		assertFalse((style.isSetLineVisible())); // is it in state unsettable
		assertNull(style.getLineWidth());
		assertNull(style.getRenderingStyle());
		assertFalse(style.isSetFilled());
		assertNull(style.getTransparency()); // is it in state unsettable
	}

	@Test
	public void testIsFilledForBug423913() {
		GaServiceImpl gaService = new GaServiceImpl();
		Rectangle rectangle = gaService.createPlainRectangle(null);
		Style style1 = gaService.createPlainStyle(null, "style1");
		style1.setFilled(false);
		Style style2 = gaService.createPlainStyle(style1, "style2");
		rectangle.setStyle(style2);

		boolean filled = gaService.isFilled(rectangle, true);

		assertFalse(filled);
	}
}
