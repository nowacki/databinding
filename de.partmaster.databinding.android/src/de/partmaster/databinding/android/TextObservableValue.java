/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Brad Reynolds (bug 135446)
 *     Brad Reynolds - bug 164653
 *******************************************************************************/
package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

/**
 * {@link IObservable} implementation that wraps a {@link JTextComponent}
 * widget. The time at which listeners should be notified about changes to the
 * text is specified on construction.
 * 
 * <dl>
 * <dt>Events:</dt>
 * <dd>If the update event type (specified on construction) is
 * <code>SWT.Modify</code> a value change event will be fired on every key
 * stroke. If the update event type is <code>SWT.FocusOut</code> a value change
 * event will be fired on focus out. When in either mode if the user is entering
 * text and presses [Escape] the value will be reverted back to the last value
 * set using doSetValue(). Regardless of the update event type a value changing
 * event will fire on verify to enable vetoing of changes.</dd>
 * </dl>
 * 
 * @since 1.0
 */
public class TextObservableValue extends AbstractAndroidVetoableValue {

	/**
	 * {@link JTextComponent} widget that this is being observed.
	 */
	private final TextView text;

	/**
	 * Flag to track when the model is updating the widget. When
	 * <code>true</code> the handlers for the SWT events should not process the
	 * event as this would cause an infinite loop.
	 */
	private boolean updating = false;

	/**
	 * Valid types for the {@link #updateEventType}.
	 */
	private static final int[] validUpdateEventTypes = new int[] {
			AndroidEventConstants.Modify, AndroidEventConstants.FocusOut,
			AndroidEventConstants.None };

	/**
	 * Previous value of the Text.
	 */
	private String oldValue;

	private Object updateListener = null;

	/**
	 * Constructs a new instance bound to the given <code>text</code> widget and
	 * configured to fire change events to its listeners at the time of the
	 * <code>updateEventType</code>.
	 * 
	 * @param text
	 * @param updateEventType
	 *            SWT event constant as to what SWT event to update the model in
	 *            response to. Appropriate values are: <code>SWT.Modify</code>,
	 *            <code>SWT.FocusOut</code>, <code>SWT.None</code>.
	 * @throws IllegalArgumentException
	 *             if <code>updateEventType</code> is an incorrect type.
	 */
	public TextObservableValue(final TextView text, int updateEventType) {
		this(AndroidObservables.getRealm(), text,
				updateEventType);
	}

	/**
	 * Constructs a new instance.
	 * 
	 * @param realm
	 *            can not be <code>null</code>
	 * @param text
	 * @param updateEventType
	 */
	public TextObservableValue(final Realm realm, TextView text,
			int updateEventType) {
		super(realm, text);
		boolean eventValid = false;
		for (int i = 0; !eventValid && i < validUpdateEventTypes.length; i++) {
			eventValid = (updateEventType == validUpdateEventTypes[i]);
		}
		if (!eventValid) {
			throw new IllegalArgumentException(
					"UpdateEventType [" + updateEventType + "] is not supported."); //$NON-NLS-1$//$NON-NLS-2$
		}
		this.text = text;

		if (updateEventType == AndroidEventConstants.FocusOut) {
			this.updateListener = createFocusListener();
			this.text
					.setOnFocusChangeListener((OnFocusChangeListener) updateListener);
		} else {
			this.updateListener = createDocumentListener();
			this.text.addTextChangedListener((TextWatcher) updateListener);
		}

		oldValue = text.getText().toString();

	}

	private OnFocusChangeListener createFocusListener() {
		return new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus && !updating) {
					String newValue = text.getText().toString();

					if (!newValue.equals(oldValue)) {
						fireValueChange(Diffs.createValueDiff(oldValue,
								newValue));
						oldValue = newValue;
					}
				}
			}
		};
	}

	private TextWatcher createDocumentListener() {
		return new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				if (!updating) {
					String newValue = s.toString();
					if (!newValue.equals(oldValue)) {
						fireValueChange(Diffs.createValueDiff(oldValue,
								newValue));
						oldValue = newValue;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		};
	}

	/**
	 * Sets the bound {@link JTextComponent Text's} text to the passed
	 * <code>value</code>.
	 * 
	 * @param value
	 *            new value, String expected
	 * @see org.eclipse.core.databinding.observable.value.AbstractVetoableValue#doSetApprovedValue(java.lang.Object)
	 * @throws ClassCastException
	 *             if the value is anything other than a String
	 */
	protected void doSetApprovedValue(final Object value) {
		try {
			updating = true;
			text.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
			oldValue = text.getText().toString();
		} finally {
			updating = false;
		}
	}

	/**
	 * Returns the current value of the {@link JTextComponent}.
	 * 
	 * @see org.eclipse.core.databinding.observable.value.AbstractVetoableValue#doGetValue()
	 */
	public Object doGetValue() {
		return oldValue = text.getText().toString();
	}

	/**
	 * Returns the type of the value from {@link #doGetValue()}, i.e.
	 * String.class
	 * 
	 * @see org.eclipse.core.databinding.observable.value.IObservableValue#getValueType()
	 */
	public Object getValueType() {
		return String.class;
	}

	public void dispose() {
		if (updateListener != null) {
			if (updateListener instanceof OnFocusChangeListener)
				this.text.setOnFocusChangeListener(null);
			else
				this.text.removeTextChangedListener((TextWatcher) updateListener);
		}

		// if (!text.isDisposed()) {
		// if (updateEventType != SWT.None) {
		// text.removeListener(updateEventType, updateListener);
		// }
		// text.removeVerifyListener(verifyListener);
		// }
		super.dispose();
	}
}
