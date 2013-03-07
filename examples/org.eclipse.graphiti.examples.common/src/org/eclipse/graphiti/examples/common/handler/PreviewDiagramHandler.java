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
 *    fvelasco - Initial version
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.examples.common.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author fvelasco
 * 
 * @since 0.10
 */
public class PreviewDiagramHandler extends AbstractHandler implements IHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection currentSelection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		Object[] elements = currentSelection.toArray();

		IResource res1 = (IResource) Platform.getAdapterManager().getAdapter(elements[0], IResource.class);
		IResource res2 = (IResource) Platform.getAdapterManager().getAdapter(elements[1], IResource.class);

		if (res1 != null && res2 != null) {

			DoubleDiagramDialog dialog = new DoubleDiagramDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell(), res1, res2);
			dialog.open();
		}
		return null;
	}
}
