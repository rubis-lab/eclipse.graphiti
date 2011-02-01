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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.context;

/**
 * The Interface IMultiDeleteInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IMultiDeleteInfo {

	/**
	 * Returns whether a dialog can be shown or should be suppressed.
	 * 
	 * @return TRUE, a dialog can be shown
	 */
	boolean isShowDialog();

	/**
	 * Sets whether a dialog can be shown or should be suppressed.
	 * 
	 * @param showDialog
	 *            TRUE, a dialog can be shown
	 */
	void setShowDialog(boolean showDialog);

	/**
	 * Returns the state whether delete has been canceled or not.
	 * 
	 * @return TRUE if delete canceled; FALSE in all other cases
	 */
	boolean isDeleteCanceled();

	/**
	 * Sets the state whether delete has been canceled or not.
	 * 
	 * @param deleteCanceled
	 *            TRUE, if delete canceled; FALSE in all other cases
	 */
	void setDeleteCanceled(boolean deleteCanceled);

}
