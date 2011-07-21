/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 352709 - invalid image provider id crashes diagram editor 
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.Test;

/**
 *
 */
public class ExtensionManagerTest extends AbstractGFTests {

	@Test
	public void testCreateDiagramTypeProvider() {
		// Create a diagram type provider which defines an invalid image provider. The DTP must be created nevertheless.
		// See Bug 352709
		IDiagramTypeProvider diagramTypeProvider = GraphitiUi.getExtensionManager().createDiagramTypeProvider(
				"org.eclipse.graphiti.ui.tests.dtpWithInvalidImageProvider");
		assertNotNull("Diagram type Provider must not be null", diagramTypeProvider);
	}
}
