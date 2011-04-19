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

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.internal.services.IEmfService;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class EmfService implements IEmfService {

	private WeakHashMap<Diagram, WeakReference<IDiagramTypeProvider>> diagToProvider = new WeakHashMap<Diagram, WeakReference<IDiagramTypeProvider>>();

	@Override
	public boolean isObjectAlive(EObject obj) {
		return obj != null && obj.eResource() != null;
	}

	@Override
	public IDiagramTypeProvider getDTPForDiagram(Diagram d) {
		WeakReference<IDiagramTypeProvider> weakReference = diagToProvider.get(d);
		if (weakReference == null || weakReference.get() == null) {
			return new GenericDiagramTypeProvider() {
			};
		}
		return weakReference.get();
	}

	@Override
	public void wireDTPToDiagram(Diagram d, IDiagramTypeProvider provider) {
		diagToProvider.put(d, new WeakReference<IDiagramTypeProvider>(provider));

	}

	/**
	 * Just a marker class. We want to return the generic provider to be able to
	 * work with diagrams (follow links etc) even if no DTP is registered.
	 * Scenario: extract the diagram object from a file and follow links to
	 * model objects-> link service needs the DTP to decide equality for bos
	 */
	private class GenericDiagramTypeProvider extends AbstractDiagramTypeProvider {

	}
}
