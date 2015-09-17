/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2015 Eclipse Modeling Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Laurent Le Moux, mwenz - Bug 423018 - Direct Graphiti diagram exporter
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.graphiti.platform.ga.IVisualState;
import org.eclipse.graphiti.platform.ga.IVisualStateChangeListener;
import org.eclipse.graphiti.platform.ga.IVisualStateHolder;
import org.eclipse.graphiti.platform.ga.VisualState;
import org.eclipse.graphiti.platform.ga.VisualStateChangedEvent;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.swt.SWT;

public class DummyExportAsImageDiagramTypeProvider extends AbstractDiagramTypeProvider {
	@Override
	public IFeatureProvider getFeatureProvider() {
		// To avoid NPE...
		return new DefaultFeatureProvider(this);
	}

	@Override
	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return new DummyExportAsImageAlgorithmRendererFactory();
	}

	// Used for rendering any tool specific platform graphics algorithm in a
	// standard way (display shape with info text that this cannot be rendered).
	private class DummyExportAsImageAlgorithmRendererFactory implements IGraphicsAlgorithmRendererFactory {
		public IGraphicsAlgorithmRenderer createGraphicsAlgorithmRenderer(IRendererContext context) {
			return new NoRendererAvailableFigure();
		}
	}

	// Renderer for the standard text that this platform graphics algorithm
	// cannot be rendered.
	private class NoRendererAvailableFigure extends RectangleFigure implements IGraphicsAlgorithmRenderer,
			IVisualStateHolder, IVisualStateChangeListener {

		private Insets defaultFigureInsets = new Insets(2);

		private VisualState visualState;

		public NoRendererAvailableFigure() {
			super();
		}

		@Override
		public IVisualState getVisualState() {
			if (visualState == null) {
				visualState = new VisualState();
				visualState.addChangeListener(this);
			}
			return visualState;
		}

		@Override
		public void visualStateChanged(VisualStateChangedEvent e) {
			setBackgroundColor(ColorConstants.white);
			setLineWidth(1);
		}

		@Override
		protected void fillShape(Graphics g) {
			g.setAntialias(SWT.ON);
			Rectangle innerBounds = getInnerBounds();
			g.setBackgroundColor(ColorConstants.white);
			g.fillRectangle(new Rectangle(innerBounds.getLeft(), innerBounds.getRight()));
		}

		@Override
		protected void outlineShape(Graphics g) {
			Rectangle innerBounds = getInnerBounds();

			g.drawLine(innerBounds.getTopLeft(), innerBounds.getTopRight());
			g.drawLine(innerBounds.getTopRight(), innerBounds.getBottomRight());
			g.drawLine(innerBounds.getBottomRight(), innerBounds.getBottomLeft());
			g.drawLine(innerBounds.getBottomLeft(), innerBounds.getTopLeft());

			g.setForegroundColor(ColorConstants.black);
			g.drawText("Sorry!\n\nThis tool specific shape\ncannot be rendered.", innerBounds.x + 10,
					innerBounds.y + 10);
		}

		private Rectangle getInnerBounds() {
			return getBounds().getCopy().getShrinked(defaultFigureInsets);
		}
	}
}