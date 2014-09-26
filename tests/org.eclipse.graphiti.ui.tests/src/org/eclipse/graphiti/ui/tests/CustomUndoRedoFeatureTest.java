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
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
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
import org.eclipse.graphiti.features.ICustomUndoRedoFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.internal.command.CommandContainer;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.command.ReconnectCommand;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.editor.GFCommandStack;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.junit.Test;

/**
 *
 */
@SuppressWarnings("restriction")
public class CustomUndoRedoFeatureTest extends GFAbstractTestCase {

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
		assertTrue("preUndo() must have been called", feature.preUndoCalled);
		assertTrue("undo() must have been called", feature.undoCalled);
		assertTrue("postUndo() must have been called", feature.postUndoCalled);

		// Check that feature can be redone
		assertTrue("Executed command must be redoable", commandStack.canRedo());

		// Check that redo is called
		commandStack.redo();
		assertTrue("preRedo() must have been called", feature.preRedoCalled);
		assertTrue("redo() must have been called", feature.redoCalled);
		assertTrue("postRedo() must have been called", feature.postRedoCalled);
	}

	@Test
	public void testReconnect() {
		TransactionalEditingDomain editingDomain = GraphitiUi.getEmfService().createResourceSetAndEditingDomain();
		IToolBehaviorProvider toolBehaviorProvider = EasyMock.createNiceMock(IToolBehaviorProvider.class);
		EasyMock.replay(toolBehaviorProvider);

		IDiagramTypeProvider diagramTypeProvider = EasyMock.createNiceMock(IDiagramTypeProvider.class);
		IFeatureProvider featureProvider = EasyMock.createNiceMock(IFeatureProvider.class);
		TestReconnectionFeature feature = new TestReconnectionFeature(featureProvider);
		EasyMock.expect(featureProvider.getReconnectionFeature(EasyMock.<IReconnectionContext> anyObject()))
				.andReturn(feature).anyTimes();
		EasyMock.replay(featureProvider);

		EasyMock.expect(diagramTypeProvider.getCurrentToolBehaviorProvider()).andReturn(toolBehaviorProvider).anyTimes();
		EasyMock.expect(diagramTypeProvider.getFeatureProvider()).andReturn(featureProvider).anyTimes();
		EasyMock.replay(diagramTypeProvider);

		IDiagramContainerUI diagramContainer = EasyMock.createNiceMock(IDiagramContainerUI.class);

		DiagramBehavior diagramBehavior = new MockDiagramBehavior(diagramContainer, editingDomain);

		IConfigurationProvider configurationProvider = EasyMock.createNiceMock(IConfigurationProviderInternal.class);
		EasyMock.expect(configurationProvider.getDiagramTypeProvider()).andReturn(diagramTypeProvider).anyTimes();
		EasyMock.expect(configurationProvider.getDiagramBehavior()).andReturn(diagramBehavior).anyTimes();
		EasyMock.replay(configurationProvider);
		GFCommandStack commandStack = new GFCommandStack(configurationProvider, editingDomain);

		ICustomContext context = EasyMock.createNiceMock(ICustomContext.class);
		EasyMock.replay(context);

		Connection connection = EasyMock.createNiceMock(Connection.class);
		Anchor oldAnchor = EasyMock.createNiceMock(Anchor.class);
		Anchor newAnchor = EasyMock.createNiceMock(Anchor.class);
		PictogramElement newTargetPictogramElement = EasyMock.createNiceMock(PictogramElement.class);

		ReconnectCommand reconnectCommand = new ReconnectCommand(configurationProvider, connection, oldAnchor,
				newAnchor, newTargetPictogramElement, ReconnectionContext.RECONNECT_SOURCE, null);
		commandStack.execute(reconnectCommand);

		// Check that feature can be undone
		assertTrue("Executed command must be undoable", commandStack.canUndo());

		// Check that undo is called
		commandStack.undo();
		assertTrue("preUndo() must have been called", feature.preUndoCalled);
		assertTrue("undo() must have been called", feature.undoCalled);
		assertTrue("postUndo() must have been called", feature.postUndoCalled);

		// Check that feature can be redone
		assertTrue("Executed command must be redoable", commandStack.canRedo());

		// Check that redo is called
		commandStack.redo();
		assertTrue("preRedo() must have been called", feature.preRedoCalled);
		assertTrue("redo() must have been called", feature.redoCalled);
		assertTrue("postRedo() must have been called", feature.postRedoCalled);

	}

	private class TestCustomFeature extends AbstractCustomFeature implements ICustomUndoRedoFeature {

		public boolean preUndoCalled = false;
		public boolean undoCalled = false;
		public boolean postUndoCalled = false;

		public boolean preRedoCalled = false;
		public boolean redoCalled = false;
		public boolean postRedoCalled = false;

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

		@Override
		public void preUndo(IContext context) {
			preUndoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public void undo(IContext context) {
			undoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		@Override
		public void postUndo(IContext context) {
			postUndoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public boolean canRedo(IContext context) {
			return true;
		}

		@Override
		public void preRedo(IContext context) {
			preRedoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public void redo(IContext context) {
			redoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		@Override
		public void postRedo(IContext context) {
			postRedoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}
	}

	private class TestReconnectionFeature extends DefaultReconnectionFeature implements ICustomUndoRedoFeature {

		public boolean preUndoCalled = false;
		public boolean undoCalled = false;
		public boolean postUndoCalled = false;

		public boolean preRedoCalled = false;
		public boolean redoCalled = false;
		public boolean postRedoCalled = false;

		private IReconnectionContext context = null;

		public TestReconnectionFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canReconnect(IReconnectionContext context) {
			return true;
		}

		@Override
		public boolean canUndo(IContext context) {
			return true;
		}

		@Override
		public void preUndo(IContext context) {
			preUndoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public void undo(IContext context) {
			undoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		@Override
		public void postUndo(IContext context) {
			postUndoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public boolean canRedo(IContext context) {
			return true;
		}

		@Override
		public void preRedo(IContext context) {
			preRedoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public void redo(IContext context) {
			redoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		@Override
		public void postRedo(IContext context) {
			postRedoCalled = true;
			assertEquals("Context object must be the same as in execute", this.context, context);
		}

		public void preReconnect(IReconnectionContext context) {
			// Do nothing
			this.context = context;
		}

		public void postReconnect(IReconnectionContext context) {
		}

		public void canceledReconnect(IReconnectionContext context) {
		}
	}

	public class MockDiagramBehavior extends DiagramBehavior {

		private TransactionalEditingDomain editingDomain;

		public MockDiagramBehavior(IDiagramContainerUI diagramContainer, TransactionalEditingDomain editingDomain) {
			super(diagramContainer);
			this.editingDomain = editingDomain;
		}

		@Override
		public TransactionalEditingDomain getEditingDomain() {
			return editingDomain;
		}
	}

}
