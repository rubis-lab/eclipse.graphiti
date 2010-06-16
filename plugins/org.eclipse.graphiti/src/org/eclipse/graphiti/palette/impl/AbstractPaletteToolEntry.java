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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.palette.ICreationToolEntry;


/**
 * The Class AbstractPaletteToolEntry.
 */
public abstract class AbstractPaletteToolEntry extends AbstractPaletteEntry implements ICreationToolEntry {

	/**
	 * The features.
	 */
	protected List<IFeature> features = new ArrayList<IFeature>();

	private String description;

	private String largeIconId = null;

	/**
	 * Instantiates a new abstract palette tool entry.
	 * 
	 * @param label
	 *            the label
	 * @param description
	 *            the description
	 * @param iconId
	 *            the icon id
	 * @param largeIconId
	 *            the large icon id
	 */
	public AbstractPaletteToolEntry(String label, String description, String iconId, String largeIconId) {
		super(label, iconId);
		this.description = description;
		setLargeIconId(largeIconId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.palette.ICreationToolEntry#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the features.
	 * 
	 * @return the features
	 */
	public List<IFeature> getFeatures() {
		return features;
	}

	public String getLargeIconId() {
		return largeIconId;
	}

	private void setLargeIconId(String largeIconId) {
		this.largeIconId = largeIconId;
	}

}
