/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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

import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TextResourceFactory
 */
public interface TextResource extends Resource {

	/**
	 * Returns the character set of this resource.
	 * 
	 * @return the {@link Charset}.
	 * 
	 * @since 1.1
	 */
	Charset getCharset();

	/**
	 * Returns the text {@link String} of the resource.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText() throws ResourcesException;

	/**
	 * Returns a formatted text of the resource.
	 * <p>
	 * The string is formatted as in
	 * {@link String#format(Locale, String, Object...)} with the current text as
	 * the format string and the current locale as the locale.
	 * 
	 * @param args
	 *            the arguments.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 * 
	 * @since 1.2
	 */
	String getFormattedText(Object... args) throws ResourcesException;

}
