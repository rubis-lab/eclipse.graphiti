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
package org.eclipse.graphiti.testtool.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.testtool.ecore.features.association.TestAddAssociationFeature;
import org.eclipse.graphiti.testtool.ecore.features.association.TestCreateAssociationFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestAddClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestCreateClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestDirectEditingClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestLayoutClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestMoveClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestResizeClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.clazz.TestUpdateClassFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.RenamePackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestAddPackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestCreatePackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestLayoutPackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestMovePackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestResizePackageFeature;
import org.eclipse.graphiti.testtool.ecore.features.pack.TestUpdatePackageFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

/**
 * The Class TestFeatureProvider.
 */
public class TestFeatureProvider extends DefaultFeatureProvider {

	/**
	 * Instantiates a new test feature provider.
	 * 
	 * @param dtp
	 *            the dtp
	 */
	public TestFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {

		if (context.getNewObject() instanceof EPackage) {
			return new TestAddPackageFeature(this);
		} else if (context.getNewObject() instanceof EClass) {
			return new TestAddClassFeature(this);
		} else if (context.getNewObject() instanceof EReference) {
			return new TestAddAssociationFeature(this);
		}

		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new TestCreatePackageFeature(this), new TestCreateClassFeature(this) };
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof EPackage) {
				return new TestUpdatePackageFeature(this);
			} else if (bo instanceof EClass) {
				return new TestUpdateClassFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
		if (bo instanceof EPackage) {
			return new TestMovePackageFeature(this);
		} else if (bo instanceof EClass) {
			return new TestMoveClassFeature(this);
		}
		return super.getMoveShapeFeature(context);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
		if (bo instanceof EPackage) {
			return new TestResizePackageFeature(this);
		} else if (bo instanceof EClass) {
			return new TestResizeClassFeature(this);
		}
		return super.getResizeShapeFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof EPackage) {
			return new TestLayoutPackageFeature(this);
		} else if (bo instanceof EClass) {
			return new TestLayoutClassFeature(this);
		}
		return super.getLayoutFeature(context);
	}

	
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		
		return new ICustomFeature[] { new RenamePackageFeature(this) };
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new TestCreateAssociationFeature(this) };
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof EClass) {
			return new TestDirectEditingClassFeature(this);
		}
		return super.getDirectEditingFeature(context);
	}
}
