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
 *    mwenz - Bug 323155 - Check usage scenarios for DefaultPrintFeature and
 *            DefaultSaveImageFeature
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.features;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.features.impl.AbstractSaveImageFeature;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

/**
 * The default feature implementation for saving a diagram to an image. This
 * feature is used to trigger saving from inside an open and initialized
 * {@link DiagramEditor}. It relies on an existing {@link GraphicalViewer}
 * showing the diagram to save.
 * 
 * @since 0.10 Has been moved from plug-in org.eclipse.graphiti package
 *        org.eclipse.graphiti.features
 */
public class DefaultSaveImageFeature extends AbstractSaveImageFeature implements ISaveImageFeature {

	/**
	 * Creates a new {@link DefaultSaveImageFeature}.
	 * 
	 * @param fp
	 *            The feature provider providing this feature
	 */
	public DefaultSaveImageFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * TODO
	 * 
	 * @since 0.10
	 */
	public void save(ISaveImageContext context) {

		// Get viewer containing the diagram to print (by default the one
		// contained in the diagram editor that starts this feature
		GraphicalViewer viewer = getGraphicalViewer(context);

		// TODO This needs to be split up, opened and made configurable by
		// clients
		GraphitiUiInternal.getUiService().startSaveAsImageDialog(viewer);
	}

	/**
	 * Must return a {@link GraphicalViewer} that contains the diagram to be
	 * saved. The default implementation returns the viewer of the
	 * {@link DiagramEditor} that started this save as image feature; this is
	 * the one associated to the feature provider of the currently opened
	 * diagram, see {@link #getDiagramEditor()}.
	 * 
	 * @param context
	 *            Context information for saving.
	 * @return the viewer holding the diagram to save.
	 */
	protected GraphicalViewer getGraphicalViewer(ISaveImageContext context) {
		DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
		return (GraphicalViewer) diagramEditor.getAdapter(GraphicalViewer.class);
	}
}
