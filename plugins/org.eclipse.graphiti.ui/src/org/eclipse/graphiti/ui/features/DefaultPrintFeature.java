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
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.features;

import org.eclipse.draw2d.PrintFigureOperation;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPrintFeature;
import org.eclipse.graphiti.features.context.IPrintContext;
import org.eclipse.graphiti.features.impl.AbstractPrintFeature;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ui.print.PrintFigureDialog;
import org.eclipse.graphiti.ui.internal.util.ui.print.PrintFigureScaleableOperation;
import org.eclipse.graphiti.ui.print.IPrintConfiguration;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Shell;

/**
 * The default feature implementation for printing a diagram. This feature is
 * used to trigger printing from inside an open and initialized
 * {@link DiagramEditor}. It relies on an existing {@link GraphicalViewer}
 * showing the diagram to print.
 * 
 * @since 0.10 Has been moved from plug-in org.eclipse.graphiti package
 *        org.eclipse.graphiti.features
 */
public class DefaultPrintFeature extends AbstractPrintFeature implements IPrintFeature {

	/**
	 * Creates a new {@link DefaultPrintFeature}.
	 * 
	 * @param fp
	 *            The feature provider providing this feature
	 */
	public DefaultPrintFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Performs the print operation. The default implementation delegates to
	 * {@link #getGraphicalViewer(IPrintContext)} to retrieve the
	 * {@link GraphicalViewer} that already displays the diagram, queries for
	 * {@link PrinterData} to use by calling
	 * {@link #getPrinterData(IPrintContext)} and finally uses
	 * {@link #getPrintOperation(Printer, IPrintConfiguration)} to create an
	 * operation to perform the printing. All those methods may be overridden to
	 * change the default behavior, so normally one would not need to override
	 * this method unless the complete sequence needs to changed or the printing
	 * is performed in a completely different scenario.
	 * 
	 * @param context
	 *            Context information for printing.
	 */
	public void print(IPrintContext context) {

		// Get viewer containing the diagram to print (by default the one
		// contained in the diagram editor that starts this feature
		GraphicalViewer viewer = getGraphicalViewer(context);

		// Create PrinterData and Printer
		PrinterData printerData = getPrinterData(context);
		Printer printer = new Printer(printerData);

		// Create the PrintFigureDialog and open it
		IPrintConfiguration printConfiguration = getPrintConfiguration(viewer, printer);
		if (printConfiguration.configure() == IPrintConfiguration.OK) {
			// Create the print operation ...
			PrintFigureOperation op = getPrintOperation(printConfiguration);
			// ... and start the printing
			op.run(getName());
			printConfiguration.cleanUp();
		}
	}

	/**
	 * Must return a {@link GraphicalViewer} that contains the diagram to be
	 * printed. The default implementation returns the viewer of the
	 * {@link DiagramEditor} that started this print feature; this is the one
	 * associated to the feature provider of the currently opened diagram, see
	 * {@link #getDiagramEditor()}.
	 * 
	 * @param context
	 *            Context information for printing.
	 * @return the viewer holding the diagram to print.
	 */
	protected GraphicalViewer getGraphicalViewer(IPrintContext context) {
		DiagramEditor diagramEditor = (DiagramEditor) getDiagramBehavior().getDiagramContainer();
		return (GraphicalViewer) diagramEditor.getAdapter(GraphicalViewer.class);
	}

	/**
	 * Creates the {@link PrinterData} information used for this print feature.
	 * The default implementation simply gets the info for the default printer
	 * of the system or the first printer in the list of available printers in
	 * case no default is defined.
	 * 
	 * @param context
	 *            Context information for printing.
	 * @return The printer data to use for this print feature.
	 */
	protected PrinterData getPrinterData(IPrintContext context) {
		PrinterData printerData = Printer.getDefaultPrinterData();
		if (printerData == null || (printerData.name == null && printerData.driver == null)) {
			PrinterData[] printerDatas = Printer.getPrinterList();
			if (printerDatas != null && printerDatas.length > 0) {
				printerData = printerDatas[0];
			}
		}
		return printerData;
	}

	/**
	 * Called to create a configuration object for the printing that defines
	 * what to print on which printer and how. The default implementation
	 * returns the standard Graphiti dialog used for printing that allows the
	 * user to define which printer to use, which figure to print and various
	 * other print settings.
	 * 
	 * @param viewer
	 *            The viewer displaying the diagram to print
	 * @param printer
	 *            The printer to use as default
	 * @return A newly created dialog that implements the
	 *         {@link IPrintConfiguration} interface used in the print job.
	 */
	protected IPrintConfiguration getPrintConfiguration(GraphicalViewer viewer, Printer printer) {
		Shell shell = GraphitiUiInternal.getWorkbenchService().getShell();
		IPrintConfiguration printImageDialog = new PrintFigureDialog(shell, viewer, printer);
		return printImageDialog;
	}

	/**
	 * Called to create the operation that is actually used for printing a
	 * diagram. The default implementation returns the Graphiti default print
	 * operation that should be sufficient for almost all use cases.
	 * 
	 * @param printConfiguration
	 *            The {@link IPrintConfiguration} instance that was used to
	 *            configure this print operation. In the default implementation
	 *            this is the dialog to use for selecting the printer and other
	 *            settings.
	 * @return The operation that will be used to actually perform the printing.
	 */
	protected PrintFigureOperation getPrintOperation(IPrintConfiguration printConfiguration) {
		PrintFigureOperation op = new PrintFigureScaleableOperation(printConfiguration);
		return op;
	}
}
