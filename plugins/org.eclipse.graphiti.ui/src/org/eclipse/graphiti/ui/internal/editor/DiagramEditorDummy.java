/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    mwenz - Bug 363539 - Enabled feature delegation via IDiagramEditor.execute method - contributed by Hernan
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    mgorning - Bug 371671 - addGraphicalRepresentation returns null in dark mode
 *    pjpaulin - Bug 352120 - Added additional methods required by interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
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
	private TransactionalEditingDomain editingDomain;

	public DiagramEditorDummy(IDiagramTypeProvider diagramTypeProvider, TransactionalEditingDomain eDomain) {
		setDiagramTypeProvider(diagramTypeProvider);
		editingDomain = eDomain;
	}

	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

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

	public void refresh(PictogramElement pe) {
	}

	public void refreshTitle() {
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

	public ILocation getCurrentMouseLocation() {
		return null;
	}

	/**
	 * Can be called to execute the given {@link IFeature} using the given
	 * {@link IContext}; also the Graphiti framework uses this method to call
	 * additional features from with feature processing. In the latter case this
	 * method is called from with an EMF Transaction so that modifications are
	 * wrapped inside this Transaction. In case the method is called directly by
	 * a user, it needs to be ensured that the call happens with an EMF
	 * transaction, otherwise an according EMF Transactions exception will be
	 * thrown.
	 * 
	 * @param feature
	 *            the {@link IFeature} to execute
	 * @param context
	 *            the {@link IContext} to use while executing the feature
	 * @return in case of an {@link IAddFeature} being passed as feature the
	 *         newly added {@link PictogramElement} will be returned (in case
	 *         the add method returning it), in all other cases
	 *         <code>null</code>
	 */
	public Object executeFeature(IFeature feature, IContext context) {
		Object returnValue = null;
		if (feature != null && context != null && feature.canExecute(context)) {
			if (feature instanceof IAddFeature) {
				IAddFeature addFeature = (IAddFeature) feature;
				returnValue = addFeature.add((IAddContext) context);
			} else {
				feature.execute(context);
			}
		}

		return returnValue;
	}

	public void disableAdapters() {
	}

	public void enableAdapters() {
	}

	public void commandStackChanged(EventObject event) {
	}

	public boolean isAlive() {
		return false;
	}

	public void selectBufferedPictogramElements() {
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		return null;
	}

	public void refreshContent() {
	}

	public void editingDomainInitialized() {
	}

	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}
}
