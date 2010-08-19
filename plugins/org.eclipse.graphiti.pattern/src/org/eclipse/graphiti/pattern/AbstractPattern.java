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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.graphiti.features.DefaultResizeConfiguration;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.config.IPatternConfiguration;
import org.eclipse.graphiti.pattern.mapping.IStructureMapping;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;
import org.eclipse.graphiti.pattern.mapping.data.IImageDataMapping;
import org.eclipse.graphiti.pattern.mapping.data.ITextDataMapping;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Class AbstractPattern.
 */
public abstract class AbstractPattern extends AbstractBasePattern implements IPattern {

	private IPatternConfiguration patternConfiguration;

	/**
	 * Creates a new {@link AbstractPattern}.
	 * 
	 * @param patternConfiguration
	 *            the pattern configuration
	 */
	public AbstractPattern(IPatternConfiguration patternConfiguration) {
		setPatternConfiguration(patternConfiguration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.ICreate#canCreate(org.eclipse.graphiti.features
	 * .context.ICreateContext)
	 */
	public boolean canCreate(ICreateContext context) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.ILayout#canLayout(org.eclipse.graphiti.features
	 * .context.ILayoutContext)
	 */
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		return isPatternControlled(pictogramElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IMoveShape#canMoveShape(org.eclipse.graphiti
	 * .features.context.IMoveShapeContext)
	 */
	public boolean canMoveShape(IMoveShapeContext context) {
		return context.getSourceContainer() != null && context.getSourceContainer().equals(context.getTargetContainer())
				&& isPatternRoot(context.getPictogramElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IResizeShape#canResizeShape(org.eclipse.graphiti
	 * .features.context.IResizeShapeContext)
	 */
	public boolean canResizeShape(IResizeShapeContext context) {
		return isPatternRoot(context.getPictogramElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IUpdate#canUpdate(org.eclipse.graphiti.features
	 * .context.IUpdateContext)
	 */
	public boolean canUpdate(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		return isPatternControlled(pictogramElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.ICreate#create(org.eclipse.graphiti.features
	 * .context.ICreateContext)
	 */
	public Object[] create(ICreateContext context) {
		return EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.pattern.IPattern#getCreateDescription()
	 */
	public String getCreateDescription() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.func.ICreateInfo#getCreateImageId()
	 */
	public String getCreateImageId() {
		return null;
	}

	public String getCreateLargeImageId() {
		return getCreateImageId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.pattern.IPattern#getCreateName()
	 */
	public String getCreateName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.pattern.IPattern#isMainBusinessObjectApplicable(
	 * java.lang.Object)
	 */
	abstract public boolean isMainBusinessObjectApplicable(Object mainBusinessObject);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.ILayout#layout(org.eclipse.graphiti.features
	 * .context.ILayoutContext)
	 */
	public boolean layout(ILayoutContext context) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IMoveShapeFeature#moveShape(org.eclipse
	 * .graphiti.features.context.IMoveShapeContext)
	 */
	final public void moveShape(IMoveShapeContext context) {
		preMoveShape(context);
		moveAllBendpoints(context);
		internalMove(context);
		postMoveShape(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IMoveShapeFeature#postMoveShape(org.eclipse
	 * .graphiti.dt.IContext)
	 */
	/**
	 * Post move shape.
	 * 
	 * @param context
	 *            the context
	 */
	protected void postMoveShape(IMoveShapeContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IMoveShapeFeature#preMoveShape(org.eclipse
	 * .graphiti.features.context.IMoveShapeContext)
	 */
	/**
	 * Pre move shape.
	 * 
	 * @param context
	 *            the context
	 */
	protected void preMoveShape(IMoveShapeContext context) {
	}

	/**
	 * Internal move.
	 * 
	 * @param context
	 *            the context
	 */
	protected void internalMove(IMoveShapeContext context) {
		Shape shapeToMove = context.getShape();
		ContainerShape oldContainerShape = context.getSourceContainer();
		ContainerShape newContainerShape = context.getTargetContainer();

		int x = context.getX();
		int y = context.getY();

		if (oldContainerShape != newContainerShape) {
			// the following is a workaround due to an MMR bug
			if (oldContainerShape != null) {
				Collection<Shape> children = oldContainerShape.getChildren();
				if (children != null) {
					children.remove(shapeToMove);
				}
			}

			shapeToMove.setContainer(newContainerShape);
			if (shapeToMove.getGraphicsAlgorithm() != null) {
				Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y, avoidNegativeCoordinates());
			}
		} else { // move within the same container
			if (shapeToMove.getGraphicsAlgorithm() != null) {
				Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y, avoidNegativeCoordinates());
			}
		}
	}

	// move bendpoints within a container shape
	/**
	 * Move all bendpoints.
	 * 
	 * @param context
	 *            the context
	 */
	protected void moveAllBendpoints(IMoveShapeContext context) {

		if (!(context.getShape() instanceof ContainerShape)) {
			return;
		}

		ContainerShape shapeToMove = (ContainerShape) context.getShape();

		int x = context.getX();
		int y = context.getY();

		int deltaX = x - shapeToMove.getGraphicsAlgorithm().getX();
		int deltaY = y - shapeToMove.getGraphicsAlgorithm().getY();

		if (deltaX != 0 || deltaY != 0) {

			List<Anchor> anchorsFrom = getAnchors(shapeToMove);
			List<Anchor> anchorsTo = new ArrayList<Anchor>(anchorsFrom);

			for (Anchor anchorFrom : anchorsFrom) {

				Collection<Connection> outgoingConnections = anchorFrom.getOutgoingConnections();

				for (Connection connection : outgoingConnections) {
					for (Anchor anchorTo : anchorsTo) {

						Collection<Connection> incomingConnections = anchorTo.getIncomingConnections();
						if (incomingConnections.contains(connection)) {
							if (connection instanceof FreeFormConnection) {
								FreeFormConnection ffc = (FreeFormConnection) connection;
								List<Point> points = ffc.getBendpoints();
								for (int i = 0; i < points.size(); i++) {
									Point point = points.get(i);
									int oldX = point.getX();
									int oldY = point.getY();
									points.set(i, Graphiti.getGaService().createPoint(oldX + deltaX, oldY + deltaY));
								}
							}
						}
					}
				}
			}
		}
	}

	private List<Anchor> getAnchors(ContainerShape containerShape) {
		List<Anchor> ret = new ArrayList<Anchor>();
		ret.addAll(containerShape.getAnchors());

		List<Shape> children = containerShape.getChildren();
		for (Shape shape : children) {
			if (shape instanceof ContainerShape) {
				ret.addAll(getAnchors((ContainerShape) shape));
			} else {
				ret.addAll(shape.getAnchors());
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IResizeShape#resizeShape(org.eclipse.graphiti
	 * .features.context.IResizeShapeContext)
	 */
	public void resizeShape(IResizeShapeContext context) {
		Shape shape = context.getShape();
		int width = context.getWidth();
		int height = context.getHeight();

		if (shape.getGraphicsAlgorithm() != null) {
			Graphiti.getGaService().setSize(shape.getGraphicsAlgorithm(), width, height);
		}

		layoutPictogramElement(shape);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IUpdate#update(org.eclipse.graphiti.features
	 * .context.IUpdateContext)
	 */
	public boolean update(IUpdateContext context) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.func.IUpdate#updateNeeded(org.eclipse.graphiti.features
	 * .context.IUpdateContext)
	 */
	public IReason updateNeeded(IUpdateContext context) {
		return Reason.createFalseReason();
	}

	/**
	 * Adds the graphical representation.
	 * 
	 * @param context
	 *            the context
	 * @param newObject
	 *            the new object
	 */
	protected void addGraphicalRepresentation(IAreaContext context, Object newObject) {
		getFeatureProvider().addIfPossible(new AddContext(context, newObject));
	}

	/**
	 * Sublasses can override this method.
	 * 
	 * @return true if moving to negative coordinates should not be possible
	 */
	protected boolean avoidNegativeCoordinates() {
		return false;
	}

	/**
	 * Gets the diagram.
	 * 
	 * @return the diagram
	 */
	protected Diagram getDiagram() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagram();
	}

	/**
	 * Gets the image.
	 * 
	 * @param structureMapping
	 *            the structure mapping
	 * @param link
	 *            the link
	 * @return the image
	 */
	protected String getImage(IStructureMapping structureMapping, PictogramLink link) {
		String ret = null;
		IDataMapping dm = structureMapping.getDataMapping();
		if (dm instanceof IImageDataMapping) {
			ret = ((IImageDataMapping) dm).getImageId(link);
		}
		return ret;
	}

	/**
	 * Gets the text.
	 * 
	 * @param structureMapping
	 *            the structure mapping
	 * @param link
	 *            the link
	 * @return the text
	 */
	protected String getText(IStructureMapping structureMapping, PictogramLink link) {
		String ret = null;
		IDataMapping dm = structureMapping.getDataMapping();
		if (dm instanceof ITextDataMapping) {
			ret = ((ITextDataMapping) dm).getText(link);
		}
		return ret;
	}

	/**
	 * This method must be implemented by the pattern developer / provider.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return true, if is pattern controlled
	 */
	abstract protected boolean isPatternControlled(PictogramElement pictogramElement);

	/**
	 * This method must be implemented by the pattern developer / provider.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return true, if is pattern root
	 */
	abstract protected boolean isPatternRoot(PictogramElement pictogramElement);

	/**
	 * Layout pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void layoutPictogramElement(PictogramElement pe) {
		LayoutContext context = new LayoutContext(pe);
		getFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * Update pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void updatePictogramElement(PictogramElement pe) {
		UpdateContext context = new UpdateContext(pe);
		getFeatureProvider().updateIfPossible(context);
		layoutPictogramElement(pe);
	}

	/**
	 * Sets the pattern configuration.
	 * 
	 * @param patternConfiguration
	 *            the patternConfiguration to set
	 */
	protected void setPatternConfiguration(IPatternConfiguration patternConfiguration) {
		this.patternConfiguration = patternConfiguration;
	}

	/**
	 * Gets the pattern configuration.
	 * 
	 * @return the patternConfiguration
	 */
	protected IPatternConfiguration getPatternConfiguration() {
		return patternConfiguration;
	}

	/**
	 * Manage color.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 * @return the color
	 */
	protected Color manageColor(int red, int green, int blue) {
		return Graphiti.getGaService().manageColor(getDiagram(), red, green, blue);
	}

	/**
	 * Manage color.
	 * 
	 * @param colorConstant
	 *            the color constant
	 * @return the color
	 */
	protected Color manageColor(IColorConstant colorConstant) {
		return Graphiti.getGaService().manageColor(getDiagram(), colorConstant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.pattern.IPattern#completeInfo(org.eclipse.graphiti
	 * .features.IDirectEditingInfo, java.lang.Object)
	 */
	public void completeInfo(IDirectEditingInfo info, Object bo) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.pattern.IPattern#completeInfo(org.eclipse.graphiti
	 * .features.IDirectEditingInfo, java.lang.Object, java.lang.String)
	 */
	public void completeInfo(IDirectEditingInfo info, Object bo, String keyProperty) {
	}

	public IResizeConfiguration getResizeConfiguration() {
		return new DefaultResizeConfiguration();
	}
}
