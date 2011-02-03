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

import org.eclipse.graphiti.features.context.IMultiDeleteInfo;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MultiDeleteInfo implements IMultiDeleteInfo {

	private boolean showDialog;

	private boolean deleteCanceled;

	private int number;

	/**
	 * @param showDialog
	 * @param deleteCanceled
	 * @param number
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
