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
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests migration services.
 */
public class MigrationServiceTest extends GFAbstractTestCase {

	private static TransactionalEditingDomain editingDomain;

	@BeforeClass
	public static void before() {
		editingDomain = GraphitiUi.getEmfService().createResourceSetAndEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("diagram", new XMIResourceFactoryImpl()); //$NON-NLS-1$

		// Register the packages of our model with EMF.
		{
			@SuppressWarnings("unused")
			Object o = PictogramsPackage.eINSTANCE;
			o = AlgorithmsPackage.eINSTANCE;
			o = StylesPackage.eINSTANCE;
		}
	}

	@AfterClass
	public static void after() {
		editingDomain.dispose();
	}

	/**
	 * The diagram file contains two identical fonts for two Abstract Texts.
	 * These fonts should be aggregated at the diagram level, resulting in only
	 * one font.
	 */
	@Test
	public void testMig070To080() {

		// Load diagram file.
		URL url = getClass().getClassLoader().getResource("org/eclipse/graphiti/ui/tests/testUtil.diagram"); //$NON-NLS-1$
		URI uri = URI.createURI(url.toString());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(uri, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0"); //$NON-NLS-1$
		assertTrue(diagram.getFonts().isEmpty());
		assertTrue(Graphiti.getMigrationService().shouldMigrate070To080(diagram));
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				Graphiti.getMigrationService().migrate070To080(diagram);
			}
		});
		EList<Font> fonts = diagram.getFonts();
		assertTrue(fonts.size() == 1);
		assertFalse(Graphiti.getMigrationService().shouldMigrate070To080(diagram));
	}

	/**
	 * The diagram file contains two styles with identical fonts. These fonts
	 * should be aggregated at the diagram level, resulting in only one font.
	 */
	@Test
	public void testMig070To0802() {

		// Load diagram file.
		URL url = getClass().getClassLoader().getResource("org/eclipse/graphiti/ui/tests/tut.diagram"); //$NON-NLS-1$
		URI uri = URI.createURI(url.toString());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(uri, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0"); //$NON-NLS-1$
		assertTrue(diagram.getFonts().isEmpty());
		assertTrue(Graphiti.getMigrationService().shouldMigrate070To080(diagram));
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				Graphiti.getMigrationService().migrate070To080(diagram);
			}
		});
		EList<Font> fonts = diagram.getFonts();
		assertTrue(fonts.size() == 1);
		assertFalse(Graphiti.getMigrationService().shouldMigrate070To080(diagram));
	}

}
