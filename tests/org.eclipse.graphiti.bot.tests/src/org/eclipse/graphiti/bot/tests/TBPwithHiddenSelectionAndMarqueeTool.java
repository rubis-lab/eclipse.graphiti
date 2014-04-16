/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 341224: Allow to hide the selection and marquee tools in the palette
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;

public class TBPwithHiddenSelectionAndMarqueeTool extends DefaultToolBehaviorProvider {

	public TBPwithHiddenSelectionAndMarqueeTool(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public boolean isShowSelectionTool() {
		return false;
	}

	@Override
	public boolean isShowMarqueeTool() {
		return false;
	}
}
