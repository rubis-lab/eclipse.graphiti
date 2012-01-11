/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2011, 2010 SAP AG.
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
package org.eclipse.graphiti.testtool.sketch.features;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.PrecisionPoint;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.CurvedConnection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * The Class ModifyControlPointsFeature.
 */
public class ModifyControlPointsFeature extends AbstractCustomFeature {

	public ModifyControlPointsFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Modify control points...";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0 && pes[0] instanceof CurvedConnection) {
			CurvedConnection connection = (CurvedConnection) pes[0];

			final ListDialogWithButtons dialog = new ListDialogWithButtons(Display.getCurrent().getActiveShell(),
					connection);
			dialog.setTitle("Control Points");
			dialog.setContentProvider(new IStructuredContentProvider() {
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				}

				public void dispose() {
				}

				public Object[] getElements(Object inputElement) {
					if (inputElement instanceof CurvedConnection) {
						return ((CurvedConnection) inputElement).getControlPoints().toArray();
					}
					return null;
				}
			});
			dialog.setLabelProvider(new ILabelProvider() {

				public void removeListener(ILabelProviderListener listener) {
				}

				public boolean isLabelProperty(Object element, String property) {
					return false;
				}

				public void dispose() {
				}

				public void addListener(ILabelProviderListener listener) {
				}

				public String getText(Object element) {
					if (element instanceof PrecisionPoint) {
						return ((PrecisionPoint) element).getX() + ", " + ((PrecisionPoint) element).getY();
					}
					return null;
				}

				public Image getImage(Object element) {
					return null;
				}
			});
			dialog.setInput(connection);
			if (dialog.open() != Window.OK) {
				throw new OperationCanceledException("Cancelled by user");
			}

		}
	}

	@Override
	public boolean isAvailable(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			if (pes == null || pes.length > 1) {
				return false;
			}
			PictogramElement pe = pes[0];
			if (pe instanceof CurvedConnection && pe.getGraphicsAlgorithm() instanceof Polyline
					&& !(pe instanceof Diagram)) {
				return true;
			}
		}
		return false;
	}

	private class ListDialogWithButtons extends ListDialog implements ISelectionChangedListener {

		private Button addButton;
		private Button removeButton;
		private Button upButton;
		private Button downButton;
		private CurvedConnection connection;

		public ListDialogWithButtons(Shell parent, CurvedConnection connection) {
			super(parent);
			this.connection = connection;
		}

		@Override
		protected Control createDialogArea(Composite container) {
			Composite composite = new Composite(container, SWT.None);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			composite.setLayout(layout);
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
			composite.setLayoutData(data);

			Control area = super.createDialogArea(composite);
			area.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

			Composite buttonsComposite = new Composite(composite, SWT.None);
			layout = new GridLayout();
			layout.numColumns = 1;
			buttonsComposite.setLayout(layout);
			data = new GridData(SWT.FILL, SWT.FILL, true, true);
			buttonsComposite.setLayoutData(data);

			addButton = new Button(buttonsComposite, SWT.PUSH);
			addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			addButton.setText("Add...");
			addButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					InputDialog inputDialog = new InputDialog(getShell(), "New Control Point",
							"Enter a new control point in the format '1.0,1.0'.", "", new IInputValidator() {
								public String isValid(String newText) {
									int index = newText.indexOf(',');
									if (index != -1) {
										String first = newText.substring(0, index);
										String second = newText.substring(index + 1);
										try {
											Double.parseDouble(first);
											Double.parseDouble(second);
										} catch (NumberFormatException e) {
											return "No valid number";
										}
										return null;
									}

									return "No valid input, use comma to seperate x and y.";
								}
							});
					if (inputDialog.open() == OK) {
						String value = inputDialog.getValue();
						int index = value.indexOf(',');
						double firstValue = 0;
						double secondValue = 0;
						if (index != -1) {
							String first = value.substring(0, index);
							String second = value.substring(index + 1);
							try {
								firstValue = Double.parseDouble(first);
								secondValue = Double.parseDouble(second);
							} catch (NumberFormatException e) {
								return;
							}
						}
						PrecisionPoint newPoint = StylesFactory.eINSTANCE.createPrecisionPoint();
						newPoint.setX(firstValue);
						newPoint.setY(secondValue);
						connection.getControlPoints().add(newPoint);
						getTableViewer().refresh();
						getTableViewer().setSelection(new StructuredSelection(newPoint));
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});

			removeButton = new Button(buttonsComposite, SWT.PUSH);
			removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			removeButton.setText("Remove");
			removeButton.setEnabled(false);
			removeButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					ISelection selection = getTableViewer().getSelection();
					if (selection instanceof IStructuredSelection) {
						Object firstElement = ((IStructuredSelection) selection).getFirstElement();
						if (firstElement instanceof PrecisionPoint) {
							PrecisionPoint point = (PrecisionPoint) firstElement;
							CurvedConnection connection = (CurvedConnection) point.eContainer();
							connection.getControlPoints().remove(point);
							getTableViewer().refresh();
							getTableViewer().setSelection(new StructuredSelection(point));
						}
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});

			upButton = new Button(buttonsComposite, SWT.UP);
			upButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			upButton.setText("Up");
			upButton.setEnabled(false);
			upButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					ISelection selection = getTableViewer().getSelection();
					if (selection instanceof IStructuredSelection) {
						Object firstElement = ((IStructuredSelection) selection).getFirstElement();
						if (firstElement instanceof PrecisionPoint) {
							PrecisionPoint point = (PrecisionPoint) firstElement;
							CurvedConnection connection = (CurvedConnection) point.eContainer();
							int index = connection.getControlPoints().indexOf(point);
							connection.getControlPoints().move(index - 1, point);
							getTableViewer().refresh();
							getTableViewer().setSelection(new StructuredSelection(point));
						}
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});

			downButton = new Button(buttonsComposite, SWT.DOWN);
			downButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			downButton.setText("Down");
			downButton.setEnabled(false);
			downButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					ISelection selection = getTableViewer().getSelection();
					if (selection instanceof IStructuredSelection) {
						Object firstElement = ((IStructuredSelection) selection).getFirstElement();
						if (firstElement instanceof PrecisionPoint) {
							PrecisionPoint point = (PrecisionPoint) firstElement;
							CurvedConnection connection = (CurvedConnection) point.eContainer();
							int index = connection.getControlPoints().indexOf(point);
							connection.getControlPoints().move(index + 1, point);
							getTableViewer().refresh();
							getTableViewer().setSelection(new StructuredSelection(point));
						}
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});

			getTableViewer().addSelectionChangedListener(this);

			return composite;
		}

		public void selectionChanged(SelectionChangedEvent event) {
			removeButton.setEnabled(false);
			upButton.setEnabled(false);
			downButton.setEnabled(false);
			Object selection = event.getSelection();
			if (selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection) selection).getFirstElement();
				if (firstElement instanceof PrecisionPoint) {
					PrecisionPoint point = (PrecisionPoint) firstElement;
					CurvedConnection connection = (CurvedConnection) point.eContainer();
					int index = connection.getControlPoints().indexOf(point);
					removeButton.setEnabled(index != -1);
					upButton.setEnabled(index > 0);
					downButton.setEnabled(index < connection.getControlPoints().size() - 1);
				}
			}
		}
	}
}
