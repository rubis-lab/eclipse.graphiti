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
package org.eclipse.graphiti.examples.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

/**
 * Collection of general static helper methods.
 */
public class Util {

	private static final Class PRIMITIVE_CLASSES[] = new Class[] { Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE,
			Long.TYPE, Float.TYPE, Double.TYPE };

	private static final Class NON_PRIMITIVE_CLASSES[] = new Class[] { Boolean.class, Byte.class, Character.class, Short.class,
			Integer.class, Long.class, Float.class, Double.class };

	/**
	 * Returns the non-primitive class for the given class. For example returns
	 * Integer.class for int.class. Returns the given class, if it is not a
	 * primitive class.
	 * 
	 * @param sourceClass
	 *            The class, for which to return the non-primitive class.
	 * @return The non-primitive class for the given class.
	 */
	public static Class getNonPrimitiveClass(Class sourceClass) {
		if (!sourceClass.isPrimitive())
			return sourceClass;
		for (int i = 0; i < PRIMITIVE_CLASSES.length; i++) {
			if (PRIMITIVE_CLASSES[i].equals(sourceClass))
				return NON_PRIMITIVE_CLASSES[i];
		}
		throw new RuntimeException("Unknown primitive class: " + sourceClass); //$NON-NLS-1$
	}

	/**
	 * Moves the object at the source index of the list to the _target index of
	 * the list and returns the moved object.
	 * 
	 * @param targetIndex
	 *            the new position for the object in the list.
	 * @param sourceIndex
	 *            the old position of the object in the list.
	 * @return the moved object.
	 * @exception IndexOutOfBoundsException
	 *                if either index isn't within the size range.
	 */
	public static Object moveElementInList(List<Object> list, int targetIndex, int sourceIndex) {
		if (targetIndex >= list.size() || targetIndex < 0)
			throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + list.size()); //$NON-NLS-1$ //$NON-NLS-2$

		if (sourceIndex >= list.size() || sourceIndex < 0)
			throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + list.size()); //$NON-NLS-1$ //$NON-NLS-2$

		Object object = list.get(sourceIndex);
		if (targetIndex != sourceIndex) {
			list.remove(sourceIndex);
			list.add(targetIndex, object);
		}
		return object;
	}

	/**
	 * Returns true, if the given objects equal, while null is also a valid
	 * value. In detail the check is: (o1 == null && o2 == null) ||
	 * (o1.equals(o2)).
	 * 
	 * @param o1
	 *            The first Object to compare.
	 * @param o2
	 *            The second Object to compare.
	 * @return true, if the given objects equal, while null is also a valid
	 *         value.
	 */
	public static boolean equalsWithNull(Object o1, Object o2) {
		if (o1 == null && o2 == null)
			return true;
		if (o1 == null || o2 == null)
			return false;
		return o1.equals(o2);
	}

	public static EClass[] getAllClasses(IProject project, ResourceSet rSet) {
		// FIXME: always unload to have our resources refreshed, this is highly non-performant
		EList<Resource> resources = rSet.getResources();
		for (Resource resource : resources) {
			resource.unload();
		}
		IFolder folder = project.getFolder("src");
		IFolder folderDiagrams = project.getFolder("src/diagrams");
		Collection<Diagram> diagrams = new ArrayList<Diagram>();
		Set<EClass> eClasses = new HashSet<EClass>();
		if (folder.exists()) {
			List<IResource> membersList = new ArrayList<IResource>();
			try {
				membersList.addAll(Arrays.asList(folder.members()));
				membersList.addAll(Arrays.asList(folderDiagrams.members()));
			} catch (CoreException e) {
				return new EClass[0];
			}
			for (IResource resource : membersList) {
				if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					if ("diagram".equals(file.getFileExtension()) || file.getName().equals("Predefined.data")) {
						Diagram diag = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, rSet);
						if (diag != null) {
							diagrams.add(diag);
						} else {
							URI uri = GraphitiUiInternal.getEmfService().getFileURI(file, rSet);
							Resource fileResource = rSet.getResource(uri, true);
							if (fileResource != null) {
								EList<EObject> contents = fileResource.getContents();
								for (EObject object : contents) {
									if (object instanceof EClass && !(object instanceof PictogramElement)) {
										eClasses.add((EClass) object);
									}
								}
							}
						}
					}
				}
			}
		}
		for (Diagram diagram : diagrams) {
			Resource resource = diagram.eResource();
			if (resource == null)
				return new EClass[0];
			EList<EObject> contents = resource.getContents();
			for (EObject object : contents) {
				if (object instanceof EClass && !(object instanceof PictogramElement)) {
					eClasses.add((EClass) object);
				}
			}
		}
		return eClasses.toArray(new EClass[eClasses.size()]);
	}
}
