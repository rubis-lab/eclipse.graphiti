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
import org.eclipse.graphiti.examples.filesystem.features.AddContainmentConnectionFeature;
import org.eclipse.graphiti.examples.filesystem.features.CreateContainmentConnectionFeature;
import org.eclipse.graphiti.examples.filesystem.patterns.FilePattern;
import org.eclipse.graphiti.examples.filesystem.patterns.FolderPattern;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;

public class FilesystemFeatureProvider extends DefaultFeatureProviderWithPatterns {

	public FilesystemFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		addPattern(new FolderPattern());
		addPattern(new FilePattern());
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateContainmentConnectionFeature(this) };
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context instanceof IAddConnectionContext) {
			return new AddContainmentConnectionFeature(this);
		}
		return super.getAddFeature(context);
	}
}
