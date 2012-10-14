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
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    mwenz - Bug 367204 - Correctly return the added PE inAbstractFeatureProvider's addIfPossible method
 *    fvelasco - Bug 391506 - Canceling a feature with OperationCanceledException provoked an extra undo
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
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
	 * Creates a new {@link AbstractFeatureProvider}.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public AbstractFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super();
		this.dtp = diagramTypeProvider;
	}

	public IAddFeature getAddFeature(IAddContext context) {
		return null;
	}

	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[0];
	}

	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[0];
	}

	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return ZERO_CUSTOM_FEATURES;
	}

	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return null;
	}

	public IDiagramTypeProvider getDiagramTypeProvider() {
		return this.dtp;
	}

	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		return null;
	}

	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return null;
	}

	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		return null;
	}

	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		return null;
	}

	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return null;
	}

	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		return null;
	}

	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return null;
	}

	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return null;
	}

	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return null;
	}

	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		return null;
	}

	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		return null;
	}

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

	public IReason updateIfPossible(IUpdateContext context) {
		final String SIGNATURE = "updateIfPossible(IUpdateContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		boolean b = false;
		IUpdateFeature updateFeature = getUpdateFeature(context);
		if (updateFeature != null) {
			IDiagramEditor diagramEditor = getDiagramTypeProvider().getDiagramEditor();
			diagramEditor.executeFeature(updateFeature, context);
			b = true;
		}
		IReason reason = new Reason(b);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, reason);
		}
		return reason;
	}

	public IReason layoutIfPossible(ILayoutContext context) {
		final String SIGNATURE = "layoutIfPossible(ILayoutContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		boolean b = false;
		ILayoutFeature layoutSemanticsFeature = getLayoutFeature(context);
		if (layoutSemanticsFeature != null) {
			IDiagramEditor diagramEditor = getDiagramTypeProvider().getDiagramEditor();
			diagramEditor.executeFeature(layoutSemanticsFeature, context);
			b = true;
		}
		IReason res = new Reason(b);
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, res);
		}
		return res;
	}

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

	public PictogramElement addIfPossible(IAddContext context) {
		final String SIGNATURE = "addIfPossible(IAddContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { context });
		}
		PictogramElement returnValue = null;
		if (canAdd(context).toBoolean()) {
			IAddFeature feature = getAddFeature(context);
			IDiagramEditor diagramEditor = getDiagramTypeProvider().getDiagramEditor();
			Object result = diagramEditor.executeFeature(feature, context);
			if (result instanceof PictogramElement) {
				returnValue = (PictogramElement) result;
			}
		}
		if (info) {
			T.racer().exiting(AbstractFeatureProvider.class, SIGNATURE, returnValue);
		}
		return returnValue;
	}

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

	final public IDirectEditingInfo getDirectEditingInfo() {
		return this.directEditingInfo;
	}

	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return new DefaultReconnectionFeature(this);
	}

	public IPrintFeature getPrintFeature() {
		return new DefaultPrintFeature(this);
	}

	public ISaveImageFeature getSaveImageFeature() {
		return new DefaultSaveImageFeature(this);
	}

	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		return new IFeature[0];
	}

	private class StringTransformer {
		private final static String marker = "__independentN"; //$NON-NLS-1$

		String[] decode(String value) {
			if (!value.startsWith(marker)) {
				return new String[] { value };
			} else {
				value = value.substring(marker.length(), value.length());
				return value.split(marker);
			}
		}

		String encode(String[] segments) {
			if (segments.length == 1) {
				return segments[0];
			}
			StringBuffer sb = new StringBuffer();
			for (String string : segments) {
				sb.append(marker);
				sb.append(string);
			}
			return sb.toString();
		}
	}

	StringTransformer st = new StringTransformer();

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
				String value = property.getValue();
				String[] values = getValues(value);
				for (String v : values) {
					retList.add(getIndependenceSolver().getBusinessObjectForKey(v));
				}
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

	private String[] getValues(String value) {
		if (value.length() == 0) {
			return new String[0];
		} else {
			return st.decode(value);
		}
	}

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
				String[] values = getValues(property.getValue());
				if (values.length > 0)
					ret = getIndependenceSolver().getBusinessObjectForKey(values[0]);
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
					if (property != null && Arrays.asList(getValues(property.getValue())).contains(keyForBusinessObject)) {
						retList.add(pe);
					}
				}
			}
		}

		if (businessObject instanceof EObject) {
			Diagram diagram = getDiagramTypeProvider().getDiagram();
			if (diagram != null) {
				Collection<PictogramLink> pictogramLinks = diagram.getPictogramLinks();
				for (PictogramLink pictogramLink : pictogramLinks) {
					List<EObject> businessObjects = pictogramLink.getBusinessObjects();
					for (EObject obj : businessObjects) {
						if (getDiagramTypeProvider().getCurrentToolBehaviorProvider().equalsBusinessObjects(businessObject, obj)) {
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
	 * Provides the first pictogram element which represents the given business
	 * object.
	 * 
	 * @param businessObject
	 *            the business object
	 * @return the pictogram element for business object
	 */
	public PictogramElement getPictogramElementForBusinessObject(Object businessObject) {
		final String SIGNATURE = "getPictogramElementForBusinessObject(Object)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { businessObject });
		}
		PictogramElement result = null;

		if (businessObject instanceof EObject) {
			Diagram diagram = getDiagramTypeProvider().getDiagram();
			if (diagram != null) {
				Collection<PictogramLink> pictogramLinks = diagram.getPictogramLinks();
				for (PictogramLink pictogramLink : pictogramLinks) {
					List<EObject> businessObjects = pictogramLink.getBusinessObjects();
					for (EObject obj : businessObjects) {
						if (getDiagramTypeProvider().getCurrentToolBehaviorProvider().equalsBusinessObjects(businessObject, obj)) {
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
						if (property != null && Arrays.asList(getValues(property.getValue())).contains(keyForBusinessObject)) {
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

	public void link(PictogramElement pictogramElement, Object businessObject) {
		link(pictogramElement, new Object[] { businessObject });
	}

	public void link(PictogramElement pictogramElement, Object[] businessObjects) {
		final String SIGNATURE = "link(PictogramElement, Object[])"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractFeatureProvider.class, SIGNATURE, new Object[] { pictogramElement, businessObjects });
		}
		IIndependenceSolver is = getIndependenceSolver();
		if (is == null) {
			PictogramLink link = createOrGetPictogramLink(pictogramElement);
			if (link != null) {
				// remove currently linked BOs and add new BOs
				link.getBusinessObjects().clear();
				if (businessObjects != null) {
					for (int i = 0; i < businessObjects.length; i++) {
						EObject bo = (EObject) businessObjects[i];
						Resource resource = bo.eResource();
						Assert.isNotNull(resource, " Business object " + bo + " is not contained in a resource"); //$NON-NLS-1$ //$NON-NLS-2$
						ResourceSet resourceSet = resource.getResourceSet();
						Assert.isNotNull(resourceSet, " Resource " + resource + " is not contained in a resource set"); //$NON-NLS-1$ //$NON-NLS-2$
						TransactionalEditingDomain editingDomain = getDiagramTypeProvider().getDiagramEditor().getEditingDomain();
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
			List<String> values = new ArrayList<String>();
			for (Object bo : businessObjects) {
				String propertyValue = is.getKeyForBusinessObject(bo);
				if (propertyValue != null)
					values.add(propertyValue);
			}
			String encodedValues = st.encode(values.toArray(new String[] {}));
			Graphiti.getPeService().setPropertyValue(pictogramElement, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY, encodedValues);
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
			ret = PictogramsFactory.eINSTANCE.createPictogramLink();
			ret.setPictogramElement(pe);

			// add new link to diagram
			diagram.getPictogramLinks().add(ret);
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
		return this.independenceSolver;
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

	public void dispose() {
	}

	protected ILinkService getLinkService() {
		return Graphiti.getLinkService();
	}
}
