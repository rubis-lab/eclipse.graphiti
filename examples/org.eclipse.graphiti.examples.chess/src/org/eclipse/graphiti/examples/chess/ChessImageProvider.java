package org.eclipse.graphiti.examples.chess;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class ChessImageProvider extends AbstractImageProvider implements IImageProvider {

	// The prefix for all identifiers of this image provider
	protected static final String PREFIX = "org.eclipse.graphiti.examples.chess."; //$NON-NLS-1$

	// The image identifiers for the chess pieces in black...
	public static final String IMG_BLACK_BISHOP = PREFIX + "black_bishop"; //$NON-NLS-1$
	public static final String IMG_BLACK_KING = PREFIX + "black_king"; //$NON-NLS-1$
	public static final String IMG_BLACK_KNIGHT = PREFIX + "black_knight"; //$NON-NLS-1$
	public static final String IMG_BLACK_PAWN = PREFIX + "black_pawn"; //$NON-NLS-1$
	public static final String IMG_BLACK_QUEEN = PREFIX + "black_queen"; //$NON-NLS-1$
	public static final String IMG_BLACK_ROOK = PREFIX + "black_rook"; //$NON-NLS-1$

	// ...and white
	public static final String IMG_WHITE_BISHOP = PREFIX + "white_bishop"; //$NON-NLS-1$
	public static final String IMG_WHITE_KING = PREFIX + "white_king"; //$NON-NLS-1$
	public static final String IMG_WHITE_KNIGHT = PREFIX + "white_knight"; //$NON-NLS-1$
	public static final String IMG_WHITE_PAWN = PREFIX + "white_pawn"; //$NON-NLS-1$
	public static final String IMG_WHITE_QUEEN = PREFIX + "white_queen"; //$NON-NLS-1$
	public static final String IMG_WHITE_ROOK = PREFIX + "white_rook"; //$NON-NLS-1$

	public ChessImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		// register the path for each image identifier
		addImageFilePath(IMG_BLACK_BISHOP, "resources/black_bishop.png"); //$NON-NLS-1$
		addImageFilePath(IMG_BLACK_KING, "resources/black_king.png"); //$NON-NLS-1$
		addImageFilePath(IMG_BLACK_KNIGHT, "resources/black_knight.png"); //$NON-NLS-1$
		addImageFilePath(IMG_BLACK_PAWN, "resources/black_pawn.png"); //$NON-NLS-1$
		addImageFilePath(IMG_BLACK_QUEEN, "resources/black_queen.png"); //$NON-NLS-1$
		addImageFilePath(IMG_BLACK_ROOK, "resources/black_rook.png"); //$NON-NLS-1$

		addImageFilePath(IMG_WHITE_BISHOP, "resources/white_bishop.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WHITE_KING, "resources/white_king.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WHITE_KNIGHT, "resources/white_knight.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WHITE_PAWN, "resources/white_pawn.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WHITE_QUEEN, "resources/white_queen.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WHITE_ROOK, "resources/white_rook.png"); //$NON-NLS-1$
	}

}
