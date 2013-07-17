/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2013 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 323155 - Check usage scenarios for DefaultPrintFeature and
 *            DefaultSaveImageFeature
 *    mwenz - Bug 370888 - API Access to export and print
 *    mwenz - Bug 413139 - Visibility of convertImageToBytes in DefaultSaveImageFeature
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.features;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ISaveImageFeature;
import org.eclipse.graphiti.features.context.IPrintContext;
import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.features.impl.AbstractSaveImageFeature;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ui.print.ExportDiagramDialog;
import org.eclipse.graphiti.ui.internal.util.ui.print.IDiagramsExporter;
import org.eclipse.graphiti.ui.saveasimage.ISaveAsImageConfiguration;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * The default feature implementation for saving a diagram as an image. This
 * feature is used to trigger saving from inside an open and initialized
 * {@link DiagramEditor}. It relies on an existing {@link GraphicalViewer}
 * showing the diagram to save.
 * 
 * @since 0.10 Has been moved from plug-in org.eclipse.graphiti package
 *        org.eclipse.graphiti.features
 */
public class DefaultSaveImageFeature extends AbstractSaveImageFeature implements ISaveImageFeature {

	/**
	 * Creates a new {@link DefaultSaveImageFeature}.
	 * 
	 * @param fp
	 *            The feature provider providing this feature
	 */
	public DefaultSaveImageFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Performs the save as image operation. The default implementation
	 * delegates to {@link #getGraphicalViewer(IPrintContext)} to retrieve the
	 * {@link GraphicalViewer} that already displays the diagram, queries for
	 * {@link ISaveAsImageConfiguration} to use by calling
	 * {@link #getSaveAsImageConfiguration(GraphicalViewer)}, queries for the
	 * filename by delegating to
	 * {@link #getFilename(GraphicalViewer, ISaveAsImageConfiguration)} and
	 * finally uses
	 * {@link #getSaveAsImageOperation(ISaveAsImageConfiguration, String)} to
	 * create an operation to perform the save as image operation. All those
	 * methods may be overridden to change the default behavior, so normally one
	 * would not need to override this method unless the complete sequence needs
	 * to changed or the save as image is performed in a completely different
	 * scenario.
	 * 
	 * @param context
	 *            Context information for saving as an image.
	 */
	public void save(ISaveImageContext context) {

		// Get viewer containing the diagram to print (by default the one
		// contained in the diagram editor that starts this feature
		GraphicalViewer viewer = getGraphicalViewer(context);

		// Configure and open dialog
		ISaveAsImageConfiguration saveAsImageConfiguration = getSaveAsImageConfiguration(viewer);
		if (saveAsImageConfiguration.configure() == Window.OK) {

			// Select filename with file-dialog
			String filename = getFilename(viewer, saveAsImageConfiguration);
			if (filename != null) {
				Shell shell = GraphitiUiInternal.getWorkbenchService().getShell();
				try {
					// Add extension to filename (if none exists)
					filename = addFileExtension(saveAsImageConfiguration.getFormattedFileExtension(), filename);

					// Create the save as image operation ...
					IRunnableWithProgress operation = getSaveAsImageOperation(saveAsImageConfiguration, filename);

					// ... and start save as image
					new ProgressMonitorDialog(shell).run(false, false, operation);
				} catch (InterruptedException e) {
					T.racer().warning("Save as image operation was cancelled by user"); //$NON-NLS-1$
				} catch (Exception e) {
					String message = "Cannot save image: "; //$NON-NLS-1$
					MessageDialog.openError(shell, "Cannot save image", message + e.getMessage()); //$NON-NLS-1$
					T.racer().error(message, e);
				}
			}
		}
	}

	/**
	 * Must return a {@link GraphicalViewer} that contains the diagram to be
	 * saved as an image. The default implementation returns the viewer of the
	 * {@link DiagramEditor} that started this save as image feature; this is
	 * the one associated to the feature provider of the currently opened
	 * diagram, see {@link #getDiagramEditor()}.
	 * 
	 * @param context
	 *            Context information for saving.
	 * @return the viewer holding the diagram to save.
	 */
	protected GraphicalViewer getGraphicalViewer(ISaveImageContext context) {
		IDiagramContainer diagramContainer = getDiagramBehavior().getDiagramContainer();
		if (diagramContainer instanceof IAdaptable) {
			return (GraphicalViewer) ((IAdaptable) diagramContainer).getAdapter(GraphicalViewer.class);
		} else {
			return null;
		}
	}

	/**
	 * Called to create a configuration object for the save as image operation
	 * that defines what to save and in which format, zoom level etc.. The
	 * default implementation returns the standard Graphiti dialog used for save
	 * as image that allows the user to define the standard Graphiti settings.
	 * 
	 * @param viewer
	 *            The {@link GraphicalViewer} displaying the diagram to print
	 * @return A newly created dialog that implements the
	 *         {@link ISaveAsImageConfiguration} interface used in the save as
	 *         image job.
	 */
	protected ISaveAsImageConfiguration getSaveAsImageConfiguration(GraphicalViewer viewer) {
		Shell shell = GraphitiUiInternal.getWorkbenchService().getShell();
		ISaveAsImageConfiguration saveAsImageDialog = new ExportDiagramDialog(shell, viewer);

		// Add exporters
		saveAsImageDialog.addExporters(getDiagramExporters());
		return saveAsImageDialog;
	}

	/**
	 * Must return the filename under which the image will be saved. The
	 * filename can (and shall be) without an extension as this will be added by
	 * a separate (outside) call to {@link #addFileExtension(String, String)}.
	 * The default implementation brings up a standard Eclipse file selection
	 * dialog in save mode. The dialog is configured to select between the
	 * allowed extensions for images (standard one plus the ones the registered
	 * Graphiti image exporters allow).
	 * 
	 * @param viewer
	 *            The {@link GraphicalViewer} displaying the diagram to print
	 * @param saveAsImageDialog
	 *            The save as image configurations as defined by
	 *            {@link #getSaveAsImageConfiguration(GraphicalViewer)}.
	 * @return A string containg the absolute path of the selected file, or null
	 *         if the dialog was cancelled or an error occurred.
	 */
	protected String getFilename(GraphicalViewer viewer, ISaveAsImageConfiguration saveAsImageConfiguration) {
		Shell shell = GraphitiUiInternal.getWorkbenchService().getShell();
		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		String fileExtensions[] = new String[] { "*." + saveAsImageConfiguration.getFormattedFileExtension() }; //$NON-NLS-1$
		fileDialog.setFilterExtensions(fileExtensions);
		String name = ((Diagram) viewer.getContents().getModel()).getName();
		fileDialog.setFileName(name);
		String filename = fileDialog.open();
		return filename;
	}

	/**
	 * Adds the given file extension to the given filename.
	 * 
	 * @param extension
	 *            A string holding the extension.
	 * @param filename
	 *            A string holding the filename.
	 * @return A string holding the filename plus the extension.
	 */
	protected String addFileExtension(String extension, String filename) {
		IPath path = new Path(filename);
		if (path.getFileExtension() == null) {
			filename = filename + "." + extension; //$NON-NLS-1$
		}
		return filename;
	}

	/**
	 * Called to create the operation that is actually used for executing the
	 * save as image functionality. The default implementation returns the
	 * Graphiti default save as image operation that should be sufficient for
	 * almost all use cases.
	 * <p>
	 * This method delegates to
	 * {@link #getSaveAsImageOperationForStandardExporter(ISaveAsImageConfiguration, String)}
	 * to perform the save as image for the standard formats like GIF, JPG, BMP
	 * etc. and to
	 * {@link #getSaveAsImageOperationForNonStandardExporter(ISaveAsImageConfiguration, String)}
	 * for the non standard exporters (registered via the Graphiti export image
	 * extension point) like SVG.
	 * 
	 * @param saveAsImageConfiguration
	 *            The {@link ISaveAsImageConfiguration} instance that was used
	 *            to configure this save as image operation. In the default
	 *            implementation this is the dialog to use for selecting the
	 *            image format, zoom level etc.
	 * @param filename
	 *            The filename to use for saving the image
	 * @return The operation that will be used to actually perform the save as
	 *         image.
	 */
	protected IRunnableWithProgress getSaveAsImageOperation(final ISaveAsImageConfiguration saveAsImageConfiguration,
			final String filename) {
		IRunnableWithProgress operation = null;

		String imageExtension = saveAsImageConfiguration.getFileExtension();
		if (getDiagramExporters().containsKey(imageExtension)) {
			// If the exporter is non-standard, i.e. registered via
			// extension point, we need to call the registered
			// exporter
			operation = getSaveAsImageOperationForNonStandardExporter(saveAsImageConfiguration, filename);
		} else {
			// Handle internal image format
			operation = getSaveAsImageOperationForStandardExporter(saveAsImageConfiguration, filename);
		}
		return operation;
	}

	/**
	 * Called to create the operation that is actually used for executing the
	 * save as image functionality for standard formats. The default
	 * implementation returns the Graphiti default save as image operation that
	 * should be sufficient for almost all use cases.
	 * 
	 * @param saveAsImageConfiguration
	 *            The {@link ISaveAsImageConfiguration} instance that was used
	 *            to configure this save as image operation. In the default
	 *            implementation this is the dialog to use for selecting the
	 *            image format, zoom level etc.
	 * @param filename
	 *            The filename to use for saving the image
	 * @return The operation that will be used to actually perform the save as
	 *         image.
	 */
	protected IRunnableWithProgress getSaveAsImageOperationForNonStandardExporter(
			final ISaveAsImageConfiguration saveAsImageConfiguration, final String filename) {

		String imageExtension = saveAsImageConfiguration.getFileExtension();
		final IDiagramsExporter exporter = ExtensionManager.getSingleton().getDiagramExporterForType(imageExtension);
		Assert.isNotNull(exporter);
		IRunnableWithProgress operation = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					exporter.export(saveAsImageConfiguration.getScaledImage(), saveAsImageConfiguration.getFigure(),
							filename, saveAsImageConfiguration.getImageScaleFactor());
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		return operation;
	}

	/**
	 * Called to create the operation that is actually used for executing the
	 * save as image functionality for non-standard formats. The default
	 * implementation returns the Graphiti default save as image operation that
	 * should be sufficient for almost all use cases.
	 * 
	 * @param saveAsImageConfiguration
	 *            The {@link ISaveAsImageConfiguration} instance that was used
	 *            to configure this save as image operation. In the default
	 *            implementation this is the dialog to use for selecting the
	 *            image format, zoom level etc.
	 * @param filename
	 *            The filename to use for saving the image
	 * @return The operation that will be used to actually perform the save as
	 *         image.
	 */
	protected IRunnableWithProgress getSaveAsImageOperationForStandardExporter(
			final ISaveAsImageConfiguration saveAsImageConfiguration, final String filename) {

		int imageFormat = saveAsImageConfiguration.getImageFormat();
		final byte imageBytes[] = GraphitiUi.getImageService().convertImageToBytes(
				saveAsImageConfiguration.getScaledImage(), imageFormat);
		IRunnableWithProgress operation = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				FileOutputStream outputStream = null;
				try {
					outputStream = new FileOutputStream(filename);
					outputStream.write(imageBytes);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					try {
						outputStream.close();
					} catch (Exception x) {
						T.racer().error("close output stream failed", x); //$NON-NLS-1$
					}
				}
			}
		};
		return operation;
	}

	/**
	 * Returns all available Graphiti diagram exporters that are registered at
	 * the according Graphiti extension point. Note that the standard exporters
	 * like GIF, JPG, BMP are not part of the returned ones.
	 * 
	 * @return A {@link Map} holding all exporters.
	 */
	protected Map<String, Boolean> getDiagramExporters() {
		Map<String, Boolean> diagramExporterTypes = ExtensionManager.getSingleton().getDiagramExporterTypes();
		return diagramExporterTypes;
	}
}
