package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.UpdateValueStrategy;

public final class UpdateStringFromModeStrategy extends UpdateValueStrategy {
	@Override
	public Object convert(Object value) {
		return value == ClockMode.RUN ? ClockMode.SET.toString() : ClockMode.RUN.toString();
	}
}