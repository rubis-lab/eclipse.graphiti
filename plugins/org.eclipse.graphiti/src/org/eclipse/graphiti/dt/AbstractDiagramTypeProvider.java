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
package org.eclipse.graphiti.dt;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.notification.DefaultNotificationService;
import org.eclipse.graphiti.notification.INotificationService;
import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Class AbstractDiagramTypeProvider.
 */
public abstract class AbstractDiagramTypeProvider extends AbstractExtension implements IDiagramTypeProvider {

	private IToolBehaviorProvider[] availableToolBehaviorProviders = null;

	private Diagram diagram;

	private IDiagramEditor diagramEditor;

	private IFeatureProvider featureProvider;

	private INotificationService notificationService;

	private int currentToolBahaviorIndex = 0;

	/**
	 * Creates a new {@link AbstractDiagramTypeProvider}.
	 */
	public AbstractDiagramTypeProvider() {
		super();
	}

	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (this.availableToolBehaviorProviders == null) {
			this.availableToolBehaviorProviders = new IToolBehaviorProvider[] { new DefaultToolBehaviorProvider(this) };
		}
		return this.availableToolBehaviorProviders;
	}

	public IToolBehaviorProvider getCurrentToolBehaviorProvider() {
		IToolBehaviorProvider ret = null;
		if (getAvailableToolBehaviorProviders().length > 0) {
			ret = getAvailableToolBehaviorProviders()[getCurrentToolBahaviorIndex()];
		}
		return ret;
	}

	public int getCurrentToolBahaviorIndex() {
		return this.currentToolBahaviorIndex;
	}

	public void setCurrentToolBahaviorIndex(int index) {
		if (this.currentToolBahaviorIndex != index) {
			if (index < 0 || index >= getAvailableToolBehaviorProviders().length) {
				throw new IllegalArgumentException("Index not valid"); //$NON-NLS-1$
			}
			this.currentToolBahaviorIndex = index;

			IDiagramEditor de = getDiagramEditor();
			de.refresh();
			de.refreshPalette();
		}
	}

	public Diagram getDiagram() {
		return this.diagram;
	}

	public String getDiagramTitle() {
		String name = ""; //$NON-NLS-1$
		if (getDiagram() != null) {
			name = getDiagram().getName();
		}
		return name;
	}

	public IDiagramEditor getDiagramEditor() {
		return this.diagramEditor;
	}

	public IFeatureProvider getFeatureProvider() {
		if (this.featureProvider == null) {
			T.racer().error("featureProvider is null"); //$NON-NLS-1$
		}
		return this.featureProvider;
	}

	public void init(Diagram diagram, IDiagramEditor diagramEditor) {
		setDiagram(diagram);
		setDiagramEditor(diagramEditor);
	}

	private void setDiagramEditor(IDiagramEditor diagramEditor) {
		this.diagramEditor = diagramEditor;
	}

	/**
	 * @param diagram
	 *            The diagram to set.
	 */
	private void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	/**
	 * Sets the feature provider.
	 * 
	 * @param featureProvider
	 *            The featureProvider to set.
	 */
	protected void setFeatureProvider(IFeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	public boolean isAutoUpdateAtRuntime() {
		return true;
	}

	public boolean isAutoUpdateAtStartup() {
		return false;
	}

	@Override
	public boolean isAutoUpdateAtReset() {
		return true;
	}

	public String getDiagramTitleImage() {
		return IPlatformImageConstants.IMG_DIAGRAM;
	}

	public void dispose() {
		if (getCurrentToolBehaviorProvider() != null) {
			getCurrentToolBehaviorProvider().dispose();
		}
		if (getFeatureProvider() != null) {
			getFeatureProvider().dispose();
		}
	}

	public INotificationService getNotificationService() {
		if (this.notificationService == null) {
			this.notificationService = new DefaultNotificationService(this);
		}
		return this.notificationService;
	}

	public Object[] getRelatedBusinessObjects(Object[] bos) {
		return new Object[0];
	}

	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return null;
	}

	public void postInit() {
	}

	@Override
	public void resourceReloaded(Diagram diagram) {
		setDiagram(diagram);
	}
}