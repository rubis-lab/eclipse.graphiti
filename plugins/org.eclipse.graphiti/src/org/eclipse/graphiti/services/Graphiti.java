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
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.internal.services.impl.CreateServiceImpl;
import org.eclipse.graphiti.internal.services.impl.GaServiceImpl;
import org.eclipse.graphiti.internal.services.impl.LayoutServiceImpl;
import org.eclipse.graphiti.internal.services.impl.LinkServiceImpl;
import org.eclipse.graphiti.internal.services.impl.PeServiceImpl;

public class Graphiti {
	private static IGaService gaService;

	private static IPeService peService;

	private static ILayoutService layoutService;

	private static ICreateService createService;

	private static ILinkService linkService;

	public static IGaCreateService getGaCreateService() {
		return getGaService();
	}

	public static IGaLayoutService getGaLayoutService() {
		return getGaService();
	}

	public static IGaService getGaService() {
		if (gaService == null) {
			gaService = new GaServiceImpl();
		}
		return gaService;
	}

	public static IPeCreateService getPeCreateService() {
		return getPeService();
	}

	public static IPeLayoutService getPeLayoutService() {
		return getPeService();
	}

	public static IPeService getPeService() {
		if (peService == null) {
			peService = new PeServiceImpl();
		}
		return peService;
	}

	public static ILayoutService getLayoutService() {
		if (layoutService == null) {
			layoutService = new LayoutServiceImpl();
		}
		return layoutService;
	}

	public static ICreateService getCreateService() {
		if (createService == null) {
			createService = new CreateServiceImpl();
		}
		return createService;
	}

	public static ILinkService getLinkService() {
		if (linkService == null) {
			linkService = new LinkServiceImpl();
		}
		return linkService;
	}
}
