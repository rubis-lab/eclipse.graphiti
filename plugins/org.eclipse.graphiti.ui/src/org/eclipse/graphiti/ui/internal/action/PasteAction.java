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
 *    mwenz - Bug 339525 - Enrich paste context with location information
 *    mwenz - Bug 374701 - IPasteContext showing invalid location under certain circumstances
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.PasteContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PasteAction extends AbstractPreDefinedAction {
	private static final String TEXT = Messages.PasteAction_0_xfld;

	private static final String TOOL_TIP = TEXT;

	public static final String ACTION_ID = ActionFactory.PASTE.getId(); // "predefined

	public PasteAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
	}

	public boolean isAvailable() {
		IPasteContext context = createPasteContext();
		IPasteFeature feature = getFeatureProvider().getPasteFeature(context);
		if (feature == null) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		IPasteContext context = createPasteContext();
		if (context.getPictogramElements() == null || context.getPictogramElements().length == 0) {
			return false;
		}
		IFeatureProvider featureProvider = getFeatureProvider();
		if (featureProvider == null) {
			return false;
		}
		IPasteFeature feature = featureProvider.getPasteFeature(context);
		if (feature == null || !feature.canPaste(context)) {
			return false;
		}

		return true;
	}

	@Override
	public void run() {
		IPasteContext context = createPasteContext();
		final IFeatureProvider featureProvider = getFeatureProvider();
		IPasteFeature feature = featureProvider.getPasteFeature(context);
		if (feature != null) {
			final FeatureCommandWithContext command = new GenericFeatureCommandWithContext(feature, context);
			executeOnCommandStack(command);
		}
	}

	private IPasteContext createPasteContext() {
		PictogramElement[] pes = getSelectedPictogramElements();
		PasteContext context = new PasteContext(pes);
		Point pasteLocation = new Point(-1, -1);
		IWorkbenchPart workbenchPart = getWorkbenchPart();
		IDiagramContainerUI diagramContainer = null;
		if (workbenchPart instanceof IDiagramContainerUI) {
			diagramContainer = (IDiagramContainerUI) workbenchPart;
		} else {
			diagramContainer = (IDiagramContainerUI) workbenchPart.getAdapter(IDiagramContainerUI.class);
		}

		if (diagramContainer != null) {
			pasteLocation = diagramContainer.getDiagramBehavior().calculateRealMouseLocation(
					diagramContainer.getDiagramBehavior().getMouseLocation());
			context.setLocation(pasteLocation.x, pasteLocation.y);
		}
		return context;
	}
}
