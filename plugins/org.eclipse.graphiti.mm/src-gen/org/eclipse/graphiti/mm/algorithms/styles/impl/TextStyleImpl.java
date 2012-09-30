/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.algorithms.styles.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#isUnderline <em>Underline</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getUnderlineStyle <em>Underline Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#isStrikeout <em>Strikeout</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getUnderlineColor <em>Underline Color</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.TextStyleImpl#getStrikeoutColor <em>Strikeout Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextStyleImpl extends EObjectImpl implements TextStyle {
	/**
	 * The default value of the '{@link #isUnderline() <em>Underline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNDERLINE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnderline() <em>Underline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderline()
	 * @generated
	 * @ordered
	 */
	protected boolean underline = UNDERLINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnderlineStyle() <em>Underline Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnderlineStyle()
	 * @generated
	 * @ordered
	 */
	protected static final UnderlineStyle UNDERLINE_STYLE_EDEFAULT = UnderlineStyle.UNDERLINE_SINGLE;

	/**
	 * The cached value of the '{@link #getUnderlineStyle() <em>Underline Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnderlineStyle()
	 * @generated
	 * @ordered
	 */
	protected UnderlineStyle underlineStyle = UNDERLINE_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isStrikeout() <em>Strikeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrikeout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRIKEOUT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStrikeout() <em>Strikeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrikeout()
	 * @generated
	 * @ordered
	 */
	protected boolean strikeout = STRIKEOUT_EDEFAULT;

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
	 * The cached value of the '{@link #getForeground() <em>Foreground</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeground()
	 * @generated
	 * @ordered
	 */
	protected Color foreground;

	/**
	 * The cached value of the '{@link #getBackground() <em>Background</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected Color background;

	/**
	 * The cached value of the '{@link #getUnderlineColor() <em>Underline Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnderlineColor()
	 * @generated
	 * @ordered
	 */
	protected Color underlineColor;

	/**
	 * The cached value of the '{@link #getStrikeoutColor() <em>Strikeout Color</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrikeoutColor()
	 * @generated
	 * @ordered
	 */
	protected Color strikeoutColor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextStyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylesPackage.Literals.TEXT_STYLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnderline() {
		return underline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderline(boolean newUnderline) {
		boolean oldUnderline = underline;
		underline = newUnderline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__UNDERLINE, oldUnderline, underline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnderlineStyle getUnderlineStyle() {
		return underlineStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderlineStyle(UnderlineStyle newUnderlineStyle) {
		UnderlineStyle oldUnderlineStyle = underlineStyle;
		underlineStyle = newUnderlineStyle == null ? UNDERLINE_STYLE_EDEFAULT : newUnderlineStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__UNDERLINE_STYLE, oldUnderlineStyle, underlineStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStrikeout() {
		return strikeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrikeout(boolean newStrikeout) {
		boolean oldStrikeout = strikeout;
		strikeout = newStrikeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__STRIKEOUT, oldStrikeout, strikeout));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.TEXT_STYLE__FONT, oldFont, font));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__FONT, oldFont, font));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getForeground() {
		if (foreground != null && foreground.eIsProxy()) {
			InternalEObject oldForeground = (InternalEObject)foreground;
			foreground = (Color)eResolveProxy(oldForeground);
			if (foreground != oldForeground) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.TEXT_STYLE__FOREGROUND, oldForeground, foreground));
			}
		}
		return foreground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetForeground() {
		return foreground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForeground(Color newForeground) {
		Color oldForeground = foreground;
		foreground = newForeground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__FOREGROUND, oldForeground, foreground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getBackground() {
		if (background != null && background.eIsProxy()) {
			InternalEObject oldBackground = (InternalEObject)background;
			background = (Color)eResolveProxy(oldBackground);
			if (background != oldBackground) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.TEXT_STYLE__BACKGROUND, oldBackground, background));
			}
		}
		return background;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetBackground() {
		return background;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackground(Color newBackground) {
		Color oldBackground = background;
		background = newBackground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__BACKGROUND, oldBackground, background));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getUnderlineColor() {
		if (underlineColor != null && underlineColor.eIsProxy()) {
			InternalEObject oldUnderlineColor = (InternalEObject)underlineColor;
			underlineColor = (Color)eResolveProxy(oldUnderlineColor);
			if (underlineColor != oldUnderlineColor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.TEXT_STYLE__UNDERLINE_COLOR, oldUnderlineColor, underlineColor));
			}
		}
		return underlineColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetUnderlineColor() {
		return underlineColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderlineColor(Color newUnderlineColor) {
		Color oldUnderlineColor = underlineColor;
		underlineColor = newUnderlineColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__UNDERLINE_COLOR, oldUnderlineColor, underlineColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getStrikeoutColor() {
		if (strikeoutColor != null && strikeoutColor.eIsProxy()) {
			InternalEObject oldStrikeoutColor = (InternalEObject)strikeoutColor;
			strikeoutColor = (Color)eResolveProxy(oldStrikeoutColor);
			if (strikeoutColor != oldStrikeoutColor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR, oldStrikeoutColor, strikeoutColor));
			}
		}
		return strikeoutColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetStrikeoutColor() {
		return strikeoutColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrikeoutColor(Color newStrikeoutColor) {
		Color oldStrikeoutColor = strikeoutColor;
		strikeoutColor = newStrikeoutColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR, oldStrikeoutColor, strikeoutColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StylesPackage.TEXT_STYLE__UNDERLINE:
				return isUnderline();
			case StylesPackage.TEXT_STYLE__UNDERLINE_STYLE:
				return getUnderlineStyle();
			case StylesPackage.TEXT_STYLE__STRIKEOUT:
				return isStrikeout();
			case StylesPackage.TEXT_STYLE__FONT:
				if (resolve) return getFont();
				return basicGetFont();
			case StylesPackage.TEXT_STYLE__FOREGROUND:
				if (resolve) return getForeground();
				return basicGetForeground();
			case StylesPackage.TEXT_STYLE__BACKGROUND:
				if (resolve) return getBackground();
				return basicGetBackground();
			case StylesPackage.TEXT_STYLE__UNDERLINE_COLOR:
				if (resolve) return getUnderlineColor();
				return basicGetUnderlineColor();
			case StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR:
				if (resolve) return getStrikeoutColor();
				return basicGetStrikeoutColor();
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
			case StylesPackage.TEXT_STYLE__UNDERLINE:
				setUnderline((Boolean)newValue);
				return;
			case StylesPackage.TEXT_STYLE__UNDERLINE_STYLE:
				setUnderlineStyle((UnderlineStyle)newValue);
				return;
			case StylesPackage.TEXT_STYLE__STRIKEOUT:
				setStrikeout((Boolean)newValue);
				return;
			case StylesPackage.TEXT_STYLE__FONT:
				setFont((Font)newValue);
				return;
			case StylesPackage.TEXT_STYLE__FOREGROUND:
				setForeground((Color)newValue);
				return;
			case StylesPackage.TEXT_STYLE__BACKGROUND:
				setBackground((Color)newValue);
				return;
			case StylesPackage.TEXT_STYLE__UNDERLINE_COLOR:
				setUnderlineColor((Color)newValue);
				return;
			case StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR:
				setStrikeoutColor((Color)newValue);
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
			case StylesPackage.TEXT_STYLE__UNDERLINE:
				setUnderline(UNDERLINE_EDEFAULT);
				return;
			case StylesPackage.TEXT_STYLE__UNDERLINE_STYLE:
				setUnderlineStyle(UNDERLINE_STYLE_EDEFAULT);
				return;
			case StylesPackage.TEXT_STYLE__STRIKEOUT:
				setStrikeout(STRIKEOUT_EDEFAULT);
				return;
			case StylesPackage.TEXT_STYLE__FONT:
				setFont((Font)null);
				return;
			case StylesPackage.TEXT_STYLE__FOREGROUND:
				setForeground((Color)null);
				return;
			case StylesPackage.TEXT_STYLE__BACKGROUND:
				setBackground((Color)null);
				return;
			case StylesPackage.TEXT_STYLE__UNDERLINE_COLOR:
				setUnderlineColor((Color)null);
				return;
			case StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR:
				setStrikeoutColor((Color)null);
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
			case StylesPackage.TEXT_STYLE__UNDERLINE:
				return underline != UNDERLINE_EDEFAULT;
			case StylesPackage.TEXT_STYLE__UNDERLINE_STYLE:
				return underlineStyle != UNDERLINE_STYLE_EDEFAULT;
			case StylesPackage.TEXT_STYLE__STRIKEOUT:
				return strikeout != STRIKEOUT_EDEFAULT;
			case StylesPackage.TEXT_STYLE__FONT:
				return font != null;
			case StylesPackage.TEXT_STYLE__FOREGROUND:
				return foreground != null;
			case StylesPackage.TEXT_STYLE__BACKGROUND:
				return background != null;
			case StylesPackage.TEXT_STYLE__UNDERLINE_COLOR:
				return underlineColor != null;
			case StylesPackage.TEXT_STYLE__STRIKEOUT_COLOR:
				return strikeoutColor != null;
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
		result.append(" (underline: ");
		result.append(underline);
		result.append(", underlineStyle: ");
		result.append(underlineStyle);
		result.append(", strikeout: ");
		result.append(strikeout);
		result.append(')');
		return result.toString();
	}

} //TextStyleImpl
