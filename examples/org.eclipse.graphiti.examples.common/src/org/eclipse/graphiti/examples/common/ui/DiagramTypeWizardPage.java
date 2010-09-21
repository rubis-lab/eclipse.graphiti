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
package org.eclipse.graphiti.examples.common.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * The Class DiagramTypeWizardPage.
 */
public class DiagramTypeWizardPage extends AbstractWizardPage implements ITextProvider {

	private static final String PAGE_DESC = "Select diagram type";
	private static final String PAGE_TITLE = "Diagram";

	private static final String DEFAULT_TYPE = "tutorial";
	private static final String SELECTED_TYPE = "selectedtype";
	// widget
	/**
	 * The combo box.
	 */
	Combo comboBox;

	/**
	 * The Constructor.
	 * 
	 * @param pageName
	 *            the page name
	 * @param title
	 *            the title
	 * @param titleImage
	 *            the title image
	 */
	public DiagramTypeWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	/**
	 * Instantiates a new diagram type wizard page.
	 * 
	 * @param pageName
	 *            the page name
	 */
	protected DiagramTypeWizardPage(String pageName) {
		super(pageName);
		setTitle(PAGE_TITLE);
		setDescription(PAGE_DESC);
	}

	@Override
	protected void createWizardContents(Composite parent) {
		// project specification group
		Composite projectGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		projectGroup.setLayout(layout);
		projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// new project label
		Label projectLabel = new Label(projectGroup, SWT.NONE);
		projectLabel.setFont(parent.getFont());
		projectLabel.setText("Diagram Type");

		// new project name entry field
		comboBox = new Combo(projectGroup, SWT.READ_ONLY | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 250;
		comboBox.setLayoutData(data);
		comboBox.setFont(parent.getFont());
		comboBox.setVisibleItemCount(12);
		comboBox.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IDialogSettings dialogSettings = ExamplesCommonPlugin.getDefault().getDialogSettings();
				dialogSettings.put(SELECTED_TYPE, comboBox.getText());
			}
		});

		// set the contents of the Combo-widget
		comboBox.setItems(getAllAvailableDiagramTypes());
		if (getInitialValue() != null) {
			comboBox.setText(getInitialValue());
		}
	}

	/**
	 * Gets the all available diagram types.
	 * 
	 * @return the all available diagram types
	 */
	protected String[] getAllAvailableDiagramTypes() {
		Vector<String> diagramIds = new Vector<String>();
		for (IDiagramType diagramType : GraphitiUi.getExtensionManager().getDiagramTypes()) {
			diagramIds.add(diagramType.getId());
		}

		return diagramIds.toArray(new String[] {});
	}

	/**
	 * Gets the initial value.
	 * 
	 * @return the initial value
	 */
	protected String getInitialValue() {
		// Get last choice
		IDialogSettings dialogSettings = ExamplesCommonPlugin.getDefault().getDialogSettings();
		String selType = dialogSettings.get(SELECTED_TYPE);
		List<String> asList = Arrays.asList(comboBox.getItems());
		if (asList.contains(selType)) {
			return selType;
		} else if (asList.contains(DEFAULT_TYPE)) {
			return DEFAULT_TYPE;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.examples.testview.wizard.ITextProvider#getText()
	 */
	@Override
	public String getText() {
		return comboBox.getText();
	}
}
