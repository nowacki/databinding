/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package de.partmaster.databinding.android;

import org.eclipse.core.databinding.observable.IObservable;

import android.view.View;

/**
 * {@link IObservable} observing an SWT widget.
 *
 * @since 1.0
 *
 */
public interface IAndroidObservable extends IObservable {

	/**
	 * Returns the widget of this observable
	 *
	 * @return the widget
	 */
	public View getWidget();

}
