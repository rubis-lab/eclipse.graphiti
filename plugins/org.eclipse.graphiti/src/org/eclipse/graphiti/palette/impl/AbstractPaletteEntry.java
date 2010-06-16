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
package org.eclipse.graphiti.palette.impl;

import org.eclipse.graphiti.palette.IPaletteEntry;

/**
 * The Class AbstractPaletteEntry.
 */
public abstract class AbstractPaletteEntry implements IPaletteEntry {

	private String label, iconId;

	/**
	 * Instantiates a new abstract palette entry.
	 * 
	 * @param label
	 *            the label
	 * @param iconId
	 *            the icon id
	 */
	public AbstractPaletteEntry(String label, String iconId) {
		this.label = label;
		this.iconId = iconId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.palette.IPaletteEntry#getLabel()
	 */
	public String getLabel() {
		return label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.palette.IPaletteEntry#getIconId()
	 */
	public String getIconId() {
		return iconId;
	}

}
