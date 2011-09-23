package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.UpdateValueStrategy;


public final class UpdateInvertBooleanStrategy extends UpdateValueStrategy {
	@Override
	public Object convert(Object value) {
		Boolean result = Boolean.valueOf(Boolean.FALSE.equals(value));
		return result;
	}
}