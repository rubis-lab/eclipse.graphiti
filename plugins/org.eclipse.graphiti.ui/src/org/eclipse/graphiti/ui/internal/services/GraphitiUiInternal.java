/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 324859 - Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 370888 - API Access to export and print
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.graphiti.ui.internal.services.impl.CommandService;
import org.eclipse.graphiti.ui.internal.services.impl.EmfService;
import org.eclipse.graphiti.ui.internal.services.impl.GefService;
import org.eclipse.graphiti.ui.internal.services.impl.TraceService;
import org.eclipse.graphiti.ui.internal.services.impl.WorkbenchService;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GraphitiUiInternal {

	final private static IEmfService emfService = new EmfService();
	final private static ITraceService traceService = new TraceService();
	final private static IGefService gefService = new GefService();
	final private static IWorkbenchService workbenchService = new WorkbenchService();
	final private static ICommandService commandService = new CommandService();

	public static IEmfService getEmfService() {
		return emfService;
	}

	public static ITraceService getTraceService() {
		return traceService;
	}

	public static IGefService getGefService() {
		return gefService;
	}

	public static IWorkbenchService getWorkbenchService() {
		return workbenchService;
	}

	public static ICommandService getCommandService() {
		return commandService;
	}
}
