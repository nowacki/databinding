package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;

import android.widget.ToggleButton;

public class ToggleButtonObservableText extends AbstractAndroidObservableValue {

	private final ToggleButton button;

	/**
	 * Observable a text of a button
	 *
	 * @param button
	 *            the button
	 */
	public ToggleButtonObservableText(ToggleButton button) {
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
	public ToggleButtonObservableText(Realm realm, ToggleButton button) {
		super(realm, button);
		this.button = button;
	}

	public void doSetValue(final Object value) {
		String oldValue = button.getTextOn().toString();
		String newValue = value == null ? "" : value.toString(); //$NON-NLS-1$
		button.setTextOn(newValue);
		button.setTextOff(newValue);

		if (!newValue.equals(oldValue)) {
			fireValueChange(Diffs.createValueDiff(oldValue, newValue));
		}
	}

	public Object doGetValue() {
		return button.getTextOn();
	}

	public Object getValueType() {
		return String.class;
	}

}
