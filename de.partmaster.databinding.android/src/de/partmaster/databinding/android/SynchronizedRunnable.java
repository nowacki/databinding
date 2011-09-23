package de.partmaster.databinding.android;

public class SynchronizedRunnable implements Runnable {
	private final Runnable runnable;
	private boolean wasSignalled = false;

	SynchronizedRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		try {
			runnable.run();
		} finally {
			doNotify();
		}
	}

	synchronized public void doWait() {
		while (!wasSignalled) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		wasSignalled = false;
	}

	synchronized public void doNotify() {
		wasSignalled = true;
		notify();
	}
}