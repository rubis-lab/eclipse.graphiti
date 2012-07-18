/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 324859 - initial API, implementation and documentation
 *    cbrand - Bug 377475 - Fix AbstractCustomFeature.execute and canExecute
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICustomUndoableFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.editor.GFCommandStack;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 */
@SuppressWarnings("restriction")
public class CustomUndoableFeatureTest extends GFAbstractTestCase {

	@BeforeClass
	public static void prepareClass() {
	}

	@Before
	public void initializeTest() {
	}

	@Test
	public void testPositive() {
		TransactionalEditingDomain editingDomain = GraphitiUi.getEmfService().createResourceSetAndEditingDomain();
		IToolBehaviorProvider toolBehaviorProvider = EasyMock.createNiceMock(IToolBehaviorProvider.class);
		EasyMock.replay(toolBehaviorProvider);

		IDiagramTypeProvider diagramTypeProvider = EasyMock.createNiceMock(IDiagramTypeProvider.class);
		IFeatureProvider featureProvider = EasyMock.createNiceMock(IFeatureProvider.class);
		EasyMock.replay(featureProvider);

		EasyMock.expect(diagramTypeProvider.getCurrentToolBehaviorProvider()).andReturn(toolBehaviorProvider).anyTimes();
		EasyMock.replay(diagramTypeProvider);

		IConfigurationProvider configurationProvider = EasyMock.createNiceMock(IConfigurationProviderInternal.class);
		EasyMock.expect(configurationProvider.getDiagramTypeProvider()).andReturn(diagramTypeProvider).anyTimes();
		EasyMock.replay(configurationProvider);
		GFCommandStack commandStack = new GFCommandStack(configurationProvider, editingDomain);

		ICustomContext context = EasyMock.createNiceMock(ICustomContext.class);
		EasyMock.replay(context);

		TestCustomFeature feature = new TestCustomFeature(featureProvider);
		GenericFeatureCommandWithContext featureCommand = new GenericFeatureCommandWithContext(feature, context);

		CommandContainer commandContainer = new CommandContainer(featureProvider);
		commandContainer.add(featureCommand);

		GefCommandWrapper commandWrapper = new GefCommandWrapper(commandContainer, editingDomain);
		commandStack.execute(commandWrapper);

		// Check that feature can be undone
		assertTrue("Executed command must be undoable", commandStack.canUndo());

		// Check that undo is called
		commandStack.undo();
		assertTrue("Undo() must have been called", feature.undoCalled);

		// Check that feature can be redone
		assertTrue("Executed command must be redoable", commandStack.canRedo());

		// Check that redo is called
		commandStack.redo();
		assertTrue("Redo() must have been called", feature.redoCalled);
	}

	private class TestCustomFeature extends AbstractCustomFeature implements ICustomUndoableFeature {

		public boolean undoCalled = false;
		public boolean redoCalled = false;
		private ICustomContext context = null;

		public TestCustomFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canExecute(ICustomContext context) {
			return true;
		}

		public void execute(ICustomContext context) {
			// Do nothing
			this.context = context;
		}

		@Override
		public boolean canUndo(IContext context) {
			return true;
		}

		public void undo(IContext context) {
			undoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public boolean canRedo(IContext context) {
			return true;
		}

		public void redo(IContext context) {
			redoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}
	}
}
