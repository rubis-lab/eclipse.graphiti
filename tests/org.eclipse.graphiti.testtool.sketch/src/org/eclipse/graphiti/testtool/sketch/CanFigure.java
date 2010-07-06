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
package org.eclipse.graphiti.testtool.sketch;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IVisualState;
import org.eclipse.graphiti.platform.ga.IVisualStateChangeListener;
import org.eclipse.graphiti.platform.ga.IVisualStateHolder;
import org.eclipse.graphiti.platform.ga.VisualState;
import org.eclipse.graphiti.platform.ga.VisualStateChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * The Class CanFigure.
 */
public class CanFigure extends RectangleFigure implements IGraphicsAlgorithmRenderer, IVisualStateHolder, IVisualStateChangeListener {

	private static final int SELECTION_LINE_WIDTH_PRIMARY = 4;

	private static final int SELECTION_LINE_WIDTH_SECONDARY = 3;

	private static final Color SELECTION_BACKGROUND_PRIMARY = ColorConstants.blue;

	private static final Color SELECTION_BACKGROUND_SECONDARY = ColorConstants.lightBlue;

	private static final boolean ADVANCED_GRAPHIC = true;

	private static final int DEFAULT_ELLIPSE_PROPORTION = 4;

	private static final Color DEFAULT_TEXT_COLOR = ColorConstants.black;

	private Insets defaultFigureInsets = new Insets(2);

	private boolean dropShadow = true;

	private Image icon;

	private String label = "";

	private int mProportion = DEFAULT_ELLIPSE_PROPORTION;

	private boolean ovalTop;

	private Color storeColor;

	private String storeText;

	private Color textColor = DEFAULT_TEXT_COLOR;

	boolean highlight = false;

	private VisualState visualState;

	private int storeLineWidth = -1;

	/**
	 * Instantiates a new can figure.
	 * 
	 * @param ovalTop
	 *            the oval top
	 */
	public CanFigure(boolean ovalTop) {
		this(ovalTop, DEFAULT_ELLIPSE_PROPORTION);
	}

