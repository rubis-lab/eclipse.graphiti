package org.eclipse.graphiti.examples.filesystem.features;

import org.eclipse.graphiti.examples.mm.filesystem.File;
import org.eclipse.graphiti.examples.mm.filesystem.FilesystemFactory;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class CreateInnerFileFeature extends AbstractCustomFeature {

	public CreateInnerFileFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Create File";
	}

	@Override
	public String getDescription() {
		return "Creates a new file inside this folder";
	}

	@Override
	public boolean isAvailable(IContext context) {
		return getFolderDomainObject(context) != null;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return getFolderDomainObject(context) != null;
	}

	public void execute(ICustomContext context) {
		Folder folder = getFolderDomainObject(context);
		String newName = createNewName(folder);
		File file = FilesystemFactory.eINSTANCE.createFile();
		file.setName(newName);
		folder.eResource().getContents().add(file);
		folder.getFiles().add(file);
	}

	private Folder getFolderDomainObject(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pictogramElements = ((ICustomContext) context).getPictogramElements();
			if (pictogramElements.length == 1) {
				PictogramElement pictogramElement = pictogramElements[0];
				Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
				if (domainObject instanceof Folder) {
					return (Folder) domainObject;
				}
			}
		}
		return null;
	}

	private String createNewName(Folder folder) {
		String initialName = "NewFile";
		String name = initialName;
		int number = 0;
		while (findFile(folder, name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private File findFile(Folder folder, String name) {
		for (File file : folder.getFiles()) {
			if (name.equals(file.getName())) {
				return file;
			}
		}
		return null;
	}
}
