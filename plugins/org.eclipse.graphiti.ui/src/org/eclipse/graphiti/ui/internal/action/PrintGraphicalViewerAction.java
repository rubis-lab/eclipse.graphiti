/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 371527 - Recursive attempt to activate part while in the middle of activating part
 *    mwenz - Bug 370888 - API Access to export and print
 *    mwenz - Bug 449384 - PrintGraphicalViewerAction calculateEnabled() freezes Editor
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPrintFeature;
import org.eclipse.graphiti.features.context.IPrintContext;
import org.eclipse.graphiti.features.context.impl.PrintContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.ICommand;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * An Action, which is used to print the contents of the GraphicalViewer.
 * <p>
 * Additional to the usual printing behaviour (the dialog, where the printer can
 * be selected), this Action also adds another dialog, where the size and
 * position of the graphics on the paper can be specified.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PrintGraphicalViewerAction extends PrintAction {

	/**
	 * This static Action is just used as a template, to initialize the ID,
	 * label and image of instances of this class accordingly.
	 */
	private static IAction TEMPLATE_ACTION = ActionFactory.PRINT.create(PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow());

	private IConfigurationProvider configurationProvider;

	/**
	 * Creates a new PrintGraphicalViewerAction. It initializes it with the
	 * proper ID, label and image.
	 * 
	 * @param configurationProvider
	 *            The IConfigurationProviderInternal.
	 * @param part
	 *            The WorkbenchPart (e.g. the editor), to which this Action
	 *            belongs. From the WorkbenchPart the GraphicalViewer will be
	 *            determined.
	 */
	public PrintGraphicalViewerAction(IDiagramBehavior diagramBehavior, IConfigurationProvider configurationProvider) {
		super(((IDiagramContainerUI) diagramBehavior.getDiagramContainer()).getWorkbenchPart());
		this.configurationProvider = configurationProvider;

		// set all values of the TEMPLATE_ACTION for this Action.
		setId(TEMPLATE_ACTION.getId());
		setText(TEMPLATE_ACTION.getText());
		setToolTipText(TEMPLATE_ACTION.getToolTipText());
		setDescription(TEMPLATE_ACTION.getDescription());
		setAccelerator(TEMPLATE_ACTION.getAccelerator());
		setHelpListener(TEMPLATE_ACTION.getHelpListener());
		setImageDescriptor(TEMPLATE_ACTION.getImageDescriptor());
		setHoverImageDescriptor(TEMPLATE_ACTION.getHoverImageDescriptor());
		setDisabledImageDescriptor(TEMPLATE_ACTION.getDisabledImageDescriptor());
	}

	/**
	 * Same as super.calculateEnabled(), except that it also checks, if the
	 * current WorkbenchPart has a GraphicalViewer.
	 */
	@Override
	protected boolean calculateEnabled() {
		IFeatureProvider featureProvider = getFeatureProvider();
		if (featureProvider == null) {
			return false;
		}
		IPrintFeature feature = featureProvider.getPrintFeature();
		IPrintContext context = createPrintContext();
		if (feature == null || !feature.canPrint(context)) {
			return false;
		}

		if (getWorkbenchPart().getAdapter(GraphicalViewer.class) == null) {
			return false;
		}

		/*
		 * Do not check for the availability of printers to enable/disable the
		 * action as the super method would do. E.g. on Linux this might cause
		 * the UI to be hanging when opening an editor.
		 * 
		 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=449384
		 */
		return true;
	}

	/**
	 * Prints the GraphicalViewer of the WorkbenchPart. It is the same as
	 * super.run(), except that it opens the PrintModeDialog before printing. In
	 * this dialog the size of the graphics (fit-width, fit-height, ...) can be
	 * specified.
	 */
	@Override
	public void run() {
		IPrintContext context = createPrintContext();
		final IFeatureProvider featureProvider = getFeatureProvider();
		IPrintFeature feature = featureProvider.getPrintFeature();
		if (feature != null) {
			final FeatureCommandWithContext command = new GenericFeatureCommandWithContext(feature, context);
			executeOnCommandStack(command);
		}
	}

	private IPrintContext createPrintContext() {
		PrintContext context = new PrintContext();
		return context;
	}

	private IFeatureProvider getFeatureProvider() {
		return configurationProvider.getDiagramTypeProvider().getFeatureProvider();
	}

	private void executeOnCommandStack(ICommand command) {
		CommandStack commandStack = configurationProvider.getDiagramEditor().getEditDomain().getCommandStack();
		GefCommandWrapper wrapperCommand = new GefCommandWrapper(command, configurationProvider.getDiagramBehavior()
				.getEditingDomain());
		commandStack.execute(wrapperCommand);
	}
}
