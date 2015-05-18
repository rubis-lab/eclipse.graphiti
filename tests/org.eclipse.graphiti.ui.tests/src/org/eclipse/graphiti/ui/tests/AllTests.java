/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2015 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 415884 - Cannot query size of a multi-line text
 *    mwenz - Bug 423573 - Angles should never be integer
 *    mwenz - Bug 416039 - TextStyle rendering does not fall back to abstract text font
 *    mwenz - Bug 443304 - Improve undo/redo handling in Graphiti features
 *    mwenz - Bug 453553 - Provide an abort possibility for delete and remove features in case 'pre' methods fail
 *    mwenz - Bug 467476 - NullPointerException in GFPaletteRoot.createModelIndependentTools
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.tests;

import org.eclipse.graphiti.ui.features.DefaultDeleteFeatureTest;
import org.eclipse.graphiti.ui.features.DefaultRemoveFeatureTest;
import org.eclipse.graphiti.ui.internal.editor.GFPaletteRootTest;
import org.eclipse.graphiti.ui.internal.figures.GFFigureUtilTest;
import org.eclipse.graphiti.ui.tests.compatibility.CompatibilityTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PackageTest.class, CommandStackTest.class, MigrationServiceTest.class,
		CustomUndoableFeatureTest.class, RollbackTest.class, LayoutServiceTest.class, CommandTest.class,
		CompatibilityTests.class, GFFigureUtilTest.class, CustomUndoRedoFeatureTest.class,
		DefaultDeleteFeatureTest.class, DefaultRemoveFeatureTest.class, GFPaletteRootTest.class })
public class AllTests {
}
