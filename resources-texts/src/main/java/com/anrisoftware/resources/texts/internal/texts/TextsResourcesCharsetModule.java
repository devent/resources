
package com.anrisoftware.resources.texts.internal.texts;

/*-
 * #%L
 * Resources :: Text
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static com.google.inject.name.Names.named;

import java.nio.charset.Charset;

import com.google.inject.AbstractModule;

/**
 * Binds the texts recourses default character set. The default character set is
 * used if no character set is specified in the text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.3
 */
public class TextsResourcesCharsetModule extends AbstractModule {

	private final Charset defaultCharset;

	/**
	 * Sets the default character set of the JVM as the default character set
	 * for text resources.
	 * 
	 * @see Charset#defaultCharset()
	 */
	public TextsResourcesCharsetModule() {
		this(Charset.defaultCharset());
	}

	/**
	 * Sets the specified character set as the default character set for text
	 * resources.
	 * 
	 * @param charset
	 */
	public TextsResourcesCharsetModule(Charset charset) {
		this.defaultCharset = charset;
	}

	@Override
	protected void configure() {
		bind(Charset.class).annotatedWith(named("texts-default-charset"))
				.toInstance(defaultCharset);
	}

}