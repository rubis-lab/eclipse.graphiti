package org.eclipse.graphiti.ui.saveasimage;

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Image;

public interface ISaveAsImageConfiguration {

	public void addExporters(Map<String, Boolean> diagramExporterTypes);

	public int configure();

	public String getFormattedFileExtension();
	
	public Image getScaledImage();

	public String getFileExtension();

	public int getImageFormat();

	public IFigure getFigure();

	public double getImageScaleFactor();
}
