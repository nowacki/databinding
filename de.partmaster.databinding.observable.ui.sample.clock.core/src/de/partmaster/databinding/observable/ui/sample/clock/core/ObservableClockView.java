package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.observable.value.IObservableValue;

public interface ObservableClockView {

	IObservableValue getModeSelection();

	IObservableValue getTimeText();

	IObservableValue getModeText();

	IObservableValue getTimeEnabled();
}