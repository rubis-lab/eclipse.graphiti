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
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

/**
 * The Interface ITargetConnectionDecoratorContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.8
 */
public interface ITargetConnectionDecoratorContext extends IContext {

	/**
	 * Gets the target connection decorator.
	 * 
	 * @return the target connection decorator where the new pictogram element
	 *         (currently this has to be a shape) has to be inserted
	 */
	ConnectionDecorator getTargetConnectionDecorator();

}
