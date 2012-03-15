/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Torkild U. Resheim - Allow double-click handling, bug 340708
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 341898 - Support for AdvancedPropertySheet
 *    Benjamin Schmeling - mwenz - Bug 367483 - Support composite connections
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DoubleClickContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.platform.GraphitiConnectionEditPart;

/**
 * A ConnectionEditPart, which model is of the type Connection.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class ConnectionEditPart extends GraphitiConnectionEditPart implements IConnectionEditPart,
		NodeEditPart {

	private final IAnchorContainerDelegate delegate;
	private EditPart contextParent;

	/**
	 * Creates a new ConnectionEditPart.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param connection
	 *            the connection
	 */
	public ConnectionEditPart(IConfigurationProvider configurationProvider, Connection connection,
			EditPart contextParent) {
		setModel(connection);
		delegate = new AnchorContainerDelegate(configurationProvider, connection, this);
		this.contextParent = contextParent;
	}

	/**
	 * Adds this EditPart as an AnchorListener on activation.
	 */
	@Override
	public void activate() {
		super.activate();
		delegate.activate();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, getConfigurationProvider().getEditPolicyFactory()
				.createConnectionHighlightEditPolicy());

		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				getConfigurationProvider().getEditPolicyFactory().createConnectionDeleteEditPolicy(getConfigurationProvider()));

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, getConfigurationProvider().getEditPolicyFactory().createConnectionEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, getConfigurationProvider().getEditPolicyFactory()
				.createShapeHighlightEditPolicy());
	}

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

	@Override
	public List<PictogramElement> getModelChildren() {
		return new ArrayList<PictogramElement>();
	}

	@Override
	public List<Connection> getModelSourceConnections() {
		return new ArrayList<Connection>();
	}

	@Override
	public List<Connection> getModelTargetConnections() {
		return new ArrayList<Connection>();
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		if (contextParent instanceof CompositeConnectionEditPart) {
			// This ConnectionEditPart is a child of a CompositeConnection
			if (request instanceof SelectionRequest) {
				// Store the actually selected sub-connection at the composite
				// (for adding the info to custom context later)
				((CompositeConnectionEditPart) contextParent).setOriginallySelectedChild(this);
			}
			return contextParent;
		} else {
			return super.getTargetEditPart(request);
		}
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		Object ret = delegate.getAdapter(key);
		if (ret == null) {
			ret = super.getAdapter(key);
		}

		return ret;
	}

	public IFeatureProvider getFeatureProvider() {
		IFeatureProvider ret = null;
		if (delegate != null) {
			ret = delegate.getFeatureProvider();
		}
		return ret;
	}

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

	public ConnectionAnchor getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart connection) {
		return null;
	}

	public ConnectionAnchor getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart connection) {
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		if (request instanceof CreateConnectionRequest) {
			CreateConnectionRequest r = (CreateConnectionRequest) request;
			return new XYAnchor(r.getLocation());
		}
		return null;
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return null;
	}

	/**
	 * This method tries to perform a direct-editing with the given request (see
	 * getLabels()). Additionaly it tries to forward certain requests to this
	 * EditPart (e.g. RequestConstants.REQ_OPEN). If this is not possbile, it
	 * forwards the request to super.performRequest(request).
	 * 
	 * @param request
	 *            the request
	 * @see org.eclipse.gef.EditPart#performRequest(Request)
	 */

	@Override
	public void performRequest(Request request) {

		Connection shape = (Connection) getModel();

		if (request.getType().equals(REQ_OPEN)) {

			DoubleClickContext dcc = new DoubleClickContext(getPictogramElement(), shape, shape.getGraphicsAlgorithm());
			IToolBehaviorProvider currentToolBehaviorProvider = getConfigurationProvider().getDiagramTypeProvider()
					.getCurrentToolBehaviorProvider();

			IFeature doubleClickFeature = currentToolBehaviorProvider.getDoubleClickFeature(dcc);

			if (doubleClickFeature != null && doubleClickFeature.canExecute(dcc)) {
				GenericFeatureCommandWithContext commandWithContext = new GenericFeatureCommandWithContext(doubleClickFeature, dcc);
				DiagramEditor diagramEditor = getConfigurationProvider().getDiagramEditor();
				CommandStack commandStack = diagramEditor.getEditDomain().getCommandStack();
				commandStack.execute(new GefCommandWrapper(commandWithContext, diagramEditor.getEditingDomain()));
			}
		}

		super.performRequest(request);
	}

}
