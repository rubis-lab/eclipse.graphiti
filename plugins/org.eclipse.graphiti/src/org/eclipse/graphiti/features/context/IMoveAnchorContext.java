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
 * Created on 16.11.2005
 *


 */
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;

/**
 * The Interface IMoveAnchorContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IMoveAnchorContext extends IMoveContext {

	/**
	 * Gets the anchor.
	 * 
	 * @return the anchor
	 */
	Anchor getAnchor();

	/**
	 * Gets the target container.
	 * 
	 * @return the target container
	 */
	AnchorContainer getTargetContainer();

	/**
	 * Gets the source container.
	 * 
	 * @return the source container
	 */
	AnchorContainer getSourceContainer();
}