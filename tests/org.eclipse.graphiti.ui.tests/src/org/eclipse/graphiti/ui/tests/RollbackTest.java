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
 *    mwenz - Bug 352220 - Possibility to disable guides
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.net.URL;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.internal.EMFWorkspacePlugin;
import org.eclipse.emf.workspace.internal.EMFWorkspacePlugin.Implementation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.editor.DiagramEditorFactory;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.editor.GFCommandStack;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 *
 */
public class RollbackTest extends GFAbstractTestCase {
	private static TransactionalEditingDomain editingDomain;

	@BeforeClass
	public static void before() {
		editingDomain = DiagramEditorFactory.createResourceSetAndEditingDomain();
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

	@Test
	public void testRollback() {

		// Load diagram file.
		URL resource = getClass().getClassLoader().getResource("org/eclipse/graphiti/ui/tests/tut.diagram"); //$NON-NLS-1$
		URI createFileURI = URI.createFileURI(resource.getFile());
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource diagramResource = resourceSet.getResource(createFileURI, true);
		final Diagram diagram = (Diagram) diagramResource.getEObject("/0"); //$NON-NLS-1$

		// Prepare mocking.
		IToolBehaviorProvider toolBehaviorProvider = EasyMock.createNiceMock(IToolBehaviorProvider.class);
		EasyMock.replay(toolBehaviorProvider);

		IDiagramTypeProvider diagramTypeProvider = EasyMock.createNiceMock(IDiagramTypeProvider.class);
		IFeatureProvider featureProvider = EasyMock.createNiceMock(IFeatureProvider.class);
		EasyMock.expect(featureProvider.getDiagramTypeProvider()).andReturn(diagramTypeProvider).anyTimes();
		EasyMock.replay(featureProvider);

		EasyMock.expect(diagramTypeProvider.getCurrentToolBehaviorProvider()).andReturn(toolBehaviorProvider).anyTimes();
		EasyMock.expect(diagramTypeProvider.getFeatureProvider()).andReturn(featureProvider).anyTimes();
		EasyMock.replay(diagramTypeProvider);

		IConfigurationProvider configurationProvider = EasyMock.createNiceMock(IConfigurationProvider.class);
		EasyMock.expect(configurationProvider.getDiagramTypeProvider()).andReturn(diagramTypeProvider).anyTimes();
		EasyMock.replay(configurationProvider);

		TestCustomFeature feature = new TestCustomFeature(configurationProvider.getFeatureProvider());
		ICustomContext context = EasyMock.createNiceMock(ICustomContext.class);
		EasyMock.replay(context);
		
		try {
			Field pluginField = EMFWorkspacePlugin.class.getDeclaredField("plugin");
			pluginField.setAccessible(true);
			BundleContext bundleContextMock = EasyMock.createNiceMock(BundleContext.class);
			Bundle bundleMock = EasyMock.createNiceMock(Bundle.class);
			EasyMock.expect(bundleMock.getSymbolicName()).andReturn("org.eclipse.emf.workspace.plugin.id").anyTimes();
			EasyMock.expect(bundleContextMock.getBundle()).andReturn(bundleMock).anyTimes();
			EasyMock.replay(bundleMock, bundleContextMock);
			Implementation impl = new Implementation();
			impl.start(bundleContextMock);
			pluginField.set(EMFWorkspacePlugin.class, impl);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Execute feature to be rolled back
		GFCommandStack commandStack = new GFCommandStack(configurationProvider, editingDomain);
		GenericFeatureCommandWithContext featureCommand = new GenericFeatureCommandWithContext(feature, context);
		CommandContainer commandContainer = new CommandContainer(configurationProvider.getFeatureProvider());
		commandContainer.add(featureCommand);
		GefCommandWrapper commandWrapper = new GefCommandWrapper(featureCommand, editingDomain);
		
		commandStack.execute(commandWrapper);

		// Check.
		assertTrue("Rollback failed. SnapToGrid is false. ", diagram.isSnapToGrid());
		assertFalse(commandStack.isDirty());

	}

	class TestCustomFeature extends AbstractCustomFeature {

		public TestCustomFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canExecute(ICustomContext context) {
			return true;
		}

		@Override
		public void execute(ICustomContext context) {
			assertTrue(getDiagram().isSnapToGrid());
			getDiagram().setSnapToGrid(false);
			assertTrue(!getDiagram().isSnapToGrid());
			// force rollback
			throw new OperationCanceledException();
		}

		@Override
		public boolean hasDoneChanges() {
			return true;
		}
	}
}
