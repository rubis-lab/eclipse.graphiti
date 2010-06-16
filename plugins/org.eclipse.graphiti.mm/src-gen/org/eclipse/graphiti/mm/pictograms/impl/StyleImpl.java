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
 * $Id: StyleImpl.java,v 1.1 2010/06/16 13:25:00 mwenz Exp $
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.graphiti.mm.datatypes.Color;

import org.eclipse.graphiti.mm.pictograms.AbstractStyle;
import org.eclipse.graphiti.mm.pictograms.Font;
import org.eclipse.graphiti.mm.pictograms.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Orientation;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;
import org.eclipse.graphiti.mm.pictograms.RenderingStyle;
import org.eclipse.graphiti.mm.pictograms.Style;
import org.eclipse.graphiti.mm.pictograms.StyleContainer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getLineWidth <em>Line Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getFilled <em>Filled</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getRenderingStyle <em>Rendering Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getTransparency <em>Transparency</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getStretchH <em>Stretch H</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getStretchV <em>Stretch V</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getProportional <em>Proportional</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.StyleImpl#getStyleContainer <em>Style Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StyleImpl extends StyleContainerImpl implements Style {
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
	 * The cached value of the '{@link #getForeground() <em>Foreground</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeground()
	 * @generated
	 * @ordered
	 */
	protected Color foreground;

	/**
	 * The default value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LINE_WIDTH_EDEFAULT = new Integer(1);

	/**
	 * The cached value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer lineWidth = LINE_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineStyle()
	 * @generated
	 * @ordered
	 */
	protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID;

	/**
	 * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineStyle()
	 * @generated
	 * @ordered
	 */
	protected LineStyle lineStyle = LINE_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILLED_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilled()
	 * @generated
	 * @ordered
	 */
	protected Boolean filled = FILLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineVisible() <em>Line Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineVisible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LINE_VISIBLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getLineVisible() <em>Line Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineVisible()
	 * @generated
	 * @ordered
	 */
	protected Boolean lineVisible = LINE_VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRenderingStyle() <em>Rendering Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderingStyle()
	 * @generated
	 * @ordered
	 */
	protected RenderingStyle renderingStyle;

	/**
	 * The default value of the '{@link #getTransparency() <em>Transparency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransparency()
	 * @generated
	 * @ordered
	 */
	protected static final Double TRANSPARENCY_EDEFAULT = new Double(0.0);

	/**
	 * The cached value of the '{@link #getTransparency() <em>Transparency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransparency()
	 * @generated
	 * @ordered
	 */
	protected Double transparency = TRANSPARENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFont() <em>Font</em>}' containment reference.
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
	protected static final Orientation HORIZONTAL_ALIGNMENT_EDEFAULT = Orientation.ALIGNMENT_CENTER;

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
	protected static final Integer ANGLE_EDEFAULT = null;

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
	 * The default value of the '{@link #getStretchH() <em>Stretch H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchH()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRETCH_H_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStretchH() <em>Stretch H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchH()
	 * @generated
	 * @ordered
	 */
	protected Boolean stretchH = STRETCH_H_EDEFAULT;

	/**
	 * The default value of the '{@link #getStretchV() <em>Stretch V</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchV()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRETCH_V_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStretchV() <em>Stretch V</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchV()
	 * @generated
	 * @ordered
	 */
	protected Boolean stretchV = STRETCH_V_EDEFAULT;

	/**
	 * The default value of the '{@link #getProportional() <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProportional()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PROPORTIONAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProportional() <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProportional()
	 * @generated
	 * @ordered
	 */
	protected Boolean proportional = PROPORTIONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.STYLE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.STYLE__BACKGROUND, oldBackground, background));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__BACKGROUND, oldBackground, background));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.STYLE__FOREGROUND, oldForeground, foreground));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__FOREGROUND, oldForeground, foreground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLineWidth() {
		return lineWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineWidth(Integer newLineWidth) {
		Integer oldLineWidth = lineWidth;
		lineWidth = newLineWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__LINE_WIDTH, oldLineWidth, lineWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineStyle(LineStyle newLineStyle) {
		LineStyle oldLineStyle = lineStyle;
		lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__LINE_STYLE, oldLineStyle, lineStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFilled() {
		return filled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilled(Boolean newFilled) {
		Boolean oldFilled = filled;
		filled = newFilled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__FILLED, oldFilled, filled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLineVisible() {
		return lineVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineVisible(Boolean newLineVisible) {
		Boolean oldLineVisible = lineVisible;
		lineVisible = newLineVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__LINE_VISIBLE, oldLineVisible, lineVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderingStyle getRenderingStyle() {
		if (renderingStyle != null && renderingStyle.eIsProxy()) {
			InternalEObject oldRenderingStyle = (InternalEObject)renderingStyle;
			renderingStyle = (RenderingStyle)eResolveProxy(oldRenderingStyle);
			if (renderingStyle != oldRenderingStyle) {
				InternalEObject newRenderingStyle = (InternalEObject)renderingStyle;
				NotificationChain msgs = oldRenderingStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__RENDERING_STYLE, null, null);
				if (newRenderingStyle.eInternalContainer() == null) {
					msgs = newRenderingStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__RENDERING_STYLE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.STYLE__RENDERING_STYLE, oldRenderingStyle, renderingStyle));
			}
		}
		return renderingStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderingStyle basicGetRenderingStyle() {
		return renderingStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderingStyle(RenderingStyle newRenderingStyle, NotificationChain msgs) {
		RenderingStyle oldRenderingStyle = renderingStyle;
		renderingStyle = newRenderingStyle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__RENDERING_STYLE, oldRenderingStyle, newRenderingStyle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderingStyle(RenderingStyle newRenderingStyle) {
		if (newRenderingStyle != renderingStyle) {
			NotificationChain msgs = null;
			if (renderingStyle != null)
				msgs = ((InternalEObject)renderingStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__RENDERING_STYLE, null, msgs);
			if (newRenderingStyle != null)
				msgs = ((InternalEObject)newRenderingStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__RENDERING_STYLE, null, msgs);
			msgs = basicSetRenderingStyle(newRenderingStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__RENDERING_STYLE, newRenderingStyle, newRenderingStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getTransparency() {
		return transparency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransparency(Double newTransparency) {
		Double oldTransparency = transparency;
		transparency = newTransparency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__TRANSPARENCY, oldTransparency, transparency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__DESCRIPTION, oldDescription, description));
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
				InternalEObject newFont = (InternalEObject)font;
				NotificationChain msgs = oldFont.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__FONT, null, null);
				if (newFont.eInternalContainer() == null) {
					msgs = newFont.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__FONT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.STYLE__FONT, oldFont, font));
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
	public NotificationChain basicSetFont(Font newFont, NotificationChain msgs) {
		Font oldFont = font;
		font = newFont;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__FONT, oldFont, newFont);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFont(Font newFont) {
		if (newFont != font) {
			NotificationChain msgs = null;
			if (font != null)
				msgs = ((InternalEObject)font).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__FONT, null, msgs);
			if (newFont != null)
				msgs = ((InternalEObject)newFont).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.STYLE__FONT, null, msgs);
			msgs = basicSetFont(newFont, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__FONT, newFont, newFont));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment, horizontalAlignment));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__VERTICAL_ALIGNMENT, oldVerticalAlignment, verticalAlignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getAngle() {
		return angle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAngle(Integer newAngle) {
		Integer oldAngle = angle;
		angle = newAngle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__ANGLE, oldAngle, angle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStretchH() {
		return stretchH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStretchH(Boolean newStretchH) {
		Boolean oldStretchH = stretchH;
		stretchH = newStretchH;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__STRETCH_H, oldStretchH, stretchH));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStretchV() {
		return stretchV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStretchV(Boolean newStretchV) {
		Boolean oldStretchV = stretchV;
		stretchV = newStretchV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__STRETCH_V, oldStretchV, stretchV));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getProportional() {
		return proportional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProportional(Boolean newProportional) {
		Boolean oldProportional = proportional;
		proportional = newProportional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__PROPORTIONAL, oldProportional, proportional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleContainer getStyleContainer() {
		if (eContainerFeatureID() != PictogramsPackage.STYLE__STYLE_CONTAINER) return null;
		return (StyleContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleContainer basicGetStyleContainer() {
		if (eContainerFeatureID() != PictogramsPackage.STYLE__STYLE_CONTAINER) return null;
		return (StyleContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyleContainer(StyleContainer newStyleContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStyleContainer, PictogramsPackage.STYLE__STYLE_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyleContainer(StyleContainer newStyleContainer) {
		if (newStyleContainer != eInternalContainer() || (eContainerFeatureID() != PictogramsPackage.STYLE__STYLE_CONTAINER && newStyleContainer != null)) {
			if (EcoreUtil.isAncestor(this, newStyleContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStyleContainer != null)
				msgs = ((InternalEObject)newStyleContainer).eInverseAdd(this, PictogramsPackage.STYLE_CONTAINER__STYLES, StyleContainer.class, msgs);
			msgs = basicSetStyleContainer(newStyleContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.STYLE__STYLE_CONTAINER, newStyleContainer, newStyleContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStyleContainer((StyleContainer)otherEnd, msgs);
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
			case PictogramsPackage.STYLE__RENDERING_STYLE:
				return basicSetRenderingStyle(null, msgs);
			case PictogramsPackage.STYLE__FONT:
				return basicSetFont(null, msgs);
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				return basicSetStyleContainer(null, msgs);
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
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.STYLE_CONTAINER__STYLES, StyleContainer.class, msgs);
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
			case PictogramsPackage.STYLE__BACKGROUND:
				if (resolve) return getBackground();
				return basicGetBackground();
			case PictogramsPackage.STYLE__FOREGROUND:
				if (resolve) return getForeground();
				return basicGetForeground();
			case PictogramsPackage.STYLE__LINE_WIDTH:
				return getLineWidth();
			case PictogramsPackage.STYLE__LINE_STYLE:
				return getLineStyle();
			case PictogramsPackage.STYLE__FILLED:
				return getFilled();
			case PictogramsPackage.STYLE__LINE_VISIBLE:
				return getLineVisible();
			case PictogramsPackage.STYLE__RENDERING_STYLE:
				if (resolve) return getRenderingStyle();
				return basicGetRenderingStyle();
			case PictogramsPackage.STYLE__TRANSPARENCY:
				return getTransparency();
			case PictogramsPackage.STYLE__ID:
				return getId();
			case PictogramsPackage.STYLE__DESCRIPTION:
				return getDescription();
			case PictogramsPackage.STYLE__FONT:
				if (resolve) return getFont();
				return basicGetFont();
			case PictogramsPackage.STYLE__HORIZONTAL_ALIGNMENT:
				return getHorizontalAlignment();
			case PictogramsPackage.STYLE__VERTICAL_ALIGNMENT:
				return getVerticalAlignment();
			case PictogramsPackage.STYLE__ANGLE:
				return getAngle();
			case PictogramsPackage.STYLE__STRETCH_H:
				return getStretchH();
			case PictogramsPackage.STYLE__STRETCH_V:
				return getStretchV();
			case PictogramsPackage.STYLE__PROPORTIONAL:
				return getProportional();
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				if (resolve) return getStyleContainer();
				return basicGetStyleContainer();
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
			case PictogramsPackage.STYLE__BACKGROUND:
				setBackground((Color)newValue);
				return;
			case PictogramsPackage.STYLE__FOREGROUND:
				setForeground((Color)newValue);
				return;
			case PictogramsPackage.STYLE__LINE_WIDTH:
				setLineWidth((Integer)newValue);
				return;
			case PictogramsPackage.STYLE__LINE_STYLE:
				setLineStyle((LineStyle)newValue);
				return;
			case PictogramsPackage.STYLE__FILLED:
				setFilled((Boolean)newValue);
				return;
			case PictogramsPackage.STYLE__LINE_VISIBLE:
				setLineVisible((Boolean)newValue);
				return;
			case PictogramsPackage.STYLE__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)newValue);
				return;
			case PictogramsPackage.STYLE__TRANSPARENCY:
				setTransparency((Double)newValue);
				return;
			case PictogramsPackage.STYLE__ID:
				setId((String)newValue);
				return;
			case PictogramsPackage.STYLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case PictogramsPackage.STYLE__FONT:
				setFont((Font)newValue);
				return;
			case PictogramsPackage.STYLE__HORIZONTAL_ALIGNMENT:
				setHorizontalAlignment((Orientation)newValue);
				return;
			case PictogramsPackage.STYLE__VERTICAL_ALIGNMENT:
				setVerticalAlignment((Orientation)newValue);
				return;
			case PictogramsPackage.STYLE__ANGLE:
				setAngle((Integer)newValue);
				return;
			case PictogramsPackage.STYLE__STRETCH_H:
				setStretchH((Boolean)newValue);
				return;
			case PictogramsPackage.STYLE__STRETCH_V:
				setStretchV((Boolean)newValue);
				return;
			case PictogramsPackage.STYLE__PROPORTIONAL:
				setProportional((Boolean)newValue);
				return;
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				setStyleContainer((StyleContainer)newValue);
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
			case PictogramsPackage.STYLE__BACKGROUND:
				setBackground((Color)null);
				return;
			case PictogramsPackage.STYLE__FOREGROUND:
				setForeground((Color)null);
				return;
			case PictogramsPackage.STYLE__LINE_WIDTH:
				setLineWidth(LINE_WIDTH_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__LINE_STYLE:
				setLineStyle(LINE_STYLE_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__LINE_VISIBLE:
				setLineVisible(LINE_VISIBLE_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)null);
				return;
			case PictogramsPackage.STYLE__TRANSPARENCY:
				setTransparency(TRANSPARENCY_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__ID:
				setId(ID_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__FONT:
				setFont((Font)null);
				return;
			case PictogramsPackage.STYLE__HORIZONTAL_ALIGNMENT:
				setHorizontalAlignment(HORIZONTAL_ALIGNMENT_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__VERTICAL_ALIGNMENT:
				setVerticalAlignment(VERTICAL_ALIGNMENT_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__STRETCH_H:
				setStretchH(STRETCH_H_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__STRETCH_V:
				setStretchV(STRETCH_V_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__PROPORTIONAL:
				setProportional(PROPORTIONAL_EDEFAULT);
				return;
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				setStyleContainer((StyleContainer)null);
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
			case PictogramsPackage.STYLE__BACKGROUND:
				return background != null;
			case PictogramsPackage.STYLE__FOREGROUND:
				return foreground != null;
			case PictogramsPackage.STYLE__LINE_WIDTH:
				return LINE_WIDTH_EDEFAULT == null ? lineWidth != null : !LINE_WIDTH_EDEFAULT.equals(lineWidth);
			case PictogramsPackage.STYLE__LINE_STYLE:
				return lineStyle != LINE_STYLE_EDEFAULT;
			case PictogramsPackage.STYLE__FILLED:
				return FILLED_EDEFAULT == null ? filled != null : !FILLED_EDEFAULT.equals(filled);
			case PictogramsPackage.STYLE__LINE_VISIBLE:
				return LINE_VISIBLE_EDEFAULT == null ? lineVisible != null : !LINE_VISIBLE_EDEFAULT.equals(lineVisible);
			case PictogramsPackage.STYLE__RENDERING_STYLE:
				return renderingStyle != null;
			case PictogramsPackage.STYLE__TRANSPARENCY:
				return TRANSPARENCY_EDEFAULT == null ? transparency != null : !TRANSPARENCY_EDEFAULT.equals(transparency);
			case PictogramsPackage.STYLE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PictogramsPackage.STYLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case PictogramsPackage.STYLE__FONT:
				return font != null;
			case PictogramsPackage.STYLE__HORIZONTAL_ALIGNMENT:
				return horizontalAlignment != HORIZONTAL_ALIGNMENT_EDEFAULT;
			case PictogramsPackage.STYLE__VERTICAL_ALIGNMENT:
				return verticalAlignment != VERTICAL_ALIGNMENT_EDEFAULT;
			case PictogramsPackage.STYLE__ANGLE:
				return ANGLE_EDEFAULT == null ? angle != null : !ANGLE_EDEFAULT.equals(angle);
			case PictogramsPackage.STYLE__STRETCH_H:
				return STRETCH_H_EDEFAULT == null ? stretchH != null : !STRETCH_H_EDEFAULT.equals(stretchH);
			case PictogramsPackage.STYLE__STRETCH_V:
				return STRETCH_V_EDEFAULT == null ? stretchV != null : !STRETCH_V_EDEFAULT.equals(stretchV);
			case PictogramsPackage.STYLE__PROPORTIONAL:
				return PROPORTIONAL_EDEFAULT == null ? proportional != null : !PROPORTIONAL_EDEFAULT.equals(proportional);
			case PictogramsPackage.STYLE__STYLE_CONTAINER:
				return basicGetStyleContainer() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractStyle.class) {
			switch (derivedFeatureID) {
				case PictogramsPackage.STYLE__BACKGROUND: return PictogramsPackage.ABSTRACT_STYLE__BACKGROUND;
				case PictogramsPackage.STYLE__FOREGROUND: return PictogramsPackage.ABSTRACT_STYLE__FOREGROUND;
				case PictogramsPackage.STYLE__LINE_WIDTH: return PictogramsPackage.ABSTRACT_STYLE__LINE_WIDTH;
				case PictogramsPackage.STYLE__LINE_STYLE: return PictogramsPackage.ABSTRACT_STYLE__LINE_STYLE;
				case PictogramsPackage.STYLE__FILLED: return PictogramsPackage.ABSTRACT_STYLE__FILLED;
				case PictogramsPackage.STYLE__LINE_VISIBLE: return PictogramsPackage.ABSTRACT_STYLE__LINE_VISIBLE;
				case PictogramsPackage.STYLE__RENDERING_STYLE: return PictogramsPackage.ABSTRACT_STYLE__RENDERING_STYLE;
				case PictogramsPackage.STYLE__TRANSPARENCY: return PictogramsPackage.ABSTRACT_STYLE__TRANSPARENCY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractStyle.class) {
			switch (baseFeatureID) {
				case PictogramsPackage.ABSTRACT_STYLE__BACKGROUND: return PictogramsPackage.STYLE__BACKGROUND;
				case PictogramsPackage.ABSTRACT_STYLE__FOREGROUND: return PictogramsPackage.STYLE__FOREGROUND;
				case PictogramsPackage.ABSTRACT_STYLE__LINE_WIDTH: return PictogramsPackage.STYLE__LINE_WIDTH;
				case PictogramsPackage.ABSTRACT_STYLE__LINE_STYLE: return PictogramsPackage.STYLE__LINE_STYLE;
				case PictogramsPackage.ABSTRACT_STYLE__FILLED: return PictogramsPackage.STYLE__FILLED;
				case PictogramsPackage.ABSTRACT_STYLE__LINE_VISIBLE: return PictogramsPackage.STYLE__LINE_VISIBLE;
				case PictogramsPackage.ABSTRACT_STYLE__RENDERING_STYLE: return PictogramsPackage.STYLE__RENDERING_STYLE;
				case PictogramsPackage.ABSTRACT_STYLE__TRANSPARENCY: return PictogramsPackage.STYLE__TRANSPARENCY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (lineWidth: ");
		result.append(lineWidth);
		result.append(", lineStyle: ");
		result.append(lineStyle);
		result.append(", filled: ");
		result.append(filled);
		result.append(", lineVisible: ");
		result.append(lineVisible);
		result.append(", transparency: ");
		result.append(transparency);
		result.append(", id: ");
		result.append(id);
		result.append(", description: ");
		result.append(description);
		result.append(", horizontalAlignment: ");
		result.append(horizontalAlignment);
		result.append(", verticalAlignment: ");
		result.append(verticalAlignment);
		result.append(", angle: ");
		result.append(angle);
		result.append(", stretchH: ");
		result.append(stretchH);
		result.append(", stretchV: ");
		result.append(stretchV);
		result.append(", proportional: ");
		result.append(proportional);
		result.append(')');
		return result.toString();
	}

} //StyleImpl
