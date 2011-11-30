/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
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
package org.eclipse.graphiti.ui.editor;

import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.Tool;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.palette.DefaultPaletteViewerPreferences;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.editor.GFPaletteRoot;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * @since 0.9
 */
public class DefaultPaletteBehaviour {


	protected static final String PROPERTY_PALETTE_DOCK_LOCATION = "Dock location"; //$NON-NLS-1$
	protected static final String PROPERTY_PALETTE_SIZE = "Palette Size"; //$NON-NLS-1$
	protected static final String PROPERTY_PALETTE_STATE = "Palette state"; //$NON-NLS-1$

	protected static final int DEFAULT_PALETTE_SIZE = 130;

	private DiagramEditor diagramEditor;
	private PaletteRoot paletteRoot;

	public DefaultPaletteBehaviour(DiagramEditor diagramEditor) {
		super();
		this.diagramEditor = diagramEditor;
	}

	public PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			paletteRoot = createPaletteRoot();
		}
		return paletteRoot;
	}

	public void initializeViewer() {
		// Set preference-store for palette
		PaletteViewer paletteViewer = diagramEditor.getEditDomain().getPaletteViewer();
		if (paletteViewer != null) {
			IPreferenceStore store = GraphitiUIPlugin.getDefault().getPreferenceStore();
			paletteViewer.setPaletteViewerPreferences(new DefaultPaletteViewerPreferences(store));

			// Refresh the PaletteViewer
			// This can be achieved by firing a font-change-event from the
			// IPreferenceStore. It would be nicer, if the PaletteViewer would
			// have some kind of refresh()-method directly.
			store.firePropertyChangeEvent(PaletteViewerPreferences.PREFERENCE_FONT, null, null);
		}
	}

	public FlyoutPreferences getPalettePreferences() {
		return new FlyoutPreferences() {
			public int getDockLocation() {
				return getPreferenceStore().getInt(PROPERTY_PALETTE_DOCK_LOCATION);
			}

			public int getPaletteState() {
				// TODO ? Move isShowFlyoutPalette from TBP to
				// DefaultPaletteBehaviour?
				if (!diagramEditor.getDiagramTypeProvider().getCurrentToolBehaviorProvider().isShowFlyoutPalette()) {
					return 8; // FlyoutPaletteComposite.STATE_HIDDEN is private
				}
				return getPreferenceStore().getInt(PROPERTY_PALETTE_STATE);
			}

			public int getPaletteWidth() {
				return getPreferenceStore().getInt(PROPERTY_PALETTE_SIZE);
			}

			public void setDockLocation(int location) {
				getPreferenceStore().setValue(PROPERTY_PALETTE_DOCK_LOCATION, location);
			}

			public void setPaletteState(int state) {
				getPreferenceStore().setValue(PROPERTY_PALETTE_STATE, state);
			}

			public void setPaletteWidth(int width) {
				getPreferenceStore().setValue(PROPERTY_PALETTE_SIZE, width);
			}
		};
	}

	/**
	 * Creates the PaletteRoot of this editor.
	 * 
	 * @return the palette root
	 * @see org.eclipse.graphiti.ui.editor.GraphicalEditorIncludingPalette#createPaletteRoot()
	 */
	protected PaletteRoot createPaletteRoot() {
		return new GFPaletteRoot(diagramEditor.getDiagramTypeProvider());
	}

	/**
	 * Returns the PaletteViewerProvider, which can be used to create a new
	 * PaletteViewer. This method can be overwritten to return a subclass of the
	 * PaletteViewerProvider, which configures the PaletteViewer with a
	 * different ContextMenu, with a PaletteCustomizer or with a different
	 * IPreferencesStore. Do not call this method directly, instead call
	 * getPaletteViewerProvider(), which buffers the created object.
	 * <p>
	 * By default this method returns a new PaletteViewerProvider.
	 * 
	 * @return The PaletteViewerProvider, which can be used to create a new
	 *         PaletteViewer.
	 */
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(diagramEditor.getEditDomain()) {
			private KeyHandler paletteKeyHandler = null;

			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.getKeyHandler().setParent(getPaletteKeyHandler());
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
			}

			/**
			 * @return Palette Key Handler for the palette
			 */
			private KeyHandler getPaletteKeyHandler() {

				if (paletteKeyHandler == null) {

					paletteKeyHandler = new KeyHandler() {

						/**
						 * Processes a <i>key released </i> event. This method
						 * is called by the Tool whenever a key is released, and
						 * the Tool is in the proper state. Overridden to
						 * support pressing the enter key to create a shape or
						 * connection (between two selected shapes)
						 * 
						 * @param event
						 *            the KeyEvent
						 * @return <code>true</code> if KeyEvent was handled in
						 *         some way
						 */

						public boolean keyReleased(KeyEvent event) {
							if (event.keyCode == SWT.Selection) {
								Tool tool = getEditDomain().getPaletteViewer().getActiveTool().createTool();
								if (tool instanceof CreationTool || tool instanceof ConnectionCreationTool) {
									tool.keyUp(event, diagramEditor.getGraphicalViewer());
									// Deactivate current selection
									getEditDomain().getPaletteViewer().setActiveTool(null);
									return true;
								}
							}
							return super.keyReleased(event);
						}
					};
				}
				return paletteKeyHandler;
			}
		};
	}

	public void refreshPalette() {
		PaletteRoot pr = getPaletteRoot();
		if (pr instanceof GFPaletteRoot) {
			GFPaletteRoot gpr = (GFPaletteRoot) pr;
			gpr.updatePaletteEntries();
		}
	}

	public void dispose() {
		paletteRoot = null;
	}

	private IPreferenceStore getPreferenceStore() {
		IPreferenceStore ps = GraphitiUIPlugin.getDefault().getPreferenceStore();
		ps.setDefault(DefaultPaletteBehaviour.PROPERTY_PALETTE_STATE, FlyoutPaletteComposite.STATE_PINNED_OPEN);
		ps.setDefault(DefaultPaletteBehaviour.PROPERTY_PALETTE_SIZE, DEFAULT_PALETTE_SIZE);
		return ps;
	}
}
