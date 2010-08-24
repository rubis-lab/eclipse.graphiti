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
package org.eclipse.graphiti.examples.common.preference;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.graphiti.internal.GraphitiPlugin;
import org.eclipse.graphiti.internal.pref.GFPreferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * The workbench-preference-page of this Plugin.
 */
@SuppressWarnings("restriction")
// The Test preference page is allowed to use the preference settings
// defined and used in the framework
public class GraphicsTestPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Creates and returns the text editor preference page.
	 */
	public GraphicsTestPreferencePage() {
		super(GRID);
		setDescription("Graphics Framework Test Preferences. You might need to re-open the diagrams to see the changes.");
		setPreferenceStore(new ScopedPreferenceStore(new InstanceScope(), GraphitiPlugin.PLUGIN_ID));
	}

	@Override
	public void createFieldEditors() {

		addLabel("");
		addLabel("Visual playing around");
		addField(new IntegerFieldEditor(GFPreferences.VISUAL_STATE_RENDERING,
				"&Visual state rendering (e.g. selection handling): 0=default, 1=special", getFieldEditorParent()));
		addField(new IntegerFieldEditor(GFPreferences.CONTEXT_BUTTON_PAD_DECLARATION,
				"Context bu&tton pad declaration: 0=default, 1=special", getFieldEditorParent()));
		addField(new IntegerFieldEditor(GFPreferences.ZOOM_ANIMATION_STEPS, "A&nimation steps to perform when zooming",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.PROGRESS_DIALOG_ACTIVE, "Show &progress dialog when commands are executed.",
				getFieldEditorParent()));
		addField(new IntegerFieldEditor(GFPreferences.POLYLINE_ROUNDING, "P&olyline rounding: 0=on, 1=never, 2=always.",
				getFieldEditorParent()));

		addLabel("");
		addLabel("Debugging");
		addField(new BooleanFieldEditor(GFPreferences.DEBUG_ACTIONS_ACTIVE, "Add de&bug actions to the context-menu of each shape",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.CPU_PROFILING_TRACE_ACTIVE,
				"Activate &CPU profiling trace (write CPU durations into the log-file)", getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.INVISIBLE_RECTANGLES_SHOWN, "Show &invisible rectangles (make them visible)",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.GENERIC_OUTLINE_ACTIVE, "&Use generic outline view", getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.GENERIC_PROPERTY_SHEET_ACTIVE, "U&se generic property sheet", getFieldEditorParent()));

		addLabel("");
		addLabel("Graphiti internal (do not change)");
		addField(new BooleanFieldEditor(GFPreferences.RECURSIVE_CHECK_FOR_UPDATE_ACTIVE, "Check for a needed updates &recursively",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(GFPreferences.MOF_EXAMPLE_SHOWING_ALL_PROPERTIES, "MO&F example shows all properties",
				getFieldEditorParent()));
	}

	private void addLabel(String message) {
		Label label = new Label(getFieldEditorParent(), SWT.None);
		GridData data = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 2, 1);
		label.setLayoutData(data);
		label.setForeground(ColorConstants.darkGreen);
		if (message != null)
			label.setText(message);
	}

	@Override
	public void init(IWorkbench workbench) {
		// nothing to do
	}

}
