/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2014, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mwenz - Bug 434658 - execute a feature in an asynchronous job
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.tutorial.features;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractAsynchronousCustomFeature;

public class TutorialLongRunningCustomFeature extends AbstractAsynchronousCustomFeature {

	public TutorialLongRunningCustomFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Long running custom feature";
	}

	@Override
	public String getDescription() {
		return "Simple example for a long running feature that is executed in the background showing a progress monitor";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	@Override
	protected void execute(ICustomContext context, IProgressMonitor monitor) {
		monitor.beginTask("Long work", 10);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				monitor.setCanceled(true);
			}
			monitor.worked(i);
			if (monitor.isCanceled()) {
				break;
			}
		}
		monitor.done();
	}
}
