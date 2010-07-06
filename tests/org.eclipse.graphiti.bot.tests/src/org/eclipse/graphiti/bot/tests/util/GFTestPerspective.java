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
package org.eclipse.graphiti.bot.tests.util;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This class is used for testing of GFW. Its purpose is to show all relevant
 * views (if possible next to each other).
 */
public class GFTestPerspective implements IPerspectiveFactory {

	/**
	 * Creates the layout
	 * <ul>
	 * <li>top-left: "Graphics Framework Test View"</li>
	 * <li>middle-left: "Outline"</li>
	 * <li>bottom-left: "Miniature View"</li>
	 * <li>top-right: editor</li>
	 * <li>middle-right: "Properties"</li>
	 * <li>bottom-right: several test views</li>
	 * </ul>
	 */
	@SuppressWarnings("deprecation")
	public void createInitialLayout(IPageLayout layout) {
		// top-left
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.25, IPageLayout.ID_EDITOR_AREA);
		//topLeft.addView("com.sap.tc.emf.gfw.samples.testview.MoinView");
		topLeft.addPlaceholder(IPageLayout.ID_RES_NAV);
		topLeft.addView("org.eclipse.ui.navigator.ProjectExplorer");
		topLeft.addPlaceholder("org.eclipse.jdt.ui.PackageExplorer");

		// middle-left
		IFolderLayout middleLeft = layout.createFolder("middleLeft", IPageLayout.BOTTOM, (float) 0.40, "topLeft");
		middleLeft.addView(IPageLayout.ID_OUTLINE);

		// bottom-left
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float) 0.50, "middleLeft");
		bottomLeft.addView("org.eclipse.graphiti.ui.internal.editor.thumbnailview");

		// middle-right
		IFolderLayout middleRight = layout.createFolder("middleRight", IPageLayout.BOTTOM, (float) 0.55, IPageLayout.ID_EDITOR_AREA);
		middleRight.addView(IPageLayout.ID_PROP_SHEET);

		// bottom-right
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, (float) 0.40, "middleRight");
		bottomRight.addView("org.eclipse.pde.runtime.LogView");
		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
	}
}
