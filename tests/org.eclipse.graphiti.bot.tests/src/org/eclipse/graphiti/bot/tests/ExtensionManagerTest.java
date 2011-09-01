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

import org.eclipse.graphiti.bot.tests.util.ITestConstants;
import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.ui.services.IExtensionManager;
import org.junit.Test;


public class ExtensionManagerTest extends AbstractGFTests {
	
	@Test
	public void testInstallation() throws Exception {

		PictogramsPackage pictogramsPackage = PictogramsPackage.eINSTANCE;
		assertNotNull("pictograms package not available", pictogramsPackage);

		IExtensionManager extensionManager = GraphitiUi.getExtensionManager();
		assertNotNull("extension manager not available", extensionManager);

		String[] diagramTypeProviders = extensionManager
				.getDiagramTypeProviderIds(ITestConstants.DIAGRAM_TYPE_ID_SKETCH);
		assertTrue("diagram type provider not available", diagramTypeProviders.length > 0);

		IDiagramTypeProvider dtp = extensionManager.createDiagramTypeProvider(diagramTypeProviders[0]);
		assertNotNull("diagram type provider couldn't be created", dtp);

		dtp.init(null, null);
		IFeatureProvider fp = dtp.getFeatureProvider();
		assertNotNull("feature provider not available", fp);
	}

	@Test
	public void testCreateDiagramTypeProvider() {
		// Create a diagram type provider which defines an invalid image provider. The DTP must be created nevertheless.
		// See Bug 352709
		IDiagramTypeProvider diagramTypeProvider = GraphitiUi.getExtensionManager().createDiagramTypeProvider(
				"org.eclipse.graphiti.ui.tests.dtpWithInvalidImageProvider");
		assertNotNull("Diagram type Provider must not be null", diagramTypeProvider);
	}
	
	@Test
	public void testExtensionManager() {
		IExtensionManager em = GraphitiUi.getExtensionManager();

		IDiagramType sketchDiagramType = null;

		// check whether the sketch diagram type is registered
		IDiagramType[] diagramTypes = em.getDiagramTypes();
		for (IDiagramType diagramType : diagramTypes) {
			if (ITestConstants.DIAGRAM_TYPE_ID_SKETCH.equals(diagramType.getId())) {
				sketchDiagramType = diagramType;
				break;
			}
		}

		assertNotNull("sketch digram type is not available", sketchDiagramType);

		// checker whether a provider for the sketch diagram type is registered
		// - and if yes - instantiate a diagram type provider
		if (sketchDiagramType != null) {
			String[] diagramTypeProviderExtensionIds = em.getDiagramTypeProviderIds(sketchDiagramType.getId());
			if (diagramTypeProviderExtensionIds != null && diagramTypeProviderExtensionIds.length > 0) {
				IDiagramTypeProvider dtp = em.createDiagramTypeProvider(diagramTypeProviderExtensionIds[0]);
				assertNotNull("sketch diagram type provider couldn't be instantiated", dtp);
			}
		}

	}
}
