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
 *    Bug 336488 - DiagramEditor API
 *    cbrand - Bug 377783 - Dump for figures in connection layer needed
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.feature;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DebugFeature extends AbstractCustomFeature {

	private static final String NAME_DUMP_PICTOGRAM_DATA = "Dump pictogram data"; //$NON-NLS-1$
	private static final String NAME_DUMP_FIGURE_DATA = "Dump figure data"; //$NON-NLS-1$
	private static final String NAME_DUMP_FIGURE_INCL_CONNECTION_DATA = "Dump figure data incl. connections"; //$NON-NLS-1$
	private static final String NAME_DUMP_EDIT_PART_DATA = "Dump editpart tree"; //$NON-NLS-1$
	private static final String NAME_DUMP_ALL = "Dump all data"; //$NON-NLS-1$
	private static final String NAME_REFRESH = "Refresh"; //$NON-NLS-1$

	public static final int TYPE_DUMP_PICTOGRAM_DATA = 0;
	public static final int TYPE_DUMP_FIGURE_DATA = 1;
	public static final int TYPE_DUMP_EDIT_PART_DATA = 2;
	public static final int TYPE_DUMP_ALL = 3;
	public static final int TYPE_REFRESH = 4;
	public static final int TYPE_DUMP_FIGURE_INCL_CONNECTION_DATA = 5;

	private int type;

	public DebugFeature(IFeatureProvider fp, int type) {
		super(fp);
		setType(type);
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0 && pes[0] != null) {
			PictogramElement pe = pes[0];
			IDiagramContainerUI diagramEditor = (IDiagramContainerUI) getDiagramContainer();
			GraphicalEditPart ep = diagramEditor.getDiagramSupport().getEditPartForPictogramElement(pe);
			IFigure figure = diagramEditor.getFigureForPictogramElement(pe);

			switch (getType()) {
			case TYPE_DUMP_PICTOGRAM_DATA:
				GraphitiUiInternal.getTraceService().dumpPictogramModelTree(pe);
				break;
			case TYPE_DUMP_EDIT_PART_DATA:
				GraphitiUiInternal.getTraceService().dumpEditPartTree(ep);
				break;
			case TYPE_DUMP_FIGURE_DATA:
				GraphitiUiInternal.getTraceService().dumpFigureTree(figure);
				break;
			case TYPE_DUMP_FIGURE_INCL_CONNECTION_DATA:
				GraphitiUiInternal.getTraceService().dumpFigureTreeWithConnectionLayer(figure);
				break;
			case TYPE_DUMP_ALL:
				GraphitiUiInternal.getTraceService().dumpPictogramModelTree(pe);
				GraphitiUiInternal.getTraceService().dumpEditPartTree(ep);
				if (checkIfDiagram(pe)) {
					GraphitiUiInternal.getTraceService().dumpFigureTreeWithConnectionLayer(figure);
				} else {
					GraphitiUiInternal.getTraceService().dumpFigureTree(figure);
				}
				break;
			case TYPE_REFRESH:
				ep.refresh();
				break;
			}
		}
	}

	private boolean checkIfDiagram(PictogramElement pe) {
		return pe instanceof Diagram;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	@Override
	public String getName() {
		String ret = ""; //$NON-NLS-1$
		switch (getType()) {
		case TYPE_DUMP_PICTOGRAM_DATA:
			ret = NAME_DUMP_PICTOGRAM_DATA;
			break;
		case TYPE_DUMP_FIGURE_DATA:
			ret = NAME_DUMP_FIGURE_DATA;
			break;
		case TYPE_DUMP_FIGURE_INCL_CONNECTION_DATA:
			ret = NAME_DUMP_FIGURE_INCL_CONNECTION_DATA;
			break;
		case TYPE_DUMP_EDIT_PART_DATA:
			ret = NAME_DUMP_EDIT_PART_DATA;
			break;
		case TYPE_DUMP_ALL:
			ret = NAME_DUMP_ALL;
			break;
		case TYPE_REFRESH:
			ret = NAME_REFRESH;
			break;
		}
		return ret;
	}

	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}

}
