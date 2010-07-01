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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ExposeHelper;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.fixed.FixedScrollingGraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * The Class GFScrollingGraphicalViewer.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFScrollingGraphicalViewer extends FixedScrollingGraphicalViewer {

	/**
	 * The root figure.
	 */
	IFigure rootFigure;

	/**
	 * The diagram editor.
	 */
	private DiagramEditor diagramEditor = null;

	/**
	 * Constructs a ScrollingGraphicalViewer;.
	 * 
	 * @param diagramEditor
	 *            the diagram editor
	 */
	public GFScrollingGraphicalViewer(DiagramEditor diagramEditor) {
		setDiagramEditor(diagramEditor);
	}

	/**
	 * Creates the gfw control.
	 * 
	 * @param parent
	 *            the parent
	 * @return the control
	 */
	public final Control createGFWControl(Composite parent) {
		GFFigureCanvas canvas = new GFFigureCanvas(parent, getLightweightSystem(), getDiagramEditor());
		canvas.setData(new String("name"), canvas.getClass().toString()); //$NON-NLS-1$
		setControl(canvas);
		installRootFigure();
		return canvas;
	}

	private void installRootFigure() {
		if (getGFWFigureCanvas() == null)
			return;
		if (rootFigure instanceof Viewport)
			getGFWFigureCanvas().setViewport((Viewport) rootFigure);
		else
			getGFWFigureCanvas().setContents(rootFigure);
	}

	/**
	 * Gets the gFW figure canvas.
	 * 
	 * @return the gFW figure canvas
	 */
	protected GFFigureCanvas getGFWFigureCanvas() {
		return (GFFigureCanvas) getControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.ScrollingGraphicalViewer#setRootFigure(org.eclipse
	 * .draw2d.IFigure)
	 */
	@Override
	protected void setRootFigure(IFigure figure) {
		rootFigure = figure;
		installRootFigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.ScrollingGraphicalViewer#reveal(org.eclipse.
	 * gef.EditPart)
	 */
	@Override
	public void reveal(EditPart part) {

		if (part == null)
			return;
		EditPart current = part.getParent();
		while (current != null) {
			ExposeHelper helper = (ExposeHelper) current.getAdapter(ExposeHelper.class);
			if (helper != null)
				helper.exposeDescendant(part);
			current = current.getParent();
		}
		AccessibleEditPart acc = (AccessibleEditPart) part.getAdapter(AccessibleEditPart.class);
		if (acc != null)
			getControl().getAccessible().setFocus(acc.getAccessibleID());

		Viewport port = getGFWFigureCanvas().getViewport();
		IFigure target = ((GraphicalEditPart) part).getFigure();
		Rectangle exposeRegion = target.getBounds().getCopy();
		target = target.getParent();
		while (target != null && target != port) {
			target.translateToParent(exposeRegion);
			target = target.getParent();
		}
		exposeRegion.expand(5, 5);

		Dimension viewportSize = port.getClientArea().getSize();

		Point topLeft = exposeRegion.getTopLeft();
		Point bottomRight = exposeRegion.getBottomRight().translate(viewportSize.getNegated());
		Point finalLocation = new Point();
		if (viewportSize.width < exposeRegion.width)
			finalLocation.x = Math.min(bottomRight.x, Math.max(topLeft.x, port.getViewLocation().x));
		else
			finalLocation.x = Math.min(topLeft.x, Math.max(bottomRight.x, port.getViewLocation().x));

		if (viewportSize.height < exposeRegion.height)
			finalLocation.y = Math.min(bottomRight.y, Math.max(topLeft.y, port.getViewLocation().y));
		else
			finalLocation.y = Math.min(topLeft.y, Math.max(bottomRight.y, port.getViewLocation().y));

		getGFWFigureCanvas().scrollSmoothTo(finalLocation.x, finalLocation.y);
	}

	@Override
	public void select(EditPart editpart) {
		IToolBehaviorProvider tbp = getDiagramEditor().getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		boolean connectionPossible = tbp.isConnectionSelectionEnabled();
	
		if (connectionPossible) {
			super.select(editpart);
			return;
		} else {
			Object model = editpart.getModel();
			if (!(model instanceof Connection)) {
				super.select(editpart);
				return;
			}
		}
	}

	@Override
	public void setSelection(ISelection newSelection) {
		IToolBehaviorProvider tbp = getDiagramEditor().getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		boolean multiPossible = tbp.isMultiSelectionEnabled();
		boolean connectionPossible = tbp.isConnectionSelectionEnabled();
	
		// default case
		if (multiPossible && connectionPossible) {
			super.setSelection(newSelection);
			return;
		}
	
		// multi possible but no connection
		if (multiPossible && !connectionPossible) {
			boolean change = false;
			List<Object> l = new ArrayList<Object>();
			if (newSelection instanceof IStructuredSelection) {
				IStructuredSelection strSel = (IStructuredSelection) newSelection;
				for (int i = 0; i < strSel.toArray().length; i++) {
					Object o = strSel.toArray()[i];
					if (o instanceof EditPart) {
						EditPart editpart = (EditPart) o;
						if (editpart.getModel() instanceof Connection) {
							change = true;
							continue;
						}
					}
					l.add(o);
				}
			}
			if (change) {
				newSelection = new StructuredSelection(l);
			}
			super.setSelection(newSelection);
			return;
		}
	
		// connection possible but no multi select
		if (!multiPossible && connectionPossible) {
			if (newSelection instanceof IStructuredSelection) {
				IStructuredSelection strSel = (IStructuredSelection) newSelection;
				for (int i = 0; i < strSel.toArray().length; i++) {
					Object o = strSel.toArray()[i];
					if (o instanceof EditPart) {
						EditPart ep = (EditPart) o;
						select(ep);
						return;
					}
				}
				deselectAll();
			}
			return;
		}
	
		// no multi and no connection selection
		if (!multiPossible && !connectionPossible) {
			if (newSelection instanceof IStructuredSelection) {
				IStructuredSelection strSel = (IStructuredSelection) newSelection;
				for (int i = 0; i < strSel.toArray().length; i++) {
					Object o = strSel.toArray()[i];
					if (o instanceof EditPart) {
						EditPart ep = (EditPart) o;
						if (!(ep.getModel() instanceof Connection)) {
							select(ep);
							return;
						}
					}
				}
				deselectAll();
			}
			return;
		}
	}

	@Override
	public void appendSelection(EditPart editpart) {
		IToolBehaviorProvider tbp = getDiagramEditor().getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
		boolean multiPossible = tbp.isMultiSelectionEnabled();
		boolean connectionPossible = tbp.isConnectionSelectionEnabled();
	
		// default case
		if (multiPossible && connectionPossible) {
			super.appendSelection(editpart);
			return;
		}
	
		// multi possible but no connection
		if (multiPossible && !connectionPossible) {
			Object model = editpart.getModel();
			if (model instanceof Connection) {
				return;
			} else {
				super.appendSelection(editpart);
				return;
			}
		}
	
		// connection possible but no multi select
		if (!multiPossible && connectionPossible) {
			deselectAll();
			super.appendSelection(editpart);
			return;
		}
	
		// no multi and no connection selection
		if (!multiPossible && !connectionPossible) {
			Object model = editpart.getModel();
			if (model instanceof Connection) {
				return;
			} else {
				deselectAll();
				super.appendSelection(editpart);
				return;
			}
		}
	}

	private void setDiagramEditor(DiagramEditor diagramEditor) {
		this.diagramEditor = diagramEditor;
	}

	protected DiagramEditor getDiagramEditor() {
		return diagramEditor;
	}
	
	
}
