/*******************************************************************************
 * Copyright (c) 2007, Angelo Zerr and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - Initial API and implementation
 *******************************************************************************/
package de.partmaster.databinding.android;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.observable.Diffs;

import android.graphics.Color;
import android.view.View;

/**
 * ISwingObservableValue implementation to observe properties of a Swing
 * Container This class can observe enabled, visible, tooltipText, foreground,
 * background and font properties.
 *
 * @since 1.0
 */
public class ControlObservableValue extends AbstractAndroidObservableValue {
	private final View control;

	private final String attribute;

	private Object valueType;

	/**
	 * Map of supported properties.
	 */
	private static final Map<String,Object> SUPPORTED_ATTRIBUTES = new HashMap<String,Object>();
	static {
		SUPPORTED_ATTRIBUTES.put(AndroidProperties.ENABLED, Boolean.TYPE);
		SUPPORTED_ATTRIBUTES.put(AndroidProperties.VISIBLE, Boolean.TYPE);
		SUPPORTED_ATTRIBUTES.put(AndroidProperties.TOOLTIP_TEXT, String.class);
		SUPPORTED_ATTRIBUTES.put(AndroidProperties.FOREGROUND, Color.class);
		SUPPORTED_ATTRIBUTES.put(AndroidProperties.BACKGROUND, Color.class);
		//SUPPORTED_ATTRIBUTES.put(AndroidProperties.FONT, Font.class);
	}

	/**
	 * @param control
	 * @param attribute
	 */
	public ControlObservableValue(View control, String attribute) {
		super(control);
		this.control = control;
		this.attribute = attribute;
		if (SUPPORTED_ATTRIBUTES.keySet().contains(attribute)) {
			this.valueType = SUPPORTED_ATTRIBUTES.get(attribute);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void doSetValue(Object value) {
		Object oldValue = doGetValue();
		if (attribute.equals(AndroidProperties.ENABLED)) {
			control.setEnabled(((Boolean) value).booleanValue());
//		} else if (attribute.equals(AndroidProperties.VISIBLE)) {
//			control.setVisible(((Boolean) value).booleanValue());
		} else if (attribute.equals(AndroidProperties.TOOLTIP_TEXT)) {
//			if (control instanceof JComponent) {
//				((JComponent) control).setToolTipText((String) value);
//			} else {
				throw new IllegalArgumentException("Widget [" + control.getClass().getName() + "] can not support toolTipText."); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//		} else if (attribute.equals(AndroidProperties.FOREGROUND)) {
//			control.setForeground((Color) value);
//		} else if (attribute.equals(AndroidProperties.BACKGROUND)) {
//			control.setBackground((Color) value);
//		} else if (attribute.equals(AndroidProperties.FONT)) {
//			control.setFont((Font) value);
		}
		fireValueChange(Diffs.createValueDiff(oldValue, value));
	}

	public Object doGetValue() {
		if (attribute.equals(AndroidProperties.ENABLED)) {
			return control.isEnabled() ? Boolean.TRUE : Boolean.FALSE;
		}
//		if (attribute.equals(AndroidProperties.VISIBLE)) {
//			return control.isVisible() ? Boolean.TRUE : Boolean.FALSE;
//		}
//		if (attribute.equals(AndroidProperties.TOOLTIP_TEXT)) {
//			if (control instanceof JComponent) {
//				((JComponent) control).getToolTipText();
//			} else {
//				throw new IllegalArgumentException("Widget [" + control.getClass().getName() + "] can not support toolTipText."); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//		}
//		if (attribute.equals(AndroidProperties.FOREGROUND)) {
//			return control.getForeground();
//		}
		if (attribute.equals(AndroidProperties.BACKGROUND)) {
			return control.getBackground();
		}
//		if (attribute.equals(AndroidProperties.FONT)) {
//			return control.getFont();
//		}

		return null;
	}

	public Object getValueType() {
		return valueType;
	}

}