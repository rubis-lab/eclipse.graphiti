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
 *    Felix Velasco (mwenz) - Bug 349416 - Support drag&drop operations on FixPointAnchors
 *                                         the same way as for BoxRelativeAnchors
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.parts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.features.context.impl.base.PictogramElementContext;
import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.util.draw2d.GFChopboxAnchor;
import org.eclipse.graphiti.ui.internal.util.gef.MultiCreationFactory;

/**
 * EditPart for an {@link AdvancedAnchor}. Such an anchor can be positioned
 * either at a fixed point of the container ({@link FixPointAnchor}) or relative
 * to a container ({@link BoxRelativeAnchor}). For the graphical notation see
 * {@link AdvancedAnchor} and its subclasses {@link FixPointAnchor} adn
 * {@link BoxRelativeAnchor}. .
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class AdvancedAnchorEditPart extends AbstractGraphicalEditPart implements IAnchorEditPart, NodeEditPart {

	private ConnectionAnchor connectionAnchor;

	/**
	 * The delegate.
	 */
	protected IPictogramElementDelegate delegate;

	/**
	 * Instantiates a new anchor edit part.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param anchor
	 *            the anchor
	 */
	public AdvancedAnchorEditPart(IConfigurationProvider configurationProvider, AdvancedAnchor anchor) {
		setModel(anchor);
		delegate = new PictogramElementDelegate(configurationProvider, anchor, this);
	}

	@Override
	public void activate() {
		super.activate();
		delegate.activate();
	}

	@Override
	protected IFigure createFigure() {
		IFigure figure = delegate.createFigure();
		if (getModel() instanceof AdvancedAnchor) {
			connectionAnchor = new GFChopboxAnchor(figure, (AdvancedAnchor) getModel());
		} else {
			connectionAnchor = new GFChopboxAnchor(figure);
		}

		return figure;
	}

	@Override
	public void deactivate() {
		delegate.deactivate();
		super.deactivate();
	}

	public IConfigurationProvider getConfigurationProvider() throws IllegalStateException {
		return delegate.getConfigurationProvider();
	}

	@Override
	public List<Connection> getModelSourceConnections() {

		Anchor anchor = (Anchor) getModel();
		List<Connection> connections = new ArrayList<Connection>();

		if (anchor != null) {
			connections.addAll(anchor.getOutgoingConnections());
		}

		return connections;
	}

	@Override
	public List<Connection> getModelTargetConnections() {

		Anchor anchor = (Anchor) getModel();
		List<Connection> connections = new ArrayList<Connection>();

		if (anchor != null) {
			connections.addAll(anchor.getIncomingConnections());
		}

		return connections;
	}

	public PictogramElement getPictogramElement() {
		return delegate.getPictogramElement();
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return connectionAnchor;
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return connectionAnchor;
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return connectionAnchor;
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return connectionAnchor;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		delegate.refreshFigureForEditPart();
		getFigure().setVisible(getPictogramElement().isVisible());
	}

	@Override
	protected void refreshChildren() {
		super.refreshChildren();

		// refresh edit parts for child PEs as well
		delegate.refreshEditPartsForModelChildrenAndSourceConnections(this);
	}

	@Override
	public List<PictogramElement> getModelChildren() {
		return new ArrayList<PictogramElement>();
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

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, getConfigurationProvider().getEditPolicyFactory().createModelObjectDeleteEditPolicy(
				getConfigurationProvider()));
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, getConfigurationProvider().getEditPolicyFactory().createConnectionEditPolicy());
	}

	/**
	 * Allow the initiation of a connection creation by drag&drop from an anchor
	 * (FixPoint and BoxReleative)
	 **/
	@Override
	public DragTracker getDragTracker(Request request) {
	
		PictogramElementContext context = new PictogramElementContext(getPictogramElement());
		IFeature[] dragAndDropFeatures = getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider().getDragAndDropFeatures(
				context);
		if (dragAndDropFeatures == null || dragAndDropFeatures.length == 0)
			return super.getDragTracker(request);
	
		ConnectionDragCreationTool tool = new ConnectionDragCreationTool() {
				/**
				 * changed order: feedback gets deleted after command is executed
				 * (popup!)
				 */
				@Override
				protected boolean handleCreateConnection() {
					Command endCommand = getCommand();
					setCurrentCommand(endCommand);
					executeCurrentCommand();
					eraseSourceFeedback();
	
					return true;
				}
			};
			tool.setFactory(new MultiCreationFactory(Arrays.asList(dragAndDropFeatures)));
		return tool;
	}
}