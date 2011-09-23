package de.partmaster.databinding.observable.ui.sample.clock.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.ufacekit.ui.swing.databinding.swing.SwingEventConstants;
import org.eclipse.ufacekit.ui.swing.databinding.swing.SwingObservables;

import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockView;

@SuppressWarnings("serial")
public final class SwingClockView extends JPanel implements ObservableClockView {

	final private IObservableValue timeTextObservable;
	final private IObservableValue modeTextObservable;
	final private IObservableValue timeEnabledObservable;
	final private IObservableValue modeSelectionObservable;

	public SwingClockView(JFrame frame) {
		setLayout(new GridBagLayout());

		JTextField timeWidget = new JTextField();
		timeWidget.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints timeWidgetConstraints = new GridBagConstraints();
		timeWidgetConstraints.weightx = 100;
		timeWidgetConstraints.fill = GridBagConstraints.HORIZONTAL;
		add(timeWidget, timeWidgetConstraints);

		JToggleButton modeWidget = new JToggleButton();
		GridBagConstraints modeWidgetConstraints = new GridBagConstraints();
		add(modeWidget, modeWidgetConstraints);

		timeTextObservable = SwingObservables.observeText(timeWidget, SwingEventConstants.Modify);
		timeEnabledObservable = SwingObservables.observeEnabled(timeWidget);
		modeSelectionObservable = SwingObservables.observeSelection(modeWidget);
		modeTextObservable = SwingObservables.observeText(modeWidget);

		frame.getContentPane().add(this);
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