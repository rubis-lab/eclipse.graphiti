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

/**
 * The Interface IStructureMappingSingle.
 * 
 * @experimental This API is in an experimental state and should be used by
 *               clients, as it not final and can be removed or changed without
 *               prior notice!
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           {@link StructureMappingSingle} instead
 */
public interface IStructureMappingSingle extends IStructureMapping {

	/**
	 * Returns an array of business objects. This array of business objects will
	 * be linked with a pictogram element acting as placeholder in a pattern.
	 * 
	 * @param mainBusinessObject
	 *            this is the main business object of the current pattern
	 * 
	 * @return the business object
	 */
	ILinkCreationInfo getLinkCreationInfo(Object mainBusinessObject);
}
