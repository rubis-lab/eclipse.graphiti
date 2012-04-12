/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Henrik Rentz-Reichert - mwenz - Bug 376544 - bug in re-connecting a connection with identical start and end anchor
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tests.cases;

import static org.junit.Assert.assertEquals;

import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.junit.Test;

public class ReconnectionTest extends GFAbstractTestCase {

	public ReconnectionTest() {
		super();
	}

	@Test
	public void testReconnectLoopToOtherAnchor() throws Exception {
		IPeService peService = Graphiti.getPeService();

		Shape shape = peService.createShape(null, true);
		BoxRelativeAnchor anchor1 = peService.createBoxRelativeAnchor(shape);
		BoxRelativeAnchor anchor2 = peService.createBoxRelativeAnchor(shape);
		FreeFormConnection connection = peService.createFreeFormConnection(null);
		connection.setStart(anchor1);
		connection.setEnd(anchor1);

		ReconnectionContext context = new ReconnectionContext(connection, anchor1, anchor2, null);
		context.setReconnectType(ReconnectionContext.RECONNECT_TARGET);

		DefaultReconnectionFeature reconnectionFeature = new DefaultReconnectionFeature(null);
		reconnectionFeature.reconnect(context);

		assertEquals(anchor2, connection.getEnd());
	}
}
