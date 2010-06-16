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
package org.eclipse.graphiti.internal.datatypes.impl;

import org.eclipse.graphiti.datatypes.IInsets;

/**
 * The Class InsetsImpl.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class InsetsImpl implements IInsets {

	private int left;

	private int right;

	private int top;

	private int bottom;

	/**
	 * Instantiates a new insets impl.
	 * 
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @param top
	 *            the top
	 * @param bottom
	 *            the bottom
	 */
	public InsetsImpl(int left, int right, int top, int bottom) {
		setLeft(left);
		setRight(right);
		setTop(top);
		setBottom(bottom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.IInsets#getBottom()
	 */
	public int getBottom() {
		return bottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.IInsets#getLeft()
	 */
	public int getLeft() {
		return left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.IInsets#getRight()
	 */
	public int getRight() {
		return right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.datatypes.IInsets#getTop()
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Sets the bottom.
	 * 
	 * @param bottom
	 *            the new bottom
	 */
	protected void setBottom(int bottom) {
		this.bottom = bottom;
	}

	/**
	 * Sets the left.
	 * 
	 * @param left
	 *            the new left
	 */
	protected void setLeft(int left) {
		this.left = left;
	}

	/**
	 * Sets the right.
	 * 
	 * @param right
	 *            the new right
	 */
	protected void setRight(int right) {
		this.right = right;
	}

	/**
	 * Sets the top.
	 * 
	 * @param top
	 *            the new top
	 */
	protected void setTop(int top) {
		this.top = top;
	}

}
