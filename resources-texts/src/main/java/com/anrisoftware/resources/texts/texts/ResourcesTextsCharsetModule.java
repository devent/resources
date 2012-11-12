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
package com.anrisoftware.resources.texts.texts;

import static com.google.inject.name.Names.named;

import java.nio.charset.Charset;

import com.google.inject.AbstractModule;

/**
 * Binds the texts recourses default character set. The default character set is
 * used if no character set is specified in the text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ResourcesTextsCharsetModule extends AbstractModule {

	private final Charset defaultCharset;

	/**
	 * Sets the default character set of the JVM as the default character set
	 * for text resources.
	 * 
	 * @see Charset#defaultCharset()
	 * 
	 * @since 1.3
	 */
	public ResourcesTextsCharsetModule() {
		this(Charset.defaultCharset());
	}

	/**
	 * Sets the specified character set as the default character set for text
	 * resources.
	 * 
	 * @param charset
	 */
	public ResourcesTextsCharsetModule(Charset charset) {
		this.defaultCharset = charset;
	}

	@Override
	protected void configure() {
		bind(Charset.class).annotatedWith(named("texts-default-charset"))
				.toInstance(defaultCharset);
	}

}
