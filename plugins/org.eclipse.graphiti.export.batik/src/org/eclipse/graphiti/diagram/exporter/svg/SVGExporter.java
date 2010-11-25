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
package org.eclipse.graphiti.diagram.exporter.svg;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.ui.internal.util.ui.print.IDiagramsExporter;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 * Extending the Graphiti framework with a export to SVG.
 */
public class SVGExporter implements IDiagramsExporter {

	@Override
	public void export(Image im, IFigure figure, String fileName) throws Exception {
		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg"; //$NON-NLS-1$
		Document document = domImpl.createDocument(svgNS, "svg", null); //$NON-NLS-1$
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
		ctx.setEmbeddedFontsOn(true);

		// Create an instance of the SVG Generator and paint the
		// figure.
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
		Rectangle bounds = new Rectangle(0, 0, figure.getBounds().width, figure.getBounds().height);
		GraphicsToGraphics2DAdaptor graphicsAdaptor = new GraphicsToGraphics2DAdaptor(svgGenerator, bounds);
		figure.paint(graphicsAdaptor);

		// Finally, stream out SVG to the standard output using
		// UTF-8 encoding.
		boolean useCSS = false;
		Writer out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"); //$NON-NLS-1$
		// svgGenerator.setRenderingHint(SVG_WIDTH_ATTRIBUTE
		svgGenerator.stream(out, useCSS);
	}

}
