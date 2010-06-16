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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * The Class DiagramNameWizardPage.
 */
public class DiagramNameWizardPage extends AbstractInputFieldWizardPage {

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
	public DiagramNameWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	/**
	 * Instantiates a new diagram name wizard page.
	 * 
	 * @param pageName
	 *            the page name
	 */
	protected DiagramNameWizardPage(String pageName) {
		super(pageName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.examples.testview.wizard.AbstractInputFieldWizardPage
	 * #getInitialTextFieldValue()
	 */
	@Override
	String getInitialTextFieldValue() {
		return "new diagram";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.examples.testview.wizard.AbstractInputFieldWizardPage
	 * #getLabelText()
	 */
	@Override
	String getLabelText() {
		return "Diagram Name";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.examples.testview.wizard.AbstractInputFieldWizardPage
	 * #getMessageText()
	 */
	@Override
	String getMessageText() {
		return "Please enter diagram name";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.samples.testview.wizard.AbstractInputFieldWizardPage
	 * #doWorkspaceValidation(org.eclipse.core.resources.IWorkspace,
	 * java.lang.String)
	 */
	@Override
	IStatus doWorkspaceValidation(IWorkspace workspace, String text) {
		IStatus ret = workspace.validateName(text, IResource.FILE);
		return ret;
	}
}
