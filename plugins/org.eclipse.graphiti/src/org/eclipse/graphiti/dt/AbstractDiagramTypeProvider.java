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
	 * Instantiates a new abstract diagram type provider.
	 */
	public AbstractDiagramTypeProvider() {
		super();
	}

	/*
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#createLink(java.lang.Object
	 * [])
	 */
	// public void link(PictogramElement pictogramElement, Object[]
	// businessObjects) {
	// // create link
	// PictogramLink newLink =
	// PackageUtil.getLinksPackage(getDiagram()).getPictogramLink().createPictogramLink();
	// newLink.setPictogramElement(pictogramElement);
	//
	// for (int i = 0; i < businessObjects.length; i++) {
	// List linkBos = newLink.getBusinessObjects();
	// linkBos.add(businessObjects[i]);
	// }
	//
	// // add new link to diagram-link
	// if (getDiagramLink() != null) {
	// getDiagramLink().getPictogramLinks().add(newLink);
	// }
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.dt.IDiagramTypeProvider#
	 * getAvailableToolBehaviorProviders ()
	 */
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (availableToolBehaviorProviders == null) {
			availableToolBehaviorProviders = new IToolBehaviorProvider[] { new DefaultToolBehaviorProvider(this) };
		}
		return availableToolBehaviorProviders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#getCurrentToolBehaviorProvider
	 * ()
	 */
	public IToolBehaviorProvider getCurrentToolBehaviorProvider() {
		IToolBehaviorProvider ret = null;
		if (getAvailableToolBehaviorProviders().length > 0) {
			ret = getAvailableToolBehaviorProviders()[getCurrentToolBahaviorIndex()];
		}
		return ret;
	}

	public int getCurrentToolBahaviorIndex() {
		return currentToolBahaviorIndex;
	}

	public void setCurrentToolBahaviorIndex(int index) {
		if (currentToolBahaviorIndex != index) {
			if (index < 0 || index >= getAvailableToolBehaviorProviders().length) {
				throw new IllegalArgumentException("Index not valid"); //$NON-NLS-1$
			}
			currentToolBahaviorIndex = index;

			IDiagramEditor de = getDiagramEditor();
			de.refresh();
			de.refreshPalette();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagram()
	 */
	public Diagram getDiagram() {
		return diagram;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramTitle()
	 */
	public String getDiagramTitle() {
		String name = ""; //$NON-NLS-1$
		if (getDiagram() != null) {
			name = getDiagram().getName();
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramEditor()
	 */
	public IDiagramEditor getDiagramEditor() {
		return diagramEditor;
	}

	/*
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getActionProvider()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		if (featureProvider == null) {
			T.racer().error("featureProvider is null"); //$NON-NLS-1$
		}
		return featureProvider;
	}

	/*
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#init(org.eclipse.graphiti.mm
	 * .pictograms.Diagram, org.eclipse.graphiti.platform.IPlatformService)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#init(org.eclipse.graphiti.mm
	 * .pictograms.Diagram, org.eclipse.graphiti.platform.IDiagramEditor)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtRuntime()
	 */
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtStartup()
	 */
	public boolean isAutoUpdateAtStartup() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtReset()
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramTitleImage()
	 */
	public String getDiagramTitleImage() {
		return IPlatformImageConstants.IMG_DIAGRAM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#dispose()
	 */
	public void dispose() {
		if (getCurrentToolBehaviorProvider() != null) {
			getCurrentToolBehaviorProvider().dispose();
		}
		if (getFeatureProvider() != null) {
			getFeatureProvider().dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#getNotificationService()
	 */
	public INotificationService getNotificationService() {
		if (notificationService == null) {
			notificationService = new DefaultNotificationService(this);
		}
		return notificationService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.dt.IDiagramTypeProvider#getRelatedBusinessObjects(
	 * java.lang.Object[])
	 */
	public Object[] getRelatedBusinessObjects(Object[] bos) {
		return new Object[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.dt.IDiagramTypeProvider#
	 * getGraphicsAlgorithmRendererFactory()
	 */
	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#postInit()
	 */
	public void postInit() {
	}

	@Override
	public void resourceReloaded(Diagram diagram) {
		setDiagram(diagram);
	}
}