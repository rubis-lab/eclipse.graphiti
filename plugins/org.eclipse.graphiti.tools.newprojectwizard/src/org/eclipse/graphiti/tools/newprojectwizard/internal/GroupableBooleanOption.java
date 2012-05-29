/**
 * <copyright>
 * 
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.tools.newprojectwizard.internal;

import org.eclipse.pde.ui.templates.BaseOptionTemplateSection;
import org.eclipse.pde.ui.templates.BooleanOption;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A boolean option that can be added to a group
 */
public class GroupableBooleanOption extends BooleanOption {

	private GroupOption groupOption;
	private SelectionListener listener;

	public GroupableBooleanOption(BaseOptionTemplateSection section, String name, String label,
			GroupOption groupOption, SelectionListener listener) {
		super(section, name, label);
		this.groupOption = groupOption;
		this.listener = listener;
	}

	@Override
	public void createControl(Composite parent, int span) {
		super.createControl(groupOption.getGroup(), span);
		if (listener != null) {
			addSelectionListener(listener);
		}
	}

	private void addSelectionListener(SelectionListener listener) {
		Control[] children = groupOption.getGroup().getChildren();
		for (Control control : children) {
			if (control instanceof Button) {
				((Button) control).addSelectionListener(listener);
			}
		}
	}
}
