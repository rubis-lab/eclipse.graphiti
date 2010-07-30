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
package org.eclipse.graphiti.testtool.ecore;

import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;

/**
 * The Class TestToolBehaviour.
 */
public class TestToolBehaviour extends DefaultToolBehaviorProvider {

	/**
	 * Instantiates a new test tool behaviour.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public TestToolBehaviour(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	protected boolean isContextMenuApplicable(IFeature feature) {
		// consider all custom features as candidates for the context menu
		boolean ret = (feature instanceof ICustomFeature);
		return ret;
	}

	@Override
	public IContextButtonPadData getContextButtonPadData(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPadData(context);

		ICustomContext cc = getCustomContext(context);
		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
		ICreateConnectionFeature connecFeature = createConnectionFeatures[0];

		ContextButtonEntry button = new ContextButtonEntry(connecFeature, cc);
		button.setText("Connection");
		button.setDescription("Create a new Conenction");
		button.addDragAndDropFeature(connecFeature);
		data.getDomainSpecificContextButtons().add(button);

		return data;
	}

	private ICustomContext getCustomContext(IPictogramElementContext context) {
		CustomContext result = new CustomContext(new PictogramElement[] { context.getPictogramElement() });
		GraphicsAlgorithm ga = context.getPictogramElement().getGraphicsAlgorithm();
		result.setX(ga.getX());
		result.setY(ga.getY() + 2 * 50);
		return result;
	}

	@Override
	public DiagramScrollingBehavior getDiagramScrollingBehavior() {
		return DiagramScrollingBehavior.SCROLLBARS_ALWAYS_VISIBLE;
	}

	@Override
	public String getContributorId() {
		return null; // property sheet not supported
	}
}
