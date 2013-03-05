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
 *    mwenz - Bug 340403 - Fixed NPE when adding graphical representations from 
 *                         a progress monitor job
 *    mwenz - Bug 352109 - Enable auto-update option for saved editor
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DiagramSupport;
import org.eclipse.swt.widgets.Display;

/**
 * Listener for BO changes in an EMF model linked to a diagram.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DomainModelChangeListener implements ResourceSetListener {

	private DiagramSupport diagramSupport;

	public DomainModelChangeListener(DiagramSupport diagramSupport) {
		setDiagramSupport(diagramSupport);
	}

	public NotificationFilter getFilter() {
		return NotificationFilter.NOT_TOUCH;
	}

	public boolean isAggregatePrecommitListener() {
		return false;
	}

	public boolean isPostcommitOnly() {
		return true;
	}

	public boolean isPrecommitOnly() {
		return false;
	}

	public void resourceSetChanged(ResourceSetChangeEvent event) {

		// // if there is no diagramLink, we have also no pictogramLinks -> no
		// // references to bo's -> don't handle change events
		// if (getDiagramTypeProvider() instanceof AbstractDiagramTypeProvider)
		// {
		// DiagramLink cachedDiagramLink = ((AbstractDiagramTypeProvider)
		// getDiagramTypeProvider()).getCachedDiagramLink();
		// if (cachedDiagramLink == null) {
		// return;
		// }
		// }
		// if we have no pictogramLinks -> no
		// references to bo's -> don't handle change events
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (diagram != null) {
			if (diagram.getPictogramLinks().size() == 0) {
				return;
			}
		}

		// Compute changed BOs.
		final Set<EObject> changedBOs = new HashSet<EObject>();
		List<Notification> notifications = event.getNotifications();
		for (Notification notification : notifications) {
			Object notifier = notification.getNotifier();
			if (!(notifier instanceof EObject)) {
				continue;
			}
			changedBOs.add((EObject) notifier);
		}

		final PictogramElement[] dirtyPes = getDiagramTypeProvider().getNotificationService()
				.calculateRelatedPictogramElements(changedBOs.toArray());

		// Do nothing if no BO linked to the diagram changed.
		if (dirtyPes.length == 0) {
			return;
		}

		// Do an asynchronous update in the UI thread.
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (getDiagramTypeProvider().isAutoUpdateAtRuntime()) {
					// Bug 352109: Enable unconditional auto refresh for case 3)
					// standard refresh with saved editor
					if (getDiagramTypeProvider().isAutoUpdateAtRuntimeWhenEditorIsSaved()
							|| getDiagramTypeProvider().getDiagramEditor().isDirty()) {
						// The notification service takes care of not only the
						// linked BOs but also asks the diagram provider about
						// related BOs.
						getDiagramTypeProvider().getNotificationService().updatePictogramElements(dirtyPes);
					}
				} else {
					getDiagramTypeProvider().getDiagramEditor().refresh();
				}
			}

		});

	}

	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

	private IDiagramTypeProvider getDiagramTypeProvider() {
		return getDiagramSupport().getDiagramTypeProvider();
	}

	private DiagramSupport getDiagramSupport() {
		return diagramSupport;
	}

	private void setDiagramSupport(DiagramSupport diagramSupport) {
		this.diagramSupport = diagramSupport;
	}
}
