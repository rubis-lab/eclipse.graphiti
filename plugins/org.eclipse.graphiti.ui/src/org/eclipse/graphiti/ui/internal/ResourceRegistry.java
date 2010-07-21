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
package org.eclipse.graphiti.ui.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.graphiti.util.ColorUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Resource;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ResourceRegistry implements IResourceRegistry {
	private Map<Object, Resource> registry = new HashMap<Object, Resource>();

	public Color getSwtColor(int red, int green, int blue) {
		RGB rgb = new RGB(red, green, blue);
		Resource ret = registry.get(rgb);
		if (ret == null) {
			ret = new Color(null, rgb);
			registry.put(rgb, ret);
			ResourceManager.getResourceManager().manageResource(this, ret);
		}

		Color retColor = null;
		if (ret instanceof Color) {
			retColor = (Color) ret;
		}
		return retColor;
	}

	public void dispose() {
		ResourceManager.getResourceManager().cleanUpResources(this);
		registry.clear();
	}

	public Color getSwtColor(String hexRGBString) {
		return getSwtColor(ColorUtil.getRedFromHex(hexRGBString), ColorUtil.getGreenFromHex(hexRGBString), ColorUtil
				.getBlueFromHex(hexRGBString));
	}
}
