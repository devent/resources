/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.Map;
import java.util.MissingResourceException;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Exception that is thrown if there was an error loading a resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ResourcesException extends MissingResourceException {

	private final Context<ResourcesException> context;

	/**
	 * @see MissingResourceException#MissingResourceException(String, String,
	 *      String)
	 * 
	 * @since 1.7
	 */
	public ResourcesException(String s, String className, String key) {
		super(s, className, key);
		this.context = new Context<ResourcesException>(this);
		addContext("key", key);
	}

	/**
	 * Adds the context with the specified name.
	 * 
	 * @param name
	 *            the name of the context.
	 * 
	 * @param value
	 *            the context value.
	 * 
	 * @return the context {@link Exception}.
	 * 
	 * @since 1.7
	 */
	public ResourcesException addContext(String name, Object value) {
		return context.addContext(name, value);
	}

	/**
	 * Returns the context of the exception.
	 * 
	 * @return an unmodifiable {@link Map} with the context.
	 * 
	 * @since 1.7
	 */
	public Map<String, Object> getContext() {
		return context.getContext();
	}

	/**
	 * Output the exception message and the context:
	 * 
	 * <pre>
	 * Message of the exception, context:
	 * Aaa := bbb
	 * Ccc := ddd
	 * </pre>
	 * 
	 * @since 1.7
	 */
	@Override
	public String toString() {
		return context.toString();
	}
}
