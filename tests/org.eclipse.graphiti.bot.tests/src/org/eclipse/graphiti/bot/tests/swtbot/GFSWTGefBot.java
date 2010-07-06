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
package org.eclipse.graphiti.bot.tests.swtbot;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.ui.IEditorReference;

/**
 *
 */
public class GFSWTGefBot extends SWTGefBot {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot#createEditor(org.eclipse
	 * .ui.IEditorReference, org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot)
	 */
	@Override
	protected GFSWTBotGefEditor createEditor(IEditorReference reference, SWTWorkbenchBot bot) {
		return new GFSWTBotGefEditor(reference, bot);
	}

}
