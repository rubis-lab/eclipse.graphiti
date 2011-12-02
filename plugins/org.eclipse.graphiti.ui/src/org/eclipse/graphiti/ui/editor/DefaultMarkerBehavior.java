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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.swt.widgets.Display;

/**
 * @since 0.9
 */
public class DefaultMarkerBehavior {

	private DiagramEditor diagramEditor;

	/**
	 * Is responsible for creating workspace resource markers presented in
	 * Eclipse's Problems View.
	 */
	private final MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
	 * Map to store the diagnostic associated with a resource.
	 */
	private final Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

	/**
	 * Controls whether the problem indication should be updated.
	 */
	private boolean updateProblemIndication = true;

	public DefaultMarkerBehavior(DiagramEditor diagramEditor) {
		super();
		this.diagramEditor = diagramEditor;
	}

	/**
	 * @return the problemIndicationAdapter
	 */
	public EContentAdapter getProblemIndicationAdapter() {
		return problemIndicationAdapter;
	}

	public void disableProblemIndicationUpdate() {
		updateProblemIndication = false;
	}

	public void enableProblemIndicationUpdate() {
		updateProblemIndication = true;
		updateProblemIndication();
	}

	/**
	 * Updates the problems indication with the information described in the
	 * specified diagnostic.
	 */
	void updateProblemIndication() {
		TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
		if (updateProblemIndication && editingDomain != null) {
			ResourceSet resourceSet = editingDomain.getResourceSet();
			final BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, GraphitiUIPlugin.PLUGIN_ID, 0, null,
					new Object[] { resourceSet });
			for (final Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
				if (childDiagnostic.getSeverity() != Diagnostic.OK) {
					diagnostic.add(childDiagnostic);
				}
			}
			if (markerHelper.hasMarkers(resourceSet)) {
				markerHelper.deleteMarkers(resourceSet);
			}
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				try {
					markerHelper.createMarkers(diagnostic);
					T.racer().info(diagnostic.toString());
				} catch (final CoreException exception) {
					T.racer().error(exception.getMessage(), exception);
				}
			}
		}
	}

	/**
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any).
	 */
	public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
		if ((!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) && updateProblemIndication) {
			final IFile file = GraphitiUiInternal.getEmfService().getFile(resource.getURI());
			final String fileName = file != null ? file.getFullPath().toString() : "unknown name"; //$NON-NLS-1$
			final BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
					Diagnostic.ERROR,
					GraphitiUIPlugin.PLUGIN_ID,
					0,
					"Problems encountered in file " + fileName, new Object[] { exception == null ? (Object) resource : exception }); //$NON-NLS-1$
			basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
			return basicDiagnostic;
		} else if (exception != null) {
			return new BasicDiagnostic(Diagnostic.ERROR, GraphitiUIPlugin.PLUGIN_ID, 0, "Problems encountered in file", //$NON-NLS-1$ 
					new Object[] { exception });
		} else {
			return Diagnostic.OK_INSTANCE;
		}
	}

	public void dispose() {
		disableProblemIndicationUpdate();
	}

	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded.
	 */
	private final EContentAdapter problemIndicationAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				switch (notification.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
				case Resource.RESOURCE__ERRORS:
				case Resource.RESOURCE__WARNINGS: {
					final Resource resource = (Resource) notification.getNotifier();
					final Diagnostic diagnostic = analyzeResourceProblems(resource, null);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						resourceToDiagnosticMap.put(resource, diagnostic);
					} else {
						resourceToDiagnosticMap.remove(resource);
					}

					if (updateProblemIndication) {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								updateProblemIndication();
							}
						});
					}
					break;
				}
				}
			} else {
				super.notifyChanged(notification);
			}
		}

		@Override
		protected void setTarget(Resource target) {
			basicSetTarget(target);
		}

		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
		}
	};

}
