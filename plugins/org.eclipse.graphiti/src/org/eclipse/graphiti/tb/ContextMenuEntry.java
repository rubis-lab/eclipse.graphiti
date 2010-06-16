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

import java.util.ArrayList;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;


/**
 * The Class ContextMenuEntry.
 */
public class ContextMenuEntry extends AbstractContextEntry implements IContextMenuEntry {
	private static final IContextMenuEntry[] NO_CONTEXT_MENU_ENTRIES = new IContextMenuEntry[0];

	private ArrayList<IContextMenuEntry> children = new ArrayList<IContextMenuEntry>();

	private boolean submenu = true;

	/**
	 * Instantiates a new context menu entry.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public ContextMenuEntry(IFeature feature, IContext context) {
		super(feature, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextMenuEntry#getChildren()
	 */
	public IContextMenuEntry[] getChildren() {
		IContextMenuEntry[] ret = children.toArray(NO_CONTEXT_MENU_ENTRIES);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextMenuEntry#add(org.eclipse.graphiti.tb.IContextMenuEntry)
	 */
	public void add(IContextMenuEntry entry) {
		children.add(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextMenuEntry#isSubmenu()
	 */
	public boolean isSubmenu() {
		return submenu;
	}

	/**
	 * Sets the submenu.
	 * 
	 * @param submenu
	 *            the new submenu
	 */
	public void setSubmenu(boolean submenu) {
		this.submenu = submenu;
	}
}
