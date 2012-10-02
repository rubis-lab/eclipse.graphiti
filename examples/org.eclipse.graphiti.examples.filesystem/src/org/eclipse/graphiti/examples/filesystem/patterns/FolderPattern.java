package org.eclipse.graphiti.examples.filesystem.patterns;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.mm.filesystem.File;
import org.eclipse.graphiti.examples.mm.filesystem.FilesystemFactory;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.pattern.id.IdLayoutContext;
import org.eclipse.graphiti.pattern.id.IdPattern;
import org.eclipse.graphiti.pattern.id.IdUpdateContext;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.PredefinedColoredAreas;

public class FolderPattern extends IdPattern implements IPattern {

	private static final String ID_FOLDER_NAME_TEXT = "folderNameText";
	private static final String ID_OUTER_RECTANGLE = "outerRectangle";
	private static final String ID_MAIN_RECTANGLE = "mainRectangle";
	private static final String ID_NAME_SEPARATOR = "nameSeparator";
	private static final String ID_FILE_NAMES_RECTANGLE = "fileNamesRectangle";
	private static final String ID_FILE_NAME_TEXT = "fileNameText";

	public FolderPattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "Folder";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof Folder;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Folder newFolder = FilesystemFactory.eINSTANCE.createFolder();
		getDiagram().eResource().getContents().add(newFolder);
		newFolder.setName(createNewName());

