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
/*
 * Created on 05.07.2005
 */
package org.eclipse.graphiti.internal.command;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IResizeConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IResizeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IResizeConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IResizeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;

/**
 * The Class ResizeShapeFeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ResizeShapeFeatureCommandWithContext extends FeatureCommandWithContext {

	/**
	 * The Constructor.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public ResizeShapeFeatureCommandWithContext(IFeature feature, IResizeContext context) {
		super(feature, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		boolean ret = true;
		if (ret) {
			IResizeFeature f = getResizeFeature();
			if (f instanceof IResizeConnectionDecoratorFeature) {
				IResizeConnectionDecoratorFeature resizeConnectionDecoratorFeature = (IResizeConnectionDecoratorFeature) f;
				IResizeConnectionDecoratorContext resizeContext = (IResizeConnectionDecoratorContext) getResizeContext();
				ret = (f != null) && resizeConnectionDecoratorFeature.canResizeConnectionDecorator(resizeContext);
			} else if (f instanceof IResizeShapeFeature) {
				IResizeShapeFeature resizeShapeFeature = (IResizeShapeFeature) f;
				IResizeShapeContext resizeContext = (IResizeShapeContext) getResizeContext();
				ret = (f != null) && resizeShapeFeature.canResizeShape(resizeContext);
			}
		}
		return ret;
	}

	/**
	 * @return
	 */
	private IResizeContext getResizeContext() {
		IResizeContext ret = null;
		if (getContext() instanceof IResizeShapeContext) {
			ret = (IResizeShapeContext) getContext();
		} else if (getContext() instanceof IResizeConnectionDecoratorContext) {
			ret = (IResizeConnectionDecoratorContext) getContext();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final boolean execute() {
		IResizeFeature resizeFeature = getResizeFeature();

		if (resizeFeature instanceof IResizeConnectionDecoratorFeature) {
			IResizeConnectionDecoratorFeature resizeConnectionDecoratorFeature = (IResizeConnectionDecoratorFeature) resizeFeature;
			IResizeConnectionDecoratorContext resizeContext = (IResizeConnectionDecoratorContext) getResizeContext();
			resizeConnectionDecoratorFeature.resizeConnectionDecorator(resizeContext);
		} else if (resizeFeature instanceof IResizeShapeFeature) {
			IResizeShapeFeature resizeShapeFeature = (IResizeShapeFeature) resizeFeature;
			IResizeShapeContext resizeContext = (IResizeShapeContext) getResizeContext();
			resizeShapeFeature.canResizeShape(resizeContext);
		}

		return true;
	}

	/**
	 * Gets the resize shape feature.
	 * 
	 * @return the resize shape feature
	 */
	protected IResizeFeature getResizeFeature() {
		IResizeFeature ret = null;
		if (getFeature() instanceof IResizeShapeFeature) {
			ret = (IResizeShapeFeature) getFeature();
		}
		if (getFeature() instanceof IResizeConnectionDecoratorFeature) {
			ret = (IResizeConnectionDecoratorFeature) getFeature();
		} else {
			return ret;
		}
		return ret;
	}
}