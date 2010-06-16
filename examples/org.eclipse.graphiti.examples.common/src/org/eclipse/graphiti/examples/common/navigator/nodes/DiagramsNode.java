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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.examples.common.ProjectUtil;
import org.eclipse.graphiti.examples.common.navigator.nodes.base.AbstractInstancesOfTypeContainerNode;


public class DiagramsNode extends AbstractInstancesOfTypeContainerNode {

	private static final String NAME = "Diagrams";

	public DiagramsNode(Object parent, IProject project) {
		super(parent, project);
	}

	@Override
	protected String getContainerName() {
		return NAME;
	}

	@Override
	public Object[] getChildren() {
		IProject project = getProject();
		if (project != null) {
			ResourceSet rSet = new ResourceSetImpl();
			return ProjectUtil.getAllDiagramFiles(project, rSet).toArray();
		}
		return null;
	}
}
