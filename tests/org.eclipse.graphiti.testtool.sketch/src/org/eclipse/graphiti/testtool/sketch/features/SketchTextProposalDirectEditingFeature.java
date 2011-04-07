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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.func.IProposal;
import org.eclipse.graphiti.func.IProposalSupport;
import org.eclipse.graphiti.func.Proposal;
import org.eclipse.graphiti.testtool.sketch.SketchUtil;

/**
 * The Class SketchLabelDirectEditingFeature.
 */
public class SketchTextProposalDirectEditingFeature extends SketchTextDirectEditingFeature {

	protected static final IProposal[] EMPTY_PROPOSAL_ARRAY = new IProposal[0];

	private IProposalSupport proposalSupport = null;

	/**
	 * Instantiates a new sketch label direct editing feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public SketchTextProposalDirectEditingFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public IProposalSupport getProposalSupport() {
		if (proposalSupport == null) {
			proposalSupport = new IProposalSupport() {

				@Override
				public void setValue(String value, IProposal proposal, IDirectEditingContext context) {
					String text = value;
					if (proposal != null && proposal.getText() != null) {
						text = proposal.getText();
					}
					SketchUtil.setCurrentLabelValue(context.getPictogramElement(), text);
				}

				@Override
				public IProposal[] getValueProposals(String value, int caretPos, IDirectEditingContext context) {
					return new IProposal[] {
							new Proposal("proposal_1", context.getPictogramElement()), new Proposal("proposal_2", context.getGraphicsAlgorithm()) }; //$NON-NLS-1$ //$NON-NLS-2$
				}

				@Override
				public IProposal[] getPossibleValues(IDirectEditingContext context) {
					return EMPTY_PROPOSAL_ARRAY;
				}

				@Override
				public String completeValue(String value, int caretPosition, IProposal choosenValue, IDirectEditingContext context) {
					return choosenValue.getText();
				}

				@Override
				public String checkValueValid(String text, IProposal proposal, IDirectEditingContext context) {
					return null;
				}
			};
		}

		return proposalSupport;
	}
}
