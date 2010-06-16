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
 * $Id: LinkToDiagramImpl.java,v 1.1 2010/06/16 13:24:59 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.datatypes.ViewPort;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.LinkToDiagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link To Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl#isAsIcon <em>As Icon</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl#getViewport <em>Viewport</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.LinkToDiagramImpl#getDiagram <em>Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkToDiagramImpl extends ShapeImpl implements LinkToDiagram {
	/**
	 * The default value of the '{@link #isAsIcon() <em>As Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsIcon()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AS_ICON_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAsIcon() <em>As Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsIcon()
	 * @generated
	 * @ordered
	 */
	protected boolean asIcon = AS_ICON_EDEFAULT;

	/**
	 * The cached value of the '{@link #getViewport() <em>Viewport</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewport()
	 * @generated
	 * @ordered
	 */
	protected ViewPort viewport;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkToDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.LINK_TO_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAsIcon() {
		return asIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsIcon(boolean newAsIcon) {
		boolean oldAsIcon = asIcon;
		asIcon = newAsIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.LINK_TO_DIAGRAM__AS_ICON, oldAsIcon, asIcon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewPort getViewport() {
		if (viewport != null && viewport.eIsProxy()) {
			InternalEObject oldViewport = (InternalEObject)viewport;
			viewport = (ViewPort)eResolveProxy(oldViewport);
			if (viewport != oldViewport) {
				InternalEObject newViewport = (InternalEObject)viewport;
				NotificationChain msgs = oldViewport.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, null, null);
				if (newViewport.eInternalContainer() == null) {
					msgs = newViewport.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, oldViewport, viewport));
			}
		}
		return viewport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewPort basicGetViewport() {
		return viewport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetViewport(ViewPort newViewport, NotificationChain msgs) {
		ViewPort oldViewport = viewport;
		viewport = newViewport;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, oldViewport, newViewport);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewport(ViewPort newViewport) {
		if (newViewport != viewport) {
			NotificationChain msgs = null;
			if (viewport != null)
				msgs = ((InternalEObject)viewport).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, null, msgs);
			if (newViewport != null)
				msgs = ((InternalEObject)newViewport).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, null, msgs);
			msgs = basicSetViewport(newViewport, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT, newViewport, newViewport));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM, oldDiagram, diagram));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM, oldDiagram, diagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT:
				return basicSetViewport(null, msgs);
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
			case PictogramsPackage.LINK_TO_DIAGRAM__AS_ICON:
				return isAsIcon();
			case PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT:
				if (resolve) return getViewport();
				return basicGetViewport();
			case PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM:
				if (resolve) return getDiagram();
				return basicGetDiagram();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PictogramsPackage.LINK_TO_DIAGRAM__AS_ICON:
				setAsIcon((Boolean)newValue);
				return;
			case PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT:
				setViewport((ViewPort)newValue);
				return;
			case PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM:
				setDiagram((Diagram)newValue);
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
			case PictogramsPackage.LINK_TO_DIAGRAM__AS_ICON:
				setAsIcon(AS_ICON_EDEFAULT);
				return;
			case PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT:
				setViewport((ViewPort)null);
				return;
			case PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM:
				setDiagram((Diagram)null);
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
			case PictogramsPackage.LINK_TO_DIAGRAM__AS_ICON:
				return asIcon != AS_ICON_EDEFAULT;
			case PictogramsPackage.LINK_TO_DIAGRAM__VIEWPORT:
				return viewport != null;
			case PictogramsPackage.LINK_TO_DIAGRAM__DIAGRAM:
				return diagram != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (asIcon: ");
		result.append(asIcon);
		result.append(')');
		return result.toString();
	}

} //LinkToDiagramImpl
