/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
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
package org.eclipse.graphiti.ui.internal.parts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * A ConnectionEditPart, which model is of the type Connection.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class ConnectionEditPart extends AbstractConnectionEditPart implements IConnectionEditPart {

	private final IAnchorContainerDelegate delegate;

	/**
	 * Creates a new ConnectionEditPart.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param connection
	 *            the connection
	 */
	public ConnectionEditPart(IConfigurationProvider configurationProvider, Connection connection) {
		setModel(connection);
		delegate = new AnchorContainerDelegate(configurationProvider, connection, this);
	}

	/**
	 * Adds this EditPart as an AnchorListener on activation.
	 */
	@Override
	public void activate() {
		super.activate();
		delegate.activate();
	}

	// ======================= overwriteable behaviour ========================

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, getConfigurationProvider().getEditPolicyFactory()
				.createConnectionHighlightEditPolicy());

		installEditPolicy(EditPolicy.CONNECTION_ROLE, getConfigurationProvider().getEditPolicyFactory().createConnectionDeleteEditPolicy(
				getConfigurationProvider()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		IFigure ret = delegate.createFigure();
		return ret;
	}

	/**
	 * Removes this EditPart as an AnchorListener on deactivation.
	 */
	@Override
	public void deactivate() {
		delegate.deactivate();
		super.deactivate();
	}

	/**
	 * Gets the configuration provider.
	 * 
	 * @return The IConfigurationProvider of this EditPart
	 */
	public IConfigurationProvider getConfigurationProvider() {
		return delegate.getConfigurationProvider();
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	protected Connection getConnection() {
		Connection ret = null;
		if (getPictogramElement() instanceof Connection) {
			ret = (Connection) getPictogramElement();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart#
	 * getPictogramElement()
	 */
	public PictogramElement getPictogramElement() {
		return delegate.getPictogramElement();
	}

	/**
	 * Refresh visuals.
	 */

	// ========================= standard behaviour ===========================
	/**
	 * This method is called, whenever the data of the underlying ModelObject
	 * changes. It must update the figures to display the changed data.
	 * Sub-classes will nearly always overwrite this method.
	 * <p>
	 * By default this method takes care to update the labels of the attributes
	 * (if existing) and to update the arrows at the connection-endpoints, so
	 * sub-classes should call super.refreshVisuals().
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		delegate.refreshFigureForEditPart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	@Override
	public List getModelChildren() {
		List list = super.getModelChildren();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections
	 * ()
	 */
	@Override
	public List<Connection> getModelSourceConnections() {
		return super.getModelSourceConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections
	 * ()
	 */
	@Override
	public List<Connection> getModelTargetConnections() {
		return super.getModelTargetConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractConnectionEditPart#getAdapter(java.
	 * lang.Class)
	 */
	@Override
	public Object getAdapter(Class key) {
		Object ret = delegate.getAdapter(key);
		if (ret == null) {
			ret = super.getAdapter(key);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		IFeatureProvider ret = null;
		if (delegate != null) {
			ret = delegate.getFeatureProvider();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart#
	 * getPictogramElementDelegate()
	 */
	public IPictogramElementDelegate getPictogramElementDelegate() {
		return delegate;
	}

	public void forceVisualRefresh() {
		getPictogramElementDelegate().setForceRefresh(true);
		refreshVisuals();
		getPictogramElementDelegate().setForceRefresh(false);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()); //$NON-NLS-1$
	}
}