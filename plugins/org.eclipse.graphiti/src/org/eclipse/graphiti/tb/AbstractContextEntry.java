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
package org.eclipse.graphiti.tb;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;

/**
 * The Class AbstractContextEntry.
 */
public class AbstractContextEntry implements IContextEntry {

	private IContext context;

	private IFeature feature;

	private String iconId;

	private String text;

	private String description;

	/**
	 * Instantiates a new abstract context entry.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public AbstractContextEntry(IFeature feature, IContext context) {
		setFeature(feature);
		setContext(context);
		if (feature instanceof ICustomFeature) {
			setIconId(((ICustomFeature) feature).getImageId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#canExecute()
	 */
	public boolean canExecute() {
		if (getFeature() == null) {
			return false;
		}
		return getFeature().canExecute(getContext());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#execute()
	 */
	public void execute() {
		GenericFeatureCommandWithContext genericFeatureCommandWithContext = new GenericFeatureCommandWithContext(getFeature(), getContext());
		TransactionalEditingDomain editingDomain = getFeature().getFeatureProvider().getDiagramTypeProvider().getDiagramEditor()
				.getEditingDomain();
		CommandExec.getSingleton().executeCommand(genericFeatureCommandWithContext, editingDomain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#getContext()
	 */
	public IContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#getFeature()
	 */
	public IFeature getFeature() {
		return feature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#getIconId()
	 */
	public String getIconId() {
		return iconId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#getText()
	 */
	public String getText() {
		String ret = text;
		if (ret == null) {
			IFeature f = getFeature();
			if (f instanceof ICustomFeature) {
				ICustomFeature customFeature = (ICustomFeature) f;
				ret = customFeature.getName();
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#getDescription()
	 */
	public String getDescription() {
		String ret = description;
		if (ret == null) {
			IFeature f = getFeature();
			if (f instanceof ICustomFeature) {
				ICustomFeature customFeature = (ICustomFeature) f;
				ret = customFeature.getDescription();
			}
		}
		return ret;
	}

	private void setContext(IContext context) {
		this.context = context;
	}

	private void setFeature(IFeature feature) {
		this.feature = feature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#setIconId(java.lang.String)
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.tb.IContextEntry#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.tb.IContextEntry#setDescription(java.lang.String)
	 */
	public void setDescription(String text) {
		this.description = text;
	}

}
