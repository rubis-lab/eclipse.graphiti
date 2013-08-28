/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 415884 - Cannot query size of a multi-line text
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LayoutServiceTest extends GFAbstractTestCase {

	private static Font font;

	@BeforeClass
	public static void before() {
		GFAbstractTestCase.before();

		font = StylesFactory.eINSTANCE.createFont();
		font.eSet(StylesPackage.eINSTANCE.getFont_Name(), "dummy");
	}

	@Test
	public void testPlainCalculation() throws Exception {
		IDimension textSize1 = GraphitiUi.getUiLayoutService().calculateTextSize("Plain text", font);
		IDimension textSize2 = GraphitiUi.getUiLayoutService().calculateTextSize("Plain text \n continued", font);

		Assert.assertEquals(textSize1.getHeight(), textSize2.getHeight());
		Assert.assertTrue(textSize1.getWidth() < textSize2.getWidth());
	}

	@Test
	public void testNewLineCalculation() throws Exception {
		IDimension textSize1 = GraphitiUi.getUiLayoutService().calculateTextSize("Plain text", font, true);
		IDimension textSize2 = GraphitiUi.getUiLayoutService().calculateTextSize("Plain text \n continued", font, true);

		Assert.assertNotEquals(textSize1.getHeight(), textSize2.getHeight());
	}

	@Test
	public void testPlainTextCalculation() throws Exception {
		Text text1 = AlgorithmsFactory.eINSTANCE.createText();
		text1.setFont(font);
		text1.setValue("Plain text");

		Text text2 = AlgorithmsFactory.eINSTANCE.createText();
		text2.setFont(font);
		text2.setValue("Plain text \n continued");

		IDimension textSize1 = GraphitiUi.getUiLayoutService().calculateTextSize(text1);
		IDimension textSize2 = GraphitiUi.getUiLayoutService().calculateTextSize(text2);

		Assert.assertEquals(textSize1.getHeight(), textSize2.getHeight());
		Assert.assertTrue(textSize1.getWidth() < textSize2.getWidth());
	}

	@Test
	public void testMultiTextCalculation() throws Exception {
		MultiText text1 = AlgorithmsFactory.eINSTANCE.createMultiText();
		text1.setFont(font);
		text1.setValue("Plain text");

		MultiText text2 = AlgorithmsFactory.eINSTANCE.createMultiText();
		text2.setFont(font);
		text2.setValue("Plain text \n continued");

		IDimension textSize1 = GraphitiUi.getUiLayoutService().calculateTextSize(text1);
		IDimension textSize2 = GraphitiUi.getUiLayoutService().calculateTextSize(text2);

		Assert.assertNotEquals(textSize1.getHeight(), textSize2.getHeight());
	}
}
