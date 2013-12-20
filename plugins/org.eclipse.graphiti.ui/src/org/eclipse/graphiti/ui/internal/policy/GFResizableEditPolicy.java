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
 *    jpasch - BUG 341180: Graphiti fails to handle resize after custom feature addition in the tutorial
 *    Patch 185019 from Bug 332360 contributed by Volker Wegert
 *    mwenz - Bug 346487 - No selection feedback for non-resizable diagram nodes
 *    mgorning - Bug 363186 - Allow modification of selection and hover state also for anchors
 *    mwenz - Bug 373298 - Possible Resource leaks in Graphiti
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.policy;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.IResizeConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IResizeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IResizeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.util.draw2d.GFHandleHelper;
import org.eclipse.graphiti.ui.internal.util.draw2d.TransparentGhostFigure;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFResizableEditPolicy extends ResizableEditPolicy {
	private GFEditPolicyDelegate delegate;
	private IResizeContext resizeContext;

	public GFResizableEditPolicy(IConfigurationProviderInternal cfgProvider) {
		setDelegate(new GFEditPolicyDelegate(cfgProvider));
	}

	public GFResizableEditPolicy(IConfigurationProviderInternal configurationProvider, IResizeContext resizeContext) {
		this(configurationProvider);
		setResizeContext(resizeContext);
	}

	@Override
	protected IFigure createDragSourceFeedbackFigure() {
		IFigure feedback = new TransparentGhostFigure(70);
		addFeedback(feedback);
		return feedback;
	}

	@Override
	protected List<?> createSelectionHandles() {
		boolean resizeAllowed = false;
		if (getResizeContext() instanceof IResizeShapeContext) {
			resizeAllowed = !(getResizeFeature() == null || !((IResizeShapeFeature) getResizeFeature())
					.canResizeShape(
					(IResizeShapeContext) getResizeContext()));
		} else if (getResizeContext() instanceof IResizeConnectionDecoratorContext) {
			resizeAllowed = !(getResizeFeature() == null || !((IResizeConnectionDecoratorFeature) getResizeFeature())
					.canResizeConnectionDecorator((IResizeConnectionDecoratorContext) getResizeContext()));
		}

		GraphicalEditPart owner = (GraphicalEditPart) getHost();
		List<?> list = GFHandleHelper.createShapeHandles(owner, getConfigurationProvider(), getResizeDirections(),
				isDragAllowed(), resizeAllowed);
		return list;
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		/* dispose ghosts */
		@SuppressWarnings("unchecked")
		List<IFigure> children = getFeedbackLayer().getChildren();
		for (IFigure child : children) {
			if (child instanceof TransparentGhostFigure) {
				((TransparentGhostFigure) child).dispose();
			}
		}
		super.eraseSourceFeedback(request);
	}

	protected IConfigurationProviderInternal getConfigurationProvider() {
		return getDelegate().getConfigurationProvider();
	}

	private GFEditPolicyDelegate getDelegate() {
		return this.delegate;
	}

	@Override
	public int getResizeDirections() {
		int ret = 0;
		if (!(getResizeContext() == null)) {
			IResizeFeature resizeFeature = getResizeFeature();
			if (resizeFeature != null) {
				IResizeConfiguration resizeConfiguration = resizeFeature
						.getResizeConfiguration(getResizeContext());
				if (resizeConfiguration.isHorizontalResizeAllowed()) {
					ret = ret | PositionConstants.EAST_WEST;
				}
				if (resizeConfiguration.isVerticalResizeAllowed()) {
					ret = ret | PositionConstants.NORTH_SOUTH;
				}
			}
		}
		return ret;
	}

	private IResizeContext getResizeContext() {
		return this.resizeContext;
	}

	private IResizeFeature getResizeFeature() {
		if (getResizeContext() instanceof IResizeShapeContext) {
			return getConfigurationProvider().getFeatureProvider().getResizeShapeFeature(
					(IResizeShapeContext) getResizeContext());
		} else if (getResizeContext() instanceof IResizeConnectionDecoratorContext) {
			return getConfigurationProvider().getFeatureProvider().getResizeConnectionDecoratorFeature(
					(IResizeConnectionDecoratorContext) getResizeContext());
		} else {
			return null;
		}
	}

	private void setDelegate(GFEditPolicyDelegate delegate) {
		this.delegate = delegate;
	}

	private void setResizeContext(IResizeContext resizeContext) {
		this.resizeContext = resizeContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#hideSelection()
	 */
	@Override
	protected void hideSelection() {
		getDelegate().hideSelection(getHostFigure());
		removeSelectionHandles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionEditPolicy#showPrimarySelection()
	 */
	@Override
	protected void showPrimarySelection() {
		if (GraphitiInternal.getEmfService().isObjectAlive((PictogramElement) getHost().getModel())) {
			getDelegate().showPrimarySelection(getHostFigure());
			addSelectionHandles();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		getDelegate().showSelection(getHostFigure());
		addSelectionHandles();
	}
}
