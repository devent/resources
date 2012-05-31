package com.anrisoftware.resources.api;

import static java.lang.String.format;

import java.util.MissingResourceException;

@SuppressWarnings("serial")
public class ResourcesException extends MissingResourceException {

	public ResourcesException(String className, String key, String format,
			Object... args) {
		super(format(format, args), className, key);
	}

	@Override
	public String getMessage() {
		return hasCause() ? createMessageWithCause() : createMessage();
	}

	private boolean hasCause() {
		return getCause() != null;
	}

	private String createMessageWithCause() {
		String message = super.getMessage();
		String causeMessage = getCause().getMessage();
		return format("%s: %s.", message, causeMessage);
	}

	private String createMessage() {
		String message = super.getMessage();
		return format("%s.", message);
	}

}
