package de.partmaster.datadinding.observable.ui.sample.clock.android.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import de.partmaster.datadinding.observable.ui.sample.clock.android.ClockActivity;

public class ClockActivityTest extends
		ActivityInstrumentationTestCase2<ClockActivity> {

	private ClockActivity activity;


	public ClockActivityTest() {
		super(ClockActivity.class.getPackage().getName(), ClockActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		activity = getActivity();
	}

	
	public void testIt() {
		Log.d(getClass().getSimpleName(), "enter");
		assertNotNull(activity);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		Log.d(getClass().getSimpleName(), "leave");
	}
}
