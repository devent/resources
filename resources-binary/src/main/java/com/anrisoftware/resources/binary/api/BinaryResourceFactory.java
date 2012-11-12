/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.api;

import java.net.URL;
import java.util.Locale;

/**
 * Factory to create a new binary data resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinaryResourceFactory {

	/**
	 * Creates a new binary resource with the specified name, locale and URL.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this the resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 */
	BinaryResource create(String name, Locale locale, URL url);
}
