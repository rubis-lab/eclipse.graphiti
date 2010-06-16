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
 * $Id: DiagramLinkImpl.java,v 1.1 2010/06/16 13:25:09 mwenz Exp $
 */
package org.eclipse.graphiti.mm.links.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.mm.links.DiagramLink;
import org.eclipse.graphiti.mm.links.LinksPackage;
import org.eclipse.graphiti.mm.links.PictogramLink;

import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.links.impl.DiagramLinkImpl#getPictogramLinks <em>Pictogram Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramLinkImpl extends EObjectImpl implements DiagramLink {
	/**
	 * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagram()
	 * @generated
	 * @ordered
	 */
	protected Diagram diagram;

	/**
	 * The cached value of the '{@link #getPictogramLinks() <em>Pictogram Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPictogramLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<PictogramLink> pictogramLinks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LinksPackage.Literals.DIAGRAM_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram getDiagram() {
		if (diagram != null && diagram.eIsProxy()) {
			InternalEObject oldDiagram = (InternalEObject)diagram;
			diagram = (Diagram)eResolveProxy(oldDiagram);
			if (diagram != oldDiagram) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LinksPackage.DIAGRAM_LINK__DIAGRAM, oldDiagram, diagram));
			}
		}
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram basicGetDiagram() {
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(Diagram newDiagram) {
		Diagram oldDiagram = diagram;
		diagram = newDiagram;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.DIAGRAM_LINK__DIAGRAM, oldDiagram, diagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PictogramLink> getPictogramLinks() {
		if (pictogramLinks == null) {
			pictogramLinks = new EObjectWithInverseResolvingEList<PictogramLink>(PictogramLink.class, this, LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS, LinksPackage.PICTOGRAM_LINK__DIAGRAM_LINK);
		}
		return pictogramLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPictogramLinks()).basicAdd(otherEnd, msgs);
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
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				return ((InternalEList<?>)getPictogramLinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LinksPackage.DIAGRAM_LINK__DIAGRAM:
				if (resolve) return getDiagram();
				return basicGetDiagram();
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				return getPictogramLinks();
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
			case LinksPackage.DIAGRAM_LINK__DIAGRAM:
				setDiagram((Diagram)newValue);
				return;
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				getPictogramLinks().clear();
				getPictogramLinks().addAll((Collection<? extends PictogramLink>)newValue);
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
			case LinksPackage.DIAGRAM_LINK__DIAGRAM:
				setDiagram((Diagram)null);
				return;
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				getPictogramLinks().clear();
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
			case LinksPackage.DIAGRAM_LINK__DIAGRAM:
				return diagram != null;
			case LinksPackage.DIAGRAM_LINK__PICTOGRAM_LINKS:
				return pictogramLinks != null && !pictogramLinks.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DiagramLinkImpl
