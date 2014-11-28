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
 *    mwenz - Bug 453553 - Provide an abort possibility for delete and remove features in case 'pre' methods fail
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.features;

import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.junit.Test;

public class DefaultRemoveFeatureTest {

	@Test
	public void testIsAbortAfterPreDelete() {
		IContext context = EasyMock.createNiceMock(IRemoveContext.class);
		EasyMock.replay(context);
		IFeatureProvider featureProvider = EasyMock.createNiceMock(IFeatureProvider.class);
		EasyMock.replay(featureProvider);

		IFeature feature = new TestRemoveFeatureAbort(featureProvider);

		try {
			feature.execute(context);
			fail("Must not be reached");
		} catch (OperationCanceledException e) {
			// expected
		}
	}

	private class TestRemoveFeatureAbort extends DefaultRemoveFeature {

		public TestRemoveFeatureAbort(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canRemove(IRemoveContext context) {
			return true;
		}

		@Override
		public boolean isRemoveAbort() {
			return true;
		}

		@Override
		protected void removeAllConnections(Anchor anchor) {
			fail("Must not be reached");
		}

		@Override
		public void remove(IRemoveContext context) {
			super.remove(context);
		}
	}
}
