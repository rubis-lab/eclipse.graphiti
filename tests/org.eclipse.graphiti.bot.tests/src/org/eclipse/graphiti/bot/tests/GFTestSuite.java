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

		return suite;
	}

}
