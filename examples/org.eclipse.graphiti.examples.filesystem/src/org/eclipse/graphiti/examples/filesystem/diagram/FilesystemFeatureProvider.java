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
 *    cbrand - Bug 382928 - Introduce factory method(s) for easier gradient creation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.filesystem.diagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.filesystem.features.AddContainmentConnectionFeature;
import org.eclipse.graphiti.examples.filesystem.features.CreateContainmentConnectionFeature;
import org.eclipse.graphiti.examples.filesystem.features.CreateInnerFileFeature;
import org.eclipse.graphiti.examples.filesystem.features.DeleteInnerFileFeature;
import org.eclipse.graphiti.examples.filesystem.features.GradientColorFeature;
import org.eclipse.graphiti.examples.filesystem.patterns.FilePattern;
import org.eclipse.graphiti.examples.filesystem.patterns.FilesystemPattern;
import org.eclipse.graphiti.examples.filesystem.patterns.FolderPattern;
import org.eclipse.graphiti.examples.filesystem.ui.FilesystemPredefinedColoredAreas;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;

public class FilesystemFeatureProvider extends DefaultFeatureProviderWithPatterns {
	static List<String> ALL_GRADIENT_IDS = Arrays.asList(FilesystemPredefinedColoredAreas.GREEN_WHITE_ID,
			FilesystemPredefinedColoredAreas.RED_WHITE_ID, IPredefinedRenderingStyle.BLUE_WHITE_ID,
			IPredefinedRenderingStyle.BLUE_WHITE_GLOSS_ID);

	public FilesystemFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		addPattern(new FilesystemPattern());
		addPattern(new FilePattern());
		addPattern(new FolderPattern());
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

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		ICustomFeature[] ret = super.getCustomFeatures(context);

		// Add features to change gradient
		List<ICustomFeature> retList = new ArrayList<ICustomFeature>();
		for (String gid : ALL_GRADIENT_IDS) {
			retList.add(new GradientColorFeature(this, gid));
		}

		// Add create/delete features for files inside folders
		retList.add(new CreateInnerFileFeature(this));
		retList.add(new DeleteInnerFileFeature(this));

		ret = retList.toArray(ret);
		return ret;
	}
}
