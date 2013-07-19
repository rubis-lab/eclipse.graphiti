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
 *    mwenz - Bug 327669 - removed dependencies to GEF internal stuff
 *    fvelasco - Bug 403664 - Enable DoubleClickFeature on the diagram background
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.tools.PanningSelectionTool;
import org.eclipse.graphiti.ui.internal.Messages;

/**
 * A ToolEntry for a {@link GFPanningSelectionTool}.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPanningSelectionToolEntry extends PanningSelectionToolEntry {

	/**
	 * Creates GFPanningSelectionToolEntry.
	 */
	public GFPanningSelectionToolEntry() {
		this(null);
	}

	/**
	 * Constructor for GFPanningSelectionToolEntry.
	 * 
	 * @param label
	 *            the label
	 */
	public GFPanningSelectionToolEntry(String label) {
		this(null, label);
	}

	/**
	 * Constructor for GFPanningSelectionToolEntry.
	 * 
	 * @param label
	 *            the label
	 * @param shortDesc
	 *            the description
	 */
	public GFPanningSelectionToolEntry(String label, String shortDesc) {
		super(label, shortDesc);
		if (label == null || label.length() == 0) {
			setLabel(Messages.GFPanningSelectionToolEntry_Select);
		}
		if (shortDesc == null || shortDesc.length() == 0) {
			setDescription(Messages.GFPanningSelectionToolEntry_0_xmsg);
		}
		setToolClass(PanningSelectionTool.class);
	}

}
