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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.examples.common.FileService;
import org.eclipse.graphiti.examples.common.SamplesCommonPlugin;
import org.eclipse.graphiti.examples.common.navigator.nodes.base.AbstractInstancesOfTypeContainerNode;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

/**
 * The Class CreateDiagramWizard.
 */
public class CreateDiagramWizard extends BasicNewResourceWizard {
	private static final String PAGE_NAME_DIAGRAM_TYPE = "Diagram Type";

	private static final String PAGE_NAME_DIAGRAM_NAME = "Diagram Name";

	private Diagram diagram;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		addPage(new DiagramTypeWizardPage(PAGE_NAME_DIAGRAM_TYPE));
		addPage(new DiagramNameWizardPage(PAGE_NAME_DIAGRAM_NAME));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		return super.canFinish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.wizards.newresource.BasicNewResourceWizard#init(org.eclipse
	 * .ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		ITextProvider typePage = (ITextProvider) getPage(PAGE_NAME_DIAGRAM_TYPE);
		final String diagramTypeId = typePage.getText();

		ITextProvider namePage = (ITextProvider) getPage(PAGE_NAME_DIAGRAM_NAME);
		final String diagramName = namePage.getText();

		IProject project = null;
		IFolder diagramFolder = null;

		Object element = getSelection().getFirstElement();
		if (element instanceof IProject) {
			project = (IProject) element;
		} else if (element instanceof AbstractInstancesOfTypeContainerNode) {
			AbstractInstancesOfTypeContainerNode aiocn = (AbstractInstancesOfTypeContainerNode) element;
			project = aiocn.getProject();
		} else if (element instanceof IFolder) {
			diagramFolder = (IFolder) element;
			project = diagramFolder.getProject();
		}

		if (project == null || !project.isAccessible()) {
			String error = "No open project was found for the current selection. Select a project and restart the wizard.";
			IStatus status = new Status(IStatus.ERROR, SamplesCommonPlugin.getID(), error);
			ErrorDialog.openError(getShell(), "No Project Found", null, status);
			return false;
		}

		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
		if (diagramFolder == null) {
			diagramFolder = project.getFolder("src/diagrams/");
		}

		IFile diagramFile = diagramFolder.getFile(diagramName + ".diagram");
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);

		TransactionalEditingDomain domain = FileService.createEmfFileForDiagram(uri, diagram);

		GraphitiUiInternal.getWorkbenchService().openDiagramEditor(diagram, domain, true);

		return true;
	}

	/**
	 * Gets the diagram.
	 * 
	 * @return the diagram
	 */
	public Diagram getDiagram() {
		return diagram;
	}
}
