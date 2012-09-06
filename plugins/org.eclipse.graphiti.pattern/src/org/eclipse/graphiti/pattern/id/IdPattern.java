/*******************************************************************************
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
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern.id;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.pattern.TypedPattern;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Base class for ID patterns. The basic idea behind is to tag single parts of a
 * pattern shape with IDs (using {@link Property} objects). These IDs are used
 * to identify the parts of that shape and to call the update and layout methods
 * for the shapes with IDs. Clients do not need to search through the shape
 * hierarchy to find the shapes to update and layout.
 * 
 * @since 0.10
 */
public abstract class IdPattern extends TypedPattern implements IPattern {

	protected static final String PROPERTY_VALUE_PATTERN_TYPE_ID = "org.eclipse.graphiti.pattern.idpattern"; //$NON-NLS-1$
	protected static final String PROPERTY_KEY_ID = "org.eclipse.graphiti.pattern.id.id"; //$NON-NLS-1$

	public IdPattern() {
		super(null);
	}

	/*
	 * Base functionality
	 */

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		EList<Property> properties = pictogramElement.getProperties();
		boolean rootPropertyFound = false;
		for (Property property : properties) {
			if (PROPERTY_KEY_PATTERN_TYPE.equals(property.getKey())
					&& PROPERTY_VALUE_PATTERN_TYPE_ID.equals(property.getValue())) {
				rootPropertyFound = true;
			}
		}
		return isMainBusinessObjectApplicable(domainObject) && rootPropertyFound;
	}

	/*
	 * ID handling
	 */

	protected void setId(PropertyContainer container, String id) {
		Graphiti.getPeService().setPropertyValue(container, PROPERTY_KEY_ID, id);
	}

	protected String getId(PropertyContainer container) {
		EList<Property> properties = container.getProperties();
		for (Property property : properties) {
			if (PROPERTY_KEY_ID.equals(property.getKey())) {
				return property.getValue();
			}
		}
		return null;
	}

	protected PropertyContainer findById(PictogramElement pictogramElement, String idToFind) {
		if (idToFind == null || idToFind.length() == 0) {
			return null;
		}

		// Check id for PE
		String id = getId(pictogramElement);
		if (idToFind.equals(id)) {
			return pictogramElement;
		}

		// Check id for GA
		GraphicsAlgorithm graphicsAlgorithm = pictogramElement.getGraphicsAlgorithm();
		id = getId(graphicsAlgorithm);
		if (idToFind.equals(id)) {
			return graphicsAlgorithm;
		}

		// Check children of PE
		if (pictogramElement instanceof ContainerShape) {
			EList<Shape> children = ((ContainerShape) pictogramElement).getChildren();
			for (Shape shape : children) {
				PropertyContainer propertyContainer = findById(shape, idToFind);
				if (propertyContainer != null) {
					return propertyContainer;
				}
			}
		}

		// Check children of GA
		PropertyContainer propertyContainer = findByIdInGraphicsAlgorithmChildren(graphicsAlgorithm, idToFind);
		if (propertyContainer != null) {
			return propertyContainer;
		}

		return null;
	}

	private PropertyContainer findByIdInGraphicsAlgorithmChildren(GraphicsAlgorithm graphicsAlgorithm, String idToFind) {
		EList<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		for (GraphicsAlgorithm graphicsAlgorithmChild : graphicsAlgorithmChildren) {
			String id = getId(graphicsAlgorithmChild);
			if (idToFind.equals(id)) {
				return graphicsAlgorithmChild;
			}

			PropertyContainer propertyContainer = findByIdInGraphicsAlgorithmChildren(graphicsAlgorithmChild, idToFind);
			if (propertyContainer != null) {
				return propertyContainer;
			}
		}
		return null;
	}

	/*
	 * Add functionality
	 */

	@Override
	public boolean canAdd(IAddContext context) {
		return isMainBusinessObjectApplicable(context.getNewObject());
	}

	@Override
	public PictogramElement add(IAddContext context) {
		AreaContext areaContext = new AreaContext();
		areaContext.setHeight(context.getHeight());
		areaContext.setWidth(context.getWidth());
		areaContext.setLocation(context.getX(), context.getY());
		IdAddContext addContext = new IdAddContext(areaContext, context.getNewObject());
		addContext.setTargetConnection(context.getTargetConnection());
		addContext.setTargetConnectionDecorator(context.getTargetConnectionDecorator());
		addContext.setTargetContainer(context.getTargetContainer());
		PictogramElement pictogramElement = add(addContext);
		setPatternType(pictogramElement, PROPERTY_VALUE_PATTERN_TYPE_ID);
		layout(new LayoutContext(pictogramElement));
		return pictogramElement;
	}

	abstract protected PictogramElement add(IdAddContext context);

	/*
	 * Layout functionality
	 */

	@Override
	public boolean canLayout(ILayoutContext context) {
		return PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(context.getPictogramElement()))
				&& isMainBusinessObjectApplicable(getBusinessObjectForPictogramElement(context.getPictogramElement()));
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean changesDone = false;

		PictogramElement rootPictogramElement;
		if (context instanceof IdLayoutContext) {
			rootPictogramElement = ((IdLayoutContext) context).getRootPictogramElement();
		} else {
			rootPictogramElement = context.getPictogramElement();
			while (!isPatternRoot(rootPictogramElement)) {
				if (rootPictogramElement instanceof Shape) {
					ContainerShape container = ((Shape) rootPictogramElement).getContainer();
					if (container != null) {
						rootPictogramElement = container;
					} else {
						break;
					}
				} else {
					if (rootPictogramElement instanceof GraphicsAlgorithm) {
						PictogramElement pictogramElement = ((GraphicsAlgorithm) rootPictogramElement)
								.getPictogramElement();
						if (pictogramElement != null) {
							rootPictogramElement = pictogramElement;
						} else {
							break;
						}
					}
				}
			}
		}

		// Check id for PE
		PictogramElement pictogramElement = context.getPictogramElement();
		String id = getId(pictogramElement);
		if (id != null) {
			IdLayoutContext layoutContext = new IdLayoutContext(pictogramElement,
					pictogramElement.getGraphicsAlgorithm(), rootPictogramElement);
			if (layout(layoutContext, id)) {
				changesDone = true;
			}
		}

		// Check id for GA
		GraphicsAlgorithm graphicsAlgorithm = pictogramElement.getGraphicsAlgorithm();
		id = getId(graphicsAlgorithm);
		if (id != null) {
			IdLayoutContext layoutContext = new IdLayoutContext(graphicsAlgorithm.getPictogramElement(),
					graphicsAlgorithm, rootPictogramElement);
			if (layout(layoutContext, id)) {
				changesDone = true;
			}
		}

		// Check children of PE
		if (pictogramElement instanceof ContainerShape) {
			EList<Shape> children = ((ContainerShape) pictogramElement).getChildren();
			for (Shape shape : children) {
				LayoutContext idContext = new IdLayoutContext(shape, shape.getGraphicsAlgorithm(), rootPictogramElement);
				if (layout(idContext)) {
					changesDone = true;
				}
			}
		}

		// Check children of GA
		IdLayoutContext layoutContext;
		if (context instanceof IdLayoutContext) {
			layoutContext = (IdLayoutContext) context;
		} else {
			layoutContext = new IdLayoutContext(null, graphicsAlgorithm, rootPictogramElement);
		}
		if (checkLayoutChildren(graphicsAlgorithm, layoutContext)) {
			changesDone = true;
		}

		return changesDone;
	}

	protected boolean checkLayoutChildren(GraphicsAlgorithm graphicsAlgorithm, IdLayoutContext context) {
		boolean changesDone = false;
		EList<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		for (GraphicsAlgorithm graphicsAlgorithmChild : graphicsAlgorithmChildren) {
			String id = getId(graphicsAlgorithmChild);
			if (id != null) {
				IdLayoutContext layoutContext = new IdLayoutContext(graphicsAlgorithmChild.getPictogramElement(),
						graphicsAlgorithmChild, context.getRootPictogramElement());
				if (layout(layoutContext, id)) {
					changesDone = true;
				}
			}
			if (checkLayoutChildren(graphicsAlgorithmChild, context)) {
				changesDone = true;
			}
		}
		return changesDone;
	}

	abstract protected boolean layout(IdLayoutContext context, String id);

	/*
	 * Update functionality
	 */

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(context.getPictogramElement()))
				&& isMainBusinessObjectApplicable(getBusinessObjectForPictogramElement(context.getPictogramElement()));
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {

		PictogramElement rootPictogramElement;
		if (context instanceof IdUpdateContext) {
			rootPictogramElement = ((IdUpdateContext) context).getRootPictogramElement();
		} else {
			rootPictogramElement = context.getPictogramElement();
		}

		// Check id for PE
		PictogramElement pictogramElement = context.getPictogramElement();
		String id = getId(pictogramElement);
		if (id != null) {
			IdUpdateContext updateContext = new IdUpdateContext(pictogramElement,
					pictogramElement.getGraphicsAlgorithm(), rootPictogramElement,
					getBusinessObjectForPictogramElement(pictogramElement));
			IReason reason = updateNeeded(updateContext, id);
			if (reason.toBoolean()) {
				return reason;
			}
		}

		// Check id for GA
		GraphicsAlgorithm graphicsAlgorithm = pictogramElement.getGraphicsAlgorithm();
		id = getId(graphicsAlgorithm);
		if (id != null) {
			PictogramElement element = graphicsAlgorithm.getPictogramElement();
			IdUpdateContext updateContext = new IdUpdateContext(element, graphicsAlgorithm, rootPictogramElement,
					getBusinessObjectForPictogramElement(element));
			IReason reason = updateNeeded(updateContext, id);
			if (reason.toBoolean()) {
				return reason;
			}
		}

		// Check children of PE
		if (pictogramElement instanceof ContainerShape) {
			EList<Shape> children = ((ContainerShape) pictogramElement).getChildren();
			for (Shape shape : children) {
				IdUpdateContext updateContext = new IdUpdateContext(shape, shape.getGraphicsAlgorithm(),
						rootPictogramElement, getBusinessObjectForPictogramElement(shape));
				IReason reason = updateNeeded(updateContext);
				if (reason.toBoolean()) {
					return reason;
				}
			}
		}

		// Check children of GA
		IdUpdateContext updateContext;
		if (context instanceof IdUpdateContext) {
			updateContext = (IdUpdateContext) context;
		} else {
			updateContext = new IdUpdateContext(null, graphicsAlgorithm, rootPictogramElement, null);
		}
		IReason reason = checkUpdateNeededChildren(graphicsAlgorithm, updateContext);
		if (reason.toBoolean()) {
			return reason;
		}

		return Reason.createFalseReason();
	}

	protected IReason checkUpdateNeededChildren(GraphicsAlgorithm graphicsAlgorithm, IdUpdateContext context) {
		EList<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		for (GraphicsAlgorithm graphicsAlgorithmChild : graphicsAlgorithmChildren) {
			String id = getId(graphicsAlgorithmChild);
			if (id != null) {
				PictogramElement pictogramElement = graphicsAlgorithmChild.getPictogramElement();
				IdUpdateContext updateContext = new IdUpdateContext(pictogramElement, graphicsAlgorithmChild,
						context.getRootPictogramElement(), getBusinessObjectForPictogramElement(pictogramElement));
				IReason reason = updateNeeded(updateContext, id);
				if (reason.toBoolean()) {
					return reason;
				}
			}
			IReason reason = checkUpdateNeededChildren(graphicsAlgorithmChild, context);
			if (reason.toBoolean()) {
				return reason;
			}
		}
		return Reason.createFalseReason();
	}

	abstract protected IReason updateNeeded(IdUpdateContext context, String id);

	@Override
	public boolean update(IUpdateContext context) {
		boolean result = false;

		PictogramElement rootPictogramElement;
		if (context instanceof IdUpdateContext) {
			rootPictogramElement = ((IdUpdateContext) context).getRootPictogramElement();
		} else {
			rootPictogramElement = context.getPictogramElement();
		}

		// Check id for PE
		PictogramElement pictogramElement = context.getPictogramElement();
		String id = getId(pictogramElement);
		if (id != null) {
			IdUpdateContext updateContext = new IdUpdateContext(pictogramElement,
					pictogramElement.getGraphicsAlgorithm(), rootPictogramElement,
					getBusinessObjectForPictogramElement(pictogramElement));
			if (update(updateContext, id)) {
				result = true;
			}
		}

		// Check id for GA
		GraphicsAlgorithm graphicsAlgorithm = pictogramElement.getGraphicsAlgorithm();
		id = getId(graphicsAlgorithm);
		if (id != null) {
			PictogramElement element = graphicsAlgorithm.getPictogramElement();
			IdUpdateContext updateContext = new IdUpdateContext(element, graphicsAlgorithm, rootPictogramElement,
					getBusinessObjectForPictogramElement(element));
			if (update(updateContext, id)) {
				result = true;
			}
		}

		// Check children of PE
		if (pictogramElement instanceof ContainerShape) {
			EList<Shape> children = ((ContainerShape) pictogramElement).getChildren();
			for (Shape shape : children) {
				IdUpdateContext updateContext = new IdUpdateContext(shape, shape.getGraphicsAlgorithm(),
						rootPictogramElement, getBusinessObjectForPictogramElement(shape));
				if (update(updateContext)) {
					result = true;
				}
			}
		}

		// Check children of GA
		IdUpdateContext updateContext;
		if (context instanceof IdUpdateContext) {
			updateContext = (IdUpdateContext) context;
		} else {
			updateContext = new IdUpdateContext(null, graphicsAlgorithm, rootPictogramElement, null);
		}
		if (checkUpdateChildren(graphicsAlgorithm, updateContext)) {
			result = true;
		}

		return result;
	}

	protected boolean checkUpdateChildren(GraphicsAlgorithm graphicsAlgorithm, IdUpdateContext context) {
		boolean result = false;
		EList<GraphicsAlgorithm> graphicsAlgorithmChildren = graphicsAlgorithm.getGraphicsAlgorithmChildren();
		for (GraphicsAlgorithm graphicsAlgorithmChild : graphicsAlgorithmChildren) {
			String id = getId(graphicsAlgorithmChild);
			if (id != null) {
				PictogramElement pictogramElement = graphicsAlgorithmChild.getPictogramElement();
				IdUpdateContext updateContext = new IdUpdateContext(pictogramElement, graphicsAlgorithmChild,
						context.getRootPictogramElement(), getBusinessObjectForPictogramElement(pictogramElement));
				if (update(updateContext, id)) {
					result = true;
				}
			}
			if (checkUpdateChildren(graphicsAlgorithmChild, context)) {
				result = true;
			}
		}
		return result;
	}

	abstract protected boolean update(IdUpdateContext context, String id);

	/*
	 * Direct editing functionality
	 */

	@Override
	public int getEditingType() {
		// TODO how to use different editing types for different parts of the
		// pattern? No context to identify id...
		return TYPE_TEXT;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		boolean patternResponsible = PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(pictogramElement))
				&& isMainBusinessObjectApplicable(domainObject);

		if (patternResponsible) {
			String id = getId(pictogramElement);
			if (id == null) {
				id = getId(context.getGraphicsAlgorithm());

				if (id != null) {
					return canDirectEdit(context, id);
				}
			}
		}
		return false;
	}

	protected boolean canDirectEdit(IDirectEditingContext context, String id) {
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		boolean patternResponsible = PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(pictogramElement))
				&& isMainBusinessObjectApplicable(domainObject);

		if (patternResponsible) {
			String id = getId(pictogramElement);
			if (id == null) {
				id = getId(context.getGraphicsAlgorithm());

				if (id != null) {
					return getInitialValue(context, id);
				}
			}
		}
		return null;
	}

	protected String getInitialValue(IDirectEditingContext context, String id) {
		return null;
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		boolean patternResponsible = PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(pictogramElement))
				&& isMainBusinessObjectApplicable(domainObject);

		if (patternResponsible) {
			String id = getId(pictogramElement);
			if (id == null) {
				id = getId(context.getGraphicsAlgorithm());

				if (id != null) {
					return checkValueValid(value, context, id);
				}
			}
		}
		return null;
	}

	protected String checkValueValid(String value, IDirectEditingContext context, String id) {
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		boolean patternResponsible = PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(pictogramElement))
				&& isMainBusinessObjectApplicable(domainObject);

		if (patternResponsible) {
			String id = getId(pictogramElement);
			if (id == null) {
				id = getId(context.getGraphicsAlgorithm());

				if (id != null) {
					setValue(value, context, id);
				}
			}
		}
	}

	protected void setValue(String value, IDirectEditingContext context, String id) {
	}
}
