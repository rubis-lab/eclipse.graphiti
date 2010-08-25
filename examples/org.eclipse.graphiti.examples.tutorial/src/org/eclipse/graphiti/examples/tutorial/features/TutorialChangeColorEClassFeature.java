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
package org.eclipse.graphiti.examples.tutorial.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.examples.tutorial.StyleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class TutorialChangeColorEClassFeature extends AbstractCustomFeature {

	private boolean background;

	public TutorialChangeColorEClassFeature(IFeatureProvider fp, boolean background) {
		super(fp);
		this.background = background;
	}

	@Override
	public String getName() {
		String colorType = background ? "&background" : "&foreground"; //$NON-NLS-1$ //$NON-NLS-2$
		return "Change " + colorType + " color"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String getDescription() {
		String colorType = background ? "background" : "foreground"; //$NON-NLS-1$ //$NON-NLS-2$
		return "Change the " + colorType + " color"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes == null || pes.length == 0) { // nothing selected
			return false;
		}

		// return true, if all elements are EClasses
		// note, that in execute() the selected elements are not even accessed,
		// so theoretically it would be possible that canExecute() always
		// returns true. But for usability reasons it is better to check
		// if the selected elements are EClasses.
		for (PictogramElement pe : pes) {
			final Object bo = getBusinessObjectForPictogramElement(pe);
			if (!(bo instanceof EClass)) {
				return false;
			}
		}
		return true;
	}

	public void execute(ICustomContext context) {
		Style style = StyleUtil.getStyleForEClass(getDiagram());

		// let the user choose the new color
		Color currentColor;
		if (background) {
			currentColor = style.getBackground();
		} else {
			currentColor = style.getForeground();
		}
		Color newColor = ExampleUtil.editColor(currentColor);
		if (newColor == null) { // user did not choose new color
			return;
		}

		// set new color
		if (background) {
			style.setBackground(newColor);
		} else {
			style.setForeground(newColor);
		}
	}
}
