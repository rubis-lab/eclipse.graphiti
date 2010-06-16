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
package org.eclipse.graphiti.internal.services.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.impl.AbstractFeatureProvider;
import org.eclipse.graphiti.internal.services.IEmfService;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class EmfService implements IEmfService {

	public boolean isObjectAlive(EObject obj) {
		if (obj != null && obj.eResource() != null) {
			return true;
		}
		return false;
	}

	public TransactionalEditingDomain getTransactionalEditingDomain(IDiagramTypeProvider dtp) {
		final String SIGNATURE = "getTransactionalEditingDomain()"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[0]);
		}
		TransactionalEditingDomain ret = null;

		IDiagramEditor diagramEditor = dtp.getDiagramEditor();
		if (diagramEditor != null) {
			ret = diagramEditor.getTransactionalEditingDomain();
		}

		if (ret == null) {
			Diagram diagram = dtp.getDiagram();
			if (diagram != null && diagram.eResource() != null) {
				ResourceSet resourceSet = diagram.eResource().getResourceSet();
				ret = TransactionUtil.getEditingDomain(resourceSet);
				;
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

}
