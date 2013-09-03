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
 *    mwenz - Bug 363796 - Make setting of selection width of connections public
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.ecore;

import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;

/**
 * The Class TestToolBehavior.
 */
public class TestToolBehavior extends DefaultToolBehaviorProvider {

	public static boolean showFlyoutPalette = true;
	public static int lineSelectionWidth = DEFAULT_LINE_SELECTION_WIDTH;
	public static boolean lineSelectionWidthCalled = false;
	public static DiagramScrollingBehavior scrollingBehavior = DiagramScrollingBehavior.SCROLLBARS_ALWAYS_VISIBLE;

	public static void setShowFlyoutPalette(boolean showFlyoutPalette) {
		TestToolBehavior.showFlyoutPalette = showFlyoutPalette;
	}


	/**
	 * Instantiates a new test tool behavior.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public TestToolBehavior(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPad(context);

		ICustomContext cc = getCustomContext(context);
		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
		ICreateConnectionFeature connecFeature = createConnectionFeatures[0];

		ContextButtonEntry button = new ContextButtonEntry(connecFeature, cc);
		button.setText("Connection");
		button.setDescription("Create a new Connection");
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
		return scrollingBehavior;
	}

	@Override
	public String getContributorId() {
		return null; // property sheet not supported
	}
	
	@Override
	public boolean isShowFlyoutPalette() {
		return showFlyoutPalette;
	}

	@Override
	public int getLineSelectionWidth(Polyline polyline) {
		lineSelectionWidthCalled = true;
		return lineSelectionWidth;
	}
}
