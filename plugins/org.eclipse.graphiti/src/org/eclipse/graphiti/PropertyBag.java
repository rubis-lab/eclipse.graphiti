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
package org.eclipse.graphiti;

import java.util.HashMap;

public class PropertyBag implements IPropertyBag {
	private HashMap<Object, Object> propertyMap;

	/*
	 * @see org.eclipse.graphiti.IPropertyBag#getProperty(java.lang.Object)
	 */
	@Override
	public Object getProperty(Object key) {
		return getPropertyMap().get(key);
	}

	/*
	 * @see org.eclipse.graphiti.IPropertyBag#putProperty(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public Object putProperty(Object key, Object value) {
		return getPropertyMap().put(key, value);
	}

	private HashMap<Object, Object> getPropertyMap() {
		if (propertyMap == null) {
			propertyMap = new HashMap<Object, Object>();
		}
		return propertyMap;
	}

}
