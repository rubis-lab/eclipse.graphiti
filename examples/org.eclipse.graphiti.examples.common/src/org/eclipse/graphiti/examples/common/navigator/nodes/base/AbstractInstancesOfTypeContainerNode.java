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
/*
 * Created on 13.06.2005
 */
package org.eclipse.graphiti.examples.common.navigator.nodes.base;

import org.eclipse.core.resources.IProject;

/**
 * The Class AbstractInstancesOfTypeContainerNode.
 */
public abstract class AbstractInstancesOfTypeContainerNode extends AbstractContainerNode {

	private Object parent;
	IProject project;

	/**
	 * The Constructor.
	 * 
	 * @param parent
	 *            the parent
	 */
	public AbstractInstancesOfTypeContainerNode(Object parent, IProject project) {
		super();
		this.parent = parent;
		this.project = project;
	}

	public Object getParent() {
		return parent;
	}

	@Override
	public boolean hasChildren() {
		return super.hasChildren(); // getChildren().length > 0;
	}

	public IProject getProject() {
		return project;
	}
}
