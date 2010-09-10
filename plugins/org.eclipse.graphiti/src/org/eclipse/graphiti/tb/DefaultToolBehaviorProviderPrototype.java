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
/*
 * Created on 18.09.2010
 */
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;

@Deprecated
public class DefaultToolBehaviorProviderPrototype extends DefaultToolBehaviorProvider implements IToolBehaviorProviderPrototype {

	public DefaultToolBehaviorProviderPrototype(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public void postRedo(IExecutionInfo executionInfo) {
	}

	@Override
	public void postUndo(IExecutionInfo executionInfo) {
	}

	@Override
	public void preRedo(IExecutionInfo executionInfo) {
	}

	@Override
	public void preUndo(IExecutionInfo executionInfo) {
	}

}
