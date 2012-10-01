/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 342869 - Image doesn't scale the contained SWT Image on resize
 *    mwenz - Bug 358255 - Add Border/Background decorators
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.testtool.sketch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.IName;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.examples.common.IExampleImageConstants;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.mm.algorithms.styles.UnderlineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ISelectionInfo;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.tb.SelectionInfoImpl;
import org.eclipse.graphiti.testtool.sketch.features.ChangeAlignmentFeature;
import org.eclipse.graphiti.testtool.sketch.features.ClearDecoratorsFeature;
import org.eclipse.graphiti.testtool.sketch.features.CornerDimensionFeature;
import org.eclipse.graphiti.testtool.sketch.features.DisplayDecoratorFeature;
import org.eclipse.graphiti.testtool.sketch.features.LineStyleFeature;
import org.eclipse.graphiti.testtool.sketch.features.LineWidthFeature;
import org.eclipse.graphiti.testtool.sketch.features.SetImageAttributesFeature;
import org.eclipse.graphiti.testtool.sketch.features.SketchFontFeature;
import org.eclipse.graphiti.testtool.sketch.features.TransparencyFeature;
import org.eclipse.graphiti.testtool.sketch.features.create.SketchCreateCanFigureFeature;
import org.eclipse.graphiti.testtool.sketch.features.style.SetStyleFeature;
import org.eclipse.graphiti.ui.editor.IEclipseImageDescriptor;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The Class SketchToolBehavior.
 */
public class SketchToolBehavior extends DefaultToolBehaviorProvider implements IName {

	private Dictionary<PictogramElement, List<IDecorator>> decorators = new Hashtable<PictogramElement, List<IDecorator>>();

	private class MyObjectCreationToolEntry extends ObjectCreationToolEntry implements IEclipseImageDescriptor {

		private final String imageFilePath;

		public MyObjectCreationToolEntry(String label, String description, ICreateFeature createFeature, String imageFilePath) {
			super(label, description, null, null, createFeature);
			this.imageFilePath = imageFilePath;
		}

		public ImageDescriptor getImageDescriptor() {
			return AbstractUIPlugin.imageDescriptorFromPlugin(SketchPlugin.getID(), imageFilePath);
		}
	}

	private class MyPaletteCompartmentEntry extends PaletteCompartmentEntry implements IEclipseImageDescriptor {

		private final String imageFilePath;

		public MyPaletteCompartmentEntry(String label, String imageFilePath) {
			super(label, null);
			this.imageFilePath = imageFilePath;
		}

		public ImageDescriptor getImageDescriptor() {
			return AbstractUIPlugin.imageDescriptorFromPlugin(SketchPlugin.getID(), imageFilePath);
		}
	}

	private static final String ICON_CAN_FIGURE = "icons/pid16.gif"; //$NON-NLS-1$

	private static final String ICON_CONNECTION_COMPARTMENT = "icons/connection16.gif"; //$NON-NLS-1$

	private static final double[] ZOOM_LEVELS = new double[] { 0.05, 0.1, 0.5, 1, 1.5, 2, 3, 4 };

	private static boolean TEST_SHOW_WARNING_DECORATORS = false;

	private final ISelectionInfo selectionInfo = new SelectionInfoImpl(IColorConstant.BLUE, IColorConstant.LIGHT_BLUE, IColorConstant.RED,
			LineStyle.DOT);
	{
		selectionInfo.setPrimarySelectionBackgroundColor(IColorConstant.LIGHT_BLUE);
		selectionInfo.setSecondarySelectionBackgroundColor(IColorConstant.LIGHT_ORANGE);
	}

