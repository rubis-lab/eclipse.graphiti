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
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPeCreateService {

	/**
	 * Creates a box relative anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new anchor
	 */
	BoxRelativeAnchor createBoxRelativeAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates a chop box anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new anchor
	 */
	ChopboxAnchor createChopboxAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates the connection decorator.
	 * 
	 * @param connection
	 *            the connection
	 * @param active
	 *            the active
	 * @param location
	 *            the location
	 * @param isRelative
	 *            true if the decorator should be positioned relative to the
	 *            connection's midpoint
	 * @return the created ConnectionDecorator Create a ConnectionDecorator and
	 *         add it to the given connection
	 */
	ConnectionDecorator createConnectionDecorator(Connection connection, boolean active, double location, boolean isRelative);

	/**
	 * Creates a container shape inside the given parent container shape.
	 * 
	 * @param parentContainerShape
	 *            the parent container shape
	 * @param active
	 *            true if container shape should be active (means selectable in
	 *            editor)
	 * @return the new container shape
	 */
	ContainerShape createContainerShape(ContainerShape parentContainerShape, boolean active);

	/**
	 * Creates the diagram.
	 * 
	 * @param diagramTypeId
	 *            the diagram type id
	 * @param diagramName
	 *            the diagram name
	 * @param snap
	 *            the snap
	 * @return the diagram
	 * @see createDiagram(String diagramTypeId, String diagramName, int
	 *      gridUnit, boolean snap)
	 */
	Diagram createDiagram(String diagramTypeId, String diagramName, boolean snap);

	/**
	 * Create a diagram.
	 * 
	 * @param diagramTypeId
	 *            id of the diagram type
	 * @param diagramName
	 *            name of the diagram
	 * @param gridUnit
	 *            grid size in pixel; if 0 then no grid will be drawn
	 * @param snap
	 *            true enables snap to grid
	 * @return the new diagram
	 *         <p>
	 *         The following values are set by default:
	 *         <p>
	 *         diagram.setShowGuides(true); <br>
	 */
	Diagram createDiagram(String diagramTypeId, String diagramName, int gridUnit, boolean snap);

	/**
	 * Creates a fix point anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new anchor
	 */
	FixPointAnchor createFixPointAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates a free form connection inside the given diagram.
	 * 
	 * @param diagram
	 *            the diagram
	 * @return the new connection
	 */
	FreeFormConnection createFreeFormConnection(Diagram diagram);

	/**
	 * Creates a container shape inside the given parent container shape.
	 * 
	 * @param parentContainerShape
	 *            the shape's parent
	 * @param active
	 *            true if shape should be active (means selectable in editor)
	 * @return the new shape
	 */
	Shape createShape(ContainerShape parentContainerShape, boolean active);

}
