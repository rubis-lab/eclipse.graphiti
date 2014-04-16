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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramBehavior;

/**
 * This interface is intended as UI dependent base interface for the common
 * behavior object that describes and implements the behavior of diagrams and
 * can be reused within all kinds of diagram containers, e.g. editors, views or
 * plain UI composites.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           DiagramBehavior instead.
 * 
 * @since 0.10
 */
public interface IDiagramBehaviorUI extends IDiagramBehavior {

	/**
	 * Calculates the mouse location depending on scrollbars and zoom factor.
	 * 
	 * @param nativeLocation
	 *            the native location given as {@link Point}
	 * @return the {@link Point} of the real mouse location
	 */
	public Point calculateRealMouseLocation(Point nativeLocation);

	/**
	 * Returns the GEF edit domain as needed for some of the feature
	 * functionality in Graphiti.
	 * 
	 * @return the {@link DefaultEditDomain} used in this behavior's editor
	 * @see GraphicalEditor#getEditDomain()
	 */
	public DefaultEditDomain getEditDomain();

	/**
	 * Method to retrieve the GEF {@link EditPart} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the GEF
	 *            representation for
	 * @return the GEF {@link GraphicalEditPart} that represents the given
	 *         {@link PictogramElement}.
	 */
	public GraphicalEditPart getEditPartForPictogramElement(PictogramElement pe);

	/**
	 * Method to retrieve the Draw2D {@link IFigure} for a given
	 * {@link PictogramElement}.
	 * 
	 * @param pe
	 *            the {@link PictogramElement} to retrieve the Draw2D
	 *            representation for
	 * @return the Draw2D {@link IFigure} that represents the given
	 *         {@link PictogramElement}.
	 */
	public IFigure getFigureForPictogramElement(PictogramElement pe);
}
