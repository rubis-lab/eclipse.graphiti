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
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mgorning - Bug 343983 - Notification for Cancelled Reconnection Events
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramEditorUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.datatypes.impl.LocationImpl;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.IDiagramEditorUI;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ReconnectCommand extends AbstractCommand implements IFeatureAndContext {

	private IReconnectionContext ctx;

	private IReconnectionFeature feature;

	private boolean reconnectFinished = false;

	/**
	 * Instantiate a command that can reconnect a Connection instance to a
	 * different source or target endpoint.
	 * 
	 * @param conn
	 *            the connection instance to reconnect (non-null)
	 * @throws IllegalArgumentException
	 *             if conn is null
	 */
	public ReconnectCommand(IConfigurationProvider configurationProvider, Connection connection, Anchor oldAnchor,
			Anchor newAnchor, PictogramElement newTargetPictogramElement, String reconnectType, Point location) {
		super(configurationProvider);

		ILocation targetLocation = null;
		if (location != null) {
			IDiagramEditorUI diagramEditor = (IDiagramEditorUI) getFeatureProvider().getDiagramTypeProvider().getDiagramEditor();
			Point realLocation = diagramEditor.calculateRealMouseLocation(location);
			targetLocation = new LocationImpl(realLocation.x, realLocation.y);
		}
		this.ctx = new ReconnectionContext(connection, oldAnchor, newAnchor, targetLocation);
		ctx.setTargetPictogramElement(newTargetPictogramElement);
		((ReconnectionContext) ctx).setReconnectType(reconnectType);
		this.feature = getFeatureProvider().getReconnectionFeature(ctx);
	}

	@Override
	public boolean canExecute() {
		if (feature != null) {
			return feature.canReconnect(ctx);
		}
		return false;
	}

	/**
	 * Reconnect the connection to newSource (if setNewSource(...) was invoked
	 * before) or newTarget (if setNewTarget(...) was invoked before).
	 */
	@Override
	public void execute() {
		if (feature != null) {
			// feature.reconnnect(ctx);
			GenericFeatureCommandWithContext genericFeatureCommandWithContext = new GenericFeatureCommandWithContext(
					feature, ctx);
			try {
				CommandExec.getSingleton().executeCommand(genericFeatureCommandWithContext,
						getTransactionalEditingDomain());
			} catch (Exception e) {
				// Wrap in runtime exception (handled outside)
				if (e instanceof RuntimeException) {
					throw (RuntimeException) e;
				} else {
					throw new RuntimeException(e);
				}
			}
		}
		reconnectFinished = true;
	}

	public IFeature getFeature() {
		return feature;
	}

	public IContext getContext() {
		return ctx;
	}

	public void deactivate() {
		if (feature != null && ctx != null && !reconnectFinished) {
			feature.canceledReconnect(ctx);
		}
	}
}