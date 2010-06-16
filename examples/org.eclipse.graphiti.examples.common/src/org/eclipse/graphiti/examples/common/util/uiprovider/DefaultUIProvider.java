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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.examples.common.util.Util;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;


/**
 * A default implementation of the interface IUIProvider.
 */
public class DefaultUIProvider implements IUIProvider {

	/**
	 * The Constant ARRAY_TEXT_START.
	 */
	public static final String ARRAY_TEXT_START = "["; //$NON-NLS-1$

	/**
	 * The Constant ARRAY_TEXT_SEPARATOR.
	 */
	public static final String ARRAY_TEXT_SEPARATOR = "; "; //$NON-NLS-1$

	/**
	 * The Constant ARRAY_TEXT_END.
	 */
	public static final String ARRAY_TEXT_END = "]"; //$NON-NLS-1$

	/**
	 * The Constant TUPLE_TEXT_START.
	 */
	public static final String TUPLE_TEXT_START = "("; //$NON-NLS-1$

	/**
	 * The Constant TUPLE_TEXT_SEPARATOR.
	 */
	public static final String TUPLE_TEXT_SEPARATOR = ", "; //$NON-NLS-1$

	/**
	 * The Constant TUPLE_TEXT_END.
	 */
	public static final String TUPLE_TEXT_END = ")"; //$NON-NLS-1$

	/**
	 * The Constant FORMAT_JAVA_SQL_DATE.
	 */
	public static final DateFormat FORMAT_JAVA_SQL_DATE = DateFormat.getDateInstance(DateFormat.SHORT);

	/**
	 * The Constant FORMAT_JAVA_UTIL_DATE.
	 */
	public static final DateFormat FORMAT_JAVA_UTIL_DATE = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	/**
	 * The Constant FORMAT_JAVA_SQL_TIME.
	 */
	public static final DateFormat FORMAT_JAVA_SQL_TIME = DateFormat.getTimeInstance(DateFormat.SHORT);

	private Map<TwoObjectsContainer, Image> _imageRegistry = new HashMap<TwoObjectsContainer, Image>(); // ImageDescriptor

