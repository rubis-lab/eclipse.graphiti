package org.eclipse.graphiti.ui.tests;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.eclipse.graphiti.ui.internal.command.AddModelObjectCommand;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.graphiti.ui.internal.command.CreateModelObjectCommand;
import org.eclipse.graphiti.ui.internal.command.ReconnectCommand;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class CommandTest extends GFAbstractTestCase {

	@Test
	public void testAddModelObjectCommand_canUndo_callsFeature() throws Exception {
		final boolean[] undoWasCalled = { false };

		IAddFeature addFeature = new IAddFeature() {
			@Override
			public IFeatureProvider getFeatureProvider() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public boolean isAvailable(IContext context) {
				return false;
			}

			@Override
			public boolean hasDoneChanges() {
				return false;
			}

			@Override
			public void execute(IContext context) {
			}

			@Override
			public boolean canUndo(IContext context) {
				undoWasCalled[0] = true;
				return true;
			}

			@Override
			public boolean canExecute(IContext context) {
				return false;
			}

			@Override
			public boolean canAdd(IAddContext context) {
				return true;
			}

			@Override
			public PictogramElement add(IAddContext context) {
				return null;
			}
		};

		IFeatureProvider featureProviderMock = createNiceMock(IFeatureProvider.class);
		expect(featureProviderMock.getAddFeature((IAddContext) EasyMock.anyObject())).andReturn(addFeature);
		replay(featureProviderMock);

		IDiagramTypeProvider diagramTypeProviderMock = createNiceMock(IDiagramTypeProvider.class);
		expect(diagramTypeProviderMock.getFeatureProvider()).andReturn(featureProviderMock).anyTimes();
		replay(diagramTypeProviderMock);

		IConfigurationProviderInternal configurationProviderMock = createNiceMock(IConfigurationProviderInternal.class);
		expect(configurationProviderMock.getDiagramTypeProvider()).andReturn(diagramTypeProviderMock).anyTimes();
		replay(configurationProviderMock);

		ContainerShape containerShapeMock = createNiceMock(ContainerShape.class);
		replay(containerShapeMock);

		IAdaptable adaptableMock = createNiceMock(IAdaptable.class);
		expect(adaptableMock.getAdapter(EObject.class)).andReturn(containerShapeMock).anyTimes();
		replay(adaptableMock);

		ISelection selection = new StructuredSelection(new Object[] { adaptableMock });

		Rectangle rectangle = new Rectangle();

		AddModelObjectCommand myAddModelObjectCommand = new AddModelObjectCommand(configurationProviderMock,
				containerShapeMock, selection, rectangle);

		assertTrue(myAddModelObjectCommand.canUndo());

		assertTrue(undoWasCalled[0]);
	}

	@Test
	public void testCreateConnectionCommand_canUndo_callsFeature() throws Exception {
		final boolean[] undoWasCalled = { false };

		ICreateConnectionFeature[] createConnectionFeatures = { new ICreateConnectionFeature() {
			@Override
			public boolean canUndo(IContext context) {
				undoWasCalled[0] = true;
				return true;
			}

			@Override
			public boolean canCreate(ICreateConnectionContext context) {
				return false;
			}

			@Override
			public Connection create(ICreateConnectionContext context) {
				return null;
			}

			@Override
			public boolean canStartConnection(ICreateConnectionContext context) {
				return false;
			}

			@Override
			public void startConnecting() {
			}

			@Override
			public void endConnecting() {
			}

			@Override
			public void attachedToSource(ICreateConnectionContext context) {
			}

			@Override
			public void canceledAttaching(ICreateConnectionContext context) {
			}

			@Override
			public String getCreateName() {
				return null;
			}

			@Override
			public String getCreateDescription() {
				return null;
			}

			@Override
			public String getCreateImageId() {
				return null;
			}

			@Override
			public String getCreateLargeImageId() {
				return null;
			}

			@Override
			public boolean isAvailable(IContext context) {
				return false;
			}

			@Override
			public boolean canExecute(IContext context) {
				return false;
			}

			@Override
			public void execute(IContext context) {
			}

			@Override
			public boolean hasDoneChanges() {
				return false;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public IFeatureProvider getFeatureProvider() {
				return null;
			}
		}};

		IFeatureProvider featureProviderMock = createNiceMock(IFeatureProvider.class);
		expect(featureProviderMock.getCreateConnectionFeatures()).andReturn(
				(ICreateConnectionFeature[]) createConnectionFeatures);
		replay(featureProviderMock);

		IDiagramTypeProvider diagramTypeProviderMock = createNiceMock(IDiagramTypeProvider.class);
		expect(diagramTypeProviderMock.getFeatureProvider()).andReturn(featureProviderMock).anyTimes();
		replay(diagramTypeProviderMock);

		IConfigurationProvider configurationProviderMock = createNiceMock(IConfigurationProvider.class);
		expect(configurationProviderMock.getDiagramTypeProvider()).andReturn(diagramTypeProviderMock).anyTimes();
		replay(configurationProviderMock);

		@SuppressWarnings("unchecked")
		EList<Anchor> anchorList = createNiceMock(EList.class);
		expect(anchorList.iterator()).andReturn(new ArrayList().iterator());
		replay(anchorList);
		
		ContainerShape containerShapeMock = createNiceMock(ContainerShape.class);
		expect(containerShapeMock.getAnchors()).andReturn(anchorList);
		replay(containerShapeMock);

		IFeature[] features = createConnectionFeatures;
		CreateConnectionCommand myCreateConnectionCommand = new CreateConnectionCommand(configurationProviderMock,
				containerShapeMock, Arrays.asList(features));

		assertTrue(myCreateConnectionCommand.canUndo());

		assertTrue(undoWasCalled[0]);
	}

	@Test
	public void testCreateodelObjectCommand_canUndo_callsFeature() throws Exception {
		final boolean[] undoWasCalled = { false };

		ICreateFeature[] createFeatures = { new ICreateFeature() {
			@Override
			public boolean canUndo(IContext context) {
				undoWasCalled[0] = true;
				return true;
			}

			@Override
			public boolean canCreate(ICreateContext context) {
				return false;
			}

			@Override
			public Object[] create(ICreateContext context) {
				return null;
			}

			@Override
			public String getCreateName() {
				return null;
			}

			@Override
			public String getCreateDescription() {
				return null;
			}

			@Override
			public String getCreateImageId() {
				return null;
			}

			@Override
			public String getCreateLargeImageId() {
				return null;
			}

			@Override
			public boolean isAvailable(IContext context) {
				return false;
			}

			@Override
			public boolean canExecute(IContext context) {
				return false;
			}

			@Override
			public void execute(IContext context) {
			}

			@Override
			public boolean hasDoneChanges() {
				return false;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public IFeatureProvider getFeatureProvider() {
				return null;
			}
		} };

		IFeatureProvider featureProviderMock = createNiceMock(IFeatureProvider.class);
		expect(featureProviderMock.getCreateFeatures()).andReturn(createFeatures);
		replay(featureProviderMock);

		IDiagramTypeProvider diagramTypeProviderMock = createNiceMock(IDiagramTypeProvider.class);
		expect(diagramTypeProviderMock.getFeatureProvider()).andReturn(featureProviderMock).anyTimes();
		replay(diagramTypeProviderMock);

		IConfigurationProviderInternal configurationProviderMock = createNiceMock(IConfigurationProviderInternal.class);
		expect(configurationProviderMock.getDiagramTypeProvider()).andReturn(diagramTypeProviderMock).anyTimes();
		replay(configurationProviderMock);

		CreateModelObjectCommand myCreateModelObjectCommand = new CreateModelObjectCommand(configurationProviderMock,
				createFeatures[0], null, null);

		assertTrue(myCreateModelObjectCommand.canUndo());

		assertTrue(undoWasCalled[0]);
	}

	@Test
	public void testReconnectCommand_canUndo_callsFeature() throws Exception {
		final boolean[] undoWasCalled = { false };

		IReconnectionFeature reconnectFeature = new IReconnectionFeature() {

			@Override
			public IFeatureProvider getFeatureProvider() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public boolean isAvailable(IContext context) {
				return false;
			}

			@Override
			public boolean hasDoneChanges() {
				return false;
			}

			@Override
			public void execute(IContext context) {
			}

			@Override
			public boolean canUndo(IContext context) {
				undoWasCalled[0] = true;
				return true;
			}

			@Override
			public boolean canExecute(IContext context) {
				return false;
			}

			@Override
			public void reconnect(IReconnectionContext context) {
			}

			@Override
			public void preReconnect(IReconnectionContext context) {
			}

			@Override
			public void postReconnect(IReconnectionContext context) {
			}

			@Override
			public void canceledReconnect(IReconnectionContext context) {
			}

			@Override
			public boolean canReconnect(IReconnectionContext context) {
				return false;
			}
		};

		IFeatureProvider featureProviderMock = createNiceMock(IFeatureProvider.class);
		expect(featureProviderMock.getReconnectionFeature((IReconnectionContext) EasyMock.anyObject())).andReturn(
				reconnectFeature);
		replay(featureProviderMock);

		IDiagramTypeProvider diagramTypeProviderMock = createNiceMock(IDiagramTypeProvider.class);
		expect(diagramTypeProviderMock.getFeatureProvider()).andReturn(featureProviderMock).anyTimes();
		replay(diagramTypeProviderMock);

		IConfigurationProviderInternal configurationProviderMock = createNiceMock(IConfigurationProviderInternal.class);
		expect(configurationProviderMock.getDiagramTypeProvider()).andReturn(diagramTypeProviderMock).anyTimes();
		replay(configurationProviderMock);

		ReconnectCommand myReconnectCommand = new ReconnectCommand(configurationProviderMock, null, null, null, null,
				null, null);

		assertTrue(myReconnectCommand.canUndo());

		assertTrue(undoWasCalled[0]);
	}
}
