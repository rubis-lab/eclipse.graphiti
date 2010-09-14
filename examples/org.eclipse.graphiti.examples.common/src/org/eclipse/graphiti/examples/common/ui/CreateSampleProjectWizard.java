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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.examples.common.ExampleProjectNature;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class CreateSampleProjectWizard extends BasicNewProjectResourceWizard {

	@Override
	public boolean performFinish() {
		if (!super.performFinish())
			return false;

		IProject newProject = getNewProject();
		try {
			IProjectDescription description = newProject.getDescription();
			description.setNatureIds(new String[] { ExampleProjectNature.NATURE_ID });
			newProject.setDescription(description, null);
			createPredefinedContent(newProject);
		} catch (CoreException e) {
			return false;
		}

		return true;
	}

	private void createPredefinedContent(IProject newProject) throws CoreException {
		EClass eclass = EcoreFactory.eINSTANCE.createEClass();
		eclass.setName("PredefinedEClass"); //$NON-NLS-1$
		ResourceSet set = new ResourceSetImpl();
		URI uri = URI.createPlatformResourceURI(newProject.getFolder("src").getFile("Predefined.data").getFullPath().toString(), true); //$NON-NLS-1$ //$NON-NLS-2$
		Resource resource = set.createResource(uri);
		resource.getContents().add(eclass);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			IStatus status = new Status(IStatus.ERROR, "org.eclipse.tutorial.support", 0, e.getMessage(), e); //$NON-NLS-1$
			throw new CoreException(status);
		}
	}
}
