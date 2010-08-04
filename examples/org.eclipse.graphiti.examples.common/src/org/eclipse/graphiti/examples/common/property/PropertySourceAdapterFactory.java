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
package org.eclipse.graphiti.examples.common.property;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

public class PropertySourceAdapterFactory implements IAdapterFactory {

	private static final Class[] ADAPTERS = new Class[] { IPropertySource.class };

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		//		if (GFPreferences.getInstance().isGenericPropertySheetActive()) {
		//			if (IPropertySource.class.equals(adapterType)) {
		//				if (adaptableObject instanceof IConfigurationProviderHolder) {
		//					IConfigurationProviderHolder holder = (IConfigurationProviderHolder) adaptableObject;
		//					GraphicsPropertySheetPage page = new GraphicsPropertySheetPage(holder.getConfigurationProvider().getDiagramEditor(),
		//							null);
		//					return page.getPropertySource(adaptableObject);
		//				}
		//			}
		//		}
		return null;
	}

	public Class[] getAdapterList() {
		return ADAPTERS;
	}
}
