/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 329523 - Add notification of DiagramTypeProvider after saving a diagram
 *    mwenz - Bug 352109 - Enable auto-update option for saved editor
 *    fvelasco - Bug 323349 - Enable external invocation of features
 *    mwenz - Bug 434684 - DiagramProvider should rewire Diagram on refresh
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.dt;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.notification.DefaultNotificationService;
import org.eclipse.graphiti.notification.INotificationService;
import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * The Class AbstractDiagramTypeProvider.
 * 
 */
@SuppressWarnings("deprecation")
public abstract class AbstractDiagramTypeProvider extends AbstractExtension implements IDiagramTypeProvider {

	private IToolBehaviorProvider[] availableToolBehaviorProviders = null;

	private Diagram diagram;

	private IDiagramBehavior diagramBehavior;

	private IFeatureProvider featureProvider;

	private INotificationService notificationService;

	private int currentToolBehaviorIndex = 0;

	private String contextId;

	/**
	 * Creates a new {@link AbstractDiagramTypeProvider}.
	 */
	public AbstractDiagramTypeProvider() {
		super();
	}

	/**
	 * Returns all available tool behavior providers
	 * 
	 * @return An array of all registered tool behavior providers
	 */
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (this.availableToolBehaviorProviders == null) {
			this.availableToolBehaviorProviders = new IToolBehaviorProvider[] { new DefaultToolBehaviorProvider(this) };
		}
		return this.availableToolBehaviorProviders;
	}

	public IToolBehaviorProvider getCurrentToolBehaviorProvider() {
		IToolBehaviorProvider ret = null;
		if (getAvailableToolBehaviorProviders().length > 0) {
			ret = getAvailableToolBehaviorProviders()[getCurrentToolBehaviorIndex()];
		}
		return ret;
	}

	public int getCurrentToolBehaviorIndex() {
		return this.currentToolBehaviorIndex;
	}

	public void setCurrentToolBehaviorIndex(int index) {
		if (this.currentToolBehaviorIndex != index) {
			if (index < 0 || index >= getAvailableToolBehaviorProviders().length) {
				throw new IllegalArgumentException("Index not valid"); //$NON-NLS-1$
			}
			this.currentToolBehaviorIndex = index;

			IDiagramBehavior diagramBehavior = getDiagramBehavior();
			diagramBehavior.refresh();
			diagramBehavior.refreshPalette();
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

	/**
	 * @deprecated Use {@link #getDiagramBehavior()} instead
	 */
	public IDiagramEditor getDiagramEditor() {
		return getDiagramBehavior().getDiagramContainer();
	}

	/**
	 * @since 0.10
	 */
	public IDiagramBehavior getDiagramBehavior() {
		return diagramBehavior;
	}

	public IFeatureProvider getFeatureProvider() {
		if (this.featureProvider == null) {
			T.racer().error("featureProvider is null"); //$NON-NLS-1$
		}
		return this.featureProvider;
	}

	/**
	 * @since 0.10
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * @since 0.10
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	/**
	 * @deprecated Use {@link #init(Diagram, IDiagramBehavior)} instead
	 */
	public void init(Diagram diagram, IDiagramEditor diagramEditor) {
		setAndWireDiagram(diagram);
		setDiagramBehavior(((IDiagramContainer) diagramEditor).getDiagramBehavior());
	}

	/**
	 * @since 0.10
	 */
	public void init(Diagram diagram, IDiagramBehavior diagramBehavior) {
		setAndWireDiagram(diagram);
		setDiagramBehavior(diagramBehavior);
	}

	private void setDiagramBehavior(IDiagramBehavior diagramBehavior) {
		this.diagramBehavior = diagramBehavior;
	}

	/**
	 * @param diagram
	 *            The diagram to set.
	 */
	private void setAndWireDiagram(Diagram diagram) {
		this.diagram = diagram;
		GraphitiInternal.getEmfService().wireDTPToDiagram(diagram, this);
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

	/**
	 * @since 0.9
	 */
	public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
		return false;
	}

	public boolean isAutoUpdateAtStartup() {
		return false;
	}

	public boolean isAutoUpdateAtReset() {
		return true;
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

	public void resourceReloaded(Diagram diagram) {
		setAndWireDiagram(diagram);
	}

	public void resourcesSaved(Diagram diagram, Resource[] savedResources) {
	}
}
