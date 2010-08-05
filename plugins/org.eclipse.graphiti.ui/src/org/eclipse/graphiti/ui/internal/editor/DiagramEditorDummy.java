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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.internal.datatypes.impl.DimensionImpl;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;

/**
 * 
 * can be used in the dark feature processing mode
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DiagramEditorDummy implements IDiagramEditor {

	private IDiagramTypeProvider diagramTypeProvider;

	public DiagramEditorDummy(IDiagramTypeProvider diagramTypeProvider) {
		setDiagramTypeProvider(diagramTypeProvider);
	}

	@Override
	public TransactionalEditingDomain getEditingDomain() {
		return null;
	}

	@Override
	public ResourceSet getResourceSet() {
		return null;
	}

	public IDimension getCurrentSize() {
		return new DimensionImpl(0, 0);
	}

	public IDiagramTypeProvider getDiagramTypeProvider() {
		return diagramTypeProvider;
	}

	public PictogramElement[] getSelectedPictogramElements() {
		return new PictogramElement[0];
	}

	public boolean isDirty() {
		return false;
	}

	public void refresh() {
	}

	public void refreshTitleToolTip() {
	}

	public void selectPictogramElements(PictogramElement[] pictogramElements) {
	}

	public void setPictogramElementForSelection(PictogramElement pictogramElement) {
	}

	public void setPictogramElementsForSelection(PictogramElement[] pictogramElements) {
	}

	protected void setDiagramTypeProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
	}

	public void refreshRenderingDecorators(PictogramElement pe) {
	}

	public void refreshPalette() {
	}

	@Override
	public ILocation getCurrentMouseLocation() {
		return null;
	}
}
