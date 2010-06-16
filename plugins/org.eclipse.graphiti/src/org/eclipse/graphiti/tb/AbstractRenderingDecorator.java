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
package org.eclipse.graphiti.tb;

/**
 * The Class AbstractRenderingDecorator.
 */
public abstract class AbstractRenderingDecorator implements IRenderingDecorator {

	private String message;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IRenderingDecorator#getMessage()
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IRenderingDecorator#setMessage(java.lang.String)
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
