/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 356218 - Added hasDoneChanges updates to update diagram feature
 *                         and called features via editor command stack to check it
 *    Laurent Le Moux (mwenz) - Bug 453553 - Provide an abort possibility for delete and remove features in case 'pre' methods fail
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.features.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.ICustomAbortableUndoRedoFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class DefaultUpdateDiagramFeature. Updates the shapes directly contained
 * in the diagram. Does not update the connections contained in the diagram,
 * since in many cases the connections get already updated by the shapes they
 * belong to.
 */
public class DefaultUpdateDiagramFeature extends AbstractUpdateFeature implements ICustomAbortableUndoRedoFeature {

	private boolean hasDoneChanges = false;

	/**
	 * Creates a new {@link DefaultUpdateDiagramFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultUpdateDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canUpdate(IUpdateContext context) {
		return context.getPictogramElement() instanceof Diagram;
	}

	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, true);

		// Reset hasDoneChanges flag because no changes have happened so far
		hasDoneChanges = false;
		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature
					&& ((ICustomAbortableUndoRedoFeature) feature).isAbort()) {
				// Perform all updates but stop as soon as one is aborted
				break;
			}

			feature.update(connToUpdate.get(feature));
			if (feature.hasDoneChanges()) {
				// At least one sub feature did changes
				hasDoneChanges = true;
			}
		}
		return true;
	}

	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			Diagram d = (Diagram) pe;
			EList<Shape> children = d.getChildren();
			for (Shape shape : children) {
				UpdateContext updateContext = new UpdateContext(shape);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.updateNeeded(updateContext).toBoolean()) {
					return Reason.createTrueReason();
				}
			}
			EList<Connection> connections = d.getConnections();
			for (Connection connection : connections) {
				UpdateContext updateContext = new UpdateContext(connection);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.updateNeeded(updateContext).toBoolean()) {
					return Reason.createTrueReason();
				}
			}
		}
		return Reason.createFalseReason();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.impl.AbstractFeature#hasDoneChanges()
	 */
	@Override
	public boolean hasDoneChanges() {
		return hasDoneChanges;
	}

	@Override
	public boolean canUndo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, false);
		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (!abortableFeature.canUndo(context)) {
					// One sub feature cannot be undone
					// --> disable undo for composite
					return false;
				}
			}
		}

		return super.canUndo(context);
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preUndo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, true);

		// Reset hasDoneChanges flag because no changes have happened so far
		hasDoneChanges = false;
		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (abortableFeature.isAbort()) {
					// Perform all updates but stop as soon as one is
					// aborted
					break;
				}
				abortableFeature.preUndo(connToUpdate.get(feature));
				if (feature.hasDoneChanges()) {
					// At least one sub feature did some changes
					hasDoneChanges = true;
				}
			}
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postUndo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, false);

		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (abortableFeature.isAbort()) {
					// Perform all updates but stop as soon as one is
					// aborted
					break;
				}
				abortableFeature.postUndo(connToUpdate.get(feature));
				if (feature.hasDoneChanges()) {
					// At least one sub feature did some changes
					hasDoneChanges = true;
				}
			}
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public boolean canRedo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, false);

		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (!abortableFeature.canRedo(context)) {
					// One sub feature cannot be redone
					// --> disable redo for composite
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void preRedo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, true);

		// Reset hasDoneChanges flag because no changes have happened so far
		hasDoneChanges = false;
		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (abortableFeature.isAbort()) {
					// Perform all updates but stop as soon as one is
					// aborted
					break;
				}
				abortableFeature.preRedo(connToUpdate.get(feature));
				if (feature.hasDoneChanges()) {
					// At least one sub feature did some changes
					hasDoneChanges = true;
				}
			}
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public void postRedo(IContext context) {
		PictogramElement pe = ((IUpdateContext) context).getPictogramElement();
		Map<IUpdateFeature, IUpdateContext> connToUpdate = findFeaturesToUpdateChildren(pe, false);

		for (IUpdateFeature feature : connToUpdate.keySet()) {
			if (feature instanceof ICustomAbortableUndoRedoFeature) {
				ICustomAbortableUndoRedoFeature abortableFeature = (ICustomAbortableUndoRedoFeature) feature;
				if (abortableFeature.isAbort()) {
					// Perform all updates but stop as soon as one is
					// aborted
					break;
				}
				abortableFeature.postRedo(connToUpdate.get(feature));
				if (feature.hasDoneChanges()) {
					// At least one sub feature did some changes
					hasDoneChanges = true;
				}
			}
		}
	}

	/**
	 * @since 0.12
	 */
	@Override
	public boolean isAbort() {
		return false;
	}

	/**
	 * @since 0.12
	 */
	protected Map<IUpdateFeature, IUpdateContext> findFeaturesToUpdateChildren(PictogramElement pe,
			boolean checkUpdateNeeded) {
		Map<IUpdateFeature, IUpdateContext> connToUpdate = new HashMap<IUpdateFeature, IUpdateContext>();
		if (pe instanceof Diagram) {
			Diagram d = (Diagram) pe;
			EList<Shape> children = d.getChildren();
			for (Shape shape : children) {
				UpdateContext updateContext = new UpdateContext(shape);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.canUpdate(updateContext)
						&& updateFeature.updateNeeded(updateContext).toBoolean()) {
					connToUpdate.put(updateFeature, updateContext);
				}
			}
		}
		return connToUpdate;
	}
}
