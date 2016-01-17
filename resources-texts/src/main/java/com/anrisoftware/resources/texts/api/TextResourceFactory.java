/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts.api;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Factory to create a new text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TextResourceFactory {

	/**
	 * Creates a new text resource which will load the text from the URL.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this text resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param charset
	 *            the {@link Charset} of the resource.
	 * 
	 * @since 1.1
	 */
	TextResource create(String name, Locale locale, URL url, Charset charset);
}
