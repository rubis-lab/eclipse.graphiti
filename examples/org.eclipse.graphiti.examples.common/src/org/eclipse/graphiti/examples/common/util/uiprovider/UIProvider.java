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
package org.eclipse.graphiti.examples.common.util.uiprovider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * A concrete implementation of the interface IUIProvider, which works on a JMI
 * Model.
 */
public class UIProvider extends DefaultUIProvider {

	// =============== overwritten to specify texts and images ================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.ide.eclipse.gf.config.provider.IUIProvider#getText(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public String getText(Object element, Object textType) {
		// get inner objects from 'own' wrappers
		//		if (element instanceof PropertyForAttribute) {
		//			PropertyForAttribute casted = (PropertyForAttribute) element;
		//			element = casted.getValue();
		//		}
		//		if (element instanceof PropertyTypeForAttribute) {
		//			PropertyTypeForAttribute casted = (PropertyTypeForAttribute) element;
		//			element = casted.getAttribute();
		//		}

		//		// special handlings
		//		if (element instanceof Attribute) {
		//			Attribute casted = (Attribute) element;
		//			if (Util.equalsWithNull(textType, TEXT_TYPE_DEFAULT) || Util.equalsWithNull(textType, TEXT_TYPE_NAME))
		//				return casted.getName();
		//			if (Util.equalsWithNull(textType, TEXT_TYPE_TOOLTIP))
		//				return "The attribute" + " " + casted.getName();
		//			if (Util.equalsWithNull(textType, TEXT_TYPE_PROPERTYSHEET_CATEGORY)) {
		//				if (casted.refOutermostComposite().refGetValue("name").equals("pictograms")) {
		//					return "Graphics Extension";
		//				} else {
		//					return "Business Model";
		//				}
		//			}
		//		}

		if (element instanceof EObject) {
			EObject casted = (EObject) element;
			if (GraphitiInternal.getEmfService().isObjectAlive(casted)) {
				final EObject eObject = casted.eClass();
				if (GraphitiInternal.getEmfService().isObjectAlive(eObject) && eObject instanceof EClass) {
					EClass eClass = (EClass) eObject;
					String className = eClass.getName();
					return className + " (" + casted.toString() + ")";
				}
			}
		}

		return super.getText(element, textType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.ide.eclipse.gf.config.provider.IUIProvider#getImageDescriptor
	 * (java.lang.Object, java.lang.Object)
	 */
	@Override
	public ImageDescriptor getImageDescriptor(Object element, Object imageType) {
		return super.getImageDescriptor(element, imageType);
	}
}
