/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mgorning - Bug 329517 - state call backs during creation of a connection
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Connection;

/**
 * The Interface ICreateConnection.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ICreateConnection extends ICreateInfo {

	/**
	 * Can create.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canCreate(ICreateConnectionContext context);

	/**
	 * Creates the connection.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return The connection that has been created or <code>null</code> in case
	 *         no valid connection can be returned. Currently this return value
	 *         is not evaluated by the Graphiti framework but it might be used
	 *         in future versions but only for performance optimizations.
	 */
	Connection create(ICreateConnectionContext context);

	/**
	 * Can start connection.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canStartConnection(ICreateConnectionContext context);

	/**
	 * Will called after a connection creation tool from the palette was
	 * selected.<br>
	 * Note: In contrast to the standard feature methods like
	 * {@link #canCreate(ICreateConnectionContext)} and
	 * {@link #create(ICreateConnectionContext)} this method will not be called
	 * in the scope of an EMF transaction. In case you want to modify the model
	 * (EMF domain objects or Graphiti pictogram objects) you need to do that
	 * within a command executed on the editor's command stack to make sure that
	 * the modification happens inside an EMF write transaction. Keep in mind
	 * that any changes you do within such a command will be create a seperate
	 * entry in the undo/redo stack for the editor, which might not be the
	 * desired effect.
	 * 
	 * @since 0.9
	 */
	void startConnecting();

	/**
	 * Will called after a connection creation tool from the palette was
	 * deselected.<br>
	 * Note: In contrast to the standard feature methods like
	 * {@link #canCreate(ICreateConnectionContext)} and
	 * {@link #create(ICreateConnectionContext)} this method will not be called
	 * in the scope of an EMF transaction. In case you want to modify the model
	 * (EMF domain objects or Graphiti pictogram objects) you need to do that
	 * within a command executed on the editor's command stack to make sure that
	 * the modification happens inside an EMF write transaction. Keep in mind
	 * that any changes you do within such a command will be create a seperate
	 * entry in the undo/redo stack for the editor, which might not be the
	 * desired effect.
	 * 
	 * @since 0.9
	 */
	void endConnecting();

	/**
	 * Will called after a connection was successfully attached to an anchor of
	 * a source object.<br>
	 * Note: In contrast to the standard feature methods like
	 * {@link #canCreate(ICreateConnectionContext)} and
	 * {@link #create(ICreateConnectionContext)} this method will not be called
	 * in the scope of an EMF transaction. In case you want to modify the model
	 * (EMF domain objects or Graphiti pictogram objects) you need to do that
	 * within a command executed on the editor's command stack to make sure that
	 * the modification happens inside an EMF write transaction. Keep in mind
	 * that any changes you do within such a command will be create a seperate
	 * entry in the undo/redo stack for the editor, which might not be the
	 * desired effect.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @since 0.9
	 */
	void attachedToSource(ICreateConnectionContext context);

	/**
	 * Will called if the connection creation process was canceled after the
	 * successful attachment of the connection to an anchor of a source object.
	 * E.g. user pressed ESC, user clicked on an invalid target, focus was lost,
	 * ...<br>
	 * Note: In contrast to the standard feature methods like
	 * {@link #canCreate(ICreateConnectionContext)} and
	 * {@link #create(ICreateConnectionContext)} this method will not be called
	 * in the scope of an EMF transaction. In case you want to modify the model
	 * (EMF domain objects or Graphiti pictogram objects) you need to do that
	 * within a command executed on the editor's command stack to make sure that
	 * the modification happens inside an EMF write transaction. Keep in mind
	 * that any changes you do within such a command will be create a seperate
	 * entry in the undo/redo stack for the editor, which might not be the
	 * desired effect.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @since 0.9
	 */
	void canceledAttaching(ICreateConnectionContext context);
}
