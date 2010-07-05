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
package org.eclipse.graphiti.tests;

import static org.junit.Assert.*;

import org.eclipse.graphiti.mm.pictograms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Image;
import org.eclipse.graphiti.mm.pictograms.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Orientation;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;

public class GFAbstractCreateTestCase extends GFAbstractTestCase {

	protected static final String VALUE = "TEST";

	protected void checkGraphicsAlgorithmDefaults(GraphicsAlgorithm ga) {
		if (ga instanceof Polyline && !(ga instanceof Polygon))
			assertFalse(ga.getFilled());
		else
			assertTrue(ga.getFilled());
		assertSame(LineStyle.SOLID, ga.getLineStyle());
		assertTrue(ga.getLineVisible());
		assertEquals(new Integer(1), ga.getLineWidth());
		assertEquals(0.0, ga.getTransparency(), 0);
	}

	protected void checkImageDefaults(Image im) {
		assertEquals(VALUE, im.getId());
		assertFalse(im.getProportional());
		assertFalse(im.getStretchH());
		assertFalse(im.getStretchV());
	
	}

	protected void checkTextDefaults(AbstractText text, String value) {
		checkGraphicsAlgorithmDefaults(text);
		assertEquals(new Integer(0), text.getAngle());
		assertEquals(Orientation.ALIGNMENT_LEFT, text.getHorizontalAlignment());
		assertEquals(Orientation.ALIGNMENT_CENTER, text.getVerticalAlignment());
		assertEquals(value, text.getValue());
	}
}
