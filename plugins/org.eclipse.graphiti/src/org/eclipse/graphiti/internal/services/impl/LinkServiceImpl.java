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
package org.eclipse.graphiti.internal.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.internal.ExternalPictogramLink;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.links.DiagramLink;
import org.eclipse.graphiti.mm.links.LinksFactory;
import org.eclipse.graphiti.mm.links.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Property;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class LinkServiceImpl implements ILinkService {

	private long cumulativeTime = 0; // just for performance tracing
	private int cumulativeCalls = 0; // just for performance tracing

	// property-key for the property of a pictgram-element which is part of a
	// link-object and the business-object(s) of the link-object is not unique
	private static final String KEY_LINK_PROPERTY = "keyLinkProperty"; //$NON-NLS-1$

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
	public EObject[] getAllBusinessObjectsForLinkedPictogramElement(PictogramElement pictogramElement) {
		EObject[] ret = new EObject[0];
		if (pictogramElement != null && GraphitiInternal.getEmfService().isObjectAlive(pictogramElement)) {
			PictogramLink pl = getLinkForPictogramElement(pictogramElement);
			if (pl != null && (!(pl instanceof ExternalPictogramLink)) && GraphitiInternal.getEmfService().isObjectAlive(pl)) {
				List<EObject> boList = pl.getBusinessObjects();
				if (boList != null && boList.size() > 0) {
					ret = boList.toArray(new EObject[boList.size()]);
				}
			}
		}
		return ret;
	}

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
	public EObject getBusinessObjectForLinkedPictogramElement(PictogramElement pictogramElement) {
		EObject ret = null;
		EObject eObject[] = getAllBusinessObjectsForLinkedPictogramElement(pictogramElement);
		if (eObject != null && eObject.length > 0) {
			ret = eObject[0];
		}
		return ret;
	}

	/**
	 * Gets the diagram link.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param createIfNotYetThere
	 *            create if not yet there
	 * @return the diagram link
	 */
	public DiagramLink getDiagramLink(Diagram diagram, boolean createIfNotYetThere) {

		if (diagram == null) {
			return null;
		}

		//		DiagramLinkReferencesDiagram diagramLinkReferencesDiagram = PackageUtil.getLinksPackage(diagram).getDiagramLinkReferencesDiagram();
		//		DiagramLink ret = diagramLinkReferencesDiagram.getLink(diagram);

		//		DiagramLinkReferencesDiagram.getLink(diagram);

		Resource resource = diagram.eResource();
		EList<EObject> contents = resource.getContents();
		for (EObject object : contents) {
			if (object instanceof DiagramLink) {
				DiagramLink dl = (DiagramLink) object;
				if (diagram.equals(dl.getDiagram())) {
					return dl;
				}
			}
		}

		if (createIfNotYetThere) {
			return createDiagramLink(diagram);
		}
		return null;
	}

	/**
	 * Returns the pictogram link referencing the given pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the pictogram link referencing the given pictogram element
	 */
	public PictogramLink getLinkForPictogramElement(PictogramElement pictogramElement) {
		long start = System.currentTimeMillis();

		PictogramLink ret = null;

		if (pictogramElement != null && GraphitiInternal.getEmfService().isObjectAlive(pictogramElement)) {
			ret = pictogramElement.getLink();
		}

		if (T.racer().info()) {
			long end = System.currentTimeMillis();
			long time = (end - start);
			cumulativeTime += time;
			cumulativeCalls++;
			double averageTime = (double) cumulativeTime / (double) cumulativeCalls;
			String averageTimeString = new DecimalFormat("0.000").format(averageTime); //$NON-NLS-1$
			T.racer().info("LinkUtil.getLinkForPictogramElement() took " + time + " ms (cumulative: " + cumulativeTime + " ms for " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ cumulativeCalls + " calls; average: " + averageTimeString + " ms/call)"); //$NON-NLS-1$ //$NON-NLS-2$
			T.racer().info("LinkUtil.getLinkForPictogramElement() average time: " + averageTimeString + " ms/call)"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (ret == null) {
			Property independentProperty = Graphiti.getPeService().getProperty(pictogramElement,
					ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
			if (independentProperty != null) {
				ret = new ExternalPictogramLink();
				ret.setPictogramElement(pictogramElement);
			}
		}

		return ret;
	}

	/**
	 * get all pictogram elements which references the given eObject.
	 * 
	 * @param eObject
	 *            the ref object
	 * @param diagramLink
	 *            the diagram link
	 * @return the pictogram elements
	 */
	public List<PictogramElement> getPictogramElements(DiagramLink diagramLink, EObject eObject) {
		List<PictogramElement> ret = new ArrayList<PictogramElement>();
		if (eObject != null && GraphitiInternal.getEmfService().isObjectAlive(eObject)) {
			Collection<PictogramLink> links = diagramLink.getPictogramLinks();
			for (PictogramLink link : links) {
				EObject bo = getBusinessObjectForLinkedPictogramElement(link.getPictogramElement());
				if (EcoreUtil.equals(eObject, bo)) {
					ret.add(link.getPictogramElement());
				}
			}
		}
		return ret;
	}

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
	public List<PictogramElement> getPictogramElements(Diagram diagram, List<EObject> eObjects, boolean onlyActive) {
		List<PictogramElement> ret = new ArrayList<PictogramElement>();
		if (diagram != null && eObjects != null && eObjects.size() > 0) {
			DiagramLink diagramLink = getDiagramLink(diagram, true);
			if (diagramLink != null) {
				Collection<PictogramLink> links = diagramLink.getPictogramLinks();
				for (PictogramLink link : links) {
					PictogramElement pe = link.getPictogramElement();
					if (!onlyActive || pe.isActive()) {
						EObject[] bos = getAllBusinessObjectsForLinkedPictogramElement(pe);
						for (EObject bo : bos) {
							if (eObjects.contains(bo)) {
								ret.add(pe);
								break;
							}
						}
					}
				}
			}
		}
		return ret;
	}

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
	public boolean hasLinkProperty(PictogramElement pictogramElement, String propertyValue) {
		boolean ret = false;
		if (pictogramElement != null && propertyValue != null) {
			Property property = getLinkProperty(pictogramElement);
			if (property != null) {
				if (propertyValue.equals(property.getValue())) {
					ret = true;
				}
			}
		}
		return ret;
	}

	/**
	 * Adds or modifies a property to a given pictogram element. It is intended
	 * to use this property to be able to distinguish multiple pictogram
	 * elements linked to same domain model element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param propertyValue
	 *            the new property value
	 */
	public void setLinkProperty(PictogramElement pictogramElement, String propertyValue) {
		Graphiti.getPeService().setPropertyValue(pictogramElement, KEY_LINK_PROPERTY, propertyValue);
	}

	/**
	 * Gets the link property.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the link property
	 */
	public Property getLinkProperty(PictogramElement pictogramElement) {
		return Graphiti.getPeService().getProperty(pictogramElement, KEY_LINK_PROPERTY);
	}

	private static DiagramLink createDiagramLink(Diagram diagram) {
		DiagramLink ret = LinksFactory.eINSTANCE.createDiagramLink();
		ret.setDiagram(diagram);
		diagram.eResource().getContents().add(ret);
		return ret;
	}
}
