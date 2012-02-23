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

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IDecorator. Decorators are a means to modify the visualization
 * of shapes in a diagram without modifying the dirty state of the editor. This
 * is done by the Graphiti framework by simply applying the decorators to a
 * shape after it has been drawn on the screen as defined in the pictograms
 * model.
 * <p>
 * The list of decorators that shall be applied to a {@link PictogramElement} is
 * queried from the
 * {@link IToolBehaviorProvider#getDecorators(PictogramElement)} method when a
 * shape is drawn or updated on the diagram by the Graphiti framework.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IDecorator {

	/**
	 * Gets the message to be shown as a tooltip for the decorator. This can
	 * e.g. be an error message stating why the shape is marked.
	 * 
	 * @return the message
	 */
	String getMessage();

	/**
	 * Sets the message to be shown as a tooltip for the decorator. This can
	 * e.g. be an error message stating why the shape is marked.
	 * 
	 * @param message
	 *            the new message
	 */
	void setMessage(String message);
}
