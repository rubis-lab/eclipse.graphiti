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
package org.eclipse.graphiti.examples.common.navigator.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.examples.common.navigator.nodes.base.AbstractInstancesOfTypeContainerNode;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

/**
 * EClassesNode should display the EClasses of the currently activated diagram
 * editor.
 * 
 */
public class EClassesNode extends AbstractInstancesOfTypeContainerNode {
	private static final String NAME = "EClasses";

	private ResourceSetImpl rSet;

	public EClassesNode(Object parent, IProject project, Viewer viewer) {
		super(parent, project);
		rSet = new ResourceSetImpl();

	}

	@Override
	protected String getContainerName() {
		return NAME;
	}

	@Override
	public Object[] getChildren() {

		// FIXME: always unload to have our resources refreshed, this is highly non-performant
		EList<Resource> resources = rSet.getResources();
		for (Resource resource : resources) {
			resource.unload();
		}
		IProject project = getProject();
		IFolder folder = project.getFolder("src");
		IFolder folderDiagrams = project.getFolder("src/diagrams");
		Collection<Diagram> diagrams = new ArrayList<Diagram>();
		Set<EClass> eClasses = new HashSet<EClass>();
		if (folder.exists()) {
			List<IResource> membersList = new ArrayList<IResource>();
			try {
				membersList.addAll(Arrays.asList(folder.members()));
				membersList.addAll(Arrays.asList(folderDiagrams.members()));
			} catch (CoreException e) {
				return new Object[0];
			}
			for (IResource resource : membersList) {
				if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					if ("diagram".equals(file.getFileExtension()) || file.getName().equals("Predefined.data")) {
						Diagram diag = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, rSet);
						if (diag != null) {
							diagrams.add(diag);
						} else {
							URI uri = GraphitiUiInternal.getEmfService().getFileURI(file, rSet);
							Resource fileResource = rSet.getResource(uri, true);
							if (fileResource != null) {
								EList<EObject> contents = fileResource.getContents();
								for (EObject object : contents) {
									if (object instanceof EClass && !(object instanceof PictogramElement)) {
										eClasses.add((EClass) object);
									}
								}
							}
						}
					}
				}
			}
		}
		for (Diagram diagram : diagrams) {
			Resource resource = diagram.eResource();
			if (resource == null)
				return new Object[0];
			EList<EObject> contents = resource.getContents();
			for (EObject object : contents) {
				if (object instanceof EClass && !(object instanceof PictogramElement)) {
					eClasses.add((EClass) object);
				}
			}
		}
		return eClasses.toArray();
	}

	@Override
	public Image getImage() {
		return super.getImage(); // ImagePool.getImage(ImagePool.ROOT_FOLDER_FOR_IMG);
	}

}
