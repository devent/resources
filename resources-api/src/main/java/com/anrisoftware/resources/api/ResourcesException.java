/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-api.
 *
 * resources-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-api. If not, see <http://www.gnu.org/licenses/>.
 */
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
