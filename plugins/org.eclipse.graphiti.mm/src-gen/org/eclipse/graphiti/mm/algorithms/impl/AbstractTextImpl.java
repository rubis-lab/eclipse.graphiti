/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 423573 - Angles should never be integer
 *    
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.algorithms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getStyleRegions <em>Style Regions</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl#getRotation <em>Rotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractTextImpl extends GraphicsAlgorithmImpl implements AbstractText {
	/**
	 * The cached value of the '{@link #getFont() <em>Font</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFont()
	 * @generated
	 * @ordered
	 */
	protected Font font;

	/**
	 * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final Orientation HORIZONTAL_ALIGNMENT_EDEFAULT = Orientation.ALIGNMENT_LEFT;

	/**
	 * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected Orientation horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final Orientation VERTICAL_ALIGNMENT_EDEFAULT = Orientation.ALIGNMENT_CENTER;

	/**
	 * The cached value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalAlignment()
	 * @generated
	 * @ordered
	 */
	protected Orientation verticalAlignment = VERTICAL_ALIGNMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ANGLE_EDEFAULT = new Integer(0);

	/**
	 * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected Integer angle = ANGLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStyleRegions() <em>Style Regions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleRegions()
	 * @generated
	 * @ordered
	 */
	protected EList<TextStyleRegion> styleRegions;

	/**
	 * The default value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotation()
	 * @generated
	 * @ordered
	 */
	protected static final Double ROTATION_EDEFAULT = new Double(0.0);

	/**
	 * The cached value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotation()
	 * @generated
	 * @ordered
	 */
	protected Double rotation = ROTATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlgorithmsPackage.Literals.ABSTRACT_TEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Font getFont() {
		if (font != null && font.eIsProxy()) {
			InternalEObject oldFont = (InternalEObject)font;
			font = (Font)eResolveProxy(oldFont);
			if (font != oldFont) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlgorithmsPackage.ABSTRACT_TEXT__FONT, oldFont, font));
			}
		}
		return font;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Font basicGetFont() {
		return font;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFont(Font newFont) {
		Font oldFont = font;
		font = newFont;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__FONT, oldFont, font));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHorizontalAlignment(Orientation newHorizontalAlignment) {
		Orientation oldHorizontalAlignment = horizontalAlignment;
		horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT : newHorizontalAlignment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment, horizontalAlignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerticalAlignment(Orientation newVerticalAlignment) {
		Orientation oldVerticalAlignment = verticalAlignment;
		verticalAlignment = newVerticalAlignment == null ? VERTICAL_ALIGNMENT_EDEFAULT : newVerticalAlignment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__VERTICAL_ALIGNMENT, oldVerticalAlignment, verticalAlignment));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public Integer getAngle() {
		Double rotation = getRotation();
		if (rotation == null) {
			return null;
		}
		return (int) Math.round(rotation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void setAngle(Integer newAngle) {
		Integer oldAngle = angle;
		angle = ANGLE_EDEFAULT;
		if (newAngle != null) {
			setRotation(newAngle.doubleValue());
		} else {
			setRotation(null);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__ANGLE, oldAngle, angle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TextStyleRegion> getStyleRegions() {
		if (styleRegions == null) {
			styleRegions = new EObjectContainmentEList.Resolving<TextStyleRegion>(TextStyleRegion.class, this, AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS);
		}
		return styleRegions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public void setRotation(Double newRotation) {
		Double oldRotation = rotation;
		rotation = newRotation;
		Integer oldAngle = angle;
		if (newRotation != null) {
			angle = new Long(Math.round(newRotation)).intValue();
		} else {
			angle = null;
		}
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ABSTRACT_TEXT__ROTATION, oldRotation, rotation));
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.STYLE__ANGLE, oldAngle, angle));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS:
				return ((InternalEList<?>)getStyleRegions()).basicRemove(otherEnd, msgs);
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
			case AlgorithmsPackage.ABSTRACT_TEXT__FONT:
				if (resolve) return getFont();
				return basicGetFont();
			case AlgorithmsPackage.ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT:
				return getHorizontalAlignment();
			case AlgorithmsPackage.ABSTRACT_TEXT__VERTICAL_ALIGNMENT:
				return getVerticalAlignment();
			case AlgorithmsPackage.ABSTRACT_TEXT__ANGLE:
				return getAngle();
			case AlgorithmsPackage.ABSTRACT_TEXT__VALUE:
				return getValue();
			case AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS:
				return getStyleRegions();
			case AlgorithmsPackage.ABSTRACT_TEXT__ROTATION:
				return getRotation();
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
			case AlgorithmsPackage.ABSTRACT_TEXT__FONT:
				setFont((Font)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT:
				setHorizontalAlignment((Orientation)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__VERTICAL_ALIGNMENT:
				setVerticalAlignment((Orientation)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__ANGLE:
				setAngle((Integer)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__VALUE:
				setValue((String)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS:
				getStyleRegions().clear();
				getStyleRegions().addAll((Collection<? extends TextStyleRegion>)newValue);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__ROTATION:
				setRotation((Double)newValue);
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
			case AlgorithmsPackage.ABSTRACT_TEXT__FONT:
				setFont((Font)null);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT:
				setHorizontalAlignment(HORIZONTAL_ALIGNMENT_EDEFAULT);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__VERTICAL_ALIGNMENT:
				setVerticalAlignment(VERTICAL_ALIGNMENT_EDEFAULT);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS:
				getStyleRegions().clear();
				return;
			case AlgorithmsPackage.ABSTRACT_TEXT__ROTATION:
				setRotation(ROTATION_EDEFAULT);
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
			case AlgorithmsPackage.ABSTRACT_TEXT__FONT:
				return font != null;
			case AlgorithmsPackage.ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT:
				return horizontalAlignment != HORIZONTAL_ALIGNMENT_EDEFAULT;
			case AlgorithmsPackage.ABSTRACT_TEXT__VERTICAL_ALIGNMENT:
				return verticalAlignment != VERTICAL_ALIGNMENT_EDEFAULT;
			case AlgorithmsPackage.ABSTRACT_TEXT__ANGLE:
				return ANGLE_EDEFAULT == null ? angle != null : !ANGLE_EDEFAULT.equals(angle);
			case AlgorithmsPackage.ABSTRACT_TEXT__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case AlgorithmsPackage.ABSTRACT_TEXT__STYLE_REGIONS:
				return styleRegions != null && !styleRegions.isEmpty();
			case AlgorithmsPackage.ABSTRACT_TEXT__ROTATION:
				return ROTATION_EDEFAULT == null ? rotation != null : !ROTATION_EDEFAULT.equals(rotation);
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
		result.append(" (horizontalAlignment: ");
		result.append(horizontalAlignment);
		result.append(", verticalAlignment: ");
		result.append(verticalAlignment);
		result.append(", angle: ");
		result.append(angle);
		result.append(", value: ");
		result.append(value);
		result.append(", rotation: ");
		result.append(rotation);
		result.append(')');
		return result.toString();
	}

} //AbstractTextImpl
