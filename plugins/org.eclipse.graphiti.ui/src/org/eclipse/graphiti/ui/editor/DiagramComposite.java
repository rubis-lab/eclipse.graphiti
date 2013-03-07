/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013 SRC
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    pjpaulin - initial API, implementation and documentation
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * An SWT composite that can display a Graphiti diagram. This implementation is
 * based on a custom {@link GraphicalComposite} class that works much like the
 * GEF GraphicalEditorWithFlyoutPalette class.
 * 
 * A DiagramComposite can be used anywhere a regular SWT Composite can. A
 * DiagramComposite can participate in a parent transaction or create one of
 * it's own. If the DiagramComposites is managing it's own transaction, it is up
 * to the enclosing workbench part to call the appropriate methods on the
 * composite.
 * 
 * @since 0.10
 */
public class DiagramComposite extends GraphicalComposite implements IDiagramContainerUI {

	private DiagramSupport diagramSupport;

	public DiagramComposite(IWorkbenchPart ownedPart, Composite parent, int style) {
		super(parent, style);
		diagramSupport = new DiagramSupport(this);
		diagramSupport.setParentPart(ownedPart);
		setEditDomain(new DefaultEditDomain(null));
	}

	public DiagramComposite(Composite parent, int style) {
		this(null, parent, style);
	}

	public void setInput(IDiagramEditorInput input) {
		TransactionalEditingDomain editingDomain = GraphitiUiInternal.getEmfService()
				.createResourceSetAndEditingDomain();
		this.setInput(editingDomain, input);
	}

	public void setInput(TransactionalEditingDomain editingDomain, IDiagramEditorInput input) {

		// assign editing domain to update behavior
		diagramSupport.getUpdateBehavior().setEditingDomain(editingDomain);
		this.diagramSupport.setInput(input);

		if (this.diagramSupport.getEditorInitializationError() != null) {
			this.diagramSupport.createErrorPartControl(this);
			return;
		}

		diagramSupport.getUpdateBehavior().init();
		init();
		this.diagramSupport.migrateDiagramModelIfNecessary();
		this.diagramSupport.createPartControl();
	}

	@Override
	public void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		this.diagramSupport.initializeGraphicalViewer();
	}

	@Override
	protected void createGraphicalViewer() {
		this.diagramSupport.createGraphicalViewer(this);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		this.diagramSupport.configureGraphicalViewer();
	}

	public void refreshTitle() {
		// String name = getConfigurationProvider().getDiagramTypeProvider()
		// .getDiagramTitle();
		// if (name == null || name.length() == 0) {
		//			name = getConfigurationElement().getAttribute("name"); //$NON-NLS-1$
		// }
		// if (name == null || name.length() == 0) {
		// name = URI.decode(getDiagramTypeProvider().getDiagram().eResource()
		// .getURI().lastSegment());
		// }
		// setPartName(name);

		/* TODO Need to figure out how to access this */
		// this.getParentPart().setPartName("State View");
	}

	public void refreshTitleToolTip() {
		/* TODO Need to figure out how to access this */
		// this.parentPart.setTitleToolTip("Test Graphiti View");
	}

	public void updateDirtyState() {
		// TODO Auto-generated method stub
	}

	@Override
	public IWorkbenchPart getWorkbenchPart() {
		return diagramSupport.getParentPart();
	}

	public void close() {
		// TODO Auto-generated method stub
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		Object returnObj = this.diagramSupport.getAdapter(type);
		if (returnObj != null)
			return returnObj;
		return super.getAdapter(type);
	}

	public void dispose() {
		// unregister selection listener, registered during createPartControl()
		if (getWorkbenchPart() != null && getWorkbenchPart().getSite() != null
				&& getWorkbenchPart().getSite().getPage() != null) {
			getWorkbenchPart().getSite().getPage().removeSelectionListener(this);
		}

		this.diagramSupport.preSuperDispose();

		RuntimeException exc = null;
		try {
			super.dispose();
		} catch (RuntimeException e) {
			exc = e;
		}

		this.diagramSupport.postSuperDispose();

		if (exc != null) {
			throw exc;
		}
	}

	/**
	 * Sets the focus by delegating to the super class implementation in the GEF
	 * editor and additionally triggers a update of the diagram by delegating to
	 * {@link DefaultUpdateBehavior#handleActivate()}.
	 */
	public boolean setFocus() {
		if (getGraphicalViewer() == null) {
			return false;
		}

		if (super.setFocus()) {
			diagramSupport.getUpdateBehavior().handleActivate();
			return true;
		}
		return false;
	}

	/* Methods routed directly to DiagramSupport */

	public final DefaultUpdateBehavior getUpdateBehavior() {
		return this.diagramSupport.getUpdateBehavior();
	}

	public final DefaultRefreshBehavior getRefreshBehavior() {
		return this.diagramSupport.getRefreshBehavior();
	}

	public void editingDomainInitialized() {
		this.diagramSupport.editingDomainInitialized();
	}

	public void disableAdapters() {
		this.diagramSupport.disableAdapters();
	}

	public void enableAdapters() {
		this.diagramSupport.enableAdapters();
	}

	public boolean isDirty() {
		return this.diagramSupport.isDirty();
	}

	protected final PaletteViewerProvider createPaletteViewerProvider() {
		return this.diagramSupport.createPaletteViewerProvider();
	}

	protected final FlyoutPreferences getPalettePreferences() {
		return this.diagramSupport.getPalettePreferences();
	}

	protected final PaletteRoot getPaletteRoot() {
		return this.diagramSupport.getPaletteRoot();
	}

	public PictogramElement[] getSelectedPictogramElements() {
		return diagramSupport.getSelectedPictogramElements();
	}

	public void selectPictogramElements(PictogramElement[] pictogramElements) {
		diagramSupport.selectPictogramElements(pictogramElements);
	}

	public void setPictogramElementForSelection(PictogramElement pictogramElement) {
		diagramSupport.setPictogramElementForSelection(pictogramElement);
	}

	public void setPictogramElementsForSelection(PictogramElement pictogramElements[]) {
		diagramSupport.setPictogramElementsForSelection(pictogramElements);
	}

	public IDiagramTypeProvider getDiagramTypeProvider() {
		return this.diagramSupport.getDiagramTypeProvider();
	}

	public GraphicalEditPart getEditPartForPictogramElement(PictogramElement pe) {
		return this.diagramSupport.getEditPartForPictogramElement(pe);
	}

	public double getZoomLevel() {
		return this.diagramSupport.getZoomLevel();
	}

	public boolean isAlive() {
		return this.diagramSupport.isAlive();
	}

	public boolean isDirectEditingActive() {
		return this.diagramSupport.isDirectEditingActive();
	}

	public void setDirectEditingActive(boolean directEditingActive) {
		this.diagramSupport.setDirectEditingActive(directEditingActive);
	}

	public IDiagramEditorInput getDiagramEditorInput() {
		return diagramSupport.getInput();
	}

	/* Methods from container interface */

	public IWorkbenchPartSite getSite() {
		return getWorkbenchPart().getSite();
	}

	public String getTitle() {
		return getWorkbenchPart().getTitle();
	}

	public String getTitleToolTip() {
		return getWorkbenchPart().getTitleToolTip();
	}

	public void doSave(IProgressMonitor monitor) {
		diagramSupport.getPersistencyBehavior().saveDiagram(monitor);
	}

	public DiagramSupport getDiagramSupport() {
		return diagramSupport;
	}
}
