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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.func.IAdd;
import org.eclipse.graphiti.func.ICreate;
import org.eclipse.graphiti.func.ILayout;
import org.eclipse.graphiti.func.IMoveShape;
import org.eclipse.graphiti.func.IResizeShape;
import org.eclipse.graphiti.func.IUpdate;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IPattern.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           {@link AbstractPattern} instead
 */
public interface IPattern extends ICreate, IAdd, IUpdate, ILayout, IResizeShape, IMoveShape {

	/**
	 * Gets the create name.
	 * 
	 * @return name for UI representation
	 */
	String getCreateName();

	/**
	 * Gets the create description.
	 * 
	 * @return description for UI representation
	 */
	String getCreateDescription();

	// *************************************************************
	// others
	// *************************************************************
	/**
	 * Sets the feature provider.
	 * 
	 * @param fp
	 *            the new feature provider
	 */
	void setFeatureProvider(IFeatureProvider fp);

	/**
	 * This method must be implemented by the pattern user. The main pictogram
	 * element of the pattern is linked with the main business object.
	 * 
	 * @param mainBusinessObject
	 *            the main business object
	 * 
	 * @return True, if this pattern is responsible for the main business object
	 */
	boolean isMainBusinessObjectApplicable(Object mainBusinessObject);

	/**
	 * Complete info.
	 * 
	 * @param info
	 *            the info
	 * @param bo
	 *            the bo
	 */
	void completeInfo(IDirectEditingInfo info, Object bo);

	/**
	 * Complete info.
	 * 
	 * @param info
	 *            the info
	 * @param bo
	 *            the bo
	 * @param keyProperty
	 *            the key property
	 */
	void completeInfo(IDirectEditingInfo info, Object bo, String keyProperty);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IAdd#add(org.eclipse.graphiti.features.context
	 * .IAddContext)
	 */
	public PictogramElement add(IAddContext context);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context
	 * .IAddContext)
	 */
	public boolean canAdd(IAddContext context);

	/**
	 * Provides configuration object, which describes the resize behavior
	 * 
	 * @return configuration object
	 */
	IResizeConfiguration getResizeConfiguration();
}
