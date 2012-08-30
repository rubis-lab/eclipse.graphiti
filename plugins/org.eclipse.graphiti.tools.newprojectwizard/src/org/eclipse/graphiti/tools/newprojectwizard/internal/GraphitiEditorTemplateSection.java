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
 *    mwenz - Bug 388211 - New plug-in with Graphiti editor wizard adds not needed dependency to org.eclipse.ui
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
import org.eclipse.pde.ui.IPluginFieldData;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;
import org.eclipse.pde.ui.templates.StringOption;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

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
	private static final String KEY_USE_PATTERNS = "usePatterns"; //$NON-NLS-1$

	private static final String KEY_USE_SHAPE_DOMAIN_OBJECT = "useShapeDomainObject"; //$NON-NLS-1$
	private static final String KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME = "shapeDomainObjectClassName"; //$NON-NLS-1$
	private static final String KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME_SHORT = "shapeDomainObjectClassNameShort"; //$NON-NLS-1$

	private static final String KEY_USE_CONNECTION_DOMAIN_OBJECT = "useConnectionDomainObject"; //$NON-NLS-1$
	private static final String KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME = "connectionDomainObjectClassName"; //$NON-NLS-1$
	private static final String KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME_SHORT = "connectionDomainObjectClassNameShort"; //$NON-NLS-1$

	private static final String KEY_GENERATE_ACTIVATOR = "generateActivator"; //$NON-NLS-1$

	private SelectTypeOption shapeDomainObjectOption;
	private SelectTypeOption connectionDomainObjectOption;

	public GraphitiEditorTemplateSection() {
		super();
		setPageCount(2);
		createOptions();
	}

	private void createOptions() {

		GroupOption diagramTypeGroupOption = addGroupOption(0,
				Messages.GraphitiEditorTemplateSection_groupNameDiagramType);
		addGroupedOption(KEY_DT_ID, Messages.GraphitiEditorTemplateSection_fieldNameId, null, 0, diagramTypeGroupOption);
		addGroupedOption(KEY_DT_NAME, Messages.GraphitiEditorTemplateSection_fieldNameName, null, 0,
				diagramTypeGroupOption);
		addGroupedOption(KEY_DT_TYPE, Messages.GraphitiEditorTemplateSection_fieldNameType, null, 0,
				diagramTypeGroupOption);
		TemplateOption diagramTypeDescriptionOption = addGroupedOption(KEY_DT_DESCRIPTION,
				Messages.GraphitiEditorTemplateSection_fieldNameDescription, "", 0, //$NON-NLS-1$
				diagramTypeGroupOption);
		diagramTypeDescriptionOption.setRequired(false);

		GroupOption diagramTypeProviderGroupOption = addGroupOption(0,
				Messages.GraphitiEditorTemplateSection_groupNameDiagramTypeProvider);
		addGroupedOption(KEY_PACKAGE_NAME, Messages.GraphitiEditorTemplateSection_fieldNamePackageName, null, 0,
				diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_ID, Messages.GraphitiEditorTemplateSection_fieldNameId, null, 0,
				diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_NAME, Messages.GraphitiEditorTemplateSection_fieldNameName, null, 0,
				diagramTypeProviderGroupOption);
		addGroupedOption(KEY_DTP_CLASS_NAME, Messages.GraphitiEditorTemplateSection_fieldNameClassName, null, 0,
				diagramTypeProviderGroupOption);
		TemplateOption diagramTypeProviderDescriptionOption = addGroupedOption(KEY_DTP_DESCRIPTION,
				Messages.GraphitiEditorTemplateSection_fieldNameDescription, "", //$NON-NLS-1$
				0, diagramTypeProviderGroupOption);
		diagramTypeProviderDescriptionOption.setRequired(false);

		GroupOption featureProviderGroupOption = addGroupOption(0,
				Messages.GraphitiEditorTemplateSection_groupNameFeatureProvider);
		addGroupedOption(KEY_FEATURE_PROVIDER_CLASS_NAME, Messages.GraphitiEditorTemplateSection_fieldNameClassName,
				null, 0, featureProviderGroupOption);
		addGroupedOption(KEY_USE_PATTERNS, Messages.GraphitiEditorTemplateSection_fieldNameUsePatterns, false, 0,
				featureProviderGroupOption, null);

		final GroupOption shapeDomainObjectGroupOption = addGroupOption(1,
				Messages.GraphitiEditorTemplateSection_groupNameShapeDomainObject);
		addGroupedOption(KEY_USE_SHAPE_DOMAIN_OBJECT,
				Messages.GraphitiEditorTemplateSection_fieldNameUseShapeDomainObject, false, 1,
				shapeDomainObjectGroupOption, new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Button source = (Button) e.getSource();
						boolean selection = source.getSelection();
						shapeDomainObjectOption.setRequired(selection);
						shapeDomainObjectOption.setEnabled(selection);
						if (!selection) {
							shapeDomainObjectOption.setText("");
						}
						validateOptions(shapeDomainObjectGroupOption);
					}
				});
		shapeDomainObjectOption = addSelectTypeOption(KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME,
				Messages.GraphitiEditorTemplateSection_fieldNameShapeDomainObject, null, 1,
				shapeDomainObjectGroupOption);
		shapeDomainObjectOption.setRequired(false);

		final GroupOption connectionDomainObjectGroupOption = addGroupOption(1,
				Messages.GraphitiEditorTemplateSection_groupNameConnectionDomainObject);
		addGroupedOption(KEY_USE_CONNECTION_DOMAIN_OBJECT,
				Messages.GraphitiEditorTemplateSection_fieldNameUseConenctionDomainObject, false, 1,
				connectionDomainObjectGroupOption, new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Button source = (Button) e.getSource();
						boolean selection = source.getSelection();
						connectionDomainObjectOption.setRequired(selection);
						connectionDomainObjectOption.setEnabled(selection);
						if (!selection) {
							connectionDomainObjectOption.setText("");
						}
						validateOptions(connectionDomainObjectGroupOption);
					}
				});
		connectionDomainObjectOption = addSelectTypeOption(KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME,
				Messages.GraphitiEditorTemplateSection_fieldNameConnectionDomainObject, null, 1,
				connectionDomainObjectGroupOption);
		connectionDomainObjectOption.setRequired(false);
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

	private GroupableBooleanOption addGroupedOption(String name, String label, boolean value, int pageIndex,
			GroupOption groupOption, SelectionListener listener) {
		GroupableBooleanOption option = new GroupableBooleanOption(this, name, label, groupOption, listener);
		registerOption(option, value, pageIndex);
		return option;
	}

	private SelectTypeOption addSelectTypeOption(String name, String label, String value, int pageIndex,
			GroupOption groupOption) {
		SelectTypeOption option = new SelectTypeOption(this, name, label, groupOption);
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
		if (data instanceof IPluginFieldData) {
			initializeOption(KEY_GENERATE_ACTIVATOR, ((IPluginFieldData) data).doGenerateClass());
		}
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

		// Shape domain object
		initializeOption(KEY_USE_SHAPE_DOMAIN_OBJECT, false);
		initializeOption(KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME, ""); //$NON-NLS-1$

		// Connection domain object
		initializeOption(KEY_USE_CONNECTION_DOMAIN_OBJECT, false);
		initializeOption(KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME, ""); //$NON-NLS-1$
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

		page = createPage(1);
		page.setTitle(Messages.GraphitiEditorTemplateSection_pageTitleDomainObjects);
		page.setDescription(Messages.GraphitiEditorTemplateSection_pageDescriptionDomainObjects);
		wizard.addPage(page);

		markPagesAdded();
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		ArrayList<IPluginReference> result = new ArrayList<IPluginReference>();

		IPluginReference[] superDependencies = super.getDependencies(schemaVersion);
		for (IPluginReference dependency : superDependencies) {
			if ("org.eclipse.ui".equals(dependency.getId())) { //$NON-NLS-1$
				if (!getBooleanOption(KEY_GENERATE_ACTIVATOR)) {
					/*
					 * Skip dependency to og.eclipse.ui, because no activator
					 * shall be created and we don't need this dependency for
					 * anything else; in contrary it is harmful if one tries to
					 * create an e4 RCP app, see
					 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=388211
					 */
					continue;
				}
			}
			result.add(dependency);
		}

		result.add(new PluginReference("org.eclipse.graphiti", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.graphiti.ui", null, 0)); //$NON-NLS-1$
		if (Boolean.TRUE.equals(getValue(KEY_USE_PATTERNS))) {
			result.add(new PluginReference("org.eclipse.graphiti.pattern", null, 0)); //$NON-NLS-1$
		}

		// Add additional references defined on page 2 of the wizard (domain
		// dependency)
		if (Boolean.TRUE.equals(getValue(KEY_USE_SHAPE_DOMAIN_OBJECT))) {
			String bundleName = shapeDomainObjectOption.getBundleName();
			result.add(new PluginReference(bundleName, null, 0));
		}
		if (Boolean.TRUE.equals(getValue(KEY_USE_CONNECTION_DOMAIN_OBJECT))) {
			String bundleName = connectionDomainObjectOption.getBundleName();
			PluginReference reference = new PluginReference(bundleName, null, 0);
			if (!result.contains(reference)) {
				result.add(reference);
			}
		}

		return (IPluginReference[]) result.toArray(new IPluginReference[result.size()]);
	}

	@Override
	protected URL getInstallURL() {
		return Activator.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
	}

	@Override
	public String getSectionId() {
		if ((Boolean) getValue(KEY_USE_PATTERNS)) {
			return "patterns"; //$NON-NLS-1$
		} else {
			return "features"; //$NON-NLS-1$
		}
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

	@Override
	public String getReplacementString(String fileName, String key) {
		if (KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME_SHORT.equals(key)) {
			String value = super.getReplacementString(fileName, KEY_SHAPE_DOMAIN_OBJECT_CLASS_NAME);
			if (value != null && value.length() > 0) {
				// Domain object name was given -> get last segment
				int lastIndexOfDot = value.lastIndexOf("."); //$NON-NLS-1$
				value = value.substring(lastIndexOfDot + 1);
			} else {
				// No domain object given -> use generic name
				value = "DomainObject"; //$NON-NLS-1$
			}
			return value;
		} else if (KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME_SHORT.equals(key)) {
			String value = super.getReplacementString(fileName, KEY_CONNECTION_DOMAIN_OBJECT_CLASS_NAME);
			if (value != null && value.length() > 0) {
				// Domain object name was given -> get last segment
				int lastIndexOfDot = value.lastIndexOf("."); //$NON-NLS-1$
				value = value.substring(lastIndexOfDot + 1);
			} else {
				// No domain object given -> use generic name
				value = "DomainObjectConnection"; //$NON-NLS-1$
			}
			return value;
		} else {
			return super.getReplacementString(fileName, key);
		}
	}

	@Override
	public void validateOptions(TemplateOption source) {
		/*
		 * Workaround for super class only checking page one. reimplement here
		 * and check all pages
		 */

		// Check template source itself
		if (source.isRequired() && source.isEmpty()) {
			flagMissingRequiredOption(source);
		}

		// Check first page
		TemplateOption[] allPageOptions = getOptions(0);
		for (int i = 0; i < allPageOptions.length; i++) {
			TemplateOption nextOption = allPageOptions[i];
			if (nextOption.isRequired() && nextOption.isEmpty()) {
				flagMissingRequiredOption(nextOption);
				return;
			}
		}
		resetPageState();

		// Check second page
		allPageOptions = getOptions(1);
		for (int i = 0; i < allPageOptions.length; i++) {
			TemplateOption nextOption = allPageOptions[i];
			if (nextOption.isRequired() && nextOption.isEmpty()) {
				flagMissingRequiredOption(nextOption);
				return;
			}
		}
		resetPageState();
	}
}
