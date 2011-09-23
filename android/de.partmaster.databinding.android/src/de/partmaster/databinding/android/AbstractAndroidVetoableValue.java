/*******************************************************************************
 * Copyright (c) 2006 The Pampered Chef, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The Pampered Chef, Inc. - initial API and implementation
 ******************************************************************************/

package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.AbstractVetoableValue;

import android.view.View;

/**
 * NON-API - An abstract superclass for vetoable values that gurantees that the
 * observable will be disposed when the control to which it is attached is
 * disposed.
 *
 * @since 1.0
 */
public abstract class AbstractAndroidVetoableValue extends AbstractVetoableValue implements IAndroidObservableValue {

	private final View widget;

	/**
	 * Standard constructor for an SWT VetoableValue.  Makes sure that
	 * the observable gets disposed when the SWT widget is disposed.
	 *
	 * @param widget
	 */
	protected AbstractAndroidVetoableValue(View widget) {
		this(AndroidRealm.getDefault(), widget);
	}

	/**
	 * Constructs a new instance for the provided <code>realm</code> and <code>widget</code>.
	 *
	 * @param realm
	 * @param widget
	 */
	protected AbstractAndroidVetoableValue(Realm realm, View widget) {
		super(realm);
		this.widget = widget;
		if (widget == null) {
			throw new IllegalArgumentException("The widget parameter is null."); //$NON-NLS-1$
		}
//		widget.addDisposeListener(disposeListener);
	}

//	private DisposeListener disposeListener = new DisposeListener() {
//		public void widgetDisposed(DisposeEvent e) {
//			AbstractSWTVetoableValue.this.dispose();
//		}
//	};

	/**
	 * @return Returns the widget.
	 */
	public View getWidget() {
		return widget;
	}
}
