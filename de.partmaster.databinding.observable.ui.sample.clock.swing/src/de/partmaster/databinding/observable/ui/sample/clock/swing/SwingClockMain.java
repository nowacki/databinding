package de.partmaster.databinding.observable.ui.sample.clock.swing;


import javax.swing.JFrame;

import org.eclipse.ufacekit.ui.swing.databinding.swing.SwingObservables;

import de.partmaster.databinding.observable.ui.sample.clock.core.ClockService;
import de.partmaster.databinding.observable.ui.sample.clock.core.ClockUBean;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockController;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockView;

/**
 * @author now
 */
public class SwingClockMain {

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		ObservableClockView view = new SwingClockView(frame);
		ClockUBean bean = new ClockUBean();
		new ObservableClockController().bind(view, bean);
		new ClockService(SwingObservables.getRealm(), bean).start();

		frame.pack();
		frame.setVisible(true);
	}
}
