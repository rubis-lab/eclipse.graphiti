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
import org.eclipse.pde.ui.templates.StringOption;
import org.eclipse.swt.widgets.Composite;

/**
 * A string option that can be added to a group
 */
public class GroupableStringOption extends StringOption {

	private GroupOption groupOption;

	public GroupableStringOption(BaseOptionTemplateSection section, String name, String label, GroupOption groupOption) {
		super(section, name, label);
		this.groupOption = groupOption;
	}

	@Override
	public void createControl(Composite parent, int span) {
		super.createControl(groupOption.getGroup(), span);
	}
}
