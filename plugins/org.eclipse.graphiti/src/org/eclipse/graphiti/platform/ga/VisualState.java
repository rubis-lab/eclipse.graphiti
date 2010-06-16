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
package org.eclipse.graphiti.platform.ga;

import java.util.ArrayList;

/**
 * A simple implementation of {@link IVisualState}.
 */
public class VisualState implements IVisualState {

	private ArrayList<IVisualStateChangeListener> changeListeners = new ArrayList<IVisualStateChangeListener>();

	private int actionTargetFeedback = ACTION_TARGET_FEEDBACK_OFF;

	private int selectionFeedback = SELECTION_FEEDBACK_OFF;

	private int hoverFeedback = HOVER_OFF;

	public void addChangeListener(IVisualStateChangeListener listener) {
		changeListeners.add(listener);
	}

	public void removeChangeListener(IVisualStateChangeListener listener) {
		changeListeners.remove(listener);
	}

	protected void fireVisualStateChanged() {
		for (IVisualStateChangeListener listener : changeListeners) {
			listener.visualStateChanged();
		}
	}

	public int getActionTargetFeedback() {
		return actionTargetFeedback;
	}

	public void setActionTargetFeedback(int feedback) {
		int oldActionTargetFeedback = actionTargetFeedback;
		actionTargetFeedback = feedback;
		if (oldActionTargetFeedback != actionTargetFeedback)
			fireVisualStateChanged();
	}

	public int getSelectionFeedback() {
		return selectionFeedback;
	}

	public void setSelectionFeedback(int feedback) {
		int oldSelectionFeedback = selectionFeedback;
		selectionFeedback = feedback;
		if (oldSelectionFeedback != selectionFeedback)
			fireVisualStateChanged();
	}

	public void setHoverFeedback(int feedback) {
		int oldHoverFeedback = hoverFeedback;
		hoverFeedback = feedback;
		if (oldHoverFeedback != hoverFeedback)
			fireVisualStateChanged();

	}

	public int getHoverFeedback() {
		return hoverFeedback;
	}
}
