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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.ISplitConnectionContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class SplitConnectionContext.
 */
public class SplitConnectionContext extends DefaultContext implements ISplitConnectionContext {

	private Connection connection;

	private Shape shape;

	/**
	 * Instantiates a new split connection context.
	 * 
	 * @param connection
	 *            the connection
	 * @param shape
	 *            the shape
	 */
	public SplitConnectionContext(Connection connection, Shape shape) {
		super();
		this.connection = connection;
		this.shape = shape;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.context.ISplitConnectionContext#getConnection()
	 */
	public Connection getConnection() {
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.context.ISplitConnectionContext#getShape()
	 */
	public Shape getShape() {
		return shape;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " connection: " + getConnection() + " shape: " + getShape(); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
