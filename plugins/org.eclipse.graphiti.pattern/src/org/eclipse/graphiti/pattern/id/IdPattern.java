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
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.func.IDirectEditing;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.pattern.TypedPattern;
import org.eclipse.graphiti.pattern.config.IPatternConfiguration;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Base class for ID patterns. The basic idea behind is to tag single parts of a
 * pattern shape with IDs (using {@link Property} objects). These IDs are used
 * to identify the parts of that shape and to call the update and layout methods
 * for the shapes with IDs. Clients do not need to search through the shape
 * hierarchy to find the shapes to update and layout.
 * <p>
 * Besides IDs this pattern base implementation also supports tagging
 * {@link PictogramElement}s with an index property that allows to number a
 * sequence of children using the same ID, e.g. a list of attributes inside a
 * class.
 * 
 * @since 0.10
 * @experimental This API is in an experimental state and should be used by
 *               clients only with care, as it not final and can be removed or
 *               changed without prior notice!
 */
public abstract class IdPattern extends TypedPattern implements IPattern {

	/**
	 * The property key that stores the information that a
	 * {@link PictogramElement} is the root object of a {@link IdPattern}
	 * subclass. The value is set by the {@link #add(IAddContext)} method.
	 */
	protected static final String PROPERTY_VALUE_PATTERN_TYPE_ID = "org.eclipse.graphiti.pattern.idpattern"; //$NON-NLS-1$

	/**
	 * The property key that is used to tag individual {@link PictogramElement}s
	 * with a specific ID to identify them later during e.g. update or layout.
	 */
	protected static final String PROPERTY_KEY_ID = "org.eclipse.graphiti.pattern.id.id"; //$NON-NLS-1$

	/**
	 * The property key that is used to tag individual {@link PictogramElement}s
	 * with a specific index beyond the ID to identify them later during e.g.
	 * update or layout. This can e.g. be used for lists of shapes like the
	 * attributes of a class.
	 */
	protected static final String PROPERTY_KEY_INDEX = "org.eclipse.graphiti.pattern.id.index"; //$NON-NLS-1$

	/**
	 * Default constructor for a new IdPattern. Clients should call either call
	 * this or the constructor using a {@link IPatternConfiguration} instance
	 * from their subclass constructor.
	 */
	public IdPattern() {
		super();
	}

	/**
	 * Constructor taking some pattern configuration data for the created
	 * IdPattern. Clients should call either call this or the null parameter
	 * constructor from their subclass constructor.
	 * 
	 * @param patternConfiguration
	 *            The configuration data to use
	 */
	public IdPattern(IPatternConfiguration patternConfiguration) {
		super(patternConfiguration);
	}

	/*
	 * Base functionality
	 */

