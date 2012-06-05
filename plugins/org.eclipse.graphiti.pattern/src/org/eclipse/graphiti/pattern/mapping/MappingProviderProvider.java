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

/**
 * The Class MappingProviderProvider.
 * 
 * @experimental This API is in an experimental state and should be used by
 *               clients, as it not final and can be removed or changed without
 *               prior notice!
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public abstract class MappingProviderProvider {

	private IMappingProvider mappingProvider;

	/**
	 * Creates a new {@link MappingProviderProvider}.
	 * 
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public MappingProviderProvider(IMappingProvider mappingProvider) {
		setMappingProvider(mappingProvider);
	}

	private void setMappingProvider(IMappingProvider mappingProvider) {
		this.mappingProvider = mappingProvider;
	}

	/**
	 * Gets the mapping provider.
	 * 
	 * @return the mapping provider
	 */
	protected IMappingProvider getMappingProvider() {
		return mappingProvider;
	}

}
