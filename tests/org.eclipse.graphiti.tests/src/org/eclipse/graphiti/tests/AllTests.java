/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Henrik Rentz-Reichert - mwenz - Bug 376544 - bug in re-connecting a connection with identical start and end anchor
 *    mwenz - Bug 394801 - AddGraphicalRepresentation doesn't carry properties
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tests;

import org.eclipse.graphiti.tests.cases.CreateServiceTest;
import org.eclipse.graphiti.tests.cases.DefaultFeaturesTest;
import org.eclipse.graphiti.tests.cases.FeatureParametersTest;
import org.eclipse.graphiti.tests.cases.GaServiceTest;
import org.eclipse.graphiti.tests.cases.LinkServiceTest;
import org.eclipse.graphiti.tests.cases.PeServiceTest;
import org.eclipse.graphiti.tests.cases.ReconnectionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DefaultFeaturesTest.class, GaServiceTest.class, LinkServiceTest.class, PeServiceTest.class,
		CreateServiceTest.class, ReconnectionTest.class, FeatureParametersTest.class })
public class AllTests {

}
