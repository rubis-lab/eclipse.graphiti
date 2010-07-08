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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.links.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Property;

/**
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILinkService {

	/**
	 * Returns all business objects which are linked to the given pictogram
	 * element.
	 * 
	 * @param pictogramElement
	 *            The pictogram element for which to return the business
	 *            objects.
	 * @return The business objects which are linked to the given pictogram
	 *         element. Can be empty but not null.
	 * @see #link(PictogramElement, Object[])
	 */
	EObject[] getAllBusinessObjectsForLinkedPictogramElement(PictogramElement pictogramElement);

	/**
	 * Returns the first of possibly several business objects which are linked
	 * to the given pictogram element. This is a convenience method for
	 * {@link #getAllBusinessObjectsForPictogramElement(PictogramElement)},
	 * because in many usecases only a single business object is linked.
	 * 
	 * @param pictogramElement
	 *            The pictogram element for which to return the business object.
	 * @return The first of possibly several business objects which are linked
	 *         to the given pictogram element. Can be null.
	 * @see #link(PictogramElement, Object)
	 */
	EObject getBusinessObjectForLinkedPictogramElement(PictogramElement pictogramElement);

	/**
	 * Returns the pictogram link referencing the given pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the pictogram link referencing the given pictogram element
	 */
	PictogramLink getLinkForPictogramElement(PictogramElement pictogramElement);

	/**
	 * get all pictogram elements which references the given eObject.
	 * 
	 * @param eObject
	 *            the ref object
	 * @param diagram
	 *            the diagram
	 * @return the pictogram elements
	 */
	List<PictogramElement> getPictogramElements(Diagram diagram, EObject eObject);

	/**
	 * Gets the pictogram elements.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param eObjects
	 *            business objects
	 * @param onlyActive
	 *            if true, then only active pictogram elements of the diagram
	 *            will be considered; if false all pictogram elements will be
	 *            considered
	 * @return all (active) pictogram elements in the diagram, which have at
	 *         least one reference to one of the business objects
	 */
	List<PictogramElement> getPictogramElements(Diagram diagram, List<EObject> eObjects, boolean onlyActive);

	/**
	 * Checks existence and value of a property to a given pictogram element. It
	 * is intended to use this property to be able to disinguish multiple
	 * pictogram elements linked to same domain model element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param propertyValue
	 *            the value to check against the property
	 * @return true if property exists an has the given value; false if not
	 */
	boolean hasLinkProperty(PictogramElement pictogramElement, String propertyValue);

	/**
	 * Adds or modifies a property to a given pictogram element. It is intended
	 * to use this property to be able to disinguish multiple pictogram elements
	 * linked to same domain model element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param propertyValue
	 *            the new property value
	 */
	void setLinkProperty(PictogramElement pictogramElement, String propertyValue);

	/**
	 * Gets the link property.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the link property
	 */
	Property getLinkProperty(PictogramElement pictogramElement);
}
