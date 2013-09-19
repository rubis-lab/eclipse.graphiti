/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Bug 336488 - DiagramEditor API
 *    mwenz - Bug 373298 - Possible Resource leaks in Graphiti
 *    mwenz - Bug 389379 - Editor Dirty Handling (gets broken after context button execution)
 *    fvelasco - Bug 396247 - ImageDescriptor changes
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.contextbuttons;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.internal.contextbuttons.PositionedContextButton;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.editor.GFDragConnectionTool;
import org.eclipse.graphiti.ui.internal.figures.GFFigureUtil;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;
import org.eclipse.graphiti.ui.internal.util.draw2d.Tooltip;
import org.eclipse.graphiti.ui.internal.util.ui.PopupMenu;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;

/**
 * A context button, which is used for example in the context button pad. It
 * does not extend button, but is an implementation from scratch, because it has
 * a quite specific look and behavior.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ContextButton extends Clickable implements MouseMotionListener, ActionListener {
	private ITransparencyProvider transparencyProvider = null;

	/**
	 * The {@link PositionedContextButton} contains the visual information for
	 * the context button (colors, position, size, opacity, ...). It is set in
	 * the constructor.
	 */
	private PositionedContextButton positionedContextButton;

	/**
	 * The {@link ContextButtonEntry} contains the logical and identifying
	 * information for the context button (command, name, image, ...). It is set
	 * in the constructor.
	 */
	private ContextButtonEntry contextButtonEntry;

	/**
	 * The {@link ContextButtonPad} is used to access the environment (editor,
	 * ...). It is set in the constructor.
	 */
	private ContextButtonPad contextButtonPad;

	/**
	 * The current opacity of the context button. It is changed for example on
	 * mouse-events.
	 */
	private double opacity;

	/**
	 * The tooltip of the context button. It never changes, but it is sometimes
	 * shortly disabled (e.g. on button-pressed).
	 */
	private Tooltip tooltip;

	/**
	 * Used in the mouse-drag scenario (see {@link #mouseDragged(MouseEvent)})
	 */
	private MouseMoveListener mouseDragMoveListener;

	/**
	 * Used in the mouse-drag scenario (see {@link #mouseDragged(MouseEvent)})
	 */
	private MouseListener mouseDragUpListener;

	private String providerId;

	private GFDragConnectionTool dragConnectionTool;

	// ============================ inner classes =============================

	/**
	 * The label provider for the popup-menu, which appears when the
	 * context-button is clicked.
	 */
	private class PopupMenuLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			return ((ContextButtonEntry) element).getText();
		}

		@Override
		public Image getImage(Object element) {
			return GraphitiUi.getImageService().getImageForId(providerId, ((ContextButtonEntry) element).getIconId());
		}
	};

	// ============================ constructors ==============================

	/**
	 * Creates a new ContextButton.
	 * 
	 * @param providerId
	 * 
	 * @param positionedContextButton
	 *            The {@link PositionedContextButton} contains the visual
	 *            information for the context button (colors, position, size,
	 *            opacity, ...). It also provides access to the
	 *            {@link ContextButtonEntry}.
	 * @param contextButtonPad
	 *            The {@link ContextButtonPad} is used to access the environment
	 *            (editor, ...).
	 */
	public ContextButton(String providerId, PositionedContextButton positionedContextButton,
			ContextButtonPad contextButtonPad) {
		this.positionedContextButton = positionedContextButton;
		this.contextButtonPad = contextButtonPad;
		this.contextButtonEntry = (ContextButtonEntry) positionedContextButton.getContextButtonEntry();
		this.providerId = providerId;

		setBorder(null); // get rid of default border
		setTransparencyProvider(contextButtonPad);
		setOpacity(getPositionedContextButton().getDefaultOpacity());

		if (getEntry().getText() != null && getEntry().getText().length() > 0) {
			if (tooltip == null) {
				tooltip = new Tooltip();
			}
			tooltip.setHeader(getEntry().getText());
		}
		if (getEntry().getDescription() != null && getEntry().getDescription().length() > 0) {
			if (tooltip == null) {
				tooltip = new Tooltip();
			}
			tooltip.setDescription(getEntry().getDescription());
		}
		setToolTip(tooltip);

		addMouseMotionListener(this);
		addActionListener(this);

		// disable the context button, if it is not executable.
		// Note, that this has to be done dependent on the context button functionality
		// (drag&drop, click with popup, single click)
		if (getEntry().getDragAndDropFeatures().size() > 0) {
			setEnabled(true);
		} else if (getEntry().getContextButtonMenuEntries().size() > 0) {
			setEnabled(getExecutableMenuEntries().size() > 0);
		} else {
			setEnabled(getEntry().canExecute());
		}
	}

	// ========================= getter and setter ============================

	/**
	 * Returns the {@link PositionedContextButton} which contains the visual
	 * information for the context button (colors, position, size, opacity, ...)
	 * 
	 * @return The {@link PositionedContextButton} which contains the visual
	 *         information for the context button (colors, position, size,
	 *         opacity, ...)
	 */
	public final PositionedContextButton getPositionedContextButton() {
		return positionedContextButton;
	}

	/**
	 * Returns the {@link ContextButtonEntry} which contains the logical and
	 * identifying information for the context button (command, name, image,
	 * ...). It is set in the constructor.
	 * 
	 * @return The {@link ContextButtonEntry} which contains the logical and
	 *         identifying information for the context button (command, name,
	 *         image, ...). It is set in the constructor.
	 */
	public final ContextButtonEntry getEntry() {
		return contextButtonEntry;
	}

	/**
	 * Returns the {@link ContextButtonPad} which is used to access the
	 * environment (editor, ...).
	 * 
	 * @return The {@link ContextButtonPad} which is used to access the
	 *         environment (editor, ...).
	 */
	public final ContextButtonPad getContextButtonPad() {
		return contextButtonPad;
	}

	/**
	 * Returns the zoom-level for which the context-button shall be painted.
	 * 
	 * @return The zoom-level for which the context-button shall be painted.
	 */
	public final double getZoomLevel() {
		return getContextButtonPad().getZoomLevel();
	}

	/**
	 * Returns the {@link IDiagramContainerUI} for which the context button is
	 * displayed.
	 * 
	 * @return The {@link IDiagramContainerUI} for which the context button is
	 *         displayed.
	 */
	public final DiagramBehavior getDiagramBehavior() {
		return getContextButtonPad().getDiagramBehavior();
	}

	/**
	 * Returns the {@link EditPart} for which the context button is displayed.
	 * 
	 * @return The {@link EditPart} for which the context button is displayed.
	 */
	public final EditPart getEditPart() {
		return getContextButtonPad().getEditPart();
	}

	/**
	 * Returns the transparency-provider, which allows to define the
	 * transparency externally.
	 * 
	 * @return The transparency-provider, which allows to define the
	 *         transparency externally.
	 */
	private ITransparencyProvider getTransparencyProvider() {
		return transparencyProvider;
	}

	/**
	 * Sets the transparency-provider, which allows to define the transparency
	 * externally.
	 * 
	 * @param transparencyProvider
	 *            The transparency-provider to set.
	 */
	private void setTransparencyProvider(ITransparencyProvider transparencyProvider) {
		this.transparencyProvider = transparencyProvider;
	}

	/**
	 * Sets the opacity of this figure.
	 * 
	 * @param opacity
	 *            The opacity to set.
	 */
	private void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	/**
	 * Returns the opacity adjusted by the current transparency. Concretely this
	 * means "opacity * transparency".
	 * 
	 * @return The opacity adjusted by the current transparency.
	 */
	private double getAdjustedOpacity() {
		if (getTransparencyProvider() != null) {
			return opacity * getTransparencyProvider().getCurrentTransparency();
		}

		return opacity;
	}

	// ============================== painting ================================

	/**
	 * Paints the context button (lines, filling, image, ...).
	 */
	@SuppressWarnings("unused")
	@Override
	protected void paintFigure(Graphics graphics) {
		int lw = ((int) (getPositionedContextButton().getLineWidth() * getZoomLevel()));
		graphics.setLineWidth(lw);
		graphics.setAntialias(SWT.ON);
		graphics.setAlpha((int) (getAdjustedOpacity() * 255));

		Path pathOuterLine = createPath(1);
		Path pathMiddleLine = createPath(2);
		Path pathFill = createPath(3);

		graphics.setBackgroundColor(getAdjustedColor(getPositionedContextButton().getFillColor()));
		graphics.fillPath(pathFill);
		graphics.setForegroundColor(getAdjustedColor(getPositionedContextButton().getMiddleLineColor()));
		graphics.drawPath(pathMiddleLine);
		graphics.setForegroundColor(getAdjustedColor(getPositionedContextButton().getOuterLineColor()));
		graphics.drawPath(pathOuterLine);

		pathOuterLine.dispose();
		pathMiddleLine.dispose();
		pathFill.dispose();
		pathOuterLine = null;
		pathMiddleLine = null;
		pathFill = null;

		// change opacity for image (never transparent)
		double imageOpacity = 1;
		graphics.setAlpha((int) (imageOpacity * 255));

		// create image
		String iconId = getEntry().getIconId();
		Image originalImage = GraphitiUi.getImageService().getImageForId(providerId, iconId);
		Image image;
		if (!isEnabled()) {
			image = new Image(originalImage.getDevice(), originalImage, SWT.IMAGE_DISABLE);
		} else {
			if (originalImage == null) {
				return;
			}
			image = new Image(originalImage.getDevice(), originalImage, SWT.IMAGE_COPY);
		}

		// draw image
		org.eclipse.swt.graphics.Rectangle rect = image.getBounds();
		Rectangle newRect = new Rectangle(0, 0, rect.width, rect.height);
		newRect.scale(getZoomLevel());
		newRect.x = getBounds().x + ((getBounds().width - newRect.width) / 2);
		newRect.y = getBounds().y + ((getBounds().height - newRect.height) / 2);
		graphics.drawImage(image, rect.x, rect.y, rect.width, rect.height, newRect.x, newRect.y, newRect.width, newRect.height);
		image.dispose();

		// paint indicators
		List<IContextButtonEntry> menuEntries = getEntry().getContextButtonMenuEntries();
		boolean isSubmenuButton = menuEntries != null && menuEntries.size() > 0;
		if (isSubmenuButton) {
			paintSubmenuIndicator(graphics, newRect);
		}

		// TODO: currently switched off. Discuss if and how to continue this functionality.
		if (false) {
			final List<IFeature> dragFeatures = getEntry().getDragAndDropFeatures();
			final boolean isDragButton = dragFeatures != null && dragFeatures.size() > 0;
			if (isDragButton && getEntry().getFeature() != null) {
				paintDragOrClickIndicator(graphics, newRect);
			} else if (isDragButton) {
				paintDragOnlyIndicator(graphics, newRect);
			}
		}
	}

	/**
	 * Paints a sub-menu indicator on the context button (opens sub-menu on
	 * click).
	 */
	private void paintSubmenuIndicator(Graphics graphics, Rectangle newRect) {
		int x = newRect.x;
		int y = newRect.y;
		int w = newRect.width;
		int h = newRect.height;
		PointList pl = new PointList();
		pl.addPoint(x + w, y + h * 3 / 4);
		pl.addPoint(x + w, y + h);
		pl.addPoint(x + w * 3 / 4, y + h);

		preparePaintIndicator(graphics);
		graphics.drawPolygon(pl);
		graphics.fillPolygon(pl);
	}

	/**
	 * Paints a drag-only indicator on the context button (can be dragged but
	 * not clicked)
	 */
	private void paintDragOnlyIndicator(Graphics graphics, Rectangle newRect) {
		int x = newRect.x;
		int y = newRect.y;
		int w = newRect.width;
		int h = newRect.height;

		preparePaintIndicator(graphics);
		graphics.drawRectangle(x + w * 3 / 4, y + h / 2, w / 8, h / 8);
		graphics.fillRectangle(x + w * 3 / 4, y + h / 2, w / 8, h / 8);
	}

	/**
	 * Paints a drag-or-click indicator on the context button (can either be
	 * dragged or be clicked).
	 */
	private void paintDragOrClickIndicator(Graphics graphics, Rectangle newRect) {
		int x = newRect.x;
		int y = newRect.y;
		int w = newRect.width;
		int h = newRect.height;

		preparePaintIndicator(graphics);
		graphics.drawOval(x + w * 3 / 4, y + h / 4, w / 8, h / 8);
		graphics.fillOval(x + w * 3 / 4, y + h / 4, w / 8, h / 8);
	}

	/**
	 * Prepares the given graphics for painting an indicator (sets colors,
	 * line-width, ...).
	 */
	private void preparePaintIndicator(Graphics graphics) {
		graphics.setLineWidth(1);
		IColorConstant outerLineColor = getPositionedContextButton().getOuterLineColor();
		Color adjustedColor = getAdjustedColor(outerLineColor);
		graphics.setForegroundColor(adjustedColor);
		graphics.setBackgroundColor(adjustedColor);
	}

	/**
	 * Returns the adjusted SWT color for the given IColorConstant. Note, that
	 * this method also adjusts the color, in case that this figure is disabled.
	 * 
	 * @param color
	 *            The IColorConstant for which to return the SWT color.
	 * @return The adjusted SWT color for the given IColorConstant.
	 */
	private Color getAdjustedColor(IColorConstant color) {
		if (!isEnabled()) {
			int disabledAdjustment = 80;
			int r = Math.min(255, color.getRed() + disabledAdjustment);
			int g = Math.min(255, color.getGreen() + disabledAdjustment);
			int b = Math.min(255, color.getBlue() + disabledAdjustment);
			color = new ColorConstant(r, g, b);
		}
		Color swtColor = DataTypeTransformation.toSwtColor(getContextButtonPad().getResourceRegistry(), color);
		return swtColor;
	}

	/**
	 * Creates and returns the path, which defines the outer lines and filling
	 * area of the context button.
	 * 
	 * @param shrinkLines
	 *            The number of lines, by which the path shall be shrinked. This
	 *            allows to use this method to create the outer line, middle
	 *            line and inner line of the context button.
	 * @return The path, which defines the outer lines and filling area of the
	 *         context button.
	 */
	protected Path createPath(int shrinkLines) {
		double zoom = getZoomLevel();
		int lw = (int) (getPositionedContextButton().getLineWidth() * zoom);
		Rectangle r = GFFigureUtil.getAdjustedRectangle(getBounds(), 1.0, shrinkLines * lw);

		// adjust corner for the inner path (formula found by experimenting)
		double zoomedCorner = (getPositionedContextButton().getCornerRadius() * zoom);
		int corner = (int) Math.max(1, zoomedCorner - (((shrinkLines - 1) * lw) + zoomedCorner / 64));

		Path path = new Path(null);
		path.moveTo(r.x, r.y);
		path.addArc(r.x, r.y, corner, corner, 90, 90);
		path.addArc(r.x, r.y + r.height - corner, corner, corner, 180, 90);
		path.addArc(r.x + r.width - corner, r.y + r.height - corner, corner, corner, 270, 90);
		path.addArc(r.x + r.width - corner, r.y, corner, corner, 0, 90);
		path.close();

		return path;
	}

	// ============================== eventing ================================

	/**
	 * Changes the opacity of the context button.
	 */
	@Override
	public void handleMouseEntered(MouseEvent event) {
		setOpacity(getPositionedContextButton().getMouseOverOpacity());
		repaint();
		super.handleMouseEntered(event);

	}

	/**
	 * Changes the opacity of the context button.
	 */
	@Override
	public void handleMouseExited(MouseEvent event) {
		setOpacity(getPositionedContextButton().getDefaultOpacity());
		repaint();
		super.handleMouseExited(event);
	}

	/**
	 * Changes the opacity of the context button.
	 * <p>
	 * Additionally it disables the tooltip. Especially in the middle of the
	 * drag & drop procedure no tooltip shall appear.
	 */
	@Override
	public void handleMousePressed(MouseEvent event) {
		// disable tooltip
		// This does not hide an already showing tooltip (which would be
		// preferred), but at least it prevents showing a tooltip after
		// mouse-pressed. This is especially important when dragging starts
		// after mouse-pressed.
		setToolTip(null);

		setOpacity(getPositionedContextButton().getMouseDownOpacity());
		repaint();
		super.handleMousePressed(event);
	}

	/**
	 * Changes the opacity of the context button.
	 * <p>
	 * Additionally it enables the tooltip again.
	 */
	@Override
	public void handleMouseReleased(MouseEvent event) {
		// enable tooltip, which was disabled on mouse-pressed
		setToolTip(tooltip);

		// still entered the button, so go back to mouse-over
		setOpacity(getPositionedContextButton().getMouseOverOpacity());
		repaint();
		super.handleMouseReleased(event);
	}

	/**
	 * Creates a connection with the connection-tool when dragging the context
	 * button.
	 */
	public void mouseDragged(MouseEvent me) {
		if (getEntry().getDragAndDropFeatures().size() == 0) {
			return;
		}

		me.consume();

		if (mouseDragMoveListener == null) { // not already in dragging

			// creates a new drag-connection tool on each mouse move
			mouseDragMoveListener = new MouseMoveListener() {
				public void mouseMove(org.eclipse.swt.events.MouseEvent e) {
					EditPart targetEditPart = getDiagramBehavior().getDiagramContainer().getGraphicalViewer()
							.findObjectAt(new Point(e.x, e.y));
					getGFDragConnectionTool().continueConnection(getEditPart(),
							targetEditPart);
				}
			};

			// removes the mouse-drag listeners on mouse up
			mouseDragUpListener = new MouseAdapter() {
				@Override
				public void mouseUp(org.eclipse.swt.events.MouseEvent e) {
					getDiagramBehavior().getDiagramContainer().getGraphicalViewer().getControl()
							.removeMouseListener(mouseDragUpListener);
					getDiagramBehavior().getDiagramContainer().getGraphicalViewer().getControl()
							.removeMouseMoveListener(mouseDragMoveListener);
					mouseDragUpListener = null;
					mouseDragMoveListener = null;
					dragConnectionTool = null;
				}
			};

			// adds the mouse-drag listeners
			getDiagramBehavior().getDiagramContainer().getGraphicalViewer().getControl()
					.addMouseListener(mouseDragUpListener);
			getDiagramBehavior().getDiagramContainer().getGraphicalViewer().getControl()
					.addMouseMoveListener(mouseDragMoveListener);
		}
	}

	/**
	 * Returns a new GFDragConnectionTool, which is initialized and set as
	 * active tool.
	 * 
	 * @return A new GFDragConnectionTool, which is initialized and set as
	 *         active tool.
	 */
	private GFDragConnectionTool getGFDragConnectionTool() {
		if (dragConnectionTool == null) {
			GFDragConnectionTool dragConnectionTool = new GFDragConnectionTool(getDiagramBehavior(), getEntry());

			DefaultEditDomain editDomain = getDiagramBehavior().getEditDomain();
			dragConnectionTool.setEditDomain(editDomain);
			editDomain.setActiveTool(dragConnectionTool);

			this.dragConnectionTool = dragConnectionTool;
		}

		return dragConnectionTool;
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	public void mouseHover(MouseEvent me) {
	}

	public void mouseMoved(MouseEvent me) {
	}

	/**
	 * Performs the command of the context button or opens a context-menu with a
	 * selection of multiple commands to perform.
	 */
	public void actionPerformed(ActionEvent event) {
		if (getEntry().getContextButtonMenuEntries().size() > 0) {
			List<IContextButtonEntry> menuEntries = getExecutableMenuEntries();
			if (menuEntries.size() == 0) {
				return;
			}

			ILabelProvider labelProvider = new PopupMenuLabelProvider();
			PopupMenu popupMenu = new PopupMenu(menuEntries, labelProvider);

			boolean b = popupMenu.show(Display.getCurrent().getActiveShell());
			if (b) {
				((ContextButtonEntry) popupMenu.getResult()).execute();
			}
		} else if (getEntry().canExecute()) {
			// has no ContextButtonMenuEntries -> execute ContextButton
			getEntry().execute();
		}

		getContextButtonPad().getContextButtonManagerForPad().hideContextButtonsInstantly();
	}

	/*
	 * Introduced to fix bug 373298
	 */
	void dispose() {
		if (tooltip != null) {
			tooltip.dispose();
		}
	}

	/**
	 * Returns all context button menu entries, which are executable.
	 * 
	 * @return All context button menu entries, which are executable.
	 */
	private List<IContextButtonEntry> getExecutableMenuEntries() {
		// has ContextButtonMenuEntries -> create popup
		List<IContextButtonEntry> contextButtonMenuEntries = getEntry().getContextButtonMenuEntries();
		List<IContextButtonEntry> menuEntries = new ArrayList<IContextButtonEntry>();
		for (IContextButtonEntry contextButtonEntry : contextButtonMenuEntries) {
			if (contextButtonEntry.canExecute()) {
				menuEntries.add(contextButtonEntry);
			}
		}
		return menuEntries;
	}
}
