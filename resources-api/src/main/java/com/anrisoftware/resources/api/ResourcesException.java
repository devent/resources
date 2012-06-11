package com.anrisoftware.resources.api;

import static java.lang.String.format;

import java.util.MissingResourceException;

/**
 * Exception that is thrown if there was an error loading a resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ResourcesException extends MissingResourceException {

	/**
	 * Sets the cause for this exception with a message.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause.
	 * 
	 * @param key
	 *            the {@link String} key for the missing resource.
	 * 
	 * @param format
	 *            a format {@link String} for the exception message.
	 * 
	 * @param args
	 *            the optional {@link Object}s for the format message.
	 */
	public ResourcesException(Throwable cause, String key, String format,
			Object... args) {
		super(format("%s: %s", cause.getMessage(), format(format, args)), "",
				key);
	}

	/**
	 * Sets the class name, key and a formatted messsage.
	 * 
	 * @param className
	 *            the {@link String} name of the resource class
	 * 
	 * @param key
	 *            the {@link String} key for the missing resource.
	 * 
	 * @param format
	 *            a format {@link String} for the exception message.
	 * 
	 * @param args
	 *            the optional {@link Object}s for the format message.
	 */
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
