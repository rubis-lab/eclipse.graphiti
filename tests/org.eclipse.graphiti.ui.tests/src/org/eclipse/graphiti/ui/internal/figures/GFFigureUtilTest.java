/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2014, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation (Bug 416039 - TextStyle rendering does not fall back to abstract text font)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import static org.junit.Assert.assertNotNull;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextLayout;
import org.junit.Test;

public class GFFigureUtilTest {

	/*
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=416039
	 */
	@SuppressWarnings("restriction")
	@Test
	public void testDrawRichTextFallsBackToAbstractTextFont() {
		Graphics graphicsMock = new GraphicsMock();
		Font font = StylesFactory.eINSTANCE.createFont();
		font.eSet(StylesPackage.Literals.FONT__NAME, "name");
		font.eSet(StylesPackage.Literals.FONT__SIZE, 10);

		Text text = AlgorithmsFactory.eINSTANCE.createText();
		text.setFont(font);

		GFFigureUtil.drawRichText(graphicsMock, "Teststring", 10, 10, null, text);
	}

	private class GraphicsMock extends Graphics {

		@Override
		public void drawTextLayout(TextLayout layout, int x, int y, int selectionStart, int selectionEnd,
				Color selectionForeground, Color selectionBackground) {
			assertNotNull(layout.getFont());
		}

		@Override
		public void clipRect(Rectangle r) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public void drawArc(int x, int y, int w, int h, int offset, int length) {
		}

		@Override
		public void drawFocus(int x, int y, int w, int h) {
		}

		@Override
		public void drawImage(Image srcImage, int x, int y) {
		}

		@Override
		public void drawImage(Image srcImage, int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		}

		@Override
		public void drawLine(int x1, int y1, int x2, int y2) {
		}

		@Override
		public void drawOval(int x, int y, int w, int h) {
		}

		@Override
		public void drawPolygon(PointList points) {
		}

		@Override
		public void drawPolyline(PointList points) {
		}

		@Override
		public void drawRectangle(int x, int y, int width, int height) {
		}

		@Override
		public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		}

		@Override
		public void drawString(String s, int x, int y) {
		}

		@Override
		public void drawText(String s, int x, int y) {
		}

		@Override
		public void fillArc(int x, int y, int w, int h, int offset, int length) {
		}

		@Override
		public void fillGradient(int x, int y, int w, int h, boolean vertical) {
		}

		@Override
		public void fillOval(int x, int y, int w, int h) {
		}

		@Override
		public void fillPolygon(PointList points) {
		}

		@Override
		public void fillRectangle(int x, int y, int width, int height) {
		}

		@Override
		public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		}

		@Override
		public void fillString(String s, int x, int y) {
		}

		@Override
		public void fillText(String s, int x, int y) {
		}

		@Override
		public Color getBackgroundColor() {
			return null;
		}

		@Override
		public Rectangle getClip(Rectangle rect) {
			return null;
		}

		@Override
		public org.eclipse.swt.graphics.Font getFont() {
			return null;
		}

		@Override
		public FontMetrics getFontMetrics() {
			return null;
		}

		@Override
		public Color getForegroundColor() {
			return null;
		}

		@Override
		public int getLineStyle() {
			return 0;
		}

		@Override
		public int getLineWidth() {
			return 0;
		}

		@Override
		public float getLineWidthFloat() {
			return 0;
		}

		@Override
		public boolean getXORMode() {
			return false;
		}

		@Override
		public void popState() {
		}

		@Override
		public void pushState() {
		}

		@Override
		public void restoreState() {
		}

		@Override
		public void scale(double amount) {
		}

		@Override
		public void setBackgroundColor(Color rgb) {
		}

		@Override
		public void setClip(Rectangle r) {
		}

		@Override
		public void setFont(org.eclipse.swt.graphics.Font f) {
		}

		@Override
		public void setForegroundColor(Color rgb) {
		}

		@Override
		public void setLineStyle(int style) {
		}

		@Override
		public void setLineWidth(int width) {
		}

		@Override
		public void setLineWidthFloat(float width) {
		}

		@Override
		public void setLineMiterLimit(float miterLimit) {
		}

		@Override
		public void setXORMode(boolean b) {
		}

		@Override
		public void translate(int dx, int dy) {
		}
	}
}
