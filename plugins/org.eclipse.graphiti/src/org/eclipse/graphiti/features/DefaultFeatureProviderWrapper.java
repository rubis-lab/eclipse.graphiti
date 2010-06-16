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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class DefaultFeatureProviderWrapper implements IFeatureProvider {
	private IFeatureProvider innerFeatureProvider;

	public DefaultFeatureProviderWrapper(IFeatureProvider innerFeatureProvider) {
		setInnerFeatureProvider(innerFeatureProvider);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#addIfPossible(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public PictogramElement addIfPossible(IAddContext context) {
		return getInnerFeatureProvider().addIfPossible(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#canAdd(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public IReason canAdd(IAddContext context) {
		return getInnerFeatureProvider().canAdd(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#canLayout(org.eclipse.graphiti.features.context.ILayoutContext)
	 */
	public IReason canLayout(ILayoutContext context) {
		return getInnerFeatureProvider().canLayout(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#canUpdate(org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason canUpdate(IUpdateContext context) {
		return getInnerFeatureProvider().canUpdate(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getAddBendpointFeature(org.eclipse.graphiti.features.context.IAddBendpointContext)
	 */
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return getInnerFeatureProvider().getAddBendpointFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getAddFeature(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public IAddFeature getAddFeature(IAddContext context) {
		return getInnerFeatureProvider().getAddFeature(context);
	}

	/**
	 * @param pictogramElement
	 * @return
	 * @see org.eclipse.graphiti.features.IMappingProvider#getAllBusinessObjectsForPictogramElement(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public Object[] getAllBusinessObjectsForPictogramElement(PictogramElement pictogramElement) {
		return getInnerFeatureProvider().getAllBusinessObjectsForPictogramElement(pictogramElement);
	}

	/**
	 * @param businessObject
	 * @return
	 * @see org.eclipse.graphiti.features.IMappingProvider#getAllPictogramElementsForBusinessObject(java.lang.Object)
	 */
	public PictogramElement[] getAllPictogramElementsForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().getAllPictogramElementsForBusinessObject(businessObject);
	}

	/**
	 * @param pictogramElement
	 * @return
	 * @see org.eclipse.graphiti.features.IMappingProvider#getBusinessObjectForPictogramElement(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public Object getBusinessObjectForPictogramElement(PictogramElement pictogramElement) {
		return getInnerFeatureProvider().getBusinessObjectForPictogramElement(pictogramElement);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getCopyFeature(org.eclipse.graphiti.features.context.ICopyContext)
	 */
	public ICopyFeature getCopyFeature(ICopyContext context) {
		return getInnerFeatureProvider().getCopyFeature(context);
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getCreateConnectionFeatures()
	 */
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return getInnerFeatureProvider().getCreateConnectionFeatures();
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getCreateFeatures()
	 */
	public ICreateFeature[] getCreateFeatures() {
		return getInnerFeatureProvider().getCreateFeatures();
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getCustomFeatures(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return getInnerFeatureProvider().getCustomFeatures(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getDeleteFeature(org.eclipse.graphiti.features.context.IDeleteContext)
	 */
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return getInnerFeatureProvider().getDeleteFeature(context);
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getDiagramTypeProvider()
	 */
	public IDiagramTypeProvider getDiagramTypeProvider() {
		return getInnerFeatureProvider().getDiagramTypeProvider();
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getDirectEditingFeature(org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		return getInnerFeatureProvider().getDirectEditingFeature(context);
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getDirectEditingInfo()
	 */
	public IDirectEditingInfo getDirectEditingInfo() {
		return getInnerFeatureProvider().getDirectEditingInfo();
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getDragAndDropFeatures(org.eclipse.graphiti.features.context.IPictogramElementContext)
	 */
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		return getInnerFeatureProvider().getDragAndDropFeatures(context);
	}

	public IFeatureProvider getInnerFeatureProvider() {
		return innerFeatureProvider;
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getLayoutFeature(org.eclipse.graphiti.features.context.ILayoutContext)
	 */
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		return getInnerFeatureProvider().getLayoutFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getMoveAnchorFeature(org.eclipse.graphiti.features.context.IMoveAnchorContext)
	 */
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		return getInnerFeatureProvider().getMoveAnchorFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getMoveBendpointFeature(org.eclipse.graphiti.features.context.IMoveBendpointContext)
	 */
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return getInnerFeatureProvider().getMoveBendpointFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getMoveConnectionDecoratorFeature(org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext)
	 */
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		return getInnerFeatureProvider().getMoveConnectionDecoratorFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getMoveShapeFeature(org.eclipse.graphiti.features.context.IMoveShapeContext)
	 */
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return getInnerFeatureProvider().getMoveShapeFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getPasteFeature(org.eclipse.graphiti.features.context.IPasteContext)
	 */
	public IPasteFeature getPasteFeature(IPasteContext context) {
		return getInnerFeatureProvider().getPasteFeature(context);
	}

	/**
	 * @param businessObject
	 * @return
	 * @see org.eclipse.graphiti.features.IMappingProvider#getPictogramElementForBusinessObject(java.lang.Object)
	 */
	public PictogramElement getPictogramElementForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().getPictogramElementForBusinessObject(businessObject);
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getPrintFeature()
	 */
	public IPrintFeature getPrintFeature() {
		return getInnerFeatureProvider().getPrintFeature();
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getReconnectionFeature(org.eclipse.graphiti.features.context.IReconnectionContext)
	 */
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return getInnerFeatureProvider().getReconnectionFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getRemoveBendpointFeature(org.eclipse.graphiti.features.context.IRemoveBendpointContext)
	 */
	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		return getInnerFeatureProvider().getRemoveBendpointFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getRemoveFeature(org.eclipse.graphiti.features.context.IRemoveContext)
	 */
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return getInnerFeatureProvider().getRemoveFeature(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getResizeShapeFeature(org.eclipse.graphiti.features.context.IResizeShapeContext)
	 */
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return getInnerFeatureProvider().getResizeShapeFeature(context);
	}

	/**
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getSaveImageFeature()
	 */
	public ISaveImageFeature getSaveImageFeature() {
		return getInnerFeatureProvider().getSaveImageFeature();
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getUpdateFeature(org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		return getInnerFeatureProvider().getUpdateFeature(context);
	}

	/**
	 * @param businessObject
	 * @return
	 * @see org.eclipse.graphiti.features.IMappingProvider#hasPictogramElementForBusinessObject(java.lang.Object)
	 */
	public boolean hasPictogramElementForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().hasPictogramElementForBusinessObject(businessObject);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#layoutIfPossible(org.eclipse.graphiti.features.context.ILayoutContext)
	 */
	public IReason layoutIfPossible(ILayoutContext context) {
		return getInnerFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * @param pictogramElement
	 * @param businessObject
	 * @see org.eclipse.graphiti.features.IMappingProvider#link(org.eclipse.graphiti.mm.pictograms.PictogramElement,
	 *      java.lang.Object)
	 */
	public void link(PictogramElement pictogramElement, Object businessObject) {
		getInnerFeatureProvider().link(pictogramElement, businessObject);
	}

	/**
	 * @param pictogramElement
	 * @param businessObjects
	 * @see org.eclipse.graphiti.features.IMappingProvider#link(org.eclipse.graphiti.mm.pictograms.PictogramElement,
	 *      java.lang.Object[])
	 */
	public void link(PictogramElement pictogramElement, Object[] businessObjects) {
		getInnerFeatureProvider().link(pictogramElement, businessObjects);
	}

	private void setInnerFeatureProvider(IFeatureProvider innerFeatureProvider) {
		this.innerFeatureProvider = innerFeatureProvider;
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#updateIfPossible(org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateIfPossible(IUpdateContext context) {
		return getInnerFeatureProvider().updateIfPossible(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#updateIfPossibleAndNeeded(org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateIfPossibleAndNeeded(IUpdateContext context) {
		return getInnerFeatureProvider().updateIfPossibleAndNeeded(context);
	}

	/**
	 * @param context
	 * @return
	 * @see org.eclipse.graphiti.features.IFeatureProvider#updateNeeded(org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateNeeded(IUpdateContext context) {
		return getInnerFeatureProvider().updateNeeded(context);
	}

	@Override
	public void dispose() {
	}
}
