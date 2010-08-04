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
 * Created on 28.06.2005
 */
package org.eclipse.graphiti.examples.common.outline.tree;

import java.util.Collection;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.graphiti.examples.common.util.uiprovider.IUIProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderHolder;
import org.eclipse.swt.graphics.Image;

/**
 * 
 */
public class AbstractGraphicsTreeEditPart extends AbstractTreeEditPart implements IConfigurationProviderHolder {

	private IConfigurationProvider configurationProvider;

	/**
	 * 
	 */
	public AbstractGraphicsTreeEditPart(IConfigurationProvider configurationProvider) {
		super();
		setConfigurationProvider(configurationProvider);
	}

	/**
	 * @param model
	 */
	public AbstractGraphicsTreeEditPart(IConfigurationProvider configurationProvider, Object model) {
		super(model);
		setConfigurationProvider(configurationProvider);
	}

	/**
	 * Returns the IConfigurationProvider of this EditPart
	 * 
	 * @return The IConfigurationProvider of this EditPart
	 */
	public IConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	/**
	 * This method is called from refreshVisuals(), to display the image of the
	 * TreeItem.
	 * <p>
	 * By default this method displays the image of the FIRST attribute of the
	 * ModelObject as the TreeItem.
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	protected Image getImage() {
		Image result = getUIProvider().getImage(getModel(), null);
		return result;
	}

	/**
	 * This method is called by refreshVisuals(), to display the text of the
	 * TreeItem.
	 * <p>
	 * By default this method displays the FIRST attribute of the model Object
	 * as the TreeItem.
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	protected String getText() {
		String text = getUIProvider().getText(getModel(), IUIProvider.TEXT_TYPE_DEFAULT);
		return (text == null) ? "" : text; //$NON-NLS-1$
	}

	/**
	 * @param configurationProvider
	 *            The configurationProvider to set.
	 */
	private void setConfigurationProvider(IConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	private IUIProvider getUIProvider() {
		return ExamplesCommonPlugin.getDefault().getUIProvider();
	}

	/**
	 * Adds the all elements if not null.
	 * 
	 * @param resultList
	 *            the result list
	 * @param collection
	 *            the collection
	 */
	protected void addAllElementsIfNotNull(List<Object> resultList, Collection<?> collection) {
		for (Object element : collection) {
			if (element != null) {
				resultList.add(element);
			}
		}
	}

}