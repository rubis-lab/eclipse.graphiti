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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

/**
 * The Class ResizeShapeContext.
 * 
 * @since 0.11
 */
public class ResizeConnectionDecoratorContext extends ResizeContext implements IResizeConnectionDecoratorContext {

	private ConnectionDecorator connectionDecorator;
	private boolean executeAllowed;

	/**
	 * Creates a new {@link ResizeConnectionDecoratorContext}.
	 * 
	 * @param shape
	 *            the shape
	 */
	public ResizeConnectionDecoratorContext(ConnectionDecorator connectionDecorator) {
		super();
		setConnectionDecorator(connectionDecorator);
	}

	/**
	 * Gets the connection decorator.
	 * 
	 * @return Returns the connection decorator.
	 */
	@Override
	public ConnectionDecorator getConnectionDecorator() {
		return connectionDecorator;
	}


	/**
	 * @param shape
	 *            The shape to set.
	 */
	private void setConnectionDecorator(ConnectionDecorator connectionDecorator) {
		this.connectionDecorator = connectionDecorator;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " connection decorator: " + getConnectionDecorator(); //$NON-NLS-1$
	}

	@Override
	public boolean isExecuteAllowed() {
		return this.executeAllowed;
	}

	/**
	 * Sets the execute allowed.
	 * 
	 * @param executeAllowed
	 *            the new execute allowed
	 */
	protected void setExecuteAllowed(boolean executeAllowed) {
		this.executeAllowed = executeAllowed;
	}
}
