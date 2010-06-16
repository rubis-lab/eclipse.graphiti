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
package org.eclipse.graphiti.pattern.mapping;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;


/**
 * The Class StructureMappingSingle.
 */
public abstract class StructureMappingSingle extends StructureMapping implements IStructureMappingSingle {

	/**
	 * Instantiates a new structure mapping single.
	 * 
	 * @param dataMapping
	 *            the data mapping
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public StructureMappingSingle(IDataMapping dataMapping, IMappingProvider mappingProvider) {
		super(dataMapping, mappingProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.pattern.mapping.IStructureMappingSingle#getLinkCreationInfo(java.lang.Object)
	 */
	public ILinkCreationInfo getLinkCreationInfo(Object mainBusinessObject) {
		return new LinkCreationInfo(mainBusinessObject);
	}
}
