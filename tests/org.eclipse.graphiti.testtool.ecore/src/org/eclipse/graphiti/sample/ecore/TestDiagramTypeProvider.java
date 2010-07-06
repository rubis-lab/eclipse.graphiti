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
package org.eclipse.graphiti.sample.ecore;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Class TestDiagramTypeProvider.
 */
public class TestDiagramTypeProvider extends AbstractDiagramTypeProvider {

	private IToolBehaviorProvider[] availableToolBehaviorProviders;

	/**
	 * Instantiates a new test diagram type provider.
	 */
	public TestDiagramTypeProvider() {
		super();
		setFeatureProvider(new TestFeatureProvider(this));
	}

	/**
	 * definition of a new diagram type.
	 * 
	 * @return the available tool behavior providers
	 */

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (availableToolBehaviorProviders == null) {
			availableToolBehaviorProviders = new IToolBehaviorProvider[] { new TestToolBehaviour(this) };
		}
		return availableToolBehaviorProviders;
	}
}
