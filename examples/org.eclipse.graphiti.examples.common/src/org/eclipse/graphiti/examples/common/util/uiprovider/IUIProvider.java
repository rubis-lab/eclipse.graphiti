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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * Provides UI-specific informations to the model.
 */
public interface IUIProvider {

	/**
	 * The Constant TEXT_TYPE_DEFAULT.
	 */
	public static final String TEXT_TYPE_DEFAULT = null;

	/**
	 * The Constant TEXT_TYPE_TOOLTIP.
	 */
	public static final String TEXT_TYPE_TOOLTIP = "tooltip"; //$NON-NLS-1$

	/**
	 * The Constant TEXT_TYPE_NAME.
	 */
	public static final String TEXT_TYPE_NAME = "name"; //$NON-NLS-1$

	/**
	 * The Constant TEXT_TYPE_PROPERTYSHEET_CATEGORY.
	 */
	public static final String TEXT_TYPE_PROPERTYSHEET_CATEGORY = "ps-category"; //$NON-NLS-1$

	/**
	 * The Constant IMAGE_TYPE_DEFAULT.
	 */
	public static final String IMAGE_TYPE_DEFAULT = null;

	/**
	 * The Constant IMAGE_TYPE_LARGE.
	 */
	public static final String IMAGE_TYPE_LARGE = "large"; //$NON-NLS-1$

	/**
	 * Disposes this object and frees all resources. This object will be unusable afterwards.
	 */
	public void dispose();

	/**
	 * Returns the text for the given element. Returns "", if no text shall be displayed for the given element. Returns null, if this
	 * provider can not handle the given element.
	 * 
	 * @param element
	 *            The element, for which to return the text.
	 * @param textType
	 *            The type of the text. Can be null, to indicate the default-type. Possible types are for example TEXT_TYPE_DEFAULT,
	 *            TEXT_TYPE_TOOLTIP and TEXT_TYPE_DESCRIPTION. It is also possible to use user-defined types.
	 * 
	 * @return The text for the given element.
	 */
	public String getText(Object element, Object textType);

	/**
	 * Returns the element for the given text. This is the opposite method of getText().
	 * 
	 * @param elementText
	 *            The text, which to convert to an Object.
	 * @param textType
	 *            The type of the text. Can be null, to indicate the default-type. Possible types are for example TEXT_TYPE_DEFAULT,
	 *            TEXT_TYPE_TOOLTIP and TEXT_TYPE_DESCRIPTION. It is also possible to use user-defined types.
	 * @param elementClass
	 *            The wanted class of the Object.
	 * 
	 * @return The element for the given text.
	 * 
	 * @throws Exception
	 *             If the text is not convertable to the specified class.
	 */
	public Object getObject(String elementText, Object textType, Class elementClass) throws Exception;

	/**
	 * Returns the image for the given element. Returns null, if no image shall be displayed for the given element, or if the element can
	 * not be handled by this provider. Typically this method 'forwards' the call to getImageDescriptor(element), so that both methods
	 * return the same informations (just in another format).
	 * 
	 * @param element
	 *            The element, for which to return the image.
	 * @param imageType
	 *            The type of the image. Can be null, to indicate the default-type. Possible types are for example IMAGE_TYPE_DEFAULT and
	 *            IMAGE_TYPE_LARGE. It is also possible to use user-defined types.
	 * 
	 * @return The image for the given element.
	 */
	public Image getImage(Object element, Object imageType);

	/**
	 * Returns the image-descriptor for the given element. Returns null, if no image shall be displayed for the given element, or if the
	 * element can not be handled by this provider.
	 * 
	 * @param imageType
	 *            The type of the image. Can be null, to indicate the default-type. Possible types are for example IMAGE_TYPE_DEFAULT and
	 *            IMAGE_TYPE_LARGE. It is also possible to use user-defined types.
	 * @param element
	 *            The element, for which to return the image-descriptor.
	 * 
	 * @return The image-descriptor for the given element.
	 */
	public ImageDescriptor getImageDescriptor(Object element, Object imageType);
}