/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2011 SAP AG.
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
package org.eclipse.graphiti.ui.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.internal.pref.GFPreferences;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.editor.RefreshPerformanceCache;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.graphiti.ui.internal.parts.IShapeEditPart;
import org.eclipse.graphiti.ui.internal.parts.ShapeEditPart;
import org.eclipse.swt.widgets.Display;

/**
 * @since 0.9
 */
public class DefaultRefreshBehavior {

	protected final DiagramEditor diagramEditor;

	private boolean autoRefresh = true;
	private RefreshPerformanceCache refreshPerformanceCache = new RefreshPerformanceCache();

	/**
	 * 
	 */
	public DefaultRefreshBehavior(DiagramEditor diagramEditor) {
		super();
		this.diagramEditor = diagramEditor;
	}

	/**
	 * Initializes the performance cache. Should not be called by external
	 * clients.
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 * @since 0.9
	 */
	public void initRefresh() {
		getRefreshPerformanceCache().initRefresh();
	}

	/**
	 * Handle auto update at startup.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 * @since 0.9
	 */
	protected void handleAutoUpdateAtStartup(Diagram diagram, IDiagramTypeProvider diagramTypeProvider) {
		if (diagramTypeProvider.isAutoUpdateAtStartup()) {
			autoUpdate(diagram, diagramTypeProvider);
		}
	}

	/**
	 * Handle auto update at reset.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 * @since 0.9
	 */
	protected void handleAutoUpdateAtReset(Diagram diagram, IDiagramTypeProvider diagramTypeProvider) {
		if (diagramTypeProvider.isAutoUpdateAtReset()) {
			autoUpdate(diagram, diagramTypeProvider);
		}
	}

	/**
	 * @since 0.9
	 */
	protected void autoUpdate(Diagram diagram, IDiagramTypeProvider diagramTypeProvider) {
		IFeatureProvider featureProvider = diagramTypeProvider.getFeatureProvider();
		IUpdateContext updateCtx = new UpdateContext(diagram);
		featureProvider.updateIfPossible(updateCtx);
		refresh();
	}

	public void refresh() {
		if (!diagramEditor.isAlive()) {
			return;
		}

		if (Display.getCurrent() == null) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					refresh();
				}
			});
			return;
		}

		if (GFPreferences.getInstance().isCPUProfilingTraceActive()) {
			if (T.racer().info()) {
				T.racer().info("DiagramEditorInternal.refresh()"); //$NON-NLS-1$
			}
		}

		long start = System.currentTimeMillis();

		final EditPart contentEditPart = diagramEditor.getContentEditPart();
		if (contentEditPart == null) {
			return;
		}

		internalRefreshEditPart(contentEditPart);

		diagramEditor.refreshTitle();

		long stop = System.currentTimeMillis();
		long time = (stop - start);
		if (time > 500) {
			String output = "refresh took " + time + " ms."; //$NON-NLS-1$ //$NON-NLS-2$
			T.racer().warning("DiagramEditorInternal.refresh() ", output); //$NON-NLS-1$
		}

		// prove if switch to direct editing is required
		IDirectEditingInfo dei = diagramEditor.getConfigurationProvider().getFeatureProvider().getDirectEditingInfo();
		if (dei.isActive()) {
			EditPart editPart = (EditPart) diagramEditor.getGraphicalViewer().getEditPartRegistry()
					.get(dei.getMainPictogramElement());
			if (editPart instanceof ShapeEditPart) {
				ShapeEditPart shapeEditPart = (ShapeEditPart) editPart;
				shapeEditPart.switchToDirectEditingMode(dei.getPictogramElement(), dei.getGraphicsAlgorithm());
				// reset values
				dei.reset();
			}
		}
		diagramEditor.selectBufferedPictogramElements();
	}

	protected void refresh(PictogramElement pe) {
		if (pe == null || !pe.isActive()) {
			return;
		}
		GraphicalEditPart editPart = diagramEditor.getEditPartForPictogramElement(pe);
		if (editPart != null && editPart instanceof IPictogramElementEditPart) {
			IPictogramElementEditPart ep = (IPictogramElementEditPart) editPart;
			IPictogramElementDelegate delegate = ep.getPictogramElementDelegate();
			delegate.setForceRefresh(true);
			editPart.refresh();
			delegate.setForceRefresh(false);
		}
	}

	public void refreshRenderingDecorators(PictogramElement pe) {
		GraphicalEditPart ep = diagramEditor.getEditPartForPictogramElement(pe);
		if (ep instanceof IShapeEditPart) {
			IShapeEditPart sep = (IShapeEditPart) ep;
			sep.refreshDecorators();
		}
	}

	/**
	 * Internal refresh edit part.
	 * 
	 * @param editPart
	 *            the edit part
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void internalRefreshEditPart(final EditPart editPart) {
		if (Display.getCurrent() == null) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					internalRefreshEditPart(editPart);
					// refreshOutline();
				}
			});
			return;
		}

		long start = System.currentTimeMillis();

		try {
			diagramEditor.getConfigurationProvider().getContextButtonManager().hideContextButtonsInstantly();

			editPart.refresh();

			long stop = System.currentTimeMillis();
			long time = (stop - start);
			if (time > 500) {
				String output = "refreshEditPart took " + time + " ms."; //$NON-NLS-1$ //$NON-NLS-2$
				T.racer().warning("DiagramEditorInternal.refreshEditPart() ", output); //$NON-NLS-1$
			}
		} catch (NullPointerException e) {
			T.racer().error("refresh edit part problem", e); //$NON-NLS-1$
		}
	}

	/**
	 * Checks if is auto refresh.
	 * 
	 * @return true, if is auto refresh
	 */
	public boolean isAutoRefresh() {
		return autoRefresh;
	}

	/**
	 * Sets the auto refresh.
	 * 
	 * @param value
	 *            the new auto refresh
	 */
	protected void setAutoRefresh(boolean value) {
		autoRefresh = value;
	}

	public boolean isMultipleRefreshSupressionActive() {
		return true;
	}

	/**
	 * Manages the performance cache. Should not be called by external clients.
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 * @since 0.9
	 */
	public boolean shouldRefresh(Object obj) {
		return getRefreshPerformanceCache().shouldRefresh(obj);
	}

	private RefreshPerformanceCache getRefreshPerformanceCache() {
		return refreshPerformanceCache;
	}
}
