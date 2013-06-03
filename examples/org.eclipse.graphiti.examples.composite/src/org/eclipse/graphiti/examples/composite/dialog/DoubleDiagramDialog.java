/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 Felix Velasco.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    fvelasco - Initial version
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.composite.dialog;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * A very simple example of a popup that shows 2 Graphiti diagrams side by side.
 * The popup allows basic modifications of the diagrams but does not care about
 * dirty handling.
 * 
 * @since 0.10
 */
public class DoubleDiagramDialog extends Dialog {

	private IResource firstResource;
	private IResource secondResource;

	public DoubleDiagramDialog(Shell parentShell, IResource firstResource, IResource secondResource) {
		super(parentShell);
		this.firstResource = firstResource;
		this.secondResource = secondResource;
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite control = (Composite) super.createDialogArea(parent);

		GridLayout layout = (GridLayout) control.getLayout();
		layout.numColumns = 2;

		URI diagramURI = URI.createPlatformResourceURI(firstResource.getFullPath().toString(), true);
		DiagramComposite diagramComposite = new DiagramComposite(control, SWT.BORDER);

		IDiagramEditorInput input = new DiagramEditorInput(diagramURI, null);
		diagramComposite.setInput(input);

		URI diagramURI2 = URI.createPlatformResourceURI(secondResource.getFullPath().toString(), true);
		DiagramComposite diagramComposite2 = new DiagramComposite(control, SWT.BORDER);

		IDiagramEditorInput input2 = new DiagramEditorInput(diagramURI2, null);
		diagramComposite2.setInput(input2);

		return control;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Two Diagrams Popup (Graphiti Example)");
	}
}
