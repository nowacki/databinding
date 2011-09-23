package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Realm;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Android Implementation Realm.
 * 
 */
public class AndroidRealm extends Realm {
	private static final Handler handler = new Handler(Looper.getMainLooper());
	public static void createDefault() {
		setDefault(new AndroidRealm());
	}
	public boolean isCurrent() {
		return Thread.currentThread() == Looper.getMainLooper().getThread();
	}
	@Override
	public void asyncExec(Runnable runnable) {
		Log.d(getClass().getSimpleName(), "asyncExec(" + runnable + ")");
		handler.post(runnable);
	}
	@Override
	protected void syncExec(final Runnable runnable) {
		Log.d(getClass().getSimpleName(), "syncExec(" + runnable + ")");
		SynchronizedRunnable synchronizedRunnable = new SynchronizedRunnable(
				runnable);
		handler.post(synchronizedRunnable);
		synchronizedRunnable.doWait();

	}
	@Override
	public void timerExec(int milliseconds, Runnable runnable) {
		Log.d(getClass().getSimpleName(), "timerExec(" + milliseconds + ", "
				+ runnable + ")");
		handler.postDelayed(runnable, milliseconds);
	}
}
