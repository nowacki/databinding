package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.observable.Realm;

public class ClockService implements Runnable {

	private final ClockUBean bean;
	private final Realm realm;
	private boolean stopped;

	public ClockService(Realm realm, ClockUBean bean) {
		this.realm = realm;
		this.bean = bean;
	}

	synchronized public void start() {
		stopped = false;
		timerExec();
	}

	private void timerExec() {
		realm.timerExec(1000, this);
	}

	@Override
	public void run() {
		if (ClockMode.RUN == bean.getMode()) {
			bean.setTime(bean.getTime() + 1);
		}
		restart();
	}

	synchronized private void restart() {
		if (!stopped) {
			timerExec();
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	synchronized public void stop() {
		stopped = true;
	}
}