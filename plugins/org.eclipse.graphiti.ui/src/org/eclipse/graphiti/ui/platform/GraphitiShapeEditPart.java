/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - Bug 341898 - initial API, implementation and documentation
 *    pjpaulin - Bug 352120 - Now uses IDiagramEditorUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.platform;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.IDiagramEditorUI;

/**
 * This is an abstract marker class that indicates that an {@link EditPart} is a
 * Graphiti edit part for a shape. It can e.g. be used to register an
 * {@link IAdapterFactory} against Graphiti edit parts. This is useful e.g. for
 * providing standard property sheets (or
 * org.eclipse.ui.views.properties.tabbed.AdvancedPropertySections inside tabbed
 * property sheets) for a selection in the Graphiti {@link IDiagramEditorUI};
 * simply use this class as for the adaptableType attribute of the adapter
 * factory definition in plugin.xml.
 * <p>
 * Introduced as part of the fix for Bugzilla 341898.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 * @since 0.9
 */
public abstract class GraphitiShapeEditPart extends AbstractGraphicalEditPart implements IFeatureProviderHolder {

	/**
	 * Returns the {@link PictogramElement} (the model object) that this
	 * {@link EditPart} represents.
	 * 
	 * @return the {@link PictogramElement}
	 */
	public abstract PictogramElement getPictogramElement();
}
