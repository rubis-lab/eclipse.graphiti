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
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature and
 *                         called features via editor command stack to check it
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class DTPwithAutoUpdateAtStartup extends AbstractDiagramTypeProvider {

	public DTPwithAutoUpdateAtStartup() {
		super();
		setFeatureProvider(new DefaultFeatureProvider(this));
	}

	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}
}
