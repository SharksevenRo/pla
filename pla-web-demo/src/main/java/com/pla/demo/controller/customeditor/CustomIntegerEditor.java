package com.pla.demo.controller.customeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * <p>
 * Description:
 * </p>
 * Copyright: Copyright (c) 2011 Company: WDHL
 * 
 * @author machengyuan
 * @version 1.0 2012-1-4
 */
public class CustomIntegerEditor extends PropertyEditorSupport {
	private final boolean allowEmpty;

	public CustomIntegerEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
			try {
				setValue(Integer.parseInt(text));
			} catch (Exception ex) {
				//throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	@Override
	public String getAsText() {
		Integer value = (Integer) getValue();

		return value != null ? value.toString() : null;
	}
}
