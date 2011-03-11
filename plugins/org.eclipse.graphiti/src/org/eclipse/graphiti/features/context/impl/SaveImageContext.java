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
 *    mwenz - Bug 323155 - Check usage scenarios for DefaultPrintFeature and
 *            DefaultSaveImageFeature
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;

/**
 * The Class SaveImageContext.
 */
public class SaveImageContext extends DefaultContext implements ISaveImageContext {

	/**
	 * Creates a new {@link SaveImageContext}.
	 */
	public SaveImageContext() {
		super();
	}
}
