/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 467476 - NullPointerException in GFPaletteRoot.createModelIndependentTools
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import static org.junit.Assert.fail;

import org.junit.Test;

public class GFPaletteRootTest {

	@SuppressWarnings("restriction")
	@Test
	public void testGFPaletteRoot() {
		try {
			new GFPaletteRoot(null);
			fail("IllegalArgumentException was expected");
		} catch (IllegalArgumentException e) {
			// expected exception
		}
	}
}
