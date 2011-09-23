package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.ufacekit.core.ubean.UArrayBean;

public class ClockUBean extends UArrayBean {

	public static final int TIME = 1;
	public static final int MODE = 2;

	public ClockUBean() {
		setMode(ClockMode.RUN);
		setTime(0);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getValueType(int featureId) {
		switch (featureId) {
		case TIME:
			return (T) long.class;
		case MODE:
			return (T) ClockMode.class;
		default:
			return null;
		}
	}

	public void setTime(long value) {
		set(TIME, Long.valueOf(value));
	}

	public long getTime() {
		return ((Long) get(TIME)).longValue();
	}

	public void setMode(ClockMode value) {
		set(MODE, value);
	}

	public ClockMode getMode() {
		return (ClockMode) get(MODE);
	}
	
	@Override
	public void set(int featureId, Object value) {
		super.set(featureId, value);
	}
	
	@Override
	public <V> V get(int featureId) {
		V result = super.get(featureId);
		return result;
	}

}