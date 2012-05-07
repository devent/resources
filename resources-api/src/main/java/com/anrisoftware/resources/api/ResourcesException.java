package com.anrisoftware.resources.api;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class ResourcesException extends Exception {

	public ResourcesException(Throwable cause, String format, Object... args) {
		super(format(format, args), cause);
	}

	public ResourcesException(String format, Object... args) {
		super(format(format, args));
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
