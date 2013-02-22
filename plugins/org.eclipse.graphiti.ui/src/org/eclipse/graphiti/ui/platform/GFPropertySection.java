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
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    Bug 336488 - DiagramEditor API
 *    pjpaulin - Bug 352120 - Now uses IDiagramEditorUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.ui.editor.IDiagramEditorUI;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IContributedContentsView;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * The Class GFPropertySection.
 */
public abstract class GFPropertySection extends AbstractPropertySection implements PropertyChangeListener {

	/**
	 * @return the selected pictogram element.
	 */
	protected PictogramElement getSelectedPictogramElement() {
		if (getSelection() instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) getSelection();

			Object firstElement = structuredSelection.getFirstElement();

			if (firstElement instanceof PictogramElement) {
				return (PictogramElement) firstElement;
			}

			EditPart editPart = null;
			if (firstElement instanceof EditPart) {
				editPart = (EditPart) firstElement;
			} else if (firstElement instanceof IAdaptable) {
				editPart = (EditPart) ((IAdaptable) firstElement).getAdapter(EditPart.class);
			}
			if (editPart != null && editPart.getModel() instanceof PictogramElement) {
				return (PictogramElement) editPart.getModel();
			}
		}
		return null;
	}

	/**
	 * Executes the feature and adds it to the command stack.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public void execute(IFeature feature, IContext context) {
		try {
			CommandExec.executeFeatureWithContext(feature, context);
		} catch (Exception e) {
			if (e instanceof OperationCanceledException) {
				// Just log it as info (operation was cancelled on purpose) 
				T.racer().log(IStatus.INFO, "GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			} else {
				// Just log it as an error
				T.racer().error("GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			}
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

	/**
	 * @return the {@link IDiagramEditor} diagram editor.
	 */
	protected IDiagramEditor getDiagramEditor() {
		IWorkbenchPart part = getPart();
		if (part instanceof IDiagramEditorUI) {
			return (IDiagramEditorUI) part;
		}
		IContributedContentsView contributedView = (IContributedContentsView) part.getAdapter(IContributedContentsView.class);
		if (contributedView != null) {
			part = contributedView.getContributingPart();
		}
		if (part instanceof IDiagramEditorUI) {
			return (IDiagramEditorUI) part;
		}

		return null;
	}

	/**
	 * @return the diagram.
	 */
	protected Diagram getDiagram() {
		IDiagramTypeProvider diagramTypeProvider = getDiagramTypeProvider();
		if (diagramTypeProvider == null) {
			return null;
		}
		return diagramTypeProvider.getDiagram();
	}

	/**
	 * @return the diagram type provider.
	 */
	protected IDiagramTypeProvider getDiagramTypeProvider() {
		IDiagramEditor diagramEditor = getDiagramEditor();
		if (diagramEditor == null) {
			return null;
		}
		return diagramEditor.getDiagramTypeProvider();
	}
}
