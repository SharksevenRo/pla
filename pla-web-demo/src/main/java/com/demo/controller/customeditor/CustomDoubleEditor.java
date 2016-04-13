package com.demo.controller.customeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class CustomDoubleEditor extends PropertyEditorSupport {
	private final boolean allowEmpty;

	public CustomDoubleEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
			try {
				setValue(Double.parseDouble(text));
			} catch (Exception ex) {
				//throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	@Override
	public String getAsText() {
		Double value = (Double) getValue();

		return value != null ? value.toString() : null;
	}
}
