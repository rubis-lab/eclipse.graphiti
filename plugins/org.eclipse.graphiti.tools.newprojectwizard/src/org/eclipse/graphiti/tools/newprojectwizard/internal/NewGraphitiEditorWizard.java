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

import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

public class NewGraphitiEditorWizard extends NewPluginTemplateWizard {

	public NewGraphitiEditorWizard() {
		super();
	}

	@Override
	public void init(IFieldData data) {
		super.init(data);
		setWindowTitle(Messages.NewGraphitiEditorWizard_windowTitle);
	}

	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] { new GraphitiEditorTemplateSection() };
	}
}
