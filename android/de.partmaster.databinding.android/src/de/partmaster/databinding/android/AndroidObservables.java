package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AndroidObservables {

	
	private static Realm realm = new AndroidRealm();

	public static Realm getRealm() {
		return realm;
	}

	public static IObservableValue observeEnabled(View control) {
		return new ControlObservableValue(control, AndroidProperties.ENABLED);
	}

	public static IObservableValue observeSelection(CompoundButton button) {
			return new ButtonObservableValue(button);
	}

	public static IObservableValue observeText(TextView control) {
		return new TextViewObservableText(control);
	}

	public static IObservableValue observeToggleText(ToggleButton control) {
		return new ToggleButtonObservableText(control);
	}

	public static IObservableValue observeTextOff(ToggleButton control) {
		return new ButtonObservableTextOff(control);
	}

	public static IObservableValue observeText(TextView control,
			int updateEventType) {
		return new TextObservableValue(control, updateEventType);
	}

}
