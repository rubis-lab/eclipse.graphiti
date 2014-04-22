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
 *    mwenz - Bug 324859 Need Undo/Redo support for Non-EMF based domain objects
 *    mwenz - Bug 342874 CreateConnectionCommand overwriting "sourceLocation" with
 *            "targetLocation" on creation of a Connection
 *    mwenz - Bug 340627 - Features should be able to indicate cancellation
 *    mgorning - Bug 329517 - state call backs during creation of a connection
 *    Bug 336488 - DiagramEditor API
 *    fvelasco - Bug 396247 - ImageDescriptor changes
 *    pjpaulin - Bug 352120 - Now uses IDiagramContainerUI interface
 *    fvelasco - Bug 417577 - state call backs review
 *    Philip Alldredge - Bug 418676 - Undo is not disabled when canUndo is false for Palette features
 *    mwenz - Bug 424020 - Infinite loop when IConnectionCreateFeature returns true for the diagram
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.func.ICreateInfo;
import org.eclipse.graphiti.internal.DefaultFeatureAndContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.internal.datatypes.impl.LocationImpl;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.IDiagramBehaviorUI;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.util.ui.PopupMenu;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CreateConnectionCommand extends AbstractCommand {

	/** The connection instance. */
	// private Connection connection;
	private List<IFeature> features;

	// the location when connection is dropped on canvas
	private Point location;

	// the location where new connection starts
	private ILocation sourceLocation;

	/** Start endpoint for the connection. */
	private final PictogramElement sourceObject;

	/** Target endpoint for the connection. */
	private PictogramElement targetObject;

	private boolean createConnectionStarted = false;
	private boolean createConnectionFinished = false;

	/**
	 * Instantiate a command that can create a connection between two anchors.
	 * 
	 * @param source
	 *            the source endpoint (a non-null Shape instance)
	 * @param lineStyle
	 *            the desired line style. See Connection#setLineStyle(int) for
	 *            details
	 * @throws IllegalArgumentException
	 *             if source is null
	 * @see Connection#setLineStyle(int)
	 */
	public CreateConnectionCommand(IConfigurationProvider configurationProvider, PictogramElement pe,
			List<IFeature> features) {
		super(configurationProvider);
		setLabel(Messages.CreateConnectionCommand_0_xmsg);

		this.features = features;
		this.sourceObject = pe;
	}

	@Override
	public boolean canExecute() {

		// allow connections only from anchor to anchor
		Anchor sourceAnchor = getAnchor(sourceObject);
		Anchor targetAnchor = getAnchor(targetObject);

		// if (sourceAnchor != null) {

		CreateConnectionContext connectionContext = new CreateConnectionContext();
		connectionContext.setSourceAnchor(sourceAnchor);
		connectionContext.setTargetAnchor(targetAnchor);
		connectionContext.setSourcePictogramElement(sourceObject);
		connectionContext.setTargetPictogramElement(targetObject);
		connectionContext.setTargetLocation(getCurrentLocation());
		connectionContext.setSourceLocation(sourceLocation);

		CustomContext customContext = new CustomContext();
		customContext.setPictogramElements(new PictogramElement[] { sourceObject, targetObject });
		customContext.setX(location.x);
		customContext.setY(location.y);

		IContext context = null;

		for (IFeature feature : features) {

			if (feature instanceof ICreateConnectionFeature)
				context = connectionContext;
			else
				context = customContext;

			GenericFeatureCommandWithContext ccc = new GenericFeatureCommandWithContext(feature, context);

			if (ccc.canExecute())
				return true;

		}

		return false;
	}

	@Override
	public void execute() {
		createConnectionFinished = true;

		// create a new connection between source- and target-anchor
		Anchor sourceAnchor = getAnchor(sourceObject);
		Anchor targetAnchor = getAnchor(targetObject);

		// if (sourceAnchor != null) {
		CreateConnectionContext connectionContext = new CreateConnectionContext();
		connectionContext.setSourceAnchor(sourceAnchor);
		connectionContext.setTargetAnchor(targetAnchor);
		connectionContext.setSourcePictogramElement(sourceObject);
		connectionContext.setTargetPictogramElement(targetObject);
		connectionContext.setTargetLocation(getCurrentLocation());
		connectionContext.setSourceLocation(sourceLocation);

		CustomContext customContext = new CustomContext();
		customContext.setPictogramElements(new PictogramElement[] { sourceObject, targetObject });

		IDiagramBehaviorUI diagramBehavior = (IDiagramBehaviorUI) getFeatureProvider().getDiagramTypeProvider()
				.getDiagramBehavior();
		Point newLocation = diagramBehavior.calculateRealMouseLocation(location);
		customContext.setLocation(newLocation.x, newLocation.y);

		List<GenericFeatureCommandWithContext> commands = new ArrayList<GenericFeatureCommandWithContext>();

		IContext context = null;

		for (IFeature feature : features) {

			if (feature instanceof ICreateConnectionFeature)
				context = connectionContext;
			else
				context = customContext;

			GenericFeatureCommandWithContext ccc = new GenericFeatureCommandWithContext(feature, context);

			if (ccc.canExecute())
				commands.add(ccc);

		}
		if (commands.size() == 0)
			return;

		if (commands.size() == 1) {

			try {
				CommandExec.getSingleton().executeCommand(commands.get(0), getTransactionalEditingDomain());
			} catch (Exception e) {
				// Wrap in runtime exception (handled outside)
				if (e instanceof RuntimeException) {
					throw (RuntimeException) e;
				} else {
					throw new RuntimeException(e);
				}
			}
			return;
		}

		PopupMenu popupMenu = new PopupMenu(commands, labelProvider);

		boolean b = popupMenu.show(Display.getCurrent().getActiveShell());

		if (b) {
			GenericFeatureCommandWithContext result = (GenericFeatureCommandWithContext) popupMenu.getResult();
			try {
				commands.remove(result);
				CommandExec.getSingleton().executeCommand(result, getTransactionalEditingDomain());
			} catch (Exception e) {
				// Wrap in runtime exception (handled outside)
				if (e instanceof RuntimeException) {
					throw (RuntimeException) e;
				} else {
					throw new RuntimeException(e);
				}
			}
		}

		for (GenericFeatureCommandWithContext command : commands) {
			if (command.getFeature() instanceof ICreateConnectionFeature) {
				((ICreateConnectionFeature) command.getFeature()).canceledAttaching(connectionContext);
			}
		}
	}

	public boolean canStartConnection() {

		CreateConnectionContext connectionContext = createContext();
		// allow connections only from anchor to anchor
		if (connectionContext.getSourceAnchor() == null) {
			// Prevent infinite loop in case no source anchor is available (e.g.
			// when connection starts on diagram root), see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=424020
			return false;
		}
		sourceLocation = connectionContext.getSourceLocation(); // store
																// location for
																// later usage

		for (IFeature feature : features) {

			if (feature instanceof ICreateConnectionFeature) {
				ICreateConnectionFeature ccf = (ICreateConnectionFeature) feature;
				if (ccf.canStartConnection(connectionContext)) {
					return true;
				}
			} else
				return true;
		}

		return false;
	}

	private CreateConnectionContext createContext() {
		Anchor sourceAnchor = getAnchor(sourceObject);
		Anchor targetAnchor = null;

		CreateConnectionContext connectionContext = new CreateConnectionContext();
		connectionContext.setSourceAnchor(sourceAnchor);
		connectionContext.setTargetAnchor(targetAnchor);
		connectionContext.setSourcePictogramElement(sourceObject);
		connectionContext.setTargetPictogramElement(null);
		connectionContext.setTargetLocation(null);
		ILocation currentLocation = getCurrentLocation();
		connectionContext.setSourceLocation(currentLocation);
		return connectionContext;
	}

	@Override
	public void redo() {
		// connection.reconnect();
	}

	/**
	 * Set the target endpoint for the connection.
	 * 
	 * @param target
	 *            that target endpoint (a non-null Shape instance)
	 * @throws IllegalArgumentException
	 *             if target is null
	 */
	public void setTarget(PictogramElement pe) {
		targetObject = pe;
	}

	@Override
	public boolean canUndo() {
		final CreateConnectionContext context = createContext();
		context.setTargetPictogramElement(targetObject);
		context.setTargetAnchor(getAnchor(targetObject));
		context.setTargetLocation(getCurrentLocation());

		for (IFeature feature : features) {
			if (feature instanceof ICreateConnectionFeature) {
				if (feature.canUndo(context)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void undo() {
		// connection.disconnect();
	}

	private Anchor getAnchor(PictogramElement pe) {
		Anchor ret = null;
		if (pe instanceof Anchor) {
			ret = (Anchor) pe;
		} else if (pe instanceof AnchorContainer) {
			ret = Graphiti.getPeService().getChopboxAnchor((AnchorContainer) pe);
		}
		return ret;
	}

	public PictogramElement getSourceObject() {
		return sourceObject;
	}

	/**
	 * sets the location for the command when connection is dropped into nowhere
	 * 
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * label provider for popup menu
	 */
	private static ILabelProvider labelProvider = new ILabelProvider() {

		public void removeListener(ILabelProviderListener listener) {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void dispose() {

		}

		public void addListener(ILabelProviderListener listener) {

		}

		public String getText(Object element) {

			GenericFeatureCommandWithContext command = (GenericFeatureCommandWithContext) element;

			IFeature feature = command.getFeature();

			if (feature instanceof ICreateInfo) // e.g. ICreateConnectionFeature
				return ((ICreateInfo) feature).getCreateName();
			if (feature instanceof ICustomFeature)
				return ((ICustomFeature) feature).getName();

			return null;
		}

		public Image getImage(Object element) {
			GenericFeatureCommandWithContext command = (GenericFeatureCommandWithContext) element;

			IFeature feature = command.getFeature();
			String providerId = feature.getFeatureProvider().getDiagramTypeProvider().getProviderId();
			if (feature instanceof ICreateInfo) // e.g. ICreateConnectionFeature
				return GraphitiUi.getImageService().getImageForId(providerId,
						((ICreateInfo) feature).getCreateImageId());
			if (feature instanceof ICustomFeature)
				return GraphitiUi.getImageService().getImageForId(providerId, ((ICustomFeature) feature).getImageId());

			return null;
		}

	};

	public IFeatureAndContext[] getFeaturesAndContexts() {
		List<IFeatureAndContext> featureList = new ArrayList<IFeatureAndContext>(features.size());

		CreateConnectionContext context = createContext();

		for (Iterator<IFeature> iterator = features.iterator(); iterator.hasNext();) {
			IFeature feature = iterator.next();
			featureList.add(new DefaultFeatureAndContext(feature, context));
		}

		return featureList.toArray(new IFeatureAndContext[featureList.size()]);
	}

	private ILocation getCurrentLocation() {
		if (location == null) {
			return null;
		}
		IDiagramBehaviorUI diagramBehavior = (IDiagramBehaviorUI) getFeatureProvider().getDiagramTypeProvider()
				.getDiagramBehavior();
		Point realLocation = diagramBehavior.calculateRealMouseLocation(location);
		ILocation currentLocation = new LocationImpl(realLocation.x, realLocation.y);
		return currentLocation;
	}

	public void connectionStarted() {
		createConnectionStarted = true;

		// inform feature about attachment to source
		CreateConnectionContext connectionContext = createContext();
		connectionContext.setSourceLocation(sourceLocation);
		for (IFeature feature : features) {
			if (feature instanceof ICreateConnectionFeature) {
				ICreateConnectionFeature ccf = (ICreateConnectionFeature) feature;
				ccf.attachedToSource(connectionContext);
			}
		}
	}

	public void deactivate() {
		if (createConnectionStarted && !createConnectionFinished) {
			CreateConnectionContext connectionContext = createContext();
			connectionContext.setSourceLocation(sourceLocation);
			for (IFeature feature : features) {
				if (feature instanceof ICreateConnectionFeature) {
					ICreateConnectionFeature ccf = (ICreateConnectionFeature) feature;
					ccf.canceledAttaching(connectionContext);
				}
			}
		}
	}
}