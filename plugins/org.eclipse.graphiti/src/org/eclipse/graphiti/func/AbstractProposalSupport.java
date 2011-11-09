package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.IDirectEditingContext;

/**
 * @since 0.8
 */
public class AbstractProposalSupport implements IProposalSupport {
	protected static final IProposal[] EMPTY_PROPOSAL_ARRAY = new IProposal[0];

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IProposalSupport#getPossibleValues(org.eclipse
	 * .graphiti.features.context.IDirectEditingContext)
	 */
	public IProposal[] getPossibleValues(IDirectEditingContext context) {
		return EMPTY_PROPOSAL_ARRAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IProposalSupport#completeValue(java.lang.String
	 * , int, org.eclipse.graphiti.func.IProposal,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String completeValue(String value, int caretPosition, IProposal choosenValue, IDirectEditingContext context) {
		return choosenValue.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IProposalSupport#checkValueValid(java.lang.
	 * String, org.eclipse.graphiti.func.IProposal,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String checkValueValid(String text, IProposal proposal, IDirectEditingContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IProposalSupport#getValueProposals(java.lang
	 * .String, int,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public IProposal[] getValueProposals(String value, int caretPosition, IDirectEditingContext context) {
		return EMPTY_PROPOSAL_ARRAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IProposalSupport#setValue(java.lang.String,
	 * org.eclipse.graphiti.func.IProposal,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public void setValue(String text, IProposal proposal, IDirectEditingContext context) {
	}

}
