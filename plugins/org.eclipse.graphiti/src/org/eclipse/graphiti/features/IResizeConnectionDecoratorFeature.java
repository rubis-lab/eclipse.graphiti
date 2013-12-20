/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation (Bug 424458)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.features.impl.DefaultMoveConnectionDecoratorFeature;

/**
 * The Interface IResizeConnectionDecoratorFeature.
 * 
 * Resizing shapes means to change their coordinates or/and their parent.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultMoveConnectionDecoratorFeature} instead.
 * @since 0.11
 */
public interface IResizeConnectionDecoratorFeature extends IResizeFeature {

	/**
	 * Can resize connection decorator.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canResizeConnectionDecorator(IResizeConnectionDecoratorContext context);

	/**
	 * Resize connection decorator.
	 * 
	 * @param context
	 *            the context
	 */
	void resizeConnectionDecorator(IResizeConnectionDecoratorContext context);
}