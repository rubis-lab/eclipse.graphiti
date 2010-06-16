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
package org.eclipse.graphiti.platform.ga;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PlatformGraphicsAlgorithm;

/**
 * The Class RendererContext.
 */
public class RendererContext implements IRendererContext {
	private PlatformGraphicsAlgorithm platformGraphicsAlgorithm;

	private IDiagramTypeProvider diagramTypeProvider;

	/**
	 * Instantiates a new renderer context.
	 * 
	 * @param pga
	 *            the pga
	 * @param dtp
	 *            the dtp
	 */
	public RendererContext(PlatformGraphicsAlgorithm pga, IDiagramTypeProvider dtp) {
		setPlatformGraphicsAlgorithm(pga);
		setDiagramTypeProvider(dtp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.platform.ga.IRendererContext#getMappingProvider()
	 */
	public IMappingProvider getMappingProvider() {
		return getDiagramTypeProvider().getFeatureProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.platform.ga.IRendererContext#getPlatformGraphicsAlgorithm()
	 */
	public PlatformGraphicsAlgorithm getPlatformGraphicsAlgorithm() {
		return platformGraphicsAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProviderHolder#getDiagramTypeProvider()
	 */
	public IDiagramTypeProvider getDiagramTypeProvider() {
		return diagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.IGraphicsAlgorithmHolder#getGraphicsAlgorithm()
	 */
	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return getPlatformGraphicsAlgorithm();
	}

	/**
	 * @param platformGraphicsAlgorithm
	 *            the platformGraphicsAlgorithm to set
	 */
	private void setPlatformGraphicsAlgorithm(PlatformGraphicsAlgorithm platformGraphicsAlgorithm) {
		this.platformGraphicsAlgorithm = platformGraphicsAlgorithm;
	}

	/**
	 * @param diagramTypeProvider
	 *            the diagramTypeProvider to set
	 */
	private void setDiagramTypeProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
	}
}
