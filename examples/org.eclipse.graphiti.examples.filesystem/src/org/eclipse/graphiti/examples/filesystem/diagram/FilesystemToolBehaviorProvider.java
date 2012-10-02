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
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.filesystem.FilesystemImageProvider;
import org.eclipse.graphiti.examples.filesystem.features.CreateInnerFileFeature;
import org.eclipse.graphiti.examples.filesystem.features.DeleteInnerFileFeature;
import org.eclipse.graphiti.examples.filesystem.features.GradientColorFeature;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
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

	@Override
	public IContextMenuEntry[] getContextMenu(ICustomContext context) {
		IContextMenuEntry[] ret = NO_CONTEXT_MENU_ENTRIES;
		List<IContextMenuEntry> retList = new ArrayList<IContextMenuEntry>();
		// custom features
		ICustomContext customContext = context;
		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(customContext);

		// Gradient colors submenu
		ContextMenuEntry changeGradientColorEntry = null;

		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			ContextMenuEntry contextMenuEntry = new ContextMenuEntry(customFeature, context);
			if (customFeature instanceof GradientColorFeature) {
				if (changeGradientColorEntry == null) {
					changeGradientColorEntry = new ContextMenuEntry(null, null);
					changeGradientColorEntry.setSubmenu(true);
					changeGradientColorEntry.setText("Gradient Color");
					changeGradientColorEntry.setDescription("Change Gradient Color");
					retList.add(changeGradientColorEntry);
				}
				changeGradientColorEntry.add(contextMenuEntry);
			} else if (customFeature instanceof CreateInnerFileFeature) {
				retList.add(contextMenuEntry);
			} else if (customFeature instanceof DeleteInnerFileFeature) {
				retList.add(contextMenuEntry);
			}
		}
		return retList.toArray(ret);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();

		CustomContext customContext = new CustomContext(new PictogramElement[] { pe });
		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(customContext);
		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			if (customFeature instanceof CreateInnerFileFeature) {
				IContextButtonEntry button = null;
				button = new ContextButtonEntry(customFeature, customContext);
				button.setText(customFeature.getName());
				button.setDescription(customFeature.getDescription());
				button.setIconId(FilesystemImageProvider.IMG_CREATE_FILE);
				data.getDomainSpecificContextButtons().add(button);
			} else if (customFeature instanceof DeleteInnerFileFeature) {
				IContextButtonEntry button = null;
				button = new ContextButtonEntry(customFeature, customContext);
				button.setText(customFeature.getName());
				button.setDescription(customFeature.getDescription());
				button.setIconId(FilesystemImageProvider.IMG_DELETE_FILE);
				data.getDomainSpecificContextButtons().add(button);
			}
		}

		return data;
	}
}
