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
import org.eclipse.graphiti.ui.editor.DiagramEditorFactory;
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
		editingDomain = DiagramEditorFactory.createResourceSetAndEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("diagram", new XMIResourceFactoryImpl());

		// Register the packages of our model with EMF.
		{
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
		URL resource = getClass().getClassLoader().getResource("org/eclipse/graphiti/ui/tests/testUtil.diagram");
		URI createFileURI = URI.createFileURI(resource.getFile());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(createFileURI, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0");
		assertTrue(diagram.getFonts().isEmpty());
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				Graphiti.getMigrationService().migrate070To080(diagram);
			}
		});
		EList<Font> fonts = diagram.getFonts();
		assertTrue(fonts.size() == 1);
	}

	/**
	 * The diagram file contains two styles with identical fonts. These fonts
	 * should be aggregated at the diagram level, resulting in only one font.
	 */
	@Test
	public void testMig070To0802() {

		// Load diagram file.
		URL resource = getClass().getClassLoader().getResource("org/eclipse/graphiti/ui/tests/tut.diagram");
		URI createFileURI = URI.createFileURI(resource.getFile());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(createFileURI, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0");
		assertTrue(diagram.getFonts().isEmpty());
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				Graphiti.getMigrationService().migrate070To080(diagram);
			}
		});
		EList<Font> fonts = diagram.getFonts();
		assertTrue(fonts.size() == 1);
	}

}
