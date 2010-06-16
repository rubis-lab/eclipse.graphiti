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
package org.eclipse.graphiti.ui.internal.platform;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorDummy;
import org.eclipse.graphiti.ui.platform.IImageProvider;
import org.eclipse.graphiti.ui.platform.PlatformImageProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.ui.services.IExtensionManager;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ExtensionManager implements IExtensionManager {

	private static ExtensionManager singleton;

	private static String EP_DIAGRAM_TYPES = "org.eclipse.graphiti.ui.diagramTypes"; //$NON-NLS-1$

	private static String EP_IMAGE_PROVIDERS = "org.eclipse.graphiti.ui.imageProviders"; //$NON-NLS-1$

	private static String EP_DIAGRAM_TYPE_PROVIDERS = "org.eclipse.graphiti.ui.diagramTypeProviders"; //$NON-NLS-1$

	private static final String EP_CHILD_NODE_IMAGE_PROVIDER = "imageProvider"; //$NON-NLS-1$

	private static final String EP_CHILD_NODE_DIAGRAM_TYPE = "diagramType"; //$NON-NLS-1$

	private static final String EP_ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$

	private static final String EP_ATTRIBUTE_ID = "id"; //$NON-NLS-1$

	private static final String EP_ATTRIBUTE_TYPE = "type"; //$NON-NLS-1$

	private static final String EP_ATTRIBUTE_NAME = "name"; //$NON-NLS-1$

	private static final String EP_ATTRIBUTE_DESCRIPTION = "description"; //$NON-NLS-1$

	private IDiagramType diagramTypes[] = null;

	private IImageProvider imageProviders[] = null;

	/**
	 * 
	 */
	private ExtensionManager() {
		super();
		searchForExtensions();
	}

	public static ExtensionManager getSingleton() {
		if (singleton == null) {
			singleton = new ExtensionManager();
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.platform.IExtensionManager#
	 * getDiagramTypeProviderExtensionIds(String diagramTypeId)
	 */
	public String[] getDiagramTypeProviderExtensionIds(String diagramTypeId) {
		String ret[] = new String[0];
		if (diagramTypeId == null) {
			return ret;
		}
		List<String> retList = new ArrayList<String>();

		// query diagram type providers registered with the new extension point
		List<String> diagramTypeProviderExtensionIdsNewEP = getDiagramTypeProviderExtensionIdsNewEP(diagramTypeId);
		retList.addAll(diagramTypeProviderExtensionIdsNewEP);

		ret = retList.toArray(ret);
		return ret;
	}

	private void searchForExtensions() {

		// read and store all diagram types which are registered with the new
		// extension point
		List<IDiagramType> diagramTypeListNewExtensionPoint = createDiagramTypesNewEP();
		diagramTypes = diagramTypeListNewExtensionPoint.toArray(new IDiagramType[0]);

		imageProviders = new IImageProvider[0];

		// load image providers from the framework
		loadImageProvider(PlatformImageProvider.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.platform.IExtensionManager#createDiagramTypeProvider
	 * (java.lang.String)
	 */
	public IDiagramTypeProvider createDiagramTypeProvider(String providerId) {
		if (providerId == null) {
			return null;
		}
		IDiagramTypeProvider diagramTypeProvider = null;
		// try to get diagram type provider from the new extension point
		diagramTypeProvider = createDiagramTypeProviderNewEP(providerId);

		return diagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.platform.IExtensionManagerEclipse
	 * #getImageProviders()
	 */
	public IImageProvider[] getImageProviders() {
		return imageProviders;
	}

	public IDiagramType[] getDiagramTypes() {
		return diagramTypes;
	}

	private IDiagramTypeProvider createDiagramTypeProviderNewEP(String providerId) {
		IDiagramTypeProvider diagramTypeProvider = null;

		if (providerId == null) {
			return diagramTypeProvider;
		}
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(EP_DIAGRAM_TYPE_PROVIDERS);
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] configurationElements = extension.getConfigurationElements();
			for (int j = 0; j < configurationElements.length; j++) {
				IConfigurationElement element = configurationElements[j];
				String extensionId = element.getAttribute(EP_ATTRIBUTE_ID);
				if (extensionId != null && extensionId.equals(providerId)) {
					String name = element.getAttribute(EP_ATTRIBUTE_NAME);
					String description = element.getAttribute(EP_ATTRIBUTE_DESCRIPTION);
					if (description == null) {
						description = ""; //$NON-NLS-1$
					}
					if (name != null) {
						// read references to image extensions and try to
						// instantiate image provider
						IConfigurationElement[] children = element.getChildren();
						for (int k = 0; k < children.length; k++) {
							IConfigurationElement childElement = children[k];
							String childName = childElement.getName();
							String childExtensionId = childElement.getAttribute(EP_ATTRIBUTE_ID);
							if (childName != null && childExtensionId != null) {
								if (EP_CHILD_NODE_IMAGE_PROVIDER.equals(childName)) {
									boolean ret = loadImageProvider(childExtensionId);
									if (ret == false) {
										return null;
									}
								}
							}
						}
						try {
							Object executableExtension = element.createExecutableExtension(EP_ATTRIBUTE_CLASS);
							if (executableExtension instanceof IDiagramTypeProvider) {
								diagramTypeProvider = (IDiagramTypeProvider) executableExtension;
								diagramTypeProvider.setProviderId(extensionId);
								// TODO later store also name and description -
								// is only necessary if this information
								// are shown in the UI
							}
						} catch (CoreException e) {
							// $JL-EXC$
							T.racer().error(e.toString());
						}
					}
				}
			}
		}

		return diagramTypeProvider;
	}

	private List<String> getDiagramTypeProviderExtensionIdsNewEP(String diagramTypeId) {
		List<String> retList = new ArrayList<String>();

		if (diagramTypeId == null) {
			return retList;
		}
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(EP_DIAGRAM_TYPE_PROVIDERS);
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] configurationElements = extension.getConfigurationElements();
			for (int j = 0; j < configurationElements.length; j++) {
				IConfigurationElement element = configurationElements[j];
				String extensionId = element.getAttribute(EP_ATTRIBUTE_ID);
				if (extensionId != null) {
					// read references to diagram types
					IConfigurationElement[] children = element.getChildren();
					for (int k = 0; k < children.length; k++) {
						IConfigurationElement childElement = children[k];
						String childName = childElement.getName();
						String childExtensionId = childElement.getAttribute(EP_ATTRIBUTE_ID);
						if (childName != null && childExtensionId != null) {
							if (EP_CHILD_NODE_DIAGRAM_TYPE.equals(childName)) {
								String typeId = getDiagramTypeIdForDiagramTypeExtensionId(childExtensionId);
								if (diagramTypeId.equals(typeId)) {
									retList.add(extensionId);
									break;
								}
							}
						}
					}
				}
			}
		}

		return retList;
	}

	private String getDiagramTypeIdForDiagramTypeExtensionId(String extensionId) {
		if (extensionId != null) {
			IDiagramType diagramTypes[] = getDiagramTypes();
			for (int i = 0; i < diagramTypes.length; i++) {
				IDiagramType diagramType = diagramTypes[i];
				if (extensionId.equals(diagramType.getProviderId())) {
					return diagramType.getId();
				}
			}
		}
		return null;
	}

	private boolean loadImageProvider(String extensionId) {

		// check whether the image provider is already loaded
		IImageProvider providers[] = getImageProviders();
		for (int i = 0; i < providers.length; i++) {
			IImageProvider provider = providers[i];
			if (extensionId.equals(provider.getProviderId())) {
				return true;
			}
		}
		IImageProvider newProvider = createImageProviderNewEP(extensionId);
		if (newProvider != null) {

			// extend current list of image providers
			IImageProvider imageProvidersDest[] = new IImageProvider[imageProviders.length + 1];
			System.arraycopy(imageProviders, 0, imageProvidersDest, 0, imageProviders.length);
			imageProvidersDest[imageProviders.length] = newProvider;
			imageProviders = imageProvidersDest;

			return true;
		}

		return false;
	}

	private List<IDiagramType> createDiagramTypesNewEP() {

		List<IDiagramType> ret = new ArrayList<IDiagramType>();

		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(EP_DIAGRAM_TYPES);
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] configurationElements = extension.getConfigurationElements();
			for (int j = 0; j < configurationElements.length; j++) {
				IConfigurationElement element = configurationElements[j];
				String extensionId = element.getAttribute(EP_ATTRIBUTE_ID);
				String typeId = element.getAttribute(EP_ATTRIBUTE_TYPE);
				String typeName = element.getAttribute(EP_ATTRIBUTE_NAME);
				String typeDescription = element.getAttribute(EP_ATTRIBUTE_DESCRIPTION);
				if (typeDescription == null) {
					typeDescription = ""; //$NON-NLS-1$
				}
				if (extensionId != null && typeId != null && typeName != null) {
					IDiagramType diagramType = new DiagramTypeImpl(typeId, typeName, typeDescription);
					diagramType.setProviderId(extensionId);
					ret.add(diagramType);
				}
			}
		}
		return ret;
	}

	private IImageProvider createImageProviderNewEP(String providerId) {

		if (providerId == null) {
			return null;
		}
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(EP_IMAGE_PROVIDERS);
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] configurationElements = extension.getConfigurationElements();
			for (int j = 0; j < configurationElements.length; j++) {
				IConfigurationElement element = configurationElements[j];
				String extensionId = element.getAttribute(EP_ATTRIBUTE_ID);
				if (providerId.equals(extensionId)) {
					try {
						Object executableExtension = element.createExecutableExtension(EP_ATTRIBUTE_CLASS);
						if (executableExtension instanceof IImageProvider) {
							IContributor contributor = extension.getContributor();
							if (contributor != null) {
								String pluginId = contributor.getName();
								if (pluginId != null) {
									IImageProvider imageProvider = (IImageProvider) executableExtension;
									imageProvider.setProviderId(extensionId);
									imageProvider.setPluginId(pluginId);
									return imageProvider;
								}
							}
						}
					} catch (CoreException e) {
						// $JL-EXC$
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public IFeatureProvider createFeatureProvider(Diagram diagram) {
		String[] dtpExtensionIds = getDiagramTypeProviderExtensionIds(diagram.getDiagramTypeId());
		if (dtpExtensionIds != null && dtpExtensionIds.length > 0) {
			IDiagramTypeProvider dtp = createDiagramTypeProvider(diagram, dtpExtensionIds[0]);
			return dtp.getFeatureProvider();
		}
		return null;
	}

	public IDiagramTypeProvider createDiagramTypeProvider(Diagram diagram, String providerId) {

		IDiagramTypeProvider dtp = createDiagramTypeProvider(providerId);
		if (dtp != null) {
			IDiagramEditor diagramEditor = new DiagramEditorDummy(dtp);
			dtp.init(diagram, diagramEditor);
		}
		return dtp;
	}

	public String getProviderId(Diagram diagram) {
		String diagramTypeProviders[] = GraphitiUi.getExtensionManager().getDiagramTypeProviderExtensionIds(diagram.getDiagramTypeId());
		if (diagramTypeProviders != null && diagramTypeProviders.length > 0) {
			// TODO handle if more then one DTPs are available
			return diagramTypeProviders[0];
		}
		return null;
	}

}
