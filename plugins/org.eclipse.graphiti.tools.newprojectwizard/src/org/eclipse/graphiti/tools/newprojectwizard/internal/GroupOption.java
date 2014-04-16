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
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * An option that realizes a group.
 */
public class GroupOption extends TemplateOption {

	private final static String OPTION_NAME = "groupOption"; //$NON-NLS-1$
	private static int NUM_CREATED = 0;

	private Group groupControl;

	public GroupOption(BaseOptionTemplateSection section, String label) {
		super(section, getUniqueName(), label);
		setRequired(false);
	}

	private static String getUniqueName() {
		return OPTION_NAME + Integer.toString(NUM_CREATED++);
	}

	/**
	 * Creates the goup option control.
	 * 
	 * @param parent
	 *            parent composite of the string option widget
	 * @param span
	 *            the number of columns that the widget should span
	 */
	public void createControl(Composite parent, int span) {
		groupControl = new Group(parent, SWT.None);
		GridLayout layout = new GridLayout(span, false);
		groupControl.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = span;
		gd.verticalIndent = 5;
		groupControl.setLayoutData(gd);
		groupControl.setText(getLabel());
	}

	public Group getGroup() {
		return groupControl;
	}
}
