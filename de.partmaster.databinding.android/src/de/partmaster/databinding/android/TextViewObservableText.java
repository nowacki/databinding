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

import android.widget.TextView;

/**
 * ISwingObservableValue implementation to observe text button.
 *
 * @since 1.0
 */
public class TextViewObservableText extends AbstractAndroidObservableValue {

	private final TextView control;

	/**
	 * Observable a text of a button
	 *
	 * @param control
	 *            the button
	 */
	public TextViewObservableText(TextView control) {
		super(control);
		this.control = control;
	}

	/**
	 * Observable a text of a button
	 *
	 * @param realm
	 *            the realm
	 * @param control
	 */
	public TextViewObservableText(Realm realm, TextView control) {
		super(realm, control);
		this.control = control;
	}

	public void doSetValue(final Object value) {
		String oldValue = control.getText().toString();
		String newValue = value == null ? "" : value.toString(); //$NON-NLS-1$
		control.setText(newValue);

		if (!newValue.equals(oldValue)) {
			fireValueChange(Diffs.createValueDiff(oldValue, newValue));
		}
	}

	public Object doGetValue() {
		return control.getText();
	}

	public Object getValueType() {
		return String.class;
	}

}
