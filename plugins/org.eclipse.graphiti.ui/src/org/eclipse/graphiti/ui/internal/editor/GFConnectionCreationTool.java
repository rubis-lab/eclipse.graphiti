/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mgorning - Bug 329517 - state call backs during creation of a connection
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.command.CreateConnectionCommand;
import org.eclipse.swt.events.KeyEvent;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFConnectionCreationTool extends ConnectionCreationTool {

	public GFConnectionCreationTool() {
		super();
	}

	public GFConnectionCreationTool(CreationFactory factory) {
		super(factory);
	}

	@Override
	protected boolean handleKeyUp(KeyEvent e) {
		Object object = getFactory().getNewObject();
		if (object instanceof List<?>) {
			List<?> list = (List<?>) object;
			if (list.size() > 0) {
				object = list.get(0);
			}
		}
		if (object instanceof ICreateConnectionFeature) {
			final ICreateConnectionFeature createFeature = (ICreateConnectionFeature) object;
			final CreateConnectionContext context = new CreateConnectionContext();
			IFeatureProvider featureProvider = createFeature.getFeatureProvider();
			IDiagramTypeProvider diagramTypeProvider = featureProvider.getDiagramTypeProvider();
			PictogramElement[] selectedPictogramElements = diagramTypeProvider.getDiagramEditor()
					.getSelectedPictogramElements();
			if (selectedPictogramElements.length == 2) {
				PictogramElement sourcePictogramElement = selectedPictogramElements[0];
				Anchor sourceAnchor = getAnchor(sourcePictogramElement);
				PictogramElement targetPictogramElement = selectedPictogramElements[1];
				Anchor targetAnchor = getAnchor(targetPictogramElement);
				context.setSourcePictogramElement(sourcePictogramElement);
				context.setSourceAnchor(sourceAnchor);
				context.setTargetPictogramElement(targetPictogramElement);
				context.setTargetAnchor(targetAnchor);
				if (createFeature.canExecute(context)) {
					try {
						CommandExec.executeFeatureWithContext(createFeature, context);
					} catch (Exception ex) {
						if (ex instanceof RollbackException) {
							// Just log it as info (operation was cancelled on
							// purpose)
							T.racer().log(IStatus.INFO, "GFCommandStack.execute(Command) " + ex, ex); //$NON-NLS-1$
						} else {
							// Just log it as an error
							T.racer().error("GFCommandStack.execute(Command) " + ex, ex); //$NON-NLS-1$
						}
					}
				}
			}
		}
		eraseTargetFeedback();
		unlockTargetEditPart();
		return true;
	}

	private Anchor getAnchor(PictogramElement sourcePictogramElement) {
		if (sourcePictogramElement instanceof AnchorContainer) {
			EList<Anchor> anchors = ((AnchorContainer) sourcePictogramElement).getAnchors();
			if (anchors.size() > 0) {
				// Search for the first ChopboxAnchor...
				for (Iterator<Anchor> it = anchors.iterator(); it.hasNext();) {
					Anchor anchor = it.next();
					if (anchor instanceof ChopboxAnchor) {
						// ... and return it
						return anchor;
					}
				}
				// No ChopboxAnchor --> simply return first anchor
				return anchors.get(0);
			}
		}
		// No anchors
		return null;
	}

	@Override
	public void deactivate() {
		CreateConnectionCommand cmd = getCreateConnectionCommand();
		if (cmd != null) {
			cmd.deactivate();
		}
		for (ICreateConnectionFeature ccf : getCreateConnectionFeatures()) {
			ccf.endConnecting();
		}
		super.deactivate();
	}

	@Override
	public void activate() {
		super.activate();
		for (ICreateConnectionFeature ccf : getCreateConnectionFeatures()) {
			ccf.startConnecting();
		}
	}

	@Override
	protected void setState(int state) {
		if (state == STATE_CONNECTION_STARTED) {
			CreateConnectionCommand cmd = getCreateConnectionCommand();
			if (cmd != null) {
				cmd.connectionStarted();
			}
		}
		super.setState(state);
	}

	private CreateConnectionCommand getCreateConnectionCommand() {
		if (getTargetRequest() instanceof CreateConnectionRequest) {
			CreateConnectionRequest r = (CreateConnectionRequest) getTargetRequest();
			if (r.getStartCommand() instanceof CreateConnectionCommand) {
				CreateConnectionCommand cmd = (CreateConnectionCommand) r.getStartCommand();
				return cmd;
			}
		}
		return null;
	}

	private Iterable<ICreateConnectionFeature> getCreateConnectionFeatures() {
		if (getTargetRequest() instanceof CreateConnectionRequest) {
			List<ICreateConnectionFeature> ret = new ArrayList<ICreateConnectionFeature>();
			CreateConnectionRequest r = (CreateConnectionRequest) getTargetRequest();
			@SuppressWarnings("unchecked")
			List<IFeature> features = (List<IFeature>) r.getNewObject();
			for (IFeature feature : features) {
				if (feature instanceof ICreateConnectionFeature) {
					ICreateConnectionFeature ccf = (ICreateConnectionFeature) feature;
					ret.add(ccf);
				}
			}
			return ret;
		}
		return Collections.emptyList();
	}
}
