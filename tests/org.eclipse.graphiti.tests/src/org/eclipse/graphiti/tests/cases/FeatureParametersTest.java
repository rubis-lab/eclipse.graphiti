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
 *    SAP AG - initial API, implementation and documentation
 *    pjpaulin - Bug 352120 - Added required methods to inner-class IDiagramEditor
 *    
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tests.cases;

import static org.junit.Assert.assertEquals;

import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.features.impl.AbstractFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.tests.reuse.GFAbstractTestCase;
import org.junit.Test;

public class FeatureParametersTest extends GFAbstractTestCase {

	private final class AbstractAddFeatureExtension extends AbstractAddFeature {
		private String property1 = null;

		private AbstractAddFeatureExtension(IFeatureProvider fp) {
			super(fp);
		}

		public boolean canAdd(IAddContext context) {
			return true;
		}

		public PictogramElement add(IAddContext context) {
			property1 = (String) context.getProperty("prop1");
			return null;
		}

		public String getProperty1() {
			return property1;
		}
	}

	private class AbstractFeatureExtension extends AbstractFeature {
		private AbstractFeatureExtension(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public PictogramElement addGraphicalRepresentation(IAreaContext context, Object newObject) {
			// Overriden to get access
			return super.addGraphicalRepresentation(context, newObject);
		}

		public void execute(IContext context) {
		}

		public boolean canExecute(IContext context) {
			return false;
		}
	}

	/*
	 * Test for Bugzilla 394801 - AddGraphicalRepresentation doesn't carry
	 * properties
	 */
	@Test
	public void testContextPropertiesPassedToAddFeature() throws Exception {
		final AbstractAddFeatureExtension addFeature = new AbstractAddFeatureExtension(null);

		IDiagramContainer diagramEditor = new IDiagramContainer() {
			public void setPictogramElementsForSelection(PictogramElement[] pictogramElements) {
			}

			public void setPictogramElementForSelection(PictogramElement pictogramElement) {
			}

			public void selectPictogramElements(PictogramElement[] pictogramElements) {
			}

			public void refreshTitleToolTip() {
			}

			public void refreshTitle() {
			}

			public void refreshRenderingDecorators(PictogramElement pe) {
			}

			public void refreshPalette() {
			}

			public void refresh() {
			}

			public boolean isDirty() {
				return false;
			}

			public PictogramElement[] getSelectedPictogramElements() {
				return null;
			}

			public ResourceSet getResourceSet() {
				return null;
			}

			public IDiagramTypeProvider getDiagramTypeProvider() {
				return null;
			}

			public Object executeFeature(IFeature feature, IContext context) {
				feature.execute(context);
				return null;
			}

			public TransactionalEditingDomain getEditingDomain() {
				return null;
			}

			public void disableAdapters() {
			}

			public void enableAdapters() {
			}

			public void commandStackChanged(EventObject event) {
			}

			public boolean isAlive() {
				return false;
			}

			public void selectBufferedPictogramElements() {
			}

			public void refreshContent() {
			}

			public void editingDomainInitialized() {
			}

			@SuppressWarnings("rawtypes")
			public Object getAdapter(Class type) {
				return null;
			}

			public boolean isLocalEditingDomain() {
				return true;
			}

			public void doSave(IProgressMonitor monitor) {
				// TODO Auto-generated method stub

			}
		};

		AbstractDiagramTypeProvider diagramTypeProvider = new AbstractDiagramTypeProvider() {
		};
		diagramTypeProvider.init(null, diagramEditor);

		AbstractFeatureProvider featureProvider = new AbstractFeatureProvider(diagramTypeProvider) {
			@Override
			public IAddFeature getAddFeature(IAddContext context) {
				return addFeature;
			}

			public IPasteFeature getPasteFeature(IPasteContext context) {
				return null;
			}

			public ICopyFeature getCopyFeature(ICopyContext context) {
				return null;
			}
		};
		AbstractFeatureExtension abstractFeature = new AbstractFeatureExtension(featureProvider);
		
		IAreaContext context = new AreaContext();
		context.putProperty("prop1", "value1");

		abstractFeature.addGraphicalRepresentation(context, null);

		assertEquals("value1", addFeature.getProperty1());
	}
}
