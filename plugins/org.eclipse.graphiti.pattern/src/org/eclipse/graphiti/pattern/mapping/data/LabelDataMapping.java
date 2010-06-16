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
package org.eclipse.graphiti.pattern.mapping.data;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.mm.links.PictogramLink;

/**
 * The Class LabelDataMapping.
 */
public abstract class LabelDataMapping extends TextDataMapping implements ILabelDataMapping {

	/**
	 * Instantiates a new label data mapping.
	 * 
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public LabelDataMapping(IMappingProvider mappingProvider) {
		super(mappingProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.pattern.mapping.IImageDataMapping#getImageId(org.eclipse.graphiti.mm.links.PictogramLink)
	 */
	public String getImageId(PictogramLink link) {
		return null;
	}

}
