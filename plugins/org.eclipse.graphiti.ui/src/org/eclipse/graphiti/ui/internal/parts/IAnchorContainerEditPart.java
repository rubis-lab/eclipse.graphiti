/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 459386 - Refresh Connection when getDiagramBehavior().refreshRenderingDecorators(PEInstance) is called
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 09.03.2005
 *


 */
package org.eclipse.graphiti.ui.internal.parts;

/**
 * The Interface IAnchorContainerEditPart.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IAnchorContainerEditPart extends IPictogramElementEditPart {

	void refreshDecorators();
}