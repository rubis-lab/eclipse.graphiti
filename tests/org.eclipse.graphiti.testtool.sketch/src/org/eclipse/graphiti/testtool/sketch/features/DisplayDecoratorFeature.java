/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 358255 - initial API, implementation and documentation
 *    mwenz - Bug 396793 - Text decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.draw2d.Graphics;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.tb.BorderDecorator;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.tb.TextDecorator;
import org.eclipse.graphiti.testtool.sketch.SketchToolBehavior;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class DisplayDecoratorFeature.
 */
public class DisplayDecoratorFeature extends AbstractCustomFeature {

	public static final int TYPE_IMAGE = 0;
	public static final int TYPE_BORDER = 1;
	public static final int TYPE_COLOR = 2;
	public static final int TYPE_TEXT = 3;

	private int type;

	public DisplayDecoratorFeature(IFeatureProvider fp, ICustomContext context, int type) {
		super(fp);
		this.type = type;
	}

	@Override
	public String getName() {
		switch (type) {
		case TYPE_IMAGE:
			return "Image";
		case TYPE_BORDER:
			return "Border";
		case TYPE_COLOR:
			return "Color";
		case TYPE_TEXT:
			return "Text";
		default:
			return "<Error>";
		}
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0) {
			PictogramElement pe = pes[0];

			IDecorator decorator;
			switch (type) {
			case TYPE_IMAGE:
				decorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
				break;
			case TYPE_BORDER:
				decorator = new BorderDecorator(IColorConstant.ORANGE, 2, Graphics.LINE_SOLID);
				break;
			case TYPE_COLOR:
				decorator = new ColorDecorator(IColorConstant.DARK_GREEN, IColorConstant.LIGHT_ORANGE);
				break;
			case TYPE_TEXT:
				decorator = new TextDecorator("Decorated with some example text");
				((TextDecorator) decorator).setBackgroundColor(IColorConstant.ORANGE);
				((TextDecorator) decorator).setForegroundColor(IColorConstant.WHITE);
				break;
			default:
				throw new IllegalStateException("Unknown decorator type: " + type);
			}

			SketchToolBehavior toolBehaviorProvider = (SketchToolBehavior) getFeatureProvider()
					.getDiagramTypeProvider().getCurrentToolBehaviorProvider();
			toolBehaviorProvider.addDecorators(pe, decorator);
			getDiagramBehavior().refreshRenderingDecorators(pe);
		}
	}

	@Override
	public boolean isAvailable(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			if (pes == null || pes.length > 1) {
				return false;
			}
			PictogramElement pe = pes[0];
			if (pe instanceof Shape && !(pe instanceof Diagram)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasDoneChanges() {
		return false;
	}
}
