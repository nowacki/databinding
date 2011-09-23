package de.partmaster.databinding.observable.ui.sample.clock.swt;

import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.partmaster.databinding.observable.ui.sample.clock.core.ClockService;
import de.partmaster.databinding.observable.ui.sample.clock.core.ClockUBean;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockController;
import de.partmaster.databinding.observable.ui.sample.clock.core.ObservableClockView;

public class SwtClockMain {

	public static void main(String[] args) {

		Display display = Display.getDefault();
		Composite shell = new Shell(display);
		GridLayoutFactory.fillDefaults().applyTo(shell);

		GridData gridData = GridDataFactory.fillDefaults().grab(true, false).create();
		ObservableClockView view = new SwtClockView(shell, gridData);

		ClockUBean bean = new ClockUBean();
		new ObservableClockController().bind(view, bean);
		new ClockService(SWTObservables.getRealm(display), bean).start();

		shell.pack();
		shell.setVisible(true);
		for (;;) {
			if (display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
