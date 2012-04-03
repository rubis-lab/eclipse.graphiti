/**
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
 * 
 * </copyright>
 */
package org.eclipse.graphiti.tools.newprojectwizard.internal;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;
import org.eclipse.pde.ui.templates.StringOption;
import org.eclipse.pde.ui.templates.TemplateOption;

public class GraphitiEditorTemplateSection extends OptionTemplateSection {

	private static final String KEY_DT_ID = "diagramTypeId"; //$NON-NLS-1$
	private static final String KEY_DT_NAME = "diagramTypeName"; //$NON-NLS-1$
	private static final String KEY_DT_TYPE = "diagramTypeType"; //$NON-NLS-1$
	private static final String KEY_DT_DESCRIPTION = "diagramTypeDescription"; //$NON-NLS-1$

	private static final String KEY_DTP_CLASS_NAME = "diagramTypeProviderClassName"; //$NON-NLS-1$
	private static final String KEY_DTP_ID = "diagramTypeProviderId"; //$NON-NLS-1$
	private static final String KEY_DTP_NAME = "diagramTypeProviderName"; //$NON-NLS-1$
	private static final String KEY_DTP_DESCRIPTION = "diagramTypeProviderDescription"; //$NON-NLS-1$

	private static final String KEY_FEATURE_PROVIDER_CLASS_NAME = "featureProviderClassName"; //$NON-NLS-1$

	public GraphitiEditorTemplateSection() {
		super();
		setPageCount(1);
		createOptions();
	}

	private void createOptions() {

		GroupOption diagramTypeGroupOption = addGroupOption(0, Messages.GraphitiEditorTemplateSection_groupNameDiagramType);
		addGroupedOption(KEY_DT_ID, Messages.GraphitiEditorTemplateSection_fieldNameId, null, 0, diagramTypeGroupOption);
		addGroupedOption(KEY_DT_NAME, Messages.GraphitiEditorTemplateSection_fieldNameName, null, 0, diagramTypeGroupOption);
		addGroupedOption(KEY_DT_TYPE, Messages.GraphitiEditorTemplateSection_fieldNameType, null, 0, diagramTypeGroupOption);
		TemplateOption diagramTypeDescriptionOption = addGroupedOption(KEY_DT_DESCRIPTION, Messages.GraphitiEditorTemplateSection_fieldNameDescription, "", 0, //$NON-NLS-2$ //$NON-NLS-1$
				diagramTypeGroupOption);
		diagramTypeDescriptionOption.setRequired(false);

		GroupOption diagramTypeProviderGroupOption = addGroupOption(0, Messages.GraphitiEditorTemplateSection_groupNameDiagramTypeProvider);
		addGroupedOption(KEY_PACKAGE_NAME, Messages.GraphitiEditorTemplateSection_fieldNamePackageName, null, 0, diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_ID, Messages.GraphitiEditorTemplateSection_fieldNameId, null, 0, diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_NAME, Messages.GraphitiEditorTemplateSection_fieldNameName, null, 0, diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_CLASS_NAME, Messages.GraphitiEditorTemplateSection_fieldNameClassName, null, 0, diagramTypeProviderGroupOption);
		TemplateOption diagramTypeProviderDescriptionOption = addGroupedOption(KEY_DTP_DESCRIPTION, Messages.GraphitiEditorTemplateSection_fieldNameDescription, "", //$NON-NLS-2$ //$NON-NLS-1$
				0, diagramTypeProviderGroupOption);
		diagramTypeProviderDescriptionOption.setRequired(false);

		GroupOption featureProviderGroupOption = addGroupOption(0, Messages.GraphitiEditorTemplateSection_groupNameFeatureProvider);
		addGroupedOption(KEY_FEATURE_PROVIDER_CLASS_NAME, Messages.GraphitiEditorTemplateSection_fieldNameClassName,
				null, 0, featureProviderGroupOption);
	}

	private GroupOption addGroupOption(int pageIndex, String label) {
		GroupOption option = new GroupOption(this, label);
		registerOption(option, "", pageIndex); //$NON-NLS-1$
		return option;
	}

	private TemplateOption addGroupedOption(String name, String label, String value, int pageIndex,
			GroupOption groupOption) {
		StringOption option = new GroupableStringOption(this, name, label, groupOption);
		registerOption(option, value, pageIndex);
		return option;
	}

	@Override
	public boolean isDependentOnParentWizard() {
		// Return true to indicate that the data collection in previous steps of
		// the wizard (like plugin id and name) need to be propagated to the
		// initializyFields methods in this instance
		return true;
	}

	@Override
	protected void initializeFields(IFieldData data) {
		initializeFields(data.getId(), data.getName());
	}

	@Override
	public void initializeFields(IPluginModelBase model) {
		super.initializeFields(model);
		initializeFields(model.getPluginBase().getId(), model.getPluginBase().getName());
	}

