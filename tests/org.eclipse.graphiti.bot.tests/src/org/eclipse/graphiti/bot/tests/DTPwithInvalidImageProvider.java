/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 352709 - invalid image provider id crashes diagram editor
 *    mwenz - Bug 421626 - Moved from ui.test to bot.test plugin
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

public class DTPwithInvalidImageProvider extends AbstractDiagramTypeProvider {

	public DTPwithInvalidImageProvider() {
		super();
	}
}
