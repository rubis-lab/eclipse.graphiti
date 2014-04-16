/**
 * <copyright>
 * 
 * Copyright (c) 2012, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    cbrand - Bug 382928 - Introduce factory method(s) for easier gradient creation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.filesystem.patterns;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.filesystem.ui.FilesystemPredefinedColoredAreas;
import org.eclipse.graphiti.examples.mm.filesystem.Filesystem;
import org.eclipse.graphiti.examples.mm.filesystem.FilesystemFactory;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

public class FilesystemPattern extends AbstractPattern implements IPattern {

	public FilesystemPattern() {
		super(null);
	}

	@Override
	public String getCreateName() {
		return "Filesystem";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof Filesystem;
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Filesystem newFilesystem = FilesystemFactory.eINSTANCE.createFilesystem();
		newFilesystem.setName(createNewName());

		getDiagram().eResource().getContents().add(newFilesystem);

		addGraphicalRepresentation(context, newFilesystem);
		return new Object[] { newFilesystem };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof Filesystem && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		Filesystem addedDomainObject = (Filesystem) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Outer container (invisible)
		ContainerShape outerContainerShape = peCreateService.createContainerShape(targetDiagram, true);
		Rectangle outerRectangle = gaService.createInvisibleRectangle(outerContainerShape);
		gaService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), context.getWidth(),
				context.getHeight());

		// Register tab
		Rectangle registerRectangle = gaService.createRectangle(outerRectangle);
		gaService.setLocationAndSize(registerRectangle, 0, 0, 20, 20);
		registerRectangle.setFilled(true);
		gaService.setRenderingStyle(registerRectangle, FilesystemPredefinedColoredAreas.getLightGrayAdaptions());

		// Main contents area
		Rectangle mainRectangle = gaService.createRectangle(outerRectangle);
		setLocationAndSizeOfMainContentsArea(outerRectangle, mainRectangle);
		mainRectangle.setFilled(true);
		gaService.setRenderingStyle(mainRectangle, FilesystemPredefinedColoredAreas.getLightGrayAdaptions());

		// Folder name
		Shape shape = peCreateService.createShape(outerContainerShape, false);
		Text text = gaService.createText(shape, addedDomainObject.getName());
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		setLocationAndSizeOfTextArea(outerRectangle, text);

		peCreateService.createChopboxAnchor(outerContainerShape);

		link(outerContainerShape, addedDomainObject);

		return outerContainerShape;
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return context.getPictogramElement() instanceof ContainerShape
				&& getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof Filesystem;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean changesDone = false;
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				Rectangle outerRectangle = (Rectangle) outerGraphicsAlgorithm;

				// Adapt size of main contents area
				EList<GraphicsAlgorithm> graphicsAlgorithmChildren = outerRectangle.getGraphicsAlgorithmChildren();
				if (graphicsAlgorithmChildren.size() > 1) {
					GraphicsAlgorithm graphicsAlgorithm = graphicsAlgorithmChildren.get(1);
					if (graphicsAlgorithm instanceof Rectangle) {
						setLocationAndSizeOfMainContentsArea(outerRectangle, (Rectangle) graphicsAlgorithm);
						changesDone = true;
					}
				}
			}
		}

		// Adapt size and location of text field
		Rectangle outerRectangle = getOuterRectangle(pictogramElement);
		Text nameText = getNameText(pictogramElement);
		if (outerRectangle != null && nameText != null) {
			setLocationAndSizeOfTextArea(outerRectangle, nameText);
			changesDone = true;
		}

		return changesDone;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		Text nameText = getNameText(context.getPictogramElement());
		Filesystem domainObject = (Filesystem) getBusinessObjectForPictogramElement(context.getPictogramElement());
		if (domainObject.getName() == null || !domainObject.getName().equals(nameText.getValue())) {
			return Reason.createTrueReason("Name differs. Expected: '" + domainObject.getName() + "'");
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		Text nameText = getNameText(context.getPictogramElement());
		Filesystem domainObject = (Filesystem) getBusinessObjectForPictogramElement(context.getPictogramElement());
		nameText.setValue(domainObject.getName());
		return true;
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		Object domainObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (domainObject instanceof Filesystem && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		Filesystem filesystem = (Filesystem) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return filesystem.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "Folder name must not be empty";
		}

		Filesystem filesystem = (Filesystem) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof Filesystem) {
				if (!domainObject.equals(filesystem) && value.equals(((Filesystem) domainObject).getName())) {
					return "A filesystem with name '" + ((Filesystem) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		Filesystem filesystem = (Filesystem) getBusinessObjectForPictogramElement(context.getPictogramElement());
		filesystem.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private void setLocationAndSizeOfMainContentsArea(Rectangle outerRectangle, Rectangle mainRectangle) {
		Graphiti.getGaService().setLocationAndSize(mainRectangle, 0, 10, outerRectangle.getWidth(),
				outerRectangle.getHeight() - 10);
	}

	private void setLocationAndSizeOfTextArea(Rectangle outerRectangle, Text text) {
		Graphiti.getGaService().setLocationAndSize(text, 0, 10, outerRectangle.getWidth(),
				outerRectangle.getHeight() - 10);
	}

	private Rectangle getOuterRectangle(PictogramElement pictogramElement) {
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				return (Rectangle) outerGraphicsAlgorithm;
			}
		}
		return null;
	}

	private Text getNameText(PictogramElement pictogramElement) {
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				EList<Shape> children = outerContainerShape.getChildren();
				if (children.size() > 0) {
					Shape shape = children.get(0);
					GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
					if (graphicsAlgorithm instanceof Text) {
						return (Text) graphicsAlgorithm;
					}
				}
			}
		}
		return null;
	}

	private String createNewName() {
		String initialName = "NewFilesystem";
		String name = initialName;
		int number = 0;
		while (findFilesystem(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private Filesystem findFilesystem(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof Filesystem) {
				if (name.equals(((Filesystem) eObject).getName())) {
					return (Filesystem) eObject;
				}
			}
		}
		return null;
	}
}
