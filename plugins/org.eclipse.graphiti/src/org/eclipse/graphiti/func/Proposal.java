package org.eclipse.graphiti.func;

public class Proposal implements IProposal {

	private String text;

	private Object object;

	public Proposal(String text) {
		super();
		this.text = text;
	}

	public Proposal(String text, Object object) {
		this(text);
		setObject(object);
	}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public String getText() {
		return text;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Proposal[] textToProposals(String[] texts) {
		Proposal[] ret = new Proposal[texts.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new Proposal(texts[i]);
		}
		return ret;
	}

	public static String[] ProposalsToTexts(IProposal[] proposals) {
		String[] ret = new String[proposals.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = proposals[i].getText();
		}
		return ret;
	}

}
