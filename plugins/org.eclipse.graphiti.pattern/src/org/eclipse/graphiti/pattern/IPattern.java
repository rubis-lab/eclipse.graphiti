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
 *    Patch 185019 from Bug 332360 contributed by Volker Wegert
 *    Volker Wegert - Bug 336828: patterns should support delete,
 *                    remove, direct editing and conditional palette
 *                    creation entry
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.func.IAdd;
import org.eclipse.graphiti.func.ICreate;
import org.eclipse.graphiti.func.IDelete;
import org.eclipse.graphiti.func.IDirectEditing;
import org.eclipse.graphiti.func.ILayout;
import org.eclipse.graphiti.func.IMoveShape;
import org.eclipse.graphiti.func.IRemove;
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
public interface IPattern extends ICreate, IAdd, IDelete, IRemove, IUpdate, ILayout, IResizeShape, IMoveShape, IDirectEditing {

	/**
	 * Determines whether the pattern supports the creation of new business
	 * objects from the palette. Setting this to <code>false</code> will
	 * suppress the creation of a palette item for this pattern.
	 * 
	 * @return <code>true</code> if the pattern supports the {@link ICreate}
	 *         methods and a palette item should be generated
	 */
	boolean isPaletteApplicable();

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
	 * Complete the directEditing info. This information is needed to switch
	 * automatically into the direct editing mode. (e.g. after creation of a new
	 * object)
	 * 
	 * @param info
	 *            the directEditing info
	 * @param bo
	 *            the business object
	 */
	void completeInfo(IDirectEditingInfo info, Object bo);

	/**
	 * Complete the directEditing info. This information is needed to switch
	 * automatically into the direct editing mode. (e.g. after creation of a new
	 * object)
	 * 
	 * @param info
	 *            the directEditing info
	 * @param bo
	 *            the business object
	 * @param keyProperty
	 *            the key property
	 */
	void completeInfo(IDirectEditingInfo info, Object bo, String keyProperty);

	public PictogramElement add(IAddContext context);

	public boolean canAdd(IAddContext context);

	/**
	 * Provides configuration object, which describes the resize behavior.
	 * 
	 * @param context
	 *            the resizing context
	 * @return the resize configuration object
	 */
	IResizeConfiguration getResizeConfiguration(IResizeShapeContext context);
}
