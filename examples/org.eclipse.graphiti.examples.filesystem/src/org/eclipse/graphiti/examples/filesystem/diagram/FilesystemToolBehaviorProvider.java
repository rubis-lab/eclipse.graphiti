/**
 * <copyright>
 * 
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.filesystem.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ILocationInfo;
import org.eclipse.graphiti.util.LocationInfo;

public class FilesystemToolBehaviorProvider extends DefaultToolBehaviorProvider implements IToolBehaviorProvider {

	public FilesystemToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ILocationInfo getLocationInfo(PictogramElement pe, ILocationInfo locationInfo) {
		Object domainObject = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		if (domainObject instanceof Folder && locationInfo != null) {
			return new LocationInfo((Shape) pe, locationInfo.getGraphicsAlgorithm());
		}
		return super.getLocationInfo(pe, locationInfo);
	}
}
