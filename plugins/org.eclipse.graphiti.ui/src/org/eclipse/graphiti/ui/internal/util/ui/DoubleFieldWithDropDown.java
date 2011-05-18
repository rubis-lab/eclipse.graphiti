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
 *    mwenz - Bug 323359 Avoid usage of java.lang.text, ICU4J etc.
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.ui;

import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * A Combo control allowing only non-negative decimal input. Writes its state to
 * a DefaultPreferences object passed in the constructor.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DoubleFieldWithDropDown extends Composite implements ModifyListener, SelectionListener {

	public static final String decimalNumberFormat = "###0.00"; //$NON-NLS-1$

	private DefaultPreferences _preferences;
	private int _index;
	private Combo _text;
	private boolean _internalModify = false;
	private boolean _initialUpdate = true;

	/**
	 * Creates a new DoubleFieldWithDropDown.
	 */
	public DoubleFieldWithDropDown(Composite parent, int style, DefaultPreferences prefs, int index, double[] defaults) {
		super(parent, style);
		_preferences = prefs;
		_index = index;
		FillLayout fill = new FillLayout();
		setLayout(fill);
		_text = new Combo(this, SWT.DROP_DOWN | SWT.BORDER);
		for (int i = 0; i < defaults.length; i++) {
			_text.add(Double.toString(defaults[i]));
		}
		updateControl();

		_text.addModifyListener(this);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gridData.widthHint = 50;
		// setLayoutData(gridData);
	}

	// ========================== public access ===============================

	@Override
	public void setEnabled(boolean en) {
		_text.setEnabled(en);
	}

	/**
	 * Updates control according to value in the DefaultPreferences. If it is
	 * the first update with an empty textfield, the preference value is set. If
	 * it is not the first time an empty text field occurs, nothing happens
	 * avoiding the confusing behaviour of updating to 0.00 when the user
	 * deletes all characters for preparing new input.
	 */
	public void updateControl() {
		_internalModify = true;
		double newValue = _preferences.getDoublePreference(_index);
		try {
			if (_text.getText().length() != 0) {
				double oldValue = Double.valueOf(_text.getText()).doubleValue();
				if (newValue != oldValue) {
					_text.setText(Double.toString(newValue));
				}
			} else {
				if (_initialUpdate) {
					_text.setText(Double.toString(newValue));
				}
			}
		} catch (NumberFormatException e) {
			_text.setText(Double.toString(newValue));
		} finally {
			_internalModify = false;
		}
		_initialUpdate = false;
	}

	public Object getText() {
		return _text;
	}

	// ============================ eventing ==================================

	public void addModifyListener(ModifyListener listener) {
		_text.addModifyListener(listener);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		if (_internalModify)
			return;
		try {
			String text = _text.getText();
			double value;
			if (text.length() == 0) {
				value = new Double(0);
			} else {
				value = new Double(text);
				if (value < 0) {
					throw new NumberFormatException(Messages.DoubleFieldWithDropDown_0 + value);
				}
			}
			_preferences.setDoublePreference(_index, value);
		} catch (NumberFormatException x) {
			// $JL-EXC$ do nothing, reset after the conversion anyway
			updateControl();
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (_internalModify)
			return;

		try {
			String text = _text.getItem(_text.getSelectionIndex());
			double value = new Double(text);
			_preferences.setDoublePreference(_index, value);
		} catch (NumberFormatException x) {
			// $JL-EXC$ do nothing, reset after the conversion anyway
			updateControl();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}
}