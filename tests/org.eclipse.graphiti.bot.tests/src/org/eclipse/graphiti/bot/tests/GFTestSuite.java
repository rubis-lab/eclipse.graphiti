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
 *    mwenz - Bug 352709 - invalid image provider id crashes diagram editor 
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.bot.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class GFTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(GFTestSuite.class.getName());
		suite.addTestSuite(GFOtherTests.class);
		suite.addTestSuite(GFPackageTests.class);
		suite.addTestSuite(GFDialogTests.class);
		suite.addTestSuite(GFInteractionComponentTests.class);
		suite.addTestSuite(ExtensionManagerTest.class);
		return suite;
	}

}