	/**
	 * Instantiates a new sketch tool behaviour.
	 * 
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public SketchToolBehavior(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@SuppressWarnings("unused")
	private PictogramElement findParentToBeSelected(PictogramElement originalPe, PictogramElement[] oldSelection) {
		List<PictogramElement> oldSelectList = Arrays.asList(oldSelection);
		if (oldSelectList.contains(originalPe)) {
			return null;
		}

		if (originalPe instanceof Shape) {
			Shape shape = (Shape) originalPe;
			ContainerShape container = shape.getContainer();
			if (container instanceof Diagram || oldSelectList.contains(container)) {
				return shape;
			} else {
				return findParentToBeSelected(container, oldSelection);
			}
		}
		return null;
	}

	@Override
	public PictogramElement getSelection(PictogramElement originalPe, PictogramElement[] oldSelection) {
		if (SketchUtil.isLonelyLanePe(originalPe)) {
			if (originalPe instanceof ContainerShape) {
				ContainerShape cs = (ContainerShape) originalPe;
				return cs.getContainer();
			}
		}

		return super.getSelection(originalPe, oldSelection);
	}

	@Override
	public GraphicsAlgorithm getChopboxAnchorArea(PictogramElement pe) {
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
		IGaService gaService = Graphiti.getGaService();
		if (!gaService.isFilled(ga, true) && !gaService.isLineVisible(ga, true)) {
			return ga.getGraphicsAlgorithmChildren().get(0);
		} else
			return super.getChopboxAnchorArea(pe);
	}

	@Override
	public ISelectionInfo getSelectionInfoForConnection(Connection connection) {
		if (connection instanceof FreeFormConnection) {
			return selectionInfo;
		}
		return super.getSelectionInfoForConnection(connection);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		if (SketchUtil.isConnectionPoint(context.getPictogramElement())) {
			return null;
		}
		IContextButtonPadData ret = super.getContextButtonPad(context);
		PictogramElement pictogramElement = context.getPictogramElement();
		if (!SketchUtil.isLanePe(pictogramElement)) {
			List<IContextButtonEntry> specificContextButtons = ret.getDomainSpecificContextButtons();
			IFeatureProvider featureProvider = getFeatureProvider();
			CustomContext customContext = new CustomContext(new PictogramElement[] { pictogramElement });
			ICustomFeature[] customFeatures = featureProvider.getCustomFeatures(customContext);
			ContextButtonEntry lineWidthFeatureButton = null;
			ContextButtonEntry lineStyleFeatureButton = null;
			ContextButtonEntry transparencyFeatureButton = null;
			for (int i = 0; i < customFeatures.length; i++) {
				ICustomFeature customFeature = customFeatures[i];
				if (customFeature instanceof SketchFontFeature) {
					// example for a collapse button
					IContextButtonEntry collapseButton = ContextEntryHelper.createCollapseContextButton(true, customFeature, customContext);
					ret.setCollapseContextButton(collapseButton);
				} else if (customFeature.isAvailable(customContext)) {
					ContextButtonEntry contextButtonEntry = new ContextButtonEntry(customFeature, customContext);
					contextButtonEntry.setIconId(IPlatformImageConstants.IMG_ECLIPSE_QUICKASSIST);
					if (customFeature instanceof LineWidthFeature) {
						if (lineWidthFeatureButton == null) {
							lineWidthFeatureButton = new ContextButtonEntry(null, null);
							lineWidthFeatureButton.setText("Line width");
							lineWidthFeatureButton.setDescription("Change line width");
							lineWidthFeatureButton.setIconId(IExampleImageConstants.IMG_TREE_LEFT);
							specificContextButtons.add(lineWidthFeatureButton);
						}
						lineWidthFeatureButton.getContextButtonMenuEntries().add(contextButtonEntry);
					} else if (customFeature instanceof LineStyleFeature) {
						if (lineStyleFeatureButton == null) {
							lineStyleFeatureButton = new ContextButtonEntry(null, null);
							lineStyleFeatureButton.setText("Line style");
							lineStyleFeatureButton.setDescription("Change line style");
							lineStyleFeatureButton.setIconId(IExampleImageConstants.IMG_TREE_LEFT);
							specificContextButtons.add(lineStyleFeatureButton);
						}
						lineStyleFeatureButton.getContextButtonMenuEntries().add(contextButtonEntry);
					} else if (customFeature instanceof TransparencyFeature) {
						if (transparencyFeatureButton == null) {
							transparencyFeatureButton = new ContextButtonEntry(null, null);
							transparencyFeatureButton.setText("Transparency");
							transparencyFeatureButton.setDescription("Change transparency");
							transparencyFeatureButton.setIconId(IExampleImageConstants.IMG_TREE_RIGHT);
							specificContextButtons.add(transparencyFeatureButton);
						}
						transparencyFeatureButton.getContextButtonMenuEntries().add(contextButtonEntry);
					} else {
						specificContextButtons.add(contextButtonEntry);
					}
				}
			}

			if (showCreateConnectionFeatures()) {
				ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
				ContextButtonEntry cbEntryForDnD = null;
				for (int i = 0; i < createConnectionFeatures.length; i++) {
					ICreateConnectionFeature createConnectionFeature = createConnectionFeatures[i];
					if (createConnectionFeature.isAvailable(context)) {
						if (cbEntryForDnD == null) {
							cbEntryForDnD = new ContextButtonEntry(null, context);
							cbEntryForDnD.setText("Create connection");
							cbEntryForDnD.setIconId(IPlatformImageConstants.IMG_ECLIPSE_INFORMATION);
							specificContextButtons.add(cbEntryForDnD);
						}
						cbEntryForDnD.addDragAndDropFeature(createConnectionFeature);

						ContextButtonEntry cbe = new ContextButtonEntry(null, context);
						cbe.addDragAndDropFeature(createConnectionFeature);
						cbe.setText(createConnectionFeature.getCreateName());
						cbe.setDescription(createConnectionFeature.getCreateDescription());
						cbe.setIconId(IPlatformImageConstants.IMG_ECLIPSE_INFORMATION_TSK);
						specificContextButtons.add(cbe);
					}
				}
			}
		}

		return ret;
	}

	protected boolean showCreateConnectionFeatures() {
		return true;
	}

	@Override
	public IContextMenuEntry[] getContextMenu(ICustomContext context) {
		IContextMenuEntry[] ret = NO_CONTEXT_MENU_ENTRIES;
		List<IContextMenuEntry> retList = new ArrayList<IContextMenuEntry>();
		for (int i = 0; i < ret.length; i++) {
			retList.add(ret[i]);
		}

		// custom features
		ICustomContext customContext = context;
		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(customContext);

		// menu groups
		ContextMenuEntry changeAlignmentEntry = null;
		ContextMenuEntry changetTransparencyEntry = null;
		ContextMenuEntry changetLineWidthEntry = null;
		ContextMenuEntry changetLineStyleEntry = null;
		ContextMenuEntry changetCornerDimensionEntry = null;
		ContextMenuEntry setStyleEntry = null;
		ContextMenuEntry setImageAttributeEntry = null;
		ContextMenuEntry displayImageDecoratorEntry = null;

		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			ContextMenuEntry contextMenuEntry = new ContextMenuEntry(customFeature, context);
			if (customFeature instanceof ChangeAlignmentFeature) {
				if (changeAlignmentEntry == null) {
					changeAlignmentEntry = new ContextMenuEntry(null, null);
					changeAlignmentEntry.setSubmenu(true);
					changeAlignmentEntry.setText("Text Alignment");
					changeAlignmentEntry.setDescription("Change Text Alignment");
					retList.add(changeAlignmentEntry);
				}
				changeAlignmentEntry.add(contextMenuEntry);
			} else if (customFeature instanceof TransparencyFeature) {
				if (changetTransparencyEntry == null) {
					changetTransparencyEntry = new ContextMenuEntry(null, null);
					changetTransparencyEntry.setSubmenu(true);
					changetTransparencyEntry.setText("Transparency");
					changetTransparencyEntry.setDescription("Change Shape Transparency");
					retList.add(changetTransparencyEntry);
				}
				changetTransparencyEntry.add(contextMenuEntry);
			} else if (customFeature instanceof LineWidthFeature) {
				if (changetLineWidthEntry == null) {
					changetLineWidthEntry = new ContextMenuEntry(null, null);
					changetLineWidthEntry.setSubmenu(true);
					changetLineWidthEntry.setText("Line Width");
					changetLineWidthEntry.setDescription("Change Line Width");
					retList.add(changetLineWidthEntry);
				}
				changetLineWidthEntry.add(contextMenuEntry);
			} else if (customFeature instanceof LineStyleFeature) {
				if (changetLineStyleEntry == null) {
					changetLineStyleEntry = new ContextMenuEntry(null, null);
					changetLineStyleEntry.setSubmenu(true);
					changetLineStyleEntry.setText("Line Style");
					changetLineStyleEntry.setDescription("Change Line Style");
					retList.add(changetLineStyleEntry);
				}
				changetLineStyleEntry.add(contextMenuEntry);
			} else if (customFeature instanceof CornerDimensionFeature) {
				if (changetCornerDimensionEntry == null) {
					changetCornerDimensionEntry = new ContextMenuEntry(null, null);
					changetCornerDimensionEntry.setSubmenu(true);
					changetCornerDimensionEntry.setText("Corner Dimension");
					changetCornerDimensionEntry.setDescription("Change Corner Dimension");
					retList.add(changetCornerDimensionEntry);
				}
				changetCornerDimensionEntry.add(contextMenuEntry);
			} else if (customFeature instanceof SetStyleFeature) {
				if (setStyleEntry == null) {
					setStyleEntry = new ContextMenuEntry(null, null);
					setStyleEntry.setSubmenu(true);
					setStyleEntry.setText("Set Style");
					setStyleEntry.setDescription("Set Shape's Style");
					retList.add(setStyleEntry);
				}
				setStyleEntry.add(contextMenuEntry);
			} else if (customFeature instanceof SetImageAttributesFeature) {
				if (setImageAttributeEntry == null) {
					setImageAttributeEntry = new ContextMenuEntry(null, null);
					setImageAttributeEntry.setSubmenu(true);
					setImageAttributeEntry.setText("Image");
					setImageAttributeEntry.setDescription("Set Image Attributes");
					retList.add(setImageAttributeEntry);
				}
				setImageAttributeEntry.add(contextMenuEntry);

			} else if (customFeature instanceof DisplayDecoratorFeature
					|| customFeature instanceof ClearDecoratorsFeature) {
				if (displayImageDecoratorEntry == null) {
					displayImageDecoratorEntry = new ContextMenuEntry(null, null);
					displayImageDecoratorEntry.setSubmenu(true);
					displayImageDecoratorEntry.setText("Decorators");
					displayImageDecoratorEntry.setDescription("Display a decorator");
					retList.add(displayImageDecoratorEntry);
				}
				displayImageDecoratorEntry.add(contextMenuEntry);

			} else {
				retList.add(contextMenuEntry);
			}

		}

		ret = retList.toArray(NO_CONTEXT_MENU_ENTRIES);
		return ret;
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		List<IPaletteCompartmentEntry> compartments = new ArrayList<IPaletteCompartmentEntry>();

		{
			final PaletteCompartmentEntry connectionsCompartmentEntry = new MyPaletteCompartmentEntry("Connections",
					ICON_CONNECTION_COMPARTMENT);
			connectionsCompartmentEntry.setInitiallyOpen(false);
			compartments.add(connectionsCompartmentEntry);

			IFeatureProvider featureProvider = getFeatureProvider();
			ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
			if (createConnectionFeatures.length > 0) {

				ConnectionCreationToolEntry multiTool = null;
				if (createConnectionFeatures.length > 1) {
					multiTool = new ConnectionCreationToolEntry("Connection", "dynamic connection", null, null);
					connectionsCompartmentEntry.addToolEntry(multiTool);
				}

				for (ICreateConnectionFeature createConnectionFeature : createConnectionFeatures) {
					ConnectionCreationToolEntry ccTool = new ConnectionCreationToolEntry(createConnectionFeature.getCreateName(),
							createConnectionFeature.getCreateDescription(), createConnectionFeature.getCreateImageId(),
							createConnectionFeature.getCreateLargeImageId());
					ccTool.addCreateConnectionFeature(createConnectionFeature);
					if (multiTool != null) {
						multiTool.addCreateConnectionFeature(createConnectionFeature);
					}
					connectionsCompartmentEntry.addToolEntry(ccTool);
				}

			}
		}

		{
			final PaletteCompartmentEntry objectsCompartmentEntry = new PaletteCompartmentEntry("Objects", null);
			objectsCompartmentEntry.setInitiallyOpen(true);
			compartments.add(objectsCompartmentEntry);

			IFeatureProvider featureProvider = getFeatureProvider();
			ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();

			for (ICreateFeature createFeature : createFeatures) {
				ObjectCreationToolEntry objectCreationToolEntry;
				// create icon for the canFigure entry with an image descriptor
				// instead an image id
				if (createFeature instanceof SketchCreateCanFigureFeature) {
					objectCreationToolEntry = new MyObjectCreationToolEntry(createFeature.getCreateName(),
							createFeature.getCreateDescription(), createFeature, ICON_CAN_FIGURE);
				} else {
					objectCreationToolEntry = new ObjectCreationToolEntry(createFeature.getCreateName(),
							createFeature.getCreateDescription(), createFeature.getCreateImageId(), createFeature.getCreateLargeImageId(),
							createFeature);
				}
				objectsCompartmentEntry.addToolEntry(objectCreationToolEntry);
			}
		}

		return compartments.toArray(new IPaletteCompartmentEntry[compartments.size()]);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		if (TEST_SHOW_WARNING_DECORATORS) {
			ImageDecorator rd = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING);
			int x = 22;
			int y = 33;
			rd.setX(x);
			rd.setY(y);
			rd.setMessage("Warning (x=" + x + " y=" + y + ")");

			IDecorator[] ret = new IDecorator[] { rd };
			return ret;
		} else {
			List<IDecorator> list = decorators.get(pe);
			if (list != null) {
				return list.toArray(new IDecorator[decorators.size()]);
			} else {
				return super.getDecorators(pe);
			}
		}
	}

	public void addDecorators(PictogramElement pe, IDecorator decorator) {
		List<IDecorator> list = decorators.get(pe);
		if (list == null) {
			list = new ArrayList<IDecorator>();
			decorators.put(pe, list);
		}
		list.add(decorator);
	}

	public void clearDecorators(PictogramElement pe) {
		decorators.remove(pe);
	}

	@Override
	public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
		GraphicsAlgorithm ghostGa = SketchUtil.getGhostGa(pe);
		if (ghostGa != null) {
			return ghostGa.getGraphicsAlgorithmChildren().toArray(new GraphicsAlgorithm[0]);
		}
		return super.getClickArea(pe);
	}

	@Override
	public GraphicsAlgorithm getSelectionBorder(PictogramElement pe) {
		GraphicsAlgorithm ghostGa = SketchUtil.getGhostGa(pe);
		if (ghostGa != null) {
			return ghostGa.getGraphicsAlgorithmChildren().get(0);
		}
		return super.getSelectionBorder(pe);
	}

	@Override
	public ISelectionInfo getSelectionInfoForShape(Shape shape) {
		if (shape instanceof ContainerShape) {
			return selectionInfo;
		}
		return super.getSelectionInfoForShape(shape);
	}

	@Override
	public AbstractText getRichToolTip(GraphicsAlgorithm ga) {
		if (SketchUtil.isConnectionPoint(ga.getPictogramElement())) {
			return null;
		}
		
		if (ga instanceof AbstractText && ga.getParentGraphicsAlgorithm() != null) {
			return getRichToolTip(ga.getParentGraphicsAlgorithm());
		}
		final int x = ga.getX();
		final int y = ga.getY();
		final int width = ga.getWidth();
		final int height = ga.getHeight();
		String first = ga.getClass().getPackage().getName();
		String second = ga.getClass().getSimpleName();
		String third = "(x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ")";
		String toolTip = first + "." + second + "\n" + third;

		IGaService gaService = Graphiti.getGaService();
		Text text = gaService.createPlainText(null, toolTip);

		TextStyleRegion textStyleRegion = gaService.createTextStyleRegion(text, first.length() + 1, first.length()
				+ second.length());
		gaService.createTextStyle(textStyleRegion, true, false, UnderlineStyle.UNDERLINE_SINGLE);

		TextStyleRegion textStyleRegion2 = gaService.createTextStyleRegion(text);
		textStyleRegion2.setStart(first.length() + second.length() + 2);
		textStyleRegion2.setEnd(toolTip.length());

		TextStyle textStyle = gaService.createTextStyle(textStyleRegion);
		Font font = gaService.manageFont(getDiagramTypeProvider().getDiagram(), IGaService.DEFAULT_FONT,
				IGaService.DEFAULT_FONT_SIZE, true, false);
		textStyle.setFont(font);

		return text;
	}

	@Override
	public double[] getZoomLevels() {
		return ZOOM_LEVELS;
	}

	@Override
	public void postExecute(IExecutionInfo executionInfo) {
		super.postExecute(executionInfo);

		//Graphiti.getPeService().moveBendpoints(executionInfo);
	}

	@Override
	public void preExecute(IExecutionInfo executionInfo) {
		super.preExecute(executionInfo);
	}

	@Override
	public GraphicsAlgorithm getContentArea(ContainerShape cs) {
		if (cs != null && cs.getGraphicsAlgorithm() != null && SketchUtil.isContainer(cs) && SketchUtil.isGhost(cs)) {
			if (SketchUtil.isRectangle(cs) || SketchUtil.isRoundedRectangle(cs)) {
				List<GraphicsAlgorithm> gaChildren = cs.getGraphicsAlgorithm().getGraphicsAlgorithmChildren();
				if (gaChildren != null && gaChildren.size() == 2) {
					GraphicsAlgorithm ga = gaChildren.get(0);
					if (ga instanceof Rectangle || ga instanceof RoundedRectangle) {
						return ga;
					}
				}
			}
		}
		return super.getContentArea(cs);
	}

	public String getName() {
		return "Edit Mode";
	}

}
