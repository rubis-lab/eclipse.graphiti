/**
 * <copyright>
 * 
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.filesystem.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.mm.filesystem.File;
import org.eclipse.graphiti.examples.mm.filesystem.Filesystem;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class CreateContainmentConnectionFeature extends AbstractCreateConnectionFeature implements
		ICreateConnectionFeature {

	public CreateContainmentConnectionFeature(IFeatureProvider fp) {
		super(fp, "Containment", "Creates a new containment relation between two folders");
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		// Defines the start of the connection; allowed are objects that may
		// contain other objects
		Object domainObject = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		return domainObject instanceof Folder || domainObject instanceof Filesystem;
	}

	public boolean canCreate(ICreateConnectionContext context) {
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();

		if (sourcePictogramElement == null || targetPictogramElement == null) {
			return false;
		}

		Object sourceDomainObject = getBusinessObjectForPictogramElement(sourcePictogramElement);
		Object targetDomainObject = getBusinessObjectForPictogramElement(targetPictogramElement);

		return (sourceDomainObject instanceof Folder || sourceDomainObject instanceof Filesystem)
				&& (targetDomainObject instanceof Folder || targetDomainObject instanceof File);
	}

	public Connection create(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		if (targetAnchor == null) {
			// Target destination is somewhere inside structured folder
			// representation
			Shape shape = (Shape) context.getTargetPictogramElement();
			while (shape.getAnchors().isEmpty()) {
				shape = shape.getContainer();
			}
			targetAnchor = shape.getAnchors().get(0);
		}

		EObject sourceObject = (EObject) getBusinessObjectForPictogramElement(sourceAnchor.getParent());
		EObject targetObject = (EObject) getBusinessObjectForPictogramElement(targetAnchor.getParent());

		if (sourceObject instanceof Filesystem) {
			if (targetObject instanceof Folder) {
				((Filesystem) sourceObject).getFolders().add((Folder) targetObject);
			} else if (targetObject instanceof File) {
				((Filesystem) sourceObject).getFiles().add((File) targetObject);
			} else {
				throw new IllegalStateException("Filesystem may only contain Folders or Files");
			}
		} else if (sourceObject instanceof Folder) {
			if (targetObject instanceof Folder) {
				((Folder) sourceObject).getFolders().add((Folder) targetObject);
			} else if (targetObject instanceof File) {
				((Folder) sourceObject).getFiles().add((File) targetObject);
			} else {
				throw new IllegalStateException("Folder may only contain Folders or Files");
			}
		} else {
			throw new IllegalStateException("Unknown container object");
		}

		AddConnectionContext addContext = new AddConnectionContext(sourceAnchor, targetAnchor);
		getFeatureProvider().addIfPossible(addContext);

		return null;
	}
}
