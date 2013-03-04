/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG.
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
package org.eclipse.graphiti.platform;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * @since 0.10
 */
public interface IDiagramContainer extends IDiagramEditor {

	public ResourceSet getResourceSet();

	public TransactionalEditingDomain getEditingDomain();

	public void refresh();

	public void refreshTitle();

	public void refreshPalette();

	public void setPictogramElementForSelection(PictogramElement pictogramElement);

	public void setPictogramElementsForSelection(PictogramElement[] pictogramElements);

	public PictogramElement[] getSelectedPictogramElements();
}
