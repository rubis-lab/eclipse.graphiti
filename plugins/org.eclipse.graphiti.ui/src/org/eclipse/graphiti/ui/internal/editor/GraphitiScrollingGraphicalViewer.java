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
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    fvelasco - Bug 323356 - Mouse-wheel support for scrolling and zooming
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.util.gef.MouseWheelHorizontalScrollHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;

public class GraphitiScrollingGraphicalViewer extends ScrollingGraphicalViewer {

	private DiagramBehavior diagramBehavior;

	public GraphitiScrollingGraphicalViewer(DiagramBehavior diagramBehavior) {
		this.setDiagramBehavior(diagramBehavior);
		setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
		setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD2), MouseWheelHorizontalScrollHandler.SINGLETON);
	}

	@Override
	public void select(EditPart editpart) {
		IToolBehaviorProvider tbp = getDiagramBehavior().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
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
		IToolBehaviorProvider tbp = getDiagramBehavior().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
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
		IToolBehaviorProvider tbp = getDiagramBehavior().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
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

	private void setDiagramBehavior(DiagramBehavior diagramBehavior) {
		this.diagramBehavior = diagramBehavior;
	}

	protected DiagramBehavior getDiagramBehavior() {
		return diagramBehavior;
	}
}
