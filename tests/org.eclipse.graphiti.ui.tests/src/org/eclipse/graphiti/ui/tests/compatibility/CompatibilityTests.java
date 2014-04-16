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
 *    SAP AG - initial API, implementation and documentation (Bug 423573 - Angles should never be integer)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests.compatibility;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompatibilityTests {

	private static TransactionalEditingDomain editingDomain;

	@BeforeClass
	public static void before() {
		editingDomain = GraphitiUi.getEmfService().createResourceSetAndEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("diagram", new XMIResourceFactoryImpl()); //$NON-NLS-1$

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

	@SuppressWarnings("deprecation")
	@Test
	public void testCanReadDiagramWithAngleAttributeInAbstractText() throws Exception {

		// Load diagram file.
		URL resource = getClass().getClassLoader().getResource(
				"org/eclipse/graphiti/ui/tests/compatibility/AbstractText_angle_to_rotation.diagram"); //$NON-NLS-1$
		URI createFileURI = URI.createFileURI(resource.getFile());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(createFileURI, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0"); //$NON-NLS-1$

		EList<Shape> children = diagram.getChildren();
		Shape shape = children.get(0);
		GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
		EList<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		MultiText text = (MultiText) graphicsAlgorithmChildren.get(0);

		// Text has angle attribute set to 45
		// -> Reading the object must have stored the value in the new replacing
		// field rotation but also (for compatibility) in the angle attribute
		assertEquals(45d, text.getRotation(), 0d);
		assertEquals(new Integer(45), text.getAngle());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCanReadDiagramWithAngleAttributeInStyle() throws Exception {

		// Load diagram file.
		URL resource = getClass().getClassLoader().getResource(
				"org/eclipse/graphiti/ui/tests/compatibility/Style_angle_to_rotation.diagram"); //$NON-NLS-1$
		URI createFileURI = URI.createFileURI(resource.getFile());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(createFileURI, true);

		final Diagram diagram = (Diagram) diagramResource.getEObject("/0"); //$NON-NLS-1$

		EList<Style> styles = diagram.getStyles();
		Style style = styles.get(0);

		// Style has angle attribute set to 30
		// -> Reading the object must have stored the value in the new replacing
		// field rotation but also (for compatibility) in the angle attribute
		assertEquals(30d, style.getRotation(), 0d);
		assertEquals(new Integer(30), style.getAngle());
	}
}