		addGraphicalRepresentation(context, newFolder);
		return new Object[] { newFolder };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement doAdd(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		Folder addedFolder = (Folder) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Outer container (invisible)
		ContainerShape outerContainerShape = peCreateService.createContainerShape(targetDiagram, true);
		Rectangle outerRectangle = gaService.createInvisibleRectangle(outerContainerShape);
		setId(outerRectangle, ID_OUTER_RECTANGLE);
		gaService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), context.getWidth(),
				context.getHeight());

		// Register tab
		Rectangle registerRectangle = gaService.createRectangle(outerRectangle);
		gaService.setLocationAndSize(registerRectangle, 0, 0, 20, 20);
		registerRectangle.setFilled(true);
		gaService.setRenderingStyle(registerRectangle, PredefinedColoredAreas.getSilverWhiteGlossAdaptions());

		// Main contents area
		Rectangle mainRectangle = gaService.createRectangle(outerRectangle);
		setId(mainRectangle, ID_MAIN_RECTANGLE);
		mainRectangle.setFilled(true);
		gaService.setRenderingStyle(mainRectangle, PredefinedColoredAreas.getSilverWhiteGlossAdaptions());

		// Folder name
		Shape textShape = peCreateService.createShape(outerContainerShape, false);
		Text folderNameText = gaService.createText(textShape, "");
		setId(folderNameText, ID_FOLDER_NAME_TEXT);
		folderNameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		folderNameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);

		// Separating line
		Shape lineShape = peCreateService.createShape(outerContainerShape, false);
		Polyline polyline = gaService.createPolyline(lineShape);
		setId(polyline, ID_NAME_SEPARATOR);
		polyline.setForeground(manageColor(IColorConstant.BLACK));

		// List of files in folder
		ContainerShape filesContainerShape = peCreateService.createContainerShape(outerContainerShape, false);
		Rectangle filesRectangle = gaService.createInvisibleRectangle(filesContainerShape);
		setId(filesRectangle, ID_FILE_NAMES_RECTANGLE);

		peCreateService.createChopboxAnchor(outerContainerShape);

		link(outerContainerShape, addedFolder);
		link(textShape, addedFolder);
		link(filesContainerShape, addedFolder);

		return outerContainerShape;
	}

	@Override
	protected boolean layout(IdLayoutContext context, String id) {
		boolean changesDone = false;

		Rectangle outerRectangle = (Rectangle) context.getRootPictogramElement().getGraphicsAlgorithm();

		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (id.equals(ID_MAIN_RECTANGLE)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 10, outerRectangle.getWidth(),
					outerRectangle.getHeight() - 10);
			changesDone = true;
		} else if (id.equals(ID_FOLDER_NAME_TEXT)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 10, outerRectangle.getWidth(), 20);
			changesDone = true;
		} else if (id.equals(ID_NAME_SEPARATOR)) {
			Polyline polyline = (Polyline) ga;
			polyline.getPoints().clear();
			List<Point> pointList = Graphiti.getGaService().createPointList(
					new int[] { 0, 30, outerRectangle.getWidth(), 30 });
			polyline.getPoints().addAll(pointList);
			changesDone = true;
		} else if (id.equals(ID_FILE_NAMES_RECTANGLE)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 30, outerRectangle.getWidth(),
					outerRectangle.getHeight() - 30);
			changesDone = true;
		} else if (id.equals(ID_FILE_NAME_TEXT)) {
			int index = getIndex(context.getGraphicsAlgorithm());
			Graphiti.getGaService().setLocationAndSize(ga, 5, 30 + 20 * index, outerRectangle.getWidth() - 10, 20);
			changesDone = true;
		}

		return changesDone;
	}

	@Override
	protected IReason updateNeeded(IdUpdateContext context, String id) {
		if (id.equals(ID_FOLDER_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Folder domainObject = (Folder) context.getDomainObject();
			if (domainObject.getName() == null || !domainObject.getName().equals(nameText.getValue())) {
				return Reason.createTrueReason("Name differs. Expected: '" + domainObject.getName() + "'");
			}
		} else if (id.equals(ID_FILE_NAMES_RECTANGLE)) {
			ContainerShape filesContainerShape = (ContainerShape) context.getPictogramElement();
			Folder folder = (Folder) context.getDomainObject();
			if (filesContainerShape.getChildren().size() != folder.getFiles().size()) {
				return Reason.createTrueReason("Number of files differ. Expected: " + folder.getFiles().size());
			}
		} else if (id.equals(ID_FILE_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			File file = (File) context.getDomainObject();
			if (file.getName() == null || !file.getName().equals(nameText.getValue())) {
				return Reason.createTrueReason("Name differs. Expected: '" + file.getName() + "'");
			}
		}

		return Reason.createFalseReason();
	}

	@Override
	protected boolean update(IdUpdateContext context, String id) {
		if (id.equals(ID_FOLDER_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Folder domainObject = (Folder) context.getDomainObject();
			nameText.setValue(domainObject.getName());
			return true;
		} else if (id.equals(ID_FILE_NAMES_RECTANGLE)) {
			EList<Shape> children = ((ContainerShape) context.getPictogramElement()).getChildren();
			Shape[] toDelete = children.toArray(new Shape[children.size()]);
			for (Shape shape : toDelete) {
				EcoreUtil.delete(shape, true);
			}
			EList<File> files = ((Folder) context.getDomainObject()).getFiles();
			int index = 0;
			for (File file : files) {
				Shape shape = Graphiti.getPeCreateService().createShape((ContainerShape) context.getPictogramElement(),
						true);
				Text fileNameText = Graphiti.getGaService().createText(shape, file.getName());
				setId(fileNameText, ID_FILE_NAME_TEXT);
				setIndex(fileNameText, index);
				index++;
				fileNameText.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
				fileNameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
				link(shape, file);
			}
			return true;
		} else if (id.equals(ID_FILE_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			File file = (File) context.getDomainObject();
			nameText.setValue(file.getName());
			return true;
		}
		return false;
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		Object domainObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (domainObject instanceof Folder && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		Folder folder = (Folder) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return folder.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "Folder name must not be empty";
		}

		Folder folder = (Folder) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof Folder) {
				if (!domainObject.equals(folder) && value.equals(((Folder) domainObject).getName())) {
					return "A folder with name '" + ((Folder) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		Folder folder = (Folder) getBusinessObjectForPictogramElement(context.getPictogramElement());
		folder.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private String createNewName() {
		String initialName = "NewFolder";
		String name = initialName;
		int number = 0;
		while (findFolder(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private Folder findFolder(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof Folder) {
				if (name.equals(((Folder) eObject).getName())) {
					return (Folder) eObject;
				}
			}
		}
		return null;
	}
}
