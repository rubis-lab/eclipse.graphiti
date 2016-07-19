/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial implementation
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO : figure out a way to test the disposal, via an invocation on DiagramBehavior.disposeAfterGefDispose()
// but this method is not visible for our test code...
public class ResourceManagerTest extends GFAbstractTestCase {

	private static DiagramBehavior diagramBehavior;
	private static Display display;

	@BeforeClass
	public static void before() {
		GFAbstractTestCase.before();
		display = Display.getDefault();
		diagramBehavior = new DiagramBehavior(null);
	}

	@AfterClass
	public static void after() {
		diagramBehavior.getResourceManager().dispose();
		display.dispose();
	}

	@Test
	public void testResourceManagerIsPresent() throws Exception {
		assertNotNull("JFace ResourceManager must be present on DiagramBehavior", diagramBehavior.getResourceManager());
	}

	@Test
	public void testColorCreation() throws Exception {
		Color color = diagramBehavior.getResourceManager().createColor(new RGB(100, 100, 100));
		assertNotNull(color);
	}

	@Test
	public void testColorReuse() throws Exception {
		Color color1 = diagramBehavior.getResourceManager().createColor(new RGB(100, 100, 100));
		Color color2 = diagramBehavior.getResourceManager().createColor(new RGB(100, 100, 100));
		assertSame(color1, color2);
	}

	@Test
	public void testFontCreation() throws Exception {
		Font font = diagramBehavior.getResourceManager().createFont(FontDescriptor.createFrom("Arial", 10, SWT.NORMAL));
		assertNotNull(font);
	}

	@Test
	public void testFontReuse() throws Exception {
		Font f1 = diagramBehavior.getResourceManager().createFont(FontDescriptor.createFrom("Arial", 10, SWT.NORMAL));
		Font f2 = diagramBehavior.getResourceManager().createFont(FontDescriptor.createFrom("Arial", 10, SWT.NORMAL));
		assertSame(f1, f2);
	}
}
