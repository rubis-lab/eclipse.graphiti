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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.links.LinksFactory;
import org.eclipse.graphiti.mm.links.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * The Class AddLinkFeature.
 */
public class AddLinkFeature extends AbstractAddFeature {

	/**
	 * Instantiates a new adds the link feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AddLinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		boolean ret = false;
		Object newObject = context.getNewObject();
		if (newObject instanceof EObject) {
			ret = true;
		}
		return ret;
	}

	public PictogramElement add(IAddContext context) {
		Object newObject = context.getNewObject();
		if (newObject instanceof EObject) {
			boolean sure = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Are you sure?",
					"Do you really want to link the object?");
			if (sure) {
				URI uri = EcoreUtil.getURI((EObject) newObject);
				EObject newEObject = getDiagramEditor().getResourceSet().getEObject(uri, true);
				ContainerShape targetContainer = context.getTargetContainer();
				PictogramLink link = Graphiti.getLinkService().getLinkForPictogramElement(targetContainer);
				if (link == null) {
					link = LinksFactory.eINSTANCE.createPictogramLink();
					link.setPictogramElement(targetContainer);
				}
				link.getBusinessObjects().add(newEObject);
				return targetContainer;
			} else {
				return null;
			}
		}
		return null;
	}

}
