/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern.mapping;

/**
 * The Class LinkCreationInfo.
 * 
 * @experimental This API is in an experimental state and should be used by
 *               clients, as it not final and can be removed or changed without
 *               prior notice!
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class LinkCreationInfo implements ILinkCreationInfo {

	private Object[] businessObjects;

	private String property;

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObject
	 *            the business object
	 */
	public LinkCreationInfo(Object businessObject) {
		this(new Object[] { businessObject });
	}

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObjects
	 *            the business objects
	 */
	public LinkCreationInfo(Object[] businessObjects) {
		this(businessObjects, null);
	}

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObjects
	 *            the business objects
	 * @param property
	 *            the property
	 */
	public LinkCreationInfo(Object[] businessObjects, String property) {
		super();
		this.businessObjects = businessObjects;
		this.property = property;
	}

	public Object[] getBusinessObjects() {
		return businessObjects;
	}

	public String getProperty() {
		return property;
	}

}
