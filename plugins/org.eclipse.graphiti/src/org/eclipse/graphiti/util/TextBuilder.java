/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2014, 2014 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eindhoven University of Technology (Albert Hofkamp) - Bug 440796 - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle;

/**
 * Class to construct {@link TextStyleRegion}s while adding text.
 * 
 * @since 0.12
 */
public class TextBuilder {

	/** Constructed text string. */
	private StringBuilder text = new StringBuilder();

	/** Number of code points in {@link #text}. */
	private int length = 0;

	/** Regions created in the collected text. */
	private List<TextStyleRegion> regions = new ArrayList<TextStyleRegion>();

	/** Partly finished text styles. */
	private Map<String, TextBuilderRegion> styles = new LinkedHashMap<String, TextBuilderRegion>();

	/**
	 * Add text to the builder.
	 * 
	 * @param text
	 *            Text to append.
	 */
	public void add(String text) {
		length += text.length();
		this.text.append(text);
	}

	/**
	 * Get current length of the collected text.
	 * 
	 * @return Length of the text collected so far (number of code points).
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Is any text added yet?
	 * 
	 * @return {@code true} if no text added yet, else {@code false}.
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Get the collected text.
	 * 
	 * @return The concatenated strings previously given through {@link #add}.
	 */
	@Override
	public String toString() {
		return text.toString();
	}

	/**
	 * Get the regions created and finished for the text.
	 * 
	 * @return Regions of the text.
	 */
	public List<TextStyleRegion> getRegions() {
		return regions;
	}

	/**
	 * Start a new region in the text. Returns the created region for setting
	 * the style of the new region.
	 * 
	 * @param name
	 *            Name of the region.
	 * @return The created region.
	 */
	public TextBuilderRegion startRegion(String name) {
		TextBuilderRegion tbr = new TextBuilderRegion().setStart(length);
		styles.put(name, tbr);
		return tbr;
	}

	/**
	 * Get a previously created and not yet finished region.
	 * 
	 * @param name
	 *            Name of the region to retrieve.
	 * @return The queried region if it exists, else {@code null}.
	 */
	public TextBuilderRegion getRegion(String name) {
		return styles.get(name);
	}

	/**
	 * Finish a region.
	 * 
	 * @param name
	 *            Name of the region to finish.
	 */
	public void finishRegion(String name) {
		TextBuilderRegion region = styles.get(name);
		styles.remove(name);

		if (region != null && region.getStyle() != null) {
			TextStyleRegion styleRegion;
			styleRegion = StylesFactory.eINSTANCE.createTextStyleRegion();
			styleRegion.setStart(region.getStart());
			styleRegion.setStyle(region.getStyle());
			styleRegion.setEnd(length);
			regions.add(styleRegion);
		}
	}

	/**
	 * Drop a region that was created but not yet finished.
	 * 
	 * @param name
	 *            Name of the region to forget.
	 */
	public void forgetRegion(String name) {
		styles.remove(name);
	}

	/**
	 * Inner class for storing the partial region styles.
	 */
	public static class TextBuilderRegion {
		/** Current style of the region, {@code null} means default style. */
		private TextStyle style = null;

		/** Start offset of the region in the text. */
		private int start = 0;

		/** Constructor of the {@link TextBuilderRegion} class. */
		public TextBuilderRegion() {
			style = null;
		}

		/**
		 * Get start offset of the region in the text.
		 * 
		 * @return The start offset of the region.
		 */
		public int getStart() {
			return start;
		}

		/**
		 * Get the text style of the region in the text.
		 * 
		 * @return The text style, or {@code null} for the default style.
		 */
		public TextStyle getStyle() {
			return style;
		}

		/** Ensure a non-default style is available for modification. */
		private void ensureStyle() {
			if (style == null)
				style = StylesFactory.eINSTANCE.createTextStyle();
		}

		/**
		 * Assign a style to the region of text.
		 * 
		 * @param style
		 *            Style to assign.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setStyle(TextStyle style) {
			this.style = style;
			return this;
		}

		/**
		 * Assign a start to the partial region style.
		 * 
		 * @param start
		 *            New start offset of the region in the text.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setStart(int start) {
			this.start = start;
			return this;
		}

		/**
		 * Set the underline style for the region.
		 * 
		 * @param uStyle
		 *            Underline style to assign. {@code null} means disable
		 *            underline.
		 * @param col
		 *            Color of the underline, {@code null} means skip setting
		 *            the color.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setUnderline(UnderlineStyle uStyle, Color col) {
			ensureStyle();
			if (uStyle == null) {
				style.setUnderline(false);
			} else {
				style.setUnderline(true);
				style.setUnderlineStyle(uStyle);
			}
			setUnderline(col);
			return this;
		}

		/**
		 * Set the underline color for the region.
		 * 
		 * @param col
		 *            Color of the underline, {@code null} means skip setting
		 *            the color.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setUnderline(Color col) {
			ensureStyle();
			if (col != null && style.isUnderline())
				style.setUnderlineColor(col);
			return this;
		}

		/**
		 * Set strikeout for the region.
		 * 
		 * @param value
		 *            Enable or disable strikeout.
		 * @param col
		 *            Color of the strikeout, if enabled. {@code null} means
		 *            skip setting of the color.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setStrikeOut(boolean value, Color col) {
			ensureStyle();
			style.setStrikeout(value);
			if (value && col != null)
				style.setStrikeoutColor(col);
			return this;
		}

		/**
		 * Set the foreground color of the text for the region.
		 * 
		 * @param col
		 *            Foreground color to set. {@code null} means skip setting
		 *            of the color.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setForeground(Color col) {
			ensureStyle();
			if (col != null)
				style.setForeground(col);
			return this;
		}

		/**
		 * Set the background color of the text for the region.
		 * 
		 * @param col
		 *            Background color to set. {@code null} means skip setting
		 *            of the color.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setBackground(Color col) {
			ensureStyle();
			if (col != null)
				style.setBackground(col);
			return this;
		}

		/**
		 * Set the font for the region.
		 * 
		 * @param font
		 *            Font to set.
		 * @return The updated partial region style.
		 */
		public TextBuilderRegion setFont(Font font) {
			ensureStyle();
			style.setFont(font);
			return this;
		}
	}
}
