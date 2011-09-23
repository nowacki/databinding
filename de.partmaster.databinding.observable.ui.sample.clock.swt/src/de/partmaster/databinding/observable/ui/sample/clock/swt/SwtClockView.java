package de.partmaster.databinding.observable.ui.sample.clock.swt;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockView;

public final class SwtClockView extends Composite implements ObservableClockView {

	final private IObservableValue timeTextObservable;
	final private IObservableValue modeTextObservable;
	final private IObservableValue timeEnabledObservable;
	final private IObservableValue modeSelectionObservable;

	public SwtClockView(Composite parent, GridData layoutData) {
		super(parent, SWT.NONE);
		setLayoutData(layoutData);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(this);

		Text timeWidget = new Text(this, SWT.RIGHT);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(timeWidget);

		Button modeWidget = new Button(this, SWT.TOGGLE);
		GridDataFactory.fillDefaults().applyTo(modeWidget);

		timeTextObservable = SWTObservables.observeText(timeWidget, SWT.Modify);
		modeSelectionObservable = SWTObservables.observeSelection(modeWidget);
		modeTextObservable = SWTObservables.observeText(modeWidget);
		timeEnabledObservable = SWTObservables.observeEnabled(timeWidget);
	}

	@Override
	public IObservableValue getModeSelection() {
		return modeSelectionObservable;
	}

	@Override
	public IObservableValue getTimeText() {
		return timeTextObservable;
	}

	@Override
	public IObservableValue getModeText() {
		return modeTextObservable;
	}

	@Override
	public IObservableValue getTimeEnabled() {
		return timeEnabledObservable;
	}
}