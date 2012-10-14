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
package org.eclipse.graphiti.testtool.sketch.features.create;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class SketchCreateFreeformConnectionFeature.
 */
public class SketchCreateFreeformConnectionFeature extends AbstractSketchCreateSimpleConnectionFeature {

	private static boolean cancel = false;

	public static void setCancelling(boolean cancel) {
		SketchCreateFreeformConnectionFeature.cancel = cancel;
	}

	public static boolean isCancelling() {
		return cancel;
	}

	/**
	 * Instantiates a new sketch create freeform connection feature.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public SketchCreateFreeformConnectionFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	protected Connection createConnection() {
		if (isCancelling())
			throw new OperationCanceledException();
		return Graphiti.getPeCreateService().createFreeFormConnection(getDiagram());
	}
}
