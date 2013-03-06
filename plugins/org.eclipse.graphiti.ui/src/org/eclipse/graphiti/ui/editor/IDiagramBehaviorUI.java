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
package org.eclipse.graphiti.ui.editor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramBehavior;

/**
 * @since 0.10
 */
public interface IDiagramBehaviorUI extends IDiagramBehavior {

	public Point calculateRealMouseLocation(Point nativeLocation);

	public DefaultEditDomain getEditDomain();

	public GraphicalEditPart getEditPartForPictogramElement(PictogramElement pe);

	public IFigure getFigureForPictogramElement(PictogramElement pe);
}
