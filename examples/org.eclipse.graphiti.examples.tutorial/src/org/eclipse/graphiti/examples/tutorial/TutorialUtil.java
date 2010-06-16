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
package org.eclipse.graphiti.examples.tutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

public class TutorialUtil {

	public static Collection<Diagram> getDiagrams(IProject p) {
		List<IFile> files = getDiagramFiles(p);
		List<Diagram> diagramList = new ArrayList<Diagram>();
		ResourceSet rSet = new ResourceSetImpl();
		for (IFile file : files) {
			Diagram diagram = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, rSet);
			if (diagram != null) {
				diagramList.add(diagram);
			}
		}
		return diagramList;
	}

	private static List<IFile> getDiagramFiles(IContainer folder) {
		List<IFile> ret = new ArrayList<IFile>();
		try {
			IResource[] members = folder.members();
			for (IResource resource : members) {
				if (resource instanceof IContainer) {
					ret.addAll(getDiagramFiles((IContainer) resource));
				} else if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					if (file.getName().endsWith(".diagram")) { //$NON-NLS-1$
						ret.add(file);
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
