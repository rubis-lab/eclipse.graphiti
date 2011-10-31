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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;

/**
 * Can be used to pass the information if a popup shall be shown or of a multi
 * delete has been cancelled by the user to the next delete steps. See
 * {@link IDeleteContext#getMultiDeleteInfo()}.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MultiDeleteInfo implements IMultiDeleteInfo {

	private boolean showDialog;

	private boolean deleteCanceled;

	private int number;

	/**
	 * Creates a new instance with the given settings.
	 * 
	 * @param showDialog
	 *            Determines if a "Are you sure" popup will be shown or not
	 * @param deleteCanceled
	 *            Determines if cancellation happened
	 * @param number
	 *            Provides information on the number of objects to delete; the
	 *            number will be shown in the popup if displayed.
	 */
	public MultiDeleteInfo(boolean showDialog, boolean deleteCanceled, int number) {
		super();
		setShowDialog(showDialog);
		setDeleteCanceled(deleteCanceled);
		this.number = number;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public boolean isDeleteCanceled() {
		return deleteCanceled;
	}

	public void setDeleteCanceled(boolean deleteCanceled) {
		this.deleteCanceled = deleteCanceled;
	}

	public int getNumber() {
		return number;
	}
}
