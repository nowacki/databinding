/*******************************************************************************
 * Copyright (c) 2007, Angelo Zerr and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - Initial API and implementation
 *******************************************************************************/
package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;

import android.widget.ToggleButton;

/**
 * ISwingObservableValue implementation to observe text button.
 *
 * @since 1.0
 */
public class ButtonObservableTextOff extends AbstractAndroidObservableValue {

	private final ToggleButton button;

	/**
	 * Observable a text of a button
	 *
	 * @param button
	 *            the button
	 */
	public ButtonObservableTextOff(ToggleButton button) {
		super(button);
		this.button = button;
	}

	/**
	 * Observable a text of a button
	 *
	 * @param realm
	 *            the realm
	 * @param button
	 */
	public ButtonObservableTextOff(Realm realm, ToggleButton button) {
		super(realm, button);
		this.button = button;
	}

	public void doSetValue(final Object value) {
		String oldValue = button.getTextOff().toString();
		String newValue = value == null ? "" : value.toString(); //$NON-NLS-1$
		button.setTextOff(newValue);

		if (!newValue.equals(oldValue)) {
			fireValueChange(Diffs.createValueDiff(oldValue, newValue));
		}
	}

	public Object doGetValue() {
		return button.getTextOff();
	}

	public Object getValueType() {
		return String.class;
	}

}
