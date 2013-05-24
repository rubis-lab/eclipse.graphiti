/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - initial API implementation

 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.diagramviewer.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

public class DiagramViewerDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public DiagramViewerDiagramTypeProvider() {
		super();
		setFeatureProvider(new DiagramViewerFeatureProvider(this));
	}
}
