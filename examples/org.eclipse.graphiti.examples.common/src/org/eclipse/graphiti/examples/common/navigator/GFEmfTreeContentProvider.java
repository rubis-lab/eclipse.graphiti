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
package org.eclipse.graphiti.examples.common.navigator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.common.ExampleProjectNature;
import org.eclipse.graphiti.examples.common.navigator.nodes.EClassesNode;
import org.eclipse.graphiti.examples.common.navigator.nodes.base.IContainerNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.UIJob;

public class GFEmfTreeContentProvider implements ITreeContentProvider, IResourceChangeListener {

	private Viewer viewer;
	private Map<IProject, EClassesNode> projectToEClassesNode = new HashMap<IProject, EClassesNode>();

	public GFEmfTreeContentProvider() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);

	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IContainerNode) {
			IContainerNode icn = (IContainerNode) parentElement;
			return icn.getChildren();
		}
		if (parentElement instanceof IProject) {
			IProject project = (IProject) parentElement;
			try {
				if (project.isAccessible() && project.hasNature(ExampleProjectNature.NATURE_ID)) {
					EClassesNode node = projectToEClassesNode.get(project);
					if (node == null) {
						node = new EClassesNode(project, project, viewer);
						projectToEClassesNode.put(project, node);
					}
					return new Object[] { node };
				}
			} catch (CoreException e) {
				// Ignore. E.g., project was deleted.
			}
		}
		if (parentElement instanceof EObject) {
			EObject eObject = (EObject) parentElement;
			return eObject.eContents().toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof IContainerNode) {
			IContainerNode icn = (IContainerNode) element;
			return icn.hasChildren();
		}
		if (element instanceof EObject) {
			return !((EObject) element).eContents().isEmpty();
		}
		return true;
	}

	public Object[] getElements(Object inputElement) {
		return null;
	}

	public void dispose() {
		// do nothing 
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					if (resource == null)
						return false;
					switch (resource.getType()) {
					case IResource.ROOT:
						return true;
					case IResource.PROJECT:
						IProject p = (IProject) resource;
						try {
							boolean hasNature = p.hasNature(ExampleProjectNature.NATURE_ID);
							return hasNature;
						} catch (CoreException e) {
							// Do nothing, e.g. project deleted.
						}
						return false;
					case IResource.FOLDER:
						return true;
					case IResource.FILE:
						final IFile file = (IFile) resource;
						if (file.getName().endsWith(".diagram") || file.getName().equals("Predefined.data")) { //$NON-NLS-1$ //$NON-NLS-2$
							UIJob job = new UIJob("Update Viewer") { //$NON-NLS-1$
								@Override
								public IStatus runInUIThread(IProgressMonitor monitor) {
									if (viewer != null && !viewer.getControl().isDisposed()) {
										EClassesNode classesNode = projectToEClassesNode.get(file.getProject());
										if (viewer instanceof StructuredViewer && classesNode != null) {
											((StructuredViewer) viewer).refresh(classesNode, true);
										} else {
											viewer.refresh();
										}
									}
									return Status.OK_STATUS;
								}
							};
							job.setSystem(true);
							job.schedule();
						}
						return false;
					}
					return false;

				}

			});
		} catch (CoreException e1) {
			return;
		}
	}
}
