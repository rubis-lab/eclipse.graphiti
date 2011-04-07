/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
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
package org.eclipse.graphiti.ui.internal.parts.directedit;

import org.eclipse.graphiti.func.IProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;

/**
 *
 */
public class ContentProposalListener implements IContentProposalListener {

	private TextCellEditor textCellEditor;

	/**
	 * 
	 */
	public ContentProposalListener(TextCellEditor textCellEditor) {
		super();
		this.textCellEditor = textCellEditor;
	}

	@Override
	public void proposalAccepted(IContentProposal proposal) {
		if (proposal instanceof ContentProposal) {
			IProposal acceptedProposal = ((ContentProposal) proposal).getProposal();
			if (acceptedProposal != null) {
				textCellEditor.setAcceptedProposal(acceptedProposal);
			}
		}
	}
}
