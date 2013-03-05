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
 *    Felix Velasco (mwenz) - Bug 349416 - Support drag&drop operations on FixPointAnchors
 *                                         the same way as for BoxRelativeAnchors
 *    Benjamin Schmeling - mwenz - Bug 367483 - Support composite connections
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.partfactory;

import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.internal.config.AbstractConfigurationProviderHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.config.IEditPartFactory;
import org.eclipse.graphiti.ui.internal.parts.AdvancedAnchorEditPart;
import org.eclipse.graphiti.ui.internal.parts.CompositeConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.ConnectionDecoratorEditPart;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.graphiti.ui.internal.parts.CurvedConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.internal.parts.FreeFormConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.ManhattanConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.ShapeEditPart;

/**
 * A concrete implementation of the interface IEditPartFactory, which works on a
 * pictogram model.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PictogramsEditPartFactory extends AbstractConfigurationProviderHolder implements IEditPartFactory {

	/**
	 * Creates a new PictogramsEditPartFactory.
	 */
	public PictogramsEditPartFactory(IConfigurationProviderInternal configurationProvider) {
		super(configurationProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart ret = null;
		IConfigurationProviderInternal configurationProvider = getConfigurationProvider();

		Map<?, ?> epRegistry = getConfigurationProvider().getDiagramEditor().getGraphicalViewer()
				.getEditPartRegistry();
		if (epRegistry != null && epRegistry.containsKey(model)) {
			T.racer().warning("PictogramsEditPartFactory.createEditPart()", "edit part for this model already exists"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (model instanceof Shape) {
			Shape shape = (Shape) model;
			if (!(shape instanceof Diagram) && !shape.isActive()) {
				// return ret;
				// the following is a temorary fix just to make it fly
				ret = new ShapeEditPart(configurationProvider, shape);
			}
		}
		if (ret == null) {
			if (model instanceof Diagram) {
				ret = new DiagramEditPart(configurationProvider, (Diagram) model);
			} else if (model instanceof ConnectionDecorator) {
				ret = new ConnectionDecoratorEditPart(configurationProvider, (Shape) model);
			} else if (model instanceof ContainerShape) {
				ret = new ContainerShapeEditPart(configurationProvider, (ContainerShape) model);
			} else if (model instanceof Shape) {
				ret = new ShapeEditPart(configurationProvider, (Shape) model);
			} else if (model instanceof ManhattanConnection) {
				ret = new ManhattanConnectionEditPart(configurationProvider, (ManhattanConnection) model, context);
			} else if (model instanceof FreeFormConnection) {
				ret = new FreeFormConnectionEditPart(configurationProvider, (FreeFormConnection) model, context);
			} else if (model instanceof CurvedConnection) {
				ret = new CurvedConnectionEditPart(configurationProvider, (CurvedConnection) model, context);
			} else if (model instanceof CompositeConnection) {
				ret = new CompositeConnectionEditPart(configurationProvider, (CompositeConnection) model, this, context);
			} else if (model instanceof AdvancedAnchor) {
				ret = new AdvancedAnchorEditPart(configurationProvider, (AdvancedAnchor) model);
			}
		}

		// check whether autoswitch to direct editing has been set
		// if yes: store the affected edit part in the editor for later use in
		// the refresh method
		// if (ret instanceof ShapeEditPart) {
		// IDirectEditingInfo dei =
		// getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider()
		// .getDirectEditingInfo();
		// if (dei.isActive() && model.equals(dei.getMainPictogramElement())) {
		// getConfigurationProvider().getDiagramEditor().setDirectEditingEditPart((ShapeEditPart)
		// ret);
		// }
		// }

		return ret;
	}
}