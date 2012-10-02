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
package org.eclipse.graphiti.pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.config.IPatternConfiguration;
import org.eclipse.graphiti.services.Graphiti;

/**
 * This class provides the basis for all specific pattern types. It provides
 * functionality to tag a shape as being created and maintained by a specific
 * pattern type. Type type is identified using a special {@link Property} with
 * the key PROPERTY_KEY_PATTERN_TYPE.
 * 
 * @since 0.10
 * @experimental This API is in an experimental state and should be used by
 *               clients only with care, as it not final and can be removed or
 *               changed without prior notice!
 */
public abstract class TypedPattern extends AbstractPattern {

	/**
	 * Constant indication the type of pattern, e.g. ID pattern.
	 */
	protected static final String PROPERTY_KEY_PATTERN_TYPE = "org.eclipse.graphiti.pattern.patternType";

	/**
	 * Constructor to be used when no configuration data is needed.
	 */
	public TypedPattern() {
		super();
	}

	/**
	 * Constructor to be used when configuration data is needed.
	 */
	public TypedPattern(IPatternConfiguration patternConfiguration) {
		super(patternConfiguration);
	}

	/**
	 * Sets the property that indicates that the given root shape of the pattern
	 * is created and maintained by a specific pattern type.
	 * 
	 * @param patternRootShape
	 *            The {@link PropertyContainer} object that is used as root
	 *            shape of the pattern.
	 * @param patternType
	 *            The {@link String} type of the pattern.
	 */
	protected void setPatternType(PropertyContainer patternRootShape, String patternType) {
		Graphiti.getPeService().setPropertyValue(patternRootShape, PROPERTY_KEY_PATTERN_TYPE, patternType);
	}

	/**
	 * Returns the property that indicates that the given root shape of the
	 * pattern is created and maintained by a specific pattern type if it is set
	 * for the given shape or one of its parents. If the property is set for the
	 * given shape it is returned, otherwise the parents are asked for the
	 * property; first the parent {@link ContainerShape} in the shape hierarchy
	 * is asked if there is no parent the {@link GraphicsAlgorithm} parent is
	 * asked.
	 * 
	 * @param patternRootShape
	 *            The {@link PropertyContainer} object that is used as root
	 *            shape of the pattern.
	 * @return The {@link String} type of the pattern.
	 */
	protected String getPatternType(PropertyContainer patternRootShape) {
		EList<Property> properties = patternRootShape.getProperties();
		for (Property property : properties) {
			if (PROPERTY_KEY_PATTERN_TYPE.equals(property.getKey())) {
				return property.getValue();
			}
		}
		if (patternRootShape instanceof Shape) {
			ContainerShape parent = ((Shape) patternRootShape).getContainer();
			if (parent != null && !(parent instanceof Diagram)) {
				return getPatternType(parent);
			}
		} else if (patternRootShape instanceof GraphicsAlgorithm) {
			PictogramElement pictogramElement = ((GraphicsAlgorithm) patternRootShape).getPictogramElement();
			if (pictogramElement != null) {
				return getPatternType(pictogramElement);
			} else {
				GraphicsAlgorithm parentGraphicsAlgorithm = ((GraphicsAlgorithm) patternRootShape)
						.getParentGraphicsAlgorithm();
				if (parentGraphicsAlgorithm != null) {
					return getPatternType(parentGraphicsAlgorithm);
				}
			}
		}
		return null;
	}
}
