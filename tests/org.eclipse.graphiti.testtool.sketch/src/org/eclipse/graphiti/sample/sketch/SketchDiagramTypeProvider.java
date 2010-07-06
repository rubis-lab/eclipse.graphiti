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
package org.eclipse.graphiti.sample.sketch;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.features.ConfigurableFeatureProviderWrapper;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Class SketchDiagramTypeProvider.
 */
public class SketchDiagramTypeProvider extends AbstractDiagramTypeProvider {

	private IToolBehaviorProvider[] availableToolBehaviorProviders;

	private IGraphicsAlgorithmRendererFactory factory;

	/**
	 * Instantiates a new sketch diagram type provider.
	 */
	public SketchDiagramTypeProvider() {
		super();
		setFeatureProvider(new ConfigurableFeatureProviderWrapper(new SketchFeatureProvider(this)));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (availableToolBehaviorProviders == null) {
			availableToolBehaviorProviders = new IToolBehaviorProvider[] { new SketchToolBehaviour(this),
					new SketchViewerModeToolBehavior(this) };
		}
		return availableToolBehaviorProviders;
	}

	@Override
	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		if (factory == null) {
			factory = new SketchGraphicsAlgorithmRendererFactory();
		}
		return factory;
	}
}