	/**
	 * Instantiates a new can figure.
	 * 
	 * @param ovalTop
	 *            the oval top
	 * @param ellipseProportion
	 *            the ellipse proportion
	 */
	public CanFigure(boolean ovalTop, int ellipseProportion) {
		super();
		if (ellipseProportion > 0) {
			mProportion = ellipseProportion;
		}
		this.ovalTop = ovalTop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#containsPoint(int, int)
	 */
	@Override
	public boolean containsPoint(int x, int y) {
		if (!super.containsPoint(x, y))
			return false;

		Rectangle ell = getInnerBounds().getCopy();
		ell.height /= mProportion;

		Rectangle topEll = ell;
		if (containsPoint(topEll, x, y))
			return true;

		Rectangle bottomEll = ell.getCopy().translate(0, getHeight(ell));
		if (containsPoint(bottomEll, x, y))
			return true;
		return new Rectangle(topEll.getLeft(), bottomEll.getRight()).contains(x, y);
	}

	/**
	 * Gets the figure insets.
	 * 
	 * @return the figure insets
	 */
	public Insets getFigureInsets() {
		return defaultFigureInsets;
	}

	/**
	 * Returns the visual state of this shape.
	 * 
	 * @return The visual state of this shape.
	 */
	public IVisualState getVisualState() {
		if (visualState == null) {
			visualState = new VisualState();
			visualState.addChangeListener(this);
		}
		return visualState;
	}

	/**
	 * Is called after the visual state changed.
	 */
	public void visualStateChanged(VisualStateChangedEvent e) {
		int selectionFeedback = getVisualState().getSelectionFeedback();
		if (selectionFeedback == IVisualState.SELECTION_PRIMARY) {
			// text
			if (storeText == null) {
				storeText = getCaption();
			}
			setCaption("primary select");

			// text color
			setTextColor(ColorConstants.white);

			// background color
			if (storeColor == null) {
				storeColor = getBackgroundColor();
			}
			setBackgroundColor(SELECTION_BACKGROUND_PRIMARY);

			// line width
			if (storeLineWidth < 0) {
				storeLineWidth = getLineWidth();
			}
			setLineWidth(SELECTION_LINE_WIDTH_PRIMARY);
		} else if (selectionFeedback == IVisualState.SELECTION_SECONDARY) {
			// text
			if (storeText == null) {
				storeText = getCaption();
			}
			setCaption("secondary select");

			// text color
			setTextColor(ColorConstants.white);

			// background color
			if (storeColor == null) {
				storeColor = getBackgroundColor();
			}
			setBackgroundColor(SELECTION_BACKGROUND_SECONDARY);

			// line width
			if (storeLineWidth < 0) {
				storeLineWidth = getLineWidth();
			}
			setLineWidth(SELECTION_LINE_WIDTH_SECONDARY);
		} else if (selectionFeedback == IVisualState.SELECTION_FEEDBACK_OFF) {
			// text color
			if (storeText != null) {
				setCaption(storeText);
				storeText = null;
			}

			// text color
			setTextColor(DEFAULT_TEXT_COLOR);

			// background color
			if (storeColor != null) {
				setBackgroundColor(storeColor);
				storeColor = null;
			}

			// line width
			if (storeLineWidth >= 0) {
				setLineWidth(storeLineWidth);
				storeLineWidth = -1;
			}
		}
	}

	/**
	 * Sets the caption.
	 * 
	 * @param label
	 *            the new caption
	 */
	public void setCaption(String label) {
		this.label = label;
		setToolTip(new Label(" " + label + " "));
		repaint();
	}

	/**
	 * Sets the drop shadow.
	 * 
	 * @param dropShadow
	 *            the new drop shadow
	 */
	public void setDropShadow(boolean dropShadow) {
		this.dropShadow = dropShadow;
	}

	/**
	 * Sets the highlight.
	 * 
	 * @param highlight
	 *            the new highlight
	 */
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
		repaint();
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon
	 *            the new icon
	 */
	public void setIcon(Image icon) {
		this.icon = icon;
		repaint();
	}

	/**
	 * Sets the text color.
	 * 
	 * @param textColor
	 *            the new text color
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	private boolean containsPoint(Rectangle el, int x, int y) {

		long ux = x - el.x - el.width / 2;
		long uy = y - el.y - el.height / 2;
		return ((ux * ux) << 10) / (el.width * el.width) + ((uy * uy) << 10) / (el.height * el.height) <= 256;
	}

	private String getCaption() {
		return label;
	}

	private int getHeight(Rectangle topEllipse) {
		return getInnerBounds().height - topEllipse.height;
	}

	private Rectangle getInnerBounds() {
		return getBounds().getCopy().getCropped(defaultFigureInsets);
	}

	@Override
	protected void fillShape(Graphics g) {
		if (ADVANCED_GRAPHIC)
			g.setAntialias(SWT.ON);
		Rectangle upperArc = getInnerBounds();
		upperArc.height /= mProportion;

		Rectangle lowerArc = upperArc.getCopy().translate(0, getHeight(upperArc));
		if (highlight) {
			g.setBackgroundColor(ColorConstants.blue);
			g.fillRectangle(getBounds());
		} else if (dropShadow) {

			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					g.setBackgroundColor(ColorConstants.lightGray);
					break;
				case 1:
					g.setBackgroundColor(ColorConstants.gray);
					break;
				case 2:
					g.setBackgroundColor(ColorConstants.darkGray);
					break;
				}

				g.fillRectangle(new Rectangle(upperArc.getLeft(), lowerArc.getRight()).getTranslated(3, 3).getExpanded(-i, 1));
				g.fillArc(new Rectangle(upperArc.getLocation(), upperArc.getBottomRight()).getTranslated(3, 2).getExpanded(-i, -i), 0, 180);
				g.fillArc(new Rectangle(lowerArc.getLocation(), lowerArc.getBottomRight()).getTranslated(3, 3).getExpanded(-i, -i), 180,
						180);

			}

		}
		g.setBackgroundColor(getBackgroundColor());

		g.fillRectangle(new Rectangle(upperArc.getLeft(), lowerArc.getRight()));
		g.fillArc(new Rectangle(upperArc.getLocation(), upperArc.getBottomRight().getTranslated(0, 1)), 0, 180);
		g.fillArc(new Rectangle(lowerArc.getLocation(), lowerArc.getBottomRight()), 180, 180);
	}

	@Override
	protected void outlineShape(Graphics g) {

		Rectangle innerBounds = getInnerBounds();

		Rectangle ovalBounds = innerBounds.getCopy();

		ovalBounds.height /= mProportion;

		// Rectangle ovalBounds = ellipseBounds.getCopy().shrink((lineWidth - 1)
		// / 2, (lineWidth - 1) / 2);
		if (ovalTop) {
			g.drawOval(ovalBounds);
		} else {
			g.drawArc(ovalBounds, 0, 180);
		}

		Rectangle arcBounds = ovalBounds.getCopy().translate(0, getHeight(ovalBounds));
		g.drawLine(ovalBounds.getLeft(), arcBounds.getLeft());
		g.drawLine(ovalBounds.getRight(), arcBounds.getRight());
		g.drawArc(new Rectangle(arcBounds.getLocation(), arcBounds.getBottomRight().translate(-2, -1)), 180, 180);

		int maxChars = (innerBounds.width - 40) / g.getFontMetrics().getAverageCharWidth();
		if (maxChars < 3)
			maxChars = 3;

		if (label != null) {
			String newCaption = label;
			if (label.length() > maxChars)
				newCaption = label.substring(0, maxChars - 3) + "...";

			g.setForegroundColor(textColor);
			g.drawText(newCaption, innerBounds.x + 25, innerBounds.y + innerBounds.height / 2 - 6);
			if (icon != null)
				g.drawImage(icon, innerBounds.x + 5, innerBounds.y + innerBounds.height / 2 - 8);
		}
	}
}