	/**
	 * Checks if the {@link PictogramElement} is controlled by the pattern. The
	 * default implementation simply checks if the domain object linked to the
	 * given {@link PictogramElement} is the one that is controlled by this
	 * pattern, see {@link #isMainBusinessObjectApplicable(Object)}. Especially
	 * the default implementation does not traverse up the hierarchy to find a
	 * suitable parent.
	 * 
	 * @param pictogramElement
	 *            The {@link PictogramElement} to check
	 * @return <code>true</code> in case the pattern controls the given
	 *         {@link PictogramElement}, <code>false</code> otherwise.
	 */
	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	/**
	 * Checks if the given {@link PictogramElement} is the root object of this
	 * pattern. The default implementation checks if the domain object linked to
	 * the given {@link PictogramElement} is the one that is controlled by this
	 * pattern, see {@link #isMainBusinessObjectApplicable(Object)}. It also
	 * checks if the object is controlled by an ID pattern by checking the
	 * according property ({@link TypedPattern#PROPERTY_KEY_PATTERN_TYPE}).
	 * 
	 * @param pictogramElement
	 *            The {@link PictogramElement} to check
	 * @return <code>true</code> in case the given {@link PictogramElement} is
	 *         the root shape of this pattern, <code>false</code> otherwise.
	 */
	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		if (!isMainBusinessObjectApplicable(domainObject)) {
			return false;
		}
		String patternTypePropertyValue = Graphiti.getPeService().getPropertyValue(pictogramElement,
				PROPERTY_KEY_PATTERN_TYPE);
		return PROPERTY_VALUE_PATTERN_TYPE_ID.equals(patternTypePropertyValue);
	}

	/*
	 * ID handling
	 */

	/**
	 * Set the ID property ({@link #PROPERTY_KEY_ID}) for the given
	 * {@link PictogramElement}; it can be any {@link PropertyContainer}
	 * ,especially {@link Shape}s or {@link GraphicsAlgorithm}s are allowed. The
	 * used ID string can later be used to identify the shape, e.g. in the
	 * update or layout methods.
	 * 
	 * @param container
	 *            The {@link PictogramElement} to set the ID property for
	 * @param id
	 *            The {@link String} ID to set.
	 */
	protected void setId(PropertyContainer container, String id) {
		Graphiti.getPeService().setPropertyValue(container, PROPERTY_KEY_ID, id);
	}

	/**
	 * Returns any ID that has been set for the given {@link PictogramElement};
	 * it can be any {@link PropertyContainer}, especially {@link Shape}s or
	 * {@link GraphicsAlgorithm}s are allowed.
	 * 
	 * @param container
	 *            The {@link PictogramElement} to get the ID property from
	 * @return A {@link String} representing the value of the property or
	 *         <code>null</code> in case the property is not set, see
	 *         {@link #setId(PropertyContainer, String)}.
	 */
	protected String getId(PropertyContainer container) {
		EList<Property> properties = container.getProperties();
		for (Property property : properties) {
			if (PROPERTY_KEY_ID.equals(property.getKey())) {
				return property.getValue();
			}
		}
		return null;
	}

	/**
	 * Searches for a {@link PictogramElement} that has the given ID starting
	 * from the given {@link PictogramElement}. First the given element is
	 * checked, then its {@link GraphicsAlgorithm}; after that the
	 * {@link PictogramElement} children are checked recursively and last the
	 * {@link GraphicsAlgorithm} children also recursively. The first
	 * {@link PictogramElement} that has the given ID is returned, in case none
	 * is found in the tree spanned by the given {@link PictogramElement},
	 * <code>null</code> is returned.
	 * 
	 * @param pictogramElement
	 *            The {@link PictogramElement} at which the search shall start,
	 *            any {@link Shape}s or {@link GraphicsAlgorithm}s on top of
	 *            this element are ignored.
	 * @param idToFind
	 *            A {@link String} representing the ID to search for
	 * @return The {@link PictogramElement} that has the given ID property, in
	 *         case none id found <code>null</code>.
	 */
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

	/**
	 * Set the index property ({@link #PROPERTY_KEY_ID}) for the given
	 * {@link PictogramElement}; it can be any {@link PropertyContainer}
	 * ,especially {@link Shape}s or {@link GraphicsAlgorithm}s are allowed. The
	 * used index can later - together with the ID string - be used to identify
	 * the concrete shape in case of a list of shapes, e.g. in the update or
	 * layout methods.
	 * 
	 * @param container
	 *            The {@link PictogramElement} to set the index property for
	 * @param id
	 *            The {@link Integer} index to set.
	 */
	protected void setIndex(PropertyContainer container, int index) {
		Graphiti.getPeService().setPropertyValue(container, PROPERTY_KEY_INDEX, Integer.toString(index));
	}

	/**
	 * Returns any index that has been set for the given
	 * {@link PictogramElement}; it can be any {@link PropertyContainer},
	 * especially {@link Shape}s or {@link GraphicsAlgorithm}s are allowed.
	 * 
	 * @param container
	 *            The {@link PictogramElement} to get the index property from
	 * @return An {@link Integer} representing the value of the property or -1
	 *         in case the property is not set, see
	 *         {@link #setIndex(PropertyContainer, int)}.
	 */
	protected int getIndex(PropertyContainer container) {
		EList<Property> properties = container.getProperties();
		for (Property property : properties) {
			if (PROPERTY_KEY_INDEX.equals(property.getKey())) {
				return Integer.valueOf(property.getValue());
			}
		}
		return -1;
	}

	/*
	 * Add functionality
	 */

	/**
	 * Checks if adding is possible using this pattern in the given context. The
	 * default implementation simply checks if the new object passed in the
	 * context is the main domain object for the pattern, see
	 * {@link #isMainBusinessObjectApplicable(Object)}.
	 * 
	 * @param context
	 *            An {@link IAddContext} describing the add operation.
	 * @return <code>true</code> in case adding is possible, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean canAdd(IAddContext context) {
		return isMainBusinessObjectApplicable(context.getNewObject());
	}

	/**
	 * Adds a {@link PictogramElement} representation for the given context to
	 * the diagram. The default implementation delegates to
	 * {@link #doAdd(IAddContext)} (which clients should primarily override) and
	 * updates and layouts the returned {@link PictogramElement} afterwards.
	 * 
	 * @param context
	 *            An {@link IAddContext} describing the add operation.
	 * @return The root object of the created {@link PictogramElement} tree.
	 */
	@Override
	public PictogramElement add(IAddContext context) {
		PictogramElement pictogramElement = doAdd(context);
		setPatternType(pictogramElement, PROPERTY_VALUE_PATTERN_TYPE_ID);
		update(new UpdateContext(pictogramElement));
		layout(new LayoutContext(pictogramElement));
		return pictogramElement;
	}

	/**
	 * Clients should primarily override this method and implement their add
	 * functionality here. This method is called from within
	 * {@link #add(IAddContext)}.
	 * 
	 * @param context
	 *            An {@link IAddContext} describing the add operation.
	 * @return The root object of the created {@link PictogramElement} tree.
	 */
	abstract protected PictogramElement doAdd(IAddContext context);

	/*
	 * Layout functionality
	 */

	/**
	 * Checks if layouting a shape is possible using this pattern in the given
	 * context. The default implementation simply checks if the object passed in
	 * the context is the main domain object for the pattern, see
	 * {@link #isMainBusinessObjectApplicable(Object)} and if the
	 * {@link PictogramElement} given in the context is controlled by an ID
	 * pattern.
	 * 
	 * @param context
	 *            An {@link ILayoutContext} describing the layout operation.
	 * @return <code>true</code> in case layouting is possible,
	 *         <code>false</code> otherwise.
	 */
	@Override
	public boolean canLayout(ILayoutContext context) {
		return PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(context.getPictogramElement()))
				&& isMainBusinessObjectApplicable(getBusinessObjectForPictogramElement(context.getPictogramElement()));
	}

	/**
	 * Layouts a {@link PictogramElement} representation given in the context.
	 * The default implementation delegates to
	 * {@link #layout(IdLayoutContext, String)} (which clients should primarily
	 * override) to actually update individual {@link PictogramElement}s. This
	 * delegation is done for any {@link PictogramElement} tagged with an ID in
	 * the following order:
	 * <ul>
	 * <li>The {@link PictogramElement} given in the context itself</li>
	 * <li>The {@link GraphicsAlgorithm} of the {@link PictogramElement} in the
	 * context</li>
	 * <li>The {@link PictogramElement} children of the {@link PictogramElement}
	 * in the context</li>
	 * <li>The {@link GraphicsAlgorithm} children of the
	 * {@link GraphicsAlgorithm} of the {@link PictogramElement} in the context.
	 * For this the method
	 * {@link #layoutGraphicsAlgorithmChildren(GraphicsAlgorithm, IdLayoutContext)}
	 * is called which in term calles itself and this method recursively.</li>
	 * </ul>
	 * 
	 * @param context
	 *            An {@link ILayoutContext} describing the layout operation.
	 * @return <code>true</code> in case the layout operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
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
		if (layoutGraphicsAlgorithmChildren(graphicsAlgorithm, layoutContext)) {
			changesDone = true;
		}

		return changesDone;
	}

	/**
	 * This method implements the part of the layout that deals with the
	 * {@link GraphicsAlgorithm} children, see {@link #layout(IdLayoutContext)}.
	 * Clients should primarily override
	 * {@link #layout(IdLayoutContext, String)} which is called for all found
	 * {@link PictogramElement}s and {@link GraphicsAlgorithm}s as described
	 * above that have an ID.
	 * 
	 * @param graphicsAlgorithm
	 *            The {@link GraphicsAlgorithm} to layout.
	 * @param context
	 *            An {@link ILayoutContext} describing the layout operation.
	 * @return <code>true</code> in case the layout operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	protected boolean layoutGraphicsAlgorithmChildren(GraphicsAlgorithm graphicsAlgorithm, IdLayoutContext context) {
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
			if (layoutGraphicsAlgorithmChildren(graphicsAlgorithmChild, context)) {
				changesDone = true;
			}
		}
		return changesDone;
	}

	/**
	 * Clients should primarily override this method and implement their layout
	 * functionality here. This method is called from within
	 * {@link #layout(ILayoutContext)} for each of the {@link PictogramElement}s
	 * and {@link GraphicsAlgorithm}s that have been tagged with an ID.
	 * 
	 * @param context
	 *            An {@link IdLayoutContext} describing the layout operation.
	 * @return <code>true</code> in case the layout operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	abstract protected boolean layout(IdLayoutContext context, String id);

	/*
	 * Update functionality
	 */

	/**
	 * Checks if updating a shape is possible using this pattern in the given
	 * context. The default implementation simply checks if the object passed in
	 * the context is the main domain object for the pattern, see
	 * {@link #isMainBusinessObjectApplicable(Object)} and if the
	 * {@link PictogramElement} given in the context is controlled by an ID
	 * pattern.
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the update operation.
	 * @return <code>true</code> in case updating is possible,
	 *         <code>false</code> otherwise.
	 */
	@Override
	public boolean canUpdate(IUpdateContext context) {
		return PROPERTY_VALUE_PATTERN_TYPE_ID.equals(getPatternType(context.getPictogramElement()))
				&& isMainBusinessObjectApplicable(getBusinessObjectForPictogramElement(context.getPictogramElement()));
	}

	/**
	 * Checks if an updates is needed for a {@link PictogramElement}
	 * representation given in the context. The default implementation delegates
	 * to {@link #updateNeeded(IdUpdateContext, String)} (which clients should
	 * primarily override) to actually update individual
	 * {@link PictogramElement}s. This delegation is done for any
	 * {@link PictogramElement} tagged with an ID in the following order:
	 * <ul>
	 * <li>The {@link PictogramElement} given in the context itself</li>
	 * <li>The {@link GraphicsAlgorithm} of the {@link PictogramElement} in the
	 * context</li>
	 * <li>The {@link PictogramElement} children of the {@link PictogramElement}
	 * in the context</li>
	 * <li>The {@link GraphicsAlgorithm} children of the
	 * {@link GraphicsAlgorithm} of the {@link PictogramElement} in the context.
	 * For this the method
	 * {@link #updateNeededGraphicsAlgorithmChildren(GraphicsAlgorithm, IdUpdateContext)}
	 * is called which in term calls itself and this method recursively.</li>
	 * </ul>
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the update operation.
	 * @return An {@link IReason} indicating <code>true</code> and a
	 *         {@link String} reason in case the update operation is needed, an
	 *         {@link IReason} indicating <code>false</code> otherwise.
	 */
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
		IReason reason = updateNeededGraphicsAlgorithmChildren(graphicsAlgorithm, updateContext);
		if (reason.toBoolean()) {
			return reason;
		}

		return Reason.createFalseReason();
	}

	/**
	 * This method implements the part of the update needed check that deals
	 * with the {@link GraphicsAlgorithm} children, see
	 * {@link #updateNeeded(IUpdateContext)}. Clients should primarily override
	 * {@link #updateNeeded(IdUpdateContext, String)} which is called for all
	 * found {@link PictogramElement}s and {@link GraphicsAlgorithm}s as
	 * described above that have an ID.
	 * 
	 * @param graphicsAlgorithm
	 *            The {@link GraphicsAlgorithm} to perform the update check for.
	 * @param context
	 *            An {@link IdUpdateContext} describing the update operation.
	 * @return An {@link IReason} indicating <code>true</code> and a
	 *         {@link String} reason in case the update operation is needed, an
	 *         {@link IReason} indicating <code>false</code> otherwise.
	 */
	protected IReason updateNeededGraphicsAlgorithmChildren(GraphicsAlgorithm graphicsAlgorithm, IdUpdateContext context) {
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
			IReason reason = updateNeededGraphicsAlgorithmChildren(graphicsAlgorithmChild, context);
			if (reason.toBoolean()) {
				return reason;
			}
		}
		return Reason.createFalseReason();
	}

	/**
	 * Clients should primarily override this method and implement their update
	 * check functionality here. This method is called from within
	 * {@link #updateNeeded(IUpdateContext)} for each of the
	 * {@link PictogramElement}s and {@link GraphicsAlgorithm}s that have been
	 * tagged with an ID.
	 * 
	 * @param context
	 *            An {@link IdUpdateContext} describing the update operation.
	 * @return An {@link IReason} indicating <code>true</code> and a
	 *         {@link String} reason in case the update operation is needed, an
	 *         {@link IReason} indicating <code>false</code> otherwise.
	 */
	abstract protected IReason updateNeeded(IdUpdateContext context, String id);

	/**
	 * Updates a {@link PictogramElement} representation given in the context.
	 * The default implementation only delegates to
	 * {@link #update(IUpdateContext, boolean)} setting the parameter innerCall
	 * to <code>false</code>.
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the update operation.
	 * @return <code>true</code> in case the update operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	@Override
	public boolean update(IUpdateContext context) {
		return update(context, false);
	}

	/**
	 * Updates a {@link PictogramElement} representation given in the context
	 * recursively. The default implementation delegates to
	 * {@link #update(IdUpdateContext, String)} (which clients should primarily
	 * override) to actually update individual {@link PictogramElement}s. This
	 * delegation is done for any {@link PictogramElement} tagged with an ID in
	 * the following order:
	 * <ul>
	 * <li>The {@link PictogramElement} given in the context itself</li>
	 * <li>The {@link GraphicsAlgorithm} of the {@link PictogramElement} in the
	 * context</li>
	 * <li>The {@link PictogramElement} children of the {@link PictogramElement}
	 * in the context</li>
	 * <li>The {@link GraphicsAlgorithm} children of the
	 * {@link GraphicsAlgorithm} of the {@link PictogramElement} in the context.
	 * For this the method
	 * {@link #updateGraphicsAlgorithmChildren(GraphicsAlgorithm, IdUpdateContext)}
	 * is called which in term calls itself and this method recursively.</li>
	 * </ul>
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the update operation.
	 * @return <code>true</code> in case the update operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	protected boolean update(IUpdateContext context, boolean innerCall) {
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
				if (update(updateContext, true)) {
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
		if (updateGraphicsAlgorithmChildren(graphicsAlgorithm, updateContext)) {
			result = true;
		}

		if (result && !innerCall) {
			layoutPictogramElement(rootPictogramElement);
		}

		return result;
	}

	/**
	 * This method implements the part of the update that deals with the
	 * {@link GraphicsAlgorithm} children, see
	 * {@link #update(IUpdateContext, boolean)}. Clients should primarily
	 * override {@link #update(IdUpdateContext, String)} which is called for all
	 * found {@link PictogramElement}s and {@link GraphicsAlgorithm}s as
	 * described above that have an ID.
	 * 
	 * @param graphicsAlgorithm
	 *            The {@link GraphicsAlgorithm} to update.
	 * @param context
	 *            An {@link IdUpdateContext} describing the update operation.
	 * @return <code>true</code> in case the update operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	protected boolean updateGraphicsAlgorithmChildren(GraphicsAlgorithm graphicsAlgorithm, IdUpdateContext context) {
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
			if (updateGraphicsAlgorithmChildren(graphicsAlgorithmChild, context)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Clients should primarily override this method and implement their update
	 * functionality here. This method is called from within
	 * {@link #update(IUpdateContext, boolean)} for each of the
	 * {@link PictogramElement}s and {@link GraphicsAlgorithm}s that have been
	 * tagged with an ID.
	 * 
	 * @param context
	 *            An {@link IdUpdateContext} describing the update operation.
	 * @return <code>true</code> in case the update operation did changes to the
	 *         diagram, <code>false</code> otherwise.
	 */
	abstract protected boolean update(IdUpdateContext context, String id);

	/*
	 * Direct editing functionality
	 */

	/**
	 * The Graphiti framework calls this method to decide which UI to show up
	 * for direct editing. Corresponds to the method
	 * {@link AbstractDirectEditingFeature#getEditingType()}. The default
	 * implementation return {@link IDirectEditing#TYPE_NONE}, other valid type
	 * are defined by the TYPE_* constants in {@link IDirectEditing}.
	 * 
	 * @return The desired editing type
	 */
	@Override
	public int getEditingType() {
		// TODO how to use different editing types for different parts of the
		// pattern? No context to identify id...
		return super.getEditingType();
	}

	/**
	 * Clients can override this method to indicate that the pattern allows
	 * direct editing for the shape described in the passed
	 * {@link IDirectEditingContext}, but the recommended method to override for
	 * {@link IdPattern} is
	 * {@link #canDirectEdit(IDirectEditingContext, String)}. Corresponds to the
	 * method
	 * {@link AbstractDirectEditingFeature#canDirectEdit(IDirectEditingContext)}
	 * . The default implementation checks if the pattern is responsible for the
	 * shape and an ID is set; in that case it delegates to
	 * {@link #canDirectEdit(IDirectEditingContext, String)}.
	 * 
	 * @param context
	 *            A context object describing the direct edit request.
	 * @return <code>true</code> in case direct editing shall be allowed,
	 *         <code>false</code> otherwise.
	 */
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

	/**
	 * Clients can override this method to indicate that the pattern allows
	 * direct editing for the shape described in the passed
	 * {@link IDirectEditingContext} holding the given ID. Corresponds to the
	 * method
	 * {@link AbstractDirectEditingFeature#canDirectEdit(IDirectEditingContext)}
	 * . The default implementation simply returns <code>false</code>.
	 * 
	 * @param context
	 *            A context object describing the direct edit request.
	 * @param id
	 *            The ID to check direct editing for
	 * @return <code>true</code> in case direct editing shall be allowed,
	 *         <code>false</code> otherwise.
	 */
	protected boolean canDirectEdit(IDirectEditingContext context, String id) {
		return false;
	}

	/**
	 * Provides the initial value for display in the newly opened text editing
	 * UI component. Corresponds to the method
	 * {@link AbstractDirectEditingFeature#getInitialValue(IDirectEditingContext)}
	 * . The default implementation checks if the pattern is responsible for the
	 * shape and an ID is set; in that case it delegates to
	 * {@link #getInitialValue(IDirectEditingContext, String)}.
	 * 
	 * @param context
	 *            A context object describing the direct edit request.
	 * @return The initial string value to be displayed for editing by the user.
	 */
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
		return "";
	}

	/**
	 * Provides the initial value for display in the newly opened text editing
	 * UI component. Corresponds to the method
	 * {@link AbstractDirectEditingFeature#getInitialValue(IDirectEditingContext)}
	 * . The default implementation always returns an empty string.
	 * 
	 * @param context
	 *            A context object describing the direct edit request.
	 * @param id
	 *            The ID of the shape to get the initial value for
	 * @return The initial string value to be displayed for editing by the user.
	 */
	protected String getInitialValue(IDirectEditingContext context, String id) {
		return "";
	}

	/**
	 * This method will be called by the framework to check if the passed String
	 * is valid as new value for the shape. This method's response time should
	 * be small since the method is queried after each change of the value in
	 * the direct edit UI. In case of a not valid value, the returned string
	 * shall indicate the reason why the value is not valid. Corresponds to the
	 * method
	 * {@link AbstractDirectEditingFeature#checkValueValid(String, IDirectEditingContext)}
	 * . The default implementation checks if the pattern is responsible for the
	 * shape and an ID is set; in that case it delegates to
	 * {@link #checkValueValid(String, IDirectEditingContext, String)}.
	 * 
	 * @param value
	 *            The new value to check
	 * @param context
	 *            A context object describing the direct edit request.
	 * @return <code>null</code> in case of a valid value, a string describing
	 *         the reason for being not valid otherwise.
	 */
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

	/**
	 * This method will be called by the framework to check if the passed String
	 * is valid as new value for the shape. This method's response time should
	 * be small since the method is queried after each change of the value in
	 * the direct edit UI. The default implementation simply returns null to
	 * indicate that all values are valid. In case of a not valid value, the
	 * returned string shall indicate the reason why the value is not valid.
	 * Corresponds to the method
	 * {@link AbstractDirectEditingFeature#checkValueValid(String, IDirectEditingContext)}
	 * .
	 * 
	 * @param value
	 *            The new value to check
	 * @param context
	 *            A context object describing the direct edit request.
	 * @param id
	 *            The ID of the shape to check the value for
	 * @return <code>null</code> in case of a valid value, a string describing
	 *         the reason for being not valid otherwise.
	 */
	protected String checkValueValid(String value, IDirectEditingContext context, String id) {
		return null;
	}

	/**
	 * Set the new value after direct editing is finished. The value comes from
	 * the text editing UI component. Corresponds to the method
	 * {@link AbstractDirectEditingFeature#setValue(String, IDirectEditingContext)}
	 * . The default implementation checks if the pattern is responsible for the
	 * shape and an ID is set; in that case it delegates to
	 * {@link #setValue(String, IDirectEditingContext, String)}.
	 * 
	 * @param value
	 *            The new value to be set
	 * @param context
	 *            A context object describing the direct edit request.
	 */
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

	/**
	 * Set the new value after direct editing is finished. The value comes from
	 * the text editing UI component. Corresponds to the method
	 * {@link AbstractDirectEditingFeature#setValue(String, IDirectEditingContext)}
	 * . The default implementation does nothing.
	 * 
	 * @param value
	 *            The new value to be set
	 * @param context
	 *            A context object describing the direct edit request.
	 * @param id
	 *            The ID of the shape to set the value for
	 */
	protected void setValue(String value, IDirectEditingContext context, String id) {
	}
}
