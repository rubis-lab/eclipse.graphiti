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
package org.eclipse.graphiti.examples.common.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.graphiti.examples.common.Messages;
import org.eclipse.graphiti.examples.common.navigator.nodes.EClassesNode;
import org.eclipse.graphiti.examples.common.navigator.nodes.base.IContainerNode;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class GFEmfLabelProvider extends LabelProvider {
	private static final String IMPL = "Impl"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		String ret = ""; //$NON-NLS-1$
		if (element instanceof IContainerNode) {
			IContainerNode icn = (IContainerNode) element;
			ret = icn.getText();
		}
		if (element instanceof IFile) {
			IFile file = (IFile) element;
			return file.getName();
		}
		if (element instanceof Diagram) {
			Diagram diagram = (Diagram) element;
			if (diagram != null) {
				ret = createTextForDiagram(diagram);
			}
		}
		if (element instanceof EClass) {
			String name = ((EClass) element).getName();
			if (name == null) {
				name = Messages.GFEmfLabelProvider_NameNotAvailable;
			}
			return name;
		}
		if (element instanceof EObject && ret.length() <= 0) {
			EObject eObject = (EObject) element;
			ret = ret + eObject.getClass().getSimpleName();
			if (ret.endsWith(IMPL)) {
				ret = ret.substring(0, ret.length() - (IMPL.length()));
			}
		}
		if (element instanceof GraphicsAlgorithm && ret.length() > 0) {
			ret = ret + "   -   "; //$NON-NLS-1$
			ret = ret + super.getText(element);
		}
		return ret;
	}

	private String createTextForDiagram(Diagram diagram) {
		return Messages.GFEmfLabelProvider_DiagramText;
	}

	//	private String createTextForDiagramFile(Diagram diagram) {
	//		return diagram.getName() + " (" + diagram.getDiagramTypeId() + ")";
	//	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof EClassesNode) {
			return getEClassesNodeImage();
		}
		if (element instanceof IContainerNode) {
			IContainerNode icn = (IContainerNode) element;
			return icn.getImage();
		}
		if (element instanceof IFile) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
		if (element instanceof PictogramElement) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
		if (element instanceof EClass) {
			return getEClassImage();
		}
		if (element instanceof EObject) {
			return getEObjectImage();
		}
		return super.getImage(element);
	}

	private Image getEClassImage() {
		ImageRegistry registry = ExamplesCommonPlugin.getDefault().getImageRegistry();
		String key = "icons/full/obj16/EClass.gif"; //$NON-NLS-1$
		Image image = registry.get(key);
		if (image == null) {
			ImageDescriptor desc = ExamplesCommonPlugin.imageDescriptorFromPlugin("org.eclipse.emf.ecore.edit", key); //$NON-NLS-1$
			registry.put(key, desc);
			image = registry.get(key);
		}
		return image;
	}

	private Image getEObjectImage() {
		ImageRegistry registry = ExamplesCommonPlugin.getDefault().getImageRegistry();
		String key = "icons/full/obj16/EObject.gif"; //$NON-NLS-1$
		Image image = registry.get(key);
		if (image == null) {
			ImageDescriptor desc = ExamplesCommonPlugin.imageDescriptorFromPlugin("org.eclipse.emf.ecore.edit", key); //$NON-NLS-1$
			registry.put(key, desc);
			image = registry.get(key);
		}
		return image;
	}

	private Image getEClassesNodeImage() {
		ImageRegistry registry = ExamplesCommonPlugin.getDefault().getImageRegistry();
		String key = "icons/full/obj16/EPackage.gif"; //$NON-NLS-1$
		Image image = registry.get(key);
		if (image == null) {
			ImageDescriptor desc = ExamplesCommonPlugin.imageDescriptorFromPlugin("org.eclipse.emf.ecore.edit", key); //$NON-NLS-1$
			registry.put(key, desc);
			image = registry.get(key);
		}
		return image;
	}
}
