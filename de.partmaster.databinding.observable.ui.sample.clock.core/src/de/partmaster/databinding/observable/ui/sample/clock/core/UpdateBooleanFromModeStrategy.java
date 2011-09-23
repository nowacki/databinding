package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.UpdateValueStrategy;


public final class UpdateBooleanFromModeStrategy extends UpdateValueStrategy {
	@Override
	public Object convert(Object value) {
		Boolean result = Boolean.valueOf(ClockMode.RUN.equals(value));
		return result;
	}
}