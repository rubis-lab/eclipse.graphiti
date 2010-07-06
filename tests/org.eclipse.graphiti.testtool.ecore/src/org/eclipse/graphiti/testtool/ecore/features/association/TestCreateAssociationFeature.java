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
package org.eclipse.graphiti.testtool.ecore.features.association;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class TestCreateAssociationFeature.
 */
public class TestCreateAssociationFeature extends AbstractCreateConnectionFeature {

	/**
	 * Instantiates a new test create association feature.
	 * 
	 * @param fp
	 *            the fp
	 */
	public TestCreateAssociationFeature(IFeatureProvider fp) {
		/*
		 * provide name and description for the UI, e.g. the palette
		 */
		super(fp, "Association", "create association");
	}

	public boolean canCreate(ICreateConnectionContext context) {
		EClass source = getEClass(context.getSourceAnchor());
		EClass target = getEClass(context.getTargetAnchor());

		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		/*
		 * return true if start anchor belongs to a EClass
		 */
		if (getEClass(context.getSourceAnchor()) != null) {
			return true;
		}
		return false;
	}

	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		// get eclasses which should be connected
		EClass source = getEClass(context.getSourceAnchor());
		EClass target = getEClass(context.getTargetAnchor());

		if (source != null && target != null) {

			// create business object for the new connection
			EReference association = createAssociation(source, target);

			// use existing add feature for the creation of the graphical
			// representation
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			addContext.setNewObject(association);
			PictogramElement pe = getFeatureProvider().addIfPossible(addContext);
			if (pe instanceof Connection) {
				newConnection = (Connection) pe;

				// link new pictogram element with business object
				link(newConnection, association);
			}

		}

		return newConnection;
	}

	/**
	 * If the parent of the anchor is connected with an EClass, this method <br>
	 * returns the EClass.
	 * 
	 * @param anchor
	 * @return the EClass or null if no EClass is connected
	 */
	private EClass getEClass(Anchor anchor) {
		if (anchor != null) {
			Object eObject = getBusinessObjectForPictogramElement(anchor.getParent());
			if (eObject instanceof EClass) {
				return (EClass) eObject;
			}
		}
		return null;
	}

	/**
	 * Creates the business object(s) for the new connection between the two
	 * classes.<br>
	 * It creates an association with two association ends which both point to
	 * an EClass.
	 * 
	 * @param source
	 * @param target
	 * @return the newly created association
	 */
	private EReference createAssociation(EClass source, EClass target) {

		EReference eReference = EcoreFactory.eINSTANCE.createEReference();
		eReference.setName("new EReference"); //$NON-NLS-1$

		eReference.setEType(target);
		eReference.setLowerBound(0);
		eReference.setUpperBound(-1);
		source.getEStructuralFeatures().add(eReference);

		return eReference;
	}

}
