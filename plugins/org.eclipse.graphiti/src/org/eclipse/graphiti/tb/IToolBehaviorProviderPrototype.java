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

@Deprecated
public interface IToolBehaviorProviderPrototype extends IToolBehaviorProvider {

	void postRedo(IExecutionInfo executionInfo);

	void postUndo(IExecutionInfo executionInfo);

	void preRedo(IExecutionInfo executionInfo);

	void preUndo(IExecutionInfo executionInfo);

}
