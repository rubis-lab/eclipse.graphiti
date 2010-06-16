/**
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
 * $Id: PictogramLinkImpl.java,v 1.1 2010/06/16 13:25:09 mwenz Exp $
 */
package org.eclipse.graphiti.mm.links.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.graphiti.mm.links.DiagramLink;
import org.eclipse.graphiti.mm.links.LinksPackage;
import org.eclipse.graphiti.mm.links.PictogramLink;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

import org.eclipse.graphiti.mm.pictograms.impl.PropertyContainerImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pictogram Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl#getPictogramElement <em>Pictogram Element</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl#getDiagramLink <em>Diagram Link</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.links.impl.PictogramLinkImpl#getBusinessObjects <em>Business Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PictogramLinkImpl extends PropertyContainerImpl implements PictogramLink {
	/**
	 * The cached value of the '{@link #getDiagramLink() <em>Diagram Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramLink()
	 * @generated
	 * @ordered
	 */
	protected DiagramLink diagramLink;

	/**
	 * The cached value of the '{@link #getBusinessObjects() <em>Business Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> businessObjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PictogramLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LinksPackage.Literals.PICTOGRAM_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement getPictogramElement() {
		if (eContainerFeatureID() != LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement basicGetPictogramElement() {
		if (eContainerFeatureID() != LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPictogramElement(PictogramElement newPictogramElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPictogramElement, LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPictogramElement(PictogramElement newPictogramElement) {
		if (newPictogramElement != eInternalContainer() || (eContainerFeatureID() != LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT && newPictogramElement != null)) {
			if (EcoreUtil.isAncestor(this, newPictogramElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPictogramElement != null)
				msgs = ((InternalEObject)newPictogramElement).eInverseAdd(this, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, PictogramElement.class, msgs);
			msgs = basicSetPictogramElement(newPictogramElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, newPictogramElement, newPictogramElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramLink getDiagramLink() {
		if (diagramLink != null && diagramLink.eIsProxy()) {
			InternalEObject oldDiagramLink = (InternalEObject)diagramLink;
			diagramLink = (DiagramLink)eResolveProxy(oldDiagramLink);
			if (diagramLink != oldDiagramLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK, oldDiagramLink, diagramLink));
			}
		}
		return diagramLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramLink basicGetDiagramLink() {
		return diagramLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagramLink(DiagramLink newDiagramLink, NotificationChain msgs) {
		DiagramLink oldDiagramLink = diagramLink;
		diagramLink = newDiagramLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK, oldDiagramLink, newDiagramLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagramLink(DiagramLink newDiagramLink) {
		if (newDiagramLink != diagramLink) {
			NotificationChain msgs = null;
			if (diagramLink != null)
				msgs = ((InternalEObject)diagramLink).eInverseRemove(this, LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS, DiagramLink.class, msgs);
			if (newDiagramLink != null)
				msgs = ((InternalEObject)newDiagramLink).eInverseAdd(this, LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS, DiagramLink.class, msgs);
			msgs = basicSetDiagramLink(newDiagramLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK, newDiagramLink, newDiagramLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getBusinessObjects() {
		if (businessObjects == null) {
			businessObjects = new EObjectResolvingEList<EObject>(EObject.class, this, LinksPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS);
		}
		return businessObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPictogramElement((PictogramElement)otherEnd, msgs);
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				if (diagramLink != null)
					msgs = ((InternalEObject)diagramLink).eInverseRemove(this, LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS, DiagramLink.class, msgs);
				return basicSetDiagramLink((DiagramLink)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return basicSetPictogramElement(null, msgs);
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				return basicSetDiagramLink(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, PictogramElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				if (resolve) return getPictogramElement();
				return basicGetPictogramElement();
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				if (resolve) return getDiagramLink();
				return basicGetDiagramLink();
			case LinksPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				return getBusinessObjects();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)newValue);
				return;
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				setDiagramLink((DiagramLink)newValue);
				return;
			case LinksPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				getBusinessObjects().clear();
				getBusinessObjects().addAll((Collection<? extends EObject>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)null);
				return;
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				setDiagramLink((DiagramLink)null);
				return;
			case LinksPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				getBusinessObjects().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LinksPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return basicGetPictogramElement() != null;
			case LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK:
				return diagramLink != null;
			case LinksPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				return businessObjects != null && !businessObjects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PictogramLinkImpl
