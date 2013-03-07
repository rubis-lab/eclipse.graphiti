/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 375533 - Problems with copy&paste in the tutorial
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.ui.features;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.ui.editor.DiagramSupport;
import org.eclipse.graphiti.ui.internal.util.clipboard.ModelClipboard;

/**
 * The Class AbstractPasteFeature.
 */
public abstract class AbstractPasteFeature extends AbstractFeature implements IPasteFeature {

	private static final String NAME = Messages.AbstractPasteFeature_0_xfld;

	/**
	 * Creates a new {@link AbstractPasteFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public AbstractPasteFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IPasteContext) {
			ret = canPaste((IPasteContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IPasteContext) {
			paste((IPasteContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * Gets original content from clipboard.
	 * 
	 * @return the content from clipboard
	 * @throws IllegalStateException
	 *             if not called from UI thread
	 */
	protected Object[] getFromClipboard() {
		return ModelClipboard.getDefault().getContentAsEObjects(
				getDiagramBehavior().getEditingDomain().getResourceSet());
	}

	/**
	 * Duplicates the clipboard's content.
	 * 
	 * @param target
	 *            an object acting as composite parent for the copies.
	 *            <code>null</code> if the copied elements should be top-level
	 *            elements.
	 * @return the copy result or <code>null</code> in case of an empty
	 *         clipboard
	 * @throws IllegalStateException
	 *             if not called from UI thread
	 * @see #isCompositionAllowed(EObject, EObject[])
	 */
	protected Object[] getCopiesFromClipBoard(Object target) {
		return ModelClipboard.getDefault().duplicateAndPaste(target, getDiagramBehavior().getEditingDomain())
				.toArray();
	}

	/**
	 * Answers whether at least one of the given objects can be aggregated below
	 * the given parent as composite children.
	 * 
	 * @param parent
	 *            the composite parent
	 * @param objects
	 *            the objects to check
	 * @return <code>true</code> if at least one object may be a composite child
	 *         of <code>parent</code>
	 * @see #getCopiesFromClipBoard(Object)
	 */
	protected boolean isCompositionAllowed(EObject parent, EObject[] objects) {
		return ModelClipboard.getDefault().isCompositionAllowed(parent, objects);
	}

	/**
	 * Checks if the given {@link EObject} can be resolved in the local
	 * {@link EditingDomain} of the current {@link DiagramSupport}. Pasting an
	 * EObject that is not resolvable (e.g. it was just created in another
	 * editor and has not yet been persisted) may cause issues with the
	 * graphical presentation of the new object: the Graphiti update will not
	 * find any domain object and will therefore mark the object as update
	 * needed (an update triggered will then remove the shape from the diagram).
	 * At least some special handling will be needed for the paste process of
	 * such an object: e.g. the domain object could be created along with its
	 * graphical presentation during {@link #paste(IPasteContext)}, but that may
	 * lead (depending on the domain) to other follow-up issues.<br>
	 * Clients may use this method to check for such a situation and react
	 * accordingly. Created as part of the fix for Bugzilla 375533.
	 * 
	 * @param object
	 *            The object to check
	 * @return <code>true</code> in case the given object can be resolved in the
	 *         editing domain of the current editor, <code>false</code>
	 *         otherwise.
	 * @since 0.9
	 */
	protected boolean isResolvable(EObject object) {
		URI uri = EcoreUtil.getURI(object);
		// First try the URI resolution without loading not yet loaded
		// resources because calling with loadOnDemand will _always_
		// create a new Resource instance for newly created and not yet
		// saved Resources, no matter if they already exist within the
		// ResourceSet or not
		EObject resolved = getDiagramBehavior().getEditingDomain().getResourceSet().getEObject(uri, false);
		if (resolved == null) {
			resolved = getDiagramBehavior().getEditingDomain().getResourceSet().getEObject(uri, true);
		}
		return resolved != null;
	}
}