	/**
	 * Returns the ImageRegistry, which is used by this IUIProvider.
	 * 
	 * @return The ImageRegistry, which is used by this IUIProvider.
	 */
	protected Map<TwoObjectsContainer, Image> getImageRegistry() {
		return _imageRegistry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.ide.eclipse.gf.config.provider.IUIProvider#dispose()
	 */
	public void dispose() {
		if (_imageRegistry != null) {
			for (Iterator<Image> iter = _imageRegistry.values().iterator(); iter.hasNext();) {
				Image image = iter.next();
				image.dispose();
			}
		}
		_imageRegistry = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.ide.eclipse.gf.config.provider.IUIProvider#getImage(java.lang.Object, java.lang.Object)
	 */
	public Image getImage(Object element, Object imageType) {
		if (element != null) {
			ImageDescriptor descriptor = getImageDescriptor(element, imageType);
			TwoObjectsContainer container = new TwoObjectsContainer(descriptor, imageType);
			if (descriptor != null) {
				Image image = getImageRegistry().get(container);
				if (image == null) {
					image = descriptor.createImage();
					getImageRegistry().put(container, image);
				}
				return image;
			}
		}
		return null;
	}

	// =============== overwrite to specify texts and images ==================

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.ide.eclipse.gf.config.provider.IUIProvider#getText(java.lang.Object, java.lang.Object)
	 */
	public String getText(Object element, Object textType) {
		if (element == null)
			return ""; //$NON-NLS-1$

		// Collection: convert to array and call recursively
		if (element instanceof Collection) {
			Collection collection = (Collection) element;
			return getText(collection.toArray(), textType);
		}

		// Array: build comma-separated List with recursive calls
		if (element.getClass().isArray()) {
			StringBuffer result = new StringBuffer();
			result.append(ARRAY_TEXT_START);
			boolean afterFirstElement = false;
			for (int i = 0; i < Array.getLength(element); i++) {
				if (afterFirstElement)
					result.append(ARRAY_TEXT_SEPARATOR);
				Object next = Array.get(element, i); // this automatically
				// converts primitive
				// types to Object types
				result.append(getText(next, textType));
				afterFirstElement = true;
			}
			result.append(ARRAY_TEXT_END);
			return result.toString();
		}

		// Date/Time: use DateFormatter
		if (element instanceof java.sql.Date)
			return FORMAT_JAVA_SQL_DATE.format((java.sql.Date) element);
		if (element instanceof java.sql.Time)
			return FORMAT_JAVA_SQL_TIME.format((java.sql.Time) element);
		if (element instanceof java.util.Date) // java.uti.Date must be the
			// last, because the java.sql.*
			// extend this class
			return FORMAT_JAVA_UTIL_DATE.format((java.util.Date) element);

		// Rectangle/Point
		if (element instanceof Rectangle) {
			Rectangle casted = (Rectangle) element;
			StringBuffer result = new StringBuffer();
			result.append(TUPLE_TEXT_START);
			result.append(casted.x).append(TUPLE_TEXT_SEPARATOR).append(casted.y).append(TUPLE_TEXT_SEPARATOR).append(casted.width).append(
					TUPLE_TEXT_SEPARATOR).append(casted.height);
			result.append(TUPLE_TEXT_END);
			return result.toString();
		}
		if (element instanceof Point) {
			Point casted = (Point) element;
			StringBuffer result = new StringBuffer();
			result.append(TUPLE_TEXT_START);
			result.append(casted.x).append(TUPLE_TEXT_SEPARATOR).append(casted.y);
			result.append(TUPLE_TEXT_END);
			return result.toString();
		}

		// default
		return element.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.ide.eclipse.gf.config.provider.IUIProvider#getObject(java.lang.String, java.lang.Object, java.lang.Class)
	 */
	public Object getObject(String elementText, Object textType, Class elementClass) throws Exception {
		if (elementText == null)
			return null;

		// Collection
		if (Collection.class.isAssignableFrom(elementClass)) {
			Object elementArray[] = (Object[]) getObject(elementText, textType, String[].class);
			Object result = Arrays.asList(elementArray);
			return result;
		}

		// Array
		if (elementClass.isArray()) {
			// parsing the array-string is done fuzzy, to allow an easier input
			// into text-fields
			if (elementText.startsWith(ARRAY_TEXT_START))
				elementText = elementText.substring(ARRAY_TEXT_START.length());
			if (elementText.endsWith(ARRAY_TEXT_END))
				elementText = elementText.substring(0, elementText.length() - ARRAY_TEXT_END.length());

			StringTokenizer tokenizer = new StringTokenizer(elementText, ARRAY_TEXT_SEPARATOR.trim());
			ArrayList<String> elementItemTexts = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				token = token.trim();
				if (token.length() != 0)
					elementItemTexts.add(token);
			}

			// now that the elementItemTexts are found, they must be converted
			// to an array of the correct class
			Class targetClass = elementClass.getComponentType();
			Object result = Array.newInstance(targetClass, elementItemTexts.size());

			int pos = 0;
			for (Iterator<String> iter = elementItemTexts.iterator(); iter.hasNext();) {
				String elementItemText = iter.next();
				Object elementItem = getObject(elementItemText, textType, targetClass);
				Array.set(result, pos++, elementItem);
			}
			return result;
		}

		// Date/Time
		if (java.sql.Date.class.isAssignableFrom(elementClass))
			return FORMAT_JAVA_SQL_DATE.parse(elementText);
		if (java.sql.Time.class.isAssignableFrom(elementClass))
			return FORMAT_JAVA_SQL_TIME.parse(elementText);
		if (java.util.Date.class.isAssignableFrom(elementClass)) // java.uti.Date
			// must be
			// the last,
			// because
			// the
			// java.sql.*
			// extend
			// this
			// class
			return FORMAT_JAVA_UTIL_DATE.parse(elementText);

		// Tuples (Rectangle/Point)
		if (Rectangle.class.isAssignableFrom(elementClass) || Point.class.isAssignableFrom(elementClass)) {
			// parsing the tuple-string is done fuzzy, to allow an easier input
			// into text-fields
			if (elementText.startsWith(TUPLE_TEXT_START))
				elementText = elementText.substring(TUPLE_TEXT_START.length());
			if (elementText.endsWith(TUPLE_TEXT_END))
				elementText = elementText.substring(0, elementText.length() - TUPLE_TEXT_END.length());

			StringTokenizer tokenizer = new StringTokenizer(elementText, TUPLE_TEXT_SEPARATOR.trim());
			ArrayList<String> elementItemTexts = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				token = token.trim();
				if (token.length() != 0)
					elementItemTexts.add(token);
			}

			// now that the elementItemTexts are found, they must be converted
			// to an instance of the correct class
			if (Rectangle.class.isAssignableFrom(elementClass)) {
				Rectangle result = new Rectangle();
				result.x = Integer.parseInt(elementItemTexts.get(0));
				result.y = Integer.parseInt(elementItemTexts.get(1));
				result.width = Integer.parseInt(elementItemTexts.get(2));
				result.height = Integer.parseInt(elementItemTexts.get(3));
				return result;
			}
			if (Point.class.isAssignableFrom(elementClass)) {
				Point result = new Point();
				result.x = Integer.parseInt(elementItemTexts.get(0));
				result.y = Integer.parseInt(elementItemTexts.get(1));
				return result;
			}
		}

		// default
		Class nonPrimitiveElementClass = Util.getNonPrimitiveClass(elementClass);
		Constructor constructor = nonPrimitiveElementClass.getConstructor(new Class[] { String.class });
		Object result = constructor.newInstance(elementText);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.ide.eclipse.gf.config.provider.IUIProvider#getImageDescriptor(java.lang.Object, java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object element, Object imageType) {
		return null;
	}
}
