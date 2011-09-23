package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
					
/**
 * IAndroidObservableValue implementation to observe if compound button (ToggleButton....) is checked or not.
 */
public class ButtonObservableValue extends AbstractAndroidObservableValue {

	private final CompoundButton button;
	private boolean selectionValue;
	private boolean updating = false;
	private OnCheckedChangeListener updateListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (updating)
				return;
			boolean oldSelectionValue = selectionValue;
			selectionValue = isChecked;
			notifyIfChanged(oldSelectionValue, selectionValue);
		}
	};
	public ButtonObservableValue(CompoundButton button) {
		super(button);
		this.button = button;
		init();
	}
	public ButtonObservableValue(Realm realm, CompoundButton button) {
		super(realm, button);
		this.button = button;
		init();
	}
	private void init() {
		this.selectionValue = button.isChecked();
		button.setOnCheckedChangeListener(updateListener);
	}
	public void doSetValue(final Object value) {
		try {
			updating = true;
			boolean oldSelectionValue = selectionValue;
			selectionValue = value == null ? false : ((Boolean) value).booleanValue();
			button.setChecked(selectionValue);
			notifyIfChanged(oldSelectionValue, selectionValue);
		} finally {
			updating = false;
		}
	}
	public Object doGetValue() {
		return Boolean.valueOf(button.isChecked());
	}
	public Object getValueType() {
		return Boolean.TYPE;
	}
	public synchronized void dispose() {
		super.dispose();
		button.setOnCheckedChangeListener(null);
	}
	private void notifyIfChanged(boolean oldValue, boolean newValue) {
		if (oldValue != newValue) {
			fireValueChange(Diffs.createValueDiff(Boolean.valueOf(oldValue), Boolean.valueOf(newValue)));
		}
	}
}
