package org.eclipse.graphiti.examples.chess.features;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaLayoutService;
import org.eclipse.graphiti.util.IColorConstant;

public class AddChessBoardFeature extends AbstractAddShapeFeature implements IAddFeature {

	private static final int CHESS_BOARD_ROWS = 8;
	private static final int CHESS_BOARD_COLUMNS = 8;

	public AddChessBoardFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getTargetContainer() instanceof Diagram) {
			// Add new board only in case of an empty diagram
			return context.getTargetContainer().getChildren().size() == 0;
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ICreateService createService = Graphiti.getCreateService();
		IGaLayoutService layoutService = Graphiti.getGaLayoutService();

		ContainerShape outerContainerShape = createService.createContainerShape(getDiagram(), true);
		Rectangle outerRectangle = createService.createRectangle(outerContainerShape);
		layoutService.setLocationAndSize(outerRectangle, 50, 50, 400, 400);

		for (int i = 0; i <= CHESS_BOARD_ROWS; i++) {
			for (int j = 0; j <= CHESS_BOARD_COLUMNS; j++) {
				ContainerShape fieldShape = createService.createContainerShape(outerContainerShape, true);
				Rectangle fieldRectangle = createService.createRectangle(fieldShape);
				layoutService.setLocationAndSize(fieldRectangle, i * 50, j * 50, 50, 50);
				IColorConstant color;
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						color = IColorConstant.WHITE;
					} else {
						color = IColorConstant.BLACK;
					}
				} else {
					if (j % 2 == 0) {
						color = IColorConstant.BLACK;
					} else {
						color = IColorConstant.WHITE;
					}
				}
				fieldRectangle.setBackground(manageColor(color));
			}
		}

		return outerContainerShape;
	}

}
