/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern.mapping;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;


/**
 * The Class StructureMappingSingle.
 * 
 * @experimental This API is in an experimental state and should be used by
 *               clients, as it not final and can be removed or changed without
 *               prior notice!
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public abstract class StructureMappingSingle extends StructureMapping implements IStructureMappingSingle {

	/**
	 * Creates a new {@link StructureMappingSingle}.
	 * 
	 * @param dataMapping
	 *            the data mapping
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public StructureMappingSingle(IDataMapping dataMapping, IMappingProvider mappingProvider) {
		super(dataMapping, mappingProvider);
	}

	public ILinkCreationInfo getLinkCreationInfo(Object mainBusinessObject) {
		return new LinkCreationInfo(mainBusinessObject);
	}
}
