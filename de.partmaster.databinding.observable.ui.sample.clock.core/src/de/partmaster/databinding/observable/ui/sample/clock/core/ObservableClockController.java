package de.partmaster.databinding.observable.ui.sample.clock.core;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.ufacekit.core.ubean.databinding.observables.UBeansObservables;

public class ObservableClockController {

	public void bind(ObservableClockView view, ClockUBean bean) {
		Realm realm = view.getModeSelection().getRealm();
		DataBindingContext context = new DataBindingContext(realm);
		UpdateValueStrategy updateNeverStrategy = new UpdateValueStrategy(
				UpdateValueStrategy.POLICY_NEVER);
		IObservableValue modeProperty = UBeansObservables.observeValue(realm,
				bean, ClockUBean.MODE);

		context.bindValue(view.getTimeText(),
				UBeansObservables.observeValue(realm, bean, ClockUBean.TIME));
		context.bindValue(view.getModeSelection(), modeProperty,
				new UpdateModeFromBooleanStrategy(),
				new UpdateBooleanFromModeStrategy());
		context.bindValue(view.getModeText(), modeProperty,
				updateNeverStrategy, new UpdateStringFromModeStrategy());
		UpdateValueStrategy invert = new UpdateInvertBooleanStrategy();
		context.bindValue(view.getTimeEnabled(), view.getModeSelection(), invert, invert);
	}
}