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
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 339525 - Enrich paste context with location information
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 17.11.2005
 */
package org.eclipse.graphiti.features.context;

/**
 * The Interface IPasteContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPasteContext extends IPictogramElementsContext, ILocationContext {
}