	private void initializeFields(String pluginId, String pluginName) {
		// Get the root package name
		String rootPackageName = getFormattedPackageName(pluginId);
		initializeOption(KEY_PACKAGE_NAME, rootPackageName);

		// Create a prefix
		String prefix;
		int lastDot = pluginId.lastIndexOf('.');
		if (lastDot != -1) {
			prefix = pluginId.substring(lastDot + 1);
		} else {
			prefix = pluginId;
		}

		// Diagram type
		initializeOption(KEY_DT_ID, pluginId + "." + prefix + "DiagramType"); //$NON-NLS-1$ //$NON-NLS-2$
		initializeOption(KEY_DT_NAME, pluginName + " Diagram Type"); //$NON-NLS-1$
		initializeOption(KEY_DT_TYPE, prefix);

		// Diagram type provider
		initializeOption(KEY_DTP_ID, pluginId + "." + prefix + "DiagramTypeProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		initializeOption(KEY_DTP_NAME, pluginName + " Diagram Type Provider"); //$NON-NLS-1$
		String classNamePrefix = prefix.substring(0, 1).toUpperCase(Locale.ENGLISH) + prefix.substring(1);
		initializeOption(KEY_DTP_CLASS_NAME, classNamePrefix + "DiagramTypeProvider"); //$NON-NLS-1$

		// Feature provider
		initializeOption(KEY_FEATURE_PROVIDER_CLASS_NAME, classNamePrefix + "FeatureProvider"); //$NON-NLS-1$
	}

	private String getFormattedPackageName(String name) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (buffer.length() == 0) {
				if (Character.isJavaIdentifierStart(ch))
					buffer.append(Character.toLowerCase(ch));
			} else {
				if (Character.isJavaIdentifierPart(ch) || ch == '.')
					buffer.append(ch);
			}
		}
		return buffer.toString().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0);
		page.setTitle(Messages.GraphitiEditorTemplateSection_pageName);
		page.setDescription(Messages.GraphitiEditorTemplateSection_pageDescription);
		wizard.addPage(page);
		markPagesAdded();
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		ArrayList<IPluginReference> result = new ArrayList<IPluginReference>();

		IPluginReference[] superDependencies = super.getDependencies(schemaVersion);
		for (IPluginReference dependency : superDependencies) {
			result.add(dependency);
		}

		result.add(new PluginReference("org.eclipse.graphiti", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.graphiti.ui", null, 0)); //$NON-NLS-1$

		return (IPluginReference[]) result.toArray(new IPluginReference[result.size()]);
	}

	@Override
	protected URL getInstallURL() {
		return Activator.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
	}

	@Override
	public String getSectionId() {
		return "newgraphitieditor"; //$NON-NLS-1$
	}

	public String getUsedExtensionPoint() {
		return "org.eclipse.graphiti"; //$NON-NLS-1$
	}

	public String[] getNewFiles() {
		return new String[] {};
	}

	@Override
	protected ResourceBundle getPluginResourceBundle() {
		return null;
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		IPluginBase plugin = model.getPluginBase();
		IPluginModelFactory factory = model.getPluginFactory();

		// Diagram type
		{
			IPluginExtension extension = createExtension("org.eclipse.graphiti.ui.diagramTypes", true); //$NON-NLS-1$

			IPluginElement element = factory.createElement(extension);
			element.setName("diagramType"); //$NON-NLS-1$
			element.setAttribute("id", getStringOption(KEY_DT_ID)); //$NON-NLS-1$
			element.setAttribute("name", getStringOption(KEY_DT_NAME)); //$NON-NLS-1$
			element.setAttribute("type", getStringOption(KEY_DT_TYPE)); //$NON-NLS-1$
			String description = getStringOption(KEY_DT_DESCRIPTION);
			if (description != null && description.length() > 0) {
				element.setAttribute("description", description); //$NON-NLS-1$
			}

			extension.add(element);
			plugin.add(extension);
		}

		// Diagram type provider
		{
			IPluginExtension extension = createExtension("org.eclipse.graphiti.ui.diagramTypeProviders", true); //$NON-NLS-1$

			IPluginElement element = factory.createElement(extension);
			element.setName("diagramTypeProvider"); //$NON-NLS-1$
			element.setAttribute("id", getStringOption(KEY_DTP_ID)); //$NON-NLS-1$
			element.setAttribute("name", getStringOption(KEY_DTP_NAME)); //$NON-NLS-1$
			String description = getStringOption(KEY_DTP_DESCRIPTION);
			if (description != null && description.length() > 0) {
				element.setAttribute("description", description); //$NON-NLS-1$
			}

			String fullClassName = getStringOption(KEY_PACKAGE_NAME) + ".diagram." //$NON-NLS-1$
					+ getStringOption(KEY_DTP_CLASS_NAME);
			element.setAttribute("class", fullClassName); //$NON-NLS-1$

			IPluginElement diagramTypeElement = factory.createElement(element);
			diagramTypeElement.setName("diagramType"); //$NON-NLS-1$
			diagramTypeElement.setAttribute("id", getStringOption(KEY_DT_ID)); //$NON-NLS-1$
			element.add(diagramTypeElement);

			extension.add(element);
			plugin.add(extension);
		}
	}
}
