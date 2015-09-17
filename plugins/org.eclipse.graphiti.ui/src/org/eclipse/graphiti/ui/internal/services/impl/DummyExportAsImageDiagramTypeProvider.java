/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 Eclipse Modeling Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Laurent Le Moux, mwenz - Bug 423018 - Direct Graphiti diagram exporter
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class DummyExportAsImageDiagramTypeProvider extends AbstractDiagramTypeProvider {
	@Override
	public IFeatureProvider getFeatureProvider() {
		// To avoid NPE...
		return new DefaultFeatureProvider(this);
	}
}