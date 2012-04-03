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

import org.eclipse.pde.ui.templates.NewPluginProjectFromTemplateWizard;


public class NewGraphitiPluginProjectWizard extends NewPluginProjectFromTemplateWizard {

	public NewGraphitiPluginProjectWizard() {
		super();
	}

	@Override
	protected String getTemplateID() {
		return "org.eclipse.graphiti.tools.newprojectwizard.newgraphitieditorwizard"; //$NON-NLS-1$
	}
}
