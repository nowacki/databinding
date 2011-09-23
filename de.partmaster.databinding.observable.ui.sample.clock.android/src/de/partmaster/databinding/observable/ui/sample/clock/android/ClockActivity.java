package de.partmaster.databinding.observable.ui.sample.clock.android;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ToggleButton;
import de.partmaster.databinding.android.AndroidEventConstants;
import de.partmaster.databinding.android.AndroidObservables;
import de.partmaster.databinding.observable.ui.sample.clock.core.ClockMode;
import de.partmaster.databinding.observable.ui.sample.clock.core.ClockService;
import de.partmaster.databinding.observable.ui.sample.clock.core.ClockUBean;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockController;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockView;
import de.partmaster.databinding.observable.ui.sample.clock.android.R;

public class ClockActivity extends Activity implements ObservableClockView {

	private IObservableValue timeTextObservable;
	private IObservableValue timeEnabledObservable;
	private IObservableValue modeSelectionObservable;
	private IObservableValue modeTextObservable;
	private ClockService clockService;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		unlockScreen();
		setContentView(R.layout.main);
		ToggleButton modeWidget = (ToggleButton) findViewById(R.id.mode);
		modeWidget.setTextOn(ClockMode.SET.name());
		modeWidget.setTextOff(ClockMode.RUN.name());
		EditText timeWidget = (EditText) findViewById(R.id.time);

		timeTextObservable = AndroidObservables.observeText(timeWidget,
				AndroidEventConstants.Modify);
		timeEnabledObservable = AndroidObservables.observeEnabled(timeWidget);
		modeTextObservable = new WritableValue(AndroidObservables.getRealm());
		modeSelectionObservable = AndroidObservables.observeSelection(modeWidget);

		ClockUBean bean = new ClockUBean();
		new ObservableClockController().bind(this, bean);
		clockService = new ClockService(AndroidObservables.getRealm(), bean);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		clockService.start();
	}
	
	@Override
	protected void onStop() {
		clockService.stop();
		super.onStop();
	}

	private void unlockScreen() {
		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager.newKeyguardLock(getClass()
				.getName());
		lock.disableKeyguard();
	}

	@Override
	public IObservableValue getModeSelection() {
		return modeSelectionObservable;
	}

	@Override
	public IObservableValue getTimeText() {
		return timeTextObservable;
	}

	@Override
	public IObservableValue getModeText() {
		return modeTextObservable;
	}

	@Override
	public IObservableValue getTimeEnabled() {
		return timeEnabledObservable;
	}

}