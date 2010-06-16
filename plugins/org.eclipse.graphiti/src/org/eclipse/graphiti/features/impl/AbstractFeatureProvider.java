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
package org.eclipse.graphiti.features.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IPrintFeature;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.ExternalPictogramLink;
import org.eclipse.graphiti.internal.command.AddFeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.links.DiagramLink;
import org.eclipse.graphiti.mm.links.LinksFactory;
import org.eclipse.graphiti.mm.links.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Property;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;

/**
 * The Class AbstractFeatureProvider.
 */
public abstract class AbstractFeatureProvider implements IFeatureProvider {

	private static final ICustomFeature[] ZERO_CUSTOM_FEATURES = new ICustomFeature[0];

	/**
	 * The Constant EMPTY_REF_OBJECTS.
	 */
	protected static final EObject[] NO_OBJECTS = new EObject[0];

	/**
	 * The Constant EMPTY_PICTOGRAM_ELEMENTS.
	 */
	protected static final PictogramElement[] EMPTY_PICTOGRAM_ELEMENTS = new PictogramElement[0];

	private IDiagramTypeProvider dtp;

	private IDirectEditingInfo directEditingInfo = new DefaultDirectEditingInfo();

	private IIndependenceSolver independenceSolver = null;

	/**
	 * Instantiates a new abstract feature provider.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public AbstractFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super();
		dtp = diagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getAddFeature(org.eclipse
	 * .graphiti.features.context.IAddContext)
	 */
	public IAddFeature getAddFeature(IAddContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getCreateConnectionFeatures
	 * ()
	 */
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.edit.IFeatureProvider#getCreateFeatures()
	 */
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getCustomFeatures(org.
	 * eclipse.graphiti.features.context.IContext)
	 */
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return ZERO_CUSTOM_FEATURES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getDeleteFeature(org.eclipse
	 * .graphiti.features.context.IDeleteContext)
	 */
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getDiagramTypeProvider()
	 */
	public IDiagramTypeProvider getDiagramTypeProvider() {
		return dtp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.edit.IFeatureProvider#getPropertyFeatures()
	 */

	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.edit.IFeatureProvider#getRemoveFeature(
	 * org.eclipse.graphiti.features.context.IContext)
	 */
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getUpdateFeature(org.eclipse
	 * .graphiti.features.context.IUpdateContext)
	 */
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getLayoutFeature(org.eclipse
	 * .graphiti.features.context.ILayoutContext)
	 */
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getMoveShapeFeatures(org
	 * .eclipse.graphiti.features.context.IMoveShapeContext)
	 */
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeatureProvider#
	 * getMoveConnectionDecoratorFeatures(org.eclipse.graphiti.features.context.
	 * IMoveConnectionDecoratorContext)
	 */
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getMoveBendpointFeatures
	 * (org.eclipse.graphiti.features.context.IMoveBendpointContext)
	 */
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getResizeShapeFeatures
	 * (org.eclipse.graphiti.features.context.IResizeShapeContext)
	 */
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getAddBendpointFeature
	 * (org.eclipse.graphiti.features.context.IAddBendpointContext)
	 */
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getRemoveBendpointFeature
	 * (org.eclipse.graphiti.features.context.IRemoveBendpointContext)
	 */
	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getDirectEditingFeature(
	 * org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IConvenientFeatureProvider#canUpdate(org
	 * .eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason canUpdate(IUpdateContext context) {
		final String SIGNATURE = "canUpdate(IUpdateContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		IReason ret = Reason.createFalseReason();
		IUpdateFeature updateFeature = getUpdateFeature(context);
		if (updateFeature != null) {
			boolean b = updateFeature.canUpdate(context);
			ret = new Reason(b);
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#canLayout(org.eclipse.
	 * graphiti.features.context.ILayoutContext)
	 */
	public IReason canLayout(ILayoutContext context) {
		final String SIGNATURE = "canLayout(ILayoutContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		IReason ret = Reason.createFalseReason();
		ILayoutFeature layoutFeature = getLayoutFeature(context);
		if (layoutFeature != null) {
			boolean b = layoutFeature.canLayout(context);
			ret = new Reason(b);
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IConvenientFeatureProvider#updateIfPossible
	 * (org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateIfPossible(IUpdateContext context) {
		final String SIGNATURE = "updateIfPossible(IUpdateContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		boolean b = false;
		IUpdateFeature updateFeature = getUpdateFeature(context);
		if (updateFeature != null) {
			// if (updateSemanticsFeature != null &&
			// updateSemanticsFeature.canUpdate(context)) {
			// ret = updateSemanticsFeature.update(context);
			// }
			TransactionalEditingDomain editingDomain = GraphitiInternal.getEmfService().getTransactionalEditingDomain(
					getDiagramTypeProvider());
			b = CommandExec.getSingleton().executeCommand(new GenericFeatureCommandWithContext(updateFeature, context), editingDomain);
		}
		IReason reason = new Reason(b);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, reason);
		}
		return reason;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#layoutIfPossible(org.eclipse
	 * .graphiti.features.context.ILayoutContext)
	 */
	public IReason layoutIfPossible(ILayoutContext context) {
		final String SIGNATURE = "layoutIfPossible(ILayoutContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		boolean b = false;
		ILayoutFeature layoutSemanticsFeature = getLayoutFeature(context);
		if (layoutSemanticsFeature != null) {
			TransactionalEditingDomain editingDomain = GraphitiInternal.getEmfService().getTransactionalEditingDomain(
					getDiagramTypeProvider());
			b = CommandExec.getSingleton().executeCommand(new GenericFeatureCommandWithContext(layoutSemanticsFeature, context),
					editingDomain);
		}
		IReason res = new Reason(b);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, res);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IConvenientFeatureProvider#updateNeeded
	 * (org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateNeeded(IUpdateContext context) {
		final String SIGNATURE = "updateNeeded(IUpdateContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		IReason ret = Reason.createFalseReason();
		PictogramElement pe = context.getPictogramElement();
		if (pe != null && GraphitiInternal.getEmfService().isObjectAlive(pe)) {
			IUpdateFeature updateFeature = getUpdateFeature(context);
			if (updateFeature != null) {
				ret = updateFeature.updateNeeded(context);
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IConvenientFeatureProvider#
	 * updateIfPossibleAndNeeded
	 * (org.eclipse.graphiti.features.context.IUpdateContext)
	 */
	public IReason updateIfPossibleAndNeeded(IUpdateContext context) {
		final String SIGNATURE = "updateIfPossibleAndNeeded(IUpdateContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		IReason ret = canUpdate(context);
		if (ret.toBoolean()) {
			ret = updateNeeded(context);
			if (ret.toBoolean()) {
				ret = updateIfPossible(context);
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IConvenientFeatureProvider#addIfPossible(
	 * org.eclipse.graphiti.features.context.IAddContext)
	 */
	public PictogramElement addIfPossible(IAddContext context) {
		final String SIGNATURE = "addIfPossible(IAddContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		PictogramElement ret = null;
		if (canAdd(context).toBoolean()) {
			IAddFeature feature = getAddFeature(context);
			AddFeatureCommandWithContext addFeatureCommandWithContext = new AddFeatureCommandWithContext(feature, context);
			TransactionalEditingDomain editingDomain = GraphitiInternal.getEmfService().getTransactionalEditingDomain(
					getDiagramTypeProvider());
			boolean b = CommandExec.getSingleton().executeCommand(addFeatureCommandWithContext, editingDomain);
			if (b) {
				ret = addFeatureCommandWithContext.getAddedPictogramElements();
				IDiagramEditor diagramEditor = getDiagramTypeProvider().getDiagramEditor();
				if (diagramEditor != null) {
					diagramEditor.setPictogramElementForSelection(ret);
				}
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IConvenientFeatureProvider#canAdd(org.eclipse
	 * .graphiti.features.context.IAddContext)
	 */
	public IReason canAdd(IAddContext context) {
		final String SIGNATURE = "canAdd(IAddContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		IReason ret = Reason.createFalseReason();
		IAddFeature feature = getAddFeature(context);
		if (feature != null) {
			boolean b = feature.canAdd(context);
			ret = new Reason(b);
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getDirectEditingInfo()
	 */
	final public IDirectEditingInfo getDirectEditingInfo() {
		return directEditingInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getReconnectionFeature
	 * (org.eclipse.graphiti.features.context.IReconnectionContext)
	 */
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return new DefaultReconnectionFeature(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getPrintFeature()
	 */
	public IPrintFeature getPrintFeature() {
		return new DefaultPrintFeature(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeatureProvider#getSaveImageFeature()
	 */
	public ISaveImageFeature getSaveImageFeature() {
		return new DefaultSaveImageFeature(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProvider#getDragAndDropFeatures
	 * (org.eclipse.graphiti.features.context.IPictogramElementContext)
	 */
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		return new IFeature[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IMappingProvider#
	 * getAllBusinessObjectsForPictogramElement
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public Object[] getAllBusinessObjectsForPictogramElement(PictogramElement pictogramElement) {
		final String SIGNATURE = "getAllBusinessObjectsForPictogramElement(PictogramElement)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { pictogramElement });
		}
		Object[] ret = new Object[0];
		List<Object> retList = new ArrayList<Object>();

		if (getIndependenceSolver() != null) {
			Property property = Graphiti.getPeService().getProperty(pictogramElement, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
			if (property != null && property.getValue() != null) {
				// TODO currently only a single linked business object is
				// supported
				retList.add(getIndependenceSolver().getBusinessObjectForKey(property.getValue()));
			}
		}
		EObject[] allBusinessObjectsForLinkedPictogramElement = getLinkService().getAllBusinessObjectsForLinkedPictogramElement(
				pictogramElement);
		for (EObject eObject : allBusinessObjectsForLinkedPictogramElement) {
			retList.add(eObject);
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return retList.toArray(ret);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IMappingProvider#
	 * getBusinessObjectForPictogramElement
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	public Object getBusinessObjectForPictogramElement(PictogramElement pictogramElement) {
		final String SIGNATURE = "getBusinessObjectForPictogramElement(PictogramElement)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { pictogramElement });
		}
		Object ret = null;

		if (getIndependenceSolver() != null) {
			Property property = Graphiti.getPeService().getProperty(pictogramElement, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
			if (property != null && property.getValue() != null) {
				ret = getIndependenceSolver().getBusinessObjectForKey(property.getValue());
				if (ret != null) {
					if (info) {
						T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
					}
					return ret;
				}
			}
		}

		ret = getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IMappingProvider#
	 * getAllPictogramElementsForBusinessObject(java.lang.Object)
	 */
	public PictogramElement[] getAllPictogramElementsForBusinessObject(Object businessObject) {
		final String SIGNATURE = "getAllPictogramElementsForBusinessObject(Object)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { businessObject });
		}
		List<PictogramElement> retList = new ArrayList<PictogramElement>();

		IIndependenceSolver solver = getIndependenceSolver();
		if (solver != null) {
			String keyForBusinessObject = solver.getKeyForBusinessObject(businessObject);
			if (keyForBusinessObject != null) {
				Collection<PictogramElement> allContainedPictogramElements = Graphiti.getPeService().getAllContainedPictogramElements(
						getDiagramTypeProvider().getDiagram());
				for (PictogramElement pe : allContainedPictogramElements) {
					Property property = Graphiti.getPeService().getProperty(pe, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
					if (property != null && keyForBusinessObject.equals(property.getValue())) {
						retList.add(pe);
					}
				}
			}
		}

		if (businessObject instanceof EObject) {
			DiagramLink diagramLink = getDiagramTypeProvider().getDiagramLink();
			if (diagramLink != null) {
				Collection<PictogramLink> pictogramLinks = diagramLink.getPictogramLinks();
				for (PictogramLink pictogramLink : pictogramLinks) {
					List<EObject> businessObjects = pictogramLink.getBusinessObjects();
					for (EObject obj : businessObjects) {
						if (EcoreUtil.equals((EObject) businessObject, (EObject) obj)) {
							PictogramElement pe = pictogramLink.getPictogramElement();
							if (pe != null) {
								retList.add(pe);
							}
							break;
						}
					}
				}
			}
		}

		PictogramElement[] res = retList.toArray(new PictogramElement[0]);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, res);
		}
		return res;
	}

	/**
	 * This method is similar to.
	 * 
	 * @param businessObject
	 *            the business object
	 * @return the pictogram element for business object
	 * @see "getPictogramElementsForBusinessObject()", but only return the first
	 *      PictogramElement.
	 */
	public PictogramElement getPictogramElementForBusinessObject(Object businessObject) {
		final String SIGNATURE = "getPictogramElementForBusinessObject(Object)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { businessObject });
		}
		PictogramElement result = null;

		if (businessObject instanceof EObject) {
			DiagramLink diagramLink = getDiagramTypeProvider().getDiagramLink();
			if (diagramLink != null) {
				Collection<PictogramLink> pictogramLinks = diagramLink.getPictogramLinks();
				for (PictogramLink pictogramLink : pictogramLinks) {
					List<EObject> businessObjects = pictogramLink.getBusinessObjects();
					for (EObject obj : businessObjects) {
						if (EcoreUtil.equals((EObject) businessObject, obj)) {
							PictogramElement pe = pictogramLink.getPictogramElement();
							if (pe != null) {
								result = pe;
							}
							break;
						}
					}
					if (result != null) {
						break;
					}
				}
			}
		} else {
			IIndependenceSolver solver = getIndependenceSolver();
			if (solver != null) {
				String keyForBusinessObject = solver.getKeyForBusinessObject(businessObject);
				if (keyForBusinessObject != null) {
					Collection<PictogramElement> allContainedPictogramElements = Graphiti.getPeService().getAllContainedPictogramElements(
							getDiagramTypeProvider().getDiagram());
					for (PictogramElement pe : allContainedPictogramElements) {
						Property property = Graphiti.getPeService().getProperty(pe, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
						if (property != null && keyForBusinessObject.equals(property.getValue())) {
							result = pe;
							break;
						}
					}
				}
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, result);
		}
		return result;
	}

	/**
	 * Check does there have pictogram element linked to this business object.
	 * 
	 * @param businessObject
	 *            the business object
	 * @return true when at least one pictogram element is linked, otherwise
	 *         return false.
	 */
	public boolean hasPictogramElementForBusinessObject(Object businessObject) {
		return getPictogramElementForBusinessObject(businessObject) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IMappingProvider#link(org.eclipse.graphiti.
	 * mm.pictograms.PictogramElement, java.lang.Object)
	 */
	public void link(PictogramElement pictogramElement, Object businessObject) {
		link(pictogramElement, new Object[] { businessObject });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IMappingProvider#link(org.eclipse.graphiti.
	 * mm.pictograms.PictogramElement, java.lang.Object[])
	 */
	public void link(PictogramElement pictogramElement, Object[] businessObjects) {
		final String SIGNATURE = "link(PictogramElement, Object[])"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { pictogramElement, businessObjects });
		}
		IIndependenceSolver is = getIndependenceSolver();
		if (getIndependenceSolver() == null) {
			PictogramLink link = createOrGetPictogramLink(pictogramElement);
			if (link != null) {
				// remove currently linked BOs and add new BOs
				link.getBusinessObjects().clear();
				if (businessObjects != null) {
					for (int i = 0; i < businessObjects.length; i++) {
						EObject bo = (EObject) businessObjects[i];

						ResourceSet resourceSet = bo.eResource().getResourceSet();
						TransactionalEditingDomain editingDomain = GraphitiInternal.getEmfService().getTransactionalEditingDomain(
								getDiagramTypeProvider());
						ResourceSet editorResourceSet = editingDomain.getResourceSet();
						if (!resourceSet.equals(editorResourceSet)) {
							URI boUri = EcoreUtil.getURI(bo);
							bo = editorResourceSet.getEObject(boUri, true);
						}

						if (bo != null) {
							link.getBusinessObjects().add(bo);
						}
					}
				}
			}
		} else {
			// TODO currently only a single linked business object is supported
			String propertyValue = is.getKeyForBusinessObject(businessObjects[0]);
			Graphiti.getPeService().setPropertyValue(pictogramElement, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY, propertyValue);
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE);
		}
	}

	private PictogramLink createPictogramLink(PictogramElement pe) {
		PictogramLink ret = null;
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (diagram != null) {
			// create new link
			ret = LinksFactory.eINSTANCE.createPictogramLink();
			ret.setPictogramElement(pe);

			// add new link to diagram-link
			DiagramLink diagramLink = getDiagramTypeProvider().getDiagramLink();
			if (diagramLink != null) {
				diagramLink.getPictogramLinks().add(ret);
			}
		}
		return ret;
	}

	private PictogramLink createOrGetPictogramLink(PictogramElement pe) {
		PictogramLink link = pe.getLink();
		if (link == null) {
			link = createPictogramLink(pe);
		}
		return link;
	}

	/**
	 * Gets the independence solver.
	 * 
	 * @return the independence solver
	 */
	protected final IIndependenceSolver getIndependenceSolver() {
		return independenceSolver;
	}

	/**
	 * Sets the independence solver.
	 * 
	 * @param independenceSolver
	 *            the new independence solver
	 */
	protected final void setIndependenceSolver(IIndependenceSolver independenceSolver) {
		this.independenceSolver = independenceSolver;
	}

	@Override
	public void dispose() {
	}

	protected ILinkService getLinkService() {
		return Graphiti.getLinkService();
	}
}
