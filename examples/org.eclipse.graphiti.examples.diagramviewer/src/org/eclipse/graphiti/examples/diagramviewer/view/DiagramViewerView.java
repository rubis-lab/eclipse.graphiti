/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2013, 2013 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - initial API implementation

 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.diagramviewer.view;

import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.examples.diagramviewer.Activator;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.ViewPart;

public class DiagramViewerView extends ViewPart {

	private PageBook book;
	private Label noDiagramLabel;
	private DiagramComposite diagramComposite;

	private DiagramEditorInput input = null;

	public DiagramViewerView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		book = new PageBook(parent, SWT.NONE);

		noDiagramLabel = new Label(book, SWT.NONE);
		noDiagramLabel.setText("No diagram to display");

		diagramComposite = createDiagramComposite();
	}

	private DiagramComposite createDiagramComposite() {
		DiagramComposite diagramComposite = new DiagramComposite(this, book, SWT.NONE);
		diagramComposite.setLayout(new FillLayout());
		diagramComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		return diagramComposite;
	}

	@Override
	public void setFocus() {
		diagramComposite.setFocus();
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if (diagramComposite != null && !diagramComposite.isDisposed() && input != null) {
			return diagramComposite.getAdapter(type);
		}

		return super.getAdapter(type);
	}

	public void showDiagram(URI uri) {
		if (uri != null) {
			input = new DiagramEditorInput(uri, Activator.DIAGRAM_VIEWER_DIAGRAM_TYPE_PROVIDER);
			if (diagramComposite != null) {
				diagramComposite.dispose();
				diagramComposite = createDiagramComposite();
			}
			diagramComposite.setInput(input);
			book.showPage(diagramComposite);
		} else {
			input = null;
			book.showPage(noDiagramLabel);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		noDiagramLabel.dispose();
		diagramComposite.dispose();
		book.dispose();

		noDiagramLabel = null;
		diagramComposite = null;
		book = null;

		input = null;
	}
}
