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

import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class CreateContainmentConnectionFeature extends AbstractCreateConnectionFeature
		implements ICreateConnectionFeature {

	public CreateContainmentConnectionFeature(IFeatureProvider fp) {
		super(fp, "DomainObjectConnection", "Creates a new DomainObjectConnection between two Folders");
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		// TODO: check for right domain object instance below
		// return getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof <DomainObject>;

		return true;
	}

	public boolean canCreate(ICreateConnectionContext context) {
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();

		// TODO: check for right domain object instance below
		// if (getBusinessObjectForPictogramElement(sourcePictogramElement) instanceof <DomainObject> && getBusinessObjectForPictogramElement(targetPictogramElement) instanceof <DomainObject>) {
		//  	return true;
		// }
		
		return sourcePictogramElement != null && targetPictogramElement != null;
	}

	public Connection create(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		Folder sourceFolder = (Folder) getBusinessObjectForPictogramElement(sourceAnchor.getParent());
		Folder targetFolder = (Folder) getBusinessObjectForPictogramElement(targetAnchor.getParent());
		sourceFolder.getFolders().add(targetFolder);
		
		AddConnectionContext addContext = new AddConnectionContext(sourceAnchor, targetAnchor);
		getFeatureProvider().addIfPossible(addContext);

		return null;
	}
}
