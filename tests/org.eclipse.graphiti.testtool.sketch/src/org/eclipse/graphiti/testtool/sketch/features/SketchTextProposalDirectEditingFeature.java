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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.func.IProposal;
import org.eclipse.graphiti.func.Proposal;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;

/**
 * The Class SketchLabelDirectEditingFeature.
 */
public class SketchTextProposalDirectEditingFeature extends AbstractDirectEditingFeature {

	/**
	 * Instantiates a new sketch label direct editing feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchTextProposalDirectEditingFeature(IFeatureProvider fp) {
		super(fp);
	}

	public int getEditingType() {
		return TYPE_TEXT;
	}

	public String getInitialValue(IDirectEditingContext context) {
		return SketchUtil.getCurrentLabelValue(context.getPictogramElement());
	}

	@Override
	public void setValueAsProposal(IProposal value, IDirectEditingContext context) {
		String newValue = value.getText()+" - "+value.getObject().toString();
		SketchUtil.setCurrentLabelValue(context.getPictogramElement(), newValue);
	}

	@Override
	public boolean isCompletionAvailable() {
		return true;
	}

	@Override
	public boolean isAutoCompletionEnabled() {
		return false;
	}

	@Override
	public IProposal[] getValueProposalsAsProposal(String value, int caretPos, IDirectEditingContext context) {
		return new IProposal[] { new Proposal("proposal_1", context.getPictogramElement()), new Proposal("proposal_2", context.getGraphicsAlgorithm()) }; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
