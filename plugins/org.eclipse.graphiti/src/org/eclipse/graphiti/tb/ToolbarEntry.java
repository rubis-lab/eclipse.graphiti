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
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Class ToolbarEntry.
 */
public class ToolbarEntry extends AbstractContextEntry implements IToolbarEntry {

	/**
	 * Creates a new {@link ToolbarEntry}.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public ToolbarEntry(IFeature feature, IContext context) {
		super(feature, context);
	}

}
