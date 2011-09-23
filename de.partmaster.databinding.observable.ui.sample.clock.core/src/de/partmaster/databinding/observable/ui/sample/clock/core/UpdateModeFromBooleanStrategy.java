package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.UpdateValueStrategy;


public final class UpdateModeFromBooleanStrategy extends UpdateValueStrategy {
	@Override
	public Object convert(Object value) {
		ClockMode result = Boolean.TRUE.equals(value) ? ClockMode.RUN : ClockMode.SET;
		return result;
	}
}