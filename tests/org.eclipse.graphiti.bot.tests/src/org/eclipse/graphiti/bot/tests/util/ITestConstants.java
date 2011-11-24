/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 348662 - Setting tooptip to null in tool behavior provider doesn't clear up
 *                         tooltip if the associated figure has a previous tooltip
 *    mwenz - Bug 341224: Allow to hide the selection and marquee tools in the palette
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests.util;

public interface ITestConstants {
	static final String DC_NAME = "TESTPROJECT-CreateDiagramTest_EMF";

	static final String TEST_DIAGRAM_PREFIX = "test_diagram_";
	/**
	 * The Constant DIAGRAM_TYPE_ID_SKETCH.
	 */
	public static final String DIAGRAM_TYPE_ID_SKETCH = "testtool.sketch";

	/**
	 * The Constant DIAGRAM_TYPE_ID_ECORE.
	 */
	public static final String DIAGRAM_TYPE_ID_ECORE = "testtool.ecore";

	/**
	 * The Constant DIAGRAM_TYPE_ID_TUTORIAL.
	 * 
	 * @since 0.9
	 */
	public static final String DIAGRAM_TYPE_ID_TUTORIAL = "tutorial";

	/**
	 * The Constant DIAGRAM_TYPE_ID_WITH_AUTO_UPDATE_AT_STARTUP.
	 * 
	 * @since 0.9
	 */
	public static final String DIAGRAM_TYPE_ID_WITH_AUTO_UPDATE_AT_STARTUP = "dtWithAutoUpdateAtStartup";

	/**
	 * The Constant DIAGRAM_TYPE_ID_WITH_HIDDEN_SELECTION_AND_MARQUEE_TOOL.
	 * 
	 * @since 0.9
	 */
	public static final String DIAGRAM_TYPE_ID_WITH_HIDDEN_SELECTION_AND_MARQUEE_TOOL = "dtWithHiddenSelectionAndMarqueeTool";
}
