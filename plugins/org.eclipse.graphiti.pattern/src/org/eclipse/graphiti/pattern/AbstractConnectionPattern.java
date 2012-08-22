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
 *    mgorning - Bug 329517 - state call backs during creation of a connection
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.context.IConnectionContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * This is the base class AbstractConnectionPattern that clients writing a
 * pattern for a connection domain object should subclass.
 */
public abstract class AbstractConnectionPattern extends AbstractBasePattern implements IConnectionPattern {

	/**
	 * Creates a new {@link AbstractConnectionPattern}.
	 */
	public AbstractConnectionPattern() {
		super();
	}

	/**
	 * Creates a new {@link AddConnectionContext} suitable for adding a
	 * connection for this pattern. The default implementation simply takes the
	 * source and target anchors of the provided
	 * {@link ICreateConnectionContext} and adds them to a newly created
	 * {@link AddConnectionContext} object.
	 * 
	 * @param context
	 *            The create connection context to be used as a basis for adding
	 *            a connection.
	 * 
	 * @return The {@link AddConnectionContext}.
	 */
	protected static AddConnectionContext getAddConnectionContext(ICreateConnectionContext context) {
		AddConnectionContext result = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		return result;
	}

	/**
	 * Clients must override this method to indicate that the pattern can be
	 * used to create domain objects as defined in the given
	 * {@link ICreateConnectionContext}. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#canCreate(ICreateConnectionContext)}
	 * . The default implementation simply returns <code>false</code>.
	 * 
	 * @param context
	 *            The context holding information on the connection domain
	 *            object to be created.
	 * @return <code>true</code> in case this pattern can create such a
	 *         connection domain object, <code>false</code> otherwise.
	 */
	public boolean canCreate(ICreateConnectionContext context) {
		return false;
	}

	/**
	 * Clients must override this method to indicate that the pattern can be
	 * used to create domain objects <b>starting</b> from what is defined in the
	 * given {@link ICreateConnectionContext}. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#canStartConnection(ICreateConnectionContext)}
	 * . The default implementation simply returns <code>false</code>.
	 * 
	 * @param context
	 *            The context holding information on the connection domain
	 *            object to be created.
	 * @return <code>true</code> in case this pattern can create such a
	 *         connection domain object, <code>false</code> otherwise.
	 */
	public boolean canStartConnection(ICreateConnectionContext context) {
		return false;
	}

	/**
	 * Clients must override this method to implement the functionality to
	 * create a new connection domain object as defined in the given
	 * {@link ICreateConnectionContext}. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#create(ICreateConnectionContext)}.
	 * The default implementation simply does nothing and returns
	 * <code>null</code>.
	 * 
	 * @param context
	 *            The context holding information on the connection domain
	 *            object to be created.
	 * @return The newly create {@link Connection} pictogram element.
	 */
	public Connection create(ICreateConnectionContext context) {
		return null;
	}

	/**
	 * Adds the graphical representation of the given new {@link Object} with
	 * the information in the given {@link IConnectionContext}.
	 * 
	 * @param context
	 *            The connection context for the new object
	 * @param newObject
	 *            The new object instance itself
	 * 
	 * @return The {@link Connection} prictogram element instance created for
	 *         the connection domain object.
	 */
	protected Connection addGraphicalRepresentation(IConnectionContext context, Object newObject) {
		AddConnectionContext newContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		newContext.setNewObject(newObject);
		return (Connection) getFeatureProvider().addIfPossible(newContext);
	}

	/**
	 * Helper method that triggers a layout of the given
	 * {@link PictogramElement}. The default implementation queries the feature
	 * provider and tries to find a functionality either in the pattern of an
	 * additional {@link AbstractLayoutFeature} that can handle the request and
	 * triggers the operation.
	 * 
	 * @param pe
	 *            The pictogram element to layout
	 */
	protected void layoutPictogramElement(PictogramElement pe) {
		LayoutContext context = new LayoutContext(pe);
		getFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * Helper method that triggers an update of the given
	 * {@link PictogramElement}. The default implementation queries the feature
	 * provider and tries to find a functionality either in the pattern of an
	 * additional {@link AbstractUpdateFeature} that can handle the request and
	 * triggers the operation.
	 * 
	 * @param pe
	 *            The pictogram element to update
	 */
	protected void updatePictogramElement(PictogramElement pe) {
		UpdateContext context = new UpdateContext(pe);
		getFeatureProvider().updateIfPossible(context);
		layoutPictogramElement(pe);
	}

	/**
	 * Client should override to return a string description of the type of
	 * domain object that is created with this pattern. The Graphiti framework
	 * uses this information to fill a tooltip for the creation tool entry in
	 * the palette. The default implementation simply returns <code>null</code>
	 * which indicates that no tooltip shall be displayed.
	 * 
	 * @return A {@link String} holding the tooltip
	 */
	public String getCreateDescription() {
		return null;
	}

	/**
	 * Client should override to return a string id of the the image icon for
	 * the domain object that is created with this pattern. The Graphiti
	 * framework uses this information to add an icon to the creation tool entry
	 * in the palette. The default implementation simply returns
	 * <code>null</code> which indicates that no icon shall be displayed.
	 * 
	 * @return A {@link String} holding the id of the icon as defined in the
	 *         AbstractImageProvider.
	 */
	public String getCreateImageId() {
		return null;
	}

	/**
	 * Client should override to return a string id of the the large image icon
	 * for the domain object that is created with this pattern. The Graphiti
	 * framework uses this information to add a large icon to the creation tool
	 * entry in the palette. The default implementation simply returns
	 * <code>null</code> which indicates that no icon shall be displayed.
	 * 
	 * @return A {@link String} holding the id of the large icon as defined in
	 *         the AbstractImageProvider.
	 */
	public String getCreateLargeImageId() {
		return getCreateImageId();
	}

	/**
	 * Client should override to return the name of the domain object that is
	 * created with this pattern. The Graphiti framework uses this information
	 * to fill the text for the creation tool entry in the palette. The default
	 * implementation simply returns <code>null</code> which results in an empty
	 * entry in the palette.
	 * 
	 * @return A {@link String} holding the name of the domain object.
	 */
	public String getCreateName() {
		return null;
	}

	/**
	 * Hook that is called by the Graphiti framework as soon as a new connection
	 * is started. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#startConnecting()}. The default
	 * implementation simply does nothing.
	 * 
	 * @since 0.9
	 */
	public void startConnecting() {
	}

	/**
	 * Hook that is called by the Graphiti framework as soon as a new connection
	 * is ended. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#endConnecting()}. The default
	 * implementation simply does nothing.
	 * 
	 * @since 0.9
	 */
	public void endConnecting() {
	}

	/**
	 * Hook that is called by the Graphiti framework as soon as a new connection
	 * is attached to its source anchor. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#attachedToSource(ICreateConnectionContext)}
	 * . The default implementation simply does nothing.
	 * 
	 * @since 0.9
	 */
	public void attachedToSource(ICreateConnectionContext context) {
	}

	/**
	 * Hook that is called by the Graphiti framework as soon as a connection
	 * creation is cancelled. Corresponds to the method
	 * {@link AbstractCreateConnectionFeature#canceledAttaching(ICreateConnectionContext)}
	 * . The default implementation simply does nothing.
	 * 
	 * @since 0.9
	 */
	public void canceledAttaching(ICreateConnectionContext context) {
	}
}
