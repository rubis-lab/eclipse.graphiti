/*******************************************************************************
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
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern.id;

import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.impl.AddContext;

/**
 * @since 0.10
 */
public class IdAddContext extends AddContext {

	public IdAddContext() {
		super();
	}

	public IdAddContext(IAreaContext context, Object newObject) {
		super(context, newObject);
	}
}
