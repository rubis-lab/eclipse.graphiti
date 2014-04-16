package org.eclipse.graphiti.examples.filesystem.features;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.mm.filesystem.File;
import org.eclipse.graphiti.examples.mm.filesystem.Folder;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class DeleteInnerFileFeature extends AbstractCustomFeature {

	public DeleteInnerFileFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Delete File";
	}

	@Override
	public String getDescription() {
		return "Deletes the selected file inside this folder";
	}

	@Override
	public boolean isAvailable(IContext context) {
		return getFileDomainObject(context) != null;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return getFileDomainObject(context) != null;
	}

	public void execute(ICustomContext context) {
		File file = getFileDomainObject(context);

		EcoreUtil.delete(file);
	}

	private File getFileDomainObject(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pictogramElements = ((ICustomContext) context).getPictogramElements();
			if (pictogramElements.length == 1) {
				PictogramElement pictogramElement = pictogramElements[0];
				Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
				if (domainObject instanceof File) {
					if (pictogramElement instanceof Shape && pictogramElement.eContainer() instanceof ContainerShape
							&& pictogramElement.eContainer().eContainer() instanceof ContainerShape) {
						if (getBusinessObjectForPictogramElement((ContainerShape) pictogramElement.eContainer()
								.eContainer()) instanceof Folder) {
							return (File) domainObject;
						}
					}
				}
			}
		}
		return null;
	}
}
