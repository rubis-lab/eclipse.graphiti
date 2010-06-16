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
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class AbstractDirectEditingFeature.
 */
public abstract class AbstractDirectEditingFeature extends AbstractFeature implements IDirectEditingFeature {

	/**
	 * The Constant EMPTY_STRING_ARRAY.
	 */
	protected static final String[] EMPTY_STRING_ARRAY = new String[0];

	/**
	 * Used to track if direct editing did really change anything
	 */
	private boolean valueChanged = false;

	/**
	 * Instantiates a new abstract direct editing feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractDirectEditingFeature(IFeatureProvider fp) {
		super(fp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature#canExecute
	 * (org.eclipse.graphiti.features.context.IContext)
	 */
	public boolean canExecute(IContext context) {
		final String SIGNATURE = "canExecute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractDirectEditingFeature.class, SIGNATURE, new Object[] { context });
		}
		boolean ret = false;
		if (context instanceof IDirectEditingContext) {
			ret = canDirectEdit((IDirectEditingContext) context);
		}

		if (info) {
			T.racer().exiting(AbstractDirectEditingFeature.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IDirectEditing#canDirectEdit(org.eclipse.graphiti
	 * .features.context.IDirectEditingContext)
	 */
	public boolean canDirectEdit(IDirectEditingContext context) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeature#execute(org.eclipse.graphiti.features
	 * .context.IContext)
	 */
	public void execute(IContext context) {
		// nop
	}

	/*
	 * Returns true only if the direct editing feature really has changed
	 * anything. This is indicated by the execution of the feature in the
	 * DirectEditingFeatureCommandWithContext.execute method
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#isUndoable()
	 */
	@Override
	public boolean hasDoneChanges() {
		return valueChanged;
	}

	/**
	 * Called by the framework (@see DirectEditingFeatureCommandWithContext) to
	 * indicate that this direct editing feature execution has really changed
	 * something. Only in this case there should be an entry in the undo stack
	 * and the editor should get dirty.
	 */
	public final void setValueChanged() {
		valueChanged = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDirectEditingFeature#getPossibleValues
	 * (org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String[] getPossibleValues(IDirectEditingContext context) {
		return EMPTY_STRING_ARRAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDirectEditingFeature#getValueProposals
	 * (java.lang.String,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String[] getValueProposals(String value, int caretPos, IDirectEditingContext context) {
		return EMPTY_STRING_ARRAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDirectEditingFeature#checkValueValid(java
	 * .lang.String,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String checkValueValid(String value, IDirectEditingContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IDirectEditingFeature#completeValue(java
	 * .lang.String, java.lang.String,
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public String completeValue(String value, int caretPos, String choosenValue, IDirectEditingContext context) {
		return choosenValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#stretchTextfieldToFitText()
	 */
	public boolean stretchTextfieldToFitText() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#isAutoCompletionEnabled()
	 */
	public boolean isAutoCompletionEnabled() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.IDirectEditing#isCompletionAvailable()
	 */
	public boolean isCompletionAvailable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.AbstractDirectEditingFeature_0_xfld;
}